package com.yuxin.wx.controller.branchschool;


import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.auth.IAuthRoleService;
import com.yuxin.wx.api.company.ICompanyManageService;
import com.yuxin.wx.api.system.ISysConfigDictService;
import com.yuxin.wx.common.JsonMsg;
import com.yuxin.wx.common.PageFinder2;
import com.yuxin.wx.model.auth.AuthRole;
import com.yuxin.wx.model.company.CompanyLiveConfig;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.model.company.CompanyVo;
import com.yuxin.wx.model.system.SysConfigDict;
import com.yuxin.wx.utils.WebUtils;

@Controller
@RequestMapping("/berkeley")
public class BranchSchoolIndex {
	
	 private Log log=LogFactory.getLog("log");
	
	 @Autowired
	 private ISysConfigDictService sysConfigDictServiceImpl;

	 @Autowired
	 private ICompanyManageService companyManageServiceImpl;
	 @Autowired
	 private IAuthRoleService authRoleServiceImpl;
    /**
     * 跳转到订单列表
     *
     * @return
     */
    @RequestMapping(value = "/berkeleyOrder/{companyId}")
    public String gotobranchSchoolOrder(Model model,@PathVariable Integer companyId) {
    	model.addAttribute("companyId", companyId);
        return "/berkeley/berkeleyOrder";
    }
    /**
     * 跳转到老师管理
     *
     * @return
     */
    @RequestMapping(value = "/teacherManagement/{companyId}")
    public String gototeacherManage(Model model,@PathVariable Integer companyId){
    	
        return "berkeley/teacherManagement";
    }
    /**
     * 跳转到添加老师页面
     *
     * @return
     */
    @RequestMapping(value = "/addTeacher")
    public String gotoaddTeacher(){

        return "berkeley/addTeacher";
    }
    /**
     * 跳转到权限管理
     *
     * @return
     */
    @RequestMapping(value = "/permissionManagement/{companyId}")
    public String gotopermissionManagement(Model model,@PathVariable Integer companyId){
    	model.addAttribute("companyId", companyId);
    	List<AuthRole> roleList = authRoleServiceImpl.findAll();
    	model.addAttribute("roleList", roleList);
    	return "berkeley/permissionManagement";
    }
    /**
     * 跳转到添加用户页面
     *
     * @return
     */
    @RequestMapping(value = "/addUsers")
    public String gotoaddUsers(){

        return "/berkeley/addUsers";
    }
    /**
     * 跳转到计算资源管理
     *
     * @return
     */
    @RequestMapping(value = "/resourceManagement")
    public String gotoresourceManagement(){

        return "/berkeley/resourceManagement";
    }

