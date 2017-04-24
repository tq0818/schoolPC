package com.yuxin.wx.model.user;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:UsersFrontMyCoupons
 * 
 * @author wang.zx
 * @date 2016-6-21
 */
@SuppressWarnings("serial")
public class UsersFrontMyCoupons extends BaseEntity {
	
	
	private String	couponsCode;		 /* 优惠券码 */ 
	private Integer	status;		 /* 状态 */ 
	private Integer	userId;		 /* 用户ID */ 

	// Constructor
	public UsersFrontMyCoupons() {
	}
	
	/**
	 * full Constructor
	 */
	public UsersFrontMyCoupons(Integer id, String couponsCode, Integer status, Integer userId) {
		setId(id);
		this.couponsCode = couponsCode;
		this.status = status;
		this.userId = userId;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为UsersFrontMyCoupons可以实现连缀设置属性
	
	public String getCouponsCode() {
		return couponsCode;
	}

	public UsersFrontMyCoupons setCouponsCode(String couponsCode) {
		this.couponsCode = couponsCode;
		return this;
	}
	
	
	public Integer getStatus() {
		return status;
	}

	public UsersFrontMyCoupons setStatus(Integer status) {
		this.status = status;
		return this;
	}
	
	
	public Integer getUserId() {
		return userId;
	}

	public UsersFrontMyCoupons setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}
	
	@Override
	public String toString() {
		return "UsersFrontMyCoupons [" + "id=" + getId() + ", couponsCode=" + couponsCode + ", status=" + status + ", userId=" + userId +  "]";
	}
}
