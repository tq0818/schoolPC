package com.yuxin.wx.vo.system;

import java.util.Date;

import com.yuxin.wx.common.BaseEntity;

public class TeacherDynamicsReplayVo  extends BaseEntity{
	private Integer statusId; /* 老师动态ID */
	private Integer parentId; /* 父ID */
	private String content; /* 内容 */
	private Date publishTime; /* 发布时间 */
	private Integer delFlag;
	private Integer userId; /* 用户编号，可以是老师ID，也可以是学员ID */
	private Integer userType; /* 用户类型 0-学员 1-老师 */
	private Integer replayType; /* 回复类型: 0-赞 1-评论 */
	private String userName;
	private String userPic;
	private String publishTimeString;
	private String publishTimeString2;
	private String parentName;
	private Integer isSelf;
	private String teacherReply;
	private String stuName;
	
	
	
	
	
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getTeacherReply() {
		return teacherReply;
	}
	public void setTeacherReply(String teacherReply) {
		this.teacherReply = teacherReply;
	}
	public Integer getStatusId() {
		return statusId;
	}
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public Integer getReplayType() {
		return replayType;
	}
	public void setReplayType(Integer replayType) {
		this.replayType = replayType;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPic() {
		return userPic;
	}
	public void setUserPic(String userPic) {
		this.userPic = userPic;
	}
	public String getPublishTimeString() {
		return publishTimeString;
	}
	public void setPublishTimeString(String publishTimeString) {
		this.publishTimeString = publishTimeString;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getPublishTimeString2() {
		return publishTimeString2;
	}
	public void setPublishTimeString2(String publishTimeString2) {
		this.publishTimeString2 = publishTimeString2;
	}
	public Integer getIsSelf() {
		return isSelf;
	}
	public void setIsSelf(Integer isSelf) {
		this.isSelf = isSelf;
	}

}
