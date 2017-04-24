package com.yuxin.wx.vo.privilege;

import java.util.Date;
import java.util.List;

import com.yuxin.wx.common.BaseEntity;

@SuppressWarnings("serial")
public class PrivilegeVo extends BaseEntity{

	private Integer	roleId;		
	private Integer	privilegeId;		
	private String	privilegeName;		
	private String	description;		
	private Integer	privilegeCategoryId;		
	private Integer	companyId;		 /* 公司id */ 
	private Date	createTime;		 /* 创建时间 */ 
	private String	creator;		 /* 创建人 */ 
	private Date	updateTime;		 /* 更新时间 */ 
	private String	updator;		 /* 更新人 */
	
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getPrivilegeId() {
		return privilegeId;
	}
	public void setPrivilegeId(Integer privilegeId) {
		this.privilegeId = privilegeId;
	}
	public String getPrivilegeName() {
		return privilegeName;
	}
	public void setPrivilegeName(String privilegeName) {
		this.privilegeName = privilegeName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getPrivilegeCategoryId() {
		return privilegeCategoryId;
	}
	public void setPrivilegeCategoryId(Integer privilegeCategoryId) {
		this.privilegeCategoryId = privilegeCategoryId;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getUpdator() {
		return updator;
	}
	public void setUpdator(String updator) {
		this.updator = updator;
	}
	
}
