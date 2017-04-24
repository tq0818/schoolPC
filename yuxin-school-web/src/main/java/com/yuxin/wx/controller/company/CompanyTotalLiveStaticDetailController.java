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

import com.yuxin.wx.api.company.ICompanyTotalLiveStaticDetailService;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.company.CompanyTotalLiveStaticDetail;

/**
 * Controller of CompanyTotalLiveStaticDetail
 * @author wang.zx
 * @date 2016-2-29
 */
@Controller
@RequestMapping("/companyTotalLiveStaticDetail")
public class CompanyTotalLiveStaticDetailController {
	
	@Autowired
	private ICompanyTotalLiveStaticDetailService companyTotalLiveStaticDetailServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CompanyTotalLiveStaticDetail search){
		if (search == null) {
			search = new CompanyTotalLiveStaticDetail();
			// search.setPageSize(20);
		}
		model.addAttribute("list", companyTotalLiveStaticDetailServiceImpl.findCompanyTotalLiveStaticDetailByPage(search));
		return "companyTotalLiveStaticDetail/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(CompanyTotalLiveStaticDetail CompanyTotalLiveStaticDetail) {
		companyTotalLiveStaticDetailServiceImpl.insert(CompanyTotalLiveStaticDetail);
		return "redirect:/companyTotalLiveStaticDetail";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(CompanyTotalLiveStaticDetail CompanyTotalLiveStaticDetail) {
		companyTotalLiveStaticDetailServiceImpl.update(CompanyTotalLiveStaticDetail);
		return "redirect:/companyTotalLiveStaticDetail";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		companyTotalLiveStaticDetailServiceImpl.deleteCompanyTotalLiveStaticDetailById(id);
		return "redirect:/companyTotalLiveStaticDetail";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public CompanyTotalLiveStaticDetail getJson(Model model, @PathVariable Integer id){
		return companyTotalLiveStaticDetailServiceImpl.findCompanyTotalLiveStaticDetailById(id);
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
