package com.yuxin.wx.controller.classes;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.classes.IClassModuleLessonService;
import com.yuxin.wx.api.classes.IClassModuleNoService;
import com.yuxin.wx.api.classes.IClassModuleService;
import com.yuxin.wx.api.classes.IClassTypeMemberDiscountService;
import com.yuxin.wx.api.classes.IClassTypeOfBranchSchoolService;
import com.yuxin.wx.api.classes.IClassTypeResourceService;
import com.yuxin.wx.api.classes.IClassTypeResourceTypeService;
import com.yuxin.wx.api.classes.IClassTypeService;
import com.yuxin.wx.api.company.ICompanyFunctionSetService;
import com.yuxin.wx.api.company.ICompanyIntegralConfigService;
import com.yuxin.wx.api.company.ICompanyLiveStaticDetailService;
import com.yuxin.wx.api.company.ICompanyMemberConfigService;
import com.yuxin.wx.api.company.ICompanyMemberLevelConfigService;
import com.yuxin.wx.api.company.ICompanyRegisterConfigService;
import com.yuxin.wx.api.course.ICourseExerciseService;
import com.yuxin.wx.api.course.ICourseVideoChapterService;
import com.yuxin.wx.api.student.IStudentService;
import com.yuxin.wx.api.system.ISysConfigDictService;
import com.yuxin.wx.api.system.ISysConfigItemService;
import com.yuxin.wx.api.system.ISysConfigServiceService;
import com.yuxin.wx.api.system.ISysConfigTeacherService;
import com.yuxin.wx.common.JsonMsg;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.common.SysConfigConstant;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.classes.ClassModuleLesson;
import com.yuxin.wx.model.classes.ClassModuleNo;
import com.yuxin.wx.model.classes.ClassTypeMemberDiscount;
import com.yuxin.wx.model.classes.ClassTypeResource;
import com.yuxin.wx.model.company.CompanyFunctionSet;
import com.yuxin.wx.model.company.CompanyIntegralConfig;
import com.yuxin.wx.model.company.CompanyMemberConfig;
import com.yuxin.wx.model.company.CompanyMemberLevelConfig;
import com.yuxin.wx.model.company.CompanyRegisterConfig;
import com.yuxin.wx.model.course.CourseExercise;
import com.yuxin.wx.model.statistics.QueryLessonByClassTypeVo;
import com.yuxin.wx.model.student.Student;
import com.yuxin.wx.model.system.SysConfigDict;
import com.yuxin.wx.model.system.SysConfigItemRelation;
import com.yuxin.wx.model.system.SysConfigService;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.utils.DateUtil;
import com.yuxin.wx.utils.FileUtil;
import com.yuxin.wx.utils.PropertiesUtil;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.classes.ClassTypeResourceVo;
import com.yuxin.wx.vo.classes.ClassTypeVo;
import com.yuxin.wx.vo.classes.ClassmNoVo;
import com.yuxin.wx.vo.course.ChapterAndLectureListVo;
import com.yuxin.wx.vo.course.CourseExerciseVo;
import com.yuxin.wx.vo.student.StudentClassLeanDetailVo;
import com.yuxin.wx.vo.student.StudentClassLeanRecordVo;
import com.yuxin.wx.vo.student.StudentLessTimeVo;

/**
 * 数校公开课
 * @author cxl
 *
 */
@Controller
@RequestMapping("/branchSchool")
public class ClassTypeOfBranchSchoolContorller {

	private Log log=LogFactory.getLog("log");
	
	@Autowired
	private ISysConfigDictService sysConfigDictServiceImpl;
	
	@Autowired
	private IClassTypeOfBranchSchoolService classTypeOfBranchSchoolService;
	@Autowired
	private IClassTypeMemberDiscountService classTypeMemberDiscountServiceImpl;
	@Autowired
	private ICompanyMemberLevelConfigService companyMemberLevelConfigServiceImpl;

	@Autowired
	private ISysConfigTeacherService sysConfigTeacherServiceImpl;
	@Autowired
	private IClassModuleLessonService classModuleLessonServiceImpl;
    @Autowired
    private ICompanyRegisterConfigService companyRegisterConfigServiceImpl;
	
    @Autowired
    private ICompanyMemberConfigService companyMemberConfigServiceImpl;

	@Autowired
	private ICourseExerciseService courseExerciseServiceImpl;
	
	@Autowired
	private ICompanyIntegralConfigService companyIntegralConfigServiceImpl;
	
	@Autowired
	private ICompanyFunctionSetService companyFunctionSetImpl;
    
	@Autowired
    private IClassModuleService classModuleServiceImpl;
    
    @Autowired
    private ISysConfigServiceService sysConfigServiceServiceImpl;
    
    @Autowired
    private ICompanyFunctionSetService companyFunctionSetServiceImpl;

    @Autowired
    private PropertiesUtil propertiesUtil;

	@Autowired
	private IStudentService	studentServiceImpl;
    
    @Autowired
    private IClassTypeService classTypeServiceImpl;

	@Autowired
	private ICompanyLiveStaticDetailService companyLiveStaticDetailServiceImpl;
    
    @Autowired
    private ISysConfigItemService sysConfigItemServiceImpl;
    
	@Autowired
	private IClassTypeResourceTypeService classTypeResourceTypeServiceImpl;
	
    @Autowired
    private IClassTypeResourceService classTypeResourceServiceImpl;
    
	@Autowired
	private ISysConfigServiceService sysconfigServiceImpl;

	@Autowired
	private ICourseVideoChapterService courseVideoChapterServiceImpl;
	
	@Autowired
	private IClassModuleNoService classModuleNoServiceImpl;
	/**
     * 分校开放课程管理
     * @return
     */
    @RequestMapping(value = "/queryClassType")
    public String queryClassType(Model model,HttpServletRequest request){
    	List<SysConfigDict> areaList=classTypeOfBranchSchoolService.findByDicCode("EDU_SCHOOL_AREA");
    	model.addAttribute("firstItems", areaList);
    	
        return "classType/branchSchool/classTypeManage";
    }
    
