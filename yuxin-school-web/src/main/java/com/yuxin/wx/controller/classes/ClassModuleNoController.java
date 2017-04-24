package com.yuxin.wx.controller.classes;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
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

import com.yuxin.wx.api.classes.IClassModuleLessonService;
import com.yuxin.wx.api.classes.IClassModuleNoService;
import com.yuxin.wx.api.classes.IClassModuleService;
import com.yuxin.wx.api.classes.IClassTypeService;
import com.yuxin.wx.api.company.ICompanyFunctionSetService;
import com.yuxin.wx.api.company.ICompanyMemberServiceService;
import com.yuxin.wx.api.student.IStudentPaySlaveService;
import com.yuxin.wx.api.system.ISysConfigCampusService;
import com.yuxin.wx.api.system.ISysConfigItemService;
import com.yuxin.wx.api.system.ISysConfigSchoolService;
import com.yuxin.wx.api.system.ISysConfigTeacherService;
import com.yuxin.wx.api.system.ISysConfigTermService;
import com.yuxin.wx.classes.impl.GenseeLiveRoomServiceImpl;
import com.yuxin.wx.common.JsonMsg;
import com.yuxin.wx.common.LiveRoomConstant;
import com.yuxin.wx.common.SysLoader;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.classes.ClassModuleLesson;
import com.yuxin.wx.model.classes.ClassModuleNo;
import com.yuxin.wx.model.classes.ClassType;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.model.student.StudentPaySlave;
import com.yuxin.wx.model.system.SysConfigDict;
import com.yuxin.wx.model.system.SysConfigItem;
import com.yuxin.wx.model.system.SysConfigTerm;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.util.HttpPostRequest;
import com.yuxin.wx.util.MD5;
import com.yuxin.wx.utils.DateUtil;
import com.yuxin.wx.utils.PropertiesUtil;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.classes.ClassModuleNoListVo;
import com.yuxin.wx.vo.classes.ClassModuleNoVo;
import com.yuxin.wx.vo.classes.LessonsListVo;
import com.yuxin.wx.vo.classes.ModuleNoAndLessonVo;

/**
 * Controller of ClassModuleNo
 *
 * @author wang.zx
 * @date 2014-12-5
 */
@Controller
@RequestMapping("/classModuleNo")
public class ClassModuleNoController {
	Log log = LogFactory.getLog("log");

	@Autowired
	private IClassModuleNoService classModuleNoServiceImpl;
	@Autowired
	private IClassModuleLessonService classModuleLessonService;
	@Autowired
	private IClassModuleService classModuleServiceImpl;
	@Autowired
	private ISysConfigTermService sysConfigTermServiceImpl;
	@Autowired
	private IStudentPaySlaveService studentPaySlaveServiceImpl;
	@Autowired
	private ISysConfigItemService sysConfigItemServiceImpl;
	@Autowired
	private ISysConfigSchoolService sysConfigSchoolServiceImpl;
	@Autowired
	private ISysConfigCampusService sysConfigCampusServiceImpl;
	@Autowired
	private IClassTypeService classTypeServiceImpl;
	@Autowired
	private ICompanyMemberServiceService companyMemberServiceServiceImpl;
	@Autowired
	private GenseeLiveRoomServiceImpl genseeLiveRoomServiceImpl;
	@Autowired
	private PropertiesUtil properties;
	@Autowired
	private ISysConfigTeacherService sysConfigTeacherServiceImpl;
	@Autowired
	private ICompanyFunctionSetService companyFunctionSetServiceImpl;

	SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * @Description: 直接跳转到添加班号的首页
	 * @author wzx
	 * @date 2015-6-2 上午10:27:55
	 * @version 1.0
	 * @param model
	 * @param classModuleNo
	 * @return
	 */
	@RequestMapping(value = "/add")
	public String add(Model model, ClassModuleNo classModuleNo) {
		model.addAttribute("classTypeId", 0);
		return "module/addClassModuleNo";
	}

