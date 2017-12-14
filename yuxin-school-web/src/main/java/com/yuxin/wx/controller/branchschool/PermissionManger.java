package com.yuxin.wx.controller.branchschool;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuxin.wx.api.auth.IAuthPrivilegeCategoryService;
import com.yuxin.wx.api.auth.IAuthRolePrivilegeService;
import com.yuxin.wx.api.auth.IAuthRoleService;
import com.yuxin.wx.api.auth.IAuthUserRoleService;
import com.yuxin.wx.api.company.ICompanyConfigProxyOrgService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.system.ISysConfigDictService;
import com.yuxin.wx.api.system.ISysConfigItemService;
import com.yuxin.wx.api.system.ISysConfigSchoolService;
import com.yuxin.wx.api.system.ISysConfigTeacherService;
import com.yuxin.wx.api.user.IUsersService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.auth.AuthRole;
import com.yuxin.wx.model.auth.AuthUserRole;
import com.yuxin.wx.model.company.CompanyConfigProxyOrg;
import com.yuxin.wx.model.system.SysConfigDict;
import com.yuxin.wx.model.system.SysConfigSchool;
import com.yuxin.wx.model.system.SysConfigTeacher;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.utils.ParameterUtil;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.privilege.PrivilegeListVo;
import com.yuxin.wx.vo.privilege.PrivilegeVo;
import com.yuxin.wx.vo.privilege.UserRolesListVo;
import com.yuxin.wx.vo.system.SysConfigTeachersVo;


@Controller
@RequestMapping("/permissionManger")
public class PermissionManger {

    @Autowired
    private IAuthRoleService authRoleServiceImpl;
    @Autowired
    private ISysConfigDictService sysConfigDictService;
    @Autowired
    private ISysConfigSchoolService sysConfigSchoolServiceImpl;
    @Autowired
    private IUsersService userServiceImpl;
    @Autowired
    private ISysConfigTeacherService sysConfigTeacherServiceImpl;
    @Autowired
    private IAuthPrivilegeCategoryService authPrivilegeCategoryServiceImpl;
    @Autowired
    private ICompanyConfigProxyOrgService companyConfigProxyOrgServiceImpl;
    @Autowired
    private IAuthUserRoleService authUserRoleServiceImpl;
    @Autowired
    private ISysConfigItemService sysConfigItemServiceImpl;
    @Autowired
	private IAuthRolePrivilegeService authRolePrivilegeServiceImpl;


    @RequestMapping(value = "/queryUserRolesByCompanyId", method = RequestMethod.POST)
    public String queryUserRolesByCompanyId(Model model, Integer page, String condition, HttpServletRequest request, Integer companyId) {
        //从session中取值
    	int schoolId=sysConfigItemServiceImpl.findschooIdByCompanyId(companyId);
        Integer userId = WebUtils.getCurrentUserId(request);
        /**
         * 根据用户id查询用户角色，根据角色标记判断当前用户是否可跨分校
         * true:当前可跨分校，false:当前不可跨分校
         */
        if (authRoleServiceImpl.hasRoleFlag(userId)) {
            model.addAttribute("peoplemark", "admin");
        }
        UserRolesListVo search = new UserRolesListVo();
        search.setCompanyId(companyId);
        if (StringUtils.isNotBlank(condition)) {
            if (ParameterUtil.isMobilePhone(condition)) {
                search.setMobile(condition);
            } else if (ParameterUtil.isUserName(condition)) {
                search.setUsername(condition);
            } else {
                search.setRealName(condition);
            }
        }
        
        search.setSchoolId(schoolId);
        search.setPage(page);
        //获取用户列表
        PageFinder<UserRolesListVo> pageFinder = authRoleServiceImpl.queryNewAllUser(search);
        model.addAttribute("pageFinder", pageFinder);
        model.addAttribute("userId", userId);
        //判断用户性别
        List<SysConfigDict> dictList = sysConfigDictService.findAll();
        model.addAttribute("dictList", dictList);
        return "berkeley/permissionUserlist";
    }


