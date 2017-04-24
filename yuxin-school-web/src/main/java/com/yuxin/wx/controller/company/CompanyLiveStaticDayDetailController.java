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

import com.yuxin.wx.api.company.ICompanyLiveStaticDayDetailService;
import com.yuxin.wx.model.company.CompanyLiveStaticDayDetail;

/**
 * Controller of CompanyLiveStaticDayDetail
 * @author wang.zx
 * @date 2015-6-8
 */
@Controller
@RequestMapping("/companyLiveStaticDayDetail")
public class CompanyLiveStaticDayDetailController {
	
	@Autowired
	private ICompanyLiveStaticDayDetailService companyLiveStaticDayDetailServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CompanyLiveStaticDayDetail search){
		if (search == null) {
			search = new CompanyLiveStaticDayDetail();
			// search.setPageSize(20);
		}
		model.addAttribute("list", companyLiveStaticDayDetailServiceImpl.findCompanyLiveStaticDayDetailByPage(search));
		return "companyLiveStaticDayDetail/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(CompanyLiveStaticDayDetail CompanyLiveStaticDayDetail) {
		companyLiveStaticDayDetailServiceImpl.insert(CompanyLiveStaticDayDetail);
		return "redirect:/companyLiveStaticDayDetail";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(CompanyLiveStaticDayDetail CompanyLiveStaticDayDetail) {
		companyLiveStaticDayDetailServiceImpl.update(CompanyLiveStaticDayDetail);
		return "redirect:/companyLiveStaticDayDetail";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		companyLiveStaticDayDetailServiceImpl.deleteCompanyLiveStaticDayDetailById(id);
		return "redirect:/companyLiveStaticDayDetail";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public CompanyLiveStaticDayDetail getJson(Model model, @PathVariable Integer id){
		return companyLiveStaticDayDetailServiceImpl.findCompanyLiveStaticDayDetailById(id);
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
