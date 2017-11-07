package com.yuxin.wx.controller.query;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.classes.IClassTypeService;
import com.yuxin.wx.api.company.ICompanyFunctionSetService;
import com.yuxin.wx.api.company.ICompanyPayConfigService;
import com.yuxin.wx.api.course.IVideoService;
import com.yuxin.wx.api.query.IStudentStatisticsService;
import com.yuxin.wx.api.query.ISysPlayLogsService;
import com.yuxin.wx.api.student.IStudentService;
import com.yuxin.wx.api.system.ISysConfigDictService;
import com.yuxin.wx.api.system.ISysConfigItemRelationService;
import com.yuxin.wx.api.system.ISysConfigItemService;
import com.yuxin.wx.api.user.IUsersService;
import com.yuxin.wx.common.CCVideoConstant;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.common.PageFinder2;
import com.yuxin.wx.common.ViewFiles;
import com.yuxin.wx.model.classes.ClassType;
import com.yuxin.wx.model.company.CompanyFunctionSet;
import com.yuxin.wx.model.company.CompanyPayConfig;
import com.yuxin.wx.model.system.SysConfigDict;
import com.yuxin.wx.model.system.SysConfigItem;
import com.yuxin.wx.model.system.SysConfigItemRelation;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.model.watchInfo.WatchInfoResult;
import com.yuxin.wx.utils.*;
import com.yuxin.wx.vo.classes.ClassTypeVo;
import com.yuxin.wx.vo.course.UserVideoVo;
import com.yuxin.wx.vo.course.VideoCourseVo;
import com.yuxin.wx.vo.student.StudentListVo;
import com.yuxin.wx.vo.user.UsersAreaRelation;
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

