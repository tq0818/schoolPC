package com.yuxin.wx.controller.auth;

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

import com.yuxin.wx.model.auth.AuthPrivilegeCategory;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.api.auth.IAuthPrivilegeCategoryService;

/**
 * Controller of AuthPrivilegeCategory
 * @author wang.zx
 * @date 2015-1-27
 */
@Controller
@RequestMapping("/authPrivilegeCategory")
public class AuthPrivilegeCategoryController {
	
	@Autowired
	private IAuthPrivilegeCategoryService authPrivilegeCategoryServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, AuthPrivilegeCategory search){
		if (search == null) {
			search = new AuthPrivilegeCategory();
			// search.setPageSize(20);
		}
		model.addAttribute("list", authPrivilegeCategoryServiceImpl.findAuthPrivilegeCategoryByPage(search));
		return "authPrivilegeCategory/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(AuthPrivilegeCategory AuthPrivilegeCategory) {
		authPrivilegeCategoryServiceImpl.insert(AuthPrivilegeCategory);
		return "redirect:/authPrivilegeCategory";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(AuthPrivilegeCategory AuthPrivilegeCategory) {
		authPrivilegeCategoryServiceImpl.update(AuthPrivilegeCategory);
		return "redirect:/authPrivilegeCategory";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		authPrivilegeCategoryServiceImpl.deleteAuthPrivilegeCategoryById(id);
		return "redirect:/authPrivilegeCategory";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public AuthPrivilegeCategory getJson(Model model, @PathVariable Integer id){
		return authPrivilegeCategoryServiceImpl.findAuthPrivilegeCategoryById(id);
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
