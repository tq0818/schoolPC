package com.yuxin.wx.model.tiku;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:TikuUserExerciseAnswer
 * 
 * @author wang.zx
 * @date 2015-8-24
 */
@SuppressWarnings("serial")
public class TikuUserExerciseAnswerStatistics extends BaseEntity {
	private Integer paperId;	/*试卷id*/
	private String exerciseType;	/*试卷类型*/
	private Integer topicId;	/*试题id*/
	private String topicType;	/*试题类型*/
	private Integer topicOptionId;	/*试题答案id*/
	private String topicOptionAnswer;	/*试卷答案*/
	private Integer topicOptionAnswerNum;	/*试卷答题选择结果*/
	// Constructor
	public TikuUserExerciseAnswerStatistics() {
	}

	/**
	 * full Constructor
	 */
	public TikuUserExerciseAnswerStatistics(Integer id, String exerciseType, Integer topicId, String topicType,
											Integer topicOptionId, String topicOptionAnswer, Integer topicOptionAnswerNum) {
		setId(id);
		this.exerciseType = exerciseType;
		this.topicId = topicId;
		this.topicType = topicType;
		this.topicOptionId = topicOptionId;
		this.topicOptionAnswer = topicOptionAnswer;
		this.topicOptionAnswerNum = topicOptionAnswerNum;
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

	public Integer getTopicOptionId() {
		return topicOptionId;
	}

	public void setTopicOptionId(Integer topicOptionId) {
		this.topicOptionId = topicOptionId;
	}

	public String getTopicOptionAnswer() {
		return topicOptionAnswer;
	}

	public void setTopicOptionAnswer(String topicOptionAnswer) {
		this.topicOptionAnswer = topicOptionAnswer;
	}

	public Integer getTopicOptionAnswerNum() {
		return topicOptionAnswerNum;
	}

	public void setTopicOptionAnswerNum(Integer topicOptionAnswerNum) {
		this.topicOptionAnswerNum = topicOptionAnswerNum;
	}

	@Override
	public String toString() {
		return "TikuUserExerciseAnswerStatistics [" + "id=" + getId() + ", exerciseType=" + exerciseType+ ", topicId=" + topicId + ", topicType=" + topicType + ", userAnswer=" + topicOptionAnswer + "]";
	}
}
