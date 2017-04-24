package com.yuxin.wx.controller.classes;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.auth.IAuthRoleService;
import com.yuxin.wx.api.auth.IAuthUserRoleService;
import com.yuxin.wx.api.classes.*;
import com.yuxin.wx.api.company.*;
import com.yuxin.wx.api.course.*;
import com.yuxin.wx.api.student.IStudentPayMasterService;
import com.yuxin.wx.api.student.IStudentService;
import com.yuxin.wx.api.system.*;
import com.yuxin.wx.api.user.IUsersFrontService;
import com.yuxin.wx.api.user.IUsersService;
import com.yuxin.wx.classes.impl.CCLiveRoomServiceImpl;
import com.yuxin.wx.classes.impl.EketangLiveRoomServiceImpl;
import com.yuxin.wx.classes.impl.GenseeLiveRoomServiceImpl;
import com.yuxin.wx.common.*;
import com.yuxin.wx.company.mapper.CompanyServiceStaticDayMapper;
import com.yuxin.wx.company.mapper.CompanyServiceStaticMapper;
import com.yuxin.wx.course.mapper.VideoMapper;
import com.yuxin.wx.model.classes.*;
import com.yuxin.wx.model.classes.liveroom.ZsReturnInfo;
import com.yuxin.wx.model.company.*;
import com.yuxin.wx.model.course.*;
import com.yuxin.wx.model.student.Student;
import com.yuxin.wx.model.student.StudentPayMaster;
import com.yuxin.wx.model.system.*;
import com.yuxin.wx.model.user.UserMessage;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.model.user.UsersFront;
import com.yuxin.wx.util.*;
import com.yuxin.wx.util.HttpPostRequest;
import com.yuxin.wx.util.MD5;
import com.yuxin.wx.util.ParameterUtil;
import com.yuxin.wx.utils.DateUtil;
import com.yuxin.wx.utils.*;
import com.yuxin.wx.utils.smsClient.interfacej.SmsClientSend;
import com.yuxin.wx.vo.classes.*;
import com.yuxin.wx.vo.common.email.Mail;
import com.yuxin.wx.vo.common.email.NoticeEmail;
import com.yuxin.wx.vo.course.CourseRemoteVo;
import com.yuxin.wx.vo.course.CourseVideoMarqueeVo;
import com.yuxin.wx.vo.course.VideoVo;
import com.yuxin.wx.vo.system.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.DigestException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Controller of ClassModule
 * @author wang.zx
 * @date 2014-12-5
 */
@Controller
@RequestMapping("/classModule")
public class ClassModuleController {

	private Log log = LogFactory.getLog("log");

    @Autowired
    private ICompanyVideoConfigService companyVideoConfigImpl;
	@Autowired
	private CompanyServiceStaticMapper companyServiceStaticMapper;
	@Autowired
	private CompanyServiceStaticDayMapper companyServiceStaticDayMapper;
	@Autowired
	private VideoMapper videoMapper;
	@Autowired
	private IVideoService videoImpl;
	@Autowired
	private IUsersFrontService usersFrontService;
	@Autowired
	private ICompanyLiveCoursewareZsService companyLiveCoursewareZsImpl;
	@Autowired
	private ICompanyTotalLiveStaticDetailService companyTotalLiveStaticDetailServiceImpl;
	@Autowired
	private ICompanyLiveStaticDayDetailService companyLiveStaticDayDetailServiceImpl;
	@Autowired
	private ICompanyLiveConcurrentService companyLiveConcurrentServiceImpl;
	@Autowired
	private ICompanyLiveConfigService companyLiveConfigServiceImpl;
	@Autowired
	private ICompanyServiceStaticService companyServiceStaticServiceImpl;
	@Autowired
	private IAuthRoleService authRoleServiceImpl;
	@Autowired
	private CCLiveRoomServiceImpl CCLiveRoomServiceImpl;
	@Autowired
	private GenseeLiveRoomServiceImpl genseeLiveRoomServiceImpl;
	@Autowired
	private EketangLiveRoomServiceImpl eketangLiveRoomServiceImpl;
	@Autowired
	private ILiveOpenCourseService liveOpenCourseServiceImpl;
	@Autowired
	private ICompanyMemberServiceService companyMemberServiceServiceImpl;
	@Autowired
	private IAuthUserRoleService authUserRoleServiceImpl;
	@Autowired
	private ICompanyService companyServiceImpl;
	@Autowired
	private ICompanyEmailHistoryService companyEmailServiecimpl;
	@Autowired
	private ICompanyMessageHistoryService companyMessageHistoryServiceImpl;
	@Autowired
	private IStudentService studentServiceImpl;
	@Autowired
	private ICompanyStudentMessageService companyStudentMessageServiceImpl;
	@Autowired
	private IStudentPayMasterService studentPayMasterServiceImpl;
	@Autowired
	private PropertiesUtil properties;
	@Autowired
	private ISysConfigTeacherService sysConfigTeacherServiceImpl;
	@Autowired
	private IClassModuleLessonService classModuleLessonServiceImpl;
	@Autowired
	private IClassModuleService classModuleServiceImpl;
	@Autowired
	private ICourseVideoMarqueeService courseVideoMarqueeServiceImpl;
	@Autowired
	private ICourseVideoService courseVideoServiceImpl;
	@Autowired
	private IClassModuleVideoRelationService classModuleVideoRelationServiceImpl;
	@Autowired
	private IClassModuleNoService classModuleNoServiceImpl;
	@Autowired
	private ISysConfigItemService sysConfigItemServiceImpl;
	@Autowired
	private ISysConfigDictService sysConfigDictServiceimpl;
	@Autowired
	private ICourseRemoteService courseRemoteServiceImpl;
	@Autowired
	private IClassTypeService classTypeServiceImpl;
	@Autowired
	private IClassTypeModuleRelationService classTypeModuleRelationServiceImpl;
	@Autowired
	private IClassModuleNoOnsaleService classModuleNoOnsaleServiceImpl;
	@Autowired
	private IVideoCourseCommentService videoCourseCommentServiceImpl;
	@Autowired
	private ISysTeacherPersonalStatusService teacherPersonalStatusServiceImpl;
	@Autowired
	private ISysTeacherPersonalStatusReplayService sysTeacherPersonalStatusReplayImpl;
	@Autowired
	private PropertiesUtil propertiesUtil;
	@Autowired
	private ISysTeacherPersonalStatusPicService sysTeacherPersonalStatusPicImpl;
	@Autowired
	private IUsersService usersServiceImpl;
	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");


	//查询在售班号列表
	@ResponseBody
	@RequestMapping(value="/queryOnsaleList",method=RequestMethod.POST)
	public List<ClassmoduleNoOnsaleVo> queryOnsaleList(Integer classTypeId){
		return classModuleServiceImpl.queryModuleNoByClasstypeId(classTypeId);
	}

