package com.yuxin.wx.vo.classes;

public class ClassTypeModelNoVo {
	
	private String className; /* 班型*/
	private String classNo; /* 班号*/
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getClassNo() {
		return classNo;
	}
	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}
	
	
	public ClassTypeModelNoVo() {
		super();
	}
	public ClassTypeModelNoVo(String className, String classNo) {
		super();
		this.className = className;
		this.classNo = classNo;
	}
	
}
