package com.yuxin.wx.model.tiku;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:TikuUserBatch
 * 
 * @author wang.zx
 * @date 2015-8-24
 */
@SuppressWarnings("serial")
public class TikuUserBatch extends BaseEntity {
	
	
	private String	batchName;		 /* 答题批次名称 */ 
	private String	batchType;		 /* 答题批次类型：快速做题，章节练习（TIKU_EXERCISE_TYPE） */ 
	private Integer	userId;		
	private Integer	companyId;		
	private Integer	categoryId;		
	private Integer	subjectId;		
	private Integer	totalTopic;		 /* 总共包含的题数 */ 

	// Constructor
	public TikuUserBatch() {
	}
	
	/**
	 * full Constructor
	 */
	public TikuUserBatch(Integer id, String batchName, String batchType, Integer userId, Integer companyId, Integer categoryId, Integer subjectId, Integer totalTopic) {
		setId(id);
		this.batchName = batchName;
		this.batchType = batchType;
		this.userId = userId;
		this.companyId = companyId;
		this.categoryId = categoryId;
		this.subjectId = subjectId;
		this.totalTopic = totalTopic;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为TikuUserBatch可以实现连缀设置属性
	
	public String getBatchName() {
		return batchName;
	}

	public TikuUserBatch setBatchName(String batchName) {
		this.batchName = batchName;
		return this;
	}
	
	
	public String getBatchType() {
		return batchType;
	}

	public TikuUserBatch setBatchType(String batchType) {
		this.batchType = batchType;
		return this;
	}
	
	
	public Integer getUserId() {
		return userId;
	}

	public TikuUserBatch setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public TikuUserBatch setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public Integer getCategoryId() {
		return categoryId;
	}

	public TikuUserBatch setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
		return this;
	}
	
	
	public Integer getSubjectId() {
		return subjectId;
	}

	public TikuUserBatch setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
		return this;
	}
	
	
	public Integer getTotalTopic() {
		return totalTopic;
	}

	public TikuUserBatch setTotalTopic(Integer totalTopic) {
		this.totalTopic = totalTopic;
		return this;
	}
	
	@Override
	public String toString() {
		return "TikuUserBatch [" + "id=" + getId() + ", batchName=" + batchName + ", batchType=" + batchType + ", userId=" + userId + ", companyId=" + companyId + ", categoryId=" + categoryId + ", subjectId=" + subjectId + ", totalTopic=" + totalTopic +  "]";
	}
}
