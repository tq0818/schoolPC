package com.yuxin.wx.vo.classes;

import com.yuxin.wx.common.BaseEntity;

public class LessonVo extends BaseEntity {
	
	private	Integer	companyId;
	private	String	liveroomIdGh;
	
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getLiveroomIdGh() {
		return liveroomIdGh;
	}
	public void setLiveroomIdGh(String liveroomIdGh) {
		this.liveroomIdGh = liveroomIdGh;
	}
	
	public LessonVo() {
		super();
	}
	public LessonVo(Integer companyId, String liveroomIdGh) {
		super();
		this.companyId = companyId;
		this.liveroomIdGh = liveroomIdGh;
	}
	
}
