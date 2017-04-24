package com.yuxin.wx.model.tiku;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:TikuTopicExampoint
 * 
 * @author wang.zx
 * @date 2015-7-8
 */
@SuppressWarnings("serial")
public class TikuTopicExampoint extends BaseEntity {
	
	
	private Integer	topicId;		 /* 试题id */ 
	private Integer	chapterId;		 /* 章的id */ 
	private Integer	sectionId;		 /* 节的id */ 
	private Integer	pointId;		 /* 考点id */ 

	// Constructor
	public TikuTopicExampoint() {
	}
	
	/**
	 * full Constructor
	 */
	public TikuTopicExampoint(Integer id, Integer topicId, Integer chapterId, Integer sectionId, Integer pointId) {
		setId(id);
		this.topicId = topicId;
		this.chapterId = chapterId;
		this.sectionId = sectionId;
		this.pointId = pointId;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为TikuTopicExampoint可以实现连缀设置属性
	
	public Integer getTopicId() {
		return topicId;
	}

	public TikuTopicExampoint setTopicId(Integer topicId) {
		this.topicId = topicId;
		return this;
	}
	
	
	public Integer getChapterId() {
		return chapterId;
	}

	public TikuTopicExampoint setChapterId(Integer chapterId) {
		this.chapterId = chapterId;
		return this;
	}
	
	
	public Integer getSectionId() {
		return sectionId;
	}

	public TikuTopicExampoint setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
		return this;
	}
	
	
	public Integer getPointId() {
		return pointId;
	}

	public TikuTopicExampoint setPointId(Integer pointId) {
		this.pointId = pointId;
		return this;
	}
	
	@Override
	public String toString() {
		return "TikuTopicExampoint [" + "id=" + getId() + ", topicId=" + topicId + ", chapterId=" + chapterId + ", sectionId=" + sectionId + ", pointId=" + pointId +  "]";
	}
}
