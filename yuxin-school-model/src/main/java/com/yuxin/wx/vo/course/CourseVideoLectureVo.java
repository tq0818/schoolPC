package com.yuxin.wx.vo.course;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.model.course.Video;
import com.yuxin.wx.util.ShortDateSerializer;

public class CourseVideoLectureVo extends BaseEntity {

	private Integer	chapterId;		 /* 章的主键 */ 
	private String	lectureName;		 /* 讲座名字 */ 
	private Integer	lectureOrder;		 /* 讲座的顺序 */ 
	private String	videoId;		 /* 视频表video主键 */ 
	private String	publishStatus;		 /* 视频发布状态取字典表数据（COURSE_VIDEO_STATUS） */ 
	private Date	publishDate;		 /* 发布时间 */ 
	private Integer	delFlag;		 /* 删除标记：1：已删除；0：未删除 */ 
	private Integer freeFlag;
	private Integer	creator;		
	private Date	createTime;		
	private Integer	updator;		
	private Date	updateTime;		
	private Video	video;
	private Integer studyId;		/*学习状态 id*/
	private Integer studyStatus;	/* 学习状态（1：未学习；2：未完成；3：已学习）*/
	
	private Integer recentId;
	
	private Integer fileId;
	private String fileType;
	
	private Integer testType;		/* 测验类型*/
	private String testName;		/* 测验名*/
	private Integer allowContinue;	/* 是否允许不测验继续听课 0-不允许 1-允许*/
	private Integer passFlag;		/* 是否通过 0-未通过 1-通过 */
	private Integer courtype;		/* 当前类型 0-课程 1-测验*/
	
	private Integer disable;			/* 是否可用 0-不可用 1-可用*/
	
	private Integer chapterOrder;		/* 章排序*/
	private Integer sort;				/* 节排序*/
	
	private Integer hasHomework;
	private Integer homeworkId;
	private Integer homeworkType;
	

	
	// Constructor
	public CourseVideoLectureVo() {
	}
	
	/**
	 * full Constructor
	 */
	public CourseVideoLectureVo(Integer id, Integer chapterId, String lectureName, Integer lectureOrder, String videoId, String publishStatus, Date publishDate, Integer delFlag, Integer creator, Date createTime, Integer updator, Date updateTime) {
		setId(id);
		this.chapterId = chapterId;
		this.lectureName = lectureName;
		this.lectureOrder = lectureOrder;
		this.videoId = videoId;
		this.publishStatus = publishStatus;
		this.publishDate = publishDate;
		this.delFlag = delFlag;
		this.creator = creator;
		this.createTime = createTime;
		this.updator = updator;
		this.updateTime = updateTime;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CourseVideoLectureVo可以实现连缀设置属性
	
	public Integer getChapterOrder() {
		return chapterOrder;
	}

	public void setChapterOrder(Integer chapterOrder) {
		this.chapterOrder = chapterOrder;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getChapterId() {
		return chapterId;
	}

	public CourseVideoLectureVo setChapterId(Integer chapterId) {
		this.chapterId = chapterId;
		return this;
	}
	
	public Video getVideo() {
		return video;
	}

	public CourseVideoLectureVo setVideo(Video video) {
		this.video = video;
		return this;
	}
	
	public String getLectureName() {
		return lectureName;
	}

	public CourseVideoLectureVo setLectureName(String lectureName) {
		this.lectureName = lectureName;
		return this;
	}
	
	
	public Integer getLectureOrder() {
		return lectureOrder;
	}

	public CourseVideoLectureVo setLectureOrder(Integer lectureOrder) {
		this.lectureOrder = lectureOrder;
		return this;
	}
	
	
	public String getVideoId() {
		return videoId;
	}

	public CourseVideoLectureVo setVideoId(String videoId) {
		this.videoId = videoId;
		return this;
	}
	
	
	public String getPublishStatus() {
		return publishStatus;
	}

	public CourseVideoLectureVo setPublishStatus(String publishStatus) {
		this.publishStatus = publishStatus;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getPublishDate() {
		return publishDate;
	}

	public CourseVideoLectureVo setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
		return this;
	}
	
	
	public Integer getDelFlag() {
		return delFlag;
	}

	public CourseVideoLectureVo setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
		return this;
	}
	
	
	public Integer getCreator() {
		return creator;
	}

	public CourseVideoLectureVo setCreator(Integer creator) {
		this.creator = creator;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public CourseVideoLectureVo setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	
	public Integer getUpdator() {
		return updator;
	}

	public CourseVideoLectureVo setUpdator(Integer updator) {
		this.updator = updator;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getUpdateTime() {
		return updateTime;
	}

	public CourseVideoLectureVo setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	
	@Override
	public String toString() {
		return "CourseVideoLectureVo [" + "id=" + getId() + ", chapterId=" + chapterId + ", lectureName=" + lectureName + ", lectureOrder=" + lectureOrder + ", videoId=" + videoId + ", publishStatus=" + publishStatus + ", publishDate=" + publishDate + ", delFlag=" + delFlag + ", creator=" + creator + ", createTime=" + createTime + ", updator=" + updator + ", updateTime=" + updateTime +  "]";
	}

	public Integer getFreeFlag() {
		return freeFlag;
	}

	public void setFreeFlag(Integer freeFlag) {
		this.freeFlag = freeFlag;
	}

	public Integer getStudyStatus() {
		return studyStatus;
	}

	public void setStudyStatus(Integer studyStatus) {
		this.studyStatus = studyStatus;
	}

	public Integer getStudyId() {
		return studyId;
	}

	public void setStudyId(Integer studyId) {
		this.studyId = studyId;
	}

	public Integer getRecentId() {
		return recentId;
	}

	public void setRecentId(Integer recentId) {
		this.recentId = recentId;
	}

	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Integer getTestType() {
		return testType;
	}

	public void setTestType(Integer testType) {
		this.testType = testType;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public Integer getAllowContinue() {
		return allowContinue;
	}

	public void setAllowContinue(Integer allowContinue) {
		this.allowContinue = allowContinue;
	}

	public Integer getPassFlag() {
		return passFlag;
	}

	public void setPassFlag(Integer passFlag) {
		this.passFlag = passFlag;
	}

	public Integer getCourtype() {
		return courtype;
	}

	public void setCourtype(Integer courtype) {
		this.courtype = courtype;
	}

	public Integer getDisable() {
		return disable;
	}

	public void setDisable(Integer disable) {
		this.disable = disable;
	}
	
	public Integer getHasHomework() {
		return hasHomework;
	}

	public void setHasHomework(Integer hasHomework) {
		this.hasHomework = hasHomework;
	}

	public Integer getHomeworkId() {
		return homeworkId;
	}

	public void setHomeworkId(Integer homeworkId) {
		this.homeworkId = homeworkId;
	}
	
	public Integer getHomeworkType() {
		return homeworkType;
	}

	public void setHomeworkType(Integer homeworkType) {
		this.homeworkType = homeworkType;
	}

}
