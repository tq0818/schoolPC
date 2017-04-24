package com.yuxin.wx.vo.company;


import java.util.Date;

import com.yuxin.wx.common.BaseEntity;

@SuppressWarnings("serial")
public class CompanyTotalVo extends BaseEntity{
	private String sourceType;    /* 来源类型*/
	private Integer registNum;    /* 注册人数*/
	private String registDate;    /* 注册时间*/
	private String marks;
	private String startTime;
	private String endTime;
	private Integer timeLen;
	
	public CompanyTotalVo() {
	
	}


	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public Integer getRegistNum() {
		return registNum;
	}

	public void setRegistNum(Integer registNum) {
		this.registNum = registNum;
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


	public String getRegistDate() {
		return registDate;
	}


	public void setRegistDate(String registDate) {
		this.registDate = registDate;
	}


	public Integer getTimeLen() {
		return timeLen;
	}


	public void setTimeLen(Integer timeLen) {
		this.timeLen = timeLen;
	}
	
}
