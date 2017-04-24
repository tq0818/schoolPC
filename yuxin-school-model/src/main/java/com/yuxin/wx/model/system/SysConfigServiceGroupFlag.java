package com.yuxin.wx.model.system;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:SysConfigServiceGroupFlag
 * 
 * @author chopin
 * @date 2017-3-24
 */
@SuppressWarnings("serial")
public class SysConfigServiceGroupFlag extends BaseEntity {
	
	
	private String	groupCode;		 /* 服务组编码 */ 
	private Integer	groupSequence;		 /* 服务组顺序，0代表不需要组合 */ 

	// Constructor
	public SysConfigServiceGroupFlag() {
	}
	
	/**
	 * full Constructor
	 */
	public SysConfigServiceGroupFlag(Integer id, String groupCode, Integer groupSequence) {
		setId(id);
		this.groupCode = groupCode;
		this.groupSequence = groupSequence;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为SysConfigServiceGroupFlag可以实现连缀设置属性
	
	public String getGroupCode() {
		return groupCode;
	}

	public SysConfigServiceGroupFlag setGroupCode(String groupCode) {
		this.groupCode = groupCode;
		return this;
	}
	
	
	public Integer getGroupSequence() {
		return groupSequence;
	}

	public SysConfigServiceGroupFlag setGroupSequence(Integer groupSequence) {
		this.groupSequence = groupSequence;
		return this;
	}
	
	@Override
	public String toString() {
		return "SysConfigServiceGroupFlag [" + "id=" + getId() + ", groupCode=" + groupCode + ", groupSequence=" + groupSequence +  "]";
	}
}
