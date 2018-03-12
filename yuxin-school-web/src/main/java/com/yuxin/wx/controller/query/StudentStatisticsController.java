package com.yuxin.wx.controller.query;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.classes.IClassTypeService;
import com.yuxin.wx.api.company.ICompanyFunctionSetService;
import com.yuxin.wx.api.company.ICompanyPayConfigService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.query.IStudentStatisticsService;
import com.yuxin.wx.api.query.ISysPlayLogsService;
import com.yuxin.wx.api.student.IStudentService;
import com.yuxin.wx.api.system.ISysConfigDictService;
import com.yuxin.wx.api.system.ISysConfigItemRelationService;
import com.yuxin.wx.api.system.ISysConfigItemService;
import com.yuxin.wx.api.user.IUserHistoryService;
import com.yuxin.wx.api.user.IUsersFrontService;
import com.yuxin.wx.api.user.IUsersService;
import com.yuxin.wx.common.CCVideoConstant;
import com.yuxin.wx.common.ExcelSheetEntity;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.common.PageFinder2;
import com.yuxin.wx.common.SimplePage;
import com.yuxin.wx.common.ViewFiles;
import com.yuxin.wx.model.classes.ClassType;
import com.yuxin.wx.model.classes.EduMasterClass;
import com.yuxin.wx.model.company.CompanyFunctionSet;
import com.yuxin.wx.model.company.CompanyPayConfig;
import com.yuxin.wx.model.system.SysConfigDict;
import com.yuxin.wx.model.system.SysConfigItem;
import com.yuxin.wx.model.system.SysConfigItemRelation;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.model.watchInfo.WatchInfoResult;
import com.yuxin.wx.shiro.service.RedisHelper;
import com.yuxin.wx.utils.CommonUtils;
import com.yuxin.wx.utils.DateUtil;
import com.yuxin.wx.utils.EntityUtil;
import com.yuxin.wx.utils.ExcelUtil;
import com.yuxin.wx.utils.HttpPostRequest;
import com.yuxin.wx.utils.MD5;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.classes.ClassLessonVO;
import com.yuxin.wx.vo.classes.ClassTypeVo;
import com.yuxin.wx.vo.company.CompanySchoolVO;
import com.yuxin.wx.vo.company.CompanyVo;
import com.yuxin.wx.vo.course.UserVideoVo;
import com.yuxin.wx.vo.course.VideoCourseVo;
import com.yuxin.wx.vo.redis.ClassLectureVO;
import com.yuxin.wx.vo.student.StudentListVo;
import com.yuxin.wx.vo.user.UserHistoryAllVo;
import com.yuxin.wx.vo.user.UsersAreaRelation;
import com.yuxin.wx.vo.user.UsersFrontVo;
import redis.clients.jedis.BinaryClient;

@Controller
@RequestMapping("/query")
public class StudentStatisticsController {
    private ExecutorService fixedThreadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    @Autowired
	private IStudentStatisticsService studentStatisticsServiceImpl;
    @Autowired
    private ISysConfigDictService sysConfigDictServiceImpl;
    @Autowired
    private ICompanyFunctionSetService companyFunctionSetServiceImpl;
    @Autowired
    private IUsersService usersServiceImpl;
    @Autowired
    private IStudentService studentServiceImpl;
    @Autowired
    private ISysConfigItemService sysConfigItemService;
    @Autowired
    private ISysConfigItemRelationService sysConfigItemRelationServiceImpl;

    @Autowired
    private ISysPlayLogsService sysPlayLogsServiceImpl;
    @Autowired
    private ICompanyPayConfigService companyPayConfigServiceImpl;
    @Autowired
    private IClassTypeService classTypeServiceImpl;
    @Autowired
    private IUserHistoryService userHistoryServiceImpl;
    @Autowired
    private IUsersFrontService usersFrontService;
    @Autowired
    private ICompanyService companyService;

	/**
	 * 页面跳转
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/statistics/index")
	public String index(Model model, HttpServletRequest request){
        //获取账号对应用户信息
        UsersAreaRelation uersAreaRelation = new UsersAreaRelation();
		//学员总数
		Long allStuNum = studentStatisticsServiceImpl.getAllStudentNum(uersAreaRelation);
		//完善属性学员总数
		Long completeStuNum = studentStatisticsServiceImpl.getAllStudentNumOfComplete(uersAreaRelation);

		model.addAttribute("allStuNum",allStuNum);
		model.addAttribute("completeStuNum",completeStuNum);

		return "/query/index";
	}

	/**
	 * 数据查询
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/statistics/indexOfComplete")
	@ResponseBody
	public List<Map> indexOfComplete(Model model, HttpServletRequest request){
		//区域学员统计
		List<Map> areaStuStatistics = studentStatisticsServiceImpl.getAreaStudentStatistics();
		return areaStuStatistics;
	}

    /**
     * 页面跳转
     * @param model
     * @return
     */
    @RequestMapping(value="/statistics/studentList")
    public String studentList(Model model, HttpServletRequest request){
        // 查询课程的多课程单元和多班号功能
        CompanyFunctionSet search = new CompanyFunctionSet();
        search.setFunctionCode("COMPANY_FUNCTION_COURSE");
        search.setCompanyId(WebUtils.getCurrentCompanyId());
        //查询学校所在区域
        SysConfigDict areaDict = new SysConfigDict();
        areaDict.setDictCode("EDU_SCHOOL_AREA");

        List<SysConfigDict> areas = sysConfigDictServiceImpl.queryConfigDictListByDictCode(areaDict);
        model.addAttribute("areas", areas);
        
        // 学员分组
        CompanyFunctionSet companyFunctionSet = new CompanyFunctionSet();
        companyFunctionSet.setCompanyId(WebUtils.getCurrentCompanyId());
        companyFunctionSet.setFunctionCode("STUDENT_GROUP");
        List<CompanyFunctionSet> companyFunctionSetList = companyFunctionSetServiceImpl.findCompanyFunctionSetByPage(companyFunctionSet);
        if (companyFunctionSetList != null && companyFunctionSetList.size() > 0) {
            model.addAttribute("sgOpen", companyFunctionSetList.get(0).getStatus());
        }

        // 查看该机构学员地址信息配置功能
        search.setFunctionCode("STUDENT_ADDRESS_INFO");
        CompanyFunctionSet address = companyFunctionSetServiceImpl.findCompanyUseCourse(search);
        if (address != null && "1".equals(address.getStatus())) {
            model.addAttribute("address", 1);
        } else {
            model.addAttribute("address", 0);
        }
        areaDict.setDictCode("EDU_STEP");
        List<SysConfigDict> steps = sysConfigDictServiceImpl.queryConfigDictListByDictCode(areaDict);
        model.addAttribute("steps", steps);
        //年份列表
        List<Integer> years = new ArrayList<Integer>();
        int curYear = DateUtil.getCurYear();
        for(int year = 0;year<6;year++){
            years.add(curYear-year);
        }
        model.addAttribute( "years", years);
        CompanyFunctionSet userorg_roleopenflag = WebUtils.getFunctionSet("USERORG_ROLEOPENFLAG");
        model.addAttribute("userorg_roleopenflag", userorg_roleopenflag==null?0:userorg_roleopenflag.getStatus());
       
        return "/query/query_student";
    }

    /**
     * 页面跳转
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value="/statistics/queryOrg")
    public String queryOrg(Model model, HttpServletRequest request){
        //查询学校所在区域
        SysConfigDict areaDict = new SysConfigDict();
        areaDict.setDictCode("EDU_SCHOOL_AREA");
        List<SysConfigDict> areas = sysConfigDictServiceImpl.queryConfigDictListByDictCode(areaDict);
        model.addAttribute("areas", areas);

        //查询学校所属学段
        SysConfigDict stepDict = new SysConfigDict();
        stepDict.setDictCode("EDU_STEP");
        List<SysConfigDict> stepNews = sysConfigDictServiceImpl.queryConfigDictListByDictCode(stepDict);
        model.addAttribute("stepNews", stepNews);
        return "/query/query_org";
    }

    /**
     * 数据查询
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value="/statistics/orgStudentTotalStatistics")
    @ResponseBody
    public Map<String, Object> orgStudentTotalStatistics(Model model, HttpServletRequest request){
        //区域学员统计
        String eduArea = request.getParameter("eduArea");
        String eduStep = request.getParameter("eduStep");
        Subject subject = SecurityUtils.getSubject();
        if(StringUtils.isBlank(eduArea) || "null".equals(eduArea)){
            return null;
        }

        Map<String, Object> orgStuStatistics = null;
        if(!subject.hasRole("学校负责人")){
            if(StringUtils.isBlank(eduStep) || "null".equals(eduStep)){
                return null;
            }
            //传递参数
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("eduArea",eduArea);
            map.put("eduStep",eduStep);
            orgStuStatistics = studentStatisticsServiceImpl.getOrgStudentTotalStatisticsByAreaAndStep(map);
        }else{
            //传递参数
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("orgCode",eduArea);
//            orgStuStatistics = studentStatisticsServiceImpl.getOrgStudentStatistics(map);
        }
        //传递参数
        return orgStuStatistics;
    }

    /**
     * 数据查询
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value="/statistics/areaTotalStatistics")
    @ResponseBody
    public JSONObject areaTotalStatistics(Model model, HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        //区域学员统计
        String eduArea = request.getParameter("eduArea");
        String eduStep = request.getParameter("eduStep");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("eduArea",eduArea);
        map.put("eduStep",eduStep);
        List<Map<String, Object>> areaStatistics = studentStatisticsServiceImpl.getAreaTotalStatistics(map);
        //传递参数
        jsonObject.put("data", areaStatistics);
        return jsonObject;
    }

    /**
     * 数据查询
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value="/statistics/orgStudentStatistics")
    @ResponseBody
    public List<Map> orgStudentStatistics(Model model, HttpServletRequest request){
        //区域学员统计
        String eduArea = request.getParameter("eduArea");
        String eduStep = request.getParameter("eduStep");
        Subject subject = SecurityUtils.getSubject();
        if(StringUtils.isBlank(eduArea) || "null".equals(eduArea)){
            return null;
        }

        List<Map> orgStuStatistics = null;
//        if(!subject.hasRole("学校负责人")){
        if(StringUtils.isNotBlank(eduStep)){
            //传递参数
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("eduArea",eduArea);
            map.put("eduStep",eduStep);
            orgStuStatistics = studentStatisticsServiceImpl.getOrgStudentStatisticsByAreaAndStep(map);
        }else{
            //传递参数
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("orgCode",eduArea);
            orgStuStatistics = studentStatisticsServiceImpl.getOrgStudentStatistics(map);
        }
        //传递参数
        return orgStuStatistics;
    }

    /**
     * 页面跳转
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value="/areastatistics/index")
    public String areaindex(Model model, HttpServletRequest request) throws Exception {
        Users loginUser = WebUtils.getCurrentUser(request);
        if(loginUser==null || loginUser.getId()==null){
            throw new Exception("数据出现异常，请联系管理员！");
        }
        //获取账号对应用户信息
        UsersAreaRelation uersAreaRelation = usersServiceImpl.findUsersAreaRelation(loginUser.getId());
        if(uersAreaRelation==null){
            throw new Exception("数据出现异常，请联系管理员！");
        }
        //学员总数
        Long allStuNum = studentStatisticsServiceImpl.getAllStudentNum(uersAreaRelation);
        //完善属性学员总数
        Long completeStuNum = studentStatisticsServiceImpl.getAllStudentNumOfComplete(uersAreaRelation);

        //查询学校所在区域
        SysConfigDict areaDict = new SysConfigDict();
        areaDict.setItemCode(uersAreaRelation.getEduArea());
        areaDict.setDictCode("EDU_SCHOOL_AREA");
        List<SysConfigDict> area = sysConfigDictServiceImpl.queryConfigDictListByDictCode(areaDict);
        if(area!=null && area.get(0)!=null){
            model.addAttribute("areaDict", area.get(0));
        }
        model.addAttribute("allStuNum",allStuNum);
        model.addAttribute("completeStuNum",completeStuNum);
        model.addAttribute("eduArea", uersAreaRelation.getEduArea());
        return "/query/areaindex";
    }

    /**
     * 页面跳转
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value="/areastatistics/queryOrg")
    public String queryOrgArea(Model model, HttpServletRequest request) throws Exception {
        Users loginUser = WebUtils.getCurrentUser(request);
        if(loginUser==null || loginUser.getId()==null){
            throw new Exception("数据出现异常，请联系管理员！");
        }
        //获取账号对应用户信息
        UsersAreaRelation uersAreaRelation = usersServiceImpl.findUsersAreaRelation(loginUser.getId());
        if(uersAreaRelation==null){
            throw new Exception("数据出现异常，请联系管理员！");
        }

        //查询学校所在区域
        SysConfigDict areaDict = new SysConfigDict();
        areaDict.setItemCode(uersAreaRelation.getEduArea());
        areaDict.setDictCode("EDU_SCHOOL_AREA");
        List<SysConfigDict> area = sysConfigDictServiceImpl.queryConfigDictListByDictCode(areaDict);
        if(area!=null && area.get(0)!=null){
            model.addAttribute("area", area.get(0));
        }

        //查询学校所属学段
        SysConfigDict stepDict = new SysConfigDict();
        stepDict.setDictCode("EDU_STEP");
        List<SysConfigDict> stepNews = sysConfigDictServiceImpl.queryConfigDictListByDictCode(stepDict);
        model.addAttribute("stepNews", stepNews);

        return "/query/query_org_area";
    }

    /**
     * 页面跳转
     * @param model
     * @return
     */
    @RequestMapping(value="/areastatistics/studentList")
    public String studentListArea(Model model, HttpServletRequest request) throws Exception {
        Users loginUser = WebUtils.getCurrentUser(request);
        if(loginUser==null || loginUser.getId()==null){
            throw new Exception("数据出现异常，请联系管理员！");
        }
        //获取账号对应用户信息
        UsersAreaRelation uersAreaRelation = usersServiceImpl.findUsersAreaRelation(loginUser.getId());
        if(uersAreaRelation==null){
            throw new Exception("数据出现异常，请联系管理员！");
        }
        // 查询课程的多课程单元和多班号功能
        CompanyFunctionSet search = new CompanyFunctionSet();
        search.setFunctionCode("COMPANY_FUNCTION_COURSE");
        search.setCompanyId(WebUtils.getCurrentCompanyId());
        //查询学校所在区域
        SysConfigDict areaDict = new SysConfigDict();
        areaDict.setDictCode("EDU_SCHOOL_AREA");
        areaDict.setItemCode(uersAreaRelation.getEduArea());
        List<SysConfigDict> areas = sysConfigDictServiceImpl.queryConfigDictListByDictCode(areaDict);
        if(areas!=null && areas.get(0)!=null){
            model.addAttribute("area", areas.get(0));
        }

        // 学员分组
        CompanyFunctionSet companyFunctionSet = new CompanyFunctionSet();
        companyFunctionSet.setCompanyId(WebUtils.getCurrentCompanyId());
        companyFunctionSet.setFunctionCode("STUDENT_GROUP");
        List<CompanyFunctionSet> companyFunctionSetList = companyFunctionSetServiceImpl.findCompanyFunctionSetByPage(companyFunctionSet);
        if (companyFunctionSetList != null && companyFunctionSetList.size() > 0) {
            model.addAttribute("sgOpen", companyFunctionSetList.get(0).getStatus());
        }

        // 查看该机构学员地址信息配置功能
        search.setFunctionCode("STUDENT_ADDRESS_INFO");
        CompanyFunctionSet address = companyFunctionSetServiceImpl.findCompanyUseCourse(search);
        if (address != null && "1".equals(address.getStatus())) {
            model.addAttribute("address", 1);
        } else {
            model.addAttribute("address", 0);
        }
        model.addAttribute("isAreaSchool1", request.getSession().getAttribute("isAreaSchool1"));
        CompanyFunctionSet userorg_roleopenflag = WebUtils.getFunctionSet("USERORG_ROLEOPENFLAG");
        model.addAttribute("userorg_roleopenflag", userorg_roleopenflag==null?0:userorg_roleopenflag.getStatus());
        return "/query/query_student_area";
    }
    /**
     * 
     * @author jishangyang 2018年1月2日 下午1:31:12
     * @Method: userList 
     * @Description:用户统计
     * @param model
     * @param request
     * @return
     * @throws Exception 
     * @throws
     */
    @RequestMapping(value="/areastatistics/userList")
    public String userList(Model model, HttpServletRequest request) throws Exception {
    	Users loginUser = WebUtils.getCurrentUser(request);
    	if(loginUser==null || loginUser.getId()==null){
    		throw new Exception("数据出现异常，请联系管理员！");
    	}
    	//获取账号对应用户信息
    	UsersAreaRelation uersAreaRelation = usersServiceImpl.findUsersAreaRelation(loginUser.getId());
    	if(uersAreaRelation==null){
    		throw new Exception("数据出现异常，请联系管理员！");
    	}
    	// 查询课程的多课程单元和多班号功能
    	CompanyFunctionSet search = new CompanyFunctionSet();
    	search.setFunctionCode("COMPANY_FUNCTION_COURSE");
    	search.setCompanyId(WebUtils.getCurrentCompanyId());
    	//查询学校所在区域
    	SysConfigDict areaDict = new SysConfigDict();
    	areaDict.setDictCode("EDU_STEP");
    	List<SysConfigDict> steps = sysConfigDictServiceImpl.queryConfigDictListByDictCode(areaDict);
    	model.addAttribute("steps", steps);
    	//年份列表
    	List<Integer> years = new ArrayList<Integer>();
    	int curYear = DateUtil.getCurYear();
    	for(int year = 0;year<6;year++){
    		years.add(curYear-year);
    	}
    	model.addAttribute( "years", years);
    	areaDict.setDictCode("EDU_SCHOOL_AREA");
    	areaDict.setItemCode(uersAreaRelation.getEduArea());
    	List<SysConfigDict> areas = sysConfigDictServiceImpl.queryConfigDictListByDictCode(areaDict);
    	model.addAttribute("areas", areas);
    	/*List<SysConfigDict> areas = sysConfigDictServiceImpl.queryConfigDictListByDictCode(areaDict);
    	if(areas!=null && areas.get(0)!=null){
    		model.addAttribute("area", areas.get(0));
    	}*/

    	// 学员分组
//    	CompanyFunctionSet companyFunctionSet = new CompanyFunctionSet();
//    	companyFunctionSet.setCompanyId(WebUtils.getCurrentCompanyId());
//    	companyFunctionSet.setFunctionCode("STUDENT_GROUP");
//    	List<CompanyFunctionSet> companyFunctionSetList = companyFunctionSetServiceImpl.findCompanyFunctionSetByPage(companyFunctionSet);
//    	if (companyFunctionSetList != null && companyFunctionSetList.size() > 0) {
//    		model.addAttribute("sgOpen", companyFunctionSetList.get(0).getStatus());
//    	}
//    	
//    	// 查看该机构学员地址信息配置功能
//    	search.setFunctionCode("STUDENT_ADDRESS_INFO");
//    	CompanyFunctionSet address = companyFunctionSetServiceImpl.findCompanyUseCourse(search);
//    	if (address != null && "1".equals(address.getStatus())) {
//    		model.addAttribute("address", 1);
//    	} else {
//    		model.addAttribute("address", 0);
//    	}
//    	
//    	CompanyFunctionSet userorg_roleopenflag = WebUtils.getFunctionSet("USERORG_ROLEOPENFLAG");
//    	model.addAttribute("userorg_roleopenflag", userorg_roleopenflag==null?0:userorg_roleopenflag.getStatus());
    	return "/query/query_user_area";
    }

