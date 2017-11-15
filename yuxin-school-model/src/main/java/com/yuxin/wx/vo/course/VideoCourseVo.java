package com.yuxin.wx.vo.course;

import com.yuxin.wx.common.BaseEntity;

import java.util.Date;

@SuppressWarnings("serial")
public class VideoCourseVo extends BaseEntity {

	private Integer userId;		 /* 观看用户ID */
	private Integer	commodityId;		 /* 商品ID */
	private Integer	classTypeId;		 /* 课程ID */
	private Long totleStudy;		 /* 播放总量 */
	private String totleStudyLength;		 /* 播放总时长 */
	private String studyRate;	 /* 播完率 */
	private String courseName;	 /* 课程名称 */
	private String eduArea;	 /* 区域编码 */
	private String areaName;	 /* 区域名称 */
	private String eduSchoolStep;	 /* 学校性质编码 */
	private String schoolStepName;	 /* 学校性质名称 */
	private String stepName;	 /* 学校属性名称 */
	private String eduSchool;	 /* 学校编码 */
	private String schoolName;	 /* 学校名称 */
	private String eduSubject;	 /* 学科编码 */
	private String subjectName;	 /* 学科名称 */
	private String videoId;	 /* 播放视频源ID */
	private Integer companyId;	 /* 单位ID */
	private Integer schoolId;	 /* 学校ID */
	private String teaName;	 /* 教师名称 */
	private String startTime;	 /* 开始时间 */
	private String endTime;	 /* 结束时间 */
	private String fieldName;	 /* 排序字段 */
	private String sortType;	 /* 排序类型 */
	private Long viewNum;	 /* 观看人数 */
	// Constructor
	public VideoCourseVo() {
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
	}

	public Integer getClassTypeId() {
		return classTypeId;
	}

	public void setClassTypeId(Integer classTypeId) {
		this.classTypeId = classTypeId;
	}

	public Long getTotleStudy() {
		return totleStudy;
	}

	public void setTotleStudy(Long totleStudy) {
		this.totleStudy = totleStudy;
	}

	public String getTotleStudyLength() {
		return totleStudyLength;
	}

	public void setTotleStudyLength(String totleStudyLength) {
		this.totleStudyLength = totleStudyLength;
	}

	public String getStudyRate() {
		return studyRate;
	}

	public void setStudyRate(String studyRate) {
		this.studyRate = studyRate;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public String getStepName() {
		return stepName;
	}

	public void setStepName(String stepName) {
		this.stepName = stepName;
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

	public String getTeaName() {
		return teaName;
	}

	public void setTeaName(String teaName) {
		this.teaName = teaName;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getEduArea() {
		return eduArea;
	}

	public void setEduArea(String eduArea) {
		this.eduArea = eduArea;
	}

	public String getEduSchool() {
		return eduSchool;
	}

	public void setEduSchool(String eduSchool) {
		this.eduSchool = eduSchool;
	}

	public String getEduSubject() {
		return eduSubject;
	}

	public void setEduSubject(String eduSubject) {
		this.eduSubject = eduSubject;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEduSchoolStep() {
		return eduSchoolStep;
	}

	public void setEduSchoolStep(String eduSchoolStep) {
		this.eduSchoolStep = eduSchoolStep;
	}

	public String getSchoolStepName() {
		return schoolStepName;
	}

	public void setSchoolStepName(String schoolStepName) {
		this.schoolStepName = schoolStepName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public Long getViewNum() {
		return viewNum;
	}

	public void setViewNum(Long viewNum) {
		this.viewNum = viewNum;
	}
}
