package com.yuxin.wx.controller.system;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuxin.wx.api.company.ICompanyMarketSetService;
import com.yuxin.wx.api.company.ICompanyMemberServiceService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.api.system.ISysConfigPageRedirectService;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.CompanyMarketSet;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.model.system.SysConfigPageRedirect;
import com.yuxin.wx.utils.WebUtils;
/**
 * 配置右边栏模板显示showCompanyMarket
 * @author licong
 *
 */
@Controller
@RequestMapping("/sysConfigPage")
public class SysConfigPageRedirectControler {
	@Autowired
	private ISysConfigPageRedirectService sysConfigPageRedirectServiceImpl;
	@Autowired
	private ICompanyMarketSetService companyMarketSetServiceImpl;
	@Autowired
	private ICompanyService companyService;
	@Autowired
	private ICompanyMemberServiceService companyMemberServiceServiceImpl;
	@Autowired
	private ICompanyServiceStaticService companyServiceStaticServiceImpl;
	/**
	 * 打开配置页面
	 */
	@RequestMapping("/toConfigPage")
	public String toConfigPage(Model model){
		
		CompanyMarketSet comMark=companyMarketSetServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
		model.addAttribute("comMark", comMark);
		Integer companyId=WebUtils.getCurrentCompanyId();
		Company company = companyService.findCompanyById(companyId);

		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);
		model.addAttribute("company",company);
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		
		SysConfigPageRedirect spr = new SysConfigPageRedirect();
		spr.setCompanyId(WebUtils.getCurrentCompanyId());
		spr.setSchoolId(WebUtils.getCurrentSchoolId());
		spr.setBussinessType("REDIRECT_FRONT_MARKETING");
		SysConfigPageRedirect findSpr = null;
		List<SysConfigPageRedirect> list = sysConfigPageRedirectServiceImpl.findBySearch(spr);
		if(list != null && list.size()>0)
			findSpr = list.get(0);
		model.addAttribute("spr", findSpr);
		return "system/marketing_pt";
	}
	/**
	 * 选择模板类型
	 */
	@ResponseBody
	@RequestMapping(value="/configPage",method=RequestMethod.POST)
	public String configPage(SysConfigPageRedirect pageSprc){
		SysConfigPageRedirect spr = new SysConfigPageRedirect();
		spr.setCompanyId(WebUtils.getCurrentCompanyId());
		spr.setSchoolId(WebUtils.getCurrentSchoolId());
		spr.setBussinessType("REDIRECT_FRONT_MARKETING");
		
		SysConfigPageRedirect findSpr = null;
		List<SysConfigPageRedirect> list = sysConfigPageRedirectServiceImpl.findBySearch(spr);
		if(list != null && list.size()>0)
			findSpr = list.get(0);
		
		if(findSpr!=null){
			findSpr.setStatus(1);
			findSpr.setLink(pageSprc.getLink());
			sysConfigPageRedirectServiceImpl.update(findSpr);
		}else{
			//设置默认属性并保存一条
			spr.setLink(pageSprc.getBussinessType());
			spr.setStatus(1);
			spr.setTemplateId(1);
			spr.setSysType(0);
			sysConfigPageRedirectServiceImpl.insert(spr);
		}
		return "ok";
	}
}