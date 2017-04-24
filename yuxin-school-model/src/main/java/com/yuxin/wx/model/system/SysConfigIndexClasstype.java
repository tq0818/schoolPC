package com.yuxin.wx.model.system;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:SysConfigIndexClasstype
 * 
 * @author chopin
 * @date 2015-3-17
 */
@SuppressWarnings("serial")
public class SysConfigIndexClasstype extends BaseEntity {
	
	private Integer	id;		
	private String	title;		 /* 标题 */ 
	private String	classTypeOne;		 /* 班型1的ID */ 
	private String	classTypeTwo;		 /* 班型2的ID */ 
	private String	classTypeThree;		 /* 班型3的ID */ 
	private Integer privatePageId;
	
	private String type; /*类型*/

	// Constructor
	public SysConfigIndexClasstype() {
	}
	
	/**
	 * full Constructor
	 */
	public SysConfigIndexClasstype(Integer id, String title, String classTypeOne, String classTypeTwo, String classTypeThree) {
		this.id = id;
		this.title = title;
		this.classTypeOne = classTypeOne;
		this.classTypeTwo = classTypeTwo;
		this.classTypeThree = classTypeThree;
	}
	
	public String getTitle() {
		return title;
	}

	public SysConfigIndexClasstype setTitle(String title) {
		this.title = title;
		return this;
	}
	
	
	public String getClassTypeOne() {
		return classTypeOne;
	}

	public SysConfigIndexClasstype setClassTypeOne(String classTypeOne) {
		this.classTypeOne = classTypeOne;
		return this;
	}
	
	
	public String getClassTypeTwo() {
		return classTypeTwo;
	}

	public SysConfigIndexClasstype setClassTypeTwo(String classTypeTwo) {
		this.classTypeTwo = classTypeTwo;
		return this;
	}
	
	
	public String getClassTypeThree() {
		return classTypeThree;
	}

	public SysConfigIndexClasstype setClassTypeThree(String classTypeThree) {
		this.classTypeThree = classTypeThree;
		return this;
	}
	
	
	
	public Integer getPrivatePageId() {
		return privatePageId;
	}

	public void setPrivatePageId(Integer privatePageId) {
		this.privatePageId = privatePageId;
	}
	
	

	public String getType() {
		return "INDEX_CLASSTYPE";
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "SysConfigIndexClasstype [" + "ID=" + id + ", title=" + title + ", classTypeOne=" + classTypeOne + ", classTypeTwo=" + classTypeTwo + ", classTypeThree=" + classTypeThree +  "]";
	}
}
