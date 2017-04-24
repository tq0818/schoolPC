package com.yuxin.wx.model.course;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CourseVideoChapter
 * 
 * @author wang.zx
 * @date 2015-5-8
 */
@SuppressWarnings("serial")
public class CourseVideoChapter extends BaseEntity {
	
	
	private String	chapterName;		 /* 章的名称 */ 
	private Integer	moduleId;		 /* 模块主键 */ 
	private Integer	chapterOrder;		 /* 章的顺序 */ 

	// Constructor
	public CourseVideoChapter() {
	}
	
	/**
	 * full Constructor
	 */
	public CourseVideoChapter(Integer id, String chapterName, Integer moduleId, Integer chapterOrder) {
		setId(id);
		this.chapterName = chapterName;
		this.moduleId = moduleId;
		this.chapterOrder = chapterOrder;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CourseVideoChapter可以实现连缀设置属性
	
	public String getChapterName() {
		return chapterName;
	}

	public CourseVideoChapter setChapterName(String chapterName) {
		this.chapterName = chapterName;
		return this;
	}
	
	
	public Integer getModuleId() {
		return moduleId;
	}

	public CourseVideoChapter setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
		return this;
	}
	
	
	public Integer getChapterOrder() {
		return chapterOrder;
	}

	public CourseVideoChapter setChapterOrder(Integer chapterOrder) {
		this.chapterOrder = chapterOrder;
		return this;
	}
	
	@Override
	public String toString() {
		return "CourseVideoChapter [" + "id=" + getId() + ", chapterName=" + chapterName + ", moduleId=" + moduleId + ", chapterOrder=" + chapterOrder +  "]";
	}
}
