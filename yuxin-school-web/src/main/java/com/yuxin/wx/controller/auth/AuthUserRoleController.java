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

import com.yuxin.wx.model.auth.AuthUserRole;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.api.auth.IAuthUserRoleService;

/**
 * Controller of AuthUserRole
 * @author wang.zx
 * @date 2015-1-27
 */
@Controller
@RequestMapping("/authUserRole")
public class AuthUserRoleController {
	
	@Autowired
	private IAuthUserRoleService authUserRoleServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, AuthUserRole search){
		if (search == null) {
			search = new AuthUserRole();
			// search.setPageSize(20);
		}
		model.addAttribute("list", authUserRoleServiceImpl.findAuthUserRoleByPage(search));
		return "authUserRole/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(AuthUserRole AuthUserRole) {
		authUserRoleServiceImpl.insert(AuthUserRole);
		return "redirect:/authUserRole";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(AuthUserRole AuthUserRole) {
		authUserRoleServiceImpl.update(AuthUserRole);
		return "redirect:/authUserRole";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
//		String r=rolesId.substring(0, rolesId.length()-1);
//		String[] roles=r.split(",");
		AuthUserRole role=new AuthUserRole();
		role.setUserId(id);
//		role.setRoles(roles);
		authUserRoleServiceImpl.deleteAuthUserRoleById(role);
		//authUserRoleServiceImpl.deleteAuthUserRoleById(id);
		return "redirect:/authUserRole";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public AuthUserRole getJson(Model model, @PathVariable Integer id){
		return authUserRoleServiceImpl.findAuthUserRoleById(id);
	}
	
 @ResponseBody
 @RequestMapping(value="/getUserRoles/{id}", method = RequestMethod.POST)
	public String getUserRoles(@PathVariable Integer id){
	    return authUserRoleServiceImpl.findUserRoles(id);
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
