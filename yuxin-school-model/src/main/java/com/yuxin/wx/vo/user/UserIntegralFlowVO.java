package com.yuxin.wx.vo.user;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.util.ShortDateSerializer;
import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:UserIntegralFlow
 * 
 * @author chopin
 * @date 2016-5-17
 */
@SuppressWarnings("serial")
public class UserIntegralFlowVO extends BaseEntity {
	
	
	private Date	updateTime;		 /* 积分变化时间 */ 
	private String	reason;		 /* 积分变化原因 */ 
	private Integer	record;		 /* 积分记录 */ 
	private Integer	lastRecord;		 /* 最后记录 */ 
	private Integer	userId;		 /* 前台用户ID */ 
	private Integer	stuId;		 /* 学员ID */ 

	
	//其他
	private String marks;
	private String startTime;
	private String endTime;
	private Integer timeLen;
	// Constructor
	public UserIntegralFlowVO() {
	}
	
	/**
	 * full Constructor
	 */
	public UserIntegralFlowVO(Integer id, Date updateTime, String reason, Integer record, Integer lastRecord, Integer userId, Integer stuId) {
		setId(id);
		this.updateTime = updateTime;
		this.reason = reason;
		this.record = record;
		this.lastRecord = lastRecord;
		this.userId = userId;
		this.stuId = stuId;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为UserIntegralFlow可以实现连缀设置属性
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getUpdateTime() {
		return updateTime;
	}

	public UserIntegralFlowVO setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	
	
	public String getReason() {
		return reason;
	}

	public UserIntegralFlowVO setReason(String reason) {
		this.reason = reason;
		return this;
	}
	
	
	public Integer getRecord() {
		return record;
	}

	public UserIntegralFlowVO setRecord(Integer record) {
		this.record = record;
		return this;
	}
	
	
	public Integer getLastRecord() {
		return lastRecord;
	}

	public UserIntegralFlowVO setLastRecord(Integer lastRecord) {
		this.lastRecord = lastRecord;
		return this;
	}
	
	
	public Integer getUserId() {
		return userId;
	}

	public UserIntegralFlowVO setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}
	
	
	public Integer getStuId() {
		return stuId;
	}

	public UserIntegralFlowVO setStuId(Integer stuId) {
		this.stuId = stuId;
		return this;
	}
	
	public String getMarks() {
		return marks;
	}

	public void setMarks(String marks) {
		this.marks = marks;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getTimeLen() {
		return timeLen;
	}

	public void setTimeLen(Integer timeLen) {
		this.timeLen = timeLen;
	}

	@Override
	public String toString() {
		return "UserIntegralFlow [" + "id=" + getId() + ", updateTime=" + updateTime + ", reason=" + reason + ", record=" + record + ", lastRecord=" + lastRecord + ", userId=" + userId + ", stuId=" + stuId +  "]";
	}

}
