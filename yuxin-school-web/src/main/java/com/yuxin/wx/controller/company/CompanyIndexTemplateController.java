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

import com.yuxin.wx.api.company.ICompanyIndexTemplateService;
import com.yuxin.wx.model.company.CompanyIndexTemplate;

/**
 * Controller of CompanyIndexTemplate
 * @author chopin
 * @date 2015-5-18
 */
@Controller
@RequestMapping("/companyIndexTemplate")
public class CompanyIndexTemplateController {
	
	@Autowired
	private ICompanyIndexTemplateService companyIndexTemplateServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CompanyIndexTemplate search){
		if (search == null) {
			search = new CompanyIndexTemplate();
			// search.setPageSize(20);
		}
		model.addAttribute("list", companyIndexTemplateServiceImpl.findCompanyIndexTemplateByPage(search));
		return "companyIndexTemplate/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(CompanyIndexTemplate CompanyIndexTemplate) {
		companyIndexTemplateServiceImpl.insert(CompanyIndexTemplate);
		return "redirect:/companyIndexTemplate";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(CompanyIndexTemplate CompanyIndexTemplate) {
		companyIndexTemplateServiceImpl.update(CompanyIndexTemplate);
		return "redirect:/companyIndexTemplate";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		companyIndexTemplateServiceImpl.deleteCompanyIndexTemplateById(id);
		return "redirect:/companyIndexTemplate";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public CompanyIndexTemplate getJson(Model model, @PathVariable Integer id){
		return companyIndexTemplateServiceImpl.findCompanyIndexTemplateById(id);
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
