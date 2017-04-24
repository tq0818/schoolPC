package com.yuxin.wx.vo.classes;

import java.util.Date;

import com.yuxin.wx.common.BaseEntity;

public class ThreadModuleLessonVo extends BaseEntity {
	
	
	private String lessonName; /*课次名称*/
	private Date lessonDate; /* 上课日期*/
	private String lessonTimeStart; /* 开始 时间*/
	private String lessonTimeEnd; /* 结束时间*/
	private Integer moduleNoId; /* 班号*/
	
	private String name;	/* 班号 名称*/
	private Integer enrollYetStudents; /* 学员数量*/
	private Integer moduleId; /* 模块id*/
	private Date liveDate; /* 直播时间 */
	private Date liveEnd ; /* 直播结束*/
	private String timeLong; /* 时长*/
	private Integer teacherId; /* 教师 id*/
	
	private Integer schoolId; /* 分校id*/
	private Integer status; /* 状态*/
	private String currentTime; /* 当前 时分   ，判断 状态*/
	private String teacherUrlGh; /* 老师讲课地址*/
	private String replayUrlGh; /* 回看地址*/
	private String liveroomIdGh; /* 直播id*/
	private Integer userId; /* 用户id*/
	private Integer assistantId; /* 助教id*/
	private String teacherName;	/* 教室姓名*/
	private Integer classType; /*直播类型1:直播   2:公开课*/
	private String	liveCompanyType;		 /* 使用的直播公司类型（光慧：gh；展视：zs） */ 
	
	private Integer isday; /* 0:今天 ,1以前，2以后*/
	
	public String getLessonName() {
		return lessonName;
	}
	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}
	public Date getLessonDate() {
		return lessonDate;
	}
	public void setLessonDate(Date lessonDate) {
		this.lessonDate = lessonDate;
	}
	public String getLessonTimeStart() {
		return lessonTimeStart;
	}
	public void setLessonTimeStart(String lessonTimeStart) {
		this.lessonTimeStart = lessonTimeStart;
	}
	public String getLessonTimeEnd() {
		return lessonTimeEnd;
	}
	public void setLessonTimeEnd(String lessonTimeEnd) {
		this.lessonTimeEnd = lessonTimeEnd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getEnrollYetStudents() {
		return enrollYetStudents;
	}
	public void setEnrollYetStudents(Integer enrollYetStudents) {
		this.enrollYetStudents = enrollYetStudents;
	}
	public Integer getModuleId() {
		return moduleId;
	}
	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}
	public ThreadModuleLessonVo(String lessonName, Date lessonDate,
			String lessonTimeStart, String lessonTimeEnd, String name,
			Integer enrollYetStudents, Integer moduleId) {
		super();
		this.lessonName = lessonName;
		this.lessonDate = lessonDate;
		this.lessonTimeStart = lessonTimeStart;
		this.lessonTimeEnd = lessonTimeEnd;
		this.name = name;
		this.enrollYetStudents = enrollYetStudents;
		this.moduleId = moduleId;
	}
	public ThreadModuleLessonVo() {
		super();
	}
	public Date getLiveDate() {
		return liveDate;
	}
	public void setLiveDate(Date liveDate) {
		this.liveDate = liveDate;
	}
	public String getTimeLong() {
		return timeLong;
	}
	public void setTimeLong(String timeLong) {
		this.timeLong = timeLong;
	}
	public Date getLiveEnd() {
		return liveEnd;
	}
	public void setLiveEnd(Date liveEnd) {
		this.liveEnd = liveEnd;
	}
	public Integer getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getCurrentTime() {
		return currentTime;
	}
	public void setCurrentTime(String currentTime) {
		this.currentTime = currentTime;
	}
	public Integer getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	public String getTeacherUrlGh() {
		return teacherUrlGh;
	}
	public void setTeacherUrlGh(String teacherUrlGh) {
		this.teacherUrlGh = teacherUrlGh;
	}
	public String getReplayUrlGh() {
		return replayUrlGh;
	}
	public void setReplayUrlGh(String replayUrlGh) {
		this.replayUrlGh = replayUrlGh;
	}
	public String getLiveroomIdGh() {
		return liveroomIdGh;
	}
	public void setLiveroomIdGh(String liveroomIdGh) {
		this.liveroomIdGh = liveroomIdGh;
	}
	public Integer getModuleNoId() {
		return moduleNoId;
	}
	public void setModuleNoId(Integer moduleNoId) {
		this.moduleNoId = moduleNoId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getAssistantId() {
		return assistantId;
	}
	public void setAssistantId(Integer assistantId) {
		this.assistantId = assistantId;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public Integer getClassType() {
		return classType;
	}
	public void setClassType(Integer classType) {
		this.classType = classType;
	}
	public Integer getIsday() {
		return isday;
	}
	public void setIsday(Integer isday) {
		this.isday = isday;
	}
	public String getLiveCompanyType() {
		return liveCompanyType;
	}
	public void setLiveCompanyType(String liveCompanyType) {
		this.liveCompanyType = liveCompanyType;
	}
}
