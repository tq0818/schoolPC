package com.yuxin.wx.controller.auth;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.auth.IAuthPrivilegeCategoryService;
import com.yuxin.wx.api.auth.IAuthRolePrivilegeService;
import com.yuxin.wx.api.auth.IAuthRoleService;
import com.yuxin.wx.api.auth.IAuthUserRoleService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.model.auth.AuthRole;
import com.yuxin.wx.model.auth.AuthRolePrivilege;
import com.yuxin.wx.model.auth.AuthUserRole;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.privilege.PrivilegeListVo;
import com.yuxin.wx.vo.privilege.TreeNode;

/**
 * Controller of AuthRole
 * @author wang.zx
 * @date 2015-1-27
 */
@Controller
@RequestMapping("/authRole")
public class AuthRoleController {
	
	@Autowired
	private IAuthRoleService authRoleServiceImpl;
	@Autowired
	private IAuthUserRoleService authUserRoleServiceImpl;
	@Autowired
	private IAuthRolePrivilegeService authRolePrivilegeServiceImpl;
	@Autowired
	private IAuthPrivilegeCategoryService authPrivilegeCategoryServiceImpl ;
	@Autowired
	private ICompanyService companyServiceImpl;
	
	
	@RequestMapping(value="/page/{page}", method = RequestMethod.GET)
	public String page(Model model,@PathVariable String page){
	    return "/auth/"+page;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, AuthRole search){
		if (search == null) {
			search = new AuthRole();
			// search.setPageSize(20);
		}
		model.addAttribute("list", authRoleServiceImpl.findAuthRoleByPage(search));
		return "authRole/list";
	}
	
	@ResponseBody
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(HttpServletRequest request, AuthRole authRole) {
	 authRole.setDescription(authRole.getRoleName());
	 authRole.setCompanyId(WebUtils.getCurrentCompanyId());
	 authRole.setCreateTime(new Date());
	 authRole.setCreator(WebUtils.getCurrentUserName(request));
  authRole.setUpdateTime(new Date());
  authRole.setUpdator(WebUtils.getCurrentUserName(request));
		authRoleServiceImpl.insert(authRole);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value="/update", method = RequestMethod.POST) 
	public String update(HttpServletRequest request,AuthRole authRole) {
	 authRole.setUpdateTime(new Date());
	 authRole.setUpdator(WebUtils.getCurrentUserName(request));
		authRoleServiceImpl.update(authRole);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value="/save", method = RequestMethod.POST) 
 public String save(HttpServletRequest request) {
	 List<TreeNode> nodes=JSONObject.parseArray(request.getParameter("tree"),com.yuxin.wx.vo.privilege.TreeNode.class);
	 
	 return authRoleServiceImpl.saveRoles(nodes,WebUtils.getCurrentUser());
 }
	
	@ResponseBody
	@RequestMapping(value="/del/{id}", method = RequestMethod.POST)
	public Boolean del(Model model, @PathVariable String id) {
		  List<AuthRolePrivilege> list1=authRolePrivilegeServiceImpl.findPrivilegeByRole(id);
		  List<AuthUserRole> list2=authUserRoleServiceImpl.findByRoleId(id);
		  if(list1.size()==0 && list2.size()==0){
		      authRoleServiceImpl.deleteAuthRoleById(id);
		      return true;
		  }else{
		      return false;
		  }
	}
	
