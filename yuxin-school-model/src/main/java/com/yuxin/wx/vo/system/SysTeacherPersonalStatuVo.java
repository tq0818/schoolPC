package com.yuxin.wx.vo.system;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.model.system.SysTeacherPersonalStatusPic;
import com.yuxin.wx.util.ShortDateSerializer;
import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:SysTeacherPersonalStatus
 * 
 * @author chopin
 * @date 2015-10-26
 */
@SuppressWarnings("serial")
public class SysTeacherPersonalStatuVo extends BaseEntity {
	
	
	private String	content;		 /* 内容 */ 
	private String	publishTime;		 /* 发布时间 */ 
	private Integer	teacherId;		 /* 教师编号 */ 
	private Integer	topFlag;		 /* 置顶标记 */ 
	private Integer	delFlag;		 /* 删除标记 */ 
	private Integer	schoolId;		
	private Integer	companyId;	
	
	private String teaherName;
	private String headpicUrl;
	private Integer supportNum;
	private Integer commentsNum;
	
	private List<SysTeacherPersonalStatusPic> arrPic;
	
	private Integer userId;
	private Integer flagNum;
	private String userPic;  /* 学员头像*/

	// Constructor
	public SysTeacherPersonalStatuVo() {
	}
	
	
	public SysTeacherPersonalStatuVo(String content, String publishTime,
			Integer teacherId, Integer topFlag, Integer delFlag,
			Integer schoolId, Integer companyId, String teaherName,
			String headpicUrl, Integer supportNum, Integer commentsNum) {
		this.content = content;
		this.publishTime = publishTime;
		this.teacherId = teacherId;
		this.topFlag = topFlag;
		this.delFlag = delFlag;
		this.schoolId = schoolId;
		this.companyId = companyId;
		this.teaherName = teaherName;
		this.headpicUrl = headpicUrl;
		this.supportNum = supportNum;
		this.commentsNum = commentsNum;
	}

	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getPublishTime() {
		return publishTime;
	}


	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}


	public Integer getTeacherId() {
		return teacherId;
	}


	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}


	public Integer getTopFlag() {
		return topFlag;
	}


	public void setTopFlag(Integer topFlag) {
		this.topFlag = topFlag;
	}


	public Integer getDelFlag() {
		return delFlag;
	}


	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
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


	public String getTeaherName() {
		return teaherName;
	}


	public void setTeaherName(String teaherName) {
		this.teaherName = teaherName;
	}


	public String getHeadpicUrl() {
		return headpicUrl;
	}


	public void setHeadpicUrl(String headpicUrl) {
		this.headpicUrl = headpicUrl;
	}


	public Integer getSupportNum() {
		return supportNum;
	}


	public void setSupportNum(Integer supportNum) {
		this.supportNum = supportNum;
	}


	public Integer getCommentsNum() {
		return commentsNum;
	}


	public void setCommentsNum(Integer commentsNum) {
		this.commentsNum = commentsNum;
	}

	public List<SysTeacherPersonalStatusPic> getArrPic() {
		return arrPic;
	}


	public void setArrPic(List<SysTeacherPersonalStatusPic> arrPic) {
		this.arrPic = arrPic;
	}


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public Integer getFlagNum() {
		return flagNum;
	}


	public void setFlagNum(Integer flagNum) {
		this.flagNum = flagNum;
	}


	public String getUserPic() {
		return userPic;
	}


	public void setUserPic(String userPic) {
		this.userPic = userPic;
	}


	@Override
	public String toString() {
		return "SysTeacherPersonalStatus [" + "id=" + getId() + ", content=" + content + ", publishTime=" + publishTime + ", teacherId=" + teacherId + ", topFlag=" + topFlag + ", delFlag=" + delFlag + ", schoolId=" + schoolId + ", companyId=" + companyId +  "]";
	}
}
