package com.yuxin.wx.vo.course;

import com.yuxin.wx.common.BaseEntity;

@SuppressWarnings("serial")
public class UserVideoVo extends BaseEntity {

	private Integer userId;		 /* 观看用户ID */
	private String username;		 /* 观看用户名 */
	private String name;		 /* 观看用户姓名 */
	private Integer	commodityId;		 /* 商品ID */
	private Integer	classTypeId;		 /* 课程ID */
	private Long viewNum;		 /* 观看次数 */
	private String totleStudyLength;		 /* 播放总时长 */
	private String studyRate;	 /* 播放率 */
	private String courseName;	 /* 课程名称 */
	private String eduArea;	 /* 区域编码 */
	private String areaName;	 /* 区域名称 */
	private String eduSchoolStep;	 /* 学校属性编码 */
	private String stepName;	 /* 学校属性名称 */
	private String eduSchool;	 /* 学校编码 */
	private String schoolName;	 /* 学校名称 */
	private String eduSubject;	 /* 学科编码 */
	private String subjectName;	 /* 学科名称 */
	private String eduYear;	 /* 入学年份编码 */
	private String yearName;	 /* 入学年份名称 */
	private String eduClass;	 /* 班级编码 */
	private String className;	 /* 班级名称 */

	private String videoId;	 /* 播放视频源ID */
	private Integer companyId;	 /* 单位ID */
	private Integer schoolId;	 /* 学校ID */
	private String teaName;	 /* 教师名称 */
	private String startTime;	 /* 开始时间 */
	private String endTime;	 /* 结束时间 */
	private String fieldName;	 /* 排序字段 */
	private String sortType;	 /* 排序类型 */
	// Constructor
	public UserVideoVo() {
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

	public Long getViewNum() {
		return viewNum;
	}

	public void setViewNum(Long viewNum) {
		this.viewNum = viewNum;
	}

	public String getEduYear() {
		return eduYear;
	}

	public void setEduYear(String eduYear) {
		this.eduYear = eduYear;
	}

	public String getYearName() {
		return yearName;
	}

	public void setYearName(String yearName) {
		this.yearName = yearName;
	}

	public String getEduClass() {
		return eduClass;
	}

	public void setEduClass(String eduClass) {
		this.eduClass = eduClass;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
}