   @RequestMapping("/classTypeDetail")
  	public String classTypeDetail(Model model,HttpServletRequest req,ClassTypeVo classtype){
    	Map<String, Object> queryParams=new HashMap<String, Object>();
    	queryParams.put("areaId", req.getParameter("areaId"));
    	queryParams.put("schoolCode", req.getParameter("schoolCode"));
    	queryParams.put("firstItemCode", req.getParameter("firstItemCode"));
    	queryParams.put("secondItemCode", req.getParameter("secondItemCode"));
    	queryParams.put("thirdItemCode", req.getParameter("thirdItemCode"));
    	queryParams.put("cddsStatus", req.getParameter("cddsStatus"));
	    String courseType = req.getParameter("courseType");
	    if("1".equals(courseType)){
			queryParams.put("liveFlag", "1");
		}else if("0".equals(courseType)){
			queryParams.put("videoFlag", "1");
		}
    	if(StringUtils.isNotEmpty(req.getParameter("name"))){
    		queryParams.put("name", req.getParameter("name").trim());
    	}
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
    	try {
			if(StringUtils.isNotEmpty(req.getParameter("startTime"))){
				queryParams.put("startTime", sdf.parse(req.getParameter("startTime")));
			}
			if(StringUtils.isNotEmpty(req.getParameter("endTime"))){
				queryParams.put("endTime", sdf.parse(req.getParameter("endTime")));
			}
		} catch (ParseException e) {
			
		}
    	queryParams.put("firstIndex", classtype.getFirstIndex());
    	queryParams.put("pageSize", classtype.getPageSize());
    	
    	//查询分校课程
    	List<ClassTypeVo> clist=classTypeOfBranchSchoolService.queryClassTypeOfBranchSchool(queryParams);
    	int count=classTypeOfBranchSchoolService.queryCountClassTypeOfBranchSchool(queryParams);
		PageFinder<ClassTypeVo> msgPage = new PageFinder<ClassTypeVo>(classtype.getPage(), classtype.getPageSize(), count, clist);
		model.addAttribute("msgPage", msgPage);
    	return "classType/branchSchool/classTypeManageDetail";
    }
   
   /**
    * 编辑班型(第一步)
    * @param model
    * @param request
    * @param ct
    * @return
    */
   @RequestMapping(value = "/editClassBaseInfo/{classId}/{lable}")
   public String editClassBaseInfo(Model model, HttpServletRequest request,@PathVariable String classId,@PathVariable String lable) {
       //获取班型信息
	   Map<String, String> map = new HashMap<String, String>();
	   map.put("classId", classId);
	   ClassTypeVo classType = this.classTypeOfBranchSchoolService.findClassTypeDetail(map);
	   // 根据班型id查询详情
       model.addAttribute("ct", classType);
       model.addAttribute("isArea",WebUtils.getCurrentIsArea());
       model.addAttribute("lable", lable);
       if("live".equals(lable)){
    	   return "classType/branchSchool/classTypeFaceOrLiveMessage";
       }else if("video".equals(lable)){
    	   ClassModule m = this.classModuleServiceImpl.queryModuleByClasstypeId(classType.getId());
           if (m != null) {
               model.addAttribute("courseNum", m.getTotalClassHour());
           } else {
               model.addAttribute("courseNum", 0);
           }
    	   return "classType/branchSchool/classTypeVideoMessage";
       }else{
    	   return "classType/branchSchool/classTypeFaceOrLiveMessage";
       }
   }
   
   // 添加单个直播或面授
   @RequestMapping(value = "/editliveOrface/{id}/{lable}")
   public String editliveOrface(@PathVariable String id, @PathVariable String lable, Model model) {
       Map<String, String> map = new HashMap<String, String>();
	   map.put("classId", id);
       ClassTypeVo ct = this.classTypeOfBranchSchoolService.findClassTypeDetail(map);
       
       model.addAttribute("ct", ct);
       model.addAttribute("lable", lable);
       if ("live".equals(lable)) {
           return "classType/branchSchool/classTypeLiveMessage_3";
       }
       return "classType/branchSchool/classTypeLiveMessage_3";
   }
   

	@RequestMapping(value = "/editCourseDetail/{id}/{lable}")
   public String editCourseDetail(Model model, @PathVariable String id, @PathVariable String lable) {
       Map<String, String> map = new HashMap<String, String>();
	   map.put("classId", id);
       ClassTypeVo ct = this.classTypeOfBranchSchoolService.findClassTypeDetail(map);
       model.addAttribute("ct", ct);
       model.addAttribute("lable", lable);
       if (null != ct) {
	       if (ct.getCover() != null && !"".equals(ct.getCover())) {
	           String url = "http://" + this.propertiesUtil.getProjectImageUrl() + "/";
	           ct.setCover(url + ct.getCover());
	       } else {
	    	   ct.setCover("");
	       }
       }

       if ("face".equals(lable) || "live".equals(lable)) {
           return "classType/branchSchool/classTypeFaceOrLiveMessage_2";
       } else if ("video".equals(lable)) {
           // 查询班型下的模块信息
//           ClassModule m = this.classModuleServiceImpl.queryModuleByClasstypeId(cs.getId());
//           model.addAttribute("classTypeId", cs.getId());
//           model.addAttribute("moduleName", cs.getName());
//           model.addAttribute("itemOneId", cs.getItemOneId());
//           model.addAttribute("itemSecondId", cs.getItemSecondId());
//           if (m != null) {
//               model.addAttribute("moduleId", m.getId());
//           }
//           model.addAttribute("id", cs.getId());
           return "classType/branchSchool/classTypeVideoMessage_2";
       } else if ("remote".equals(lable)) {
           return "classType/branchSchool/addClassTypeOtherMessage_2";
       } else {
           return "classType/branchSchool/addClassTypeTogtherMessage_2";
       }
   }
	
