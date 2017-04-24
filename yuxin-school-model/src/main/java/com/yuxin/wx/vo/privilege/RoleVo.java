package com.yuxin.wx.vo.privilege;

import java.util.Date;

import com.yuxin.wx.common.BaseEntity;

public class RoleVo extends BaseEntity{
	
	private Integer	userId;		
	private String	roleUid;   /*角色id*/
	private String roleName;  /* 角色名称 */
	private String description; /*角色描述*/
	private Date	createTime;		 /* 创建时间 */ 
	private String	creator;		 /* 创建人 */ 
	private Date	updateTime;		 /* 更新时间 */ 
	private String	updator;		 /* 更新人 */
	private Integer companyId;
	private Integer parentId; 
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getRoleUid() {
		return roleUid;
	}
	public void setRoleUid(String roleUid) {
		this.roleUid = roleUid;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	} 

}
