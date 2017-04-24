package com.yuxin.wx.model.course;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:VideoCourseComment
 * 
 * @author wang.zx
 * @date 2015-9-29
 */
@SuppressWarnings("serial")
public class VideoCourseComment extends BaseEntity {
	
	
	private String	comment;		 /* 评论内容 */ 
	private Integer	score;		 /* 评论得分 */ 
	private Integer	videoLectureId;		 /* 笔记对应的课次 */ 
	private Integer	userId;		 /* 笔记所属人 */ 
	private Integer	schoolId;		 /* 学校ID */ 
	private Integer	companyId;		
	private Date	createTime;		 /* 创建时间 */
	private Integer teacherId;		/*老师*/
	
	private String nickname;		/* 昵称*/
	private String createTimes;		/* 时间*/
	private String mobilename;		/* 手机*/
	private String username;		/* 用户名*/
	
	private Integer videoChapterId;  /* 章的id*/
	private String videoChapterName;
	private String videoLectureName;
	private Integer source;  /* 源自*/
	
	private Integer delFlag;
	private String headUrl;
	private String sort;
	private Integer totalNum;
	private Integer wellNum;
	private Integer goodNum;
	private Integer badNum;
	private String  publicTime;		 /* 创建时间 */
	private String classTypeName;
	
	private String lecIds;
	private Integer classTypeId;
	private Integer limit;
	// Constructor
	public VideoCourseComment() {
	}
	
	/**
	 * full Constructor
	 */
	public VideoCourseComment(Integer id, String comment, Integer score, Integer videoLectureId, Integer userId, Integer schoolId, Integer companyId, Date createTime) {
		setId(id);
		this.comment = comment;
		this.score = score;
		this.videoLectureId = videoLectureId;
		this.userId = userId;
		this.schoolId = schoolId;
		this.companyId = companyId;
		this.createTime = createTime;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为VideoCourseComment可以实现连缀设置属性
	
	public String getComment() {
		return comment;
	}

	public VideoCourseComment setComment(String comment) {
		this.comment = comment;
		return this;
	}
	
	
	public Integer getScore() {
		return score;
	}

	public VideoCourseComment setScore(Integer score) {
		this.score = score;
		return this;
	}
	
	
	public Integer getVideoLectureId() {
		return videoLectureId;
	}

	public VideoCourseComment setVideoLectureId(Integer videoLectureId) {
		this.videoLectureId = videoLectureId;
		return this;
	}
	
	
	public Integer getUserId() {
		return userId;
	}

	public VideoCourseComment setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}
	
	
	public Integer getSchoolId() {
		return schoolId;
	}

	public VideoCourseComment setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public VideoCourseComment setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public VideoCourseComment setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	@Override
	public String toString() {
		return "VideoCourseComment [" + "id=" + getId() + ", comment=" + comment + ", score=" + score + ", videoLectureId=" + videoLectureId + ", userId=" + userId + ", schoolId=" + schoolId + ", companyId=" + companyId + ", createTime=" + createTime +  "]";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public String getCreateTimes() {
		return createTimes;
	}

	public void setCreateTimes(String createTimes) {
		this.createTimes = createTimes;
	}

	public Integer getVideoChapterId() {
		return videoChapterId;
	}

	public void setVideoChapterId(Integer videoChapterId) {
		this.videoChapterId = videoChapterId;
	}

	public String getVideoChapterName() {
		return videoChapterName;
	}

	public void setVideoChapterName(String videoChapterName) {
		this.videoChapterName = videoChapterName;
	}

	public String getVideoLectureName() {
		return videoLectureName;
	}

	public void setVideoLectureName(String videoLectureName) {
		this.videoLectureName = videoLectureName;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public Integer getWellNum() {
		return wellNum;
	}

	public void setWellNum(Integer wellNum) {
		this.wellNum = wellNum;
	}

	public Integer getGoodNum() {
		return goodNum;
	}

	public void setGoodNum(Integer goodNum) {
		this.goodNum = goodNum;
	}

	public Integer getBadNum() {
		return badNum;
	}

	public void setBadNum(Integer badNum) {
		this.badNum = badNum;
	}

	public String getPublicTime() {
		return publicTime;
	}

	public void setPublicTime(String publicTime) {
		this.publicTime = publicTime;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public String getClassTypeName() {
		return classTypeName;
	}

	public void setClassTypeName(String classTypeName) {
		this.classTypeName = classTypeName;
	}

	public String getLecIds() {
		return lecIds;
	}

	public void setLecIds(String lecIds) {
		this.lecIds = lecIds;
	}

	public Integer getClassTypeId() {
		return classTypeId;
	}

	public void setClassTypeId(Integer classTypeId) {
		this.classTypeId = classTypeId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getMobilename() {
		return mobilename;
	}

	public void setMobilename(String mobilename) {
		this.mobilename = mobilename;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	
	
}
