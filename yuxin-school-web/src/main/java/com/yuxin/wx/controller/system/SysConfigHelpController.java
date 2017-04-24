package com.yuxin.wx.controller.system;

import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.system.ISysConfigHelpService;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.system.SysConfigHelp;
import com.yuxin.wx.utils.WebUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/help")
public class SysConfigHelpController {
	private Log log=LogFactory.getLog("log");
	@Autowired
	private ISysConfigHelpService sysconfigHelpImpl;
	@Autowired
	private ICompanyService companyServiceImpl;
	
	@ResponseBody
	@RequestMapping(value="/doc/{path}",method=RequestMethod.POST)
	public SysConfigHelp docHelp(Model model,@PathVariable String path){
		SysConfigHelp help=sysconfigHelpImpl.findByLocation(path);
		return help;
	}
	
	@ResponseBody
	@RequestMapping(value="/video/{path}",method=RequestMethod.POST)
	public SysConfigHelp videoHelp(Model model,@PathVariable String path){
		SysConfigHelp help=sysconfigHelpImpl.findByLocation(path);
		return help;
	}
	
	@ResponseBody
	@RequestMapping(value="/find",method=RequestMethod.POST)
	public SysConfigHelp find(HttpServletRequest request){
		String url=request.getParameter("url");
		SysConfigHelp help=sysconfigHelpImpl.findByLocation(url);
	
		if(help!=null){
			Company company=companyServiceImpl.findCompanyById(WebUtils.getCurrentCompanyId());
			if(null!=company && null!=company.getBuyFlag()){
				help.setBuyFlag(company.getBuyFlag());
			}
			return help;
		}else{
			help=new SysConfigHelp();
			Company company=companyServiceImpl.findCompanyById(WebUtils.getCurrentCompanyId());
			if(null!=company && null!=company.getBuyFlag()){
				help.setBuyFlag(company.getBuyFlag());
			}
			return help;
		}
	}
}
