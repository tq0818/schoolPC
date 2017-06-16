package com.yuxin.wx.controller.query;

import com.yuxin.wx.api.auth.IAuthRoleService;
import com.yuxin.wx.api.company.ICompanyMarketSetService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.company.IOrganLeaveMessageBlacklistService;
import com.yuxin.wx.api.company.IOrganLeaveMessageService;
import com.yuxin.wx.api.fee.IStageService;
import com.yuxin.wx.api.pay.IPayOrderService;
import com.yuxin.wx.api.student.IStudentFeeRefundService;
import com.yuxin.wx.api.student.IStudentFeeStageService;
import com.yuxin.wx.api.system.ISysConfigDictService;
import com.yuxin.wx.api.system.ISysConfigTeacherService;
import com.yuxin.wx.api.user.IUserIntegralFlowService;
import com.yuxin.wx.api.user.IUsersFrontService;
import com.yuxin.wx.api.user.IUsersService;
import com.yuxin.wx.common.ExcelSheetEntity;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.common.ViewFiles;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.CompanyFunctionSet;
import com.yuxin.wx.model.company.OrganLeaveMessage;
import com.yuxin.wx.model.system.SysConfigDict;
import com.yuxin.wx.model.system.SysConfigService;
import com.yuxin.wx.model.system.SysConfigTeacher;
import com.yuxin.wx.model.user.UserIntegralFlow;
import com.yuxin.wx.model.user.UserLoginSession;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.utils.ExcelUtil;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.company.CompanyTotalVo;
import com.yuxin.wx.vo.fee.RefundVo;
import com.yuxin.wx.vo.fee.StagingTotalVo;
import com.yuxin.wx.vo.fee.StagingVo;
import com.yuxin.wx.vo.query.MarketingVo;
import com.yuxin.wx.vo.query.RegisterInfoVo;
import com.yuxin.wx.vo.system.PayOrderIntegralVo;
import com.yuxin.wx.vo.system.PayOrderVipVo;
import com.yuxin.wx.vo.user.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.util.SystemOutLogger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/query")
public class QueryController {
	@Autowired
	private IUsersFrontService usersFrontServiceImpl;
	@Autowired
    private IStageService stageServiceImpl;
	@Autowired
	private IStudentFeeRefundService studentFeeRefundImpl;
	@Autowired
	private IStudentFeeStageService studentFeeStageServiceImpl;
	@Autowired
	private ISysConfigDictService sysConfigDictServiceImpl;
	@Autowired
	private ICompanyService companyServiceImpl;
	@Autowired
	private IUsersService usersServiceImpl;
	@Autowired
	private IAuthRoleService authRoleServiceImpl;
	@Autowired
	private ICompanyMarketSetService companyMarketSetServiceImpl;
	@Autowired
	private IUserIntegralFlowService userIntegralFlowServiceImpl;
	@Autowired
	private IPayOrderService payOrderServiceImpl;
	
	@Autowired
	private IOrganLeaveMessageService  organLeaveMessageServiceImpl;
	@Autowired
	private IOrganLeaveMessageBlacklistService  organLeaveMessageServiceBlacklistImpl;
	@Autowired
	private ISysConfigTeacherService sysConfigTeacherServiceImpl;

	
	/**
	 * 页面跳转
	 * @param model
	 * @param name
	 * @return
	 */
	@RequestMapping(value="/page/{name}")
	public String page(Model model,@PathVariable String name,HttpServletRequest request){
		Integer userId=WebUtils.getCurrentUserId(request);
		Integer companyId = WebUtils.getCurrentCompanyId();
		model.addAttribute("schoolId",WebUtils.getCurrentSchoolId());
		model.addAttribute("schoolName",WebUtils.getCurrentUser().getSchoolName());
		/*if(authRoleServiceImpl.hasRoleFlag(userId)){
			model.addAttribute("isAdmin",true);
		}else{
			model.addAttribute("isSubAdmin",false);
		}*/
		Subject subject = SecurityUtils.getSubject();
		model.addAttribute("isAdmin",subject.hasRole("机构管理员"));
		model.addAttribute("isSubAdmin",subject.hasRole("分校管理员"));
		model.addAttribute("isProxyOrg",subject.hasRole("代理机构"));
		//代理机构
		CompanyFunctionSet userorg_roleopenflag = WebUtils.getFunctionSet("USERORG_ROLEOPENFLAG");
        model.addAttribute("userorg_roleopenflag", userorg_roleopenflag==null?0:userorg_roleopenflag.getStatus());
		if("marketing".equals(name)){
			List<String> blackList = organLeaveMessageServiceBlacklistImpl.queryList();
			model.addAttribute("blackList",blackList);
		}else if("fee".equals(name)){
			 // 学习卡服务
	    	SysConfigService service = WebUtils.getConfigService("SERVICE_STUDYCARD");
	        model.addAttribute("stydycardservice", service != null ? service.getDelFlag() : 0);
		}
		return "/query/"+name;
	}
	
	/**
	 * 查询用户图表数据
	 * @param model
	 * @param name
	 * @return UsersFront
	 */
	@ResponseBody
	@RequestMapping(value="/studentTable")
	public PageFinder<UsersFrontVo> studentTable(UsersFrontVo search){
		if("today".equals(search.getTimeMark())){
			search.setTimeLen(0);
		}else if("yesday".equals(search.getTimeMark())){
			search.setTimeLen(1);
		}else if("sevnday".equals(search.getTimeMark())){
			search.setTimeLen(7);
		}else if("thirty".equals(search.getTimeMark())){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String str=sdf.format(new Date());
			search.setTimeLen(Integer.parseInt(str.substring(8,str.length()))-1);
		}else if("thirmonth".equals(search.getTimeMark())){
			search.setTimeLen(90);
		}
		Subject subject = SecurityUtils.getSubject();
		if(search.getSchoolId() == null && !subject.hasRole("机构管理员")){
			search.setSchoolId(WebUtils.getCurrentSchoolId());
		}
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		return usersFrontServiceImpl.findUserList(search);
	}
	
