package com.yuxin.wx.controller.microSchool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.company.ICompanyFootInfoService;
import com.yuxin.wx.api.company.ICompanyFunctionSetService;
import com.yuxin.wx.api.company.ICompanyMemberServiceService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.api.system.ISysConfigServiceService;
import com.yuxin.wx.api.system.ISysPageHeadFootService;
import com.yuxin.wx.common.JsonMsg;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.CompanyFootInfo;
import com.yuxin.wx.model.company.CompanyFunctionSet;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.model.system.SysConfigService;
import com.yuxin.wx.model.system.SysPageHeadFoot;
import com.yuxin.wx.utils.WebUtils;

@Controller
@RequestMapping("/microSchool")
public class MicroSchoolController {
	
	@Autowired
	private ICompanyMemberServiceService companyMemberServiceServiceImpl;
	@Autowired
	private ICompanyServiceStaticService companyServiceStaticServiceImpl;
	@Autowired
	private ICompanyService companyServiceImpl;
	@Autowired
	private ISysPageHeadFootService sysPageHeadFootServiceImpl;
	@Autowired
	private ICompanyFootInfoService companyFootInfoserviceImpl;
	@Autowired
	private ICompanyFunctionSetService companyFunctionSetServiceImpl;
	@Autowired
	private ISysConfigServiceService sysConfigServiceServiceImpl;
	
	/**
	 * 
	 * Class Name: MicroSchoolController.java
	 * @Description: 进入导航配置
	 * @author dongshuai
	 * @date 2017年2月16日 下午3:42:51
	 * @modifier
	 * @modify-date 2017年2月16日 下午3:42:51
	 * @version 1.0
	 * @return
	 */
	@RequestMapping(value="/gotoNavigationConfig")
	public String gotoNavigationConfig(Model model){
		this.queryCompanyInfo(model);	
		this.queryServiceOpenFlags(model);
		return "microSchool/navigationConfig";
	}
	
	/**
	 * 
	 * Class Name: MicroSchoolController.java
	 * @Description: 进入页尾设置
	 * @author dongshuai
	 * @date 2017年2月16日 下午3:44:24
	 * @modifier
	 * @modify-date 2017年2月16日 下午3:44:24
	 * @version 1.0
	 * @return
	 */
	@RequestMapping(value="/gotoFooterConfig")
	public String gotoFooterConfig(Model model){
		this.queryCompanyInfo(model);
		this.queryFootOpenFlag(model);
		this.queryCompanyFootInfo(model);
		return "microSchool/footerConfig";
	}
	
	/**
	 * 
	 * Class Name: MicroSchoolController.java
	 * @Description: 进入轮播图设置
	 * @author dongshuai
	 * @date 2017年2月16日 下午3:45:59
	 * @modifier
	 * @modify-date 2017年2月16日 下午3:45:59
	 * @version 1.0
	 * @return
	 */
	@RequestMapping(value="/gotoCarouselFigureConfig")
	public String gotoCarouselFigureConfig(Model model){
		this.queryCompanyInfo(model);
		Integer companyId = WebUtils.getCurrentCompanyId();
		CompanyFunctionSet search = new CompanyFunctionSet();
		search.setFunctionCode("MICROSCHOOL_CYCLEUSE");
		search.setCompanyId(companyId);
		search = this.companyFunctionSetServiceImpl.findCompanyUseCourse(search);
		
		if(search == null){
			search = new CompanyFunctionSet();
			search.setCompanyId(companyId);
			search.setFunctionCode("MICROSCHOOL_CYCLEUSE");
			search.setFunctionName("微网校轮播图配置开关");
			search.setContent("0：使用PC端轮播图；1：使用微网校轮播图");
			search.setStatus("0");
			this.companyFunctionSetServiceImpl.insert(search);
		}
		model.addAttribute("status",search.getStatus());
		return "microSchool/carouselFigureConfig";
	}
	
