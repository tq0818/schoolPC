package com.yuxin.wx.vo.user;

public class UserDateVo {
	
	private Integer date; /*当天*/

	public Integer getDate() {
		return date;
	}

	public void setDate(Integer date) {
		this.date = date;
	}
	
	public UserDateVo() {
		super();
	}

	public UserDateVo(Integer date) {
		super();
		this.date = date;
	}
	
}