	/**
	 * 查询用户图表数据
	 * @param model
	 * @param name
	 * @return UsersFront
	 */
	@ResponseBody
	@RequestMapping(value="/studentChart")
	public List<Map> studentChart(UsersFrontVo search){
		if("today".equals(search.getTimeMark())){
			search.setTimeLen(0);
		}else if("yesday".equals(search.getTimeMark())){
			search.setTimeLen(1);
		}else if("sevnday".equals(search.getTimeMark())){
			search.setTimeLen(7);
		}else if("thirty".equals(search.getTimeMark())){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String str=sdf.format(new Date());
			search.setTimeLen(Integer.parseInt(str.substring(8,str.length()))-1);
		}else if("thirmonth".equals(search.getTimeMark())){
			search.setTimeLen(90);
		}
		Subject subject = SecurityUtils.getSubject();
		if(search.getSchoolId() == null && !subject.hasRole("机构管理员")){
			search.setSchoolId(WebUtils.getCurrentSchoolId());
		}
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		List<Map> countUserByDate = usersFrontServiceImpl.countUserByDate(search);
		return countUserByDate;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/studentChartBySeven")
	public List<Map> studentChart2(){
		UsersFrontVo search =  new UsersFrontVo();
		search.setTimeLen(7);
		Subject subject = SecurityUtils.getSubject();
		if(search.getSchoolId() == null && !subject.hasRole("机构管理员")){
			search.setSchoolId(WebUtils.getCurrentSchoolId());
		}
		if(subject.hasRole("教科院")){
			search.setIsStu(1);
		}else if(subject.hasRole("区县负责人")){
			Users loginUser = WebUtils.getCurrentUser();
			//获取账号对应用户信息
			UsersAreaRelation uersAreaRelation = usersServiceImpl.findUsersAreaRelation(loginUser.getId());
			search.setEduArea(uersAreaRelation!=null ? uersAreaRelation.getEduArea():"");
			search.setIsStu(1);
		}else if(subject.hasRole("学校负责人")){
			Users loginUser = WebUtils.getCurrentUser();
			//获取账号对应用户信息
			UsersAreaRelation uersAreaRelation = usersServiceImpl.findUsersAreaRelation(loginUser.getId());
			search.setEduSchool(uersAreaRelation!=null ? uersAreaRelation.getEduSchool():"");
			search.setIsStu(1);
		}
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		List<Map> countUserByDate = usersFrontServiceImpl.countUserByDate(search);
		return countUserByDate;
	}

	/**
	 * 查询用户图表数据
	 * @param model
	 * @param name
	 * @return UsersFront
	 */
	@ResponseBody
	@RequestMapping(value="/count")
	public Map countOffLine(UsersFrontVo search){
		if("today".equals(search.getTimeMark())){
			search.setTimeLen(0);
		}else if("yesday".equals(search.getTimeMark())){
			search.setTimeLen(1);
		}else if("sevnday".equals(search.getTimeMark())){
			search.setTimeLen(7);
		}else if("thirty".equals(search.getTimeMark())){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String str=sdf.format(new Date());
			search.setTimeLen(Integer.parseInt(str.substring(8,str.length()))-1);
		}else if("thirmonth".equals(search.getTimeMark())){
			search.setTimeLen(90);
		}
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		Subject subject = SecurityUtils.getSubject();
		if(search.getSchoolId() == null && !subject.hasRole("机构管理员")){
			search.setSchoolId(WebUtils.getCurrentSchoolId());
		}
		return usersFrontServiceImpl.countOffLine(search);
	}
	
	//流水
	@ResponseBody
	@RequestMapping(value="/querylsFee")
	public PageFinder<StagingVo> queryFeeList(StagingVo search){
		if("today".equals(search.getTimeMark())){
			search.setTimeLen(0);
		}else if("yesday".equals(search.getTimeMark())){
			search.setTimeLen(1);
		}else if("sevnday".equals(search.getTimeMark())){
			search.setTimeLen(7);
		}else if("thirty".equals(search.getTimeMark())){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String str=sdf.format(new Date());
			search.setTimeLen(Integer.parseInt(str.substring(8,str.length()))-1);
		}else if("thirmonth".equals(search.getTimeMark())){
			search.setTimeLen(90);
		}
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		PageFinder<StagingVo> pageFinder=stageServiceImpl.queryList(search);
		return pageFinder;
	}
	
	@ResponseBody
	@RequestMapping(value="/querylsFeeLast5")
	public PageFinder<StagingVo> queryFeeList2(){
		StagingVo search = new StagingVo();
		search.setPageSize(5);
		search.setPage(1);
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		Subject subject = SecurityUtils.getSubject();
		if(!subject.hasRole("机构管理员")){
			search.setSchoolId(WebUtils.getCurrentSchoolId());
		}
		PageFinder<StagingVo> pageFinder=stageServiceImpl.queryList(search);
		return pageFinder;
	}
	
	//查询流水费用统计
	@ResponseBody
	@RequestMapping(value="/querylsTotal")
	public StagingTotalVo querylsFeeTotal(StagingVo search){
		if("today".equals(search.getTimeMark())){
			search.setTimeLen(0);
		}else if("yesday".equals(search.getTimeMark())){
			search.setTimeLen(1);
		}else if("sevnday".equals(search.getTimeMark())){
			search.setTimeLen(7);
		}else if("thirty".equals(search.getTimeMark())){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String str=sdf.format(new Date());
			search.setTimeLen(Integer.parseInt(str.substring(8,str.length()))-1);
		}else if("thirmonth".equals(search.getTimeMark())){
			search.setTimeLen(90);
		}
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		StagingTotalVo stf=studentFeeStageServiceImpl.querylsFeeTotal(search);
		return stf;
	}
	
	//退费
	@ResponseBody
	@RequestMapping(value="/queryrefund")
	public PageFinder<RefundVo> query(RefundVo search){
		if("today".equals(search.getTimeMark())){
			search.setTimeLen(0);
		}else if("yesday".equals(search.getTimeMark())){
			search.setTimeLen(1);
		}else if("sevnday".equals(search.getTimeMark())){
			search.setTimeLen(7);
		}else if("thirty".equals(search.getTimeMark())){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String str=sdf.format(new Date());
			search.setTimeLen(Integer.parseInt(str.substring(8,str.length()))-1);
		}else if("thirmonth".equals(search.getTimeMark())){
			search.setTimeLen(90);
		}
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		PageFinder<RefundVo> pageFinder=studentFeeRefundImpl.queryList(search);
		return pageFinder;
	}
	
	//查询退费金额统计
	@ResponseBody
	@RequestMapping(value="/querythTotal")
	public StagingTotalVo querytfTotalMoney(RefundVo search){
		if("today".equals(search.getTimeMark())){
			search.setTimeLen(0);
		}else if("yesday".equals(search.getTimeMark())){
			search.setTimeLen(1);
		}else if("sevnday".equals(search.getTimeMark())){
			search.setTimeLen(7);
		}else if("thirty".equals(search.getTimeMark())){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String str=sdf.format(new Date());
			search.setTimeLen(Integer.parseInt(str.substring(8,str.length()))-1);
		}else if("thirmonth".equals(search.getTimeMark())){
			search.setTimeLen(90);
		}
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		StagingTotalVo st=studentFeeRefundImpl.querythTotalMoney(search);
		return st;
	};
	
	/**
	 * 
	 * Class Name: QueryController.java
	 * @Description: 查询线上线下费用统计
	 * @author zhang.zx
	 * @date 2015年6月26日 上午10:54:22
	 * @modifier
	 * @modify-date 2015年6月26日 上午10:54:22
	 * @version 1.0
	 * @param search
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryonoffFee")
	public List<Map> queryTotalFee(StagingVo search){
		if("today".equals(search.getTimeMark())){
			search.setTimeLen(0);
		}else if("yesday".equals(search.getTimeMark())){
			search.setTimeLen(1);
		}else if("sevnday".equals(search.getTimeMark())){
			search.setTimeLen(7);
		}else if("thirty".equals(search.getTimeMark())){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String str=sdf.format(new Date());
			search.setTimeLen(Integer.parseInt(str.substring(8,str.length()))-1);
		}else if("thirmonth".equals(search.getTimeMark())){
			search.setTimeLen(90);
		}
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		List<Map> arr=studentFeeStageServiceImpl.queryOnoffFeeTotal(search);
		return arr;
	}

	 /**
	  * 
	  * Class Name: SysConfigNewsController.java
	  * @Description: 计算一段时间相隔天数
	  * @author zhang.zx
	  * @date 2015年6月8日 下午9:20:07
	  * @modifier
	  * @modify-date 2015年6月8日 下午9:20:07
	  * @version 1.0
	  * @param beginDateStr
	  * @param endDateStr
	  * @return
	  */
   public static Integer getDaySub(String beginDateStr,String endDateStr) {
       Integer day=0;
       java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");    
       java.util.Date beginDate;
       java.util.Date endDate;
       try{
           beginDate = format.parse(beginDateStr);
           endDate= format.parse(endDateStr);    
           day=(int) ((endDate.getTime()-beginDate.getTime())/(24*60*60*1000));    
           //System.out.println("相隔的天数="+day);   
       } catch (ParseException e){
           e.printStackTrace();
       }   
       return day;
   }
   
   /**
    * 
    * Class Name: QueryController.java
    * @Description: 查询推广来源类型
    * @author zhang.zx
    * @date 2015年7月27日 下午4:13:41
    * @modifier
    * @modify-date 2015年7月27日 下午4:13:41
    * @version 1.0
    * @return
    */
   @ResponseBody
   @RequestMapping(value="getSource")
   public List<SysConfigDict> queryUtmSource(){
	   return sysConfigDictServiceImpl.findByDicCode("SYS_UTM_SOURCE");
   }
   
   /**
    * 
    * Class Name: QueryController.java
    * @Description: 查询公司来源
    * @author zhang.zx
    * @date 2015年7月27日 下午5:44:29
    * @modifier
    * @modify-date 2015年7月27日 下午5:44:29
    * @version 1.0
    * @return
    */
   @ResponseBody
   @RequestMapping(value="/querySourceList")
   public List<CompanyTotalVo> querySourceList(CompanyTotalVo c){
	   if("today".equals(c.getMarks())){
			c.setTimeLen(0);
		}else if("yesday".equals(c.getMarks())){
			c.setTimeLen(1);
		}else if("sevnday".equals(c.getMarks())){
			c.setTimeLen(7);
		}else if("thirty".equals(c.getMarks())){
			c.setTimeLen(30);
		}
	   return companyServiceImpl.querySourceList(c);
   }
   
   /**
    * 
    * Class Name: QueryController.java
    * @Description: 用户来源信息统计
    * @author zhang.zx
    * @date 2015年7月28日 上午10:31:34
    * @modifier
    * @modify-date 2015年7月28日 上午10:31:34
    * @version 1.0
    * @return
    */
   @ResponseBody
   @RequestMapping(value="/queryUserList")
   public PageFinder<UserLoginSession>  queryUserTotalList(UserLoginSession u){
	   if("today".equals(u.getMarks())){
			u.setTimeLen(0);
		}else if("yesday".equals(u.getMarks())){
			u.setTimeLen(1);
		}else if("sevnday".equals(u.getMarks())){
			u.setTimeLen(7);
		}else if("thirty".equals(u.getMarks())){
			u.setTimeLen(30);
		}
	  PageFinder<UserLoginSession> pageFinder=usersServiceImpl.findUserTotalList(u);
	   return pageFinder;
   }
   /**
    * 
    * Class Name: QueryController.java
    * @Description: 查询公司注册信息
    * @author dongshuai
    * @date 2016年8月11日 下午5:46:05
    * @modifier
    * @modify-date 2016年8月11日 下午5:46:05
    * @version 1.0
    * @param riv
    * @return
    */
   @ResponseBody
   @RequestMapping(value="/queryRegisterInfo")
   public PageFinder<RegisterInfoVo>  queryRegisterInfo(RegisterInfoVo riv){
	   if("today".equals(riv.getMarks())){
		   riv.setTimeLen(0);
	   }else if("yesday".equals(riv.getMarks())){
		   riv.setTimeLen(1);
	   }else if("sevnday".equals(riv.getMarks())){
		   riv.setTimeLen(7);
	   }else if("thirty".equals(riv.getMarks())){
		   riv.setTimeLen(30);
	   }
	   PageFinder<RegisterInfoVo> pageFinder=companyServiceImpl.queryRegisterInfo(riv);
	   return pageFinder;
   }
   
   /**
    * 
    * Class Name: QueryController.java
    * @Description: 导出注册统计信息
    * @author dongshuai
    * @date 2016年8月11日 下午6:44:34
    * @modifier
    * @modify-date 2016年8月11日 下午6:44:34
    * @version 1.0
    * @param model
    * @param request
    * @param riv
    * @param marks
    * @return
    */
	@RequestMapping(value="exportRegisterInfo/{marks}")
	public ModelAndView exportLeaveMessage(Model model,HttpServletRequest request,RegisterInfoVo riv,@PathVariable String marks){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if("today".equals(marks)){
			riv.setMarks(marks);
			riv.setTimeLen(0);
	    }else if("yesday".equals(marks)){
	    	riv.setMarks(marks);
	    	riv.setTimeLen(1);
	    }else if("sevnday".equals(marks)){
	    	riv.setMarks(marks);
	    	riv.setTimeLen(7);
	    }else if("thirty".equals(marks)){
	    	riv.setMarks(marks);
	    	riv.setTimeLen(30);
	    }
		if(!"nos".equals(marks)){
			riv.setStartTime("");
			riv.setEndTime("");
		}
		List<RegisterInfoVo>  result=companyServiceImpl.queryRegisterInfoExport(riv);
		ExcelSheetEntity sheet=ExcelSheetEntity.newInstance("url:utmUrl,手机:mobile,注册时间:registTime", result);
		try{
			 ViewFiles excel = new ViewFiles(); 
		     Map<String,Object> map = new HashMap<String,Object>();  
		     map.put("entity", sheet);
		     map.put("fileName", "注册统计.xls");
		     return new ModelAndView(excel,map);
		}catch(Exception ex){
		}
		return new ModelAndView();
	}
   /**
    * 
    * Class Name: QueryController.java
    * @Description: 留言信息查询
    * @author dongshuai
    * @date 2016年5月31日 
    * @return
    */
   @ResponseBody
   @RequestMapping(value="/queryOrganLeaveMessage")
   public PageFinder<OrganLeaveMessage>  queryOrganLeaveMessage(OrganLeaveMessageVo olm){
	   if("today".equals(olm.getMarks())){
		   olm.setTimeLen(0);
	   }else if("yesday".equals(olm.getMarks())){
		   olm.setTimeLen(1);
	   }else if("sevnday".equals(olm.getMarks())){
		   olm.setTimeLen(7);
	   }else if("thirty".equals(olm.getMarks())){
		   olm.setTimeLen(30);
	   }
	   PageFinder<OrganLeaveMessage> pageFinder=organLeaveMessageServiceImpl.queryOrganLeaveMessageList(olm);
	   return pageFinder;
   }
   /**
    * 
    * Class Name: QueryController.java
    * @Description: 根据留言id信息查询
    * @author dongshuai
    * @date 2016年5月31日 
    * @return
    */
   @ResponseBody
   @RequestMapping(value="/queryOrganLeaveMessageById")
   public OrganLeaveMessage queryOrganLeaveMessageById(String id){
	   OrganLeaveMessage olm=new OrganLeaveMessage();
	   olm=organLeaveMessageServiceImpl.findOrganLeaveMessageById(Integer.parseInt(id));
	   return olm;
   }
   /**
    * 
    * Class Name: QueryController.java
    * @Description: 查询用户图表统计
    * @author zhang.zx
    * @date 2015年7月28日 上午11:19:04
    * @modifier
    * @modify-date 2015年7月28日 上午11:19:04
    * @version 1.0
    * @param u
    * @return
    */
   @ResponseBody
   @RequestMapping(value="/queryUserChar")
   public List<UserLoginSession> queryUserChar(UserLoginSession u){
	   if("today".equals(u.getMarks())){
			u.setTimeLen(0);
		}else if("yesday".equals(u.getMarks())){
			u.setTimeLen(1);
		}else if("sevnday".equals(u.getMarks())){
			u.setTimeLen(7);
		}else if("thirty".equals(u.getMarks())){
			u.setTimeLen(30);
		}
	   return usersServiceImpl.queryUserChar(u);
   }
   
   /**
    * 
    * Class Name: QueryController.java
    * @Description: 注册人数统计
    * @author zhang.zx
    * @date 2015年7月29日 下午1:58:44
    * @modifier
    * @modify-date 2015年7月29日 下午1:58:44
    * @version 1.0
    * @param u
    * @return
    */
   @ResponseBody
   @RequestMapping(value="/queryRegistNumList")
   public PageFinder<CompanyTotalVo>  queryLoginList(CompanyTotalVo c){
	   if("today".equals(c.getMarks())){
			c.setTimeLen(0);
		}else if("yesday".equals(c.getMarks())){
			c.setTimeLen(1);
		}else if("sevnday".equals(c.getMarks())){
			c.setTimeLen(7);
		}else if("thirty".equals(c.getMarks())){
			c.setTimeLen(30);
		}
	  PageFinder<CompanyTotalVo> pageFinder=companyServiceImpl.queryCompanyRegistNum(c);
	   return pageFinder;
   }
   
   /**
    * 
    * Class Name: QueryController.java
    * @Description: 注册人数图表统计
    * @author zhang.zx
    * @date 2015年7月29日 下午1:58:44
    * @modifier
    * @modify-date 2015年7月29日 下午1:58:44
    * @version 1.0
    * @param u
    * @return
    */
   @ResponseBody
   @RequestMapping(value="/queryRegistChar")
   public List<CompanyTotalVo>  queryRegistChar(CompanyTotalVo c){
	   if("today".equals(c.getMarks())){
			c.setTimeLen(0);
		}else if("yesday".equals(c.getMarks())){
			c.setTimeLen(1);
		}else if("sevnday".equals(c.getMarks())){
			c.setTimeLen(7);
		}else if("thirty".equals(c.getMarks())){
			c.setTimeLen(30);
		}
	 List<CompanyTotalVo> arr=companyServiceImpl.queryRegistNumChar(c);
	   return arr;
   }
   
   /**
    * 
    * Class Name: QueryController.java
    * @Description: 导出URL统计数据
    * @author zhang.zx
    * @date 2015年7月29日 下午1:58:44
    * @modifier
    * @modify-date 2015年7月29日 下午1:58:44
    * @version 1.0
    * @param u
    * @return
    */
   @RequestMapping(value="/queryRegistNumList/export")
   public ModelAndView  exportRegistNumList(CompanyTotalVo c){
	   if("today".equals(c.getMarks())){
			c.setTimeLen(0);
		}else if("yesday".equals(c.getMarks())){
			c.setTimeLen(1);
		}else if("sevnday".equals(c.getMarks())){
			c.setTimeLen(7);
		}else if("thirty".equals(c.getMarks())){
			c.setTimeLen(30);
		}
	   c.setPageSize(100);
	   Map<String,Object> search =new HashMap<String,Object>();
	   search.put("sourceType", c.getSourceType());
	   search.put("marks",c.getMarks());
	   search.put("timeLen", c.getTimeLen());
	   search.put("startTime", c.getStartTime());
	   search.put("endTime", c.getEndTime());
	   List<Company> data=companyServiceImpl.queryRegisterCompany(search);
	   ViewFiles excel = new ViewFiles(); 
	     Map<String,Object> map = new HashMap<String,Object>();  
	     ExcelSheetEntity entity=ExcelSheetEntity.newInstance("公司名称:companyName,手机号:mobile,用户名:username,域名:domain,是否购买:buyFlag,注册时间:registTime",data);
	     map.put("entity", entity);
	     map.put("fileName", "注册列表.xls");
	     return new ModelAndView(excel,map);
   }
   
   /**
    * 
    * Class Name: QueryController.java
    * @Description: URL统计
    * @author zhang.zx
    * @date 2015年7月29日 下午1:58:44
    * @modifier
    * @modify-date 2015年7月29日 下午1:58:44
    * @version 1.0
    * @param u
    * @return
    */
   @ResponseBody
   @RequestMapping(value="/queryUrlList")
   public PageFinder<MarketingVo>  queryUrlList(CompanyTotalVo c){
	   
	   if("today".equals(c.getMarks())){
			c.setTimeLen(0);
		}else if("yesday".equals(c.getMarks())){
			c.setTimeLen(1);
		}else if("sevnday".equals(c.getMarks())){
			c.setTimeLen(7);
		}else if("thirty".equals(c.getMarks())){
			c.setTimeLen(30);
		}
	   c.setPageSize(100);
	   PageFinder<MarketingVo> result=companyMarketSetServiceImpl.findMarketingPageByDate(c);
	   return result;
   }
   
   /**
    * 
    * Class Name: QueryController.java
    * @Description: 导出URL统计数据
    * @author zhang.zx
    * @date 2015年7月29日 下午1:58:44
    * @modifier
    * @modify-date 2015年7月29日 下午1:58:44
    * @version 1.0
    * @param u
    * @return
    */
   @RequestMapping(value="/queryUrlList/export")
   public ModelAndView  exportUrlList(CompanyTotalVo c){
	   if("today".equals(c.getMarks())){
			c.setTimeLen(0);
		}else if("yesday".equals(c.getMarks())){
			c.setTimeLen(1);
		}else if("sevnday".equals(c.getMarks())){
			c.setTimeLen(7);
		}else if("thirty".equals(c.getMarks())){
			c.setTimeLen(30);
		}
	  List<MarketingVo> result=companyMarketSetServiceImpl.findMarketingByDate(c);
	   ViewFiles excel = new ViewFiles(); 
	     Map<String,Object> map = new HashMap<String,Object>();  
	     ExcelSheetEntity entity=ExcelSheetEntity.newInstance("url:utmUrl,点击量:pv,注册量:uv",result);
	     map.put("entity", entity);
	     map.put("fileName", "url列表.xls");
	     return new ModelAndView(excel,map);
   }
   
   /**
    * 
    * Class Name: QueryController.java
    * @Description: 积分统计
    * @author zhang.zx
    * @date 2016年5月19日 上午11:02:53
    * @modifier
    * @modify-date 2016年5月19日 上午11:02:53
    * @version 1.0
    * @param search
    * @return
    */
   @ResponseBody
   @RequestMapping(value="/queryIntegralList")
   public PageFinder<UsersFrontIntegralVo> queryStudentIntegralTotal(UsersFrontIntegralVo search){
	   search.setCompanyId(WebUtils.getCurrentCompanyId());
	   return usersFrontServiceImpl.queryStudentIntegralTotal(search);
   }
   
   /**
    * 
    * Class Name: QueryController.java
    * @Description: 查询积分详情
    * @author zhang.zx
    * @date 2016年5月19日 上午11:46:48
    * @modifier
    * @modify-date 2016年5月19日 上午11:46:48
    * @version 1.0
    * @param search
    * @return
    */
   @ResponseBody
   @RequestMapping(value="/queryIntegralFlowDetail")
   public List<UserIntegralFlow> queryIntegralFlowByWhere(UserIntegralFlowVO search){
	   if("today".equals(search.getMarks())){
		   search.setTimeLen(0);
		}else if("yesday".equals(search.getMarks())){
			search.setTimeLen(1);
		}else if("sevnday".equals(search.getMarks())){
			search.setTimeLen(7);
		}else if("thirty".equals(search.getMarks())){
			search.setTimeLen(30);
		}
	   return userIntegralFlowServiceImpl.queryIntegralFlowByWhere(search);
   }
   
   /**
    * 
    * Class Name: QueryController.java
    * @Description: 积分订单
    * @author zhang.zx
    * @date 2016年5月19日 下午4:04:45
    * @modifier
    * @modify-date 2016年5月19日 下午4:04:45
    * @version 1.0
    * @param search
    * @return
    */
   @ResponseBody
   @RequestMapping(value="/queryOrderIntegral")
   public PageFinder<PayOrderIntegralVo> queryIntegralOrder(PayOrderIntegralVo search){
	   search.setCompanyId(WebUtils.getCurrentCompanyId());
	   if("today".equals(search.getTimeMark())){
		   search.setTimeLen(0);
		}else if("yesday".equals(search.getTimeMark())){
			search.setTimeLen(1);
		}else if("sevnday".equals(search.getTimeMark())){
			search.setTimeLen(7);
		}else if("thirty".equals(search.getTimeMark())){
			search.setTimeLen(30);
		}
	   PageFinder<PayOrderIntegralVo> pageFinder=payOrderServiceImpl.queryIntegralOrder(search);
	   return pageFinder;
   }
   
   /**
    * 
    * @Description: 会员订单
    * @author: dongshuai
    * @date: 2016年5月24日
    * @param search
    * @return
    *
    */
   @ResponseBody
   @RequestMapping(value="/queryVipOrder")
   public PageFinder<PayOrderVipVo> queryVipOrder(PayOrderVipVo search){
	   search.setCompanyId(WebUtils.getCurrentCompanyId());
	   if("today".equals(search.getTimeMark())){
		   search.setTimeLen(0);
		}else if("yesday".equals(search.getTimeMark())){
			search.setTimeLen(1);
		}else if("sevnday".equals(search.getTimeMark())){
			search.setTimeLen(7);
		}else if("thirty".equals(search.getTimeMark())){
			search.setTimeLen(30);
		}else if("thirmonth".equals(search.getTimeMark())){
			   search.setTimeLen(90);
		 }
	   search.setPageSize(10);
	   PageFinder<PayOrderVipVo> pageFinder=payOrderServiceImpl.queryVipOrder(search);
	   return pageFinder;
   }
   
   @RequestMapping(value="/exportVipOrder/{mark}")
   public ModelAndView exportLeaveMessage(String memberId,String mobile,String stuName,String startTime,String endTime,String username,@PathVariable String mark){
	   PayOrderVipVo search=new PayOrderVipVo();
	   search.setCompanyId(WebUtils.getCurrentCompanyId());
	   search.setUsername(username);
	   if(!"".equals(memberId) && null!=memberId){
		   search.setMemberId(Integer.parseInt(memberId));
	   }
	   if(!"".equals(mobile) && null!=mobile){
		   search.setMobile(mobile);
	   }
	   if(!"".equals(stuName) && null!=stuName){
		   search.setStuName(stuName);
	   }
	   if(!"".equals(startTime) && null!=startTime){
		   search.setStartTime(startTime);
	   }
	   if(!"".equals(endTime) && null!=endTime){
		   search.setEndTime(endTime);
	   }
		if("today".equals(mark)){
			search.setTimeMark(mark);
			search.setTimeLen(0);
		}else if("yesday".equals(mark)){
			search.setTimeMark(mark);
			search.setTimeLen(1);
		}else if("sevnday".equals(mark)){
			search.setTimeMark(mark);
			search.setTimeLen(7);
		}else if("thirty".equals(mark)){
			search.setTimeMark(mark);
			search.setTimeLen(30);
		}else if("thirmonth".equals(mark)){
			search.setTimeMark(mark);
			search.setTimeLen(90);
		}
		if(!"nos".equals(mark)){
			search.setStartTime("");
			search.setEndTime("");
		}
		List<PayOrderVipVo>  result=payOrderServiceImpl.findVipPayOrderListForExport(search);
		for (PayOrderVipVo povo : result) {
			if(null!=povo.getMemberLength() && !"".equals(povo.getMemberLength())){
				if(povo.getMemberLength()==-1){
					povo.setOrderVipMemberLength("-");
				}else{
					povo.setOrderVipMemberLength(povo.getMemberLength().toString()+"个月");
				}
			}else{
				povo.setOrderVipMemberLength("");
			}
			if(null!=povo.getOrderTime() && null!=povo.getMemberLength() && !"".equals(povo.getMemberLength())){
				if(povo.getMemberLength()==-1 ){
					povo.setMemberEndTime(null);
					povo.setOrderVipEndTime("无限期");
				}else{
					Date d = povo.getOrderTime();
					Calendar ca = Calendar.getInstance();
					ca.setTime(povo.getOrderTime());
					ca.add(Calendar.DATE, povo.getMemberLength()*30);
					d = ca.getTime();
					povo.setMemberEndTime(d);
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
					povo.setOrderVipEndTime(sdf.format(d));
				}
			}else{
				povo.setOrderVipEndTime("");
			}
			String payType_str="";
			String applyChannelCode_str="";
			if(!"".equals(povo.getPayType()) && null!=povo.getPayType()){
				String[] payTypes=povo.getPayType().split(",");
				for (String pt : payTypes) {
					if("PAY_METHOD_CASH".equals(pt)){
						payType_str+="现金，";
					}
					if("PAY_METHOD_POS".equals(pt)){
						payType_str+="POS，";
					}
					if("PAY_METHOD_CHECK".equals(pt)){
						payType_str+="支票，";
					}
					if("PAY_METHOD_ONLINE_PAY".equals(pt)){
						payType_str+="在线支付，";
					}
					if("PAY_METHOD_TRANSFE".equals(pt)){
						payType_str+="转账，";
					}
					if("PAY_METHOD_LOAN".equals(pt)){
						payType_str+="贷款，";
					}
					if("PAY_TYPE_ZFB".equals(pt)){
						payType_str+="支付宝，";
					}
					if("PAY_TYPE_SXY".equals(pt)){
						payType_str+="首信易在线支付，";
					}
					if("PAY_TYPE_YYZF".equals(pt)){
						payType_str+="银联语音支付，";
					}
					if("PAY_TYPE_KJZF".equals(pt)){
						payType_str+="银联快捷支付，";
					}
					if("PAY_TYPE_GRDB".equals(pt)){
						payType_str+="个人担保交易，";
					}
					if("PAY_TYPE_REMIT".equals(pt)){
						payType_str+="银行汇款，";
					}
					if("PAY_TYPE_ZFB_ZZ".equals(pt)){
						payType_str+="支付宝转账，";
					}
					if("PAY_TYPE_WX_PERSON".equals(pt)){
						payType_str+="微信个人收款，";
					}
					if("PAY_TYPE_WX_GZH".equals(pt)){
						payType_str+="微信公众号支付，";
					}
				}
			}
			if(!"".equals(payType_str)){
				povo.setPayType(payType_str.substring(0,payType_str.length()-1));
			}else{
				povo.setPayType("其他");
			}
			if(!"".equals(povo.getApplyChannelCode()) && null!=povo.getApplyChannelCode() && !"".equals(povo.getCommodityName()) && null!=povo.getCommodityName()){
				if("CHANNEL_ONLINE".equals(povo.getApplyChannelCode())){
					applyChannelCode_str="线上"+povo.getCommodityName();
					povo.setApplyChannelCode(applyChannelCode_str);
				}else{
					applyChannelCode_str="线下"+povo.getCommodityName();
					povo.setApplyChannelCode(applyChannelCode_str);
				}
			}
			
		}
		ExcelSheetEntity sheet=ExcelSheetEntity.newInstance("姓名:stuName,用户名:username,手机号:mobile,会员等级:orderMemberLevel,有效期:orderVipMemberLength,订单日期:orderTime,购买方式:payType,金额:payPrice,订单来源:applyChannelCode", result);
		try{
			 ViewFiles excel = new ViewFiles(); 
		     Map<String,Object> map = new HashMap<String,Object>();  
		     map.put("entity", sheet);
		     map.put("fileName", "会员订单.xls");
		     return new ModelAndView(excel,map);
		}catch(Exception ex){
			
		}
		return new ModelAndView();
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
	 * Class Name: QueryController.java
	 * @Description: 导出费用退费表
	 * @author 周文斌
	 * @date 2016-7-12 下午4:35:34
	 * @version 1.0
	 * @param model
	 * @param search
	 * @param types
	 * @return
	 */
	@RequestMapping("/exprotTuif")
	public ModelAndView exprotTuif(Model model,RefundVo search,String types){
		search.setPageSize(100000);
		if("today".equals(search.getTimeMark())){
			search.setTimeLen(0);
		}else if("yesday".equals(search.getTimeMark())){
			search.setTimeLen(1);
		}else if("sevnday".equals(search.getTimeMark())){
			search.setTimeLen(7);
		}else if("thirty".equals(search.getTimeMark())){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String str=sdf.format(new Date());
			search.setTimeLen(Integer.parseInt(str.substring(8,str.length()))-1);
		}else if("thirmonth".equals(search.getTimeMark())){
			search.setTimeLen(90);
		}
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		PageFinder<RefundVo> pageFinder=studentFeeRefundImpl.queryList(search);
		int xuhao = 1;
		List<Map<String,Object>> lists = new ArrayList<Map<String,Object>>();
		for(RefundVo s:pageFinder.getData()){
			String payMethod = "";
			double cou = 0.0;
			if(s.getCheckRefund() != null){
				cou+=s.getCheckRefund();
				payMethod+="支票 ";
			}
			if(s.getPosRefund() != null){
				cou+=s.getPosRefund();
				payMethod+="POS ";
			}
			if(s.getCashRefund() != null){
				cou+=s.getCashRefund();
				payMethod+="现金 ";
			}
			if(s.getRemitRefund() != null){
				cou+=s.getRemitRefund();
				payMethod+="转账";
			}
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("xuhao", xuhao);
			map.put("schoolName", s.getSchoolName());
			map.put("stuName", s.getStuName());
			map.put("applyTime", (s.getRefundDate()!=null?new SimpleDateFormat("yyyy-MM-dd")
				.format(s.getRefundDate()):""));
			map.put("itemName", s.getItemOneName());
			map.put("itemTwoName", s.getItemSecondName());
			map.put("className", s.getClassTypeName());
			if(s.getApplyChannelCode().equals("CHANNEL_ONLINE")){
				map.put("types", "线上报名");
			}else{
				map.put("types", "线下报名");
			}
			map.put("payMethod", payMethod);
			map.put("cou", new BigDecimal(cou).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue());
			
			lists.add(map);
			xuhao++;
		}
		StringBuffer title=new StringBuffer("序号:xuhao,所属分校:schoolName" +
				",姓名:stuName,退费日期:applyTime,科目:itemName," +
				"科目小类:itemTwoName,课程:className,退费类型:types," +
				"退费方式:payMethod,金额:cou");
		ViewFiles excel = new ViewFiles(); 
		HSSFWorkbook wb=new HSSFWorkbook();
		try{
			wb=ExcelUtil.newWorkbook(lists, "sheet1", title.toString());
		}catch(Exception ex){
			
		}
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("workbook", wb);
		map.put("fileName", "费用退费表.xls");
		return new ModelAndView(excel,map);
	}
	
	/**
	 * 
	 * Class Name: QueryController.java
	 * @Description: 导出费用流水表
	 * @author 周文斌
	 * @date 2016-7-12 下午4:35:22
	 * @version 1.0
	 * @param model
	 * @param search
	 * @param types
	 * @return
	 */
	@RequestMapping("/exprotLius")
	public ModelAndView exprotLius(Model model,StagingVo search,String types){
		search.setPageSize(100000);
		if("today".equals(search.getTimeMark())){
			search.setTimeLen(0);
		}else if("yesday".equals(search.getTimeMark())){
			search.setTimeLen(1);
		}else if("sevnday".equals(search.getTimeMark())){
			search.setTimeLen(7);
		}else if("thirty".equals(search.getTimeMark())){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String str=sdf.format(new Date());
			search.setTimeLen(Integer.parseInt(str.substring(8,str.length()))-1);
		}else if("thirmonth".equals(search.getTimeMark())){
			search.setTimeLen(90);
		}
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		PageFinder<StagingVo> pageFinder=stageServiceImpl.queryList(search);
		int xuhao = 1;
		List<Map<String,Object>> lists = new ArrayList<Map<String,Object>>();
		for(StagingVo s:pageFinder.getData()){
			String payMethod = "";
			double cou = 0.0;
			if(s.getCheckReal() != null && s.getCheckReal() !=-1 && s.getCheckReal()>0.0){
				cou+=s.getCheckReal();
				payMethod+="支票 ";
			}
			if(s.getPosReal() != null && s.getPosReal() !=-1 && s.getPosReal()>0.0){
				cou+=s.getPosReal();
				payMethod+="POS ";
			}
			if(s.getCashReal() != null && s.getCashReal() !=-1&& s.getCashReal()>0.0){
				cou+=s.getCashReal();
				payMethod+="现金 ";
			}
			if(s.getRemitReal() != null && s.getRemitReal() !=-1&& s.getRemitReal()>0.0){
				cou+=s.getRemitReal();
				payMethod+="转账";
			}
			if(s.getCheckReal() ==-1 || s.getRemitReal() ==-1 || 
					s.getCashReal() ==-1 || s.getPosReal() ==-1){
				if(s.getStageFee() != null){
					cou+=s.getStageFee();
				}
			}
			if(s.getPayMethod() != null){
				if(s.getPayMethod().equals("PAY_TYPE_ZFB")){
					payMethod="支付宝";
				}else if(s.getPayMethod().equals("PAY_TYPE_REMIT")){
					payMethod="银行汇款";
				}else if(s.getPayMethod().equals("PAY_TYPE_ZFB_ZZ")){
					payMethod="支付宝转账";
				}else if(s.getPayMethod().equals("PAY_TYPE_WX_PERSON")){
					payMethod="微信个人收款";
				}else if(s.getPayMethod().equals("PAY_TYPE_WX_GZH")){
					payMethod="微信公众号";
				}
			}
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("xuhao", xuhao);
			map.put("schoolName", s.getSchoolName());
			map.put("stuName", s.getStuName());
			map.put("applyTime", (s.getApplyTime()!=null?new SimpleDateFormat("yyyy-MM-dd")
				.format(s.getApplyTime()):""));
			map.put("itemName", s.getItemOneName());
			map.put("className", s.getClassTypeName());
			map.put("payDate", (s.getPayDate() != null?new SimpleDateFormat("yyyy-MM-dd")
				.format(s.getPayDate()):""));
			if(s.getApplyChannelCode().equals("CHANNEL_ONLINE")){
				map.put("types", "线上报名");
			}else{
				map.put("types", "线下报名");
			}
			map.put("payMethod", payMethod);
			map.put("cou", new BigDecimal(cou).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue());
			
			lists.add(map);
			xuhao++;
		}
		StringBuffer title=new StringBuffer("序号:xuhao,所属分校:schoolName" +
				",姓名:stuName,报名时间:applyTime,科目:itemName," +
				"课程:className,缴费日期:payDate,类型:types," +
				"缴费方式:payMethod,金额:cou");
		ViewFiles excel = new ViewFiles(); 
		HSSFWorkbook wb=new HSSFWorkbook();
		try{
			wb=ExcelUtil.newWorkbook(lists, "sheet1", title.toString());
		}catch(Exception ex){
			
		}
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("workbook", wb);
		map.put("fileName", "费用流水表.xls");
		return new ModelAndView(excel,map);
	}
}
