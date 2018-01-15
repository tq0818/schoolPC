package com.yuxin.wx.controller.queAns;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
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

import com.yuxin.wx.api.auth.IAuthRoleService;
import com.yuxin.wx.api.company.ICompanyFunctionSetService;
import com.yuxin.wx.api.company.ICompanyMemberServiceService;
import com.yuxin.wx.api.company.ICompanyNewStepService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.api.queAns.IQuestionClassifyService;
import com.yuxin.wx.api.queAns.IQuestionService;
import com.yuxin.wx.api.system.ISysConfigItemService;
import com.yuxin.wx.api.system.ISysConfigServiceService;
import com.yuxin.wx.api.system.ISysConfigTeacherService;
import com.yuxin.wx.api.system.ISysSchoolItemRelationService;
import com.yuxin.wx.api.system.ISysServiceDredgeConfigService;
import com.yuxin.wx.api.user.IUsersService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.common.SysConfigConstant;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.CompanyFunctionSet;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.model.company.CompanyNewStep;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.model.queAns.QueQuestion;
import com.yuxin.wx.model.queAns.QuestionClassify;
import com.yuxin.wx.model.system.SysConfigItem;
import com.yuxin.wx.model.system.SysConfigService;
import com.yuxin.wx.model.system.SysConfigTeacher;
import com.yuxin.wx.model.system.SysSchoolItemRelation;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.utils.DateUtil;
import com.yuxin.wx.utils.PropertiesUtil;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.queAns.QuestionVo;
import com.yuxin.wx.vo.system.SysServiceDredgeVo;

/**
 * Controller of Question
 * 
 * @author wang.zx
 * @date 2015-12-9
 */
@Controller
@RequestMapping("/Question")
public class QuestionController {

    @Autowired
    private ISysServiceDredgeConfigService sysServiceDredgeImpl;

    @Autowired
    private ICompanyNewStepService companyNewStepServiceImpl;

    private Log log = LogFactory.getLog("log");

    @Autowired
    private IUsersService usersServiceImpl;

    @Autowired
    private ICompanyService companyServiceImpl;

    @Autowired
    private ICompanyFunctionSetService companyFunctionSetImpl;

    @Autowired
    private ISysConfigServiceService sysConfigServiceServiceImpl;

    @Autowired
    private IQuestionService questionServiceImpl;

    @Autowired
    private ISysConfigItemService sysConfigItemServiceImpl;

    @Autowired
    private PropertiesUtil properties;

    @Autowired
    private ICompanyFunctionSetService companyFunctionSetServiceImpl;

    @Autowired
    private IQuestionClassifyService questionClassifyServiceImpl;

    @Autowired
    private ICompanyMemberServiceService companyMemberServiceServiceImpl;

    @Autowired
    private ICompanyServiceStaticService companyServiceStaticServiceImpl;

    @Autowired
    private ICompanyService companyService;

    @Autowired
    private ISysSchoolItemRelationService sysSchoolItemRelationServiceImpl;

    @Autowired
    private IAuthRoleService authRoleServiceImpl;

