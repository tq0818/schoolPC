package com.yuxin.wx.model.user;

import java.util.Date;

import com.yuxin.wx.common.BaseEntity;
/**
 * 录播dto
 * @author zengdeqiang
 *
 */
@SuppressWarnings("serial")
public class SysPlayLogsVo extends BaseEntity {
	private String userId;//用户标识号
	private String commodityId;//商品标识号
	private String classTypeId;//课程标识号
	private String lectureId;//课次标识号
	private Date studyDate;//学习时间
	private String studyLength;//学习长度
	private String sourceId;//来源标识号
	private String device;//访问设备
	private String splId;//sys_play_logs主键标识号
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCommodityId() {
		return commodityId;
	}
	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
	}
	public String getLectureId() {
		return lectureId;
	}
	public void setLectureId(String lectureId) {
		this.lectureId = lectureId;
	}
	public Date getStudyDate() {
		return studyDate;
	}
	public void setStudyDate(Date studyDate) {
		this.studyDate = studyDate;
	}
	public String getStudyLength() {
		return studyLength;
	}
	public void setStudyLength(String studyLength) {
		this.studyLength = studyLength;
	}
	public String getSourceId() {
		return sourceId;
	}
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public String getClassTypeId() {
		return classTypeId;
	}
	public void setClassTypeId(String classTypeId) {
		this.classTypeId = classTypeId;
	}
	public String getSplId() {
		return splId;
	}
	public void setSplId(String splId) {
		this.splId = splId;
	}
	
}
