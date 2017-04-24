package com.yuxin.wx.vo.user;

import java.util.Date;

import com.yuxin.wx.common.BaseEntity;

@SuppressWarnings("serial")
public class UserVo extends BaseEntity{
	
	private String	username;		 /* 用户名 */ 
	private String	password;		 /* 密码 */ 
	private String	realName;		
	private String	email;		 /* 手机号 */ 
	private String	userType;		 /* 用户类型（1:机构用户；2:普通用户） */ 
	private String  city;        /* 用户所在城市*/
	private Integer	schoolId;		 /* 所属校区id */ 
	private Integer	status;		 /* 用户状态（1：有效；0：无效），默认为1 */ 
	private Integer	companyId;		 /* 所属公司id */ 
	private String schoolName;
	
	private Integer roleId;
	private String roleIds;
	private String roleName;
	
	private Integer privilegeId;
	private String privilegeCategoryId;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	public Integer getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Integer getPrivilegeId() {
		return privilegeId;
	}
	public void setPrivilegeId(Integer privilegeId) {
		this.privilegeId = privilegeId;
	}
	public String getPrivilegeCategoryId() {
		return privilegeCategoryId;
	}
	public void setPrivilegeCategoryId(String privilegeCategoryId) {
		this.privilegeCategoryId = privilegeCategoryId;
	}
}
