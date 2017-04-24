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
import com.yuxin.wx.common.LiveRoomConstant;
import com.yuxin.wx.model.classes.ClassModuleLesson;
import com.yuxin.wx.model.company.CompanyLiveConfig;
import com.yuxin.wx.model.course.LiveOpenCourse;
import com.yuxin.wx.model.system.SysConfigTeacher;
import com.yuxin.wx.util.HttpPostRequest;

@Service
public class GenseeLiveRoomServiceImpl extends BaseServiceImpl implements ILiveRoomService{
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
		Map<String,Object> param = null;
		Integer lessonId = 0;
		
		//普通课程
		if(o instanceof ClassModuleLesson){
			lesson = (ClassModuleLesson) o;
			//获取当前需要传递的参数（课程）
			param = lessonConvertLiveRoom((ClassModuleLesson) o, companyId);
			lessonId = lesson.getId();
		}
		//公开课
		if(o instanceof LiveOpenCourse){
			open = (LiveOpenCourse) o;
			//获取当前需要传递的参数（课程）
			param = openConvertLiveRoom((LiveOpenCourse) o, companyId);
			lessonId = open.getId();
		}
		
		//获取创建直播课的URL
		String url = param.get("domain") + LiveRoomConstant.CREATE_ZS_LIVE_ROOM;
		
