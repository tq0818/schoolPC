package com.yuxin.wx.vo.user;

import com.yuxin.wx.common.BaseEntity;

import java.util.Date;

@SuppressWarnings("serial")
public class UsersAreaRelation extends BaseEntity{

	private Integer userId;			/* 用户id */
	private String	eduArea;		 /* 区域 */
	private String	eduSchool;		 /* 类型 */
	private Integer	userType;		/* 用户类型。1负责人 */
	private Date createTime;		 /* 创建时间 */

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEduArea() {
		return eduArea;
	}

	public void setEduArea(String eduArea) {
		this.eduArea = eduArea;
	}

	public String getEduSchool() {
		return eduSchool;
	}

	public void setEduSchool(String eduSchool) {
		this.eduSchool = eduSchool;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
