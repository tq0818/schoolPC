package com.yuxin.wx.vo.user;

public class ThirdCreatAccOrBindAcc {
	private String username;
	private String mobile;
	private Integer smsCode;
	private String password;
	private String type;     	//1生成账号 2绑定已有账号
	private String platform;    //第三方平台
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getSmsCode() {
		return smsCode;
	}
	public void setSmsCode(Integer smsCode) {
		this.smsCode = smsCode;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	
}
