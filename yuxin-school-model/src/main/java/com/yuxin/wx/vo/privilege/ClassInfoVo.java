package com.yuxin.wx.vo.privilege;

public class ClassInfoVo {
	//班级标识号
	private String classId;
	//年级名称
	private String gradeName;
	//班级名称
	private String className;
	//班主任标识号
	private String userId;
	
	//是否选中
	private Boolean selected=false;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public Boolean getSelected() {
		return selected;
	}
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
}
