package com.yuxin.wx.vo.system;

import java.util.Date;
import java.util.List;

import com.yuxin.wx.common.BaseEntity;

public class TeacherDynamicsVo extends BaseEntity {
		private Integer topFlag;
		private String content;
		private String publishTimeString;
		private String publishTime2String;
		private Date publishTime;
		private Integer delFlag;
		private Integer teacherId;
		private List<String> teacherIdList;
		private Integer	schoolId;		
		private Integer	companyId;	
		private String	name;	
		private String headpicUrl; /*头像地址*/ 
		private Integer userId;
		private List<String> picList; /*动态图片 */
		private Integer goodCount; /*赞的数量*/
		private Integer commentCount; /*评论的数量*/
		public Integer getTopFlag() {
			return topFlag;
		}
		public void setTopFlag(Integer topFlag) {
			this.topFlag = topFlag;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public String getPublishTimeString() {
			return publishTimeString;
		}
		public void setPublishTimeString(String publishTimeString) {
			this.publishTimeString = publishTimeString;
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
		public Integer getTeacherId() {
			return teacherId;
		}
		public void setTeacherId(Integer teacherId) {
			this.teacherId = teacherId;
		}
		public Integer getSchoolId() {
			return schoolId;
		}
		public void setSchoolId(Integer schoolId) {
			this.schoolId = schoolId;
		}
		public Integer getCompanyId() {
			return companyId;
		}
		public void setCompanyId(Integer companyId) {
			this.companyId = companyId;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getHeadpicUrl() {
			return headpicUrl;
		}
		public void setHeadpicUrl(String headpicUrl) {
			this.headpicUrl = headpicUrl;
		}
		public Integer getUserId() {
			return userId;
		}
		public void setUserId(Integer userId) {
			this.userId = userId;
		}
		public List<String> getPicList() {
			return picList;
		}
		public void setPicList(List<String> picList) {
			this.picList = picList;
		}
		public Integer getGoodCount() {
			return goodCount;
		}
		public void setGoodCount(Integer goodCount) {
			this.goodCount = goodCount;
		}
		public Integer getCommentCount() {
			return commentCount;
		}
		public void setCommentCount(Integer commentCount) {
			this.commentCount = commentCount;
		}
		public String getPublishTime2String() {
			return publishTime2String;
		}
		public void setPublishTime2String(String publishTime2String) {
			this.publishTime2String = publishTime2String;
		}
		public List<String> getTeacherIdList() {
			return teacherIdList;
		}
		public void setTeacherIdList(List<String> teacherIdList) {
			this.teacherIdList = teacherIdList;
		}
}
