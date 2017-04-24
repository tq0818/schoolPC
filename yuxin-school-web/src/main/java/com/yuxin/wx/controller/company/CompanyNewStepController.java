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

import com.yuxin.wx.api.company.ICompanyNewStepService;
import com.yuxin.wx.model.company.CompanyNewStep;

/**
 * Controller of CompanyNewStep
 * @author chopin
 * @date 2015-5-20
 */
@Controller
@RequestMapping("/companyNewStep")
public class CompanyNewStepController {
	
	@Autowired
	private ICompanyNewStepService companyNewStepServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CompanyNewStep search){
		if (search == null) {
			search = new CompanyNewStep();
			// search.setPageSize(20);
		}
		model.addAttribute("list", companyNewStepServiceImpl.findCompanyNewStepByPage(search));
		return "companyNewStep/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(CompanyNewStep CompanyNewStep) {
		companyNewStepServiceImpl.insert(CompanyNewStep);
		return "redirect:/companyNewStep";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(CompanyNewStep CompanyNewStep) {
		companyNewStepServiceImpl.update(CompanyNewStep);
		return "redirect:/companyNewStep";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		companyNewStepServiceImpl.deleteCompanyNewStepById(id);
		return "redirect:/companyNewStep";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public CompanyNewStep getJson(Model model, @PathVariable Integer id){
		return companyNewStepServiceImpl.findCompanyNewStepById(id);
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
