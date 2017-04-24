package com.yuxin.wx.vo.common.email;

public class ChangeAuthCodeYunduo extends MailBaseModel{
	private String userName;
	private String authCode;
	private String sendDate;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAuthCode() {
		return authCode;
	}
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	public String getSendDate() {
		return sendDate;
	}
	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

}
