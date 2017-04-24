package com.yuxin.wx.model.tiku;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:TikuUserExerciseAnswer
 * 
 * @author wang.zx
 * @date 2015-8-24
 */
@SuppressWarnings("serial")
public class TikuUserExerciseAnswer extends BaseEntity {
	
	
	private Integer	userExerciseId;		 /* 用户练习表id */ 
	private Integer	topicId;		 /* 试题id */ 
	private String	topicType;		 /* 试题类型 */ 
	private String	userAnswer;		 /* 用户的答案 */ 
	private Integer	correctFlag;		 /* 正确标记（1：正确；0：不正确） */ 
	private Double	score;		 /* 得分 */ 
	private Integer userId;		/* 用户Id */
	private Integer categoryId; /* 题库类别Id */
	private Integer subjectId; /* 题库科目Id */
	private Integer companyId; /* 公司Id */
	private Integer parentId;/*题的父id*/
	private Integer keyMarker;/*是否标记*/
	
	// Constructor
	public TikuUserExerciseAnswer() {
	}
	
	/**
	 * full Constructor
	 */
	public TikuUserExerciseAnswer(Integer id, Integer userExerciseId, Integer topicId, String topicType, String userAnswer, 
			Integer correctFlag, Double score, Integer userId, Integer categoryId, Integer subjectId, Integer companyId) {
		setId(id);
		this.userExerciseId = userExerciseId;
		this.topicId = topicId;
		this.topicType = topicType;
		this.userAnswer = userAnswer;
		this.correctFlag = correctFlag;
		this.score = score;
		this.userId = userId;
		this.categoryId = categoryId;
		this.subjectId = subjectId;
		this.companyId = companyId;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为TikuUserExerciseAnswer可以实现连缀设置属性
	
	public Integer getUserExerciseId() {
		return userExerciseId;
	}

	public TikuUserExerciseAnswer setUserExerciseId(Integer userExerciseId) {
		this.userExerciseId = userExerciseId;
		return this;
	}
	
	
	public Integer getTopicId() {
		return topicId;
	}

	public TikuUserExerciseAnswer setTopicId(Integer topicId) {
		this.topicId = topicId;
		return this;
	}
	
	
	public String getTopicType() {
		return topicType;
	}

	public TikuUserExerciseAnswer setTopicType(String topicType) {
		this.topicType = topicType;
		return this;
	}
	
	
	public String getUserAnswer() {
		return userAnswer;
	}

	public TikuUserExerciseAnswer setUserAnswer(String userAnswer) {
		this.userAnswer = userAnswer;
		return this;
	}
	
	
	public Integer getCorrectFlag() {
		return correctFlag;
	}

	public TikuUserExerciseAnswer setCorrectFlag(Integer correctFlag) {
		this.correctFlag = correctFlag;
		return this;
	}
	
	
	public Double getScore() {
		return score;
	}

	public TikuUserExerciseAnswer setScore(Double score) {
		this.score = score;
		return this;
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	@Override
	public String toString() {
		return "TikuUserExerciseAnswer [" + "id=" + getId() + ", userExerciseId=" + userExerciseId + ", topicId=" + topicId + ", topicType=" + topicType + ", userAnswer=" + userAnswer + ", correctFlag=" + correctFlag + ", score=" + score + ", userId=" + userId + ", categoryId=" + categoryId +", subjectId=" + subjectId +", companyId=" + companyId + "]";
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getKeyMarker() {
		return keyMarker;
	}

	public void setKeyMarker(Integer keyMarker) {
		this.keyMarker = keyMarker;
	}
}
