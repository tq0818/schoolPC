package com.yuxin.wx.vo.system;

import java.util.Date;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:SysConfigTeacher
 * @author wang.zx
 * @date 2014-12-5
 */
@SuppressWarnings("serial")
public class TeachersVo extends BaseEntity {
	
	private String	name;		 /* 老师姓名 */ 
	private String	teacherType;		 /* 老师类型（老师；助教；跟班生;主播）字典表 */ 
	private String	mobile;		 /* 手机号码 */ 
	private String resume;    /* 个人简介 */
	
	private String headpicUrl; /*头像地址*/ 
	private Date	createTime;		
	private Integer	creator;		
	private Integer	companyId;		 
	private Integer schoolId;
	private Integer	teacherId;		 /* 授课老师 */
	private Integer score;
	private Integer userId;
	private Integer courseNum;
	private Integer stuNum;
	private Integer	delFlag;		 /* 删除标记，默认值为0 1：已删除；0：未删除 */ 
	private String cusorder;
	private Integer commentNum;
	
	private Integer itemOneId;
	private String itemOneName;
	private String schoolsId;
	private String schoolName;
	private String teacherLevel;
	private String teacherArea;
	private String schoolShortName;
	private Integer utId;
	private String isCollect;

	public String getTeacherLevel() {
		return teacherLevel;
	}

	public void setTeacherLevel(String teacherLevel) {
		this.teacherLevel = teacherLevel;
	}

	public String getTeacherArea() {
		return teacherArea;
	}

	public void setTeacherArea(String teacherArea) {
		this.teacherArea = teacherArea;
	}

	public TeachersVo() {
		
	}
	
	public TeachersVo(String name, String teacherType, String mobile,
			String resume, String headpicUrl, Date createTime, Integer creator,
			Integer companyId, Integer schoolId, Integer teacherId,
			Integer score, Integer userId, Integer courseNum, Integer stuNum,
			Integer delFlag, String cusorder) {
		this.name = name;
		this.teacherType = teacherType;
		this.mobile = mobile;
		this.resume = resume;
		this.headpicUrl = headpicUrl;
		this.createTime = createTime;
		this.creator = creator;
		this.companyId = companyId;
		this.schoolId = schoolId;
		this.teacherId = teacherId;
		this.score = score;
		this.userId = userId;
		this.courseNum = courseNum;
		this.stuNum = stuNum;
		this.delFlag = delFlag;
		this.cusorder = cusorder;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTeacherType() {
		return teacherType;
	}
	public void setTeacherType(String teacherType) {
		this.teacherType = teacherType;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
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
	public Integer getCourseNum() {
		return courseNum;
	}
	public void setCourseNum(Integer courseNum) {
		this.courseNum = courseNum;
	}
	public Integer getStuNum() {
		return stuNum;
	}
	public void setStuNum(Integer stuNum) {
		this.stuNum = stuNum;
	}
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getCreator() {
		return creator;
	}
	public void setCreator(Integer creator) {
		this.creator = creator;
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
	public Integer getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public String getCusorder() {
		return cusorder;
	}
	public void setCusorder(String cusorder) {
		this.cusorder = cusorder;
	}

	public Integer getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}

	public Integer getItemOneId() {
		return itemOneId;
	}

	public void setItemOneId(Integer itemOneId) {
		this.itemOneId = itemOneId;
	}

	public String getItemOneName() {
		return itemOneName;
	}

	public void setItemOneName(String itemOneName) {
		this.itemOneName = itemOneName;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getSchoolsId() {
		return schoolsId;
	}

	public void setSchoolsId(String schoolsId) {
		this.schoolsId = schoolsId;
	}

	public String getSchoolShortName() {
		return schoolShortName;
	}

	public void setSchoolShortName(String schoolShortName) {
		this.schoolShortName = schoolShortName;
	}

	public Integer getUtId() {
		return utId;
	}

	public void setUtId(Integer utId) {
		this.utId = utId;
	}
	public String getIsCollect() {
		return isCollect;
	}

	public void setIsCollect(String isCollect) {
		this.isCollect = isCollect;
	}
}