    /**
     * 获取角色列表
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getRoleList", method = RequestMethod.GET)
    public String getRoleList(Model model) {
        //判断用户性别
        List<AuthRole> roleList = authRoleServiceImpl.findAll();
        model.addAttribute("roleList", roleList);
        return "berkeley/permissionManagement";
    }


    /**
     * 添加用户去添加页面
     *
     * @param model
     * @param request
     * @param type
     * @param userId
     * @param schoolId
     * @return
     */
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(Model model, HttpServletRequest request, String type, Integer userId, String schoolId, Integer companyId) {
        Integer uId = WebUtils.getCurrentUserId(request);
        if ("save".equals(type)) {
            //查询校区列表
//            Integer companyId=WebUtils.getCurrentCompanyId();
            if (authRoleServiceImpl.hasRoleFlag(uId)) {
                model.addAttribute("peoplemark", "admin");
                List<SysConfigSchool> schoolList = sysConfigSchoolServiceImpl.findSysConfigSchoolByCompanyId(companyId);
                model.addAttribute("schoolId", schoolId);
                model.addAttribute("schoolList", schoolList);
                List<AuthRole> authRoleList = authRoleServiceImpl.findByCompanyId(companyId);
                if (authRoleList.size() <= 0) {
                    authRoleList = authRoleServiceImpl.findByCompanyId(0);
                }
                model.addAttribute("authRoleList", authRoleList);
            } else {
                model.addAttribute("peoplemark", "schooladmin");
                SysConfigSchool school = sysConfigSchoolServiceImpl.findSysConfigSchoolById(Integer.parseInt(schoolId));
                model.addAttribute("school1", school);
                AuthRole search = new AuthRole();
                search.setRoleUid(1 + "");
                search.setCompanyId(companyId);
                List<AuthRole> authRoleList = authRoleServiceImpl.queryRolesByCondition(search);
                if (authRoleList.size() <= 0) {
                    search.setCompanyId(0);
                    authRoleList = authRoleServiceImpl.queryRolesByCondition(search);
                }
                model.addAttribute("authRoleList", authRoleList);
            }
        } else {
            Users user = userServiceImpl.findUsersById(userId);
            model.addAttribute("user", user);
            if (authRoleServiceImpl.hasRoleFlag(uId)) {
                model.addAttribute("peoplemark", "admin");
                List<AuthRole> authRoleList = authRoleServiceImpl.findByCompanyId(companyId);
                if (authRoleList.size() <= 0) {
                    authRoleList = authRoleServiceImpl.findByCompanyId(0);
                }
                model.addAttribute("authRoleList", authRoleList);
            } else {
                model.addAttribute("peoplemark", "schooladmin");
                AuthRole search = new AuthRole();
                search.setRoleUid(1 + "");
                search.setCompanyId(WebUtils.getCurrentCompanyId());
                List<AuthRole> authRoleList = authRoleServiceImpl.queryRolesByCondition(search);
                if (authRoleList.size() <= 0) {
                    search.setCompanyId(0);
                    authRoleList = authRoleServiceImpl.queryRolesByCondition(search);
                }
                model.addAttribute("authRoleList", authRoleList);
            }
            SysConfigTeachersVo sysConfigTeacher = new SysConfigTeachersVo();
            sysConfigTeacher.setUserId(userId);
            List<SysConfigTeachersVo> arr = sysConfigTeacherServiceImpl.findSysConfigTeachersByName(sysConfigTeacher);
            model.addAttribute("teacherList", arr);
        }
        //查询菜单列表
        PrivilegeListVo privilege = new PrivilegeListVo();
        List<PrivilegeListVo> privilegeList = authPrivilegeCategoryServiceImpl.queryOnePrivilege(privilege);
        model.addAttribute("privilegeList", privilegeList);
        model.addAttribute("type", type);
        //代理机构
        CompanyConfigProxyOrg search = new CompanyConfigProxyOrg();
        search.setCompanyId(WebUtils.getCurrentCompanyId());
        List<CompanyConfigProxyOrg> proxyOrgs = this.companyConfigProxyOrgServiceImpl.queryProxyByCompanyId(search);
        model.addAttribute("proxyOrgs", proxyOrgs);
        model.addAttribute("companyId", companyId);

        return "berkeley/addUsers";
    }

/**
 * 
 * @author jishangyang 2017年12月11日 下午10:50:40
 * @Method: saveUser 
 * @Description:保存
 * @param user
 * @param rolesId
 * @param teachersId
 * @param request
 * @param response
 * @param model
 * @throws IOException 
 * @throws
 */
	@RequestMapping(value="/saveUser",method=RequestMethod.POST)
	public void saveUser(Users user,String rolesId,String teachersId,HttpServletRequest request,HttpServletResponse response, Model model) throws IOException{
		user.setCompanyId(WebUtils.getCurrentCompanyId());
		user.setStatus(1);
		user.setPassword(new Md5Hash(user.getPassword(),ByteSource.Util.bytes(user.getUsername()+"salt")).toHex());	
		
		//添加用户信息
		userServiceImpl.insert(user);
		//添加用户角色权限信息
		if(rolesId!=null&&!"".equals(rolesId)){
			String r=rolesId.substring(0, rolesId.length()-1);
			String[] roles=r.split(",");
			for(int i=0;i<roles.length;i++){
			    AuthUserRole authUserRole=new AuthUserRole();
			    authUserRole.setUserId(user.getId());
			    authUserRole.setRoleUid(roles[i]);
			    authUserRole.setCreateTime(new Date());
			    authUserRole.setCreator(WebUtils.getCurrentUserId(request)+"");
			    authUserRole.setUpdateTime(new Date());
			    authUserRole.setUpdator(WebUtils.getCurrentUserId(request)+"");
				authUserRoleServiceImpl.insert(authUserRole);
			}
		}
		
		//修改教师信息
		if(teachersId!=null&&!"".equals(teachersId)){
			String t=teachersId.substring(0, teachersId.length()-1);
			String[] teachers=t.split(",");
			for(int i=0;i<teachers.length;i++){
				SysConfigTeacher sysConfigTeacher=new SysConfigTeacher();
				sysConfigTeacher.setId(Integer.parseInt(teachers[i]));
				sysConfigTeacher.setUserId(user.getId());
				sysConfigTeacherServiceImpl.updateauthTeacher(sysConfigTeacher);		
			}
		}
		Integer uId=WebUtils.getCurrentUserId(request);
		if(authRoleServiceImpl.hasRoleFlag(uId)){
			model.addAttribute("peoplemark", "admin");
			List<SysConfigSchool> schoolList=sysConfigSchoolServiceImpl.findSysConfigSchoolByCompanyId(WebUtils.getCurrentCompanyId());
			model.addAttribute("schoolId", user.getSchoolId());
			model.addAttribute("schoolList", schoolList);
		}else{
			model.addAttribute("peoplemark", "schooladmin");
			SysConfigSchool school=sysConfigSchoolServiceImpl.findSysConfigSchoolById(user.getSchoolId());
			model.addAttribute("school1",school);
		}
//		Subject subject = SecurityUtils.getSubject();
//		if(subject.hasRole("机构管理员")){
//			model.addAttribute("peoplemark", "admin");
//			List<SysConfigSchool> schoolList=sysConfigSchoolServiceImpl.findSysConfigSchoolByCompanyId(WebUtils.getCurrentCompanyId());
//			model.addAttribute("schoolId", user.getSchoolId());
//			model.addAttribute("schoolList", schoolList);
//		}else if(subject.hasRole("分校管理员")){
//			model.addAttribute("peoplemark", "schooladmin");
//			SysConfigSchool school=sysConfigSchoolServiceImpl.findSysConfigSchoolById(user.getSchoolId());
//			model.addAttribute("school1",school);
//		}
//		Integer companyId=WebUtils.getCurrentCompanyId();
//		List<SysConfigSchool> schoolList=sysConfigSchoolServiceImpl.findSysConfigSchoolByCompanyId(companyId);
//		model.addAttribute("schoolId", user.getSchoolId());
//		model.addAttribute("schoolList", schoolList);
		//return "system/authPrivilegeIndex";
		response.sendRedirect("showAuth");
	}
    
