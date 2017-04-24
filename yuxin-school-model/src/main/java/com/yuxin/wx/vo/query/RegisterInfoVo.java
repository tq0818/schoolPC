package com.yuxin.wx.vo.query;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.util.LongDateSerializer;
import com.yuxin.wx.util.ShortDateSerializer;

public class RegisterInfoVo extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String utmUrl;
	private String mobile;
	private Date registTime;
	
	private String companyName;
	private String userName;
	
	private Integer timeLen;
	private String marks;
	
	private String startTime;
	private String endTime;
	
	public String getUtmUrl() {
		return utmUrl;
	}
	public void setUtmUrl(String utmUrl) {
		this.utmUrl = utmUrl;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@JsonSerialize(using = LongDateSerializer.class)
	public Date getRegistTime() {
		return registTime;
	}
	public void setRegistTime(Date registTime) {
		this.registTime = registTime;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
