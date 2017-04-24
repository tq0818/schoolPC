package com.yuxin.wx.model.company;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyIndexModelNews
 * 
 * @author chopin
 * @date 2015-5-18
 */
@SuppressWarnings("serial")
public class CompanyIndexModelNews extends BaseEntity {
	
	
	private String	title;		 /* 标题 */ 
	private Integer	newsId;		 /* 新闻id，一图时使用 */ 
	private String	picture;		 /* 图片 */ 
	private String	description;		 /* 描述 */ 
	private String	sortBy;		 /* 排序方式：按发布时间、阅读次数排序 */ 
	private Integer	recommentFlag;		 /* 是否推荐优先（1：是；0：否） */ 
	
	private String type;

	// Constructor
	public CompanyIndexModelNews() {
	}
	
	/**
	 * full Constructor
	 */
	public CompanyIndexModelNews(Integer id, String title, Integer newsId, String picture, String description, String sortBy, Integer recommentFlag) {
		setId(id);
		this.title = title;
		this.newsId = newsId;
		this.picture = picture;
		this.description = description;
		this.sortBy = sortBy;
		this.recommentFlag = recommentFlag;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CompanyIndexModelNews可以实现连缀设置属性
	
	public String getTitle() {
		return title;
	}

	public CompanyIndexModelNews setTitle(String title) {
		this.title = title;
		return this;
	}
	
	
	public Integer getNewsId() {
		return newsId;
	}

	public CompanyIndexModelNews setNewsId(Integer newsId) {
		this.newsId = newsId;
		return this;
	}
	
	
	public String getPicture() {
		return picture;
	}

	public CompanyIndexModelNews setPicture(String picture) {
		this.picture = picture;
		return this;
	}
	
	
	public String getDescription() {
		return description;
	}

	public CompanyIndexModelNews setDescription(String description) {
		this.description = description;
		return this;
	}
	
	
	public String getSortBy() {
		return sortBy;
	}

	public CompanyIndexModelNews setSortBy(String sortBy) {
		this.sortBy = sortBy;
		return this;
	}
	
	
	public Integer getRecommentFlag() {
		return recommentFlag;
	}

	public CompanyIndexModelNews setRecommentFlag(Integer recommentFlag) {
		this.recommentFlag = recommentFlag;
		return this;
	}
	
	public String getType() {
		return "INDEX_NEWS";
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "CompanyIndexModelNews [" + "id=" + getId() + ", title=" + title + ", newsId=" + newsId + ", picture=" + picture + ", description=" + description + ", sortBy=" + sortBy + ", recommentFlag=" + recommentFlag +  "]";
	}
}
