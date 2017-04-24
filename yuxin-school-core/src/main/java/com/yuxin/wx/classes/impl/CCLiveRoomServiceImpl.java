package com.yuxin.wx.classes.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.BaseServiceImpl;
import net.sf.json.JSONObject;
import net.sf.json.util.WebUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuxin.wx.api.classes.ILiveRoomService;
import com.yuxin.wx.api.company.ICompanyLiveConfigService;
import com.yuxin.wx.api.system.ISysConfigTeacherService;
import com.yuxin.wx.common.CCLiveInterface;
import com.yuxin.wx.common.LiveRoomConstant;
import com.yuxin.wx.model.classes.ClassModuleLesson;
import com.yuxin.wx.model.company.CompanyLiveConfig;
import com.yuxin.wx.model.course.LiveOpenCourse;
import com.yuxin.wx.model.system.SysConfigTeacher;
import com.yuxin.wx.util.APIServiceFunction;
import com.yuxin.wx.util.HttpPostRequest;

@Service
public class CCLiveRoomServiceImpl extends BaseServiceImpl implements ILiveRoomService{
	Log log = LogFactory.getLog("log");
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	 
	@Autowired
	private ISysConfigTeacherService sysConfigTeacherServiceImpl;
	@Autowired
	private ICompanyLiveConfigService companyLiveConfigServiceImpl;

