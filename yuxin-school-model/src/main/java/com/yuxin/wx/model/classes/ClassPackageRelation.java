package com.yuxin.wx.model.classes;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:ClassPackageRelation
 * 
 * @author chopin
 * @date 2016-3-21
 */
@SuppressWarnings("serial")
public class ClassPackageRelation extends BaseEntity {
	
	
	private Integer	classPackageId;		
	private Integer	classTypeId;		
	private Integer	classPackageStageId;		
	private Integer	sort;		

	// Constructor
	public ClassPackageRelation() {
	}
	
	/**
	 * full Constructor
	 */
	public ClassPackageRelation(Integer id, Integer classPackageId, Integer classTypeId, Integer classPackageStageId, Integer sort) {
		setId(id);
		this.classPackageId = classPackageId;
		this.classTypeId = classTypeId;
		this.classPackageStageId = classPackageStageId;
		this.sort = sort;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为ClassPackageRelation可以实现连缀设置属性
	
	public Integer getClassPackageId() {
		return classPackageId;
	}

	public ClassPackageRelation setClassPackageId(Integer classPackageId) {
		this.classPackageId = classPackageId;
		return this;
	}
	
	
	public Integer getClassTypeId() {
		return classTypeId;
	}

	public ClassPackageRelation setClassTypeId(Integer classTypeId) {
		this.classTypeId = classTypeId;
		return this;
	}
	
	
	public Integer getClassPackageStageId() {
		return classPackageStageId;
	}

	public ClassPackageRelation setClassPackageStageId(Integer classPackageStageId) {
		this.classPackageStageId = classPackageStageId;
		return this;
	}
	
	
	public Integer getSort() {
		return sort;
	}

	public ClassPackageRelation setSort(Integer sort) {
		this.sort = sort;
		return this;
	}
	
	@Override
	public String toString() {
		return "ClassPackageRelation [" + "id=" + getId() + ", classPackageId=" + classPackageId + ", classTypeId=" + classTypeId + ", classPackageStageId=" + classPackageStageId + ", sort=" + sort +  "]";
	}
}
