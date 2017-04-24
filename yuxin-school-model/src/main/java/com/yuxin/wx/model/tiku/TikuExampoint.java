package com.yuxin.wx.model.tiku;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:TikuExampoint
 * 
 * @author wang.zx
 * @date 2015-7-8
 */
@SuppressWarnings("serial")
public class TikuExampoint extends BaseEntity {
	
	
	private Integer	sectionId;		 /* 节的id */ 
	private Integer	chapterId;		 /* 章的id */ 
	private String	pointName;		 /* 考点名称 */ 
	private Integer	delFlag;		 /* 删除标记（1：删除；0：未删除） */ 
	private Integer	parentId;		 /* 父节点id */ 
	private Integer	companyId;		
	private Integer	tikuCategoryId;		
	private Integer	tikuSubjectId;		

	// Constructor
	public TikuExampoint() {
	}
	
	/**
	 * full Constructor
	 */
	public TikuExampoint(Integer id, Integer sectionId, Integer chapterId, String pointName, Integer delFlag, Integer parentId, Integer companyId, Integer tikuCategoryId, Integer tikuSubjectId) {
		setId(id);
		this.sectionId = sectionId;
		this.chapterId = chapterId;
		this.pointName = pointName;
		this.delFlag = delFlag;
		this.parentId = parentId;
		this.companyId = companyId;
		this.tikuCategoryId = tikuCategoryId;
		this.tikuSubjectId = tikuSubjectId;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为TikuExampoint可以实现连缀设置属性
	
	public Integer getSectionId() {
		return sectionId;
	}

	public TikuExampoint setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
		return this;
	}
	
	
	public Integer getChapterId() {
		return chapterId;
	}

	public TikuExampoint setChapterId(Integer chapterId) {
		this.chapterId = chapterId;
		return this;
	}
	
	
	public String getPointName() {
		return pointName;
	}

	public TikuExampoint setPointName(String pointName) {
		this.pointName = pointName;
		return this;
	}
	
	
	public Integer getDelFlag() {
		return delFlag;
	}

	public TikuExampoint setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
		return this;
	}
	
	
	public Integer getParentId() {
		return parentId;
	}

	public TikuExampoint setParentId(Integer parentId) {
		this.parentId = parentId;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public TikuExampoint setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public Integer getTikuCategoryId() {
		return tikuCategoryId;
	}

	public TikuExampoint setTikuCategoryId(Integer tikuCategoryId) {
		this.tikuCategoryId = tikuCategoryId;
		return this;
	}
	
	
	public Integer getTikuSubjectId() {
		return tikuSubjectId;
	}

	public TikuExampoint setTikuSubjectId(Integer tikuSubjectId) {
		this.tikuSubjectId = tikuSubjectId;
		return this;
	}
	
	@Override
	public String toString() {
		return "TikuExampoint [" + "id=" + getId() + ", sectionId=" + sectionId + ", chapterId=" + chapterId + ", pointName=" + pointName + ", delFlag=" + delFlag + ", parentId=" + parentId + ", companyId=" + companyId + ", tikuCategoryId=" + tikuCategoryId + ", tikuSubjectId=" + tikuSubjectId +  "]";
	}
}
