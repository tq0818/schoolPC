package com.yuxin.wx.model.certificate;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CertificateCourseRelation
 * 
 * @author chopin
 * @date 2016-9-22
 */
@SuppressWarnings("serial")
public class CertificateCourseRelation extends BaseEntity {
	
	
	private Integer	cerId;		
	private Integer	courseId;		 /* 课程ID class_type_id */ 
	private String courseName;

	

	// Constructor
	public CertificateCourseRelation() {
	}
	
	/**
	 * full Constructor
	 */
	public CertificateCourseRelation(Integer id, Integer cerId, Integer courseId) {
		setId(id);
		this.cerId = cerId;
		this.courseId = courseId;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CertificateCourseRelation可以实现连缀设置属性
	
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	
	public Integer getCerId() {
		return cerId;
	}

	public CertificateCourseRelation setCerId(Integer cerId) {
		this.cerId = cerId;
		return this;
	}
	
	
	public Integer getCourseId() {
		return courseId;
	}

	public CertificateCourseRelation setCourseId(Integer courseId) {
		this.courseId = courseId;
		return this;
	}
	
	@Override
	public String toString() {
		return "CertificateCourseRelation [" + "id=" + getId() + ", cerId=" + cerId + ", courseId=" + courseId +  "]";
	}
}
