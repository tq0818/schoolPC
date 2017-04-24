package com.yuxin.wx.controller.company;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import com.yuxin.wx.api.company.ICompanyConfigProxyOrgService;
import com.yuxin.wx.api.company.ICompanyInvitConfigOrgService;
import com.yuxin.wx.api.company.ICompanyMemberServiceService;
import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.api.system.ISysConfigServiceService;
import com.yuxin.wx.model.company.CompanyConfigProxyOrg;
import com.yuxin.wx.model.company.CompanyInvitConfigOrg;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.model.system.SysConfigService;
import com.yuxin.wx.utils.WebUtils;

/**
 * Controller of CompanyInvitConfigOrg
 * @author chopin
 * @date 2017-3-16
 */
@Controller
@RequestMapping("/companyInvitConfigOrg")
public class CompanyInvitConfigOrgController {
	
	@Autowired
	private ICompanyInvitConfigOrgService companyInvitConfigOrgServiceImpl;
	

	@Autowired
	private ISysConfigServiceService sysConfigServiceImpl;
	
	@Autowired
	private ICompanyMemberServiceService companyMemberServiceServiceImpl;
	
	@Autowired
	private ICompanyServiceStaticService companyServiceStaticServiceImpl;
	
	@Autowired
	private ICompanyConfigProxyOrgService companyConfigProxyOrgServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CompanyInvitConfigOrg search){
		if (search == null) {
			search = new CompanyInvitConfigOrg();
			// search.setPageSize(20);
		}
		model.addAttribute("list", companyInvitConfigOrgServiceImpl.findCompanyInvitConfigOrgByPage(search));
		return "companyInvitConfigOrg/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(CompanyInvitConfigOrg CompanyInvitConfigOrg) {
		companyInvitConfigOrgServiceImpl.insert(CompanyInvitConfigOrg);
		return "redirect:/companyInvitConfigOrg";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(CompanyInvitConfigOrg CompanyInvitConfigOrg) {
		companyInvitConfigOrgServiceImpl.update(CompanyInvitConfigOrg);
		return "redirect:/companyInvitConfigOrg";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		companyInvitConfigOrgServiceImpl.deleteCompanyInvitConfigOrgById(id);
		return "redirect:/companyInvitConfigOrg";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public CompanyInvitConfigOrg getJson(Model model, @PathVariable Integer id){
		return companyInvitConfigOrgServiceImpl.findCompanyInvitConfigOrgById(id);
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
	
	@RequestMapping(value="/toConfigOrgInvitePage")
	public String toConfigTeacherInvitePage(HttpServletRequest request,Model model){
		CompanyInvitConfigOrg search = new CompanyInvitConfigOrg();
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		List<CompanyInvitConfigOrg> list = companyInvitConfigOrgServiceImpl.findCompanyInvitConfigOrgByPage(search);
		if(list !=null && list.size()>0){
			model.addAttribute("orgConfig", list.get(0));
			return "company/companyOrgInviteUpdate";
		}
		return "company/companyOrgInviteAdd";
	}
	
	
	@ResponseBody
	@RequestMapping(value="/ajaxCheck",method=RequestMethod.POST)
	public Object ajaxCheck(HttpServletRequest request){
		CompanyInvitConfigOrg search = new CompanyInvitConfigOrg();
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		List<CompanyInvitConfigOrg> list = companyInvitConfigOrgServiceImpl.findCompanyInvitConfigOrgByPage(search);
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/updateBtn",method=RequestMethod.POST)
	public String updateBtn(HttpServletRequest request,String flag){
		CompanyInvitConfigOrg search = new CompanyInvitConfigOrg();
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		List<CompanyInvitConfigOrg> list = companyInvitConfigOrgServiceImpl.findCompanyInvitConfigOrgByPage(search);
		if(list != null && list.size()>0){
			CompanyInvitConfigOrg configOrg = list.get(0);
			configOrg.setOpenFlag(Integer.parseInt(flag));
			companyInvitConfigOrgServiceImpl.update(configOrg);
		}
		return "sucesss";
	}
	
	@ResponseBody
	@RequestMapping(value="/saveOrUpdateCompanyInvitConfigOrg",method=RequestMethod.POST)
	public String saveOrUpdateCompanyInvitConfigTeacher(CompanyInvitConfigOrg org,Model model){
		CompanyInvitConfigOrg search = new CompanyInvitConfigOrg();
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		List<CompanyInvitConfigOrg> list = companyInvitConfigOrgServiceImpl.findCompanyInvitConfigOrgByPage(search);
		if(list != null && list.size()>0){
			org.setId(list.get(0).getId());
			companyInvitConfigOrgServiceImpl.update(org);
			return "success";
		}
		org.setCompanyId(WebUtils.getCurrentCompanyId());
		companyInvitConfigOrgServiceImpl.insert(org);
		List<CompanyConfigProxyOrg> list2 = companyConfigProxyOrgServiceImpl.queryCompanyUnsetRateOrg(WebUtils.getCurrentCompanyId());
		if(list2!= null && list2.size()>0){
			for (CompanyConfigProxyOrg companyConfigProxyOrg : list2) {
				companyConfigProxyOrg.setCommissionRate(org.getInvitCastAward());
				companyConfigProxyOrgServiceImpl.update(companyConfigProxyOrg);
			}
		}
		return "success";
	}
	
	
	@ResponseBody
	@RequestMapping(value="/checkOpenProxy",method=RequestMethod.POST)
	public String checkOpenProxy(HttpServletRequest request){
		SysConfigService service = new SysConfigService();
		service.setCompanyId(WebUtils.getCurrentCompanyId());
		service.setGroupCode("SERVICE_STUDYCARD");
		service.setDelFlag(1);
		List<SysConfigService> list = sysConfigServiceImpl.findSysConfigServiceByPage(service);
		if(list != null && list.size()>0)
			return "success";
		else {
			CompanyInvitConfigOrg org = new CompanyInvitConfigOrg();
			org.setCompanyId(WebUtils.getCurrentCompanyId());
			org.setOpenFlag(1);
			List<CompanyInvitConfigOrg> list2 = companyInvitConfigOrgServiceImpl.findCompanyInvitConfigOrgByPage(org);
			if(list2!= null && list2.size()>0)
				return "success";
		}
		return "fail";
	}
	
	@ResponseBody
	@RequestMapping(value="/checkOpenInviteCode",method=RequestMethod.POST)
	public String checkOpenInviteCode(HttpServletRequest request){
		CompanyInvitConfigOrg org = new CompanyInvitConfigOrg();
		org.setCompanyId(WebUtils.getCurrentCompanyId());
		org.setOpenFlag(1);
		List<CompanyInvitConfigOrg> list2 = companyInvitConfigOrgServiceImpl.findCompanyInvitConfigOrgByPage(org);
		if(list2!= null && list2.size()>0)
			return "success";
		else 
			return "fail";
	}
	
	
	@ResponseBody
	@RequestMapping(value="/checkCustomSetting",method=RequestMethod.POST)
	public String checkCustomSetting(HttpServletRequest request){
		CompanyInvitConfigOrg org = new CompanyInvitConfigOrg();
		org.setCompanyId(WebUtils.getCurrentCompanyId());
		org.setOpenFlag(1);
		org.setRewardsCustomSetting(1);
		List<CompanyInvitConfigOrg> list2 = companyInvitConfigOrgServiceImpl.findCompanyInvitConfigOrgByPage(org);
		if(list2!= null && list2.size()>0)
			return "success";
		else 
			return "fail";
	}
}
