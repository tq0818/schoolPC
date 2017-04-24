package com.yuxin.wx.vo.certificate;

import java.util.Date;

import com.yuxin.wx.common.BaseEntity;

public class CertificateUserCourseVo extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer courseId;
	private Integer stuId;
	private String stuName;
	private String mobile;
	private String username;
	private Integer totalLectureNum;
	private Integer passLectureNum;
	private Integer userNum;
	private String receiveTime;
	private String courseName;
	private Integer cerId;
	private Integer isRelease;
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
	public Integer getIsRelease() {
		return isRelease;
	}
	public void setIsRelease(Integer isRelease) {
		this.isRelease = isRelease;
	}
	public Integer getCerId() {
		return cerId;
	}
	public void setCerId(Integer cerId) {
		this.cerId = cerId;
	}
	
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public Integer getStuId() {
		return stuId;
	}
	public void setStuId(Integer stuId) {
		this.stuId = stuId;
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
	public Integer getTotalLectureNum() {
		return totalLectureNum;
	}
	public void setTotalLectureNum(Integer totalLectureNum) {
		this.totalLectureNum = totalLectureNum;
	}
	public Integer getPassLectureNum() {
		return passLectureNum;
	}
	public void setPassLectureNum(Integer passLectureNum) {
		this.passLectureNum = passLectureNum;
	}
	public Integer getUserNum() {
		return userNum;
	}
	public void setUserNum(Integer userNum) {
		this.userNum = userNum;
	}
	
	public String getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
