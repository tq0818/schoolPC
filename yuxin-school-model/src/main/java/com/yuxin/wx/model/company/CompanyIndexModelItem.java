package com.yuxin.wx.model.company;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyIndexModelItem
 * 
 * @author chopin
 * @date 2015-5-18
 */
@SuppressWarnings("serial")
public class CompanyIndexModelItem extends BaseEntity {
	
	
	private String	title;		 /* 标题 */ 
	private Integer	itemOneId;		 /* 一级项目 */ 
	private String	sortBy;		 /* 排序的维度，从字典表读。学员数量（student_num）、时间（publish_date）、价格（price）对此项目下的版型排序。学员数量由多到少，时间由近到远，价格从低到高 */ 
	private String	sort;		 /* 该字段暂时不用了。排序类型 :  从字典表读。asc(正序), desc(倒序)，random(随机) */ 
	private Integer	showItemSecond;		 /* 是否显示二级项目：0(不显示) 1(显示) */ 
	private Integer	recommendFlag;		 /* 是否推荐优先（1：是；0：否） */ 
	private String  type;
	private String  teachType;
	// Constructor
	public CompanyIndexModelItem() {
	}
	
	/**
	 * full Constructor
	 */
	public CompanyIndexModelItem(Integer id, String title, Integer itemOneId, String sortBy, String sort, Integer showItemSecond, Integer recommendFlag) {
		setId(id);
		this.title = title;
		this.itemOneId = itemOneId;
		this.sortBy = sortBy;
		this.sort = sort;
		this.showItemSecond = showItemSecond;
		this.recommendFlag = recommendFlag;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CompanyIndexModelItem可以实现连缀设置属性
	
	public String getTitle() {
		return title;
	}

	public CompanyIndexModelItem setTitle(String title) {
		this.title = title;
		return this;
	}
	
	
	public Integer getItemOneId() {
		return itemOneId;
	}

	public CompanyIndexModelItem setItemOneId(Integer itemOneId) {
		this.itemOneId = itemOneId;
		return this;
	}
	
	
	public String getSortBy() {
		return sortBy;
	}

	public CompanyIndexModelItem setSortBy(String sortBy) {
		this.sortBy = sortBy;
		return this;
	}
	
	
	public String getSort() {
		return sort;
	}

	public CompanyIndexModelItem setSort(String sort) {
		this.sort = sort;
		return this;
	}
	
	
	public Integer getShowItemSecond() {
		return showItemSecond;
	}

	public CompanyIndexModelItem setShowItemSecond(Integer showItemSecond) {
		this.showItemSecond = showItemSecond;
		return this;
	}
	
	
	public Integer getRecommendFlag() {
		return recommendFlag;
	}

	public CompanyIndexModelItem setRecommendFlag(Integer recommendFlag) {
		this.recommendFlag = recommendFlag;
		return this;
	}
	public String getType() {
		return "INDEX_ITEM";
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getTeachType() {
		return teachType;
	}

	public void setTeachType(String teachType) {
		this.teachType = teachType;
	}

	@Override
	public String toString() {
		return "CompanyIndexModelItem [" + "id=" + getId() + ", title=" + title + ", itemOneId=" + itemOneId + ", sortBy=" + sortBy + ", sort=" + sort + ", showItemSecond=" + showItemSecond + ", recommendFlag=" + recommendFlag +  "]";
	}
}