    /**
     * 
     * @author jishangyang 2017年12月11日 下午10:49:38
     * @Method: updateUser 
     * @Description: 保存修改用户
     * @param user
     * @param usernames
     * @param rolesId
     * @param teachersId
     * @param request
     * @param model
     * @return 
     * @throws
     */
    @RequestMapping(value="/updateUser",method=RequestMethod.POST)
	public String updateUser(Users user,String usernames, String rolesId,String teachersId,HttpServletRequest request,Model model){	
		//修改用户
		if(user.getPassword()!=null&&!"".equals(user.getPassword())){
			user.setUsername(usernames);
			user.setPassword(new Md5Hash(user.getPassword(),ByteSource.Util.bytes(user.getUsername()+"salt")).toHex());
		}
		userServiceImpl.update(user);
		//修改用户角色
		if(rolesId!=null&&!"".equals(rolesId)){
			//清除原来用户角色
			authUserRoleServiceImpl.deleteAuthUserRoleById(user.getId());
			String r=rolesId.substring(0, rolesId.length()-1);
			String[] roles=r.split(",");
			for(int i=0;i<roles.length;i++){
				 AuthUserRole authUserRole=new AuthUserRole();
			     authUserRole.setUserId(user.getId());
			     authUserRole.setRoleUid(roles[i]);
			     authUserRole.setCreateTime(new Date());
			     authUserRole.setCreator(WebUtils.getCurrentUserId(request)+"");
			     authUserRole.setUpdateTime(new Date());
			     authUserRole.setUpdator(WebUtils.getCurrentUserId(request)+"");
				 authUserRoleServiceImpl.insert(authUserRole);
			}	
		}
		//修改教师
		if(teachersId!=null&&!"".equals(teachersId)){
			//清除
			List<SysConfigTeacher> teac=sysConfigTeacherServiceImpl.findTeachersByUserId(user.getId());
			for(SysConfigTeacher t1:teac){
				t1.setUserId(null);
				sysConfigTeacherServiceImpl.updateauthTeacher(t1);
			}
			String t=teachersId.substring(0, teachersId.length()-1);
			String[] teachers=t.split(",");
			for(int i=0;i<teachers.length;i++){
				SysConfigTeacher sysConfigTeacher=new SysConfigTeacher();
				sysConfigTeacher.setId(Integer.parseInt(teachers[i]));
				sysConfigTeacher.setUserId(user.getId());
				sysConfigTeacherServiceImpl.updateauthTeacher(sysConfigTeacher);
			}
		}
		Users user1=userServiceImpl.findUsersById(user.getId());
		Integer uId=WebUtils.getCurrentUserId(request);
		if(authRoleServiceImpl.hasRoleFlag(uId)){
			model.addAttribute("peoplemark", "admin");
			List<SysConfigSchool> schoolList=sysConfigSchoolServiceImpl.findSysConfigSchoolByCompanyId(WebUtils.getCurrentCompanyId());
			model.addAttribute("schoolId", user1.getSchoolId());
			model.addAttribute("schoolList", schoolList);
		}else{
			model.addAttribute("peoplemark", "schooladmin");
			SysConfigSchool school=sysConfigSchoolServiceImpl.findSysConfigSchoolById(user1.getSchoolId());
			model.addAttribute("school1",school);
		}
//		Subject subject = SecurityUtils.getSubject();
//		if(subject.hasRole("机构管理员")){
//			model.addAttribute("peoplemark", "admin");
//			List<SysConfigSchool> schoolList=sysConfigSchoolServiceImpl.findSysConfigSchoolByCompanyId(WebUtils.getCurrentCompanyId());
//			model.addAttribute("schoolId", user1.getSchoolId());
//			model.addAttribute("schoolList", schoolList);
//		}else if(subject.hasRole("分校管理员")){
//			model.addAttribute("peoplemark", "schooladmin");
//			SysConfigSchool school=sysConfigSchoolServiceImpl.findSysConfigSchoolById(user1.getSchoolId());
//			model.addAttribute("school1",school);
//		}
//		Integer companyId=WebUtils.getCurrentCompanyId();
//		List<SysConfigSchool> schoolList=sysConfigSchoolServiceImpl.findSysConfigSchoolByCompanyId(companyId);
//		model.addAttribute("schoolId", user1.getSchoolId());
//		model.addAttribute("schoolList", schoolList);
		return "system/authPrivilegeIndex";
	}
    
