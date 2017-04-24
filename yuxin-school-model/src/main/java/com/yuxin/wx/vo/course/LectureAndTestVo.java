package com.yuxin.wx.vo.course;

import java.util.Date;

public class LectureAndTestVo implements Comparable<LectureAndTestVo>{

	
	//公共字段
	private Integer id;
	private Integer chapterId;
	private Integer order;
	private Integer type;
	private String name;
	private Integer	updater;		 
	private Date	updateTime;		 
	private Integer delFlag;
	//测验字段
	private Integer	testScore;
	private Integer	testTotalNum;		 /* 题目数 */ 
	private Integer	allowContinue;		 /* 是否允许未完成测验继续听课  0-不允许 1-允许 */ 
	private Integer	score;		 /* 得分 */ 
	private Integer	passFlag;		 /* 通过标记  0-未通过 1-通过 */
	private Integer testType;		/*测验类型  1试卷 2章节*/
	//小节字段
	private String	videoId;		 /* 视频表video主键 */ 
	private String	publishStatus;		 /* 视频发布状态取字典表数据（COURSE_VIDEO_STATUS） */ 
	private Date	publishDate;		 /* 发布时间 */ 
	private Integer freeFlag;
	private Integer	creator;		
	private Date	createTime;	
	private String  videoName;
	private Integer fileId;
	private String fileType;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getChapterId() {
		return chapterId;
	}
	public void setChapterId(Integer chapterId) {
		this.chapterId = chapterId;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getUpdater() {
		return updater;
	}
	public void setUpdater(Integer updater) {
		this.updater = updater;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	public Integer getTestScore() {
		return testScore;
	}
	public void setTestScore(Integer testScore) {
		this.testScore = testScore;
	}
	public Integer getTestTotalNum() {
		return testTotalNum;
	}
	public void setTestTotalNum(Integer testTotalNum) {
		this.testTotalNum = testTotalNum;
	}
	public Integer getAllowContinue() {
		return allowContinue;
	}
	public void setAllowContinue(Integer allowContinue) {
		this.allowContinue = allowContinue;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Integer getPassFlag() {
		return passFlag;
	}
	public void setPassFlag(Integer passFlag) {
		this.passFlag = passFlag;
	}
	public String getVideoId() {
		return videoId;
	}
	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}
	public String getPublishStatus() {
		return publishStatus;
	}
	public void setPublishStatus(String publishStatus) {
		this.publishStatus = publishStatus;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	public Integer getFreeFlag() {
		return freeFlag;
	}
	public void setFreeFlag(Integer freeFlag) {
		this.freeFlag = freeFlag;
	}
	public Integer getCreator() {
		return creator;
	}
	public void setCreator(Integer creator) {
		this.creator = creator;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getVideoName() {
		return videoName;
	}
	public void setVideoName(String videoName) {
		this.videoName = videoName;
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
	@Override
	public String toString() {
		return "LectureAndTestVo [id=" + id + ", chapterId=" + chapterId + ", order=" + order + ", type=" + type
				+ ", name=" + name + ", updater=" + updater + ", updateTime=" + updateTime + ", delFlag=" + delFlag
				+ ", testScore=" + testScore + ", testTotalNum=" + testTotalNum + ", allowContinue=" + allowContinue
				+ ", score=" + score + ", passFlag=" + passFlag + ", videoId=" + videoId + ", publishStatus="
				+ publishStatus + ", publishDate=" + publishDate + ", freeFlag=" + freeFlag + ", creator=" + creator
				+ ", createTime=" + createTime + ", videoName=" + videoName + ", fileId=" + fileId + ", fileType="
				+ fileType + "]";
	}
	@Override
	public int compareTo(LectureAndTestVo o) {
		return this.getOrder()-o.getOrder();
	}
	
	
}
