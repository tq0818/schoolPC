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

import com.yuxin.wx.api.company.ICompanyLiveWarmupZsService;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.company.CompanyLiveWarmupZs;

/**
 * Controller of CompanyLiveWarmupZs
 * @author wang.zx
 * @date 2015-12-11
 */
@Controller
@RequestMapping("/companyLiveWarmupZs")
public class CompanyLiveWarmupZsController {
	
	@Autowired
	private ICompanyLiveWarmupZsService companyLiveWarmupZsServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CompanyLiveWarmupZs search){
		if (search == null) {
			search = new CompanyLiveWarmupZs();
			// search.setPageSize(20);
		}
		model.addAttribute("list", companyLiveWarmupZsServiceImpl.findCompanyLiveWarmupZsByPage(search));
		return "companyLiveWarmupZs/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(CompanyLiveWarmupZs CompanyLiveWarmupZs) {
		companyLiveWarmupZsServiceImpl.insert(CompanyLiveWarmupZs);
		return "redirect:/companyLiveWarmupZs";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(CompanyLiveWarmupZs CompanyLiveWarmupZs) {
		companyLiveWarmupZsServiceImpl.update(CompanyLiveWarmupZs);
		return "redirect:/companyLiveWarmupZs";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		companyLiveWarmupZsServiceImpl.deleteCompanyLiveWarmupZsById(id);
		return "redirect:/companyLiveWarmupZs";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public CompanyLiveWarmupZs getJson(Model model, @PathVariable Integer id){
		return companyLiveWarmupZsServiceImpl.findCompanyLiveWarmupZsById(id);
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