	/**
	 * @Description: 根据条件跳转到
	 * @author wzx
	 * @date 2015-6-2 上午10:34:43
	 * @version 1.0
	 * @param request
	 * @param itemOneId
	 * @param itemSecondId
	 * @param examTerm
	 * @return
	 */
	@RequestMapping(value = "addByCondition/{itemOneId}/{itemSecondId}/{examTerm}")
	public String addByCondition(HttpServletRequest request, Model model, @PathVariable Integer itemOneId, @PathVariable Integer itemSecondId,
	        @PathVariable Integer examTerm) {
		Users user = WebUtils.getCurrentUser(request);

		if (itemOneId != null && itemOneId > 0) {
			// 查询出该校区所有的一级项目
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("companyId", user.getCompanyId());
			param.put("schoolId", user.getSchoolId());
			// 根据公司id 和学校id 查询 一级项目
			List<SysConfigItem> items = sysConfigItemServiceImpl.findItemBySchoolCompanyId(param);

			List<SysConfigItem> firstItems = new ArrayList<SysConfigItem>();
			List<SysConfigItem> secondItems = new ArrayList<SysConfigItem>();
			;
			if (items != null && items.size() > 0) {
				for (SysConfigItem item : items) {
					if (item.getItemType().equals("1")) {
						firstItems.add(item);
					} else if (item.getItemType().equals("2") && item.getParentId().equals(itemOneId)) {
						secondItems.add(item);
					}
				}
			}

			// 根据一级项目查询对应的考期
			List<SysConfigTerm> terms = sysConfigTermServiceImpl.findTermByOneItemId(itemOneId);
			model.addAttribute("firstItems", firstItems);
			model.addAttribute("secondItems", secondItems);
			model.addAttribute("terms", terms);
			model.addAttribute("itemOneId", itemOneId);
			model.addAttribute("itemSecondId", itemSecondId);
			model.addAttribute("examTerm", examTerm);

		} else {
			return "module/addClassModuleNo";
		}

		return "module/addClassModuleNoWithCondition";
	}

	@ResponseBody
	@RequestMapping(value = "findModuleNoByName")
	public String findModuleNoByName(HttpServletRequest request, ClassModuleNo classModuleNo) {
		Users user = WebUtils.getCurrentUser(request);

		classModuleNo.setCompanyId(user.getCompanyId());
		List<ClassModuleNo> modules = classModuleNoServiceImpl.findClassModuleNoByPage(classModuleNo);
		if (modules != null && modules.size() > 0) {
			ClassModuleNo moduleNo = modules.get(0);
			if (!moduleNo.getId().equals(classModuleNo.getId())) {
				return "exist";
			} else {
				return "not_exist";
			}
		}
		return "not_exist";
	}

	/**
	 * @Description: 保存班号的基本信息
	 * @author wzx
	 * @date 2015-5-15 下午4:14:26
	 * @version 1.0
	 * @param request
	 * @param model
	 * @param module
	 * @return
	 */
	@RequestMapping(value = "addModule", method = RequestMethod.POST)
	public String addModule(HttpServletRequest request, Model model, ClassModuleNo moduleNo, String next, Integer classTypeId, String methed,
	        String courselable) {
		Users user = WebUtils.getCurrentUser();
		if (user == null) {
			return "redirect:/";
		}
		if (moduleNo != null && moduleNo.getId() != null && moduleNo.getId() > 0) {
			classModuleNoServiceImpl.update(moduleNo);
		} else {
			moduleNo.setCreateTime(new Date());
			moduleNo.setCreator(user.getId());
			moduleNo.setDelFlag(0);
			moduleNo.setCompanyId(user.getCompanyId());
			moduleNo.setClassTeachType(SysLoader.dictName2Code(moduleNo.getClassTeachType()));
			moduleNo.setLessonPlan(0);
			moduleNo.setLessonTotal(0);
			classModuleNoServiceImpl.insert(moduleNo);
		}
		// 如何为保存并继续则进入到下一个页面，如果则仅是保存则返回到列表页面
		if (next != null && next.length() > 0) {
			return "redirect:moduleLessons/" + moduleNo.getId();
		} else if (classTypeId != null && classTypeId > 0) {
			if (courselable != null && !courselable.equals("")) {
				return "redirect:/editClass/editCourseList/" + classTypeId + "/" + courselable;
			} else {
				return "redirect:/classType/showClassTypeStu?id=" + classTypeId + "&mark=saveandshou&lable=" + methed;
			}
			// showClassCourseDetail
		} else {
			return "redirect:/classModuleLesson/classes";
		}
	}

