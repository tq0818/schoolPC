package com.yuxin.wx.controller.company;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
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
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.auth.IAuthPrivilegeService;
import com.yuxin.wx.api.auth.IAuthRoleService;
import com.yuxin.wx.api.company.ICompanyCouponsConfigService;
import com.yuxin.wx.api.company.ICompanyFunctionSetService;
import com.yuxin.wx.api.company.ICompanyLiveConcurrentService;
import com.yuxin.wx.api.company.ICompanyMemberConfigService;
import com.yuxin.wx.api.company.ICompanyMemberServiceChangelogService;
import com.yuxin.wx.api.company.ICompanyMemberServiceService;
import com.yuxin.wx.api.company.ICompanyNewStepService;
import com.yuxin.wx.api.company.ICompanyPayConfigService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.api.company.ICompanyVideoConfigService;
import com.yuxin.wx.api.course.ICourseVideoMarqueeLineService;
import com.yuxin.wx.api.course.ICourseVideoMarqueeService;
import com.yuxin.wx.api.queAns.IQuestionClassifyService;
import com.yuxin.wx.api.system.ISysConfigDictService;
import com.yuxin.wx.api.system.ISysConfigPageRedirectService;
import com.yuxin.wx.api.system.ISysConfigServiceGroupService;
import com.yuxin.wx.api.system.ISysConfigServiceService;
import com.yuxin.wx.api.system.ISysCyclePicService;
import com.yuxin.wx.api.system.ISysPageHeadFootService;
import com.yuxin.wx.api.system.ISysServiceDredgeConfigService;
import com.yuxin.wx.api.tiku.ITikuConfigService;
import com.yuxin.wx.api.tiku.ITikuSetService;
import com.yuxin.wx.api.user.IUsersService;
import com.yuxin.wx.common.JsonMsg;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.common.SysConfigConstant;
import com.yuxin.wx.common.ViewFiles;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.CompanyCouponsConfig;
import com.yuxin.wx.model.company.CompanyFunctionSet;
import com.yuxin.wx.model.company.CompanyLiveConcurrent;
import com.yuxin.wx.model.company.CompanyMemberConfig;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.model.company.CompanyMemberServiceChangelog;
import com.yuxin.wx.model.company.CompanyNewStep;
import com.yuxin.wx.model.company.CompanyPayConfig;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.model.course.CourseVideoMarquee;
import com.yuxin.wx.model.queAns.QuestionClassify;
import com.yuxin.wx.model.system.SysConfigDict;
import com.yuxin.wx.model.system.SysConfigPageRedirect;
import com.yuxin.wx.model.system.SysConfigService;
import com.yuxin.wx.model.system.SysConfigServiceGroup;
import com.yuxin.wx.model.system.SysCyclePic;
import com.yuxin.wx.model.system.SysPageHeadFoot;
import com.yuxin.wx.model.tiku.TikuConfig;
import com.yuxin.wx.model.tiku.TikuSet;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.util.JedisUtil;
import com.yuxin.wx.utils.DateUtil;
import com.yuxin.wx.utils.ExcelUtil;
import com.yuxin.wx.utils.PropertiesUtil;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.company.CompanyOrgMessageReadVo;
import com.yuxin.wx.vo.company.CompanyOrgMessageVo;
import com.yuxin.wx.vo.company.CompanyPicsVo;
import com.yuxin.wx.vo.company.CompanyVo;
import com.yuxin.wx.vo.system.SysServiceDredgeVo;

/**
 * Controller of Company
 * 
 * @author chopin
 * @date 2015-4-23
 */
