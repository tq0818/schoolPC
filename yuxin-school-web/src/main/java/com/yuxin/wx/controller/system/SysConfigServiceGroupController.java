package com.yuxin.wx.controller.system;

import com.yuxin.wx.api.classes.IClassTypeService;
import com.yuxin.wx.api.company.*;
import com.yuxin.wx.api.system.*;
import com.yuxin.wx.api.user.IUsersService;
import com.yuxin.wx.common.CacheService;
import com.yuxin.wx.common.CacheService.Ckey;
import com.yuxin.wx.common.Constants;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.CompanyFunctionSet;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.model.company.CompanyNewStep;
import com.yuxin.wx.model.system.SysConfigService;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.utils.DateUtil;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.system.SysConfigPrivatePageVo;
import com.yuxin.wx.vo.system.SysServiceDredgeVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/serviceGroup")
public class SysConfigServiceGroupController {
	private Log log = LogFactory.getLog("log");

	@Autowired
	private ISysServiceDredgeConfigService sysServiceDredgeImpl;
	@Autowired
	private ISysConfigServiceService configservice;
	@Autowired
	private ICompanyNewStepService companyNewStepServiceImpl;
	@Autowired
	private IUsersService usersServiceImpl;
	@Autowired
	private ICompanyMemberServiceService companyMemberServiceServiceImpl;
	@Autowired
	private ICompanyService companyServiceImpl;
	@Autowired
	private ICompanyFunctionSetService companyFunctionSetImpl;
	@Autowired
	private ISysConfigServiceService sysComfigServiceImpl; 
	@Autowired
	private ISysCyclePicService cyclePicService;
	@Autowired
	private ISysPageHeadFootService sysPageHeadFootService;
	@Autowired
	private ISysNewsService sysNewsServiceImpl;
	@Autowired
	private IClassTypeService classTypeServiceImpl;
	@Autowired
	private ICompanyIndexTemplateService companyIndexTemplateServiceImpl; 
	@Autowired
	private ISysBodyService sysBodySerivceImpl;
	@Autowired
	private ISysConfigDictService sysConfigDictServiceImpl;
	@Autowired
	private ICompanyLiveConcurrentService companyLiveConcurrentServiceImpl;
	@Autowired
	private ICompanyHeadFootConfigService companyHeadFootConfigServiceImpl;
	@Autowired
	private ICompanyFootInfoService companyFootInfoServiceImpl;
	@Autowired
	private ICompanyLoginConfigService companyLoginConfigServiceImpl;
	@Autowired
	private ICompanyRegisterConfigService companyRegisterConfigServiceImpl;

	//跳转到公司服务页面
	@RequestMapping("/chooseCompanyService")
	public String chooseCompanyService(Model model){
		// 做查询
		List<CompanyNewStep> isExists=companyNewStepServiceImpl.findCompanyNewStepByCompany(WebUtils.getCurrentCompanyId());
		if(isExists!=null && isExists.size()>0){
			CompanyNewStep entity=isExists.get(0);
			if(entity.getNewStepFlag()!=null && (int)entity.getNewStepFlag() == 1){
				return "redirect:/index";
			}
		}
		Company company=companyServiceImpl.findCompanyById(WebUtils.getCurrentCompanyId());
		model.addAttribute("company", company);
		return "../../companyService";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(SysConfigService service) {
		configservice.update(service);
		return "success";
	}
	
	public void relogin(){
		Subject subject = SecurityUtils.getSubject();
		Users users=usersServiceImpl.queryUserByName(subject.getPrincipal().toString());
		subject.logout();
		UsernamePasswordToken token =  new UsernamePasswordToken(users.getUsername(), users.getPassword());
		subject.login(token);
		subject.getSession().setAttribute("loginUser", users);
		//设置登录后的状态
        setUserStatus();
		Company company=companyServiceImpl.findCompanyById(users.getCompanyId());
		String status = String.valueOf(company.getStatus());
		if(company.getMemberLevel() < 20){
            List<SysServiceDredgeVo> ssdVo = sysServiceDredgeImpl.findDredgeByCom(company.getId());
			if(ssdVo != null){
				for (SysServiceDredgeVo s : ssdVo) {
					subject.getSession().setAttribute(s.getName(), 
							(s.getStatus() != null ? s.getStatus() : 0));
				}
			}
		}
	    try {
      	   //将机构使用视频服务存入session
      	   CompanyMemberService service=companyMemberServiceServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
      	   if(service!=null){
      		 subject.getSession().setAttribute("useVideo", service.getVideoServiceProvider());
      	   }
      	   
      	    //将公司课程版本存入session(1表示简易版，0或空值表示复杂版)
	   		CompanyFunctionSet search=new CompanyFunctionSet();
	   		search.setCompanyId(WebUtils.getCurrentCompanyId());
	   		search.setFunctionCode("COMPANY_FUNCTION_COURSE");
	   		CompanyFunctionSet functionSet=companyFunctionSetImpl.findCompanyUseCourse(search);
	   		if(functionSet instanceof Object){
	   		   subject.getSession().setAttribute("courseFunction", functionSet.getStatus());
	   		}else{
	   		   subject.getSession().setAttribute("courseFunction", "");
	   		}
		} catch (Exception e) {
			log.info("存入session值失败",e);
			e.printStackTrace();
		}
		WebUtils.setSessionAttribute("company_status", status);//公司认证标记
		WebUtils.setSessionAttribute("company_buy_flag", company.getBuyFlag());//购买标记
		WebUtils.setSessionAttribute("company_try_flag",DateUtil.daysBetween(company.getMemberEndDate(),(new Date())));//试用期
	}
	
	/**
	 * 
	 * 查询机构的 购买标记、试用标记、认证标记，计入session
	 * 
	 */
	public void setUserStatus(){
		Company company=companyServiceImpl.findCompanyById(WebUtils.getCurrentCompanyId());
		List<CompanyNewStep> l=companyNewStepServiceImpl.findCompanyNewStepByCompany(WebUtils.getCurrentCompanyId());
		String status = String.valueOf(company.getStatus());

		WebUtils.setSessionAttribute("company_status", status);//公司认证标记
		WebUtils.setSessionAttribute("company_buy_flag", company.getBuyFlag());//购买标记
		if(l!=null && !l.isEmpty() && l.get(0)!=null){
			WebUtils.setSessionAttribute("company_first_use", l.get(0).getNewStepFlag());
		}else{
			WebUtils.setSessionAttribute("company_first_use", null);
		}
		System.out.println(daysBetween(company.getMemberEndDate(),(new Date())));
		WebUtils.setSessionAttribute("company_try_flag",daysBetween(company.getMemberEndDate(),(new Date())));//试用期
	}
	private Integer daysBetween(Date smdate,Date bdate)    
    {    
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			smdate = sdf.parse(sdf.format(smdate));
			bdate = sdf.parse(sdf.format(bdate));
			Calendar cal = Calendar.getInstance();
			cal.setTime(smdate);
			long time1 = cal.getTimeInMillis();
			cal.setTime(bdate);
			long time2 = cal.getTimeInMillis();
			long between_days = (time2 - time1) / (1000 * 3600 * 24);
			return Integer.parseInt(String.valueOf(between_days));
		} catch (Exception e) {
			return null;
		}
    } 
	//选择视频服务（七牛或cc）
	public void addVideoService(Integer companyId,String ser){
		//添加服务
		if(ser==null||"".equals(ser)){
			ser="VIDEO_STORAGE_TYPE_QN";
		}
		CompanyMemberService service=companyMemberServiceServiceImpl.findByCompanyId(companyId);
		if(null!=service){
			CompanyMemberService entity=new CompanyMemberService();
			entity.setId(service.getId());
			entity.setVideoServiceProvider(ser);
			companyMemberServiceServiceImpl.update(entity);
		}else{
			CompanyMemberService entity=new CompanyMemberService();
			entity.setVideoServiceProvider(ser);
			companyMemberServiceServiceImpl.insert(entity);
		}
	}
	
