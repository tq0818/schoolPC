package com.yuxin.wx.controller.system;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.yuxin.wx.api.auth.IAuthUserRoleService;
import com.yuxin.wx.api.classes.IClassModuleLessonService;
import com.yuxin.wx.api.classes.IClassModuleService;
import com.yuxin.wx.api.company.ICompanyFunctionSetService;
import com.yuxin.wx.api.system.ISysConfigDictService;
import com.yuxin.wx.api.system.ISysConfigItemService;
import com.yuxin.wx.api.system.ISysConfigSchoolService;
import com.yuxin.wx.api.system.ISysConfigTeacherLessonService;
import com.yuxin.wx.api.system.ISysConfigTeacherService;
import com.yuxin.wx.api.user.IUsersService;
import com.yuxin.wx.common.CacheService;
import com.yuxin.wx.common.Constant;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.common.SysConfigConstant;
import com.yuxin.wx.model.auth.AuthUserRole;
import com.yuxin.wx.model.classes.ClassModuleLesson;
import com.yuxin.wx.model.system.SysConfigDict;
import com.yuxin.wx.model.system.SysConfigItem;
import com.yuxin.wx.model.system.SysConfigSchool;
import com.yuxin.wx.model.system.SysConfigTeacher;
import com.yuxin.wx.model.system.SysConfigTeacherLesson;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.utils.FileUtil;
import com.yuxin.wx.utils.ImageUtils;
import com.yuxin.wx.utils.ParameterUtil;
import com.yuxin.wx.utils.PropertiesUtil;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.classes.ClassModuleLessonVo;
import com.yuxin.wx.vo.system.SysConfigTeacherLessonVo;
import com.yuxin.wx.vo.system.SysConfigTeachersVo;
import com.yuxin.wx.vo.system.TeachersVo;

/**
 * Controller of SysConfigTeacher
 * 
 * @author wang.zx
 * @date 2014-12-5
 */
