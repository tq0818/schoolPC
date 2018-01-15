package com.yuxin.wx.controller.classes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
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
import com.yuxin.wx.api.classes.IClassTypeOfBranchSchoolService;
import com.yuxin.wx.api.classes.IClassTypeResourceTypeService;
import com.yuxin.wx.api.classes.IClassTypeService;
import com.yuxin.wx.api.commodity.ICommodityProductRealtionService;
import com.yuxin.wx.api.company.ICompanyFunctionSetService;
import com.yuxin.wx.api.company.ICompanyLiveStaticDetailService;
import com.yuxin.wx.api.company.ICompanyRegisterConfigService;
import com.yuxin.wx.api.student.IStudentService;
import com.yuxin.wx.api.system.ISysConfigDictService;
import com.yuxin.wx.api.system.ISysConfigItemRelationService;
import com.yuxin.wx.api.system.ISysConfigItemService;
import com.yuxin.wx.api.system.ISysConfigTeacherService;
import com.yuxin.wx.common.JsonMsg;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.classes.ClassModuleLesson;
import com.yuxin.wx.model.classes.ClassModuleNo;
import com.yuxin.wx.model.classes.ClassType;
import com.yuxin.wx.model.classes.ClassTypeResourceType;
import com.yuxin.wx.model.commodity.Commodity;
import com.yuxin.wx.model.commodity.CommodityProductRealtion;
import com.yuxin.wx.model.company.CompanyFunctionSet;
import com.yuxin.wx.model.company.CompanyRegisterConfig;
import com.yuxin.wx.model.statistics.QueryLessonByClassTypeVo;
import com.yuxin.wx.model.student.Student;
import com.yuxin.wx.model.system.SysConfigDict;
import com.yuxin.wx.model.system.SysConfigItem;
import com.yuxin.wx.model.system.SysConfigItemRelation;
import com.yuxin.wx.model.system.SysConfigTeacher;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.utils.DateUtil;
import com.yuxin.wx.utils.PropertiesUtil;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.classes.ClassTypeVo;
import com.yuxin.wx.vo.classes.ClassmNoVo;
import com.yuxin.wx.vo.course.ChapterAndLectureListVo;

/**
 * 分校间课程分享
 * @author cxl
 *
 */
@Controller
@RequestMapping("/otherSchool")
public class ClassTypeOfOtherSchoolContorller {

	private Log log=LogFactory.getLog("log");

	@Autowired
	private IClassTypeOfBranchSchoolService classTypeOfBranchSchoolService;

    @Autowired
    private IClassModuleService classModuleServiceImpl;
    @Autowired
    private PropertiesUtil propertiesUtil;

    @Autowired
    private IClassTypeService classTypeServiceImpl;
    @Autowired
    private IStudentService studentServiceImpl;

    @Autowired
    private ISysConfigItemRelationService sysConfigItemRelationServiceImpl;
    
    @Autowired
    private ISysConfigItemService sysConfigItemServiceImpl;

	@Autowired
	private ISysConfigDictService sysConfigDictServiceImpl;

	@Autowired
	private IClassTypeResourceTypeService classTypeResourceTypeServiceImpl;
    
    @Autowired
    private ICompanyFunctionSetService companyFunctionSetServiceImpl;

    @Autowired
    private ISysConfigTeacherService sysConfigTeacherServiceImpl;

    @Autowired
    private ICommodityProductRealtionService commodityProductRealtionServiceImpl;

	@Autowired
	private IClassModuleNoService classModuleNoServiceImpl;
	
	@Autowired
	private IClassModuleLessonService classModuleLessonServiceImpl;

    @Autowired
    private ICompanyRegisterConfigService companyRegisterConfigServiceImpl;
    

	@Autowired
	private ICompanyLiveStaticDetailService companyLiveStaticDetailServiceImpl;
	
	/**
     * 分校间购买/分享课程管理
     * @return
     */
    @RequestMapping(value = "/queryClassType")
    public String queryClassType(Model model,HttpServletRequest request){
    	Map<String, Object> params=new HashMap<String, Object>();
    	params.put("level", 0);
    	params.put("parentCode", "TYPE");
    	params.put("companyId", WebUtils.getCurrentCompanyId());
       	List<SysConfigItemRelation> relations=classTypeOfBranchSchoolService.findItemFront(params);
       	model.addAttribute("oneItems", relations);
        return "classType/otherSchool/classTypeManage";
    }
    
