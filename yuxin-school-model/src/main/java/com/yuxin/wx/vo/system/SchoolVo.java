package com.yuxin.wx.vo.system;

import java.util.Date;


public class SchoolVo {
	
	private String schoolName; /* 分校 名字 */
	private Date createTime; /* 建校时间*/
	private Integer senate; /* 教务人员数量*/
	private Integer teacher; /* 教师数量*/
	private Integer project; /* 项目数量*/
	private Integer campus; /* 校区  数量*/
	private Integer classroom; /* 线下教室数量*/
	
	
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getSenate() {
		return senate;
	}
	public void setSenate(Integer senate) {
		this.senate = senate;
	}
	public Integer getTeacher() {
		return teacher;
	}
	public void setTeacher(Integer teacher) {
		this.teacher = teacher;
	}
	public Integer getProject() {
		return project;
	}
	public void setProject(Integer project) {
		this.project = project;
	}
	public Integer getCampus() {
		return campus;
	}
	public void setCampus(Integer campus) {
		this.campus = campus;
	}
	public Integer getClassroom() {
		return classroom;
	}
	public void setClassroom(Integer classroom) {
		this.classroom = classroom;
	}
	
	
	public SchoolVo() {
		super();
	}
	public SchoolVo(String schoolName, Date createTime, Integer senate,
			Integer teacher, Integer project, Integer campus, Integer classroom) {
		super();
		this.schoolName = schoolName;
		this.createTime = createTime;
		this.senate = senate;
		this.teacher = teacher;
		this.project = project;
		this.campus = campus;
		this.classroom = classroom;
	}
	
}
