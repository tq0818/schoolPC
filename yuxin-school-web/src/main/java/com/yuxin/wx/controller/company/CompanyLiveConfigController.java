package com.yuxin.wx.controller.company;

import java.text.SimpleDateFormat;
import java.util.Date;

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

import com.yuxin.wx.api.company.ICompanyLiveConfigService;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.company.CompanyLiveConfig;

/**
 * Controller of CompanyLiveConfig
 * @author wang.zx
 * @date 2016-2-29
 */
@Controller
@RequestMapping("/companyLiveConfig")
public class CompanyLiveConfigController {
	
	@Autowired
	private ICompanyLiveConfigService companyLiveConfigServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CompanyLiveConfig search){
		if (search == null) {
			search = new CompanyLiveConfig();
			// search.setPageSize(20);
		}
		model.addAttribute("list", companyLiveConfigServiceImpl.findCompanyLiveConfigByPage(search));
		return "companyLiveConfig/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(CompanyLiveConfig CompanyLiveConfig) {
		companyLiveConfigServiceImpl.insert(CompanyLiveConfig);
		return "redirect:/companyLiveConfig";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(CompanyLiveConfig CompanyLiveConfig) {
		companyLiveConfigServiceImpl.update(CompanyLiveConfig);
		return "redirect:/companyLiveConfig";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		companyLiveConfigServiceImpl.deleteCompanyLiveConfigById(id);
		return "redirect:/companyLiveConfig";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public CompanyLiveConfig getJson(Model model, @PathVariable Integer id){
		return companyLiveConfigServiceImpl.findCompanyLiveConfigById(id);
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