import javax.servlet.http.HttpServletRequest;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/query")
public class StudentStatisticsController {
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
        stepDict.setDictCode("EDU_STEP_NEW");
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
        if(!subject.hasRole("学校负责人")){
            if(StringUtils.isBlank(eduStep) || "null".equals(eduStep)){
                return null;
            }
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
        stepDict.setDictCode("EDU_STEP_NEW");
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

        CompanyFunctionSet userorg_roleopenflag = WebUtils.getFunctionSet("USERORG_ROLEOPENFLAG");
        model.addAttribute("userorg_roleopenflag", userorg_roleopenflag==null?0:userorg_roleopenflag.getStatus());
        return "/query/query_student_area";
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
        return "/query/query_student_org";
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
        UsersAreaRelation uersAreaRelation = usersServiceImpl.findUsersAreaRelation(loginUser.getId());
        if(uersAreaRelation==null){
            throw new Exception("数据出现异常，请联系管理员！");
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
            search.setPageSize(50000);
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
     *
     * @Description: 统计信息导出学员数据
     * @param model
     * @param search
     * @return
     */
    @RequestMapping(value = "/exportExcleSchool")
    public ModelAndView exportExcleSchool(Model model, StudentListVo search) {
        List<StudentListVo> al = new ArrayList<StudentListVo>();
        if (EntityUtil.isNotBlank(search)) {
            search.setCompanyId(WebUtils.getCurrentCompanyId());
            // search.setSchoolId(WebUtils.getCurrentSchoolId());
            search.setPageSize(50000);
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
        Users user = WebUtils.getCurrentUser();
        UsersAreaRelation uersAreaRelation = usersServiceImpl.findUsersAreaRelation(user.getId());
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("学校负责人")) {

        }else if(subject.hasRole("教科院")){

        }else if(subject.hasRole("区县负责人")){
            model.addAttribute("isArea",true);
            SysConfigDict  search = new SysConfigDict();
            search.setDictCode("EDU_STEP_NEW");
            model.addAttribute("eduStep",sysConfigDictServiceImpl.findByDicCode("EDU_STEP_NEW"));
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
        Users user = WebUtils.getCurrentUser();
        UsersAreaRelation uersAreaRelation = usersServiceImpl.findUsersAreaRelation(user.getId());

        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("学校负责人")) {
            map.put("schoolId",uersAreaRelation.getEduSchool());
            map.put("groupBy","edu_year");
        }else if(subject.hasRole("教科院")){
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
        Users user = WebUtils.getCurrentUser();
        UsersAreaRelation uersAreaRelation = usersServiceImpl.findUsersAreaRelation(user.getId());

        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("学校负责人")) {
            map.put("schoolId",uersAreaRelation.getEduSchool());
            map.put("groupBy","edu_year");
        }else if(subject.hasRole("教科院")){
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
                map.put("schoolId",uersAreaRelation.getEduSchool());
                map.put("groupBy","edu_year");
            }else if(subject.hasRole("教科院")){
                // map.put("areaId",uersAreaRelation.getE);
                map.put("groupBy","edu_area");
            }else if(subject.hasRole("区县负责人")){
                map.put("areaId",uersAreaRelation.getEduArea());
                map.put("groupBy","edu_school");
            }
        }
        map.put("endDate",endDate);
        map.put("startDate",startDate);
        List<Map> index = studentStatisticsServiceImpl.getWatchInfoIndex(map);
        List<Map> all =   studentStatisticsServiceImpl.getWatchInfoAll(map);
        Map<String,Object> result = new HashMap<>();
        if(index.size()>0){
            result.put("index",index.get(0).get("times"));
        }else{
            result.put("index",0);
        }
        if(all.size()>0){
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


        Users user = WebUtils.getCurrentUser();
        UsersAreaRelation uersAreaRelation = usersServiceImpl.findUsersAreaRelation(user.getId());
        if(uersAreaRelation==null){
//            map.put("groupBy","edu_area");
            model.addAttribute("role","all");
        }else{

            Subject subject = SecurityUtils.getSubject();
            if(subject.hasRole("学校负责人")) {
//                map.put("schoolId",uersAreaRelation.getEduSchool());
//                map.put("groupBy","edu_year");
                model.addAttribute("role","school");
                model.addAttribute("school",uersAreaRelation.getEduSchool());

            }else if(subject.hasRole("教科院")){
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
            }
        }

        return "/query/query_student_watchList";
    }
    //查询直播统计
    @ResponseBody
    @RequestMapping(value = "/statistics/queryStudentsWatchInfoList")
    public Map queryStudentsWatchInfoList(WatchInfoResult search) {
        String flag = "";
        //search.setCompanyId(WebUtils.getCurrentCompanyId());
        // 分页调整
        search.setPageSize(10);
        Integer total = studentStatisticsServiceImpl.totalPayMasterCount(search);
        PageFinder2<WatchInfoResult> pageFinder = studentStatisticsServiceImpl.queryStudentsWatchInfoList(search);
        Map<String,Object> map = new HashMap<>();
        map.put("total",total);
        map.put("pageFinder",pageFinder);
        return map;
    }
    //查询直播统计
//    @ResponseBody
//    @RequestMapping(value = "/statistics/totalPayMasterCount")
//    public Integer totalPayMasterCount(WatchInfoResult search) {
//        Integer total = studentStatisticsServiceImpl.totalPayMasterCount(search);
//        return total;
//    }

    /**
     * 用户点播统计
     * @param model
     * @return
     */
    @RequestMapping(value = "/exportUserWatchExcle")
    public ModelAndView exportUserWatchExcle(Model model, WatchInfoResult search) {
        List<WatchInfoResult> list = new ArrayList<>();
        if (EntityUtil.isNotBlank(search)) {
            //userVideoVo.setCompanyId(WebUtils.getCurrentCompanyId());
            search.setPageSize(50000);
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
                map.put("eduClass", v.getStudyTime());//班级
            }else if(subject.hasRole("教科院")){
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
//            map.put("stepName", v.getEduStep());//学段
//            map.put("eduYear", v.getEduYear());//入学年份
//            map.put("eduClass", v.getEduClass());//班级
            map.put("times", v.getTimes());//观看次数
            map.put("studyTime", v.getStudyTime());//观看时长
            lists.add(map);
        }
        String titles = "";
        if(subject.hasRole("学校负责人")) {
            titles = "课程名称:className,课次名称:lessonName,用户名:userName,学员名称:studentName,学段:stepName,班级:eduClass,观看累计次数:times,观看累计时长:studyTime";
        }else if(subject.hasRole("教科院")){
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
        List<SysConfigItemRelation> subjectItem = sysConfigItemRelationServiceImpl.findItemFrontByLevel(2);//查询学科
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
        model.addAttribute("ednTime" ,sdf.format(cal.getTime()));
        cal.add(Calendar.DAY_OF_MONTH, -6);
        model.addAttribute("startTime" ,sdf.format(cal.getTime()));
        return "/queVideo/queryUserVideoList";
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
    public PageFinder<UserVideoVo> queryUserVideoList(UserVideoVo userVideoVo) {
        userVideoVo.setCompanyId(WebUtils.getCurrentCompanyId());
        // 分页调整
        if (userVideoVo.getPageSize() == null) {
            userVideoVo.setPageSize(10);
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
            videoCourseVo.setPageSize(50000);
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
            userVideoVo.setPageSize(50000);
            al = sysPlayLogsServiceImpl.queryUserVideoList(userVideoVo);
        }
        List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
        for (UserVideoVo v : al) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("time", userVideoVo.getStartTime() + "至" + userVideoVo.getEndTime());
            map.put("areaName", v.getAreaName());
            map.put("schoolName", v.getSchoolName());
            map.put("stepName", v.getStepName());
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
        Integer totleNum = sysPlayLogsServiceImpl.queryTotleUserVideoNum(papamMap);
        model.addAttribute("totleNum", totleNum);

        return "/queVideo/videoCourseIndex";
    }


    /**
     * 点播统计-概况
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/statistics/queryTotleVideoCourse")
    @ResponseBody
    public JSONObject queryTotleVideoCourse(HttpServletRequest request, String startTime, String endTime) throws Exception {
        JSONObject jsonObject = new JSONObject();
        Users loginUser = WebUtils.getCurrentUser(request);
        if(loginUser==null || loginUser.getId()==null){
            throw new Exception("数据出现异常，请联系管理员！");
        }

        Map<String, Object> papamMap = new HashMap<String, Object>();
        papamMap.put("startTime", startTime);
        papamMap.put("endTime", endTime);
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
    @RequestMapping(value="/statistics/queryTotleSchoolStep")
    @ResponseBody
    public JSONObject queryTotleSchoolStep(HttpServletRequest request, String startTime, String endTime) throws Exception {
        JSONObject jsonObject = new JSONObject();
        Users loginUser = WebUtils.getCurrentUser(request);
        if(loginUser==null || loginUser.getId()==null){
            throw new Exception("数据出现异常，请联系管理员！");
        }

        //查询区域的录播观看人数
        Map<String, Object> papamMap = new HashMap<String, Object>();
        papamMap.put("startTime", startTime);
        papamMap.put("endTime", endTime);
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
    public JSONObject queryTopSchoolView(HttpServletRequest request, String startTime, String endTime) throws Exception {
        JSONObject jsonObject = new JSONObject();
        Users loginUser = WebUtils.getCurrentUser(request);
        if(loginUser==null || loginUser.getId()==null){
            throw new Exception("数据出现异常，请联系管理员！");
        }

        //查询区域的录播观看人数
        Map<String, Object> papamMap = new HashMap<String, Object>();
        papamMap.put("startTime", startTime);
        papamMap.put("endTime", endTime);
        papamMap.put("pageSize", 5);
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
    public JSONObject queryTopSubjectView(HttpServletRequest request, String startTime, String endTime) throws Exception {
        JSONObject jsonObject = new JSONObject();
        Users loginUser = WebUtils.getCurrentUser(request);
        if(loginUser==null || loginUser.getId()==null){
            throw new Exception("数据出现异常，请联系管理员！");
        }

        Map<String, Object> papamMap = new HashMap<String, Object>();
        papamMap.put("startTime", startTime);
        papamMap.put("endTime", endTime);
        papamMap.put("pageSize", 5);
        List<SysConfigItemRelation> itemList = sysConfigItemRelationServiceImpl.findItemFrontByLevel(2);
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
        List<SysConfigItemRelation> subjectItem = sysConfigItemRelationServiceImpl.findItemFrontByLevel(2);//查询学科
        model.addAttribute("subjectItem", subjectItem);

        //学校所属学段
        List<SysConfigItemRelation> stepItem = sysConfigItemRelationServiceImpl.findItemFrontByLevel(1);//查询学段
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
            papamMap.put("className", className);
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
    @RequestMapping(value = "/exportVideoExcle")
    public ModelAndView exportVideoExcle(Model model, String startTime, String endTime) {
        List<UserVideoVo> al = new ArrayList<UserVideoVo>();
        //查询区域的录播观看人数
        Map<String, Object> papamMap = new HashMap<String, Object>();
        papamMap.put("startTime", startTime);
        papamMap.put("endTime", endTime);
        papamMap.put("pageSize", 50000);
        List<Map<String, Object>> totleVideoList = sysPlayLogsServiceImpl.queryTotleVideo(papamMap);

        List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> map : totleVideoList) {
            map.put("eduArea", map.get("edu_area"));
            map.put("eduSchool", map.get("edu_school"));
            map.put("eduStep", map.get("edu_step"));
            map.put("eduSubject", map.get("edu_subject"));
            map.put("totleStudy", map.get("totle_study"));
            map.put("totleStudyLength", map.get("totle_study_length"));
            map.put("studyRate", map.get("study_rate"));
            lists.add(map);
        }
        StringBuffer title = new StringBuffer(
                "区域:eduArea,学校:eduSchool,学段:eduStep,学科:eduSubject,总播放量:totleStudy,总播放时长:totleStudyLength,播完率:studyRate,观看人数:totleStudy");
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
}