    /**
     * 页面跳转
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value="/orgstatistics/index")
    public String orgindex(Model model, HttpServletRequest request) throws Exception {
        Users loginUser = WebUtils.getCurrentUser(request);
        if(loginUser==null || loginUser.getId()==null){
            throw new Exception("数据出现异常，请联系管理员！");
        }
        UsersAreaRelation uersAreaRelation=new UsersAreaRelation();
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("班主任")){
        	uersAreaRelation=usersServiceImpl.findUsersAreaRelationT(loginUser.getId(),WebUtils.getCurrentCompany().getEduAreaSchool());
        }else if(subject.hasRole("任课老师")){
        	uersAreaRelation=usersServiceImpl.findUsersAreaRelationR(loginUser.getId(),WebUtils.getCurrentCompany().getEduAreaSchool());
        }else{
        	uersAreaRelation = usersServiceImpl.findUsersAreaRelation(loginUser.getId());	
        }
        //获取账号对应用户信息
        if(uersAreaRelation==null){
            //throw new Exception("数据出现异常，请联系管理员！");
        	model.addAttribute("allStuNum",0);
            model.addAttribute("completeStuNum",0);
        	return "/query/orgindex";
        }
        //学员总数
        Long allStuNum = studentStatisticsServiceImpl.getAllStudentNum(uersAreaRelation);
        //完善属性学员总数
        Long completeStuNum = studentStatisticsServiceImpl.getAllStudentNumOfComplete(uersAreaRelation);

        //查询学校所在区域
        SysConfigDict areaDict = new SysConfigDict();
        areaDict.setDictCode("EDU_SCHOOL");
        areaDict.setItemCode(uersAreaRelation.getEduSchool());
        List<SysConfigDict> areas = sysConfigDictServiceImpl.queryConfigDictListByDictCode(areaDict);
        if(areas!=null && areas.get(0)!=null){
            model.addAttribute("schoolDict", areas.get(0).getItemValue());
        }
        model.addAttribute("allStuNum",allStuNum);
        model.addAttribute("completeStuNum",completeStuNum);
        return "/query/orgindex";
    }

    /**
     * 页面跳转
     * @param model
     * @return
     */
    @RequestMapping(value="/orgstatistics/studentList")
    public String studentListOrg(Model model, HttpServletRequest request) throws Exception {
        Users loginUser = WebUtils.getCurrentUser(request);
        if(loginUser==null || loginUser.getId()==null){
            throw new Exception("数据出现异常，请联系管理员！");
        }
        
        //获取账号对应用户信息
        UsersAreaRelation uersAreaRelation=new UsersAreaRelation();
        Subject subject = SecurityUtils.getSubject();
        if( subject.hasRole("班主任")){
        	uersAreaRelation=usersServiceImpl.findUsersAreaRelationT(loginUser.getId(),WebUtils.getCurrentCompany().getEduAreaSchool());
        	int userId=WebUtils.getCurrentUserId(request);
 			EduMasterClass etc=new EduMasterClass();
        	etc.setUserId(String.valueOf(userId));
 			List<EduMasterClass> list=studentServiceImpl.findClassByTeacherId(etc,WebUtils.getCurrentCompany().getEduAreaSchool());
 			if(null!=list && list.size()>0){
 				String eduStep=list.get(0).getEduStep();
 				String eduYear=list.get(0).getEduYear();
 				String eduClass=list.get(0).getEduClass();
				model.addAttribute("eduStep", eduStep);
				if("STEP_01".equals(eduStep)){
					model.addAttribute("eduStepName", "小学");
				}else if("STEP_02".equals(eduStep)){
					model.addAttribute("eduStepName", "初中中学");
				}else{
					model.addAttribute("eduStepName", "高中中学");
				}
				model.addAttribute("eduYear", eduYear);
				model.addAttribute("eduClass", eduClass);
				model.addAttribute("role", "2");//2表示教师
 			}
        }else if(subject.hasRole("任课老师")){
 			EduMasterClass ets =new EduMasterClass();
        	ets.setUserId(String.valueOf(WebUtils.getCurrentUserId(request)));
    		ets.setEduSchool(WebUtils.getCurrentCompany().getEduAreaSchool());
    		ets.setEduStep("1");
    		List<EduMasterClass> eduStepGLY=studentServiceImpl.findSubjectClassByTeacherId(ets);
    		ets.setEduStep("");
    		ets.setEduYear("1");
    		List<EduMasterClass> eduYearGLY=studentServiceImpl.findSubjectClassByTeacherId(ets);
    		ets.setEduYear("");
    		ets.setEduClass("1");
    		List<EduMasterClass> eduClassGLY=studentServiceImpl.findSubjectClassByTeacherId(ets);
    		model.addAttribute("eduStep", eduStepGLY);
    		model.addAttribute("eduYear", eduYearGLY);
    		model.addAttribute("eduClass", eduClassGLY);
        	uersAreaRelation=usersServiceImpl.findUsersAreaRelationR(loginUser.getId(),WebUtils.getCurrentCompany().getEduAreaSchool());
        	model.addAttribute("role", "3");//3表示任课老师
        }else{
        	model.addAttribute("role", "1");
        	uersAreaRelation = usersServiceImpl.findUsersAreaRelation(loginUser.getId());
        	if(("0".equals(WebUtils.getCurrentCompany().getIsArea())||"1".equals(WebUtils.getCurrentCompany().getIsArea()))&&subject.hasRole("学校负责人")){
	        	List<SysConfigDict> areas = sysConfigDictServiceImpl.queryAreaBySchool(uersAreaRelation.getEduSchool());
	            model.addAttribute("areas", areas);
	            SysConfigDict areaDict=new SysConfigDict();
	            areaDict.setDictCode("EDU_SCHOOL");
	            List<SysConfigDict> schools = sysConfigDictServiceImpl.queryConfigDictListByDictCode(areaDict);
	            if(null!=schools && schools.size()>0){
		           	 SysConfigDict schoolName =new SysConfigDict();
		           	 for(int i=0;i<schools.size();i++){
		           		 if(uersAreaRelation.getEduSchool().equals(schools.get(i).getItemCode())){
		           			 schoolName.setItemCode(uersAreaRelation.getEduSchool());
		           			 schoolName.setItemValue(schools.get(i).getItemValue());
		           			 break;
		           		 }
	           	 }
	           	 model.addAttribute("schoolName", schoolName);
	            }
	            List<Integer> years = new ArrayList<Integer>();
	            int curYear = DateUtil.getCurYear();
	            for(int year = 0;year<7;year++){
	                years.add(curYear-year);
	            }
	            model.addAttribute( "years", years);
	            areaDict.setDictCode("EDU_STEP");
	            List<SysConfigDict> steps = sysConfigDictServiceImpl.queryConfigDictListByDictCode(areaDict);
	            model.addAttribute("steps", steps);
            }
        }
        if(uersAreaRelation==null){
        	return "/query/query_student_org";
           // throw new Exception("数据出现异常，请联系管理员！");
        }
        // 查询课程的多课程单元和多班号功能
        CompanyFunctionSet search = new CompanyFunctionSet();
        search.setFunctionCode("COMPANY_FUNCTION_COURSE");
        search.setCompanyId(WebUtils.getCurrentCompanyId());
        //查询学校所在区域
        SysConfigDict areaDict = new SysConfigDict();
        areaDict.setDictCode("EDU_SCHOOL");
        areaDict.setItemCode(uersAreaRelation.getEduSchool());
        List<SysConfigDict> schools = sysConfigDictServiceImpl.queryConfigDictListByDictCode(areaDict);
        if(schools!=null && schools.size()>0 && schools.get(0)!=null){
            model.addAttribute("school", schools.get(0));
        }

        // 学员分组
        CompanyFunctionSet companyFunctionSet = new CompanyFunctionSet();
        companyFunctionSet.setCompanyId(WebUtils.getCurrentCompanyId());
        companyFunctionSet.setFunctionCode("STUDENT_GROUP");
        List<CompanyFunctionSet> companyFunctionSetList = companyFunctionSetServiceImpl.findCompanyFunctionSetByPage(companyFunctionSet);
        if (companyFunctionSetList != null && companyFunctionSetList.size() > 0) {
            model.addAttribute("sgOpen", companyFunctionSetList.get(0).getStatus());
        }

        // 查看该机构学员地址信息配置功能
        search.setFunctionCode("STUDENT_ADDRESS_INFO");
        CompanyFunctionSet address = companyFunctionSetServiceImpl.findCompanyUseCourse(search);
        if (address != null && "1".equals(address.getStatus())) {
            model.addAttribute("address", 1);
        } else {
            model.addAttribute("address", 0);
        }

        CompanyFunctionSet userorg_roleopenflag = WebUtils.getFunctionSet("USERORG_ROLEOPENFLAG");
        model.addAttribute("userorg_roleopenflag", userorg_roleopenflag==null?0:userorg_roleopenflag.getStatus());
        List<SysConfigDict> stepList = sysConfigDictServiceImpl.findByDicCode("EDU_STEP");
        model.addAttribute("stepList", stepList);
        model.addAttribute("isArea", WebUtils.getCurrentIsArea());

        //课程科目
        List<SysConfigItemRelation> subjectList = sysConfigItemRelationServiceImpl.findItemFrontByLevel(2,WebUtils.getCurrentCompanyId());
        model.addAttribute( "subjectItem", subjectList);

        if(("0".equals(WebUtils.getCurrentCompany().getIsArea())||"1".equals(WebUtils.getCurrentCompany().getIsArea()))&&subject.hasRole("学校负责人")){
        	return "/query/query_student_org";
        }else{
        	return "/query/query_student_orgteacher";
        }
    }

    /**
     * 异步获取数校学员学习详情记录
     */
    @ResponseBody
    @RequestMapping(value="/learningDetails/queryStudentsList")
    public SimplePage queryStudentsListData(HttpServletRequest request,StudentListVo search) throws Exception {
    	SimplePage pg = new SimplePage();
    	try{
            //获取当前学校管理员的学校组织机构代码
            Users loginUser = WebUtils.getCurrentUser(request);
            if(loginUser==null || loginUser.getId()==null){
                return SimplePage.getFailed("获取学校信息失败 ");
            }
            //判断用户身份
            Subject subject = SecurityUtils.getSubject();
           /* Integer companyId = companyService.findCompanyByCode(loginUser.getId());
            if(null == companySchoolVO){
            	return SimplePage.getFailed("获取用户所负责学校失败");
            }*/
            //获取当前用户管辖的分校id
            Integer companyId = companyService.findCompanyByCode(search.getEduSchool());
            if(null != companyId){
                search.setCompanyId(companyId);
            }
            if(!"".equals(search.getPage()) && null != search.getPage() && search.getPage()!=1 && search.getPage()!=0){
                search.setPage((search.getPage()-1)*10);
                search.setPageSize(10);
            }else if("0".equals(search.getPage())){
                search.setPage(0);
                search.setPageSize(10);
            }else {
                search.setPage(0);
                search.setPageSize(10);
            }

            
            //TODO 设定用户身份
            int isResponse = 1;
          //  待完成
            if(1 == isResponse){
            	//学校负责人
            	pg =  usersFrontService.getUserClassStudyAsSchoolResponse(search,loginUser);
            	System.out.println(JSONObject.toJSON(pg).toString());
            }else{
            	//班主任
            }

    	}catch(Exception e){
    		e.printStackTrace();
    		return SimplePage.getFailed("服务器错误");
    	}

        return pg;
    }


