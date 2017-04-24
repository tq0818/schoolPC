package com.yuxin.wx.model.tiku;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:TikuUserCollect
 * 
 * @author wang.zx
 * @date 2015-8-24
 */
@SuppressWarnings("serial")
public class TikuUserCollect extends BaseEntity {
	
	
	private Integer	userId;		
	private Integer	topicId;		 /* 试题id */ 
	private String	topicType;		 /* 试题类型 */ 
	private Date	addTime;		 /* 添加时间 */ 
	private Integer	categoryId;		
	private Integer	subjectId;		
	private String	companyId;		
	
	private Integer parentId;
	
	private List<TikuTopic> topicList;	/* 子题*/

	// Constructor
	public TikuUserCollect() {
	}
	
	/**
	 * full Constructor
	 */
	public TikuUserCollect(Integer id, Integer userId, Integer topicId, String topicType, Date addTime, Integer categoryId, Integer subjectId, String companyId) {
		setId(id);
		this.userId = userId;
		this.topicId = topicId;
		this.topicType = topicType;
		this.addTime = addTime;
		this.categoryId = categoryId;
		this.subjectId = subjectId;
		this.companyId = companyId;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为TikuUserCollect可以实现连缀设置属性
	
	public Integer getUserId() {
		return userId;
	}

	public TikuUserCollect setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}
	
	
	public Integer getTopicId() {
		return topicId;
	}

	public TikuUserCollect setTopicId(Integer topicId) {
		this.topicId = topicId;
		return this;
	}
	
	
	public String getTopicType() {
		return topicType;
	}

	public TikuUserCollect setTopicType(String topicType) {
		this.topicType = topicType;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getAddTime() {
		return addTime;
	}

	public TikuUserCollect setAddTime(Date addTime) {
		this.addTime = addTime;
		return this;
	}
	
	
	public Integer getCategoryId() {
		return categoryId;
	}

	public TikuUserCollect setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
		return this;
	}
	
	
	public Integer getSubjectId() {
		return subjectId;
	}

	public TikuUserCollect setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
		return this;
	}
	
	
	public String getCompanyId() {
		return companyId;
	}

	public TikuUserCollect setCompanyId(String companyId) {
		this.companyId = companyId;
		return this;
	}
	
	@Override
	public String toString() {
		return "TikuUserCollect [" + "id=" + getId() + ", userId=" + userId + ", topicId=" + topicId + ", topicType=" + topicType + ", addTime=" + addTime + ", categoryId=" + categoryId + ", subjectId=" + subjectId + ", companyId=" + companyId +  "]";
	}

	public List<TikuTopic> getTopicList() {
		return topicList;
	}

	public void setTopicList(List<TikuTopic> topicList) {
		this.topicList = topicList;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
}
