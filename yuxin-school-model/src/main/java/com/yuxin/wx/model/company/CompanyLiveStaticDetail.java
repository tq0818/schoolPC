package com.yuxin.wx.model.company;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.util.ShortDateSerializer;

/**
 * POJO:CompanyLiveStaticDetail
 * 
 * @author chopin
 * @date 2015-4-23
 */
@SuppressWarnings("serial")
public class CompanyLiveStaticDetail extends BaseEntity {
	
	
	private Integer	companyId;			/* 公司id */ 
	private Date	liveDate;			/* 直播日期 */ 
	private Integer	classLessonId;		/* 课次id */ 
	private Integer	liveNum;			/* 直播并发数 */
	private Integer userId;				/*进入用户 */
	private Integer	classType;			/*直播类型0：公开课，1：直播*/
	private Date inTime;				/* 进入时间*/
	private Integer watchType;			/* 直播类型*/
	private Date outTime;				/* 退出时间*/

	// Constructor
	public CompanyLiveStaticDetail() {
	}
	
	/**
	 * full Constructor
	 */
	public CompanyLiveStaticDetail(Integer id, Integer companyId, Date liveDate, Integer classLessonId, Integer liveNum) {
		setId(id);
		this.companyId = companyId;
		this.liveDate = liveDate;
		this.classLessonId = classLessonId;
		this.liveNum = liveNum;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CompanyLiveStaticDetail可以实现连缀设置属性
	
	public Integer getCompanyId() {
		return companyId;
	}

	public CompanyLiveStaticDetail setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getLiveDate() {
		return liveDate;
	}

	public CompanyLiveStaticDetail setLiveDate(Date liveDate) {
		this.liveDate = liveDate;
		return this;
	}
	
	
	public Integer getClassLessonId() {
		return classLessonId;
	}

	public CompanyLiveStaticDetail setClassLessonId(Integer classLessonId) {
		this.classLessonId = classLessonId;
		return this;
	}
	
	
	public Integer getLiveNum() {
		return liveNum;
	}

	public CompanyLiveStaticDetail setLiveNum(Integer liveNum) {
		this.liveNum = liveNum;
		return this;
	}
	
	@Override
	public String toString() {
		return "CompanyLiveStaticDetail [" + "id=" + getId() + ", companyId=" + companyId + ", liveDate=" + liveDate + ", classLessonId=" + classLessonId + ", liveNum=" + liveNum +  "]";
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getClassType() {
		return classType;
	}

	public void setClassType(Integer classType) {
		this.classType = classType;
	}

	public Date getInTime() {
		return inTime;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}

	public Integer getWatchType() {
		return watchType;
	}

	public void setWatchType(Integer watchType) {
		this.watchType = watchType;
	}

	public Date getOutTime() {
		return outTime;
	}

	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}
}
