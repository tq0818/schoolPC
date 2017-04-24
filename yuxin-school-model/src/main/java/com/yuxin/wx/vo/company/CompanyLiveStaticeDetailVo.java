package com.yuxin.wx.vo.company;

import com.yuxin.wx.model.company.CompanyLiveStaticDetail;

public class CompanyLiveStaticeDetailVo extends CompanyLiveStaticDetail {
	private Integer lookType;
	private String lessonDate;
	public String getLessonDate() {
		return lessonDate;
	}
	public void setLessonDate(String lessonDate) {
		this.lessonDate = lessonDate;
	}
	public Integer getLookType() {
		return lookType;
	}
	public void setLookType(Integer lookType) {
		this.lookType = lookType;
	}
	
	
}