	/**
	 * 
	 * Class Name: MicroSchoolController.java
	 * @Description: 查询mobile端 页头模块
	 * @author dongshuai
	 * @date 2017年2月17日 下午2:06:22
	 * @modifier
	 * @modify-date 2017年2月17日 下午2:06:22
	 * @version 1.0
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryWaphead",method = RequestMethod.POST)
	public JSONObject queryWaphead(HttpServletRequest request){
		JSONObject json = new JSONObject();
		json.put(JsonMsg.RESULT, JsonMsg.SUCCESS);
		Integer companyId = WebUtils.getCurrentCompanyId();
		
		List<SysPageHeadFoot> headlist = this.sysPageHeadFootServiceImpl.queryWapheadByCompanyId(companyId);
		
		json.put("headlist", headlist);
		return json;
	}
	
	/**
	 * 
	 * Class Name: MicroSchoolController.java
	 * @Description: 添加/修改模块
	 * @author dongshuai
	 * @date 2017年2月21日 下午6:04:50
	 * @modifier
	 * @modify-date 2017年2月21日 下午6:04:50
	 * @version 1.0
	 * @param param
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/saveWaphead",method=RequestMethod.POST)
	public JSONObject saveWaphead(SysPageHeadFoot param, HttpServletRequest request){
		JSONObject json = new JSONObject();
		json.put(JsonMsg.RESULT, JsonMsg.SUCCESS);
		
		Integer companyId = WebUtils.getCurrentCompanyId();
		Integer schoolId  = WebUtils.getCurrentSchoolId();
		Integer userId    = WebUtils.getCurrentUserId(request);
		
		param.setCompanyId(companyId);
		param.setSchoolId(schoolId);
	
		param.setPageType("waphead");
		
		SysPageHeadFoot search = new SysPageHeadFoot();
		search.setCompanyId(companyId);
		search.setUrl(param.getUrl());
		search = this.sysPageHeadFootServiceImpl.queryWapheadByCompanyIdAndUrlName(search);
		if(search == null){
			param.setCreateTime(new Date());
			param.setCreator(userId);
			this.sysPageHeadFootServiceImpl.insert(param);
		}else{
			param.setId(search.getId());
			param.setUpdateTime(new Date());
			param.setUpdator(userId);
			this.sysPageHeadFootServiceImpl.update(param);
		}
		
		return json;
	}
	
	/**
	 * 
	 * Class Name: MicroSchoolController.java
	 * @Description: 保存微网校页尾手机号
	 * @author dongshuai
	 * @date 2017年2月22日 上午10:48:41
	 * @modifier
	 * @modify-date 2017年2月22日 上午10:48:41
	 * @version 1.0
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/saveMobile",method=RequestMethod.POST)
	public JSONObject saveMobile(CompanyFootInfo cfi){
		JSONObject json = new JSONObject();
		json.put(JsonMsg.RESULT, JsonMsg.SUCCESS);
		
		Integer companyId = WebUtils.getCurrentCompanyId();
		CompanyFootInfo footInfo = this.companyFootInfoserviceImpl.findByCompanyId(companyId);
		if(footInfo == null){
			json.put(JsonMsg.RESULT, JsonMsg.ERROR);
			return json;
		}else{
			footInfo.setContactNumber(cfi.getContactNumber());
			this.companyFootInfoserviceImpl.update(footInfo);
		}
		return json;
	}
	
	/**
	 * 
	 * Class Name: MicroSchoolController.java
	 * @Description: 更新微网校页尾开关设置
	 * @author dongshuai
	 * @date 2017年2月22日 上午11:03:39
	 * @modifier
	 * @modify-date 2017年2月22日 上午11:03:39
	 * @version 1.0
	 * @param flag
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/saveFootFuncFlag", method = RequestMethod.POST)
	public JSONObject saveFootFuncFlag(String flag){
		JSONObject json = new JSONObject();
		json.put(JsonMsg.RESULT, JsonMsg.SUCCESS);
		
		Integer companyId = WebUtils.getCurrentCompanyId();
		
		CompanyFunctionSet search = new CompanyFunctionSet();
		search.setFunctionCode("MICROSCHOOL_FOOT_SERVICE");
		search.setCompanyId(companyId);
		search = this.companyFunctionSetServiceImpl.findCompanyUseCourse(search);
		
		if(search == null){
			search = new CompanyFunctionSet();
			search.setCompanyId(companyId);
			search.setFunctionCode("MICROSCHOOL_FOOT_SERVICE");
			search.setFunctionName("微网校页尾配置开关");
			search.setContent("0：关闭；1：开启");
			search.setStatus(flag);
			this.companyFunctionSetServiceImpl.insert(search);
		}else{
			search.setStatus(flag);
			this.companyFunctionSetServiceImpl.update(search);
		}
		
		return json;
	}
	
	/**
	 * 查询机构信息
	 */
	private void queryCompanyInfo(Model model){
		Integer companyId = WebUtils.getCurrentCompanyId();
		CompanyMemberService cms = this.companyMemberServiceServiceImpl.findByCompanyId(companyId);
		CompanyServiceStatic css = this.companyServiceStaticServiceImpl.findByCompanyId(companyId);
		Company company 		 = this.companyServiceImpl.findCompanyById(companyId);
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		model.addAttribute("company",company);
	}
	
