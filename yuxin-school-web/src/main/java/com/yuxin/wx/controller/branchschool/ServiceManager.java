package com.yuxin.wx.controller.branchschool;


import com.yuxin.wx.api.system.ISysConfigDictService;
import com.yuxin.wx.api.system.ISysConfigServiceService;
import com.yuxin.wx.model.system.SysConfigDict;
import com.yuxin.wx.model.system.SysConfigService;
import com.yuxin.wx.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/serviceManager")
public class ServiceManager {
    @Autowired
    private ISysConfigDictService iSysConfigDictService;
    @Autowired
    private ISysConfigServiceService configService;

    @RequestMapping(value = "/getServiceManager")
    public String getServiceManager(Model model, Integer companyId,Integer page) {
        //获取服务类型及服务名称
       /* List<SysConfigDict> data = iSysConfigDictService.querSysConfigDictList(companyId);
        //获取服务总数
        int count = iSysConfigDictService.querSysConfigDictCount(companyId);

        int a = 1;
        List<SysConfigDict> sysConfigDicts = new ArrayList<>();
        for (SysConfigDict scd : data) {
            if (!scd.getItemValue().equals("APP")) {
                scd.setSout(a);
                sysConfigDicts.add(scd);
            }
            a++;
        }
        //PageFinder<SysConfigDict> pageFinder = new PageFinder<SysConfigDict>(search.getPage(), search.getPageSize(), count, data);
        model.addAttribute("sysConfigDicts", sysConfigDicts);*/

        return "berkeley/serviceManagement";
    }


    @ResponseBody
    @RequestMapping(value = "/updateDelFlag", method = RequestMethod.POST)
    public String updateDelFlag(HttpServletRequest request, Integer companyId, String itemCode, Integer delFlag) {
        /*SysConfigService serv = new SysConfigService();
        if (delFlag == 0) {
            serv.setDelFlag(1);
        } else {
            serv.setDelFlag(0);
        }
        Integer userId = WebUtils.getCurrentUserId(request);
        serv.setUpdator(userId);
        serv.setUpdateTime(new Date());
        serv.setCompanyId(companyId);
        serv.setGroupCode(itemCode);
        if(configService.updateDelFlagByCompanyId(serv)){
            return "ok";
        }*/
        return "no";
    }

}
