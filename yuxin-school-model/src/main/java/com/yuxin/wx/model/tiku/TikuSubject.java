package com.yuxin.wx.model.tiku;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:TikuSubject
 * 
 * @author wang.zx
 * @date 2015-7-8
 */
@SuppressWarnings("serial")
public class TikuSubject extends BaseEntity {
	
	
	private Integer	categoryId;		 /* 题库分类id */ 
	private String	subjectName;		 /* 科目名称 */ 
	private String	subjectDesc;		 /* 科目描述 */ 
	private Integer	delFlag;		 /* 删除标记（1：已删除；0：未删除） */ 

	// Constructor
	public TikuSubject() {
	}
	
	/**
	 * full Constructor
	 */
	public TikuSubject(Integer id, Integer categoryId, String subjectName, String subjectDesc, Integer delFlag) {
		setId(id);
		this.categoryId = categoryId;
		this.subjectName = subjectName;
		this.subjectDesc = subjectDesc;
		this.delFlag = delFlag;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为TikuSubject可以实现连缀设置属性
	
	public Integer getCategoryId() {
		return categoryId;
	}

	public TikuSubject setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
		return this;
	}
	
	
	public String getSubjectName() {
		return subjectName;
	}

	public TikuSubject setSubjectName(String subjectName) {
		this.subjectName = subjectName;
		return this;
	}
	
	
	public String getSubjectDesc() {
		return subjectDesc;
	}

	public TikuSubject setSubjectDesc(String subjectDesc) {
		this.subjectDesc = subjectDesc;
		return this;
	}
	
	
	public Integer getDelFlag() {
		return delFlag;
	}

	public TikuSubject setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
		return this;
	}
	
	@Override
	public String toString() {
		return "TikuSubject [" + "id=" + getId() + ", categoryId=" + categoryId + ", subjectName=" + subjectName + ", subjectDesc=" + subjectDesc + ", delFlag=" + delFlag +  "]";
	}
}
