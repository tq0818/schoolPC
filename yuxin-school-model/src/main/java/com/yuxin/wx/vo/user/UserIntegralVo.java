package com.yuxin.wx.vo.user;

import com.yuxin.wx.common.BaseEntity;

@SuppressWarnings("serial")
public class UserIntegralVo extends BaseEntity {
	
	private Integer daytime; 	/* 日期*/
	private Integer integral;	/* 累计积分*/
	
	public UserIntegralVo() {
		super();
	}
	public UserIntegralVo(Integer daytime, Integer integral) {
		super();
		this.daytime = daytime;
		this.integral = integral;
	}
	public Integer getDaytime() {
		return daytime;
	}
	public void setDaytime(Integer daytime) {
		this.daytime = daytime;
	}
	public Integer getIntegral() {
		return integral;
	}
	public void setIntegral(Integer integral) {
		this.integral = integral;
	}
}
