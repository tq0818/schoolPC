package com.yuxin.wx.model.system;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:SysCcAccount
 * 
 * @author wang.zx
 * @date 2015-6-17
 */
@SuppressWarnings("serial")
public class SysCcAccount extends BaseEntity {
	
	
	private String	ccApiKey;		
	private String	ccUserId;		
	private Integer	useStatus;		 /* 使用状态（1：已使用；0：未使用） */ 

	// Constructor
	public SysCcAccount() {
	}
	
	/**
	 * full Constructor
	 */
	public SysCcAccount(Integer id, String ccApiKey, String ccUserId, Integer useStatus) {
		setId(id);
		this.ccApiKey = ccApiKey;
		this.ccUserId = ccUserId;
		this.useStatus = useStatus;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为SysCcAccount可以实现连缀设置属性
	
	public String getCcApiKey() {
		return ccApiKey;
	}

	public SysCcAccount setCcApiKey(String ccApiKey) {
		this.ccApiKey = ccApiKey;
		return this;
	}
	
	
	public String getCcUserId() {
		return ccUserId;
	}

	public SysCcAccount setCcUserId(String ccUserId) {
		this.ccUserId = ccUserId;
		return this;
	}
	
	
	public Integer getUseStatus() {
		return useStatus;
	}

	public SysCcAccount setUseStatus(Integer useStatus) {
		this.useStatus = useStatus;
		return this;
	}
	
	@Override
	public String toString() {
		return "SysCcAccount [" + "id=" + getId() + ", ccApiKey=" + ccApiKey + ", ccUserId=" + ccUserId + ", useStatus=" + useStatus +  "]";
	}
}