	//查询在售班号列表
	@ResponseBody
	@RequestMapping(value="/queryOnsaleList2",method=RequestMethod.POST)
	public List<ClassmoduleNoOnsaleVo> queryOnsaleList2(Integer moduleId){
		return classModuleServiceImpl.queryModuleNoByModuleId(moduleId);
	}

	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, ClassModule search){
		if (search == null) {
			search = new ClassModule();
			// search.setPageSize(20);
		}
		model.addAttribute("list", classModuleServiceImpl.findClassModuleByPage(search));
		return "classModule/list";
	}

	@ResponseBody
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(HttpServletRequest request,ClassModule classModule,String ralationsIdVal) {
		int currentUserId=WebUtils.getCurrentUserId(request);
		classModule.setCreateTime(new Date());
		classModule.setUpdateTime(new Date());
		classModule.setUpdator(currentUserId);
		classModule.setSchoolId(WebUtils.getCurrentSchoolId());
		classModule.setCreator(currentUserId);
		classModule.setDelFlag(0);
		classModule.setCompanyId(WebUtils.getCurrentCompanyId());
		classModuleServiceImpl.insert(classModule);
		if(classModule.getTeachMethod().equals("TEACH_METHOD_VIDEO")){
			List<ClassModuleVideoRelation> listCmvr=new ArrayList<ClassModuleVideoRelation>();
			String [] rids=ralationsIdVal.split(",");
			for(int i=0;i<rids.length;i++){
				ClassModuleVideoRelation cmvr=new ClassModuleVideoRelation();
				cmvr.setModuleId(classModule.getId());
				cmvr.setVideoId(Integer.parseInt(rids[i]));
				listCmvr.add(cmvr);
			}
			classModuleVideoRelationServiceImpl.batchInsert(listCmvr);
		}
		return JsonMsg.SUCCESS;
	}

	@ResponseBody
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(Model model, ClassModule classModule,HttpServletRequest request,String ralationsIdVal) {
		int currentUserId=WebUtils.getCurrentUserId(request);
		classModule.setUpdator(currentUserId);
		classModule.setUpdateTime(new Date());
		classModuleServiceImpl.update(classModule);
		if(classModule.getTeachMethod()!=null&&classModule.getTeachMethod().equals("TEACH_METHOD_VIDEO")){
			classModuleVideoRelationServiceImpl.deleteByModuleId(classModule.getId().toString());
			List<ClassModuleVideoRelation> listCmvr=new ArrayList<ClassModuleVideoRelation>();
			String [] rids=ralationsIdVal.split(",");
			for(int i=0;i<rids.length;i++){
				ClassModuleVideoRelation cmvr=new ClassModuleVideoRelation();
				cmvr.setModuleId(classModule.getId());
				cmvr.setVideoId(Integer.parseInt(rids[i]));
				listCmvr.add(cmvr);
			}
			classModuleVideoRelationServiceImpl.batchInsert(listCmvr);
		}
		return JsonMsg.SUCCESS;
	}

	//修改模块
	@ResponseBody
	@RequestMapping(value="/updateMoudle", method = RequestMethod.POST)
	public String updateMoudle(ClassModule classModule,HttpServletRequest request) {
		int currentUserId=WebUtils.getCurrentUserId(request);
		classModule.setUpdator(currentUserId);
		classModule.setUpdateTime(new Date());
		classModuleServiceImpl.update(classModule);
		return JsonMsg.SUCCESS;
	}

	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		classModuleServiceImpl.deleteClassModuleById(id);
		return "redirect:/classModule";
	}

	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public ClassModule getJson(Model model, @PathVariable Integer id){
		return classModuleServiceImpl.findClassModuleById(id);
	}


	@RequestMapping(value="/toAddModule", method = RequestMethod.GET)
	public String addModule(HttpServletRequest request,Model model,ClassModule classModule) {
		if(classModule.getId()!=null&&!classModule.getId().equals("")){
			classModule=classModuleServiceImpl.findClassModuleById(classModule.getId());
			model.addAttribute("toMethod", "update");
			if(classModule.getTeachMethod().equals("TEACH_METHOD_VIDEO")){
				CourseVideo videoSearch=new CourseVideo();
				videoSearch.setSchoolId(WebUtils.getCurrentSchoolId());
				videoSearch.setPublishStatus("COURSE_VIDEO_PUBLISHED");
				videoSearch.setItemOneId(classModule.getItemOneId());
				List<CourseVideo> videos=courseVideoServiceImpl.findCourseVideoByPage(videoSearch);
				model.addAttribute("listVideos", videos);
				List<CourseVideo> courseVideos=courseVideoServiceImpl.findCourseVideoByModuleId(classModule.getId());
				model.addAttribute("courseVideos", courseVideos);
			}

		}else{
			model.addAttribute("toMethod", "add");
		}
		model.addAttribute("module", classModule);
		return "module/moduleUpdate";
	}

	/**
	 *
	 * Class Name: ClassModuleController.java
	 * @Description: 根据id查询模块
	 * @author Kevin
	 * @date 2014-12-19 上午10:45:57
	 * @version 1.0
	 * @param model
	 * @param classModule
	 * @return
	 */
	@RequestMapping(value="/queryOneById", method = RequestMethod.POST)
	public String queryOneById(Model model,ClassModule classModule) {
		ClassModuleVo cmodule=classModuleServiceImpl.queryOneVoById(classModule.getId());
		model.addAttribute("module", cmodule);
		return "module/moduleAjaxDetail";
	}
	/**
	 *
	 * Class Name: ClassModuleController.java
	 * @Description: genj
	 * @author Keyn
	 * @date 2014-12-19 上午10:46:43
	 * @version 1.0
	 * @param model
	 * @param classModule
	 * @return
	 */
	@RequestMapping(value="/queryVideosToModule", method = RequestMethod.POST)
	public String queryVideosToModule(Model model,CourseVideo videoSearch) {
		List<CourseVideo> videos=courseVideoServiceImpl.findCourseVideoByPage(videoSearch);
		model.addAttribute("list", videos);
		return "module/moduleAjaxVideo";
	}



	/**
	 *
	 * Class Name: ClassModuleController.java
	 * @Description: 跳转至主页
	 * @author zhangbo
	 * @date 2014-12-18 下午2:49:05
	 * @version 1.0
	 * @param model
	 * @param classModule
	 * @return
	 */
	@RequestMapping(value="/queryModuleIndex",method = RequestMethod.GET)
	public String queryModuleIndex(Model model, ClassModule classModule){
		return "module/moduleList";
	}

	/**
	 *
	 * Class Name: ClassModuleController.java
	 * @Description: 根据条件查询模块分页
	 * @author zhangbo
	 * @date 2014-12-18 下午2:48:28
	 * @version 1.0
	 * @param request
	 * @param response
	 * @param model
	 * @param classModule
	 * @return
	 */
	/*@RequestMapping(value="/moduleAjaxList",method = RequestMethod.POST)
	public String queryModuleList(Model model,HttpServletRequest request, ClassModule classModule){
		classModule.setSchoolId(WebUtils.getCurrentSchoolId());
		PageFinder<ClassModuleVo> pf= classModuleServiceImpl.queryClassModuleByKeys(classModule);
		model.addAttribute("pageFinder",pf);
		return "module/moduleAjaxList";
	}*/

	/**
	 * @Description: 根据条件查询对应的模块
	 * @author wzx
	 * @date 2015-5-6 下午5:08:56
	 * @version 1.0
	 * @param model
	 * @param classModule
	 * @return
	 */
	@RequestMapping(value="/moduleAjaxModule",method = RequestMethod.POST)
	public String moduleAjaxSetModule(Model model, ClassModule classModule){
		classModule.setPublishStatus("MODULE_PUBLISHED");
		List<ClassModuleVo> modules = classModuleServiceImpl.queryClassModuleByKeys(classModule);
		List<ClassModuleVo> liveModule = null;
		List<ClassModuleVo> faceModule = null;

		if(modules != null && modules.size() > 0){
			for(ClassModuleVo module : modules){
				if(module.getTeachMethod().equals(Constant.TEACH_METHOD_FACE)){
					if(faceModule == null){
						faceModule = new ArrayList<ClassModuleVo>();
					}
					faceModule.add(module);
				}else if(module.getTeachMethod().equals(Constant.TEACH_METHOD_LIVE)){
					if(liveModule == null){
						liveModule = new ArrayList<ClassModuleVo>();
					}
					liveModule.add(module);
				}
			}
		}
		model.addAttribute("liveModule",liveModule);
		model.addAttribute("faceModule",faceModule);
		return "module/moduleAjaxModule";
	}

	/**
	 *
	 * Class Name: ClassModuleController.java
	 * @Description: 根据一二级项目、班型、考期获取模块
	 * @author chopin
	 * @date 2014-12-18 下午2:47:29
	 * @version 1.0
	 * @param model
	 * @param classModule
	 * @return
	 */
	/*@RequestMapping(value="/getList",method = RequestMethod.POST)
	public String moduleAjaxList(Model model, ClassModule classModule){
		PageFinder<ClassModuleVo> pf= classModuleServiceImpl.queryClassModuleByKeys(classModule);
		model.addAttribute("pageFinder",pf);
		return "module/moduleAjaxSetModule";
	}*/

	/**
	 * Class Name: ClassModuleController.java
	 * @Description:(根据二级项目查询对应的模块)
	 * @author wang.zx
	 * @date 2014-12-27 下午7:14:10
	 * @version 1.0
	 * @param secondItemId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="modules/{secondItemId}/{isAll}")
	public List<ClassModule> modules(HttpServletRequest request, @PathVariable Integer secondItemId, @PathVariable Integer isAll){
		ClassModule newModule = new ClassModule();
		newModule.setItemSecondId(secondItemId);
		newModule.setSchoolId(WebUtils.getCurrentUserSchoolId(request));
		if(isAll == 0){
			newModule.setTeachMethod(Constant.TEACH_METHOD_VIDEO);
		}
		List<ClassModule> modules = classModuleServiceImpl.findModulesByModule(newModule);

		if(modules != null && modules.size() > 0){
			for(ClassModule module : modules){
				SysConfigDict type = SysLoader.getDict(module.getModuleType());
				SysConfigDict method = SysLoader.getDict(module.getTeachMethod());
				if(type != null && type.getItemValue() != null){
					module.setModuleType(type.getItemValue());
				}else{
					module.setModuleType("");
				}
				if(method != null && method.getItemValue() != null){
					module.setTeachMethod(method.getItemValue());
				}else{
					module.setTeachMethod("");
				}
				module.setDelFlag(0);
			}
		}
		return modules;
	}

	/**
	 * @Description:(上下架班号)
	 * @author wang.zx
	 * @date 2015-2-1 下午1:33:36
	 * @version 1.0
	 * @param request
	 * @param id
	 * @param publishStatus
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="shelvesOrUnderModuleNo")
	public String shelvesOrUnderModuleNo(HttpServletRequest request, Integer id, String publishStatus){
		ClassModule module = classModuleServiceImpl.findClassModuleById(id);
		if(module != null){
			module.setPublishStatus(publishStatus);
		}
		try {
			classModuleServiceImpl.update(module);
			return "succ";
		} catch (Exception e) {
			return "failure";
		}
	}

	@ResponseBody
	@RequestMapping(value="/checkUpdate",method = RequestMethod.POST)
	public boolean checkUpdate(Model model, ClassModule classModule){
		Integer count=classModuleServiceImpl.checkUpdate(classModule.getId());
		if(count!=null&&count>0){
			return true;
		}else{
			return false;
		}
	}

	@ResponseBody
	@RequestMapping(value="/checkStop",method = RequestMethod.POST)
	public boolean checkStop(Model model, ClassModule classModule){
		Integer count=classModuleServiceImpl.checkStop(classModule.getId());
		if(count!=null&&count>0){
			return true;
		}else{
			return false;
		}
	}

	@ResponseBody
	@RequestMapping(value="/checkModuleNoStop",method = RequestMethod.POST)
	public boolean checkModuleNoStop(Model model, ClassModule classModule){
		ClassModuleNo classModuleNo=new ClassModuleNo();
		classModuleNo.setModuleId(classModule.getId());
		classModuleNo.setPublishStatus("MODULE_NO_ON_SALE");
		Integer count=classModuleNoServiceImpl.findCountByKeys(classModuleNo);
		if(count!=null&&count>0){
			return true;
		}else{
			return false;
		}
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
	 *
	 * Class Name: ClassModuleController.java
	 * @Description: 上直播信息
	 * @author 周文斌
	 * @date 2015-5-11 上午10:26:38
	 * @version 1.0
	 * @param model
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/show")
	public String show(Model model,HttpServletRequest request) throws ParseException{
		Integer userId = WebUtils.getCurrentUserId(request);
		if(authRoleServiceImpl.hasRoleFlag(userId)){
			model.addAttribute("role", "admin");
		}else{
			model.addAttribute("role", "teacher");
		}
		return "operate/live/live";
	}

	/**
	 *
	 * Class Name: ClassModuleController.java
	 * @Description: 查询 上直播   分页
	 * @author 周文斌
	 * @date 2015-5-11 下午6:50:18
	 * @version 1.0
	 * @param model
	 * @param request
	 * @param mlv
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/selClass")
	public String selClass(Model model,HttpServletRequest request,ModuleLessonVo mlv) throws ParseException{
		Integer schoolId = WebUtils.getCurrentUserSchoolId(request);
		Integer userId = WebUtils.getCurrentUserId(request);
		//当前用户 是否是 老师
		SysConfigTeacher teacher = sysConfigTeacherServiceImpl.findByUserId(userId);
		if(authRoleServiceImpl.hasRoleFlag(userId)){
			mlv.setSchoolId(schoolId);
			if(teacher != null){
				model.addAttribute("roles", "adminAndTeacher");
			}
			model.addAttribute("role", "admin");
		}else{
			mlv.setSchoolId(schoolId);
			mlv.setTeacherId(userId);
			model.addAttribute("role", "teacher");
		}
		Date date = new Date();
		String da = new SimpleDateFormat("yyyy-MM-dd").format(date);
		Integer hours = date.getHours();
		Integer minutes = date.getMinutes();
		mlv.setPageSize(5);
		if(mlv.getStatus() != null){
			if(mlv.getStatus().equals(1) || mlv.getStatus().equals(0)){
				mlv.setLessonDate(new SimpleDateFormat("yyyy-MM-dd").parse(da));
			}else if(mlv.getStatus().equals(2)){
				mlv.setLessonDate(DateUtil.addDate(new SimpleDateFormat("yyyy-MM-dd").parse(da), 1));
			}else if(mlv.getStatus().equals(3)){
				mlv.setLessonDate(DateUtil.addDate(new SimpleDateFormat("yyyy-MM-dd").parse(da), -1));
			}
			if(mlv.getStatuss().equals(4) || mlv.getStatuss().equals(5)
					|| mlv.getStatuss().equals(6)){
				if(mlv.getStatus().equals(2)){
					mlv.setCurrentTime("00:00");
				}else if(mlv.getStatus().equals(3)){
					mlv.setCurrentTime("23:59");
				}else{
					mlv.setCurrentTime((hours.toString().length() < 2 ? "0"
							+ hours : hours)
							+ ":" +
							(minutes.toString().length() < 2 ? "0"
							+ minutes : minutes));
				}
			}
		}

		//查询直播课信息 ，根据 schoolId
		List<ModuleLessonVo> moduleList = classModuleServiceImpl.findBySchoolId(mlv);
		for (ModuleLessonVo m : moduleList) {
			StringBuilder sbStart = new StringBuilder(new SimpleDateFormat("yyyy-MM-dd").format(m.getLessonDate()));
			StringBuilder sbEnd = new StringBuilder(new SimpleDateFormat("yyyy-MM-dd").format(m.getEndDate()));
			sbStart.append(" " + m.getLessonTimeStart());
			sbEnd.append(" " + m.getLessonTimeEnd());
			m.setLiveDate(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(sbStart.toString()));
			m.setLiveEnd(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(sbEnd.toString()));
			Long start = m.getLiveDate().getTime();
			Long end = m.getLiveEnd().getTime();
			Long cha = (end - start);
			Long dd = (cha / ( 24 * 60 * 60 * 1000));
			Long hh = ((cha - dd * 24 * 60 * 60 * 1000) / (60 * 60 * 1000));
			Long mm = ((cha - dd * 24 * 60 * 60 * 1000 - hh * 60 * 60 * 1000) / (60 * 1000));
			String day = dd.toString();
			String hour = hh.toString();
			String minute = mm.toString();
			day = day.length() < 2 ? "0" + day : day;
			hour = hour.length() < 2 ? "0" + hour : hour;
			minute = minute.length() < 2 ? "0" + minute : minute;

			m.setTimeLong(day + " 天 " + hour + " 小时 " + minute + " 分钟 ");

			Map<String,Object> param = new HashMap<String, Object>();
			param.put("companyId", WebUtils.getCurrentCompanyId());
			param.put("schoolId", WebUtils.getCurrentUserSchoolId(request));
			param.put("resourceId", m.getModuleNoId());
			Integer count = null;
			if(m.getClassType().equals(1)){
				count = studentPayMasterServiceImpl.findByResourceid(param);
			}

			if(m.getEndDate().before(new SimpleDateFormat("yyyy-MM-dd").parse(da))){
				m.setIsday(1);
			}else if(m.getLessonDate().after(new SimpleDateFormat("yyyy-MM-dd").parse(da))){
				m.setIsday(2);
			}else{
				m.setIsday(0);
			}

			//根据teacherid 查询 userid
			SysConfigTeacher teachers = null;
			if(teacher != null){
				if(teacher.getTeacherType().equals("PERSON_TEACHER")){
					teachers = sysConfigTeacherServiceImpl.findSysConfigTeacherById(m.getTeacherId());
				}
				if(teacher.getTeacherType().equals("PERSON_ASSISTANT")){
					teachers = sysConfigTeacherServiceImpl.findSysConfigTeacherById(m.getAssistantId());
				}
			}
			if(teachers != null){
				m.setUserId(teachers.getUserId());
			}

			m.setEnrollYetStudents(count);
		}

		//查询总条数
		Integer count = classModuleServiceImpl.findCountBySchoolId(mlv);

		PageFinder<ModuleLessonVo> mvlPage = new PageFinder<ModuleLessonVo>(mlv.getPage(),mlv.getPageSize(),count,moduleList);

		model.addAttribute("userid", userId);
		model.addAttribute("mvlPage", mvlPage);
		return "operate/live/liveInfo";
	}
	/**
	 * 查询首页直播
	 * @author licong
	 * @date 2016年10月20日 下午7:57:44
	 * @param
	 * @param model
	 * @param request
	 * @param mlv
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping("/selIndexLiveClass")
	public Object selIndexLiveClass(Model model,HttpServletRequest request,ModuleLessonVo mlv) throws ParseException{
		Integer schoolId = WebUtils.getCurrentUserSchoolId(request);
		Integer userId = WebUtils.getCurrentUserId(request);
		SysConfigTeacher teacher = sysConfigTeacherServiceImpl.findByUserId(userId);

		mlv.setSchoolId(schoolId);
		mlv.setTeacherId(userId);
		mlv.setPageSize(1);

		Date date = new Date();
		String da = new SimpleDateFormat("yyyy-MM-dd").format(date);
		Integer hours = date.getHours();
		Integer minutes = date.getMinutes();

		//首先查询今天直播中的，没有的话查询今天未直播的，再没有查询未来的，再没有查询昨天的
		List<ModuleLessonVo> moduleList = null;
		mlv.setLessonDate(new SimpleDateFormat("yyyy-MM-dd").parse(da));
		//今天直播中
		mlv.setStatus(0);
		mlv.setStatuss(4);
		mlv.setCurrentTime((hours.toString().length() < 2 ? "0"
				+ hours : hours)
				+ ":" +
				(minutes.toString().length() < 2 ? "0"
				+ minutes : minutes));
		moduleList = classModuleServiceImpl.findBySchoolId(mlv);

		mlv.setStatuss(null);
		//今天直播未开始
		if(moduleList == null || moduleList.size()==0){
			mlv.setStatus(4);
			moduleList = classModuleServiceImpl.findBySchoolId(mlv);
		}
		//7天内直播未开始(不包含今天)
		if(moduleList == null || moduleList.size()==0){
			mlv.setStatus(5);
			mlv.setLessonDate(DateUtil.addDate(new SimpleDateFormat("yyyy-MM-dd").parse(da), 7));
			moduleList = classModuleServiceImpl.findBySchoolId(mlv);
		}

		//查询直播课信息 ，根据 schoolId
		for (ModuleLessonVo m : moduleList) {
			StringBuilder sbStart = new StringBuilder(new SimpleDateFormat("yyyy-MM-dd").format(m.getLessonDate()));
			StringBuilder sbEnd = new StringBuilder(new SimpleDateFormat("yyyy-MM-dd").format(m.getEndDate()));
			sbStart.append(" " + m.getLessonTimeStart());
			sbEnd.append(" " + m.getLessonTimeEnd());
			m.setLiveDate(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(sbStart.toString()));
			m.setLiveEnd(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(sbEnd.toString()));
			Long start = m.getLiveDate().getTime();
			Long end = m.getLiveEnd().getTime();
			Long cha = (end - start);
			Long dd = (cha / ( 24 * 60 * 60 * 1000));
			Long hh = ((cha - dd * 24 * 60 * 60 * 1000) / (60 * 60 * 1000));
			Long mm = ((cha - dd * 24 * 60 * 60 * 1000 - hh * 60 * 60 * 1000) / (60 * 1000));
			String day = dd.toString();
			String hour = hh.toString();
			String minute = mm.toString();
			day = day.length() < 2 ? "0" + day : day;
			hour = hour.length() < 2 ? "0" + hour : hour;
			minute = minute.length() < 2 ? "0" + minute : minute;

			m.setTimeLong(day + " 天 " + hour + " 小时 " + minute + " 分钟 ");

			Map<String,Object> param = new HashMap<String, Object>();
			param.put("companyId", WebUtils.getCurrentCompanyId());
			param.put("schoolId", WebUtils.getCurrentUserSchoolId(request));
			param.put("resourceId", m.getModuleNoId());
			Integer count = null;
			if(m.getClassType().equals(1)){
				count = studentPayMasterServiceImpl.findByResourceid(param);
			}

			if(m.getEndDate().before(new SimpleDateFormat("yyyy-MM-dd").parse(da))){
				m.setIsday(1);
			}else if(m.getLessonDate().after(new SimpleDateFormat("yyyy-MM-dd").parse(da))){
				m.setIsday(2);
			}else{
				m.setIsday(0);
			}

			//根据teacherid 查询 userid
			SysConfigTeacher teachers = null;
			if(teacher != null){
				if(teacher.getTeacherType().equals("PERSON_TEACHER")){
					teachers = sysConfigTeacherServiceImpl.findSysConfigTeacherById(m.getTeacherId());
				}
				if(teacher.getTeacherType().equals("PERSON_ASSISTANT")){
					teachers = sysConfigTeacherServiceImpl.findSysConfigTeacherById(m.getAssistantId());
				}
			}
			if(teachers != null){
				m.setUserId(teachers.getUserId());
			}

			m.setEnrollYetStudents(count);
		}
		Subject subject = SecurityUtils.getSubject();
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("liveTeacher", subject.hasRole("直播老师"));
		result.put("paikeTeacher", subject.hasRole("排课老师"));
		result.put("module", moduleList);
		return result;
	}
	@RequestMapping(value="toTeach")
	public String toTeach(Model model){
		Integer companyId = WebUtils.getCurrentCompanyId();
		SysConfigItem search = new SysConfigItem();
		search.setCompanyId(companyId);
		search.setItemType("1");
		//查询出公司所有一级项目
		List<SysConfigItem> oneItems = sysConfigItemServiceImpl.findSysConfigItemByPid("1", null, WebUtils.getCurrentCompanyId(), WebUtils.getCurrentSchoolId());
		//List<SysConfigItem> oneItems = sysConfigItemServiceImpl.findItem(search);
		model.addAttribute("oneItems", oneItems);
		if(oneItems!=null && !oneItems.isEmpty()){
			//查询出第一个一级项目所有的二级项目
			List<SysConfigItem> twoItems = sysConfigItemServiceImpl.findTwoByOneId(oneItems.get(0).getId());
			model.addAttribute("twoItems", twoItems);
			model.addAttribute("one", oneItems.get(0).getId());
		}
		return "/classType/teach/main";
	}
	@RequestMapping(value="toSearch",method=RequestMethod.POST)
	public String toSearch(ClassModule module,Model model){

		module.setCompanyId(WebUtils.getCurrentCompanyId());
		model.addAttribute("method", module.getTeachMethod());
		module.setSchoolId(WebUtils.getCurrentSchoolId());
		if("TEACH_METHOD_REMOTE".equals(module.getTeachMethod())){
			CourseRemote remote = new CourseRemote();
			remote.setItemOneId(module.getItemOneId());
			remote.setItemSecondId(module.getItemSecondId());
			remote.setCompanyId(WebUtils.getCurrentCompanyId());
			remote.setPage(module.getPage());
			 PageFinder<CourseRemoteVo> page = courseRemoteServiceImpl.queryCourseRemoteVoByKeys(remote);
			model.addAttribute("pageFinder", page);
			return "/classType/teach/remote";
		}
		PageFinder<ClassModuleVo> pageFinder = classModuleServiceImpl.querySearchPage(module);

		model.addAttribute("pageFinder", pageFinder);
		return "/classType/teach/live";
	}
	/**
	 *
	 * Class Name: ClassModuleController.java
	 * @Description: 做假删除
	 * @author 权飞虎
	 * @date 2015年5月12日 下午5:25:31
	 * @modifier
	 * @modify-date 2015年5月12日 下午5:25:31
	 * @version 1.0
	 * @param moduleId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="changeStatus",method=RequestMethod.POST)
	public String changeStatus(Integer moduleId){
		ClassModule module = new ClassModule();
		module.setId(moduleId);
		module.setUpdateTime(new Date());
		module.setUpdator(WebUtils.getCurrentUser().getId());
		classModuleServiceImpl.changeStatus(module);
		return JsonMsg.SUCCESS;
	}
	/**
	 *
	 * Class Name: ClassModuleController.java
	 * @Description: 跳转到添加模块页面(直播和面授)
	 * @author 权飞虎
	 * @date 2015年5月12日 下午6:08:04
	 * @modifier
	 * @modify-date 2015年5月12日 下午6:08:04
	 * @version 1.0
	 * @param itemOneId
	 * @param itemTwoId
	 * @param teachMethod
	 * @return
	 */
	@RequestMapping(value="toAddModule2",method=RequestMethod.POST)
	public String toAddModule(Integer itemOneId,Integer itemTwoId,String teachMethod,Model model,Integer id){
		Integer companyId = WebUtils.getCurrentCompanyId();
		SysConfigItem search = new SysConfigItem();
		search.setCompanyId(companyId);
		search.setItemType("1");
		//查询出公司所有一级项目
		List<SysConfigItem> oneItems = sysConfigItemServiceImpl.findSysConfigItemByPid("1", null, WebUtils.getCurrentCompanyId(), WebUtils.getCurrentSchoolId());
		//List<SysConfigItem> oneItems = sysConfigItemServiceImpl.findItem(search);
		model.addAttribute("oneItems", oneItems);
		//查询出一级项目所有的二级项目
		List<SysConfigItem> twoItems = sysConfigItemServiceImpl.findTwoByOneId(itemOneId);
		model.addAttribute("twoItems", twoItems);
		model.addAttribute("itemOneId", itemOneId);
		model.addAttribute("itemTwoId", itemTwoId);
		model.addAttribute("teachMethod", teachMethod);
		if("TEACH_METHOD_REMOTE".equals(teachMethod)){


			return "/classType/teach/editRemote";
		}
		//查询所有模块类型
		String code="MODULE_TYPE";
		List<SysConfigDict> types = sysConfigDictServiceimpl.findByDicCode(code);
		model.addAttribute("types", types);
		if(id!=null&&!"".equals(id)){
			ClassModule classModule = classModuleServiceImpl.findClassModuleById(id);
			//SysConfigItem one = sysConfigItemServiceImpl.findSysConfigItemById(classModule.getItemOneId());
			//SysConfigItem two = sysConfigItemServiceImpl.findSysConfigItemById(classModule.getItemSecondId());
			twoItems = sysConfigItemServiceImpl.findTwoByOneId(classModule.getItemOneId());
			itemOneId=classModule.getItemOneId();
			itemTwoId=classModule.getItemSecondId();
			model.addAttribute("twoItems", twoItems);
			model.addAttribute("moduleType", classModule.getModuleType());
			model.addAttribute("classModule", classModule);
			//return "/classType/teach/editVideo";
		}
		return "/classType/teach/live_add";
	}
	@ResponseBody
	@RequestMapping(value="checkName",method=RequestMethod.POST)
	public Boolean checkName(String name){
		List<ClassModule> list = classModuleServiceImpl.findByName(name,WebUtils.getCurrentCompanyId());
		if(list.size()>0){
			return false;
		}else{
			return true;
		}

	}
	@ResponseBody
	@RequestMapping(value="checkName2",method=RequestMethod.POST)
	public Boolean checkName2(String name,Integer id){
		List<ClassModule> list = classModuleServiceImpl.findByName(name,WebUtils.getCurrentCompanyId());
		//int count=0;
		if(list.size()>0){
			for (ClassModule classModule : list) {
				if(!classModule.getId().equals(id)){

					return false;

				}
			}
			return true;
		}else{
			return true;
		}

	}
	@ResponseBody
	@RequestMapping(value="saveModule",method=RequestMethod.POST)
	public Integer saveModule(ClassModule module,Model model){
		module.setDelFlag(0);
		module.setCompanyId(WebUtils.getCurrentCompanyId());

		module.setUpdateTime(new Date());
		module.setUpdator(WebUtils.getCurrentUser().getId());
		module.setSchoolId(WebUtils.getCurrentSchoolId());
		module.setPublishStatus("MODULE_PUBLISHED");
		if(module.getId()!=null&&!"".equals(module.getId())){
			classModuleServiceImpl.update(module);
		}else{
			module.setCreateTime(new Date());
			module.setCreator(WebUtils.getCurrentUser().getId());
			classModuleServiceImpl.insert(module);
		}

		return module.getId();
	}
	@RequestMapping(value="detail",method=RequestMethod.POST)
	public String detail(Integer id,Model model){
		ClassModuleVo moduleVo = classModuleServiceImpl.findClassModuleVoById(id);
		model.addAttribute("moduleVo", moduleVo);
		//使用此模块的班型
		List<ClassType> list = classTypeServiceImpl.findByModule(id);
		model.addAttribute("list", list);
		//根据模块查询班号
		List<ClassModuleNoListVo> classModuleNos = classModuleNoServiceImpl.findVoByCmId(id);
		model.addAttribute("classModuleNos", classModuleNos);
		return "/classType/teach/liveDetail";
	}
	@RequestMapping(value="remoteDetail",method=RequestMethod.POST)
	public String remoteDetail(Integer id,Model model){
		CourseRemoteVo remote = courseRemoteServiceImpl.findByOneId(id);
		model.addAttribute("remote", remote);
		//使用此模块的课程
		List<ClassType> list = classTypeServiceImpl.findByRemote(id);
		model.addAttribute("list", list);

		return "/classType/teach/remoteDetail";
	}
	@RequestMapping(value="editRemote",method=RequestMethod.POST)
	public String editRemote(Integer id,Model model){
		Integer companyId = WebUtils.getCurrentCompanyId();
		SysConfigItem search = new SysConfigItem();
		search.setCompanyId(companyId);
		search.setItemType("1");
		//查询出该远程模块
		CourseRemoteVo remote = courseRemoteServiceImpl.findByOneId(id);
		model.addAttribute("remote", remote);
		//查询出公司所有一级项目
		List<SysConfigItem> oneItems = sysConfigItemServiceImpl.findSysConfigItemByPid("1", null, WebUtils.getCurrentCompanyId(), WebUtils.getCurrentSchoolId());
		//List<SysConfigItem> oneItems = sysConfigItemServiceImpl.findItem(search);
		model.addAttribute("oneItems", oneItems);
		//查询出一级项目所有的二级项目
		List<SysConfigItem> twoItems = sysConfigItemServiceImpl.findTwoByOneId(remote.getItemOneId());
		model.addAttribute("twoItems", twoItems);
		model.addAttribute("itemOneId", remote.getItemOneId());
		model.addAttribute("itemTwoId", remote.getItemSecondId());
		model.addAttribute("teachMethod", "TEACH_METHOD_REMOTE");

		return "/classType/teach/editRemote";
	}
	@ResponseBody
	@RequestMapping(value="saveRemote",method=RequestMethod.POST)
	public String saveRemote(CourseRemote remote,Model model){
		if(remote.getId()==null||"".equals(remote.getId())){
			remote.setCompanyId(WebUtils.getCurrentCompanyId());
			remote.setDelFlag(0);
			remote.setStatus("REMOTE_ENABLE");
			remote.setCreateTime(new Date());
			remote.setCreator(WebUtils.getCurrentUser().getId());
			remote.setSchoolId(WebUtils.getCurrentSchoolId());
			courseRemoteServiceImpl.insert(remote);
		}else{
			remote.setUpdateTime(new Date());
			remote.setUpdator(WebUtils.getCurrentUser().getId());
			courseRemoteServiceImpl.update(remote);

		}
		return JsonMsg.SUCCESS;
	}
	/**
	 *
	 * Class Name: ClassModuleController.java
	 * @Description: 跳转到主页面
	 * @author admin
	 * @date 2015年5月14日 上午11:56:56
	 * @modifier
	 * @modify-date 2015年5月14日 上午11:56:56
	 * @version 1.0
	 * @return
	 */
	@RequestMapping(value="toMain",method=RequestMethod.POST)
	public String toMain(ClassModule module,Model model){
		if(module.getTeachMethod()==null){
			module.setTeachMethod("TEACH_METHOD_REMOTE");
		}
		Integer companyId = WebUtils.getCurrentCompanyId();
		SysConfigItem search = new SysConfigItem();
		search.setCompanyId(companyId);
		search.setItemType("1");
		//查询出公司所有一级项目
		List<SysConfigItem> oneItems = sysConfigItemServiceImpl.findSysConfigItemByPid("1", null, WebUtils.getCurrentCompanyId(), WebUtils.getCurrentSchoolId());
		//List<SysConfigItem> oneItems = sysConfigItemServiceImpl.findItem(search);

		List<SysConfigItem> twoItems = sysConfigItemServiceImpl.findTwoByOneId(module.getItemOneId());
		model.addAttribute("oneItems", oneItems);
		model.addAttribute("twoItems", twoItems);
		model.addAttribute("one", module.getItemOneId());
		model.addAttribute("two", module.getItemSecondId());
		model.addAttribute("moduleType", module.getTeachMethod());
		if("TEACH_METHOD_VIDEO".equals(module.getTeachMethod())){
			model.addAttribute("moduleId", module.getId());
			return "/classType/teach/videoAddBase";
		}
		return "/classType/teach/main";
	}
	@ResponseBody
	@RequestMapping(value="checkMajor",method=RequestMethod.POST)
	public Boolean checkMajor(String major,String schoolName){

		List<CourseRemote> list = courseRemoteServiceImpl.findByMajor(major,WebUtils.getCurrentCompanyId(),schoolName);
		if(list.size()>0){
			return false;
		}
		return true;

	}

	/**
	 *
	 * Class Name: ClassModuleController.java
	 * @Description: 根据 课次 id 查询详细
	 * @author 周文斌
	 * @date 2015-5-12 上午11:32:43
	 * @version 1.0
	 * @param model
	 * @param request
	 * @param id
	 * @param moduleId
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/classDetail")
	public String detail(Model model,HttpServletRequest request,Integer id,Integer moduleId,Integer status) throws ParseException{
		//根据 课次 id查询
		ClassModuleLesson cml = classModuleLessonServiceImpl.findClassModuleLessonById(id);
		//根据教师 id 查询 教师信息 助教 id
		SysConfigTeacher teacher = sysConfigTeacherServiceImpl.findSysConfigTeacherById(Integer.parseInt(cml.getTeachers()));
		if(cml.getAssistants() != null && !cml.getAssistants().equals("")){
			SysConfigTeacher assistant = sysConfigTeacherServiceImpl.findSysConfigTeacherById(Integer.parseInt(cml.getAssistants()));
			model.addAttribute("assistant", assistant);
		}
		//查询module  类型
		ClassModule module = classModuleServiceImpl.findClassModuleById(moduleId);

		//开始时间  结束时间
		StringBuilder sbStart = new StringBuilder(new SimpleDateFormat("yyyy-MM-dd").format(cml.getLessonDate()));
		StringBuilder sbEnd = new StringBuilder(new SimpleDateFormat("yyyy-MM-dd").format(cml.getLessonDate()));
		sbStart.append(" " + cml.getLessonTimeStart());
		sbEnd.append(" " + cml.getLessonTimeEnd());
		Date startDate = (new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(sbStart.toString()));
		Date endDate = (new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(sbEnd.toString()));
		//查询助教列表
		if(status == 1){
			List<SysConfigTeacher> assistantList = sysConfigTeacherServiceImpl.findAssistantBySchool(WebUtils.getCurrentUserSchoolId(request));
			model.addAttribute("assistantList", assistantList);
		}

		model.addAttribute("status", status);
		model.addAttribute("cml", cml);
		model.addAttribute("teacher", teacher);
		model.addAttribute("module", module);
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		return "operate/live/detail";
	}

	@ResponseBody
	@RequestMapping("/updateLesson")
	public JSONObject updateLesson(ClassModuleLesson cml,Integer assistantId,String updateTime,String startTime) throws ParseException{
		JSONObject json = new JSONObject();
		ClassModuleLesson curLesson = classModuleLessonServiceImpl.findClassModuleLessonById(cml.getId());
		Integer companyId = WebUtils.getCurrentCompanyId();
		// 打散时间
		String[] times = startTime.split(" ");
		curLesson.setLessonDate((new SimpleDateFormat("yyyy-MM-dd").parse(times[0])));
		curLesson.setLessonTimeStart(times[1].substring(0,times[1].lastIndexOf(":")));
		cml.setLessonDate((new SimpleDateFormat("yyyy-MM-dd").parse(times[0])));
		cml.setLessonTimeStart(times[1].substring(0,times[1].lastIndexOf(":")));

		String[] timest = updateTime.split(" ");
		curLesson.setLessonTimeEnd(timest[1].substring(0,timest[1].lastIndexOf(":")));
		cml.setLessonTimeEnd(timest[1].substring(0,timest[1].lastIndexOf(":")));
		//查询助教信息
		SysConfigTeacher assistant = sysConfigTeacherServiceImpl.findSysConfigTeacherById(assistantId);

		TeacherVo av = null;
		List<TeacherVo> avs = new ArrayList<TeacherVo>();
		String assistants = "";
		if(assistant != null){
			curLesson.setAssistants(assistant.getId().toString());
			curLesson.setAssistantsName(assistant.getName());
			cml.setAssistants(assistant.getId().toString());
			cml.setAssistantsName(assistant.getName());
			av = new TeacherVo(assistant.getId()+"",assistant.getName(),assistant.getName(),assistant.getHeadpicUrl(),"",assistant.getResume());
			avs.add(av);
			assistants = JSONObject.toJSONString(avs);
		}

		CompanyLiveConfig clc = companyLiveConfigServiceImpl
				.findByCompanyId(companyId);
		String openId = null;
		String openToken = null;
		if(clc != null && clc.getLiveType().equals(3)){
			openId = clc.getLoginName();
			openToken = clc.getPassword();
		}

		if(curLesson.getLiveCompanyType() == null
				|| curLesson.getLiveCompanyType().equals("gh")
				|| curLesson.getLiveCompanyType().equals("")){
			//查询 光慧直播 id
			String customer = "";
			String ue = curLesson.getStudentUrlGh();
			customer = ue.substring(ue.indexOf("&customer=") + 10,
					ue.indexOf("&customerType"));
			long timestamp = System.currentTimeMillis();
			String secretKey = "";
			if(customer.equals("yxkj")){
				secretKey = properties.getSecretKey();
			}else{
				secretKey= properties.getFreeSecretKey();
			}

			String str = MD5.getMD5ofStr(customer+timestamp+secretKey);
			str = str.substring(0,10)+str.substring(str.length() - 10);

			String url = null;

			url = properties.getInterfaceAddress() + LiveRoomConstant.UPDATE_LIVEROOM;

			String day = new SimpleDateFormat("yyyy-MM-dd").format(cml.getLessonDate());
			String startTimes = cml.getLessonTimeStart();
			String endTime = cml.getLessonTimeEnd();
			long beginTimeLong = DateUtil.parseDateTime(day,startTimes);
			long endTimeLong = DateUtil.parseDateTime(day,endTime);

			Map<String,Object> param = new HashMap<String, Object>();
			param.put("id", curLesson.getLiveRoom());
			param.put("beginTime", beginTimeLong);
			param.put("endTime", endTimeLong);
			param.put("customer", customer);
			param.put("timestamp", timestamp);
			param.put("s", str);
			//学生不需要鉴权
			param.put("fee", 1);

			param.put("assistants", assistants);
			try {

				String detail = HttpPostRequest.post(url, param);
				log.info("调用E课堂修改直播教室接口,返回信息如下：" + detail);

				Integer code = JSONObject.parseObject(detail).getInteger("code");
				if(code.equals(0)){
					cml.setLiveRoom(JSONObject.parseObject(detail).getString("id"));
					cml.setLiveroomIdGh(JSONObject.parseObject(detail).getString("liveRoomId"));
					cml.setStudentUrlGh(JSONObject.parseObject(detail).getString("studentUrl"));
					cml.setTeacherUrlGh(JSONObject.parseObject(detail).getString("teacherUrl"));
					cml.setAssistantUrlGh(JSONObject.parseObject(detail).getString("assistantUrl"));
					cml.setReplayUrlGh(JSONObject.parseObject(detail).getString("replayUrl"));

					classModuleLessonServiceImpl.update(cml);
					json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
					return json;
				}else{
					json.put(JsonMsg.MSG, "直播课修改失败");
					return json;
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				json.put(JsonMsg.MSG, "异常:直播课修改失败");
				return json;
			}
		}else if(curLesson.getLiveCompanyType().equals("zs")){
			try {
				log.info("zsup：展示修改教室");
				String detail = genseeLiveRoomServiceImpl.updateLiveRoom(curLesson, companyId);
				if(detail != null){
					Integer code = JSONObject.parseObject(detail).getInteger("code");
					if(code.equals(0)){
						classModuleLessonServiceImpl.update(curLesson);
					}
				}
				log.info(curLesson);
				json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
				return json;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				log.info("zsup：展示修改直播出错:" + e.getMessage());
				json.put(JsonMsg.MSG, "异常:直播课修改失败");
				return json;
			}

		}else if(curLesson.getLiveCompanyType().equals("ht")){
			try {
				String res = "";
				//老师介绍
				SysConfigTeacher teacher = sysConfigTeacherServiceImpl
						.findSysConfigTeacherById(Integer.parseInt(
								curLesson.getTeachers()));
				//Map<Object, Object> options = new HashMap<Object,Object>();
				Map<Object, Object> param = new HashMap<Object,Object>();
				param.put("course_name", curLesson.getLessonName());
				param.put("account", teacher.getId());
				param.put("start_time", new SimpleDateFormat("yyyy-MM-dd")
					.format(curLesson.getLessonDate()) + " " + curLesson.getLessonTimeStart()
					+ ":00");
				param.put("end_time",  new SimpleDateFormat("yyyy-MM-dd")
					.format(curLesson.getLessonDate()) + " " + curLesson.getLessonTimeEnd()
					+ ":00");
				param.put("nickname", teacher.getName());
				param.put("accountIntro", teacher.getResume());

				if(curLesson.getLiveroomIdGh() != null
						&& curLesson.getLiveroomIdGh().length() > 0){
					param.put("course_id", curLesson.getLiveroomIdGh());
					res = TalkfunUtils.getRsult(param, "course.update",openId,openToken);
				}else{
					Map<Object, Object> options = new HashMap<Object,Object>();
					options.put("departmentId", companyId);
					param.put("options", options);
					res = TalkfunUtils.getRsult(param, "course.add",openId,openToken);
				}
				JSONObject rjson = JSONObject.parseObject(res);
				if(rjson.getInteger("code").equals(0)){
					classModuleLessonServiceImpl.update(cml);
					json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
					return json;
				}else{
					if(cml.getLiveroomIdGh() == null){
						classModuleLessonServiceImpl
							.deleteClassModuleLessonById(cml.getId());
					}
					json.put(JsonMsg.MSG, rjson.getString("msg"));
					return json;
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				log.info("创建欢拓直播失败");
				json.put(JsonMsg.MSG, "创建直播课程失败");
				return json;
			}
		}else{
			classModuleLessonServiceImpl.update(curLesson);
			log.info(curLesson);
			json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
			return json;
		}
	}
	/**
	 *
	 * Class Name: ClassModuleController.java
	 * @Description: 判断当前模块是否正在在在售班型中使用
	 * @author 权飞虎
	 * @date 2015年5月18日 下午4:03:31c
	 * @modifier
	 * @modify-date 2015年5月18日 下午4:03:31
	 * @version 1.0
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="isUse",method=RequestMethod.POST)
	public String isUse(Integer id){
		Integer count = classModuleServiceImpl.isUse(id,WebUtils.getCurrentCompanyId());
		if(count>0){
			return "yes";
		}else{
			return "no";
		}

	}

	@ResponseBody
	@RequestMapping("/getParam")
	public JSONObject getParam(HttpServletRequest request,Integer lessonId,String types,Integer classType){
		JSONObject json = new JSONObject();
		//获得权限
		Users user = WebUtils.getCurrentUser(request);
		//当前用户 是否是 老师
		SysConfigTeacher teacher = sysConfigTeacherServiceImpl.findByUserId(user.getId());

		log.info("查询当前用户是否配置了单独账号");
		CompanyLiveConfig clc = companyLiveConfigServiceImpl.findByCompanyId(user.getCompanyId());
		String openId = null;
		String openToken = null;
		if(clc != null && clc.getLiveType().equals(3)){
			openId = clc.getLoginName();
			openToken = clc.getPassword();
		}
		String rol = "";
		String rols = "";
		String role = "";

		if(authRoleServiceImpl.hasRoleFlag(user.getId())){
			rol = "admin";
		}
		if(teacher != null && "PERSON_TEACHER".equals(teacher.getTeacherType())){
			rols = "adminAndTeacher";
		}
		if(teacher != null && ("PERSON_ASSISTANT".equals(teacher.getTeacherType())
				|| "PERSON_MANAGER".equals(teacher.getTeacherType()))){
			role = "ass";
		}

		try {
			if(classType == 1){
				//根据lessonId 查询 课次
				ClassModuleLesson cml = classModuleLessonServiceImpl.findClassModuleLessonById(lessonId);
				String cmltypes = cml.getLiveCompanyType();
				log.info("lesson：查询课次" +cml);
				log.info("lesson：权限:" +rol+","+rols+","+role);
				//查询老师下用户id
				SysConfigTeacher teachers = null;
				if(cml != null ){
					if(!rols.equals("") && cml.getTeachers() != null && !cml.getTeachers().equals("")){
						teachers = sysConfigTeacherServiceImpl.findSysConfigTeacherById(Integer.parseInt(cml.getTeachers()));
					}else if(!role.equals("") && cml.getAssistants() != null && !cml.getAssistants().equals("")){
						teachers = sysConfigTeacherServiceImpl.findSysConfigTeacherById(Integer.parseInt(cml.getAssistants()));
					}
				}
				log.info("lesson：老师：" +teachers);
				String path = null;

				//查询权限根据用户id
				if(teachers == null || !user.getId().equals(teachers.getUserId()) || (user.getId().equals(teachers.getUserId()) && rol.equals("admin") && rols.equals("") && role.equals(""))){
					if(types.equals("upclass")){
						if(cmltypes != null
								&& cmltypes.equals("ht")){
							path = userinto(cml.getLiveroomIdGh(), "course.access"
									, user, "liveUrl",openId,openToken);
						}else{
							path = cml.getStudentUrlGh();
						}
						log.info("lesson：路径：" +path);
						json.put("isres", "no");
					}else{
						//查询结束时间
						//计算时间是否超过三十分钟
						long time = (new Date().getTime() - new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse((((cml.getLessonDate().getYear() + 1900) + "-" + (cml.getLessonDate().getMonth() + 1) + "-" + (cml.getLessonDate().getDate()) ) + " " + cml.getLessonTimeEnd() + ":00")).getTime());
						if((time / 60000) < 30){
							json.put(JsonMsg.URL, "timeno");
							return json;
						}
						json.put("isres", "yes");
						if(cmltypes != null
								&& cmltypes.equals("ht")){
							path = userinto(cml.getLiveroomIdGh(), "course.access.playback"
									, user, "playbackLiveUrl",openId,openToken);
						}else{
							path = cml.getReplayUrlGh();
						}
						log.info("lesson：路径：" +path);
					}
					if(cmltypes == null
							|| cmltypes.equals("gh")
							|| cmltypes.equals("")){
						//直播参数
						String customer = "";
						String secretKey = "";
						String liveAddress = "";

						Map<String, Object> param = EktSetParam.setLiveParam(clc, cml,properties);
						customer = (String) param.get("customer");
						secretKey = (String) param.get("secretKey");
						liveAddress = (String) param.get("liveAddress");

						Long timestamp = System.currentTimeMillis();
						String k = MD5.getMD5ofStr(customer + "" + timestamp + "" + secretKey + "" + user.getId() + "" + cml.getLiveroomIdGh());
						String p = "&p=" +k + "|" + timestamp;
						String url = liveAddress + path;

						json.put(JsonMsg.URL, (url + p));
						json.put("types", "gh");
					}else if(cmltypes.equals("zs")){
						//展视互动
						json.put("liveClassType", cml.getLiveClassType());
						json.put(JsonMsg.URL, path);
						json.put("types", "zs");
					}else if(cmltypes.equals("ht")){
						json.put(JsonMsg.URL, path);
						json.put("types", "ht");
					}else{
						json.put(JsonMsg.URL, path);
						json.put("types", "cc");
					}
					json.put("auth", "u");
					return json;
				}
				log.info("lesson：根据userid查询老师：" +teacher);
				if(types.equals("upclass")){
					if(Integer.parseInt(cml.getTeachers()) == teacher.getId()){
						if(cmltypes != null && cmltypes.equals("ht")){
							path = teacherinto(cml.getLiveroomIdGh(),
									"course.launch",openId,openToken);
							json.put("curr", "ishtlive");
						}else{
							path = cml.getTeacherUrlGh();
						}
					}else{
						if(cmltypes != null && cmltypes.equals("ht")){
							path = assinto(cml.getLiveroomIdGh(),
									"course.access", teachers,openId,openToken);
						}else{
							path = cml.getAssistantUrlGh();
						}
					}
					json.put("isr", 0);
					json.put("isres", "no");
				}else{
					json.put("isres", "yes");
					if(cmltypes != null
							&& cmltypes.equals("ht")){
						path = userinto(cml.getLiveroomIdGh(), "course.access.playback"
								, user, "playbackLiveUrl",openId,openToken);
					}else{
						path = cml.getReplayUrlGh();
					}
					json.put("isr", 1);
				}
				if(cmltypes == null
						|| cmltypes.equals("gh")
						|| cmltypes.equals("")){
					//是否是支持手机
					if(cml.getSupportMobile().equals(1) && cml.getCreateTime() != null){
						//检查是否过了 创建 15分钟
						long time = (new Date().getTime() - cml.getCreateTime().getTime());
						if((time / 60000) <= 15){
							json.put(JsonMsg.URL, "timeno30");
							return json;
						}
					}
					//直播参数
					String customer = "";
					String secretKey = "";
					String liveAddress = "";
					Map<String, Object> param = EktSetParam.setLiveParam(clc, cml,properties);
					customer = (String) param.get("customer");
					secretKey = (String) param.get("secretKey");
					liveAddress = (String) param.get("liveAddress");
					Long timestamp = System.currentTimeMillis();
					String k = MD5.getMD5ofStr(customer + "" + timestamp + "" + secretKey + "" + teacher.getId() + "" + cml.getLiveroomIdGh());
					String p = "&p=" +k + "|" + timestamp;
					String url = liveAddress + path;
					json.put(JsonMsg.URL, (url + p));
					json.put("types", "gh");
				}else if(cmltypes.equals("zs")){
					//展视互动
					json.put("liveClassType", cml.getLiveClassType());
					json.put(JsonMsg.URL, path);
					json.put("types", "zs");
				}else if(cmltypes.equals("ht")){
					json.put(JsonMsg.URL, path);
					json.put("types", "ht");
				}else{
					json.put(JsonMsg.URL, path);
					json.put("types", "cc");
				}
				json.put("auth", "t");
				return json;
			}else{
				//根据lessonId 查询 公开课
				LiveOpenCourse cml = liveOpenCourseServiceImpl.findLiveOpenCourseById(lessonId);
				String cmltypes = cml.getLiveServiceProvider();
				//获得权限
				log.info("lesson：查询公开课：" +cml);
				log.info("lesson：权限：" +rol+","+rols+","+role);
				//查询老师下用户id
				SysConfigTeacher teachers = null;
				if(cml != null ){
					if(!rols.equals("")){
						teachers = sysConfigTeacherServiceImpl.findSysConfigTeacherById(cml.getTeacherId());
					}else if(!role.equals("")){
						teachers = sysConfigTeacherServiceImpl.findSysConfigTeacherById(cml.getAssistantId());
					}
				}
				log.info("lesson：老师：" +teachers);
				String path = null;

				//查询权限根据用户id
				if(teachers == null || !user.getId().equals(teachers.getUserId()) || (user.getId().equals(teachers.getUserId()) && rol.equals("admin") && rols.equals("") && role.equals(""))){
					if(types.equals("upclass")){
						if(cmltypes != null && cmltypes.equals("ht")){
							path = userinto(cml.getLiveroomIdGh(),
									"course.access", user, "liveUrl",openId,openToken);
						}else{
							path = cml.getStudentUrlGh();
						}
						log.info("lesson：路径：" +path);
						json.put("isres", "no");
					}else{
						//查询结束时间
						//计算时间是否超过三十分钟
						long time = (new Date().getTime() - new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse((((cml.getStartOpenData().getYear() + 1900) + "-" + (cml.getStartOpenData().getMonth() + 1) + "-" + (cml.getStartOpenData().getDate()) ) + " " + cml.getEndTime() + ":00")).getTime());
						if((time / 60000) < 30){
							json.put(JsonMsg.URL, "timeno");
							return json;
						}
						json.put("isres", "yes");
						if(cmltypes != null && cmltypes.equals("ht")){
							path = userinto(cml.getLiveroomIdGh(),
									"course.access.playback", user, "playbackLiveUrl"
									,openId,openToken);
						}else{
							path = cml.getReplayUrlGh();
						}
						log.info("lesson：路径：" +path);
					}
					if(cmltypes == null
							|| cmltypes.equals("gh")
							|| cmltypes.equals("")){
						//直播参数
						String customer = "";
						String secretKey = "";
						String liveAddress = "";
						Map<String, Object> param = EktSetParam.setOpenParam(clc, cml,properties);
						customer = (String) param.get("customer");
						secretKey = (String) param.get("secretKey");
						liveAddress = (String) param.get("liveAddress");
						Long timestamp = System.currentTimeMillis();
						String k = MD5.getMD5ofStr(customer + "" + timestamp + "" + secretKey + "" + user.getId() + "" + cml.getLiveroomIdGh());
						String p = "&p=" +k + "|" + timestamp;
						String url = liveAddress + path;

						json.put(JsonMsg.URL, (url + p));
						json.put("types", "gh");
					}else if(cmltypes.equals("zs")){
						//展视互动
						json.put("liveClassType", "LIVE_BIG_CLASS_ROOM");
						json.put(JsonMsg.URL, path);
						json.put("types", "zs");
					}else if(cmltypes.equals("ht")){
						json.put(JsonMsg.URL, path);
						json.put("types", "ht");
					}else{
						json.put(JsonMsg.URL, path);
						json.put("types", "cc");
					}
					json.put("auth", "u");
					return json;
				}

				log.info("lesson：根据userid查询老师：" +teacher);
				if(types.equals("upclass")){
					if(cml.getTeacherId().equals(teacher.getId())){
						if(cmltypes != null && cmltypes.equals("ht")){
							path = teacherinto(cml.getLiveroomIdGh(),
									"course.launch",openId,openToken);
							json.put("curr", "ishtlive");
						}else{
							path = cml.getTeacherUrlGh();
						}
					}else{
						if(cmltypes != null && cmltypes.equals("ht")){
							path = assinto(cml.getLiveroomIdGh(),
									"course.access", teachers,openId,openToken);
						}else{
							path = cml.getAssistantUrlGh();
						}
					}
					json.put("isr", 0);
					json.put("isres", "no");
				}else{
					json.put("isres", "yes");
					if(cmltypes != null
							&& cmltypes.equals("ht")){
						path = userinto(cml.getLiveroomIdGh(), "course.access.playback"
								, user, "playbackLiveUrl",openId,openToken);
					}else{
						path = cml.getReplayUrlGh();
					}
					json.put("isr", 1);
				}
				if(cmltypes == null
						|| cmltypes.equals("gh")
						|| cmltypes.equals("")){
					//是否是支持手机
					if(cml.getSupportMobile().equals(1) && cml.getCreateTime() != null){
						//检查是否过了 创建 15分钟
						long time = (new Date().getTime() - cml.getCreateTime().getTime());
						if((time / 60000) <= 30){
							json.put(JsonMsg.URL, "timeno30");
							return json;
						}
					}
					//直播参数

					String customer = "";
					String secretKey = "";
					String liveAddress = "";
					Map<String, Object> param = EktSetParam.setOpenParam(clc, cml,properties);
					customer = (String) param.get("customer");
					secretKey = (String) param.get("secretKey");
					liveAddress = (String) param.get("liveAddress");
					Long timestamp = System.currentTimeMillis();
					String k = MD5.getMD5ofStr(customer + "" + timestamp + "" + secretKey + "" + teacher.getId() + "" + cml.getLiveroomIdGh());
					String p = "&p=" +k + "|" + timestamp;
					String url = liveAddress + path;

					json.put(JsonMsg.URL, (url + p));
					json.put("types", "gh");
				}else if(cmltypes.equals("zs")){
					//展视互动
					json.put("liveClassType", "LIVE_BIG_CLASS_ROOM");
					json.put(JsonMsg.URL, path);
					json.put("types", "zs");
				}else if(cmltypes.equals("ht")){
					json.put(JsonMsg.URL, path);
					json.put("types", "ht");
				}else{
					json.put(JsonMsg.URL, path);
					json.put("types", "cc");
				}
				json.put("auth", "t");
				return json;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("lesson：出错了：" +e.getMessage());
			json.put(JsonMsg.URL, "error");
			json.put(JsonMsg.MSG, JsonMsg.INFORMATION);
			return json;
		}
	}


	@ResponseBody
	@RequestMapping(value="manageDynamicsReplayJsonLast5")
	public Object manageDynamicsReplayJson2 (){
		Users users = WebUtils.getCurrentUser();
		Integer companyId = WebUtils.getCurrentCompanyId();
		Integer schoolId = WebUtils.getCurrentSchoolId();

		SysTeacherPersonalStatus vo = new SysTeacherPersonalStatus();
		vo.setCompanyId(companyId);
		vo.setPage(1);
		vo.setPageSize(5);
		Subject subject = SecurityUtils.getSubject();
		if(!subject.hasRole("机构管理员")){
			vo.setSchoolId(schoolId);
		}
		if(!subject.hasRole("机构管理员") && (subject.hasRole("直播老师")||subject.hasRole("排课老师"))){
			SysConfigTeacher teacher=sysConfigTeacherServiceImpl.findByUserId(users.getId());
			vo.setTeacherId(teacher.getId());
		}
		List<TeacherDynamicsReplayAndStatusVo> findTeacherStatusReplay = sysTeacherPersonalStatusReplayImpl.findTeacherStatusReplay(vo);

		return findTeacherStatusReplay;
	}

	/**
	 *
	 * Class Name: ClassModuleController.java
	 * @Description: 用户进入
	 * @author 周文斌
	 * @date 2016-10-19 下午4:33:24
	 * @modify	2016-10-19 下午4:33:24
	 * @version
	 * @param courseId
	 * @param cmd
	 * @param user
	 * @param types
	 * @return
	 * @throws Exception
	 */
	private String userinto(String courseId,String cmd,Users user,String types
			,String openId,String openToken) throws Exception{
		Map<Object, Object> param = new HashMap<Object,Object>();
		param.put("course_id", courseId);
		/*Map<Object, Object> options = new HashMap<Object,Object>();
		options.put("avatar", headimg);*/

		param.put("uid", user.getId());
		param.put("nickname", (user.getRealName() != null ? user.getRealName()
				: user.getUsername()));
		param.put("role", "user");
		param.put("expire", 120);
		/*param.put("options", options);*/
		String res = TalkfunUtils
				.getRsult(param, cmd,openId,openToken);
		String datastr = JSONObject.parseObject(res).getString("data");
		if(types.equals("liveUrl")){
			return JSONObject.parseObject(datastr).getString("liveUrl");
		}else{
			return JSONObject.parseObject(datastr).getString("playbackUrl");
		}
	}

	/**
	 *
	 * Class Name: ClassModuleController.java
	 * @Description: 助教进入
	 * @author 周文斌
	 * @date 2016-10-19 下午4:33:24
	 * @modify	2016-10-19 下午4:33:24
	 * @version
	 * @param courseId
	 * @param cmd
	 * @param user
	 * @param types
	 * @return
	 * @throws Exception
	 */
	private String assinto(String courseId,String cmd,SysConfigTeacher teacher
			,String openId,String openToken) throws Exception{
		Map<Object, Object> param = new HashMap<Object,Object>();
		param.put("course_id", courseId);
		/*Map<Object, Object> options = new HashMap<Object,Object>();
		options.put("avatar", headimg);*/

		param.put("uid", teacher.getId());
		param.put("nickname", teacher.getName());
		param.put("role", "admin");
		param.put("expire", 120);
		/*param.put("options", options);*/
		String res = TalkfunUtils
				.getRsult(param, cmd,openId,openToken);
		String datastr = JSONObject.parseObject(res).getString("data");
		return JSONObject.parseObject(datastr).getString("liveUrl");
	}

	/**
	 *
	 * Class Name: ClassModuleController.java
	 * @Description: 教师进入
	 * @author 周文斌
	 * @date 2016-10-19 下午4:33:24
	 * @modify	2016-10-19 下午4:33:24
	 * @version
	 * @param courseId
	 * @param cmd
	 * @param user
	 * @param types
	 * @return
	 * @throws Exception
	 */
	private String teacherinto(String courseId,String cmd,String openId,String openToken) throws Exception{
		Map<Object, Object> param = new HashMap<Object,Object>();
		param.put("course_id", courseId);
		String res = TalkfunUtils
				.getRsult(param, cmd,openId,openToken);
		String datastr = JSONObject.parseObject(res).getString("data");
		String path = JSONObject.parseObject(datastr).getString("url");
		String protocol = JSONObject.parseObject(datastr).getString("protocol");
		String download = JSONObject.parseObject(datastr).getString("download");
		path.replaceAll("\\u0026", "&");
		return protocol + "," + download;
	}

	/**
	 *
	 * Class Name: ClassModuleController.java
	 * @Description: 查询二级项目
	 * @author 周文斌
	 * @date 2015-6-5 上午11:35:33
	 * @version 1.0
	 * @param request
	 * @param messageType
	 * @param oneItem
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/selTwoItem")
	public JSONObject selItem(HttpServletRequest request,Integer oneItem){
		//根据一级项目id 查询 二级 项目
		List<SysConfigItem> item = sysConfigItemServiceImpl.findSysConfigItemByPid("2", oneItem, WebUtils.getCurrentCompanyId(), WebUtils.getCurrentUserSchoolId(request));
		JSONObject json = new JSONObject();
		json.put("two", item);
		return json;
	}

	/**
	 *
	 * Class Name: ClassModuleController.java
	 * @Description: 查询班型
	 * @author 周文斌
	 * @date 2015-6-5 下午12:06:38
	 * @version 1.0
	 * @param request
	 * @param classType
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/selClassType")
	public JSONObject selClassType(HttpServletRequest request,ClassType classType){
		//查询 班型
		List<ClassType> types = classTypeServiceImpl.findClassByItem(WebUtils.getCurrentCompanyId(), WebUtils.getCurrentUserSchoolId(request), classType.getItemOneId(), classType.getItemSecondId());
		JSONObject json = new JSONObject();
		json.put("types", types);
		return json;
	}

	@ResponseBody
	@RequestMapping("/selModuleNo")
	public JSONObject selModuleNo(HttpServletRequest request,ClassModuleNo classModuleNo){
		classModuleNo.setSchoolId(WebUtils.getCurrentUserSchoolId(request));
		List<ClassModuleNo> list = classModuleNoServiceImpl.findByItem(classModuleNo);

		JSONObject json = new JSONObject();
		json.put("types", list);
		return json;
	}

	/**
	 *
	 * Class Name: ClassModuleController.java
	 * @Description: 查询人数
	 * @author 周文斌
	 * @date 2015-6-5 下午12:56:21
	 * @version 1.0
	 * @param request
	 * @param messageType
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/selPerson")
	public JSONObject selPerson(HttpServletRequest request,String messageType,Integer id,Integer itemOneId,Integer itemSecondId){
		Integer companyId = WebUtils.getCurrentCompanyId();
		Integer schoolId = WebUtils.getCurrentUserSchoolId(request);
		Integer count = 0;
		if(messageType.equals("STUDENT_MESSAGE_CLASSTYPE")){
			StudentPayMaster spm = new StudentPayMaster();
			spm.setCompanyId(companyId);
			spm.setSchoolId(schoolId);
			spm.setItemOneId(itemOneId);
			spm.setItemSecondId(itemSecondId);
			spm.setCommodityId(id);
			spm.setCommodityType("COMMODITY_CLASS");
			count = studentPayMasterServiceImpl.findCountByPayMaster(spm);

		}else if(messageType.equals("STUDENT_MESSAGE_MODULENO")){
			Map<String,Object> param = new HashMap<String, Object>();
			param.put("companyId", companyId);
			param.put("schoolId", schoolId);
			param.put("itemOneId", itemOneId);
			param.put("itemSecondId", itemSecondId);
			param.put("resourceId", id);
			count = studentPayMasterServiceImpl.findByResourceid(param);
		}

		JSONObject json = new JSONObject();
		json.put("count", count);
		return json;
	}

	/**
	 *
	 * Class Name: ClassModuleController.java
	 * @Description: 查询 学生总数
	 * @author 周文斌
	 * @date 2015-6-5 下午5:39:11
	 * @version 1.0
	 * @param request
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/selStuContent")
	public JSONObject selStuContent(HttpServletRequest request,Integer id){

		Map<String,Object> param = new HashMap<String, Object>();
		param.put("companyId", WebUtils.getCurrentCompanyId());
		param.put("schoolId", WebUtils.getCurrentUserSchoolId(request));
		param.put("resourceId", id);
		Integer count = studentPayMasterServiceImpl.findByResourceid(param);
		JSONObject json = new JSONObject();
		json.put("count", count);
		return json;
	}

	/**
	 *
	 * Class Name: ClassModuleController.java
	 * @Description: 进入直播教室
	 * @author 周文斌
	 * @date 2015-6-6 下午8:51:22
	 * @version 1.0
	 * @param model
	 * @param url
	 * @return
	 */
	@RequestMapping("/liveroom")
	public String liveroom(Model model,HttpServletRequest request,String types
			,String auth,String isres,String custom,Integer lessonId){

		//获得权限
		Users user = WebUtils.getCurrentUser(request);

		if(user.getId() == null){
			return "redirect:/login";
		}
		Integer companyId = user.getCompanyId();
		String target = "";
		if("ishtlive".equals(custom)){
			target = "operate/live/custom";
		}else{
			target = "operate/live/liveroom";
		}

		log.info("查询当前用户是否配置了单独账号");
		CompanyLiveConfig clc = companyLiveConfigServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());

		String urlPath = request.getParameter("url");
		String lct = request.getParameter("liveClassType");
		String p = "";

		if(!types.equals("ht")){
			if(auth.equals("t")){
				//获得教师信息
				SysConfigTeacher teacher = sysConfigTeacherServiceImpl.findByUserId(user.getId());

				if(teacher != null){
					String nickname = URLEncoder.encode(teacher.getName());
					if(types.equals("gh")){
						p = "|" + teacher.getId() + "|" + nickname + "|1";
					}else if(types.equals("zs")){
						Integer uid = 1000000000 + teacher.getId();
						long timestamp = System.currentTimeMillis();
						String isr = request.getParameter("isr");
						String token = "";
						if(!teacher.getTeacherType().equals("PERSON_TEACHER")){
							if(isr.equals("0")){
								if(clc != null && clc.getLiveType().equals(1)){
									token = clc.getAssistantToken();
								}else{
									token = LiveRoomConstant.ASSISTANT_TOKEN;
								}
							}else{
								if(isres.equals("yes")){
									if(clc != null && clc.getLiveType().equals(1)){
										token = clc.getStudentWebToken();
									}else{
										token = LiveRoomConstant.STUDENT_TOKEN_WEB;
									}
								}else{
									if(lct == null || lct.equals("LIVE_BIG_CLASS_ROOM")){
										if(clc != null && clc.getLiveType().equals(1)){
											token = clc.getStudentWebToken();
										}else{
											token = LiveRoomConstant.STUDENT_TOKEN_WEB;
										}
									}else{
										if(clc != null && clc.getLiveType().equals(1)){
											token = clc.getStudentClientToken();
										}else{
											token = LiveRoomConstant.STUDENT_TOKEN_CLINT;
										}
									}
								}
							}

							String md5 = ParameterUtil.GenseeMd5(uid, lessonId
									,companyId , timestamp, token);
							String k = uid+":"+lessonId+":"+companyId+":"+timestamp
									+":"+token+":"+md5;
							p = "?nickname=" + nickname + "&token=" + token + "&sec=plain&uid=" + uid + "&k="+k;
						}else{
							if(isr.equals("0")){
								if(clc != null && clc.getLiveType().equals(1)){
									token = clc.getTeacherToken();
								}else{
									token = LiveRoomConstant.TEACHER_TOKEN;
								}
							}else{
								if(isres.equals("yes")){
									if(clc != null && clc.getLiveType().equals(1)){
										token = clc.getStudentWebToken();
									}else{
										token = LiveRoomConstant.STUDENT_TOKEN_WEB;
									}
								}else{
									if(lct == null || lct.equals("LIVE_BIG_CLASS_ROOM")){
										if(clc != null && clc.getLiveType().equals(1)){
											token = clc.getStudentWebToken();
										}else{
											token = LiveRoomConstant.STUDENT_TOKEN_WEB;
										}
									}else{
										if(clc != null && clc.getLiveType().equals(1)){
											token = clc.getStudentClientToken();
										}else{
											token = LiveRoomConstant.STUDENT_TOKEN_CLINT;
										}
									}
								}
							}

							String md5 = ParameterUtil.GenseeMd5(uid, lessonId
									,companyId , timestamp, token);
							String k = uid+":"+lessonId+":"+companyId+":"+timestamp
									+":"+token+":"+md5;
							p = "?nickname=" + nickname + "&token=" + token + "&sec=plain&uid=" + uid + "&k="+k;
						}
					}else if(types.equals("cc")){
						if(!teacher.getTeacherType().equals("PERSON_TEACHER")){
							if(clc != null && clc.getLiveType().equals(4)){
								p = "&autoLogin=true&viewername=" + teacher.getId()+":" + nickname
									+ "&viewertoken=" + clc.getAssistantToken();
							}else{
								p = "&autoLogin=true&viewername=" + teacher.getId()+":" + nickname
									+ "&viewertoken=" + CCLiveInterface.ASSISTANT_PASS;
							}
						}else{
							if(clc != null && clc.getLiveType().equals(4)){
								p = "&publishname=" + nickname
									+ "&publishpassword=" + clc.getTeacherToken();
							}else{
								p = "&publishname=" + nickname
									+ "&publishpassword=" + CCLiveInterface.TEACHER_PASS;
							}
						}
					}
				}else{
					String nickname = URLEncoder.encode(user.getRealName() != null ? user.getRealName() : user.getUsername());
					if(types.equals("gh")){
						if(user != null && user.getRealName() != null){
							p = "|" + user.getId() + "|" + nickname + "|1";
						}else{
							p = "|" + user.getId() + "|" + nickname + "|1";
						}
					}else if(types.equals("zs")){
						String token = "";
						if(isres.equals("yes")){
							if(clc != null && clc.getLiveType().equals(1)){
								token = clc.getStudentWebToken();
							}else{
								token = LiveRoomConstant.STUDENT_TOKEN_WEB;
							}
						}else{
							if(lct == null || lct.equals("LIVE_BIG_CLASS_ROOM")){
								if(clc != null && clc.getLiveType().equals(1)){
									token = clc.getStudentWebToken();
								}else{
									token = LiveRoomConstant.STUDENT_TOKEN_WEB;
								}
							}else{
								if(clc != null && clc.getLiveType().equals(1)){
									token = clc.getStudentClientToken();
								}else{
									token = LiveRoomConstant.STUDENT_TOKEN_CLINT;
								}
							}
						}
						Integer uid = 1000000000 + user.getId();
						long timestamp = System.currentTimeMillis();
						String md5 = ParameterUtil.GenseeMd5(uid, lessonId
								,companyId , timestamp, token);
						String k = uid+":"+lessonId+":"+companyId+":"+timestamp
								+":"+token+":"+md5;
						p = "?nickname=" + nickname + "&token=" + token + "&sec=plain&uid=" + uid + "&k=" +k;
					}else if(types.equals("cc")){
						if(clc != null && clc.getLiveType().equals(4)){
								p = "&autoLogin=true&viewername=" + user.getId()+":" + nickname
									+ "&viewertoken=" + clc.getStudentWebToken();
						}else{
								p = "&autoLogin=true&viewername=" + user.getId()+":" + nickname
									+ "&viewertoken=" + CCLiveInterface.STUDENT_PASS;
						}
					}
				}
			}else{
				String nickname = URLEncoder.encode(user.getRealName() != null ? user.getRealName() : user.getUsername());
				if(types.equals("gh")){
					if(user != null && user.getRealName() != null){
						p = "|" + user.getId() + "|" + nickname + "|1";
					}else{
						p = "|" + user.getId() + "|" + nickname + "|1";
					}
				}else if(types.equals("zs")){
					String token = "";
					if(isres.equals("yes")){
						if(clc != null && clc.getLiveType().equals(1)){
							token = clc.getStudentWebToken();
						}else{
							token = LiveRoomConstant.STUDENT_TOKEN_WEB;
						}
					}else{
						if(lct == null || lct.equals("LIVE_BIG_CLASS_ROOM")){
							if(clc != null && clc.getLiveType().equals(1)){
								token = clc.getStudentWebToken();
							}else{
								token = LiveRoomConstant.STUDENT_TOKEN_WEB;
							}
						}else{
							if(clc != null && clc.getLiveType().equals(1)){
								token = clc.getStudentClientToken();
							}else{
								token = LiveRoomConstant.STUDENT_TOKEN_CLINT;
							}
						}
					}
					Integer uid = 1000000000 + user.getId();
					long timestamp = System.currentTimeMillis();
					String md5 = ParameterUtil.GenseeMd5(uid, lessonId
							,companyId , timestamp, token);
					String k = uid+":"+lessonId+":"+companyId+":"+timestamp
							+":"+token+":"+md5;
					p = "?nickname=" + nickname + "&token=" + token + "&sec=plain&uid=" + uid + "&k=" +k;
				}else if(types.equals("cc")){
					if(clc != null && clc.getLiveType().equals(4)){
						p = "&autoLogin=true&viewername=" + user.getId()+":" + nickname
							+ "&viewertoken=" + clc.getStudentWebToken();
					}else{
						p = "&autoLogin=true&viewername=" + user.getId()+":" + nickname
							+ "&viewertoken=" + CCLiveInterface.STUDENT_PASS;
					}
				}
			}
		}
		model.addAttribute("url", urlPath);
		model.addAttribute("p", p);
		return target;
	}
	public static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
	/**
	 *
	 * Class Name: ClassModuleController.java
	 * @Description: 发送消息
	 * @author 周文斌
	 * @date 2015-6-6 下午9:08:28
	 * @version 1.0
	 * @param request
	 * @param companyStudentMessage
	 * @param phone
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/sendMsg")
	public JSONObject sendMsg(HttpServletRequest request,CompanyStudentMessage companyStudentMessage,String phone,String email){
		String result = "";
		String status = "";
		String message = "";
		String content = companyStudentMessage.getContent();
		if(companyStudentMessage.getMessageMethod().equals("STUDENT_MESSAGE_WEB")|| companyStudentMessage.getMessageMethod().equals("STUDENT_MESSAGE_EMAIL"))
			content = replaceBlank(content);
		companyStudentMessage.setContent(content);

		Integer companyId = WebUtils.getCurrentCompanyId();
		Integer schoolId = WebUtils.getCurrentUserSchoolId(request);
		companyStudentMessage.setCompanyId(companyId);
		companyStudentMessage.setSchoolId(schoolId);
		Users user = WebUtils.getCurrentUser();
		companyStudentMessage.setCreator(user.getId());
		companyStudentMessage.setCreatorName(user.getRealName() != null ? user.getRealName() : user.getUsername());
		companyStudentMessage.setCreateTime(new Date());
		companyStudentMessage.setMessageStatus("STUDENT_MESSAGE_COMMIT");

		Integer messageCost = companyStudentMessage.getContent().length() % 70 == 0 ? companyStudentMessage.getContent().length() / 70 : (companyStudentMessage.getContent().length() / 70) +1;
		ClassType ct = new ClassType();
		if(companyStudentMessage.getMessageType().equals("STUDENT_MESSAGE_CLASSTYPE")){
			ct = classTypeServiceImpl.findClassTypeById(companyStudentMessage.getClassTypeId());
			companyStudentMessage.setClassTypeName(ct.getName());
		}
		ClassModuleNo cmn = new ClassModuleNo();
		if(companyStudentMessage.getMessageType().equals("STUDENT_MESSAGE_MODULENO")){
			cmn = classModuleNoServiceImpl.findClassModuleNoById(companyStudentMessage.getModuleNoId());
			companyStudentMessage.setModuleNoName(cmn.getName());
			companyStudentMessage.setClassTypeId(null);
		}

		JSONObject json = new JSONObject();
		//学生信息
		List<Student> stuList = new ArrayList<Student>();
		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);
		if(companyStudentMessage.getMessageMethod().equals("STUDENT_MESSAGE_MOBILE")){
			Integer emailsum = ((cms.getMessageTotal() != null ? cms.getMessageTotal() : 0)
					+ (cms.getGiveMessage() != null ? cms.getGiveMessage() : 0));
			Integer emailuse = css.getMessageSend();

			if(((cms.getMessageTotal() + cms.getGiveMessage()) - css.getMessageSend()) <= 0){
				json.put(JsonMsg.RESULT, "msgNotCount");
				return json;
			}
			if(companyStudentMessage.getMessageType().equals("STUDENT_MESSAGE_CLASSTYPE")){
				stuList = studentServiceImpl.findByPayMaster(companyStudentMessage);
				if(stuList.size() == 0){
					json.put(JsonMsg.RESULT, "stuno");
					return json;
				}

				if((emailsum - emailuse) < (stuList.size() * (messageCost))){
					json.put(JsonMsg.RESULT, "msgNotCount");
					return json;
				}
				companyStudentMessageServiceImpl.insert(companyStudentMessage);
				//2016/7/7 无手机号则不发送短信
				Integer sendcounts=0;
				sendcounts = sendPhoneMessage(request, companyStudentMessage, content, companyId, schoolId, user,
						messageCost, stuList, sendcounts);
			//	companyStudentMessage.setMessageCost(stuList.size() * (messageCost));
				companyStudentMessage.setMessageCost(sendcounts * (messageCost));//2016/7/7  发送条数更改
			}else if(companyStudentMessage.getMessageType().equals("STUDENT_MESSAGE_MODULENO")){

				if(((cms.getMessageTotal() + cms.getGiveMessage()) - css.getMessageSend()) <= 0){
					json.put(JsonMsg.RESULT, "msgNotCount");
					return json;
				}
				stuList = studentServiceImpl.findByPaySlave(companyStudentMessage);
				if(stuList.size() == 0){
					json.put(JsonMsg.RESULT, "stuno");
					return json;
				}
				if((emailsum - emailuse) < (stuList.size() * (messageCost))){
					json.put(JsonMsg.RESULT, "msgNotCount");
					return json;
				}
				companyStudentMessageServiceImpl.insert(companyStudentMessage);
				//2016/7/7 无手机号则不发送短信
				Integer sendcounts=0;
				sendcounts = sendPhoneMessage(request, companyStudentMessage, content, companyId, schoolId, user,
						messageCost, stuList, sendcounts);
			//	companyStudentMessage.setMessageCost(stuList.size() * (messageCost));
				companyStudentMessage.setMessageCost(sendcounts * (messageCost));//2016/7/7  发送条数更改
			}else if(companyStudentMessage.getMessageType().equals("STUDENT_MESSAGE_SPECIAL")){

				if(((cms.getMessageTotal() + cms.getGiveMessage()) - css.getMessageSend()) <= 0){
					json.put(JsonMsg.RESULT, "msgNotCount");
					return json;
				}
				companyStudentMessageServiceImpl.insert(companyStudentMessage);
				Map<String , Object> param = new HashMap<String, Object>();
				if(phone.indexOf(",") < 0){
					if((emailsum - emailuse) < ((messageCost))){
						json.put(JsonMsg.RESULT, "msgNotCount");
						return json;
					}
					result = SmsClientSend.sendSmsTwo(request
							,phone.trim(), content + "【在线网校】"
							,user.getId(),"stu-notice");
					status = result.substring(result.indexOf("<returnstatus>"),result.indexOf("</returnstatus>"));
					status = status.substring(status.indexOf(">") + 1);
					message = result.substring(result.indexOf("<message>"),result.indexOf("</message>"));
					message = message.substring(message.indexOf(">") + 1);
					if(message.equals("ok")){
						message = "发送成功";
					}
					//根据公司查
					param.clear();
					param.put("phone", phone);
					param.put("companyId", companyId);
					Student stu = studentServiceImpl.findByPhone(param);
					CompanyMessageHistory cmh = new CompanyMessageHistory();
					if(stu != null){
						cmh.setReceiverUserId(""+stu.getId());
						cmh.setReceiverMobile(phone.trim());
						cmh.setContent(companyStudentMessage.getContent());
						cmh.setSendTime(new Date());
						if(status.equals("Success")){
							cmh.setSendStatus(1);
						}else{
							cmh.setSendStatus(0);
						}
						cmh.setSendResult(message);
						cmh.setBusinessType("BUSINESS_STUDENT_MESSAGE");
						cmh.setCompanyId(companyId);
						cmh.setSchoolId(schoolId);
						cmh.setCostNum(messageCost);
						cmh.setMessageId(companyStudentMessage.getId());
					}else{
						cmh.setReceiverMobile(phone.trim());
						cmh.setContent(companyStudentMessage.getContent());
						cmh.setSendTime(new Date());
						if(status.equals("Success")){
							cmh.setSendStatus(1);
						}else{
							cmh.setSendStatus(0);
						}
						cmh.setSendResult(message);
						cmh.setBusinessType("BUSINESS_STUDENT_MESSAGE");
						cmh.setCompanyId(companyId);
						cmh.setSchoolId(schoolId);
						cmh.setCostNum(messageCost);
						cmh.setMessageId(companyStudentMessage.getId());
					}
					companyMessageHistoryServiceImpl.insert(cmh);
					companyStudentMessage.setMessageCost(messageCost);
				}else{
					String[] phones = phone.split(",");
					int count = 0;
					if((emailsum - emailuse) < (phones.length * (messageCost))){
						json.put(JsonMsg.RESULT, "notsend");
						return json;
					}
					for (String s : phones) {
						result = SmsClientSend.sendSmsTwo(request
								,s.trim(), content + "【在线网校】"
								,user.getId(),"stu-notice");
						status = result.substring(result.indexOf("<returnstatus>"),result.indexOf("</returnstatus>"));
						status = status.substring(status.indexOf(">") + 1);
						message = result.substring(result.indexOf("<message>"),result.indexOf("</message>"));
						message = message.substring(message.indexOf(">") + 1);
						if(message.equals("ok")){
							message = "发送成功";
						}
						//根据公司查
						param.clear();
						param.put("phone", s.trim());
						param.put("companyId", companyId);
						Student stu = studentServiceImpl.findByPhone(param);
						CompanyMessageHistory cmh = new CompanyMessageHistory();
						if(stu != null){
							cmh.setReceiverUserId(""+stu.getId());
							cmh.setReceiverMobile(s.trim());
							cmh.setContent(companyStudentMessage.getContent());
							cmh.setSendTime(new Date());
							if(status.equals("Success")){
								cmh.setSendStatus(1);
							}else{
								cmh.setSendStatus(0);
							}
							cmh.setSendResult(message);
							cmh.setBusinessType("BUSINESS_STUDENT_MESSAGE");
							cmh.setCompanyId(companyId);
							cmh.setSchoolId(schoolId);
							cmh.setCostNum(messageCost);
							cmh.setMessageId(companyStudentMessage.getId());
						}else{
							cmh.setReceiverMobile(s.trim());
							cmh.setContent(companyStudentMessage.getContent());
							cmh.setSendTime(new Date());
							if(status.equals("Success")){
								cmh.setSendStatus(1);
							}else{
								cmh.setSendStatus(0);
							}
							cmh.setSendResult(message);
							cmh.setBusinessType("BUSINESS_STUDENT_MESSAGE");
							cmh.setCompanyId(companyId);
							cmh.setSchoolId(schoolId);
							cmh.setCostNum(messageCost);
							cmh.setMessageId(companyStudentMessage.getId());
						}
						companyMessageHistoryServiceImpl.insert(cmh);
						count ++;
					}
					companyStudentMessage.setMessageCost(count * (messageCost));
				}
			}else if(companyStudentMessage.getMessageType().equals("STUDENT_MESSAGE_GROUP")){
				Map<String,Object> search = new HashMap<String,Object>();
				search.put("companyId", WebUtils.getCurrentCompanyId());
				search.put("groupOneId",companyStudentMessage.getGroupOneId());
				search.put("groupTwoId", companyStudentMessage.getGroupTwoId());
				stuList = studentServiceImpl.findGroupStu(search);
				if(stuList.size() == 0){
					json.put(JsonMsg.RESULT, "stuno");
					return json;
				}
				companyStudentMessageServiceImpl.insert(companyStudentMessage);
				//2016/10/24 无手机号不发送短信
				Integer sendcounts=0;
				sendcounts = sendPhoneMessage(request, companyStudentMessage, content, companyId, schoolId, user,
						messageCost, stuList, sendcounts);
			//	companyStudentMessage.setMessageCost(stuList.size() * (messageCost));
				companyStudentMessage.setMessageCost(sendcounts * (messageCost));//2016/7/7  发送条数更改
			}

			//查询失败人数
			CompanyMessageHistory cmh = new CompanyMessageHistory();
			cmh.setBusinessType("BUSINESS_STUDENT_MESSAGE");
			cmh.setCompanyId(companyId);
			cmh.setSchoolId(schoolId);
			cmh.setMessageId(companyStudentMessage.getId());
			cmh.setSendStatus(0);
			Integer failCount = companyMessageHistoryServiceImpl.findByUserCount(cmh);
			if(failCount == null){
				failCount = 0;
			}
			companyStudentMessage.setFailNum(failCount);
			//查询全部人数
			cmh = new CompanyMessageHistory();
			cmh.setBusinessType("BUSINESS_STUDENT_MESSAGE");
			cmh.setCompanyId(companyId);
			cmh.setSchoolId(schoolId);
			cmh.setMessageId(companyStudentMessage.getId());
			Integer sendCount = companyMessageHistoryServiceImpl.findByUserCount(cmh);
			companyStudentMessage.setSendNum(sendCount);
			companyStudentMessage.setMessageStatus("STUDENT_MESSAGE_FINISH");
			companyStudentMessageServiceImpl.update(companyStudentMessage);
			json.put(JsonMsg.RESULT, JsonMsg.SUCCESS);
		}else if(companyStudentMessage.getMessageMethod().equals("STUDENT_MESSAGE_EMAIL")){///发送邮件
			int count = 0;

			if(companyStudentMessage.getMessageType().equals("STUDENT_MESSAGE_CLASSTYPE")){
				stuList = studentServiceImpl.findByPayMaster(companyStudentMessage);
				if(stuList.size() == 0){
					json.put(JsonMsg.RESULT, "stuno");
					return json;
				}
				companyStudentMessageServiceImpl.insert(companyStudentMessage);
				count = sendNoticeEmial(companyStudentMessage, companyId, schoolId, stuList);
			}else if(companyStudentMessage.getMessageType().equals("STUDENT_MESSAGE_MODULENO")){
				stuList = studentServiceImpl.findByPaySlave(companyStudentMessage);
				if(stuList.size() == 0){
					json.put(JsonMsg.RESULT, "stuno");
					return json;
				}
				companyStudentMessageServiceImpl.insert(companyStudentMessage);
				count = sendNoticeEmial(companyStudentMessage, companyId, schoolId, stuList);
			}else if(companyStudentMessage.getMessageType().equals("STUDENT_MESSAGE_SPECIAL")){
				companyStudentMessageServiceImpl.insert(companyStudentMessage);
				String[] emails = email.split(",");
				//邮件头
				try {
					Company company = companyServiceImpl.findCompanyById(companyId);
					Properties prop = new Properties();
					InputStream in = MailUtil.class.getClassLoader().getResourceAsStream("config.properties");
					prop.load(in);
					String url = prop.getProperty("url");
					String apiUser = prop.getProperty("apiUser");
					String apiKey = prop.getProperty("apiKey");
					//邮件标题头
					Mail mail = new Mail();
					mail.setFrom("contact@yuuxin.com");
					mail.setFromName(company.getCompanyName());
					mail.setSubject(companyStudentMessage.getEmailTitle());
					//邮件模板
					NoticeEmail model = new NoticeEmail();
					model.setMsg(companyStudentMessage.getContent().replace("\"", "\\\""));
					for (String str : emails) {
						Student s = new Student();
						s.setCompanyId(company.getId());
						s.setEmail(str);
						s = studentServiceImpl.findByEmail(s);
						mail.setTo(str);
						MailUtil mu = new MailUtil(mail, model);
						String result11 = null;
						if(s != null){
							result11 = mu.sendmail(url, apiUser, apiKey,MailUtil.NOTICE);
							CompanyEmailHistory cmh = new CompanyEmailHistory();
							cmh.setReceiverEmail(str);
							if(s != null)
							cmh.setReceiverUserId(s.getUserId());
							cmh.setContent(companyStudentMessage.getContent());
							cmh.setSendTime(new Date());
							if(result11.equals("success")){
								cmh.setSendStatus(1);
							}else{
								cmh.setSendStatus(0);
							}
							cmh.setSendResult(result11);
							cmh.setBusinessType("BUSINESS_STUDENT_NOTICE");
							cmh.setCompanyId(companyId);
							cmh.setSchoolId(schoolId);
							cmh.setTitle(companyStudentMessage.getEmailTitle());
							cmh.setMessageId(companyStudentMessage.getId());
							companyEmailServiecimpl.insert(cmh);
							count ++;
						}

					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else if(companyStudentMessage.getMessageType().equals("STUDENT_MESSAGE_GROUP")){
				Map<String,Object> search = new HashMap<String,Object>();
				search.put("companyId", WebUtils.getCurrentCompanyId());
				search.put("groupOneId",companyStudentMessage.getGroupOneId());
				search.put("groupTwoId", companyStudentMessage.getGroupTwoId());
				stuList = studentServiceImpl.findGroupStu(search);
				if(stuList.size() == 0){
					json.put(JsonMsg.RESULT, "stuno");
					return json;
				}
				companyStudentMessageServiceImpl.insert(companyStudentMessage);
				count = sendNoticeEmial(companyStudentMessage, companyId, schoolId, stuList);
			}

			companyStudentMessage.setMessageCost(count);

			//查询失败人数
			CompanyEmailHistory cmh = new CompanyEmailHistory();
			cmh.setBusinessType("BUSINESS_STUDENT_NOTICE");
			cmh.setCompanyId(companyId);
			cmh.setSchoolId(schoolId);
			cmh.setMessageId(companyStudentMessage.getId());
			cmh.setSendStatus(0);
			Integer failCount = companyEmailServiecimpl.findByUserCount(cmh);
			if(failCount == null){
				failCount = 0;
			}
			companyStudentMessage.setFailNum(failCount);
			//查询全部人数
			cmh = new CompanyEmailHistory();
			cmh.setBusinessType("BUSINESS_STUDENT_NOTICE");
			cmh.setCompanyId(companyId);
			cmh.setSchoolId(schoolId);
			cmh.setMessageId(companyStudentMessage.getId());
			Integer sendCount = companyEmailServiecimpl.findByUserCount(cmh);
			companyStudentMessage.setSendNum(sendCount);
			companyStudentMessage.setMessageStatus("STUDENT_MESSAGE_FINISH");
			companyStudentMessageServiceImpl.update(companyStudentMessage);
			json.put(JsonMsg.RESULT, JsonMsg.SUCCESS);
		}else{//站内信
			// 查询 用户id
			if(companyStudentMessage.getMessageType().equals("STUDENT_MESSAGE_CLASSTYPE")){
				stuList = studentServiceImpl.findByPayMaster(companyStudentMessage);
				if(stuList.size() == 0){
					json.put(JsonMsg.RESULT, "stuno");
					return json;
				}
				companyStudentMessageServiceImpl.insert(companyStudentMessage);
				for (Student s : stuList) {
					UserMessage um = new UserMessage();
					um.setUserId(s.getUserId());
					um.setMessageId(companyStudentMessage.getId());
					um.setReadFlag(0);
					companyStudentMessageServiceImpl.insertUserMessage(um);
				}
			}else if(companyStudentMessage.getMessageType().equals("STUDENT_MESSAGE_MODULENO")){
				stuList = studentServiceImpl.findByPaySlave(companyStudentMessage);
				if(stuList.size() == 0){
					json.put(JsonMsg.RESULT, "stuno");
					return json;
				}
				companyStudentMessageServiceImpl.insert(companyStudentMessage);
				for (Student s : stuList) {
					UserMessage um = new UserMessage();
					um.setUserId(s.getUserId());
					um.setMessageId(companyStudentMessage.getId());
					um.setReadFlag(0);
					companyStudentMessageServiceImpl.insertUserMessage(um);
				}
			}else if(companyStudentMessage.getMessageType().equals("STUDENT_MESSAGE_GROUP")){
				Map<String,Object> search = new HashMap<String,Object>();
				search.put("companyId", WebUtils.getCurrentCompanyId());
				search.put("groupOneId",companyStudentMessage.getGroupOneId());
				search.put("groupTwoId", companyStudentMessage.getGroupTwoId());
				stuList = studentServiceImpl.findGroupStu(search);
				if(stuList.size() == 0){
					json.put(JsonMsg.RESULT, "stuno");
					return json;
				}
				companyStudentMessageServiceImpl.insert(companyStudentMessage);
				for (Student s : stuList) {
					UserMessage um = new UserMessage();
					um.setUserId(s.getUserId());
					um.setMessageId(companyStudentMessage.getId());
					um.setReadFlag(0);
					companyStudentMessageServiceImpl.insertUserMessage(um);
				}
			}

			companyStudentMessage.setSendNum(stuList.size());
			companyStudentMessage.setMessageStatus("STUDENT_MESSAGE_FINISH");
			companyStudentMessageServiceImpl.update(companyStudentMessage);
			json.put(JsonMsg.RESULT, JsonMsg.SUCCESS);
		}
		return json;
	}
	/**
	 * 学员通知发送邮件
	 * @author licong
	 * @date 2016年10月25日 下午5:00:19
	 * @param
	 * @param companyStudentMessage
	 * @param companyId
	 * @param schoolId
	 * @param stuList
	 * @return
	 */
	public int sendNoticeEmial(CompanyStudentMessage companyStudentMessage, Integer companyId,
			Integer schoolId, List<Student> stuList) {
		int count = 0;
		Company company = companyServiceImpl.findCompanyById(companyId);
		//邮件头
		try {
			Properties prop = new Properties();
			InputStream in = MailUtil.class.getClassLoader().getResourceAsStream("config.properties");
			prop.load(in);
			String url = prop.getProperty("url");
			String apiUser = prop.getProperty("apiUser");
			String apiKey = prop.getProperty("apiKey");
			//邮件标题头
			Mail mail = new Mail();
			mail.setFrom("contact@yuuxin.com");
			mail.setFromName(company.getCompanyName());
			mail.setSubject(companyStudentMessage.getEmailTitle());
			//邮件模板
			NoticeEmail model = new NoticeEmail();
			model.setMsg(companyStudentMessage.getContent().replace("\"", "\\\""));
			for (Student student : stuList) {
				if(student != null && student.getEmail() != null){
					mail.setTo(student.getEmail());
					MailUtil mu = new MailUtil(mail, model);
					String result11 = mu.sendmail(url, apiUser, apiKey,MailUtil.NOTICE);
					CompanyEmailHistory cmh = new CompanyEmailHistory();
					cmh.setReceiverEmail(student.getEmail());
					cmh.setReceiverUserId(student.getUserId());
					cmh.setContent(companyStudentMessage.getContent());
					cmh.setSendTime(new Date());
					if(result11.equals("success")){
						cmh.setSendStatus(1);
					}else{
						cmh.setSendStatus(0);
					}
					cmh.setSendResult(result11);
					cmh.setBusinessType("BUSINESS_STUDENT_NOTICE");
					cmh.setCompanyId(companyId);
					cmh.setSchoolId(schoolId);
					cmh.setTitle(companyStudentMessage.getEmailTitle());
					cmh.setMessageId(companyStudentMessage.getId());
					companyEmailServiecimpl.insert(cmh);
					count ++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return count;
	}
	/**
	 * 学员通知发送短信
	 * @author licong
	 * @date 2016年10月25日 下午5:00:38
	 * @param
	 * @param request
	 * @param companyStudentMessage
	 * @param content
	 * @param companyId
	 * @param schoolId
	 * @param user
	 * @param messageCost
	 * @param stuList
	 * @param sendcounts
	 * @return
	 */
	public Integer sendPhoneMessage(HttpServletRequest request, CompanyStudentMessage companyStudentMessage,
			String content, Integer companyId, Integer schoolId, Users user, Integer messageCost, List<Student> stuList,
			Integer sendcounts) {
		String result;
		String status;
		String message;
		for (Student s : stuList) {
			if(null!=s && null!=s.getMobile() && !"".equals(s.getMobile())){//2016/7/7  手机为空则不发短信
				result = SmsClientSend.sendSmsTwo(request
						,s.getMobile().trim(), content + "【在线网校】"
						,user.getId(),"stu-notice");
				status = result.substring(result.indexOf("<returnstatus>"),result.indexOf("</returnstatus>"));
				status = status.substring(status.indexOf(">") + 1);
				message = result.substring(result.indexOf("<message>"),result.indexOf("</message>"));
				message = message.substring(message.indexOf(">") + 1);
				if(message.equals("ok")){
					message = "发送成功";
				}
				CompanyMessageHistory cmh = new CompanyMessageHistory();
				cmh.setReceiverUserId(""+s.getId());
				cmh.setReceiverMobile(s.getMobile().trim());
				cmh.setContent(companyStudentMessage.getContent());
				cmh.setSendTime(new Date());
				if(status.equals("Success")){
					cmh.setSendStatus(1);
				}else{
					cmh.setSendStatus(0);
				}
				cmh.setSendResult(message);
				cmh.setBusinessType("BUSINESS_STUDENT_MESSAGE");
				cmh.setCompanyId(companyId);
				cmh.setSchoolId(schoolId);
				cmh.setCostNum(messageCost);
				cmh.setMessageId(companyStudentMessage.getId());
				companyMessageHistoryServiceImpl.insert(cmh);
				sendcounts++;
			}
		}
		return sendcounts;
	}

	//添加模块
	@ResponseBody
	@RequestMapping(value="/saveModules",method=RequestMethod.POST)
	public ClassModule saveModules(ClassModule module,Model model,HttpServletRequest request){
		module.setDelFlag(0);
		module.setCompanyId(WebUtils.getCurrentCompanyId());
		module.setUpdateTime(new Date());
		module.setUpdator(WebUtils.getCurrentUserId(request));
		module.setSchoolId(WebUtils.getCurrentSchoolId());
		module.setPublishStatus("MODULE_PUBLISHED");
		module.setCreateTime(new Date());
		module.setCreator(WebUtils.getCurrentUserId(request));
		classModuleServiceImpl.insert(module);
		return classModuleServiceImpl.findClassModuleById(module.getId());
	}

	/**
	 *
	 * Class Name: ClassModuleController.java
	 * @Description: 添加简版课程
	 * @author zhang.zx
	 * @date 2015年9月9日 下午3:27:01
	 * @modifier
	 * @modify-date 2015年9月9日 下午3:27:01
	 * @version 1.0
	 * @param module
	 * @param classtypeId
	 * @param model
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/saveCourse",method=RequestMethod.POST)
	public JSONObject saveCourse(ClassModule module,Integer classtypeId,Model model,HttpServletRequest request){
		JSONObject json=new JSONObject();
		module.setDelFlag(0);
		module.setCompanyId(WebUtils.getCurrentCompanyId());
		module.setUpdateTime(new Date());
		module.setUpdator(WebUtils.getCurrentUserId(request));
		module.setSchoolId(WebUtils.getCurrentSchoolId());
		module.setPublishStatus("MODULE_PUBLISHED");
		module.setCreateTime(new Date());
		module.setCreator(WebUtils.getCurrentUserId(request));
		classModuleServiceImpl.insert(module);
		//添加班型模块管理表
		ClassTypeModuleRelation ctmr=new ClassTypeModuleRelation();
    	ctmr.setModuleId(module.getId());
    	ctmr.setClassTypeId(classtypeId);
    	classTypeModuleRelationServiceImpl.insert(ctmr);
    	//添加班号
    	ClassModuleNo classmoduleNo=new ClassModuleNo();
    	String name=productClassNo(module.getName(), module.getTeachMethod());
    	classmoduleNo.setName(name);
    	classmoduleNo.setStartDate(new Date());
    	classmoduleNo.setItemOneId(module.getItemOneId());
    	classmoduleNo.setItemSecondId(module.getItemSecondId());
    	classmoduleNo.setModuleId(module.getId());
    	classmoduleNo.setClassTeachType(module.getTeachMethod());
    	classmoduleNo.setTotalHours(module.getTotalClassHour());
    	classmoduleNo.setSchoolId(WebUtils.getCurrentSchoolId());
    	classmoduleNo.setCompanyId(WebUtils.getCurrentCompanyId());
    	classmoduleNo.setDelFlag(0);
    	classmoduleNo.setCreateTime(new Date());
    	classmoduleNo.setCreator(WebUtils.getCurrentUserId(request));
    	classmoduleNo.setUpdateTime(new Date());
    	classmoduleNo.setUpdator(WebUtils.getCurrentUserId(request));
    	classmoduleNo.setPublishStatus("MODULE_NO_ON_SALE");
    	classmoduleNo.setLessonTotal(1);
    	classmoduleNo.setLessonPlan(1);
    	classModuleNoServiceImpl.insert(classmoduleNo);
    	//添加在售班号
    	ClassModuleNoOnsale entity=new ClassModuleNoOnsale();
    	entity.setClasstypeId(classtypeId);
    	entity.setDefaultFlag(1);
    	entity.setModuleId(module.getId());
    	entity.setModuleNoId(classmoduleNo.getId());
    	classModuleNoOnsaleServiceImpl.insert(entity);

		json.put("moduleId", module.getId());
		json.put("classModuleNoId", classmoduleNo.getId());
		json.put("onsaleNoId", entity.getId());
		return json;
	}

	//生成班号
	private String productClassNo(String name,String type1){
		String type="";
    	if("TEACH_METHOD_LIVE".equals(type1)){
    		type="直播";
    	}else{
    		type="面授";
    	}
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
    	String d=sdf.format(new Date());
    	return name+"-"+type+"-"+d;
	}

	//添加课次
	@ResponseBody
	@RequestMapping(value="/addCourseLesson",method=RequestMethod.POST)
	public JSONObject addCourseLesson(HttpServletRequest request,String moduleName
			,Integer classtypeId,Integer itemOneId,Integer itemSecondId,String teachMethod
			,Integer totalHours,String lessonDate,String lessonTimeStart,String lessonTimeEnd
			,String lessonHour,String teachers,String teachersName,String assistants
			,String assistantsName,String lessonName,String classroomName,Integer classroomId
			,Integer mark,Integer classNoId,Integer supportMobile,String liveClassType,
			Integer barrage,Integer modetype) throws Exception{
		JSONObject json = new JSONObject();
		Integer companyId = WebUtils.getCurrentCompanyId();

		CompanyLiveConfig clc = companyLiveConfigServiceImpl
				.findByCompanyId(companyId);
		String openId = null;
		String openToken = null;
		if(clc != null && clc.getLiveType().equals(3)){
			openId = clc.getLoginName();
			openToken = clc.getPassword();
		}
		if(classNoId!=null&&!"".equals(classNoId)){
			ClassModuleNo moduleNo=classModuleNoServiceImpl.findClassModuleNoById(classNoId);
			List<ClassModuleLesson> lessonsarr=classModuleLessonServiceImpl.findClassModuleLessonByModuleNoId(moduleNo.getId());
			if(lessonsarr.size()>0){
				//添加在售班号
		    	ClassModuleNoOnsale entity=new ClassModuleNoOnsale();
		    	entity.setClasstypeId(classtypeId);
		    	entity.setDefaultFlag(0);
		    	entity.setModuleId(moduleNo.getModuleId());
		    	entity.setModuleNoId(moduleNo.getId());
		    	classModuleNoOnsaleServiceImpl.insert(entity);
				//创建课次
				ClassModuleLesson lesson=new ClassModuleLesson();
				lesson.setLessonName(lessonName);
				lesson.setModuleNoId(moduleNo.getId());
				lesson.setDelFlag(0);
				lesson.setLessonDate(new Date(lessonDate));
				String newDate=lessonDate.replaceAll("/", "-");
				String week=DatetimeUtil.getWeekByDateStr(newDate);
				lesson.setWeekType(week);
				lesson.setLessonTimeStart(lessonTimeStart);
				lesson.setLessonTimeEnd(lessonTimeEnd);
				lesson.setTeachers(teachers);
				lesson.setTeachersName(teachersName);
				lesson.setAssistants(assistants);
				lesson.setAssistantsName(assistantsName);
				lesson.setLessonHour(lessonHour);
				lesson.setDelFlag(0);
				lesson.setChapterFlag(mark);
				lesson.setSupportMobile(supportMobile);
				lesson.setLiveClassType(liveClassType);
				lesson.setCreateTime(new Date());
				lesson.setCreator(WebUtils.getCurrentUserId(request));
				lesson.setLiveRoom(UUID.randomUUID().toString().replaceAll("-", ""));
				if("TEACH_METHOD_FACE".equals(teachMethod)){
					lesson.setClassroom(classroomName);
					lesson.setClassroomId(classroomId);
				}
				lesson.setBarrage(barrage);
				lesson.setModetype(modetype);
				classModuleLessonServiceImpl.insert(lesson);
				//更新直播教室
				if("TEACH_METHOD_LIVE".equals(teachMethod)){
					ClassModuleLesson lec=classModuleLessonServiceImpl.findClassModuleLessonById(lesson.getId());
					//如果是新增的话，需要判断当前公司的直播服务提供商是哪家
					CompanyMemberService liveProvider = companyMemberServiceServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
					//判断当前公司是E课堂还是展示互动
					if(liveProvider == null
							|| liveProvider.getLiveServiceProvider() == null
							|| liveProvider.getLiveServiceProvider().equals("")
							|| liveProvider.getLiveServiceProvider().equals("gh")){
						try {
							String liveRoom = eketangLiveRoomServiceImpl.createLiveRoom(lec, companyId);
							LiveRoomInfo bean = (LiveRoomInfo) net.sf.json.JSONObject.toBean(net.sf.json.JSONObject.fromObject(liveRoom),LiveRoomInfo.class);
							if (bean != null && ("ok").equals(bean.getMsg())) {
								lec.setLiveroomIdGh(bean.getLiveRoomId());
								if(liveProvider != null && liveProvider.getLiveServiceTemplate() != null && liveProvider.getLiveServiceTemplate().equals(Constant.EKETANG_TEMPALATE_SOOONER)){
									lec.setStudentUrlGh(bean.getStudentUrl());
								}else{
									lec.setStudentUrlGh(bean.getStudentUrl().replace("soooner", "taobao"));
								}
								lec.setTeacherUrlGh(bean.getTeacherUrl());
								lec.setAssistantUrlGh(bean.getAssistantUrl());
								lec.setReplayUrlGh(bean.getReplayUrl());
								lec.setLiveCompanyType("gh");
							}
							json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
						} catch (Exception e) {
							e.printStackTrace();
							log.info("创建展示互动直播课程失败");
							json.put(JsonMsg.MSG, "创建直播课失败");
							return json;
						}
					}else if(liveProvider.getLiveServiceProvider().equals("zs")){
						try {
							//展示互动的账号/密码
							String zsLoginName = properties.getZsLoginName();
							String zsAddress = properties.getZsAddress();
							String returnLiveRoom=null;
							if(lec.getLiveroomIdGh() != null && lec.getLiveroomIdGh().length() > 0){
								returnLiveRoom= genseeLiveRoomServiceImpl.updateLiveRoom(lec,companyId);
							}else{
								returnLiveRoom= genseeLiveRoomServiceImpl.createLiveRoom(lec,companyId);
							}
							ZsReturnInfo bean = null;
							try {
								bean = (ZsReturnInfo) net.sf.json.JSONObject.toBean(net.sf.json.JSONObject.fromObject(returnLiveRoom),ZsReturnInfo.class);
							} catch (Exception e) {
								e.printStackTrace();
								log.info("创建直播教室出现异常，当前bean信息为："+bean+", 教室的ID为："+lec.getId()+", 教室的名称："+lec.getLessonName());
								json.put(JsonMsg.MSG, "创建直播课失败");
								return json;
							}
							lec.setLiveroomIdGh(bean.getId());
							lec.setStudentUrlGh(bean.getStudentJoinUrl());
							lec.setTeacherUrlGh(bean.getTeacherJoinUrl());
							lec.setAssistantUrlGh(bean.getTeacherJoinUrl());
							lec.setLiveCompanyType("zs");
							json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
						} catch (Exception e) {
							e.printStackTrace();
							log.info("创建展示互动直播课程失败");
							json.put(JsonMsg.MSG, "创建直播课失败");
							return json;
						}
					}else if(liveProvider.getLiveServiceProvider().equals("ht")){
						try {
							String res = "";
							//老师介绍
							SysConfigTeacher teacher = sysConfigTeacherServiceImpl.findSysConfigTeacherById(Integer.parseInt(lec.getTeachers()));
							//Map<Object, Object> options = new HashMap<Object,Object>();
							Map<Object, Object> param = new HashMap<Object,Object>();
							param.put("course_name", lec.getLessonName());
							param.put("account", teacher.getId());
							param.put("start_time", new SimpleDateFormat("yyyy-MM-dd")
								.format(lec.getLessonDate()) + " " + lec.getLessonTimeStart()
								+ ":00");
							param.put("end_time",  new SimpleDateFormat("yyyy-MM-dd")
								.format(lec.getLessonDate()) + " " + lec.getLessonTimeEnd()
								+ ":00");
							param.put("nickname", teacher.getName());
							param.put("accountIntro", teacher.getResume());

							Map<Object, Object> options = new HashMap<Object,Object>();
							options.put("departmentId", companyId);
							options.put("barrage", lec.getBarrage());
							options.put("modetype", lec.getModetype());
							param.put("options", options);

							if(lec.getLiveroomIdGh() != null && lec.getLiveroomIdGh().length() > 0){
								param.put("course_id", lec.getLiveroomIdGh());
								res = TalkfunUtils.getRsult(param, "course.update",openId,openToken);
							}else{
								res = TalkfunUtils.getRsult(param, "course.add",openId,openToken);
							}
							JSONObject rjson = JSONObject.parseObject(res);
							if(rjson.getInteger("code").equals(0)){
								json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
							}else{
								if(lec.getLiveroomIdGh() == null){
									classModuleLessonServiceImpl
										.deleteClassModuleLessonById(lec.getId());
								}
								json.put(JsonMsg.MSG, rjson.getString("msg"));
								return json;
							}
							if(rjson.get("data") != null && !rjson.get("data").equals("")){
								TalkfunEntityVo tf = JSONObject.parseObject(
										rjson.getJSONObject("data").toJSONString()
										, TalkfunEntityVo.class);
								lec.setLiveroomIdGh(tf.getCourse_id());
								lec.setLiveCompanyType("ht");
							}
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
							log.info("创建欢拓直播失败");
							json.put(JsonMsg.MSG, "创建直播课程失败");
							return json;
						}
					}else{
						try {
							String returnLiveRoom=null;
							if(lec.getLiveroomIdGh() != null && lec.getLiveroomIdGh().length() > 0){
								returnLiveRoom= CCLiveRoomServiceImpl.updateLiveRoom(lec,companyId);
							}else{
								returnLiveRoom= CCLiveRoomServiceImpl.createLiveRoom(lec,companyId);
							}
							if(returnLiveRoom != null && "OK".equals(net.sf.json.JSONObject
									.fromObject(returnLiveRoom).getString("result"))){
								JSONObject rj = JSONObject.parseObject(net.sf.json.JSONObject
										.fromObject(returnLiveRoom).getString("room"));
								lec.setLiveroomIdGh(rj.getString("roomid"));
								lec.setStudentUrlGh(rj.getString("s"));
								lec.setTeacherUrlGh(rj.getString("t"));
								lec.setAssistantUrlGh(rj.getString("a"));
								lec.setLiveCompanyType("cc");
								json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
							}else{
								json.put(JsonMsg.MSG, "创建直播课程失败");
								return json;
							}
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
							log.debug("创建cc直播课失败," + e.getMessage());
							json.put(JsonMsg.MSG, "创建直播课程失败");
							return json;
						}
					}
					classModuleLessonServiceImpl.update(lec);
				}
			}
		}else{
			//添加模块
			ClassModule module=new ClassModule();
			module.setName(moduleName);
			module.setTeachMethod(teachMethod);
			module.setTotalClassHour(totalHours);
			module.setItemOneId(itemOneId);
			module.setItemSecondId(itemSecondId);
			module.setDelFlag(0);
			module.setChapterFlag(mark);
			module.setCompanyId(WebUtils.getCurrentCompanyId());
			module.setUpdateTime(new Date());
			module.setUpdator(WebUtils.getCurrentUserId(request));
			module.setSchoolId(WebUtils.getCurrentSchoolId());
			module.setPublishStatus("MODULE_PUBLISHED");
			module.setCreateTime(new Date());
			module.setCreator(WebUtils.getCurrentUserId(request));
			classModuleServiceImpl.insert(module);
			//添加班型模块管理表
			ClassTypeModuleRelation ctmr=new ClassTypeModuleRelation();
	    	ctmr.setModuleId(module.getId());
	    	ctmr.setClassTypeId(classtypeId);
	    	classTypeModuleRelationServiceImpl.insert(ctmr);
	    	//添加班号
	    	ClassModuleNo classmoduleNo=new ClassModuleNo();
	    	String name=productClassNo(moduleName, teachMethod);
	    	classmoduleNo.setName(name);
	    	classmoduleNo.setItemOneId(itemOneId);
	    	classmoduleNo.setItemSecondId(itemSecondId);
	    	classmoduleNo.setModuleId(module.getId());
	    	classmoduleNo.setTotalHours(totalHours);
	    	classmoduleNo.setClassTeachType(teachMethod);
	    	classmoduleNo.setSchoolId(WebUtils.getCurrentSchoolId());
	    	classmoduleNo.setCompanyId(WebUtils.getCurrentCompanyId());
	    	classmoduleNo.setDelFlag(0);
	    	classmoduleNo.setCreateTime(new Date());
	    	classmoduleNo.setCreator(WebUtils.getCurrentUserId(request));
	    	classmoduleNo.setUpdateTime(new Date());
	    	classmoduleNo.setUpdator(WebUtils.getCurrentUserId(request));
	    	classmoduleNo.setPublishStatus("MODULE_NO_ON_SALE");
	    	classmoduleNo.setLessonTotal(1);
	    	classmoduleNo.setLessonPlan(1);
	    	classModuleNoServiceImpl.insert(classmoduleNo);
	    	//添加在售班号
	    	ClassModuleNoOnsale entity=new ClassModuleNoOnsale();
	    	entity.setClasstypeId(classtypeId);
	    	entity.setDefaultFlag(1);
	    	entity.setModuleId(module.getId());
	    	entity.setModuleNoId(classmoduleNo.getId());
	    	classModuleNoOnsaleServiceImpl.insert(entity);
			//创建课次
			ClassModuleLesson lesson=new ClassModuleLesson();
			lesson.setLessonName(lessonName);
			lesson.setModuleNoId(classmoduleNo.getId());
			lesson.setDelFlag(0);
			lesson.setLessonDate(new Date(lessonDate));
			String newDate=lessonDate.replaceAll("/", "-");
			String week=DatetimeUtil.getWeekByDateStr(newDate);
			lesson.setWeekType(week);
			lesson.setLessonTimeStart(lessonTimeStart);
			lesson.setLessonTimeEnd(lessonTimeEnd);
			lesson.setTeachers(teachers);
			lesson.setTeachersName(teachersName);
			lesson.setAssistants(assistants);
			lesson.setAssistantsName(assistantsName);
			lesson.setLessonHour(lessonHour);
			lesson.setDelFlag(0);
			lesson.setChapterFlag(mark);
			lesson.setCreateTime(new Date());
			lesson.setCreator(WebUtils.getCurrentUserId(request));
			lesson.setLiveRoom(UUID.randomUUID().toString().replaceAll("-", ""));
			if("TEACH_METHOD_FACE".equals(teachMethod)){
				lesson.setClassroom(classroomName);
				lesson.setClassroomId(classroomId);
			}
			lesson.setBarrage(barrage);
			lesson.setModetype(modetype);
			classModuleLessonServiceImpl.insert(lesson);
			//更新直播教室
			if("TEACH_METHOD_LIVE".equals(teachMethod)){
				ClassModuleLesson lec=classModuleLessonServiceImpl.findClassModuleLessonById(lesson.getId());
				//如果是新增的话，需要判断当前公司的直播服务提供商是哪家
				CompanyMemberService liveProvider = companyMemberServiceServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
				//判断当前公司是E课堂还是展示互动
				if(liveProvider != null
						|| liveProvider.getLiveServiceProvider() != null
						|| liveProvider.getLiveServiceProvider().equals("")
						|| liveProvider.getLiveServiceProvider().equals("gh")){
					try {
						String liveRoom = eketangLiveRoomServiceImpl.createLiveRoom(lec, companyId);
						LiveRoomInfo bean = (LiveRoomInfo) net.sf.json.JSONObject.toBean(net.sf.json.JSONObject.fromObject(liveRoom),LiveRoomInfo.class);
						if (bean != null && ("ok").equals(bean.getMsg())) {
							lec.setLiveroomIdGh(bean.getLiveRoomId());
							if(liveProvider != null && liveProvider.getLiveServiceTemplate() != null && liveProvider.getLiveServiceTemplate().equals(Constant.EKETANG_TEMPALATE_SOOONER)){
								lec.setStudentUrlGh(bean.getStudentUrl());
							}else{
								lec.setStudentUrlGh(bean.getStudentUrl().replace("soooner", "taobao"));
							}
							lec.setTeacherUrlGh(bean.getTeacherUrl());
							lec.setAssistantUrlGh(bean.getAssistantUrl());
							lec.setReplayUrlGh(bean.getReplayUrl());
						}
						json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
					} catch (Exception e) {
						e.printStackTrace();
						json.put(JsonMsg.MSG, "创建直播课失败");
						return json;
					}
				}else if(liveProvider.getLiveServiceProvider().equals("zs")){
					try {
						//展示互动的账号/密码
						String zsLoginName = properties.getZsLoginName();
						String zsAddress = properties.getZsAddress();
						String returnLiveRoom=null;
						if(lec.getLiveroomIdGh() != null && lec.getLiveroomIdGh().length() > 0){
							returnLiveRoom= genseeLiveRoomServiceImpl.updateLiveRoom(lec,companyId);
						}else{
							returnLiveRoom= genseeLiveRoomServiceImpl.createLiveRoom(lec,companyId);
						}
						ZsReturnInfo bean = null;
						try {
							bean = (ZsReturnInfo) net.sf.json.JSONObject.toBean(net.sf.json.JSONObject.fromObject(returnLiveRoom),ZsReturnInfo.class);
						} catch (Exception e) {
							e.printStackTrace();
							log.info("创建直播教室出现异常，当前bean信息为："+bean+", 教室的ID为："+lec.getId()+", 教室的名称："+lec.getLessonName());
							json.put(JsonMsg.MSG, "创建直播课失败");
							return json;
						}
						lec.setLiveroomIdGh(bean.getId());
						lec.setStudentUrlGh(bean.getStudentJoinUrl());
						lec.setTeacherUrlGh(bean.getTeacherJoinUrl());
						lec.setAssistantUrlGh(bean.getTeacherJoinUrl());
						json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
					} catch (Exception e) {
						e.printStackTrace();
						json.put(JsonMsg.MSG, "创建直播课失败");
						return json;
					}
				}else if(liveProvider.getLiveServiceProvider().equals("ht")){
					try {
						String res = "";
						//老师介绍
						SysConfigTeacher teacher = sysConfigTeacherServiceImpl.findSysConfigTeacherById(Integer.parseInt(lec.getTeachers()));
						//Map<Object, Object> options = new HashMap<Object,Object>();
						Map<Object, Object> param = new HashMap<Object,Object>();
						param.put("course_name", lec.getLessonName());
						param.put("account", teacher.getId());
						param.put("start_time", new SimpleDateFormat("yyyy-MM-dd")
							.format(lec.getLessonDate()) + " " + lec.getLessonTimeStart()
							+ ":00");
						param.put("end_time",  new SimpleDateFormat("yyyy-MM-dd")
							.format(lec.getLessonDate()) + " " + lec.getLessonTimeEnd()
							+ ":00");
						param.put("nickname", teacher.getName());
						param.put("accountIntro", teacher.getResume());

						Map<Object, Object> options = new HashMap<Object,Object>();
						options.put("departmentId", companyId);
						options.put("barrage", lec.getBarrage());
						options.put("modetype", lec.getModetype());
						param.put("options", options);

						if(lec.getLiveroomIdGh() != null && lec.getLiveroomIdGh().length() > 0){
							param.put("course_id", lec.getLiveroomIdGh());
							res = TalkfunUtils.getRsult(param, "course.update",openId,openToken);
						}else{
							res = TalkfunUtils.getRsult(param, "course.add",openId,openToken);
						}
						JSONObject rjson = JSONObject.parseObject(res);
						if(rjson.getInteger("code").equals(0)){
							json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
						}else{
							if(lec.getLiveroomIdGh() == null){
								classModuleLessonServiceImpl
									.deleteClassModuleLessonById(lec.getId());
							}
							json.put(JsonMsg.MSG, rjson.getString("msg"));
							return json;
						}
						if(rjson.get("data") != null && !rjson.get("data").equals("")){
							TalkfunEntityVo tf = JSONObject.parseObject(
									rjson.getJSONObject("data").toJSONString()
									, TalkfunEntityVo.class);
							lec.setLiveroomIdGh(tf.getCourse_id());
						}
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						log.info("创建欢拓直播失败");
						json.put(JsonMsg.MSG, "创建直播课程失败");
						return json;
					}
				}else{
					try {
						String returnLiveRoom=null;
						if(lec.getLiveroomIdGh() != null && lec.getLiveroomIdGh().length() > 0){
							returnLiveRoom= CCLiveRoomServiceImpl.updateLiveRoom(lec,companyId);
							if("OK".equals(returnLiveRoom)){
								json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
							}else{
								json.put(JsonMsg.MSG, "修改直播课程失败");
								return json;
							}
						}else{
							returnLiveRoom= CCLiveRoomServiceImpl.createLiveRoom(lec,companyId);
							if(returnLiveRoom != null){
								JSONObject rj = JSONObject.parseObject(returnLiveRoom);
								lec.setLiveroomIdGh(rj.getString("roomid"));
								lec.setStudentUrlGh(rj.getString("s"));
								lec.setTeacherUrlGh(rj.getString("t"));
								lec.setAssistantUrlGh(rj.getString("a"));
								lec.setLiveCompanyType("cc");
								json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
							}else{
								json.put(JsonMsg.MSG, "创建直播课程失败");
								return json;
							}
						}
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						log.debug("创建cc直播课失败," + e.getMessage());
						json.put(JsonMsg.MSG, "创建直播课程失败");
						return json;
					}
				}
				classModuleLessonServiceImpl.update(lec);
			}
		}
		return json;
	}

	/**
	 *
	 * Class Name: ClassModuleController.java
	 * @Description: 添加模块下课次
	 * @author zhang.zx
	 * @date 2015年9月12日 下午7:45:37
	 * @modifier
	 * @modify-date 2015年9月12日 下午7:45:37
	 * @version 1.0
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/addModuleCourseLesson",method=RequestMethod.POST)
	public JSONObject addModuleCourseLesson(HttpServletRequest request,String moduleName,Integer classtypeId,Integer itemOneId,Integer itemSecondId,
			String teachMethod,String lessonDate,String lessonTimeStart,String lessonTimeEnd,Integer lessonHour,
			String teachers,String teachersName,String assistants,String assistantsName,String lessonName,
			String classroomName,Integer classroomId,Integer mark,Integer moduleId,Integer supportMobile,String liveClassType
			,Integer barrage ,Integer modetype) throws Exception{
		JSONObject json = new JSONObject();
		Integer companyId = WebUtils.getCurrentCompanyId();

		//更新班号表信息
		List<ClassModuleNo> arr=classModuleNoServiceImpl.queryClassModuleNoById(moduleId);
		if(!arr.isEmpty()&&arr.size()>0){
			Integer count=lessonHour;
			ClassModuleNo moduleNo=arr.get(0);
			List<ClassModuleLesson> lessons=classModuleLessonServiceImpl.findClassModuleLessonByModuleNoId(moduleNo.getId());
			for(ClassModuleLesson le:lessons){
				count+=Integer.parseInt(le.getLessonHour());
			}
			moduleNo.setTotalHours(count);
			classModuleNoServiceImpl.update(moduleNo);
			ClassModule module=new ClassModule();
			module.setId(moduleId);
			module.setTotalClassHour(count);
			classModuleServiceImpl.update(module);
		}
		//创建课次
		ClassModuleLesson lesson=new ClassModuleLesson();
		lesson.setLessonName(lessonName);
		if(null!=arr.get(0) && null!=arr.get(0).getId()){
			lesson.setModuleNoId(arr.get(0).getId());
		}
		lesson.setDelFlag(0);
		lesson.setLessonDate(new Date(lessonDate));
		String newDate=lessonDate.replaceAll("/", "-");
		String week=DatetimeUtil.getWeekByDateStr(newDate);
		lesson.setWeekType(week);
		lesson.setLessonTimeStart(lessonTimeStart);
		lesson.setLessonTimeEnd(lessonTimeEnd);
		lesson.setTeachers(teachers);
		lesson.setTeachersName(teachersName);
		lesson.setAssistants(assistants);
		lesson.setAssistantsName(assistantsName);
		lesson.setLessonHour(lessonHour.toString());
		lesson.setDelFlag(0);
		lesson.setChapterFlag(mark);
		lesson.setSupportMobile(supportMobile);
		lesson.setLiveClassType(liveClassType);
		lesson.setCreateTime(new Date());
		lesson.setCreator(WebUtils.getCurrentUserId(request));
		if("TEACH_METHOD_FACE".equals(teachMethod)){
			lesson.setClassroom(classroomName);
			lesson.setClassroomId(classroomId);
		}
		lesson.setBarrage(barrage != null ? barrage : 0);
		lesson.setModetype(modetype != null ? modetype : 3);
		lesson.setLiveRoom(UUID.randomUUID().toString().replaceAll("-", ""));
		classModuleLessonServiceImpl.insert(lesson);
		json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
		//更新直播教室
		if("TEACH_METHOD_LIVE".equals(teachMethod)){
			ClassModuleLesson lec=classModuleLessonServiceImpl.findClassModuleLessonById(lesson.getId());
			//如果是新增的话，需要判断当前公司的直播服务提供商是哪家
			CompanyMemberService liveProvider = companyMemberServiceServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
			//判断当前公司是E课堂还是展示互动
			if(liveProvider == null
					|| liveProvider.getLiveServiceProvider() == null
					|| liveProvider.getLiveServiceProvider().equals("")
					|| liveProvider.getLiveServiceProvider().equals("gh")){
				try {
					String liveRoom = eketangLiveRoomServiceImpl.createLiveRoom(lec, companyId);
//				String liveRoom = liveRoom(lec)
					LiveRoomInfo bean = (LiveRoomInfo) net.sf.json.JSONObject.toBean(net.sf.json.JSONObject.fromObject(liveRoom),LiveRoomInfo.class);
					if (bean != null && ("ok").equals(bean.getMsg())) {
						lec.setLiveroomIdGh(bean.getLiveRoomId());
						if(liveProvider != null && liveProvider.getLiveServiceTemplate() != null && liveProvider.getLiveServiceTemplate().equals(Constant.EKETANG_TEMPALATE_SOOONER)){
							lec.setStudentUrlGh(bean.getStudentUrl());
						}else{
							lec.setStudentUrlGh(bean.getStudentUrl().replace("soooner", "taobao"));
						}
						lec.setTeacherUrlGh(bean.getTeacherUrl());
						lec.setAssistantUrlGh(bean.getAssistantUrl());
						lec.setReplayUrlGh(bean.getReplayUrl());
						lec.setLiveCompanyType("gh");
					}
					json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					log.info("创建e课堂失败");
					json.put(JsonMsg.MSG, "创建直播课程失败");
					return json;
				}
			}else if(liveProvider.getLiveServiceProvider().equals("zs")){
				try {
					//展示互动的账号/密码
					String zsLoginName = properties.getZsLoginName();
					String zsAddress = properties.getZsAddress();
					String returnLiveRoom=null;
					if(lec.getLiveroomIdGh() != null && lec.getLiveroomIdGh().length() > 0){
						returnLiveRoom= genseeLiveRoomServiceImpl.updateLiveRoom(lec,companyId);
					}else{
						returnLiveRoom= genseeLiveRoomServiceImpl.createLiveRoom(lec,companyId);
					}
					ZsReturnInfo bean = null;
					try {
						bean = (ZsReturnInfo) net.sf.json.JSONObject.toBean(net.sf.json.JSONObject.fromObject(returnLiveRoom),ZsReturnInfo.class);
					} catch (Exception e) {
						e.printStackTrace();
						json.put(JsonMsg.MSG, "创建直播课程失败");
						log.info("创建直播教室出现异常，当前bean信息为："+bean+", 教室的ID为："+lec.getId()+", 教室的名称："+lec.getLessonName());
						return json;
					}
					lec.setLiveroomIdGh(bean.getId());
					lec.setStudentUrlGh(bean.getStudentJoinUrl());
					lec.setTeacherUrlGh(bean.getTeacherJoinUrl());
					lec.setAssistantUrlGh(bean.getTeacherJoinUrl());
					lec.setLiveCompanyType("zs");
					json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
				} catch (Exception e) {
					e.printStackTrace();
					log.info("创建展示互动直播课程失败");
					json.put(JsonMsg.MSG, "创建直播课程失败");
					return json;
				}
			}else if (liveProvider.getLiveServiceProvider().equals("ht")){
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
					SysConfigTeacher teacher = sysConfigTeacherServiceImpl.findSysConfigTeacherById(Integer.parseInt(lec.getTeachers()));
					//Map<Object, Object> options = new HashMap<Object,Object>();
					Map<Object, Object> param = new HashMap<Object,Object>();
					param.put("course_name", lec.getLessonName());
					param.put("account", teacher.getId());
					param.put("start_time", new SimpleDateFormat("yyyy-MM-dd")
						.format(lec.getLessonDate()) + " " + lec.getLessonTimeStart()
						+ ":00");
					param.put("end_time",  new SimpleDateFormat("yyyy-MM-dd")
						.format(lec.getLessonDate()) + " " + lec.getLessonTimeEnd()
						+ ":00");
					param.put("nickname", teacher.getName());
					param.put("accountIntro", teacher.getResume());

					Map<Object, Object> options = new HashMap<Object,Object>();
					options.put("departmentId", companyId);
					options.put("barrage", barrage);
					options.put("modetype", modetype);
					param.put("options", options);

					if(lec.getLiveroomIdGh() != null && lec.getLiveroomIdGh().length() > 0){
						param.put("course_id", lec.getLiveroomIdGh());
						res = TalkfunUtils.getRsult(param, "course.update",openId,openToken);
					}else{
						res = TalkfunUtils.getRsult(param, "course.add",openId,openToken);
					}
					JSONObject rjson = JSONObject.parseObject(res);
					if(rjson.getInteger("code").equals(0)){
						json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
					}else{
						if(lec.getLiveroomIdGh() == null){
							classModuleLessonServiceImpl
								.deleteClassModuleLessonById(lec.getId());
						}
						json.put(JsonMsg.MSG, rjson.getString("msg"));
						return json;
					}
					if(rjson.get("data") != null && !rjson.get("data").equals("")){
						TalkfunEntityVo tf = JSONObject.parseObject(
								rjson.getJSONObject("data").toJSONString()
								, TalkfunEntityVo.class);
						lec.setLiveroomIdGh(tf.getCourse_id());
						lec.setLiveCompanyType("ht");
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					log.info("创建欢拓直播失败");
					json.put(JsonMsg.MSG, "创建直播课程失败");
					return json;
				}
			}else{
				try {
					String returnLiveRoom=null;
					if(lec.getLiveroomIdGh() != null && lec.getLiveroomIdGh().length() > 0){
						returnLiveRoom= CCLiveRoomServiceImpl.updateLiveRoom(lec,companyId);
						if("OK".equals(returnLiveRoom)){
							json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
						}else{
							json.put(JsonMsg.MSG, "修改直播课程失败");
							return json;
						}
					}else{
						returnLiveRoom= CCLiveRoomServiceImpl.createLiveRoom(lec,companyId);
						if(returnLiveRoom != null){
							JSONObject rj = JSONObject.parseObject(returnLiveRoom);
							lec.setLiveroomIdGh(rj.getString("roomid"));
							lec.setStudentUrlGh(rj.getString("s"));
							lec.setTeacherUrlGh(rj.getString("t"));
							lec.setAssistantUrlGh(rj.getString("a"));
							lec.setLiveCompanyType("cc");
							json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
						}else{
							json.put(JsonMsg.MSG, "创建直播课程失败");
							return json;
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					log.debug("创建cc直播课失败," + e.getMessage());
					json.put(JsonMsg.MSG, "创建直播课程失败");
					return json;
				}
			}
			classModuleLessonServiceImpl.update(lec);
		}
		return json;
	}

	@ResponseBody
	@RequestMapping("/checkOut")
	public JSONObject checkOut(HttpServletRequest request) throws Exception{
		Integer companyId = WebUtils.getCurrentCompanyId();
		Date date = new Date();
		Date lessonDate = new SimpleDateFormat("yyyy-MM-dd").parse((date.getYear() + 1900) + "-" + (date.getMonth() + 1) + "-" + date.getDate());
		String currentTime = date.getHours() + ":" + date.getMinutes();
		JSONObject json = new JSONObject();
		//查询当前直播课程的直播id
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("lessonDate", lessonDate);
		param.put("currentTime", currentTime);

		//查询公司服务时间
		Company company = companyServiceImpl.findCompanyById(companyId);
		if(lessonDate.after(company.getMemberEndDate())){
			json.put(JsonMsg.RESULT, "service");
			return json;
		}

		json.put(JsonMsg.RESULT, JsonMsg.SUCCESS);
		return json;
	}

	/**
	 *
	 * Class Name: ClassModuleController.java
	 * @Description: 删除模块
	 * @author zhang.zx
	 * @date 2015年9月12日 下午4:02:04
	 * @modifier
	 * @modify-date 2015年9月12日 下午4:02:04
	 * @version 1.0
	 * @param moduleId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/delModule")
	public String delclassModule(Integer moduleId){
		//根据模块查询班号
		List<ClassModuleNo> arr=classModuleNoServiceImpl.queryClassModuleNoById(moduleId);
		//根据班号查询课次
		if(!arr.isEmpty()&&arr.size()>0){
			List<ClassModuleLesson>  lesson=classModuleLessonServiceImpl.findClassModuleLessonByModuleNoId(arr.get(0).getId());
			if(lesson.isEmpty()||lesson.size()<=0){
				ClassModule classModule=classModuleServiceImpl.findClassModuleById(moduleId);
				ClassModuleNoOnsale onsale=new ClassModuleNoOnsale();
				onsale.setModuleId(moduleId);
				onsale.setModuleNoId(arr.get(0).getId());
				ClassModuleNoOnsale sale=classModuleNoOnsaleServiceImpl.queryClassModuleOnSale(onsale);
				//删除模块、班号、在售班号
				try {
					classModuleServiceImpl.deleteClassModuleById(classModule.getId());
					classModuleNoServiceImpl.deleteClassModuleNoById(arr.get(0).getId());
					classModuleNoOnsaleServiceImpl.deleteClassModuleNoOnsaleById(sale.getId());
				} catch (Exception e) {
					log.error("删除模块失败-----",e);
					e.printStackTrace();
					return JsonMsg.ERROR;
				}
				return JsonMsg.SUCCESS;
			}
		}
		return JsonMsg.ERROR;
	}

	/**
	 *
	 * Class Name: ClassModuleController.java
	 * @Description: 删除课次
	 * @author zhang.zx
	 * @date 2015年9月12日 下午5:47:23
	 * @modifier
	 * @modify-date 2015年9月12日 下午5:47:23
	 * @version 1.0
	 * @param lessonId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/delModuleLesson")
	public String delClassModuleLesson(Integer lessonId){
		//查询课次
		ClassModuleLesson lesson=classModuleLessonServiceImpl.findClassModuleLessonById(lessonId);
		if(null!=lesson){
			try {
				if("ht".equals(lesson.getLiveCompanyType())){
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
					param.put("course_id", lesson.getLiveroomIdGh());
					try {
						res = TalkfunUtils.getRsult(param, "course.delete",openId,openToken);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					log.debug("删除课次，"+res);
				}
				if("cc".equals(lesson.getLiveCompanyType())){
					CompanyLiveConfig clc = companyLiveConfigServiceImpl
							.findByCompanyId(WebUtils.getCurrentCompanyId());
					if(clc == null || !clc.getLiveType().equals(4)){
						clc = null;
					}
					Map<String, String> map = new HashMap<String,String>();
					map.put("roomid", lesson.getLiveroomIdGh());
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
				ClassModuleNo mo=classModuleNoServiceImpl.findClassModuleNoById(lesson.getModuleNoId());
				ClassModule md=classModuleServiceImpl.findClassModuleById(mo.getModuleId());
				md.setTotalClassHour(md.getTotalClassHour()-Integer.parseInt(lesson.getLessonHour()));
				classModuleServiceImpl.update(md);
			} catch (Exception e) {
				log.error("更新总课时失败!",e);
				e.printStackTrace();
			}
			if(null!=lesson.getChapterFlag()&&lesson.getChapterFlag().equals(1)){
				//根据课次查询班号
				ClassModuleNo moudleNo=classModuleNoServiceImpl.findClassModuleNoById(lesson.getModuleNoId());
				if(null!=moudleNo.getModuleId()){
					//查询班号下的课次信息，如果课次大于1个就只删除当前这个课次，如果小于等于1，就删除相关联的模块、班号、在售班号。
					List<ClassModuleLesson> lessonsarr=classModuleLessonServiceImpl.findClassModuleLessonByModuleNoId(moudleNo.getId());
					if(lessonsarr.size()>1){
						classModuleLessonServiceImpl.deleteClassModuleLessonById(lesson.getId());
					}else{
						//根据班号查询模块
						ClassModule module=classModuleServiceImpl.findClassModuleById(moudleNo.getModuleId());
						//根据班号和模块号查询class_module_no_on_sale
						ClassModuleNoOnsale onsale=new ClassModuleNoOnsale();
						onsale.setModuleId(moudleNo.getId());
						onsale.setModuleNoId(moudleNo.getModuleId());
						ClassModuleNoOnsale sale=classModuleNoOnsaleServiceImpl.queryClassModuleOnSale(onsale);
						try {
							classModuleLessonServiceImpl.deleteClassModuleLessonById(lesson.getId());
							classModuleServiceImpl.deleteClassModuleById(module.getId());
							if(null!=moudleNo){
								classModuleNoServiceImpl.deleteClassModuleNoById(moudleNo.getId());
							}
							if(null!=sale.getId()){
								classModuleNoOnsaleServiceImpl.deleteClassModuleNoOnsaleById(sale.getId());
							}
						} catch (Exception e) {
							log.error("课次为章的删除操作失败",e);
							e.printStackTrace();
						}
					}
				}
			}else{
				classModuleLessonServiceImpl.deleteClassModuleLessonById(lesson.getId());
			}
		}
		return JsonMsg.SUCCESS;
	}
	/**
	 *
	 * @fileName : ClassModuleController.java
	 * @date : 2015年10月28日 下午12:08:36
	 * @author :　杨延博
	 * @description : 动态评论页面
	 */
	@RequestMapping(value="/dynamics")
	public String dynamicsPage(){
		Subject subject = SecurityUtils.getSubject();
		if (subject.hasRole("机构管理员")||subject.hasRole("分校管理员")) {
			return "operate/dynamics/manageDynamics";
		}
		else {
			return "operate/dynamics/dynamics";
		}

	}
	/**
	 *
	 * @fileName : ClassModuleController.java
	 * @date : 2015年10月28日 下午12:11:27
	 * @author :　杨延博
	 * @description :名师动态的数据
	 */
	@ResponseBody
	@RequestMapping(value="/dynamicsJson")
	public PageFinder<TeacherDynamicsVo> dynamicsJson(int page,HttpServletRequest request){
		int userId=WebUtils.getCurrentUserId(request);
		SysConfigTeacher teacher=sysConfigTeacherServiceImpl.findByUserId(userId);
		TeacherDynamicsVo teacherDynamicsVo =new TeacherDynamicsVo();
		if (teacher!=null) {
			teacherDynamicsVo.setTeacherId(teacher.getId());
		}
		teacherDynamicsVo.setCompanyId(WebUtils.getCurrentCompanyId());
		teacherDynamicsVo.setPageSize(10);
		teacherDynamicsVo.setPage(page);
		PageFinder<TeacherDynamicsVo> pageFinder=teacherPersonalStatusServiceImpl.findStatusByTeacherId(teacherDynamicsVo);
		return ossURlDynamics(pageFinder);
	}
	/**
	 *
	 * @fileName : ClassModuleController.java
	 * @author   : 杨延博
	 * @date     : 2015年11月4日下午3:35:20
	 * @description :机构查询的动态
	 */
	@ResponseBody
	@RequestMapping(value="/manageDynamicsJson")
	public PageFinder<TeacherDynamicsVo> manageDynamicsJson(int page,String teacherIdList,Integer searchType){
		TeacherDynamicsVo teacherDynamicsVo =new TeacherDynamicsVo();
		if (teacherIdList!=null&&!"".equals(teacherIdList)) {
			String[] idList=teacherIdList.split(",");
			List<String> idlist2=new ArrayList<String>();
			for (int i = 0; i < idList.length; i++) {
				idlist2.add(idList[i]);
			}
			teacherDynamicsVo.setTeacherIdList(idlist2);
		}
		teacherDynamicsVo.setCompanyId(WebUtils.getCurrentCompanyId());
		teacherDynamicsVo.setPageSize(10);
		teacherDynamicsVo.setPage(page);
		PageFinder<TeacherDynamicsVo> pageFinder=teacherPersonalStatusServiceImpl.findManageStatusByTeacherId(teacherDynamicsVo,searchType);
		return ossURlDynamics(pageFinder);
	}

	@ResponseBody
	@RequestMapping(value="/manageDynamicsJsonLast5")
	public PageFinder<TeacherDynamicsVo> manageDynamicsJson2(){

		Subject subject = SecurityUtils.getSubject();
		if (subject.hasRole("机构管理员")||subject.hasRole("分校管理员")) {
			TeacherDynamicsVo teacherDynamicsVo =new TeacherDynamicsVo();

			teacherDynamicsVo.setCompanyId(WebUtils.getCurrentCompanyId());
			teacherDynamicsVo.setPageSize(10);
			teacherDynamicsVo.setPage(1);
			PageFinder<TeacherDynamicsVo> pageFinder=teacherPersonalStatusServiceImpl.findManageStatusByTeacherId(teacherDynamicsVo,1);
			return ossURlDynamics(pageFinder);
		}
		else if(subject.hasRole("直播老师")||subject.hasRole("排课老师")){
			Users currentUser = WebUtils.getCurrentUser();
			SysConfigTeacher teacher=sysConfigTeacherServiceImpl.findByUserId(currentUser.getId());
			TeacherDynamicsVo teacherDynamicsVo =new TeacherDynamicsVo();
			if (teacher!=null) {
				teacherDynamicsVo.setTeacherId(teacher.getId());
			}
			teacherDynamicsVo.setCompanyId(WebUtils.getCurrentCompanyId());
			teacherDynamicsVo.setPageSize(10);
			teacherDynamicsVo.setPage(1);
			PageFinder<TeacherDynamicsVo> pageFinder=teacherPersonalStatusServiceImpl.findStatusByTeacherId(teacherDynamicsVo);
			return ossURlDynamics(pageFinder);
		}else{
			TeacherDynamicsVo teacherDynamicsVo =new TeacherDynamicsVo();

			teacherDynamicsVo.setCompanyId(WebUtils.getCurrentCompanyId());
			teacherDynamicsVo.setPageSize(10);
			teacherDynamicsVo.setPage(1);
			PageFinder<TeacherDynamicsVo> pageFinder=teacherPersonalStatusServiceImpl.findManageStatusByTeacherId(teacherDynamicsVo,1);
			return ossURlDynamics(pageFinder);
		}

	}


	/**
	 *
	 * @fileName : ClassModuleController.java
	 * @author   : 杨延博
	 * @date     : 2015年10月30日下午7:44:10
	 * @description :动态的评论数据
	 */
	@ResponseBody
	@RequestMapping(value="dynamicsReplayJson")
	public PageFinder<TeacherDynamicsReplayVo> dynamicsReplayJson (Integer id,Integer page){
		 TeacherDynamicsReplayVo vo=new TeacherDynamicsReplayVo();
		 vo.setStatusId(id);
		 vo.setPage(page);
		 vo.setPageSize(10);
		 PageFinder<TeacherDynamicsReplayVo> pageFinder
		 =sysTeacherPersonalStatusReplayImpl.findSysTeacherPersonalStatusReplayByStatusId(vo);
		 //判断评论是否是老师自己发的
		 Users users=WebUtils.getCurrentUser();
		 SysConfigTeacher teacher=sysConfigTeacherServiceImpl.findByUserId(users.getId());
        int teacherId=0;
        if (teacher!=null){
            teacherId =teacher.getId();
         }

		 List<TeacherDynamicsReplayVo> list=pageFinder.getData();
		 for (TeacherDynamicsReplayVo teacherDynamicsReplayVo : list) {
			 teacherDynamicsReplayVo.setIsSelf(0);
			if (teacherDynamicsReplayVo.getUserType()==1) {
				if (teacherId==teacherDynamicsReplayVo.getUserId()) {
					teacherDynamicsReplayVo.setIsSelf(1);
				}
			}
		}
		 return ossURlDynamicsReplay(pageFinder);
	}
	/**
	 *
	 * @fileName : ClassModuleController.java
	 * @author   : 杨延博
	 * @date     : 2015年11月5日下午4:05:21
	 * @description :机构查询动态的评论
	 */
	@ResponseBody
	@RequestMapping(value="manageDynamicsReplayJson")
	public PageFinder<TeacherDynamicsReplayVo> manageDynamicsReplayJson (Integer id,Integer page){
		 TeacherDynamicsReplayVo vo=new TeacherDynamicsReplayVo();
		 vo.setStatusId(id);
		 vo.setPage(page);
		 vo.setPageSize(10);
		 PageFinder<TeacherDynamicsReplayVo> pageFinder
		 =sysTeacherPersonalStatusReplayImpl.findSysTeacherPersonalStatusReplayByStatusId(vo);
		 return ossURlDynamicsReplay(pageFinder);
	}


	/**
	 *
	 * @fileName : ClassModuleController.java
	 * @author   : 杨延博
	 * @date     : 2015年11月2日下午8:21:09
	 * @description :修改置顶选项
	 */
	@ResponseBody
	@RequestMapping(value="dynamicsSetTop")
	public String dynamicsSetTop (Integer id,Integer topFlag){
		SysTeacherPersonalStatus teacherDynamics=new SysTeacherPersonalStatus();
		if (topFlag==0) {
			teacherDynamics.setTopFlag(1);
		}else if (topFlag==1) {
			teacherDynamics.setTopFlag(0);
		}
		teacherDynamics.setId(id);
		teacherPersonalStatusServiceImpl.update(teacherDynamics);
		return "success";
	}
	/**
	 *
	 * @fileName : ClassModuleController.java
	 * @author   : 杨延博
	 * @date     : 2015年11月3日上午10:15:39
	 * @description :删除动态
	 */
	@ResponseBody
	@RequestMapping(value="deleteDynamics")
	public String deleteDynamics (Integer id){
		SysTeacherPersonalStatus teacherDynamics=new SysTeacherPersonalStatus();
		teacherDynamics.setDelFlag(1);
		teacherDynamics.setId(id);
		teacherPersonalStatusServiceImpl.update(teacherDynamics);
		SysTeacherPersonalStatusReplay  sysTeacherPersonalStatusReplay=new SysTeacherPersonalStatusReplay();
		sysTeacherPersonalStatusReplay.setStatusId(id);
		sysTeacherPersonalStatusReplay.setDelFlag(1);
		sysTeacherPersonalStatusReplayImpl.updateByStatusId(sysTeacherPersonalStatusReplay);
		return "success";
	}
	@ResponseBody
	@RequestMapping(value="deleteDynamicsReplay")
	public String deleteDynamicsReplay (Integer id){
		SysTeacherPersonalStatusReplay sysTeacherPersonalStatusReplay=new SysTeacherPersonalStatusReplay();
		sysTeacherPersonalStatusReplay.setId(id);
		sysTeacherPersonalStatusReplay.setDelFlag(1);
		sysTeacherPersonalStatusReplayImpl.update(sysTeacherPersonalStatusReplay);
		return "success";
	}
	/**
	 *
	 * @fileName : ClassModuleController.java
	 * @author   : 杨延博
	 * @date     : 2015年11月3日下午3:57:19
	 * @description :添加动态的回复
	 */
	@ResponseBody
	@RequestMapping(value="insertDynamicsReplay")
	public PageFinder<TeacherDynamicsReplayVo> insertDynamicsReplay (SysTeacherPersonalStatusReplay sysTeacherPersonalStatusReplay){
		sysTeacherPersonalStatusReplay.setDelFlag(0);
		sysTeacherPersonalStatusReplay.setPublishTime(new Date());
		sysTeacherPersonalStatusReplay.setReplayType(1);
		sysTeacherPersonalStatusReplay.setUserType(1);
		Users users=WebUtils.getCurrentUser();
		SysConfigTeacher teacher=sysConfigTeacherServiceImpl.findByUserId(users.getId());
		if (teacher!=null) {
			sysTeacherPersonalStatusReplay.setUserId(teacher.getId());
		}
		sysTeacherPersonalStatusReplayImpl.insert(sysTeacherPersonalStatusReplay);
		TeacherDynamicsReplayVo vo=new TeacherDynamicsReplayVo();
		vo.setStatusId(sysTeacherPersonalStatusReplay.getStatusId());
		vo.setId(sysTeacherPersonalStatusReplay.getId());
		return ossURlDynamicsReplay(sysTeacherPersonalStatusReplayImpl.findSysTeacherPersonalStatusReplayByStatusId(vo));
	}
	/**
	 *
	 * @fileName : ClassModuleController.java
	 * @author   : 杨延博
	 * @date     : 2015年11月3日下午9:30:19
	 * @description :上传动态图片
	 */
	@ResponseBody
	@RequestMapping(value="/UploadCycles",method=RequestMethod.POST)
	public String UploadCycles(MultipartRequest multiPartRquest,HttpServletRequest req){
//		Subject subject = SecurityUtils.getSubject();
		String realPath=null;
		String picPath=null;
		MultipartFile	multipartFile = multiPartRquest.getFile("imgData");
//		subject.getSession().setAttribute("imgData", multipartFile);
		String name=multipartFile.getOriginalFilename();
		if(name!=null&&!"".equals(name)){
			try {
				realPath = FileUtil.upload(multipartFile, "teacher", WebUtils.getCurrentCompanyId()+"");
			} catch (Exception e) {
				log.error("文件上传失败",e);
				e.printStackTrace();
			}
		}
		picPath="http://"+propertiesUtil.getProjectImageUrl()+"/"+realPath;
		return "{\"url\":\""+picPath+"\",\"picPath\":\""+realPath+"\"}";
	}
	/**
	 *
	 * @fileName : ClassModuleController.java
	 * @author   : 杨延博
	 * @date     : 2015年11月4日上午10:58:58
	 * @description : 添加动态
	 */
	@ResponseBody
	@RequestMapping(value="insertDynamics")
	public String insertDynamicsReplay (String content,String list){
		//添加动态
		SysTeacherPersonalStatus sysTeacherPersonalStatus=new SysTeacherPersonalStatus();
		sysTeacherPersonalStatus.setPublishTime(new Date());
		Users users=WebUtils.getCurrentUser();
		SysConfigTeacher teacher=sysConfigTeacherServiceImpl.findByUserId(users.getId());
		if (teacher!=null) {
			sysTeacherPersonalStatus.setTeacherId(teacher.getId());
		}
		sysTeacherPersonalStatus.setCompanyId(WebUtils.getCurrentCompanyId());
		sysTeacherPersonalStatus.setSchoolId(WebUtils.getCurrentSchoolId());
		sysTeacherPersonalStatus.setDelFlag(0);
		sysTeacherPersonalStatus.setTopFlag(0);
		sysTeacherPersonalStatus.setContent(content);
		teacherPersonalStatusServiceImpl.insert(sysTeacherPersonalStatus);
		Integer id=sysTeacherPersonalStatus.getId();
		String[] picURLList;
		if (list!=null&&!"".equals(list)) {
			picURLList=list.split(",");
			List<SysTeacherPersonalStatusPic> picList=new ArrayList<SysTeacherPersonalStatusPic>();
			for (String string : picURLList) {
				SysTeacherPersonalStatusPic pic=new SysTeacherPersonalStatusPic();
				pic.setUrl(string);
				pic.setStatusId(id);
				pic.setCreateTime(new Date());
				pic.setCreator(teacher.getId());
				picList.add(pic);
			}
			sysTeacherPersonalStatusPicImpl.batchInsert(picList);
		}
		return "success";
	}

	/**
	 *
	 * @fileName : ClassModuleController.java
	 * @date : 2015年10月26日 下午3:38:53
	 * @author :　杨延博
	 * @description : 跳转到评论页面
	 */
	@RequestMapping(value="/comment")
	public String commentPage(){
		Subject subject = SecurityUtils.getSubject();
		if (subject.hasRole("机构管理员")||subject.hasRole("分校管理员")) {
			return "operate/comment/ManageComment";
		}
		else {
			return "operate/comment/comment";
		}

	}
	/**
	 *
	 * @fileName : ClassModuleController.java
	 * @date : 2015年10月26日 下午7:23:51
	 * @author :　杨延博
	 * @description : 获取comment信息
	 */
	@ResponseBody
	@RequestMapping(value="/commentJson")
	public PageFinder<TeacherCommentVo> commentJson(int page){
		Users users=WebUtils.getCurrentUser();
		SysConfigTeacher teacher=sysConfigTeacherServiceImpl.findByUserId(users.getId());
		TeacherCommentVo teacherCommentVo=new TeacherCommentVo();
		if (teacher!=null) {
			teacherCommentVo.setTeacherId(teacher.getId());
		}
		teacherCommentVo.setCompanyId(WebUtils.getCurrentCompanyId());
		teacherCommentVo.setPageSize(10);
		teacherCommentVo.setPage(page);
		PageFinder<TeacherCommentVo> pageFinder=this.ossURl(videoCourseCommentServiceImpl.findVideoCourseCommentByTeacherId(teacherCommentVo));
		return pageFinder;
	}
	/**
	 *
	 * @fileName : ClassModuleController.java
	 * @author   : 杨延博
	 * @date     : 2015年10月29日下午3:44:54
	 * @description :机构查询老师评论
	 */
	@ResponseBody
	@RequestMapping(value="/manageCommentJson")
	public PageFinder<TeacherCommentVo> manageCommentJson(Integer page,Integer teacherId,Integer id){
		TeacherCommentVo teacherCommentVo=new TeacherCommentVo();
		if (teacherId!=null) {
			teacherCommentVo.setTeacherId(teacherId);
		}
		teacherCommentVo.setId(id);
		teacherCommentVo.setCompanyId(WebUtils.getCurrentCompanyId());
		teacherCommentVo.setPageSize(10);
		teacherCommentVo.setPage(page);
		PageFinder<TeacherCommentVo> pageFinder=this.ossURl(videoCourseCommentServiceImpl.findVideoCourseCommentByTeacherId(teacherCommentVo));
		return pageFinder;

	}

	/**
	 *
	 * @fileName : ClassModuleController.java
	 * @author   : 杨延博
	 * @date     : 2015年10月29日下午3:44:54
	 * @description :机构查询老师评论
	 */
	@ResponseBody
	@RequestMapping(value="/manageCommentJsonLast5")
	public PageFinder<TeacherCommentVo> manageCommentJson2(){
		Users users=WebUtils.getCurrentUser();
		Subject subject = SecurityUtils.getSubject();
		TeacherCommentVo teacherCommentVo=new TeacherCommentVo();
		teacherCommentVo.setCompanyId(WebUtils.getCurrentCompanyId());
		teacherCommentVo.setPageSize(5);
		teacherCommentVo.setPage(1);
		if(!subject.hasRole("机构管理员")){
			teacherCommentVo.setSchoolId(WebUtils.getCurrentSchoolId());
		}
		if(!subject.hasRole("机构管理员") && (subject.hasRole("直播老师")||subject.hasRole("排课老师"))){
			SysConfigTeacher teacher=sysConfigTeacherServiceImpl.findByUserId(users.getId());
			if (teacher!=null) {
				teacherCommentVo.setTeacherId(teacher.getId());
			}
		}
		PageFinder<TeacherCommentVo> pageFinder=this.ossURl(videoCourseCommentServiceImpl.findVideoCourseCommentByTeacherId(teacherCommentVo));
		return pageFinder;
	}

	/**
	 *
	 * @fileName : ClassModuleController.java
	 * @author   : 杨延博
	 * @date     : 2015年10月29日下午5:39:18
	 * @description : 添加OSS地址
	 */
	public PageFinder<TeacherCommentVo> ossURl(PageFinder<TeacherCommentVo> pageFinder){
		List<TeacherCommentVo> list=pageFinder.getData();
		for (TeacherCommentVo teacherCommentVo : list) {
			if (null!=teacherCommentVo && teacherCommentVo.getUserImage()!=null && !teacherCommentVo.getUserImage().contains("http://") ) {
				String url="http://"+propertiesUtil.getProjectImageUrl()+"/"+teacherCommentVo.getUserImage();
				teacherCommentVo.setUserImage(url);
			}
		}
		pageFinder.setData(list);
		return pageFinder;
	}
	/**
	 *
	 * @fileName : ClassModuleController.java
	 * @author   : 杨延博
	 * @date     : 2015年10月29日下午10:06:11
	 * @description :添加动态图片OSS地址
	 */
	public PageFinder<TeacherDynamicsVo> ossURlDynamics(PageFinder<TeacherDynamicsVo> pageFinder){
		List<TeacherDynamicsVo> list=pageFinder.getData();
		for (TeacherDynamicsVo teacherDynamicsVo : list) {
			//给老师的头像添加url
			String commonURL="http://"+propertiesUtil.getProjectImageUrl()+"/";
			if (!(teacherDynamicsVo.getHeadpicUrl()==null ||"".equals(teacherDynamicsVo.getHeadpicUrl()))) {
				String url=commonURL+teacherDynamicsVo.getHeadpicUrl();
				teacherDynamicsVo.setHeadpicUrl(url);
			}
			List<String> imgList=teacherDynamicsVo.getPicList();
			List<String> imgURLList=new ArrayList<String>();
 			if (imgList!=null) {
				for (String string : imgList) {
					imgURLList.add(commonURL+string);
				}
				teacherDynamicsVo.setPicList(imgURLList);
			}

			//给动态的图片添加url

		}
		pageFinder.setData(list);
		return pageFinder;
	}
	/**
	 *
	 * @fileName : ClassModuleController.java
	 * @author   : 杨延博
	 * @date     : 2015年11月3日下午4:50:20
	 * @description :给动态的评论加图片URL
	 */
	public PageFinder<TeacherDynamicsReplayVo> ossURlDynamicsReplay(PageFinder<TeacherDynamicsReplayVo> pageFinder){
		//给老师的头像添加url
		String commonURL="http://"+propertiesUtil.getProjectImageUrl()+"/";
		List<TeacherDynamicsReplayVo> list=pageFinder.getData();
		for (TeacherDynamicsReplayVo teacherDynamicsReplayVo : list) {
			if (!(teacherDynamicsReplayVo.getUserPic()==null||"".equals(teacherDynamicsReplayVo.getUserPic()))) {
				String url = "";
				if(teacherDynamicsReplayVo.getUserPic().startsWith("http"))
					url = teacherDynamicsReplayVo.getUserPic();
				else
					url=commonURL+teacherDynamicsReplayVo.getUserPic();
				teacherDynamicsReplayVo.setUserPic(url);
			}
		}
		pageFinder.setData(list);
		return pageFinder;
	}
	/**
	 *
	 * @fileName : ClassModuleController.java
	 * @date : 2015年10月27日 下午6:12:52
	 * @author :　杨延博
	 * @description : 删除评论
	 */
	@ResponseBody
	@RequestMapping(value="/deleteComment")
	public String deleteComment(Integer id){
		VideoCourseComment vcc=new VideoCourseComment();
		vcc.setId(id);
		vcc.setDelFlag(1);
		videoCourseCommentServiceImpl.update(vcc);
		return "success";
	}

	/**
	 *
	 * @fileName : ClassModuleController.java
	 * @date : 2015年10月27日 下午6:22:42
	 * @author :　杨延博
	 * @description :查询老师名字
	 */
	@ResponseBody
	@RequestMapping(value="/teacherJson")
	public List<SysConfigTeacher> teacherJson(){
		SysConfigTeacher sysConfigTeahcer=new SysConfigTeacher();
		sysConfigTeahcer.setCompanyId(WebUtils.getCurrentCompanyId());
		sysConfigTeahcer.setSchoolId(WebUtils.getCurrentSchoolId());
		return sysConfigTeacherServiceImpl.findSysConfigTeacherByCompany(sysConfigTeahcer);
	}
	/**
	 *
	 * @fileName : ClassModuleController.java
	 * @date : 2015年10月27日 下午7:37:24
	 * @author :　杨延博
	 * @description :关键字搜索教师
	 */
	@ResponseBody
	@RequestMapping(value="/searchTeacherJson")
	public List<SysConfigTeacher> searchTeacherJson(String search){
		Map<String, String> map=new HashMap<String, String>();
		map.put("companyId", WebUtils.getCurrentCompanyId().toString());
		map.put("schoolId", WebUtils.getCurrentSchoolId().toString());
		map.put("teacherName", search);
		return sysConfigTeacherServiceImpl.findSysConfigTeacherByName(map);
	}
	/**
	 *
	 * Class Name: ClassModuleController.java
	 * @Description:根据课次id查询课次详情
	 * @author zhang.zx
	 * @date 2015年9月12日 下午5:47:49
	 * @modifier
	 * @modify-date 2015年9月12日 下午5:47:49
	 * @version 1.0
	 * @param lessonId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryLessonDetail")
	public ClassModuleLesson queryClassModuleLessDetail(Integer lessonId){

		return classModuleLessonServiceImpl.findClassModuleLessonById(lessonId);
	}

	//修改课次
	@ResponseBody
	@RequestMapping(value="/updateCourseLesson",method=RequestMethod.POST)
	public JSONObject updateCourseLesson(HttpServletRequest request,String moduleName,Integer classtypeId,
			String teachMethod,Integer totalHours,String lessonDate,String lessonTimeStart,String lessonTimeEnd,String lessonHour,
			String teachers,String teachersName,String assistants,String assistantsName,String lessonName,Integer moduleId,Integer classmoduleId,
			Integer lessonId,String classroomName,Integer classroomId,String liveClassType
			,Integer barrage,Integer modetype) throws Exception{
		JSONObject json = new JSONObject();
		Integer companyId = WebUtils.getCurrentCompanyId();

		//修改课次
		ClassModuleLesson lesson=new ClassModuleLesson();
		lesson.setId(lessonId);
		lesson.setLessonName(lessonName);
		lesson.setLessonDate(new Date(lessonDate));
		String newDate=lessonDate.replaceAll("/", "-");
		String week=DatetimeUtil.getWeekByDateStr(newDate);
		lesson.setWeekType(week);
		lesson.setLessonTimeStart(lessonTimeStart);
		lesson.setLessonTimeEnd(lessonTimeEnd);
		lesson.setTeachers(teachers);
		lesson.setTeachersName(teachersName);
		lesson.setAssistants(assistants);
		lesson.setAssistantsName(assistantsName);
		lesson.setLessonHour(lessonHour);
		lesson.setLiveClassType(liveClassType);
		if("TEACH_METHOD_FACE".equals(teachMethod)){
			lesson.setClassroom(classroomName);
			lesson.setClassroomId(classroomId);
		}
		lesson.setBarrage(barrage != null ? barrage : 0);
		lesson.setModetype(modetype != null ? modetype : 3);
		classModuleLessonServiceImpl.update(lesson);
		//更新直播教室
		if("TEACH_METHOD_LIVE".equals(teachMethod)){
			ClassModuleLesson lec=classModuleLessonServiceImpl.findClassModuleLessonById(lesson.getId());
			//如果是新增的话，需要判断当前公司的直播服务提供商是哪家
			CompanyMemberService liveProvider = companyMemberServiceServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
			//判断当前公司是E课堂还是展示互动
			if(liveProvider == null
					|| liveProvider.getLiveServiceProvider() == null
					|| liveProvider.getLiveServiceProvider().equals("")
					|| liveProvider.getLiveServiceProvider().equals("gh")){
				try {
					String liveRoom = eketangLiveRoomServiceImpl.createLiveRoom(lec, companyId);
					LiveRoomInfo bean = (LiveRoomInfo) net.sf.json.JSONObject.toBean(net.sf.json.JSONObject.fromObject(liveRoom),LiveRoomInfo.class);
					if (bean != null && ("ok").equals(bean.getMsg())) {
						lec.setLiveRoom(bean.getId());
						lec.setLiveroomIdGh(bean.getLiveRoomId());
						if(liveProvider != null && liveProvider.getLiveServiceTemplate() != null && liveProvider.getLiveServiceTemplate().equals(Constant.EKETANG_TEMPALATE_SOOONER)){
							lec.setStudentUrlGh(bean.getStudentUrl());
						}else{
							lec.setStudentUrlGh(bean.getStudentUrl().replace("soooner", "taobao"));
						}
						lec.setTeacherUrlGh(bean.getTeacherUrl());
						lec.setAssistantUrlGh(bean.getAssistantUrl());
						lec.setReplayUrlGh(bean.getReplayUrl());
						lec.setLiveCompanyType("gh");
					}
					json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
				} catch (Exception e) {
					e.printStackTrace();
					log.info("创建展示互动直播课程失败");
					json.put(JsonMsg.MSG, "创建直播课程失败");
					return json;
				}
			}else if(liveProvider.getLiveServiceProvider().equals("zs")){
				try {
					//展示互动的账号/密码
					String zsLoginName = properties.getZsLoginName();
					String zsAddress = properties.getZsAddress();
					String returnLiveRoom=null;
					if(lec.getLiveroomIdGh() != null && lec.getLiveroomIdGh().length() > 0){
						returnLiveRoom= genseeLiveRoomServiceImpl.updateLiveRoom(lec,companyId);
					}else{
						returnLiveRoom= genseeLiveRoomServiceImpl.createLiveRoom(lec,companyId);
					}
					ZsReturnInfo bean = null;
					try {
						bean = (ZsReturnInfo) net.sf.json.JSONObject.toBean(net.sf.json.JSONObject.fromObject(returnLiveRoom),ZsReturnInfo.class);
					} catch (Exception e) {
						e.printStackTrace();
						log.info("创建直播教室出现异常，当前bean信息为："+bean+", 教室的ID为："+lec.getId()+", 教室的名称："+lec.getLessonName());
						json.put(JsonMsg.MSG, "创建直播课程失败");
						return json;
					}
					lec.setLiveroomIdGh(bean.getId());
					lec.setStudentUrlGh(bean.getStudentJoinUrl());
					lec.setTeacherUrlGh(bean.getTeacherJoinUrl());
					lec.setAssistantUrlGh(bean.getTeacherJoinUrl());
					lec.setLiveCompanyType("zs");
					json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
				} catch (Exception e) {
					e.printStackTrace();
					log.info("创建展示互动直播课程失败");
					json.put(JsonMsg.MSG, "创建直播课程失败");
					return json;
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
					SysConfigTeacher teacher = sysConfigTeacherServiceImpl.findSysConfigTeacherById(Integer.parseInt(lec.getTeachers()));
					//Map<Object, Object> options = new HashMap<Object,Object>();
					Map<Object, Object> param = new HashMap<Object,Object>();
					param.put("course_name", lec.getLessonName());
					param.put("account", teacher.getId());
					param.put("start_time", new SimpleDateFormat("yyyy-MM-dd")
						.format(lec.getLessonDate()) + " " + lec.getLessonTimeStart()
						+ ":00");
					param.put("end_time",  new SimpleDateFormat("yyyy-MM-dd")
						.format(lec.getLessonDate()) + " " + lec.getLessonTimeEnd()
						+ ":00");
					param.put("nickname", teacher.getName());
					param.put("accountIntro", teacher.getResume());

					Map<Object, Object> options = new HashMap<Object,Object>();
					options.put("departmentId", companyId);
					options.put("barrage", lec.getBarrage());
					options.put("modetype", lec.getModetype());
					param.put("options", options);

					if(lec.getLiveroomIdGh() != null && lec.getLiveroomIdGh().length() > 0){
						param.put("course_id", lec.getLiveroomIdGh());
						res = TalkfunUtils.getRsult(param, "course.update",openId,openToken);
					}else{
						res = TalkfunUtils.getRsult(param, "course.add",openId,openToken);
					}
					JSONObject rjson = JSONObject.parseObject(res);
					if(rjson.getInteger("code").equals(0)){
						json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
					}else{
						if(lec.getLiveroomIdGh() == null){
							classModuleLessonServiceImpl
								.deleteClassModuleLessonById(lec.getId());
						}
						json.put(JsonMsg.MSG, rjson.getString("msg"));
						return json;
					}
					if(rjson.get("data") != null && !rjson.get("data").equals("")){
						TalkfunEntityVo tf = JSONObject.parseObject(
								rjson.getJSONObject("data").toJSONString()
								, TalkfunEntityVo.class);
						lec.setLiveroomIdGh(tf.getCourse_id());
						lec.setLiveCompanyType("ht");
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					log.info("创建欢拓直播失败");
					json.put(JsonMsg.MSG, "创建直播课程失败");
					return json;
				}
			}else{
				try {
					String returnLiveRoom=null;
					if(lec.getLiveroomIdGh() != null && lec.getLiveroomIdGh().length() > 0){
						returnLiveRoom= CCLiveRoomServiceImpl.updateLiveRoom(lec,companyId);
						if("OK".equals(returnLiveRoom)){
							json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
						}else{
							json.put(JsonMsg.MSG, "修改直播课程失败");
							return json;
						}
					}else{
						returnLiveRoom= CCLiveRoomServiceImpl.createLiveRoom(lec,companyId);
						if(returnLiveRoom != null){
							JSONObject rj = JSONObject.parseObject(returnLiveRoom);
							lec.setLiveroomIdGh(rj.getString("roomid"));
							lec.setStudentUrlGh(rj.getString("s"));
							lec.setTeacherUrlGh(rj.getString("t"));
							lec.setAssistantUrlGh(rj.getString("a"));
							lec.setLiveCompanyType("cc");
							json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
						}else{
							json.put(JsonMsg.MSG, "创建直播课程失败");
							return json;
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					log.debug("创建cc直播课失败," + e.getMessage());
					json.put(JsonMsg.MSG, "创建直播课程失败");
					return json;
				}
			}
			classModuleLessonServiceImpl.update(lec);
		}
		//更新班号表信息
		List<ClassModuleNo> arr=classModuleNoServiceImpl.queryClassModuleNoById(moduleId);
		if(!arr.isEmpty()&&arr.size()>0){
			Integer count=0;
			ClassModuleNo moduleNo=arr.get(0);
			List<ClassModuleLesson> lessons=classModuleLessonServiceImpl.findClassModuleLessonByModuleNoId(moduleNo.getId());
			for(ClassModuleLesson le:lessons){
				count+=Integer.parseInt(le.getLessonHour());
			}
			moduleNo.setTotalHours(count);
			classModuleNoServiceImpl.update(moduleNo);
			ClassModule module=new ClassModule();
			module.setId(moduleId);
			module.setTotalClassHour(count);
			classModuleServiceImpl.update(module);
		}
		return json;
	}


	/**
	 *
	 * Class Name: ClassModuleController.java
	 * @Description: 模块排序
	 * @author zhang.zx
	 * @date 2015年9月11日 下午3:44:26
	 * @modifier
	 * @modify-date 2015年9月11日 下午3:44:26
	 * @version 1.0
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/sortModule", method = RequestMethod.POST)
	public List<ClassModule> sortModule(HttpServletRequest request){
		List<ClassModule> modules=JSONArray.parseArray(request.getParameter("list"), ClassModule.class);
		List<ClassModule> sortmodules=new ArrayList<ClassModule>();
		for(ClassModule module :  modules){
			classModuleServiceImpl.update(module);
			sortmodules.add(classModuleServiceImpl.findClassModuleById(module.getId()));
		}
		return sortmodules;
	}

	public String liveRoom(ClassModuleLesson cml) {
		//查询 光慧直播 id
		String customer = properties.getCustomer();
		long timestamp = System.currentTimeMillis();
		String secretKey = properties.getSecretKey();

		String str = MD5.getMD5ofStr(customer+timestamp+secretKey);
		str = str.substring(0,10)+str.substring(str.length() - 10);

		String url = null;

		//如果该课次还没有创建直播教室，则走创建直播教室接口，否则修改
		if(cml.getLiveroomIdGh() != null && cml.getLiveroomIdGh().length() > 0){
			url = properties.getInterfaceAddress() + LiveRoomConstant.UPDATE_LIVEROOM;
		}else{
			url = properties.getInterfaceAddress() + LiveRoomConstant.CREATELIVEROOM;
		}


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
		if(teacher != null){
			tv = new TeacherVo(cml.getTeachers(),teacher.getName(),teacher.getName(),teacher.getHeadpicUrl(),"",teacher.getResume());
			tvs.add(tv);
		}
		if(assistant != null){
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

		String detail;
		try {
			detail = HttpPostRequest.post(url, param);
			log.info("调用E课堂创建直播教室接口,返回信息如下：" + detail + ", 删除的课堂名称为："+cml.getLessonName()+", 课堂Id为："+cml.getId());
			return detail;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("调用E课堂创建直播教室接口失败,课次Id为：" + cml.getId() + ", 删除的课堂名称为："+cml.getLessonName());
			return null;
		}
	}

	/**
	 * @Description: 创建展示互动直播课
	 * @author zx.wang
	 * @date 2015-11-3 下午2:27:41
	 * @version 2.0
	 * @return
	 */
	public String createGenseeLiveRoom(HttpServletRequest request){

		return null;
	}

	/**
	 *
	 * Class Name: ClassModuleController.java
	 * @Description: 查询公司使用服务
	 * @author zhang.zx
	 * @date 2015年11月27日 下午1:19:41
	 * @modifier
	 * @modify-date 2015年11月27日 下午1:19:41
	 * @version 1.0
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryUseService",method=RequestMethod.POST)
	public String queryCompanyUseService(){
		//如果是新增的话，需要判断当前公司的直播服务提供商是哪家
		CompanyMemberService liveProvider = companyMemberServiceServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
		//判断当前公司是E课堂还是展示互动
		if(liveProvider != null && liveProvider.getLiveServiceProvider() != null
				&& !liveProvider.getLiveServiceProvider().equals("")){
			return liveProvider.getLiveServiceProvider();
		}else{
			return "gh";
		}
	}

	/**
	 *
	 * Class Name: ClassModuleController.java
	 *
	 * @Description: 展视互动回调
	 * @author 周文斌
	 * @date 2015-11-14 下午5:14:02
	 * @version 1.0
	 * @param request
	 * @param ClassNo
	 * @param Operator
	 * @param Action
	 * @param Affected
	 * @param totalusernum
	 */
	@RequestMapping("/stuCount")
	public String zshd(String ClassNo,
			String Operator, String Action, String Affected,
			String totalusernum) {

		log.info("zs：展视互动回调");
		log.info("zs：回调值");
		log.info("zs：ClassNo:" + ClassNo);
		log.info("zs：Operator:" + Operator);
		log.info("zs：Action:" + Action);
		log.info("zs：Affected:" + Affected);
		log.info("zs：totalusernum:" + totalusernum);
		log.info("redis：查询redis");
		try {
			//根据班号 查询公司id

			ClassModule cm = classModuleServiceImpl.findCompanyIdByClassNo(ClassNo);

			Map<String, String> param = JedisUtil
					.hgetAll("zs_live_map_no_" + cm.getCompanyId());
			log.info("redis：" + param);
			if (Action.equals("105")) {
				log.info("zs：移除教室");
				param.remove(ClassNo);
				log.info("zs：put redis");
				JedisUtil.hmset("zs_live_map_no_" + cm.getCompanyId(), param);
				log.info("zs：" + param);
			} else {
				if (param != null && param.size() > 0) {
					param.put(ClassNo, totalusernum);
					log.info("zs：put redis");
					JedisUtil.hmset("zs_live_map_no_" + cm.getCompanyId(), param);
					log.info("zs：" + param);
				} else {
					param = new HashMap<String, String>();
					param.put(ClassNo, totalusernum);
					log.info("zs：put redis");
					JedisUtil.hmset("zs_live_map_no_" + cm.getCompanyId(), param);
					log.info("zs：" + param);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("zs：出错了：" + e.getMessage());
		}finally{
			return "operate/live/nulls";
		}
	}

	/**
	 *
	 * Class Name: ClassModuleController.java
	 * @Description: CC直播 回看 回调
	 * @author 周文斌
	 * @date 2017-2-23 下午2:44:25
	 * @modify	2017-2-23 下午2:44:25
	 * @version
	 */
	@ResponseBody
	@RequestMapping("/CCLiveReplay")
	public JSONObject CCLiveReplay(String userId,String roomId,String liveId,String recordStatus
			,String recordVideoId,String replayUrl,String startTime,String endTime){
		log.info("cc直播回调，状态:" + recordStatus + ",直播间id:"
			+ roomId + ",地址：" + replayUrl);
		JSONObject json = new JSONObject();
		try {
			if(!recordStatus.equals("10")){
				json.put("result", "NoOver");
				return json;
			}
			ClassModule cm = classModuleServiceImpl.findCompanyIdByClassNo(roomId);
			log.info("cc直播回调，cm:" + cm);
			if(cm != null){
				CompanyLiveCoursewareZs clcz = new CompanyLiveCoursewareZs();
				clcz.setCoursewareId(liveId);
				clcz.setCoursewareNumber(recordVideoId);
				clcz.setRoomId(roomId);
				clcz.setUrl(replayUrl);
				clcz.setModuleLessonId(cm.getId());
				clcz.setSchoolId(cm.getSchoolId());
				clcz.setCompanyId(cm.getCompanyId());
				clcz.setAddDate(new Date());
				if("live".equals(cm.getLiveType())){
					ClassModuleLesson cml = classModuleLessonServiceImpl
							.findClassModuleLessonById(cm.getId());
					if(cml.getReplayUrlGh() == null ||
							cml.getReplayUrlGh().equals("")){
						cml.setReplayUrlGh(replayUrl);
						classModuleLessonServiceImpl.update(cml);
					}
				} else {
					LiveOpenCourse loc = liveOpenCourseServiceImpl
							.findLiveOpenCourseById(cm.getId());
					if(loc.getReplayUrlGh() == null||
							loc.getReplayUrlGh().equals("")){
						loc.setReplayUrlGh(replayUrl);
						liveOpenCourseServiceImpl.update(loc);
					}
				}

				companyLiveCoursewareZsImpl.insert(clcz);
				json.put("result", "ok");
			}else{
				json.put("result", "NoSearch");
			}
			return json;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("cc回看回调错误，"+e.getMessage());
			json.put("resutl", "error");
			return json;
		}
	}

	/**
	 *
	 * Class Name: ClassModuleController.java
	 * @Description: 下载聊天
	 * @author 周文斌
	 * @date 2016-1-12 下午6:35:31
	 * @version 1.0
	 * @param request
	 * @param liveroom
	 * @param types
	 * @return
	 */
	@RequestMapping("/downloadChat/{liveroom}/{types}")
	public void downloadChat(HttpServletRequest request,HttpServletResponse response,@PathVariable Integer liveroom,@PathVariable Integer types){

		log.info("查询当前用户是否配置了单独账号");
		CompanyLiveConfig clc = companyLiveConfigServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());

		try {
			response.setContentType("application/x-msdownload");
			byte[] b = new byte[1024*1024];
			int ch = 0;
			OutputStream out = response.getOutputStream();
			InputStream in = null;
			if(types.equals(1)){
				ClassModuleLesson cml = classModuleLessonServiceImpl.findClassModuleLessonById(liveroom);
				response.setHeader("content-disposition", "attachment;fileName="+URLEncoder.encode(cml.getLessonName())+".txt");
				in = getInputstreamByClassModuleLesson(cml,clc);
			}else{
				LiveOpenCourse cml = liveOpenCourseServiceImpl.findLiveOpenCourseById(liveroom);
				response.setHeader("content-disposition", "attachment;fileName="+URLEncoder.encode(cml.getOpenCourseName())+".txt");
				in = getInputstreamByLiveOpenCourse(cml,clc);
			}
			while((ch=in.read(b)) != -1){
				out.write(b, 0, ch);
			}
			in.close();
			out.flush();
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("download//下载聊天错误：" + e.getMessage());
		}
	}

	/**
	 * @Description: 获取E课堂的聊天记录
	 * @author zx.wang
	 * @date 2016-1-15 下午2:52:34
	 * @version 2.0
	 * @return
	 */
	private InputStream getEKeTangChatStreamByLesson(String liveRoomId,CompanyLiveConfig clc,String customer) throws ClientProtocolException, IOException{
		//模拟登陆
        String url = propertiesUtil.getEketangLogin()+"/login";

        final String apiUser = customer;
        String apiKey = "";
        if(customer.equals("yxkj")){
        	apiKey = customer;
        }else{
        	apiKey = "123456";
        }


        //模拟下载聊天记录
        String download = propertiesUtil.getEketangLogin()+"/admin/live/download-chat?id="+liveRoomId;

        HttpPost httpPost = new HttpPost(url);
        HttpGet httpget = new HttpGet(download);

        HttpClient httpclient = new DefaultHttpClient();

        // 组装基本邮件的参数
        MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE, null, Charset.forName("UTF-8"));
        entity.addPart("account", new StringBody(apiUser, Charset.forName("UTF-8")));
        entity.addPart("passwd", new StringBody(apiKey, Charset.forName("UTF-8")));

        httpPost.setEntity(entity);

        //执行E课堂登陆方法
        HttpResponse response = httpclient.execute(httpPost);
        httpPost.releaseConnection();  //释放资源

        InputStream instream = null;
        // 处理响应
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

        	//执行获取聊天记录的方法
            HttpResponse downloadRes = httpclient.execute(httpget);
            HttpEntity enti = downloadRes.getEntity();
            if (enti != null) {
            	instream = enti.getContent();
            	log.info("获取E课堂聊天的相关信息");
            }else{
                log.info("获取E课堂聊天的相关信息为空");
            }
//            httpget.releaseConnection();  //释放资源
        } else {
        	log.info("获取E课堂聊天的相关信息发生错误，返回的状态码为："+response.getStatusLine().getStatusCode());
        }
        return instream;
	}

	/**
	 * @Description: 根据直播ID获取对应的聊天记录
	 * @author zx.wang
	 * @date 2016-1-15 下午3:40:38
	 * @version 2.0
	 * @param filePath
	 * @param liveRoomId
	 * @throws Exception
	 */
	private void getZhanShiChatStream(String filePath, String liveRoomId,CompanyLiveConfig clc) throws Exception{
		String url = "";
		if(clc != null && clc.getLiveType().equals(1)){
			url = clc.getDomain() + LiveRoomConstant.EXPORT_CHAT + "roomId=" + liveRoomId + "&loginName=" + clc.getLoginName() + "&password=" + clc.getPassword();
		}else{
			url = LiveRoomConstant.DOMIN_NAME + LiveRoomConstant.EXPORT_CHAT + "?roomId=" + liveRoomId + "&loginName=" + LiveRoomConstant.LOGIN_NAME + "&password=" + LiveRoomConstant.PASSWORD;
		}
		String res = HttpPostRequest.get(url);
		Integer code = (JSONObject.parseObject(res)).getInteger("code");

		File file = new File(filePath);
		if(!file.exists()){
			file.createNewFile();
		}

		if(code != null && code.equals(0)){
			List<ExportChatZs> chat = JSONObject.parseArray((JSONObject.parseObject(res)).getString("list"), ExportChatZs.class);

			FileWriter fw = new FileWriter(file);
			if(chat != null && chat.size() > 0){
				for (ExportChatZs c : chat) {
					fw.write("发送人：" + c.getSender() + " [" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(c.getTime())) + "]" + " \r\n内容：" + c.getMsg() + "\r\n\r\n");
				}
			}else{
				fw.write("本节课没有聊天内容");
			}
			fw.flush();
			fw.close();
		}
	}

	//直播课获得输入流
	private InputStream getInputstreamByClassModuleLesson(ClassModuleLesson cml,CompanyLiveConfig clc) throws Exception{
		String fileStorate = propertiesUtil.getFileStoragePath() +"/"+ URLEncoder.encode(cml.getLessonName()) + ".txt";
		if(cml.getLiveCompanyType() == null
				|| cml.getLiveCompanyType().equals("gh")
				|| cml.getLiveCompanyType().equals("")){
			//获取E课堂的聊天记录
			String ue = cml.getStudentUrlGh();
			String customer = ue.substring(ue.indexOf("&customer=") + 10,
					ue.indexOf("&customerType"));
			return getEKeTangChatStreamByLesson(cml.getLiveroomIdGh(),clc,customer);
		}else if(cml.getLiveCompanyType().equals("zs")){
			getZhanShiChatStream(fileStorate, cml.getLiveroomIdGh(),clc);
			return new FileInputStream(fileStorate);
		}else if(cml.getLiveCompanyType().equals("ht")){
			getTalkfunChatStream(fileStorate, cml.getLiveroomIdGh(),clc);
			return new FileInputStream(fileStorate);
		}else{
			getCCLiveInfo(fileStorate, cml.getLiveroomIdGh(), clc);
			return new FileInputStream(fileStorate);
		}
	}

	private void getTalkfunChatStream(String filePath,String id,CompanyLiveConfig clc) throws Exception{
		String openId = null;
		String openToken = null;
		if(clc != null && clc.getLiveType().equals(3)){
			openId = clc.getLoginName();
			openToken = clc.getPassword();
		}
		Map<Object, Object> paramts = new HashMap<Object,Object>();
		paramts.put("course_id", id);
		paramts.put("page", 1);
		String res = TalkfunUtils.getRsult(paramts, "course.message",openId,openToken);
		Integer pages = JSONObject.parseObject(res).getInteger("pages");
		String obj = JSONObject.parseObject(res).getString("data");
		List<TalkfunCharMessage> tfcm = JSONObject.parseArray(obj
				, TalkfunCharMessage.class);
		for (int i = 2; i <= pages; i++) {
			paramts.put("page", i);
			res = TalkfunUtils.getRsult(paramts, "course.message",openId,openToken);
			obj = JSONObject.parseObject(res).getString("data");
			tfcm.addAll(JSONObject.parseArray(obj
				, TalkfunCharMessage.class));
		}

		File file = new File(filePath);
		if(!file.exists()){
			file.createNewFile();
		}

		FileWriter fw = new FileWriter(file);
		if(tfcm != null && tfcm.size() > 0){
			for (TalkfunCharMessage c : tfcm) {
				String msg = c.getMsg();
				while (msg.indexOf("<img") != -1) {
					String s = msg.substring(msg.indexOf("<img"),msg.indexOf(">") + 1);
					msg = msg.replaceAll(s, "[图片]");
				}
				fw.write("发送人：" + c.getNickname() + " [" + c.getTime() + "]" + " \r\n内容：" + msg + "\r\n\r\n");
			}
		}else{
			fw.write("本节课没有聊天内容");
		}
		fw.flush();
		fw.close();
	}

	private void getCCLiveInfo(String filePath,String id,CompanyLiveConfig clc) throws Exception{

		String userid = "";
		if(clc != null && clc.getLiveType().equals(4)){
			userid = clc.getLoginName();
		}else{
			userid = CCLiveInterface.USER_ID;
		}
		Map<String, String> map = new HashMap<String,String>();
		map.put("roomid", id);
		map.put("userid", userid);
		map.put("pagenum", "1");
		map.put("pageindex", "1");

		net.sf.json.JSONObject json = getCCjson(map,CCLiveInterface.LIVE_INFO);

		File file = new File(filePath);
		if(!file.exists()){
			file.createNewFile();
		}

		FileWriter fw = new FileWriter(file);
		if("OK".equals(json.getString("result"))){
			net.sf.json.JSONArray rj = json.getJSONArray("lives");
			String liveid = rj.getJSONObject(0).getString("id");

			boolean b = true;
			int page = 1;
			net.sf.json.JSONArray msg = new net.sf.json.JSONArray();
			while(b){
				map.clear();
				map.put("roomid", id);
				map.put("userid", userid);
				map.put("liveid", liveid);
				map.put("pagenum", "100");
				map.put("pageindex", page+"");
				json = getCCjson(map,CCLiveInterface.CHAT_MSG);
				if("OK".equals(json.getString("result"))){
					rj = json.getJSONArray("chatMsgs");
					msg.addAll(rj);
					if(rj.size() < 100){
						b = false;
					}
				}
				page ++;
			}

			if(msg.size() <= 0){
				fw.write("本节课没有聊天内容");
			}else{
				for (Object object : msg) {
					json = net.sf.json.JSONObject.fromObject(object);
					fw.write("发送人：" + json.getString("viewerName")
							+ " [" + json.getString("time") + "]" + " \r\n内容："
							+ json.getString("content") + "\r\n\r\n");
				}
			}
		}else{
			fw.write("本节课没有聊天内容");
		}
		fw.flush();
		fw.close();
	}

	private net.sf.json.JSONObject getCCjson(Map<String, String> map,String apiurl) throws Exception{
		long timestimp = System.currentTimeMillis();
		String thqs = APIServiceFunction.createHashedQueryString(map,timestimp,CCLiveInterface.API_KEY);
		String url = apiurl + thqs;
		String res = HttpPostRequest.get(url);
		net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(res);
		return json;
	}
	//公开课获得输入流
	private InputStream getInputstreamByLiveOpenCourse(LiveOpenCourse cml,CompanyLiveConfig clc) throws Exception{
		String fileStorate = propertiesUtil.getFileStoragePath() +"/"+ URLEncoder.encode(cml.getOpenCourseName()) + ".txt";
		if(cml.getLiveServiceProvider() == null
				|| cml.getLiveServiceProvider().equals("gh")
				|| cml.getLiveServiceProvider().equals("")){
			//获取E课堂的聊天记录
			String ue = cml.getStudentUrlGh();
			String customer = ue.substring(ue.indexOf("&customer=") + 10,
					ue.indexOf("&customerType"));
			return getEKeTangChatStreamByLesson(cml.getLiveroomIdGh(),clc,customer);
		}else if(cml.getLiveServiceProvider().equals("zs")){
			getZhanShiChatStream(fileStorate, cml.getLiveroomIdGh(),clc);
			return new FileInputStream(fileStorate);
		}else if(cml.getLiveServiceProvider().equals("ht")){
			getTalkfunChatStream(fileStorate, cml.getLiveroomIdGh(),clc);
			return new FileInputStream(fileStorate);
		}else{
			getCCLiveInfo(fileStorate, cml.getLiveroomIdGh(), clc);
			return new FileInputStream(fileStorate);
		}
	}

	/**
	 *
	 * Class Name: ClassModuleController.java
	 * @Description: 私有网校请求接口
	 * @author 周文斌
	 * @date 2016-8-24 下午3:55:58
	 * @version 1.0
	 * @param request
	 * @param companyId
	 * @param userId
	 * @param liverooms
	 * @param allcount
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="checkoutPrivate")
	public JSONObject checkoutPrivate(HttpServletRequest request,Integer id,
			Integer liveTypes,Integer companyId,Integer userId,String liverooms
			,String allcount)
			throws Exception {
		log.info("进入私有");

		Date date = new Date();
		Date lessonDate = new SimpleDateFormat("yyyy-MM-dd").parse((date
				.getYear() + 1900)
				+ "-"
				+ (date.getMonth() + 1)
				+ "-"
				+ date.getDate());
		String currentTime = date.getHours() + ":" + date.getMinutes();
		JSONObject json = new JSONObject();
		// 查询公司购买 服务
		CompanyMemberService cms = companyMemberServiceServiceImpl
				.findByCompanyId(companyId);

		// 查询公司服务时间
		Company company = companyServiceImpl.findCompanyById(companyId);
		if (lessonDate.after(company.getMemberEndDate())) {
			json.put(JsonMsg.RESULT, "service");
			return json;
		}
		CompanyLiveStaticDayDetail clsdd = new CompanyLiveStaticDayDetail();


		log.info("查询当月并发");
		Map<String, Object> comtime = new HashMap<String, Object>();
		Integer concurrentMonth = Integer.parseInt((date.getYear() + 1900)
				+ "" + ((date.getMonth() + 1) < 10 ?
						"0" + (date.getMonth() + 1) :
						(date.getMonth() + 1)));
		comtime.clear();
		comtime.put("companyId", companyId);
		comtime.put("concurrentMonth", concurrentMonth);
		CompanyLiveConcurrent livec =  companyLiveConcurrentServiceImpl
				.findLiveByComidAndDate(comtime);
		if(livec == null){
			log.info("当月没有可用并发");
			json.put(JsonMsg.RESULT, "notonline");
			return json;
		}
		Integer liven = livec.getConcurrentMax();
		// 直播人数
		// 查询当前直播课程的直播id
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("lessonDate", lessonDate);
		param.put("currentTime", currentTime);
		param.put("companyId", companyId);
		List<ClassModuleLesson> liveRooms = JSONObject.parseArray(liverooms, ClassModuleLesson.class);
		String customer = properties.getCustomer();
		String secretKey = properties.getSecretKey();

		String ektStr = properties.getInterfaceAddress()
				+ LiveRoomConstant.LIVE_DYNAMICINFO;
		int count = 0;

		log.info("查询当前用户是否配置了单独账号");
		CompanyLiveConfig clc = companyLiveConfigServiceImpl
				.findByCompanyId(companyId);

		Map<String, String> ghlist = JedisUtil.hgetAll("gh_all_map_no_0");
//		List<AllLiveCount> ghlist = null;
		log.info("查询key：gh_all_map_no_0, 查询出的列表为：" + ghlist);
		if (ghlist == null || ghlist.size() == 0) {
			ghlist = new HashMap<String,String>();
		}
//		List<AllLiveCount> zslist = null;
		Map<String, String> zslist = JedisUtil.hgetAll("zs_all_map_no_0");
//		List<AllLiveCount> ghlist = null;
		log.info("查询key：zs_all_map_no_0, 查询出的列表为：" + zslist);
		if (zslist == null || zslist.size() == 0) {
			zslist = new HashMap<String,String>();
		}
		Map<String, String> cclist = JedisUtil.hgetAll("cc_all_map_no_0");
//		List<AllLiveCount> ghlist = null;
		log.info("查询key：cc_all_map_no_0, 查询出的列表为：" + cclist);
		if (cclist == null || cclist.size() == 0) {
			cclist = new HashMap<String,String>();
		}

		Map<String, String> htlist = JedisUtil.hgetAll("ht_all_map_no_0");
		log.info("查询key：ht_all_map_no_0, 查询出的列表为：" + htlist);
		if (htlist == null || htlist.size() == 0) {
			htlist = new HashMap<String,String>();
		}

		log.info("zs////查询redis");
		Map<String, String> zslive = JedisUtil.hgetAll("zs_live_map_no_" + companyId);
		if(zslive == null || zslive.size() == 0){
			zslive = new HashMap<String,String>();
		}

		for (ClassModuleLesson live : liveRooms) {
			if (live != null) {
				Date ld = live.getLessonDate();
				String lt = live.getLessonTimeEnd();
				Date times = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse((ld
						.getYear() + 1900)
						+ "-"
						+ (ld.getMonth() + 1)
						+ "-"
						+ (ld.getDate()) + " " + lt);
				if ((live.getLiveCompanyType() == null || live
						.getLiveCompanyType().equals("gh"))
						&& live.getLiveRoom() != null) {
					String ClassNo = live.getLiveRoom();
					if (clc == null || !clc.getLiveType().equals(2)) {
						Long timestamp = System.currentTimeMillis();
						String s = MD5.getMD5ofStr(customer + timestamp
								+ secretKey);
						s = (s.substring(0, 10) + s.substring(s.length() - 10));
						param.clear();
						param.put("customer", customer);
						param.put("timestamp", timestamp);
						param.put("s", s);

						param.put("id", live.getLiveRoom());
						String str = null;
						try {
							String result = HttpPostRequest.post(ektStr, param);
							str = (JSONObject.parseObject(result))
									.getString("onLineNum");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							for(StackTraceElement elem : e.getStackTrace()) {
				                log.error(elem);
				            }
							log.error("本次调用e课堂获得在线人数出错，" + e.getMessage());
						}
						if (str != null) {
							ghlist.put(ClassNo, str);
							count += Integer.parseInt(str);
						}
					}
				} else if(live.getLiveCompanyType().equals("zs")){
					if (live.getLiveroomIdGh() != null) {
						String ClassNo = live.getLiveroomIdGh();
						if (clc == null || !clc.getLiveType().equals(1)) {
							if(zslive.containsKey(ClassNo)){
								zslist.put(ClassNo, zslive.get(ClassNo));
								count += Integer.parseInt(zslive.get(ClassNo));
							}
						}
					}
				} else if(live.getLiveCompanyType().equals("ht")) {
					if (live.getLiveroomIdGh() != null) {
						String ClassNo = live.getLiveroomIdGh();
						if (clc == null || !clc.getLiveType().equals(3)) {
							Map<Object, Object> paramts = new HashMap<Object,Object>();
							paramts.put("course_id", ClassNo);
							Integer livenum = 0;
							try {
								String res = TalkfunUtils.getRsult(paramts, "course.get",null,null);
								String obj = JSONObject.parseObject(res).getString("data");
								livenum = JSONObject.parseObject(obj)
										.getInteger("onlineTotal");

								htlist.put(ClassNo, livenum+"");
								count += livenum;
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				} else {
					if (live.getLiveroomIdGh() != null) {
						String ClassNo = live.getLiveroomIdGh();
						if (clc == null || !clc.getLiveType().equals(4)) {
							int livenum = 0;
							Date sdtime = new Date();
							Date edtime = DateUtil.addSecond(sdtime, 1);
							Map<String, String> cclivemap = new HashMap<String,String>();
							cclivemap.put("roomid", ClassNo);
							cclivemap.put("userid", CCLiveInterface.USER_ID);
							cclivemap.put("starttime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
								.format(sdtime));
							cclivemap.put("endtime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
								.format(edtime));

							long timestimp = System.currentTimeMillis();
							String thqs = APIServiceFunction.createHashedQueryString(cclivemap,timestimp
									, CCLiveInterface.API_KEY);
							//获取创建直播课的URL
							String ccres = CCLiveInterface.CONNECTIONS + thqs;
							if("OK".equals(net.sf.json.JSONObject.fromObject(ccres)
									.getString("result"))){
								String connection = net.sf.json.JSONObject.fromObject(ccres)
										.getString("connections");
								JSONObject rj = JSONObject.parseObject(connection);
								livenum += rj.getInteger("count");
								livenum += rj.getInteger("replayCount");
								cclist.put(ClassNo, livenum+"");
							}
							count += livenum;
						}
					}
				}
			}
		}

		log.info("直播人数：当前实时直播人数：" + count + ", 公司ID：" + companyId
				+ ", 公司名称：" + company.getCompanyName());
		log.info("直播人数：公司拥有最大人数："
				+ liven + ", 公司ID：" + companyId
				+ ", 公司名称：" + company.getCompanyName());

		if ((count + 1) > liven) {
			json.put(JsonMsg.RESULT, JsonMsg.ERROR);
			return json;
		}

		try {
			log.info("统计总并发数");
			log.info("liveTypes:" + liveTypes);
			// 根据lessonId 查询 课次
			ClassModuleLesson cml = null;
			LiveOpenCourse loc = null;
			if (liveTypes.equals(1)) {
				cml = classModuleLessonServiceImpl
						.findClassModuleLessonById(id);
			} else {
				loc = liveOpenCourseServiceImpl
						.findLiveOpenCourseById(id);
			}
			String lrm = "";
			if (cml != null) {
				if (cml.getLiveCompanyType() != null &&
						cml.getLiveCompanyType().equals("zs")) {
					lrm = cml.getLiveroomIdGh();
				} else {
					lrm = cml.getLiveRoom();
				}
			} else if (loc != null) {
				if (loc.getLiveServiceProvider() != null &&
						loc.getLiveServiceProvider().equals("zs")) {
					lrm = loc.getLiveroomIdGh();
				} else {
					lrm = loc.getLiveRoom();
				}
			}
			List<AllLiveCountModuleLessonVo> all = JSONObject
					.parseArray(allcount, AllLiveCountModuleLessonVo.class);

			param.clear();
			param.put("lessonDate", lessonDate);
			param.put("currentTime", currentTime);
			param.put("companyId", companyId);
			getCompanyAllCount(all, ghlist, zslist, htlist, userId, lrm, companyId,
					lessonDate, date);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			for(StackTraceElement elem : e.getStackTrace()) {
                log.error(elem);
            }
			log.error("统计总并发数出错：" + e.getMessage());
			json.put(JsonMsg.RESULT, JsonMsg.ERROR);
			return json;
		}

		clsdd.setUserId(userId);
		clsdd.setCompanyId(companyId);
		clsdd.setLiveMaxNum(count + 1);
		clsdd.setAddDate(lessonDate);
		clsdd.setAddTime(date);
		companyLiveStaticDayDetailServiceImpl.insert(clsdd);
		json.put(JsonMsg.RESULT, JsonMsg.SUCCESS);
		return json;
	}

	private void getCompanyAllCount(List<AllLiveCountModuleLessonVo> all,
			Map<String, String> ghlist, Map<String, String> zslist,
			Map<String, String> htlist,Integer userId, String liveRoomId
			, Integer companyId,Date lessonDate, Date date) {
		int countgh = 0;
		int countzs = 0;
		int countht = 0;
		Long time = new Date().getTime();
		Map<String, String> alcgh = new HashMap<String,String>();
		Map<String, String> alczs = new HashMap<String,String>();
		Map<String, String> alcht = new HashMap<String,String>();
		for (AllLiveCountModuleLessonVo live : all) {
			if (live.getLiveCompanyType() == null
					|| live.getLiveCompanyType().equals("gh")
					|| live.getLiveCompanyType().equals("")) {
				String ClassNo = live.getLiveRoom();
				if(ghlist.containsKey(ClassNo)){
					alcgh.put(ClassNo, ghlist.get(ClassNo));
					countgh += Integer.parseInt(ghlist.get(ClassNo));
				}
			} else if(live.getLiveCompanyType().equals("zs")){
				String ClassNo = live.getLiveroomIdGh();
				if(zslist.containsKey(ClassNo)){
					alczs.put(ClassNo, zslist.get(ClassNo));
					countzs += Integer.parseInt(zslist.get(ClassNo));
				}
			} else {
				String ClassNo = live.getLiveroomIdGh();
				if(htlist.containsKey(ClassNo)){
					alcht.put(ClassNo, htlist.get(ClassNo));
					countht += Integer.parseInt(htlist.get(ClassNo));
				}
			}
		}
		if (countgh > 0) {
			CompanyTotalLiveStaticDetail ctlsd = new CompanyTotalLiveStaticDetail();
			ctlsd.setUserId(userId);
			ctlsd.setLiveroomId(liveRoomId);
			ctlsd.setCompanyId(companyId);
			ctlsd.setLiveData(lessonDate);
			ctlsd.setLiveDataTime(date);
			ctlsd.setLiveNum(countgh);
			ctlsd.setLiveType(1);
			companyTotalLiveStaticDetailServiceImpl.insert(ctlsd);
			JedisUtil.hmset("gh_all_map_no_0", alcgh);
		}
		if (countzs > 0) {
			CompanyTotalLiveStaticDetail ctlsd = new CompanyTotalLiveStaticDetail();
			ctlsd.setUserId(userId);
			ctlsd.setLiveroomId(liveRoomId);
			ctlsd.setCompanyId(companyId);
			ctlsd.setLiveData(lessonDate);
			ctlsd.setLiveDataTime(date);
			ctlsd.setLiveNum(countzs);
			ctlsd.setLiveType(0);
			companyTotalLiveStaticDetailServiceImpl.insert(ctlsd);
			JedisUtil.hmset("zs_all_map_no_0", alczs);
		}
		if (countht > 0) {
			CompanyTotalLiveStaticDetail ctlsd = new CompanyTotalLiveStaticDetail();
			ctlsd.setUserId(userId);
			ctlsd.setLiveroomId(liveRoomId);
			ctlsd.setCompanyId(companyId);
			ctlsd.setLiveData(lessonDate);
			ctlsd.setLiveDataTime(date);
			ctlsd.setLiveNum(countht);
			ctlsd.setLiveType(0);
			companyTotalLiveStaticDetailServiceImpl.insert(ctlsd);
			JedisUtil.hmset("ht_all_map_no_0", alcht);
		}
	}


	/**
	 * @Description: 跑马灯设置接口
	 * @author zx.wang
	 * @date 2015-10-21 上午11:14:06
	 * @version 2.0
	 * @param request
	 * @param verificationcode
	 * @param vid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "marquee", method = RequestMethod.POST)
	public String marquee(HttpServletRequest request, String verificationcode,
			String vid) {
		log.info("1验证当前视频是否播放权限， verificationcode：" + verificationcode
				+ ", vid:" + vid);

		Integer loop = -1;
		String content = "盗版必究";
		Integer fontSize = 28;
		String color = "#04F300";
		Integer duration = 10000;
		float startXpos = 0.0f;
		float startYpos = 0.0f;
		float startAlpha = 0.8f;

		float endXpos = 1.0f;
		float endYpos = 1.0f;
		float endAlpha = 0.8f;

		Map<String, Object> map = new HashMap<String, Object>();

		UsersFront user = null;
		Company company = null;
		String[] code = null;
		if (verificationcode != null && verificationcode != "") {
			code = verificationcode.split("_");
		}
		if (code != null && !code[1].equals("0") && code[2].equals("f")) {
			user = usersFrontService.findUsersFrontById(Integer
					.parseInt(code[1]));
		}
		if (code != null && !code[0].equals("0")) {
			company = companyServiceImpl.findCompanyById(Integer
					.parseInt(code[0]));
		}
		log.info("======= users:" + user);

		// 根据公司分校查询出当前分校相应的配置，来展示跑马灯的效果
		if ((user != null && user.getId() != null && user.getId() > 0)
				|| (company != null && company.getId() != null && company
						.getId() > 0)) {
			log.info("2验证当前视频是否播放权限，当前用户不为空");
			Integer companyId = 0;
			if (user != null && user.getId() != null && user.getId() > 0) {
				companyId = user.getCompanyId();
			} else if (company != null) {
				companyId = company.getId();
			}
			// 获取该公司的跑马灯设置
			CourseVideoMarqueeVo mqrqueeVo = courseVideoMarqueeServiceImpl
					.findCourseVideoMarqueeVoByCompanyId(companyId);
			log.info("3验证当前视频是否播放权限，当前跑马灯设置信息：" + mqrqueeVo);
			if (mqrqueeVo != null) {
				loop = mqrqueeVo.getCycleTime();
				if (loop > 1) {
					loop = loop - 1;
				}

				String userName = "";
				map.put("companyId", companyId);
				map.put("userId", Integer.parseInt(code[1]));
				List<Student> students = studentServiceImpl
						.findStuInfoByUserId(map);
				if (user != null && user.getId() != null && user.getId() > 0) {
					if (students != null && students.size() > 0) {
						log.info("3验证当前视频是否播放权限，当前学生的信息为："
								+ students.get(0).getName());
						userName = students.get(0).getName();
					}
					if(userName == null || userName.equals("")){
						userName = user.getNickName();
					}
					if (userName == null || userName.equals("")) {
						userName = user.getMobile();
					}
					if (userName == null || userName.equals("")) {
						userName = user.getUsername();
					}
				}

				if (userName == null || userName.equals("")) {
					content = mqrqueeVo.getContent();
				} else {
					content = userName + "_" + mqrqueeVo.getContent();
				}

				fontSize = mqrqueeVo.getFontSize();
				color = mqrqueeVo.getFontColor();
				duration = mqrqueeVo.getSingleCostTime();
				startXpos = mqrqueeVo.getStartXpos();
				startYpos = mqrqueeVo.getStartYpos();
				endXpos = mqrqueeVo.getEndXpos();
				endYpos = mqrqueeVo.getEndYPos();
			} else {
				log.info("6验证当前视频是否播放权限，公司Id为：" + companyId + "没有相关的跑马灯配置");
				content = "";
			}
		} else {
			log.info("4验证当前视频是否播放权限，当前用户为空");
			content = "";
		}
		// 返回的json串
		String jsonReturn = "{\"response\":{\"version\":\"1\",\"enable\":1,\"freetime\":120,\"message\":\"请购买后重试！\",\"callback\":\"\" ,\"marquee\":{\"loop\":"
				+ loop
				+ ",\"type\":\"text\",\"text\":{\"content\":\""
				+ content
				+ "\",\"font_size\":"
				+ fontSize
				+ ",\"color\":\""
				+ color
				+ "\"},\"action\":[{\"index\":0,\"duration\":"
				+ duration
				+ ",\"start\":{\"xpos\":"
				+ startXpos
				+ ",\"ypos\":"
				+ startYpos
				+ ",\"alpha\":"
				+ startAlpha
				+ "},\"end\":{\"xpos\":"
				+ endXpos
				+ ",\"ypos\":"
				+ endYpos
				+ ",\"alpha\":"
				+ endAlpha
				+ "}}]}}}";
		log.info("5验证当前视频是否播放权限，当前返回字符串为：" + jsonReturn);

		return jsonReturn;
	}

	@ResponseBody
	@RequestMapping("/CCLiveCheck")
	public JSONObject CCLiveCheck(String roomid,String viewername,String viewertoken){
		JSONObject json = new JSONObject();
		if(viewername.indexOf(":") < 0){
			json.put("result", "ValidateException");
			json.put("message", "登录验证异常");
		}else{
			try {
				String[] arr =  viewername.split(":");
				JSONObject userjs = new JSONObject();
				log.info("登录验证,"+ arr + ",into," + viewername);
				userjs.put("id", arr[0]);
				userjs.put("name", arr[1]);
				userjs.put("avatar", "");
				json.put("result", "ok");
				json.put("message", "验证通过");
				json.put("user", userjs);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				log.info("验证出现异常，"+e.getMessage());
				json.put("result", "ValidateException");
				json.put("message", "登录验证异常");
			}
		}
		log.info("返回,"+json);
		return json;
	}

	public static void main(String[] args) throws Exception{
		/*Map<Object, Object> param = new HashMap<Object,Object>();
		param.put("course_name", "测试");
		param.put("account", "3665");
		param.put("start_time", "2017-03-09 11:30:00");
		param.put("end_time",  "2017-03-09 11:50:00");
		param.put("nickname", "老师");
		param.put("accountIntro", "简介");

		Map<Object, Object> options = new HashMap<Object,Object>();
		options.put("departmentId", "25428");
		param.put("options", options);
		String res = TalkfunUtils.getRsult(param, "course.add","11429","75716f6b328bb355f79020b549049cfa");
		JSONObject rjson = JSONObject.parseObject(res);
		System.out.println(rjson);*/
		long s = (long) (29.11 * 1024 *1024);
		System.out.println(s);
		double sMb = new BigDecimal(Double.parseDouble(String.valueOf(s)) / (1024 * 1024))
			.setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue();
		System.out.println(sMb);
	}

	/**
	 *
	 * Class Name: ClassModuleController.java
	 * @Description: 展示鉴权
	 * @author 周文斌
	 * @date 2017-3-9 下午5:54:25
	 * @modify	2017-3-9 下午5:54:25
	 * @version
	 * @param k
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/GenseeVilidate")
	public String GenseeVilidate(String k){
		log.info("展示鉴权回调，" + k);
		try {
			String[] arr = k.split(":");
			int userId = Integer.parseInt(arr[0]);
			int lessonId = Integer.parseInt(arr[1]);
			int companyId = Integer.parseInt(arr[2]);
			long timestamp = Long.parseLong(arr[3]);
			String password = arr[4];
			String md5 = arr[5];

			long times = System.currentTimeMillis() ;
			if(((times - timestamp) / 1000) > 60){
				log.info("超时");
				return "fail";
			}
			String twoMd5 = ParameterUtil.GenseeMd5(userId, lessonId, companyId
					, timestamp, password);
			if(twoMd5.equals(md5)){
				return "pass";
			}else{
				log.info("校验码不正确," + md5 + "::" + twoMd5);
				return "fail";
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("展示鉴权回调错误，" + e.getMessage());
			return "fail";
		}
	}

	/**
	 *
	 * Class Name: ClassModuleController.java
	 * @Description: 七牛上传回调
	 * @author 周文斌
	 * @date 2017-3-13 上午11:15:09
	 * @modify	2017-3-13 上午11:15:09
	 * @version
	 * @param jsonStr
	 */
	@ResponseBody
	@RequestMapping("/QnVideoCallback")
	public void QnVideoCallback(HttpServletRequest request,
			HttpServletResponse response){
		try {
			String info = RequestUtil.convert(request);
			log.info("七牛视频回调信息，" + info);
			JSONObject json = JSONObject.parseObject(info);
			String key = json.getString("key");
			long size = json.getLongValue("size");
		double sMb = new BigDecimal(Double.parseDouble(String.valueOf(size)) / (1024 * 1024))
			.setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue();

			JSONObject avinfo = json.getJSONObject("avinfo");
			JSONObject format = avinfo.getJSONObject("format");
			double duration = format.getDoubleValue("duration");

			String times = FileQNUtils.secToTime((int)duration);

			Video video = videoImpl.findVideoByQiniuKeys(key);
			video.setVideoStatus("VIDEO_PROCESS_NOMAL");
			video.setVideoTime(times);
			video.setVodeoSize(sMb);

			CompanyVideoConfig cvc = companyVideoConfigImpl
					.findConfigByCompanyId(video.getCompanyId());

			response.setStatus(200);
			if(cvc != null && "qnvd".equals(cvc.getVideoType())){
				video.setVodeoSize(0.0);
				videoImpl.update(video);
				return ;
			}
			videoImpl.update(video);

			VideoVo vvo = new VideoVo();
			vvo.setCompanyId(video.getCompanyId());
			vvo.setCreateTime(video.getCreateTime());
			 // 获取当天的
            String sum = videoMapper.sumVideoSize(vvo);
            Double storage = Double.parseDouble(sum);
            storage = new BigDecimal(storage / 1024).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
            // 更新公司今天使用情况
            CompanyServiceStaticDay companyServiceStaticDay = new CompanyServiceStaticDay();
            companyServiceStaticDay.setCompanyId(video.getCompanyId());
            SimpleDateFormat today = new SimpleDateFormat("yyyy-MM-dd");

            try {
                companyServiceStaticDay.setUseDate(today.parse(today.format(video.getCreateTime())));
                CompanyServiceStaticDay service = companyServiceStaticDayMapper.findByDateAndCompanyId(companyServiceStaticDay);
                if (service == null) {
                    service = new CompanyServiceStaticDay();
                    service.setCompanyId(video.getCompanyId());
                    service.setUseDate(today.parse(today.format(video.getCreateTime())));
                    service.setVideoStorageNum(storage);
                    this.companyServiceStaticDayMapper.insert(service);
                } else {
                    service.setVideoStorageNum(storage);
                    this.companyServiceStaticDayMapper.update(service);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 更新公司总使用量
            vvo.setCreateTime(null);
            // 获取当天的
            String totalSum = this.videoMapper.sumVideoSize(vvo);
            Double totalStorage = Double.parseDouble(totalSum);
            CompanyServiceStatic css = companyServiceStaticMapper.findByCompanyId(video.getCompanyId());
            CompanyServiceStatic updateStatic = new CompanyServiceStatic();
            totalStorage = new BigDecimal(totalStorage / 1024).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
            updateStatic.setVideoStorage(totalStorage);
            if (css == null || css.getId() == null) {
                updateStatic.setCompanyId(video.getCompanyId());
                companyServiceStaticMapper.insert(updateStatic);
            } else {
                updateStatic.setId(css.getId());
                companyServiceStaticMapper.update(updateStatic);
            }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.debug("七牛视频回调出错,"+e.getMessage());
			response.setStatus(500);
		}
	}


	/**
	 *
	 * Class Name: ClassModuleController.java
	 * @Description: 保利视频回调通知
	 * @author 周文斌
	 * @date 2017-3-20 下午2:11:12
	 * @modify	2017-3-20 下午2:11:12
	 * @version
	 * @param vid
	 * @param type
	 * @param sign
	 */
	@ResponseBody
	@RequestMapping("/VideoBLVS")
	public void VideoBLVS(String vid,String type,String sign,String state,String format,String df){
		log.info("保利视频回调参数," + vid+ ","+type+","+sign+","+state);

		Video v = null;
		switch (type) {
		case "upload":{
			String[] arr = state.split("::");
			String reg = "upload" + vid + PolyvParams.SECRET_KEY;
			String md5 = MD5.getMD5ofStr(reg).toLowerCase();
			log.info("md5比较,sign:"+sign+",md5:"+md5);
			if(sign.equals(md5)){
				log.info("准备查询");
				v = new Video();
				try {
					v.setVideoStatus("VIDEO_PROCESS_INHAND");
					v.setItemOneId(Integer.parseInt(arr[0]));
					v.setItemSecondId(Integer.parseInt(arr[1]));
					v.setVideoTag(arr[2]);
					v.setCompanyId(Integer.parseInt(arr[3]));
					v.setSchoolId(Integer.parseInt(arr[4]));
					v.setCreator(Integer.parseInt(arr[5]));
					v.setCreateTime(new Date());
					v.setWebVideoId(vid);
					v.setStorageType("VIDEO_STORAGE_TYPE_BLVS");
					log.info("video:"+v);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					log.info("video:"+v);
					e1.printStackTrace();
				}

				CompanyVideoConfig cvc = companyVideoConfigImpl
						.findConfigByCompanyId(v.getCompanyId());
				if(cvc != null && !"blvs".equals(cvc.getVideoType())){
					cvc = null;
				}
				log.info("cvc:"+cvc);
				String readToken = (cvc != null ? cvc.getReadToken() : PolyvParams.READ_TOKEN);
				String secretKey = (cvc != null ? cvc.getReadToken() : PolyvParams.SECRET_KEY);
		    	String url = PolyvParams.GET_SINGLE_VIDEO;
		    	String strSha = "readtoken="+readToken+"&vid="+vid+secretKey;
		    	log.info("strSha:"+strSha);
				String res = "";
				try {
					String signs = SHA1.SHA1(strSha);
					url += "&readtoken="+readToken+"&vid="+vid+"&format=json&sign="+signs;
					res = HttpPostRequest.get(url);
					log.info("视频信息："+res);
				} catch (DigestException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(res);
				if(json.getInt("error") == 0){
					net.sf.json.JSONArray data = json.getJSONArray("data");
					net.sf.json.JSONObject vdata = net.sf.json.JSONObject
							.fromObject(data.get(0));
					v.setVideoName(vdata.getString("title"));
				}
				videoImpl.insert(v);
			}
		}
			break;
		case "pass":{
			String reg = "manage" + type + vid + PolyvParams.SECRET_KEY;
			String md5 = MD5.getMD5ofStr(reg).toLowerCase();
			if(sign.equals(md5)){
				v = videoImpl.findVideoByQiniuKeys(vid);

				CompanyVideoConfig cvc = companyVideoConfigImpl
						.findConfigByCompanyId(v.getCompanyId());
				if(cvc != null && !"blvs".equals(cvc.getVideoType())){
					cvc = null;
				}
				String readToken = (cvc != null ? cvc.getReadToken() : PolyvParams.READ_TOKEN);
				String secretKey = (cvc != null ? cvc.getReadToken() : PolyvParams.SECRET_KEY);
		    	String url = PolyvParams.GET_SINGLE_VIDEO;
				try {
					String strSha = "readtoken="+readToken+"&vid="+vid+secretKey;
					String signs = SHA1.SHA1(strSha);
					url += "&readtoken="+readToken+"&vid="+vid+"&format=json&sign="+signs;
					String res = HttpPostRequest.get(url);
					net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(res);
					if(json.getInt("error") == 0){
						net.sf.json.JSONArray data = json.getJSONArray("data");
						net.sf.json.JSONObject vdata = net.sf.json.JSONObject
								.fromObject(data.get(0));
						v.setVideoTime(vdata.getString("duration"));
						long size = vdata.getLong("source_filesize");
						double vsize = new BigDecimal(Double.parseDouble(String.valueOf(size)) / (1024 * 1024)).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
						v.setVodeoSize(vsize);
						if(cvc != null){
							v.setVodeoSize(0.0);
						}
						v.setVideoStatus("VIDEO_PROCESS_NOMAL");
						videoImpl.update(v);
						//如果是私人账号 不记录
						if(cvc != null ){
							break;
						}
						VideoVo vvo = new VideoVo();
						vvo.setCompanyId(v.getCompanyId());
						vvo.setCreateTime(v.getCreateTime());
						 // 获取当天的
                        String sum = videoMapper.sumVideoSize(vvo);
                        Double storage = Double.parseDouble(sum);
                        storage = new BigDecimal(storage / 1024).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
                        // 更新公司今天使用情况
                        CompanyServiceStaticDay companyServiceStaticDay = new CompanyServiceStaticDay();
                        companyServiceStaticDay.setCompanyId(v.getCompanyId());
                        SimpleDateFormat today = new SimpleDateFormat("yyyy-MM-dd");

                        try {
                            companyServiceStaticDay.setUseDate(today.parse(today.format(v.getCreateTime())));
                            CompanyServiceStaticDay service = companyServiceStaticDayMapper.findByDateAndCompanyId(companyServiceStaticDay);
                            if (service == null) {
                                service = new CompanyServiceStaticDay();
                                service.setCompanyId(v.getCompanyId());
                                service.setUseDate(today.parse(today.format(v.getCreateTime())));
                                service.setVideoStorageNum(storage);
                                this.companyServiceStaticDayMapper.insert(service);
                            } else {
                                service.setVideoStorageNum(storage);
                                this.companyServiceStaticDayMapper.update(service);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        // 更新公司总使用量
                        vvo.setCreateTime(null);
                        // 获取当天的
                        String totalSum = this.videoMapper.sumVideoSize(vvo);
                        Double totalStorage = Double.parseDouble(totalSum);
                        CompanyServiceStatic css = companyServiceStaticMapper.findByCompanyId(v.getCompanyId());
                        CompanyServiceStatic updateStatic = new CompanyServiceStatic();
                        totalStorage = new BigDecimal(totalStorage / 1024).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
                        updateStatic.setVideoStorage(totalStorage);
                        if (css == null || css.getId() == null) {
                            updateStatic.setCompanyId(v.getCompanyId());
                            companyServiceStaticMapper.insert(updateStatic);
                        } else {
                            updateStatic.setId(css.getId());
                            companyServiceStaticMapper.update(updateStatic);
                        }
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
			break;
		case "invalidVideo":{
			v = videoImpl.findVideoByQiniuKeys(vid);
			v.setVideoStatus("VIDEO_PROCESS_FAIL");
			videoImpl.update(v);
		}
			break;
		case "encode_failed":{
			v = videoImpl.findVideoByQiniuKeys(vid);
			v.setVideoStatus("VIDEO_PROCESS_FAIL");
			videoImpl.update(v);
		}
			break;
		case "del":{
			v = videoImpl.findVideoByQiniuKeys(vid);
			v.setVideoStatus("VIDEO_PROCESS_FAIL");
			videoImpl.update(v);
		}
			break;
		case "nopass":{
			v = videoImpl.findVideoByQiniuKeys(vid);
			v.setVideoStatus("VIDEO_PROCESS_FAIL");
			videoImpl.update(v);
		}
			break;
		}
	}

	/**
	 *
	 * Class Name: ClassModuleController.java
	 * @Description: 保利视频验证跑马灯
	 * @author 周文斌
	 * @date 2017-3-22 下午3:38:36
	 * @modify	2017-3-22 下午3:38:36
	 * @version
	 * @param vid
	 * @param code
	 * @param t
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/polyvideoMaquee")
	public String polyvideoMaquee(String vid ,String code,String t,String callback){
		JSONObject json = new JSONObject();
		log.info("保利视频，跑马灯校验：  " + code);
		String rStr = "";
		try {
			//String decoder = Base64Util.decoder(code);
			String[] arr = code.split("_");
			Integer userId = Integer.parseInt(arr[0]);
			Integer companyId = Integer.parseInt(arr[1]);
			Integer lessonId = Integer.parseInt(arr[2]);
			String md5 = arr[3];
			String source = arr[4];
			String name = "";
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("companyId", companyId);
			map.put("userId", userId);
			if (userId > 0) {
				UsersFront user = usersFrontService.findUsersFrontById(userId);
				List<Student> students = studentServiceImpl
						.findStuInfoByUserId(map);
				if (students != null && students.size() > 0) {
					log.info("3验证当前视频是否播放权限，当前学生的信息为："
							+ students.get(0).getName());
					name = students.get(0).getName();
				}
				if(name == null || name.equals("")){
					name = user.getNickName();
				}
				if (name == null || name.equals("")) {
					name = user.getMobile();
				}
				if (name == null || name.equals("")) {
					name = user.getUsername();
				}
			}else{
				name="游客" + (10000000 + new Random().nextInt(90000000));;
			}
			String vr = "userId="+userId+"::companyId="+companyId
					+"::lessonId="+lessonId;
			String rex = MD5.getMD5ofStr(vr);
			log.info("第一次校验，"+vr+":" + rex + ":" + md5);
			CompanyVideoConfig cvc = companyVideoConfigImpl
					.findConfigByCompanyId(companyId);
			if(cvc != null && !cvc.getVideoType().equals("blvs")){
				cvc = null;
			}
			if(rex.equals(md5) && "web".equals(source)){
				log.info("查询跑马灯配置");
				// 获取该公司的跑马灯设置
				CourseVideoMarqueeVo mvo = courseVideoMarqueeServiceImpl
						.findCourseVideoMarqueeVoByCompanyId(companyId);
				log.info("3验证当前视频是否播放权限，当前跑马灯设置信息：" + mvo);
				if(mvo != null){
					int status = 1;
					String username = name+"_"+mvo.getContent();
					String msg = "验证通过";
					String fontSize = mvo.getFontSize().toString();
					String fontColor = mvo.getFontColor();
					String speed = "200";
					String filter = "off";
					String setting = "1";
					String alpha = "1";
					String filterAlpha = "1";
					String filterColor = mvo.getFontColor();
					String blurX = "2";
					String blurY = "2";
					String tweenTime = "1";
					String interval = "5";
					String lifeTime = "3";
					String strength = "4";
					String show = "on";
					String Plain = "vid=" + vid + "&secretkey=" + (cvc != null ? cvc.getSecretKey() : PolyvParams.SECRET_KEY)
							+ "&username=" + username + "&code=" + code + "&status="
							+ status + "&t=" + t + "&msg=" + msg + "&fontSize="
							+ fontSize + "&fontColor=" + fontColor + "&speed="
							+ speed + "&filter=" +filter + "&setting=" + setting
							+ "&alpha=" + alpha + "&filterAlpha=" + filterAlpha
							+ "&filterColor=" + filterColor + "&blurX=" + blurX
							+ "&blurY=" + blurY + "&interval=" + interval
							+ "&lifeTime=" + lifeTime + "&tweenTime=" + tweenTime
							+ "&strength=" + strength + "&show=" +show;
					String sign = MD5.getMD5ofStr(Plain).toLowerCase();
					log.info("生成校验码，" + Plain);
					log.info("md5码," + sign);
					json.put("status", status);
					json.put("username", username);
					json.put("msg", "验证通过");
					json.put("sign", sign);
					json.put("fontSize", fontSize);
					json.put("fontColor", fontColor);
					json.put("speed", speed);
					json.put("filter", filter);
					json.put("setting", setting);
					json.put("alpha", alpha);
					json.put("filterAlpha", filterAlpha);
					json.put("filterColor", filterColor);
					json.put("blurX", blurX);
					json.put("blurY", blurY);
					json.put("tweenTime", tweenTime);
					json.put("interval", interval);
					json.put("lifeTime", lifeTime);
					json.put("strength", strength);
					json.put("show", show);
					rStr = json.toJSONString();
				}else{
					json.put("status", 1);
					json.put("msg", "验证通过");
					String Plain ="vid=" + vid + "&secretkey=" + (cvc!=null?cvc.getSecretKey():PolyvParams.SECRET_KEY)
							+ "&username=" + name + "&code=" + code + "&status=1&t=" + t;
					String sign = MD5.getMD5ofStr(Plain).toLowerCase();
					log.info("生成校验码，" + Plain);
					log.info("md5码," + sign);
					json.put("username", name);
					json.put("sign", sign);
					rStr = json.toJSONString();
				}
			}else if(rex.equals(md5) && "mobile".equals(source)){
				json.put("status", 1);
				json.put("msg", "验证通过");
				String Plain ="vid=" + vid + "&secretkey=" + (cvc!=null?cvc.getSecretKey():PolyvParams.SECRET_KEY)
						+ "&username=" + name + "&code=" + code + "&status=1&t="
						+ t;
				String sign = MD5.getMD5ofStr(Plain).toLowerCase();
				log.info("生成校验码，" + Plain);
				log.info("md5码," + sign);
				json.put("username", name);
				json.put("sign", sign);
				rStr = callback + "(" + json.toJSONString() + ")";
			}else{
				json.put("status", 2);
				json.put("msg", "验证失败");
				String Plain ="vid=" + vid + "&secretkey=" + (cvc!=null?cvc.getSecretKey():PolyvParams.SECRET_KEY)
						+ "&username=" + name + "&code=" + code + "&status=2&t=" + t;
				String sign = MD5.getMD5ofStr(Plain).toLowerCase();
				log.info("生成校验码，" + Plain);
				log.info("md5码," + sign);
				json.put("username", name);
				json.put("sign", sign);
				rStr = json.toJSONString();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			json.put("status", 2);
			json.put("msg", "验证失败");
			rStr = json.toJSONString();
		}
		log.info("返回:"+rStr);
		return rStr;
	}
}
