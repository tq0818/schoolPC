package com.yuxin.wx.vo.privilege;

public class SubjectInfoVo {
	//科目code
	private String subjectCode;
	//科目名称
	private String subjectName;
	//是否选中
	private Boolean selected=false;
	public String getSubjectCode() {
		return subjectCode;
	}
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public Boolean getSelected() {
		return selected;
	}
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
	
	
}
