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

import com.yuxin.wx.api.company.ICompanyIndexModelClasstypeService;
import com.yuxin.wx.model.company.CompanyIndexModelClasstype;

/**
 * Controller of CompanyIndexModelClasstype
 * @author chopin
 * @date 2015-5-18
 */
@Controller
@RequestMapping("/companyIndexModelClasstype")
public class CompanyIndexModelClasstypeController {
	
	@Autowired
	private ICompanyIndexModelClasstypeService companyIndexModelClasstypeServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CompanyIndexModelClasstype search){
		if (search == null) {
			search = new CompanyIndexModelClasstype();
			// search.setPageSize(20);
		}
		model.addAttribute("list", companyIndexModelClasstypeServiceImpl.findCompanyIndexModelClasstypeByPage(search));
		return "companyIndexModelClasstype/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(CompanyIndexModelClasstype CompanyIndexModelClasstype) {
		companyIndexModelClasstypeServiceImpl.insert(CompanyIndexModelClasstype);
		return "redirect:/companyIndexModelClasstype";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(CompanyIndexModelClasstype CompanyIndexModelClasstype) {
		companyIndexModelClasstypeServiceImpl.update(CompanyIndexModelClasstype);
		return "redirect:/companyIndexModelClasstype";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		companyIndexModelClasstypeServiceImpl.deleteCompanyIndexModelClasstypeById(id);
		return "redirect:/companyIndexModelClasstype";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public CompanyIndexModelClasstype getJson(Model model, @PathVariable Integer id){
		return companyIndexModelClasstypeServiceImpl.findCompanyIndexModelClasstypeById(id);
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
