package com.yuxin.wx.model.user;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:UsersThirdLoginRelationship
 * 
 * @author chopin
 * @date 2016-7-6
 */
@SuppressWarnings("serial")
public class UsersThirdLoginRelationship extends BaseEntity {
	
	
	private Integer	userId;		
	private String	userCode;		 /* 用户唯一标记 */ 
	private String	platform;		 /* 平台：qq、wechat、weibo */ 
	private Integer companyId;

	// Constructor
	public UsersThirdLoginRelationship() {
	}
	
	/**
	 * full Constructor
	 */
	
	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为UsersThirdLoginRelationship可以实现连缀设置属性
	
	public Integer getUserId() {
		return userId;
	}

	public UsersThirdLoginRelationship(Integer userId, String userCode, String platform, Integer companyId) {
		super();
		this.userId = userId;
		this.userCode = userCode;
		this.platform = platform;
		this.companyId = companyId;
	}

	public UsersThirdLoginRelationship setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}
	
	
	public String getUserCode() {
		return userCode;
	}

	public UsersThirdLoginRelationship setUserCode(String userCode) {
		this.userCode = userCode;
		return this;
	}
	
	
	public String getPlatform() {
		return platform;
	}

	public UsersThirdLoginRelationship setPlatform(String platform) {
		this.platform = platform;
		return this;
	}
	
	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	@Override
	public String toString() {
		return "UsersThirdLoginRelationship [" + "id=" + getId() + ", userId=" + userId + ", userCode=" + userCode + ", platform=" + platform +  "]";
	}
}
