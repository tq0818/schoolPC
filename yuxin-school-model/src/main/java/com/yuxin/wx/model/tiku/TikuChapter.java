package com.yuxin.wx.model.tiku;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:TikuChapter
 * 
 * @author wang.zx
 * @date 2015-7-8
 */
@SuppressWarnings("serial")
public class TikuChapter extends BaseEntity {
	
	
	private String	chapterName;		 /* 章的名称 */ 
	private Integer	chapterOrder;		 /* 章的顺序 */ 
	private String	delFlag;		 /* 删除标记（1：删除；0：未删除） */ 
	private Integer	tikuCategoryId;		 /* 题库分类id */ 
	private Integer	tikuSubjectId;		 /* 题库科目id */ 
	private Integer	companyId;		

	// Constructor
	public TikuChapter() {
	}
	
	/**
	 * full Constructor
	 */
	public TikuChapter(Integer id, String chapterName, Integer chapterOrder, String delFlag, Integer tikuCategoryId, Integer tikuSubjectId, Integer companyId) {
		setId(id);
		this.chapterName = chapterName;
		this.chapterOrder = chapterOrder;
		this.delFlag = delFlag;
		this.tikuCategoryId = tikuCategoryId;
		this.tikuSubjectId = tikuSubjectId;
		this.companyId = companyId;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为TikuChapter可以实现连缀设置属性
	
	public String getChapterName() {
		return chapterName;
	}

	public TikuChapter setChapterName(String chapterName) {
		this.chapterName = chapterName;
		return this;
	}
	
	
	public Integer getChapterOrder() {
		return chapterOrder;
	}

	public TikuChapter setChapterOrder(Integer chapterOrder) {
		this.chapterOrder = chapterOrder;
		return this;
	}
	
	
	public String getDelFlag() {
		return delFlag;
	}

	public TikuChapter setDelFlag(String delFlag) {
		this.delFlag = delFlag;
		return this;
	}
	
	
	public Integer getTikuCategoryId() {
		return tikuCategoryId;
	}

	public TikuChapter setTikuCategoryId(Integer tikuCategoryId) {
		this.tikuCategoryId = tikuCategoryId;
		return this;
	}
	
	
	public Integer getTikuSubjectId() {
		return tikuSubjectId;
	}

	public TikuChapter setTikuSubjectId(Integer tikuSubjectId) {
		this.tikuSubjectId = tikuSubjectId;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public TikuChapter setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	@Override
	public String toString() {
		return "TikuChapter [" + "id=" + getId() + ", chapterName=" + chapterName + ", chapterOrder=" + chapterOrder + ", delFlag=" + delFlag + ", tikuCategoryId=" + tikuCategoryId + ", tikuSubjectId=" + tikuSubjectId + ", companyId=" + companyId +  "]";
	}
}