	@RequestMapping(value = "/editClassTypeVideo/{id}/{lable}")
    public String editClassTypeVideo(Model model, @PathVariable String id, @PathVariable String lable) {
	   Map<String, String> map = new HashMap<String, String>();
	   map.put("classId", id);
       ClassTypeVo ct = this.classTypeOfBranchSchoolService.findClassTypeDetail(map);
       ClassModule m = this.classModuleServiceImpl.queryModuleByClasstypeId(ct.getId());
       if (null != m) {
          model.addAttribute("moduleId", m.getId());
       }
       model.addAttribute("ct", ct);
       model.addAttribute("lable", lable);
       return "classType/branchSchool/classTypeVideoMessage_3";
    }

	@RequestMapping("/classesResource/{id}/{lable}")
	public String classesResource(@PathVariable Integer id,@PathVariable String lable,Model model,HttpServletRequest request){
		//根据班型id查询详情
		Map<String, String> map = new HashMap<String, String>();
		map.put("classId", "" + id);
		ClassTypeVo classType=this.classTypeOfBranchSchoolService.findClassTypeDetail(map);
		model.addAttribute("ct", classType);
//		List<ClassTypeResourceType> rtList = classTypeResourceTypeServiceImpl.findResourceTypeByCompanpyId(classType.getCompanyId());
//		model.addAttribute("rtList", rtList);
        model.addAttribute("lable", lable);
		return "classType/branchSchool/commClassResource";
	}
   
    @RequestMapping("/selResource")
    public String selResource(Model model, HttpServletRequest request, ClassTypeResource res) {
        // 查询
        List<ClassTypeResourceVo> resList = this.classTypeOfBranchSchoolService.findResBy(res);
        for (ClassTypeResourceVo c : resList) {
            if (c.getSourceSize() != null) {
                c.setSourceSize(FileUtil.convertFileSize(Long.parseLong(c.getSourceSize())));
            }
        }
        // 总数
        Integer count = this.classTypeOfBranchSchoolService.findResCountBy(res);
        // 分页
        PageFinder<ClassTypeResourceVo> resPage = new PageFinder<ClassTypeResourceVo>(res.getPage(), res.getPageSize(), count, resList);
        model.addAttribute("resPage", resPage);
        return "classType/branchSchool/resourceTable";
    }
    
    // 跳转到学员列表页面
    @RequestMapping(value = "/studentList/{id}/{lable}")
    public String forwardStudentList(Model model, @PathVariable Integer id, @PathVariable String lable) {
        // 根据班型id查询详情
        Map<String, String> map = new HashMap<String, String>();
        map.put("classId", "" + id);
        ClassTypeVo ct = this.classTypeOfBranchSchoolService.findClassTypeDetail(map);
        model.addAttribute("ct", ct);
        model.addAttribute("classType", ct);
        model.addAttribute("lable", lable);
        CompanyFunctionSet search = new CompanyFunctionSet();
        search.setFunctionCode("COMPANY_FUNCTION_COURSE");
//      search.setCompanyId(ct.getCompanyId());
        search.setCompanyId(WebUtils.getCurrentCompanyId());
        CompanyFunctionSet cfs = this.companyFunctionSetServiceImpl.findCompanyUseCourse(search);
        // 查看该机构是否有删除学员的功能
        search.setFunctionCode("COMPANY_REMOVE_PAYMASTER");
        CompanyFunctionSet isDelete = this.companyFunctionSetServiceImpl.findCompanyUseCourse(search);
        if (null != isDelete && "1".equals(isDelete.getStatus())) {
            model.addAttribute("isDelete", isDelete.getStatus());
        } else {
            model.addAttribute("isDelete", 0);
        }
        // 查询公司注册设置
        CompanyRegisterConfig crc = new CompanyRegisterConfig();
//      crc.setCompanyId(ct.getCompanyId());
        crc.setCompanyId(WebUtils.getCurrentCompanyId());
        crc = this.companyRegisterConfigServiceImpl.queryByCompanyId(crc);
        if (null != crc && crc.getCloseFlag() == 1) {
            crc.setMobileFlag(1);
        }
        model.addAttribute("registConfig", crc);
        search.setFunctionCode("CLASS_POTOCOL_SET");
        search = this.companyFunctionSetServiceImpl.findCompanyUseCourse(search);
        if (search != null && "1".equals(search.getStatus())) {
            model.addAttribute("courseProtocolConfig", true);
        } else {
            model.addAttribute("courseProtocolConfig", false);
        }
        if (cfs != null && "1".equals(cfs.getStatus())) {
            return "classType/branchSchool/studentList-full";
        }
        return "classType/branchSchool/studentList";
    }
     
