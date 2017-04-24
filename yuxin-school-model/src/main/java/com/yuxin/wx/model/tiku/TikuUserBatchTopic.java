package com.yuxin.wx.model.tiku;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:TikuUserBatchTopic
 * 
 * @author wang.zx
 * @date 2015-8-24
 */
@SuppressWarnings("serial")
public class TikuUserBatchTopic extends BaseEntity {
	
	
	private Integer	batchId;		
	private Integer	topicId;		

	// Constructor
	public TikuUserBatchTopic() {
	}
	
	/**
	 * full Constructor
	 */
	public TikuUserBatchTopic(Integer id, Integer batchId, Integer topicId) {
		setId(id);
		this.batchId = batchId;
		this.topicId = topicId;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为TikuUserBatchTopic可以实现连缀设置属性
	
	public Integer getBatchId() {
		return batchId;
	}

	public TikuUserBatchTopic setBatchId(Integer batchId) {
		this.batchId = batchId;
		return this;
	}
	
	
	public Integer getTopicId() {
		return topicId;
	}

	public TikuUserBatchTopic setTopicId(Integer topicId) {
		this.topicId = topicId;
		return this;
	}
	
	@Override
	public String toString() {
		return "TikuUserBatchTopic [" + "id=" + getId() + ", batchId=" + batchId + ", topicId=" + topicId +  "]";
	}
}
