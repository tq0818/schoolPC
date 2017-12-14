package com.yuxin.wx.model.classes;

import com.yuxin.wx.common.BaseEntity;

/**
 * 
 * @author jishangyang 2017年12月13日 下午1:40:38
 * @ClassName EduMasterClass
 * @Description 行政班管理类
 * @version V1.0
 */
@SuppressWarnings("serial")
public class EduMasterClass extends BaseEntity {
	
	
	private String	eduArea;		 /* 所在学校字典代码 */ 
	private String	eduSchool;		 /* 所在学校字典代码 */ 
	private String	eduStep;		 /* 所在学段代码 */ 
	private String	eduYear;		 /* 所在入学年 */ 
	private String	eduClass;		 /* 所在班级号 */ 
	private String	userId;		 
	private String	isUsed;
	private Integer classCount;  /* 班级个数*/ 
	
    public String getEduArea() {
    	return eduArea;
    }
	
    public void setEduArea(String eduArea) {
    	this.eduArea = eduArea;
    }
	
    public String getEduSchool() {
    	return eduSchool;
    }
	
    public void setEduSchool(String eduSchool) {
    	this.eduSchool = eduSchool;
    }
	
    public String getEduStep() {
    	return eduStep;
    }
	
    public void setEduStep(String eduStep) {
    	this.eduStep = eduStep;
    }
	
    public String getEduYear() {
    	return eduYear;
    }
	
    public void setEduYear(String eduYear) {
    	this.eduYear = eduYear;
    }
	
    public String getEduClass() {
    	return eduClass;
    }
	
    public void setEduClass(String eduClass) {
    	this.eduClass = eduClass;
    }
	
    public String getUserId() {
    	return userId;
    }
	
    public void setUserId(String userId) {
    	this.userId = userId;
    }
	
    public String getIsUsed() {
    	return isUsed;
    }
	
    public void setIsUsed(String isUsed) {
    	this.isUsed = isUsed;
    }

	
    public Integer getClassCount() {
    	return classCount;
    }

	
    public void setClassCount(Integer classCount) {
    	this.classCount = classCount;
    }

	
 

	
 
	
	
}
