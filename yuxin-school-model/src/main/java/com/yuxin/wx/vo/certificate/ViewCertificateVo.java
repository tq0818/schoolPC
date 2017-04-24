package com.yuxin.wx.vo.certificate;

import java.util.Date;

import com.yuxin.wx.common.BaseEntity;

public class ViewCertificateVo extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 13231L;

	private Integer cerId;
	private Integer status;
	private Integer releaseStatus;
	private Integer stuId;
	private String stuName;
	private String mobile;
	private String username;
	private String startTime;
	private String endTime;
	private String cerName;
	private Integer courseId;
	private String receiveTime;
	private String cerStatus;
	private String releaseStatusStr;
	private Integer userId;
	private Integer companyId;
	
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getReleaseStatusStr() {
		return releaseStatusStr;
	}
	public void setReleaseStatusStr(String releaseStatusStr) {
		this.releaseStatusStr = releaseStatusStr;
	}
	
	public String getCerStatus() {
		return cerStatus;
	}
	public void setCerStatus(String cerStatus) {
		this.cerStatus = cerStatus;
	}
	
	
	public String getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}
	public Integer getStuId() {
		return stuId;
	}
	
	public void setStuId(Integer stuId) {
		this.stuId = stuId;
	}
	
	
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public String getCerName() {
		return cerName;
	}
	public void setCerName(String cerName) {
		this.cerName = cerName;
	}
	public Integer getCerId() {
		return cerId;
	}
	public void setCerId(Integer cerId) {
		this.cerId = cerId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getReleaseStatus() {
		return releaseStatus;
	}
	public void setReleaseStatus(Integer releaseStatus) {
		this.releaseStatus = releaseStatus;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
