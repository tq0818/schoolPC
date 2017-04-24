package com.yuxin.wx.model.company;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyIndexModelCustom
 * 
 * @author chopin
 * @date 2015-5-18
 */
@SuppressWarnings("serial")
public class CompanyIndexModelCustom extends BaseEntity {
	
	
	private String	title;		 /* 标题 */ 
	private String	picture;		 /* 图片 */ 
	private String	description;		 /* 描述 */ 
	private String	link;		 /* 目前只支持外链，需要时一个完整的http地址 */ 
	private String type; /*类型*/
	private Integer tile;
	// Constructor
	public CompanyIndexModelCustom() {
	}
	
	/**
	 * full Constructor
	 */
	public CompanyIndexModelCustom(Integer id, String title, String picture, String description, String link) {
		setId(id);
		this.title = title;
		this.picture = picture;
		this.description = description;
		this.link = link;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CompanyIndexModelCustom可以实现连缀设置属性
	
	public String getTitle() {
		return title;
	}

	public CompanyIndexModelCustom setTitle(String title) {
		this.title = title;
		return this;
	}
	
	
	public String getPicture() {
		return picture;
	}

	public CompanyIndexModelCustom setPicture(String picture) {
		this.picture = picture;
		return this;
	}
	
	
	public String getDescription() {
		return description;
	}

	public CompanyIndexModelCustom setDescription(String description) {
		this.description = description;
		return this;
	}
	
	
	public String getLink() {
		return link;
	}

	public CompanyIndexModelCustom setLink(String link) {
		this.link = link;
		return this;
	}
	

	public String getType() {
		return "INDEX_CUSTOM";
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	public Integer getTile() {
		return tile;
	}

	public void setTile(Integer tile) {
		this.tile = tile;
	}

	@Override
	public String toString() {
		return "CompanyIndexModelCustom [" + "id=" + getId() + ", title=" + title + ", picture=" + picture + ", description=" + description + ", link=" + link +  "]";
	}
}
