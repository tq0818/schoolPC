package com.yuxin.wx.model.classes;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:ClassTypeModuleRelation
 * @author wang.zx
 * @date 2014-12-5
 */
@SuppressWarnings("serial")
public class ClassTypeModuleRelation extends BaseEntity {
	
	
	private Integer	classTypeId;		 /* 班型表主键 */ 
	private Integer	moduleId;		 /* 模块表主键 */ 

	// Constructor
	public ClassTypeModuleRelation() {
	}
	
	/**
	 * full Constructor
	 */
	public ClassTypeModuleRelation(Integer id, Integer classTypeId, Integer moduleId) {
		setId(id);
		this.classTypeId = classTypeId;
		this.moduleId = moduleId;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为ClassTypeModuleRelation可以实现连缀设置属性
	
	public Integer getClassTypeId() {
		return classTypeId;
	}

	public ClassTypeModuleRelation setClassTypeId(Integer classTypeId) {
		this.classTypeId = classTypeId;
		return this;
	}
	
	
	public Integer getModuleId() {
		return moduleId;
	}

	public ClassTypeModuleRelation setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
		return this;
	}
	
	@Override
	public String toString() {
		return "ClassTypeModuleRelation [" + "id=" + getId() + ", classTypeId=" + classTypeId + ", moduleId=" + moduleId +  "]";
	}
}
