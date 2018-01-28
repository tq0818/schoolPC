package com.yuxin.wx.model.user;

import java.util.Date;

public class UserLoginDayVO {
	
	private Integer id;
	//对应的用户id
	private Integer userId;
	//总共学习天数
	private Integer loginDay;
	//最近登录时间
	private	Date loginDate;
	//预留字段,删除标志,0删除,1是未删除
	private Integer delFlag;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getLoginDay() {
		return loginDay;
	}
	public void setLoginDay(Integer loginDay) {
		this.loginDay = loginDay;
	}
	public Date getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	
	
}
