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

import com.yuxin.wx.api.company.ICompanyIndexModelItemService;
import com.yuxin.wx.model.company.CompanyIndexModelItem;

/**
 * Controller of CompanyIndexModelItem
 * @author chopin
 * @date 2015-5-18
 */
@Controller
@RequestMapping("/companyIndexModelItem")
public class CompanyIndexModelItemController {
	
	@Autowired
	private ICompanyIndexModelItemService companyIndexModelItemServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CompanyIndexModelItem search){
		if (search == null) {
			search = new CompanyIndexModelItem();
			// search.setPageSize(20);
		}
		model.addAttribute("list", companyIndexModelItemServiceImpl.findCompanyIndexModelItemByPage(search));
		return "companyIndexModelItem/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(CompanyIndexModelItem CompanyIndexModelItem) {
		companyIndexModelItemServiceImpl.insert(CompanyIndexModelItem);
		return "redirect:/companyIndexModelItem";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(CompanyIndexModelItem CompanyIndexModelItem) {
		companyIndexModelItemServiceImpl.update(CompanyIndexModelItem);
		return "redirect:/companyIndexModelItem";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		companyIndexModelItemServiceImpl.deleteCompanyIndexModelItemById(id);
		return "redirect:/companyIndexModelItem";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public CompanyIndexModelItem getJson(Model model, @PathVariable Integer id){
		return companyIndexModelItemServiceImpl.findCompanyIndexModelItemById(id);
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