    @RequestMapping(value = "/classBaseInfo/{id}/{lable}")
    public String classBaseInfo(Model model, @PathVariable Integer id, @PathVariable String lable) {
    	Map<String, String> map = new HashMap<String, String>();
		map.put("classId", "" + id);
		ClassTypeVo ct=this.classTypeOfBranchSchoolService.findClassTypeDetail(map);
		model.addAttribute("ct", ct);
        model.addAttribute("lable", lable);
        if("live".equals(lable)){
        	List<ClassModule> modulesVoList=classModuleServiceImpl.findModulesByClassTypeId(id);
        	if(null!=modulesVoList&&modulesVoList.size()>0){
        		for(ClassModule module:modulesVoList){
        			if("TEACH_METHOD_LIVE".equals(module.getTeachMethod())){
        				//查询模块对应的班号
        				List<ClassModuleNo> list=classModuleNoServiceImpl.findByCmId(module.getId(),id);
        				if(!list.isEmpty()&&list.size()>0){
        					ClassModuleNo mNo=list.get(0);
        					ClassmNoVo c=new ClassmNoVo();
        					c.setId(mNo.getId());
        					c.setName(mNo.getName());
        					c.setItemOneId(mNo.getItemOneId());
        					c.setItemSecondId(mNo.getItemSecondId());
        					c.setModuleId(mNo.getModuleId());
        					c.setStartDate(mNo.getStartDate());
        					c.setLessonHour(mNo.getLessonHour());
        					c.setEnrollYetStudents(mNo.getEnrollYetStudents());
        					c.setPlanEnrollStudents(mNo.getPlanEnrollStudents());
        					c.setTotalHours(mNo.getTotalHours());
        					c.setClassTeachType(mNo.getClassTeachType());
        					c.setSchoolId(mNo.getSchoolId());
        					c.setCampusId(mNo.getCampusId());
        					//查询班号对应的课次
        					List<ClassModuleLesson> arr=classModuleLessonServiceImpl.findClassModuleLessonByModuleNoId(mNo.getId());
        					/*if(arr!=null&&arr.size()>0){
						for(ClassModuleLesson ls:arr){
							if(null!=ls&&null!=ls.getTeachers()&&!"".equals(ls.getTeachers())){
								SysConfigTeacher th=sysConfigTeacherServiceImpl.findSysConfigTeacherById(Integer.parseInt(ls.getTeachers()));
								if(null!=th&&null!=th.getDelFlag()&&th.getDelFlag()==1){
									ls.setTeachers("del");
								}
							}
						}
					}*/
        					c.setClassModuleLessons(arr);
        					module.setClassmoudleNo(c);
        				}
        			}
        		}
        	}
        	model.addAttribute("liveModuleList", modulesVoList);
        }else if("video".equals(lable)){
        	 ClassModule m = this.classModuleServiceImpl.queryModuleByClasstypeId(ct.getId());
             if (null != m) {
                model.addAttribute("moduleId", m.getId());
                List<ChapterAndLectureListVo> chapterList= courseVideoChapterServiceImpl.findChapterAndLectureByModuleId(m.getId());
                model.addAttribute("videoChapterList", chapterList);
             }
        }
        return "classType/branchSchool/classTypeBaseInfo";
    }
    
    /**
	 * 打开学员的学习进度页面
	 */
	@RequestMapping(value="/classLeanRecord/{stuId}/{classTypeId}/{lable}")
	public String studenRecord(Model model,@PathVariable Integer stuId,@PathVariable Integer classTypeId,@PathVariable String lable){
		Map<String, String> map = new HashMap<String, String>();
		map.put("classId", "" + classTypeId);
		ClassTypeVo classType = this.classTypeOfBranchSchoolService.findClassTypeDetail(map);
	    model.addAttribute("classType", classType);
		model.addAttribute("ct", classType);
 		model.addAttribute("type", "update");
 		model.addAttribute("lable", lable);
 		
		//一、展示学员在该课程的上课情况：包含学习进度百分比、注册时间、报名时间、提问次数、评论次数
 		StudentClassLeanRecordVo pageData = studentServiceImpl.queryStudentLeanRecord(stuId, classTypeId);
		
 		StudentClassLeanDetailVo data = new StudentClassLeanDetailVo();
 		data.setStuId(stuId);
 		data.setClassTypeId(classTypeId);
 		data.setCompanyId(WebUtils.getCurrentCompanyId());
 		data.setSchoolId(WebUtils.getCurrentSchoolId());
 		pageData.setPrecent(leanPercent(data));
 		
 		model.addAttribute("userId", studentServiceImpl.findStudentById(stuId).getUserId());
 		
 		model.addAttribute("leanRec", pageData);
 		model.addAttribute("stuId", stuId);
 		model.addAttribute("classTypeId", classTypeId);
		return "classType/branchSchool/student-leanClass-record";
	}
    
	/**
	 * 获取学习进度的百分比
	 */
	public String leanPercent(StudentClassLeanDetailVo data){
		/**
		 * 思路：获取到课程下所有的节，包含视频、直播和面授。
		 * 		统计出用户在视频课次中所学习过的课次，其中user_history表中study_status值为3的记录
		 * 		获取所有的直播和面授的课次的结束时间，遍历集合跟当前时间做比较，其中小于当前时间的为用户已经学习完成的视频和直播课程
		 */
		Integer lecCount = totoalLecture(data);
		Integer lecFinishCount = lectuceFinishCount(data);
		List<StudentLessTimeVo> lessionEndTimeList = totalLessonVo(data);
		int lessonFinishCount = lessonFinishCount(lessionEndTimeList);
		
		return formatePercent(lecCount, lecFinishCount, lessionEndTimeList, lessonFinishCount);
	}
	//格式化百分比进度
	private String formatePercent(Integer lecCount, Integer lecFinishCount, List<StudentLessTimeVo> lessionEndTimeList, int lessonFinishCount) {
		DecimalFormat df = new DecimalFormat("######0.00");   
		double finish = lecFinishCount+lessonFinishCount;
		double sumCount = lecCount+lessionEndTimeList.size();
		if(sumCount == 0)
			return "0.00";
		String f = df.format(finish/sumCount*100);
		return f;
	}
	
	//所有的视频课次
	private Integer totoalLecture(StudentClassLeanDetailVo data) {
		Integer lecCount = studentServiceImpl.queryLecCount(data);
		return lecCount;
	}
	//所有的直播课次数量（包含课程开始时间）
	private List<StudentLessTimeVo> totalLessonVo(StudentClassLeanDetailVo data) {
		List<StudentLessTimeVo> lessionEndTimeList = studentServiceImpl.queryLessionCount(data);
		return lessionEndTimeList;
	}
	//视频课次完成数量
	private Integer lectuceFinishCount(StudentClassLeanDetailVo data) {
		Integer lecFinishCount = studentServiceImpl.queryLecFinishCount(data);
		return lecFinishCount;
	}
    
