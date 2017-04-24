package com.yuxin.wx.vo.system;

/**
 * @ClassName: ConfigTableVo
 * @Description:(用于户表结构是否必填字段)
 * @author wang.zx 
 * @date 2014-12-9 下午4:31:57
 * @version 1.0
 */
public class ConfigTableVo {
	public boolean isRequired;
	
	public boolean isSystem;

	public boolean isRequired() {
		return isRequired;
	}

	public void setRequired(boolean isRequired) {
		this.isRequired = isRequired;
	}

	public boolean isSystem() {
		return isSystem;
	}

	public void setSystem(boolean isSystem) {
		this.isSystem = isSystem;
	}
	
}
