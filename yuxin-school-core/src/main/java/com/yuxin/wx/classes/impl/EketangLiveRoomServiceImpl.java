package com.yuxin.wx.classes.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.BaseServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuxin.wx.api.classes.ILiveRoomService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.system.ISysConfigTeacherService;
import com.yuxin.wx.common.Constant;
import com.yuxin.wx.common.LiveRoomConstant;
import com.yuxin.wx.model.classes.ClassModuleLesson;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.CompanyLiveConfig;
import com.yuxin.wx.model.course.LiveOpenCourse;
import com.yuxin.wx.model.system.SysConfigTeacher;
import com.yuxin.wx.util.DateUtil;
import com.yuxin.wx.util.HttpPostRequest;
import com.yuxin.wx.util.MD5;
import com.yuxin.wx.vo.system.TeacherVo;

@Service
public class EketangLiveRoomServiceImpl extends BaseServiceImpl implements ILiveRoomService {
	@Autowired
	private ISysConfigTeacherService sysConfigTeacherServiceImpl;
	@Autowired
	private ICompanyService companyServiceImpl;
	
	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	private Log log = LogFactory.getLog("log");

	@Override
	public String createLiveRoom(Object o, Integer companyId) {
		ClassModuleLesson lesson = null;
		LiveOpenCourse open = null;
		Map<String,Object> param = null;
		
		String url = null;
		//普通课程
		if(o instanceof ClassModuleLesson){
			lesson = (ClassModuleLesson) o;
			//获取当前需要传递的参数（课程）
			param = lessonConvertLiveRoom((ClassModuleLesson) o, companyId);
			
			//如果该课次还没有创建直播教室，则走创建直播教室接口，否则修改
			if(lesson.getLiveroomIdGh() != null && lesson.getLiveroomIdGh().length() > 0){
				url = Constant.EKETANG_SERVER_ADDRESS + LiveRoomConstant.UPDATE_LIVEROOM;
			}else{
				url =  Constant.EKETANG_SERVER_ADDRESS + LiveRoomConstant.CREATELIVEROOM;
			}
			log.info("当前创建E课堂直播的课程ID为："+lesson.getId());
		}
		//公开课
		if(o instanceof LiveOpenCourse){
			open = (LiveOpenCourse) o;
			//获取当前需要传递的参数（课程）
			param = openConvertLiveRoom((LiveOpenCourse) o, companyId);
			
			//如果该课次还没有创建直播教室，则走创建直播教室接口，否则修改
			if(open.getLiveroomIdGh() != null && open.getLiveroomIdGh().length() > 0){
				url = Constant.EKETANG_SERVER_ADDRESS + LiveRoomConstant.UPDATE_LIVEROOM;
			}else{
				url =  Constant.EKETANG_SERVER_ADDRESS + LiveRoomConstant.CREATELIVEROOM;
			}
			log.info("当前创建E课堂直播的课程ID为："+open.getId());
		}
				
		
		String detail;
		try {
			detail = HttpPostRequest.post(url, param);
			log.info("调用E课堂创建直播教室接口,返回信息如下：" + detail );
			return detail;
		} catch (Exception e) {
			log.error("调用E课堂创建直播教室接口失败",e);
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String updateLiveRoom(Object o, Integer companyId) {
		return null;
	}

	@Override
	public String deleteLiveRoom(Object o) {
		return null;
	}

	@Override
	public Map<String, Object> lessonConvertLiveRoom(ClassModuleLesson cml, Integer companyId) {
		
		Map<String, String> map = getEketangAccount(cml.getTeacherUrlGh(), companyId);
		
		//查询 光慧直播 id
		String customer = map.get("customer");
		long timestamp = System.currentTimeMillis();
		String secretKey = map.get("secretKey");
		
		String str = MD5.getMD5ofStr(customer+timestamp+secretKey);
		str = str.substring(0,10)+str.substring(str.length() - 10);
		
		//教师 和 助教 信息
		SysConfigTeacher teacher = null;
		SysConfigTeacher assistant = null;
		if(cml.getTeachers() != null && cml.getTeachers().length() > 0){
			teacher = sysConfigTeacherServiceImpl.findSysConfigTeacherById(Integer.parseInt(cml.getTeachers()));
		}
		if(cml.getAssistants() != null && cml.getAssistants().length() > 0){
			assistant = sysConfigTeacherServiceImpl.findSysConfigTeacherById(Integer.parseInt(cml.getAssistants()));
		}
		
		TeacherVo tv = null;
		TeacherVo av = null;
		List<TeacherVo> avs = new ArrayList<TeacherVo>();
		List<TeacherVo> tvs = new ArrayList<TeacherVo>();
		if(teacher != null && teacher.getId() != null){
			tv = new TeacherVo(cml.getTeachers(),teacher.getName(),teacher.getName(),teacher.getHeadpicUrl(),"",teacher.getResume());
			tvs.add(tv);
		}
		if(assistant != null && assistant.getId() != null){
			av = new TeacherVo(cml.getAssistants(),assistant.getName(),assistant.getName(),assistant.getHeadpicUrl(),"",assistant.getResume());
			avs.add(av);
		}
		
		String teachers = com.alibaba.fastjson.JSONArray.toJSONString(tvs);
		String assistants = com.alibaba.fastjson.JSONObject.toJSONString(avs);
		
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("id", cml.getLiveRoom());
		param.put("title", cml.getLessonName());
		param.put("introduce", cml.getLessonName());
		
		//判断是否支持手机端
		Integer supportMobile = cml.getSupportMobile();
		if(null!=supportMobile&&!"".equals(supportMobile)){
			if(supportMobile == 1){
				param.put("supportMobile", "1");
				param.put("liveMode", "2");
			}else{
				param.put("supportMobile", "0");
				param.put("liveMode", "1");
			}
		}
		//学生不需要鉴权
		param.put("fee", 1);
		
		String day=format.format(cml.getLessonDate());
		 String startTime = cml.getLessonTimeStart();
		 String endTime = cml.getLessonTimeEnd();
		 long beginTimeLong = DateUtil.parseDateTime(day,startTime);
		 long endTimeLong = DateUtil.parseDateTime(day,endTime);
		 
		 param.put("beginTime", beginTimeLong);
		 param.put("endTime", endTimeLong);
		
		
		param.put("customer", customer);
		param.put("timestamp", timestamp);
		param.put("s", str);
		
		param.put("teachers", teachers);
		param.put("assistants", assistants);
		
		return param;
		
	}

	@Override
	public Map<String, Object> openConvertLiveRoom(LiveOpenCourse open, Integer companyId) {
		
		Map<String, String> map = getEketangAccount(open.getTeacherUrlGh(), companyId);
		
		//查询 光慧直播 id
		String customer = map.get("customer");
		long timestamp = System.currentTimeMillis();
		String secretKey = map.get("secretKey");
		
		String str = MD5.getMD5ofStr(customer+timestamp+secretKey);
		str = str.substring(0,10)+str.substring(str.length() - 10);
		
		Map<String,Object> param = new HashMap<String, Object>();
		if(open.getLiveRoom() == null || open.getLiveRoom() == ""){
			param.put("id", open.getId());
		}else{
			param.put("id", open.getLiveRoom());
		}
		param.put("title", open.getOpenCourseName());
		param.put("introduce", open.getOpenCourseName());
		//判断是否支持手机端
		Integer supportMobile = open.getSupportMobile();
		if(supportMobile == 1){
			param.put("supportMobile", "1");
			param.put("liveMode", "2");
		}else{
			param.put("supportMobile", "0");
			param.put("liveMode", "1");
		}
		
		//教师 和 助教 信息
		SysConfigTeacher teacher = null;
		SysConfigTeacher assistant = null;
		
		if(open.getTeacherId() != null){
			teacher = sysConfigTeacherServiceImpl.findSysConfigTeacherById(open.getTeacherId());
		}
		if(open.getAssistantId() != null && !open.getAssistantId().equals(-1)){
			assistant = sysConfigTeacherServiceImpl.findSysConfigTeacherById(open.getAssistantId());
		}
		
		TeacherVo tv = null;
		TeacherVo av = null;
		List<TeacherVo> avs = new ArrayList<TeacherVo>();
		List<TeacherVo> tvs = new ArrayList<TeacherVo>();
		if(teacher != null){
			tv = new TeacherVo(open.getTeacherId().toString(),teacher.getName(),teacher.getName(),teacher.getHeadpicUrl(),"",teacher.getResume());
			tvs.add(tv);
		}
		if(assistant != null){
			av = new TeacherVo(open.getAssistantId().toString(),assistant.getName(),assistant.getName(),assistant.getHeadpicUrl(),"",assistant.getResume());
			avs.add(av);
		}
		String teachers = com.alibaba.fastjson.JSONArray.toJSONString(tvs);
		String assistants = com.alibaba.fastjson.JSONObject.toJSONString(avs);
		
		
		//学生不需要鉴权
		param.put("fee", 1);
		
		 String day = format.format(open.getStartOpenData());
		 String end = format.format(open.getEndOpenData());
		 String startTime = open.getStartTime();
		 String endTime = open.getEndTime();
		 long beginTimeLong = DateUtil.parseDateTime(day,startTime);
		 long endTimeLong = DateUtil.parseDateTime(end,endTime);
		 
		 param.put("beginTime", beginTimeLong);
		 param.put("endTime", endTimeLong);
		
		
		param.put("customer", customer);
		param.put("timestamp", timestamp);
		param.put("s", str);
		
		param.put("teachers", teachers);
		if(assistant != null){
			param.put("assistants", assistants);
		}
		return param;
	}
	
	/**
	 * 根据条件获取对应的E课堂的账号
	 * @param teacherUrl
	 * @param companyId
	 * @return
	 */
	public Map<String, String> getEketangAccount(String teacherUrl, Integer companyId){
		Map<String, String> map = new HashMap<String, String>();
		String customer = null;
		String secretKey = null;
		
		if(teacherUrl != null && teacherUrl.length() > 0 && teacherUrl.indexOf("customer") > 0){
			String subString = teacherUrl.substring(teacherUrl.indexOf("customer"));
			customer = subString.substring(subString.indexOf("customer")+9, subString.indexOf("&"));
			if(customer != null && customer.length() > 0 && "yxkj".equals(customer)){
				secretKey = Constant.CHARGE_EKETANG_SECRETKEY;
			}else{
				secretKey = Constant.FREE_EKETANG_SECRETKEY;
			}
		}else{
			Company company = companyServiceImpl.findCompanyById(companyId);
			if(company != null && company.getBuyFlag().equals(1)){
				customer = Constant.CHARGE_EKETANG_USER;
				secretKey = Constant.CHARGE_EKETANG_SECRETKEY;
			}else{
				customer = Constant.FREE_EKETANG_USER;
				secretKey = Constant.FREE_EKETANG_SECRETKEY;
			}
		}
		map.put("customer", customer);
		map.put("secretKey", secretKey);
		log.info("当前公司E课堂的配置信息,customer:"+customer+", secretKey:"+secretKey);
		
		return map;
	}

	@Override
	public Object lessonConvertLiveRoom(ClassModuleLesson cml,
			CompanyLiveConfig config) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object openConvertLiveRoom(LiveOpenCourse open,
			CompanyLiveConfig config) {
		// TODO Auto-generated method stub
		return null;
	}

}
