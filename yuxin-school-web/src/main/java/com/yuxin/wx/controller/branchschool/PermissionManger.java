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
import com.yuxin.wx.common.Constant;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.auth.AuthRole;
import com.yuxin.wx.model.auth.AuthUserRole;
import com.yuxin.wx.model.company.Company;
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
	private ICompanyService companyServiceImpl;
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
    public String queryUserRolesByCompanyId(Model model, Integer page, String condition,String roleUid,String status, HttpServletRequest request, Integer companyId) {
        //从session中取值
    	int schoolId=sysConfigItemServiceImpl.findschooIdByCompanyId(companyId);
        Integer userId = WebUtils.getCurrentUserId(request);
        /**
         * 根据用户id查询用户角色，根据角色标记判断当前用户是否可跨分校
         * true:当前可跨分校，false:当前不可跨分校
         */
        if (authRoleServiceImpl.hasRoleFlag(userId,WebUtils.getCurrentCompanyId())) {
            model.addAttribute("peoplemark", "admin");
        }
        UserRolesListVo search = new UserRolesListVo();
        search.setCompanyId(companyId);
        /*if (StringUtils.isNotBlank(condition)) {
            if (ParameterUtil.isMobilePhone(condition)) {
                search.setMobile(condition);
            } else if (ParameterUtil.isUserName(condition)) {
                search.setUsername(condition);
            } else {
                search.setRealName(condition);
            }
        }*/
        search.setUsername(condition);
        if(StringUtils.isNotBlank(roleUid)) {
			search.setRoleId(Integer.valueOf(roleUid));
		}
		if(StringUtils.isNotBlank(status)) {
			search.setStatusStr(status);
		}
        search.setSchoolId(schoolId);
        search.setPage(page);
        search.setPageSize(10);
        
        //获取用户列表
        PageFinder<UserRolesListVo> pageFinder = authRoleServiceImpl.queryAllUser(search);
        model.addAttribute("pageFinder", pageFinder);
        model.addAttribute("userId", userId);
        //判断用户性别
        List<SysConfigDict> dictList = sysConfigDictService.findAll();
        model.addAttribute("dictList", dictList);
        model.addAttribute("companyId", companyId);
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
    	Integer uId=WebUtils.getCurrentUserId(request);
    	model.addAttribute("companyId",companyId);
    	Company company=null;
		if(companyId==null){
			company=WebUtils.getCurrentCompany();
		}else{
			company=companyServiceImpl.findCompanyById(companyId);
		}
		model.addAttribute("company",company);
		if("save".equals(type)){
			//查询校区列表
//			if(authRoleServiceImpl.hasRoleFlag(uId,WebUtils.getCurrentCompanyId())){
//				model.addAttribute("peoplemark", "admin");
//				List<SysConfigSchool> schoolList=sysConfigSchoolServiceImpl.findSysConfigSchoolByCompanyId(companyId);
//				model.addAttribute("schoolId", schoolId);
//				model.addAttribute("schoolList", schoolList);
//				List<AuthRole> authRoleList= authRoleServiceImpl.findByCompanyId(companyId);
//				if(authRoleList.size()<=0){
//					authRoleList=authRoleServiceImpl.findByCompanyId(0);
//				}
//				model.addAttribute("authRoleList", authRoleList);
//			}else{
				//model.addAttribute("peoplemark", "schooladmin");
				AuthRole search=new AuthRole();
				search.setRoleUid(1+"");
				search.setCompanyId(companyId);
				List<AuthRole> authRoleList= authRoleServiceImpl.queryRolesByCondition(search);
				if(authRoleList.size()<=0){
					search.setCompanyId(0);
					authRoleList=authRoleServiceImpl.queryRolesByCondition(search);
				}
				model.addAttribute("authRoleList", authRoleList);
//			}
		}else{
			Users user= userServiceImpl.findUsersById(userId);
			model.addAttribute("user", user);
//			if(authRoleServiceImpl.hasRoleFlag(uId,WebUtils.getCurrentCompanyId())){
//				model.addAttribute("peoplemark", "admin");
//				List<AuthRole> authRoleList= authRoleServiceImpl.findByCompanyId(companyId);
//				if(authRoleList.size()<=0){
//					authRoleList=authRoleServiceImpl.findByCompanyId(0);
//				}
//				model.addAttribute("authRoleList", authRoleList);
//			}else{
//				model.addAttribute("peoplemark", "schooladmin");
				AuthRole search=new AuthRole();
				search.setRoleUid(1+"");
				search.setCompanyId(companyId);
				List<AuthRole> authRoleList= authRoleServiceImpl.queryRolesByCondition(search);
				if(authRoleList.size()<=0){
					search.setCompanyId(0);
					authRoleList=authRoleServiceImpl.queryRolesByCondition(search);
				}
				model.addAttribute("authRoleList", authRoleList);
//			}
			SysConfigTeachersVo sysConfigTeacher=new SysConfigTeachersVo();
			sysConfigTeacher.setUserId(userId);
			List<SysConfigTeachersVo> arr= sysConfigTeacherServiceImpl.findSysConfigTeachersByName(sysConfigTeacher);
			model.addAttribute("teacherList", arr);
		}
		//查询菜单列表
		PrivilegeListVo privilege=new PrivilegeListVo();
		privilege.setCompanyId(company.getId());
		List<PrivilegeListVo> privilegeList=authPrivilegeCategoryServiceImpl.queryOnePrivilege(privilege);
		model.addAttribute("privilegeList", privilegeList);
		model.addAttribute("type", type);
		//代理机构
		CompanyConfigProxyOrg search = new CompanyConfigProxyOrg();
		search.setCompanyId(companyId);
		List<CompanyConfigProxyOrg> proxyOrgs = this.companyConfigProxyOrgServiceImpl.queryProxyByCompanyId(search);
		model.addAttribute("proxyOrgs", proxyOrgs);
        return "berkeley/addUsers";
    }
	/**
	 * 
	 * Class Name: AuthPrivilegeController.java
	 * @Description: 添加用户
	 * @author zhang.zx
	 * @date 2015年5月20日 上午10:49:23
	 * @modifier
	 * @modify-date 2015年5月20日 上午10:49:23
	 * @version 1.0
	 * @param user
	 * @param
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/saveUser",method=RequestMethod.POST)
	public void saveUser(Users user,String rolesId,String teachersId,
						String earaCode,String schoolAaraCode,String schoolCode,
						String gradeCode,String classCode,String subjectCode,
						String[] subJectGradeCode,String[] subjectClassCode,Integer companyId,
						HttpServletRequest request,HttpServletResponse response, Model model) throws IOException{
		Integer ccompanyId=companyId==null?WebUtils.getCurrentCompanyId():companyId;
		user.setCompanyId(ccompanyId);
		user.setStatus(1);
		user.setPassword(new Md5Hash(user.getPassword(),ByteSource.Util.bytes(user.getUsername()+"salt")).toHex());	
		//添加用户信息
		userServiceImpl.insert(user);
		//添加用户关系表
		userServiceImpl.insertUserCompanyRalation(user.getId(),ccompanyId);
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
			//管理用户关系范围
			Company company=null;
			if(companyId==null){
				company=WebUtils.getCurrentCompany();
			}else{
				company=companyServiceImpl.findCompanyById(ccompanyId);
			}
			authUserRoleServiceImpl.insertOrUpdateAreaInfo(company.getEduAreaSchool(),earaCode, schoolAaraCode, 
					schoolCode, gradeCode, classCode, subjectCode, 
					subJectGradeCode, subjectClassCode,r,user.getId());
		}
		
		//插入老师信息
		SysConfigTeacher sysConfigTeacher = new SysConfigTeacher();
//		sysConfigTeacher.setCreator(user.getId());
		sysConfigTeacher.setCreateTime(new Date());
		sysConfigTeacher.setUpdateTime(new Date());
		sysConfigTeacher.setUpdator(WebUtils.getCurrentUserId(request));
		sysConfigTeacher.setCompanyId(WebUtils.getCurrentCompanyId());
		sysConfigTeacher.setDelFlag(0);
		sysConfigTeacher.setTeacherType(Constant.PERSON_TEACHER);
		sysConfigTeacher.setStatusCode(Constant.TEACHER_USERD);
		sysConfigTeacher.setUserName(user.getUsername());
		sysConfigTeacher.setName(user.getRealName());
		sysConfigTeacher.setSex(user.getSex());
		sysConfigTeacher.setUserId(user.getId());
		sysConfigTeacher.setMobile(user.getMobile());
		sysConfigTeacherServiceImpl.insertTeacherInfo(sysConfigTeacher);
		
//		//修改教师信息
//		if(teachersId!=null&&!"".equals(teachersId)){
//			String t=teachersId.substring(0, teachersId.length()-1);
//			String[] teachers=t.split(",");
//			for(int i=0;i<teachers.length;i++){
//				SysConfigTeacher sysConfigTeacher=new SysConfigTeacher();
//				sysConfigTeacher.setId(Integer.parseInt(teachers[i]));
//				sysConfigTeacher.setUserId(user.getId());
//				sysConfigTeacherServiceImpl.updateauthTeacher(sysConfigTeacher);		
//			}
//		}
		Integer uId=WebUtils.getCurrentUserId(request);
		if(authRoleServiceImpl.hasRoleFlag(uId,WebUtils.getCurrentCompanyId())){
			model.addAttribute("peoplemark", "admin");
		}else{
			model.addAttribute("peoplemark", "schooladmin");
		}
		response.sendRedirect("/berkeley/permissionManagement/"+companyId);
	}
	
	/**
	 * 
	 * Class Name: AuthPrivilegeController.java
	 * @Description: 修改用户
	 * @author zhang.zx
	 * @date 2015年5月20日 上午10:49:38
	 * @modifier
	 * @modify-date 2015年5月20日 上午10:49:38
	 * @version 1.0
	 * @param user
	 * @param
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/updateUser",method=RequestMethod.POST)
	public void updateUser(Users user,String usernames, String rolesId,String teachersId,
			String earaCode,String schoolAaraCode,String schoolCode,
			String gradeCode,String classCode,String subjectCode,
			String[] subJectGradeCode,String[] subjectClassCode,Integer companyId,HttpServletResponse response,
			HttpServletRequest request,Model model) throws IOException{
		Integer ccompanyId= companyId==null?WebUtils.getCurrentCompanyId():companyId;
		//修改用户
		if(user.getPassword()!=null&&!"".equals(user.getPassword())){
			user.setUsername(usernames);
			user.setPassword(new Md5Hash(user.getPassword(),ByteSource.Util.bytes(user.getUsername()+"salt")).toHex());
		}
		userServiceImpl.update(user);
		//修改用户角色
		AuthUserRole role=new AuthUserRole();
		role.setUserId(user.getId());
		role.setCompanyId(ccompanyId);
		if("".equals(rolesId)){
			authUserRoleServiceImpl.deleteAuthUserRoleById(role);
		}
		else {
			//清除原来用户角色
			String r=rolesId.substring(0, rolesId.length()-1);
			String[] roles=r.split(",");
			role.setRoles(roles);
			authUserRoleServiceImpl.deleteAuthUserRoleById(role);
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
			//管理用户关系范围
			Company company=null;
				if(companyId==null){
					company=WebUtils.getCurrentCompany();
				}else{
				company=companyServiceImpl.findCompanyById(ccompanyId);
			}
			authUserRoleServiceImpl.insertOrUpdateAreaInfo(company.getEduAreaSchool(),earaCode, schoolAaraCode, 
					schoolCode, gradeCode, classCode, subjectCode, subJectGradeCode, subjectClassCode,r,user.getId());
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
		Integer uId=WebUtils.getCurrentUserId(request);
		if(authRoleServiceImpl.hasRoleFlag(uId,WebUtils.getCurrentCompanyId())){
			model.addAttribute("peoplemark", "admin");
		}else{
			model.addAttribute("peoplemark", "schooladmin");
		}
		response.sendRedirect("/berkeley/permissionManagement/"+companyId);
	}
	
    
 	@ResponseBody
	@RequestMapping(value="/queryRolesById/{userId}",method=RequestMethod.POST)
	public List<AuthRole> queryAuthRoleListByUser(@PathVariable Integer userId,Integer companyId){
 		List<AuthRole> arr= authRoleServiceImpl.queryAuthRoleListByUser(userId,companyId);
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
	public String queryUserRoleListsByPage(Model model,Integer page,Integer companyId,Integer schoolId,String condition,HttpServletRequest request){
		Integer userId=WebUtils.getCurrentUserId(request);
		if(authRoleServiceImpl.hasRoleFlag(userId,WebUtils.getCurrentCompanyId())){
			model.addAttribute("peoplemark", "admin");
		}
//		Subject subject = SecurityUtils.getSubject();
//		if(subject.hasRole("机构管理员")){
//			model.addAttribute("peoplemark", "admin");
//		}
		UserRolesListVo search=new UserRolesListVo();
		search.setCompanyId(companyId);
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
			if(authRoleServiceImpl.hasRoleFlag(uId,WebUtils.getCurrentCompanyId())){
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
			if(authRoleServiceImpl.hasRoleFlag(uId,WebUtils.getCurrentCompanyId())){
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
