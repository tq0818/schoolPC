package com.yuxin.wx.model.user;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.util.ShortDateSerializer;
import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:UsersLoginSession
 * 
 * @author chopin
 * @date 2015-7-27
 */
@SuppressWarnings("serial")
public class UsersLoginSession extends BaseEntity {
	
	
	private String	userId;		 /* 用户ID */ 
	private String	sessionId;		 /* sessionId */ 
	private Date	loginTime;		 /* 登录时间 */ 
	private Date	logoutTime;		 /* 退出时间 */ 
	private Integer	status;		 /* 当前状态:  1-在线  2-离线 */ 
	private String	ip;		 /* 用户ip地址 */ 
	private String	macAddress;		 /* 用户mac地址 */ 
	private String	address;		 /* 实际地址 */
	private String isp;
	private Integer companyId;

	// Constructor
	public UsersLoginSession() {
	}
	
	/**
	 * full Constructor
	 */
	public UsersLoginSession(Integer id, String userId, String sessionId, Date loginTime, Date logoutTime, Integer status, String ip, String macAddress, String address) {
		setId(id);
		this.userId = userId;
		this.sessionId = sessionId;
		this.loginTime = loginTime;
		this.logoutTime = logoutTime;
		this.status = status;
		this.ip = ip;
		this.macAddress = macAddress;
		this.address = address;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为UsersLoginSession可以实现连缀设置属性
	
	public String getUserId() {
		return userId;
	}

	public UsersLoginSession setUserId(String userId) {
		this.userId = userId;
		return this;
	}
	
	
	public String getSessionId() {
		return sessionId;
	}

	public UsersLoginSession setSessionId(String sessionId) {
		this.sessionId = sessionId;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getLoginTime() {
		return loginTime;
	}

	public UsersLoginSession setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getLogoutTime() {
		return logoutTime;
	}

	public UsersLoginSession setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
		return this;
	}
	
	
	public Integer getStatus() {
		return status;
	}

	public UsersLoginSession setStatus(Integer status) {
		this.status = status;
		return this;
	}
	
	
	public String getIp() {
		return ip;
	}

	public UsersLoginSession setIp(String ip) {
		this.ip = ip;
		return this;
	}
	
	
	public String getMacAddress() {
		return macAddress;
	}

	public UsersLoginSession setMacAddress(String macAddress) {
		this.macAddress = macAddress;
		return this;
	}
	
	
	public String getAddress() {
		return address;
	}

	public UsersLoginSession setAddress(String address) {
		this.address = address;
		return this;
	}
	
	
	public String getIsp() {
		return isp;
	}

	public void setIsp(String isp) {
		this.isp = isp;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	@Override
	public String toString() {
		return "UsersLoginSession [" + "id=" + getId() + ", userId=" + userId + ", sessionId=" + sessionId + ", loginTime=" + loginTime + ", logoutTime=" + logoutTime + ", status=" + status + ", ip=" + ip + ", macAddress=" + macAddress + ", address=" + address +  "]";
	}
}
