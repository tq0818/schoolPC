package com.yuxin.wx.model.system;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:SysTeacherPersonalStatusReplay
 * 
 * @author chopin
 * @date 2015-10-26
 */
@SuppressWarnings("serial")
public class SysTeacherPersonalStatusReplay extends BaseEntity {
	
	
	private Integer	statusId;		 /* 老师动态ID */ 
	private Integer	parentId;		 /* 父ID */ 
	private String	content;		 /* 内容 */ 
	private Date  publishTime;		 /* 发布时间 */ 
	private Integer	delFlag;		
	private Integer	userId;		 /* 用户编号，可以是老师ID，也可以是学员ID */ 
	private Integer	userType;		 /* 用户类型 0-学员 1-老师 */ 
	private Integer	replayType;		 /* 回复类型: 0-赞 1-评论 */ 
	
	private String userName;
	private String headUrl;
	private String commentTime;
	private Integer supportFlag;
	
	private String stuName; /* 子评论中学员名*/
	
	private String markName;

	// Constructor
	public SysTeacherPersonalStatusReplay() {
	}
	
	/**
	 * full Constructor
	 */
	public SysTeacherPersonalStatusReplay(Integer id, Integer statusId, Integer parentId, String content, Date publishTime, Integer delFlag, Integer userId, Integer userType, Integer replayType) {
		setId(id);
		this.statusId = statusId;
		this.parentId = parentId;
		this.content = content;
		this.publishTime = publishTime;
		this.delFlag = delFlag;
		this.userId = userId;
		this.userType = userType;
		this.replayType = replayType;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为SysTeacherPersonalStatusReplay可以实现连缀设置属性
	
	public Integer getStatusId() {
		return statusId;
	}

	public SysTeacherPersonalStatusReplay setStatusId(Integer statusId) {
		this.statusId = statusId;
		return this;
	}
	
	
	public Integer getParentId() {
		return parentId;
	}

	public SysTeacherPersonalStatusReplay setParentId(Integer parentId) {
		this.parentId = parentId;
		return this;
	}
	
	
	public String getContent() {
		return content;
	}

	public SysTeacherPersonalStatusReplay setContent(String content) {
		this.content = content;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getPublishTime() {
		return publishTime;
	}

	public SysTeacherPersonalStatusReplay setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
		return this;
	}
	
	
	public Integer getDelFlag() {
		return delFlag;
	}

	public SysTeacherPersonalStatusReplay setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
		return this;
	}
	
	
	public Integer getUserId() {
		return userId;
	}

	public SysTeacherPersonalStatusReplay setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}
	
	
	public Integer getUserType() {
		return userType;
	}

	public SysTeacherPersonalStatusReplay setUserType(Integer userType) {
		this.userType = userType;
		return this;
	}
	
	
	public Integer getReplayType() {
		return replayType;
	}

	public SysTeacherPersonalStatusReplay setReplayType(Integer replayType) {
		this.replayType = replayType;
		return this;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	public String getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(String commentTime) {
		this.commentTime = commentTime;
	}

	public Integer getSupportFlag() {
		return supportFlag;
	}

	public void setSupportFlag(Integer supportFlag) {
		this.supportFlag = supportFlag;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public String getMarkName() {
		return markName;
	}

	public void setMarkName(String markName) {
		this.markName = markName;
	}

	@Override
	public String toString() {
		return "SysTeacherPersonalStatusReplay [" + "id=" + getId() + ", statusId=" + statusId + ", parentId=" + parentId + ", content=" + content + ", publishTime=" + publishTime + ", delFlag=" + delFlag + ", userId=" + userId + ", userType=" + userType + ", replayType=" + replayType +  "]";
	}
}
