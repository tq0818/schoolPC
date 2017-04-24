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

import com.yuxin.wx.api.company.ICompanyMessageHistoryService;
import com.yuxin.wx.model.company.CompanyMessageHistory;

/**
 * Controller of CompanyMessageHistory
 * @author chopin
 * @date 2015-4-23
 */
@Controller
@RequestMapping("/companyMessageHistory")
public class CompanyMessageHistoryController {
	
	@Autowired
	private ICompanyMessageHistoryService companyMessageHistoryServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CompanyMessageHistory search){
		if (search == null) {
			search = new CompanyMessageHistory();
			// search.setPageSize(20);
		}
		model.addAttribute("list", companyMessageHistoryServiceImpl.findCompanyMessageHistoryByPage(search));
		return "companyMessageHistory/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(CompanyMessageHistory CompanyMessageHistory) {
		companyMessageHistoryServiceImpl.insert(CompanyMessageHistory);
		return "redirect:/companyMessageHistory";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(CompanyMessageHistory CompanyMessageHistory) {
		companyMessageHistoryServiceImpl.update(CompanyMessageHistory);
		return "redirect:/companyMessageHistory";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		companyMessageHistoryServiceImpl.deleteCompanyMessageHistoryById(id);
		return "redirect:/companyMessageHistory";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public CompanyMessageHistory getJson(Model model, @PathVariable Integer id){
		return companyMessageHistoryServiceImpl.findCompanyMessageHistoryById(id);
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
