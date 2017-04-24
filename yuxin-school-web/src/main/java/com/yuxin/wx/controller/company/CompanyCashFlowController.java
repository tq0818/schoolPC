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

import com.yuxin.wx.api.company.ICompanyCashFlowService;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.company.CompanyCashFlow;

/**
 * Controller of CompanyCashFlow
 * @author chopin
 * @date 2016-5-17
 */
@Controller
@RequestMapping("/companyCashFlow")
public class CompanyCashFlowController {
	
	@Autowired
	private ICompanyCashFlowService companyCashFlowServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CompanyCashFlow search){
		if (search == null) {
			search = new CompanyCashFlow();
			// search.setPageSize(20);
		}
		model.addAttribute("list", companyCashFlowServiceImpl.findCompanyCashFlowByPage(search));
		return "companyCashFlow/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(CompanyCashFlow CompanyCashFlow) {
		companyCashFlowServiceImpl.insert(CompanyCashFlow);
		return "redirect:/companyCashFlow";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(CompanyCashFlow CompanyCashFlow) {
		companyCashFlowServiceImpl.update(CompanyCashFlow);
		return "redirect:/companyCashFlow";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		companyCashFlowServiceImpl.deleteCompanyCashFlowById(id);
		return "redirect:/companyCashFlow";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public CompanyCashFlow getJson(Model model, @PathVariable Integer id){
		return companyCashFlowServiceImpl.findCompanyCashFlowById(id);
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
