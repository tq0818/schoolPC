package com.yuxin.wx.controller.tiku;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

import com.yuxin.wx.api.company.ICompanyMemberServiceService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.api.system.ISysConfigServiceService;
import com.yuxin.wx.api.tiku.ITikuConfigService;
import com.yuxin.wx.api.tiku.ITikuSetService;
import com.yuxin.wx.api.tiku.ITikuSubjectService;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.model.system.SysConfigService;
import com.yuxin.wx.model.tiku.TikuConfig;
import com.yuxin.wx.model.tiku.TikuSet;
import com.yuxin.wx.model.tiku.TikuSubject;
import com.yuxin.wx.util.JedisUtil;
import com.yuxin.wx.utils.WebUtils;

/**
 * Controller of TikuSet
 *
 * @author wang.zx
 * @date 2015-7-8
 */
@Controller
@RequestMapping("/tikuSet")
public class TikuSetController {

    private Log log = LogFactory.getLog("log");

    @Autowired
    private ISysConfigServiceService SysConfigserviceImpl;

    @Autowired
    private ITikuConfigService tikuConfigServiceImpl;

    @Autowired
    private ICompanyMemberServiceService companyMemberServiceServiceImpl;

    @Autowired
    private ICompanyServiceStaticService companyServiceStaticServiceImpl;

    @Autowired
    private ICompanyService companyServiceImpl;

    @Autowired
    private ITikuSubjectService tikuSubjectServiceImpl;

    @Autowired
    private ITikuSetService tikuSetServiceImpl;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model, TikuSet search) {
        if (search == null) {
            search = new TikuSet();
            // search.setPageSize(20);
        }
        model.addAttribute("list", this.tikuSetServiceImpl.findTikuSetByPage(search));
        return "tikuSet/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(TikuSet TikuSet) {
        this.tikuSetServiceImpl.insert(TikuSet);
        return "redirect:/tikuSet";
    }

    @ResponseBody
    @RequestMapping(value = "/update")
    public String update(TikuSet TikuSet) {
        this.tikuSetServiceImpl.update(TikuSet);
        Integer companyId = WebUtils.getCurrentCompanyId();
        TikuSet tiku = new TikuSet();
        tiku.setCompanyId(companyId);
        TikuSet newSet = this.tikuSetServiceImpl.findSetByCompanyIdAndCategoryId(tiku);
        JedisUtil.deleteByKey("companySet" + companyId + "");
        JedisUtil.put("companySet" + companyId + "", newSet, (30 * 24 * 3600));
        return "success";
    }

    @RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
    public String del(Model model, @PathVariable Integer id) {
        this.tikuSetServiceImpl.deleteTikuSetById(id);
        return "redirect:/tikuSet";
    }

    @ResponseBody
    @RequestMapping(value = "/{id:\\d+}", method = RequestMethod.GET)
    public TikuSet getJson(Model model, @PathVariable Integer id) {
        return this.tikuSetServiceImpl.findTikuSetById(id);
    }

    /**
     * 
     * Class Name: TikuSetController.java
     * 
     * @Description: 链接到题库设置（未设置时）
     * @author yuchanglong
     * @date 2015年7月15日 下午3:05:02
     * @version 1.0isExSet
     * @param model
     * @return
     */
    @RequestMapping(value = "/toSet/{types}")
    public String toSet(Model model, @PathVariable String types) {
        TikuSet set = new TikuSet();
        Integer companyId = WebUtils.getCurrentCompanyId();
        set.setCompanyId(companyId);
        // 进入题库设置时判读数据库是否存在该公司该题库类别的数据，若没有则新插入一条
        TikuSet findSet = this.tikuSetServiceImpl.findSetByCompanyIdAndCategoryId(set);
        model.addAttribute("setId", findSet.getId());
        model.addAttribute("set", findSet);

        // 查询 机构信息
        Company company = this.companyServiceImpl.findCompanyById(companyId);
        CompanyMemberService cms = this.companyMemberServiceServiceImpl.findByCompanyId(companyId);
        CompanyServiceStatic css = this.companyServiceStaticServiceImpl.findByCompanyId(companyId);

        model.addAttribute("company", company);
        model.addAttribute("cms", cms);
        model.addAttribute("css", css);
        return "tiku/set/" + types;
    }

    @RequestMapping(value = "/toTikuSubManage/{tikuId}")
    public String toTikuSubManage(Model model, @PathVariable Integer tikuId) {
        // 查询分类下的科目,根据id 排序
        List<TikuSubject> subList = this.tikuSubjectServiceImpl.findSubByCategoryId(tikuId);
        model.addAttribute("subList", subList);
        model.addAttribute("tikuId", tikuId);
        return "tiku/set/tikuSubManage";
    }

    @ResponseBody
    @RequestMapping(value = "/isExSet")
    public Boolean isExSet() {
        TikuSet set = new TikuSet();
        Integer companyId = WebUtils.getCurrentCompanyId();
        set.setCompanyId(companyId);
        TikuSet findSet = this.tikuSetServiceImpl.findSetByCompanyIdAndCategoryId(set);
        if (findSet == null) {
            return true;
        } else {
            return false;
        }
    }


    @ResponseBody
    @RequestMapping(value = "/isExSetNew")
    public String isExSetNew() {
        String result = "fail";
        TikuSet set = new TikuSet();
        Integer companyId = WebUtils.getCurrentCompanyId();
        set.setCompanyId(companyId);
        TikuSet findSet = this.tikuSetServiceImpl.findSetByCompanyIdAndCategoryId(set);
        if (findSet != null) {
            Subject subject = SecurityUtils.getSubject();
            if(subject.isPermitted("tiku_topic")){
                result = "topic";
            }else if(subject.isPermitted("tiku_paper")){
                result = "paper";
            }else if(subject.isPermitted("tiku_exampoint")){
                result = "exampoint";
            }else if(subject.isPermitted("tiku_subject")){
                result = "subject";
            }
        }

        return result;
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

    @RequestMapping("/getTopic")
    public String getTopic(Model model, HttpServletRequest request) {
        Integer companyId = WebUtils.getCurrentCompanyId();
        Map<String, Object> param = new HashMap<String, Object>();
        try {
            param.put("companyId", companyId);
            param.put("configType", "TIKU_OBTAIN_TOPIC_RANGE");
            List<TikuConfig> tc = this.tikuConfigServiceImpl.findList(param);
            for (TikuConfig t : tc) {
                if (t.getDelFlag().equals(0)) {
                    model.addAttribute(t.getConfigValue(), "isok");
                }
            }

            // 查询是否开通考试
            param.clear();
            param.put("companyId", companyId);
            param.put("groupCode", "SERVICE_TIKU_EXAM");
            SysConfigService scs = this.SysConfigserviceImpl.findByCodeId(param);
            model.addAttribute("scs", scs);
        } catch (Exception e) {
            this.log.error("进入选题错误：" + e.getMessage(), e);
            e.printStackTrace();
        }

        // 查询 机构信息
        Company company = this.companyServiceImpl.findCompanyById(companyId);
        CompanyMemberService cms = this.companyMemberServiceServiceImpl.findByCompanyId(companyId);
        CompanyServiceStatic css = this.companyServiceStaticServiceImpl.findByCompanyId(companyId);

        model.addAttribute("company", company);
        model.addAttribute("cms", cms);
        model.addAttribute("css", css);
        return "tiku/set/getTopic";
    }
}
