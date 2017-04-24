package com.yuxin.wx.vo.weichat;

public class QQAccessToken {
	private String access_token;
	private int expires_in;
	private String refresh_token;
	
	private String code;
	private String msg;
	public QQAccessToken(String access_token, int expires_in, String refresh_token, String code, String msg) {
		super();
		this.access_token = access_token;
		this.expires_in = expires_in;
		this.refresh_token = refresh_token;
		this.code = code;
		this.msg = msg;
	}
	public QQAccessToken() {
		super();
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public int getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}
	public String getRefresh_token() {
		return refresh_token;
	}
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	@Override
	public String toString() {
		return "QQAccessToken [access_token=" + access_token + ", expires_in=" + expires_in + ", refresh_token="
				+ refresh_token + ", code=" + code + ", msg=" + msg + "]";
	}
	
}
