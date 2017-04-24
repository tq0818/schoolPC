package com.yuxin.wx.vo.tiku.exam;

import java.util.List;

public class ExamNoPassVo {
	
	private Integer userId;
	private Integer page;
	private Integer pageSize;
	private List<Integer> itemOneIds;
	private List<Integer> itemSecondeIds;
	private List<Integer> classTypeIds;
	private Integer companyId;
	private Integer schoolId;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public List<Integer> getItemOneIds() {
		return itemOneIds;
	}
	public void setItemOneIds(List<Integer> itemOneIds) {
		this.itemOneIds = itemOneIds;
	}
	public List<Integer> getItemSecondeIds() {
		return itemSecondeIds;
	}
	public void setItemSecondeIds(List<Integer> itemSecondeIds) {
		this.itemSecondeIds = itemSecondeIds;
	}
	public List<Integer> getClassTypeIds() {
		return classTypeIds;
	}
	public void setClassTypeIds(List<Integer> classTypeIds) {
		this.classTypeIds = classTypeIds;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public Integer getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	
}
