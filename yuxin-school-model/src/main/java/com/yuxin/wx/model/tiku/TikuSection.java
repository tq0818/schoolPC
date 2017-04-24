package com.yuxin.wx.model.tiku;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:TikuSection
 * 
 * @author wang.zx
 * @date 2015-7-8
 */
@SuppressWarnings("serial")
public class TikuSection extends BaseEntity {
	
	
	private Integer	chapterId;		 /* 章的id */ 
	private String	sectionName;		 /* 节的名称 */ 
	private Integer	sectionOrder;		 /* 节的顺序 */ 
	private Integer	delFlag;		 /* 删除标记（1：已删除；0：未删除） */ 
	private Integer	companyId;		
	private Integer	tikuCategoryId;		
	private Integer	tikuSubjectId;		

	// Constructor
	public TikuSection() {
	}
	
	/**
	 * full Constructor
	 */
	public TikuSection(Integer id, Integer chapterId, String sectionName, Integer sectionOrder, Integer delFlag, Integer companyId, Integer tikuCategoryId, Integer tikuSubjectId) {
		setId(id);
		this.chapterId = chapterId;
		this.sectionName = sectionName;
		this.sectionOrder = sectionOrder;
		this.delFlag = delFlag;
		this.companyId = companyId;
		this.tikuCategoryId = tikuCategoryId;
		this.tikuSubjectId = tikuSubjectId;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为TikuSection可以实现连缀设置属性
	
	public Integer getChapterId() {
		return chapterId;
	}

	public TikuSection setChapterId(Integer chapterId) {
		this.chapterId = chapterId;
		return this;
	}
	
	
	public String getSectionName() {
		return sectionName;
	}

	public TikuSection setSectionName(String sectionName) {
		this.sectionName = sectionName;
		return this;
	}
	
	
	public Integer getSectionOrder() {
		return sectionOrder;
	}

	public TikuSection setSectionOrder(Integer sectionOrder) {
		this.sectionOrder = sectionOrder;
		return this;
	}
	
	
	public Integer getDelFlag() {
		return delFlag;
	}

	public TikuSection setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public TikuSection setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public Integer getTikuCategoryId() {
		return tikuCategoryId;
	}

	public TikuSection setTikuCategoryId(Integer tikuCategoryId) {
		this.tikuCategoryId = tikuCategoryId;
		return this;
	}
	
	
	public Integer getTikuSubjectId() {
		return tikuSubjectId;
	}

	public TikuSection setTikuSubjectId(Integer tikuSubjectId) {
		this.tikuSubjectId = tikuSubjectId;
		return this;
	}
	
	@Override
	public String toString() {
		return "TikuSection [" + "id=" + getId() + ", chapterId=" + chapterId + ", sectionName=" + sectionName + ", sectionOrder=" + sectionOrder + ", delFlag=" + delFlag + ", companyId=" + companyId + ", tikuCategoryId=" + tikuCategoryId + ", tikuSubjectId=" + tikuSubjectId +  "]";
	}
}
