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

import com.yuxin.wx.api.company.ICompanyLiveStaticRecordService;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.company.CompanyLiveStaticRecord;

/**
 * Controller of CompanyLiveStaticRecord
 * @author wang.zx
 * @date 2017-1-4
 */
@Controller
@RequestMapping("/companyLiveStaticRecord")
public class CompanyLiveStaticRecordController {
	
	@Autowired
	private ICompanyLiveStaticRecordService companyLiveStaticRecordServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CompanyLiveStaticRecord search){
		if (search == null) {
			search = new CompanyLiveStaticRecord();
			// search.setPageSize(20);
		}
		model.addAttribute("list", companyLiveStaticRecordServiceImpl.findCompanyLiveStaticRecordByPage(search));
		return "companyLiveStaticRecord/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(CompanyLiveStaticRecord CompanyLiveStaticRecord) {
		companyLiveStaticRecordServiceImpl.insert(CompanyLiveStaticRecord);
		return "redirect:/companyLiveStaticRecord";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(CompanyLiveStaticRecord CompanyLiveStaticRecord) {
		companyLiveStaticRecordServiceImpl.update(CompanyLiveStaticRecord);
		return "redirect:/companyLiveStaticRecord";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		companyLiveStaticRecordServiceImpl.deleteCompanyLiveStaticRecordById(id);
		return "redirect:/companyLiveStaticRecord";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public CompanyLiveStaticRecord getJson(Model model, @PathVariable Integer id){
		return companyLiveStaticRecordServiceImpl.findCompanyLiveStaticRecordById(id);
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
