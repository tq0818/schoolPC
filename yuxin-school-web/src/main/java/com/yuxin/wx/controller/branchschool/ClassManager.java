package com.yuxin.wx.controller.branchschool;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.company.ICompanyManageService;
import com.yuxin.wx.api.company.ICompanyMemberServiceService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.common.JsonMsg;
import com.yuxin.wx.model.company.CompanyLiveConfig;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.model.company.CompanyPayConfig;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.model.company.CompanyVo;
import com.yuxin.wx.model.company.NewCompanyVo;

@Controller
@RequestMapping("/classManager")
public class ClassManager {

	private Log log=LogFactory.getLog("log");
	@Autowired
    private ICompanyService iCompanyService;
	@Autowired
    private ICompanyServiceStaticService companyServiceStaticServiecImpl;
    @Autowired
    private ICompanyMemberServiceService companyMemberServiceServiceImpl;
    @Autowired
	private ICompanyManageService companyManageServiceImpl;
    /**
     * 获取直播课程列表
     * @param model
     * @param className
     * @param classStatus
     * @param classPerStatus
     * @return
     */
    @RequestMapping(value = "/getClassList")
    public String getClassList(Model model, String className, Integer classStatus, Integer classPerStatus){


        return "/berkeley/classManagement";
    }

    /**
     * 修改课程权限状态
     * @param classId
     * @param classPerStatus
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updatePerStatus")
    public String updatePerStatus(Integer classId, Integer classPerStatus){


        return "";
    }


    /**
     *获取课程表
     * @param classId
     * @return
     */
    @RequestMapping(value = "/getClassTable")
    public String getClassTable(Integer classId){

        return "";
    }

    /**
     * 添加课程
     * @return
     */
    @RequestMapping(value = "addClass")
    public String addClass(){
        return "";
    }

    /**
     * 搜索课程
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getClass")
    public List<T> findOtherClass(String className, String school){
        List<T> list = new ArrayList<>();
        return list;
    }


    /**
     * 查看课程详情，跳转到课程详情页面
     * @param classId
     * @param companyId
     * @return
     */ 
    @RequestMapping(value = "/getClassInfo/{companyId}",method = RequestMethod.GET)
    public String getClassInfo(Model model,Integer classId,@PathVariable Integer companyId){

    	NewCompanyVo cp=iCompanyService.findCompanyVoById(companyId);
    	//已用空间
    	CompanyServiceStatic css = companyServiceStaticServiecImpl.findByCompanyId(companyId);
    	//总空间
    	CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
    	//展示互动账号
    	CompanyLiveConfig clc=iCompanyService.findCompanyLiveConfigById(companyId);
    	//cc账号
    	CompanyPayConfig cpc = iCompanyService.findCompanyPayConfigById(companyId);
    	
    	model.addAttribute("company", cp);
    	model.addAttribute("css", css);
    	model.addAttribute("cms", cms);
    	model.addAttribute("clc", clc);
    	model.addAttribute("cpc", cpc);
    	model.addAttribute("companyId", companyId);
        return "berkeley/berkeleyDetail";
    }
    
/**
 * 
 * @author jishangyang 2017年12月7日 下午11:55:52
 * @Method: addBerkeley 
 * @Description: 修改分校信息
 * @param request
 * @param model
 * @param search
 * @param cms
 * @param clc
 * @return 
 * @throws
 */
    @ResponseBody
    @RequestMapping(value = "/editBerkeley")
    public JSONObject editBerkeley(HttpServletRequest request,Model model,CompanyVo search,CompanyMemberService cms,CompanyLiveConfig clc){
    	 JSONObject json = new JSONObject();
         log.info("qa：修改分校信息:");
//         search.setPrivateCost(search.getPrivateCost().substring(0, search.getPrivateCost().indexOf("%")));
//         search.setPublicCost(search.getPublicCost().substring(0, search.getPublicCost().indexOf("%")));
         if(null!=request.getParameter("flowSize") && !"".equals(request.getParameter("flowSize"))){
        	 int flowSize= Integer.valueOf(request.getParameter("flowSize")); 
        	 cms.setVideoFlow(flowSize);
         }
         if(null!=request.getParameter("spaceSize") && !"".equals(request.getParameter("spaceSize"))){
        	 int spaceSize= Integer.valueOf(request.getParameter("spaceSize")); 
        	 cms.setVideoStorage(spaceSize);
         }
         cms.setCompanyId(String.valueOf(search.getId()));
        
         String zsUserName= request.getParameter("zsUserName"); 
         clc.setLoginName(zsUserName);
         String zsPwd= request.getParameter("zsPwd"); 
         clc.setPassword(zsPwd);
         clc.setCompanyId(search.getId());
         CompanyPayConfig cpc =new CompanyPayConfig();
         String ccUserName=request.getParameter("ccUserName");
         String ccPwd=request.getParameter("ccPwd");
         cpc.setCcUserId(ccUserName);
         cpc.setCcApiKey(ccPwd);
         cpc.setCompanyId(search.getId());
         try {
        	 companyManageServiceImpl.eidtBerkeley(search,cms,clc,cpc);
             json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
        } catch (Exception e) {
        	 log.info("qa：修改分校信息报错");
        	 json.put(JsonMsg.MSG, JsonMsg.INFORMATION);
        }
        
         return json;
    } 
}
