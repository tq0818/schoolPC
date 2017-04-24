package com.yuxin.wx.vo.classes;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:ClassTypeModuleRelation
 * @author wang.zx
 * @date 2014-12-5
 */
@SuppressWarnings("serial")
public class ClassTypeModuleRelationVo extends BaseEntity {
	
	
	private Integer	classTypeId;		 /* 班型表主键 */ 
	private Integer	moduleId;		 	/* 模块表主键 */ 
	private String teachMethod;			/* 授课方式 */ 

	// Constructor
	public ClassTypeModuleRelationVo() {
	}
	
	/**
	 * full Constructor
	 */
	public ClassTypeModuleRelationVo(Integer id, Integer classTypeId, Integer moduleId, String teachMethod) {
		setId(id);
		this.classTypeId = classTypeId;
		this.moduleId = moduleId;
		this.teachMethod = teachMethod;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为ClassTypeModuleRelation可以实现连缀设置属性
	
	public Integer getClassTypeId() {
		return classTypeId;
	}

	public ClassTypeModuleRelationVo setClassTypeId(Integer classTypeId) {
		this.classTypeId = classTypeId;
		return this;
	}
	
	
	public Integer getModuleId() {
		return moduleId;
	}

	public ClassTypeModuleRelationVo setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
		return this;
	}
	
	public String getTeachMethod() {
		return teachMethod;
	}

	public void setTeachMethod(String teachMethod) {
		this.teachMethod = teachMethod;
	}

	@Override
	public String toString() {
		return "ClassTypeModuleRelationVo [" + "id=" + getId() + ", classTypeId=" + classTypeId + ", moduleId=" + moduleId + ", teachMethod=" + teachMethod+ "]";
	}
}