    /**
     * 页面跳转
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value="/orgstatistics/queryOrg")
    public String queryOrgOfOrg(Model model, HttpServletRequest request) throws Exception {
        Users loginUser = WebUtils.getCurrentUser(request);
        if(loginUser==null || loginUser.getId()==null){
            throw new Exception("数据出现异常，请联系管理员！");
        }
        //获取账号对应用户信息
        UsersAreaRelation uersAreaRelation=new UsersAreaRelation();
        Subject subject = SecurityUtils.getSubject();
        if( subject.hasRole("班主任")){
        	uersAreaRelation=usersServiceImpl.findUsersAreaRelationT(loginUser.getId(),WebUtils.getCurrentCompany().getEduAreaSchool());
        }else if(subject.hasRole("任课老师")){
        	uersAreaRelation=usersServiceImpl.findUsersAreaRelationR(loginUser.getId(),WebUtils.getCurrentCompany().getEduAreaSchool());
        }else{
        	uersAreaRelation = usersServiceImpl.findUsersAreaRelation(loginUser.getId());	
        }
        if(uersAreaRelation==null){
            //throw new Exception("数据出现异常，请联系管理员！");
            return "/query/query_org_org";
        }

        //查询学校所在区域
        //查询学校所在区域
        SysConfigDict areaDict = new SysConfigDict();
        areaDict.setDictCode("EDU_SCHOOL");
        areaDict.setItemCode(uersAreaRelation.getEduSchool());
        List<SysConfigDict> schools = sysConfigDictServiceImpl.queryConfigDictListByDictCode(areaDict);
        if(schools!=null && schools.size()>0 && schools.get(0)!=null){
            model.addAttribute("school", schools.get(0).getItemValue());
        }

        //查询学校所属学段
        SysConfigDict stepDict = new SysConfigDict();
        stepDict.setDictCode("EDU_STEP_NEW");
        List<SysConfigDict> stepNews = sysConfigDictServiceImpl.queryConfigDictListByDictCode(stepDict);
        model.addAttribute("stepNews", stepNews);
        model.addAttribute("isArea", WebUtils.getCurrentIsArea());
        return "/query/query_org_org";
    }

    /**
     *
     * @Description: 统计信息导出学员数据
     * @param model
     * @param search
     * @return
     */
    @RequestMapping(value = "/exportExcleArea")
    public ModelAndView exportExcleArea(Model model, StudentListVo search) {
        List<Map<String, Object>> al = new ArrayList<Map<String, Object>>();
        if (EntityUtil.isNotBlank(search)) {
            search.setCompanyId(WebUtils.getCurrentCompanyId());
            // search.setSchoolId(WebUtils.getCurrentSchoolId());
            search.setPageSize(20000);
            al = studentStatisticsServiceImpl.getAreaStudentCountList(search);
        }
        StringBuffer title = new StringBuffer(
                "学段:eduStep,学校:eduSchool,注册人数:registerNum,报名人数:paymaterCount");
        ViewFiles excel = new ViewFiles();
        HSSFWorkbook wb = new HSSFWorkbook();
        try {
            wb = ExcelUtil.newWorkbook(al, "sheet1", title.toString());
        } catch (Exception ex) {

        }
        Map map = new HashMap();
        map.put("workbook", wb);
        map.put("fileName", "学员列表.xls");
        return new ModelAndView(excel, map);
    }
    /**
     * @Description: 导出班级学生观课记录
     * @param model
     * @param search
     * @return
     * 2018-3-11
     */
    @RequestMapping(value = "/exportExcleCourse")
    public ModelAndView exportExcleCourse(Model model, StudentListVo search,HttpServletRequest request) throws Exception{
        List<StudentListVo> al = new ArrayList<StudentListVo>();
        JSONObject jsonObject = new JSONObject();
        //获取当前学校管理员的学校组织机构代码
        Users loginUser = WebUtils.getCurrentUser(request);
        if(loginUser==null || loginUser.getId()==null){
            throw new Exception("数据出现异常，请联系管理员！");
        }
        //判断是否是班主任
        Subject subject = SecurityUtils.getSubject();
        Integer comId = null;
        CompanySchoolVO companySchoolVO = companyService.findCompanyByCode(loginUser.getId());
        if(null == companySchoolVO){
            System.out.println("==========> 查询失败");
            return null;
        }
        search.setUserId(loginUser.getId());
        search.setCompanyId(companySchoolVO.getCompanyId());
        search.setEduSchool(companySchoolVO.getItem_code());
        //计算当前年级
        ClassType classType = CommonUtils.getClassTypeByStep(search.getEduStep(), search.getEduYear());
        if(null == classType){
            //TODO 参数错误
        }
        classType.setEduStep(search.getEduStep());
        classType.setEduYear(search.getEduYear());
        if(null != search.getEduClass() && !"".equals(search.getEduClass())){
            classType.setEduClass(search.getEduClass());
        }
        classType.setSubject(search.getSubject());
        classType.setLiveFlag(Integer.parseInt(search.getLiveFlag()));
        classType.setCompanyId(search.getCompanyId());
        search.setPageSize(20000);

        jsonObject = classTypeServiceImpl.getListDatas(search,classType);
        JSONObject study = jsonObject.getJSONObject("pageFinder");
        List<ClassLectureVO> classList1 =null;
        try {
            JSONArray jsonArray =jsonObject.getJSONArray("classList");
            if(jsonArray!=null) {
                classList1 = JSONArray.parseArray(jsonArray.toJSONString(), ClassLectureVO.class);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        JSONArray data = study.getJSONArray("data");
        JSONObject obj=null;

        for(int i=0;i<data.size();i++){
            StudentListVo slv = new StudentListVo();
           obj = data.getJSONObject(i);
            JSONObject info =  obj.getJSONObject("info");
            slv.setName(info.getString("name"));
            slv.setEduYear(info.getString("eduYear"));
            slv.setEduClass(info.getString("eduClass"));
            slv.setCounty(info.getString("countClass"));
            slv.setIsAgent(info.getString("studyTime"));

            JSONArray list=  obj.getJSONArray("list");
            List<String> list1 =new ArrayList<>();
            for(int j =0; j<list.size();j++){
                list1.add(list.getInteger(j)==0?"X":"√");
            }
            slv.setCourse(list1);

            al.add(slv);
        }
        String step =search.getEduStep();
        if("STEP_01".equals(step)){
            step="小学";
        }else if("STEP_02".equals(step)){
            step="初中中学";
        }else{
            step="高中中学";
        }

        List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
        for (StudentListVo s : al) {
            Map<String, Object> map = new HashMap<String, Object>();
            String name = s.getName();
            map.put("name", name);
            String eduClass = "";
            String eduYear ="";
            String studyTime=s.getIsAgent();
            String countClass = s.getCounty();

            if(!StringUtils.isBlank(s.getEduYear())){
                eduYear = s.getEduYear();
            }
            if(!StringUtils.isBlank(s.getEduClass())){
                eduClass = step+s.getEduYear()+"年"+s.getEduClass()+"班";
            }
            map.put("eduYear", eduYear);
            map.put("eduClass", eduClass);
            map.put("studyTime",studyTime);
            map.put("countClass",countClass);
            List<String> couseAll=s.getCourse();
            for(int i=0;i<couseAll.size();i++){
                map.put("lession"+i,couseAll.get(i));
            }
            lists.add(map);
        }

        StringBuffer title = new StringBuffer(
                "姓名:name,班  级:eduClass,观课总节数:studyTime,观课总时长（分钟）:countClass,");

        StringBuilder  waj =new StringBuilder();
        for (int i = 0 ; i < classList1.size(); i++)
        {
            if(i==classList1.size()-1){
                waj.append(classList1.get(i).getLesson_name().replaceAll(",",""));
                waj.append(":lession"+i);
                break;
            }
                waj.append(classList1.get(i).getLesson_name().replaceAll(",",""));
                waj.append(":lession"+i+",");
        }
        title.append(waj);

        ViewFiles excel = new ViewFiles();
        HSSFWorkbook wb = new HSSFWorkbook();
        try {
            wb = ExcelUtil.newWorkbook(lists, "sheet1", title.toString());
        } catch (Exception ex) {

        }
        Map map = new HashMap();
        map.put("workbook", wb);
        map.put("fileName", "学生观课记录.xls");
        return new ModelAndView(excel, map);
    }

    /**
     *
     * @Description: 统计信息导出学员数据
     * @param model
     * @param search
     * @return
     */
    @RequestMapping(value = "/exportExcleSchool")
    public ModelAndView exportExcleSchool(Model model, StudentListVo search,HttpServletRequest request) {
        List<StudentListVo> al = new ArrayList<StudentListVo>();
        if (EntityUtil.isNotBlank(search)) {
            search.setCompanyId(WebUtils.getCurrentCompanyId());
            // search.setSchoolId(WebUtils.getCurrentSchoolId());
            search.setPageSize(20000);
            Users loginUser = WebUtils.getCurrentUser();
            UsersAreaRelation uersAreaRelation = null;
            Subject subject = SecurityUtils.getSubject();
            if(subject.hasRole("区县负责人")){
            	uersAreaRelation = usersServiceImpl.findUsersAreaRelation(loginUser.getId());
            	if(uersAreaRelation!=null){
            		search.setEduArea(uersAreaRelation.getEduArea());
            	}
            }else if(subject.hasRole("学校负责人")) {
            	uersAreaRelation = usersServiceImpl.findUsersAreaRelation(loginUser.getId());
            	if(uersAreaRelation!=null){
            		search.setEduArea(uersAreaRelation.getEduArea());
            		search.setEduSchool(uersAreaRelation.getEduSchool());
            	}
            }else if(subject.hasRole("班主任")||subject.hasRole("任课老师")){
            	uersAreaRelation=usersServiceImpl.findUsersAreaRelationT(loginUser.getId(),WebUtils.getCurrentCompany().getEduAreaSchool());
            	if(uersAreaRelation!=null){
            		search.setEduArea(uersAreaRelation.getEduArea());
            		search.setEduSchool(uersAreaRelation.getEduSchool());
            	}
    	        List<EduMasterClass> list=null;
    	        if(subject.hasRole("班主任")){
    	        	EduMasterClass etc=new EduMasterClass();
    	        	etc.setUserId(String.valueOf(loginUser.getId()));
    	        	list=studentServiceImpl.findClassByTeacherId(etc,WebUtils.getCurrentCompany().getEduAreaSchool());
    	        }else if(subject.hasRole("任课老师")){
    	        	EduMasterClass ets =new EduMasterClass();
    	            ets.setUserId(String.valueOf(WebUtils.getCurrentUserId(request)));
    	        	ets.setEduSchool(WebUtils.getCurrentCompany().getEduAreaSchool());
    	        	ets.setEduYear("");
    	        	ets.setEduClass("1");
    	        	list=studentServiceImpl.findSubjectClassByTeacherId(ets);
    	        }
    	        search.setEduMasterClass(list);
            }
            al = studentServiceImpl.findStudentsData(search);
        }
        List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
        for (StudentListVo s : al) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("username", s.getUsername());
            map.put("name", s.getName());
            String eduYear = "";
            String eduClass = "";
            if(!StringUtils.isBlank(s.getEduYear())){
                eduYear = s.getEduYear();
            }
            if(!StringUtils.isBlank(s.getEduClass())){
                eduClass = s.getEduClass()+"班";
            }
            map.put("eduYear", eduYear);
            map.put("eduClass", eduClass);
            lists.add(map);
        }
        StringBuffer title = new StringBuffer(
                "用户名:username,学生姓名:name,入学年份:eduYear,班级:eduClass");
        ViewFiles excel = new ViewFiles();
        HSSFWorkbook wb = new HSSFWorkbook();
        try {
            wb = ExcelUtil.newWorkbook(lists, "sheet1", title.toString());
        } catch (Exception ex) {

        }
        Map map = new HashMap();
        map.put("workbook", wb);
        map.put("fileName", "学员列表.xls");
        return new ModelAndView(excel, map);
    }
//获取直播观看统计信息
    @RequestMapping(value="/statistics/watchInfoList")
    public String watchInfoList(Model model, HttpServletRequest request) throws Exception {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String endDate = sdf.format(c.getTime());
        c.add(Calendar.MONTH,-1);
        String startDate = sdf.format(c.getTime());
        model.addAttribute("endDate",endDate);
        model.addAttribute("startDate",startDate);
        //权限判断
        //Users user = WebUtils.getCurrentUser();
        //UsersAreaRelation uersAreaRelation = usersServiceImpl.findUsersAreaRelation(user.getId());
        Subject subject = SecurityUtils.getSubject();
        model.addAttribute("isArea", WebUtils.getCurrentIsArea());
        if(subject.hasRole("学校负责人")) {
            return "/query/query_student_school_watchInfo";
        }else if(subject.hasRole("教科院") || subject.hasRole("文轩教育")){
            model.addAttribute("role","all");
        }else if(subject.hasRole("区县负责人")){
            model.addAttribute("isArea",true);
            SysConfigDict  search = new SysConfigDict();
            search.setDictCode("EDU_STEP_NEW");
            model.addAttribute("eduStep",sysConfigDictServiceImpl.findByDicCode("EDU_STEP_NEW"));
            model.addAttribute("role","area");
        }else if(subject.hasRole("班主任")){
        	return "/query/query_student_school_watchInfo";
        }else if(subject.hasRole("任课老师")){
        	return "/query/query_student_school_watchInfo";
        }
        return "/query/query_student_watchInfo";
    }
    //直播观看人次
    @RequestMapping(value="/statistics/watchInfoAll")
    @ResponseBody
    public List<Map> watchInfoAll(String startDate,String endDate,HttpServletRequest request,String eduStep){
        Map<String ,Object> map = new HashMap<>();
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("eduStep",eduStep);
        map.put("companyId",WebUtils.getCurrentCompanyId());
        Users user = WebUtils.getCurrentUser();
        UsersAreaRelation uersAreaRelation = usersServiceImpl.findUsersAreaRelation(user.getId());

        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("学校负责人")) {
            map.put("schoolId",uersAreaRelation.getEduSchool());
            map.put("groupBy","edu_year");
        }else if(subject.hasRole("教科院") || subject.hasRole("文轩教育")){
            // map.put("areaId",uersAreaRelation.getE);
            map.put("role","all");
            map.put("groupBy","user_id");
        }else if(subject.hasRole("区县负责人")){
            map.put("areaId",uersAreaRelation.getEduArea());
            map.put("groupBy","edu_school");
            map.put("role","area");
        }


        List<Map>  result  =  studentStatisticsServiceImpl.watchAllChartData(map);


        return result;
    }


    //直播观看人数
    @RequestMapping(value="/statistics/watchInfoIndex")
    @ResponseBody
    public List<Map> watchInfoIndex(String startDate,String endDate,HttpServletRequest request,String eduStep){
        Map<String ,Object> map = new HashMap<>();
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("eduStep",eduStep);
        map.put("companyId", WebUtils.getCurrentCompanyId());
        Users user = WebUtils.getCurrentUser();
        UsersAreaRelation uersAreaRelation = usersServiceImpl.findUsersAreaRelation(user.getId());

        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("学校负责人")) {
            map.put("schoolId",uersAreaRelation.getEduSchool());
            map.put("groupBy","edu_year");
        }else if(subject.hasRole("教科院") || subject.hasRole("文轩教育")){
            // map.put("areaId",uersAreaRelation.getE);
            map.put("role","all");
            map.put("groupBy","user_id");
        }else if(subject.hasRole("区县负责人")){
            map.put("areaId",uersAreaRelation.getEduArea());
            map.put("groupBy","edu_school");
            map.put("role","area");
        }


        List<Map>  result  =  studentStatisticsServiceImpl.watchIndexChartData(map);


