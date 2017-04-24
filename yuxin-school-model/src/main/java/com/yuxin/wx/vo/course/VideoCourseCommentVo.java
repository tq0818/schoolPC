package com.yuxin.wx.vo.course;

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
public class VideoCourseCommentVo extends BaseEntity {
	
	
	private String	comment;		 /* 评论内容 */ 
	private Integer	score;		 /* 评论得分 */ 
	private Integer	videoLectureId;		 /* 笔记对应的课次 */ 
	private Integer	userId;		 /* 笔记所属人 */ 
	private Integer	schoolId;		 /* 学校ID */ 
	private Integer	companyId;		
	private Date	createTime;		 /* 创建时间 */
	private Integer teacherId;		/*老师*/
	
	private String username;		/* 用户名*/
	private String createTimes;		/* 时间*/
	private String logname;			/* 登录名*/
	
	private Integer source;    /* 来源*/
	private Integer delFlag;  

	// Constructor
	public VideoCourseCommentVo() {
	}
	
	/**
	 * full Constructor
	 */
	public VideoCourseCommentVo(Integer id, String comment, Integer score, Integer videoLectureId, Integer userId, Integer schoolId, Integer companyId, Date createTime) {
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

	public VideoCourseCommentVo setComment(String comment) {
		this.comment = comment;
		return this;
	}
	
	
	public Integer getScore() {
		return score;
	}

	public VideoCourseCommentVo setScore(Integer score) {
		this.score = score;
		return this;
	}
	
	
	public Integer getVideoLectureId() {
		return videoLectureId;
	}

	public VideoCourseCommentVo setVideoLectureId(Integer videoLectureId) {
		this.videoLectureId = videoLectureId;
		return this;
	}
	
	
	public Integer getUserId() {
		return userId;
	}

	public VideoCourseCommentVo setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}
	
	
	public Integer getSchoolId() {
		return schoolId;
	}

	public VideoCourseCommentVo setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public VideoCourseCommentVo setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public VideoCourseCommentVo setCreateTime(Date createTime) {
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

	public String getLogname() {
		return logname;
	}

	public void setLogname(String logname) {
		this.logname = logname;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}
}