	/**
	 * @Description: 根据班型添加班号
	 * @author wzx
	 * @date 2015-7-2 下午3:26:59
	 * @version 1.0
	 * @param request
	 * @param classTypeId
	 * @return
	 */
	@RequestMapping(value = "addModuleNo/{classTypeId}/{moduleId}/{methed}")
	public String addModuleNoByClassType(HttpServletRequest request, Model model, @PathVariable Integer classTypeId, @PathVariable Integer moduleId,
	        @PathVariable String methed) {
		Users user = WebUtils.getCurrentUser();
		if (user == null) {
			return "redirect:/";
		}
		String lable = request.getParameter("lable");
		if (null != lable && !"".equals(lable)) {
			model.addAttribute("lable", lable);
		}
		if (classTypeId != null && classTypeId > 0) {
			model.addAttribute("classTypeId", classTypeId);
			// 根据班型ID获取对应的班型 信息
			ClassType classType = classTypeServiceImpl.findClassTypeById(classTypeId);
			model.addAttribute("classType", classType);

			// 根据一级项目查询对应的考期
			SysConfigTerm search = new SysConfigTerm();
			search.setItemOneId(classType.getItemOneId());
			search.setCompanyId(WebUtils.getCurrentCompanyId());
			List<SysConfigTerm> terms = sysConfigTermServiceImpl.findSysConfigTermList(search);
			model.addAttribute("terms", terms);

			// 查询出班号对应的一级、二级项名称
			SysConfigItem itemOne = sysConfigItemServiceImpl.findSysConfigItemById(classType.getItemOneId());
			SysConfigItem itemTwo = sysConfigItemServiceImpl.findSysConfigItemById(classType.getItemSecondId());

			model.addAttribute("itemOneName", itemOne.getItemName());
			model.addAttribute("itemTwoName", itemTwo.getItemName());

			// 根据moduleId 获取对应的模块的信息
			ClassModule module = classModuleServiceImpl.findClassModuleById(moduleId);

			if (module != null) {
				SysConfigDict type = SysLoader.getDict(module.getModuleType());
				SysConfigDict method = SysLoader.getDict(module.getTeachMethod());
				if (type != null && type.getItemValue() != null) {
					module.setModuleType(type.getItemValue());
				} else {
					module.setModuleType("");
				}
				if (method != null && method.getItemValue() != null) {
					module.setTeachMethod(method.getItemValue());
				} else {
					module.setTeachMethod("");
				}
				module.setDelFlag(0);
			}

			model.addAttribute("module", module);
			model.addAttribute("methed", methed);
		} else {
			model.addAttribute("classTypeId", 0);
		}
		model.addAttribute("editModuleNo", 0);
		return "module/addClassModuleNo";
	}

	/**
	 * @Description: 编辑班号
	 * @author wzx
	 * @date 2015-7-2 下午3:26:39
	 * @version 1.0
	 * @param request
	 * @param model
	 * @param moduleNoId
	 * @return
	 */
	@RequestMapping(value = "editModule/{moduleNoId}")
	public String editModule(HttpServletRequest request, Model model, @PathVariable Integer moduleNoId) {
		Users user = WebUtils.getCurrentUser();
		if (user == null) {
			return "redirect:/";
		}
		String classTypeId = request.getParameter("classTypeId");
		String methed = request.getParameter("methed");
		String lable = request.getParameter("lable");
		if (classTypeId == null) {
			classTypeId = String.valueOf(0);
		}
		model.addAttribute("classTypeId", classTypeId);
		model.addAttribute("methed", methed);
		model.addAttribute("editModuleNo", 1);
		if (null != lable && !"".equals(lable)) {
			model.addAttribute("lable", lable);
		}
		if (moduleNoId != null && moduleNoId > 0) {
			Integer id = moduleNoId;
			// 根据ID获取班号
			ClassModuleNoListVo moduleNoVo = classModuleNoServiceImpl.findModuleNoListVoById(id);
			model.addAttribute("moduleNoVo", moduleNoVo);
			return "module/editClassModuleNo";
		}
		return "module/addClassModuleNo";
	}