    /**
     * 跳转到服务管理
     *
     * @return
     */
    @RequestMapping(value = "serviceManagement")
    public String gotoserviceManagement(){
        return "/berkeley/serviceManagement";
    }
    /**
     * 
     * @author jishangyang 2017年12月6日 下午8:40:21
     * @Method: berkeleyIndex 
     * @Description: 查询分校
     * @param model
     * @param search
     * @return 
     * @throws
     */
    @RequestMapping(value = "berkeleyIndex")
    public String berkeleyIndex(Model model,CompanyVo search){
    	//查询学校所在区域
        SysConfigDict areaDict = new SysConfigDict();
        areaDict.setDictCode("EDU_SCHOOL_AREA");
        List<SysConfigDict> areas = sysConfigDictServiceImpl.queryConfigDictListByDictCode(areaDict);
        areaDict.setDictCode("SCHOOL_PROPERTY");
        List<SysConfigDict> schoolPros = sysConfigDictServiceImpl.queryConfigDictListByDictCode(areaDict);
        //PageFinder2<CompanyVo> companyList=companyManageServiceImpl.queryCompanyVoListByCondition(search);
        model.addAttribute("areas", areas);
        model.addAttribute("schoolPros",schoolPros);
        //model.addAttribute("companyList",companyList);
        return "/berkeley/berkeleyIndex";
    }
    /**
     * 
     * @author jishangyang 2017年12月6日 下午9:50:47
     * @Method: queryCompanyList 
     * @Description: 根据条件查询分校信息
     * @param model
     * @param search
     * @return 
     * @throws
     */
    @ResponseBody
    @RequestMapping(value = "/queryCompanyList")
    public PageFinder2<CompanyVo> queryCompanyList(Model model,CompanyVo search){
    	return companyManageServiceImpl.queryCompanyVoListByCondition(search);
    }
    /**
     * 
     * @author jishangyang 2017年12月7日 上午12:14:34
     * @Method: queryCompanyVo 
     * @Description: 根据学校代码查询对应的分校
     * @param model
     * @param brachCode
     * @return 
     * @throws
     */
    @ResponseBody
    @RequestMapping(value = "/queryCompanyVo")
    public CompanyVo queryCompanyVo(Model model,String brachCode){
    	if(brachCode==null||brachCode=="") return null;
    	CompanyVo dto=companyManageServiceImpl.queryCompanyVoByCondition(brachCode);
    	return dto;
    }
    /**
     * 
     * @author jishangyang 2017年12月7日 上午12:14:17
     * @Method: addBerkeley 
     * @Description: 添加分校
     * @param model
     * @param search
     * @return 
     * @throws
     */
    @ResponseBody
    @RequestMapping(value = "/addBerkeley")
    public JSONObject addBerkeley(HttpServletRequest request,Model model,CompanyVo search,CompanyMemberService cms,CompanyLiveConfig clc){
    	 JSONObject json = new JSONObject();
         log.info("qa：添加分校:");
         String eduAreaSchool = request.getParameter("branchCode").toString();
         search.setEduAreaSchool(eduAreaSchool);
         if(null == request.getParameter("isArea") || "" ==request.getParameter("isArea")){
        	 search.setIsArea("0");
         }else if(null!=request.getParameter("isArea") &&"EDU_SCHOOL_AREA".equals(request.getParameter("isArea").toString()) ){
        	 search.setIsArea("1");
         }else{
        	 search.setIsArea("2");
         }
         String companyName	= request.getParameter("branchSchool").toString();  
         search.setCompanyName(companyName);
         String eduArea	= request.getParameter("eara").toString(); 
         search.setEduArea(eduArea);
         String schoolProperties= request.getParameter("schoolProperties").toString(); 
         search.setSchoolProperty(schoolProperties);
         search.setRegistTimes(new Date());
         search.setMemberStartDate(new Date());
         search.setMemberEndDate(new Date());
         search.setCompanyLogoType("picture");
         search.setCompanyLogo("company/18113/20170604/a964ec80-0647-4fd7-99cf-f454f3daf74a.png");
         search.setMemberLevel("80");
         search.setStatus("1");
         search.setSchoolApplyFlag("0");
         search.setUtmSource("u");
         search.setConType("company");
         search.setBuyFlag("1");
         search.setCompanyNameShot(companyName);
         search.setServiceVersion("ONLINE_COUNT");
         int flowSize= Integer.valueOf(request.getParameter("flowSize")); 
         cms.setVideoFlow(flowSize);
         int spaceSize= Integer.valueOf(request.getParameter("spaceSize")); 
         cms.setVideoStorage(spaceSize);
         String zsUserName= request.getParameter("zsUserName").toString(); 
         clc.setLoginName(zsUserName);
         String zsPwd= request.getParameter("zsPwd").toString(); 
         clc.setPassword(zsPwd);
         //ccUserName : ccUserName,
         //ccPwd : ccPwd,
         try {
        	 
        	 companyManageServiceImpl.addBerkeley(search,cms,clc,WebUtils.getCurrentUserId(request));
             json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
        } catch (Exception e) {
        	 log.info("qa：添加分校报错");
        	 json.put(JsonMsg.MSG, JsonMsg.INFORMATION);
        }
        
         return json;
    } 
}