   @RequestMapping("/classTypeDetail")
  	public String classTypeDetail(Model model,HttpServletRequest req,ClassTypeVo classtype){
    	Map<String, Object> queryParams=new HashMap<String, Object>();
    	queryParams.put("companyId", WebUtils.getCurrentCompanyId());
    	queryParams.put("firstItemCode", req.getParameter("firstItemCode"));
    	queryParams.put("secondItemCode", req.getParameter("secondItemCode"));
    	queryParams.put("thirdItemCode", req.getParameter("thirdItemCode"));
    	queryParams.put("status", req.getParameter("status"));
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
    	List<ClassTypeVo> clist=classTypeOfBranchSchoolService.queryClassTypeOfOtherSchool(queryParams);
    	int count=classTypeOfBranchSchoolService.queryCountClassTypeOfOtherSchool(queryParams);
		PageFinder<ClassTypeVo> msgPage = new PageFinder<ClassTypeVo>(classtype.getPage(), classtype.getPageSize(), count, clist);
		model.addAttribute("msgPage", msgPage);
    	return "classType/otherSchool/classTypeManageDetail";
    }

   @RequestMapping(value = "/classBaseInfo/{id}/{lable}")
   public String classBaseInfo(Model model, @PathVariable Integer id, @PathVariable String lable) {
   	Map<String, String> map = new HashMap<String, String>();
		map.put("classId", "" + id);
		ClassTypeVo ct=this.classTypeOfBranchSchoolService.findClassTypeDetail(map);
		model.addAttribute("ct", ct);
       model.addAttribute("lable", lable);
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
       	model.addAttribute("liveModuleList", modulesVoList);
       }
       return "classType/branchSchool/classTypeBaseInfo";
   }
   
    @ResponseBody
  	@RequestMapping("/selOneItem")
  	public JSONObject selOneItem(HttpServletRequest request){
   	Map<String, Object> params=new HashMap<String, Object>();
   	params.put("level", 0);
   	params.put("companyId", WebUtils.getCurrentCompanyId());
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
    	params.put("companyId", WebUtils.getCurrentCompanyId());
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
    	params.put("companyId", WebUtils.getCurrentCompanyId());
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
        	Map<String, Object> params=new HashMap<String, Object>();
        	params.put("updator", WebUtils.getCurrentUserId(request));
        	params.put("status", request.getParameter("type"));
        	params.put("publishStatus", request.getParameter("publishStatus"));
        	params.put("isSale", request.getParameter("type"));
    		String[] ids=request.getParameterValues("cIds[]");
    		params.put("cids", Arrays.asList(ids));
    		if("1".equals(request.getParameter("type"))){
    			String	result=classTypeOfBranchSchoolService.validateOnSale(params);
    			if(StringUtils.isNotEmpty(result)){
    				json.put(JsonMsg.RESULT, result);
    				return json;
    			}
    		}
    		classTypeOfBranchSchoolService.battchSaleOrStopSale(params);
        	json.put(JsonMsg.RESULT, JsonMsg.SUCCESS);
		} catch (Exception e) {
			log.error(e.getMessage());
			json.put(JsonMsg.RESULT, JsonMsg.ERROR);
		}
    	return json;
    }
    
    @RequestMapping(value = "/editClassBaseInfo/{id}/{lable}")
    public String editClassBaseInfo( HttpServletRequest request,  Model model, @PathVariable Integer id, @PathVariable String lable) {
        // 根据班型id查询详情
        Map<String, String> map = new HashMap<String, String>();
        map.put("classId", String.valueOf(id));
        ClassTypeVo classType = this.classTypeOfBranchSchoolService.findClassTypeDetail1(map);
        
        model.addAttribute("classType", classType);
        model.addAttribute("ct", classType);
        model.addAttribute("type", "update");
        model.addAttribute("lable", lable);
//        SysConfigItemRelation relation = new SysConfigItemRelation();
//        relation.setId(null);
//        List<SysConfigItemRelation> relations = sysConfigItemRelationServiceImpl.findItemFront(relation);
//        SysConfigItem item = new SysConfigItem();
//        item.setCompanyId(WebUtils.getCurrentCompanyId());
//        item.setSchoolId( WebUtils.getCurrentUserSchoolId(request));
//        item.setItemType("2");
//        item.setParentCode("TYPE");
//        List<SysConfigItem> names = sysConfigItemServiceImpl.findByParentCode(item);
//        for(SysConfigItemRelation re : relations){
//            for(SysConfigItem name :names){
//                if(re.getItemCode().equals(name.getItemCode())){
//                    re.setItemName(name.getItemName());
//                    break;
//                }
//            }
//        }
        
        Map<String, Object> params=new HashMap<String, Object>();
    	params.put("level", 0);
    	params.put("companyId", WebUtils.getCurrentCompanyId());
    	params.put("parentCode", "TYPE");
       	List<SysConfigItemRelation> relations=classTypeOfBranchSchoolService.findItemFront(params);
        model.addAttribute("typeItems", relations);
        model.addAttribute("ftype", "live");
        // 根据所属标签跳转不同页面
        return "classType/otherSchool/classTypeLiveMessage";
    }
    
    @RequestMapping(value = "/editliveOrface/{id}/{lable}")
    public String editliveOrface(@PathVariable Integer id, @PathVariable String lable, Model model) {
        ClassType ct = this.classTypeServiceImpl.findClassTypeById(id);
        SysConfigItem item = this.sysConfigItemServiceImpl.findSysConfigItemById(ct.getItemOneId());
        SysConfigItem item2 = this.sysConfigItemServiceImpl.findSysConfigItemById(ct.getItemSecondId());
        if (item != null) {
            ct.setItemOneName(item.getItemName());
        }
        if (item2 != null) {
            ct.setItemSecondName(item2.getItemName());
        }
        boolean flag = this.companyFunctionSetServiceImpl.isCurrentFuSheng(WebUtils.getCurrentCompanyId());
        Users currentUser = WebUtils.getCurrentUser();
        Subject subject = SecurityUtils.getSubject();
        boolean isJigou = subject.hasRole("机构管理员");
        boolean isFenxiao = subject.hasRole("分校管理员");
        boolean isYunYing = subject.hasRole("运营");
        model.addAttribute("isJigou", isJigou);
        model.addAttribute("isFenxiao", isFenxiao);
        model.addAttribute("isYunYing", isYunYing);
        if (subject.hasRole("排课老师")) {
            // search.setTeacherId();
            List<SysConfigTeacher> teacherList = this.sysConfigTeacherServiceImpl.findTeachersByUserId(currentUser.getId());
            if (teacherList != null && teacherList.size() > 0) {
                SysConfigTeacher configTeacher = teacherList.get(0);
                model.addAttribute("teacherId", configTeacher.getId());
            }
        }
        if (isJigou || isFenxiao || isYunYing) {
            model.addAttribute("guanliyuan", true);
        }
        model.addAttribute("isFuSheng", flag);
        model.addAttribute("ct", ct);
        model.addAttribute("lable", lable);
        return "classType/otherSchool/classTypeLiveMessage_3";
    }
    
    @RequestMapping(value = "/editCourseDetail/{id}/{lable}")
    public String editCourseDetail(Model model, @PathVariable Integer id, @PathVariable String lable) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("classId", String.valueOf(id));
        ClassTypeVo cs = this.classTypeOfBranchSchoolService.findClassTypeDetail1(map);
        model.addAttribute("type2", "update");
        model.addAttribute("ct", cs);
        model.addAttribute("lable", lable);
        if (cs.getCover() != null && !"".equals(cs.getCover())) {
            String url = "http://" + this.propertiesUtil.getProjectImageUrl() + "/";
            cs.setCover(url + cs.getCover());
        } else {
            cs.setCover("");
        }
        model.addAttribute("ftype", "live");
        SysConfigItem item = this.sysConfigItemServiceImpl.findSysConfigItemById(cs.getItemOneId());
        SysConfigItem item2 = this.sysConfigItemServiceImpl.findSysConfigItemById(cs.getItemSecondId());
        if (item != null) {
            cs.setItemOneName(item.getItemName());
        }
        if (item2 != null) {
            cs.setItemSecondName(item2.getItemName());
        }
        model.addAttribute("ct", cs);
        model.addAttribute("ctype", cs);
        if (cs.getTeacherId() != null) {
            SysConfigTeacher teacher = this.sysConfigTeacherServiceImpl.findSysConfigTeacherById(Integer.parseInt(cs.getTeacherId()));
            model.addAttribute("teacher", teacher);
        }
        model.addAttribute("classTypeId", cs.getId());
        // 根据班型id查询商品信息id
        CommodityProductRealtion comm = this.commodityProductRealtionServiceImpl.findByClassTypeId(cs.getId() + "");
        Integer cId = comm.getComId();
        model.addAttribute("cId", cId);
        model.addAttribute("itemOneid", cs.getItemOneId());
        return "classType/otherSchool/classTypeLiveMessage_2";
    }
    // 跳转到学员列表页面
    @RequestMapping(value = "/studentList/{id}/{lable}")
    public String forwardStudentList(Model model, @PathVariable Integer id, @PathVariable String lable) {
        // 根据班型id查询详情
        Map<String, String> map = new HashMap<String, String>();
        map.put("classId", "" + id);
        ClassTypeVo classType = this.classTypeServiceImpl.findClassTypeDetail(map);
        model.addAttribute("classType", classType);
        model.addAttribute("ct", classType);
        model.addAttribute("type", "update");
        model.addAttribute("lable", lable);
        CompanyFunctionSet search = new CompanyFunctionSet();
        search.setFunctionCode("COMPANY_FUNCTION_COURSE");
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
            return "classType/otherSchool/studentList-full";
        }
        return "classType/otherSchool/studentList";
    }
    
    /**
     * 
     * Class Name: StudentPayMasterController.java
     * 
     * @Description: ajax方式加载学员信息列表
     * @author zhang.zx
     * @date 2014年12月12日 下午3:33:04
     * @version 1.0
     * @param search
     * @return
     */
    @RequestMapping(value = "/showSignUp", method = RequestMethod.POST)
    public String showSignUp(Model model, Student student, Integer classTypeId, String lable) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("classId", "" + classTypeId);
        ClassTypeVo classType = this.classTypeServiceImpl.findClassTypeDetail(map);
        model.addAttribute("classType", classType);
        model.addAttribute("lable", lable);
        student.setCompanyId(WebUtils.getCurrentCompanyId());
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
            return "classType/otherSchool/student-signup-full";
        }
        return "classType/otherSchool/student-signup";
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
       ClassTypeVo classType = classTypeServiceImpl.findClassTypeDetail(map);
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
       return "classType/otherSchool/StudentsManage";
   }
    
   // 导入成功后查询导入数据页面
   @RequestMapping(value = "/queryData")
   public String importStudentData(Model model, Integer id, String lable, String stuMobiles) {
       Map<String, String> map = new HashMap<String, String>();
       map.put("classId", "" + id);
       ClassTypeVo classType = this.classTypeServiceImpl.findClassTypeDetail(map);
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
       return "classType/otherSchool/importStudentData";
   }
   
   @RequestMapping(value = "/insertStudents")
   public String insertStudents(String list, Integer classtypeId, String lable, HttpServletRequest request, Model model) {
       // 根据班型id查询详情
       Map<String, String> map1 = new HashMap<String, String>();
       map1.put("classId", "" + classtypeId);
       ClassTypeVo classType = classTypeServiceImpl.findClassTypeDetail(map1);
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
           return "classType/otherSchool/student-signupMany-full";
       }
       return "classType/otherSchool/student-signupMany";
   }
   
   
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
		/*CompanyFunctionSet search=new CompanyFunctionSet();
		search.setFunctionCode("COMPANY_FUNCTION_COURSE");
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		CompanyFunctionSet cfs=companyFunctionSetServiceImpl.findCompanyUseCourse(search);
		if(cfs!=null && "1".equals(cfs.getStatus())){
			return "classType/commClassResource";
		}*/
 		model.addAttribute("lable", lable);
		return "classType/otherSchool/commClassResource";
	}
    
    @RequestMapping(value = "/companyLiveStaticDetailList/{classTypeId}/{lable}")
	public String statisticsList(Model model, @PathVariable Integer classTypeId, @PathVariable String lable) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("classId", "" + classTypeId);
		ClassTypeVo classType = classTypeServiceImpl.findClassTypeDetail(map);
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
		/*areaDict.setDictCode("EDU_SCHOOL_AREA");
		List<SysConfigDict> areas = sysConfigDictServiceImpl.queryConfigDictListByDictCode(areaDict);
		model.addAttribute("areas", areas);*/
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
		return "classType/otherSchool/liveClassCount";
	}
}