	@RequestMapping(value = "moduleLessons/{moduleNoId}")
	public String addModuleAndLessons(HttpServletRequest request, Model model, @PathVariable Integer moduleNoId) {
		ClassModuleNo moduleNo = classModuleNoServiceImpl.findClassModuleNoById(moduleNoId);
		model.addAttribute("startDate", dateFormat1.format(moduleNo.getStartDate()));
		// 根据当前开班日期计算出对应的星期几
		int currentDate = DateUtil.getWeekOfDay(moduleNo.getStartDate());
		log.info("当前班号的开课日期对应星期："+currentDate);
		model.addAttribute("currentDate", currentDate);

		// 判断模块名称中是否包含-, 然后取第一个
		String moduleName = moduleNo.getName();
		if (moduleName.indexOf("-") > -1) {
			moduleName = moduleName.split("-")[0];
		}
		model.addAttribute("moduleName", moduleName);
		model.addAttribute("classTeachType", moduleNo.getClassTeachType());

		// 如果是新增的话，需要判断当前公司的直播服务提供商是哪家
		CompanyMemberService liveProvider = companyMemberServiceServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
		model.addAttribute("liveProvider", liveProvider.getLiveServiceProvider());

		// 判断当前班号是否创建过排课列表
		List<ClassModuleLesson> lessons = classModuleLessonService.findClassModuleLessonByModuleNoId(moduleNoId);
		model.addAttribute("totalHours", moduleNo.getTotalHours());
		if (lessons != null && lessons.size() > 0) {
			model.addAttribute("live_Class_Type", lessons.get(0).getLiveClassType());
			model.addAttribute("moduleNo", moduleNo);
			model.addAttribute("lessons", lessons);
			model.addAttribute("lessonsLength", lessons.size());
			String lessonDay = moduleNo.getlessonDay();
			if (lessonDay != null && lessonDay.length() > 0) {
				String[] lessDay = lessonDay.split(",");
				String[] weekDay = { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };
				List<String> listDay = new ArrayList<String>();
				if (lessDay.length > 0) {
					for (String str : lessDay) {
						listDay.add(weekDay[Integer.parseInt(str)]);
					}
				}
				model.addAttribute("listDay", listDay);
			}

			// 排课列表中是否显示
			model.addAttribute("isHide", 1);

			if (liveProvider != null && liveProvider.getLiveServiceProvider() != null && liveProvider.getLiveServiceProvider().equals("zs")) {
				return "module/editGenseeModuleNoAndLessons"; // 返回到展示互动的排课页面
			} else {
				return "module/editModuleNoAndLessons";
			}
		}

		model.addAttribute("isHide", 0);
		model.addAttribute("id", moduleNo.getId());
		model.addAttribute("moduleNoName", moduleNo.getName());
		if (liveProvider != null && liveProvider.getLiveServiceProvider() != null && liveProvider.getLiveServiceProvider().equals("zs")) {
			return "module/addGenseeModuleNoAndLessons"; // 返回到展示互动的排课页面
		} else {
			return "module/addModuleNoAndLessons"; // 返回到E课堂的排课页面
		}
	}

