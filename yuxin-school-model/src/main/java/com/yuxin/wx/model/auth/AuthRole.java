package com.yuxin.wx.model.auth;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:AuthRole
 * 
 * @author wang.zx
 * @date 2015-1-27
 */
@SuppressWarnings("serial")
public class AuthRole extends BaseEntity {
	
	private String  roleUid;
	private String	roleName;		
	private String	description;		
	private Integer	parentId;		
	private Integer	companyId;		 /* 公司id */ 
	private Date	createTime;		 /* 创建时间 */ 
	private String	creator;		 /* 创建人 */ 
	private Date	updateTime;		 /* 更新时间 */ 
	private String	updator;		 /* 更新人 */ 
	private Integer roleFlag;
	private String zhuCompanyId;
	// Constructor
	public AuthRole() {
	}
	
	/**
	 * full Constructor
	 */
	public AuthRole(Integer id, String roleName, String description, Integer parentId, Integer companyId, Date createTime, String creator, Date updateTime, String updator) {
		setId(id);
		this.roleName = roleName;
		this.description = description;
		this.parentId = parentId;
		this.companyId = companyId;
		this.createTime = createTime;
		this.creator = creator;
		this.updateTime = updateTime;
		this.updator = updator;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为AuthRole可以实现连缀设置属性
	
	public String getRoleName() {
		return roleName;
	}

	public AuthRole setRoleName(String roleName) {
		this.roleName = roleName;
		return this;
	}
	
	
	public String getDescription() {
		return description;
	}

	public AuthRole setDescription(String description) {
		this.description = description;
		return this;
	}
	
	
	public Integer getParentId() {
		return parentId;
	}

	public AuthRole setParentId(Integer parentId) {
		this.parentId = parentId;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public AuthRole setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public AuthRole setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	
	public String getCreator() {
		return creator;
	}

	public AuthRole setCreator(String creator) {
		this.creator = creator;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getUpdateTime() {
		return updateTime;
	}

	public AuthRole setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	
	
	public String getUpdator() {
		return updator;
	}

	public AuthRole setUpdator(String updator) {
		this.updator = updator;
		return this;
	}
	
	public String getRoleUid() {
		return roleUid;
	}

	public void setRoleUid(String roleUid) {
		this.roleUid = roleUid;
	}

	public Integer getRoleFlag() {
		return roleFlag;
	}

	public void setRoleFlag(Integer roleFlag) {
		this.roleFlag = roleFlag;
	}

	
    public String getZhuCompanyId() {
    	return zhuCompanyId;
    }

	
    public void setZhuCompanyId(String zhuCompanyId) {
    	this.zhuCompanyId = zhuCompanyId;
    }

	@Override
	public String toString() {
		return "AuthRole [" + "id=" + getId() + ", roleName=" + roleName + ", description=" + description + ", parentId=" + parentId + ", companyId=" + companyId + ", createTime=" + createTime + ", creator=" + creator + ", updateTime=" + updateTime + ", updator=" + updator +  "]";
	}
}
