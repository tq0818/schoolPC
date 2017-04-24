package com.yuxin.wx.controller.auth;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

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

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.model.auth.AuthRolePrivilege;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.privilege.PrivilegeVo;
import com.yuxin.wx.vo.privilege.TreeNode;
import com.yuxin.wx.api.auth.IAuthRolePrivilegeService;

/**
 * Controller of AuthRolePrivilege
 * @author wang.zx
 * @date 2015-1-27
 */
@Controller
@RequestMapping("/authRolePrivilege")
public class AuthRolePrivilegeController {
	
	@Autowired
	private IAuthRolePrivilegeService authRolePrivilegeServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, AuthRolePrivilege search){
		if (search == null) {
			search = new AuthRolePrivilege();
			// search.setPageSize(20);
		}
		model.addAttribute("list", authRolePrivilegeServiceImpl.findAuthRolePrivilegeByPage(search));
		return "authRolePrivilege/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(AuthRolePrivilege AuthRolePrivilege) {
		authRolePrivilegeServiceImpl.insert(AuthRolePrivilege);
		return "redirect:/authRolePrivilege";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(AuthRolePrivilege AuthRolePrivilege) {
		authRolePrivilegeServiceImpl.update(AuthRolePrivilege);
		return "redirect:/authRolePrivilege";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		authRolePrivilegeServiceImpl.deleteAuthRolePrivilegeById(id);
		return "redirect:/authRolePrivilege";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public AuthRolePrivilege getJson(Model model, @PathVariable Integer id){
		return authRolePrivilegeServiceImpl.findAuthRolePrivilegeById(id);
	}
	@ResponseBody
	@RequestMapping(value="/privileges/{roleId}", method = RequestMethod.POST)
	public List<AuthRolePrivilege> getPrivileges( @PathVariable String roleId){
	    List<AuthRolePrivilege> list=authRolePrivilegeServiceImpl.findPrivilegeByRole(roleId);
	    return list;
	}
	
	@ResponseBody
	@RequestMapping(value="/Category/{roleId}", method = RequestMethod.POST)
	public List<PrivilegeVo> getCategorys(@PathVariable String roleId){
		List<PrivilegeVo> list=authRolePrivilegeServiceImpl.findUserPrivileges(roleId);
		return list;
	}
	
 @ResponseBody
 @RequestMapping(value="/save", method = RequestMethod.POST)
 public String save(HttpServletRequest request){
     List<TreeNode> nodes=JSONObject.parseArray(request.getParameter("tree"),com.yuxin.wx.vo.privilege.TreeNode.class);
     System.out.println("[[[[长度]]]]=>>>>"+nodes.size());
     String roleId=request.getParameter("roleId");
     return authRolePrivilegeServiceImpl.saveRolePrivilege(nodes,roleId,WebUtils.getCurrentUser());
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