	//直播课程完成数量
	private int lessonFinishCount(List<StudentLessTimeVo> lessionEndTimeList) {
		int lessonFinishCount = 0;
		if(lessionEndTimeList != null && lessionEndTimeList.size()>0){
			Calendar calOld = Calendar.getInstance();
			Calendar calNow = Calendar.getInstance();
			calNow.setTime(new Date());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			try {
				for (StudentLessTimeVo studentLessTimeVo : lessionEndTimeList) {
					String dateStr = sdf.format(studentLessTimeVo.getLessonDate());
					String year = dateStr.substring(0, 4);
					String month = dateStr.substring(4, 6);
					String day = dateStr.substring(6, 8);
					String endTime = studentLessTimeVo.getHour();
					String[] hourAndMin = endTime.split(":");
					calOld.set(Integer.parseInt(year), Integer.parseInt(month)-1, Integer.parseInt(day),Integer.parseInt(hourAndMin[0]), Integer.parseInt(hourAndMin[1]));
					int result = calOld.compareTo(calNow);
					System.out.println(sdf.format(calOld.getTime()));
					if(result<0){	//当前时间大于直播的结束时间则为学习完成该视频
						lessonFinishCount++;
					}
				}
			} catch (NumberFormatException e) {
				log.error("直播课次的结束时间转换异常：数据库数据不完整",e);
				e.printStackTrace();
			}
		}
		return lessonFinishCount;
	}
	
	 /**
     * 
     * Class Name: StudentPayMasterController.java
     * @Description: ajax方式加载学员信息列表
     * @param search
     * @return
     */
    @RequestMapping(value = "/showSignUp", method = RequestMethod.POST)
    public String showSignUp(Model model, Student student, Integer classTypeId, String lable) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("classId", "" + classTypeId);
        ClassTypeVo classType = this.classTypeOfBranchSchoolService.findClassTypeDetail(map);
        model.addAttribute("classType", classType);
        model.addAttribute("lable", lable);
        Student st = this.studentServiceImpl.findByMobile(student);
        if (st != null && st.getId() != null) {
            model.addAttribute("student", st);
        } else {
            model.addAttribute("student", student);
        }
        // 查询公司注册设置
        CompanyRegisterConfig crc = new CompanyRegisterConfig();
        crc.setCompanyId(WebUtils.getCurrentCompanyId());
        crc = this.companyRegisterConfigServiceImpl.queryByCompanyId(crc);
        if (null != crc && crc.getCloseFlag() == 1) {
            crc.setMobileFlag(1);
        }
        model.addAttribute("registConfig", crc);
        CompanyFunctionSet search = new CompanyFunctionSet();
        search.setFunctionCode("COMPANY_FUNCTION_COURSE");
        search.setCompanyId(WebUtils.getCurrentCompanyId());
        CompanyFunctionSet cfs = this.companyFunctionSetServiceImpl.findCompanyUseCourse(search);

        CompanyFunctionSet CompanyFunctionSet = new CompanyFunctionSet();
        CompanyFunctionSet.setCompanyId(WebUtils.getCurrentCompanyId());
        CompanyFunctionSet.setFunctionCode("STUDENT_GROUP");
        List<CompanyFunctionSet> CompanyFunctionSetList = companyFunctionSetServiceImpl.findCompanyFunctionSetByPage(CompanyFunctionSet);
        CompanyFunctionSet groupsearch = CompanyFunctionSetList != null && CompanyFunctionSetList.size() > 0 ? CompanyFunctionSetList.get(0) : null;
        
        //查询所在区域
        SysConfigDict areaDict = new SysConfigDict();
        areaDict.setDictCode("EDU_SCHOOL_AREA");
        List<SysConfigDict> area = sysConfigDictServiceImpl.queryConfigDictListByDictCode(areaDict);
        model.addAttribute("areas", area);	
		//学段
		areaDict.setDictCode("EDU_STEP");
		List<SysConfigDict> steps = sysConfigDictServiceImpl.queryConfigDictListByDictCode(areaDict);
		model.addAttribute("steps", steps);
		//年份列表
		List<Integer> years = new ArrayList<Integer>();
		int curYear = DateUtil.getCurYear();
		for(int year = 0;year<12;year++){
			years.add(curYear-year);
		}
		model.addAttribute( "years", years);
        
        if (null != groupsearch) {
            model.addAttribute("sgOpen", groupsearch.getStatus());
        }
        if (cfs != null && "1".equals(cfs.getStatus())) {
            return "classType/branchSchool/student-signup-full";
        }
        return "classType/branchSchool/student-signup";
    }
	
    /**
    *
    * @Description: 跳转到导入学员列表页
    * @author zhang.zx
    */
   @RequestMapping(value = "/importStudents/{id}/{lable}")
   public String importStusPage(Model model, @PathVariable Integer id, @PathVariable String lable) {
       // 根据班型id查询详情
       Map<String, String> map = new HashMap<String, String>();
       map.put("classId", "" + id);
       ClassTypeVo classType = this.classTypeOfBranchSchoolService.findClassTypeDetail(map);
       model.addAttribute("classType", classType);
       model.addAttribute("ct", classType);
       model.addAttribute("type", "update");
       model.addAttribute("lable", lable);

       CompanyFunctionSet companyFunctionSet = new CompanyFunctionSet();
       companyFunctionSet.setCompanyId(WebUtils.getCurrentCompanyId());
       companyFunctionSet.setFunctionCode("STUDENT_GROUP");
       List<CompanyFunctionSet> companyFunctionSetList = companyFunctionSetServiceImpl.findCompanyFunctionSetByPage(companyFunctionSet);
       CompanyFunctionSet search = companyFunctionSetList != null && companyFunctionSetList.size() > 0 ? companyFunctionSetList.get(0) : null;
       if (null != search) {
           model.addAttribute("sgOpen", search.getStatus());
       }
       return "classType/branchSchool/StudentsManage";
   }
   
