package com.yuxin.wx.model.tiku;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:TikuPaperTopicType
 * 
 * @author wang.zx
 * @date 2015-7-8
 */
@SuppressWarnings("serial")
public class TikuPaperTopicType extends BaseEntity {
	
	
	private Integer	paperId;		 /* 试卷id */ 
	private String	topicType;		 /* 适用的试题类型（字典表）：单选、多选、不定项、判断、填空、 */ 
	private Double	scorePerTopic;		 /* 每道题的分数 */ 

	// Constructor
	public TikuPaperTopicType() {
	}
	
	/**
	 * full Constructor
	 */
	public TikuPaperTopicType(Integer id, Integer paperId, String topicType, Double scorePerTopic) {
		setId(id);
		this.paperId = paperId;
		this.topicType = topicType;
		this.scorePerTopic = scorePerTopic;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为TikuPaperTopicType可以实现连缀设置属性
	
	public Integer getPaperId() {
		return paperId;
	}

	public TikuPaperTopicType setPaperId(Integer paperId) {
		this.paperId = paperId;
		return this;
	}
	
	
	public String getTopicType() {
		return topicType;
	}

	public TikuPaperTopicType setTopicType(String topicType) {
		this.topicType = topicType;
		return this;
	}
	
	
	public Double getScorePerTopic() {
		return scorePerTopic;
	}

	public TikuPaperTopicType setScorePerTopic(Double scorePerTopic) {
		this.scorePerTopic = scorePerTopic;
		return this;
	}
	
	@Override
	public String toString() {
		return "TikuPaperTopicType [" + "id=" + getId() + ", paperId=" + paperId + ", topicType=" + topicType + ", scorePerTopic=" + scorePerTopic +  "]";
	}
}
