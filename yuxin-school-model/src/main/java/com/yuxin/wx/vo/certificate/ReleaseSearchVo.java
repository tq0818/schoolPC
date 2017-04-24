package com.yuxin.wx.vo.certificate;

import java.util.Date;

import com.yuxin.wx.common.BaseEntity;

public class ReleaseSearchVo extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3231l;
	private Integer cerId;
	private Integer courseId;
	private Integer studyStatus;
	private Integer releaseStatus;
	private String stuName;
	private String mobile;
	private String username;
	private String startTime;
	private String endTime;
	private Integer companyId;
	
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public Integer getCerId() {
		return cerId;
	}
	public void setCerId(Integer cerId) {
		this.cerId = cerId;
	}
	
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public Integer getStudyStatus() {
		return studyStatus;
	}
	public void setStudyStatus(Integer studyStatus) {
		this.studyStatus = studyStatus;
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
	
	
	
}
