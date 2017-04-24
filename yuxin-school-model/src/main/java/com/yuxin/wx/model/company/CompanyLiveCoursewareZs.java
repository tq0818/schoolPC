package com.yuxin.wx.model.company;


import java.util.Date;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyLiveCoursewareZs
 * 
 * @author wang.zx
 * @date 2015-11-28
 */
@SuppressWarnings("serial")
public class CompanyLiveCoursewareZs extends BaseEntity {
	
	
	private String	coursewareId;		 /* 课件id */ 
	private String	coursewareNumber;		 /* 课件编号 */ 
	private String	url;		 /* 课件的url */ 
	private String	subject;		 /* 课件的名字 */ 
	private String	roomId;		 /* 所属课堂名称 */ 
	private String	screenshot;		 /* 文档的截屏 */ 
	private Long	recordId;		 /* 资源id */ 
	private String	description;		 /* 描述 */ 
	private String	token;		 /* 课件口令 */ 
	private Integer	moduleLessonId;		 /* 课次表id */ 
	private Integer	companyId;		 /* 公司id */ 
	private Integer	schoolId;		 /* 分校id */ 
	private Date	addDate;		/* 哪天的课程*/

	// Constructor
	public CompanyLiveCoursewareZs() {
	}
	
	/**
	 * full Constructor
	 */
	public CompanyLiveCoursewareZs(Integer id, String coursewareId, String coursewareNumber, String url, String subject, String roomId, String screenshot, Long recordId, String description, String token, Integer moduleLessonId, Integer companyId, Integer schoolId, Date addDate) {
		setId(id);
		this.coursewareId = coursewareId;
		this.coursewareNumber = coursewareNumber;
		this.url = url;
		this.subject = subject;
		this.roomId = roomId;
		this.screenshot = screenshot;
		this.recordId = recordId;
		this.description = description;
		this.token = token;
		this.moduleLessonId = moduleLessonId;
		this.companyId = companyId;
		this.schoolId = schoolId;
		this.addDate = addDate;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CompanyLiveCoursewareZs可以实现连缀设置属性
	
	public String getCoursewareId() {
		return coursewareId;
	}

	public CompanyLiveCoursewareZs setCoursewareId(String coursewareId) {
		this.coursewareId = coursewareId;
		return this;
	}
	
	
	public String getCoursewareNumber() {
		return coursewareNumber;
	}

	public CompanyLiveCoursewareZs setCoursewareNumber(String coursewareNumber) {
		this.coursewareNumber = coursewareNumber;
		return this;
	}
	
	
	public String getUrl() {
		return url;
	}

	public CompanyLiveCoursewareZs setUrl(String url) {
		this.url = url;
		return this;
	}
	
	
	public String getSubject() {
		return subject;
	}

	public CompanyLiveCoursewareZs setSubject(String subject) {
		this.subject = subject;
		return this;
	}
	
	
	public String getRoomId() {
		return roomId;
	}

	public CompanyLiveCoursewareZs setRoomId(String roomId) {
		this.roomId = roomId;
		return this;
	}
	
	
	public String getScreenshot() {
		return screenshot;
	}

	public CompanyLiveCoursewareZs setScreenshot(String screenshot) {
		this.screenshot = screenshot;
		return this;
	}
	
	
	public Long getRecordId() {
		return recordId;
	}

	public CompanyLiveCoursewareZs setRecordId(Long recordId) {
		this.recordId = recordId;
		return this;
	}
	
	
	public String getDescription() {
		return description;
	}

	public CompanyLiveCoursewareZs setDescription(String description) {
		this.description = description;
		return this;
	}
	
	
	public String getToken() {
		return token;
	}

	public CompanyLiveCoursewareZs setToken(String token) {
		this.token = token;
		return this;
	}
	
	
	public Integer getModuleLessonId() {
		return moduleLessonId;
	}

	public CompanyLiveCoursewareZs setModuleLessonId(Integer moduleLessonId) {
		this.moduleLessonId = moduleLessonId;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public CompanyLiveCoursewareZs setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public Integer getSchoolId() {
		return schoolId;
	}

	public CompanyLiveCoursewareZs setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
		return this;
	}
	
	@Override
	public String toString() {
		return "CompanyLiveCoursewareZs [" + "id=" + getId() + ", coursewareId=" + coursewareId + ", coursewareNumber=" + coursewareNumber + ", url=" + url + ", subject=" + subject + ", roomId=" + roomId + ", screenshot=" + screenshot + ", recordId=" + recordId + ", description=" + description + ", token=" + token + ", moduleLessonId=" + moduleLessonId + ", companyId=" + companyId + ", schoolId=" + schoolId +  "]";
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
}
