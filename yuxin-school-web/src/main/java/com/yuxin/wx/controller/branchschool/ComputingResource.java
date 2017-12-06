package com.yuxin.wx.controller.branchschool;

import com.yuxin.wx.api.company.ICompanyMemberServiceService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.company.ICompanyServiceStaticDayService;
import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.api.user.IUsersService;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.model.company.CompanyServiceStaticDay;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.util.FileQNUtils;
import com.yuxin.wx.utils.DateUtil;
import com.yuxin.wx.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping("/computingResource")
public class ComputingResource {

    @Autowired
    private ICompanyMemberServiceService companyMemberServiceServiceImpl;
    @Autowired
    private ICompanyServiceStaticService companyServiceStaticServiceImpl;
    @Autowired
    private IUsersService usersServiceImpl;
    @Autowired
    private ICompanyService companyServiceImpl;
    @Autowired
    private ICompanyServiceStaticDayService companyServiceStaticDayService;
    @Autowired
    private ICompanyServiceStaticDayService dayService;

    /**
     * 录播模块下的视频统计和短信详情
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getVideoResourceAndMessageStatistics")
    public String getVideoResource(Model model, Integer companyId) {
        Map<String, Object> companyMap = queryCompany(companyId);
        model.addAllAttributes(companyMap);

        List<CompanyServiceStaticDay> dayList = dayService.findInfoByCompanyId(WebUtils.getCurrentCompanyId());
        model.addAttribute("dayList", dayList);

        return "berkeley/resourceManagement";
    }

    public Map<String, Object> queryCompany(Integer companyId) {
        // 查询公司信息
        Company company = getCompany(companyId);
        // 公司拥有的服务
        CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
        // 公司 已使用的服务
        CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);
        double usedVideo = (css.getVideoStorage() != null ? css.getVideoStorage() : 0.0);
        long crs = 0;
        usedVideo += FileQNUtils.convertFileSize(crs);
        usedVideo = new BigDecimal(usedVideo).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
        double cvf = (css.getVideoFlow() != null ? css.getVideoFlow() : 0.0);
        long crf = Long.parseLong(css.getResourceFlow() != null ? css.getResourceFlow() : "0");
        cvf += FileQNUtils.convertFileSize(crf);

        cvf = new BigDecimal(cvf).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();

        // 用户信息
        Users user = usersServiceImpl.findUserByCompanyIdAndUserType(companyId);
        /*
		 * company 是公司信息 cms 是 公司已购买的服务 css 是公司 已使用的服务
		 */
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("user", user);
        map.put("company", company);
        map.put("cms", cms);
        map.put("css", css);
        map.put("cvs", usedVideo);
        map.put("cvf", cvf);
        return map;
    }

    private Company getCompany(Integer companyId) {
        // 查询公司信息
        return companyServiceImpl.findCompanyById(companyId);
    }


    /**
     * 流量统计详情
     *
     * @param model
     * @param cssday
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/toVsAjaxInfo")
    public Map<String, Object> toVsAjaxInfo(Model model, CompanyServiceStaticDay cssday, Integer companyId) {
        Map<String, Object> res = new HashMap<String, Object>();
        //cssday.setCompanyId(WebUtils.getCurrentCompanyId());
        cssday.setCompanyId(companyId);
        if (cssday.getEndTime() == null || cssday.getEndTime() == "") {
            Date date = new Date();
            cssday.setEndTime(DateUtil.formatDateByFormat(date, "yyyy-MM-dd"));
        }
        if (cssday.getStartTime() == null || cssday.getStartTime() == "") {
            Date date = new Date();
            Date agoDate = DateUtil.addDate(date, -15);
            cssday.setStartTime(DateUtil.formatDateByFormat(agoDate, "yyyy-MM-dd"));
        }

        List<CompanyServiceStaticDay> newCDay = new ArrayList<>();
        List<CompanyServiceStaticDay> cssd = companyServiceStaticDayService.findInfoByDate(cssday);
        for (CompanyServiceStaticDay day : cssd) {
            if (day.getVideoTotalFlow() == null) {
                day.setVideoTotalFlow(0.0);
            }
            newCDay.add(day);
        }
        res.put("flow", newCDay);
        return res;
    }
}