        return result;
    }





    @RequestMapping(value="/statistics/watchInfoTotal")
    @ResponseBody
    public Map<String,Object> watchInfoTotal(String startDate,String endDate,Model model, HttpServletRequest request,String eduStep) throws Exception{
        Users user = WebUtils.getCurrentUser();
        UsersAreaRelation uersAreaRelation = usersServiceImpl.findUsersAreaRelation(user.getId());
        Map<String,Object> map = new HashMap<>();
        map.put("eduStep",eduStep);
        if(uersAreaRelation==null){
            map.put("groupBy","edu_area");
        }else{

            Subject subject = SecurityUtils.getSubject();
            if(subject.hasRole("学校负责人")) {
                map.put("eduSchool",uersAreaRelation.getEduSchool());
                map.put("groupBy","edu_year");
            }else if(subject.hasRole("教科院") || subject.hasRole("文轩教育")){
                // map.put("areaId",uersAreaRelation.getE);
                map.put("groupBy","edu_area");
            }else if(subject.hasRole("区县负责人")){
                map.put("eduArea",uersAreaRelation.getEduArea());
                map.put("groupBy","edu_school");
            }
        }
        map.put("endDate",endDate);
        map.put("startDate",startDate);
        map.put("companyId", WebUtils.getCurrentCompanyId());
        List<Map> index = studentStatisticsServiceImpl.getWatchInfoIndex(map);
        List<Map> all =   studentStatisticsServiceImpl.getWatchInfoAll(map);
        Map<String,Object> result = new HashMap<>();
        if(index.size()>0&&index.get(0)!=null){
            result.put("index",index.get(0).get("times"));
        }else{
            result.put("index",0);
        }
        if(all.size()>0&&all.get(0)!=null){
            result.put("all",all.get(0).get("times"));
        }else{
            result.put("all",0);
        }
        return result;
    }


    @RequestMapping(value="/statistics/studentWatchInfoList")
    public String studentWatchInfoList(Model model, HttpServletRequest request) throws Exception {
        // 查询课程的多课程单元和多班号功能
        CompanyFunctionSet search = new CompanyFunctionSet();
        search.setFunctionCode("COMPANY_FUNCTION_COURSE");
        search.setCompanyId(WebUtils.getCurrentCompanyId());
        //查询学校所在区域
        SysConfigDict areaDict = new SysConfigDict();
        areaDict.setDictCode("EDU_SCHOOL_AREA");

        List<SysConfigDict> areas = sysConfigDictServiceImpl.queryConfigDictListByDictCode(areaDict);
        model.addAttribute("areas", areas);
        //查询学段
        SysConfigDict stepDict = new SysConfigDict();
        stepDict.setDictCode("EDU_STEP");

        List<SysConfigDict> steps = sysConfigDictServiceImpl.queryConfigDictListByDictCode(stepDict);
        model.addAttribute("steps", steps);
        // 学员分组
        CompanyFunctionSet companyFunctionSet = new CompanyFunctionSet();
        companyFunctionSet.setCompanyId(WebUtils.getCurrentCompanyId());
        companyFunctionSet.setFunctionCode("STUDENT_GROUP");
        List<CompanyFunctionSet> companyFunctionSetList = companyFunctionSetServiceImpl.findCompanyFunctionSetByPage(companyFunctionSet);
        if (companyFunctionSetList != null && companyFunctionSetList.size() > 0) {
            model.addAttribute("sgOpen", companyFunctionSetList.get(0).getStatus());
        }

        // 查看该机构学员地址信息配置功能
        search.setFunctionCode("STUDENT_ADDRESS_INFO");
        CompanyFunctionSet address = companyFunctionSetServiceImpl.findCompanyUseCourse(search);
        if (address != null && "1".equals(address.getStatus())) {
            model.addAttribute("address", 1);
        } else {
            model.addAttribute("address", 0);
        }

        CompanyFunctionSet userorg_roleopenflag = WebUtils.getFunctionSet("USERORG_ROLEOPENFLAG");
        model.addAttribute("userorg_roleopenflag", userorg_roleopenflag==null?0:userorg_roleopenflag.getStatus());
        SysConfigItem item = new SysConfigItem();
        item.setParentCode("SUBJECT");
        item.setCompanyId(WebUtils.getCurrentCompanyId());
        item.setSchoolId(WebUtils.getCurrentSchoolId());
        List<SysConfigItem> items = sysConfigItemService.findByParentCode(item);
        model.addAttribute("subject", items);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        model.addAttribute("endTime",sdf.format(c.getTime()));
        c.add(Calendar.MONTH,-1);
        model.addAttribute("startTime",sdf.format(c.getTime()));


        UsersAreaRelation uersAreaRelation=new UsersAreaRelation();
        Subject subject = SecurityUtils.getSubject();
        Users loginUser = WebUtils.getCurrentUser(request);
        if( subject.hasRole("班主任")){
        	uersAreaRelation=usersServiceImpl.findUsersAreaRelationT(loginUser.getId(),WebUtils.getCurrentCompany().getEduAreaSchool());
        	int userId=WebUtils.getCurrentUserId(request);
 			EduMasterClass etc=new EduMasterClass();
        	etc.setUserId(String.valueOf(userId));
 			List<EduMasterClass> list=studentServiceImpl.findClassByTeacherId(etc,WebUtils.getCurrentCompany().getEduAreaSchool());
 			if(null!=list && list.size()>0){
 				String eduStep=list.get(0).getEduStep();
 				String eduYear=list.get(0).getEduYear();
 				String eduClass=list.get(0).getEduClass();
				model.addAttribute("eduStep", eduStep);
				if("STEP_01".equals(eduStep)){
					model.addAttribute("eduStepName", "小学");
				}else if("STEP_02".equals(eduStep)){
					model.addAttribute("eduStepName", "初中中学");
				}else{
					model.addAttribute("eduStepName", "高中中学");
				}
				model.addAttribute("eduYear", eduYear);
				model.addAttribute("eduClass", eduClass);
				model.addAttribute("roles", "2");//2表示教师
 			}
        }else if(subject.hasRole("任课老师")){
        	EduMasterClass ets =new EduMasterClass();
        	ets.setUserId(String.valueOf(WebUtils.getCurrentUserId(request)));
    		ets.setEduSchool(WebUtils.getCurrentCompany().getEduAreaSchool());
    		ets.setEduStep("1");
    		List<EduMasterClass> eduStepGLY=studentServiceImpl.findSubjectClassByTeacherId(ets);
    		ets.setEduStep("");
    		ets.setEduYear("1");
    		List<EduMasterClass> eduYearGLY=studentServiceImpl.findSubjectClassByTeacherId(ets);
    		ets.setEduYear("");
    		ets.setEduClass("1");
    		List<EduMasterClass> eduClassGLY=studentServiceImpl.findSubjectClassByTeacherId(ets);
    		model.addAttribute("eduStep", eduStepGLY);
    		model.addAttribute("eduYear", eduYearGLY);
    		model.addAttribute("eduClass", eduClassGLY);
        	uersAreaRelation=usersServiceImpl.findUsersAreaRelationR(loginUser.getId(),WebUtils.getCurrentCompany().getEduAreaSchool());
        	model.addAttribute("roles", "3");//2表示教师
        }else{
        	uersAreaRelation = usersServiceImpl.findUsersAreaRelation(loginUser.getId());	
        }
        if(uersAreaRelation==null){
            model.addAttribute("role","all");
        }else{

            if(subject.hasRole("学校负责人")) {
                model.addAttribute("role","school");
                model.addAttribute("area",uersAreaRelation.getEduArea());
                model.addAttribute("eduSchool",uersAreaRelation.getEduSchool());
            }else if(subject.hasRole("教科院") || subject.hasRole("文轩教育")){
                // map.put("areaId",uersAreaRelation.getE);
//                map.put("groupBy","edu_area");
                model.addAttribute("role","all");
            }else if(subject.hasRole("区县负责人")){
//                map.put("areaId",uersAreaRelation.getEduArea());
//                map.put("groupBy","edu_school");
                model.addAttribute("role","area");
                model.addAttribute("area",uersAreaRelation.getEduArea());
                SysConfigDict dic  = new SysConfigDict();
                dic.setItemCode(uersAreaRelation.getEduArea());
                model.addAttribute("areaId", sysConfigDictServiceImpl.findSysConfigDictByCode(dic).getId());
                model.addAttribute("schoolType", sysConfigDictServiceImpl.findByDicCode("EDU_STEP_NEW"));
            }else if(subject.hasRole("班主任")){
            	 model.addAttribute("role","school");
            }else if(subject.hasRole("任课老师")){
            	 model.addAttribute("role","school");
            }
        }

        return "/query/query_student_watchList";
    }




    //查询直播统计
    @ResponseBody
    @RequestMapping(value = "/statistics/queryStudentsWatchInfoList")
    public Map queryStudentsWatchInfoList(WatchInfoResult search,HttpServletRequest request) {
        String flag = "";
        //search.setCompanyId(WebUtils.getCurrentCompanyId());
        //根据不同权限查询
        UsersAreaRelation uersAreaRelation=null;
        Subject subject = SecurityUtils.getSubject();
        Users loginUser = WebUtils.getCurrentUser(request);
        if(subject.hasRole("区县负责人")){
        	uersAreaRelation = usersServiceImpl.findUsersAreaRelation(loginUser.getId());
        	if(uersAreaRelation!=null){
        		search.setEduArea(uersAreaRelation.getEduArea());
        	}
        }else if(subject.hasRole("学校负责人")) {
        	uersAreaRelation = usersServiceImpl.findUsersAreaRelation(loginUser.getId());
        	if(uersAreaRelation!=null){
        		search.setEduArea(uersAreaRelation.getEduArea());
        		search.setEduSchool(uersAreaRelation.getEduSchool());
        	}
        }else if(subject.hasRole("班主任")||subject.hasRole("任课老师")){
        	uersAreaRelation=usersServiceImpl.findUsersAreaRelationT(loginUser.getId(),WebUtils.getCurrentCompany().getEduAreaSchool());
        	if(uersAreaRelation!=null){
        		search.setEduArea(uersAreaRelation.getEduArea());
        		search.setEduSchool(uersAreaRelation.getEduSchool());
        	}
	        List<EduMasterClass> list=null;
	        if(subject.hasRole("班主任")){
	        	EduMasterClass etc=new EduMasterClass();
	        	etc.setUserId(String.valueOf(loginUser.getId()));
	        	list=studentServiceImpl.findClassByTeacherId(etc,WebUtils.getCurrentCompany().getEduAreaSchool());
	        }else if(subject.hasRole("任课老师")){
	        	EduMasterClass ets =new EduMasterClass();
	            ets.setUserId(String.valueOf(WebUtils.getCurrentUserId(request)));
	        	ets.setEduSchool(WebUtils.getCurrentCompany().getEduAreaSchool());
	        	ets.setEduYear("");
	        	ets.setEduClass("1");
	        	list=studentServiceImpl.findSubjectClassByTeacherId(ets);
	        }
	        search.setEduMasterClass(list);
        }
        // 分页调整
        search.setPageSize(10);
        search.setCompanyId(WebUtils.getCurrentCompanyId());
        Integer total = studentStatisticsServiceImpl.totalPayMasterCount(search);
        PageFinder2<WatchInfoResult> pageFinder = studentStatisticsServiceImpl.queryStudentsWatchInfoList(search);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("total",total);
        map.put("pageFinder",pageFinder);
        return map;
    }
    //查询直播统计
    @ResponseBody
    @RequestMapping(value = "/getStudentWatchInfo")
    public List<Map> getStudentWatchInfo(String lessonId,String userId) {
        Map<String,Object> map = new HashMap<>();
        map.put("lessonId",lessonId);
        map.put("userId",userId);
        List<Map> result = studentStatisticsServiceImpl.getStudentWatchInfo(map);
        return result;
    }

    /**
     * 用户点播统计
     * @param model
     * @return
     */
    @RequestMapping(value = "/exportUserWatchExcle")
    public ModelAndView exportUserWatchExcle(Model model, WatchInfoResult search,HttpServletRequest request) {
        List<WatchInfoResult> list = new ArrayList<>();
        if (EntityUtil.isNotBlank(search)) {
        	search.setCompanyId(WebUtils.getCurrentCompanyId());
            search.setPageSize(20000);
            Users loginUser = WebUtils.getCurrentUser();
            UsersAreaRelation uersAreaRelation = null;
            Subject subject = SecurityUtils.getSubject();
            if(subject.hasRole("区县负责人")){
            	uersAreaRelation = usersServiceImpl.findUsersAreaRelation(loginUser.getId());
            	if(uersAreaRelation!=null){
            		search.setEduArea(uersAreaRelation.getEduArea());
            	}
            }else if(subject.hasRole("学校负责人")) {
            	uersAreaRelation = usersServiceImpl.findUsersAreaRelation(loginUser.getId());
            	if(uersAreaRelation!=null){
            		search.setEduArea(uersAreaRelation.getEduArea());
            		search.setEduSchool(uersAreaRelation.getEduSchool());
            	}
            }else if(subject.hasRole("班主任")||subject.hasRole("任课老师")){
            	uersAreaRelation=usersServiceImpl.findUsersAreaRelationT(loginUser.getId(),WebUtils.getCurrentCompany().getEduAreaSchool());
            	if(uersAreaRelation!=null){
            		search.setEduArea(uersAreaRelation.getEduArea());
            		search.setEduSchool(uersAreaRelation.getEduSchool());
            	}
    	        List<EduMasterClass> eduMasterClassList=null;
    	        if(subject.hasRole("班主任")){
    	        	EduMasterClass etc=new EduMasterClass();
    	        	etc.setUserId(String.valueOf(loginUser.getId()));
    	        	eduMasterClassList=studentServiceImpl.findClassByTeacherId(etc,WebUtils.getCurrentCompany().getEduAreaSchool());
    	        }else if(subject.hasRole("任课老师")){
    	        	EduMasterClass ets =new EduMasterClass();
    	            ets.setUserId(String.valueOf(WebUtils.getCurrentUserId(request)));
    	        	ets.setEduSchool(WebUtils.getCurrentCompany().getEduAreaSchool());
    	        	ets.setEduYear("");
    	        	ets.setEduClass("1");
    	        	eduMasterClassList=studentServiceImpl.findSubjectClassByTeacherId(ets);
    	        }
    	        search.setEduMasterClass(eduMasterClassList);
            }
            list = studentStatisticsServiceImpl.exportStudentsWatchInfoList(search);//studentStatisticsServiceImpl.queryStudentsWatchInfoList(search);
        }
        List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
        Subject subject = SecurityUtils.getSubject();
        for (WatchInfoResult v : list) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("className", v.getClassName());
            map.put("lessonName", v.getLessonName());
            map.put("userName", v.getUserName());
            map.put("studentName", v.getStudentName());
            if(subject.hasRole("学校负责人")) {
                map.put("stepName", v.getEduStep());//学段
                map.put("eduClass", v.getStudyClass());//班级sss
            }else if(subject.hasRole("教科院") || subject.hasRole("文轩教育")){
                map.put("areaName", v.getEduArea());//区域
                map.put("schoolName", v.getEduSchool());//学校
                map.put("schoolType", v.getSchoolType());
                map.put("stepName", v.getEduStep());//学段
                map.put("eduYear", v.getEduYear());//入学年份
                map.put("eduClass", v.getEduClass());//班级
            }else if(subject.hasRole("区县负责人")){
                map.put("schoolName", v.getEduSchool());//学校
                map.put("schoolType", v.getSchoolType());
                map.put("stepName", v.getEduStep());//学段
                map.put("eduYear", v.getEduYear());//入学年份
                map.put("eduClass", v.getEduClass());//班级
            }
            map.put("times", v.getTimes());//观看次数
            map.put("studyTime", v.getStudyTime());//观看时长
            lists.add(map);
        }
        String titles = "";
        if(subject.hasRole("学校负责人")) {
            titles = "课程名称:className,课次名称:lessonName,用户名:userName,学员名称:studentName,学段:stepName,班级:eduClass,观看累计次数:times,观看累计时长:studyTime";
        }else if(subject.hasRole("教科院") || subject.hasRole("文轩教育")){
            titles = "课程名称:className,课次名称:lessonName,用户名:userName,学员名称:studentName,区域:areaName,学校:schoolName,学校性质:schoolType,学段:stepName,入学年份:eduYear,班级:eduClass,观看累计次数:times,观看累计时长:studyTime";
        }else if(subject.hasRole("区县负责人")){
            titles = "课程名称:className,课次名称:lessonName,用户名:userName,学员名称:studentName,学校:schoolName,学校性质:schoolType,学段:stepName,入学年份:eduYear,班级:eduClass,观看累计次数:times,观看累计时长:studyTime";
        }

        StringBuffer title = new StringBuffer(
                titles);
        ViewFiles excel = new ViewFiles();
        HSSFWorkbook wb = new HSSFWorkbook();
        try {
            wb = ExcelUtil.newWorkbook(lists, "sheet1", title.toString());
        } catch (Exception ex) {

        }
        Map map = new HashMap();
        map.put("workbook", wb);
        map.put("fileName", "用户直播统计.xls");
        return new ModelAndView(excel, map);
    }


    //直播观看人数
    @RequestMapping(value="/statistics/watchSchoolInfoIndex")
    @ResponseBody
    public Map watchSchoolInfoIndex(String startDate,String endDate,HttpServletRequest request){
            Map<String ,Object> map = new HashMap<>();
            map.put("startDate",startDate);
            map.put("endDate",endDate);
            Users user = WebUtils.getCurrentUser();
            UsersAreaRelation uersAreaRelation = null;

            Subject subject = SecurityUtils.getSubject();
            if(subject.hasRole("学校负责人")) {
            	uersAreaRelation = usersServiceImpl.findUsersAreaRelation(user.getId());
            }else if( subject.hasRole("班主任")){
            	uersAreaRelation=usersServiceImpl.findUsersAreaRelationT(user.getId(),WebUtils.getCurrentCompany().getEduAreaSchool());
            	//获取班主任任教班级
            	EduMasterClass etc=new EduMasterClass();
            	etc.setUserId(String.valueOf(user.getId()));
     			List<EduMasterClass> list=studentServiceImpl.findClassByTeacherId(etc,WebUtils.getCurrentCompany().getEduAreaSchool());
     			map.put("eduMasterClass",list);
            }else if(subject.hasRole("任课老师")){
            	uersAreaRelation=usersServiceImpl.findUsersAreaRelationR(user.getId(),WebUtils.getCurrentCompany().getEduAreaSchool());
            	//获取任课教师任教班级
            	EduMasterClass ets =new EduMasterClass();
            	ets.setUserId(String.valueOf(WebUtils.getCurrentUserId(request)));
        		ets.setEduSchool(WebUtils.getCurrentCompany().getEduAreaSchool());
        		ets.setEduClass("1");
        		List<EduMasterClass> eduClassGLY=studentServiceImpl.findSubjectClassByTeacherId(ets);
        		map.put("eduMasterClass",eduClassGLY);
            }else{
                return null;
            }
            map.put("groupBy","edu_year");
            map.put("schoolId",uersAreaRelation.getEduSchool());
            map.put("companyId", WebUtils.getCurrentCompanyId());
            //获取总的年级数
            List<Map> year = studentStatisticsServiceImpl.getEduYearBySchool(map);
            //获取总观看人数
            Integer  watchNum =studentStatisticsServiceImpl.getWatchNumBySchool(map);
            //获取总观看时长
            String  totalTime =studentStatisticsServiceImpl.getWatchTimeLengthBySchool(map);
            //获取总观看人次
            Integer watchAll =studentStatisticsServiceImpl.getWatchTotalBySchool(map);


            //按年级分观看人数
            List<Map>  result  =  studentStatisticsServiceImpl.watchSchoolChartData(map);
           //按年级分报名人数
            List<Map> total    =  studentStatisticsServiceImpl.getAllBuyNum(map);



            if(result.size()>0 &&result.size()<year.size() ){
                List<Map> newResult = new ArrayList<>();
                for(int n = 0 ; n <year.size() ; n++){

                    for(int m = 0 ; m <result.size() ; m++){
                        Map a  = result.get(m);
                        boolean flag = false;
                        if(a.get("edu_year").equals(year.get(n).get("edu_year"))){
                            flag = true;
                            newResult.add(a);
                            break;
                        }
                        if(m==result.size()-1 && !flag){
                            Map b = new HashMap();
                            b.put("edu_year",year.get(n).get("edu_year"));
                            b.put("times",0);
                            newResult.add(b);

                        }
                    }

                }
                result = newResult;

            }else if(result.size() == 0){
                for(int n = 0 ; n <year.size() ; n++){
                        Map a  = new HashMap();
                        String eduYear = (String)year.get(n).get("edu_year");
                        a.put("edu_year",eduYear);
                        a.put("times",0);
                        result.add(a);
                }
            }

        if(total.size()>0 &&total.size()<year.size() ){
            List<Map> newTotal = new ArrayList<>();

                for(int n = 0 ; n <year.size() ; n++){
                    for(int m = 0 ; m <total.size() ; m++){
                        Map a  = total.get(m);
                        boolean flag = false;
                    if(a.get("edu_year").equals(year.get(n).get("edu_year"))){
                        flag = true;
                        newTotal.add(a);
                        break;
                    }
                    if(m==total.size()-1 && !flag){
                        Map b = new HashMap();
                        b.put("edu_year",year.get(n).get("edu_year"));
                        b.put("times",0);
                        newTotal.add(b);

                    }

                }
            }
            total = newTotal;
        }else if(total.size()==0){
            for(int n = 0 ; n <year.size() ; n++){
                Map a  = new HashMap();
                a.put("edu_year",year.get(n).get("edu_year"));
                a.put("times",0);
                total.add(a);
            }
        }


            map = new HashMap<>();
            map.put("year",year);
            map.put("watchNum",watchNum);
            map.put("totalTime",totalTime);
            map.put("watchAll",watchAll);
            map.put("result",result);
            map.put("total",total);
            return map;
    }
    @RequestMapping(value="/statistics/watchSchoolInfoTotal")
    @ResponseBody
    public Map watchSchoolTotal(String startDate,String endDate,HttpServletRequest request){
        Map<String ,Object>  map  = new HashMap<>();
        Users user = WebUtils.getCurrentUser();
        UsersAreaRelation uersAreaRelation = usersServiceImpl.findUsersAreaRelation(user.getId());

        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("学校负责人")) {
            map.put("schoolId",uersAreaRelation.getEduSchool());
            map.put("groupBy","edu_year");
        }
        //获取总的年级数
        List<Map> year = studentStatisticsServiceImpl.getEduYearBySchool(map);
        //获取总观看人数
        Integer  watchNum =studentStatisticsServiceImpl.getWatchNumBySchool(map);
        //获取总观看时长
        String  totalTime =studentStatisticsServiceImpl.getWatchTimeLengthBySchool(map);
        //获取总观看人次
        Integer watchAll =studentStatisticsServiceImpl.getWatchTotalBySchool(map);
        return null;
    }
        /**
         * 并发统计页面跳转
         * @param model
         * @param request
         * @return
         */
        @RequestMapping(value="/statistics/watchInfoCurrentCount")
        public String watchInfoCurrentCount(Model model, HttpServletRequest request){
            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String endTime = sdf.format(c.getTime());
            model.addAttribute("endTime",endTime);
            c.add(Calendar.MONTH,-1);
            String startTime  = sdf.format(c.getTime());

            model.addAttribute("startTime",startTime);


            List<SysConfigItemRelation> seconds = sysConfigItemRelationServiceImpl.findItemFrontByLevel(1,WebUtils.getCurrentCompanyId());
            model.addAttribute("secondItem",seconds);
            //sysConfigItemRelationServiceImpl.findItem

            return "/query/query_student_watchCurrentCount";
        }
    /**
     * 查询学科
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/statistics/findItemByPid")

    public List<SysConfigItemRelation> findItemByPid( SysConfigItemRelation relation, HttpServletRequest request){
    	relation.setCompanyId(WebUtils.getCurrentCompanyId());
        List<SysConfigItemRelation> list =sysConfigItemRelationServiceImpl.findChildByCode(relation);



        return list;
    }

    @ResponseBody
    @RequestMapping(value="/statistics/queryStudentsWatchInfoCountCurrent")

    public Map queryStudentsWatchInfoCountCurrent(
            String startDate,String endDate,String secondItemCode,String itemThirdCode,
            String comId,String lesson,Integer page,String orderBy, HttpServletRequest request){

        Map<String,Object> map  = new HashMap<>();
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("secondItemCode",secondItemCode);
        map.put("itemThirdCode",itemThirdCode);
        map.put("comId",comId);
        map.put("lesson",lesson);
        map.put("firstPage",(page-1)*10);
        map.put("page",page);
        map.put("pageSize",10);
        map.put("orderBy",orderBy);
        map.put("companyId",WebUtils.getCurrentCompanyId());

        PageFinder2<Map> list =studentStatisticsServiceImpl.queryStudentsWatchInfoCountCurrent(map);

        Map<String,Object> result = new HashMap<>();
        result.put("pageFinder",list);

        return result;
    }

    @ResponseBody
    @RequestMapping(value="/statistics/queryStudentsWatchInfoTime")

    public Map queryStudentsWatchInfoTime(
            String roomId, HttpServletRequest request){

        Map<String,Object> map  = new HashMap<>();
        map.put("roomId",roomId);

        List<Map> list =studentStatisticsServiceImpl.queryStudentsWatchInfoTime(map);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Calendar c = Calendar.getInstance();
        for(Map a : list){
            /*long time = (long)a.get("watch_date");
            c.setTimeInMillis(time);*/
            a.put("watch_date",sdf.format(a.get("watch_date")));
        }
        Map<String,Object> result = new HashMap<>();
        result.put("list",list);

        return result;
    }
    @RequestMapping(value = "/exportStudentsWatchInfoCountData")
    public ModelAndView exportStudentsWatchInfoCountData(Model model,String startTime,String endTime){
        List<Map> list = new ArrayList<>();
        Map<String,Object> info  = new HashMap<>();
        info.put("startDate",startTime);
        info.put("endDate",endTime);
        info.put("pageSize",20000);
        list = studentStatisticsServiceImpl.exportStudentsWatchInfoCountData(info);
        SysConfigDict search = new SysConfigDict();
        search.setDictCode("EDU_SCHOOL_AREA");
        List<SysConfigDict> areas =  sysConfigDictServiceImpl.queryConfigDictListByDictCode(search);
        //如果查询结果与区县数目不等则补全对应区县且设置为0
        //根据字典的顺序重新加入集合

        List<Map> newList = new ArrayList<>();
        if(list.size()!=areas.size()){
            for(SysConfigDict area:areas){
                boolean flag = false;
                for(Map data:list){
                    if(data.get("item_code")!=null && data.get("item_code").equals(area.getItemCode())){
                        newList.add(data);
                        flag=true;
                        break;
                    }
                }
                if(!flag){
                    Map map = new HashMap();
                    map.put("item_code",area.getItemCode());
                    map.put("item_value",area.getItemValue());
                    map.put("times",0);
                    map.put("watch_time","00:00:00");
                    newList.add(map);
                }
            }
        }else{
            newList = list;
        }


        List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
        for (Map v : newList) {
            lists.add(v);
        }
        String titles = "";
        titles = "区域:item_value,直播观看总人次:times,直播观看总时长:watch_time";

        StringBuffer title = new StringBuffer(
                titles);
        ViewFiles excel = new ViewFiles();
        HSSFWorkbook wb = new HSSFWorkbook();
        try {
            wb = ExcelUtil.newWorkbook(lists, "sheet1", title.toString());
        } catch (Exception ex) {

        }
        Map map = new HashMap();
        map.put("workbook", wb);
        map.put("fileName", "区县直播累计观看统计.xls");
        return new ModelAndView(excel, map);
    }

    @RequestMapping(value = "/exportStudentsWatchInfoCountCurrent")
    public ModelAndView exportStudentsWatchInfoCountCurrent(Model model,String startTime,String endTime,String secondItemCode,String itemThirdCode,
                                                            String comId,String lesson) {
        List<Map> list = new ArrayList<>();
        Map<String,Object> info  = new HashMap<>();
        info.put("startDate",startTime);
        info.put("endDate",endTime);
        info.put("secondItemCode",secondItemCode);
        info.put("itemThirdCode",itemThirdCode);
        info.put("comId",comId);
        info.put("lesson",lesson);
        //userVideoVo.setCompanyId(WebUtils.getCurrentCompanyId());
        info.put("pageSize",20000);
        list = studentStatisticsServiceImpl.exportStudentsWatchInfoCountCurrent(info);//studentStatisticsServiceImpl.queryStudentsWatchInfoList(search);
        List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
        for (Map v : list) {
            lists.add(v);
        }
        String titles = "";
            titles = "课程名称:name,课次名称:lesson_name,观看人次:times,最大并发数:max_concurrent,观看人数:user_num,移动端学习人次:no_pc,非移动端学习人次:pc";

        StringBuffer title = new StringBuffer(
                titles);
        ViewFiles excel = new ViewFiles();
        HSSFWorkbook wb = new HSSFWorkbook();
        try {
            wb = ExcelUtil.newWorkbook(lists, "sheet1", title.toString());
        } catch (Exception ex) {

        }
        Map map = new HashMap();
        map.put("workbook", wb);
        map.put("fileName", "直播并发统计.xls");
        return new ModelAndView(excel, map);
    }


    /**
     * 教师授课详情页面跳转
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value="/statistics/teacherVideoList")
    public String teacherVideoList(Model model, HttpServletRequest request){
        //查询学校所在区域
        SysConfigDict areaDict = new SysConfigDict();
        areaDict.setDictCode("EDU_SCHOOL_AREA");
        List<SysConfigDict> areas = sysConfigDictServiceImpl.queryConfigDictListByDictCode(areaDict);
        model.addAttribute("areas", areas);

        //查询学校所属学段
        SysConfigDict stepDict = new SysConfigDict();
        stepDict.setDictCode("EDU_STEP_NEW");
        List<SysConfigDict> stepNews = sysConfigDictServiceImpl.queryConfigDictListByDictCode(stepDict);
        model.addAttribute("stepNews", stepNews);

        //学校所属学科
        List<SysConfigItemRelation> subjectItem = sysConfigItemRelationServiceImpl.findItemFrontByLevel(2,WebUtils.getCurrentCompanyId());//查询学科
        model.addAttribute("subjectItem", subjectItem);

        //计算时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        model.addAttribute("endTime" ,sdf.format(cal.getTime()));
        cal.add(Calendar.DAY_OF_MONTH, -6);
        model.addAttribute("startTime" ,sdf.format(cal.getTime()));
        return "/queVideo/queryTeacherVideoList";
    }

    /**
     * 用户点播详情页面跳转
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value="/statistics/userVideoList")
    public String userVideoList(Model model, HttpServletRequest request){
        //查询学校所在区域
        SysConfigDict areaDict = new SysConfigDict();
        areaDict.setDictCode("EDU_SCHOOL_AREA");
        List<SysConfigDict> areas = sysConfigDictServiceImpl.queryConfigDictListByDictCode(areaDict);
        model.addAttribute("areas", areas);

        //查询学校所属学段
        SysConfigDict stepDict = new SysConfigDict();
        stepDict.setDictCode("EDU_STEP_NEW");
        List<SysConfigDict> stepNews = sysConfigDictServiceImpl.queryConfigDictListByDictCode(stepDict);
        model.addAttribute("stepNews", stepNews);

        //年份列表
        List<Integer> years = new ArrayList<Integer>();
        int curYear = DateUtil.getCurYear();
        for(int year = 0;year<12;year++){
            years.add(curYear-year);
        }
        model.addAttribute( "years", years);

        //计算时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        model.addAttribute("endTime" ,sdf.format(cal.getTime()));
        cal.add(Calendar.DAY_OF_MONTH, -6);
        model.addAttribute("startTime" ,sdf.format(cal.getTime()));
        return "/queVideo/queryUserVideoList";
    }


    /**
     * 用户点播详情页面跳转
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value="/areastatistics/userVideoList")
    public String userVideoListArea(Model model, HttpServletRequest request){
        Users user = WebUtils.getCurrentUser();
        UsersAreaRelation uersAreaRelation = usersServiceImpl.findUsersAreaRelation(user.getId());
        //查询学校所在区域
        SysConfigDict areaDict = new SysConfigDict();
        areaDict.setItemCode(uersAreaRelation.getEduArea());
        areaDict.setDictCode("EDU_SCHOOL_AREA");
        List<SysConfigDict> area = sysConfigDictServiceImpl.queryConfigDictListByDictCode(areaDict);
        if(area!=null && area.get(0)!=null){
            model.addAttribute("area", area.get(0));
        }

        //查询学校所属学段
        SysConfigDict stepDict = new SysConfigDict();
        stepDict.setDictCode("EDU_STEP_NEW");
        List<SysConfigDict> stepNews = sysConfigDictServiceImpl.queryConfigDictListByDictCode(stepDict);
        model.addAttribute("stepNews", stepNews);

        //年份列表
        List<Integer> years = new ArrayList<Integer>();
        int curYear = DateUtil.getCurYear();
        for(int year = 0;year<12;year++){
            years.add(curYear-year);
        }
        model.addAttribute( "years", years);

        //课程学段
        List<SysConfigItemRelation> stepList = sysConfigItemRelationServiceImpl.findItemFrontByLevel(1,WebUtils.getCurrentCompanyId());
        model.addAttribute( "stepItem", stepList);
        //课程科目
        List<SysConfigItemRelation> subjectList = sysConfigItemRelationServiceImpl.findItemFrontByLevel(2,WebUtils.getCurrentCompanyId());
        model.addAttribute( "subjectItem", subjectList);

        //计算时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        model.addAttribute("endTime" ,sdf.format(cal.getTime()));
        cal.add(Calendar.DAY_OF_MONTH, -6);
        model.addAttribute("startTime" ,sdf.format(cal.getTime()));
        return "/queVideo/queryUserVideoList_area";
    }

    /**
     * 用户点播详情页面跳转
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value="/orgstatistics/userVideoList")
    public String userVideoListOrg(Model model, HttpServletRequest request){
        Users loginUser = WebUtils.getCurrentUser(request);
        UsersAreaRelation uersAreaRelation=new UsersAreaRelation();
        Subject subject = SecurityUtils.getSubject();
        if( subject.hasRole("班主任")){
        	uersAreaRelation=usersServiceImpl.findUsersAreaRelationT(loginUser.getId(),WebUtils.getCurrentCompany().getEduAreaSchool());
        	int userId=WebUtils.getCurrentUserId(request);
 			EduMasterClass etc=new EduMasterClass();
        	etc.setUserId(String.valueOf(userId));
 			List<EduMasterClass> list=studentServiceImpl.findClassByTeacherId(etc,WebUtils.getCurrentCompany().getEduAreaSchool());
 			if(null!=list && list.size()>0){
 				String eduStep=list.get(0).getEduStep();
 				String eduYear=list.get(0).getEduYear();
 				String eduClass=list.get(0).getEduClass();
				model.addAttribute("eduStep", eduStep);
				if("STEP_01".equals(eduStep)){
					model.addAttribute("eduStepName", "小学");
				}else if("STEP_02".equals(eduStep)){
					model.addAttribute("eduStepName", "初中中学");
				}else{
					model.addAttribute("eduStepName", "高中中学");
				}
				model.addAttribute("eduYear", eduYear);
				model.addAttribute("eduClass", eduClass);
				model.addAttribute("role", "2");//2表示教师
 			}
        }else if(subject.hasRole("任课老师")){
        	EduMasterClass ets =new EduMasterClass();
        	ets.setUserId(String.valueOf(WebUtils.getCurrentUserId(request)));
    		ets.setEduSchool(WebUtils.getCurrentCompany().getEduAreaSchool());
    		ets.setEduStep("1");
    		List<EduMasterClass> eduStepGLY=studentServiceImpl.findSubjectClassByTeacherId(ets);
    		ets.setEduStep("");
    		ets.setEduYear("1");
    		List<EduMasterClass> eduYearGLY=studentServiceImpl.findSubjectClassByTeacherId(ets);
    		ets.setEduYear("");
    		ets.setEduClass("1");
    		List<EduMasterClass> eduClassGLY=studentServiceImpl.findSubjectClassByTeacherId(ets);
    		model.addAttribute("eduStep", eduStepGLY);
    		model.addAttribute("eduYear", eduYearGLY);
    		model.addAttribute("eduClass", eduClassGLY);
        	uersAreaRelation=usersServiceImpl.findUsersAreaRelationR(loginUser.getId(),WebUtils.getCurrentCompany().getEduAreaSchool());
        	model.addAttribute("role", "3");//3表示任课老师
        }else{
        	uersAreaRelation = usersServiceImpl.findUsersAreaRelation(loginUser.getId());	
        }
        //查询学校所在区域
        SysConfigDict areaDict = new SysConfigDict();
        areaDict.setItemCode(uersAreaRelation.getEduArea());
        areaDict.setDictCode("EDU_SCHOOL_AREA");
        List<SysConfigDict> area = sysConfigDictServiceImpl.queryConfigDictListByDictCode(areaDict);
        if(area!=null && area.get(0)!=null){
            model.addAttribute("area", area.get(0));
        }
        //查询学校所在学校
        SysConfigDict orgDict = new SysConfigDict();
        orgDict.setItemCode(uersAreaRelation.getEduSchool());
        orgDict.setDictCode("EDU_SCHOOL");
        List<SysConfigDict> org = sysConfigDictServiceImpl.queryConfigDictListByDictCode(orgDict);
        if(org!=null && org.get(0)!=null){
            model.addAttribute("org", org.get(0));
        }

        //查询学校所属学段
        SysConfigDict stepDict = new SysConfigDict();
        stepDict.setDictCode("EDU_STEP");
        List<SysConfigDict> steps = sysConfigDictServiceImpl.queryConfigDictListByDictCode(stepDict);
        model.addAttribute("steps", steps);

        //年份列表
        List<Integer> years = new ArrayList<Integer>();
        int curYear = DateUtil.getCurYear();
        for(int year = 0;year<12;year++){
            years.add(curYear-year);
        }
        model.addAttribute( "years", years);

        //课程学段
        List<SysConfigItemRelation> stepList = sysConfigItemRelationServiceImpl.findItemFrontByLevel(1,WebUtils.getCurrentCompanyId());
        model.addAttribute( "stepItem", stepList);
        //课程科目
        List<SysConfigItemRelation> subjectList = sysConfigItemRelationServiceImpl.findItemFrontByLevel(2,WebUtils.getCurrentCompanyId());
        model.addAttribute( "subjectItem", subjectList);

        //计算时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        model.addAttribute("endTime" ,sdf.format(cal.getTime()));
        cal.add(Calendar.DAY_OF_MONTH, -6);
        model.addAttribute("startTime" ,sdf.format(cal.getTime()));
        return "/queVideo/queryUserVideoList_org";
    }

    /**
     *
     * Class Name: StudentController.java
     *
     * @Description: 查询学员列表数据
     * @author zhang.zx
     * @date 2015年9月29日 下午4:46:54
     * @modifier
     * @modify-date 2015年9月29日 下午4:46:54
     * @version 1.0
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryTeacherCourseList")
    public PageFinder<VideoCourseVo> queryTeacherCourseList(VideoCourseVo videoCourseVo) {
        videoCourseVo.setCompanyId(WebUtils.getCurrentCompanyId());
        // 分页调整
        if (videoCourseVo.getPageSize() == null) {
            videoCourseVo.setPageSize(10);
        }

        PageFinder<VideoCourseVo> pageFinder = sysPlayLogsServiceImpl.queryVideoCoursePage(videoCourseVo);
        return pageFinder;
    }

    /**
     *
     * Class Name: StudentController.java
     *
     * @Description: 查询学员列表数据
     * @author zhang.zx
     * @date 2015年9月29日 下午4:46:54
     * @modifier
     * @modify-date 2015年9月29日 下午4:46:54
     * @version 1.0
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryUserVideoList")
    public PageFinder<UserVideoVo> queryUserVideoList(UserVideoVo userVideoVo, HttpServletRequest request) {
        userVideoVo.setCompanyId(WebUtils.getCurrentCompanyId());
        // 分页调整
        if (userVideoVo.getPageSize() == null) {
            userVideoVo.setPageSize(10);
        }
        Users loginUser = WebUtils.getCurrentUser();
        UsersAreaRelation uersAreaRelation = null;
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("区县负责人")){
        	uersAreaRelation = usersServiceImpl.findUsersAreaRelation(loginUser.getId());
        	if(uersAreaRelation!=null){
        		userVideoVo.setEduArea(uersAreaRelation.getEduArea());
        	}
        }else if(subject.hasRole("学校负责人")) {
        	uersAreaRelation = usersServiceImpl.findUsersAreaRelation(loginUser.getId());
        	if(uersAreaRelation!=null){
        		userVideoVo.setEduArea(uersAreaRelation.getEduArea());
        		userVideoVo.setEduSchool(uersAreaRelation.getEduSchool());
        	}
        }else if(subject.hasRole("班主任")||subject.hasRole("任课老师")){
        	uersAreaRelation=usersServiceImpl.findUsersAreaRelationT(loginUser.getId(),WebUtils.getCurrentCompany().getEduAreaSchool());
        	if(uersAreaRelation!=null){
        		userVideoVo.setEduArea(uersAreaRelation.getEduArea());
        		userVideoVo.setEduSchool(uersAreaRelation.getEduSchool());
        	}
	        List<EduMasterClass> list=null;
	        if(subject.hasRole("班主任")){
	        	EduMasterClass etc=new EduMasterClass();
	        	etc.setUserId(String.valueOf(loginUser.getId()));
	        	list=studentServiceImpl.findClassByTeacherId(etc,WebUtils.getCurrentCompany().getEduAreaSchool());
	        }else if(subject.hasRole("任课老师")){
	        	EduMasterClass ets =new EduMasterClass();
	            ets.setUserId(String.valueOf(WebUtils.getCurrentUserId(request)));
	        	ets.setEduSchool(WebUtils.getCurrentCompany().getEduAreaSchool());
	        	ets.setEduYear("");
	        	ets.setEduClass("1");
	        	list=studentServiceImpl.findSubjectClassByTeacherId(ets);
	        }
	        userVideoVo.setEduMasterClass(list);
        }
        PageFinder<UserVideoVo> pageFinder = sysPlayLogsServiceImpl.queryUserVideoPage(userVideoVo);
        return pageFinder;
    }

    /**
     * 教师授课详情
     * @param model
     * @param videoCourseVo
     * @return
     */
    @RequestMapping(value = "/exportTeacherCourseExcle")
    public ModelAndView exportTeacherCourseExcle(Model model, VideoCourseVo videoCourseVo) {
        List<VideoCourseVo> al = new ArrayList<VideoCourseVo>();
        if (EntityUtil.isNotBlank(videoCourseVo)) {
            videoCourseVo.setCompanyId(WebUtils.getCurrentCompanyId());
            videoCourseVo.setPageSize(20000);
            al = sysPlayLogsServiceImpl.queryVideoCourseList(videoCourseVo);
        }
        List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
        for (VideoCourseVo v : al) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("areaName", v.getAreaName());
            map.put("schoolStepName", v.getSchoolStepName());
            map.put("schoolName", v.getSchoolName());
            map.put("teaName", v.getTeaName());
            map.put("courseName", v.getCourseName());
            map.put("stepName", v.getStepName());
            map.put("subjectName", v.getSubjectName());
            map.put("totleStudy", v.getTotleStudy());
            map.put("totleStudyLength", v.getTotleStudyLength());
            map.put("studyRate", v.getStudyRate()!=null ? v.getStudyRate()+"%":"");
            lists.add(map);
        }
        StringBuffer title = new StringBuffer(
                "区域:areaName,学校性质:schoolStepName,学校:schoolName,老师:teaName,课程名:courseName,课程学段:stepName,学科:subjectName,总播放量:totleStudy,总播放时长:totleStudyLength,播完率:studyRate");
        ViewFiles excel = new ViewFiles();
        HSSFWorkbook wb = new HSSFWorkbook();
        try {
            wb = ExcelUtil.newWorkbook(lists, "sheet1", title.toString());
        } catch (Exception ex) {

        }
        Map map = new HashMap();
        map.put("workbook", wb);
        map.put("fileName", "教师授课详情.xls");
        return new ModelAndView(excel, map);
    }

    /**
     * 区县详情导出
     * @param model
     * @param userVideoVo
     * @return
     */
    @RequestMapping(value = "/exportVideoCourseDetailExcle_area")
    public ModelAndView exportVideoCourseDetailExcleArea(Model model, UserVideoVo userVideoVo) {
        List<UserVideoVo> al = new ArrayList<UserVideoVo>();
        if (EntityUtil.isNotBlank(userVideoVo)) {
            userVideoVo.setCompanyId(WebUtils.getCurrentCompanyId());
            userVideoVo.setPageSize(20000);
            userVideoVo.setClassName(userVideoVo.getClassTypeName());
            al = sysPlayLogsServiceImpl.queryUserVideoList(userVideoVo);
        }
        List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
        for (UserVideoVo v : al) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("dataTime", userVideoVo.getStartTime()+"至"+userVideoVo.getEndTime());
            map.put("schoolStepName", v.getSchoolStepName());
            map.put("schoolName", v.getSchoolName());
            map.put("stepName", v.getStepName());
            map.put("subjectName", v.getSubjectName());
            map.put("courseName", v.getCourseName());
            map.put("yearName", v.getYearName());
            map.put("className", v.getClassName());
            map.put("username", v.getUsername());
            map.put("name", v.getName());
            map.put("totleStudyLength", v.getTotleStudyLength());
            map.put("studyRate", v.getStudyRate()!=null ? v.getStudyRate()+"%":"");
            map.put("viewNum", v.getViewNum());
            lists.add(map);
        }
        StringBuffer title = new StringBuffer(
                    "日期:dataTime,学校性质:schoolStepName,学校:schoolName,学段:stepName,学科:subjectName,课程名:courseName,入学年份:yearName,班级:className,用户名:username,学员名:name,总播放时长:totleStudyLength,播完率:studyRate,观看次数:viewNum");
        ViewFiles excel = new ViewFiles();
        HSSFWorkbook wb = new HSSFWorkbook();
        try {
            wb = ExcelUtil.newWorkbook(lists, "sheet1", title.toString());
        } catch (Exception ex) {

        }
        Map map = new HashMap();
        map.put("workbook", wb);
        map.put("fileName", "点播统计详情.xls");
        return new ModelAndView(excel, map);
    }

    /**
     * 区县点播导出详情
     * @param model
     * @param videoCourseVo
     * @return
     */
    @RequestMapping(value = "/exportVideoCourseIndexExcle_area")
    public ModelAndView exportVideoCourseIndexExcleArea(Model model, VideoCourseVo videoCourseVo) {
        List<VideoCourseVo> al = new ArrayList<VideoCourseVo>();
        if (EntityUtil.isNotBlank(videoCourseVo)) {
            videoCourseVo.setCompanyId(WebUtils.getCurrentCompanyId());
            videoCourseVo.setPageSize(20000);
            al = sysPlayLogsServiceImpl.queryAreaCourseIndexList(videoCourseVo);
        }
        List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
        for (VideoCourseVo v : al) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("schoolStepName", v.getSchoolStepName());
            map.put("schoolName", v.getSchoolName());
            map.put("stepName", v.getStepName());
            map.put("totleStudy", v.getTotleStudy());
            map.put("totleStudyLength", v.getTotleStudyLength());
            map.put("studyRate", v.getStudyRate()+"%");
            map.put("viewNum", v.getViewNum());
            lists.add(map);
        }
        StringBuffer title = new StringBuffer(
                    "学校性质:schoolStepName,学校:schoolName,学段:stepName,总播放量:totleStudy,总播放时长:totleStudyLength,播完率:studyRate,观看人数:viewNum");
        ViewFiles excel = new ViewFiles();
        HSSFWorkbook wb = new HSSFWorkbook();
        try {
            wb = ExcelUtil.newWorkbook(lists, "sheet1", title.toString());
        } catch (Exception ex) {

        }
        Map map = new HashMap();
        map.put("workbook", wb);
        map.put("fileName", "点播统计概况.xls");
        return new ModelAndView(excel, map);
    }

    /**
     * 管理员点播导出详情
     * @param model
     * @param videoCourseVo
     * @return
     */
    @RequestMapping(value = "/exportVideoCourseDetailExcle")
    public ModelAndView exportVideoCourseDetailExcle(Model model, VideoCourseVo videoCourseVo,HttpServletRequest request) {
        List<VideoCourseVo> al = new ArrayList<VideoCourseVo>();
        if (EntityUtil.isNotBlank(videoCourseVo)) {
            videoCourseVo.setCompanyId(WebUtils.getCurrentCompanyId());
            videoCourseVo.setPageSize(20000);
            Users loginUser = WebUtils.getCurrentUser();
            UsersAreaRelation uersAreaRelation = null;
            Subject subject = SecurityUtils.getSubject();
            if(subject.hasRole("区县负责人")){
            	uersAreaRelation = usersServiceImpl.findUsersAreaRelation(loginUser.getId());
            	if(uersAreaRelation!=null){
            		videoCourseVo.setEduArea(uersAreaRelation.getEduArea());
            	}
            }else if(subject.hasRole("学校负责人")) {
            	uersAreaRelation = usersServiceImpl.findUsersAreaRelation(loginUser.getId());
            	if(uersAreaRelation!=null){
            		videoCourseVo.setEduArea(uersAreaRelation.getEduArea());
            		videoCourseVo.setEduSchool(uersAreaRelation.getEduSchool());
            	}
            }else if(subject.hasRole("班主任")||subject.hasRole("任课老师")){
            	uersAreaRelation=usersServiceImpl.findUsersAreaRelationT(loginUser.getId(),WebUtils.getCurrentCompany().getEduAreaSchool());
            	if(uersAreaRelation!=null){
            		videoCourseVo.setEduArea(uersAreaRelation.getEduArea());
            		videoCourseVo.setEduSchool(uersAreaRelation.getEduSchool());
            	}
    	        List<EduMasterClass> list=null;
    	        if(subject.hasRole("班主任")){
    	        	EduMasterClass etc=new EduMasterClass();
    	        	etc.setUserId(String.valueOf(loginUser.getId()));
    	        	list=studentServiceImpl.findClassByTeacherId(etc,WebUtils.getCurrentCompany().getEduAreaSchool());
    	        }else if(subject.hasRole("任课老师")){
    	        	EduMasterClass ets =new EduMasterClass();
    	            ets.setUserId(String.valueOf(WebUtils.getCurrentUserId(request)));
    	        	ets.setEduSchool(WebUtils.getCurrentCompany().getEduAreaSchool());
    	        	ets.setEduYear("");
    	        	ets.setEduClass("1");
    	        	list=studentServiceImpl.findSubjectClassByTeacherId(ets);
    	        }
    	        videoCourseVo.setEduMasterClass(list);
            }
            al = sysPlayLogsServiceImpl.queryCourseIndexList(videoCourseVo);
        }
        List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
        for (VideoCourseVo v : al) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("courseName", v.getCourseName());
            map.put("teaName", v.getTeaName());
            map.put("time", videoCourseVo.getStartTime()+"至"+videoCourseVo.getEndTime());
            map.put("totleStudy", v.getTotleStudy());
            map.put("totleStudyLength", v.getTotleStudyLength());
            map.put("studyRate", v.getStudyRate()!=null?v.getStudyRate()+"%":0+"%");
            lists.add(map);
        }
        StringBuffer title = new StringBuffer(
                    "课程名:courseName,教师:teaName,日期:time,总播放量:totleStudy,总播放时长:totleStudyLength,播完率:studyRate");
        ViewFiles excel = new ViewFiles();
        HSSFWorkbook wb = new HSSFWorkbook();
        try {
            wb = ExcelUtil.newWorkbook(lists, "sheet1", title.toString());
        } catch (Exception ex) {

        }
        Map map = new HashMap();
        map.put("workbook", wb);
        map.put("fileName", "视频点播统计详情.xls");
        return new ModelAndView(excel, map);
    }

    /**
     * 用户点播统计
     * @param model
     * @param userVideoVo
     * @return
     */
    @RequestMapping(value = "/exportUserVideoExcle")
    public ModelAndView exportUserVideoExcle(Model model, UserVideoVo userVideoVo) {
        List<UserVideoVo> al = new ArrayList<UserVideoVo>();
        if (EntityUtil.isNotBlank(userVideoVo)) {
            userVideoVo.setCompanyId(WebUtils.getCurrentCompanyId());
            userVideoVo.setPageSize(20000);
            al = sysPlayLogsServiceImpl.queryUserVideoList(userVideoVo);
        }
        List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
        for (UserVideoVo v : al) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("time", userVideoVo.getStartTime() + "至" + userVideoVo.getEndTime());
            map.put("areaName", v.getAreaName());
            map.put("schoolName", v.getSchoolName());
            map.put("stepName", v.getCourseStepName());
            map.put("subjectName", v.getSubjectName());
            map.put("courseName", v.getCourseName());
            map.put("username", v.getUsername());
            map.put("name", v.getName());
            map.put("yearName", v.getYearName()!=null ? v.getYearName()+"级":"");
            map.put("className", v.getClassName()!=null ? v.getClassName()+"班":"");
            map.put("totleStudyLength", v.getTotleStudyLength());
            map.put("studyRate", v.getStudyRate()!=null ? v.getStudyRate()+"%":"");
            map.put("viewNum", v.getViewNum());
            lists.add(map);
        }
        StringBuffer title = new StringBuffer(
                "日期:time,区域:areaName,学校:schoolName,课程学段:stepName,学科:subjectName,课程名:courseName,用户名:username,学员名:name,入学年份:yearName,班级:className,总播放时长:totleStudyLength,播完率:studyRate,观看次数:viewNum");
        ViewFiles excel = new ViewFiles();
        HSSFWorkbook wb = new HSSFWorkbook();
        try {
            wb = ExcelUtil.newWorkbook(lists, "sheet1", title.toString());
        } catch (Exception ex) {

        }
        Map map = new HashMap();
        map.put("workbook", wb);
        map.put("fileName", "用户点播统计.xls");
        return new ModelAndView(excel, map);
    }

    /**
     * 点播统计--详情导出
     * @param model
     * @param userVideoVo
     * @return
     */
    @RequestMapping(value = "/exportVideoCourseDetailExcle_org")
    public ModelAndView exportVideoCourseDetailExcleOrg(Model model, UserVideoVo userVideoVo) {
        List<UserVideoVo> al = new ArrayList<UserVideoVo>();
        if (EntityUtil.isNotBlank(userVideoVo)) {
            userVideoVo.setCompanyId(WebUtils.getCurrentCompanyId());
            userVideoVo.setPageSize(20000);
            userVideoVo.setClassName(userVideoVo.getClassTypeName());
            al = sysPlayLogsServiceImpl.queryUserVideoList(userVideoVo);
        }
        List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
        for (UserVideoVo v : al) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("time", userVideoVo.getStartTime() + "至" + userVideoVo.getEndTime());
            map.put("stepName", v.getStepName());
            map.put("courseStepName", v.getCourseStepName());
            map.put("subjectName", v.getSubjectName());
            map.put("courseName", v.getCourseName());
            map.put("username", v.getUsername());
            map.put("name", v.getName());
            map.put("yearName", v.getYearName()!=null ? v.getYearName()+"级":"");
            map.put("className", v.getClassName()!=null ? v.getClassName()+"班":"");
            map.put("totleStudyLength", v.getTotleStudyLength());
            map.put("studyRate", v.getStudyRate()!=null ? v.getStudyRate()+"%":"");
            map.put("viewNum", v.getViewNum());
            lists.add(map);
        }
        StringBuffer title = new StringBuffer(
                "日期:time,学段:stepName,课程学段:courseStepName,学科:subjectName,课程名:courseName,用户名:username,学员名:name,入学年份:yearName,班级:className,总播放时长:totleStudyLength,播完率:studyRate,观看次数:viewNum");
        ViewFiles excel = new ViewFiles();
        HSSFWorkbook wb = new HSSFWorkbook();
        try {
            wb = ExcelUtil.newWorkbook(lists, "sheet1", title.toString());
        } catch (Exception ex) {

        }
        Map map = new HashMap();
        map.put("workbook", wb);
        map.put("fileName", "校级用户点播统计.xls");
        return new ModelAndView(excel, map);
    }


    /**
     * 点播统计-概况
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/statistics/videoCourseIndex")
    public String videoCourseIndex(Model model, HttpServletRequest request) throws Exception {
        Users loginUser = WebUtils.getCurrentUser(request);
        if(loginUser==null || loginUser.getId()==null){
            throw new Exception("数据出现异常，请联系管理员！");
        }

        //计算时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        String endTime = sdf.format(cal.getTime());
        model.addAttribute("endTime" ,endTime);
        cal.add(Calendar.DAY_OF_MONTH, -6);
        String startTime = sdf.format(cal.getTime());
        model.addAttribute("startTime" ,startTime);

        //查询区域的录播观看人数
        Map<String, Object> papamMap = new HashMap<String, Object>();
        papamMap.put("startTime", startTime);
        papamMap.put("endTime", endTime);

        return "/queVideo/videoCourseIndex";
    }


    /**
     * 点播统计-概况
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/areastatistics/videoCourseIndex")
    public String videoCourseIndexArea(Model model, HttpServletRequest request) throws Exception {
        Users loginUser = WebUtils.getCurrentUser(request);
        if(loginUser==null || loginUser.getId()==null){
            throw new Exception("数据出现异常，请联系管理员！");
        }

        //获取账号对应用户信息
        UsersAreaRelation uersAreaRelation = usersServiceImpl.findUsersAreaRelation(loginUser.getId());
        if(uersAreaRelation==null){
            throw new Exception("数据出现异常，请联系管理员！");
        }

        Map<String, Object> papamMap = new HashMap<String, Object>();
        //查询学校所在区域
        SysConfigDict areaDict = new SysConfigDict();
        areaDict.setItemCode(uersAreaRelation.getEduArea());
        areaDict.setDictCode("EDU_SCHOOL_AREA");
        List<SysConfigDict> area = sysConfigDictServiceImpl.queryConfigDictListByDictCode(areaDict);
        if(area!=null && area.get(0)!=null){
            model.addAttribute("area", area.get(0));
            papamMap.put("eduArea", area.get(0).getItemCode());
        }

        //计算时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        String endTime = sdf.format(cal.getTime());
        model.addAttribute("endTime" ,endTime);
        cal.add(Calendar.DAY_OF_MONTH, -6);
        String startTime = sdf.format(cal.getTime());
        model.addAttribute("startTime" ,startTime);

        papamMap.put("startTime", startTime);
        papamMap.put("endTime", endTime);

        //查询学校所属学段
        SysConfigDict stepDict = new SysConfigDict();
        stepDict.setDictCode("EDU_STEP_NEW");
        List<SysConfigDict> stepNews = sysConfigDictServiceImpl.queryConfigDictListByDictCode(stepDict);
        model.addAttribute("stepNews", stepNews);
        return "/queVideo/videoCourseIndex_area";
    }


    /**
     * 点播统计-概况
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/orgstatistics/videoCourseIndex")
    public String videoCourseIndexOrg(Model model, HttpServletRequest request) throws Exception {
        Users loginUser = WebUtils.getCurrentUser(request);
        if(loginUser==null || loginUser.getId()==null){
            throw new Exception("数据出现异常，请联系管理员！");
        }
        //获取账号对应用户信息
        UsersAreaRelation uersAreaRelation=new UsersAreaRelation();
        Subject subject = SecurityUtils.getSubject();
        if( subject.hasRole("班主任")){
        	uersAreaRelation=usersServiceImpl.findUsersAreaRelationT(loginUser.getId(),WebUtils.getCurrentCompany().getEduAreaSchool());
        }else if(subject.hasRole("任课老师")){
        	uersAreaRelation=usersServiceImpl.findUsersAreaRelationR(loginUser.getId(),WebUtils.getCurrentCompany().getEduAreaSchool());
        }else{
        	uersAreaRelation = usersServiceImpl.findUsersAreaRelation(loginUser.getId());	
        }
        if(uersAreaRelation==null){
           // throw new Exception("数据出现异常，请联系管理员！");
            return "/queVideo/videoCourseIndex_org";
        }
        Map<String, Object> papamMap = new HashMap<String, Object>();
        //查询所在区域
        SysConfigDict dict = new SysConfigDict();
        dict.setItemCode(uersAreaRelation.getEduArea());
        dict.setDictCode("EDU_SCHOOL_AREA");
        List<SysConfigDict> area = sysConfigDictServiceImpl.queryConfigDictListByDictCode(dict);
        if(area!=null && area.get(0)!=null){
            model.addAttribute("area", area.get(0));
            papamMap.put("eduArea", area.get(0).getItemCode());
        }
        //查询所在学校
        dict.setItemCode(uersAreaRelation.getEduSchool());
        dict.setDictCode("EDU_SCHOOL");
        List<SysConfigDict> org = sysConfigDictServiceImpl.queryConfigDictListByDictCode(dict);
        if(org!=null && org.get(0)!=null){
            model.addAttribute("org", org.get(0));
            papamMap.put("eduSchool", org.get(0).getItemCode());
        }
        //计算时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        String endTime = sdf.format(cal.getTime());
        model.addAttribute("endTime" ,endTime);
        cal.add(Calendar.DAY_OF_MONTH, -6);
        String startTime = sdf.format(cal.getTime());
        model.addAttribute("startTime" ,startTime);
        model.addAttribute("isArea", WebUtils.getCurrentIsArea());
        papamMap.put("startTime", startTime);
        papamMap.put("endTime", endTime);
        return "/queVideo/videoCourseIndex_org";
    }
    @RequestMapping(value="/orgstatistics/userQue")
    public String userQue(Model model, HttpServletRequest request) throws Exception {
    	 Users loginUser = WebUtils.getCurrentUser(request);
         if(loginUser==null || loginUser.getId()==null){
             throw new Exception("数据出现异常，请联系管理员！");
         }
         
         //获取账号对应用户信息
         UsersAreaRelation uersAreaRelation=new UsersAreaRelation();
         Subject subject = SecurityUtils.getSubject();
         if( subject.hasRole("班主任")){
         	uersAreaRelation=usersServiceImpl.findUsersAreaRelationT(loginUser.getId(),WebUtils.getCurrentCompany().getEduAreaSchool());
         	int userId=WebUtils.getCurrentUserId(request);
  			EduMasterClass etc=new EduMasterClass();
         	etc.setUserId(String.valueOf(userId));
  			List<EduMasterClass> list=studentServiceImpl.findClassByTeacherId(etc,WebUtils.getCurrentCompany().getEduAreaSchool());
  			if(null!=list && list.size()>0){
  				String eduStep=list.get(0).getEduStep();
  				String eduYear=list.get(0).getEduYear();
  				String eduClass=list.get(0).getEduClass();
 				model.addAttribute("eduStep", eduStep);
 				if("STEP_01".equals(eduStep)){
 					model.addAttribute("eduStepName", "小学");
 				}else if("STEP_02".equals(eduStep)){
 					model.addAttribute("eduStepName", "初中中学");
 				}else{
 					model.addAttribute("eduStepName", "高中中学");
 				}
 				model.addAttribute("eduYear", eduYear);
 				model.addAttribute("eduClass", eduClass);
 				model.addAttribute("role", "2");//2表示教师
  			}
         }else if(subject.hasRole("任课老师")){
         	List<EduMasterClass> eduStep=new ArrayList<EduMasterClass>();
         	Map<String,String>eduStepMap=new HashMap<String, String>();
         	List<EduMasterClass> eduYear=new ArrayList<EduMasterClass>();
         	Map<String,String>eduYearMap=new HashMap<String, String>();
         	List<EduMasterClass> eduClass=new ArrayList<EduMasterClass>();
         	Map<String,String>eduClassMap=new HashMap<String, String>();
         	int userId=WebUtils.getCurrentUserId(request);
  			List<EduMasterClass> list=studentServiceImpl.findClassByRKTeacherId(userId);
  			for(int i=0;i<list.size();i++){
 					if(null!=eduStepMap && !eduStepMap.containsKey(list.get(i).getEduStep())){
 						EduMasterClass vo =new EduMasterClass();
 	 					if("STEP_01".equals(list.get(i).getEduStep())){
 	 						vo.setEduStep(list.get(i).getEduStep());
 	 						vo.setEduStepName("小学");
 	 					}else if("STEP_02".equals(list.get(i).getEduStep())){
 	 						vo.setEduStep(list.get(i).getEduStep());
 	 						vo.setEduStepName("初中中学");
 	 					}else{
 	 						vo.setEduStep(list.get(i).getEduStep());
 	 						vo.setEduStepName("高中中学");
 	 					}
 	 					eduStepMap.put(list.get(i).getEduStep(), list.get(i).getEduStep());
 	 					eduStep.add(vo);
 					}
 					if(null!=eduYearMap && !eduYearMap.containsKey(list.get(i).getEduYear())){
 						EduMasterClass vo =new EduMasterClass();
 						vo.setEduYear(list.get(i).getEduYear());
 						eduYearMap.put(list.get(i).getEduYear(), list.get(i).getEduYear());
 						eduYear.add(vo);
 					}
 					if(null!=eduClassMap && !eduClassMap.containsKey(list.get(i).getEduClass())){
 						EduMasterClass vo =new EduMasterClass();
 						vo.setEduClass(list.get(i).getEduClass());
 						eduClassMap.put(list.get(i).getEduClass(), list.get(i).getEduClass());
 						eduClass.add(vo);
 					}
 				}
         	uersAreaRelation=usersServiceImpl.findUsersAreaRelationR(loginUser.getId(),WebUtils.getCurrentCompany().getEduAreaSchool());
         	model.addAttribute("eduStep", eduStep);
         	model.addAttribute("eduYear", eduYear);
         	model.addAttribute("eduClass", eduClass);
         	model.addAttribute("role", "3");//3表示任课老师
         }else{
         	uersAreaRelation = usersServiceImpl.findUsersAreaRelation(loginUser.getId());	
         }
         if(uersAreaRelation==null){
             //throw new Exception("数据出现异常，请联系管理员！");
             return "/query/query_user_org";
         }
         // 查询课程的多课程单元和多班号功能
         CompanyFunctionSet search = new CompanyFunctionSet();
         search.setFunctionCode("COMPANY_FUNCTION_COURSE");
         search.setCompanyId(WebUtils.getCurrentCompanyId());
         //查询学校所在区域
         SysConfigDict areaDict = new SysConfigDict();
         areaDict.setDictCode("EDU_SCHOOL");
         areaDict.setItemCode(uersAreaRelation.getEduSchool());
         List<SysConfigDict> schools = sysConfigDictServiceImpl.queryConfigDictListByDictCode(areaDict);
         if(schools!=null && schools.size()>0 && schools.get(0)!=null){
             model.addAttribute("school", schools.get(0));
         }

         // 学员分组
         CompanyFunctionSet companyFunctionSet = new CompanyFunctionSet();
         companyFunctionSet.setCompanyId(WebUtils.getCurrentCompanyId());
         companyFunctionSet.setFunctionCode("STUDENT_GROUP");
         List<CompanyFunctionSet> companyFunctionSetList = companyFunctionSetServiceImpl.findCompanyFunctionSetByPage(companyFunctionSet);
         if (companyFunctionSetList != null && companyFunctionSetList.size() > 0) {
             model.addAttribute("sgOpen", companyFunctionSetList.get(0).getStatus());
         }

         // 查看该机构学员地址信息配置功能
         search.setFunctionCode("STUDENT_ADDRESS_INFO");
         CompanyFunctionSet address = companyFunctionSetServiceImpl.findCompanyUseCourse(search);
         if (address != null && "1".equals(address.getStatus())) {
             model.addAttribute("address", 1);
         } else {
             model.addAttribute("address", 0);
         }

         CompanyFunctionSet userorg_roleopenflag = WebUtils.getFunctionSet("USERORG_ROLEOPENFLAG");
         model.addAttribute("userorg_roleopenflag", userorg_roleopenflag==null?0:userorg_roleopenflag.getStatus());
         List<SysConfigDict> stepList = sysConfigDictServiceImpl.findByDicCode("EDU_STEP");
         model.addAttribute("stepList", stepList);
         model.addAttribute("isArea", WebUtils.getCurrentIsArea());
    	return "/query/query_user_org";
    }

    /**
     * 点播统计-概况
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/statistics/queryTotleVideoCourse")
    @ResponseBody
    public JSONObject queryTotleVideoCourse(HttpServletRequest request, String startTime, String endTime, String eduArea) throws Exception {
        JSONObject jsonObject = new JSONObject();
        Users loginUser = WebUtils.getCurrentUser(request);
        if(loginUser==null || loginUser.getId()==null){
            throw new Exception("数据出现异常，请联系管理员！");
        }

        Map<String, Object> papamMap = new HashMap<String, Object>();
        papamMap.put("startTime", startTime);
        papamMap.put("endTime", endTime);
        papamMap.put("eduArea", eduArea);
        papamMap.put("companyId",WebUtils.getCurrentCompanyId());
        //查询区域的录播观看人数
        List<Map<String, Object>> areaVideoList = sysPlayLogsServiceImpl.queryTotleVideoCourse(papamMap);

        jsonObject.put("areaVideoList", areaVideoList);
        return jsonObject;
    }

    /**
     * 点播统计-概况
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/statistics/queryTotleVideoCourse1")
    @ResponseBody
    public JSONObject queryTotleVideoCourse1(HttpServletRequest request, String startTime, String endTime, String eduArea) throws Exception {
        JSONObject jsonObject = new JSONObject();
        Users loginUser = WebUtils.getCurrentUser(request);
        if(loginUser==null || loginUser.getId()==null){
            throw new Exception("数据出现异常，请联系管理员！");
        }

        Map<String, Object> papamMap = new HashMap<String, Object>();
        papamMap.put("startTime", startTime);
        papamMap.put("endTime", endTime);
        papamMap.put("eduArea", eduArea);
        //查询区域的录播观看人数
        List<Map<String, Object>> areaVideoList = sysPlayLogsServiceImpl.queryTotleVideoCourse1(papamMap);

        jsonObject.put("areaVideoList", areaVideoList);
        return jsonObject;
    }

    /**
     * 点播统计-概况
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/statistics/queryTotleVideoCourseForSchool")
    @ResponseBody
    public JSONObject queryTotleVideoCourseForSchool(HttpServletRequest request, String startTime, String endTime, String eduArea, String eduSchoolStep) throws Exception {
        JSONObject jsonObject = new JSONObject();
        Users loginUser = WebUtils.getCurrentUser(request);
        if(loginUser==null || loginUser.getId()==null){
            throw new Exception("数据出现异常，请联系管理员！");
        }

        Map<String, Object> papamMap = new HashMap<String, Object>();
        papamMap.put("startTime", startTime);
        papamMap.put("endTime", endTime);
        papamMap.put("eduArea", eduArea);
        papamMap.put("eduSchoolStep", eduSchoolStep);
        papamMap.put("companyId",WebUtils.getCurrentCompanyId());
        //查询区域的录播观看人数
        List<Map<String, Object>> schoolVideoList = sysPlayLogsServiceImpl.queryTotleVideoCourseForSchool(papamMap);

        jsonObject.put("schoolVideoList", schoolVideoList);
        return jsonObject;
    }

    /**
     * 点播统计-概况
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/statistics/queryTotleSchoolStep")
    @ResponseBody
    public JSONObject queryTotleSchoolStep(HttpServletRequest request, String startTime, String endTime, String eduArea) throws Exception {
        JSONObject jsonObject = new JSONObject();
        Users loginUser = WebUtils.getCurrentUser(request);
        if(loginUser==null || loginUser.getId()==null){
            throw new Exception("数据出现异常，请联系管理员！");
        }

        //查询区域的录播观看人数
        Map<String, Object> papamMap = new HashMap<String, Object>();
        papamMap.put("startTime", startTime);
        papamMap.put("endTime", endTime);
        papamMap.put("eduArea", eduArea);
        papamMap.put("companyId",WebUtils.getCurrentCompanyId());
        List<Map<String, Object>> schoolStepList = sysPlayLogsServiceImpl.queryTotleSchoolStep(papamMap);

        jsonObject.put("schoolStepList", schoolStepList);
        return jsonObject;
    }

    /**
     * 查询点播量前五
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/statistics/queryTopSchoolView")
    @ResponseBody
    public JSONObject queryTopSchoolView(HttpServletRequest request, String startTime, String endTime, String eduArea) throws Exception {
        JSONObject jsonObject = new JSONObject();
        Users loginUser = WebUtils.getCurrentUser(request);
        if(loginUser==null || loginUser.getId()==null){
            throw new Exception("数据出现异常，请联系管理员！");
        }

        //查询区域的录播观看人数
        Map<String, Object> papamMap = new HashMap<String, Object>();
        papamMap.put("startTime", startTime);
        papamMap.put("endTime", endTime);
        papamMap.put("eduArea", eduArea);
        papamMap.put("pageSize", 5);
        papamMap.put("companyId",WebUtils.getCurrentCompanyId());
        List<Map<String, Object>> schoolViewList = sysPlayLogsServiceImpl.queryTopSchoolView(papamMap);

        jsonObject.put("schoolViewList", schoolViewList);
        return jsonObject;
    }


    /**
     * 查询点播量前五
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/statistics/queryTopSubjectView")
    @ResponseBody
    public JSONObject queryTopSubjectView(HttpServletRequest request, String startTime, String endTime, String eduArea) throws Exception {
        JSONObject jsonObject = new JSONObject();
        Users loginUser = WebUtils.getCurrentUser(request);
        if(loginUser==null || loginUser.getId()==null){
            throw new Exception("数据出现异常，请联系管理员！");
        }

        Map<String, Object> papamMap = new HashMap<String, Object>();
        papamMap.put("startTime", startTime);
        papamMap.put("endTime", endTime);
        papamMap.put("eduArea", eduArea);
        papamMap.put("pageSize", 5);
        papamMap.put("companyId",WebUtils.getCurrentCompanyId());
        List<SysConfigItemRelation> itemList = sysConfigItemRelationServiceImpl.findItemFrontByLevel(2,WebUtils.getCurrentCompanyId());
        List<Map<String, Object>> subjectTotleList = new ArrayList<Map<String, Object>>();
        Map<String, Object> subjectTotleMap;
        for(SysConfigItemRelation item : itemList){
            //查询学科的录播观看人数
            papamMap.put("subjectCode", item.getItemCode());
            List<Map<String, Object>> subjectViewList = sysPlayLogsServiceImpl.queryTopSubjectView(papamMap);
            subjectTotleMap = new HashMap<String, Object>();
            subjectTotleMap.put(item.getItemName(), subjectViewList);
            subjectTotleList.add(subjectTotleMap);
        }

        jsonObject.put("subjectTotleList", subjectTotleList);
        return jsonObject;
    }

    /**
     * 点播统计-详情
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/statistics/videoCourseDetail")
    public String videoCourseDetail(Model model, HttpServletRequest request) throws Exception {
        Users loginUser = WebUtils.getCurrentUser(request);
        if(loginUser==null || loginUser.getId()==null){
            throw new Exception("数据出现异常，请联系管理员！");
        }

        //计算时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        String endTime = sdf.format(cal.getTime());
        model.addAttribute("endTime" ,endTime);
        cal.add(Calendar.DAY_OF_MONTH, -6);
        String startTime = sdf.format(cal.getTime());
        model.addAttribute("startTime" ,startTime);

        //学校所属学科
        List<SysConfigItemRelation> subjectItem = sysConfigItemRelationServiceImpl.findItemFrontByLevel(2,WebUtils.getCurrentCompanyId());//查询学科
        model.addAttribute("subjectItem", subjectItem);

        //学校所属学段
        List<SysConfigItemRelation> stepItem = sysConfigItemRelationServiceImpl.findItemFrontByLevel(1,WebUtils.getCurrentCompanyId());//查询学段
        model.addAttribute("stepItem", stepItem);

        return "/queVideo/videoCourseDetail";
    }

    /**
     * 查询单个视频详情
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/statistics/queryVideoCourseDetail")
    @ResponseBody
    public JSONObject queryVideoCourseDetail(HttpServletRequest request, String startTime, String endTime,Integer classId, String className) throws Exception {
        JSONObject jsonObject = new JSONObject();
        Users loginUser = WebUtils.getCurrentUser(request);
        if(loginUser==null || loginUser.getId()==null){
            throw new Exception("数据出现异常，请联系管理员！");
        }

        Map<String, Object> papamMap = new HashMap<String, Object>();
        papamMap.put("startTime", startTime);
        papamMap.put("endTime", endTime);
        papamMap.put("classId", classId);
        papamMap.put("className", className);
        Map<String, Object> videoDetail = sysPlayLogsServiceImpl.queryVideoCourseDetail(papamMap);
        jsonObject.put("videoDetail", videoDetail);
        if(videoDetail!=null){
            List<Map<String, Object>> deviceList = sysPlayLogsServiceImpl.queryDeviceDetail(papamMap);
            int pcNum = 0;int totleNum = 0;
            for(Map<String, Object> deviceMap : deviceList){
                if(deviceMap.get("device")!=null && "PC".equals(deviceMap.get("device"))){
                    pcNum += deviceMap.get("deviceNum")!=null ? Integer.valueOf(deviceMap.get("deviceNum").toString()):0;
                }
                totleNum += deviceMap.get("deviceNum")!=null ? Integer.valueOf(deviceMap.get("deviceNum").toString()):0;
            }

            jsonObject.put("pcNum", pcNum);
            jsonObject.put("otherNum", totleNum - pcNum);
            // 创建一个数值格式化对象
            NumberFormat numberFormat = NumberFormat.getInstance();
            // 设置精确到小数点后2位
            numberFormat.setMaximumFractionDigits(2);
            String pcRate = numberFormat.format((float) pcNum / (float) totleNum * 100);
            jsonObject.put("pcRate", pcRate);
            jsonObject.put("otherRate", numberFormat.format((float) (totleNum-pcNum) / (float) totleNum * 100));
        }
        return jsonObject;
    }

    /**
     * 查看视频的播放热点
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/statistics/queryVideoCourseHourly")
    @ResponseBody
    public JSONObject queryVideoCourseHourly(HttpServletRequest request, String startTime, String endTime,Integer classId, String className) throws Exception {
        JSONObject jsonObject = new JSONObject();
        Users loginUser = WebUtils.getCurrentUser(request);
        if(loginUser==null || loginUser.getId()==null){
            throw new Exception("数据出现异常，请联系管理员！");
        }

        Map<String, Object> papamMap = new HashMap<String, Object>();
        papamMap.put("classId", classId);
        papamMap.put("className", className);
        Map<String, Object> video = sysPlayLogsServiceImpl.queryVideo(papamMap);

        if(video!=null){
            CompanyPayConfig companyPayConfig = companyPayConfigServiceImpl.findByCompanyId(loginUser.getCompanyId());
            long nowtime = System.currentTimeMillis()/1000L;
            StringBuffer a = new StringBuffer("date="+ startTime);
//            a.append("&start_date=" + startTime);
            a.append("&userid=" + companyPayConfig.getCcUserId());
            a.append("&videoid=" + video.get("video_cc_id"));
            a.append("&time="+nowtime);
            String infoUrl = a.toString();
            a.append("&salt=" + companyPayConfig.getCcApiKey());
            System.out.println(MD5.getMD5(a.toString()));
            infoUrl+="&hash="+MD5.getMD5(a.toString());
            // 创建一个数值格式化对象
            String result = HttpPostRequest.get(CCVideoConstant.CC_VIDEO_DAILY + infoUrl);
            System.out.println("请求地址：" + CCVideoConstant.CC_VIDEO_DAILY + infoUrl);
            System.out.println("接口返回参数："+ result);
            if(StringUtils.isNotBlank(result)){
                Map<String, Object> map = JSONObject.parseObject(result, Map.class);
                if(map.get("play_counts")!=null){
                    jsonObject.put("play_counts", map.get("play_counts"));
                }
            }
        }
        return jsonObject;
    }


    /**
     * 查看视频的播放比例
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/statistics/queryVideoCourseDaily")
    @ResponseBody
    public JSONObject queryVideoCourseDaily(HttpServletRequest request, String startTime, String endTime,Integer classId, String className) throws Exception {
        JSONObject jsonObject = new JSONObject();
        Users loginUser = WebUtils.getCurrentUser(request);
        if(loginUser==null || loginUser.getId()==null){
            throw new Exception("数据出现异常，请联系管理员！");
        }

        if(classId == null && StringUtils.isBlank(className)){
            CompanyPayConfig companyPayConfig = companyPayConfigServiceImpl.findByCompanyId(loginUser.getCompanyId());
            long nowtime = System.currentTimeMillis()/1000L;
            StringBuffer a = new StringBuffer("end_date="+ endTime);
            a.append("&start_date=" + startTime);
            a.append("&userid=" + companyPayConfig.getCcUserId());
            a.append("&time="+nowtime);
            String infoUrl = a.toString();
            a.append("&salt=" + companyPayConfig.getCcApiKey());
            System.out.println(MD5.getMD5(a.toString()));
            infoUrl+="&hash="+MD5.getMD5(a.toString());
            // 创建一个数值格式化对象
            String result = HttpPostRequest.get(CCVideoConstant.CC_ATTENTION_VIDEO_USER_DAILY + infoUrl);
            System.out.println("请求地址：" + CCVideoConstant.CC_ATTENTION_VIDEO_USER_DAILY + infoUrl);
            System.out.println("接口返回参数："+ result);
            if(StringUtils.isNotBlank(result)){
                JSONObject resultJson = JSONObject.parseObject(result);
                if(resultJson.get("attentions")!=null){
                    resultJson = (JSONObject) resultJson.get("attentions");
                    if(resultJson.get("attention")!=null){
                        List<Map<String, Object>>list = (List<Map<String, Object>>) resultJson.get("attention");
                        Collections.sort(list, new Comparator<Map<String, Object>>(){
                            /*
                             * int compare(Student o1, Student o2) 返回一个基本类型的整型，
                             * 返回负数表示：o1 小于o2，
                             * 返回0 表示：o1和o2相等，
                             * 返回正数表示：o1大于o2。
                             */
                            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                                String o1section = (String) o1.get("section");
                                String o2section = (String) o2.get("section");
                                return o1section.compareToIgnoreCase(o2section);
                            }
                        });
                        System.out.println("排序后："+list);
                        jsonObject.put("attentions", list);
                    }
                }
            }
        }else{
            Map<String, Object> papamMap = new HashMap<String, Object>();
            papamMap.put("classId", classId);
            papamMap.put("className",className);
            Map<String, Object> video = sysPlayLogsServiceImpl.queryVideo(papamMap);
            if(video!=null){
                CompanyPayConfig companyPayConfig = companyPayConfigServiceImpl.findByCompanyId(loginUser.getCompanyId());
                long nowtime = System.currentTimeMillis()/1000L;
                StringBuffer a = new StringBuffer("end_date="+ endTime);
                a.append("&start_date=" + startTime);
                a.append("&userid=" + companyPayConfig.getCcUserId());
                a.append("&videoid=" + video.get("video_cc_id"));
                a.append("&time="+nowtime);
                String infoUrl = a.toString();
                a.append("&salt=" + companyPayConfig.getCcApiKey());
                System.out.println(MD5.getMD5(a.toString()));
                infoUrl+="&hash="+MD5.getMD5(a.toString());
                // 创建一个数值格式化对象
                String result = HttpPostRequest.get(CCVideoConstant.CC_ATTENTION_VIDEO_DAILY + infoUrl);
                System.out.println("请求地址：" + CCVideoConstant.CC_ATTENTION_VIDEO_DAILY + infoUrl);
                System.out.println("接口返回参数："+ result);
                if(StringUtils.isNotBlank(result)){
                    JSONObject resultJson = JSONObject.parseObject(result);
                    if(resultJson.get("attentions")!=null){
                        resultJson = (JSONObject) resultJson.get("attentions");
                        if(resultJson.get("attention")!=null){
                            List<Map<String, Object>>list = (List<Map<String, Object>>) resultJson.get("attention");
                            Collections.sort(list, new Comparator<Map<String, Object>>(){
                                /*
                                 * int compare(Student o1, Student o2) 返回一个基本类型的整型，
                                 * 返回负数表示：o1 小于o2，
                                 * 返回0 表示：o1和o2相等，
                                 * 返回正数表示：o1大于o2。
                                 */
                                public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                                    String o1section = (String) o1.get("section");
                                    String o2section = (String) o2.get("section");
                                    return o1section.compareToIgnoreCase(o2section);
                                }
                            });
                            System.out.println("排序后："+list);
                            jsonObject.put("attentions", list);
                        }
                    }
                }
            }
        }

        return jsonObject;
    }


    /**
     * 点播统计-概况导出
     * @param model
     * @return
     */
    @RequestMapping(value = "/exportVideoCourseIndexExcle")
    public ModelAndView exportVideoCourseIndexExcle(Model model, String startTime, String endTime) {
        List<UserVideoVo> al = new ArrayList<UserVideoVo>();
        //查询区域的录播观看人数
        Map<String, Object> papamMap = new HashMap<String, Object>();
        papamMap.put("startTime", startTime);
        papamMap.put("endTime", endTime);
        papamMap.put("pageSize", 20000);
        List<Map<String, Object>> totleVideoList = sysPlayLogsServiceImpl.queryTotleVideo(papamMap);

        List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> map : totleVideoList) {
            map.put("eduArea", map.get("edu_area"));
            map.put("eduSchool", map.get("edu_school"));
            map.put("eduStep", map.get("edu_step"));
            map.put("eduSubject", map.get("edu_subject"));
            map.put("totleStudy", map.get("totle_study"));
            map.put("totleStudyLength", map.get("totle_study_length"));
            map.put("studyRate", map.get("study_rate")==null?"":map.get("study_rate")+"%");
            map.put("viewNum", map.get("view_num"));
            lists.add(map);
        }
        StringBuffer title = new StringBuffer(
                "区域:eduArea,学校:eduSchool,学段:eduStep,学科:eduSubject,总播放量:totleStudy,总播放时长:totleStudyLength,播完率:studyRate,观看人数:viewNum");
        ViewFiles excel = new ViewFiles();
        HSSFWorkbook wb = new HSSFWorkbook();
        try {
            wb = ExcelUtil.newWorkbook(lists, "sheet1", title.toString());
        } catch (Exception ex) {

        }
        Map map = new HashMap();
        map.put("workbook", wb);
        map.put("fileName", "区域用户点播统计.xls");
        return new ModelAndView(excel, map);
    }

    /**
     * 点播统计-概况导出
     * @param model
     * @return
     */
    @RequestMapping(value = "/exportVideoCourseIndexExcle1")
    public ModelAndView exportVideoCourseIndexExcle1(Model model, String startTime, String endTime) {
        List<UserVideoVo> al = new ArrayList<UserVideoVo>();
        //查询区域的录播观看人数
        Map<String, Object> papamMap = new HashMap<String, Object>();
        papamMap.put("startTime", startTime);
        papamMap.put("endTime", endTime);
        List<Map<String, Object>> totleVideoList = sysPlayLogsServiceImpl.queryTotleVideoCourse1(papamMap);
        String[] areas = new String[]{"高新区","锦江区","青羊区","金牛区","武侯区","成华区","龙泉驿区","青白江区","新都区","温江区","双流区",
                "郫都区","金堂县","大邑县","蒲江县","新津县","天府新区","都江堰市","彭州市","邛崃市","崇州市","简阳市"};
        List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
        //遍历填充区域的值
        Map<String, Object> zeroMap;
        for(int i=0; i<areas.length; i++){
            int num = 0;
            for (int j=0; j<totleVideoList.size(); j++) {
                zeroMap = totleVideoList.get(j);
                if(areas[i].equals(zeroMap.get("areaName"))){
                    try{
                        zeroMap.put("studyLength", secToTime(Integer.valueOf(zeroMap.get("studyLength").toString())));
                        lists.add(zeroMap);
                        totleVideoList.remove(zeroMap);
                        j--;
                        num ++;
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
            if(num == 0){
                zeroMap = new HashMap<String, Object>();
                zeroMap.put("areaName", areas[i]);
                zeroMap.put("userNum", "0");
                zeroMap.put("studyLength", "00:00:00");
                lists.add(zeroMap);
            }
        }

        StringBuffer title = new StringBuffer(
                "区域:areaName,点播观看总人次:userNum,点播观看总时长:studyLength");
        ViewFiles excel = new ViewFiles();
        HSSFWorkbook wb = new HSSFWorkbook();
        try {
            wb = ExcelUtil.newWorkbook(lists, "sheet1", title.toString());
        } catch (Exception ex) {

        }
        Map map = new HashMap();
        map.put("workbook", wb);
        map.put("fileName", "区域用户点播统计.xls");
        return new ModelAndView(excel, map);
    }

    private String secToTime(Integer time){
        StringBuffer timeStr = new StringBuffer();
        int h=time/3600;
        if(h < 10){
            timeStr.append("0");
        }
        timeStr.append(h+":");
        int m=(time%3600)/60;
        if(m < 10){
            timeStr.append("0");
        }
        timeStr.append(m+":");
        int s=(time%3600)%60;
        if(s < 10){
            timeStr.append("0");
        }
        timeStr.append(s);
        return timeStr.toString();
    }

    /**
     * 单个点播详情
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/statistics/videoDetail/{id}/{lable}")
    public String videoDetail(Model model, HttpServletRequest request, @PathVariable Integer id, @PathVariable String lable) throws Exception {
        Users loginUser = WebUtils.getCurrentUser(request);
        if(loginUser==null || loginUser.getId()==null){
            throw new Exception("数据出现异常，请联系管理员！");
        }
        //根据班型id查询详情
        Map<String, String> map = new HashMap<String, String>();
        map.put("classId", "" + id);
        ClassTypeVo classType = classTypeServiceImpl.findClassTypeDetail(map);

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

        return "/queVideo/videoDetail";
    }


    /**
     *
     * Class Name: StudentController.java
     *
     * @Description: 查询学员列表数据
     * @author zhang.zx
     * @date 2015年9月29日 下午4:46:54
     * @modifier
     * @modify-date 2015年9月29日 下午4:46:54
     * @version 1.0
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryVideoListForSchool")
    public PageFinder<VideoCourseVo> queryVideoListForSchool(VideoCourseVo videoCourseVo) {
        videoCourseVo.setCompanyId(WebUtils.getCurrentCompanyId());
        // 分页调整
        if (videoCourseVo.getPageSize() == null) {
            videoCourseVo.setPageSize(10);
        }

        PageFinder<VideoCourseVo> pageFinder = sysPlayLogsServiceImpl.queryVideoListForSchool(videoCourseVo);
        return pageFinder;
    }

    /**
     *
     * Class Name: StudentController.java
     *
     * @Description: 查询学员列表数据
     * @author zhang.zx
     * @date 2015年9月29日 下午4:46:54
     * @modifier
     * @modify-date 2015年9月29日 下午4:46:54
     * @version 1.0
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryVideoTotleForSchool")
    public JSONObject queryVideoTotleForSchool(VideoCourseVo videoCourseVo,HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        Map<String, Object> papamMap = new HashMap<String, Object>();
        papamMap.put("startTime", videoCourseVo.getStartTime());
        papamMap.put("endTime", videoCourseVo.getEndTime());
        papamMap.put("eduArea", videoCourseVo.getEduArea());
        papamMap.put("eduSchool", videoCourseVo.getEduSchool());
        
        Users user = WebUtils.getCurrentUser();
        UsersAreaRelation uersAreaRelation = null;
        Subject subject = SecurityUtils.getSubject();
        if( subject.hasRole("班主任")){
        	uersAreaRelation=usersServiceImpl.findUsersAreaRelationT(user.getId(),WebUtils.getCurrentCompany().getEduAreaSchool());
        	//获取班主任任教班级
        	EduMasterClass etc=new EduMasterClass();
        	etc.setUserId(String.valueOf(user.getId()));
        	papamMap.put("eduArea", uersAreaRelation.getEduArea());
            papamMap.put("eduSchool", uersAreaRelation.getEduSchool());
 			List<EduMasterClass> list=studentServiceImpl.findClassByTeacherId(etc,WebUtils.getCurrentCompany().getEduAreaSchool());
 			papamMap.put("eduMasterClass",list);
        }else if(subject.hasRole("任课老师")){
        	uersAreaRelation=usersServiceImpl.findUsersAreaRelationR(user.getId(),WebUtils.getCurrentCompany().getEduAreaSchool());
        	//获取任课教师任教班级
        	EduMasterClass ets =new EduMasterClass();
        	ets.setUserId(String.valueOf(WebUtils.getCurrentUserId(request)));
    		ets.setEduSchool(WebUtils.getCurrentCompany().getEduAreaSchool());
    		ets.setEduClass("1");
    		List<EduMasterClass> eduClassGLY=studentServiceImpl.findSubjectClassByTeacherId(ets);
    		papamMap.put("eduMasterClass",eduClassGLY);
    		papamMap.put("eduArea", uersAreaRelation.getEduArea());
            papamMap.put("eduSchool", uersAreaRelation.getEduSchool());
        }
        
        //查询学校的录播观看人数
        papamMap.put("companyId",WebUtils.getCurrentCompanyId());
        Integer userNum = sysPlayLogsServiceImpl.queryTotleUserVideoNum(papamMap);
        jsonObject.put("userNum", userNum);

        //总计观看点播时长+人次
        Map<String, Object> totleVideo = sysPlayLogsServiceImpl.queryTotleStudyLengthAndPersonNum(papamMap);
        jsonObject.put("totleVideo",totleVideo);
        return jsonObject;
    }

    /**
     * 初始化历史播放记录
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/installSysPlayLogs")
    public JSONObject installSysPlayLogs() {
        JSONObject jsonObject = new JSONObject();
        //查询所有的播放记录
        List<Map<String, Object>> hisList = sysPlayLogsServiceImpl.queryHistoryAll();
        for(Map<String, Object> hisMap:hisList){
            fixedThreadPool.submit(new RuntimeStatistics(hisMap));
        }

        jsonObject.put("result","初始化历史记录信息：总条数["+hisList.size()+"]");
        return jsonObject;
    }

    public class RuntimeStatistics implements Runnable{
        private Map<String, Object> hisMap = null;
        public RuntimeStatistics(Map<String, Object> hisMap){
            this.hisMap = hisMap;
        }
        @Override
        public void run(){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                UserHistoryAllVo allVo = new UserHistoryAllVo();
                allVo.setUserId(Integer.valueOf(hisMap.get("user_id").toString()));
                allVo.setCommodityId(Integer.valueOf(hisMap.get("commodity_id").toString()));
                allVo.setClassTypeId(Integer.valueOf(hisMap.get("class_type_id").toString()));
                allVo.setLectureId(Integer.valueOf(hisMap.get("lecture_id").toString()));
                allVo.setStudyTime(sdf.parse(hisMap.get("study_time").toString()));
                Random rd = new Random();
                Integer video_time = rd.nextInt(Integer.valueOf(hisMap.get("video_time").toString()));
                allVo.setStudyLength(video_time);
                int device = rd.nextInt(100);
                if(device>76){
                    allVo.setDevice("PC");
                }else{
                    allVo.setDevice("Mobile");
                }
                userHistoryServiceImpl.insertPlayLogs(allVo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     *
     * @Description: 统计信息导出学员数据
     * @param model
     * @return
     */
    @RequestMapping(value = "/exportVideoExcle")
    public ModelAndView exportVideoExcle(Model model, String startTime, String endTime) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("companyId", WebUtils.getCurrentCompanyId());
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("type", "microClass");
        List<VideoCourseVo> videoCourseVoList = sysPlayLogsServiceImpl.queryTotleVideoCourseAll(map);//查询微课
        map.put("type", "video");
        List<VideoCourseVo> videoCourseVoList1 = sysPlayLogsServiceImpl.queryTotleVideoCourseAll(map);//查询回看
        StringBuffer colNames = new StringBuffer(
                "年级:stepName,学科:subjectName,教师:teaName,课程名称:courseName,累计点播次数:viewNum");
        ViewFiles excel = new ViewFiles();
        HSSFWorkbook wb = new HSSFWorkbook();
        try {
            List<ExcelSheetEntity> sheets = new ArrayList<ExcelSheetEntity>();
            ExcelSheetEntity entity = ExcelSheetEntity.newInstance("回放", colNames.toString(), videoCourseVoList1);
            ExcelSheetEntity entity2 = ExcelSheetEntity.newInstance("微课", colNames.toString(), videoCourseVoList);

            sheets.add(entity);
            sheets.add(entity2);
            wb = ExcelUtil.newWorkbook(sheets);
        } catch (Exception ex) {

        }
        map.put("workbook", wb);
        map.put("fileName", "视频观看记录表.xls");
        return new ModelAndView(excel, map);
    }

}
