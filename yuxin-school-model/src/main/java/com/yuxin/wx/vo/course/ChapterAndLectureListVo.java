package com.yuxin.wx.vo.course;

import java.util.List;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.model.course.CourseAfterTest;
import com.yuxin.wx.model.course.CourseVideoLecture;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author wzx
 * @date 2015-5-8 上午12:47:13
 * @version 1.0
 */
public class ChapterAndLectureListVo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	public Integer id;
	private String	chapterName;		 /* 章的名称 */ 
	private Integer	moduleId;		 /* 模块主键 */ 
	private Integer	chapterOrder;		 /* 章的顺序 */ 
	private List<CourseVideoLectureVo> videoLectrueVo;   /*节的list */
	private List<CourseVideoLecture> lectures;
	private List<CourseAfterTest> tests;
	private List<LectureAndTestVo> lectureAndTests;
	

	
	private String ids;
	
	public ChapterAndLectureListVo(){
		
	}
	
	public ChapterAndLectureListVo(Integer id, String chapterName,
			Integer moduleId, Integer chapterOrder,
			List<CourseVideoLectureVo> videoLectrueVo) {
		super();
		this.id = id;
		this.chapterName = chapterName;
		this.moduleId = moduleId;
		this.chapterOrder = chapterOrder;
		this.videoLectrueVo = videoLectrueVo;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getChapterName() {
		return chapterName;
	}
	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}
	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public Integer getChapterOrder() {
		return chapterOrder;
	}
	public void setChapterOrder(Integer chapterOrder) {
		this.chapterOrder = chapterOrder;
	}

	public List<CourseVideoLectureVo> getVideoLectrueVo() {
		return videoLectrueVo;
	}

	public void setVideoLectrue(List<CourseVideoLectureVo> videoLectrueVo) {
		this.videoLectrueVo = videoLectrueVo;
	}

	public List<CourseVideoLecture> getLectures() {
		return lectures;
	}

	public void setLectures(List<CourseVideoLecture> lectures) {
		this.lectures = lectures;
	}

	public void setVideoLectrueVo(List<CourseVideoLectureVo> videoLectrueVo) {
		this.videoLectrueVo = videoLectrueVo;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	
	public List<CourseAfterTest> getTests() {
		return tests;
	}

	public void setTests(List<CourseAfterTest> tests) {
		this.tests = tests;
	}
	
	public List getLectureAndTests() {
		return lectureAndTests;
	}

	public void setLectureAndTests(List lectureAndTests) {
		this.lectureAndTests = lectureAndTests;
	}

}
