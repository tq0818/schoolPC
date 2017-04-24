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

import com.yuxin.wx.api.company.ICompanyPicsService;
import com.yuxin.wx.model.company.CompanyPics;

/**
 * Controller of CompanyPics
 * @author chopin
 * @date 2015-5-14
 */
@Controller
@RequestMapping("/companyPics")
public class CompanyPicsController {
	
	@Autowired
	private ICompanyPicsService companyPicsServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CompanyPics search){
		if (search == null) {
			search = new CompanyPics();
			// search.setPageSize(20);
		}
		model.addAttribute("list", companyPicsServiceImpl.findCompanyPicsByPage(search));
		return "companyPics/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(CompanyPics CompanyPics) {
		companyPicsServiceImpl.insert(CompanyPics);
		return "redirect:/companyPics";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(CompanyPics CompanyPics) {
		companyPicsServiceImpl.update(CompanyPics);
		return "redirect:/companyPics";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		companyPicsServiceImpl.deleteCompanyPicsById(id);
		return "redirect:/companyPics";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public CompanyPics getJson(Model model, @PathVariable Integer id){
		return companyPicsServiceImpl.findCompanyPicsById(id);
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
