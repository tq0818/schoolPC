package com.yuxin.wx.model.tiku;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:TikuPaperTopic
 * 
 * @author wang.zx
 * @date 2015-7-8
 */
@SuppressWarnings("serial")
public class TikuPaperTopic extends BaseEntity {
	
	
	private Integer	paperId;		 /* 试卷id */ 
	private Integer	topicId;		 /* 试题id */ 
	private String topicType;		 /* 题目类型，见字典表（TIKU_TOPIC_TYPE）*/
	private Integer parentId;		 /* 父节点id，材料题的子题存材料题的id */ 	
	private Float topicScore;/*模考题的分数*/

	// Constructor
	public TikuPaperTopic() {
	}
	
	/**
	 * full Constructor
	 */
	public TikuPaperTopic(Integer id, Integer paperId, Integer topicId, String topicType, Integer parentId) {
		setId(id);
		this.paperId = paperId;
		this.topicId = topicId;
		this.topicType = topicType;
		this.parentId = parentId;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为TikuPaperTopic可以实现连缀设置属性
	
	public Integer getPaperId() {
		return paperId;
	}

	public TikuPaperTopic setPaperId(Integer paperId) {
		this.paperId = paperId;
		return this;
	}
	
	
	public Integer getTopicId() {
		return topicId;
	}

	public TikuPaperTopic setTopicId(Integer topicId) {
		this.topicId = topicId;
		return this;
	}
	
	public String getTopicType() {
		return topicType;
	}

	public void setTopicType(String topicType) {
		this.topicType = topicType;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	@Override
	public String toString() {
		return "TikuPaperTopic [" + "id=" + getId() + ", paperId=" + paperId + ", topicId=" + topicId + ", topicType=" + topicType + ", parentId=" + parentId + "]";
	}

	public Float getTopicScore() {
		return topicScore;
	}

	public void setTopicScore(Float topicScore) {
		this.topicScore = topicScore;
	}
}
