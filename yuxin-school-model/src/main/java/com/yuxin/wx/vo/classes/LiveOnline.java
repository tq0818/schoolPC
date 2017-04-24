package com.yuxin.wx.vo.classes;

import com.yuxin.wx.common.BaseEntity;

@SuppressWarnings("serial")
public class LiveOnline extends BaseEntity {
	
	private String ClassNo;			/* 班号*/
	private Integer totalusernum;	/* 并发数*/
	
	public LiveOnline() {
		super();
	}
	public LiveOnline(String classNo, Integer totalusernum) {
		super();
		ClassNo = classNo;
		this.totalusernum = totalusernum;
	}
	public String getClassNo() {
		return ClassNo;
	}
	public void setClassNo(String classNo) {
		ClassNo = classNo;
	}
	public Integer getTotalusernum() {
		return totalusernum;
	}
	public void setTotalusernum(Integer totalusernum) {
		this.totalusernum = totalusernum;
	}
	@Override
	public String toString() {
		return "LiveOnline [ClassNo=" + ClassNo + ", totalusernum="
				+ totalusernum + "]";
	}
	
}
