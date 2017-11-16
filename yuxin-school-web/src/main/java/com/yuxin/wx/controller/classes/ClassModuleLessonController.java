package com.yuxin.wx.controller.classes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.yuxin.wx.api.watchInfo.IWatchInfoService;
import com.yuxin.wx.controller.task.TestTask;
import com.yuxin.wx.model.watchInfo.WatchInfo;
import com.yuxin.wx.model.watchInfo.WatchInfoFromZSGet;
import com.yuxin.wx.model.watchInfo.WatchInfoFromZSResult;
import com.yuxin.wx.vo.classes.*;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.yuxin.wx.api.classes.IClassModuleLessonService;
import com.yuxin.wx.api.classes.IClassModuleNoService;
import com.yuxin.wx.api.classes.IClassTypeResourceTypeService;
import com.yuxin.wx.api.classes.IClassTypeService;
import com.yuxin.wx.api.company.ICompanyFunctionSetService;
import com.yuxin.wx.api.company.ICompanyLiveConfigService;
import com.yuxin.wx.api.company.ICompanyMemberServiceService;
import com.yuxin.wx.api.company.ICompanyMessageHistoryService;
import com.yuxin.wx.api.company.ICompanyStudentMessageService;
import com.yuxin.wx.api.student.IStudentPayMasterService;
import com.yuxin.wx.api.student.IStudentPaySlaveService;
import com.yuxin.wx.api.system.ISysConfigDictService;
import com.yuxin.wx.api.system.ISysConfigItemService;
import com.yuxin.wx.api.system.ISysConfigTeacherService;
import com.yuxin.wx.classes.impl.CCLiveRoomServiceImpl;
import com.yuxin.wx.classes.impl.EketangLiveRoomServiceImpl;
import com.yuxin.wx.classes.impl.GenseeLiveRoomServiceImpl;
import com.yuxin.wx.common.CCLiveInterface;
import com.yuxin.wx.common.Constant;
import com.yuxin.wx.common.LiveRoomConstant;
import com.yuxin.wx.common.JsonMsg;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.common.SysConfigConstant;
import com.yuxin.wx.model.classes.ClassModuleLesson;
import com.yuxin.wx.model.classes.ClassModuleNo;
import com.yuxin.wx.model.classes.ClassTypeResourceType;
import com.yuxin.wx.model.classes.liveroom.ZsReturnInfo;
import com.yuxin.wx.model.company.CompanyFunctionSet;
import com.yuxin.wx.model.company.CompanyLiveConfig;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.model.company.CompanyStudentMessage;
import com.yuxin.wx.model.system.SysConfigDict;
import com.yuxin.wx.model.system.SysConfigItem;
import com.yuxin.wx.model.system.SysConfigTeacher;
import com.yuxin.wx.util.APIServiceFunction;
import com.yuxin.wx.util.HttpPostRequest;
import com.yuxin.wx.util.MD5;
import com.yuxin.wx.util.TalkfunUtils;
import com.yuxin.wx.utils.DateUtil;
import com.yuxin.wx.utils.PropertiesUtil;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.student.StuMessageVo;
import com.yuxin.wx.vo.system.TeacherVo;

/**
 * Controller of ClassModuleLesson
 * @author wang.zx
 * @date 2014-12-5
 */
@Controller
@RequestMapping("/classModuleLesson")
public class ClassModuleLessonController {
	
	Log log = LogFactory.getLog("log");
	
