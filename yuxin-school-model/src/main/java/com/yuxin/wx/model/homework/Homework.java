package com.yuxin.wx.model.homework;


import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.util.LongDateSerializer;

/**
 * POJO:Homework
 * 
 * @author chopin
 * @date 2016-12-15
 */
@SuppressWarnings("serial")
public class Homework extends BaseEntity {
	
	
	private Integer	companyId;		
	private Integer	teacherId;		
	private Integer	type;		
	private String	desciption;		 /* 描述 */ 
	private Integer	resourceId;		
	private Integer	paperId;		
	private Integer	courseId;		 /* 课程ID */ 
	private Integer	lessonId;		 /* 直播课次ID */ 
	private Integer lectureId;			//视频课次id
	private String courseName;			/* 课程名*/
	private String lessonName;		/*课次名*/
	private Date createTime;
	private Integer status;			//启用状态
	
	private Integer lessonType;			//课次状态 1录播 2直播
	private String paperName;			//试卷名称
	private String fileName;			//附件名称
	
	
	public String getPaperName() {
		return paperName;
	}
	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	// Constructor
	public Homework() {
	}
	
	public Integer getLessonType() {
		return lessonType;
	}
	public void setLessonType(Integer lessonType) {
		this.lessonType = lessonType;
	}

	public Integer getLectureId() {
		return lectureId;
	}

	public void setLectureId(Integer lectureId) {
		this.lectureId = lectureId;
	}

	/**
	 * full Constructor
	 */
	public Homework(Integer id, Integer companyId, Integer teacherId, Integer type
			, String desciption, Integer resourceId, Integer paperId, Integer courseId
			, Integer lessonId,String courseName,String lessonName) {
		setId(id);
		this.companyId = companyId;
		this.teacherId = teacherId;
		this.type = type;
		this.desciption = desciption;
		this.resourceId = resourceId;
		this.paperId = paperId;
		this.courseId = courseId;
		this.lessonId = lessonId;
		this.courseName = courseName;
		this.lessonName = lessonName;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为Homework可以实现连缀设置属性
	
	

	public Integer getCompanyId() {
		return companyId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Homework setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public Integer getTeacherId() {
		return teacherId;
	}

	public Homework setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
		return this;
	}
	
	
	public Integer getType() {
		return type;
	}

	public Homework setType(Integer type) {
		this.type = type;
		return this;
	}
	
	
	public String getDesciption() {
		return desciption;
	}

	public Homework setDesciption(String desciption) {
		this.desciption = desciption;
		return this;
	}
	
	public Integer getResourceId() {
		return resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

	public Integer getPaperId() {
		return paperId;
	}

	public Homework setPaperId(Integer paperId) {
		this.paperId = paperId;
		return this;
	}
	
	
	public Integer getCourseId() {
		return courseId;
	}

	public Homework setCourseId(Integer courseId) {
		this.courseId = courseId;
		return this;
	}
	
	
	public Integer getLessonId() {
		return lessonId;
	}

	public Homework setLessonId(Integer lessonId) {
		this.lessonId = lessonId;
		return this;
	}
	
	@Override
	public String toString() {
		return "Homework [" + "id=" + getId() + ", companyId=" + companyId + ", teacherId=" + teacherId + ", type=" + type + ", desciption=" + desciption + ", resourceId=" + resourceId + ", paperId=" + paperId + ", courseId=" + courseId + ", lessonId=" + lessonId +  "]";
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getLessonName() {
		return lessonName;
	}

	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}
	
	@JsonSerialize(using = LongDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
