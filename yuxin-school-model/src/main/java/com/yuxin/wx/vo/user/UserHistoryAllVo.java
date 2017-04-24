package com.yuxin.wx.vo.user;

import java.util.Date;

import com.yuxin.wx.common.BaseEntity;

public class UserHistoryAllVo  extends BaseEntity{
	
	private Integer userId; /* 用户id */
	private Integer commodityId; /* 商品id */
	private Integer classTypeId; /* 班型id */
	private Integer lectureId; /* 视频id */
	private Date studyTime; /* 观看时间记录 */
	private Integer studyLength;	/*学习时长(秒)*/
	
	public UserHistoryAllVo() {
		super();
	}
	
	public UserHistoryAllVo(Integer userId, Integer commodityId,
			Integer classTypeId, Integer lectureId, Date studyTime) {
		super();
		this.userId = userId;
		this.commodityId = commodityId;
		this.classTypeId = classTypeId;
		this.lectureId = lectureId;
		this.studyTime = studyTime;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getCommodityId() {
		return commodityId;
	}
	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
	}
	public Integer getClassTypeId() {
		return classTypeId;
	}
	public void setClassTypeId(Integer classTypeId) {
		this.classTypeId = classTypeId;
	}
	public Integer getLectureId() {
		return lectureId;
	}
	public void setLectureId(Integer lectureId) {
		this.lectureId = lectureId;
	}
	public Date getStudyTime() {
		return studyTime;
	}
	public void setStudyTime(Date studyTime) {
		this.studyTime = studyTime;
	}

	public Integer getStudyLength() {
		return studyLength;
	}

	public void setStudyLength(Integer studyLength) {
		this.studyLength = studyLength;
	}
}