	@Override
	public String createLiveRoom(Object o, Integer companyId) {
		ClassModuleLesson lesson = null;
		LiveOpenCourse open = null;
		Map<String,String> param = null;
		Integer lessonId = 0;

		CompanyLiveConfig config = new CompanyLiveConfig();
		config.setCompanyId(companyId);
		List<CompanyLiveConfig> configs = companyLiveConfigServiceImpl.findCompanyLiveConfigByPage(config);
		
		if(configs != null && configs.size() > 0){
			config = configs.get(0);
			if(!config.getLiveType().equals(4)){
				config = null;
			}
		}
		
		//普通课程
		if(o instanceof ClassModuleLesson){
			lesson = (ClassModuleLesson) o;
			//获取当前需要传递的参数（课程）
			param = lessonConvertLiveRoom((ClassModuleLesson) o, config);
			lessonId = lesson.getId();
		}
		//公开课
		if(o instanceof LiveOpenCourse){
			open = (LiveOpenCourse) o;
			//获取当前需要传递的参数（课程）
			param = openConvertLiveRoom((LiveOpenCourse) o, config);
			lessonId = open.getId();
		}
		
		long timestimp = System.currentTimeMillis();
		String thqs = APIServiceFunction.createHashedQueryString(param,timestimp
				, config.getPassword() != null ? config.getPassword() : CCLiveInterface.API_KEY);
		//获取创建直播课的URL
		String url = CCLiveInterface.CREATE + thqs;
		
		String detail;
		try {
			detail = HttpPostRequest.get(url);
			log.info("调用cc创建直播教室接口,返回信息如下：" + detail);
			if("OK".equals(JSONObject.fromObject(detail).get("result"))){
				String jsonStr = JSONObject.fromObject(detail).getString("room");
				String roomId = JSONObject.fromObject(jsonStr).getString("id");
				param.clear();
				param = getLiveAddress(roomId, config.getLoginName() != null ?
						config.getLoginName() : CCLiveInterface.USER_ID);
				long timestimps = System.currentTimeMillis();
				String thqss = APIServiceFunction.createHashedQueryString(param,timestimps
						, config.getPassword() != null ? config.getPassword() : CCLiveInterface.API_KEY);
				
				String ulive = CCLiveInterface.LIVE_ADDRESS + thqss;
				String ress = HttpPostRequest.get(ulive);
				log.info("调用cc创建直播教室接口,返回信息如下：" + ress);
				if("OK".equals(JSONObject.fromObject(ress).getString("result"))){
					JSONObject newj = JSONObject.fromObject(ress);
					log.info(newj);
					String[] arg = (newj.getString("assistantLoginUrl")).split("[?]");
					String assisurl = arg[0] + "/login?" + arg[1];
					String resu = "{\"roomid\":\"" + newj.getString("roomId") + "\"";
					resu += ",\"t\":\"" + newj.getString("clientLoginUrl") + "\"";
					resu += ",\"a\":\"" + assisurl + "\"";
					resu += ",\"s\":\"" + newj.getString("viewUrl") + "\"}";
					log.info(resu);
					return resu;
				}else {
					return null;
				}
			}else{
				return null;
			}
		} catch (Exception e) {
			log.error("调用cc创建直播教室接口失败,"+e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String updateLiveRoom(Object o, Integer companyId) {

		CompanyLiveConfig config = new CompanyLiveConfig();
		config.setCompanyId(companyId);
		List<CompanyLiveConfig> configs = companyLiveConfigServiceImpl.findCompanyLiveConfigByPage(config);
		if(configs != null && configs.size() > 0){
			config = configs.get(0);
		}
		//获取当前需要传递的参数
		Map<String,String> param = null;
		//普通课程
		if(o instanceof ClassModuleLesson){
			param = lessonConvertLiveRoom((ClassModuleLesson) o, config);
		}
		//公开课
		if(o instanceof LiveOpenCourse){
			param = openConvertLiveRoom((LiveOpenCourse) o, config);
		}
		long timestimp = System.currentTimeMillis();
		String thqs = APIServiceFunction.createHashedQueryString(param,timestimp
				, config.getPassword() != null ? config.getPassword() : CCLiveInterface.API_KEY);
		//获取创建直播课的URL
		String url = CCLiveInterface.UPDATE + thqs;
		
		String detail;
		try {
			detail = HttpPostRequest.get(url);
			log.info("调用cc更新直播教室接口,返回信息如下：" + detail);
			if("OK".equals(JSONObject.fromObject(detail).get("result"))){
				return "OK";
			}else{
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("调用cc更新直播教室接口失败,"+e.getMessage());
			return null;
		}
	}

	@Override
	public String deleteLiveRoom(Object o) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 将本系统课程的bean转换为展示互动传递的参数
	 */
	@Override
	public Map<String, String> lessonConvertLiveRoom(ClassModuleLesson cml, CompanyLiveConfig config) {
		
		//根据公司Id获取对应的直播的相关配置
		
		Map<String,String> param = new HashMap<String, String>();
		
		//如果当前为修改状态
		if(cml != null && cml.getLiveroomIdGh() != null && cml.getLiveroomIdGh().length() > 0){
			param.put("roomid", cml.getLiveroomIdGh());
			param.put("userid", config.getLoginName() != null ? config.getLoginName() : CCLiveInterface.USER_ID);
			param.put("name", cml.getLessonName());
			param.put("desc", cml.getLessonName());
			param.put("authtype", "0");
			param.put("publisherpass", config.getTeacherToken() != null ? config.getTeacherToken() : CCLiveInterface.TEACHER_PASS);
			param.put("assistantpass", config.getAssistantToken() != null ? config.getAssistantToken() : CCLiveInterface.ASSISTANT_PASS);
			param.put("playpass", config.getStudentWebToken() != null ? config.getStudentWebToken() : CCLiveInterface.STUDENT_PASS);
			param.put("barrage", cml.getBarrage()+"");
			param.put("templatetype", cml.getModetype()+"");
		}else{
			param.put("userid", config.getLoginName() != null ? config.getLoginName() : CCLiveInterface.USER_ID);
			param.put("name", cml.getLessonName());
			param.put("desc", cml.getLessonName());
			param.put("templatetype", cml.getModetype()+"");
			param.put("authtype", "0");
			param.put("publisherpass", config.getTeacherToken() != null ? config.getTeacherToken() : CCLiveInterface.TEACHER_PASS);
			param.put("assistantpass", config.getAssistantToken() != null ? config.getAssistantToken() : CCLiveInterface.ASSISTANT_PASS);
			param.put("playpass", config.getStudentWebToken() != null ? config.getStudentWebToken() : CCLiveInterface.STUDENT_PASS);
			param.put("barrage", cml.getBarrage()+"");//弹幕0：不开启，1：开启
			param.put("foreignpublish", "0");//第三方推流0：不开启，1：开启
			param.put("openlowdelaymode", "1");//直播低延时模式0：关闭，1：开启
			param.put("showusercount", "1");//显示在线人数0：不显示，1：显示
			param.put("openhostmode", "0");//开启主持人模式0：不开启，1：开启
		}
		param.put("checkurl", "http://manage.yunduoketang.com/classModule/CCLiveCheck");
		return param;
	}

	@Override
	public Map<String, String> openConvertLiveRoom(LiveOpenCourse open, CompanyLiveConfig config) {
		
		Map<String,String> param = new HashMap<String, String>();
		
		//如果当前为修改状态
		if(open != null && open.getLiveroomIdGh() != null && open.getLiveroomIdGh().length() > 0){
			param.put("roomid", open.getLiveroomIdGh());
			param.put("userid", config.getLoginName() != null ? config.getLoginName() : CCLiveInterface.USER_ID);
			param.put("name", open.getOpenCourseName());
			param.put("desc", open.getOpenCourseName());
			param.put("authtype", "0");
			param.put("publisherpass", config.getTeacherToken() != null ? config.getTeacherToken() : CCLiveInterface.TEACHER_PASS);
			param.put("assistantpass", config.getAssistantToken() != null ? config.getAssistantToken() : CCLiveInterface.ASSISTANT_PASS);
			param.put("playpass", config.getStudentWebToken() != null ? config.getStudentWebToken() : CCLiveInterface.STUDENT_PASS);
			param.put("barrage", open.getBarrage()+"");
		}else{
			param.put("userid", config.getLoginName() != null ? config.getLoginName() : CCLiveInterface.USER_ID);
			param.put("name", open.getOpenCourseName());
			param.put("desc", open.getOpenCourseName());
			param.put("templatetype", open.getModetype()+"");
			param.put("authtype", "0");
			param.put("publisherpass", config.getTeacherToken() != null ? config.getTeacherToken() : CCLiveInterface.TEACHER_PASS);
			param.put("assistantpass", config.getAssistantToken() != null ? config.getAssistantToken() : CCLiveInterface.ASSISTANT_PASS);
			param.put("playpass", config.getStudentWebToken() != null ? config.getStudentWebToken() : CCLiveInterface.STUDENT_PASS);
			param.put("barrage", open.getBarrage()+"");//弹幕0：不开启，1：开启
			param.put("foreignpublish", "0");//第三方推流0：不开启，1：开启
			param.put("openlowdelaymode", "1");//直播低延时模式0：关闭，1：开启
			param.put("showusercount", "1");//显示在线人数0：不显示，1：显示
			param.put("openhostmode", "0");//开启主持人模式0：不开启，1：开启
		}		
		param.put("checkurl", "http://manage.yunduoketang.com/classModule/CCLiveCheck");
		return param;
	}
	
	private Map<String, String> getLiveAddress(String roomid,String userid){
		Map<String, String> map = new HashMap<String, String>();
		map.put("roomid", roomid);
		map.put("userid", userid);
		return map;
	}

	@Override
	public Object lessonConvertLiveRoom(ClassModuleLesson cml, Integer companyId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object openConvertLiveRoom(LiveOpenCourse open, Integer companyId) {
		// TODO Auto-generated method stub
		return null;
	}
}
