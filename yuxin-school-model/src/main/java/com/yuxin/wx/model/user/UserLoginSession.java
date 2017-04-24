package com.yuxin.wx.model.user;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.util.ShortDateSerializer;
import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:Users
 * 
 * @author zhang.zx
 * @date 2015-5-9
 */
@SuppressWarnings("serial")
public class UserLoginSession extends BaseEntity {
	
	private String userId;  
	private String sessionId;
	private Date loginTime;
	private Date logoutTime;
	private Integer status;
	private String ip;
	private String macAddress;
	private String address;
	private String isp;
	private Integer loginNum;
	private Integer timeLen;
	private String marks;
	
	private String startTime;
	private String endTime;
	
	public UserLoginSession() {
	
	}
	
	public UserLoginSession(String userId, String sessionId, Date loginTime,
			Date logoutTime, Integer status, String ip, String macAddress,
			String address, String isp) {
	
		this.userId = userId;
		this.sessionId = sessionId;
		this.loginTime = loginTime;
		this.logoutTime = logoutTime;
		this.status = status;
		this.ip = ip;
		this.macAddress = macAddress;
		this.address = address;
		this.isp = isp;
	}


	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getLogoutTime() {
		return logoutTime;
	}
	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getMacAddress() {
		return macAddress;
	}
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getIsp() {
		return isp;
	}
	public void setIsp(String isp) {
		this.isp = isp;
	}


	public Integer getLoginNum() {
		return loginNum;
	}


	public void setLoginNum(Integer loginNum) {
		this.loginNum = loginNum;
	}

	public Integer getTimeLen() {
		return timeLen;
	}

	public void setTimeLen(Integer timeLen) {
		this.timeLen = timeLen;
	}

	public String getMarks() {
		return marks;
	}

	public void setMarks(String marks) {
		this.marks = marks;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
}
