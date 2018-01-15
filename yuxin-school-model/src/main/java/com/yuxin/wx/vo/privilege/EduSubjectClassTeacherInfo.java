package com.yuxin.wx.vo.privilege;

public class EduSubjectClassTeacherInfo {
	//科目代码
	private String subjectCode;
	//班级标识号
	private String classId;
	//年级名称
	private String gradeName;
	//班级号
	private String eduClass;
	public String getSubjectCode() {
		return subjectCode;
	}
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
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
	public String getEduClass() {
		return eduClass;
	}
	public void setEduClass(String eduClass) {
		this.eduClass = eduClass;
	}
}
