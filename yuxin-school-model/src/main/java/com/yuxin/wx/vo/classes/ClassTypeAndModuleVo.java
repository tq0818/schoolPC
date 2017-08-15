package com.yuxin.wx.vo.classes;

import java.io.Serializable;


public class ClassTypeAndModuleVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9047775752596360415L;
	public String moduleName;
	public String teachMethod;
	public Integer totalClassHour;
	
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getTeachMethod() {
		return teachMethod;
	}
	public void setTeachMethod(String teachMethod) {
		this.teachMethod = teachMethod;
	}
	public Integer getTotalClassHour() {
		return totalClassHour;
	}
	public void setTotalClassHour(Integer totalClassHour) {
		this.totalClassHour = totalClassHour;
	}
	
}
