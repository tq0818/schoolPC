package com.yuxin.wx.vo.user;

import java.util.List;

public class UserMonthVo {
	
	private Integer month; /*月*/
	private List<UserDateVo> dateList; /* 日期集合*/
	
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public List<UserDateVo> getDateList() {
		return dateList;
	}
	public void setDateList(List<UserDateVo> dateList) {
		this.dateList = dateList;
	}
	public UserMonthVo() {
		super();
	}
	public UserMonthVo(Integer month, List<UserDateVo> dateList) {
		super();
		this.month = month;
		this.dateList = dateList;
	}
	
	
}
