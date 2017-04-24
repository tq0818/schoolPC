package com.yuxin.wx.vo.user;


public class UsersDetailVo {
	
	private Integer stuId;
	private String stuName;
	private String	username;		 /* 用户名 */ 
	private String	headPicMax;		 /* 用户大头像地址 */ 
	private String nickName;			/*昵称*/
	private String showFlag;
	private String mobile;
	
	public Integer getStuId() {
		return stuId;
	}
	public void setStuId(Integer stuId) {
		this.stuId = stuId;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getHeadPicMax() {
		return headPicMax;
	}
	public void setHeadPicMax(String headPicMax) {
		this.headPicMax = headPicMax;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getShowFlag() {
		return showFlag;
	}
	public void setShowFlag(String showFlag) {
		this.showFlag = showFlag;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
}
