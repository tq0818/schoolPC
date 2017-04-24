package com.yuxin.wx.model.classes;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:ClassTypeResourceType
 * 
 * @author wang.zx
 * @date 2015-8-11
 */
@SuppressWarnings("serial")
public class ClassTypeResourceType extends BaseEntity {
	
	
	private Integer	companyId;		 /* 公司Id */ 
	private String	resourceType;		 /* 资料类型（字典表） */ 
	private String	resourceName;		 /* 资料类型对应的名称 */ 

	// Constructor
	public ClassTypeResourceType() {
	}
	
	/**
	 * full Constructor
	 */
	public ClassTypeResourceType(Integer id, Integer companyId, String resourceType, String resourceName) {
		setId(id);
		this.companyId = companyId;
		this.resourceType = resourceType;
		this.resourceName = resourceName;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为ClassTypeResourceType可以实现连缀设置属性
	
	public Integer getCompanyId() {
		return companyId;
	}

	public ClassTypeResourceType setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public String getResourceType() {
		return resourceType;
	}

	public ClassTypeResourceType setResourceType(String resourceType) {
		this.resourceType = resourceType;
		return this;
	}
	
	
	public String getResourceName() {
		return resourceName;
	}

	public ClassTypeResourceType setResourceName(String resourceName) {
		this.resourceName = resourceName;
		return this;
	}
	
	@Override
	public String toString() {
		return "ClassTypeResourceType [" + "id=" + getId() + ", companyId=" + companyId + ", resourceType=" + resourceType + ", resourceName=" + resourceName +  "]";
	}
}
