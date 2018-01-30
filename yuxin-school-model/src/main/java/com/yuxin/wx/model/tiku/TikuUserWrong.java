package com.yuxin.wx.model.tiku;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:TikuUserWrong
 * 
 * @author wang.zx
 * @date 2015-8-24
 */
@SuppressWarnings("serial")
public class TikuUserWrong extends BaseEntity {
	
	
	private Integer	userId;		
	private Integer	topicId;		 /* 试题id */ 
	private String	topicType;		 /* 试题类型 */ 
	private Date	addTime;		 /* 添加时间 */ 
	private String	userAnswer;		 /* 用户的答案 */ 
	private Integer	delFlag;		 /* 删除标记（1：删除；0：未删除） */ 
	private Integer	categoryId;		
	private Integer	subjectId;		
	private Integer	companyId;
	private Integer parentId; 	/* 题目的父ID */
	private Integer doExam;
	// Constructor
	public TikuUserWrong() {
	}
	
	/**
	 * full Constructor
	 */
	public TikuUserWrong(Integer id, Integer userId, Integer topicId, String topicType, Date addTime, String userAnswer, Integer delFlag, Integer categoryId, Integer subjectId, Integer companyId, Integer parentId) {
		setId(id);
		this.userId = userId;
		this.topicId = topicId;
		this.topicType = topicType;
		this.addTime = addTime;
		this.userAnswer = userAnswer;
		this.delFlag = delFlag;
		this.categoryId = categoryId;
		this.subjectId = subjectId;
		this.companyId = companyId;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为TikuUserWrong可以实现连缀设置属性
	
	public Integer getUserId() {
		return userId;
	}

	public TikuUserWrong setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}
	
	
	public Integer getTopicId() {
		return topicId;
	}

	public TikuUserWrong setTopicId(Integer topicId) {
		this.topicId = topicId;
		return this;
	}
	
	
	public String getTopicType() {
		return topicType;
	}

	public TikuUserWrong setTopicType(String topicType) {
		this.topicType = topicType;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getAddTime() {
		return addTime;
	}

	public TikuUserWrong setAddTime(Date addTime) {
		this.addTime = addTime;
		return this;
	}
	
	
	public String getUserAnswer() {
		return userAnswer;
	}

	public TikuUserWrong setUserAnswer(String userAnswer) {
		this.userAnswer = userAnswer;
		return this;
	}
	
	
	public Integer getDelFlag() {
		return delFlag;
	}

	public TikuUserWrong setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
		return this;
	}
	
	
	public Integer getCategoryId() {
		return categoryId;
	}

	public TikuUserWrong setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
		return this;
	}
	
	
	public Integer getSubjectId() {
		return subjectId;
	}

	public TikuUserWrong setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
		return this;
	}
	

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	
	@Override
	public String toString() {
		return "TikuUserWrong [" + "id=" + getId() + ", userId=" + userId + ", topicId=" + topicId + ", topicType=" + topicType + ", addTime=" + addTime + ", userAnswer=" + userAnswer + ", delFlag=" + delFlag + ", categoryId=" + categoryId + ", subjectId=" + subjectId + ", companyId=" + companyId + ", parentId=" + parentId + "]";
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getDoExam() {
		return doExam;
	}

	public void setDoExam(Integer doExam) {
		this.doExam = doExam;
	}

	public Integer getCompanyId() {
		return companyId;
	}
	
}
