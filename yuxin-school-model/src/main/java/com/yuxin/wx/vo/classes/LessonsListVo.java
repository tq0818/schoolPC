package com.yuxin.wx.vo.classes;


public class LessonsListVo {
	
	private String startDate;   	 //开班日期
	private String workDay;		 //工作日期
	private String weekType;		 //星期几,根据不同的班型放入不同的值  
	private String scope;		 //开课时段	
	private String lessonTimeStart;		 //开始时间
	private String lessonTimeEnd;          //结束时间
	private String lessonName;		 //课次 ( 模块名称 + 排序号 )
	private Double	lessonHour;		 /* 每课次课时 */ 
	private Integer totalCount;		 /* 一共分为多少课次 */
	private String	teachers;		 /* 老师，存老师的id，可以有多个老师，之间用逗号分隔 */ 
	private String	classRoom;		 /* 上课教室，授课方式为面授时使用该字段 */ 
	private String	masters;		 /* 班主任，存班主任的id，可以有多个班主任，之间用逗号分隔 */ 
	private String	assistants;		 /* 助教，存助教的id，可以有多个助教，之间用逗号分隔 */
	
	
	public String getWeekType() {
		return weekType;
	}
	public void setWeekType(String weekType) {
		this.weekType = weekType;
	}
	public Double getLessonHour() {
		return lessonHour;
	}
	public void setLessonHour(Double lessonHour) {
		this.lessonHour = lessonHour;
	}
	public String getTeachers() {
		return teachers;
	}
	public void setTeachers(String teachers) {
		this.teachers = teachers;
	}
	public String getMasters() {
		return masters;
	}
	public void setMasters(String masters) {
		this.masters = masters;
	}
	public String getAssistants() {
		return assistants;
	}
	public void setAssistants(String assistants) {
		this.assistants = assistants;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public String getWorkDay() {
		return workDay;
	}
	public void setWorkDay(String workDay) {
		this.workDay = workDay;
	}
	public String getClassRoom() {
		return classRoom;
	}
	public void setClassRoom(String classRoom) {
		this.classRoom = classRoom;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
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
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getLessonName() {
		return lessonName;
	}
	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}
	
}