@Controller
@RequestMapping("/sysConfigTeacher")
public class SysConfigTeacherController {
	private Log log=LogFactory.getLog("log");
	@Autowired
	private ISysConfigTeacherService sysConfigTeacherServiceImpl;
	@Autowired
	private ISysConfigTeacherLessonService sysConfigTeacherLessonServiceImpl;
	@Autowired
	private ISysConfigSchoolService sysConfigSchoolServiceImpl;
	@Autowired
	private IClassModuleLessonService classModuleLessonServiceImpl;
	@Autowired
	private ISysConfigItemService sysConfigItemServiceImpl;
	@Autowired
	private IClassModuleService classModuleServiceImpl;
	@Autowired
	private IUsersService userServiceImpl;
	@Autowired
	private IAuthUserRoleService authUserRoleServiceImpl;
	@Autowired
	private ICompanyFunctionSetService companyFunctionSetServiceImpl;
	@Autowired
	private PropertiesUtil properties;
	@Autowired
	private ISysConfigDictService sysConfigDictServiceImpl;
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, SysConfigTeacher search) {
		if (search == null) {
			search = new SysConfigTeacher();
			// search.setPageSize(20);
		}
		model.addAttribute("list",
				sysConfigTeacherServiceImpl.findSysConfigTeacherByPage(search));
		return "sysConfigTeacher/list";
	}

	/**
	 * @Description: 保存老师信息
	 * @author wzx
	 * @date 2015-5-9 上午11:10:58
	 * @version 1.0
	 * @param model
	 * @param request
	 * @param sysConfigTeacher
	 * @param moduleIds
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/add")
	public String add(Model model, HttpServletRequest request,
			SysConfigTeacher sysConfigTeacher, String moduleIds) {
		sysConfigTeacher.setPwd(new Md5Hash(sysConfigTeacher.getPwd(),ByteSource.Util.bytes(sysConfigTeacher.getUserName()+"salt")).toHex());
		Users user = WebUtils.getCurrentUser(request);
		sysConfigTeacher.setSchoolId(user.getSchoolId());
		sysConfigTeacher.setCreator(user.getId());
		sysConfigTeacher.setCreateTime(new Date());
		sysConfigTeacher.setUpdateTime(new Date());
		sysConfigTeacher.setUpdator(user.getId());
		sysConfigTeacher.setCompanyId(sysConfigTeacher.getCompanyId());
		sysConfigTeacher.setDelFlag(0);
		sysConfigTeacher.setTeacherType(Constant.PERSON_TEACHER);
		sysConfigTeacher.setStatusCode(Constant.TEACHER_USERD);
		sysConfigTeacherServiceImpl.isnertTeaAndUse(sysConfigTeacher);
		
		SysConfigTeacherLesson lesson = new SysConfigTeacherLesson();
		Integer teaId = sysConfigTeacher.getId();
		Integer itemOneId = sysConfigTeacher.getItemOneId();
		String strTwoId = sysConfigTeacher.getItemSecondId();
		if(strTwoId == null || strTwoId == ""){
			strTwoId = "0";
		}
		Integer itemTwoId = Integer.parseInt(strTwoId);
		lesson.setItemOneId(itemOneId);
		lesson.setItemSecondId(itemTwoId);
		lesson.setTeacherId(teaId);
		sysConfigTeacherLessonServiceImpl.insert(lesson);
		
//		if (moduleIds != null && !moduleIds.equals("")) {
//			int index = moduleIds.indexOf(",");
//			if (index > 0) {
//				List<SysConfigTeacherLesson> listSctl = new ArrayList<SysConfigTeacherLesson>();
//				String[] lessons = moduleIds.substring(0, moduleIds.length() - 1).split(",");
//				
//				//根据ID查询对应的模块信息
//				List<ClassModule> modules = classModuleServiceImpl.findModuleByIds(Arrays.asList(lessons));
//				if(modules != null && modules.size() > 0){
//					for (ClassModule module : modules) {
//						SysConfigTeacherLesson sctl = new SysConfigTeacherLesson();
//						sctl.setModuleId(module.getId());
//						sctl.setItemOneId(module.getItemOneId());
//						sctl.setItemSecondId(module.getItemSecondId());
//						sctl.setTeacherId(sysConfigTeacher.getId());
//						listSctl.add(sctl);
//					}
//				}
//				sysConfigTeacherLessonServiceImpl.batchInsert(listSctl);
//			}
//		}

		return "success";
	}
	/**
	 * 
	 * Class Name: SysConfigTeacherController.java
	 * @Description: 添加教务
	 * @author ycl
	 * @date 2015-5-25 上午11:55:45
	 * @modifier
	 * @modify-date 2015-5-25 上午11:55:45
	 * @version 1.0
	 * @param request
	 * @param sysConfigTeacher
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addEducation", method = RequestMethod.POST)
	public String addEducation(HttpServletRequest request,SysConfigTeacher sysConfigTeacher){
		sysConfigTeacher.setPwd(new Md5Hash(sysConfigTeacher.getPwd(),ByteSource.Util.bytes(sysConfigTeacher.getUserName()+"salt")).toHex());
		Users user = WebUtils.getCurrentUser(request);
		sysConfigTeacher.setSchoolId(user.getSchoolId());
		sysConfigTeacher.setCreator(user.getId());
		sysConfigTeacher.setCreateTime(new Date());
		sysConfigTeacher.setUpdateTime(new Date());
		sysConfigTeacher.setUpdator(user.getId());
		sysConfigTeacher.setCompanyId(sysConfigTeacher.getCompanyId());
		sysConfigTeacher.setDelFlag(0);
		sysConfigTeacher.setStatusCode(Constant.TEACHER_USERD);
		sysConfigTeacherServiceImpl.isnertTeaAndUse(sysConfigTeacher);
		return "success";
	}
	/**
	 * @Description: 修改老师信息
	 * @author wzx
	 * @date 2015-5-9 上午11:11:18
	 * @version 1.0
	 * @param request
	 * @param model
	 * @param sysConfigTeacher
	 * @param moduleIds
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(HttpServletRequest request, Model model,
			SysConfigTeacher sysConfigTeacher, String moduleIds) {
		// Users user = WebUtils.getCurrentUser(request);
//		if (moduleIds != null && !moduleIds.equals("")) {
//			sysConfigTeacherLessonServiceImpl
//					.deleteByTeacherId(sysConfigTeacher.getId());
//			int index = moduleIds.indexOf(",");
//			if (index > 0) {
//				List<SysConfigTeacherLesson> listSctl = new ArrayList<SysConfigTeacherLesson>();
//				String[] lessons = moduleIds.substring(0, moduleIds.length() - 1).split(",");
//				
//				//根据ID查询对应的模块信息
//				List<String> aa = Arrays.asList(lessons);
//				List<ClassModule> modules = classModuleServiceImpl.findModuleByIds(aa);
//				if(modules != null && modules.size() > 0){
//					for (ClassModule module : modules) {
//						SysConfigTeacherLesson sctl = new SysConfigTeacherLesson();
//						sctl.setModuleId(module.getId());
//						sctl.setItemOneId(module.getItemOneId());
//						sctl.setItemSecondId(module.getItemSecondId());
//						sctl.setTeacherId(sysConfigTeacher.getId());
//						listSctl.add(sctl);
//					}
//				}
//				
//				sysConfigTeacherLessonServiceImpl.batchInsert(listSctl);
//			}
//		}
		String pwd = sysConfigTeacher.getPwd();
		if(pwd != null && pwd != ""){
			sysConfigTeacher.setPwd(new Md5Hash(pwd,ByteSource.Util.bytes(sysConfigTeacher.getUserName()+"salt")).toHex());
		}
		sysConfigTeacher.setUpdateTime(new Date());
		sysConfigTeacher.setUpdator(WebUtils.getCurrentUserId(request));
		//默认为老师
		if(sysConfigTeacher.getTeaOrAdu() == null || "".equals(sysConfigTeacher.getTeaOrAdu())){
			sysConfigTeacher.setTeaOrAdu("tea");
		}
		sysConfigTeacher.setCreator(WebUtils.getCurrentUser(request).getId());
		sysConfigTeacher.setSchoolId(WebUtils.getCurrentSchoolId());
		sysConfigTeacher.setCompanyId(WebUtils.getCurrentCompanyId());
		sysConfigTeacherServiceImpl.updateTeaAndUse(sysConfigTeacher);
		return "success";
	}
	
	@RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		sysConfigTeacherServiceImpl.deleteSysConfigTeacherById(id);
		return "redirect:/sysConfigTeacher";
	}

	/**
	 * @Description:(根据模块Id和学校Id查询对应的老师)
	 * @author wang.zx
	 * @date 2014-12-30 下午5:24:56
	 * @version 1.0
	 * @param model
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/{moduleId}/{teacherType}", method = RequestMethod.GET)
	public List<SysConfigTeacher> getJson(HttpServletRequest request,
			Model model, @PathVariable Integer moduleId,
			@PathVariable String teacherType) {
		Users user = WebUtils.getCurrentUser(request);

		List<SysConfigTeacher> teachers = null;
		// 判断当前老师类型查询对应的老师列表
		if (teacherType.equals(Constant.PERSON_TEACHER)) {
			teachers = sysConfigTeacherServiceImpl
					.findTeacherBySchoolAndModuleIdAndLesson(
							user.getSchoolId(), moduleId, teacherType);
		} else {
			teachers = sysConfigTeacherServiceImpl.findNotTeacherBySchoolAndModuleId(user.getSchoolId(),
							moduleId);
		}

		return teachers;
	}
	/**
	 * 
	 * Class Name: SysConfigTeacherController.java
	 * @Description: 查询公司及校区下的所有老师
	 * @author yuchanglong
	 * @date 2015年6月6日 下午4:01:57
	 * @version 1.0
	 * @param request
	 * @param teacherType
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/{teacherType}")
	public List<SysConfigTeacher> getJsonForAll(HttpServletRequest request,@PathVariable String teacherType){
		List<SysConfigTeacher> teachers = null;
		Users user = WebUtils.getCurrentUser(request);
		SysConfigTeacher configTeacher = new SysConfigTeacher();
		configTeacher.setCompanyId(user.getCompanyId());
		configTeacher.setSchoolId(user.getSchoolId());
		configTeacher.setTeacherType(teacherType);
		boolean flag = companyFunctionSetServiceImpl.isCurrentFuSheng(WebUtils.getCurrentCompanyId());
		Users currentUser = WebUtils.getCurrentUser();
		Subject subject = SecurityUtils.getSubject();
		List<SysConfigTeacher> list = new ArrayList<SysConfigTeacher>();
		if(flag && subject.hasRole("排课老师") && !(subject.hasRole("机构管理员")||subject.hasRole("分校管理员")||subject.hasRole("运营"))){
			List<SysConfigTeacher> teacherList = sysConfigTeacherServiceImpl.findTeachersByUserId(currentUser.getId());
			if(teacherList!=null && teacherList.size()>0)
			{
				list.add(teacherList.get(0));
				return list;
			}else{
				return null;
			}
		}
		/*if((subject.hasRole("直播老师")&&subject.hasRole("排课老师"))&&!(subject.hasRole("机构管理员")||subject.hasRole("分校管理员")||subject.hasRole("运营")))
	        {
	        	configTeacher.setUserId(user.getId());
	        }*/
		teachers = sysConfigTeacherServiceImpl.findSysConfigTeacherByCompany(configTeacher);
		
		return teachers;
	}
	/**
	 * 
	 * Class Name: SysConfigTeacherController.java
	 * @Description: 排课时新增教师(插入SysConfigTeacher表和SysConfigTeacherLesson表)
	 * @author yuchanglong
	 * @date 2015年6月6日 下午4:46:49
	 * @version 1.0
	 * @param request
	 * @param sysConfigTeacher
	 * @param sysConfigTeacherLesson
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/insertTAndTL")
	public String insertTAndTL(HttpServletRequest request,SysConfigTeacher sysConfigTeacher,
			SysConfigTeacherLesson sysConfigTeacherLesson){
		
		SysConfigTeacher sct = new SysConfigTeacher();
		sct.setMobile(sysConfigTeacher.getMobile());
		sct.setCompanyId(WebUtils.getCurrentCompanyId());
		
		int count = sysConfigTeacherServiceImpl.findMobileCount(sct);
		if(count > 0){
			return "error";
		}
		sysConfigTeacher.setPwd(new Md5Hash(sysConfigTeacher.getPwd(),ByteSource.Util.bytes(sysConfigTeacher.getUserName()+"salt")).toHex());
		Users user = WebUtils.getCurrentUser(request);
		sysConfigTeacher.setCompanyId(user.getCompanyId());
		sysConfigTeacher.setSchoolId(user.getSchoolId());
		sysConfigTeacher.setTeacherType("PERSON_TEACHER");
		sysConfigTeacher.setDelFlag(0);
		sysConfigTeacher.setStatusCode("TEACHER_USERD");
		Date date = new Date();
		sysConfigTeacher.setCreateTime(date);
		sysConfigTeacher.setCreator(user.getId());
		
		sysConfigTeacherServiceImpl.insertTeacherAndTecherLesson(sysConfigTeacher, sysConfigTeacherLesson);
		
		return "success";
	}
	/**
	 * 
	 * Class Name: SysConfigTeacherController.java
	 * @Description: 排课时添加教务人员
	 * @author yuchanglong
	 * @date 2015年6月6日 下午4:48:37
	 * @version 1.0
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/insertT")
	public String insertT(SysConfigTeacher sysConfigTeacher,HttpServletRequest request){
		sysConfigTeacher.setPwd(new Md5Hash(sysConfigTeacher.getPwd(),ByteSource.Util.bytes(sysConfigTeacher.getUserName()+"salt")).toHex());
		Users user = WebUtils.getCurrentUser(request);
		sysConfigTeacher.setCompanyId(user.getCompanyId());
		sysConfigTeacher.setSchoolId(user.getSchoolId());
		sysConfigTeacher.setTeacherType("PERSON_ASSISTANT");
		sysConfigTeacher.setDelFlag(0);
		sysConfigTeacher.setStatusCode("TEACHER_USERD");
		Date date = new Date();
		sysConfigTeacher.setCreateTime(date);
		sysConfigTeacher.setCreator(user.getId());
		sysConfigTeacherServiceImpl.isnertTeaAndUse(sysConfigTeacher);
		return "success";
	}
	
	@RequestMapping(value = "/toEducationalIndex", method = RequestMethod.GET)
	public String toEducationalIndex(HttpServletRequest request, Model model) {
		List<SysConfigDict> dics = CacheService.getSysDicByDicCode(
				"TEACHER_STATUS", WebUtils.getCurrentCompanyId());
		model.addAttribute("dics", dics);
		List<SysConfigDict> teacherType = CacheService.getSysDicByDicCode(
				"PERSON_TYPE", WebUtils.getCurrentCompanyId());
		model.addAttribute("teacherType", teacherType);
		return "system/educational/educationalIndex";
	}

	@RequestMapping(value = "/toEducationalUpdate", method = RequestMethod.GET)
	public String toEducationalUpdate(HttpServletRequest request, Model model,
			SysConfigTeacher sysConfigTeacher) {
		SysConfigTeacher currentEducation = new SysConfigTeacher();
		if (sysConfigTeacher.getId() != null
				&& !sysConfigTeacher.getId().equals("")) {
			currentEducation = sysConfigTeacherServiceImpl
					.findSysConfigTeacherById(sysConfigTeacher.getId());
			model.addAttribute("methodType", "update");
		} else {
			model.addAttribute("methodType", "add");
		}
		model.addAttribute("currentEducation", currentEducation);
		List<SysConfigDict> dics = CacheService.getSysDicByDicCode(
				"TEACHER_STATUS", WebUtils.getCurrentCompanyId());
		model.addAttribute("dics", dics);
		List<SysConfigDict> dicSex = CacheService.getSysDicByDicCode(
				"SEX_CODE", WebUtils.getCurrentCompanyId());
		model.addAttribute("dicSex", dicSex);

		return "system/educational/educationalManage";
	}

	@RequestMapping(value = "/educationAjaxList", method = RequestMethod.POST)
	public String educationAjaxList(Model model, SysConfigTeacher search) {
		if (search == null) {
			search = new SysConfigTeacher();
			// search.setPageSize(20);
		}
		search.setSchoolId(WebUtils.getCurrentSchoolId());
		model.addAttribute("list",
				sysConfigTeacherServiceImpl.findSysConfigTeacherByPage(search));
		model.addAttribute("search", search);
		return "system/educational/educationalAjaxList";
	}

	@RequestMapping(value = "/toCoursesAarrangIndex", method = RequestMethod.GET)
	public String toCoursesAarrangIndex(HttpServletRequest request, Model model) {
		List<SysConfigSchool> schools = sysConfigSchoolServiceImpl
				.findAllSysConfigSchool(WebUtils.getCurrentSchoolId());
		model.addAttribute("schools", schools);
		return "system/coursesAarrangDetail/coursesAarrangIndex";
	}

	@RequestMapping(value = "/coursesAarrangIndexList", method = RequestMethod.POST)
	public String coursesAarrangIndexList(HttpServletRequest request,
			Model model, ClassModuleLessonVo classModuleLessonVo) {
		PageFinder<ClassModuleLessonVo> pageFinder = classModuleLessonServiceImpl
				.findClassModuleLessonByKeys(classModuleLessonVo);
		model.addAttribute("pageFinder", pageFinder);
		return "system/coursesAarrangDetail/coursesAarrangAjaxList";
	}

	@RequestMapping(value = "/coursesAarrangDetail", method = RequestMethod.POST)
	public String coursesAarrangDetail(HttpServletRequest request, Model model,
			ClassModuleLessonVo classModuleLessonVo) {
		ClassModuleLessonVo cmlv = classModuleLessonServiceImpl
				.findClassModuleLessonById(classModuleLessonVo);
		model.addAttribute("cmlv", cmlv);
		return "system/coursesAarrangDetail/coursesAarrangDetailAjax";
	}

	@ResponseBody
	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public Boolean check(SysConfigTeacher search) {
		List<SysConfigTeacher> teachers = sysConfigTeacherServiceImpl
				.queryForCheck(search);
		if (teachers == null || teachers.size() == 0) {
			return true;
		} else {
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
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}

	/**
	 * 
	 * Class Name: SysConfigTeacherController.java
	 * 
	 * @Description: 链接到教务信息主页面
	 * @author ycl
	 * @date 2015-5-5 下午2:18:02
	 * @modifier
	 * @modify-date 2015-5-5 下午2:18:02
	 * @version 1.0
	 * @return
	 */
	@RequestMapping(value = "/toEducationInfo")
	public String toEducationInfo() {

		return "resource/education/educationInfo";
	}

	/**
	 * 
	 * Class Name: SysConfigTeacherController.java
	 * 
	 * @Description: ajax异步加载教务人员信息
	 * @author ycl
	 * @date 2015-5-5 下午2:18:29
	 * @modifier
	 * @modify-date 2015-5-5 下午2:18:29
	 * @version 1.0
	 * @param model
	 * @param configTeacher
	 * @return
	 */
	@RequestMapping(value = "/educationAjaxInfo")
	public String educationAjaxInfo(Model model, SysConfigTeacher configTeacher) {
		configTeacher.setPageSize(10);
		Users user = WebUtils.getCurrentUser();
		configTeacher.setCompanyId(user.getCompanyId());
		configTeacher.setSchoolId(user.getSchoolId());
		PageFinder<SysConfigTeacher> pageFinder = sysConfigTeacherServiceImpl.findByCompanyPage(configTeacher);
		model.addAttribute("pageFinder", pageFinder);
		List<SysConfigDict> dictList = sysConfigDictServiceImpl.findAll();
		model.addAttribute("dictList", dictList);
		return "resource/education/educationAjaxInfo";
	}

	/**
	 * 
	 * Class Name: SysConfigTeacherController.java
	 * 
	 * @Description: 链接到添加或修改教务页面
	 * @author ycl
	 * @date 2015-5-5 下午2:16:39
	 * @modifier
	 * @modify-date 2015-5-5 下午2:16:39
	 * @version 1.0
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addEducation/{id}/{type}")
	public String addEducation(@PathVariable Integer id, Model model,@PathVariable String type) {
		SysConfigTeacher teacher = null;
		if (id > 0) {
			teacher = sysConfigTeacherServiceImpl.findSysConfigTeacherById(id);
		}
		if (teacher != null) {
			model.addAttribute("teacher", teacher);
		}
		model.addAttribute("type",type);
		return "resource/education/addEducation";
	}

	/**
	 * 
	 * Class Name: SysConfigTeacherController.java
	 * 
	 * @Description: 新增或修改页面根据手机号查询信息
	 * @author ycl
	 * @date 2015-5-5 下午2:19:11
	 * @modifier
	 * @modify-date 2015-5-5 下午2:19:11
	 * @version 1.0
	 * @param search
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/findTeacherByMobile")
	public String findTeacherByMobile(SysConfigTeacher search, Model model) {
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		SysConfigTeacher teacher = sysConfigTeacherServiceImpl
				.findTeacherIdByMobile(search);
		if (teacher != null) {
			model.addAttribute("teacher", teacher);
		}
		return "resource/education/addEducation";
	}

	@ResponseBody
	@RequestMapping(value = "/checkMobile")
	public String checkMobile(SysConfigTeacher search) {
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		SysConfigTeacher teacher = sysConfigTeacherServiceImpl
				.findTeacherIdByMobile(search);
		if (teacher != null) {
			return "success";
		}
		return "fail";
	}

	/**
	 * @Description: 获取本校的一级项目
	 * @author wzx
	 * @date 2015-5-4 上午10:52:15
	 * @version 1.0
	 * @return
	 */
	public List<SysConfigItem> obtainAll() {
		Users user = WebUtils.getCurrentUser();
		SysConfigItem search = new SysConfigItem();
		search.setDelFlag(0);
		search.setItemType(SysConfigConstant.ITEMTYPE_FIRST);
		if (user != null) {
			search.setCompanyId(user.getCompanyId());
			search.setSchoolId(user.getSchoolId());
		}

		List<SysConfigItem> firstItems = sysConfigItemServiceImpl
				.findItem(search);
		return firstItems;
	}

	/**
	 * @Description: 教室管理页面
	 * @author wzx
	 * @date 2015-5-4 下午2:19:40
	 * @version 1.0
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toTeacherIndex", method = RequestMethod.GET)
	public String toTeacherIndex(HttpServletRequest request, Model model) {
		// 查询当前分校中有哪些一级项目
		List<SysConfigItem> firstItems = sysConfigItemServiceImpl.findSysConfigItemByPid(SysConfigConstant.ITEMTYPE_FIRST, null, WebUtils.getCurrentCompanyId(), WebUtils.getCurrentSchoolId());
		model.addAttribute("items", firstItems);
		return "resource/teacher/teacherIndex";
	}

	/**
	 * @Description: ajax加载改项目下的所有的教师情况
	 * @author wzx
	 * @date 2015-5-4 下午2:20:00
	 * @version 1.0
	 * @param request
	 * @param model
	 * @param sysConfigTeacher
	 * @return
	 */
	@RequestMapping(value = "/teacherAjaxList", method = RequestMethod.POST)
	public String teacherAjaxList(HttpServletRequest request, Model model,
								  SysConfigTeacher teacher) {
		// sysConfigTeacher.setSchoolId(WebUtils.getCurrentSchoolId());
		// PageFinder<SysConfigTeacher>
		// pageFinder=sysConfigTeacherServiceImpl.queryTeachersByKeys(sysConfigTeacher);
		// model.addAttribute("pageFinder",pageFinder);

		// 根据项目ID查询所有的老师
		teacher.setPageSize(10);
		Integer itemOneId = teacher.getItemOneId();
		PageFinder<SysConfigTeacher> pageFinder = null;
		if(itemOneId != null){
			if(itemOneId == 0){
				Users user = WebUtils.getCurrentUser();
				teacher.setCompanyId(user.getCompanyId());
				teacher.setTeacherType(Constant.PERSON_TEACHER);
				pageFinder = sysConfigTeacherServiceImpl.findTeacherPage(teacher);
			}else if(itemOneId > 0){
				Users user = WebUtils.getCurrentUser();
				teacher.setCompanyId(user.getCompanyId());
				teacher.setTeacherType(Constant.PERSON_TEACHER);
				pageFinder = sysConfigTeacherServiceImpl.findTeacherPage(teacher);
			}
		}

		model.addAttribute("pageFinder", pageFinder);
		return "resource/teacher/teacherAjaxList";
	}

	/**
	 * @Description: 根据老师ID获取对应的模块信息
	 * @author wzx
	 * @date 2015-5-5 上午10:56:39
	 * @version 1.0
	 * @param request
	 * @param toPath
	 * @param model
	 * @param teacherId
	 * @return
	 */
	@RequestMapping(value = "/toDetail/{teacherId}", method = RequestMethod.GET)
	public String toDetail(HttpServletRequest request, String toPath,
			Model model, @PathVariable Integer teacherId) {
		List<SysConfigTeacherLessonVo> sysConfigTeacherLessonVos = new ArrayList<SysConfigTeacherLessonVo>();
		if (teacherId != null) {
			sysConfigTeacherLessonVos = sysConfigTeacherLessonServiceImpl
					.findByTeacherId(teacherId);
		}
		model.addAttribute("sysConfigTeacherLessonVos",
				sysConfigTeacherLessonVos);
		return "resource/teacher/teacherLessonAjaxList";
	}

	/**
	 * @Description:
	 * @author wzx
	 * @date 2015-5-5 下午3:29:19
	 * @version 1.0
	 * @param request
	 * @param model
	 * @param sysConfigTeacher
	 * @return
	 */
	@RequestMapping(value = "/manage/{teacherId}", method = RequestMethod.GET)
	public String toTeacherUpdate(HttpServletRequest request, Model model,
			@PathVariable Integer teacherId) {
		Users user = WebUtils.getCurrentUser(request);
		// 根据老师ID查询对应的老师
		SysConfigTeacher teacher = sysConfigTeacherServiceImpl.findTeacherAndUserById(teacherId);
		
		// 根据老师ID查询该老师所属的公司所有的一级二级项目
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("companyId", user.getCompanyId());
		param.put("schoolId", user.getSchoolId());
		param.put("itemType","1");
		//根据公司id 和学校id 查询 一级项目
		List<SysConfigItem> items = sysConfigItemServiceImpl.findItemBySchoolCompanyId(param);
		
//		List<SysConfigItem> items = sysConfigItemServiceImpl.findItemBySchoolCompanyId(null, user.getCompanyId());

		List<SysConfigItem> firstItems = new ArrayList<SysConfigItem>();
		List<SysConfigItem> secondItems = new ArrayList<SysConfigItem>();

		if (items != null && items.size() > 0) {
			for (SysConfigItem item : items) {
				if (item.getItemType().equals("1")) {
					firstItems.add(item);
				} else if (item.getItemType().equals("2")) {
					secondItems.add(item);
				}
			}
		}

		// 循环二级项目放入map中
//		Map<Integer,List<SysConfigItem>> secondItemMap = new TreeMap<Integer, List<SysConfigItem>>();
		//指定排序器  
        TreeMap<Integer, List<SysConfigItem>> secondItemMap = new TreeMap<Integer, List<SysConfigItem>>(new Comparator<Integer>(){  
			@Override
			public int compare(Integer arg0, Integer arg1) {
				return arg0.compareTo(arg1);
			}     
        });
        
		List<SysConfigItem> dateList = null;
		
		for (SysConfigItem item : secondItems) {
			Integer keyMap = item.getParentId();
			if (secondItemMap.containsKey(keyMap)) {
				dateList = secondItemMap.get(keyMap);
				dateList.add(item);
			} else {
				dateList = new ArrayList<SysConfigItem>();
				dateList.add(item);
				secondItemMap.put(keyMap, dateList);
			}
		}

		List<SysConfigDict> schools=sysConfigDictServiceImpl.findByDicCode("EDU_SCHOOL");
		model.addAttribute("firstItems", firstItems);
		model.addAttribute("schools", schools);
		model.addAttribute("companyId", WebUtils.getCurrentCompanyId());
		model.addAttribute("secondItemMap", secondItemMap);
		model.addAttribute("imgUrl", "http://"+properties.getProjectImageUrl()+"/");
		if(teacher == null){
			teacher = new SysConfigTeacher();
			teacher.setId(0);
			model.addAttribute("teacher", teacher);
			return "resource/teacher/teacherManageAdd";
		}else{
			SysConfigTeacherLesson les = sysConfigTeacherLessonServiceImpl.findSysConfigTeacherLessonByTeaId(teacherId);
			if(les != null && les.getItemOneId() != null && les.getItemOneId().toString().length() > 0){
				teacher.setItemOneId(les.getItemOneId());
			}
			if(les != null && les.getItemSecondId() != null && les.getItemSecondId().toString().length() > 0){
				teacher.setItemSecondId(les.getItemSecondId().toString());
			}
		}
//		// 根据老师ID查询老师对应的可以授课的模块
//		List<SysConfigTeacherLessonVo> sysConfigTeacherLessonVos = sysConfigTeacherLessonServiceImpl
//				.findByTeacherId(teacherId);
//		
		model.addAttribute("teacher", teacher);
//		model.addAttribute("lessonsVo", sysConfigTeacherLessonVos);
		return "resource/teacher/teacherManage";
	}
	/**
	 * 保存图片
	 * @param multiPartRquest
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/savePic",method=RequestMethod.POST)
	public String queryPic(MultipartRequest multiPartRquest,HttpServletRequest req){
		MultipartFile multipartFile = multiPartRquest.getFile("imgData");
		String realPath=null;
		try {
			realPath = FileUtil.upload(multipartFile, "temp", WebUtils.getCurrentCompanyId()+"");
		} catch (Exception e) {
			log.info("文件上传失败!",e);
			e.printStackTrace();
		}
		String url="http://"+properties.getProjectImageUrl()+"/"+realPath;
		/*String url="/images/video-icons.png";*/
		return "{\"url\":\""+url+"\"}";
	}
	
	@ResponseBody
	@RequestMapping(value="/saveCutPic",method=RequestMethod.POST)
	public String saveCutPic(HttpServletRequest request){
		log.info("初始化截图开始===============");
		Resource resource = new ClassPathResource("config.properties");
		Properties props=null;
		try{
			props=PropertiesLoaderUtils.loadProperties(resource);
		}catch(Exception e){
			log.error(e,e);
			e.printStackTrace();
		}
		String path=request.getParameter("path");
		String fileName=path.substring(path.lastIndexOf("/")+1);
		String tempPath=props.getProperty("server.imageupload.tempPath")+"/source/"+fileName;
		String target=props.getProperty("server.imageupload.tempPath")+"/target/"+fileName;
		String header="http://"+props.getProperty("yunduoketang.oss.imagedomain")+"/";
		
		path=path.replace(header, "");
		System.out.println("oss临时文件路径["+path+"]=====本地磁盘临时文件路径["+tempPath+"]======切图后临时文件路径["+target+"]");
		FileUtil.download("temp", path,tempPath);
		//选中尺寸
		double x=Double.parseDouble(request.getParameter("x"));
		double y=Double.parseDouble(request.getParameter("y"));
		double w=Double.parseDouble(request.getParameter("w"));
		double h=Double.parseDouble(request.getParameter("h"));
		BufferedImage img =null;
		try{
			 img = ImageIO.read(new File(tempPath));
		}catch(Exception e){
			log.error("读取图片失败:"+e,e);
			e.printStackTrace();
		}
		
		//原图尺寸
		double realW=img.getWidth();
		double realH=img.getHeight();
		//示例图尺寸
		double slW=0;
		double slH=0;
		if(realW>realH){
			//过宽
			slH=300 * realH/realW;
			slW=300;
		}else{
			//过高
			slH=300;
			slW=300 * realW/realH;
		}
		//原图所选中位置和区域
		
		int xx=(new   Double(x*realW/slW)).intValue();	
		int yy=(new   Double(y*realH/slH)).intValue();
		int ww=(new   Double(w*realW/slW)).intValue();
		int hh=(new   Double(h*realH/slH)).intValue();
		System.out.println("选中区域:["+x+","+y+","+w+","+h+"]----原图选中区域:["+xx+","+yy+","+ww+","+hh+"]");
		//在原图中切图
		String cutImgPath=ImageUtils.cutImage(tempPath,target,xx,yy,ww,hh);
		//切好的图缩放到规定比例
		ImageUtils.resize(target, target, 446);
		log.info("截图完成===============");
		log.info("上传图片开始===============");
		String realPath=null;
		try {
			realPath=FileUtil.upload(cutImgPath,"headpic", WebUtils.getCurrentCompanyId()+"");
		} catch (Exception e) {
			log.error("上传文件失败",e);
			e.printStackTrace();
		}
		log.info("上传图片后路径==============="+realPath);
//		FileUtil.deleteFile(target);
//		FileUtil.deleteFile(cutImgPath);
	
		
		
		String picUrl=properties.getProjectImageUrl()+realPath;
		log.info("图片回显路径==============="+picUrl);
		
		
		return realPath;
	}
	/**
	 * 
	 * Class Name: SysConfigTeacherController.java
	 * @Description: 删除教师时查询教师未结束课程
	 * @author ycl
	 * @date 2015-5-8 下午9:20:43
	 * @modifier
	 * @modify-date 2015-5-8 下午9:20:43
	 * @version 1.0
	 * @param classModuleLesson
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value="/teahcerLesson")
	public List<ClassModuleLesson> teahcerLesson(ClassModuleLesson classModuleLesson,Model model) throws ParseException{
		List<ClassModuleLesson> lesson = classModuleLessonServiceImpl.findLessonByTeachers(classModuleLesson);
		List<ClassModuleLesson> newLesson = new ArrayList<ClassModuleLesson>();
		int i = 0;
		for (ClassModuleLesson lo : lesson) {
			if(i<5){
				newLesson.add(lo);
			}else{
				break;
			}
			i++;
		}
		return newLesson;
	}

	/**
	 * 
	 * Class Name: SysConfigTeacherController.java
	 * @Description: 根据教师名称查询教师信息
	 * @author zhang.zx
	 * @date 2015年5月19日 下午8:54:03
	 * @modifier
	 * @modify-date 2015年5月19日 下午8:54:03
	 * @version 1.0
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryTeacherDetail",method=RequestMethod.POST)
	public List<SysConfigTeachersVo> queryTeacherDetailByName(String teacherName,Integer ccompanyId,Integer teacherType, HttpServletRequest request){
		SysConfigTeachersVo sysConfigTeacher=new SysConfigTeachersVo();
		Integer companyId=ccompanyId==null?WebUtils.getCurrentCompanyId():ccompanyId;
		sysConfigTeacher.setCompanyId(companyId);
		sysConfigTeacher.setTeacherType(teacherType);
		sysConfigTeacher.setTeacherName(teacherName);
		List<SysConfigTeachersVo> arr= sysConfigTeacherServiceImpl.findSysConfigTeachersByName(sysConfigTeacher);
		return arr;
	}
	
	//班型详情中添加教师信息
	@ResponseBody
	@RequestMapping(value="/addTeacher",method=RequestMethod.POST)
	public SysConfigTeacher addTeacherDetail(String tname,String password,String realName,String tMobile){
		Users user=new Users();
		user.setUsername(tname);
		user.setRealName(realName);
		user.setMobile(tMobile);
		user.setCompanyId(WebUtils.getCurrentCompanyId());
		user.setSchoolId(WebUtils.getCurrentSchoolId());
		user.setStatus(1);
		user.setPassword(new Md5Hash(password,ByteSource.Util.bytes(tname+"salt")).toHex());
		if(ParameterUtil.isUserName(tname)){
			Users u=new Users();
			u.setUsername(tname);
			List<Users> arr=userServiceImpl.queryuserIsExist(u);
 			if(null!=arr&&arr.size()>0){
 				return new SysConfigTeacher();
 			}
		}else{
			return new SysConfigTeacher();
		}
		Integer tid=sysConfigTeacherServiceImpl.addClassTypeTeacher(user);
		return sysConfigTeacherServiceImpl.findSysConfigTeacherById(tid);
	}
	
	//班型详情中添加教师信息
	@ResponseBody
	@RequestMapping(value="/checkTeacherName",method=RequestMethod.POST)
	public String checkTeacher(HttpServletRequest request, String teacherName, String teacherType, Integer teacherId){
		Integer schoolId = WebUtils.getCurrentSchoolId();
		//根据当前名称/ 用户所属校区名称查询当前名称是否已经存在
		if(null != teacherName && ! "".equals(teacherName)){
			SysConfigTeacher teacher = new SysConfigTeacher();
			teacher.setName(teacherName);
			teacher.setSchoolId(schoolId);
			teacher.setTeacherType(teacherType);
			teacher.setDelFlag(0);
			if(ParameterUtil.isChinaeseAndWord(teacherName) ){
				List<SysConfigTeacher> teachers = sysConfigTeacherServiceImpl.findSysConfigTeacherByParam(teacher);
				if(teachers != null && teachers.size() > 0){
					if(teachers.get(0) != null && teacherId != null && teachers.get(0).getId().equals(teacherId)){
						return "true";
					}else{
						return "该老师姓名已存在";
					}
				}
				return "true";
			}else{
				return "老师姓名由汉字、字母 、数字、下划线组成";
			}
 		}else{
 			return "老师姓名不能为空";
 		}
	}
	
	//班型详情中添加教师信息
	@ResponseBody
	@RequestMapping(value="/checkMobileNum",method=RequestMethod.POST)
	public String checkMobileNum(HttpServletRequest request, String mobile, String teacherType, Integer teacherId ){
		Integer schoolId = WebUtils.getCurrentSchoolId();
		//根据当前名称/ 用户所属校区名称查询当前名称是否已经存在
		if(null != mobile && ! "".equals(mobile)){
			SysConfigTeacher teacher = new SysConfigTeacher();
			teacher.setMobile(mobile);
			teacher.setSchoolId(schoolId);
			teacher.setTeacherType(teacherType);
			teacher.setDelFlag(0);
			
			if(ParameterUtil.isMobilePhone(mobile)){
				List<SysConfigTeacher> teachers = sysConfigTeacherServiceImpl.findSysConfigTeacherByParam(teacher);
				if(teachers != null && teachers.size() > 0){
					if(teachers.get(0) != null && teacherId != null && teachers.get(0).getId().equals(teacherId)){
						return "true";
					}else{
						return "该手机号已存在";
					}
				}
				return "true";
			}else{
				return "手机号格式不正确";
			}
 		}else{
 			return "手机号不能为空";
 		}
	}
	
	@ResponseBody
	@RequestMapping(value="/checkUserMobileNum",method=RequestMethod.POST)
	public String checkUserMobileNum(HttpServletRequest request, String mobile){
		//根据当前名称/ 用户所属校区名称查询当前名称是否已经存在
		if(null != mobile && ! "".equals(mobile)){
			Users u = new Users();
			u.setMobile(mobile);
			
			if(ParameterUtil.isMobilePhone(mobile)){
				List<Users> userList = userServiceImpl.findUserByCondition(u);
				if(userList != null && userList.size() > 0){
						return "该手机号已存在";
				}
				return "true";
			}else{
				return "手机号格式不正确";
			}
 		}else{
 			return "手机号不能为空";
 		}
	}
	
	//班型详情中添加教师信息
	@ResponseBody
	@RequestMapping(value="/checkMobiles",method=RequestMethod.POST)
	public Boolean checkMobiles(HttpServletRequest request, String mobile){
		Integer schoolId = WebUtils.getCurrentSchoolId();
		//根据当前名称/ 用户所属校区名称查询当前名称是否已经存在
			SysConfigTeacher teacher = new SysConfigTeacher();
			teacher.setMobile(mobile);
			teacher.setSchoolId(schoolId);
			teacher.setTeacherType("PERSON_TEACHER");
			
			if(ParameterUtil.isMobilePhone(mobile)){
				List<SysConfigTeacher> teachers = sysConfigTeacherServiceImpl.findSysConfigTeacherByParam(teacher);
				if(teachers != null && teachers.size() > 0){
					if(teachers.get(0) != null){
						return false;
					}
				}
			}else{
				return false;
			}
			return true;
	}
	/**
	 * 
	 * Class Name: SysConfigTeacherController.java
	 * @Description: 删除时检查老师权限（管理员不允许删除）
	 * @author yuchanglong
	 * @date 2016年1月5日 下午4:54:08
	 * @version 1.0
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/checkTeacherAuth")
	public Boolean checkTeacherAuth(Integer teacherId){
		SysConfigTeacher teacher = sysConfigTeacherServiceImpl.findSysConfigTeacherById(teacherId);
		Integer userId = teacher.getUserId();
		List<AuthUserRole> roles = authUserRoleServiceImpl.findByRoleId(userId.toString());
		String roleUid = "";
		for (AuthUserRole ar : roles) {
			roleUid = ar.getRoleUid();
			if(!roleUid.equals("6")){
				//不允许删除
				return true;
			}
		}
		return false;
	}
	
	@ResponseBody
	@RequestMapping(value="/queryData")
	public PageFinder<TeachersVo> queryTeachersData(TeachersVo teacher,Model model,HttpServletRequest request){
		teacher.setCompanyId(WebUtils.getCurrentCompanyId());
		teacher.setPageSize(12);
		String url="http://"+properties.getProjectImageUrl()+"/";
		PageFinder<TeachersVo> pageFinder=sysConfigTeacherServiceImpl.findTeachersBycondition(teacher);
		List<TeachersVo> arr=pageFinder.getData();
		for(TeachersVo t:arr){
			if(null!=t&&!"".equals(t.getHeadpicUrl())&&null!=t.getHeadpicUrl()){
				t.setHeadpicUrl(url+t.getHeadpicUrl());
			}
		}
		return pageFinder;
	}
	
	

	@ResponseBody
	@RequestMapping(value="/updateSortId")
	public String updateSortId(SysConfigTeacher teacher,String type,Model model,HttpServletRequest request){
		int count;
		if(teacher.getSortId()==0){
			teacher.setSortId(null);
		}
		if(type.equals("new")){
			count = checkSortCount();
			if(count>=8){
				return "排序设置已超过8个，请修改后再设置";
			}
		}
		int index = sysConfigTeacherServiceImpl.updateSortId(teacher);
		if(index ==1){
			return "保存成功";  
		}else{
			return "保存失败"; 
		}
		
	}
	public int checkSortCount(){
		return sysConfigTeacherServiceImpl.checkSortCount();
	}
}