// 导入成功后查询导入数据页面
   @RequestMapping(value = "/queryData")
   public String importStudentData(Model model, Integer id, String lable, String stuMobiles) {
       Map<String, String> map = new HashMap<String, String>();
       map.put("classId", "" + id);
       ClassTypeVo classType = this.classTypeOfBranchSchoolService.findClassTypeDetail(map);
       model.addAttribute("classType", classType);
       model.addAttribute("lable", lable);
       /*
        * String newstr = ""; if (null != stuMobiles && !"".equals(stuMobiles))
        * { newstr = stuMobiles.substring(0, stuMobiles.length() - 1); }
        */
       model.addAttribute("stuMobiles", stuMobiles);
       // 查询公司注册设置
       CompanyRegisterConfig crc = new CompanyRegisterConfig();
       crc.setCompanyId(WebUtils.getCurrentCompanyId());
       crc = this.companyRegisterConfigServiceImpl.queryByCompanyId(crc);
       if (null != crc && crc.getCloseFlag() == 1) {
           crc.setMobileFlag(1);
       }
       model.addAttribute("registConfig", crc);
       return "classType/branchSchool/importStudentData";
   }
   
   /**
   * Class Name: StudentImportController.java
   * @Description: 导入学员信息
   * @return
   */
  @RequestMapping(value = "/insertStudents")
  public String insertStudents(String list, Integer classtypeId, String lable, HttpServletRequest request, Model model) {
      // 根据班型id查询详情
      Map<String, String> map1 = new HashMap<String, String>();
      map1.put("classId", "" + classtypeId);
      ClassTypeVo classType = this.classTypeOfBranchSchoolService.findClassTypeDetail(map1);
      model.addAttribute("classType", classType);
      model.addAttribute("ct", classType);
      model.addAttribute("lable", lable);
      String[] mobileList;
      if (list != null && !"".equals(list)) {
          mobileList = list.split(",");
      } else {
          mobileList = new String[1];
      }
      List<Student> resultList = new ArrayList<Student>();
      for (String mobile : mobileList) {
          Student student = new Student();
          student.setCompanyId(WebUtils.getCurrentCompanyId());
          student.setMobile(mobile);
          // student.setSchoolId(WebUtils.getCurrentSchoolId());
          // Student st=studentServiceImpl.findByMobile(student);
          Student st = studentServiceImpl.queryStuInfoByWhere(student);
          if (st != null && st.getId() != null) {
              if (st.getAge() == null || "".equals(st.getAge())) {
                  st.setAge(0);
              }
              resultList.add(st);
          } else {
              student.setAge(0);
              resultList.add(student);
          }
      }
      model.addAttribute("student", resultList);
      CompanyFunctionSet search = new CompanyFunctionSet();
      search.setFunctionCode("COMPANY_FUNCTION_COURSE");
      search.setCompanyId(WebUtils.getCurrentCompanyId());
      CompanyFunctionSet cfs = companyFunctionSetServiceImpl.findCompanyUseCourse(search);
      if (cfs != null && "1".equals(cfs.getStatus())) {
          return "classType/branchSchool/student-signupMany-full";
      }
      return "classType/branchSchool/student-signupMany";
  }
   
  @RequestMapping(value="/videoDetail/{id}/{lable}")
  public String videoDetail(Model model, HttpServletRequest request, @PathVariable Integer id, @PathVariable String lable) throws Exception {
      Users loginUser = WebUtils.getCurrentUser(request);
      if(loginUser==null || loginUser.getId()==null){
          throw new Exception("数据出现异常，请联系管理员！");
      }
      //根据班型id查询详情
      Map<String, String> map = new HashMap<String, String>();
      map.put("classId", "" + id);
      ClassTypeVo classType = this.classTypeOfBranchSchoolService.findClassTypeDetail(map);
      
      model.addAttribute("ct", classType);
      model.addAttribute("lable", lable);
      //计算时间
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      Calendar cal = Calendar.getInstance();
      String endTime = sdf.format(cal.getTime());
      model.addAttribute("endTime" ,endTime);
      cal.add(Calendar.DAY_OF_MONTH, -6);
      String startTime = sdf.format(cal.getTime());
      model.addAttribute("startTime" ,startTime);

      return "classType/branchSchool/videoDetail";
  }
  
   /**
	 * Class Name: CompanyLiveStaticDetailController.java
	 * @Description: 跳转到直播上课统计页面中
	 */
	@RequestMapping(value = "/companyLiveStaticDetailList/{classTypeId}/{lable}")
	public String statisticsList(Model model, @PathVariable Integer classTypeId, @PathVariable String lable) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("classId", "" + classTypeId);
		ClassTypeVo classType = this.classTypeOfBranchSchoolService.findClassTypeDetail(map);
	    model.addAttribute("classType", classType);
		model.addAttribute("ct", classType);
		model.addAttribute("type", "update");
		model.addAttribute("lable", lable);

		log.info("进入直播统计查询中了,classTypeId=" + classTypeId);
		// 通过课程编号查询当前课程下的所有课次
		List<QueryLessonByClassTypeVo> list = companyLiveStaticDetailServiceImpl.queryLessonByClassTypeId(classTypeId);
		log.info("查询出" + list.size() + "个课次.........");
		model.addAttribute("list", list);
		model.addAttribute("classLessonCount", list.size());

		// 判断是单班号还是多班号
		CompanyFunctionSet search = new CompanyFunctionSet();
		search.setFunctionCode("COMPANY_FUNCTION_COURSE");
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		CompanyFunctionSet cfs = companyFunctionSetServiceImpl.findCompanyUseCourse(search);
//		// 查看该机构是否有删除学员的功能
//		search.setFunctionCode("COMPANY_REMOVE_PAYMASTER");
//		CompanyFunctionSet isDelete = companyFunctionSetServiceImpl.findCompanyUseCourse(search);
//		if (null != isDelete && "1".equals(isDelete.getStatus())) {
//			model.addAttribute("isDelete", isDelete.getStatus());
//		} else {
//			model.addAttribute("isDelete", 0);
//		}
//		if (cfs != null && "1".equals(cfs.getStatus())) {
//			// 跳转到多班号
//			return "classes/liveClassCountMuticlass";
//		}
		model.addAttribute("plus", cfs != null && "1".equals(cfs.getStatus()));

		//查询学校所在区域
		SysConfigDict areaDict = new SysConfigDict();
		areaDict.setDictCode("EDU_SCHOOL_AREA");
		List<SysConfigDict> areas = sysConfigDictServiceImpl.queryConfigDictListByDictCode(areaDict);
		model.addAttribute("areas", areas);
		//学段
		areaDict.setDictCode("EDU_STEP");
		List<SysConfigDict> steps = sysConfigDictServiceImpl.queryConfigDictListByDictCode(areaDict);
		model.addAttribute("steps", steps);
		//年份列表
		List<Integer> years = new ArrayList<Integer>();
		int curYear = DateUtil.getCurYear();
		for(int year = 0;year<12;year++){
			years.add(curYear-year);
		}
		model.addAttribute( "years", years);
		// 跳转到单班号
		return "classType/branchSchool/liveClassCount";
	}
  
    @ResponseBody
	@RequestMapping("/selSchool")
	public JSONObject selSchool(HttpServletRequest request,String areaId){
    	List<SysConfigDict> schoolList=classTypeOfBranchSchoolService.findByParentId(areaId);
		JSONObject json = new JSONObject();
		json.put("school", schoolList);
		return json;
	}
    
    @ResponseBody
   	@RequestMapping("/selOneItem")
   	public JSONObject selOneItem(HttpServletRequest request){
    	Map<String, Object> params=new HashMap<String, Object>();
    	params.put("level", 0);
    	params.put("schoolCode",request.getParameter("schoolCode") );
    	params.put("parentCode", "TYPE");
       	List<SysConfigItemRelation> relations=classTypeOfBranchSchoolService.findItemFront(params);
   		JSONObject json = new JSONObject();
   		json.put("oneItem", relations);
   		return json;
   	}
    
    @ResponseBody
   	@RequestMapping("/selTwoItem")
   	public JSONObject selTwoItem(HttpServletRequest request){
    	Map<String, Object> params=new HashMap<String, Object>();
    	params.put("level", 1);
    	params.put("schoolCode",request.getParameter("schoolCode") );
    	params.put("parentCode", "GRADE");
    	params.put("parentId", request.getParameter("parentId"));
       	List<SysConfigItemRelation> relations=classTypeOfBranchSchoolService.findItemFront(params);
       	JSONObject json = new JSONObject();
   		json.put("towItem", relations);
   		return json;
   	}
    
    @ResponseBody
   	@RequestMapping("/selThreeItem")
   	public JSONObject selThreeItem(HttpServletRequest request){
    	Map<String, Object> params=new HashMap<String, Object>();
    	params.put("level", 2);
    	params.put("schoolCode",request.getParameter("schoolCode") );
    	params.put("parentCode", "SUBJECT");
    	params.put("parentId", request.getParameter("parentId"));
       	List<SysConfigItemRelation> relations=classTypeOfBranchSchoolService.findItemFront(params);
       	JSONObject json = new JSONObject();
   		json.put("threeItem", relations);
   		return json;
   	}
    
    @ResponseBody
   	@RequestMapping("/battchUpOrDown")
    public JSONObject battchUpOrDown(HttpServletRequest request){
    	JSONObject json = new JSONObject();
    	try {
    		String[] ids=request.getParameterValues("cIds[]");
        	Map<String, Object> param=new HashMap<String, Object>();
        	param.put("cids", Arrays.asList(ids));
        	param.put("type", request.getParameter("type"));
        	classTypeOfBranchSchoolService.battchUpOrDown(param);
        	json.put(JsonMsg.RESULT, JsonMsg.SUCCESS);
		} catch (Exception e) {
			log.error(e.getMessage());
			json.put(JsonMsg.RESULT, JsonMsg.ERROR);
		}
    	return json;
    }
    
    @ResponseBody
   	@RequestMapping("/setCddsRecommendFlag")
    public JSONObject setCddsRecommendFlag(HttpServletRequest request){
    	JSONObject json = new JSONObject();
    	try {
        	Map<String, Object> param=new HashMap<String, Object>();
        	param.put("flag", request.getParameter("flag"));
        	param.put("classTypeId", request.getParameter("classTypeId"));
        	classTypeOfBranchSchoolService.setCddsRecommendFlag(param);
        	json.put(JsonMsg.RESULT, JsonMsg.SUCCESS);
		} catch (Exception e) {
			log.error(e.getMessage());
			json.put(JsonMsg.RESULT, JsonMsg.ERROR);
		}
    	return json;
    }
    
    @ResponseBody
   	@RequestMapping("/setSaleOrNoSale")
    public JSONObject setSaleOrNoSale(HttpServletRequest request){
    	JSONObject json = new JSONObject();
    	try {
        	Map<String, Object> param=new HashMap<String, Object>();
        	param.put("cddsStatus", request.getParameter("cddsStatus"));
        	param.put("id", request.getParameter("id"));
        	classTypeOfBranchSchoolService.setSaleOrNoSale(param);
        	json.put(JsonMsg.RESULT, JsonMsg.SUCCESS);
		} catch (Exception e) {
			log.error(e.getMessage());
			json.put(JsonMsg.RESULT, JsonMsg.ERROR);
		}
    	return json;
    }
    
	@ResponseBody
	@RequestMapping(value="/couseSetting",method=RequestMethod.POST)
	public CompanyFunctionSet queryCompanycouseSet(HttpServletRequest request){
		Integer companyId=Integer.parseInt(request.getParameter("companyId"));
		CompanyFunctionSet search=new CompanyFunctionSet();
		search.setCompanyId(companyId);
		search.setFunctionCode("COURSE_VALIDITY_COURSE_LEVEL");
		return companyFunctionSetImpl.findCompanyUseCourse(search);
	}
	
	
	@ResponseBody
    @RequestMapping(value = "/queryServiceSet")
    public String querySeviceSet(SysConfigService sc) {
        // 查询是否存在
        SysConfigService ser = sysConfigServiceServiceImpl.findExist(sc);
        if (null != ser && null != ser.getDelFlag() && ser.getDelFlag() == 1) {
            return SysConfigConstant.SHOW_fLAG;
        }
        return SysConfigConstant.HIDE_fLAG;
    }
	
	/**
	 * @Description: 查询公司积分开关
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryIntegralConfig")
	public CompanyIntegralConfig queryIntegralConfigByCondition(CompanyIntegralConfig search){
		CompanyIntegralConfig config=companyIntegralConfigServiceImpl.findIntegralConfigByWhere(search);
		return config;
	}
	
	 @ResponseBody
    @RequestMapping(value = "/buySet")
    public String companyBuySet(HttpServletRequest request) {
        CompanyFunctionSet companyFunctionSet = new CompanyFunctionSet();
        Integer companyId=Integer.parseInt(request.getParameter("companyId"));
        companyFunctionSet.setCompanyId(companyId);
        companyFunctionSet.setFunctionCode("COURSE_BUY_SET");
        List<CompanyFunctionSet> companyFunctionSetList = companyFunctionSetServiceImpl.findCompanyFunctionSetByPage(companyFunctionSet);
        CompanyFunctionSet cfs = companyFunctionSetList != null && companyFunctionSetList.size() > 0 ? companyFunctionSetList.get(0) : null;
        if (null != cfs && null != cfs.getStatus() && cfs.getStatus().equals("1")) {
            return "show";
        }
        return "hide";
    }
	
    @ResponseBody
    @RequestMapping(value = "/queryMemberConfig")
    public CompanyMemberConfig queryIntegralConfigByCondition(CompanyMemberConfig search) {
        CompanyMemberConfig config = companyMemberConfigServiceImpl.queryMemberSets(search);
        return config;

    }
	    
    /**
	 * Class Name: CompanyMemberLevelConfigController.java
	 * @Description: 查询会员等级列表
	 */
	@ResponseBody
	@RequestMapping(value = "/queryLevelList")
	public List<CompanyMemberLevelConfig> queryMemberLevelList(Integer classTypeId,Integer companyId) {
		ClassTypeMemberDiscount search1 = new ClassTypeMemberDiscount();
		search1.setCompanyId(companyId);
		search1.setClassTypeId(classTypeId);
		List<ClassTypeMemberDiscount> classLevelList = classTypeMemberDiscountServiceImpl.findClassLevelList(search1);
		CompanyMemberLevelConfig search = new CompanyMemberLevelConfig();
		search.setCompanyId(companyId);
		List<CompanyMemberLevelConfig> companyLeveList = companyMemberLevelConfigServiceImpl
				.queryMemberLevelList(search);
		if (null != companyLeveList) {
			for (CompanyMemberLevelConfig companylevel : companyLeveList) {
				if (null != companylevel) {
					for (ClassTypeMemberDiscount classlevel : classLevelList) {
						if (null != classlevel && (companylevel.getId() == classlevel.getMemberId() || companylevel.getId().equals(classlevel.getMemberId()))) {
							companylevel.setDiscount(classlevel.getMemberDiscount());
							companylevel.setUseFlag(1);
							companylevel.setDiscountType(classlevel.getDiscountType());
						}
					}
				}
			}
		}
		return companyLeveList;
	}
		
	@ResponseBody
	@RequestMapping("/queryCompanyService")
	public Object queryCompanyService(Integer companyId,String groupCode){
		return WebUtils.checkCompanyServiceIsOpen(companyId, groupCode);
	}
		
	/**
	 * @Description:(获取自定义章节名称)
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/customChapter", method = RequestMethod.POST)
	public Map<String,Object> queryCustomChapterName(Integer companyId){
		CompanyFunctionSet search=new CompanyFunctionSet();
		search.setCompanyId(companyId);
		search.setFunctionCode("COMPANY_COURSE_SET");
		search.setStatus("1");
		Map<String,Object> map=new HashMap<String,Object>();
		List<CompanyFunctionSet> sets=companyFunctionSetServiceImpl.findCompanyFunctions(search);
		if(sets!=null && sets.size()>0){
			map.put("chapterName", sets.get(0).getColumn1());
			map.put("lectureName", sets.get(0).getColumn2());
		}
		//查询机构是否开启题库服务
		SysConfigService scs = new SysConfigService();
		scs.setCompanyId(companyId);
		scs.setGroupCode("SERVICE_TIKU");
		scs = sysconfigServiceImpl.findExist(scs);
		map.put("tikuService", scs != null && scs.getDelFlag() != null && (int)scs.getDelFlag() == 1);
		
		return map;
	}
		
	 /**
     * 课程资料列表
     */
    @ResponseBody
    @RequestMapping("/rourseList")
    public List<ClassTypeResourceVo> selResource(HttpServletRequest request, ClassTypeResource res) {
        // 查询
        List<ClassTypeResourceVo> resList = this.classTypeResourceServiceImpl.findResByCondition(res);
        return resList;
    }

	@ResponseBody
	@RequestMapping(value="/find", method = RequestMethod.POST)
	public CourseExerciseVo find(HttpServletRequest request,CourseExercise exercise){
		List<CourseExerciseVo> cc=courseExerciseServiceImpl.searchCourseExercise(exercise);
		if(cc!=null && !cc.isEmpty()){
			return cc.get(0);
		}
		return null;
	}

}
