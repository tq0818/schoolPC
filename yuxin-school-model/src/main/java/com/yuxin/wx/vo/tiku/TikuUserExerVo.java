package com.yuxin.wx.vo.tiku;

import java.util.Date;

import com.yuxin.wx.common.BaseEntity;

public class TikuUserExerVo extends BaseEntity {
	
	private Integer	exerciseId;		 /* 练习的批次id，paper表id或者batch表id */ 
	private Integer	userId;		 /* 用户id */ 
	private String	exerciseType;		 /* 联系类型：试卷、快速做题、章节练习（TIKU_EXERCISE_TYPE） */ 
	private Date	startTime;		 /* 开始时间 */ 
	private Date	endTime;		 /* 结束时间 */ 
	private Integer	useTime;		 /* 答题试卷，单位秒 */ 
	private Double	exerciseScore;		 /* 得分 */ 
	private String	status;		 /* 答题状态，字典表（TIKU_EXERCISE_STATUS）：答题中、暂停、结束 */ 
	private Integer	totalTopic;		 /* 总题数 */ 
	private Integer	correctTopic;		 /* 做对题数,交卷时维护 */ 
	private Integer	errorTopic;		 /* 做错题数,交卷时维护 */ 
	private Integer	categoryId;		 /* 题库类别id */ 
	private Integer	subjectId;		 /* 题库科目id */ 
	private Integer	companyId;
	
	private Integer leftNum;		/* 左半圆 数字*/
	private Integer rightNum;		/* 右半圆 数字*/
	private Integer overNum;		/* 已做多少题    继续做题用*/
	private Integer currentNum;		/* 做到第几题    继续做题用*/
	private String	exername;		/* 章节试卷名*/
	private String	time;			/* 使用时间*/
	
	private String scanAnalysis;	/*试题解析*/
	
	public Integer getExerciseId() {
		return exerciseId;
	}
	public void setExerciseId(Integer exerciseId) {
		this.exerciseId = exerciseId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getExerciseType() {
		return exerciseType;
	}
	public void setExerciseType(String exerciseType) {
		this.exerciseType = exerciseType;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Integer getUseTime() {
		return useTime;
	}
	public void setUseTime(Integer useTime) {
		this.useTime = useTime;
	}
	public Double getExerciseScore() {
		return exerciseScore;
	}
	public void setExerciseScore(Double exerciseScore) {
		this.exerciseScore = exerciseScore;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getTotalTopic() {
		return totalTopic;
	}
	public void setTotalTopic(Integer totalTopic) {
		this.totalTopic = totalTopic;
	}
	public Integer getCorrectTopic() {
		return correctTopic;
	}
	public void setCorrectTopic(Integer correctTopic) {
		this.correctTopic = correctTopic;
	}
	public Integer getErrorTopic() {
		return errorTopic;
	}
	public void setErrorTopic(Integer errorTopic) {
		this.errorTopic = errorTopic;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public Integer getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public Integer getLeftNum() {
		return leftNum;
	}
	public void setLeftNum(Integer leftNum) {
		this.leftNum = leftNum;
	}
	public Integer getRightNum() {
		return rightNum;
	}
	public void setRightNum(Integer rightNum) {
		this.rightNum = rightNum;
	}
	public Integer getOverNum() {
		return overNum;
	}
	public void setOverNum(Integer overNum) {
		this.overNum = overNum;
	}
	public Integer getCurrentNum() {
		return currentNum;
	}
	public void setCurrentNum(Integer currentNum) {
		this.currentNum = currentNum;
	}
	public String getExername() {
		return exername;
	}
	public void setExername(String exername) {
		this.exername = exername;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getScanAnalysis() {
		return scanAnalysis;
	}
	public void setScanAnalysis(String scanAnalysis) {
		this.scanAnalysis = scanAnalysis;
	}
	
}
