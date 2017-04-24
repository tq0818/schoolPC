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

import com.yuxin.wx.api.company.ICompanyLiveIntercutZsService;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.company.CompanyLiveIntercutZs;

/**
 * Controller of CompanyLiveIntercutZs
 * @author wang.zx
 * @date 2015-12-11
 */
@Controller
@RequestMapping("/companyLiveIntercutZs")
public class CompanyLiveIntercutZsController {
	
	@Autowired
	private ICompanyLiveIntercutZsService companyLiveIntercutZsServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CompanyLiveIntercutZs search){
		if (search == null) {
			search = new CompanyLiveIntercutZs();
			// search.setPageSize(20);
		}
		model.addAttribute("list", companyLiveIntercutZsServiceImpl.findCompanyLiveIntercutZsByPage(search));
		return "companyLiveIntercutZs/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(CompanyLiveIntercutZs CompanyLiveIntercutZs) {
		companyLiveIntercutZsServiceImpl.insert(CompanyLiveIntercutZs);
		return "redirect:/companyLiveIntercutZs";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(CompanyLiveIntercutZs CompanyLiveIntercutZs) {
		companyLiveIntercutZsServiceImpl.update(CompanyLiveIntercutZs);
		return "redirect:/companyLiveIntercutZs";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		companyLiveIntercutZsServiceImpl.deleteCompanyLiveIntercutZsById(id);
		return "redirect:/companyLiveIntercutZs";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public CompanyLiveIntercutZs getJson(Model model, @PathVariable Integer id){
		return companyLiveIntercutZsServiceImpl.findCompanyLiveIntercutZsById(id);
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
