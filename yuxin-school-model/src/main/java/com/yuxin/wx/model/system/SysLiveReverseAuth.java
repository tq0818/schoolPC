package com.yuxin.wx.model.system;


import java.util.Date;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:SysLiveReverseAuth
 * 
 * @author wang.zx
 * @date 2015-11-6
 */
@SuppressWarnings("serial")
public class SysLiveReverseAuth extends BaseEntity {
	
	
	private String	authKey;		
	private Integer	authCount;
	private Date createTime;

	// Constructor
	public SysLiveReverseAuth() {
	}
	
	/**
	 * full Constructor
	 */
	public SysLiveReverseAuth(Integer id, String authKey, Integer authCount, Date createTime) {
		setId(id);
		this.authKey = authKey;
		this.authCount = authCount;
		this.createTime = createTime;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为SysLiveReverseAuth可以实现连缀设置属性
	
	public String getAuthKey() {
		return authKey;
	}

	public SysLiveReverseAuth setAuthKey(String authKey) {
		this.authKey = authKey;
		return this;
	}
	
	
	public Integer getAuthCount() {
		return authCount;
	}

	public SysLiveReverseAuth setAuthCount(Integer authCount) {
		this.authCount = authCount;
		return this;
	}
	
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "SysLiveReverseAuth [" + "id=" + getId() + ", authKey=" + authKey + ", authCount=" + authCount + ", createTime=" + createTime + "]";
	}
}
