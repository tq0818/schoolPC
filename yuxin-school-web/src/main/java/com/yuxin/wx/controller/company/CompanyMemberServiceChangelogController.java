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

import com.yuxin.wx.api.company.ICompanyMemberServiceChangelogService;
import com.yuxin.wx.model.company.CompanyMemberServiceChangelog;

/**
 * Controller of CompanyMemberServiceChangelog
 * @author wang.zx
 * @date 2015-6-25
 */
@Controller
@RequestMapping("/companyMemberServiceChangelog")
public class CompanyMemberServiceChangelogController {
	
	@Autowired
	private ICompanyMemberServiceChangelogService companyMemberServiceChangelogServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CompanyMemberServiceChangelog search){
		if (search == null) {
			search = new CompanyMemberServiceChangelog();
			// search.setPageSize(20);
		}
		model.addAttribute("list", companyMemberServiceChangelogServiceImpl.findCompanyMemberServiceChangelogByPage(search));
		return "companyMemberServiceChangelog/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(CompanyMemberServiceChangelog CompanyMemberServiceChangelog) {
		companyMemberServiceChangelogServiceImpl.insert(CompanyMemberServiceChangelog);
		return "redirect:/companyMemberServiceChangelog";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(CompanyMemberServiceChangelog CompanyMemberServiceChangelog) {
		companyMemberServiceChangelogServiceImpl.update(CompanyMemberServiceChangelog);
		return "redirect:/companyMemberServiceChangelog";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		companyMemberServiceChangelogServiceImpl.deleteCompanyMemberServiceChangelogById(id);
		return "redirect:/companyMemberServiceChangelog";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public CompanyMemberServiceChangelog getJson(Model model, @PathVariable Integer id){
		return companyMemberServiceChangelogServiceImpl.findCompanyMemberServiceChangelogById(id);
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
