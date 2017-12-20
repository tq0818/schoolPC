package com.yuxin.wx.controller.auth;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
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
import com.yuxin.wx.api.auth.IAuthRolePrivilegeService;
import com.yuxin.wx.api.auth.IAuthRoleService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.model.auth.AuthRole;
import com.yuxin.wx.model.auth.AuthRolePrivilege;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.privilege.AreaInfoVo;
import com.yuxin.wx.vo.privilege.EduSubjectClassTeacherInfo;
import com.yuxin.wx.vo.privilege.GradeInfoVo;
import com.yuxin.wx.vo.privilege.PrivilegeVo;
import com.yuxin.wx.vo.privilege.SubjectInfoVo;
import com.yuxin.wx.vo.privilege.TreeNode;
import com.yuxin.wx.vo.privilege.UserInfoVo;

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
	@Autowired
	private IAuthRoleService authRoleServiceImpl;
	@Autowired
	private ICompanyService companyServiceImpl;
	
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
	@RequestMapping(value="/getCategorysInfo", method = RequestMethod.POST)
	public UserInfoVo getCategorysInfo(HttpServletRequest request,String roleId,String userId,Integer companyId){
		UserInfoVo userInfoVo=new UserInfoVo();
		Integer ccompanyId=companyId==null?WebUtils.getCurrentCompanyId():companyId;
		//查询对应的权限
		List<PrivilegeVo> privilegeVos=authRolePrivilegeServiceImpl.findUserPrivileges(roleId);
		userInfoVo.setPrivilegeVos(privilegeVos);
		//先根据角色标识号查询角色名称
		AuthRole search=new AuthRole();
		search.setRoleUid(roleId);
		search.setCompanyId(ccompanyId);
		Company company=null;
		if(companyId==null){
			company=WebUtils.getCurrentCompany();
		}else{
			company=companyServiceImpl.findCompanyById(ccompanyId);
		}
		List<AuthRole> authRoles=authRoleServiceImpl.queryRolesByConditionInfo(search);
		if(authRoles!=null&&authRoles.size()>0){
			AuthRole authRole=authRoles.get(0);
			String roleName=authRole.getRoleName();
			String eduAreaSchool=null;
			if(company!=null){
				eduAreaSchool=company.getEduAreaSchool();
			}
			//获取行政区域
			if("区县负责人".equals(roleName)||"学校负责人".equals(roleName)){
				List<AreaInfoVo> areaInfoVos=authRoleServiceImpl.queryAreaSchoolInfo(userId,company.getIsArea(),eduAreaSchool,roleName);
				userInfoVo.setAreaInfoVos(areaInfoVos);
			}
			//if("2".equals(WebUtils.getCurrentIsArea())&&("任课老师".equals(roleName)||"班主任".equals(roleName))){
			if("2".equals(WebUtils.getCurrentIsArea())&&("任课老师".equals(roleName)||"班主任".equals(roleName))){
				//校级用户，查询科目，年级，班级
				//2、查询所有的年级
				List<GradeInfoVo>gradeInfoVos=authRoleServiceImpl.queryAllGradeInfos(eduAreaSchool, userId, roleName);
				userInfoVo.setGradeInfoVos(gradeInfoVos);
				//3、任课老师独有
				if("任课老师".equals(roleName)){
					//1、查询所有的科目
					List<SubjectInfoVo> subjectInfoVos=authRoleServiceImpl.queryAllSubjectInfo(ccompanyId+"");
					//2、查询出任课教师对应的任课班级
					List<EduSubjectClassTeacherInfo>eduSubjectClassTeacherInfos=authRoleServiceImpl.queryEduSubjectClassTeacherInfo(eduAreaSchool,userId);
					if(subjectInfoVos!=null&&subjectInfoVos.size()>0){
						for(SubjectInfoVo vo:subjectInfoVos){
							if(eduSubjectClassTeacherInfos!=null&&eduSubjectClassTeacherInfos.size()>0){
								for(EduSubjectClassTeacherInfo inf:eduSubjectClassTeacherInfos){
									if(StringUtils.isNotEmpty(vo.getSubjectCode())&&vo.getSubjectCode().equals(inf.getSubjectCode())){
										vo.setSelected(true);
										break;
									}
								}
							}
						}
					}
					userInfoVo.setSubjectInfoVos(subjectInfoVos);
					userInfoVo.setEduSubjectClassTeacherInfos(eduSubjectClassTeacherInfos);
				}
			}
			userInfoVo.setRoleName(roleName);
		}
		return userInfoVo;
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
