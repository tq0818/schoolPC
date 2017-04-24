package com.yuxin.wx.model.system;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:SysConfigIndexCustom
 * 
 * @author chopin
 * @date 2015-3-17
 */
@SuppressWarnings("serial")
public class SysConfigIndexCustom extends BaseEntity {
	
	private Integer	id;		
	private String	title;		 /* 标题 */ 
	private String	picture;		 /* 图片 */ 
	private String	description;		 /* 描述 */ 
	private String	link;		 /* 目前只支持外链，需要时一个完整的http地址 */ 
	private Integer privatePageId;
	private Integer tile;
	
	private String type; /*类型*/

	// Constructor
	public SysConfigIndexCustom() {
	}
	
	/**
	 * full Constructor
	 */
	public SysConfigIndexCustom(Integer id, String title, String picture, String description, String link) {
		this.id = id;
		this.title = title;
		this.picture = picture;
		this.description = description;
		this.link = link;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为SysConfigIndexCustom可以实现连缀设置属性
	
	public String getTitle() {
		return title;
	}

	public SysConfigIndexCustom setTitle(String title) {
		this.title = title;
		return this;
	}
	
	
	public String getPicture() {
		return picture;
	}

	public SysConfigIndexCustom setPicture(String picture) {
		this.picture = picture;
		return this;
	}
	
	
	public String getDescription() {
		return description;
	}

	public SysConfigIndexCustom setDescription(String description) {
		this.description = description;
		return this;
	}
	
	
	public String getLink() {
		return link;
	}

	public SysConfigIndexCustom setLink(String link) {
		this.link = link;
		return this;
	}
	
	
	
	public Integer getPrivatePageId() {
		return privatePageId;
	}

	public void setPrivatePageId(Integer privatePageId) {
		this.privatePageId = privatePageId;
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
		return "SysConfigIndexCustom [" + "id=" + id + ", title=" + title + ", picture=" + picture + ", description=" + description + ", link=" + link +  "]";
	}
}
