package com.yuxin.wx.model.classes;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:ClassTypeResource 
 * 
 * @author wang.zx
 * @date 2015-8-11
 */
@SuppressWarnings("serial")
public class ClassTypeResource extends BaseEntity {
	
	
	private Integer	itemOneId;		 /* 学科 */ 
	private Integer	itemSecondId;		 /* 学科小类 */ 
	private Integer	classTypeId;		 /* 班型ID */ 
	private String	classTypeName;		 /* 班型名称 */ 
	private Integer	lectureId;		 /* 课次Id */ 
	private String	resourceType;		 /* 资料类型（） */ 
	private Integer	companyId;		
	private String lectureType;    /* 资料类型*/
	private Integer fileId;
	

	// Constructor
	public ClassTypeResource() {
	}
	
	/**
	 * full Constructor
	 */
	public ClassTypeResource(Integer id, Integer itemOneId, Integer itemSecondId, Integer classTypeId, String classTypeName, Integer lectureId, String resourceType, Integer companyId) {
		setId(id);
		this.itemOneId = itemOneId;
		this.itemSecondId = itemSecondId;
		this.classTypeId = classTypeId;
		this.classTypeName = classTypeName;
		this.lectureId = lectureId;
		this.resourceType = resourceType;
		this.companyId = companyId;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为ClassTypeResource可以实现连缀设置属性
	
	public Integer getItemOneId() {
		return itemOneId;
	}

	public ClassTypeResource setItemOneId(Integer itemOneId) {
		this.itemOneId = itemOneId;
		return this;
	}
	
	
	public Integer getItemSecondId() {
		return itemSecondId;
	}

	public ClassTypeResource setItemSecondId(Integer itemSecondId) {
		this.itemSecondId = itemSecondId;
		return this;
	}
	
	
	public Integer getClassTypeId() {
		return classTypeId;
	}

	public ClassTypeResource setClassTypeId(Integer classTypeId) {
		this.classTypeId = classTypeId;
		return this;
	}
	
	
	public String getClassTypeName() {
		return classTypeName;
	}

	public ClassTypeResource setClassTypeName(String classTypeName) {
		this.classTypeName = classTypeName;
		return this;
	}
	
	
	public Integer getLectureId() {
		return lectureId;
	}

	public ClassTypeResource setLectureId(Integer lectureId) {
		this.lectureId = lectureId;
		return this;
	}
	
	
	public String getResourceType() {
		return resourceType;
	}

	public ClassTypeResource setResourceType(String resourceType) {
		this.resourceType = resourceType;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public ClassTypeResource setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}

	public String getLectureType() {
		return lectureType;
	}

	public void setLectureType(String lectureType) {
		this.lectureType = lectureType;
	}

	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}
	
}
