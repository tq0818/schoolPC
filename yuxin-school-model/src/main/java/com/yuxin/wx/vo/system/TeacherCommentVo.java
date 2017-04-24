package com.yuxin.wx.vo.system;

import java.util.Date;
import java.util.List;

import com.yuxin.wx.common.BaseEntity;

public class TeacherCommentVo extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private Integer teacherId;
	private String comment;
	private Date createTime;
	private String from;
	private String videoChapter;
	private String videoLecture;
	private Integer fromId;
	private Integer videoChapterId;
	private Integer videoLectureId;
	private Integer userId;
	private Integer companyId;
	private Integer schoolId;
	private String userName;
	private String userImage;
	private String teacherName;
	private Integer score;
	private Integer classTypeId;
	private String classTypeName;
	public String getCreateTime2Text() {
		return createTime2Text;
	}
	public void setCreateTime2Text(String createTime2Text) {
		this.createTime2Text = createTime2Text;
	}
	private String createTimeText;
	private String createTime2Text;
	
	public String getClassTypeName() {
		return classTypeName;
	}
	public void setClassTypeName(String classTypeName) {
		this.classTypeName = classTypeName;
	}
	public Integer getClassTypeId() {
		return classTypeId;
	}
	public void setClassTypeId(Integer classTypeId) {
		this.classTypeId = classTypeId;
	}
	public Integer getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getVideoChapter() {
		return videoChapter;
	}
	public void setVideoChapter(String videoChapter) {
		this.videoChapter = videoChapter;
	}
	public String getVideoLecture() {
		return videoLecture;
	}
	public void setVideoLecture(String videoLecture) {
		this.videoLecture = videoLecture;
	}
	public Integer getFromId() {
		return fromId;
	}
	public void setFromId(Integer fromId) {
		this.fromId = fromId;
	}
	public Integer getVideoChapterId() {
		return videoChapterId;
	}
	public void setVideoChapterId(Integer videoChapterId) {
		this.videoChapterId = videoChapterId;
	}
	public Integer getVideoLectureId() {
		return videoLectureId;
	}
	public void setVideoLectureId(Integer videoLectureId) {
		this.videoLectureId = videoLectureId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public Integer getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserImage() {
		return userImage;
	}
	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}
	public String getCreateTimeText() {
		return createTimeText;
	}
	public void setCreateTimeText(String createTimeText) {
		this.createTimeText = createTimeText;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
}