	@ResponseBody
	@RequestMapping(value = "editLesson")
	public String editModuleNo(Model model, HttpServletRequest request, ClassModuleLesson lesson) {
		Integer companyId = WebUtils.getCurrentCompanyId();
		// 如果是新增的话，需要判断当前公司的直播服务提供商是哪家
		CompanyMemberService liveProvider = companyMemberServiceServiceImpl.findByCompanyId(companyId);

		try {
			if (lesson != null && lesson.getId() != null && lesson.getId() > 0) {

				// 根据ID获取当前课程的信息
				ClassModuleLesson curLesson = classModuleLessonService.findClassModuleLessonById(lesson.getId());

				// 判断当前公司是E课堂还是展示互动
				if (liveProvider != null && liveProvider.getLiveServiceProvider() != null && liveProvider.getLiveServiceProvider().equals("zs")) {
					if (curLesson.getLiveroomIdGh() != null && curLesson.getLiveroomIdGh().length() > 0) {
						String zsLoginName = properties.getZsLoginName();
						String zsAddress = properties.getZsAddress();

						curLesson.setLessonTimeStart(lesson.getLessonTimeStart());
						curLesson.setLessonTimeEnd(lesson.getLessonTimeEnd());
						curLesson.setLessonName(lesson.getLessonName());
						// 已经创建过直播课程
						genseeLiveRoomServiceImpl.updateLiveRoom(curLesson, companyId);
					}
					classModuleLessonService.update(lesson);
				} else {
					String customer = properties.getCustomer();
					long timestamp = System.currentTimeMillis();
					String secretKey = properties.getSecretKey();

					String str = MD5.getMD5ofStr(customer + timestamp + secretKey);
					str = str.substring(0, 10) + str.substring(str.length() - 10);

					Map<String, Object> param = new HashMap<String, Object>();

					String day = format.format(lesson.getLessonDate());
					String startTime = lesson.getLessonTimeStart();
					String endTime = lesson.getLessonTimeEnd();
					long beginTimeLong = DateUtil.parseDateTime(day, startTime);
					long endTimeLong = DateUtil.parseDateTime(day, endTime);

					param.put("beginTime", beginTimeLong);
					param.put("endTime", endTimeLong);
					param.put("customer", customer);
					param.put("timestamp", timestamp);
					param.put("s", str);

					if (curLesson != null && curLesson.getLiveRoom() != null) {
						param.put("id", curLesson.getLiveRoom());
					}

					// 学生不需要鉴权
					param.put("fee", 1);
					param.put("title", lesson.getLessonName());
					param.put("introduce", lesson.getLessonName());

					String url = properties.getInterfaceAddress() + LiveRoomConstant.UPDATE_LIVEROOM;
					try {
						String detail = HttpPostRequest.post(url, param);
						log.info("调用E课堂修改直播教室上课时间,返回信息如下：" + detail);
						classModuleLessonService.update(lesson);
					} catch (Exception e) {
						log.error("调用E课堂修改直播教室上课时间失败,课次Id为：" + lesson.getId(),e);
						e.printStackTrace();
						return "failure";
					}
				}
			} else {
				lesson.setDelFlag(0);
				if (lesson != null && lesson.getSupportMobileFlag() != null && lesson.getSupportMobileFlag().equals("是")) {
					lesson.setSupportMobile(1);
				} else {
					lesson.setSupportMobile(0);
				}
				lesson.setLiveRoom(UUID.randomUUID().toString().replaceAll("-", ""));
				lesson.setLiveCompanyType(liveProvider.getLiveServiceProvider());
				classModuleLessonService.insert(lesson);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "failure";
		}
		return JsonMsg.SUCCESS;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(ClassModuleNo classModuleNo) {
		classModuleNoServiceImpl.update(classModuleNo);
		return "redirect:/classModuleNo";
	}

	@ResponseBody
	@RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		try {
			classModuleNoServiceImpl.deleteClassModuleNoById(id);
			return JsonMsg.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return JsonMsg.ERROR;
		}
	}

	@ResponseBody
	@RequestMapping(value = "/json/{schoolId}", method = RequestMethod.GET)
	public ClassModuleNo getJson(Model model, @PathVariable Integer schoolId) {
		if (schoolId != null && schoolId.intValue() > 0) {
			return null;
		}
		return null;
		// return classModuleNoServiceImpl.findClassModuleNoById(id);
	}

	/**
	 *
	 * Class Name: ClassModuleController.java
	 *
	 * @Description: 根据模块、校区、考期获取模块号
	 * @author chopin
	 * @date 2014-12-18 下午2:47:29
	 * @version 1.0
	 * @param ClassModule
	 * @param List
	 *            <ClassModuleNoVo>
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryList", method = RequestMethod.POST)
	public List<ClassModuleNoVo> queryList(Model model, ClassModuleNo classModule) {
		List<ClassModuleNoVo> al = classModuleNoServiceImpl.findListByModule(classModule);
		return al;
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
	 * @Description:(根据选中的条件进行预排课)
	 * @author wang.zx
	 * @date 2015-1-4 上午10:38:09
	 * @version 1.0
	 * @param classNo
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "preInitClass")
	public List<LessonsListVo> preInitClass(LessonsListVo classNo, HttpServletRequest request) {

		List<LessonsListVo> listVo = new ArrayList<LessonsListVo>();
		// 获取排课天，将其进行数组化
		String[] weekDays = null;
		if (classNo != null && classNo.getWeekType() != null && classNo.getWeekType().length() > 0) {
			weekDays = classNo.getWeekType().substring(0, classNo.getWeekType().length() - 1).split(",");
		}

		// 循环处理课次对象
		Map<String, Integer> dateMap = getDateByVeginClassPoint(classNo.getStartDate(), weekDays, classNo.getTotalCount());
		int count = 1;

		// 获取排课信息
		String lessonTimeStart = classNo.getLessonTimeStart();
		String lessonTimeEnd = classNo.getLessonTimeEnd();
		Double lessonHour = classNo.getLessonHour();
		String lessonName = classNo.getLessonName();
		String scope = classNo.getScope();

		for (Map.Entry<String, Integer> entry : dateMap.entrySet()) {
			LessonsListVo vo = new LessonsListVo();
			vo.setLessonTimeStart(lessonTimeStart);
			vo.setStartDate(classNo.getStartDate());
			vo.setLessonTimeEnd(lessonTimeEnd);
			vo.setLessonHour(lessonHour);
			vo.setWeekType(getWeekChina(entry.getValue()));
			vo.setLessonName(lessonName + "-" + count);
			vo.setWorkDay(entry.getKey());
			vo.setScope(SysLoader.getDict(scope).getItemValue());
			listVo.add(vo);
			count++;
		}
		return listVo;
	}

	/**
	 * @Description:(提交添加班号)
	 * @author wang.zx
	 * @date 2015-1-6 下午3:14:51
	 * @version 1.0
	 * @param request
	 * @param moduleLesson
	 * @return
	 */
	@RequestMapping(value = "editModuleNoAndAddLesson")
	public String addModuleNoAndLesson(HttpServletRequest request, ModuleNoAndLessonVo moduleLesson) {
		ClassModuleNo moduleNo = moduleLesson.getModuleNo();
		List<ClassModuleLesson> lessons = moduleLesson.getLesson();
		Users user = WebUtils.getCurrentUser(request);
		if (user == null) {
			return "redirect:/";
		}

		// 如果是新增的话，需要判断当前公司的直播服务提供商是哪家（展示互动/E课堂）
		CompanyMemberService liveProvider = companyMemberServiceServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
		String liveType = liveProvider.getLiveServiceProvider();

		int mNo = moduleNo.getId();

		// 获取出list之后,判断出那些是新增加的课程，哪些是更新已有的课程
		List<ClassModuleLesson> newLessons = new ArrayList<ClassModuleLesson>();
		List<ClassModuleLesson> oldLessons = new ArrayList<ClassModuleLesson>();

		if (lessons != null && lessons.size() > 0) {
			for (ClassModuleLesson lesson : lessons) {
				if (lesson.getId() != null && lesson.getId() > 0) {
					oldLessons.add(lesson);
				} else {
					if (lesson.getLessonName() == null) {
						continue;
					}
					lesson.setDelFlag(0);
					lesson.setModuleNoId(mNo);
					if (lesson.getSupportMobileFlag() != null && lesson.getSupportMobileFlag().equals("是")) {
						lesson.setSupportMobile(1);
					} else {
						lesson.setSupportMobile(0);
					}

					if (lesson.getLiveClassTypeFlag() != null && lesson.getLiveClassTypeFlag().equals("大讲堂")) {
						lesson.setLiveClassType(LiveRoomConstant.LIVE_BIG_CLASS_ROOM);
					} else {
						lesson.setLiveClassType(LiveRoomConstant.LIVE_SMALL_CLASS_ROOM);
					}

					lesson.setLiveRoom(UUID.randomUUID().toString().replaceAll("-", ""));
					lesson.setLiveCompanyType(liveType);
					lesson.setCreator(user.getId());
					lesson.setCreateTime(new Date());
					newLessons.add(lesson);
				}
			}

			classModuleLessonService.batchInsert(newLessons);
			if (oldLessons.size() > 0) {
				for (ClassModuleLesson lesson : oldLessons) {
					classModuleLessonService.update(lesson);
				}
			}
		}

		// 保存新建的班号
		if (moduleNo != null) {
			moduleNo.setLessonTotal(newLessons.size());
			classModuleNoServiceImpl.update(moduleNo);
		}

		// 跳转到 添加、更改老师页面
		return "redirect:/classModuleNo/editLessonTeacher/" + mNo;
	}

	/**
	 * @Description: 给班号的课次安排老师
	 * @author wzx
	 * @date 2015-5-20 下午3:50:33
	 * @version 1.0
	 * @param request
	 * @param moduleNoId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "editLessonTeacher/{moduleNoId}")
	public String editLessonTeacher(HttpServletRequest request, @PathVariable Integer moduleNoId, Model model) {

		ClassModuleNo moduleNo = classModuleNoServiceImpl.findClassModuleNoById(moduleNoId);

		List<ClassModuleLesson> lessons = classModuleLessonService.findClassModuleLessonByModuleNoId(moduleNoId);

		model.addAttribute("lessons", lessons);
		model.addAttribute("moduleNoId", moduleNo.getId());
		model.addAttribute("moduleNoName", moduleNo.getName());
		model.addAttribute("moduleId", moduleNo.getModuleId());
		model.addAttribute("classTeachType", moduleNo.getClassTeachType());
		Subject subject = SecurityUtils.getSubject();
		if((subject.hasRole("直播老师")&&subject.hasRole("排课老师"))&&!(subject.hasRole("机构管理员")||subject.hasRole("分校管理员")||subject.hasRole("运营")))
        {
			model.addAttribute("isPaikeAndZhibo", "success");
        }
		
		return "module/selectTeacher";
	}

	/**
	 * @Description: 给班号的课次安排教室
	 * @author wzx
	 * @date 2015-5-20 下午3:54:31
	 * @version 1.0
	 * @param request
	 * @param moduleNoId
	 * @return
	 */
	@RequestMapping(value = "editClassRoom/{moduleNoId}")
	public String editLessonClassRoom(HttpServletRequest request, @PathVariable Integer moduleNoId, Model model) {
		ClassModuleNo moduleNo = classModuleNoServiceImpl.findClassModuleNoById(moduleNoId);

		List<ClassModuleLesson> lessons = classModuleLessonService.findClassModuleLessonByModuleNoId(moduleNoId);
		
		model.addAttribute("lessons", lessons);
		model.addAttribute("moduleNoId", moduleNo.getId());
		model.addAttribute("moduleId", moduleNo.getModuleId());
		model.addAttribute("moduleNoName", moduleNo.getName());
		
		return "module/selectClassRoom";
	}

	/**
	 * @Description:(根据开班点以及选择的日期，获取对应的日期)
	 * @author wang.zx
	 * @date 2015-1-5 下午4:41:16
	 * @version 1.0
	 * @return
	 */
	public Map<String, Integer> getDateByVeginClassPoint(String pointDate, String[] weeks, Integer totalCount) {
		// 开班日期
		String curStr = null;
		List<String> list = new ArrayList<String>();
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		int curWeek = 0;
		Date startDate;
		try {
			startDate = dateFormat1.parse(pointDate);
			curWeek = DateUtil.getWeekOfDay(startDate);
		} catch (ParseException e) {
			log.error("排课传入的开班点时间解析出错！",e);
			e.printStackTrace();
		}

		// 获取两个日期直接相差天数
		int differDays = 0;
		// 标示，用于判断当前改取数组中的哪个值
		int flag = 0;

		for (int i = 0; i < totalCount; i++) {
			if (list.size() <= 0) {
				differDays = DateUtil.getDaysBetweenTwoWeekDay(curWeek, Integer.parseInt(weeks[flag]));
				// 如果相差7天的话就表示是今天
				if (differDays == 7) {
					differDays = 0;
				}
				// 将下一个值复制到当前日期
				curWeek = Integer.parseInt(weeks[0]);
				// 获取对应的日期
				String dayStr = DateUtil.getDayByDateTime(pointDate, differDays);
				list.add(dayStr);
				map.put(dayStr, curWeek);
			} else {
				curStr = list.get(list.size() - 1);
				// 去模
				if (i < weeks.length) {
					flag = i;
				} else {
					flag = i % weeks.length;
				}
				differDays = DateUtil.getDaysBetweenTwoWeekDay(curWeek, Integer.parseInt(weeks[flag]));

				// 获取对应的日期
				String dayStr = DateUtil.getDayByDateTime(curStr, differDays);
				list.add(dayStr);
				// 将下一个值复制到当前日期
				curWeek = Integer.parseInt(weeks[flag]);
				map.put(dayStr, curWeek);
			}
		}

		return map;
	}

	public String getWeekChina(int week) {
		String[] weeks = { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };
		if (week < 8) {
			return weeks[week];
		} else {
			return weeks[0];
		}
	}

	/**
	 * @Description:(查询班号列表)
	 * @author wang.zx
	 * @date 2015-1-8 下午5:22:40
	 * @version 1.0
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "queryModuleNoList")
	public String queryModuleNoList(HttpServletRequest request) {
		return "module/moduleNoList";
	}

	@RequestMapping(value = "queryModuleNoAjaxList")
	public String queryModuleNoAjaxList(HttpServletRequest request, Model model, ClassModuleNo search) {

		// 查询班号信息
		List<ClassModuleNoListVo> modules = classModuleNoServiceImpl.findClassModuleNoListVoByPage(search);
		if (modules != null && modules.size() > 0) {
			for (ClassModuleNoListVo vo : modules) {
				if (vo != null && vo.getPublishStatus() != null) {
					vo.setPublishStatus(SysLoader.getDictionary(vo.getPublishStatus()).getItemValue());
				}
				if (vo != null && vo.getClassTeachType() != null) {
					vo.setClassTeachType(SysLoader.getDictionary(vo.getClassTeachType()).getItemValue());
				}
				vo.setDate(DateUtil.format1(vo.getStartDate()));
			}
		}
		model.addAttribute("search", search);
		model.addAttribute("modules", modules);
		return "module/moduleNoAjaxList";
	}

	/**
	 *
	 * @Title: getCampus_name
	 * @Description: 由id获得校区名称
	 * @return String 返回类型
	 * @throws @exception
	 * @date 2015-3-28
	 * @user by ycl
	 */

	@ResponseBody
	@RequestMapping(value = "/getCampus_nameById")
	public String getCampus_nameById(Integer id) {

		String campus_name = classModuleNoServiceImpl.getCampus_nameById(id);
		return campus_name;
	}

	/**
	 * @Description:(根据模块ID查询详情)
	 * @author wang.zx
	 * @date 2015-1-10 下午2:54:10
	 * @version 1.0
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "moduleNoDetail")
	public String queryClassModuleNoDetailById(HttpServletRequest request, Model model, Integer id) {
		ClassModuleNoListVo moduleNo = classModuleNoServiceImpl.findModuleNoListVoById(id);
		model.addAttribute("moduleNo", moduleNo);

		// 根据班号Id查询对应的课次
		List<ClassModuleLesson> lessons = classModuleLessonService.findClassModuleLessonByModuleNoId(id);
		model.addAttribute("lessons", lessons);
		return "module/classModuleNo/moduleNoDetail";
	}

	@RequestMapping(value = "getByPaySlave", method = RequestMethod.POST)
	public String getByPaySlave(StudentPaySlave paySlave, Integer termId, Model model, Integer classTypeId) {
		// 查询所报班型的模块
		List<ClassModule> list = classModuleServiceImpl.findByPayMasterId(paySlave);
		for (ClassModule classModule : list) {
			List<ClassModuleNo> classModuleNos = classModuleNoServiceImpl.findByCmId(classModule.getId(), classTypeId);
			if (termId != null && !"".equals(termId)) {
				SysConfigTerm configTerm = sysConfigTermServiceImpl.findSysConfigTermById(termId);
				for (int i = 0; i < classModuleNos.size(); i++) {
					if (!classModuleNos.get(i).getExamTerm().equals(configTerm.getTermName())) {
						classModuleNos.remove(i);
						i--;
					}
				}
			}

			classModule.setClassModuleNos(classModuleNos);
		}
		// StudentPaySlave paySlave = new StudentPaySlave();
		/*
		 * List<ClassModuleNo> list2 =
		 * classModuleNoServiceImpl.findBySearch(paySlave);
		 * if(termId!=null&&!"".equals(termId)){ SysConfigTerm configTerm =
		 * sysConfigTermServiceImpl.findSysConfigTermById(termId); for (int i =
		 * 0; i < list2.size(); i++) {
		 * if(!list2.get(i).getExamTerm().equals(configTerm.getTermName())){
		 * list2.remove(i); i--; } } }
		 */

		/*
		 * for (ClassModule classModule : list) { List<ClassModuleNo>
		 * classModuleNos
		 * =classModuleNoServiceImpl.findByCmId(classModule.getId());
		 * classModule.setClassModuleNos(classModuleNos); }
		 */

		// ClassModuleNo
		// moduleNo=classModuleNoServiceImpl.findByClassType(classTypeId,WebUtils.getCurrentCompanyId());
		model.addAttribute("list", list);
		return "/student/transaction/classModule";
	}

