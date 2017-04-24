package com.yuxin.wx.model.company;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:OrganLeaveMessageBlacklist
 * 
 * @author chopin
 * @date 2016-8-5
 */
@SuppressWarnings("serial")
public class OrganLeaveMessageBlacklist extends BaseEntity {
	
	
	private String	ip;		

	// Constructor
	public OrganLeaveMessageBlacklist() {
	}
	
	/**
	 * full Constructor
	 */
	public OrganLeaveMessageBlacklist(Integer id, String ip) {
		setId(id);
		this.ip = ip;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为OrganLeaveMessageBlacklist可以实现连缀设置属性
	
	public String getIp() {
		return ip;
	}

	public OrganLeaveMessageBlacklist setIp(String ip) {
		this.ip = ip;
		return this;
	}
	
	@Override
	public String toString() {
		return "OrganLeaveMessageBlacklist [" + "id=" + getId() + ", ip=" + ip +  "]";
	}
}
