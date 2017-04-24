package com.yuxin.wx.model.company;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyLiveStaticRecord
 * 
 * @author wang.zx
 * @date 2017-1-4
 */
@SuppressWarnings("serial")
public class CompanyLiveStaticRecord extends BaseEntity {
	
	
	private Integer	userId;		 /* 前台用户ID */ 
	private Integer	companyId;		 /* 公司ID */ 
	private Date	liveDate;		 /* 直播日期 */ 
	private Date	inTime;		 /* 进直播间的时间 */ 
	private Date	outTime;		 /* 出直播间的时间（最后离开时间） */ 
	private Integer	classLessonId;		
	private Integer	classType;		 /* 课程类型 0-公开课  1-直播 */ 
	private Integer	watchType;		 /* 观看类型： 0-上直播 1-看回放 */ 
	private Integer courseId;		/* 课程id*/
	private Integer clientType;		/* 客户端*/

	// Constructor
	public CompanyLiveStaticRecord() {
	}
	
	/**
	 * full Constructor
	 */
	public CompanyLiveStaticRecord(Integer id, Integer userId, Integer companyId, Date liveDate, Date inTime, Date outTime, Integer classLessonId, Integer classType, Integer watchType) {
		setId(id);
		this.userId = userId;
		this.companyId = companyId;
		this.liveDate = liveDate;
		this.inTime = inTime;
		this.outTime = outTime;
		this.classLessonId = classLessonId;
		this.classType = classType;
		this.watchType = watchType;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CompanyLiveStaticRecord可以实现连缀设置属性
	
	public Integer getUserId() {
		return userId;
	}

	public CompanyLiveStaticRecord setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public CompanyLiveStaticRecord setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getLiveDate() {
		return liveDate;
	}

	public CompanyLiveStaticRecord setLiveDate(Date liveDate) {
		this.liveDate = liveDate;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getInTime() {
		return inTime;
	}

	public CompanyLiveStaticRecord setInTime(Date inTime) {
		this.inTime = inTime;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getOutTime() {
		return outTime;
	}

	public CompanyLiveStaticRecord setOutTime(Date outTime) {
		this.outTime = outTime;
		return this;
	}
	
	
	public Integer getClassLessonId() {
		return classLessonId;
	}

	public CompanyLiveStaticRecord setClassLessonId(Integer classLessonId) {
		this.classLessonId = classLessonId;
		return this;
	}
	
	
	public Integer getClassType() {
		return classType;
	}

	public CompanyLiveStaticRecord setClassType(Integer classType) {
		this.classType = classType;
		return this;
	}
	
	
	public Integer getWatchType() {
		return watchType;
	}

	public CompanyLiveStaticRecord setWatchType(Integer watchType) {
		this.watchType = watchType;
		return this;
	}
	
	@Override
	public String toString() {
		return "CompanyLiveStaticRecord [" + "id=" + getId() + ", userId=" + userId + ", companyId=" + companyId + ", liveDate=" + liveDate + ", inTime=" + inTime + ", outTime=" + outTime + ", classLessonId=" + classLessonId + ", classType=" + classType + ", watchType=" + watchType +  "]";
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public Integer getClientType() {
		return clientType;
	}

	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}
}
