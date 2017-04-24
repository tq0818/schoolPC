package com.yuxin.wx.vo.tiku;

import java.util.List;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.model.tiku.TikuSubject;

public class MyTiku extends BaseEntity {

	private String tikuName;			/* 题库名*/
	private String tikuDesc;			/* 题库描述*/
	private List<TikuSubject> subList;	/* 科目ID*/
	
	private Integer userId;				/*用户id*/
	private Integer companyId;			/*公司id*/
	
	public String getTikuName() {
		return tikuName;
	}
	public void setTikuName(String tikuName) {
		this.tikuName = tikuName;
	}
	public String getTikuDesc() {
		return tikuDesc;
	}
	public void setTikuDesc(String tikuDesc) {
		this.tikuDesc = tikuDesc;
	}
	public List<TikuSubject> getSubList() {
		return subList;
	}
	public void setSubList(List<TikuSubject> subList) {
		this.subList = subList;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
}
