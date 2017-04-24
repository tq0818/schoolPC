package com.yuxin.wx.model.system;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:SysConfigIndexItem
 * 
 * @author chopin
 * @date 2015-3-17
 */
@SuppressWarnings("serial")
public class SysConfigIndexItem extends BaseEntity {
		
	private String	title;		 /* 标题 */ 
	private Integer	itemOneId;		 /* 一级项目 */ 
	private String	sortBy;		 /* 排序的纬度，从字典表读。时间、价格对此项目下的版型排序 */ 
	private String	sort;		 /* 排序类型 :  从字典表读。asc(正序), desc(倒序)，random(随机) */ 
	private Integer	showItemSecond;		 /* 是否显示二级项目：0(不显示) 1(显示) */ 
	private Integer  privatePageId;
	private String  type; 
	private String  recommendFlag;
	private String  teachType;

	// Constructor
	public SysConfigIndexItem() {
	}
	
	/**
	 * full Constructor
	 */
	public SysConfigIndexItem(Integer id, String title, Integer itemOneId, String sortBy, String sort, Integer showItemSecond) {
		this.id = id;
		this.title = title;
		this.itemOneId = itemOneId;
		this.sortBy = sortBy;
		this.sort = sort;
		this.showItemSecond = showItemSecond;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为SysConfigIndexItem可以实现连缀设置属性
	
	public String getTitle() {
		return title;
	}

	public SysConfigIndexItem setTitle(String title) {
		this.title = title;
		return this;
	}
	
	
	public Integer getItemOneId() {
		return itemOneId;
	}

	public SysConfigIndexItem setItemOneId(Integer itemOneId) {
		this.itemOneId = itemOneId;
		return this;
	}
	
	
	public String getSortBy() {
		return sortBy;
	}

	public SysConfigIndexItem setSortBy(String sortBy) {
		this.sortBy = sortBy;
		return this;
	}
	
	
	public String getSort() {
		return sort;
	}

	public SysConfigIndexItem setSort(String sort) {
		this.sort = sort;
		return this;
	}
	
	
	public Integer getShowItemSecond() {
		return showItemSecond;
	}

	public SysConfigIndexItem setShowItemSecond(Integer showItemSecond) {
		this.showItemSecond = showItemSecond;
		return this;
	}
	
	
	
	public Integer getPrivatePageId() {
		return privatePageId;
	}

	public void setPrivatePageId(Integer privatePageId) {
		this.privatePageId = privatePageId;
	}

	public String getType() {
		return "INDEX_ITEM";
	}

	public void setType(String type) {
		this.type = type;
	}
	

	public String getRecommendFlag() {
		return recommendFlag;
	}

	public void setRecommendFlag(String recommendFlag) {
		this.recommendFlag = recommendFlag;
	}
	
	public String getTeachType() {
		return teachType;
	}

	public void setTeachType(String teachType) {
		this.teachType = teachType;
	}

	@Override
	public String toString() {
		return "SysConfigIndexItem [" + "id=" + id + ", title=" + title + ", itemOneId=" + itemOneId + ", sortBy=" + sortBy + ", sort=" + sort + ", showItemSecond=" + showItemSecond +  "]";
	}
}