 	@ResponseBody
	@RequestMapping(value="/queryRolesById/{userId}",method=RequestMethod.POST)
	public List<AuthRole> queryAuthRoleListByUser(@PathVariable Integer userId){
 		List<AuthRole> arr= authRoleServiceImpl.queryAuthRoleListByUser(userId);
		return arr;
	}
 	
	@ResponseBody
	@RequestMapping(value="/Category/{roleId}", method = RequestMethod.POST)
	public List<PrivilegeVo> getCategorys(@PathVariable String roleId){
		List<PrivilegeVo> list=authRolePrivilegeServiceImpl.findNewUserPrivileges(roleId);
		return list;
	}
	
/**
 * 
 * @author jishangyang 2017年12月11日 下午10:54:03
 * @Method: queryUserRoleListsByPage 
 * @Description: 查询角色并分页
 * @param model
 * @param page
 * @param schoolId
 * @param condition
 * @param request
 * @return 
 * @throws
 */
	@RequestMapping(value="/queryUserRoles",method=RequestMethod.POST)
	public String queryUserRoleListsByPage(Model model,Integer page, Integer schoolId,String condition,HttpServletRequest request){
		Integer userId=WebUtils.getCurrentUserId(request);
		if(authRoleServiceImpl.hasRoleFlag(userId)){
			model.addAttribute("peoplemark", "admin");
		}
//		Subject subject = SecurityUtils.getSubject();
//		if(subject.hasRole("机构管理员")){
//			model.addAttribute("peoplemark", "admin");
//		}
		UserRolesListVo search=new UserRolesListVo();
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		if(StringUtils.isNotBlank(condition)) {
			if(ParameterUtil.isMobilePhone(condition)) {
				search.setMobile(condition);
			}else if(ParameterUtil.isUserName(condition)) {
				search.setUsername(condition);
			}else {
				search.setRealName(condition);
			}
		}
		search.setSchoolId(schoolId);
		search.setPage(page);
		PageFinder<UserRolesListVo> pageFinder=authRoleServiceImpl.queryAllUser(search);
		model.addAttribute("pageFinder", pageFinder);
		model.addAttribute("userId", userId);
		List<SysConfigDict> dictList = sysConfigDictService.findAll();
		model.addAttribute("dictList", dictList);
		return "system/authPrivilegeAjaxList";
	}
	
/**
 * 
 * @author jishangyang 2017年12月11日 下午10:55:20
 * @Method: addAuthPrivilage 
 * @Description: 编辑用户
 * @param model
 * @param request
 * @param type
 * @param userId
 * @param schoolId
 * @return 
 * @throws
 */
	@RequestMapping(value="/addAuthPrivilage",method=RequestMethod.POST)
	public String addAuthPrivilage(Model model,HttpServletRequest request,String type,Integer userId,String schoolId){
		Integer uId=WebUtils.getCurrentUserId(request);
		if("save".equals(type)){
			//查询校区列表
			Integer companyId=WebUtils.getCurrentCompanyId();
			if(authRoleServiceImpl.hasRoleFlag(uId)){
				model.addAttribute("peoplemark", "admin");
				List<SysConfigSchool> schoolList=sysConfigSchoolServiceImpl.findSysConfigSchoolByCompanyId(companyId);
				model.addAttribute("schoolId", schoolId);
				model.addAttribute("schoolList", schoolList);
				List<AuthRole> authRoleList= authRoleServiceImpl.findByCompanyId(companyId);
				if(authRoleList.size()<=0){
					authRoleList=authRoleServiceImpl.findByCompanyId(0);
				}
				model.addAttribute("authRoleList", authRoleList);
			}else{
				model.addAttribute("peoplemark", "schooladmin");
				SysConfigSchool school=sysConfigSchoolServiceImpl.findSysConfigSchoolById(Integer.parseInt(schoolId));
				model.addAttribute("school1",school);
				AuthRole search=new AuthRole();
				search.setRoleUid(1+"");
				search.setCompanyId(companyId);
				List<AuthRole> authRoleList= authRoleServiceImpl.queryRolesByCondition(search);
				if(authRoleList.size()<=0){
					search.setCompanyId(0);
					authRoleList=authRoleServiceImpl.queryRolesByCondition(search);
				}
				model.addAttribute("authRoleList", authRoleList);
			}
//			Subject subject = SecurityUtils.getSubject();
//			if(subject.hasRole("机构管理员")){
//				model.addAttribute("peoplemark", "admin");
//				List<SysConfigSchool> schoolList=sysConfigSchoolServiceImpl.findSysConfigSchoolByCompanyId(companyId);
//				model.addAttribute("schoolId", schoolId);
//				model.addAttribute("schoolList", schoolList);
//				List<AuthRole> authRoleList= authRoleServiceImpl.findByCompanyId(companyId);
//				if(authRoleList.size()<=0){
//					authRoleList=authRoleServiceImpl.findByCompanyId(0);
//				}
//				model.addAttribute("authRoleList", authRoleList);
//			}else if(subject.hasRole("分校管理员")){
//				model.addAttribute("peoplemark", "schooladmin");
//				SysConfigSchool school=sysConfigSchoolServiceImpl.findSysConfigSchoolById(Integer.parseInt(schoolId));
//				model.addAttribute("school1",school);
//				AuthRole search=new AuthRole();
//				search.setRoleUid(1+"");
//				search.setCompanyId(companyId);
//				List<AuthRole> authRoleList= authRoleServiceImpl.queryRolesByCondition(search);
//				if(authRoleList.size()<=0){
//					search.setCompanyId(0);
//					authRoleList=authRoleServiceImpl.queryRolesByCondition(search);
//				}
//				model.addAttribute("authRoleList", authRoleList);
//			}
		}else{
			Integer companyId=WebUtils.getCurrentCompanyId();
			Users user= userServiceImpl.findUsersById(userId);
			model.addAttribute("user", user);
			if(authRoleServiceImpl.hasRoleFlag(uId)){
				model.addAttribute("peoplemark", "admin");
				List<AuthRole> authRoleList= authRoleServiceImpl.findByCompanyId(companyId);
				if(authRoleList.size()<=0){
					authRoleList=authRoleServiceImpl.findByCompanyId(0);
				}
				model.addAttribute("authRoleList", authRoleList);
			}else{
				model.addAttribute("peoplemark", "schooladmin");
				AuthRole search=new AuthRole();
				search.setRoleUid(1+"");
				search.setCompanyId(WebUtils.getCurrentCompanyId());
				List<AuthRole> authRoleList= authRoleServiceImpl.queryRolesByCondition(search);
				if(authRoleList.size()<=0){
					search.setCompanyId(0);
					authRoleList=authRoleServiceImpl.queryRolesByCondition(search);
				}
				model.addAttribute("authRoleList", authRoleList);
			}
//			Subject subject = SecurityUtils.getSubject();
//			if(subject.hasRole("机构管理员")){
//				model.addAttribute("peoplemark", "admin");
//				List<AuthRole> authRoleList= authRoleServiceImpl.findByCompanyId(companyId);
//				if(authRoleList.size()<=0){
//					authRoleList=authRoleServiceImpl.findByCompanyId(0);
//				}
//				model.addAttribute("authRoleList", authRoleList);
//			}else if(subject.hasRole("分校管理员")){
//				model.addAttribute("peoplemark", "schooladmin");
//				AuthRole search=new AuthRole();
//				search.setRoleUid(1+"");
//				List<AuthRole> authRoleList= authRoleServiceImpl.queryRolesByCondition(search);
//				if(authRoleList.size()<=0){
//					search.setCompanyId(0);
//					authRoleList=authRoleServiceImpl.queryRolesByCondition(search);
//				}
//				model.addAttribute("authRoleList", authRoleList);
//			}else{
//				
//			}
			SysConfigTeachersVo sysConfigTeacher=new SysConfigTeachersVo();
			sysConfigTeacher.setUserId(userId);
			List<SysConfigTeachersVo> arr= sysConfigTeacherServiceImpl.findSysConfigTeachersByName(sysConfigTeacher);
			model.addAttribute("teacherList", arr);
		}
		//查询菜单列表
		PrivilegeListVo privilege=new PrivilegeListVo();
		List<PrivilegeListVo> privilegeList=authPrivilegeCategoryServiceImpl.queryOnePrivilege(privilege);
		model.addAttribute("privilegeList", privilegeList);
		model.addAttribute("type", type);
		//代理机构
		CompanyConfigProxyOrg search = new CompanyConfigProxyOrg();
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		List<CompanyConfigProxyOrg> proxyOrgs = this.companyConfigProxyOrgServiceImpl.queryProxyByCompanyId(search);
		model.addAttribute("proxyOrgs", proxyOrgs);
		
		return "system/editAuthPrivilege";
	}

}