		String detail;
		try {
			detail = HttpPostRequest.post(url, param);
			if("subject exists".equals(JSONObject.fromObject(detail).get("message"))){
				param.put("subject", param.get("subject")+"_" + lessonId);
				detail = HttpPostRequest.post(url, param);
			}
			log.info("调用展示互动创建直播教室接口,返回信息如下：" + detail + ", 创建的课堂名称为："+param.get("subject"));
			return detail;
		} catch (Exception e) {
			log.error("调用展示互动创建直播教室接口失败,  删除的课堂名称为："+param.get("subject"),e);
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String updateLiveRoom(Object o, Integer companyId) {
		//获取当前需要传递的参数
		Map<String,Object> param = null;
		//普通课程
		if(o instanceof ClassModuleLesson){
			param = lessonConvertLiveRoom((ClassModuleLesson) o, companyId);
		}
		//公开课
		if(o instanceof LiveOpenCourse){
			param = openConvertLiveRoom((LiveOpenCourse) o, companyId);
		}
		//获取创建直播课的URL
		String url = param.get("domain") + LiveRoomConstant.MODIFY_ZS_LIVE_ROOM;
		
		String detail;
		try {
			detail = HttpPostRequest.post(url, param);
			log.info("调用展示互动更新直播教室接口,返回信息如下：" + detail + ", 删除的课堂名称为："+param.get("subject"));
			return detail;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("调用展示互动更新直播教室接口失败,  删除的课堂名称为："+param.get("subject"));
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
	public Map<String, Object> lessonConvertLiveRoom(ClassModuleLesson cml, Integer companyId) {
		
		//根据公司Id获取对应的直播的相关配置
		CompanyLiveConfig config = new CompanyLiveConfig();
		config.setCompanyId(companyId);
		List<CompanyLiveConfig> configs = companyLiveConfigServiceImpl.findCompanyLiveConfigByPage(config);
		if(configs != null && configs.size() > 0){
			config = configs.get(0);
		}
		
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("subject", cml.getLessonName());
		param.put("teacherToken", config.getTeacherToken() != null ? config.getTeacherToken() : LiveRoomConstant.TEACHER_TOKEN);
		param.put("studentToken", config.getStudentWebToken() != null ? config.getStudentWebToken() : LiveRoomConstant.STUDENT_TOKEN_WEB);
		param.put("studentClientToken", config.getStudentClientToken() != null ? config.getStudentClientToken() : LiveRoomConstant.STUDENT_TOKEN_CLINT);
		param.put("startDate", dateFormat.format(cml.getLessonDate()) + " " + cml.getLessonTimeStart()+":00");
//		param.put("invalidDate", dateFormat.format(cml.getLessonDate()) + " " + cml.getLessonTimeEnd()+":00");
		param.put("assistantToken", config.getAssistantToken() != null ? config.getAssistantToken() : LiveRoomConstant.ASSISTANT_TOKEN);
		param.put("domain", config.getDomain() != null ? config.getDomain() : LiveRoomConstant.DOMIN_NAME);
		
		//老师介绍
		SysConfigTeacher teacher = sysConfigTeacherServiceImpl.findSysConfigTeacherById(Integer.parseInt(cml.getTeachers()));
		if(teacher != null && teacher.getResume() != null){
			param.put("speakerInfo", teacher.getResume());
		}
		//如果当前为修改状态
		if(cml != null && cml.getLiveroomIdGh() != null && cml.getLiveroomIdGh().length() > 0){
			param.put("id", cml.getLiveroomIdGh());
		}
		
		param.put("webJoin", true);
		param.put("clientJoin", true);
		param.put("uiMode", 4);
		param.put("realtime", true);
		
		if("LIVE_SMALL_CLASS_ROOM".equals(cml.getLiveClassType())){
			param.put("scene", 1);
		}else{
			param.put("scene", 0);
		}
//		param.put("sec", liveRoom.getSec());
		param.put("loginName", config.getLoginName() != null ? config.getLoginName() : LiveRoomConstant.LOGIN_NAME);
		param.put("password", config.getPassword() != null ? config.getPassword() : LiveRoomConstant.PASSWORD);
		
		return param;
	}

	@Override
	public Map<String, Object> openConvertLiveRoom(LiveOpenCourse open, Integer companyId) {
		
		//根据公司Id获取对应的直播的相关配置
		CompanyLiveConfig config = new CompanyLiveConfig();
		config.setCompanyId(companyId);
		List<CompanyLiveConfig> configs = companyLiveConfigServiceImpl.findCompanyLiveConfigByPage(config);
		if(configs != null && configs.size() > 0){
			config = configs.get(0);
		}
		
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("subject", open.getOpenCourseName());
//		param.put("teacherToken", LiveRoomConstant.TEACHER_TOKEN);
//		param.put("studentToken", LiveRoomConstant.STUDENT_TOKEN_WEB);
//		param.put("studentClientToken", LiveRoomConstant.STUDENT_TOKEN_CLINT);
//		param.put("startDate", dateFormat.format(open.getStartOpenData()) + " " + open.getStartTime()+":00");
////		param.put("invalidDate", dateFormat.format(open.getStartOpenData()) + " " + open.getEndTime()+":00");
//		param.put("assistantToken", LiveRoomConstant.ASSISTANT_TOKEN);
		
		param.put("teacherToken", config.getTeacherToken() != null ? config.getTeacherToken() : LiveRoomConstant.TEACHER_TOKEN);
		param.put("studentToken", config.getStudentWebToken() != null ? config.getStudentWebToken() : LiveRoomConstant.STUDENT_TOKEN_WEB);
		param.put("studentClientToken", config.getStudentClientToken() != null ? config.getStudentClientToken() : LiveRoomConstant.STUDENT_TOKEN_CLINT);
		param.put("startDate", dateFormat.format(open.getStartOpenData()) + " " + open.getStartTime()+":00");
//		param.put("invalidDate", dateFormat.format(cml.getLessonDate()) + " " + cml.getLessonTimeEnd()+":00");
		param.put("assistantToken", config.getAssistantToken() != null ? config.getAssistantToken() : LiveRoomConstant.ASSISTANT_TOKEN);
		param.put("domain", config.getDomain() != null ? config.getDomain() : LiveRoomConstant.DOMIN_NAME);
		//老师介绍
		SysConfigTeacher teacher = sysConfigTeacherServiceImpl.findSysConfigTeacherById(open.getTeacherId());
		if(teacher != null && teacher.getResume() != null){
			param.put("speakerInfo", teacher.getResume());
		}
		//如果当前为修改状态
		if(open != null && open.getLiveroomIdGh() != null && open.getLiveroomIdGh().length() > 0){
			param.put("id", open.getLiveroomIdGh());
		}
		
		param.put("webJoin", true);
		param.put("clientJoin", true);
		param.put("uiMode", 4);
		
		param.put("scene", 0);
//		param.put("sec", liveRoom.getSec());
		param.put("loginName", config.getLoginName() != null ? config.getLoginName() : LiveRoomConstant.LOGIN_NAME);
		param.put("password", config.getPassword() != null ? config.getPassword() : LiveRoomConstant.PASSWORD);
		param.put("realtime", true);
		
		return param;
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