@Controller
@RequestMapping("/company")
public class CompanyController {
    private Log log = LogFactory.getLog("log");
    @Autowired
    private ISysServiceDredgeConfigService sysServiceDredgeImpl;
    @Autowired
    private ICompanyLiveConcurrentService companyLiveConcurrentServiceImpl;
    @Autowired
    private ICompanyMemberServiceChangelogService companyMemberServiceChangelogServiceImpl;
    @Autowired
    private ITikuSetService tikuSetServiceImpl;
    @Autowired
    private ITikuConfigService tikuConfigServiceImpl;
    @Autowired
    private IQuestionClassifyService questionClassifyServiceImpl;
    @Autowired
    private ICompanyFunctionSetService companyFunctionSetServiceImpl;
    @Autowired
    private ISysCyclePicService sysCyclePicServiceImpl;
    @Autowired
    private ICourseVideoMarqueeService courseVideoMarqueeServiceImpl;
    @Autowired
    private ISysConfigPageRedirectService sysConfigPageRedirectServiceImpl;
    @Autowired
    private ISysPageHeadFootService sysPageHeadFootServiceImpl;
    @Autowired
    private IAuthPrivilegeService authPrivilegeServiceImpl;
    @Autowired
    private ISysConfigServiceGroupService sysConfigServiceGroupImpl;
    @Autowired
    private ISysConfigDictService sysConfigDictServiceImpl;
    @Autowired
    private ISysConfigServiceService sysConfigServiceServiceImpl;
    @Autowired
    private IUsersService iUsersService;
    @Autowired
    private IAuthRoleService authRoleServiceImpl;
    @Autowired
    private IUsersService usersServiceImpl;
    @Autowired
    private ICompanyMemberServiceService companyMemberServiceServiceImpl;
    @Autowired
    private ICompanyServiceStaticService companyServiceStaticServiceImpl;
    @Autowired
    private ICompanyService companyServiceImpl;
    @Autowired
    private ICompanyPayConfigService companyPayConfigService;
    @Autowired
    private PropertiesUtil propertiesUtil;
    @Autowired
    private ICompanyFunctionSetService companyFunctionSetImpl;
    @Autowired
    private ICompanyNewStepService companyNewStepServiceImpl;
    @Autowired
    private ICompanyMemberConfigService companyMemberConfigServiceImpl;
    @Autowired
    private ICompanyCouponsConfigService companyCouponsConfigServiceImpl;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model, Company search) {
        if (search == null) {
            search = new Company();
            // search.setPageSize(20);
        }
        model.addAttribute("list", companyServiceImpl.findCompanyByPage(search));
        return "company/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Company Company) {
        companyServiceImpl.insert(Company);
        return "redirect:/company";
    }

    @ResponseBody
    @RequestMapping(value = "/updateByNameShort")
    public Company updateByNameShort(String companyNameShort, HttpServletRequest request) {
        Company company = new Company();
        company.setCompanyNameShort(companyNameShort);
        company.setId(WebUtils.getCurrentCompanyId());
        company.setUpdateTime(new Date());
        company.setUpdator(WebUtils.getCurrentUserId(request) + "");
        companyServiceImpl.update(company);
        return companyServiceImpl.findCompanyById(WebUtils.getCurrentCompanyId());
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Company update(Company company, HttpServletRequest request) {
        company.setId(WebUtils.getCurrentCompanyId());
        company.setUpdateTime(new Date());
        company.setUpdator(WebUtils.getCurrentUserId(request) + "");
        company.setCompanyNameShort(company.getCompanyName());
        companyServiceImpl.update(company);
        return companyServiceImpl.findCompanyById(WebUtils.getCurrentCompanyId());
    }

    /**
     * 防止注册重复域名和公司名称
     * 
     * @author licong
     * @date 2016年5月23日 下午5:53:04
     * @param
     * @param company
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateCompany", method = RequestMethod.POST)
    public JSONObject updateCompany(Company company, HttpServletRequest request, HttpServletResponse response) {
        JSONObject json = new JSONObject();
        String companyName = company.getCompanyName();
        String domain = company.getDomain();
        if (null == companyName || "".equals(companyName)) {
            json.put("flag", false);
            json.put("msg", "请填写公司名称");
            return json;
        }
        if (null == domain || "".equals(domain)) {
            json.put("flag", false);
            json.put("msg", "请填写公司域名");
            return json;
        }
        Company repeatedRegister = new Company();
        repeatedRegister.setCompanyName(companyName);
        Company comp = companyServiceImpl.queryCompanyByCopanyCondition(repeatedRegister);
        if (comp != null) {
            json.put("flag", false);
            json.put("msg", "不能重复注册公司名称");
            return json;
        }
        repeatedRegister.setCompanyName(null);
        repeatedRegister.setDomain(domain);
        comp = companyServiceImpl.queryCompanyByCopanyCondition(repeatedRegister);
        if (comp != null) {
            json.put("flag", false);
            json.put("msg", "不能重复注册公司域名");
            return json;
        }
        company.setId(WebUtils.getCurrentCompanyId());
        company.setUpdateTime(new Date());
        company.setUpdator(WebUtils.getCurrentUserId(request) + "");
        company.setCompanyNameShort(company.getCompanyName());
        companyServiceImpl.update(company);
        json.put("company", companyServiceImpl.findCompanyById(WebUtils.getCurrentCompanyId()));
        return json;
    }

    /**
     * 初始化域名和密码
     * 
     * @param company
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/initDomainAndName", method = RequestMethod.POST)
    public Map initDomainAndName(Company company, HttpServletRequest request) {
        Map map = new HashMap();
        if (!companyServiceImpl.checkCompanyDomain(company.getDomain())) {
            map.put("flag", "failed");
            map.put("msg", "此域名已经被占用");
            map.put("field", "domain");

        }
        if (!companyServiceImpl.checkCompanyName(company.getCompanyName())) {
            map.put("flag", "failed");
            map.put("msg", "此网校名称已经被使用");
            map.put("field", "companyName");
        }
        company.setId(WebUtils.getCurrentCompanyId());
        company.setUpdateTime(new Date());
        company.setUpdator(WebUtils.getCurrentUserId(request) + "");
        company.setCompanyNameShort(company.getCompanyName());
        companyServiceImpl.update(company);
        map.put("flag", "success");
        return map;

    }

    @RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
    public String del(Model model, @PathVariable Integer id) {
        companyServiceImpl.deleteCompanyById(id);
        return "redirect:/company";
    }

    @ResponseBody
    @RequestMapping(value = "/{id:\\d+}", method = RequestMethod.GET)
    public Company getJson(Model model, @PathVariable Integer id) {
        return companyServiceImpl.findCompanyById(id);
    }

    @ResponseBody
    @RequestMapping(value = "/currtCompany", method = RequestMethod.POST)
    public Company currtCompany(Model model) {
        return companyServiceImpl.findCompanyById(WebUtils.getCurrentCompanyId());
    }

    @RequestMapping(value = "/registercomplete", method = RequestMethod.POST)
    public String registercomplete(HttpServletRequest request, Model model, String username, String password) {
        model.addAttribute("username", username);
        model.addAttribute("password", password);
        return "/company/registercomplete";
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

    @RequestMapping("/companyService")
    public String companyService(Model model, HttpServletRequest request) {
        // 获得 机构 id
        Integer companyId = WebUtils.getCurrentCompanyId();
        // 查询 机构信息
        Company company = companyServiceImpl.findCompanyById(companyId);

        CompanyPayConfig payConfig = companyPayConfigService.findByCompanyId(companyId);
        if (payConfig != null) {
            if (null != payConfig.getPayType() && payConfig.getPayType().length() > 0) {
                model.addAttribute("zfbStatus", "1");
            } else {
                model.addAttribute("zfbStatus", "0");
            }
        } else {
            model.addAttribute("zfbStatus", "0");
        }
        // 查询所有服务
        List<SysConfigDict> dictList = sysConfigDictServiceImpl.findByDicCode("SYS_CONFIG_SERVICE_TYPE");
        // 查询未开通服务
        List<SysConfigService> scservList = sysConfigServiceServiceImpl.findByCompanyId(companyId);
        for (SysConfigDict d : dictList) {
            model.addAttribute(d.getItemCode(), null);
            if (scservList != null && scservList.size() > 0) {
                for (SysConfigService s : scservList) {
                    if (s.getGroupCode().equals(d.getItemCode()) && s.getDelFlag().equals(0)) {
                        model.addAttribute(d.getItemCode(), s.getGroupCode());
                    }
                }
            }
        }

        if (authRoleServiceImpl.hasRoleFlag(WebUtils.getCurrentUserId(request),WebUtils.getCurrentCompanyId())) {
            model.addAttribute("manganger", "manager");
        }
        Date date = new Date();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("companyId", companyId);
        param.put("concurrentMonth",
                Integer.parseInt((date.getYear() + 1900) + "" + ((date.getMonth() + 1) < 10 ? "0" + (date.getMonth() + 1) : (date.getMonth() + 1))));
        // 查询本月并发
        CompanyLiveConcurrent clc = companyLiveConcurrentServiceImpl.findLiveByComidAndDate(param);

        // 下月并发
        Date time = DateUtil.addMonthsToDate(date, 1);
        Integer concurrentMonths = Integer
                .parseInt((time.getYear() + 1900) + "" + ((time.getMonth() + 1) < 10 ? "0" + (time.getMonth() + 1) : (time.getMonth() + 1)));

        param.clear();
        param.put("companyId", companyId);
        param.put("concurrentMonth", concurrentMonths);
        CompanyLiveConcurrent clcn = companyLiveConcurrentServiceImpl.findLiveByComidAndDate(param);

        CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
        CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);

        // 用户信息
        Users user = usersServiceImpl.findUserByCompanyIdAndUserType(companyId);

        // 根据companyId查询审核信息
        //Integer caId = companyAuthorityServiceImpl.findByCompanyId(companyId);

        model.addAttribute("clc", clc);
        model.addAttribute("clcn", clcn);
        //model.addAttribute("caId", caId);
        model.addAttribute("company", company);
        model.addAttribute("user", user);
        model.addAttribute("cms", cms);
        model.addAttribute("css", css);
        //充值卡服务
        CompanyFunctionSet rechargecard_service = WebUtils.getFunctionSet("RECHARGECARD_SERVICE");
        model.addAttribute("rechargecard_service", rechargecard_service==null?0:rechargecard_service.getStatus());
        return "/company/companyService";
    }

    /**
     * 
     * Class Name: CompanyController.java
     * 
     * @Description: 机构管理-选择机构
     * @author ycl
     * @date 2015-5-13 下午5:50:11
     * @modifier
     * @modify-date 2015-5-13 下午5:50:11
     * @version 1.0
     * @return
     */
    @RequestMapping(value = "/toChoiceInstitution")
    public String toChoiceInstitution() {
        Subject subject = SecurityUtils.getSubject();
        Boolean role = subject.hasRole("云朵运营");
        log.info("======" + role);
        if (subject.hasRole("云朵管理员") || subject.hasRole("云朵运营")) {
            return "institution/choiceInstitutionType";
        } else {
            return "redirect:/";
        }

    }

    /**
     * 
     * Class Name: CompanyController.java
     * 
     * @Description: 异步加载信息
     * @author ycl
     * @date 2015-5-13 下午5:51:30
     * @modifier
     * @modify-date 2015-5-13 下午5:51:30
     * @version 1.0
     * @param model
     * @param search
     * @return
     */
    @RequestMapping(value = "/loadAjaxInfo")
    public String loadAjaxInfo(Model model, Company search) {
        search.setPageSize(6);
        PageFinder<CompanyVo> pageFinder = companyServiceImpl.findCompanyVoByPage(search);

        // 图片服务器地址
        String imageServeUrl = propertiesUtil.getImageServicePath();
        model.addAttribute("imageServeUrl", imageServeUrl);
        String target = "";
        // if(pageFinder.getRowCount()==0){
        // target = "";
        // }else{

        model.addAttribute("pageFinder", pageFinder);
        target = "institution/choiceAjaxInfo";
        // }
        return target;
    }


    /**
     * 
     * Class Name: CompanyController.java
     * 
     * @Description:查询公司图片库
     * @author zhang.zx
     * @date
     * @modifier
     * @modify-date
     * @version 1.0
     * @param picVo
     * @return List<CompanyPicsVo>
     */
    @RequestMapping(value = "/queryPics", method = RequestMethod.POST)
    public String queryCompanyPics(CompanyPicsVo picVo, Model model) {
        String url = "http://" + propertiesUtil.getProjectImageUrl() + "/";
        Integer companyId = WebUtils.getCurrentCompanyId();
        picVo.setCompanyId(companyId);
        picVo.setPicType("classtype");
        // if(picVo.getPage()>1){
        // picVo.setPageSize(8);
        // }else{
        // picVo.setPageSize(7);
        // }
        picVo.setPageSize(7);
        PageFinder<CompanyPicsVo> pageFinder = companyServiceImpl.findCompanyPic(picVo);
        for (CompanyPicsVo c : pageFinder.getData()) {
            c.setRealPath(c.getPicOriginalUrl());
            c.setPicOriginalUrl(url + c.getPicOriginalUrl());
        }
        model.addAttribute("itemOneId", picVo.getItemOneId());
        model.addAttribute("pageFinder", pageFinder);
        return "classType/companyPicsAjaxList";
    }

    /**
     * 
     * Class Name: CompanyController.java
     * 
     * @Description:查询公司图片库一级条件
     * @author zhang.zx
     * @date
     * @modifier
     * @modify-date
     * @version 1.0
     * @param picVo
     * @return List<CompanyPicsVo>
     */
    @ResponseBody
    @RequestMapping(value = "/queryCondition", method = RequestMethod.POST)
    public List<CompanyPicsVo> findOneCondtion() {
        return companyServiceImpl.findOneCondtion();
    }

    /**
     * 
     * Class Name: CompanyController.java
     * 
     * @Description:查询公司私有图片库
     * @author zhang.zx
     * @date
     * @modifier
     * @modify-date
     * @version 1.0
     * @param picVo
     * @return List<CompanyPicsVo>
     */
    @RequestMapping(value = "/queryPirvatePics", method = RequestMethod.POST)
    public String queryCompanyPrivatePics(CompanyPicsVo picVo, Model model) {
        String url = "http://" + propertiesUtil.getProjectImageUrl() + "/";
        Integer companyId = 0;
        picVo.setCompanyId(companyId);
        picVo.setPicType("classtype");
        picVo.setPageSize(8);
        PageFinder<CompanyPicsVo> pageFinder = companyServiceImpl.findCompanyPrivatePic(picVo);
        for (CompanyPicsVo c : pageFinder.getData()) {
            c.setRealPath(c.getPicOriginalUrl());
            c.setPicOriginalUrl(url + c.getPicOriginalUrl());
        }
        model.addAttribute("picTag", picVo.getPicTag());
        model.addAttribute("pageFinder", pageFinder);
        return "classType/companyPubPicsAjaxList";
    }

    @ResponseBody
    @RequestMapping(value = "/getCurrtCompanyInfo", method = RequestMethod.POST)
    public Company getCurrtCompanyInfo(Model model) {
        return companyServiceImpl.findCompanyById(WebUtils.getCurrentCompanyId());
    }

    /**
     * 
     * Class Name: CompanyController.java
     * 
     * @Description: 修改公司logo显示类型
     * @author zhang.zx
     * @date 2015年6月3日 下午2:37:08
     * @modifier
     * @modify-date 2015年6月3日 下午2:37:08
     * @version 1.0
     * @param company
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateLogoType", method = RequestMethod.POST)
    public String updateCompanyLogoType(Company company, HttpServletRequest request) {
        company.setId(WebUtils.getCurrentCompanyId());
        company.setUpdateTime(new Date());
        company.setUpdator(WebUtils.getCurrentUserId(request) + "");
        companyServiceImpl.update(company);
        return JsonMsg.SUCCESS;
    }

    /**
     * 
     * Class Name: CompanyController.java
     * 
     * @Description: 消息管理
     * @author zhang.zx
     * @date 2015年7月13日 下午6:31:08
     * @modifier
     * @modify-date 2015年7月13日 下午6:31:08
     * @version 1.0
     * @return
     */
    @RequestMapping(value = "/manageMessage", method = RequestMethod.GET)
    public String manageMessage(Model model) {

        return "institution/manageMessage";
    }

    @RequestMapping(value = "/messageCenter", method = RequestMethod.GET)
    public String messageCenter(Model model) {

        return "institution/messageCenter";
    }

    // 消息列表
    @RequestMapping(value = "/queryMessageList", method = RequestMethod.POST)
    public String queryMessageList(Model model, CompanyOrgMessageVo search) {
        PageFinder<CompanyOrgMessageVo> pageFinder = companyServiceImpl.findMessageList(search);
        model.addAttribute("pageFinder", pageFinder);
        return "institution/messageAjaxList";
    }

    // 编辑消息
    @RequestMapping(value = "/editMessage/{type}", method = RequestMethod.GET)
    public String editMessage(@PathVariable String type, Model model, HttpServletRequest req) {
        CompanyOrgMessageVo message = null;
        if ("add".equals(type)) {
            message = companyServiceImpl.queryMessageById(0);
        } else {
            Integer id = Integer.parseInt(req.getParameter("id"));
            message = companyServiceImpl.queryMessageById(id);
        }
        List<Company> arr = companyServiceImpl.queryAllCompany();
        model.addAttribute("arr", arr);
        model.addAttribute("message", message);
        model.addAttribute("type", type);
        return "institution/editMessage";
    }

    /**
     * 
     * Class Name: SysConfigNewsController.java
     * 
     * @Description: 添加消息
     * @author zhang.zx
     * @date 2015年4月1日 下午1:56:58
     * @version 1.0
     * @param sysNews
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/addMessage", method = RequestMethod.POST)
    public boolean add(CompanyOrgMessageVo message, HttpServletRequest req) {
        message.setSendTime(new Date());
        message.setSender(WebUtils.getCurrentUserId(req).toString());
        companyServiceImpl.insertMsg(message);
        Integer companyId = message.getCompanyId();
        if (companyId != null && !"".equals(companyId)) {
            CompanyOrgMessageReadVo c = new CompanyOrgMessageReadVo();
            c.setOrgMessageId(message.getId());
            c.setDelFlag(0);
            c.setReadFlag(0);
            c.setCompanyId(companyId);
            companyServiceImpl.insertCompanyReadDetail(c);
        }
        return true;
    }

    /**
     * 
     * Class Name: SysConfigNewsController.java
     * 
     * @Description: 修改消息
     * @author zhang.zx
     * @date 2015年4月1日 下午1:56:00
     * @version 1.0
     * @param SysNews
     * @return String 返回类型
     */
    @ResponseBody
    @RequestMapping(value = "/updateMessage", method = RequestMethod.POST)
    public boolean update(CompanyOrgMessageVo message, HttpServletRequest req) {
        try {
            companyServiceImpl.updateMsg(message);
        } catch (Exception e) {
            log.error("修改消息出错！", e);
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 
     * Class Name: CompanyController.java
     * 
     * @Description:删除消息
     * @author zhang.zx
     * @date 2015年7月14日 上午10:07:00
     * @modifier
     * @modify-date 2015年7月14日 上午10:07:00
     * @version 1.0
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delteMsg/{id}", method = RequestMethod.GET)
    public String delte(@PathVariable Integer id) {
        companyServiceImpl.deleteMsg(id);
        return JsonMsg.SUCCESS;
    }

    /**
     * 
     * Class Name: CompanyController.java
     * 
     * @Description: 发送消息
     * @author zhang.zx
     * @date 2015年7月14日 上午10:10:04
     * @modifier
     * @modify-date 2015年7月14日 上午10:10:04
     * @version 1.0
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sendMsg", method = RequestMethod.POST)
    public String sendMsg(Integer id) {
        CompanyOrgMessageVo c = new CompanyOrgMessageVo();
        c.setId(id);
        c.setStatus(1);
        companyServiceImpl.updateMsg(c);
        return JsonMsg.SUCCESS;
    }

    // 消息中心列表
    @RequestMapping(value = "/queryMessageCenterList", method = RequestMethod.POST)
    public String queryMessageCenterList(Model model, CompanyOrgMessageReadVo c) {
        Integer companyId = WebUtils.getCurrentCompanyId();
        Company c1 = companyServiceImpl.findCompanyById(companyId);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        c.setRegistTime(sdf.format(c1.getRegistTime()));
        c.setCompanyId(WebUtils.getCurrentCompanyId());
        PageFinder<CompanyOrgMessageReadVo> pageFinder = companyServiceImpl.findMessageCenterList(c);
        model.addAttribute("pageFinder", pageFinder);
        return "institution/messageCenterAjaxList";
    }

    // 查询消息数量
    @ResponseBody
    @RequestMapping(value = "/getNum", method = RequestMethod.POST)
    public Integer getMessageNum(CompanyOrgMessageReadVo c) {
        Integer companyId = WebUtils.getCurrentCompanyId();
        Company c1 = companyServiceImpl.findCompanyById(companyId);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        c.setRegistTime(sdf.format(c1.getRegistTime()));
        c.setCompanyId(companyId);
        return companyServiceImpl.queryCompanyNotReadNum(c);
    }

    // 消息详情
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String messageDetal(@PathVariable Integer id, Model model) {
        CompanyOrgMessageVo message = companyServiceImpl.queryMessageById(id);
        model.addAttribute("message", message);
        return "institution/messageContent";
    }

    // 添加公司阅读记录
    @ResponseBody
    @RequestMapping(value = "/addReadDeatil", method = RequestMethod.POST)
    public String addCompanyReadDetail(CompanyOrgMessageReadVo c) {

        return JsonMsg.SUCCESS;
    }

    // 修改阅读标记
    @ResponseBody
    @RequestMapping(value = "/updateFlag", method = RequestMethod.POST)
    public String updateCompanyReadFlag(Integer id) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("id", id);
        map.put("companyId", WebUtils.getCurrentCompanyId());
        CompanyOrgMessageReadVo c1 = companyServiceImpl.querymessageCenterById(map);
        if (c1 != null) {
            CompanyOrgMessageReadVo c = new CompanyOrgMessageReadVo();
            c.setId(c1.getId());
            c.setReadFlag(1);
            companyServiceImpl.updateMsgReadFlag(c);
        } else {
            CompanyOrgMessageReadVo c = new CompanyOrgMessageReadVo();
            c.setReadFlag(1);
            c.setCompanyId(WebUtils.getCurrentCompanyId());
            c.setDelFlag(0);
            c.setOrgMessageId(id);
            companyServiceImpl.insertCompanyReadDetail(c);
        }

        return JsonMsg.SUCCESS;
    }

    // 删除消息记录
    @ResponseBody
    @RequestMapping(value = "/delReadFlag", method = RequestMethod.POST)
    public String delCompanyReadFlag(String ids) {
        String str = ids.substring(0, ids.length() - 1);
        if (str != null && !"".equals(str)) {
            String[] strs = str.split(",");
            for (int i = 0; i < strs.length; i++) {
                Map<String, Integer> map = new HashMap<String, Integer>();
                map.put("id", Integer.parseInt(strs[i]));
                map.put("companyId", WebUtils.getCurrentCompanyId());
                CompanyOrgMessageReadVo c1 = companyServiceImpl.querymessageCenterById(map);
                if (c1 != null && c1.getReadFlag() == 1) {
                    CompanyOrgMessageReadVo c = new CompanyOrgMessageReadVo();
                    c.setId(c1.getId());
                    c.setDelFlag(1);
                    companyServiceImpl.updateMsgReadFlag(c);
                } else {
                    CompanyOrgMessageReadVo c2 = new CompanyOrgMessageReadVo();
                    c2.setDelFlag(1);
                    c2.setCompanyId(WebUtils.getCurrentCompanyId());
                    c2.setReadFlag(1);
                    c2.setOrgMessageId(Integer.parseInt(strs[i]));
                    companyServiceImpl.insertCompanyReadDetail(c2);
                }
            }
        }
        return JsonMsg.SUCCESS;
    }

    /**
     * 
     * Class Name: CompanyController.java
     * 
     * @Description: 根据手机号查询网校状态，名称
     * @author 周文斌
     * @date 2015-8-4 下午12:01:05
     * @version 1.0
     * @param mobile
     * @return
     */
    @ResponseBody
    @RequestMapping("/selCompanyByMobile")
    public JSONObject selCompanyByMobile(String mobile) {
        JSONObject json = new JSONObject();
        try {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("mobile", mobile);
            param.put("userType", "USER_TYPE_ORG");
            // 查找公司id
            Integer companyId = iUsersService.findCompanyByMobile(param);
            if (companyId == null) {
                json.put("status", "未注册");
                json.put("name", "无");
            } else {
                // 查询公司认证状态
                Company company = companyServiceImpl.findCompanyById(companyId);
                json.put("name", company.getCompanyName());
                if (company.getStatus() == 1 || company.getStatus() == 2 || company.getStatus() == 4 || company.getStatus() == 5) {
                    json.put("status", "未认证");
                } else if (company.getStatus() == 3) {
                    json.put("status", "未试用");
                }
            }
            json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
            return json;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            System.out.println(e.getMessage());
            json.put(JsonMsg.MSG, JsonMsg.INFORMATION);
            return json;
        }
    }

    /**
     * 
     * Class Name: CompanyController.java
     * 
     * @Description: 开通服务
     * @author 周文斌
     * @date 2015-8-13 下午6:03:22
     * @version 1.0
     * @param types
     * @return
     */
    @ResponseBody
    @RequestMapping("/openService")
    public JSONObject openService(HttpServletRequest request, SysConfigService sc) {
        JSONObject json = new JSONObject();
        Integer companyId = WebUtils.getCurrentCompanyId();
        Map<String, Object> param = new HashMap<String, Object>();
        try {
            sc.setCompanyId(companyId);
            sc.setDelFlag(1);
            sc.setUpdateTime(new Date());
            sc.setUpdator(WebUtils.getCurrentUserId(request));
            // 查询是否存在
            SysConfigService ser = sysConfigServiceServiceImpl.findExist(sc);
            if (ser == null) {
                sysConfigServiceServiceImpl.insert(sc);
            } else {
                sc.setId(ser.getId());
                sysConfigServiceServiceImpl.update(sc);
            }
            if (sc.getGroupCode().equals("SERVICE_TIKU")) {
                SysPageHeadFoot sphf = new SysPageHeadFoot();
                sphf.setCompanyId(companyId);
                sphf.setSchoolId(WebUtils.getCurrentUserSchoolId(request));
                sphf.setPageHeadType("SYS_PAGE_HEAD_TIKU");
                SysPageHeadFoot head = sysPageHeadFootServiceImpl.findHeadFoot(sphf);
                // 查询 TOPIC_TYPE_ALL_NOT_PAPER是否有值
                param.clear();
                param.put("companyId", companyId);
                param.put("configType", "TIKU_OBTAIN_TOPIC_RANGE");
                List<TikuConfig> tc = tikuConfigServiceImpl.findList(param);
                /*
                 * if(head != null){ head.setValidFlag(0);
                 * head.setUpdateTime(new Date());
                 * head.setUpdator(WebUtils.getCurrentUserId(request));
                 * sysPageHeadFootServiceImpl.update(head); }else
                 */
                TikuSet set = new TikuSet();
                set.setCompanyId(companyId);
                TikuSet findSet = tikuSetServiceImpl.findSetByCompanyIdAndCategoryId(set);

                if (head == null) {
                    sphf.setValidFlag(1);
                    sphf.setName("题库");
                    sphf.setCreateTime(new Date());
                    sphf.setParentId(0);
                    sphf.setSequence(9);
                    sphf.setOpenType("self");
                    sphf.setPageType("head");
                    sphf.setUrl("tikuIndex/toIndex");
                    sphf.setCreator(WebUtils.getCurrentUserId(request));
                    sysPageHeadFootServiceImpl.insert(sphf);
                } else {
                    head.setValidFlag(1);
                    head.setUpdateTime(new Date());
                    head.setUpdator(WebUtils.getCurrentUserId(request));
                    sysPageHeadFootServiceImpl.update(head);
                }

                if (tc == null || tc.size() == 0) {
                    TikuConfig t1 = new TikuConfig();
                    t1.setCompanyId(companyId);
                    t1.setConfigType("TIKU_OBTAIN_TOPIC_RANGE");
                    t1.setConfigValue("TOPIC_OF_ALL_NOT_PAPER");
                    t1.setDelFlag(1);
                    tikuConfigServiceImpl.insert(t1);

                    tc = new ArrayList<TikuConfig>();
                    TikuConfig t2 = new TikuConfig();
                    t2.setCompanyId(companyId);
                    t2.setConfigType("TIKU_OBTAIN_TOPIC_RANGE");
                    t2.setConfigValue("TOPIC_OF_ALL");
                    t2.setDelFlag(0);
                    tikuConfigServiceImpl.insert(t2);
                }

                if (findSet == null) {
                    set.setFreeStuNo(1);
                    set.setFreeStuTry(0);
                    set.setChargeStuAll(1);
                    set.setChargeStuItem(0);
                    set.setChargeStuItemSecond(0);
                    set.setTopicAuditYes(1);
                    set.setTopicAuditNo(0);
                    set.setPaperAuditYes(1);
                    set.setPaperAuditNo(0);
                    tikuSetServiceImpl.insert(set);
                    JedisUtil.deleteByKey("companySet" + companyId + "");
                    JedisUtil.put("companySet" + companyId + "", set, (30 * 24 * 3600));
                }

            }
            if (sc.getGroupCode().equals("SERVICE_OPENCLASS")) {
                SysPageHeadFoot sphf = new SysPageHeadFoot();
                sphf.setCompanyId(companyId);
                sphf.setSchoolId(WebUtils.getCurrentUserSchoolId(request));
                sphf.setPageHeadType("SYS_PAGE_HEAD_OPENCLASS");
                SysPageHeadFoot head = sysPageHeadFootServiceImpl.findHeadFoot(sphf);

                /*
                 * if(head != null){ head.setValidFlag(0);
                 * head.setUpdateTime(new Date());
                 * head.setUpdator(WebUtils.getCurrentUserId(request));
                 * sysPageHeadFootServiceImpl.update(head); }else
                 */
                if (head == null) {
                    sphf.setValidFlag(1);
                    sphf.setName("公开课");
                    sphf.setCreateTime(new Date());
                    sphf.setSequence(8);
                    sphf.setParentId(0);
                    sphf.setOpenType("blank");
                    sphf.setPageType("head");
                    sphf.setUrl("liveOpenCourse/toOpenClass");
                    sphf.setCreator(WebUtils.getCurrentUserId(request));
                    sysPageHeadFootServiceImpl.insert(sphf);
                } else {
                    head.setValidFlag(1);
                    head.setUpdateTime(new Date());
                    head.setUpdator(WebUtils.getCurrentUserId(request));
                    sysPageHeadFootServiceImpl.update(head);
                }

                // 添加默认图片
                SysCyclePic scp = new SysCyclePic();
                scp.setCompanyId(companyId);
                scp.setSchoolId(WebUtils.getCurrentUserSchoolId(request));
                scp.setPicType("openClass");
                List<SysCyclePic> scpList = sysCyclePicServiceImpl.findSysCyclePic(scp);
                if (scpList == null || scpList.size() == 0) {
                    scp.setPicUrl("cycllepic/1/20151015/73353d92-90fd-4a2f-ac75-989bb4031e93.png");
                    scp.setValidFlag(1);
                    scp.setCreateTime(new Date());
                    scp.setPicSequence(1);
                    scp.setCreator(WebUtils.getCurrentUserId(request));
                    sysCyclePicServiceImpl.insert(scp);
                }

                SysConfigPageRedirect scpr = new SysConfigPageRedirect();
                scpr.setCompanyId(companyId);
                scpr.setSchoolId(WebUtils.getCurrentUserSchoolId(request));
                scpr.setBussinessType("REDIRECT_FRONT_OPENCLASS");
                scpr.setSysType(0);
                SysConfigPageRedirect rect = sysConfigPageRedirectServiceImpl.findPageRedirect(scpr);
                /*
                 * if(rect != null){ rect.setStatus(0);
                 * sysConfigPageRedirectServiceImpl.update(rect); }else
                 */
                if (rect == null) {
                    scpr.setTemplateId(2);
                    scpr.setStatus(1);
                    scpr.setLink("openClass/openClass");
                    sysConfigPageRedirectServiceImpl.insert(scpr);
                }
            }

            if (sc.getGroupCode().equals("SERVICE_TEACHER")) {
                SysPageHeadFoot sphf = new SysPageHeadFoot();
                sphf.setCompanyId(companyId);
                sphf.setSchoolId(WebUtils.getCurrentUserSchoolId(request));
                sphf.setPageHeadType("SYS_PAGE_HEAD_TEACHER");
                SysPageHeadFoot head = sysPageHeadFootServiceImpl.findHeadFoot(sphf);

                if (head == null) {
                    sphf.setValidFlag(1);
                    sphf.setName("名师专题");
                    sphf.setCreateTime(new Date());
                    sphf.setSequence(9);
                    sphf.setParentId(0);
                    sphf.setOpenType("blank");
                    sphf.setPageType("head");
                    sphf.setUrl("sysConfigTeacher/teachers");
                    sphf.setCreator(WebUtils.getCurrentUserId(request));
                    sysPageHeadFootServiceImpl.insert(sphf);

                } else {
                    head.setValidFlag(1);
                    head.setUpdateTime(new Date());
                    head.setUpdator(WebUtils.getCurrentUserId(request));
                    sysPageHeadFootServiceImpl.update(head);
                }
                // SysConfigPageRedirect scpr = new SysConfigPageRedirect();
                // scpr.setCompanyId(WebUtils.getCurrentCompanyId());
                // scpr.setSchoolId(WebUtils.getCurrentUserSchoolId(request));
                // scpr.setBussinessType("REDIRECT_FRONT_TEACHER");
                // scpr.setSysType(0);
                // SysConfigPageRedirect rect =
                // sysConfigPageRedirectServiceImpl.findPageRedirect(scpr);
                // if(rect == null){
                // scpr.setTemplateId(2);
                // scpr.setStatus(1);
                // scpr.setLink("sysConfigTeacher/teachers");
                // sysConfigPageRedirectServiceImpl.insert(scpr);
                // }
            }

            // 课程包
            if (sc.getGroupCode().equals("SERVICE_CLASS_PACKAGE")) {
                SysPageHeadFoot sphf = new SysPageHeadFoot();
                sphf.setCompanyId(companyId);
                sphf.setSchoolId(WebUtils.getCurrentUserSchoolId(request));
                sphf.setPageHeadType("SYS_PAGE_HEAD_CLASS_PACKAGE");
                SysPageHeadFoot head = sysPageHeadFootServiceImpl.findHeadFoot(sphf);

                if (head == null) {
                    sphf.setValidFlag(1);
                    sphf.setName("课程包");
                    sphf.setCreateTime(new Date());
                    sphf.setSequence(10);
                    sphf.setParentId(0);
                    sphf.setOpenType("blank");
                    sphf.setPageType("head");
                    sphf.setUrl("classPackage/findAll");
                    sphf.setCreator(WebUtils.getCurrentUserId(request));
                    sysPageHeadFootServiceImpl.insert(sphf);
                } else {
                    head.setValidFlag(1);
                    head.setUpdateTime(new Date());
                    head.setUpdator(WebUtils.getCurrentUserId(request));
                    sysPageHeadFootServiceImpl.update(head);
                }
            }

            if (sc.getGroupCode().equals("SERVICE_QUESTION_ANSWER")) {
                CompanyFunctionSet companyFunctionSet = new CompanyFunctionSet();
                companyFunctionSet.setCompanyId(companyId);
                companyFunctionSet.setFunctionCode("QUESTION_CLASSIFY_TYPE");
                List<CompanyFunctionSet> companyFunctionSetList = companyFunctionSetServiceImpl.findCompanyFunctionSetByPage(companyFunctionSet);
                CompanyFunctionSet cfs = companyFunctionSetList != null && companyFunctionSetList.size() > 0 ? companyFunctionSetList.get(0) : null;

                if (cfs == null) {
                    cfs = new CompanyFunctionSet();
                    cfs.setCompanyId(companyId);
                    cfs.setFunctionCode("QUESTION_CLASSIFY_TYPE");
                    cfs.setFunctionName("问答分类");
                    cfs.setContent("0：自定义分类。1：课程分类");
                    cfs.setStatus("0");
                    companyFunctionSetServiceImpl.insert(cfs);
                }
                // 是否开启标签

                companyFunctionSet = new CompanyFunctionSet();
                companyFunctionSet.setCompanyId(companyId);
                companyFunctionSet.setFunctionCode("QUESTION_LABEL_STATE");
                companyFunctionSetList = companyFunctionSetServiceImpl.findCompanyFunctionSetByPage(companyFunctionSet);
                CompanyFunctionSet cflable = companyFunctionSetList != null && companyFunctionSetList.size() > 0 ? companyFunctionSetList.get(0) : null;

                if (cflable == null) {
                    cflable = new CompanyFunctionSet();
                    cflable.setCompanyId(companyId);
                    cflable.setFunctionCode("QUESTION_LABEL_STATE");
                    cflable.setFunctionName("自定义问答标签是否开启");
                    cflable.setContent("0：关闭。1：开启");
                    cflable.setStatus("0");
                    companyFunctionSetServiceImpl.insert(cflable);
                }

                // 学生观看权限
                companyFunctionSet = new CompanyFunctionSet();
                companyFunctionSet.setCompanyId(companyId);
                companyFunctionSet.setFunctionCode("STUDENT_SACN_QUESTION_AUTHO");
                companyFunctionSetList = companyFunctionSetServiceImpl.findCompanyFunctionSetByPage(companyFunctionSet);
                CompanyFunctionSet cfstusel = companyFunctionSetList != null && companyFunctionSetList.size() > 0 ? companyFunctionSetList.get(0) : null;
                if (cfstusel == null) {
                    cfstusel = new CompanyFunctionSet();
                    cfstusel.setCompanyId(companyId);
                    cfstusel.setFunctionCode("STUDENT_SACN_QUESTION_AUTHO");
                    cfstusel.setFunctionName("学生观看课程问答权限");
                    cfstusel.setContent("1：购买本课程学员，2：所有付费学员，3：所有登陆用户，4：所有用户");
                    cfstusel.setStatus("1");
                    companyFunctionSetServiceImpl.insert(cfstusel);
                }

                // 学生回复权限
                companyFunctionSet = new CompanyFunctionSet();
                companyFunctionSet.setCompanyId(companyId);
                companyFunctionSet.setFunctionCode("STUDENT_ANSWER_QUESTION_AUTHO");
                companyFunctionSetList = companyFunctionSetServiceImpl.findCompanyFunctionSetByPage(companyFunctionSet);
                CompanyFunctionSet cfstuans = companyFunctionSetList != null && companyFunctionSetList.size() > 0 ? companyFunctionSetList.get(0) : null;
                if (cfstuans == null) {
                    cfstuans = new CompanyFunctionSet();
                    cfstuans.setCompanyId(companyId);
                    cfstuans.setFunctionCode("STUDENT_ANSWER_QUESTION_AUTHO");
                    cfstuans.setFunctionName("学生回复课程问答权限");
                    cfstuans.setContent("1：购买本课程学员，2：所有付费学员，3：所有登陆用户");
                    cfstuans.setStatus("1");
                    companyFunctionSetServiceImpl.insert(cfstuans);
                }

                // 课程问答功能

                companyFunctionSet = new CompanyFunctionSet();
                companyFunctionSet.setCompanyId(companyId);
                companyFunctionSet.setFunctionCode("COURSE_QUESTION_FUNCTION");
                companyFunctionSetList = companyFunctionSetServiceImpl.findCompanyFunctionSetByPage(companyFunctionSet);
                CompanyFunctionSet cfclassg = companyFunctionSetList != null && companyFunctionSetList.size() > 0 ? companyFunctionSetList.get(0) : null;
                if (cfclassg == null) {
                    cfclassg = new CompanyFunctionSet();
                    cfclassg.setCompanyId(companyId);
                    cfclassg.setFunctionCode("COURSE_QUESTION_FUNCTION");
                    cfclassg.setFunctionName("课程问答功能是否开启");
                    cfclassg.setContent("0: 关闭；1：开启");
                    cfclassg.setStatus("0");
                    companyFunctionSetServiceImpl.insert(cfclassg);
                }

                // 课程问答权限
                companyFunctionSet = new CompanyFunctionSet();
                companyFunctionSet.setCompanyId(companyId);
                companyFunctionSet.setFunctionCode("COURSE_QUESTION_AUTH");
                companyFunctionSetList = companyFunctionSetServiceImpl.findCompanyFunctionSetByPage(companyFunctionSet);
                CompanyFunctionSet cfclassa = companyFunctionSetList != null && companyFunctionSetList.size() > 0 ? companyFunctionSetList.get(0) : null;
                if (cfclassa == null) {
                    cfclassa = new CompanyFunctionSet();
                    cfclassa.setCompanyId(companyId);
                    cfclassa.setFunctionCode("COURSE_QUESTION_AUTH");
                    cfclassa.setFunctionName("课程问答权限是否开启");
                    cfclassa.setContent("0: 关闭；1：开启");
                    cfclassa.setStatus("0");
                    companyFunctionSetServiceImpl.insert(cfclassa);
                }

                // 查询是否有标签
                param.clear();
                param.put("companyId", companyId);
                param.put("schoolId", WebUtils.getCurrentUserSchoolId(request));
                param.put("classType", 1);
                List<QuestionClassify> qcone = questionClassifyServiceImpl.findClassifyByCompany(param);
                if (qcone == null || qcone.size() == 0) {
                    QuestionClassify qc = new QuestionClassify();
                    qc.setClassifyName("社区问答");
                    qc.setCompanyId(companyId);
                    qc.setSchoolId(WebUtils.getCurrentUserSchoolId(request));
                    qc.setClassType(1);
                    qc.setDelFlag(1);
                    qc.setCreateTime(new Date());
                    questionClassifyServiceImpl.insert(qc);
                }

                SysPageHeadFoot sphf = new SysPageHeadFoot();
                sphf.setCompanyId(companyId);
                sphf.setSchoolId(WebUtils.getCurrentUserSchoolId(request));
                sphf.setPageHeadType("SYS_PAGE_HEAD_QUESTION");
                SysPageHeadFoot head = sysPageHeadFootServiceImpl.findHeadFoot(sphf);
                /*
                 * if(head != null){ head.setValidFlag(0);
                 * head.setUpdateTime(new Date());
                 * head.setUpdator(WebUtils.getCurrentUserId(request));
                 * sysPageHeadFootServiceImpl.update(head); }else
                 */
                if (head == null) {
                    sphf.setValidFlag(1);
                    sphf.setName("问答");
                    sphf.setCreateTime(new Date());
                    sphf.setParentId(0);
                    sphf.setSequence(10);
                    sphf.setOpenType("self");
                    sphf.setPageType("head");
                    sphf.setUrl("question/comQuestionIndex");
                    sphf.setCreator(WebUtils.getCurrentUserId(request));
                    sysPageHeadFootServiceImpl.insert(sphf);
                } else {
                    head.setValidFlag(1);
                    head.setUpdateTime(new Date());
                    head.setUpdator(WebUtils.getCurrentUserId(request));
                    sysPageHeadFootServiceImpl.update(head);
                }
            }
            // 学员心声
            if (sc.getGroupCode().equals("SERVICE_STUDENT_ASPIRATIONS")) {
                SysPageHeadFoot sphf = new SysPageHeadFoot();
                sphf.setCompanyId(companyId);
                sphf.setSchoolId(WebUtils.getCurrentUserSchoolId(request));
                sphf.setPageHeadType("SERVICE_STUDENT_ASPIRATIONS");
                SysPageHeadFoot head = sysPageHeadFootServiceImpl.findHeadFoot(sphf);

                if (head == null) {
                    sphf.setValidFlag(1);
                    sphf.setName("学员心声");
                    sphf.setCreateTime(new Date());
                    sphf.setSequence(10);
                    sphf.setParentId(0);
                    sphf.setOpenType("blank");
                    sphf.setPageType("head");
                    sphf.setUrl("studentStar/findByPage");
                    sphf.setCreator(WebUtils.getCurrentUserId(request));
                    sysPageHeadFootServiceImpl.insert(sphf);
                } else {
                    head.setValidFlag(1);
                    head.setUpdateTime(new Date());
                    head.setUpdator(WebUtils.getCurrentUserId(request));
                    sysPageHeadFootServiceImpl.update(head);
                }
            }
            relogin();
            json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
            return json;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            json.put(JsonMsg.MSG, JsonMsg.INFORMATION);
            return json;
        }
    }

    /**
     * 
     * Class Name: CompanyController.java
     * 
     * @Description: 关闭服务
     * @author 周文斌
     * @date 2015-8-13 下午6:03:22
     * @version 1.0
     * @param types
     * @return
     */
    @ResponseBody
    @RequestMapping("/colseService")
    public JSONObject colseService(HttpServletRequest request, SysConfigService sc) {
        JSONObject json = new JSONObject();
        try {
            sc.setCompanyId(WebUtils.getCurrentCompanyId());
            sc.setDelFlag(0);
            sc.setUpdateTime(new Date());
            sc.setUpdator(WebUtils.getCurrentUserId(request));
            // 查询是否存在
            SysConfigService ser = sysConfigServiceServiceImpl.findExist(sc);
            if (ser == null) {
                sysConfigServiceServiceImpl.insert(sc);
            } else {
                sc.setId(ser.getId());
                sysConfigServiceServiceImpl.update(sc);
            }

            if (sc.getGroupCode().equals("SERVICE_TIKU")) {
                SysPageHeadFoot sphf = new SysPageHeadFoot();
                sphf.setCompanyId(WebUtils.getCurrentCompanyId());
                sphf.setSchoolId(WebUtils.getCurrentUserSchoolId(request));
                sphf.setPageHeadType("SYS_PAGE_HEAD_TIKU");
                SysPageHeadFoot head = sysPageHeadFootServiceImpl.findHeadFoot(sphf);
                if (head != null) {
                    // head.setValidFlag(0);
                    // head.setUpdateTime(new Date());
                    // head.setUpdator(WebUtils.getCurrentUserId(request));
                    sysPageHeadFootServiceImpl.deleteSysPageHeadFootById(head.getId());
                } else {
                    sphf.setValidFlag(0);
                    sphf.setName("题库");
                    sphf.setCreateTime(new Date());
                    sphf.setSequence(9);
                    sphf.setParentId(0);
                    sphf.setOpenType("self");
                    sphf.setPageType("head");
                    sphf.setUrl("tikuIndex/toIndex");
                    sphf.setCreator(WebUtils.getCurrentUserId(request));
                    sysPageHeadFootServiceImpl.insert(sphf);
                }
            }

            if (sc.getGroupCode().equals("SERVICE_FACE")) {
                SysConfigService serv = new SysConfigService();
                serv.setCompanyId(WebUtils.getCurrentCompanyId());
                serv.setDelFlag(0);
                serv.setUpdateTime(new Date());
                serv.setUpdator(WebUtils.getCurrentUserId(request));
                serv.setGroupCode("SERVICE_AGENT");
                SysConfigService service = sysConfigServiceServiceImpl.findExist(serv);
                if (service == null) {
                    sysConfigServiceServiceImpl.insert(serv);
                } else {
                    serv.setId(service.getId());
                    sysConfigServiceServiceImpl.update(serv);
                }
            }

            if (sc.getGroupCode().equals("SERVICE_OPENCLASS")) {
                SysPageHeadFoot sphf = new SysPageHeadFoot();
                sphf.setCompanyId(WebUtils.getCurrentCompanyId());
                sphf.setSchoolId(WebUtils.getCurrentUserSchoolId(request));
                sphf.setPageHeadType("SYS_PAGE_HEAD_OPENCLASS");
                SysPageHeadFoot head = sysPageHeadFootServiceImpl.findHeadFoot(sphf);
                if (head != null) {
                    // head.setValidFlag(0);
                    // head.setUpdateTime(new Date());
                    // head.setUpdator(WebUtils.getCurrentUserId(request));
                    // sysPageHeadFootServiceImpl.update(head);
                    sysPageHeadFootServiceImpl.deleteSysPageHeadFootById(head.getId());
                } else {
                    sphf.setValidFlag(0);
                    sphf.setName("公开课");
                    sphf.setCreateTime(new Date());
                    sphf.setParentId(0);
                    sphf.setSequence(8);
                    sphf.setOpenType("blank");
                    sphf.setPageType("head");
                    sphf.setUrl("liveOpenCourse/toOpenClass");
                    sphf.setCreator(WebUtils.getCurrentUserId(request));
                    sysPageHeadFootServiceImpl.insert(sphf);

                }
                // 添加默认图片
                SysCyclePic scp = new SysCyclePic();
                scp.setCompanyId(WebUtils.getCurrentCompanyId());
                scp.setSchoolId(WebUtils.getCurrentUserSchoolId(request));
                scp.setPicType("openClass");
                List<SysCyclePic> scpList = sysCyclePicServiceImpl.findSysCyclePic(scp);
                if (scpList == null || scpList.size() == 0) {
                    scp.setPicUrl("cycllepic/1/20151015/73353d92-90fd-4a2f-ac75-989bb4031e93.png");
                    scp.setValidFlag(1);
                    scp.setCreateTime(new Date());
                    scp.setCreator(WebUtils.getCurrentUserId(request));
                    scp.setPicSequence(1);
                    sysCyclePicServiceImpl.insert(scp);
                }

                SysConfigPageRedirect scpr = new SysConfigPageRedirect();
                scpr.setCompanyId(WebUtils.getCurrentCompanyId());
                scpr.setSchoolId(WebUtils.getCurrentUserSchoolId(request));
                scpr.setBussinessType("REDIRECT_FRONT_OPENCLASS");
                scpr.setSysType(0);
                SysConfigPageRedirect rect = sysConfigPageRedirectServiceImpl.findPageRedirect(scpr);
                /*
                 * if(rect != null){ rect.setStatus(0);
                 * sysConfigPageRedirectServiceImpl.update(rect); }else
                 */
                if (rect == null) {
                    scpr.setTemplateId(2);
                    scpr.setStatus(1);
                    scpr.setLink("openClass/openClass");
                    sysConfigPageRedirectServiceImpl.insert(scpr);
                }
            }

            if (sc.getGroupCode().equals("SERVICE_TEACHER")) {
                SysPageHeadFoot sphf = new SysPageHeadFoot();
                sphf.setCompanyId(WebUtils.getCurrentCompanyId());
                sphf.setSchoolId(WebUtils.getCurrentUserSchoolId(request));
                sphf.setPageHeadType("SYS_PAGE_HEAD_TEACHER");
                SysPageHeadFoot head = sysPageHeadFootServiceImpl.findHeadFoot(sphf);
                if (head != null) {
                    // head.setValidFlag(0);
                    // head.setUpdateTime(new Date());
                    // head.setUpdator(WebUtils.getCurrentUserId(request));
                    // sysPageHeadFootServiceImpl.update(head);
                    sysPageHeadFootServiceImpl.deleteSysPageHeadFootById(head.getId());
                } else {
                    sphf.setValidFlag(0);
                    sphf.setName("名师专题");
                    sphf.setCreateTime(new Date());
                    sphf.setParentId(0);
                    sphf.setSequence(9);
                    sphf.setOpenType("blank");
                    sphf.setPageType("head");
                    sphf.setUrl("sysConfigTeacher/teachers");
                    sphf.setCreator(WebUtils.getCurrentUserId(request));
                    sysPageHeadFootServiceImpl.insert(sphf);

                }

                // SysConfigPageRedirect scpr = new SysConfigPageRedirect();
                // scpr.setCompanyId(WebUtils.getCurrentCompanyId());
                // scpr.setSchoolId(WebUtils.getCurrentUserSchoolId(request));
                // scpr.setBussinessType("REDIRECT_FRONT_TEACHER");
                // scpr.setSysType(0);
                // SysConfigPageRedirect rect =
                // sysConfigPageRedirectServiceImpl.findPageRedirect(scpr);
                //
                // if(rect == null){
                // scpr.setTemplateId(2);
                // scpr.setStatus(1);
                // scpr.setLink("sysConfigTeacher/teachers");
                // sysConfigPageRedirectServiceImpl.insert(scpr);
                // }
            }
            if (sc.getGroupCode().equals("SERVICE_CLASS_PACKAGE")) {
                SysPageHeadFoot sphf = new SysPageHeadFoot();
                sphf.setCompanyId(WebUtils.getCurrentCompanyId());
                sphf.setSchoolId(WebUtils.getCurrentUserSchoolId(request));
                sphf.setPageHeadType("SYS_PAGE_HEAD_CLASS_PACKAGE");
                SysPageHeadFoot head = sysPageHeadFootServiceImpl.findHeadFoot(sphf);
                if (head != null) {
                    // head.setValidFlag(0);
                    // head.setUpdateTime(new Date());
                    // head.setUpdator(WebUtils.getCurrentUserId(request));
                    // sysPageHeadFootServiceImpl.update(head);
                    sysPageHeadFootServiceImpl.deleteSysPageHeadFootById(head.getId());
                } else {
                    sphf.setValidFlag(0);
                    sphf.setName("课程包");
                    sphf.setCreateTime(new Date());
                    sphf.setParentId(0);
                    sphf.setSequence(10);
                    sphf.setOpenType("blank");
                    sphf.setPageType("head");
                    sphf.setUrl("classPackage/findAll");
                    sphf.setCreator(WebUtils.getCurrentUserId(request));
                    sysPageHeadFootServiceImpl.insert(sphf);

                }
            }
            if (sc.getGroupCode().equals("SERVICE_QUESTION_ANSWER")) {
                SysPageHeadFoot sphf = new SysPageHeadFoot();
                sphf.setCompanyId(WebUtils.getCurrentCompanyId());
                sphf.setSchoolId(WebUtils.getCurrentUserSchoolId(request));
                sphf.setPageHeadType("SYS_PAGE_HEAD_QUESTION");
                SysPageHeadFoot head = sysPageHeadFootServiceImpl.findHeadFoot(sphf);
                if (head != null) {
                    // head.setValidFlag(0);
                    // head.setUpdateTime(new Date());
                    // head.setUpdator(WebUtils.getCurrentUserId(request));
                    // sysPageHeadFootServiceImpl.update(head);
                    sysPageHeadFootServiceImpl.deleteSysPageHeadFootById(head.getId());
                } else {
                    sphf.setValidFlag(0);
                    sphf.setName("问答");
                    sphf.setCreateTime(new Date());
                    sphf.setSequence(10);
                    sphf.setParentId(0);
                    sphf.setOpenType("self");
                    sphf.setPageType("head");
                    sphf.setUrl("question/comQuestionIndex");
                    sphf.setCreator(WebUtils.getCurrentUserId(request));
                    sysPageHeadFootServiceImpl.insert(sphf);
                }
            }

            // 会员
            if (sc.getGroupCode().equals("SERVICE_MEMBER")) {
                SysPageHeadFoot sphf = new SysPageHeadFoot();
                sphf.setCompanyId(WebUtils.getCurrentCompanyId());
                sphf.setSchoolId(WebUtils.getCurrentUserSchoolId(request));
                sphf.setPageHeadType("SYS_PAGE_HEAD_MEMBER");
                SysPageHeadFoot head = sysPageHeadFootServiceImpl.findHeadFoot(sphf);
                if (head != null) {
                    // 删除页头导航
                    sysPageHeadFootServiceImpl.deleteSysPageHeadFootById(head.getId());
                }
                // 关闭会员开关
                CompanyMemberConfig con = new CompanyMemberConfig();
                con.setCompanyId(WebUtils.getCurrentCompanyId());
                CompanyMemberConfig memberConfig = companyMemberConfigServiceImpl.queryMemberSets(con);
                if (null != memberConfig && null != memberConfig.getMemberPage() && memberConfig.getMemberPage() == 1) {
                    con.setMemberPage(0);
                    con.setId(memberConfig.getId());
                    companyMemberConfigServiceImpl.update(con);
                }
            }

            // 学员心声
            if (sc.getGroupCode().equals("SERVICE_STUDENT_ASPIRATIONS")) {
                SysPageHeadFoot sphf = new SysPageHeadFoot();
                sphf.setCompanyId(WebUtils.getCurrentCompanyId());
                sphf.setSchoolId(WebUtils.getCurrentUserSchoolId(request));
                sphf.setPageHeadType("SERVICE_STUDENT_ASPIRATIONS");
                SysPageHeadFoot head = sysPageHeadFootServiceImpl.findHeadFoot(sphf);

                if (head != null) {
                    sysPageHeadFootServiceImpl.deleteSysPageHeadFootById(head.getId());
                } else {
                    sphf.setValidFlag(0);
                    sphf.setName("学员心声");
                    sphf.setCreateTime(new Date());
                    sphf.setParentId(0);
                    sphf.setSequence(11);
                    sphf.setOpenType("blank");
                    sphf.setPageType("head");
                    sphf.setUrl("studentStar/findByPage");
                    sphf.setCreator(WebUtils.getCurrentUserId(request));
                    sysPageHeadFootServiceImpl.insert(sphf);
                }
            }
            relogin();
            json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
            return json;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            json.put(JsonMsg.MSG, JsonMsg.INFORMATION);
            return json;
        }
    }

    @ResponseBody
    @RequestMapping("/selauth")
    public JSONObject selauth(HttpServletRequest request, SysConfigServiceGroup group, String actions) {
        JSONObject json = new JSONObject();
        Integer companyId = WebUtils.getCurrentCompanyId();
        try {
            if (!validauth(WebUtils.getCurrentUserId(request))) {
                json.put(JsonMsg.MSG, "auth");
                return json;
            }
            if("0".equals(WebUtils.getCurrentIsArea())){
            	json.put(JsonMsg.MSG,"no_auth");
            	return json;
            }
            Company company = companyServiceImpl.findCompanyById(companyId);
            if ("open".equals(actions) && (company.getMemberLevel().equals(12) || company.getMemberLevel().equals(13))) {
                // 根据当前服务查询是否可以开启
                Integer ssd = WebUtils.getSysServiceDredge(group.getGroupCode());
                if (ssd != null) {
                    if (ssd.equals(0)) {
                        json.put(JsonMsg.MSG, "notopen");
                        return json;
                    }
                }
            }
            // 查询权限id
            List<SysConfigServiceGroup> groupList = sysConfigServiceGroupImpl.findByCode(group.getGroupCode());
            // 获得id
            if (groupList != null && groupList.size() > 0) {
                List<Integer> ids = new ArrayList<Integer>();
                for (SysConfigServiceGroup g : groupList) {
                    ids.add(g.getPrivilegeId());
                }
                // 查询权限名
                List<String> nameList = authPrivilegeServiceImpl.findNameById(ids);
                json.put(JsonMsg.MSG, nameList);
                return json;
            } else {
                json.put(JsonMsg.MSG, "notAuth");
                return json;
            }
        } catch (Exception e) {
            log.error("查询权限错误:" + e.getMessage(), e);
            e.printStackTrace();
            json.put(JsonMsg.MSG, JsonMsg.ERROR);
            return json;
        }
    }

    private void relogin() {
        Subject subject = SecurityUtils.getSubject();
        Users users = usersServiceImpl.queryUserByName(subject.getPrincipal().toString());
        subject.logout();
        UsernamePasswordToken token = new UsernamePasswordToken(users.getUsername(), users.getPassword());
        subject.login(token);
        Session session = subject.getSession();
        session.setAttribute("loginUser", users);
        // 设置登录后的状态
        setUserStatus();
        Company company = companyServiceImpl.findCompanyById(users.getCompanyId());
        if (company.getMemberLevel() < 20) {
            List<SysServiceDredgeVo> ssdVo = sysServiceDredgeImpl.findDredgeByCom(company.getId());
            if (ssdVo != null) {
                for (SysServiceDredgeVo s : ssdVo) {
                    session.setAttribute(s.getName(), (s.getStatus() != null ? s.getStatus() : 0));
                }
            }
        }
        String status = String.valueOf(company.getStatus());

        try {
            // 将机构使用视频服务存入session
            CompanyMemberService service = companyMemberServiceServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
            session.setAttribute("useVideo", service.getVideoServiceProvider());

            // 将公司课程版本存入session(1表示简易版，0或空值表示复杂版)
            CompanyFunctionSet search = new CompanyFunctionSet();
            search.setCompanyId(WebUtils.getCurrentCompanyId());
            search.setFunctionCode("COMPANY_FUNCTION_COURSE");
            CompanyFunctionSet functionSet = companyFunctionSetImpl.findCompanyUseCourse(search);
            if (functionSet instanceof Object) {
                session.setAttribute("courseFunction", functionSet.getStatus());
            } else {
                session.setAttribute("courseFunction", "");
            }
        } catch (Exception e) {
            log.error("存入session值失败", e);
            e.printStackTrace();
        }

        WebUtils.setSessionAttribute("company_status", status);// 公司认证标记
        WebUtils.setSessionAttribute("company_buy_flag", company.getBuyFlag());// 购买标记
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
     * 
     * Class Name: CompanyController.java
     * 
     * @Description: 进入跑马灯设置
     * @author 周文斌
     * @date 2015-10-12 下午12:35:35
     * @version 1.0
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/marqueeOption")
    public String marqueeOptino(Model model, HttpServletRequest request) {
        // 获得 机构 id
        Integer companyId = WebUtils.getCurrentCompanyId();
        // 查询 机构信息
        Company company = companyServiceImpl.findCompanyById(companyId);
        CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
        CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);

        // 用户信息
        Users user = usersServiceImpl.findUserByCompanyIdAndUserType(companyId);

        // 根据companyId查询审核信息
        //Integer caId = companyAuthorityServiceImpl.findByCompanyId(companyId);

        CourseVideoMarquee cvm = new CourseVideoMarquee();
        cvm.setCompanyId(companyId);
        CourseVideoMarquee marquee = courseVideoMarqueeServiceImpl.findMarqueeByCompanyId(cvm);

       // model.addAttribute("caId", caId);
        model.addAttribute("company", company);
        model.addAttribute("user", user);
        model.addAttribute("cms", cms);
        model.addAttribute("css", css);
        model.addAttribute("marquee", marquee);
        return "operate/live/marquee";
    }

    /**
     * 
     * Class Name: CompanyController.java
     * 
     * @Description: 设置跑马灯 启用禁用
     * @author 周文斌
     * @date 2015-10-12 下午4:46:47
     * @version 1.0
     * @param request
     * @param stauts
     * @return
     */
    @ResponseBody
    @RequestMapping("/marqueeState")
    public JSONObject marqueeState(HttpServletRequest request, Integer stauts) {
        JSONObject json = new JSONObject();
        Integer companyId = WebUtils.getCurrentCompanyId();
        CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
        try {
            CourseVideoMarquee cvm = new CourseVideoMarquee();
            cvm.setCompanyId(companyId);
            CourseVideoMarquee marquee = courseVideoMarqueeServiceImpl.findMarqueeByCompanyId(cvm);
            if (marquee == null) {
                cvm.setCycleTime(-1);
                cvm.setSingleCostTime(5000);
                cvm.setFontSize(26);
                cvm.setFontColor("0x3366ff");
                cvm.setMarqueeLineId(6);
                cvm.setCreateTime(new Date());
                cvm.setCreator(WebUtils.getCurrentUserId(request));
                cvm.setStauts(stauts);
                courseVideoMarqueeServiceImpl.insert(cvm);
            } else {
                marquee.setStauts(stauts);
                courseVideoMarqueeServiceImpl.update(marquee);
            }
            json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
            return json;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            json.put(JsonMsg.MSG, JsonMsg.INFORMATION);
            return json;
        }
    }

    @ResponseBody
    @RequestMapping("/selMarqueeLine")
    public JSONObject selMarqueeLine(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        Integer companyId = WebUtils.getCurrentCompanyId();
        try {
            CourseVideoMarquee cvm = new CourseVideoMarquee();
            cvm.setCompanyId(companyId);
            CourseVideoMarquee marquee = courseVideoMarqueeServiceImpl.findMarqueeByCompanyId(cvm);
            if (marquee != null) {
                json.put("marquee", marquee);
            }
            json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
            return json;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            json.put(JsonMsg.MSG, JsonMsg.INFORMATION);
            return json;
        }
    }

    @ResponseBody
    @RequestMapping("/saveMarquee")
    public JSONObject saveMarquee(HttpServletRequest request, CourseVideoMarquee marquee) {
        JSONObject json = new JSONObject();
        CourseVideoMarquee cvm = new CourseVideoMarquee();
        Integer companyId = WebUtils.getCurrentCompanyId();
        CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
        cvm.setCompanyId(companyId);
        try {
            CourseVideoMarquee mar = courseVideoMarqueeServiceImpl.findMarqueeByCompanyId(cvm);
            marquee.setId(mar.getId());
            courseVideoMarqueeServiceImpl.update(marquee);
            json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
            return json;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            json.put(JsonMsg.MSG, JsonMsg.INFORMATION);
            return json;
        }
    }

    /**
     * 
     * Class Name: CompanyController.java
     * 
     * @Description: 验证权限
     * @author 周文斌
     * @date 2015-10-14 上午11:30:43
     * @version 1.0
     * @param rquest
     * @return
     */
    private Boolean validauth(Integer userId) {
        if (authRoleServiceImpl.hasRoleFlag(userId,WebUtils.getCurrentCompanyId())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 
     * Class Name: CompanyController.java
     * 
     * @Description: 检查是否有权限去认证
     * @author 周文斌
     * @date 2016-1-27 下午12:42:54
     * @version 1.0
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/authenShow")
    public JSONObject authenShow(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            if (validauth(WebUtils.getCurrentUserId(request))) {
                json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
            } else {
                json.put(JsonMsg.MSG, JsonMsg.ERROR);
            }
            return json;
        } catch (Exception e) {
            log.error("去认证出错，" + e.getMessage(), e);
            e.printStackTrace();
            json.put(JsonMsg.MSG, JsonMsg.INFORMATION);
            return json;
        }
    }

    /**
     * 
     * Class Name: CompanyController.java
     * 
     * @Description: 我们修改公司会员日期
     * @author 周文斌
     * @date 2016-3-23 下午6:27:46
     * @version 1.0
     * @param request
     * @param company
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateById")
    public JSONObject updateById(HttpServletRequest request, Integer id, Company company, CompanyMemberServiceChangelog cmsc) {
        JSONObject json = new JSONObject();
        company.setId(id);

        cmsc.setId(null);
        cmsc.setCompanyId(id);
        cmsc.setTableName("Company");
        cmsc.setUpdator(WebUtils.getCurrentUserId(request));
        cmsc.setChangeTime(new Date());
        try {
            companyServiceImpl.update(company);
            // 记录
            companyMemberServiceChangelogServiceImpl.insert(cmsc);

            json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
            return json;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            json.put(JsonMsg.MSG, "修改时出错");
            return json;
        }
    }

    /**
     * 
     * Class Name: CompanyController.java
     * 
     * @Description: 查询公司是否为在线版
     * @author zhang.zx
     * @date 2016年4月28日 上午11:10:05
     * @modifier
     * @modify-date 2016年4月28日 上午11:10:05
     * @version 1.0
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryCompany", method = RequestMethod.POST)
    public boolean queryByCompany(HttpServletRequest request) {
        boolean flag = true;
        Company com = companyServiceImpl.findCompanyById(WebUtils.getCurrentCompanyId());
        if (null != com && null != com.getServiceVersion() && "ONLINE_COUNT".equals(com.getServiceVersion())) {
            flag = false;
        }
        return flag;
    }

    /**
     * 
     * Class Name: CompanyController.java
     * 
     * @Description: 查询相关服务状态
     * @author zhang.zx
     * @date 2016年5月18日 下午3:39:24
     * @modifier
     * @modify-date 2016年5月18日 下午3:39:24
     * @version 1.0
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryServiceSet")
    public String querySeviceSet(SysConfigService sc) {
        sc.setCompanyId(WebUtils.getCurrentCompanyId());
        // 查询是否存在
        SysConfigService ser = sysConfigServiceServiceImpl.findExist(sc);
        if (null != ser && null != ser.getDelFlag() && ser.getDelFlag() == 1) {
            return SysConfigConstant.SHOW_fLAG;
        }
        return SysConfigConstant.HIDE_fLAG;
    }

    /**
     * 
     * @Description: 学科改名Module状态改变（修改开启，关闭）
     * @author dongshuai
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/editSubjectStatus", method = RequestMethod.POST)
    public String updateSubjectStatus(HttpServletRequest request, String status) {
        if (!"".equals(status) && null != status) {
            int companyId = WebUtils.getCurrentCompanyId();

            CompanyFunctionSet companyFunctionSet = new CompanyFunctionSet();
            companyFunctionSet.setCompanyId(companyId);
            companyFunctionSet.setFunctionCode("COMPANY_SUBJECT_CUSTOMNAME");
            List<CompanyFunctionSet> companyFunctionSetList = companyFunctionSetServiceImpl.findCompanyFunctionSetByPage(companyFunctionSet);
            CompanyFunctionSet cfs = companyFunctionSetList != null && companyFunctionSetList.size() > 0 ? companyFunctionSetList.get(0) : null;
            if (null != cfs) {
                if ("0".equals(status)) {
                    cfs.setStatus("1");
                } else if ("1".equals(status)) {
                    cfs.setStatus("0");
                }
            } else {
                return "false";
            }
            companyFunctionSetImpl.update(cfs);
            return "true";
        }
        return "false";
    }

    /**
     * 
     * @Description: 学科改名Module
     * @author dongshuai
     * @param request
     * @return
     */
    @RequestMapping(value = "/updateSubjectName")
    public String updateSubjectName(HttpServletRequest request, Model model) {
        int companyId = WebUtils.getCurrentCompanyId();
        Company company = companyServiceImpl.findCompanyById(companyId);
        model.addAttribute("company", company);

        CompanyFunctionSet companyFunctionSet = new CompanyFunctionSet();
        companyFunctionSet.setCompanyId(companyId);
        companyFunctionSet.setFunctionCode("COMPANY_SUBJECT_CUSTOMNAME");
        List<CompanyFunctionSet> companyFunctionSetList = companyFunctionSetServiceImpl.findCompanyFunctionSetByPage(companyFunctionSet);
        CompanyFunctionSet cfs = companyFunctionSetList != null && companyFunctionSetList.size() > 0 ? companyFunctionSetList.get(0) : null;
        if (null == cfs) {
            cfs = new CompanyFunctionSet();
            cfs.setCompanyId(companyId);
            cfs.setFunctionCode("COMPANY_SUBJECT_CUSTOMNAME");
            cfs.setFunctionName("自定义学科");
            cfs.setContent("0：关闭；1：开启");
            cfs.setStatus("0");
            cfs.setColumn1("学科");
            cfs.setColumn2("学科小类");
            companyFunctionSetImpl.insert(cfs);
        }
        model.addAttribute("cfs", cfs);
        CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
        CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
        model.addAttribute("cms", cms);
        model.addAttribute("css", css);
        return "system/updateSubjectName";
    }

    /**
     * 
     * @Description: 学科改名
     * @author dongshuai
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/editSubjectName", method = RequestMethod.POST)
    public String editSubjectName(HttpServletRequest request, String value, String type) {
        int companyId = WebUtils.getCurrentCompanyId();

        CompanyFunctionSet companyFunctionSet = new CompanyFunctionSet();
        companyFunctionSet.setCompanyId(companyId);
        companyFunctionSet.setFunctionCode("COMPANY_SUBJECT_CUSTOMNAME");
        List<CompanyFunctionSet> companyFunctionSetList = companyFunctionSetServiceImpl.findCompanyFunctionSetByPage(companyFunctionSet);
        CompanyFunctionSet cfs = companyFunctionSetList != null && companyFunctionSetList.size() > 0 ? companyFunctionSetList.get(0) : null;
        // type: 1:修改学科 2：修改学科小类
        if ("1".equals(type)) {
            cfs.setColumn1(value);
            companyFunctionSetImpl.update(cfs);
            return "true";
        } else if ("2".equals(type)) {
            cfs.setColumn2(value);
            companyFunctionSetImpl.update(cfs);
            return "true";
        }
        return "false";
    }

    /**
     * 
     * Class Name: CompanyController.java
     * 
     * @Description: 优惠券服务配置
     * @author dongshuai
     * @date 2016年6月20日
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/setCouponService")
    public String setCouponService(HttpServletRequest request, Model model) {
        // 公司配置
        int companyId = WebUtils.getCurrentCompanyId();
        Company company = companyServiceImpl.findCompanyById(companyId);
        CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
        CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
        model.addAttribute("company", company);
        model.addAttribute("cms", cms);
        model.addAttribute("css", css);
        // 初始化优惠券服务设置
        CompanyFunctionSet cfs = initCouponsService();
        model.addAttribute("cfs", cfs);
        // 初始化优惠券设置
        CompanyCouponsConfig ccc = initCouponsConfig();
        model.addAttribute("ccc", ccc);
        return "system/updateCouponService";
    }

    /**
     * 
     * Class Name: CompanyController.java
     * 
     * @Description: 更新优惠券设置
     * @author dongshuai
     * @date 2016年6月20日
     * @param type
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateCouponConfig", method = RequestMethod.POST)
    public boolean updateCouponConfig(String type) {
        CompanyCouponsConfig search = new CompanyCouponsConfig();
        search.setCompanyId(WebUtils.getCurrentCompanyId());
        CompanyCouponsConfig ccc = companyCouponsConfigServiceImpl.findByCompanyId(search);
        if (null != ccc) {
            ccc.setUseWay(Integer.parseInt(type));
            companyCouponsConfigServiceImpl.update(ccc);
        } else {
            return false;
        }
        return true;
    }

    /**
     * 
     * Class Name: CompanyController.java
     * 
     * @Description: 更新优惠券服务
     * @author dongshuai
     * @date 2016年6月20日
     * @param status
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateCouponService", method = RequestMethod.POST)
    public boolean updateCouponService(String type) {
        CompanyFunctionSet companyFunctionSet = new CompanyFunctionSet();
        companyFunctionSet.setCompanyId(WebUtils.getCurrentCompanyId());
        companyFunctionSet.setFunctionCode("COMPANY_COUPONS_SERVICE");
        List<CompanyFunctionSet> companyFunctionSetList = companyFunctionSetServiceImpl.findCompanyFunctionSetByPage(companyFunctionSet);
        CompanyFunctionSet cfs = companyFunctionSetList != null && companyFunctionSetList.size() > 0 ? companyFunctionSetList.get(0) : null;
        if (null != cfs) {
            cfs.setStatus(type);
            companyFunctionSetImpl.update(cfs);
        } else {
            return false;
        }
        return true;
    }

    /**
     * 
     * Class Name: CompanyController.java
     * 
     * @Description: 初始化优惠券服务设置
     * @author dongshuai
     * @date 2016年6月20日
     * @return
     */
    public CompanyFunctionSet initCouponsService() {

        CompanyFunctionSet companyFunctionSet = new CompanyFunctionSet();
        companyFunctionSet.setCompanyId(WebUtils.getCurrentCompanyId());
        companyFunctionSet.setFunctionCode("COMPANY_COUPONS_SERVICE");
        List<CompanyFunctionSet> companyFunctionSetList = companyFunctionSetServiceImpl.findCompanyFunctionSetByPage(companyFunctionSet);
        CompanyFunctionSet cfs = companyFunctionSetList != null && companyFunctionSetList.size() > 0 ? companyFunctionSetList.get(0) : null;
        if (null == cfs) {
            cfs = new CompanyFunctionSet();
            cfs.setCompanyId(WebUtils.getCurrentCompanyId());
            cfs.setFunctionCode("COMPANY_COUPONS_SERVICE");
            cfs.setFunctionName("优惠券服务");
            cfs.setContent("0：关闭；1：开启");
            cfs.setStatus("0");
            companyFunctionSetImpl.insert(cfs);
        }
        return cfs;
    }

    /**
     * 
     * Class Name: CompanyController.java
     * 
     * @Description: 初始化优惠券设置
     * @author dongshuai
     * @date 2016年6月20日
     * @return
     */
    public CompanyCouponsConfig initCouponsConfig() {
        CompanyCouponsConfig search = new CompanyCouponsConfig();
        search.setCompanyId(WebUtils.getCurrentCompanyId());
        CompanyCouponsConfig ccc = companyCouponsConfigServiceImpl.findByCompanyId(search);
        if (null == ccc) {
            search.setUseWay(1);
            companyCouponsConfigServiceImpl.insert(search);
            return search;
        }
        return ccc;
    }

    /**
     * 
     * Class Name: CompanyController.java
     * 
     * @Description: 根据服务开/关查看菜单隐藏/显示
     * @author dongshuai
     * @date 2017年2月9日 下午4:29:29
     * @modifier
     * @modify-date 2017年2月9日 下午4:29:29
     * @version 1.0
     * @param serviceString
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryServiceOpenFlag", method = RequestMethod.POST)
    public JSONObject queryServiceOpenFlag(String serviceString) {
        JSONObject json = new JSONObject();
        json.put(JsonMsg.RESULT, JsonMsg.SUCCESS);

        Map<String, Object> search = new HashMap<String, Object>();
        search.put("companyId", WebUtils.getCurrentCompanyId());
        search.put("service", serviceString);
        Integer flag = this.companyServiceImpl.queryServiceOpenFlag(search);

        if (flag == null) {
            json.put(JsonMsg.RESULT, JsonMsg.ERROR);
            return json;
        }
        json.put("flag", flag);
        return json;
    }
    
    /**
     * 
     * Class Name: CompanyController.java
     * @Description: 一级菜单中专站
     * @author 周文斌
     * @date 2017-4-13 下午2:26:39
     * @modify	2017-4-13 下午2:26:39
     * @version 
     * @param model
     * @param request
     * @param firstName
     * @return
     */
    @RequestMapping("/{firstName}/firstTransferStation")
    public String FirstTransferStation(Model model,HttpServletRequest request
    		,@PathVariable String firstName){
    	model.addAttribute("firstName", firstName);
    	return "menu/FirstTransferStation";
    }
}
