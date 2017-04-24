package com.yuxin.wx.model.auth;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:AuthPrivilege
 * 
 * @author wang.zx
 * @date 2015-1-27
 */
@SuppressWarnings("serial")
public class AuthPrivilege extends BaseEntity {
	
	
	private String	privilegeName;		
	private String	description;		
	private Integer	privilegeCategoryId;		
	private Integer	companyId;		 /* 公司id */ 
	private Date	createTime;		 /* 创建时间 */ 
	private String	creator;		 /* 创建人 */ 
	private Date	updateTime;		 /* 更新时间 */ 
	private String	updator;		 /* 更新人 */ 

	// Constructor
	public AuthPrivilege() {
	}
	
	/**
	 * full Constructor
	 */
	public AuthPrivilege(Integer id, String privilegeName, String description, Integer privilegeCategoryId, Integer companyId, Date createTime, String creator, Date updateTime, String updator) {
		setId(id);
		this.privilegeName = privilegeName;
		this.description = description;
		this.privilegeCategoryId = privilegeCategoryId;
		this.companyId = companyId;
		this.createTime = createTime;
		this.creator = creator;
		this.updateTime = updateTime;
		this.updator = updator;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为AuthPrivilege可以实现连缀设置属性
	
	public String getPrivilegeName() {
		return privilegeName;
	}

	public AuthPrivilege setPrivilegeName(String privilegeName) {
		this.privilegeName = privilegeName;
		return this;
	}
	
	
	public String getDescription() {
		return description;
	}

	public AuthPrivilege setDescription(String description) {
		this.description = description;
		return this;
	}
	
	
	public Integer getPrivilegeCategoryId() {
		return privilegeCategoryId;
	}

	public AuthPrivilege setPrivilegeCategoryId(Integer privilegeCategoryId) {
		this.privilegeCategoryId = privilegeCategoryId;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public AuthPrivilege setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public AuthPrivilege setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	
	public String getCreator() {
		return creator;
	}

	public AuthPrivilege setCreator(String creator) {
		this.creator = creator;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getUpdateTime() {
		return updateTime;
	}

	public AuthPrivilege setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	
	
	public String getUpdator() {
		return updator;
	}

	public AuthPrivilege setUpdator(String updator) {
		this.updator = updator;
		return this;
	}
	
	@Override
	public String toString() {
		return "AuthPrivilege [" + "id=" + getId() + ", privilegeName=" + privilegeName + ", description=" + description + ", privilegeCategoryId=" + privilegeCategoryId + ", companyId=" + companyId + ", createTime=" + createTime + ", creator=" + creator + ", updateTime=" + updateTime + ", updator=" + updator +  "]";
	}
}
