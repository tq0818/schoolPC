package com.yuxin.wx.model.tiku;


import com.yuxin.wx.common.BaseEntity;

import java.util.Date;

/**
 * POJO:TikuUserExerciseAnswer
 * 
 * @author wang.zx
 * @date 2015-8-24
 */
@SuppressWarnings("serial")
public class TikuUserExerciseAnswerAccuracy extends BaseEntity {
	private Integer paperId;	/*试卷id*/
	private String exerciseType;	/*试卷类型*/
	private Integer topicId;	/*试题id*/
	private String topicType;	/*试题类型*/
	private Integer answerNum;	/*答题人数*/
	private Integer answerAccuracyNum;	/*正确人数*/
	private Date createDate;	/*创建时间*/
	// Constructor
	public TikuUserExerciseAnswerAccuracy() {
	}

	/**
	 * full Constructor
	 */
	public TikuUserExerciseAnswerAccuracy(Integer id, String exerciseType, Integer topicId, String topicType,
                                          Integer answerNum, Integer answerAccuracyNum, Date createDate) {
		setId(id);
		this.exerciseType = exerciseType;
		this.topicId = topicId;
		this.topicType = topicType;
		this.answerNum = answerNum;
		this.answerAccuracyNum = answerAccuracyNum;
		this.createDate = createDate;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为TikuUserExerciseAnswer可以实现连缀设置属性


	public Integer getPaperId() {
		return paperId;
	}

	public void setPaperId(Integer paperId) {
		this.paperId = paperId;
	}

	public String getExerciseType() {
		return exerciseType;
	}

	public void setExerciseType(String exerciseType) {
		this.exerciseType = exerciseType;
	}

	public Integer getTopicId() {
		return topicId;
	}

	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}

	public String getTopicType() {
		return topicType;
	}

	public void setTopicType(String topicType) {
		this.topicType = topicType;
	}

	public Integer getAnswerNum() {
		return answerNum;
	}

	public void setAnswerNum(Integer answerNum) {
		this.answerNum = answerNum;
	}

	public Integer getAnswerAccuracyNum() {
		return answerAccuracyNum;
	}

	public void setAnswerAccuracyNum(Integer answerAccuracyNum) {
		this.answerAccuracyNum = answerAccuracyNum;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "TikuUserExerciseAnswerStatistics [" + "id=" + getId() + ", exerciseType=" + exerciseType+ ", topicId=" + topicId + ", topicType=" + topicType + ", answerNum=" + answerNum + "]";
	}
}
