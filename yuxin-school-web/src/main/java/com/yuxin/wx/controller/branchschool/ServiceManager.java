package com.yuxin.wx.controller.branchschool;


import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.api.system.ISysConfigDictService;
import com.yuxin.wx.api.system.ISysConfigServiceService;
import com.yuxin.wx.common.JsonMsg;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.system.SysConfigDict;
import com.yuxin.wx.model.system.SysConfigService;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.system.SystemConfigServiceVo;

@Controller
@RequestMapping("/serviceManager")
public class ServiceManager {
	@Autowired
	private ICompanyServiceStaticService companyServiceStaticServiceImpl;
    @Autowired
    private ISysConfigDictService iSysConfigDictService;
    @Autowired
    private ISysConfigServiceService configService;
    @Autowired
   	private ICompanyService companyService ;

    @RequestMapping(value = "/getServiceManager/{companyId}")
    public String getServiceManager(Model model,@PathVariable Integer companyId,Integer page,Integer pageSize,SystemConfigServiceVo scsv){
    	Company company=companyService.findCompanyById(companyId);
     	model.addAttribute("company", company);
    	model.addAttribute("companyId", companyId);
        //获取服务类型及服务名称
    	 Map<String, Object> map = new HashMap<String, Object>();
    	 if(null==page || page ==0){
    		 page=1; 
    	 }
    	 map.put("page", (page-1) * 10);
         map.put("pageSize", 10);
         map.put("companyId", companyId);
        List<SysConfigDict> scd = iSysConfigDictService.querSysConfigDictList(map);
        List<SysConfigDict> scd1 =new ArrayList<SysConfigDict>();
        //获取服务总数
        int count = iSysConfigDictService.querSysConfigDictCount(companyId);
        if ("2".equals(company.getIsArea())) {
            for (SysConfigDict s : scd) {
                if (!s.getDictName().trim().equals("短信") && !"SERVICE_QUESTION_ANSWER".equals(s.getGroupCode()) && !"SERVICE_PAY_SET".equals(s.getGroupCode())) {
                    scd1.add(s);
                }
            }

        } else if ("1".equals(company.getIsArea())) {
            for (SysConfigDict s : scd) {
                if (!s.getDictName().trim().equals("短信") && !"SERVICE_CLASS_SET".equals(s.getGroupCode()) && !"SERVICE_QUESTION_ANSWER".equals(s.getGroupCode()) && !"SERVICE_PAY_SET".equals(s.getGroupCode())) {
                    scd1.add(s);
                }
            }
        } else {
            for (SysConfigDict s : scd) {
                if (!s.getDictName().trim().equals("短信") && !"SERVICE_QUESTION_ANSWER".equals(s.getGroupCode())) {
                    scd1.add(s);
                }
            }
        }
        PageFinder<SysConfigDict> pageFinder = new PageFinder<SysConfigDict>(page,10, scd1.size(), scd1);
        model.addAttribute("sysConfigDicts", pageFinder);
        model.addAttribute("companyId", companyId);

        return "berkeley/serviceManagement";
    }


    @ResponseBody
    @RequestMapping(value = "/updateDelFlag", method = RequestMethod.POST)
    public JSONObject updateDelFlag(HttpServletRequest request, Integer companyId, String itemCode, Integer delFlag) {
    	JSONObject json = new JSONObject();
    	SysConfigService serv = new SysConfigService();
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
        	json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
        }else{
        	 //log.info("qa：添加分校报错");
        	 json.put(JsonMsg.MSG, JsonMsg.INFORMATION);
        }
        return json;
    }

}
