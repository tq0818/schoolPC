package com.yuxin.wx.controller.company;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
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

import com.yuxin.wx.api.company.ICompanyFunctionSetService;
import com.yuxin.wx.api.company.ICompanyLoginConfigService;
import com.yuxin.wx.api.company.ICompanyMemberServiceService;
import com.yuxin.wx.api.company.ICompanyRegisterConfigService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.CompanyFunctionSet;
import com.yuxin.wx.model.company.CompanyLoginConfig;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.model.company.CompanyRegisterConfig;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.utils.WebUtils;

/**
 * Controller of CompanyFunctionSet
 * 
 * @author chopin
 * @date 2016-5-24
 */
@Controller
@RequestMapping("/companyFunctionSet")
public class CompanyFunctionSetController {

    @Autowired
    private ICompanyFunctionSetService companyFunctionSetServiceImpl;
    @Autowired
    private ICompanyService companyService;
    @Autowired
    private ICompanyMemberServiceService companyMemberServiceServiceImpl;
    @Autowired
    private ICompanyServiceStaticService companyServiceStaticServiceImpl;
    @Autowired
    private ICompanyService companyServiceImpl;
    @Autowired
    private ICompanyLoginConfigService companyLoginConfigServiceImpl;
    @Autowired
    private ICompanyRegisterConfigService companyRegisterConfigServiceImpl;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model, CompanyFunctionSet search) {
        if (search == null) {
            search = new CompanyFunctionSet();
            // search.setPageSize(20);
        }
        model.addAttribute("list", companyFunctionSetServiceImpl.findCompanyFunctionSetByPage(search));
        return "companyFunctionSet/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(CompanyFunctionSet CompanyFunctionSet) {
        companyFunctionSetServiceImpl.insert(CompanyFunctionSet);
        return "redirect:/companyFunctionSet";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(CompanyFunctionSet CompanyFunctionSet) {
        companyFunctionSetServiceImpl.update(CompanyFunctionSet);
        return "redirect:/companyFunctionSet";
    }

    @RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
    public String del(Model model, @PathVariable Integer id) {
        companyFunctionSetServiceImpl.deleteCompanyFunctionSetById(id);
        return "redirect:/companyFunctionSet";
    }

    @ResponseBody
    @RequestMapping(value = "/{id:\\d+}", method = RequestMethod.GET)
    public CompanyFunctionSet getJson(Model model, @PathVariable Integer id) {
        return companyFunctionSetServiceImpl.findCompanyFunctionSetById(id);
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

    @RequestMapping("/stuLogin")
    public String stuLogin(Model model) {
        CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
        CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
        model.addAttribute("cms", cms);
        model.addAttribute("css", css);
        Integer companyId = WebUtils.getCurrentCompanyId();
        Company company = companyService.findCompanyById(companyId);
        model.addAttribute("company", company);

        CompanyFunctionSet companyFunctionSet = new CompanyFunctionSet();
        companyFunctionSet.setCompanyId(companyId);
        companyFunctionSet.setFunctionCode("COMAPNY_ALLOW_USER_DLOGIN");
        List<CompanyFunctionSet> companyFunctionSetList = companyFunctionSetServiceImpl.findCompanyFunctionSetByPage(companyFunctionSet);
        CompanyFunctionSet cfs = companyFunctionSetList != null && companyFunctionSetList.size() > 0 ? companyFunctionSetList.get(0) : null;
        model.addAttribute("cfs", cfs);
        // -----2016-7-5增加第三方登录配置
        CompanyLoginConfig search = new CompanyLoginConfig();
        search.setCompanyId(WebUtils.getCurrentCompanyId());
        search = companyLoginConfigServiceImpl.queryByCompanyId(search);
        // -----2016-9-20 查询学生max 是否开启 (1 开启 0 关闭)
        companyFunctionSet = new CompanyFunctionSet();
        companyFunctionSet.setCompanyId(companyId);
        companyFunctionSet.setStatus("1");
        companyFunctionSet.setFunctionCode("COMPANY_STUDENT_BIND");
        companyFunctionSet = companyFunctionSetServiceImpl.getCompanyFunctionSet(cfs);
        if (companyFunctionSet != null) {
            model.addAttribute("bind_status", "1");
        } else {
            model.addAttribute("bind_status", "0");
        }

        model.addAttribute("clc", search);

        return "system/configCompanyStuLogin";
    }

    @RequestMapping("/stuRegister")
    public String stuRegister(Model model) {
        CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
        CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
        model.addAttribute("cms", cms);
        model.addAttribute("css", css);
        Integer companyId = WebUtils.getCurrentCompanyId();
        Company company = companyService.findCompanyById(companyId);
        model.addAttribute("company", company);

        CompanyFunctionSet companyFunctionSet = new CompanyFunctionSet();
        companyFunctionSet.setCompanyId(companyId);
        companyFunctionSet.setFunctionCode("COMAPNY_ALLOW_USER_DLOGIN");
        List<CompanyFunctionSet> companyFunctionSetList = companyFunctionSetServiceImpl.findCompanyFunctionSetByPage(companyFunctionSet);
        CompanyFunctionSet cfs = companyFunctionSetList != null && companyFunctionSetList.size() > 0 ? companyFunctionSetList.get(0) : null;
        model.addAttribute("cfs", cfs);

        // 注册配置
        CompanyRegisterConfig crc = new CompanyRegisterConfig();
        CompanyRegisterConfig def = new CompanyRegisterConfig();
        crc.setCompanyId(WebUtils.getCurrentCompanyId());
        crc = companyRegisterConfigServiceImpl.queryByCompanyId(crc);
        if (crc.getRegisterAgreement() == null || "".equals(crc.getRegisterAgreement()) || crc.getRegisterAgreementFlag() == null) {
            def.setCompanyId(0);
            def = companyRegisterConfigServiceImpl.queryByCompanyId(def);
            crc.setRegisterAgreement(def.getRegisterAgreement());
            crc.setRegisterAgreementFlag(1);
            companyRegisterConfigServiceImpl.update(crc);
        }
        String register = def.getRegisterAgreement();
        def.setRegisterAgreement(register);
        model.addAttribute("crc", crc);
        model.addAttribute("def", def);
        // -----2016-9-20 查询学生max 是否开启 (1 开启 0 关闭)
        companyFunctionSet = new CompanyFunctionSet();
        companyFunctionSet.setCompanyId(WebUtils.getCurrentCompanyId());
        companyFunctionSet.setStatus("1");
        companyFunctionSet.setFunctionCode("COMPANY_STUDENT_BIND");
        companyFunctionSet = companyFunctionSetServiceImpl.getCompanyFunctionSet(cfs);
        if (companyFunctionSet != null) {
            model.addAttribute("bind_status", "1");
        } else {
            model.addAttribute("bind_status", "0");
        }

        return "system/configCompanyStuRegister";
    }

    @ResponseBody
    @RequestMapping("/setStuLogin")
    public Object setStuLogin(CompanyFunctionSet CompanyFunctionSet) {
        Integer id = CompanyFunctionSet.getId();
        if (id != null && !"".equals(id)) {
            companyFunctionSetServiceImpl.update(CompanyFunctionSet);
            return true;
        } else {// 保证一个机构只有一条配置信息：先查找，没有则添加
            Integer companyId = WebUtils.getCurrentCompanyId();
            CompanyFunctionSet companyFunctionSet = new CompanyFunctionSet();
            companyFunctionSet.setCompanyId(companyId);
            companyFunctionSet.setFunctionCode("COMAPNY_ALLOW_USER_DLOGIN");
            List<CompanyFunctionSet> companyFunctionSetList = companyFunctionSetServiceImpl.findCompanyFunctionSetByPage(companyFunctionSet);
            CompanyFunctionSet cfs = companyFunctionSetList != null && companyFunctionSetList.size() > 0 ? companyFunctionSetList.get(0) : null;
            if (cfs == null) {
                CompanyFunctionSet.setCompanyId(companyId);
                CompanyFunctionSet.setFunctionCode("COMAPNY_ALLOW_USER_DLOGIN");
                CompanyFunctionSet.setFunctionName("允许学员重复登录");
                CompanyFunctionSet.setContent("0：关闭 1：开启");
                companyFunctionSetServiceImpl.insert(CompanyFunctionSet);
            } else {
                CompanyFunctionSet.setId(cfs.getId());
                companyFunctionSetServiceImpl.update(CompanyFunctionSet);
            }
            return CompanyFunctionSet;
        }
    }

    /**
     * 
     * Class Name: CompanyFunctionSetController.java
     * 
     * @Description: 第三方登录配置
     * @author dongshuai
     * @date 2016年7月4日 下午6:13:00
     * @modifier
     * @modify-date 2016年7月4日 下午6:13:00
     * @version 1.0
     * @param kind
     * @return
     */
    @RequestMapping("/setThirdLogin/{kind}")
    public String setThirdLogin(@PathVariable String kind, Model model) {
        // 公司配置
        int companyId = WebUtils.getCurrentCompanyId();
        Company company = companyServiceImpl.findCompanyById(companyId);
        CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
        CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
        model.addAttribute("company", company);
        model.addAttribute("cms", cms);
        model.addAttribute("css", css);

        CompanyLoginConfig search = new CompanyLoginConfig();
        search.setCompanyId(WebUtils.getCurrentCompanyId());
        search = companyLoginConfigServiceImpl.queryByCompanyId(search);
        model.addAttribute("clc", search);
        if ("qq".equals(kind)) {
            model.addAttribute("kind", "qq");
            return "login/login-third-qq";
        } else if ("wb".equals(kind)) {
            model.addAttribute("kind", "wb");
            return "login/login-third-wb";
        } else if ("wx".equals(kind)) {
            model.addAttribute("kind", "wx");
            return "login/login-third-wx";
        }
        return "";
    }

    /**
     * 
     * Class Name: CompanyFunctionSetController.java
     * 
     * @Description: 课程购买设置
     * @author zhang.zx
     * @date 2016年8月10日 下午1:50:36
     * @modifier
     * @modify-date 2016年8月10日 下午1:50:36
     * @version 1.0
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/buySet")
    public String companyBuySet() {
        CompanyFunctionSet companyFunctionSet = new CompanyFunctionSet();
        companyFunctionSet.setCompanyId(WebUtils.getCurrentCompanyId());
        companyFunctionSet.setFunctionCode("COURSE_BUY_SET");
        List<CompanyFunctionSet> companyFunctionSetList = companyFunctionSetServiceImpl.findCompanyFunctionSetByPage(companyFunctionSet);
        CompanyFunctionSet cfs = companyFunctionSetList != null && companyFunctionSetList.size() > 0 ? companyFunctionSetList.get(0) : null;
        if (null != cfs && null != cfs.getStatus() && cfs.getStatus().equals("1")) {
            return "show";
        }
        return "hide";
    }

    @RequestMapping(value = "showRegister")
    public String showRegister(Model model) {
        CompanyRegisterConfig crc = new CompanyRegisterConfig();
        crc.setCompanyId(WebUtils.getCurrentCompanyId());
        crc = companyRegisterConfigServiceImpl.queryByCompanyId(crc);
        model.addAttribute("crc", crc);
        return "system/page/customPage3";
    }

    @ResponseBody
    @RequestMapping(value = "/queryCompanyCertificateExist", method = RequestMethod.POST)
    public String queryCompanyCertificateExist(CompanyFunctionSet search) {
        search.setCompanyId(WebUtils.getCurrentCompanyId());
        List<CompanyFunctionSet> list = companyFunctionSetServiceImpl.findCompanyFunctionSetByPage(search);
        if (list != null && list.size() > 0) {
            return "success";
        }
        return "error";
    }

    @ResponseBody
    @RequestMapping(value = "/queryCompanyProtocolExist", method = RequestMethod.POST)
    public String queryCompanyProtocolExist(CompanyFunctionSet search) {
        search.setCompanyId(WebUtils.getCurrentCompanyId());
        search.setStatus("1");
        boolean flag = false;
        List<CompanyFunctionSet> list = companyFunctionSetServiceImpl.findCompanyFunctionSetByPage(search);
        org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();
        if (subject.hasRole("机构管理员") || subject.hasRole("分校管理员") || subject.hasRole("运营")) {
            flag = true;
        }
        if (list != null && list.size() > 0 && flag) {
            return "success";
        }
        return "error";
    }

    @RequestMapping(value = "/saveOrUpdataProtocol", method = RequestMethod.POST)
    public String saveOrUpdataProtocol(CompanyFunctionSet search, HttpServletRequest request) {
        search.setCompanyId(WebUtils.getCurrentCompanyId());
        List<CompanyFunctionSet> list = companyFunctionSetServiceImpl.queryProtocolFucntionSet(search);
        if (list != null && list.size() > 0) {
            search.setId(list.get(0).getId());
            companyFunctionSetServiceImpl.update(search);
        } else {
            search.setStatus("1");
            companyFunctionSetServiceImpl.insert(search);
        }
        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "/queryCompanyHomeworkExist", method = RequestMethod.POST)
    public String queryCompanyHomeworkExist(CompanyFunctionSet search) {
        search.setCompanyId(WebUtils.getCurrentCompanyId());
        search.setStatus("1");
        boolean flag = false;
        List<CompanyFunctionSet> list = companyFunctionSetServiceImpl.findCompanyFunctionSetByPage(search);
        org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();
        if (subject.hasRole("机构管理员") || subject.hasRole("分校管理员") || subject.hasRole("运营")) {
            flag = true;
        }
        if (list != null && list.size() > 0 && flag) {
            return "success";
        }
        return "error";
    }

    @RequestMapping(value = "/saveOrUpdataHomeworkInform", method = RequestMethod.POST)
    public String saveOrUpdataHomeworkInform(CompanyFunctionSet search, HttpServletRequest request) {
        search.setCompanyId(WebUtils.getCurrentCompanyId());
        List<CompanyFunctionSet> list = companyFunctionSetServiceImpl.queryProtocolFucntionSet(search);
        if (list != null && list.size() > 0) {
            search.setId(list.get(0).getId());
            companyFunctionSetServiceImpl.update(search);
        } else {
            search.setStatus("1");
            companyFunctionSetServiceImpl.insert(search);
        }
        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "/checkGlobalLogin", method = RequestMethod.POST)
    public Object checkGlobalLogin(HttpServletRequest request) {
        CompanyFunctionSet companyFunctionSet = new CompanyFunctionSet();
        companyFunctionSet.setCompanyId(WebUtils.getCurrentCompanyId());
        companyFunctionSet.setFunctionCode("COMPANY_LOGIN_PAGE_FLAG");
        List<CompanyFunctionSet> companyFunctionSetList = companyFunctionSetServiceImpl.findCompanyFunctionSetByPage(companyFunctionSet);
        CompanyFunctionSet cfs = companyFunctionSetList != null && companyFunctionSetList.size() > 0 ? companyFunctionSetList.get(0) : null;

        Map<String, Object> rlt = new HashMap<String, Object>();
        if (cfs != null && "1".equals(cfs.getStatus())) {
            rlt.put("flag", true);
        } else {
            rlt.put("flag", false);
        }
        return rlt;
    }

    @ResponseBody
    @RequestMapping(value = "/globalLogin", method = RequestMethod.POST)
    public Object globalLogin(CompanyFunctionSet search, HttpServletRequest request) {
        CompanyFunctionSet companyFunctionSet = new CompanyFunctionSet();
        companyFunctionSet.setCompanyId(WebUtils.getCurrentCompanyId());
        companyFunctionSet.setFunctionCode("COMPANY_LOGIN_PAGE_FLAG");
        List<CompanyFunctionSet> companyFunctionSetList = companyFunctionSetServiceImpl.findCompanyFunctionSetByPage(companyFunctionSet);
        CompanyFunctionSet cfs = companyFunctionSetList != null && companyFunctionSetList.size() > 0 ? companyFunctionSetList.get(0) : null;
        if (cfs != null) {
            cfs.setStatus(search.getStatus());
            companyFunctionSetServiceImpl.update(cfs);
        } else {
            search.setCompanyId(WebUtils.getCurrentCompanyId());
            search.setFunctionCode("COMPANY_LOGIN_PAGE_FLAG");
            companyFunctionSetServiceImpl.insert(search);
        }
        Map<String, Object> rlt = new HashMap<String, Object>();
        rlt.put("flag", true);
        return rlt;
    }
}
