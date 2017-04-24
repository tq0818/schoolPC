package com.yuxin.wx.model.system;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:SysLogManagerOptionDict
 * 
 * @author chopin
 * @date 2017-3-13
 */
@SuppressWarnings("serial")
public class SysLogManagerOptionDict extends BaseEntity {
	
	
	private String	action;		 /* 动作 */ 
	private String	operation;		 /* 操作 */ 
	private String	flag;		 /* 新增：add  修改：modify  删除 ：del */ 
	private Integer	monitoringTag;		 /* 监控标记 */ 

	// Constructor
	public SysLogManagerOptionDict() {
	}
	
	/**
	 * full Constructor
	 */
	public SysLogManagerOptionDict(Integer id, String action, String operation, String flag, Integer monitoringTag) {
		setId(id);
		this.action = action;
		this.operation = operation;
		this.flag = flag;
		this.monitoringTag = monitoringTag;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为SysLogManagerOptionDict可以实现连缀设置属性
	
	public String getAction() {
		return action;
	}

	public SysLogManagerOptionDict setAction(String action) {
		this.action = action;
		return this;
	}
	
	
	public String getOperation() {
		return operation;
	}

	public SysLogManagerOptionDict setOperation(String operation) {
		this.operation = operation;
		return this;
	}
	
	
	public String getFlag() {
		return flag;
	}

	public SysLogManagerOptionDict setFlag(String flag) {
		this.flag = flag;
		return this;
	}
	
	
	public Integer getMonitoringTag() {
		return monitoringTag;
	}

	public SysLogManagerOptionDict setMonitoringTag(Integer monitoringTag) {
		this.monitoringTag = monitoringTag;
		return this;
	}
	
	@Override
	public String toString() {
		return "SysLogManagerOptionDict [" + "id=" + getId() + ", action=" + action + ", operation=" + operation + ", flag=" + flag + ", monitoringTag=" + monitoringTag +  "]";
	}
}
