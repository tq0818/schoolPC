package com.yuxin.wx.vo.course;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CourseExercise
 * 
 * @author wang.zx
 * @date 2015-12-29
 */
@SuppressWarnings("serial")
public class CourseExerciseVo extends BaseEntity {
	
	
	private Integer	classTypeId;		 /* class_type表ID */ 
	private String	resourceType;		 /* 字典表，直播、面授、视频 */ 
	private Integer	resourceId;		 /* 直播和面授存储课次表id，视频存储讲座表id */ 
	private String	exerciseType;		 /* 课后练习，课后测验 */ 
	private String	tikuResourceType;		/*字典表：试卷、章节*/
	private Integer	tikuCategoryId;		 /* 题库分类ID */ 
	private Integer	tikuChapterId;		 /* 题库章的id */ 
	private Integer	tikuSectionId;		 /* 题库节的id */ 
	private Integer	topicNum;		 /* 按章节做题数量 */ 
	private Integer	paperId;		 /* 试卷id, 按试卷做题使用该字段 */ 
	private Integer	companyId;		 /* 公司ID */ 
	private Integer tikuSubjectId;
	
	private String categoryName;
	private String subjectName;
	private String chapterName;
	private String sectionName;
	private String paperName;
	
	// Constructor
	public CourseExerciseVo() {
	}
	
	/**
	 * full Constructor
	 */
	public CourseExerciseVo(Integer id, Integer classTypeId, String resourceType, Integer resourceId, String exerciseType, Integer tikuCategoryId, Integer tikuChapterId, Integer tikuSectionId, Integer topicNum, Integer paperId, Integer companyId) {
		setId(id);
		this.classTypeId = classTypeId;
		this.resourceType = resourceType;
		this.resourceId = resourceId;
		this.exerciseType = exerciseType;
		this.tikuCategoryId = tikuCategoryId;
		this.tikuChapterId = tikuChapterId;
		this.tikuSectionId = tikuSectionId;
		this.topicNum = topicNum;
		this.paperId = paperId;
		this.companyId = companyId;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CourseExercise可以实现连缀设置属性
	
	public Integer getClassTypeId() {
		return classTypeId;
	}

	public CourseExerciseVo setClassTypeId(Integer classTypeId) {
		this.classTypeId = classTypeId;
		return this;
	}
	
	
	public String getResourceType() {
		return resourceType;
	}

	public CourseExerciseVo setResourceType(String resourceType) {
		this.resourceType = resourceType;
		return this;
	}
	
	
	public Integer getResourceId() {
		return resourceId;
	}

	public CourseExerciseVo setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
		return this;
	}
	
	
	public String getExerciseType() {
		return exerciseType;
	}

	public CourseExerciseVo setExerciseType(String exerciseType) {
		this.exerciseType = exerciseType;
		return this;
	}
	
	
	public Integer getTikuCategoryId() {
		return tikuCategoryId;
	}

	public CourseExerciseVo setTikuCategoryId(Integer tikuCategoryId) {
		this.tikuCategoryId = tikuCategoryId;
		return this;
	}
	
	
	public Integer getTikuChapterId() {
		return tikuChapterId;
	}

	public CourseExerciseVo setTikuChapterId(Integer tikuChapterId) {
		this.tikuChapterId = tikuChapterId;
		return this;
	}
	
	
	public Integer getTikuSectionId() {
		return tikuSectionId;
	}

	public CourseExerciseVo setTikuSectionId(Integer tikuSectionId) {
		this.tikuSectionId = tikuSectionId;
		return this;
	}
	
	
	public Integer getTopicNum() {
		return topicNum;
	}

	public CourseExerciseVo setTopicNum(Integer topicNum) {
		this.topicNum = topicNum;
		return this;
	}
	
	
	public Integer getPaperId() {
		return paperId;
	}

	public CourseExerciseVo setPaperId(Integer paperId) {
		this.paperId = paperId;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public CourseExerciseVo setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public Integer getTikuSubjectId() {
		return tikuSubjectId;
	}

	public void setTikuSubjectId(Integer tikuSubjectId) {
		this.tikuSubjectId = tikuSubjectId;
	}
	
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public String getPaperName() {
		return paperName;
	}

	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}

	@Override
	public String toString() {
		return "CourseExercise [" + "id=" + getId() + ", classTypeId=" + classTypeId + ", resourceType=" + resourceType + ", resourceId=" + resourceId + ", exerciseType=" + exerciseType + ", tikuCategoryId=" + tikuCategoryId + ", tikuChapterId=" + tikuChapterId + ", tikuSectionId=" + tikuSectionId + ", topicNum=" + topicNum + ", paperId=" + paperId + ", companyId=" + companyId +  "]";
	}

	public String getTikuResourceType() {
		return tikuResourceType;
	}

	public void setTikuResourceType(String tikuResourceType) {
		this.tikuResourceType = tikuResourceType;
	}
}