	@ResponseBody
	@RequestMapping(value = "toChange", method = RequestMethod.POST)
	public String toChange(Integer payMasterId, String moduleNo) {
		String[] arr = moduleNo.split(";");
		for (String str : arr) {
			studentPaySlaveServiceImpl.update2(payMasterId, str);
		}
		return JsonMsg.SUCCESS;
	}

	@ResponseBody
	@RequestMapping("/updateStatus")
	public String updateStatus(HttpServletRequest request, ClassModuleNo cmn) {
		Integer userId = WebUtils.getCurrentUserId(request);
		cmn.setUpdateTime(new Date());
		cmn.setUpdator(userId);
		try {
			classModuleNoServiceImpl.update(cmn);
			return JsonMsg.SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return JsonMsg.ERROR;
		}
	}

	public static void main(String[] args) {

		/**
		 * 1.获取当前星期数组中第一个星期的日期 2.根据第一个日期依次类推获取下一个日期
		 */

		// Integer[] weeks = {2, 5, 6};
		// //开班日期
		// Date curDate = new Date();
		// String curStr = null;
		// List<String> list = new ArrayList<String>();
		// Map<String,Integer> map = new HashMap<String, Integer>();
		// int curWeek = 0;
		// curWeek = DateUtil.getWeekOfDay(curDate);
		//
		// //获取两个日期直接相差天数
		// int differDays = 0;
		// //标示，用于判断当前改取数组中的哪个值
		// int flag = 0;
		//
		// for(int i = 0; i < 35; i++){
		// if(list.size() <= 0){
		// differDays = DateUtil.getDaysBetweenTwoWeekDay(curWeek, weeks[flag]);
		// //将下一个值复制到当前日期
		// curWeek = weeks[0];
		// //获取对应的日期
		// String dayStr =
		// DateUtil.getDayByDateTime(DateUtil.format1(curDate),differDays);
		// list.add(dayStr);
		// map.put(dayStr, curWeek);
		// }else{
		// curStr = list.get(list.size()-1);
		// //去模
		// if(i < weeks.length){
		// flag = i;
		// }else{
		// flag = i % 3;
		// }
		// differDays = DateUtil.getDaysBetweenTwoWeekDay(curWeek, weeks[flag]);
		//
		// //获取对应的日期
		// String dayStr = DateUtil.getDayByDateTime(curStr,differDays);
		// list.add(dayStr);
		// //将下一个值复制到当前日期
		// curWeek = weeks[flag];
		// map.put(dayStr, curWeek);
		// }
		// }
		//
		// for(int i = 0; i < list.size(); i++){
		// System.out.println(list.get(i));
		// }

		String a = "经济法基础保过班001-面授-20150422";
		System.out.println(a.indexOf("-"));
		System.out.println(a.split("-")[0]);

	}
}
