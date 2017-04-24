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

import com.yuxin.wx.api.company.ICompanyMemberLevelDetailService;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.company.CompanyMemberLevelDetail;

/**
 * Controller of CompanyMemberLevelDetail
 * @author chopin
 * @date 2016-5-17
 */
@Controller
@RequestMapping("/companyMemberLevelDetail")
public class CompanyMemberLevelDetailController {
	
	@Autowired
	private ICompanyMemberLevelDetailService companyMemberLevelDetailServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CompanyMemberLevelDetail search){
		if (search == null) {
			search = new CompanyMemberLevelDetail();
			// search.setPageSize(20);
		}
		model.addAttribute("list", companyMemberLevelDetailServiceImpl.findCompanyMemberLevelDetailByPage(search));
		return "companyMemberLevelDetail/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(CompanyMemberLevelDetail CompanyMemberLevelDetail) {
		companyMemberLevelDetailServiceImpl.insert(CompanyMemberLevelDetail);
		return "redirect:/companyMemberLevelDetail";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(CompanyMemberLevelDetail CompanyMemberLevelDetail) {
		companyMemberLevelDetailServiceImpl.update(CompanyMemberLevelDetail);
		return "redirect:/companyMemberLevelDetail";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		companyMemberLevelDetailServiceImpl.deleteCompanyMemberLevelDetailById(id);
		return "redirect:/companyMemberLevelDetail";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public CompanyMemberLevelDetail getJson(Model model, @PathVariable Integer id){
		return companyMemberLevelDetailServiceImpl.findCompanyMemberLevelDetailById(id);
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
