package com.yuxin.wx.vo.homework;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.util.LongDateSerializer;

public class StudentHomeWorkVo extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	//student users_front
	private Integer id;					/* student id */
	private String mobile;	
	private String name;
	private String username;
	private Integer userId;
	//homework student
	private Integer hscId;
	private Integer homeworkId;
	private String content;
	private Integer status;
	private String stuResourceId;
	private Integer completeFlag;
	private Date completeTime;
	private Integer teacherId;
	private Integer type;
	private String desciption;
	private String hwResourceId;
	private Integer paperId;
	private Integer courseId;
	private Integer lessonId;
	private String courseName;
	private String lessonName;
	private Integer companyId;
	private Integer groupOneId;
	private Integer groupTwoId;
	
	//homework teacher
	private String score;
	private Date readTime;
	
	//统计
	private Integer studentNum;
	private Integer alreadyCommitNum;
	private Integer alreadyReadNum;
	private String studentHomeworkListStatus;
	
	//查询
	
	private Integer resourceId;
	private Integer moduleId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getHomeworkId() {
		return homeworkId;
	}
	public void setHomeworkId(Integer homeworkId) {
		this.homeworkId = homeworkId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getCompleteFlag() {
		return completeFlag;
	}
	public void setCompleteFlag(Integer completeFlag) {
		this.completeFlag = completeFlag;
	}
	@JsonSerialize(using = LongDateSerializer.class)
	public Date getCompleteTime() {
		return completeTime;
	}
	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}
	public Integer getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getDesciption() {
		return desciption;
	}
	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}
	public Integer getPaperId() {
		return paperId;
	}
	public void setPaperId(Integer paperId) {
		this.paperId = paperId;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public Integer getLessonId() {
		return lessonId;
	}
	public void setLessonId(Integer lessonId) {
		this.lessonId = lessonId;
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
	
	@Override
	public String toString() {
		return "StudentHomeWorkVo [id=" + id + ", mobile=" + mobile + ", name=" + name + ", username=" + username
				+ ", userId=" + userId + ", homeworkId=" + homeworkId + ", content=" + content + ", status=" + status
				+ ", stuResourceId=" + stuResourceId + ", score=" + score + ", completeFlag=" + completeFlag
				+ ", completeTime=" + completeTime + ", teacherId=" + teacherId + ", type=" + type + ", desciption="
				+ desciption + ", hwResourceId=" + hwResourceId + ", paperId=" + paperId + ", courseId=" + courseId
				+ ", lessonId=" + lessonId + ", courseName=" + courseName + ", lessonName=" + lessonName + "]";
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public Integer getStudentNum() {
		return studentNum;
	}
	public void setStudentNum(Integer studentNum) {
		this.studentNum = studentNum;
	}
	public Integer getAlreadyCommitNum() {
		return alreadyCommitNum;
	}
	public void setAlreadyCommitNum(Integer alreadyCommitNum) {
		this.alreadyCommitNum = alreadyCommitNum;
	}
	public Integer getAlreadyReadNum() {
		return alreadyReadNum;
	}
	public void setAlreadyReadNum(Integer alreadyReadNum) {
		this.alreadyReadNum = alreadyReadNum;
	}
	public String getStudentHomeworkListStatus() {
		return studentHomeworkListStatus;
	}
	public void setStudentHomeworkListStatus(String studentHomeworkListStatus) {
		this.studentHomeworkListStatus = studentHomeworkListStatus;
	}
	@JsonSerialize(using = LongDateSerializer.class)
	public Date getReadTime() {
		return readTime;
	}
	public void setReadTime(Date readTime) {
		this.readTime = readTime;
	}
	public String getStuResourceId() {
		return stuResourceId;
	}
	public void setStuResourceId(String stuResourceId) {
		this.stuResourceId = stuResourceId;
	}
	public String getHwResourceId() {
		return hwResourceId;
	}
	public void setHwResourceId(String hwResourceId) {
		this.hwResourceId = hwResourceId;
	}
	public Integer getHscId() {
		return hscId;
	}
	public void setHscId(Integer hscId) {
		this.hscId = hscId;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public Integer getResourceId() {
		return resourceId;
	}
	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}
	public Integer getModuleId() {
		return moduleId;
	}
	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}
	public Integer getGroupOneId() {
		return groupOneId;
	}
	public void setGroupOneId(Integer groupOneId) {
		this.groupOneId = groupOneId;
	}
	public Integer getGroupTwoId() {
		return groupTwoId;
	}
	public void setGroupTwoId(Integer groupTwoId) {
		this.groupTwoId = groupTwoId;
	}
	

}