	@Autowired
	private ICompanyLiveConfigService companyLiveConfigServiceImpl;
	@Autowired
	private CCLiveRoomServiceImpl CCLiveRoomServiceImpl;
	@Autowired
	private IClassTypeResourceTypeService classTypeResourceTypeServiceImpl;
	@Autowired
	private IStudentPaySlaveService studentPaySlaveServiceImpl;
	@Autowired
	private ISysConfigTeacherService sysConfigTeacherServiceImpl;
	@Autowired
	private ICompanyMessageHistoryService companyMessageHistoryServiceImpl;
	@Autowired
	private IStudentPayMasterService studentPayMasterServiceImpl;
	@Autowired
	private ICompanyStudentMessageService companyStudentMessageServiceImpl;
	@Autowired
	private PropertiesUtil properties;
	@Autowired
	private IClassModuleNoService classModuleNoServiceImpl;
	@Autowired
	private ISysConfigItemService sysConfigItemServiceImpl;
	@Autowired
	private IClassModuleLessonService classModuleLessonServiceImpl;
	@Autowired
	private ISysConfigDictService sysConfigDictServiceImpl;
	@Autowired
	private ICompanyMemberServiceService companyMemberServiceServiceImpl;
	@Autowired
	private GenseeLiveRoomServiceImpl genseeLiveRoomServiceImpl;
	@Autowired
	private EketangLiveRoomServiceImpl eketangLiveRoomServiceImpl;
	@Autowired
	private IClassTypeService classTypeServiceImpl;
	@Autowired
	private ICompanyFunctionSetService companyFunctionSetServiceImpl;
	@Autowired
	private IWatchInfoService watchInfoServiceImpl;
	
	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, ClassModuleLesson search){
		if (search == null) {
			search = new ClassModuleLesson();
			// search.setPageSize(20);
		}
		model.addAttribute("list", classModuleLessonServiceImpl.findClassModuleLessonByPage(search));
		return "classModuleLesson/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(ClassModuleLesson classModuleLesson) {
		classModuleLessonServiceImpl.insert(classModuleLesson);
		return "redirect:/classModuleLesson";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(ClassModuleLesson classModuleLesson) {
		classModuleLessonServiceImpl.update(classModuleLesson);
		return "redirect:/classModuleLesson";
	}
	
	/**
	 * @Description: 逻辑删除,对数据进行更新
	 * @author wzx
	 * @date 2015-5-23 下午2:47:51
	 * @version 1.0
	 * @param model
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		try {
			//根据id查询出对应的课次
			ClassModuleLesson lesson = classModuleLessonServiceImpl.findClassModuleLessonById(id);
			//如果课次不为空，并且已经被按排过
			if(lesson != null){
				if(lesson.getModuleNoId() != null && lesson.getModuleNoId() > 0){
					deleteLesson(lesson);
				}
			}
			classModuleLessonServiceImpl.deleteClassModuleLessonById(id);
			return JsonMsg.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return JsonMsg.ERROR;
		}
	}
	
	
	/**
	 * @Description: 删除课次,并更新班号中安排过课次的数量
	 * @author wzx
	 * @date 2015-5-29 下午3:34:17
	 * @version 1.0
	 * @param lesson
	 */
	public void deleteLesson(ClassModuleLesson lesson){
		ClassModuleNo moduleNo = classModuleNoServiceImpl.findClassModuleNoById(lesson.getModuleNoId());
		if(moduleNo != null){
			String classTeachType = moduleNo.getClassTeachType();
			//如果是面授直接删除该课次，如果是直播，则需要删除对应的直播教室
			if(classTeachType != null && Constant.TEACH_METHOD_FACE.equals(classTeachType)){
				updateModuleNo(moduleNo);
			}else if(classTeachType != null && Constant.TEACH_METHOD_LIVE.equals(classTeachType)){
				updateModuleNo(moduleNo);
				//此处预留删除一课堂直播教室
				//修改为不删除E课堂直播教室 2015-09-23
				//deleteLiveRoom(lesson);
				if("ht".equals(lesson.getLiveCompanyType())){
					deleteTalkfun(lesson.getLiveroomIdGh());
				}
				if("cc".equals(lesson.getLiveCompanyType())){
					deleteCCLive(lesson.getLiveroomIdGh());
				}
			}
		}
	}
	
	private void deleteTalkfun(String courseId){
		CompanyLiveConfig clc = companyLiveConfigServiceImpl
				.findByCompanyId(WebUtils.getCurrentCompanyId());
		String openId = null;
		String openToken = null;
		if(clc != null && clc.getLiveType().equals(3)){
			openId = clc.getLoginName();
			openToken = clc.getPassword();
		}
		String res = "";
		Map<Object, Object> param = new HashMap<Object,Object>();
		param.put("course_id", courseId);
		try {
			res = TalkfunUtils.getRsult(param, "course.delete",openId,openToken);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.debug("删除课次，"+res);
	}
	
	private void deleteCCLive(String courseId){
		CompanyLiveConfig clc = companyLiveConfigServiceImpl
				.findByCompanyId(WebUtils.getCurrentCompanyId());
		if(clc == null || !clc.getLiveType().equals(4)){
			clc = null;
		}
		Map<String, String> map = new HashMap<String,String>();
		map.put("roomid", courseId);
		map.put("userid", (clc != null ? clc.getLoginName()
				: CCLiveInterface.USER_ID));

		long timestimp = System.currentTimeMillis();
		String thqs = APIServiceFunction.createHashedQueryString(map,timestimp, 
				clc != null ? clc.getPassword() : CCLiveInterface.API_KEY);
		String url = CCLiveInterface.CLOSE + thqs;
		try {
			String res = HttpPostRequest.get(url);
			log.debug("删除cc直播 信息," + res);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.debug("cc直播间关闭失败,"+e.getMessage());
		}
	}
	
	/**
	 * @Description: 如果是面授直接删除该课次，如果是直播，则需要删除对应的直播教室
	 * @author zx
	 * @date 2015-9-6 下午2:38:50
	 * @version 1.0
	 * @param lesson
	 */
	public void deleteLiveRoom(ClassModuleLesson lesson){
		
		Map<String,Object> param = new HashMap<String, Object>();
		//查询 光慧直播 id
		String customer = properties.getCustomer();
		long timestamp = System.currentTimeMillis();
		String secretKey = properties.getSecretKey();
		
		String str = MD5.getMD5ofStr(customer+timestamp+secretKey);
		str = str.substring(0,10)+str.substring(str.length() - 10);
		param.put("s", str);
		param.put("id", lesson.getLiveRoom());
		param.put("customer", customer);
		param.put("timestamp", timestamp);
		
		String url = properties.getInterfaceAddress() + LiveRoomConstant.DELETE_LIVEROOM;
		
		String detail = "";
		try {
			detail = HttpPostRequest.post(url, param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("调用E课堂删除直播教室接口,返回信息如下：" + detail + ", 删除的课堂名称为："+lesson.getLessonName()+", 课堂Id为："+lesson.getId());
	}
	
	/**
	 * @Description: 更新班号中已安排的课次数量
	 * @author wzx
	 * @date 2015-5-29 下午3:44:46
	 * @version 1.0
	 * @param moduleNo
	 */
	public void updateModuleNo(ClassModuleNo moduleNo){
		Integer lessonPlan = moduleNo.getLessonPlan();
		if(lessonPlan > 1){
			moduleNo.setLessonPlan(moduleNo.getLessonPlan() - 1);
		}else{
			moduleNo.setLessonPlan(0);
		}
		Integer lessonTotal = moduleNo.getLessonTotal();
		if(lessonTotal > 1){
			moduleNo.setLessonTotal(moduleNo.getLessonTotal() - 1);
		}
		classModuleNoServiceImpl.update(moduleNo);
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public ClassModuleLesson getJson(Model model, @PathVariable Integer id){
		return classModuleLessonServiceImpl.findClassModuleLessonById(id);
	}
	
	/**
	 * 后台接收Date转换
	 */
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
	
	/**
	 * @Description:(根据教室ID获取对应的课次，对其根据课次日期分组返回页面)
	 * @author wang.zx 
	 * @date 2015-1-13 下午6:47:19
	 * @version 1.0
	 * @param request
	 * @param classroomId
	 * @param currentDay
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="choseClassRoom")
	public Map<String, List<ClassModuleLesson>> choseClassRoomForLesson(HttpServletRequest request, Integer classroomId, Date lessonDate){
		List<ClassModuleLesson> lesssons = null;
		List<ClassModuleLesson> valueList = null;
		Map<String, List<ClassModuleLesson>> dataMap = new LinkedHashMap<String, List<ClassModuleLesson>>();
		//根据当前日期，获取上周的周一以及下周的周日
		Date startDate = DateUtil.getWeekOfDayByCurentDay(-1, 1, lessonDate);
		Date endDate = DateUtil.getWeekOfDayByCurentDay(2, 7, lessonDate);
		
		String startDateStr = new SimpleDateFormat("yyyy-MM-dd").format(startDate);
		
		if(classroomId != null && classroomId > 0){
			lesssons = classModuleLessonServiceImpl.findLessonsByRoomIdAndDate(classroomId, startDate, endDate);
		}
		//将开始日期至结束日期都做为Key
		List<String> dateKey = new ArrayList<String>();
		
		dateKey.add(startDateStr);
		//共查询三周的教室时间
		for(int i = 0; i < 20; i++){
			Date date = DateUtil.addDate(startDate, 1);
			startDate = date;
			dateKey.add(new SimpleDateFormat("yyyy-MM-dd").format(date));
		}
		//循环加入map中
		for(String str : dateKey){
			valueList = new ArrayList<ClassModuleLesson>();
			dataMap.put(str, valueList);
		}
		
		//查询出来的课次放入到map中
		if(lesssons != null && lesssons.size() > 0){
			for(ClassModuleLesson lesson : lesssons){
				String keyMap = DateUtil.format1(lesson.getLessonDate());
				valueList = dataMap.get(keyMap);
				valueList.add(lesson);
			}
		}
		//将日期没有对应课次的添加一个星期几
		for(String str : dateKey){
			boolean flag = false;
			for(Map.Entry<String, List<ClassModuleLesson>> entry : dataMap.entrySet()){
				if(dataMap.get(str).size() == 0 && entry.getKey().equals(str)){
					flag = true;
					break;
				}
			}
			if(flag){
				List<ClassModuleLesson> lessons = dataMap.get(str);
				ClassModuleLesson les = new ClassModuleLesson();
				Date dateTime = DateUtil.getdate(str);
				les.setLessonDate(dateTime);
				les.setWeekType(weekStr(dateTime));
				lessons.add(les);
				dataMap.put(str, lessons);
			}
		}
		return dataMap;
	}
	
	public String weekStr(Date date){
		String[] weeks = {"周一","周二","周三","周四","周五","周六","周日"};
		int th = DateUtil.dayOfWeek(date);
		return weeks[th - 1];
	}
	
	/**
	 * @Description: 根据课次编辑对应的老师信息
	 * @author wzx
	 * @date 2015-5-19 下午11:52:27
	 * @version 1.0
	 * @param request
	 * @param teacherId
	 * @param id
	 * @param ModuleNoId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="editTeacherForLesson")
	public String editTeacherForLesson(HttpServletRequest request, ClassModuleLesson lesson
			){
		//首先判断是否需要更新该班号下所有的课次对应的老师, 如果班号ID不为空则表示要更新所有的，如果为空或者0，则表示更新当前的课次
		List<ClassModuleLesson> lessons = null;
		
		try {
			if(lesson.getModuleNoId() != null && lesson.getModuleNoId() > 0 ){
				lessons = classModuleLessonServiceImpl.findClassModuleLessonByModuleNoId(lesson.getModuleNoId());
				//根据班号Id查询该课次对应的班号
				ClassModuleNo moduleNo = classModuleNoServiceImpl.findClassModuleNoById(lesson.getModuleNoId());
				
				if(!moduleNo.getClassTeachType().equals("TEACH_METHOD_FACE")){
					moduleNo.setLessonPlan(moduleNo.getLessonTotal());
					classModuleNoServiceImpl.update(moduleNo);
				}
			}
			if(lessons != null && lessons.size() > 0){
				for(ClassModuleLesson cml : lessons){
					updateTeacher(cml, lesson, true);
				}
				return "batchSuccess";
			}
			if(lesson.getId() != null && lesson.getId() > 0){
				ClassModuleLesson cml = classModuleLessonServiceImpl.findClassModuleLessonById(lesson.getId());
				log.info("单个修改对应课次的老师，并同时修改E课堂直播ID对应的老师,该课次Id为："+lesson.getId());
				if(cml != null && cml.getLessonName() != null && cml.getLessonName().length() > 0){
					updateTeacher(cml, lesson, true);
				}
				//根据班号Id查询该课次对应的班号
				ClassModuleNo moduleNo = classModuleNoServiceImpl.findClassModuleNoById(cml.getModuleNoId());
				
				if(moduleNo != null && moduleNo.getClassTeachType() != null && !("TEACH_METHOD_FACE").equals(moduleNo.getClassTeachType())){
					if(moduleNo.getLessonPlan() != null){
						moduleNo.setLessonPlan(moduleNo.getLessonPlan()+1);
					}else{
						moduleNo.setLessonPlan(1);
					}
					classModuleNoServiceImpl.update(moduleNo);
				}
				
				return JsonMsg.SUCCESS;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		return "fail";
	}
	
	/**
	 * @Description: 更新老师信息
	 * @author wzx
	 * @date 2015-5-20 下午8:05:55
	 * @version 1.0
	 * @param cml
	 */
	public void updateTeacher(ClassModuleLesson cml, ClassModuleLesson lesson, boolean isTeacher
			){
		Integer companyId = WebUtils.getCurrentCompanyId();
		if(isTeacher){
			ClassModuleNo moduleNo = classModuleNoServiceImpl.findClassModuleNoById(cml.getModuleNoId());
			
			//如果是新增的话，需要判断当前公司的直播服务提供商是哪家
			CompanyMemberService liveProvider = companyMemberServiceServiceImpl.findByCompanyId(companyId);
			
			String liveRoom = null;
			
			cml.setTeachers(lesson.getTeachers());
			cml.setTeachersName(lesson.getTeachersName());
			cml.setAssistants(lesson.getAssistants());
			cml.setAssistantsName(lesson.getAssistantsName());
			synchronized (moduleNo) {
				if (moduleNo != null
						&& moduleNo.getClassTeachType() != null
						&& moduleNo.getClassTeachType().equals(
								"TEACH_METHOD_LIVE")) {
					//判断当前公司是E课堂还是展示互动
					if(liveProvider == null 
							|| liveProvider.getLiveServiceProvider() == null 
							|| liveProvider.getLiveServiceProvider().equals("")
							|| liveProvider.getLiveServiceProvider().equals("gh")){
						try {
							//E课堂
							liveRoom = eketangLiveRoomServiceImpl.createLiveRoom(cml, companyId);
							LiveRoomInfo bean = (LiveRoomInfo) JSONObject.toBean(JSONObject.fromObject(liveRoom),LiveRoomInfo.class);
							if (bean != null && ("ok").equals(bean.getMsg())) {
								cml.setLiveroomIdGh(bean.getLiveRoomId());
								if(liveProvider != null && liveProvider.getLiveServiceTemplate() != null && liveProvider.getLiveServiceTemplate().equals(Constant.EKETANG_TEMPALATE_SOOONER)){
									cml.setStudentUrlGh(bean.getStudentUrl());
								}else{
									cml.setStudentUrlGh(bean.getStudentUrl().replace("soooner", "taobao"));
								}
								cml.setTeacherUrlGh(bean.getTeacherUrl());
								cml.setAssistantUrlGh(bean.getAssistantUrl());
								cml.setReplayUrlGh(bean.getReplayUrl());
							}
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
							log.info("创建e课堂直播异常，" + e.getMessage());
						}
					}else if(liveProvider.getLiveServiceProvider().equals("zs")){
						try {
							if(cml.getLiveroomIdGh() != null && cml.getLiveroomIdGh().length() > 0){
								//已经创建过直播课程
								genseeLiveRoomServiceImpl.updateLiveRoom(cml, companyId );
							}else{
								//展示互动创建直播教室
								String returnLiveRoom = genseeLiveRoomServiceImpl.createLiveRoom(cml, companyId);
								ZsReturnInfo bean = null;
								try {
									bean = (ZsReturnInfo) JSONObject.toBean(JSONObject.fromObject(returnLiveRoom),ZsReturnInfo.class);
								} catch (Exception e) {
									e.printStackTrace();
									log.info("创建直播教室出现异常，当前bean信息为："+bean+", 教室的ID为："+cml.getId()+", 教室的名称："+cml.getLessonName());
								}
								
								cml.setLiveroomIdGh(bean.getId());
								cml.setStudentUrlGh(bean.getStudentJoinUrl());
								cml.setTeacherUrlGh(bean.getTeacherJoinUrl());
								cml.setAssistantUrlGh(bean.getTeacherJoinUrl());
							}
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
							log.info("创建展示直播异常，" + e.getMessage());
						}
					}else if(liveProvider.getLiveServiceProvider().equals("ht")){
						try {
							CompanyLiveConfig clc = companyLiveConfigServiceImpl
									.findByCompanyId(companyId);
							String openId = null;
							String openToken = null;
							if(clc != null && clc.getLiveType().equals(3)){
								openId = clc.getLoginName();
								openToken = clc.getPassword();
							}
							String res = "";
							//老师介绍
							SysConfigTeacher teacher = sysConfigTeacherServiceImpl.findSysConfigTeacherById(Integer.parseInt(cml.getTeachers()));
							//Map<Object, Object> options = new HashMap<Object,Object>();
							Map<Object, Object> param = new HashMap<Object,Object>();
							param.put("course_name", cml.getLessonName());
							param.put("account", teacher.getId());
							param.put("start_time", new SimpleDateFormat("yyyy-MM-dd")
								.format(cml.getLessonDate()) + " " + cml.getLessonTimeStart() 
								+ ":00");
							param.put("end_time",  new SimpleDateFormat("yyyy-MM-dd")
								.format(cml.getLessonDate()) + " " + cml.getLessonTimeEnd() 
								+ ":00");
							param.put("nickname", teacher.getName());
							param.put("accountIntro", teacher.getResume());

							Map<Object, Object> options = new HashMap<Object,Object>();
							options.put("departmentId", companyId);
							options.put("barrage", cml.getBarrage());
							options.put("modetype", cml.getModetype());
							param.put("options", options);
							
							if(cml.getLiveroomIdGh() != null && cml.getLiveroomIdGh().length() > 0){
								param.put("course_id", cml.getLiveroomIdGh());
								res = TalkfunUtils.getRsult(param, "course.update",openId,openToken);
							}else{
								res = TalkfunUtils.getRsult(param, "course.add",openId,openToken);
							}
							com.alibaba.fastjson.JSONObject rjson = com.alibaba.fastjson.JSONObject.parseObject(res);
							log.info(rjson);
							if(!rjson.getInteger("code").equals(0)){
								if(cml.getLiveroomIdGh() == null){
									classModuleLessonServiceImpl
										.deleteClassModuleLessonById(cml.getId());
								}
								return;
							}else {
								if(rjson.get("data") != null && !rjson.get("data").equals("")){
									TalkfunEntityVo tf = com.alibaba.fastjson.JSONObject.parseObject(
											rjson.getJSONObject("data").toJSONString()
											, TalkfunEntityVo.class);
									cml.setLiveroomIdGh(tf.getCourse_id());
									cml.setLiveCompanyType("ht");
								}
							}
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
							log.info("创建欢拓直播失败");
						}
					}else{
						try {
							String returnLiveRoom=null;
							if(cml.getLiveroomIdGh() != null && cml.getLiveroomIdGh().length() > 0){
								returnLiveRoom= CCLiveRoomServiceImpl.updateLiveRoom(cml,companyId);
								if("OK".equals(returnLiveRoom)){
									log.info("修改直播课程成功");
								}else{
									log.info("修改直播课程失败");
								}
							}else{
								returnLiveRoom= CCLiveRoomServiceImpl.createLiveRoom(cml,companyId);
								if(returnLiveRoom != null){
									com.alibaba.fastjson.JSONObject rj = com.alibaba.fastjson.JSONObject.parseObject(returnLiveRoom);
									cml.setLiveroomIdGh(rj.getString("roomid"));
									cml.setStudentUrlGh(rj.getString("s"));
									cml.setTeacherUrlGh(rj.getString("t"));
									cml.setAssistantUrlGh(rj.getString("a"));
									cml.setLiveCompanyType("cc");
								}
							}
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
							log.debug("创建cc直播课失败," + e.getMessage());
						}
					}
				}
			}
		}else{
			cml.setClassroom(lesson.getClassroom());
			cml.setClassroomId(lesson.getClassroomId());
		}
		classModuleLessonServiceImpl.update(cml);
	}
	
	/**
	 * @Description: 创建E课堂直播课程
	 * @author zx.wang
	 * @date 2015-11-9 下午3:22:10
	 * @version 2.0
	 * @param cml
	 * @return
	 */
//	public String ghLiveRoom(ClassModuleLesson cml) {
//		//查询 光慧直播 id
//		String customer = properties.getCustomer();
//		long timestamp = System.currentTimeMillis();
//		String secretKey = properties.getSecretKey();
//		
//		String str = MD5.getMD5ofStr(customer+timestamp+secretKey);
//		str = str.substring(0,10)+str.substring(str.length() - 10);
//		
//		String url = null;
//		
//		//如果该课次还没有创建直播教室，则走创建直播教室接口，否则修改
//		if(cml.getLiveroomIdGh() != null && cml.getLiveroomIdGh().length() > 0){
//			url = properties.getInterfaceAddress() + LiveRoomConstant.UPDATE_LIVEROOM;
//		}else{
//			url = properties.getInterfaceAddress() + LiveRoomConstant.CREATELIVEROOM;
//		}
//				
//		
//		//教师 和 助教 信息
//		SysConfigTeacher teacher = null;
//		SysConfigTeacher assistant = null;
//		if(cml.getTeachers() != null && cml.getTeachers().length() > 0){
//			teacher = sysConfigTeacherServiceImpl.findSysConfigTeacherById(Integer.parseInt(cml.getTeachers()));
//		}
//		if(cml.getAssistants() != null && cml.getAssistants().length() > 0){
//			assistant = sysConfigTeacherServiceImpl.findSysConfigTeacherById(Integer.parseInt(cml.getAssistants()));
//		}
//		
//		TeacherVo tv = null;
//		TeacherVo av = null;
//		List<TeacherVo> avs = new ArrayList<TeacherVo>();
//		List<TeacherVo> tvs = new ArrayList<TeacherVo>();
//		if(teacher != null){
//			tv = new TeacherVo(cml.getTeachers(),teacher.getName(),teacher.getName(),teacher.getHeadpicUrl(),"",teacher.getResume());
//			tvs.add(tv);
//		}
//		if(assistant != null){
//			av = new TeacherVo(cml.getAssistants(),assistant.getName(),assistant.getName(),assistant.getHeadpicUrl(),"",assistant.getResume());
//			avs.add(av);
//		}
//		
//		String teachers = com.alibaba.fastjson.JSONArray.toJSONString(tvs);
//		String assistants = com.alibaba.fastjson.JSONObject.toJSONString(avs);
//		
//		Map<String,Object> param = new HashMap<String, Object>();
//		param.put("id", cml.getLiveRoom());
//		param.put("title", cml.getLessonName());
//		param.put("introduce", cml.getLessonName());
//		
//		//是否支持手机端
//		if(cml.getSupportMobile() != null){
//			if(cml.getSupportMobile().equals(0)){
//				param.put("supportMobile", 0);
//				param.put("liveMode", 1);
//			}else if(cml.getSupportMobile().equals(1)){
//				param.put("supportMobile", 1);
//				param.put("liveMode", 2);
//			}
//		}
//		
//		//学生不需要鉴权
//		param.put("fee", 1);
//		
//		 String day = format.format(cml.getLessonDate());
//		 String startTime = cml.getLessonTimeStart();
//		 String endTime = cml.getLessonTimeEnd();
//		 long beginTimeLong = DateUtil.parseDateTime(day,startTime);
//		 long endTimeLong = DateUtil.parseDateTime(day,endTime);
//		 
//		 param.put("beginTime", beginTimeLong);
//		 param.put("endTime", endTimeLong);
//		
//		
//		param.put("customer", customer);
//		param.put("timestamp", timestamp);
//		param.put("s", str);
//		
//		param.put("teachers", teachers);
//		param.put("assistants", assistants);
//		
//		String detail;
//		try {
//			detail = HttpPostRequest.post(url, param);
//			log.info("调用E课堂创建直播教室接口,返回信息如下：" + detail + ", 删除的课堂名称为："+cml.getLessonName()+", 课堂Id为："+cml.getId());
//			return detail;
//		} catch (Exception e) {
//			e.printStackTrace();
//			log.info("调用E课堂创建直播教室接口失败,课次Id为：" + cml.getId() + ", 删除的课堂名称为："+cml.getLessonName());
//			return null;
//		}
//	}
	
	@ResponseBody
	@RequestMapping(value="editClassRoomForLesson")
	public String editClassRoomForLesson(HttpServletRequest request, ClassModuleLesson lesson
			){
		//首先判断是否需要更新该班号下所有的课次对应的教室, 如果班号ID不为空则表示要更新所有的，如果为空或者0，则表示更新当前的课次
		List<ClassModuleLesson> lessons = null;
		try {
			if(lesson.getModuleNoId() != null && lesson.getModuleNoId() > 0 ){
				lessons = classModuleLessonServiceImpl.findClassModuleLessonByModuleNoId(lesson.getModuleNoId());
				
				//根据班号Id查询该课次对应的班号
				ClassModuleNo moduleNo = classModuleNoServiceImpl.findClassModuleNoById(lesson.getModuleNoId());
				
				if(moduleNo.getClassTeachType().equals("TEACH_METHOD_FACE")){
					moduleNo.setLessonPlan(moduleNo.getLessonTotal());
					classModuleNoServiceImpl.update(moduleNo);
				}
			}
			if(lessons != null && lessons.size() > 0){
				log.info("为班号批量修改老师信息，并且批量调用E课堂那边直播ID对应的老师");
				for(ClassModuleLesson ls : lessons){
					updateTeacher(ls, lesson, false);
				}
				return "batchSuccess";
			}
			if(lesson.getId() != null && lesson.getId() > 0){
				ClassModuleLesson cml = classModuleLessonServiceImpl.findClassModuleLessonById(lesson.getId());
				log.info("单个修改对应课次的老师，并同时修改E课堂直播ID对应的老师,该课次Id为："+lesson.getId());
				if(cml != null && cml.getLessonName() != null && cml.getLessonName().length() > 0){
					updateTeacher(cml, lesson, false);
				}
				
				//根据班号Id查询该课次对应的班号
				ClassModuleNo moduleNo = classModuleNoServiceImpl.findClassModuleNoById(cml.getModuleNoId());
				
				if(moduleNo != null && moduleNo.getClassTeachType() != null && !("TEACH_METHOD_FACE").equals(moduleNo.getClassTeachType())){
					moduleNo.setLessonPlan(moduleNo.getLessonPlan()+1);
					classModuleNoServiceImpl.update(moduleNo);
				}
				
				return JsonMsg.SUCCESS;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		return "fail";
		
	}
	
	/**
	 * 
	 * Class Name: ClassModuleLessonController.java
	 * @Description: 进入排课查询界面
	 * @author 周文斌
	 * @date 2015-5-14 下午3:18:05
	 * @version 1.0
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/classes")
	public String classes(Model model,HttpServletRequest request){
		Integer companyId = WebUtils.getCurrentCompanyId();
		Integer schoolId = WebUtils.getCurrentUserSchoolId(request);
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("companyId", companyId);
		param.put("schoolId", schoolId);
		param.put("itemType", 1);
		//根据公司id 和学校id 查询 一级项目
		List<SysConfigItem> oneItem = sysConfigItemServiceImpl.findItemBySchoolCompanyId(param);
		
		model.addAttribute("oneItem", oneItem);
		return "classes/class";
	}
	
	@RequestMapping("/detail")
	public String detail(Model model,HttpServletRequest request,ClassVo classVo){
		Integer companyId = WebUtils.getCurrentCompanyId();
		Integer schoolId = WebUtils.getCurrentUserSchoolId(request);
		classVo.setCompanyId(companyId);
		classVo.setSchoolId(schoolId);
		String url = null;
		if(classVo.getSelNo() != null && classVo.getSelNo().length() > 0){
			url = "classes/selNoDetail";
		}else{
			url = "classes/classLessonDetail";
		}
		//分页
		List<ClassVo> classList = classModuleNoServiceImpl.findClassInfoByMap(classVo);
		
		Map<String ,Object> param = new HashMap<String, Object>();
		for (ClassVo c : classList) {
			param.clear();
			param.put("resourceId", c.getId());
			param.put("schoolId", schoolId);
			param.put("companyId", companyId);
			//查询 人数
			c.setEnrollYetStudents(studentPayMasterServiceImpl.findByResourceid(param));
		}
		
		//查询总数
		Integer count = classModuleNoServiceImpl.findCountByMap(classVo);
		
		PageFinder<ClassVo> classVoPage = new PageFinder<ClassVo>(classVo.getPage(),classVo.getPageSize(),count,classList);
		model.addAttribute("classVoPage", classVoPage);
		model.addAttribute("status", classVo.getStatus());
		return url;
	}
	
	public static void main(String[] args){
		StringBuffer buffer = new StringBuffer();
		buffer.append("{");
		buffer.append("'code': '0',");
		buffer.append("'msg': 'ok',");
		buffer.append("'id': '182',");
		buffer.append("'liveRoomId': '5562f72fa842cdbf38000397',");
		buffer.append("'studentUrl': '/student/index.html?liveClassroomId=5562f72fa842cdbf38000397&customer=yxkj&customerType=soooner&sp=0',");
		buffer.append("'teacherUrl': '/teacher/index.html?liveClassroomId=5562f72fa842cdbf38000397&customer=yxkj&customerType=soooner&sp=0',");
		buffer.append("'assistantUrl': '/teacher/index.html?liveClassroomId=5562f72fa842cdbf38000397&customer=yxkj&customerType=soooner&sp=0',");
		buffer.append("'replayUrl': '/playback/index.html?liveClassroomId=5562f72fa842cdbf38000397&customer=yxkj&customerType=soooner&sp=0'");
		buffer.append("}");
		
		LiveRoomInfo bean = (LiveRoomInfo) JSONObject.toBean(JSONObject.fromObject(buffer.toString()), LiveRoomInfo.class);
		System.out.println(bean);
	}
	
	/**
	 * 
	 * Class Name: ClassModuleLessonController.java
	 * @Description: 通知详细
	 * @author 周文斌
	 * @date 2015-6-3 下午3:09:15
	 * @version 1.0
	 * @param model
	 * @param request
	 * @param companyStudentMessage
	 * @return
	 */
	@RequestMapping("/noticeDetail")
	public String noticeDetail(Model model,HttpServletRequest request,CompanyStudentMessage companyStudentMessage){
		companyStudentMessage.setCompanyId(WebUtils.getCurrentCompanyId());
		companyStudentMessage.setSchoolId(WebUtils.getCurrentUserSchoolId(request));
		List<CompanyStudentMessage> msgList = companyStudentMessageServiceImpl.selMsgByCond(companyStudentMessage);
		//查询总数
		Integer count = companyStudentMessageServiceImpl.selMsgCount(companyStudentMessage);
		
		PageFinder<CompanyStudentMessage> msgPage = new PageFinder<CompanyStudentMessage>(companyStudentMessage.getPage(), companyStudentMessage.getPageSize(), count, msgList);
		
		model.addAttribute("msgPage", msgPage);
		return "student/notice/noticeDetail";
	}

	/**
	 * 
	 * Class Name: ClassModuleLessonController.java
	 * @Description: 通知结果
	 * @author 周文斌
	 * @date 2015-6-3 下午3:09:52
	 * @version 1.0
	 * @param request
	 * @param msgId
	 * @return
	 */
	@RequestMapping("/selMessage")
	public String selMessage(Model model,HttpServletRequest request,Integer msgId){
		CompanyStudentMessage msg = companyStudentMessageServiceImpl.findCompanyStudentMessageById(msgId);
		
		model.addAttribute(JsonMsg.MSG, msg);
		return "student/notice/selMessage";
	}
	
	/**
	 * 
	 * Class Name: ClassModuleLessonController.java
	 * @Description: 查询学员通知信息
	 * @author 周文斌
	 * @date 2015-6-3 下午6:56:13
	 * @version 1.0
	 * @param model
	 * @param msgId
	 * @param status
	 * @return
	 */
	@RequestMapping("/selStudent")
	public String selStudent(Model model,Integer msgId,StuMessageVo stuMessageVo){
		CompanyStudentMessage msg = companyStudentMessageServiceImpl.findCompanyStudentMessageById(msgId);
		//根据班型id 查询 学员 信息
		
		List<StuMessageVo> classList = new ArrayList<StuMessageVo>();
		List<StuMessageVo> moduleList = new ArrayList<StuMessageVo>();
		List<StuMessageVo> noticeList = new ArrayList<StuMessageVo>();
		
		stuMessageVo.setCompanyId(msg.getCompanyId());
		stuMessageVo.setSchoolId(msg.getSchoolId());
		stuMessageVo.setItemOneId(msg.getItemOneId());
		stuMessageVo.setItemSecondId(msg.getItemSecondId());
		stuMessageVo.setMessageId(msgId);
		stuMessageVo.setMessageMethod(msg.getMessageMethod());
		stuMessageVo.setGroupOneId(msg.getGroupOneId());
		stuMessageVo.setGroupTwoId(msg.getGroupTwoId());
		
		if(msg.getMessageType().equals("STUDENT_MESSAGE_CLASSTYPE")){
			stuMessageVo.setClassTypeId(msg.getClassTypeId());
			classList = companyStudentMessageServiceImpl.findStudentMsgByClassId(stuMessageVo);
			Integer count = companyStudentMessageServiceImpl.findStudentMsgCountByClassId(stuMessageVo);
			PageFinder<StuMessageVo> msgVoPage = new PageFinder<StuMessageVo>(stuMessageVo.getPage(), stuMessageVo.getPageSize(), count, classList);
			model.addAttribute("msgVoPage", msgVoPage);
		}
		if(msg.getMessageType().equals("STUDENT_MESSAGE_MODULENO")){
			stuMessageVo.setModuleNoId(msg.getModuleNoId());
			moduleList = companyStudentMessageServiceImpl.findStudentMsgByModuleId(stuMessageVo);
			Integer count = companyStudentMessageServiceImpl.findStudentMsgCountByModuleId(stuMessageVo);
			PageFinder<StuMessageVo> msgVoPage = new PageFinder<StuMessageVo>(stuMessageVo.getPage(), stuMessageVo.getPageSize(), count, moduleList);
			model.addAttribute("msgVoPage", msgVoPage);
		}
		if(msg.getMessageType().equals("STUDENT_MESSAGE_SPECIAL")){
			//根据消息id 查询
			noticeList = companyStudentMessageServiceImpl.findNoticeList(stuMessageVo);
			for (StuMessageVo s : noticeList) {
				if((int)s.getCompanyId() != (int)msg.getCompanyId()){
					s.setName("");
				}
			}
			Integer count = companyStudentMessageServiceImpl.findNoticeListCount(stuMessageVo);
			PageFinder<StuMessageVo> msgVoPage = new PageFinder<StuMessageVo>(stuMessageVo.getPage(), stuMessageVo.getPageSize(), count, noticeList);
			model.addAttribute("msgVoPage", msgVoPage);
		}
		if(msg.getMessageType().equals("STUDENT_MESSAGE_GROUP")){
			noticeList = companyStudentMessageServiceImpl.findStudentMsgByGroup(stuMessageVo);
			Integer count = companyStudentMessageServiceImpl.findStudentMsgByGroupCount(stuMessageVo);
			PageFinder<StuMessageVo> msgVoPage = new PageFinder<StuMessageVo>(stuMessageVo.getPage(), stuMessageVo.getPageSize(), count, noticeList);
			model.addAttribute("msgVoPage", msgVoPage);
		}
		
		model.addAttribute(JsonMsg.MSG, msg);
		model.addAttribute("msgId", msgId);
		model.addAttribute("status", stuMessageVo.getStatus());
		return "student/notice/selStudent";
	}
	
	/**
	 * 
	 * Class Name: ClassModuleLessonController.java
	 * @Description: 查询学员通知结果
	 * @author 周文斌
	 * @date 2015-6-3 下午8:51:54
	 * @version 1.0
	 * @param companyMessageHistory
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/selStuResult")
	public JSONObject selStuResult(Integer id){
		JSONObject json = new JSONObject();
		String result = companyMessageHistoryServiceImpl.findResult(id);
		json.put(JsonMsg.RESULT, result);
		return json;
	}
	
	/**
	 * 
	 * Class Name: ClassModuleLessonController.java
	 * @Description: 课程资料
	 * @author 周文斌
	 * @date 2015-8-11 下午5:42:33
	 * @version 1.0
	 * @param model
	 * @param request
	 * @return
	 */
//	@RequestMapping("/classesResource")
//	public String classesResource(Model model,HttpServletRequest request){
//		Integer companyId = WebUtils.getCurrentCompanyId();
//		Integer schoolId = WebUtils.getCurrentUserSchoolId(request);
//		Map<String,Object> param = new HashMap<String, Object>();
//		param.put("companyId", companyId);
//		param.put("schoolId", schoolId);
//		param.put("itemType", 1);
//		//根据公司id 和学校id 查询 一级项目
//		List<SysConfigItem> oneItem = sysConfigItemServiceImpl.findSysConfigItemByPid(SysConfigConstant.ITEMTYPE_FIRST, null, WebUtils.getCurrentCompanyId(), WebUtils.getCurrentSchoolId());
//		//查询字典
//		List<SysConfigDict> dictList = sysConfigDictServiceImpl.findByDicCode("COURSE_RESOURCE_TYPE");
//		//查询当前公司 资料类型
//		List<ClassTypeResourceType> rtList = classTypeResourceTypeServiceImpl.findResourceTypeByCompanpyId(companyId);
//		
//		model.addAttribute("rtList", rtList);
//		model.addAttribute("oneItem", oneItem);
//		model.addAttribute("dictList", dictList);
//		return "classes/classResourse";
//	}
	
	/**
	 * 
	 * Class Name: ClassModuleLessonController.java
	 * @Description: 新版课程资料
	 * @author zhang.zx
	 * @date 2016年6月22日 上午10:07:24
	 * @modifier
	 * @modify-date 2016年6月22日 上午10:07:24
	 * @version 1.0
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/classesResource/{id}/{lable}")
	public String classesResource(@PathVariable Integer id,@PathVariable String lable,Model model,HttpServletRequest request){
		Integer companyId = WebUtils.getCurrentCompanyId();
		Integer schoolId = WebUtils.getCurrentUserSchoolId(request);
		//根据班型id查询详情
		Map<String, String> map = new HashMap<String, String>();
		map.put("classId", "" + id);
		ClassTypeVo classType=classTypeServiceImpl.findClassTypeDetail(map);
		model.addAttribute("classType", classType);
		model.addAttribute("ct", classType);
 		model.addAttribute("type", "update");
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("companyId", companyId);
		param.put("schoolId", schoolId);
		param.put("itemType", 1);
		//根据公司id 和学校id 查询 一级项目
		List<SysConfigItem> oneItem = sysConfigItemServiceImpl.findItemBySchoolCompanyId(param);
		//查询字典
		List<SysConfigDict> dictList = sysConfigDictServiceImpl.findByDicCode("COURSE_RESOURCE_TYPE");
		//查询当前公司 资料类型
		List<ClassTypeResourceType> rtList = classTypeResourceTypeServiceImpl.findResourceTypeByCompanpyId(companyId);
		
		model.addAttribute("classtypeId", id);
		model.addAttribute("oneId", classType.getItemOneId());
		model.addAttribute("twoId", classType.getItemSecondId());
		model.addAttribute("rtList", rtList);
		model.addAttribute("oneItem", oneItem);
		model.addAttribute("dictList", dictList);
		CompanyFunctionSet search=new CompanyFunctionSet();
		search.setFunctionCode("COMPANY_FUNCTION_COURSE");
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		CompanyFunctionSet cfs=companyFunctionSetServiceImpl.findCompanyUseCourse(search);
		if(cfs!=null && "1".equals(cfs.getStatus())){
			model.addAttribute("lable", lable);
			return "classType/commClassResource";
		}
        if(null!=lable && "none".equals(lable)){
        	if(null!=classType){
    			if(classType.getLiveFlag()==1 && classType.getFaceFlag()==1 && classType.getVideoFlag()==1){
    				model.addAttribute("lable", "togther");
    			}else if(classType.getLiveFlag()==1 && classType.getFaceFlag()==0 && classType.getVideoFlag()==1){
    				model.addAttribute("lable", "togther");
    			}else if(classType.getLiveFlag()==0 && classType.getFaceFlag()==1 && classType.getVideoFlag()==1){
    				model.addAttribute("lable", "togther");
    			}else if(classType.getLiveFlag()==1 && classType.getFaceFlag()==0 && classType.getVideoFlag()==0){
    				model.addAttribute("lable", "live");
    			}else if(classType.getLiveFlag()==0 && classType.getFaceFlag()==1 && classType.getVideoFlag()==0){
    				model.addAttribute("lable", "face");
    			}else if(classType.getLiveFlag()==0 && classType.getFaceFlag()==0 && classType.getVideoFlag()==1){
    				model.addAttribute("lable", "video");
    			}else{
    				model.addAttribute("lable", "remote");
    			}
    		}
 		}else{
 			model.addAttribute("lable", lable);
 		}
		return "simpleClasses/commClassResource";
	}
	
	@RequestMapping("/resourcedetail/{id}")
	public String classesResourceData(Model model,@PathVariable Integer id,HttpServletRequest request){
		Integer oneId=Integer.parseInt(request.getParameter("oneId"));
		Integer twoId=Integer.parseInt(request.getParameter("twoId"));
		Integer companyId = WebUtils.getCurrentCompanyId();
		Integer schoolId = WebUtils.getCurrentUserSchoolId(request);
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("companyId", companyId);
		param.put("schoolId", schoolId);
		param.put("itemType", 1);
		//根据公司id 和学校id 查询 一级项目
		List<SysConfigItem> oneItem = sysConfigItemServiceImpl.findItemBySchoolCompanyId(param);
		//查询字典
		List<SysConfigDict> dictList = sysConfigDictServiceImpl.findByDicCode("COURSE_RESOURCE_TYPE");
		//查询当前公司 资料类型
		List<ClassTypeResourceType> rtList = classTypeResourceTypeServiceImpl.findResourceTypeByCompanpyId(companyId);
		
		model.addAttribute("classtypeId", id);
		model.addAttribute("oneId", oneId);
		model.addAttribute("twoId", twoId);
		model.addAttribute("rtList", rtList);
		model.addAttribute("oneItem", oneItem);
		model.addAttribute("dictList", dictList);
		return "classes/classResourse";
	}
	
	/**
	 * 改变课次所在的模块信息
	 * zhang.zx
	 * @param moduleId
	 * @param lessonId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/mvModuleLesson", method = RequestMethod.POST)
	public ClassModuleLesson mvModuleLesson(Integer moduleId,Integer lessonId){
		//根据模块查询对应班号
		List<ClassModuleNo> arr=classModuleNoServiceImpl.queryClassModuleNoById(moduleId);
		try {
			if(!arr.isEmpty()){
				moduleId=arr.get(0).getId();
			}
		}catch (Exception e) {
			log.info(e.getStackTrace());
			log.info("该模块对应多个班号");
		}
		ClassModuleLesson l=classModuleLessonServiceImpl.findClassModuleLessonById(lessonId);
		if(l!=null){
			Map<String, Object> param=new HashMap<String, Object>();
			param.put("moduleNoId", moduleId);
			param.put("lessonId", lessonId);
			classModuleLessonServiceImpl.mvLesson(param);
		}
		return l;
	}
	

	/**
	 * 
	 * Class Name: ClassModuleLessonController.java
	 * @Description: 课次排序
	 * @author zhang.zx
	 * @date 2015年9月11日 下午3:45:19
	 * @modifier
	 * @modify-date 2015年9月11日 下午3:45:19
	 * @version 1.0
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/sortLesson", method = RequestMethod.POST)
	public List<ClassModuleLesson> sortLecture(HttpServletRequest request){
		List<ClassModuleLesson> lessons=JSONArray.parseArray(request.getParameter("list"), ClassModuleLesson.class);
		List<ClassModuleLesson> sortlesson=new ArrayList<ClassModuleLesson>();
		for(ClassModuleLesson lesson : lessons){
			classModuleLessonServiceImpl.update(lesson);
			sortlesson.add(classModuleLessonServiceImpl.findClassModuleLessonById(lesson.getId()));
		}
		return sortlesson;
	}
	
	/**
	 * 
	 * Class Name: ClassModuleLessonController.java
	 * @Description: 校验课次名称
	 * @author zhang.zx
	 * @date 2015年9月15日 下午3:17:05
	 * @modifier
	 * @modify-date 2015年9月15日 下午3:17:05
	 * @version 1.0
	 * @param lessonName
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/checklessonName")
	public String findLessonsByName(String lessonName,Integer mouduleNoId){
		try {
			List<ClassModuleNo> marr=classModuleNoServiceImpl.queryClassModuleNoById(mouduleNoId);
			List<ClassModuleLesson> arr=classModuleLessonServiceImpl.findclassLessonByName(lessonName,marr.get(0).getId());
			if(null!=arr&&arr.size()>0){
				return JsonMsg.ERROR;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("校验课次名称是,查询班号异常");
		}
		return JsonMsg.SUCCESS;
	}
	//根据商品ID获取课次
	@ResponseBody
	@RequestMapping(value="/findLessonByCommodityId")
	public List<ClassModuleLesson> findLessonByCommodityId(Integer id,HttpServletRequest request){
   		return classModuleLessonServiceImpl.findLessonByCommodityId(id);
	}

	@RequestMapping(value="/getAllWatchInfo")
	public void getAllWatchInfo(HttpServletRequest request){
		List<WatchInfo> list = watchInfoServiceImpl.getLessonByDate(new HashMap());
		Map<String,Object> map = new HashMap();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		CompanyLiveConfig config = null;//companyLiveConfigServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
		String url ="";
		if(config==null){
			map.put("loginName", LiveRoomConstant.LOGIN_NAME);
			map.put("password",LiveRoomConstant.PASSWORD);
			url = LiveRoomConstant.DOMIN_NAME;

		}else{
			map.put("loginName", config.getLoginName());
			map.put("password",config.getPassword());
			url = config.getDomain();
		}
		for(WatchInfo lesson :list){
			map.put("startTime",sdf.format(lesson.getLessonDate())+" 00:00:00");
			map.put("endTime",sdf.format(lesson.getLessonDate())+" 23:59:59");
			map.put("roomId",lesson.getLiveroomId());
			String result = null;
			try {
				result = com.yuxin.wx.utils.HttpPostRequest.post(url+"/integration/site/training/export/history",map);
			} catch (Exception e) {
				e.printStackTrace();
			}
			map.remove("startTime");
			map.remove("endTime");
			System.out.println(result);
			Gson g = new Gson();
			TestTask.LiveResult re =  g.fromJson(result,TestTask.LiveResult.class);
			if(!re.getCode().equals("0")){
				System.out.println(re.getMessage());
				continue;
			}
			//用户信息过滤并存入数据库
			for(TestTask.MessUser mUser : re.getList()){
				if(Long.valueOf(mUser.getUid())-1000000000<1000000000){
					lesson.setJoinTime(mUser.getJoinTime());
					lesson.setLeaveTime(mUser.getLeaveTime());
					lesson.setUserId(Integer.parseInt(mUser.getUid())-1000000000);
					lesson.setLessonId(lesson.getLessonId());
					lesson.setWatchTime(Long.parseLong(mUser.getLeaveTime())-Long.parseLong(mUser.getJoinTime()));
					lesson.setDevice(mUser.getDevice());
					watchInfoServiceImpl.addWatchInfo(lesson);
					lesson.setId(null);
				}
			}
		}

	}


	@RequestMapping(value="/getAllWatchInfoHistory")
	public void getAllWatchInfoHistory(HttpServletRequest request){

		List<WatchInfo> lessonList = watchInfoServiceImpl.getLessonByDate(new HashMap());
		Map<String,Object> map = new HashMap();
		CompanyLiveConfig config = null;//companyLiveConfigServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String url ="";
		if(config==null){
			map.put("loginName", LiveRoomConstant.LOGIN_NAME);
			map.put("password",LiveRoomConstant.PASSWORD);
			url = LiveRoomConstant.DOMIN_NAME;

		}else{
			map.put("loginName", config.getLoginName());
			map.put("password",config.getPassword());
			url = config.getDomain();
		}


		for(WatchInfo lesson : lessonList){
			String theDate = sdf.format(lesson.getLessonDate());
			map.put("startTime",theDate+" 00:00:00");
			map.put("endTime",theDate+" 23:59:59                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     ");
			map.put("roomId",lesson.getLiveroomId());

			String result = null;
			try {
				result = com.yuxin.wx.utils.HttpPostRequest.post(url+"/integration/site/training/export/room/usage ",map);
			} catch (Exception e) {
				e.printStackTrace();
			}
			map.remove("startTime");
			map.remove("endTime");
			System.out.println(result);
			Gson g = new Gson();
			WatchInfoFromZSResult re =  g.fromJson(result,WatchInfoFromZSResult.class);
			List<WatchInfoFromZSGet> list  = re.getList();
			if(!re.getCode().equals("0")){
				 System.out.println(re.getMessage());
				 log.error(re.getMessage());
				continue;
			}else{
				if(re.getList().size()>0){
					Collections.sort(list, new Comparator<WatchInfoFromZSGet>(){

						/*
                         * int compare(Student o1, Student o2) 返回一个基本类型的整型，
                         * 返回负数表示：o1 小于o2，
                         * 返回0 表示：o1和o2相等，
                         * 返回正数表示：o1大于o2。
                         */
						public int compare(WatchInfoFromZSGet o1, WatchInfoFromZSGet o2) {

							//按照学生的年龄进行升序排列
							if(o1.getMaxConcurrent() > o2.getMaxConcurrent()){
								return -1;
							}
							if(o1.getMaxConcurrent() == o2.getMaxConcurrent()){
								return 0;
							}
							return 1;
						}
					});
					System.out.println(list.get(0).getMaxConcurrent());

					watchInfoServiceImpl.addWatchInfoFromZSResult(list.get(0));

				}
			}
		}
	}


	@RequestMapping(value="/testInfo")
	public void test(){
		//获取当日的课次
//        Date date = new Date();
//        date.setTime(date.getTime()-(3600*24*1000));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar ca = Calendar.getInstance();
		//ca.set(Calendar.MONTH,7);
		ca.add(Calendar.DAY_OF_MONTH,-1);
		String lessonDate = sdf.format(ca.getTime());
		Map dateMap = new HashMap();
		dateMap.put("lessonDate",lessonDate);
		List<WatchInfo> list = watchInfoServiceImpl.getLessonByDate(dateMap);
		Map<String,Object> map = new HashMap();
		CompanyLiveConfig config = companyLiveConfigServiceImpl.findByCompanyId(18113);
		String url ="";
		if(config==null){
			map.put("loginName", LiveRoomConstant.LOGIN_NAME);
			map.put("password",LiveRoomConstant.PASSWORD);
			url = LiveRoomConstant.DOMIN_NAME;

		}else{
			map.put("loginName", config.getLoginName());
			map.put("password",config.getPassword());
			url = config.getDomain();
		}

		map.put("startTime",lessonDate+" 00:00:00");
		map.put("endTime",lessonDate+" 23:59:59");
		for(WatchInfo lesson :list){
			map.put("roomId",lesson.getLiveroomId());
			String result = null;
			try {
				result = com.yuxin.wx.utils.HttpPostRequest.post(url+"/integration/site/training/export/history",map);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(result);
			Gson g = new Gson();
			TestTask.LiveResult re =  g.fromJson(result,TestTask.LiveResult.class);
			if(!re.getCode().equals("0")){
				System.out.println(re.getMessage());
				continue;
			}
			//用户信息过滤并存入数据库
			for(TestTask.MessUser mUser : re.getList()){
				if(Long.valueOf(mUser.getUid())-1000000000<1000000000){
					lesson.setJoinTime(mUser.getJoinTime());
					lesson.setLeaveTime(mUser.getLeaveTime());
					lesson.setUserId(Integer.parseInt(mUser.getUid())-1000000000);
					lesson.setLessonId(lesson.getLessonId());
					lesson.setWatchTime(Long.parseLong(mUser.getLeaveTime())-Long.parseLong(mUser.getJoinTime()));
					lesson.setDevice(mUser.getDevice());
					lesson.setId(null);
					watchInfoServiceImpl.addWatchInfo(lesson);
				}
			}
		}
	}

}
