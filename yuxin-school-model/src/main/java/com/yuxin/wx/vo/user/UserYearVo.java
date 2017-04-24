package com.yuxin.wx.vo.user;

import java.util.List;

public class UserYearVo {
	
	private Integer year; /*年份*/
	private List<UserMonthVo> monthList; /*月份集合*/
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public List<UserMonthVo> getMonthList() {
		return monthList;
	}
	public void setMonthList(List<UserMonthVo> monthList) {
		this.monthList = monthList;
	}
	public UserYearVo() {
		super();
	}
	public UserYearVo(Integer year, List<UserMonthVo> monthList) {
		super();
		this.year = year;
		this.monthList = monthList;
	}
	
	
}