	/**
	 * 查询机构服务开通情况
	 */
	private void queryServiceOpenFlags(Model model){
		Integer companyId = WebUtils.getCurrentCompanyId();
		String[] services = {"SERVICE_CLASS_PACKAGE",
							 "SERVICE_OPENCLASS",
							 "SERVICE_TEACHER",
							 "SERVICE_TIKU",
							 "SERVICE_QUESTION_ANSWER",
							 "SERVICE_MEMBER"};
		
		for (String service : services) {
			Map<String, Object> search = new HashMap<String, Object>();
			search.put("companyId", companyId);
			search.put("service", service);
			Integer flag = this.companyServiceImpl.queryServiceOpenFlag(search);
			model.addAttribute(service, flag!=null?flag:0);
		}
	}
	
	/**
	 * 微网校页尾开关状态
	 */
	private void queryFootOpenFlag(Model model){
		Integer companyId = WebUtils.getCurrentCompanyId();
		CompanyFunctionSet search = new CompanyFunctionSet();
		search.setFunctionCode("MICROSCHOOL_FOOT_SERVICE");
		search.setCompanyId(companyId);
		search = this.companyFunctionSetServiceImpl.findCompanyUseCourse(search);
		if(search == null){
			search = new CompanyFunctionSet();
			search.setCompanyId(companyId);
			search.setFunctionCode("MICROSCHOOL_FOOT_SERVICE");
			search.setFunctionName("微网校页尾配置开关");
			search.setContent("0：关闭；1：开启");
			search.setStatus("0");
			this.companyFunctionSetServiceImpl.insert(search);
			model.addAttribute("functionStatus", search.getStatus());
		}else{
			model.addAttribute("functionStatus", search.getStatus());
		}
	}
	
	/**
	 * 
	 * Class Name: MicroSchoolController.java
	 * @Description: 轮播图使用方式
	 * @author dongshuai
	 * @date 2017年2月28日 上午11:27:15
	 * @modifier
	 * @modify-date 2017年2月28日 上午11:27:15
	 * @version 1.0
	 * @param status
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/cycleUseStatus", method = RequestMethod.POST)
	public JSONObject cycleUseStatus(String status){
		JSONObject json = new JSONObject();
		json.put(JsonMsg.RESULT, JsonMsg.SUCCESS);
		
		Integer companyId = WebUtils.getCurrentCompanyId();
		
		CompanyFunctionSet search = new CompanyFunctionSet();
		search.setFunctionCode("MICROSCHOOL_CYCLEUSE");
		search.setCompanyId(companyId);
		search = this.companyFunctionSetServiceImpl.findCompanyUseCourse(search);
		
		if(search == null){
			search = new CompanyFunctionSet();
			search.setCompanyId(companyId);
			search.setFunctionCode("MICROSCHOOL_CYCLEUSE");
			search.setFunctionName("微网校轮播配置开关");
			search.setContent("0：使用PC端轮播图；1：使用微网校轮播图");
			search.setStatus(status);
			this.companyFunctionSetServiceImpl.insert(search);
		}else{
			search.setStatus(status);
			this.companyFunctionSetServiceImpl.update(search);
		}
		
		return json;
	}
	
	
	/**
	 * 查询公司页尾配置
	 */
	private void queryCompanyFootInfo(Model model){
		Integer companyId = WebUtils.getCurrentCompanyId();
		CompanyFootInfo footInfo = this.companyFootInfoserviceImpl.findByCompanyId(companyId);
		model.addAttribute("footInfo",footInfo);
	}
	
	/**
	 * 
	 * Class Name: MicroSchoolController.java
	 * @Description: 查询服务状态
	 * @author dongshuai
	 * @date 2017年2月28日 下午1:38:27
	 * @modifier
	 * @modify-date 2017年2月28日 下午1:38:27
	 * @version 1.0
	 * @param service
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryServiceStatus",method=RequestMethod.POST)
	public boolean queryServiceStatus(String service){
		Integer companyId = WebUtils.getCurrentCompanyId();
		
		SysConfigService search = new SysConfigService();
		search.setCompanyId(companyId);
		search.setGroupCode(service);
		search = this.sysConfigServiceServiceImpl.findExist(search);
		
		return (search.getDelFlag() == 1)?true:false;
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
