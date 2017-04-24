package com.yuxin.wx.controller.company;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

import com.yuxin.wx.api.company.ICompanyIndexModelPrivatepageService;
import com.yuxin.wx.model.company.CompanyIndexModelPrivatepage;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.company.CompanyIndexModelPrivatepageVo;

/**
 * Controller of CompanyIndexModelPrivatepage
 * @author chopin
 * @date 2015-5-18
 */
@Controller
@RequestMapping("/companyIndexModelPrivatepage")
public class CompanyIndexModelPrivatepageController {
	
	@Autowired
	private ICompanyIndexModelPrivatepageService companyIndexModelPrivatepageServiceImpl;
	private String type;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CompanyIndexModelPrivatepage search){
		if (search == null) {
			search = new CompanyIndexModelPrivatepage();
			// search.setPageSize(20);
		}
		model.addAttribute("list", companyIndexModelPrivatepageServiceImpl.findCompanyIndexModelPrivatepageByPage(search));
		return "companyIndexModelPrivatepage/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(CompanyIndexModelPrivatepage CompanyIndexModelPrivatepage) {
		companyIndexModelPrivatepageServiceImpl.insert(CompanyIndexModelPrivatepage);
		return "redirect:/companyIndexModelPrivatepage";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(CompanyIndexModelPrivatepage CompanyIndexModelPrivatepage) {
		companyIndexModelPrivatepageServiceImpl.update(CompanyIndexModelPrivatepage);
		return "redirect:/companyIndexModelPrivatepage";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		companyIndexModelPrivatepageServiceImpl.deleteCompanyIndexModelPrivatepageById(id);
		return "redirect:/companyIndexModelPrivatepage";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public CompanyIndexModelPrivatepage getJson(Model model, @PathVariable Integer id){
		return companyIndexModelPrivatepageServiceImpl.findCompanyIndexModelPrivatepageById(id);
	}
	
	@ResponseBody
	@RequestMapping(value="/findByTemplate/{type}/{id}/{sid}", method = RequestMethod.POST)
	public List<CompanyIndexModelPrivatepageVo> findByTemplate(Model model, @PathVariable String type, @PathVariable Integer id, @PathVariable Integer sid){
		CompanyIndexModelPrivatepage search=new CompanyIndexModelPrivatepage();
		if("sys".equals(type)){
			search.setCompanyId(0);
			search.setSchoolId(0);
		}
		if("user".equals(type)){
			search.setCompanyId(WebUtils.getCurrentCompanyId());
			search.setSchoolId(sid);
		}
		search.setTemplateId(id);
		return companyIndexModelPrivatepageServiceImpl.findByTemplate(search);
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
