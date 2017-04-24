package com.yuxin.wx.vo.user;

import java.util.Date;

import com.yuxin.wx.common.BaseEntity;

@SuppressWarnings("serial")
public class UsersStudentInfo extends BaseEntity {
	
	private String mobile;
	private String stuname;
	private String nickName;
	private Date memberEndTime;
	
	public UsersStudentInfo() {
		super();
	}
	public UsersStudentInfo(String mobile, String stuname, String nickName,
			Date memberEndTime) {
		super();
		this.mobile = mobile;
		this.stuname = stuname;
		this.nickName = nickName;
		this.memberEndTime = memberEndTime;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getStuname() {
		return stuname;
	}
	public void setStuname(String stuname) {
		this.stuname = stuname;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Date getMemberEndTime() {
		return memberEndTime;
	}
	public void setMemberEndTime(Date memberEndTime) {
		this.memberEndTime = memberEndTime;
	}
}
