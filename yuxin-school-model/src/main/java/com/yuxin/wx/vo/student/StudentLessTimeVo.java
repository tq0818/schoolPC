package com.yuxin.wx.vo.student;

import java.util.Calendar;
import java.util.Date;

public class StudentLessTimeVo {
	private Date lessonDate;/*课程日期*/
	private String hour;	/*几点结束*/
	private String star;    /*几点开始*/
	private Integer userId;		//用户id，用来判断直播记录表中学员是否进入直播课
	
	private String classPackageName;
	private String classTypeName;
	private String lessonName;
	
	private Integer status;//直播状态: 1正在播放，2即将开始，3未开始，4直播结束
	private Calendar lessonStar;//课程开始时间
	
	private String teachMethod;//授课方式
	private String liveType; //直播类型
	
	private String lessonUrl;//直播的观看地址
	private Integer lessonId;//直播的id
	private Integer classTypeId;//版型的id
	private Integer commodityId;
	
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getClassTypeId() {
		return classTypeId;
	}
	public void setClassTypeId(Integer classTypeId) {
		this.classTypeId = classTypeId;
	}
	public String getLessonUrl() {
		return lessonUrl;
	}
	public void setLessonUrl(String lessonUrl) {
		this.lessonUrl = lessonUrl;
	}
	public Integer getLessonId() {
		return lessonId;
	}
	public void setLessonId(Integer lessonId) {
		this.lessonId = lessonId;
	}
	public String getTeachMethod() {
		return teachMethod;
	}
	public void setTeachMethod(String teachMethod) {
		this.teachMethod = teachMethod;
	}
	public Date getLessonDate() {
		return lessonDate;
	}
	public void setLessonDate(Date lessonDate) {
		this.lessonDate = lessonDate;
	}
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}
	public String getStar() {
		return star;
	}
	public void setStar(String star) {
		this.star = star;
	}
	public String getClassPackageName() {
		return classPackageName;
	}
	public void setClassPackageName(String classPackageName) {
		this.classPackageName = classPackageName;
	}
	public String getClassTypeName() {
		return classTypeName;
	}
	public void setClassTypeName(String classTypeName) {
		this.classTypeName = classTypeName;
	}
	public String getLessonName() {
		return lessonName;
	}
	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Calendar getLessonStar() {
		return lessonStar;
	}
	public void setLessonStar(Calendar lessonStar) {
		this.lessonStar = lessonStar;
	}
	public Integer getCommodityId() {
		return commodityId;
	}
	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
	}
	public String getLiveType() {
		return liveType;
	}
	public void setLiveType(String liveType) {
		this.liveType = liveType;
	}
	
}
