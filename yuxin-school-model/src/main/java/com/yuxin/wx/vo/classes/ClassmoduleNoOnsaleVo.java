package com.yuxin.wx.vo.classes;

import com.yuxin.wx.common.BaseEntity;


@SuppressWarnings("serial")
public class ClassmoduleNoOnsaleVo extends BaseEntity {
	
	private Integer classtypeId;
	private Integer moduleId;
	private Integer moduleNoId;
	private Integer defaultFlag;
	
	public ClassmoduleNoOnsaleVo() {}

	public ClassmoduleNoOnsaleVo(Integer classtypeId, Integer moduleId,
			Integer moduleNoId, Integer defaultFlag) {
		this.classtypeId = classtypeId;
		this.moduleId = moduleId;
		this.moduleNoId = moduleNoId;
		this.defaultFlag = defaultFlag;
	}

	public Integer getClasstypeId() {
		return classtypeId;
	}

	public void setClasstypeId(Integer classtypeId) {
		this.classtypeId = classtypeId;
	}

	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public Integer getModuleNoId() {
		return moduleNoId;
	}

	public void setModuleNoId(Integer moduleNoId) {
		this.moduleNoId = moduleNoId;
	}

	public Integer getDefaultFlag() {
		return defaultFlag;
	}

	public void setDefaultFlag(Integer defaultFlag) {
		this.defaultFlag = defaultFlag;
	}

	
	
}
