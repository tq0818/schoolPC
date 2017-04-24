package com.yuxin.wx.common;


import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import net.sf.json.JSONObject;

public class ViewResult {
	
	public Boolean result; /*请求是否发生异常 true：无异常  false：有异常*/
	public String message; /*消息*/
	public String data;    /*json数据*/
	public String sessionId; /*当前session的sessionId*/
	
	public ViewResult() {
		this.result=true;
		this.message="";
	}
	
	public ViewResult(Boolean result, String message, String data, String sessionId) {
		super();
		this.result = result;
		this.message = message;
		this.data = data;
		this.sessionId = sessionId;
	}
	
	public ViewResult(Boolean result, String message) {
		super();
		this.result = result;
		this.message = message;
	}

	public Boolean getResult() {
		return result;
	}
	public void setResult(Boolean result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getSessionId() {
		if(StringUtils.isBlank(sessionId)){
			try{
				Subject subject= SecurityUtils.getSubject();
				sessionId=""+subject.getSession().getId();
			}catch(Exception igone){
				sessionId=null;
			}
		}
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	public String toString(){
		return JSONObject.fromObject(this).toString();
	}

}
