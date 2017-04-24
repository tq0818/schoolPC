package com.yuxin.wx.model.auth;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:AuthPrivilegeCategory
 * 
 * @author wang.zx
 * @date 2015-1-27
 */
@SuppressWarnings("serial")
public class AuthPrivilegeCategory extends BaseEntity {
	
	
	private String	categoryName;		
	private Integer	parentId;		
	private Integer	companyId;		 /* 公司id */ 
	private Date	createTime;		 /* 创建时间 */ 
	private String	creator;		 /* 创建人 */ 
	private Date	updateTime;		 /* 更新时间 */ 
	private String	updator;		 /* 更新人 */ 

	// Constructor
	public AuthPrivilegeCategory() {
	}
	
	/**
	 * full Constructor
	 */
	public AuthPrivilegeCategory(Integer id, String categoryName, Integer parentId, Integer companyId, Date createTime, String creator, Date updateTime, String updator) {
		setId(id);
		this.categoryName = categoryName;
		this.parentId = parentId;
		this.companyId = companyId;
		this.createTime = createTime;
		this.creator = creator;
		this.updateTime = updateTime;
		this.updator = updator;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为AuthPrivilegeCategory可以实现连缀设置属性
	
	public String getCategoryName() {
		return categoryName;
	}

	public AuthPrivilegeCategory setCategoryName(String categoryName) {
		this.categoryName = categoryName;
		return this;
	}
	
	
	public Integer getParentId() {
		return parentId;
	}

	public AuthPrivilegeCategory setParentId(Integer parentId) {
		this.parentId = parentId;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public AuthPrivilegeCategory setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public AuthPrivilegeCategory setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	
	public String getCreator() {
		return creator;
	}

	public AuthPrivilegeCategory setCreator(String creator) {
		this.creator = creator;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getUpdateTime() {
		return updateTime;
	}

	public AuthPrivilegeCategory setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	
	
	public String getUpdator() {
		return updator;
	}

	public AuthPrivilegeCategory setUpdator(String updator) {
		this.updator = updator;
		return this;
	}
	
	@Override
	public String toString() {
		return "AuthPrivilegeCategory [" + "id=" + getId() + ", categoryName=" + categoryName + ", parentId=" + parentId + ", companyId=" + companyId + ", createTime=" + createTime + ", creator=" + creator + ", updateTime=" + updateTime + ", updator=" + updator +  "]";
	}
}
