package com.yuxin.wx.model.auth;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:AuthRolePrivilege
 * 
 * @author wang.zx
 * @date 2015-1-27
 */
@SuppressWarnings("serial")
public class AuthRolePrivilege extends BaseEntity {
	
	
	private String	roleUid;		
	private Integer	privilegeId;		
	private Date	createTime;		 /* 创建时间 */ 
	private String	creator;		 /* 创建人 */ 
	private Date	updateTime;		 /* 更新时间 */ 
	private String	updator;		 /* 更新人 */ 
	private Integer privilegeCategoryId;  /* 权限菜单*/

	// Constructor
	public AuthRolePrivilege() {
	}
	
	/**
	 * full Constructor
	 */
	public AuthRolePrivilege(Integer id, String roleUid, Integer privilegeId, Date createTime, String creator, Date updateTime, String updator) {
		setId(id);
		this.roleUid = roleUid;
		this.privilegeId = privilegeId;
		this.createTime = createTime;
		this.creator = creator;
		this.updateTime = updateTime;
		this.updator = updator;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为AuthRolePrivilege可以实现连缀设置属性
	
	public String getRoleUid() {
		return roleUid;
	}

	public AuthRolePrivilege setRoleUid(String roleUid) {
		this.roleUid = roleUid;
		return this;
	}
	
	
	public Integer getPrivilegeId() {
		return privilegeId;
	}

	public AuthRolePrivilege setPrivilegeId(Integer privilegeId) {
		this.privilegeId = privilegeId;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public AuthRolePrivilege setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	
	public String getCreator() {
		return creator;
	}

	public AuthRolePrivilege setCreator(String creator) {
		this.creator = creator;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getUpdateTime() {
		return updateTime;
	}

	public AuthRolePrivilege setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	
	
	public String getUpdator() {
		return updator;
	}

	public AuthRolePrivilege setUpdator(String updator) {
		this.updator = updator;
		return this;
	}
	
	public Integer getPrivilegeCategoryId() {
		return privilegeCategoryId;
	}

	public void setPrivilegeCategoryId(Integer privilegeCategoryId) {
		this.privilegeCategoryId = privilegeCategoryId;
	}

	@Override
	public String toString() {
		return "AuthRolePrivilege [" + "id=" + getId() + ", roleUid=" + roleUid + ", privilegeId=" + privilegeId + ", createTime=" + createTime + ", creator=" + creator + ", updateTime=" + updateTime + ", updator=" + updator +  "]";
	}
}
