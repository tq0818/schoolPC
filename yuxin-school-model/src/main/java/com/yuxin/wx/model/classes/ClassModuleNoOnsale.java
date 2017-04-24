package com.yuxin.wx.model.classes;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:ClassModuleNoOnsale
 * 
 * @author wang.zx
 * @date 2015-8-14
 */
@SuppressWarnings("serial")
public class ClassModuleNoOnsale extends BaseEntity {
	
	
	private Integer	classtypeId;		
	private Integer	moduleId;		 /* 模块id */ 
	private Integer	moduleNoId;		 /* 班号id */ 
	private Integer	defaultFlag;		 /* 默认班号标记: 0-非默认 1-默认 */ 

	// Constructor
	public ClassModuleNoOnsale() {
	}
	
	/**
	 * full Constructor
	 */
	public ClassModuleNoOnsale(Integer id, Integer classtypeId, Integer moduleId, Integer moduleNoId, Integer defaultFlag) {
		setId(id);
		this.classtypeId = classtypeId;
		this.moduleId = moduleId;
		this.moduleNoId = moduleNoId;
		this.defaultFlag = defaultFlag;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为ClassModuleNoOnsale可以实现连缀设置属性
	
	public Integer getClasstypeId() {
		return classtypeId;
	}

	public ClassModuleNoOnsale setClasstypeId(Integer classtypeId) {
		this.classtypeId = classtypeId;
		return this;
	}
	
	
	public Integer getModuleId() {
		return moduleId;
	}

	public ClassModuleNoOnsale setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
		return this;
	}
	
	
	public Integer getModuleNoId() {
		return moduleNoId;
	}

	public ClassModuleNoOnsale setModuleNoId(Integer moduleNoId) {
		this.moduleNoId = moduleNoId;
		return this;
	}
	
	
	public Integer getDefaultFlag() {
		return defaultFlag;
	}

	public ClassModuleNoOnsale setDefaultFlag(Integer defaultFlag) {
		this.defaultFlag = defaultFlag;
		return this;
	}
	
	@Override
	public String toString() {
		return "ClassModuleNoOnsale [" + "id=" + getId() + ", classtypeId=" + classtypeId + ", moduleId=" + moduleId + ", moduleNoId=" + moduleNoId + ", defaultFlag=" + defaultFlag +  "]";
	}
}
