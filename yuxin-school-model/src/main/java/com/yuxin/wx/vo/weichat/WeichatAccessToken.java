package com.yuxin.wx.vo.weichat;

public class WeichatAccessToken {
	public String access_token;		//接口调用凭证
	public int expires_in;		//access_token接口调用凭证超时时间，单位（秒）
	public String refresh_token;//用户刷新access_token
	public String openid;	//授权用户唯一标识
	public String scope;	//用户授权的作用域，使用逗号（,）分隔
	public String unionid;//当且仅当该网站应用已获得该用户的userinfo授权时，才会出现该字段。
	
	public String errcode;
	public String errmsg;
	public WeichatAccessToken() {
		super();
	}
	public WeichatAccessToken(String access_token, int expires_in, String refresh_token, String openid, String scope,
			String unionid, String errcode, String errmsg) {
		super();
		this.access_token = access_token;
		this.expires_in = expires_in;
		this.refresh_token = refresh_token;
		this.openid = openid;
		this.scope = scope;
		this.unionid = unionid;
		this.errcode = errcode;
		this.errmsg = errmsg;
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
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	public String getErrcode() {
		return errcode;
	}
	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	@Override
	public String toString() {
		return "WeichatAccessToken [access_token=" + access_token + ", expires_in=" + expires_in + ", refresh_token="
				+ refresh_token + ", openid=" + openid + ", scope=" + scope + ", unionid=" + unionid + ", errcode="
				+ errcode + ", errmsg=" + errmsg + "]";
	}
	

	
}