	/**
	 * 
	 * Class Name: SysConfigServiceGroupController.java
	 * @Description: 查询当前公司使用课程服务方式
	 * @author zhang.zx
	 * @date 2015年9月6日 下午7:10:54
	 * @modifier
	 * @modify-date 2015年9月6日 下午7:10:54
	 * @version 1.0
	 * @return
	 */
//	@ResponseBody
//	@RequestMapping(value="/couseMethod",method=RequestMethod.POST)
//	public String queryCompanyUseCourseMethod(){
//		
//		return WebUtils.getCompanyCourseMethod();
//	}
	//简单、复杂版课程开关
	@ResponseBody
	@RequestMapping(value="/couseMethod",method=RequestMethod.POST)
	public CompanyFunctionSet queryCompanyUseCourseMethod(){
		Integer companyId=WebUtils.getCurrentCompanyId();
		CompanyFunctionSet search=new CompanyFunctionSet();
		search.setCompanyId(companyId);
		search.setFunctionCode("COMPANY_FUNCTION_COURSE");
		return companyFunctionSetImpl.findCompanyUseCourse(search);
	}
	
	//简单、复杂版课程开关
	@ResponseBody
	@RequestMapping(value="/couseSetting",method=RequestMethod.POST)
	public CompanyFunctionSet queryCompanycouseSet(){
		Integer companyId=WebUtils.getCurrentCompanyId();
		CompanyFunctionSet search=new CompanyFunctionSet();
		search.setCompanyId(companyId);
		search.setFunctionCode("COURSE_VALIDITY_COURSE_LEVEL");
		return companyFunctionSetImpl.findCompanyUseCourse(search);
	}
	
	//课程三级标签开关
	@ResponseBody
	@RequestMapping(value="/lableSetting",method=RequestMethod.POST)
	public CompanyFunctionSet queryCompanylableSet(){
		Integer companyId=WebUtils.getCurrentCompanyId();
		CompanyFunctionSet search=new CompanyFunctionSet();
		search.setCompanyId(companyId);
		search.setFunctionCode("COURSE_THIRD_CATEGORY");
		return companyFunctionSetImpl.findCompanyUseCourse(search);
	}
	
	//课程二级标签开关
	@ResponseBody
	@RequestMapping(value="/lableSeondSetting",method=RequestMethod.POST)
	public CompanyFunctionSet lableSeondSetting(){
		Integer companyId=WebUtils.getCurrentCompanyId();
		CompanyFunctionSet search=new CompanyFunctionSet();
		search.setCompanyId(companyId);
		search.setFunctionCode("COURSE_SECOND_CATEGORY");
		return companyFunctionSetImpl.findCompanyUseCourse(search);
	}
	
	public String useTemplate(HttpServletRequest request,Integer id,Integer schoolId,Integer companyId){
		companyIndexTemplateServiceImpl.useTemplate(companyId, schoolId, id);
		List<SysConfigPrivatePageVo>list=sysBodySerivceImpl.publishToFront(companyId, schoolId, id);
		Ckey ckey=new Ckey();
		ckey.setModule(Constants.MODULE_BODY);
		ckey.setCompanyId(""+companyId);
		ckey.setSchoolId(""+companyId);
		CacheService.updateCache(ckey,list);
		return "success";
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
}
