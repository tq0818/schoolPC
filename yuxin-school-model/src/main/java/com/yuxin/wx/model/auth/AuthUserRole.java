package com.yuxin.wx.model.auth;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:AuthUserRole
 * 
 * @author wang.zx
 * @date 2015-1-27
 */
@SuppressWarnings("serial")
public class AuthUserRole extends BaseEntity {
	
	
	private Integer	userId;		
	private String	roleUid;		
	private Date	createTime;		 /* 创建时间 */ 
	private String	creator;		 /* 创建人 */ 
	private Date	updateTime;		 /* 更新时间 */ 
	private String	updator;		 /* 更新人 */ 
	private Integer companyId;
	private String[]roles;
	// Constructor
	public AuthUserRole() {
	}
	
	/**
	 * full Constructor
	 */
	public AuthUserRole(Integer id, Integer userId, Integer roleId, Date createTime, String creator, Date updateTime, String updator) {
		setId(id);
		this.userId = userId;
		this.roleUid = roleUid;
		this.createTime = createTime;
		this.creator = creator;
		this.updateTime = updateTime;
		this.updator = updator;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为AuthUserRole可以实现连缀设置属性
	
	public Integer getUserId() {
		return userId;
	}

	public AuthUserRole setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}
	
	
	public String getRoleUid() {
		return roleUid;
	}

	public AuthUserRole setRoleUid(String roleUid) {
		this.roleUid = roleUid;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public AuthUserRole setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	
	public String getCreator() {
		return creator;
	}

	public AuthUserRole setCreator(String creator) {
		this.creator = creator;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getUpdateTime() {
		return updateTime;
	}

	public AuthUserRole setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	
	
	public String getUpdator() {
		return updator;
	}

	public AuthUserRole setUpdator(String updator) {
		this.updator = updator;
		return this;
	}
	
	
  

	
    public Integer getCompanyId() {
    	return companyId;
    }

	
    public void setCompanyId(Integer companyId) {
    	this.companyId = companyId;
    }

	
    public String[] getRoles() {
    	return roles;
    }

	
    public void setRoles(String[] roles) {
    	this.roles = roles;
    }

	@Override
	public String toString() {
		return "AuthUserRole [" + "id=" + getId() + ", userId=" + userId + ", roleUid=" + roleUid + ", createTime=" + createTime + ", creator=" + creator + ", updateTime=" + updateTime + ", updator=" + updator +  "]";
	}
}
