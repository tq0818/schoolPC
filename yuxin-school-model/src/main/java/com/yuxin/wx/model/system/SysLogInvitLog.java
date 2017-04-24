package com.yuxin.wx.model.system;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:SysLogInvitLog
 * 
 * @author chopin
 * @date 2016-7-29
 */
@SuppressWarnings("serial")
public class SysLogInvitLog extends BaseEntity {
	
	
	private String	inviteCode;		
	private Date	requestTime;		 /* 访问时间 */ 
	private String	ip;		

	// Constructor
	public SysLogInvitLog() {
	}
	
	/**
	 * full Constructor
	 */
	public SysLogInvitLog(Integer id, String inviteCode, Date requestTime, String ip) {
		setId(id);
		this.inviteCode = inviteCode;
		this.requestTime = requestTime;
		this.ip = ip;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为SysLogInvitLog可以实现连缀设置属性
	
	public String getInviteCode() {
		return inviteCode;
	}

	public SysLogInvitLog setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getRequestTime() {
		return requestTime;
	}

	public SysLogInvitLog setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
		return this;
	}
	
	
	public String getIp() {
		return ip;
	}

	public SysLogInvitLog setIp(String ip) {
		this.ip = ip;
		return this;
	}
	
	@Override
	public String toString() {
		return "SysLogInvitLog [" + "id=" + getId() + ", inviteCode=" + inviteCode + ", requestTime=" + requestTime + ", ip=" + ip +  "]";
	}
}
