package com.yuxin.wx.model.company;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyIndexModelClasstype
 * 
 * @author chopin
 * @date 2015-5-18
 */
@SuppressWarnings("serial")
public class CompanyIndexModelClasstype extends BaseEntity {
	
	
	private String	title;		 /* 标题 */ 
	private Integer	classTypeOne;		 /* 班型1的ID */ 
	private Integer	classTypeTwo;		 /* 班型2的ID */ 
	private Integer	classTypeThree;		 /* 班型3的ID */ 
	
	private String type; /*类型*/

	// Constructor
	public CompanyIndexModelClasstype() {
	}
	
	/**
	 * full Constructor
	 */
	public CompanyIndexModelClasstype(Integer id, String title, Integer classTypeOne, Integer classTypeTwo, Integer classTypeThree) {
		setId(id);
		this.title = title;
		this.classTypeOne = classTypeOne;
		this.classTypeTwo = classTypeTwo;
		this.classTypeThree = classTypeThree;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CompanyIndexModelClasstype可以实现连缀设置属性
	
	public String getTitle() {
		return title;
	}

	public CompanyIndexModelClasstype setTitle(String title) {
		this.title = title;
		return this;
	}
	
	
	public Integer getClassTypeOne() {
		return classTypeOne;
	}

	public CompanyIndexModelClasstype setClassTypeOne(Integer classTypeOne) {
		this.classTypeOne = classTypeOne;
		return this;
	}
	
	
	public Integer getClassTypeTwo() {
		return classTypeTwo;
	}

	public CompanyIndexModelClasstype setClassTypeTwo(Integer classTypeTwo) {
		this.classTypeTwo = classTypeTwo;
		return this;
	}
	
	
	public Integer getClassTypeThree() {
		return classTypeThree;
	}

	public CompanyIndexModelClasstype setClassTypeThree(Integer classTypeThree) {
		this.classTypeThree = classTypeThree;
		return this;
	}

	public String getType() {
		return "INDEX_CLASSTYPE";
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "CompanyIndexModelClasstype [" + "id=" + getId() + ", title=" + title + ", classTypeOne=" + classTypeOne + ", classTypeTwo=" + classTypeTwo + ", classTypeThree=" + classTypeThree +  "]";
	}
}
