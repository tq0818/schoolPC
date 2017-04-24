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

import com.yuxin.wx.api.company.ICompanyEmailHistoryService;
import com.yuxin.wx.model.company.CompanyEmailHistory;

/**
 * Controller of CompanyEmailHistory
 * @author chopin
 * @date 2015-4-23
 */
@Controller
@RequestMapping("/companyEmailHistory")
public class CompanyEmailHistoryController {
	
	@Autowired
	private ICompanyEmailHistoryService companyEmailHistoryServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CompanyEmailHistory search){
		if (search == null) {
			search = new CompanyEmailHistory();
			// search.setPageSize(20);
		}
		model.addAttribute("list", companyEmailHistoryServiceImpl.findCompanyEmailHistoryByPage(search));
		return "companyEmailHistory/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(CompanyEmailHistory CompanyEmailHistory) {
		companyEmailHistoryServiceImpl.insert(CompanyEmailHistory);
		return "redirect:/companyEmailHistory";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(CompanyEmailHistory CompanyEmailHistory) {
		companyEmailHistoryServiceImpl.update(CompanyEmailHistory);
		return "redirect:/companyEmailHistory";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		companyEmailHistoryServiceImpl.deleteCompanyEmailHistoryById(id);
		return "redirect:/companyEmailHistory";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public CompanyEmailHistory getJson(Model model, @PathVariable Integer id){
		return companyEmailHistoryServiceImpl.findCompanyEmailHistoryById(id);
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