    @Autowired
    private ISysConfigTeacherService sysConfigTeacherServiceImpl;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model, QueQuestion search) {
        if (search == null) {
            search = new QueQuestion();
            // search.setPageSize(20);
        }
        model.addAttribute("list", questionServiceImpl.findQuestionByPage(search));
        return "Question/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(QueQuestion question) {
        questionServiceImpl.insert(question);
        return "redirect:/question";
    }

    @ResponseBody
    @RequestMapping(value = "/update")
    public String update(QueQuestion question) {
        Integer topFlag = question.getTopFlag();
        if (topFlag != null) {
            Date date = new Date();
            question.setUpdateTime(date);
        }
        questionServiceImpl.update(question);
        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "/del/{id}")
    public String del(Model model, @PathVariable Integer id) {
        questionServiceImpl.deleteQuestionById(id);
        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "/{id:\\d+}", method = RequestMethod.GET)
    public QueQuestion getJson(Model model, @PathVariable Integer id) {
        return questionServiceImpl.findQuestionById(id);
    }

    /**
     * 
     * Class Name: QuestionController.java
     * 
     * @Description: 课程问答
     * @author yuchanglong
     * @date 2015年12月9日 下午5:56:48
     * @version 1.0
     * @param model
     * @return
     */
    @RequestMapping(value = "/questionIndex")
    public String questionIndex(Model model) {
        int companyId = WebUtils.getCurrentCompanyId();

        List<SysConfigItem> firstItems = sysConfigItemServiceImpl.findSysConfigItemByPid(SysConfigConstant.ITEMTYPE_FIRST, null, WebUtils.getCurrentCompanyId(),
                WebUtils.getCurrentSchoolId());
        model.addAttribute("firstItems", firstItems);
        CompanyFunctionSet search = new CompanyFunctionSet();
        search.setCompanyId(companyId);
        search.setFunctionCode("COURSE_QUESTION_FUNCTION");
        search.setStatus("1");
        // 判断选择的是课程分类还是自定义分类
        CompanyFunctionSet companyFunctionSet = companyFunctionSetServiceImpl.findCompanyUseCourse(search);
        if (companyFunctionSet != null) {
            model.addAttribute("courseQuestionFunctionSet", 1);
        }
        return "queAns/questionIndex";
    }

    /**
     * 
     * Class Name: QuestionController.java
     * 
     * @Description: 社区问答
     * @author yuchanglong
     * @date 2015年12月10日 下午4:13:53
     * @version 1.0
     * @return
     */
    @RequestMapping(value = "/comQuestionIndex")
    public String comQuestionIndex(Model model) {
        Integer companyId = WebUtils.getCurrentCompanyId();
        CompanyFunctionSet search = new CompanyFunctionSet();
        search.setCompanyId(companyId);
        search.setFunctionCode("COURSE_QUESTION_FUNCTION");
        search.setStatus("1");
        // 判断选择的是课程分类还是自定义分类
        CompanyFunctionSet companyFunctionSet = companyFunctionSetServiceImpl.findCompanyUseCourse(search);
        if (companyFunctionSet != null) {
            model.addAttribute("courseQuestionFunctionSet", 1);
        }
        search = new CompanyFunctionSet();
        search.setCompanyId(companyId);
        search.setFunctionCode("QUESTION_CLASSIFY_TYPE");
        // 判断选择的是课程分类还是自定义分类
        CompanyFunctionSet classOrPer = companyFunctionSetServiceImpl.findCompanyUseCourse(search);
        String isCorP = "";
        if (classOrPer == null) {
            CompanyFunctionSet newClass = new CompanyFunctionSet();
            newClass.setCompanyId(companyId);
            newClass.setFunctionCode("QUESTION_CLASSIFY_TYPE");
            newClass.setContent("0：自定义分类。1：课程分类");
            newClass.setStatus("0");
            newClass.setFunctionName("问答分类");
            companyFunctionSetServiceImpl.insert(newClass);
            isCorP = "0";
        } else {
            isCorP = classOrPer.getStatus();
        }

        if (isCorP.equals(1)) {
            // 选择的是课程分类

        } else {
            // 选择的是自定义分类，判断是否开启课程标签
            search.setFunctionCode("QUESTION_LABEL_STATE");
            CompanyFunctionSet classOpen = companyFunctionSetServiceImpl.findCompanyUseCourse(search);
            String isC = "";
            if (classOpen == null) {
                CompanyFunctionSet newOpen = new CompanyFunctionSet();
                newOpen.setCompanyId(companyId);
                newOpen.setFunctionCode("QUESTION_LABEL_STATE");
                newOpen.setContent("0：关闭。1：开启");
                newOpen.setStatus("0");
                newOpen.setFunctionName("自定义问答课程标签是否开启");
                companyFunctionSetServiceImpl.insert(newOpen);
                isC = "0";
            } else {
                isC = classOpen.getStatus();
            }

            model.addAttribute("isC", isC);
        }
        model.addAttribute("isCorP", isCorP);

        QuestionClassify questionClassify = new QuestionClassify();
        questionClassify.setCompanyId(companyId);
        questionClassify.setSchoolId(WebUtils.getCurrentSchoolId());
        // questionClassify.setDelFlag(1);//显示全部问题
        List<QuestionClassify> classify = questionClassifyServiceImpl.findQuestionClassify(questionClassify);
        List<QuestionClassify> perClassify = new ArrayList<QuestionClassify>();
        List<QuestionClassify> itemClassify = new ArrayList<QuestionClassify>();
        Integer classType = 0;
        Integer itemSecId = 0;
        for (QuestionClassify qc : classify) {
            classType = qc.getClassType();
            if (classType.equals(1)) {
                perClassify.add(qc);
            } else {
                itemSecId = Integer.parseInt(qc.getItemId());
                SysSchoolItemRelation searchLation = new SysSchoolItemRelation();
                searchLation.setItemId(itemSecId);
                searchLation.setSchoolId(WebUtils.getCurrentSchoolId());
                SysSchoolItemRelation relation = sysSchoolItemRelationServiceImpl.findSysSchoolItemRelationByItemId(searchLation);
                Integer delflag = relation.getDelFlag();
                if (delflag != null && delflag.equals(0)) {
                    itemClassify.add(qc);
                }
            }
        }

        model.addAttribute("perClassify", perClassify);
        model.addAttribute("itemClassify", itemClassify);

        return "queAns/comQuestionIndex";
    }

    /**
     * 
     * Class Name: QuestionController.java
     * 
     * @Description: 课程分类说明页
     * @author yuchanglong
     * @date 2016年1月4日 下午2:39:16
     * @version 1.0
     * @return
     */
    @RequestMapping(value = "/queKcfl")
    public String kcfl() {

        return "queAns/queAnsKc";
    }

    /**
     * 
     * Class Name: QuestionController.java
     * 
     * @Description: 自定义说明页
     * @author yuchanglong
     * @date 2016年1月4日 下午2:39:16
     * @version 1.0
     * @return
     */
    @RequestMapping(value = "/queZdyfl")
    public String zdyfl() {

        return "queAns/queAnsZdy";
    }

    /**
     * 
     * Class Name: QuestionController.java
     * 
     * @Description: 问答异步加载内容
     * @author yuchanglong
     * @date 2015年12月9日 下午8:42:53
     * @version 1.0
     * @param Question
     * @param model
     * @return
     */
    @RequestMapping(value = "/questionAjax")
    public String questionAjax(QueQuestion question, Model model, HttpServletRequest request) {
        Integer companyId = WebUtils.getCurrentCompanyId();
        Integer schoolId = WebUtils.getCurrentSchoolId();
        Integer userId = WebUtils.getCurrentUserId(request);
        question.setCompanyId(companyId);
        question.setSchoolId(schoolId);
        question.setPageSize(10);
        question.setDelFlag(1);
        Subject subject = SecurityUtils.getSubject();
        if (authRoleServiceImpl.hasRoleFlag(userId,WebUtils.getCurrentCompanyId())) {
            model.addAttribute("isMan", "yes");
        } else if(subject.hasRole("运营")){//是否运营人员
            model.addAttribute("isMan", "yes");
        } else{
            model.addAttribute("isMan", "no");
        }
        String imgUrl = "http://" + properties.getProjectImageUrl() + "/";
        model.addAttribute("imgUrl", imgUrl);

        PageFinder<QuestionVo> pageFinder = questionServiceImpl.findVoByPage(question);
        model.addAttribute("pageFinder", pageFinder);

        return "queAns/questionIndexAjaxList";
    }

    @ResponseBody
    @RequestMapping(value = "/questionAjaxLast5")
    public Object questionAjax() {
        Users users = WebUtils.getCurrentUser();
        Integer companyId = WebUtils.getCurrentCompanyId();
        Integer schoolId = WebUtils.getCurrentSchoolId();

        QueQuestion question = new QueQuestion();
        question.setCourseFlag(1);
        question.setCompanyId(companyId);
        question.setPageSize(10);
        question.setDelFlag(1);
        question.setAnswerCount(0);// 未作答问题
        Subject subject = SecurityUtils.getSubject();
        if (!subject.hasRole("机构管理员")) { // 运营或者分校管理员
            question.setSchoolId(schoolId);
            PageFinder<QuestionVo> pageFinder = questionServiceImpl.findVoByPage(question);
            return pageFinder.getData();
        } else if (!subject.hasRole("机构管理员") && (subject.hasRole("直播老师") || subject.hasRole("排课老师"))) {
            SysConfigTeacher teacher = sysConfigTeacherServiceImpl.findByUserId(users.getId());
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("companyId", companyId);
            map.put("schoolId", schoolId);
            map.put("teacherId", teacher.getId());
            map.put("delFlag", 1);
            map.put("answerCount", 0);
            List<QuestionVo> findTeacherQuestion = questionServiceImpl.findTeacherQuestion(map);
            return findTeacherQuestion;
        } else {
            PageFinder<QuestionVo> pageFinder = questionServiceImpl.findVoByPage(question);
            return pageFinder.getData();
        }
    }

    /**
     * 
     * Class Name: QuestionController.java
     * 
     * @Description: 问答设置
     * @author yuchanglong
     * @date 2015年12月23日 上午11:28:16
     * @version 1.0
     * @param model
     * @return
     */
    @RequestMapping(value = "/questionSetIndex")
    public String questionSetIndex(Model model) {
        Integer companyId = WebUtils.getCurrentCompanyId();
        Company company = companyService.findCompanyById(companyId);

        CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
        CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);

        CompanyFunctionSet search = new CompanyFunctionSet();
        search.setCompanyId(companyId);
        search.setFunctionCode("QUESTION_CLASSIFY_TYPE");
        // 判断选择的是课程分类还是自定义分类
        String isCorP = "";
        CompanyFunctionSet classOrPer = companyFunctionSetServiceImpl.findCompanyUseCourse(search);
        if (classOrPer == null) {
            search.setFunctionName("问答分类");
            search.setContent("0：自定义分类。1：课程分类");
            search.setStatus("0");
            companyFunctionSetServiceImpl.insert(search);
            isCorP = "0";
        } else {
            isCorP = classOrPer.getStatus();
        }
        model.addAttribute("isCorP", isCorP);
        model.addAttribute("company", company);
        model.addAttribute("cms", cms);
        model.addAttribute("css", css);
        return "queAns/questionSetIndex";
    }

    /**
     * 
     * Class Name: QuestionController.java
     * 
     * @Description: 问答设置
     * @author yuchanglong
     * @date 2015年12月23日 上午11:28:16
     * @version 1.0
     * @param model
     * @return
     */
    @RequestMapping(value = "/questionClassfy")
    public String questionClassfy(Model model) {
        Integer companyId = WebUtils.getCurrentCompanyId();
        Integer schoolId = WebUtils.getCurrentSchoolId();
        Company company = companyService.findCompanyById(companyId);

        CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
        CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);

        CompanyFunctionSet search = new CompanyFunctionSet();
        search.setCompanyId(companyId);
        search.setFunctionCode("QUESTION_CLASSIFY_TYPE");
        // 判断选择的是课程分类还是自定义分类
        CompanyFunctionSet classOrPer = companyFunctionSetServiceImpl.findCompanyUseCourse(search);
        String isCorP = classOrPer.getStatus();
        if (isCorP.equals(1)) {
            // 选择的是课程分类

        } else {
            // 选择的是自定义分类，判断是否开启课程标签
            String isC = "";
            search.setFunctionCode("QUESTION_LABEL_STATE");
            CompanyFunctionSet classOpen = companyFunctionSetServiceImpl.findCompanyUseCourse(search);
            if (classOpen == null) {
                search.setFunctionName("自定义问答标签是否开启");
                search.setContent("0：关闭。1：开启");
                search.setStatus("0");
                companyFunctionSetServiceImpl.insert(search);
                isC = "0";
            } else {
                isC = classOpen.getStatus();
            }
            model.addAttribute("isC", isC);
        }

        model.addAttribute("isCorP", isCorP);
        model.addAttribute("company", company);
        model.addAttribute("cms", cms);
        model.addAttribute("css", css);
        return "queAns/questionClassfy";
    }

    /**
     * 
     * Class Name: QuestionController.java
     * 
     * @Description: 加载自定义标签
     * @author yuchanglong
     * @date 2015年12月23日 下午4:51:47
     * @version 1.0
     * @param model
     * @return
     */
    @RequestMapping(value = "/loadZdy")
    public String loadZdy(Model model) {
        Integer companyId = WebUtils.getCurrentCompanyId();
        QuestionClassify qc = new QuestionClassify();
        qc.setCompanyId(companyId);
        qc.setClassType(1);
        List<QuestionClassify> classfys = questionClassifyServiceImpl.findQuestionClassify(qc);
        model.addAttribute("classfys", classfys);
        return "queAns/loadZdy";
    }

    /**
     * 
     * Class Name: QuestionController.java
     * 
     * @Description: 加载课程标签
     * @author yuchanglong
     * @date 2015年12月23日 下午4:51:47
     * @version 1.0
     * @param model
     * @return
     */
    @RequestMapping(value = "/loadKc")
    public String loadKc(Model model) {
        Integer companyId = WebUtils.getCurrentCompanyId();
        Integer schoolId = WebUtils.getCurrentSchoolId();
        QuestionClassify qc = new QuestionClassify();
        qc.setCompanyId(companyId);
        qc.setClassType(2);// 这里用来表示学科小类
        qc.setSchoolId(schoolId);
        List<QuestionClassify> seconds = questionClassifyServiceImpl.findQuestionClassifyKc(qc);
        model.addAttribute("seconds", seconds);
        return "queAns/loadKc";
    }

    /**
     * 
     * Class Name: QuestionController.java
     * 
     * @Description: 问答设置-修改选择类型
     * @author yuchanglong
     * @date 2015年12月23日 上午11:28:16
     * @version 1.0
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/changeStatus")
    public String changeStatus(HttpServletRequest request, String status, String code) {
        Integer companyId = WebUtils.getCurrentCompanyId();
        CompanyFunctionSet search = new CompanyFunctionSet();
        search.setCompanyId(companyId);
        search.setFunctionCode(code);
        // 判断选择的是课程分类还是自定义分类
        CompanyFunctionSet classOrPer = companyFunctionSetServiceImpl.findCompanyUseCourse(search);
        classOrPer.setStatus(status);
        if (code.equals("COURSE_QUESTION_FUNCTION")) {
            // 查询是否有课程问答权限
            SysConfigService sc = new SysConfigService();
            sc.setGroupCode("SERVICE_COURSE_QUESTION");
            sc.setCompanyId(companyId);
            sc.setDelFlag(Integer.parseInt(status));
            sc.setUpdateTime(new Date());
            sc.setUpdator(WebUtils.getCurrentUserId(request));

            SysConfigService ser = sysConfigServiceServiceImpl.findExist(sc);
            if (ser == null) {
                sysConfigServiceServiceImpl.insert(sc);
            } else {
                sc.setId(ser.getId());
                sysConfigServiceServiceImpl.update(sc);
            }
//            relogin();
        }
        companyFunctionSetServiceImpl.update(classOrPer);
        return "success";
    }

    /**
     * 
     * Class Name: QuestionController.java
     * 
     * @Description: 课程问答权限
     * @author yuchanglong
     * @date 2015年12月29日 上午10:29:28
     * @version 1.0
     * @param model
     * @return
     */
    @RequestMapping(value = "/queAnsSet")
    public String queAnsSet(Model model) {
        Integer companyId = WebUtils.getCurrentCompanyId();
        Company company = companyService.findCompanyById(companyId);

        CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
        CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);

        CompanyFunctionSet search = new CompanyFunctionSet();
        search.setCompanyId(companyId);
        // 课程观看权限
        String lookStatus = "";
        search.setFunctionCode("STUDENT_SACN_QUESTION_AUTHO");
        CompanyFunctionSet lookFucSet = companyFunctionSetServiceImpl.findCompanyUseCourse(search);
        if (lookFucSet == null) {
            CompanyFunctionSet search1 = new CompanyFunctionSet();
            search1.setCompanyId(companyId);
            search1.setFunctionCode("STUDENT_SACN_QUESTION_AUTHO");
            search1.setStatus("1");
            search1.setFunctionName("学生观看课程问答权限");
            search1.setContent("1：购买本课程学员，2：所有付费学员，3：所有登陆用户，4：所有用户");
            companyFunctionSetServiceImpl.insert(search1);
            lookStatus = "1";
        } else {
            lookStatus = lookFucSet.getStatus();
        }

        // 课程回复权限
        String ansStatus = "";
        search.setFunctionCode("STUDENT_ANSWER_QUESTION_AUTHO");
        CompanyFunctionSet ansFucSet = companyFunctionSetServiceImpl.findCompanyUseCourse(search);
        if (ansFucSet == null) {
            CompanyFunctionSet search2 = new CompanyFunctionSet();
            search2.setCompanyId(companyId);
            search2.setFunctionCode("STUDENT_ANSWER_QUESTION_AUTHO");
            search2.setStatus("1");
            search2.setFunctionName("学生回复课程问答权限");
            search2.setContent("1：购买本课程学员，2：所有付费学员，3：所有登陆用户");
            companyFunctionSetServiceImpl.insert(search2);
            ansStatus = "1";
        } else {
            ansStatus = ansFucSet.getStatus();
        }
        // 课程问答功能是否开启（权限）
        String ansIsOpenStatus = "";
        search.setFunctionCode("COURSE_QUESTION_FUNCTION");
        CompanyFunctionSet ansIsOpen = companyFunctionSetServiceImpl.findCompanyUseCourse(search);
        if (ansIsOpen == null) {
            CompanyFunctionSet search3 = new CompanyFunctionSet();
            search3.setCompanyId(companyId);
            search3.setFunctionCode("COURSE_QUESTION_FUNCTION");
            search3.setStatus("0");
            search3.setFunctionName("课程问答功能是否开启(权限)");
            search3.setContent("0: 关闭；1：开启");
            companyFunctionSetServiceImpl.insert(search3);
            ansIsOpenStatus = "0";
        } else {
            ansIsOpenStatus = ansIsOpen.getStatus();
        }
        // 课程问答权限是否开启（列表条件）
        String authIsOpenStatus = "";
        search.setFunctionCode("COURSE_QUESTION_AUTH");
        CompanyFunctionSet ansAuthIsOpen = companyFunctionSetServiceImpl.findCompanyUseCourse(search);
        if (ansAuthIsOpen == null) {
            CompanyFunctionSet search4 = new CompanyFunctionSet();
            search4.setCompanyId(companyId);
            search4.setFunctionCode("COURSE_QUESTION_AUTH");
            search4.setStatus("0");
            search4.setFunctionName("课程问答权限是否开启(列表条件)");
            search4.setContent("0: 关闭；1：开启");
            companyFunctionSetServiceImpl.insert(search4);
            authIsOpenStatus = "0";
        } else {
            authIsOpenStatus = ansAuthIsOpen.getStatus();
        }
        model.addAttribute("lookStatus", lookStatus);
        model.addAttribute("ansStatus", ansStatus);
        model.addAttribute("ansIsOpenStatus", ansIsOpenStatus);
        model.addAttribute("authIsOpenStatus", authIsOpenStatus);
        model.addAttribute("company", company);
        model.addAttribute("cms", cms);
        model.addAttribute("css", css);
        return "queAns/queAnsSet";
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

    private void relogin() {
        Subject subject = SecurityUtils.getSubject();
        Users users = usersServiceImpl.queryUserByName(subject.getPrincipal().toString());
        subject.logout();
        UsernamePasswordToken token = new UsernamePasswordToken(users.getUsername(), users.getPassword());
        subject.login(token);
        subject.getSession().setAttribute("loginUser", users);
        // 设置登录后的状态
        setUserStatus();
        Company company = companyServiceImpl.findCompanyById(users.getCompanyId());
        String status = String.valueOf(company.getStatus());
        if (company.getMemberLevel() < 20) {
            List<SysServiceDredgeVo> ssdVo = sysServiceDredgeImpl.findDredgeByCom(company.getId());
            if (ssdVo != null) {
                for (SysServiceDredgeVo s : ssdVo) {
                    subject.getSession().setAttribute(s.getName(), (s.getStatus() != null ? s.getStatus() : 0));
                }
            }
        }
        try {
            // 将机构使用视频服务存入session
            CompanyMemberService service = companyMemberServiceServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
            subject.getSession().setAttribute("useVideo", service.getVideoServiceProvider());

            // 将公司课程版本存入session(1表示简易版，0或空值表示复杂版)
            CompanyFunctionSet search = new CompanyFunctionSet();
            search.setCompanyId(WebUtils.getCurrentCompanyId());
            search.setFunctionCode("COMPANY_FUNCTION_COURSE");
            CompanyFunctionSet functionSet = companyFunctionSetImpl.findCompanyUseCourse(search);
            if (functionSet instanceof Object) {
                subject.getSession().setAttribute("courseFunction", functionSet.getStatus());
            } else {
                subject.getSession().setAttribute("courseFunction", "");
            }
        } catch (Exception e) {
            log.error("存入session值失败", e);
            e.printStackTrace();
        }

        WebUtils.setSessionAttribute("company_status", status);// 公司认证标记
        WebUtils.setSessionAttribute("company_buy_flag", company.getBuyFlag());// 购买标记
        WebUtils.setSessionAttribute("company_try_flag", DateUtil.daysBetween(company.getMemberEndDate(), (new Date())));// 试用期
    }

    /**
     * 
     * 查询机构的 购买标记、试用标记、认证标记，计入session
     * 
     */
    public void setUserStatus() {
        Company company = companyServiceImpl.findCompanyById(WebUtils.getCurrentCompanyId());
        List<CompanyNewStep> l = companyNewStepServiceImpl.findCompanyNewStepByCompany(WebUtils.getCurrentCompanyId());
        String status = String.valueOf(company.getStatus());

        WebUtils.setSessionAttribute("company_status", status);// 公司认证标记
        WebUtils.setSessionAttribute("company_buy_flag", company.getBuyFlag());// 购买标记
        if (l != null && !l.isEmpty() && l.get(0) != null) {
            WebUtils.setSessionAttribute("company_first_use", l.get(0).getNewStepFlag());
        } else {
            WebUtils.setSessionAttribute("company_first_use", null);
        }
        System.out.println(daysBetween(company.getMemberEndDate(), (new Date())));
        WebUtils.setSessionAttribute("company_try_flag", daysBetween(company.getMemberEndDate(), (new Date())));// 试用期
    }

    private Integer daysBetween(Date smdate, Date bdate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            smdate = sdf.parse(sdf.format(smdate));
            bdate = sdf.parse(sdf.format(bdate));
            Calendar cal = Calendar.getInstance();
            cal.setTime(smdate);
            long time1 = cal.getTimeInMillis();
            cal.setTime(bdate);
            long time2 = cal.getTimeInMillis();
            long between_days = (time2 - time1) / (1000 * 3600 * 24);
            return Integer.parseInt(String.valueOf(between_days));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 首页查询问答
     * 
     * @param request
     * @param question
     * @param model
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/getJson")
    public Object getJson(HttpServletRequest request, QueQuestion question, Model model) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();

        Integer companyId = WebUtils.getCurrentCompanyId();
        // Integer schoolId = WebUtils.getCurrentSchoolId();
        question.setPageSize(10);
        question.setDelFlag(1);
        question.setCompanyId(companyId);
        // question.setSchoolId(schoolId);

        question.setCourseFlag(0);

        PageFinder<QuestionVo> pageFinder = this.questionServiceImpl.findVoByPage(question);

        result.put("questions", pageFinder.getData());

        return result;
    }
}
