package com.yuxin.wx.controller.branchschool;

import com.yuxin.wx.api.auth.IAuthPrivilegeCategoryService;
import com.yuxin.wx.api.auth.IAuthRoleService;
import com.yuxin.wx.api.auth.IAuthUserRoleService;
import com.yuxin.wx.api.company.ICompanyConfigProxyOrgService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.system.ISysConfigDictService;
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
import com.yuxin.wx.vo.company.CompanyVo;
import com.yuxin.wx.vo.privilege.PrivilegeListVo;
import com.yuxin.wx.vo.privilege.UserRolesListVo;
import com.yuxin.wx.vo.system.SysConfigTeachersVo;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;


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

    private ICompanyService iCompanyService;


    @RequestMapping(value = "/queryUserRolesByCompanyId", method = RequestMethod.POST)
    public String queryUserRolesByCompanyId(Model model, Integer page, Integer schoolId, String condition, HttpServletRequest request, Integer companyId) {
        //从session中取值
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
        PageFinder<UserRolesListVo> pageFinder = authRoleServiceImpl.queryAllUser(search);
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

        return "berkeley/addUsers";
    }


    /**
     *点击添加用户页面的保存按钮
     * @param user
     * @param rolesId
     * @param teachersId
     * @param request
     * @param response
     * @param model
     * @throws IOException
     */
    @RequestMapping(value="/saveUser",method=RequestMethod.POST)
    public void saveUser(Users user, String rolesId, String teachersId, HttpServletRequest request, HttpServletResponse response, Model model, Integer companyId) throws IOException {
      /* //判断此用户是否已经存在
        if(!userServiceImpl.isExist(user.getMobile())){//如果不存在则插入用户信息
            //获取company中isArea,eduAreaSchool字段的值
            CompanyVo company =  iCompanyService.getIsAreaByCompanyId(companyId);
            //插入用户信息
            user.setEduAreaSchool(company.getEduAreaSchool());
            user.setIsArea(company.getIsArea());
            user.setCompanyId(companyId);
            user.setStatus(1);
            user.setPassword(new Md5Hash(user.getPassword(), ByteSource.Util.bytes(user.getUsername()+"salt")).toHex());

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
            Integer uId= WebUtils.getCurrentUserId(request);
            if(authRoleServiceImpl.hasRoleFlag(uId)){
                model.addAttribute("peoplemark", "admin");
                List<SysConfigSchool> schoolList=sysConfigSchoolServiceImpl.findSysConfigSchoolByCompanyId(companyId);
                model.addAttribute("schoolId", user.getSchoolId());
                model.addAttribute("schoolList", schoolList);
            }else{
                model.addAttribute("peoplemark", "schooladmin");
                SysConfigSchool school=sysConfigSchoolServiceImpl.findSysConfigSchoolById(user.getSchoolId());
                model.addAttribute("school1",school);
            }
        }

        Integer userId = userServiceImpl.findUserByModel(user.getMobile());
        //如果存在则向users_company_relation表中插入关联数据
        UserCompanyRelation userCompanyRelation = new UserCompanyRelation();
        userCompanyRelation.setCompanyId(companyId);
        userCompanyRelation.setUserId(userId);
        userCompanyRelation.setIsUsed(1);
        userCompanyRelationService.insert(userCompanyRelation);*/


        response.sendRedirect("/berkeley/permissionManagement");
    }

}
