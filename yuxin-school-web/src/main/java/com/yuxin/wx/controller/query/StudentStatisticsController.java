package com.yuxin.wx.controller.query;

import com.yuxin.wx.api.company.ICompanyFunctionSetService;
import com.yuxin.wx.api.query.IStudentStatisticsService;
import com.yuxin.wx.api.system.ISysConfigDictService;
import com.yuxin.wx.api.system.ISysConfigTeacherService;
import com.yuxin.wx.api.user.IUsersService;
import com.yuxin.wx.model.company.CompanyFunctionSet;
import com.yuxin.wx.model.system.SysConfigDict;
import com.yuxin.wx.model.system.SysConfigTeacher;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.user.UsersAreaRelation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * @param name
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
     * @param name
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
     * @param name
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
}