	/**
	 * 
	 * Class Name: AuthRoleController.java
	 * @Description: 删除用户角色及相关权限信息
	 * @author zhang.zx
	 * @date 2016年1月15日 下午2:09:23
	 * @modifier
	 * @modify-date 2016年1月15日 下午2:09:23
	 * @version 1.0
	 * @param model
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/del_privleges/{id}")
	public String delRole_privleges(Model model,@PathVariable String id){
		AuthRole authRole=authRoleServiceImpl.findAuthRoleById(id);
		if(authRole != null){
			//删除角色
			authRoleServiceImpl.deleteAuthRoleById(authRole.getId().toString());
			//删除权限
			List<AuthRolePrivilege> list1=authRolePrivilegeServiceImpl.findPrivilegeByRole(id);
			if(list1.size()>0){
				for(AuthRolePrivilege pris:list1){
					authRolePrivilegeServiceImpl.deleteAuthRolePrivilegeById(pris.getId());
				}
			}
		}
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public AuthRole getJson(Model model, @PathVariable String id){
		return authRoleServiceImpl.findAuthRoleById(id);
	}
	
 @ResponseBody
 @RequestMapping(value="/ajaxList", method = RequestMethod.POST)
	public List<TreeNode> getJsons(Model model,HttpServletRequest request){
     List<AuthRole> roles=authRoleServiceImpl.findAllAuthRole();
     List<TreeNode> nodes=new ArrayList<TreeNode>();
     for(AuthRole role : roles){
         TreeNode node=new TreeNode();
         node.setId(""+role.getId());
         node.setName(role.getRoleName());
         node.setPId(role.getParentId());
         nodes.add(node);
     }
     System.out.println(JSONArray.toJSONString(nodes));
	    return nodes; 
	}

 	@ResponseBody
	@RequestMapping(value="/queryRolesById/{userId}",method=RequestMethod.POST)
	public List<AuthRole> queryAuthRoleListByUser(@PathVariable Integer userId){
 		List<AuthRole> arr= authRoleServiceImpl.queryAuthRoleListByUser(userId);
		return arr;
	}
 	
 	@ResponseBody
 	@RequestMapping(value="/queryRoleFlag/{userId}")
 	public boolean queryAuthRoleFlag(@PathVariable Integer userId){
 		if(authRoleServiceImpl.hasRoleFlag(userId)){
 			return true;
 		}
 		return false;
 	}
 	
 	@ResponseBody
 	@RequestMapping(value="/authAdmin")
 	public boolean queryAuthRoleIsAdmin(HttpServletRequest request){
 		Integer userId = WebUtils.getCurrentUserId(request);
 		if(authRoleServiceImpl.hasRoleFlag(userId)){
 			return true;
 		}
 		return false;
 	}
 	
 	@ResponseBody
 	@RequestMapping(value="/authAdmin2")
 	public String queryAuthRoleIsAdmin2(HttpServletRequest request){
 		Integer userId = WebUtils.getCurrentUserId(request);
 		Subject subject = SecurityUtils.getSubject();
 		if(authRoleServiceImpl.hasRoleFlag(userId)){
 			return "1";
 		}
 		else if(subject.hasRole("直播老师")&& !authRoleServiceImpl.hasRoleFlag(userId)){
 			return "2";
 		}
 		return "";
 	}
 	
 	//-----------------------公司角色定制-------------------------------
 	/**
 	 * 
 	 * Class Name: AuthRoleController.java
 	 * @Description: TODO(这里用一句话描述这个方法的作用)
 	 * @author DELL.COM
 	 * @date 2016年1月8日 下午5:25:56
 	 * @modifier
 	 * @modify-date 2016年1月8日 下午5:25:56
 	 * @version 1.0
 	 * @return
 	 */
 	@RequestMapping(value="/configRole")
 	public String configCompanyRoles(Model model,HttpServletRequest request){
 		if(!authRoleServiceImpl.checkUserHasPrivilege(WebUtils.getCurrentUserId(request), "system_privilege_config")){
 			return "404";
 		}
 		List<AuthRole> authRoleList= authRoleServiceImpl.findAll();
		model.addAttribute("authRoleList", authRoleList);
 		//查询菜单列表
		PrivilegeListVo privilege=new PrivilegeListVo();
		List<PrivilegeListVo> privilegeList=authPrivilegeCategoryServiceImpl.queryOnePrivilegeByCompanys(privilege);
		model.addAttribute("privilegeList", privilegeList);
		Company company=new Company();
		company.setBuyFlag(1);
		List<Company> companys=companyServiceImpl.queryCompanyBuyFlag(company);
		//List<Company> companys=companyServiceImpl.queryAllCompany();
		model.addAttribute("companyIds", companys);
 		return "system/companyRolePrivilege";
 	}
 	
 	/**
 	 * 
 	 * Class Name: AuthRoleController.java
 	 * @Description: 查看用户角色名称是否存在
 	 * @author zhang.zx
 	 * @date 2016年1月11日 下午4:28:07
 	 * @modifier
 	 * @modify-date 2016年1月11日 下午4:28:07
 	 * @version 1.0
 	 * @param name
 	 * @return
 	 */
 	@ResponseBody
 	@RequestMapping(value="/queryRoleName")
 	public String queryRoleName(String name,Integer companyId){
 		AuthRole search=new AuthRole();
 		search.setCompanyId(companyId);
 		search.setRoleName(name);
 		List<AuthRole> list=authRoleServiceImpl.queryRolesByCondition(search);
 		if(null!=list&&list.size()>0){
 			return "repet";
 		}
 		return "norepet";
 	}
 	
 	/**
 	 * 
 	 * Class Name: AuthRoleController.java
 	 * @Description: 添加角色和菜单
 	 * @author zhang.zx
 	 * @date 2016年1月11日 下午4:32:06
 	 * @modifier
 	 * @modify-date 2016年1月11日 下午4:32:06
 	 * @version 1.0
 	 * @param request
 	 * @return
 	 */
 	@ResponseBody
 	@RequestMapping(value="/addRoleandMenu")
 	public boolean addRoleandMenu(HttpServletRequest request){
 		//角色名称
 		String name=request.getParameter("name");
 		String privlage=request.getParameter("privlage");
 		Integer companyId=Integer.parseInt(request.getParameter("companyId"));
 		Integer roleFlag=Integer.parseInt(request.getParameter("roleFlag"));
 		//权限菜单id
		String privlageIds=privlage.substring(0, privlage.length()-1);
		return authRoleServiceImpl.addAuthRoleandMenu(companyId, name, privlageIds,roleFlag);
 	}
 	
 	@ResponseBody
 	@RequestMapping(value="/getRolesByCompanyId/{id}")
 	public List<AuthRole> queryAuthRolesByCompanyId(@PathVariable Integer id){
 		AuthRole search=new AuthRole();
		search.setCompanyId(id);
		List<AuthRole> list=authRoleServiceImpl.queryRolesByCondition(search);
		return list;
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
