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

import com.yuxin.wx.api.company.ICompanyLiveRecordZsService;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.company.CompanyLiveRecordZs;

/**
 * Controller of CompanyLiveRecordZs
 * @author wang.zx
 * @date 2015-12-7
 */
@Controller
@RequestMapping("/companyLiveRecordZs")
public class CompanyLiveRecordZsController {
	
	@Autowired
	private ICompanyLiveRecordZsService companyLiveRecordZsServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CompanyLiveRecordZs search){
		if (search == null) {
			search = new CompanyLiveRecordZs();
			// search.setPageSize(20);
		}
		model.addAttribute("list", companyLiveRecordZsServiceImpl.findCompanyLiveRecordZsByPage(search));
		return "companyLiveRecordZs/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(CompanyLiveRecordZs CompanyLiveRecordZs) {
		companyLiveRecordZsServiceImpl.insert(CompanyLiveRecordZs);
		return "redirect:/companyLiveRecordZs";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(CompanyLiveRecordZs CompanyLiveRecordZs) {
		companyLiveRecordZsServiceImpl.update(CompanyLiveRecordZs);
		return "redirect:/companyLiveRecordZs";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		companyLiveRecordZsServiceImpl.deleteCompanyLiveRecordZsById(id);
		return "redirect:/companyLiveRecordZs";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public CompanyLiveRecordZs getJson(Model model, @PathVariable Integer id){
		return companyLiveRecordZsServiceImpl.findCompanyLiveRecordZsById(id);
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
