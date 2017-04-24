package com.yuxin.wx.model.course;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CourseVideoLecture
 * 
 * @author wang.zx
 * @date 2015-5-8
 */
@SuppressWarnings("serial")
public class CourseVideoLecture extends BaseEntity {
	
	
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
	private String  videoName;
	
	private Integer fileId;
	private String fileType;
	private String fileName;
	private Integer type;
	
	//4.4版本添加
	private Integer moduleId;

	

	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	// Constructor
	public CourseVideoLecture() {
	}
	
	/**
	 * full Constructor
	 */
	public CourseVideoLecture(Integer id, Integer chapterId, String lectureName, Integer lectureOrder, String videoId, String publishStatus, Date publishDate, Integer delFlag, Integer creator, Date createTime, Integer updator, Date updateTime) {
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
	// 在setter方法最后加上"return this;"并把返回参数改为CourseVideoLecture可以实现连缀设置属性
	
	
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
	public Integer getType() {
		return type;
	}

	public CourseVideoLecture setType(Integer type) {
		this.type = type;
		return this;
	}
	
	public Integer getChapterId() {
		return chapterId;
	}

	public CourseVideoLecture setChapterId(Integer chapterId) {
		this.chapterId = chapterId;
		return this;
	}
	
	
	public String getLectureName() {
		return lectureName;
	}

	public CourseVideoLecture setLectureName(String lectureName) {
		this.lectureName = lectureName;
		return this;
	}
	
	
	public Integer getLectureOrder() {
		return lectureOrder;
	}

	public CourseVideoLecture setLectureOrder(Integer lectureOrder) {
		this.lectureOrder = lectureOrder;
		return this;
	}
	
	
	public String getVideoId() {
		return videoId;
	}

	public CourseVideoLecture setVideoId(String videoId) {
		this.videoId = videoId;
		return this;
	}
	
	
	public String getPublishStatus() {
		return publishStatus;
	}

	public CourseVideoLecture setPublishStatus(String publishStatus) {
		this.publishStatus = publishStatus;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getPublishDate() {
		return publishDate;
	}

	public CourseVideoLecture setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
		return this;
	}
	
	
	public Integer getDelFlag() {
		return delFlag;
	}

	public CourseVideoLecture setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
		return this;
	}
	
	
	public Integer getCreator() {
		return creator;
	}

	public CourseVideoLecture setCreator(Integer creator) {
		this.creator = creator;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public CourseVideoLecture setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	
	public Integer getUpdator() {
		return updator;
	}

	public CourseVideoLecture setUpdator(Integer updator) {
		this.updator = updator;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getUpdateTime() {
		return updateTime;
	}

	public CourseVideoLecture setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	
	
	public Integer getFreeFlag() {
		return freeFlag;
	}

	public void setFreeFlag(Integer freeFlag) {
		this.freeFlag = freeFlag;
	}
	
	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	@Override
	public String toString() {
		return "CourseVideoLecture [" + "id=" + getId() + ", chapterId=" + chapterId + ", lectureName=" + lectureName + ", lectureOrder=" + lectureOrder + ", videoId=" + videoId + ", publishStatus=" + publishStatus + ", publishDate=" + publishDate + ", delFlag=" + delFlag + ", creator=" + creator + ", createTime=" + createTime + ", updator=" + updator + ", updateTime=" + updateTime +  "]";
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
}
