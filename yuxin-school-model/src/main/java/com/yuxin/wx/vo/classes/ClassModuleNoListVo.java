package com.yuxin.wx.vo.classes;

import java.util.Date;

import com.yuxin.wx.common.BaseEntity;

/**
 * @Description:(用于展示班号List)
 * @author wang.zx 
 * @date 2015-1-9 上午11:49:20
 * @version 1.0
 */
public class ClassModuleNoListVo extends BaseEntity{
	
	
	/**
	 * @Fields serialVersionUID :(用一句话描述这个变量表示什么)
	*/
		
	private static final long serialVersionUID = 1L;
	private Integer	itemOneId;		 /* 所属一级项目id */ 
	private String itemName;    	 /* 一级项目名称  */
	private Integer	itemSecondId;		 /* 二级项目Id */
	private String	itemSecondName;		 /* 二级项目名称 */
	private Integer moduleId;		/*模块Id*/
	private String moduleName;		/*模块名称*/
	private String moduleType;     /*模块类型*/
	private String moduleDesc;     /*模块描述*/
	private Integer termId;			 /* 考期Id  */
	private String	termName;		 /* 考期名称 */
	private String name;		 /* 班号名称 */ 
	private Date startDate;		 /* 开课日期 */
	private String date;		 /* 展示开课日期,在后台做处理之后展示到前台 */
	private String classTeachType; /* 授课方式 */
	private Integer	enrollYetStudents;		 /* 实际招生人数 */ 
	
	private Integer	schoolId;		 /* 所属分校id，授课方式为面授时使用该字段 */ 
	private String	schoolName;		 /* 所属分校名称，授课方式为面授时使用该字段 */
	
	private Integer lessonCount;     /* 该班号一共有多少课次 */
	private String publishStatus;	 /* 班号状态 */
	
	private Integer	planEnrollStudents;		 /* 计划招生人数 */ 
	private Integer	totalHours;		 /* 班号总课时 */ 
	
	private Integer campusId;		/* 校区id */
	private String campusNo;		/* 校区编号 */
	private String campusName;		/* 校区名称 */
	
	private String description;     /* 班号描述 */
	
	private String lessonTotal;
	private String lessonPlan;
	private String mouId;
	private Integer classtypeId;
	
	public Integer getItemOneId() {
		return itemOneId;
	}
	public void setItemOneId(Integer itemOneId) {
		this.itemOneId = itemOneId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public Integer getItemSecondId() {
		return itemSecondId;
	}
	public void setItemSecondId(Integer itemSecondId) {
		this.itemSecondId = itemSecondId;
	}
	public String getItemSecondName() {
		return itemSecondName;
	}
	public void setItemSecondName(String itemSecondName) {
		this.itemSecondName = itemSecondName;
	}
	public Integer getTermId() {
		return termId;
	}
	public void setTermId(Integer termId) {
		this.termId = termId;
	}
	public String getTermName() {
		return termName;
	}
	public void setTermName(String termName) {
		this.termName = termName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getClassTeachType() {
		return classTeachType;
	}
	public void setClassTeachType(String classTeachType) {
		this.classTeachType = classTeachType;
	}
	public Integer getEnrollYetStudents() {
		return enrollYetStudents;
	}
	public void setEnrollYetStudents(Integer enrollYetStudents) {
		this.enrollYetStudents = enrollYetStudents;
	}
	public Integer getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public Integer getLessonCount() {
		return lessonCount;
	}
	public void setLessonCount(Integer lessonCount) {
		this.lessonCount = lessonCount;
	}
	public String getPublishStatus() {
		return publishStatus;
	}
	public void setPublishStatus(String publishStatus) {
		this.publishStatus = publishStatus;
	}
	public Integer getModuleId() {
		return moduleId;
	}
	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public Integer getPlanEnrollStudents() {
		return planEnrollStudents;
	}
	public void setPlanEnrollStudents(Integer planEnrollStudents) {
		this.planEnrollStudents = planEnrollStudents;
	}
	public Integer getTotalHours() {
		return totalHours;
	}
	public void setTotalHours(Integer totalHours) {
		this.totalHours = totalHours;
	}
	public Integer getCampusId() {
		return campusId;
	}
	public void setCampusId(Integer campusId) {
		this.campusId = campusId;
	}
	public String getCampusName() {
		return campusName;
	}
	public void setCampusName(String campusName) {
		this.campusName = campusName;
	}
	public String getModuleType() {
		return moduleType;
	}
	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}
	public String getModuleDesc() {
		return moduleDesc;
	}
	public void setModuleDesc(String moduleDesc) {
		this.moduleDesc = moduleDesc;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCampusNo() {
		return campusNo;
	}
	public void setCampusNo(String campusNo) {
		this.campusNo = campusNo;
	}
	public String getLessonTotal() {
		return lessonTotal;
	}
	public void setLessonTotal(String lessonTotal) {
		this.lessonTotal = lessonTotal;
	}
	public String getLessonPlan() {
		return lessonPlan;
	}
	public void setLessonPlan(String lessonPlan) {
		this.lessonPlan = lessonPlan;
	}
	public String getMouId() {
		return mouId;
	}
	public void setMouId(String mouId) {
		this.mouId = mouId;
	}
	public Integer getClasstypeId() {
		return classtypeId;
	}
	public void setClasstypeId(Integer classtypeId) {
		this.classtypeId = classtypeId;
	}
	
}
