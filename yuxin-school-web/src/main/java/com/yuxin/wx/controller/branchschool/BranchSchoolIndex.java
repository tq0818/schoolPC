package com.yuxin.wx.controller.branchschool;


import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.yuxin.wx.api.company.ICompanyMemberServiceService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.company.ICompanyServiceStaticDayService;
import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.api.system.ISysConfigDictService;
import com.yuxin.wx.api.user.IUsersService;
import com.yuxin.wx.common.JsonMsg;
import com.yuxin.wx.common.PageFinder2;
import com.yuxin.wx.model.auth.AuthRole;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.CompanyLiveConfig;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.model.company.CompanyPayConfig;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.model.company.CompanyServiceStaticDay;
import com.yuxin.wx.model.company.CompanyVo;
import com.yuxin.wx.model.system.SysConfigDict;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.util.FileQNUtils;
import com.yuxin.wx.utils.DateUtil;
import com.yuxin.wx.utils.WebUtils;

@Controller
@RequestMapping("/berkeley")
public class BranchSchoolIndex {
	
	 private Log log=LogFactory.getLog("log");
	
	 @Autowired
	 private ISysConfigDictService sysConfigDictServiceImpl;
	 @Autowired
	 private ICompanyServiceStaticDayService companyServiceStaticDayService;
	 @Autowired
	 private ICompanyManageService companyManageServiceImpl;
	 @Autowired
	 private IAuthRoleService authRoleServiceImpl;
	 @Autowired
   	 private ICompanyService companyService ;
	 @Autowired
	 private IUsersService usersServiceImpl;
	 @Autowired
	 private ICompanyService companyServiceImpl;
	 @Autowired
	 private ICompanyMemberServiceService companyMemberServiceServiceImpl;
	 @Autowired
	 private ICompanyServiceStaticService companyServiceStaticServiceImpl;
    /**
     * 跳转到订单列表
     *
     * @return
     */
    @RequestMapping(value = "/berkeleyOrder/{companyId}")
    public String gotobranchSchoolOrder(Model model,@PathVariable Integer companyId) {
    	Company company=companyService.findCompanyById(companyId);
     	model.addAttribute("company", company);
    	model.addAttribute("companyId", companyId);
        return "/berkeley/berkeleyNewOrder";
    }
    /**
     * 跳转到老师管理
     *
     * @return
     */
    @RequestMapping(value = "/teacherManagement/{companyId}")
    public String gototeacherManage(Model model,@PathVariable Integer companyId){
    	Company company=companyService.findCompanyById(companyId);
     	model.addAttribute("company", company);
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
    	List<AuthRole> roleList = authRoleServiceImpl.findByCompanyId(companyId);
    	model.addAttribute("companyId", companyId);
    	model.addAttribute("roleList", roleList);
    	Company company=companyService.findCompanyById(companyId);
     	model.addAttribute("company", company);
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
    @RequestMapping(value = "/resourceManagement/{companyId}")
    public String gotoresourceManagement(Model model,@PathVariable Integer companyId){
    	Map<String, Object> companyMap = selCompay(companyId);
		model.addAllAttributes(companyMap);
        return "/berkeley/resourceManagement";
    }
	/**
	 * 周文斌 查询公司信息 以及服务 记录
	 */
	private Map<String, Object> selCompay(Integer companyId) {
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
	/**
	 *
	 * Class Name: CompanyMemberServiceController.java
	 *
	 * @Description: (查询公司信息)
	 * @author zwb
	 * @date 2015-4-29 下午2:50:56
	 * @version 1.0
	 * @param companyId
	 * @return
	 */
	private Company getCompany(Integer companyId) {
		// 查询公司信息
		return companyServiceImpl.findCompanyById(companyId);
	}
    /**
	 *
	 * Class Name: CompanyMemberServiceController.java
	 *
	 * @Description: 异步加载短信图表
	 * @author ycl
	 * @date 2015-5-25 下午2:15:47
	 * @modifier
	 * @modify-date 2015-5-25 下午2:15:47
	 * @version 1.0
	 * @param model
	 * @param cssday
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/toMsAjaxInfo ")
	public JSONObject toMsAjaxInfo(Model model, CompanyServiceStaticDay cssday,Integer companyId) {
		JSONObject json = new JSONObject();
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
		List<CompanyServiceStaticDay> newCDay = new ArrayList<CompanyServiceStaticDay>();
		List<CompanyServiceStaticDay> cssd = companyServiceStaticDayService.findInfoByDate(cssday);
		for (CompanyServiceStaticDay day : cssd) {
			if (day.getMessageNum() == null) {
				day.setMessageNum(0);
			}
			newCDay.add(day);
		}
		json.put("message", newCDay);
		return json;
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
//      areaDict.setDictCode("EDU_SCHOOL_AREA");
//      List<SysConfigDict> areas = sysConfigDictServiceImpl.queryConfigDictListByDictCode(areaDict);
        areaDict.setDictCode("EDU_STEP_NEW");
        List<SysConfigDict> schoolPros = sysConfigDictServiceImpl.queryConfigDictListByDictCode(areaDict);
		List<SysConfigDict> areas =companyManageServiceImpl.queryCompanyVoListByQuyu();
//        PageFinder2<CompanyVo> companyList=companyManageServiceImpl.queryCompanyVoListByCondition(search);
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
    	if(search.getPage()==0){
    		search.setPage(1);
    	}
    	if(search.getPageSize()==12){
    		search.setPageSize(10);
    	}
    	if(null==search.getPaixu()){
    		search.setPaixu("2");
    	}
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
    	if(dto.getDictCode().equals("EDU_SCHOOL_AREA")){
    		dto.setSchoolProperty("无");
    	}
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
         SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
        try {
        	Date date = sdf.parse("2099-04-24");
	        search.setMemberEndDate(date);
        } catch (ParseException e1) {
	        e1.printStackTrace();
        }
         search.setCompanyLogoType("picture");
         search.setCompanyLogo("company/18113/20170604/a964ec80-0647-4fd7-99cf-f454f3daf74a.png");
         search.setMemberLevel("80");
         search.setStatus("1");
         search.setSchoolApplyFlag("0");
         search.setUtmSource("n");
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
         CompanyPayConfig cpc =new CompanyPayConfig();
         String ccUserName= request.getParameter("ccUserName").toString(); 
         String ccPwd= request.getParameter("ccPwd").toString(); 
         cpc.setCcUserId(ccUserName);
         cpc.setCcApiKey(ccPwd);
         try {
        	 
        	 companyManageServiceImpl.addBerkeley(search,cms,clc,cpc,WebUtils.getCurrentUserId(request));
             json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
        } catch (Exception e) {
        	 log.info("qa：添加分校报错");
        	 json.put(JsonMsg.MSG, JsonMsg.INFORMATION);
        }
        
         return json;
    } 
    
    @ResponseBody
    @RequestMapping(value = "/checkDomain")
    public JSONObject checkDomain(Model model,String domain,String domainManage){
    	JSONObject json = new JSONObject();
    	  try {
    		  CompanyVo search =new CompanyVo();
    		  search.setDomain(domain);
    		  search.setDomainManage(domainManage);
    		  int count=companyManageServiceImpl.checkDomain(search);
    		  if(count==0){
    			  json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
    		  }else{
    			  json.put(JsonMsg.MSG, JsonMsg.INFORMATION);
    		  }
         } catch (Exception e) {
         	 log.info("qa：添加分校报错");
         	 json.put(JsonMsg.MSG, JsonMsg.INFORMATION);
         }
    	return json;
    }
}
