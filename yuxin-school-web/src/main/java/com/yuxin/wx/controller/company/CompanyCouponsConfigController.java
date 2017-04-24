package com.yuxin.wx.controller.company;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

import com.yuxin.wx.api.company.ICompanyCouponsConfigService;
import com.yuxin.wx.api.company.ICompanyFunctionSetService;
import com.yuxin.wx.api.company.ICompanyMemberServiceService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.CompanyCouponsConfig;
import com.yuxin.wx.model.company.CompanyFunctionSet;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.utils.WebUtils;

/**
 * Controller of CompanyCouponsConfig
 * 
 * @author chopin
 * @date 2016-6-20
 */
@Controller
@RequestMapping("/companyCouponsConfig")
public class CompanyCouponsConfigController {

    @Autowired
    private ICompanyCouponsConfigService companyCouponsConfigServiceImpl;
    @Autowired
    private ICompanyService companyServiceImpl;
    @Autowired
    private ICompanyMemberServiceService companyMemberServiceServiceImpl;
    @Autowired
    private ICompanyServiceStaticService companyServiceStaticServiceImpl;
    @Autowired
    private ICompanyFunctionSetService companyFunctionSetServiceImpl;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model, CompanyCouponsConfig search) {
        if (search == null) {
            search = new CompanyCouponsConfig();
            // search.setPageSize(20);
        }
        model.addAttribute("list", companyCouponsConfigServiceImpl.findCompanyCouponsConfigByPage(search));
        return "companyCouponsConfig/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(CompanyCouponsConfig CompanyCouponsConfig) {
        companyCouponsConfigServiceImpl.insert(CompanyCouponsConfig);
        return "redirect:/companyCouponsConfig";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(CompanyCouponsConfig CompanyCouponsConfig) {
        companyCouponsConfigServiceImpl.update(CompanyCouponsConfig);
        return "redirect:/companyCouponsConfig";
    }

    @RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
    public String del(Model model, @PathVariable Integer id) {
        companyCouponsConfigServiceImpl.deleteCompanyCouponsConfigById(id);
        return "redirect:/companyCouponsConfig";
    }

    @ResponseBody
    @RequestMapping(value = "/{id:\\d+}", method = RequestMethod.GET)
    public CompanyCouponsConfig getJson(Model model, @PathVariable Integer id) {
        return companyCouponsConfigServiceImpl.findCompanyCouponsConfigById(id);
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
     * 
     * Class Name: CompanyCouponsConfigController.java
     * 
     * @Description: 优惠券列表
     * @author dongshuai
     * @date 2016年6月21日 上午10:31:40
     * @modifier
     * @modify-date 2016年6月21日 上午10:31:40
     * @version 1.0
     * @param model
     * @return
     */
    @RequestMapping(value = "/showCouponsList")
    public String showCouponsList(Model model) {
        // 公司配置
        int companyId = WebUtils.getCurrentCompanyId();
        Company company = companyServiceImpl.findCompanyById(companyId);
        CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
        CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
        model.addAttribute("company", company);
        model.addAttribute("cms", cms);
        model.addAttribute("css", css);
        initCouponsService();
        initCouponsConfig();
        CompanyFunctionSet cfs = new CompanyFunctionSet();
        cfs.setCompanyId(WebUtils.getCurrentCompanyId());
        cfs.setFunctionCode("COMPANY_COUPONS_SERVICE");
        List<CompanyFunctionSet> cfslist = companyFunctionSetServiceImpl.findCompanyFunctionSet(cfs);
        model.addAttribute("cfs", cfslist.get(0));
        return "coupon/coupon-manage";
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
            companyFunctionSetServiceImpl.insert(cfs);
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
        }
        return ccc;
    }
}
