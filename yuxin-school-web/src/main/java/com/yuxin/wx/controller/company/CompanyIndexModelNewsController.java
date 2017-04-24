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

import com.yuxin.wx.api.company.ICompanyIndexModelNewsService;
import com.yuxin.wx.model.company.CompanyIndexModelNews;

/**
 * Controller of CompanyIndexModelNews
 * @author chopin
 * @date 2015-5-18
 */
@Controller
@RequestMapping("/companyIndexModelNews")
public class CompanyIndexModelNewsController {
	
	@Autowired
	private ICompanyIndexModelNewsService companyIndexModelNewsServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CompanyIndexModelNews search){
		if (search == null) {
			search = new CompanyIndexModelNews();
			// search.setPageSize(20);
		}
		model.addAttribute("list", companyIndexModelNewsServiceImpl.findCompanyIndexModelNewsByPage(search));
		return "companyIndexModelNews/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(CompanyIndexModelNews CompanyIndexModelNews) {
		companyIndexModelNewsServiceImpl.insert(CompanyIndexModelNews);
		return "redirect:/companyIndexModelNews";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(CompanyIndexModelNews CompanyIndexModelNews) {
		companyIndexModelNewsServiceImpl.update(CompanyIndexModelNews);
		return "redirect:/companyIndexModelNews";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		companyIndexModelNewsServiceImpl.deleteCompanyIndexModelNewsById(id);
		return "redirect:/companyIndexModelNews";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public CompanyIndexModelNews getJson(Model model, @PathVariable Integer id){
		return companyIndexModelNewsServiceImpl.findCompanyIndexModelNewsById(id);
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
