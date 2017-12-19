package com.yuxin.wx.vo.privilege;

import java.util.List;

public class GradeInfoVo {
	//年级名称
	private String gradeName;
	//年级代码
	private String gradeCode;
	//是否选中
	private Boolean selected=false;
	//班级集合
	private List<ClassInfoVo> classInfos;
	
	public Boolean getSelected() {
		return selected;
	}
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public String getGradeCode() {
		return gradeCode;
	}
	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}
	public List<ClassInfoVo> getClassInfos() {
		return classInfos;
	}
	public void setClassInfos(List<ClassInfoVo> classInfos) {
		this.classInfos = classInfos;
	}
	
	
}
