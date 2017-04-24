package com.yuxin.wx.vo.classes;

import com.yuxin.wx.common.BaseEntity;

public class ClassVo extends BaseEntity {
	private String	itemPic;				/* 项目图标*/
	private String	oneItemName;			/* 一级项目名*/
	private String	twoItemName;			/* 二级项目名*/
	private String	name;					/* 班号*/
	private String	classTeachType;			/* 授课方式（字典表）*/
	private String	campusName;				/* 校区名字*/
	private Integer	planEnrollStudents;		/* 计划招生人数 */ 
	private Integer	enrollYetStudents;		/* 实际招生人数 */ 
	private String	publishStatus;			/* 状态*/
	private String	moduleName;				/* 模块名*/
	private String	totalHourse;			/* 总课时*/
	private	Integer	lessonTotal;			/* 总课次*/
	private Integer	lessonPlan;				/* 已排课*/
	
	private Integer companyId;
	private Integer schoolId;
	private Integer oneItemId;
	private Integer twoItemId;
	private Integer termId;
	private String type;
	private Integer campusId;
	private String status;
	private String selNo;
	
	public String getItemPic() {
		return itemPic;
	}
	public void setItemPic(String itemPic) {
		this.itemPic = itemPic;
	}
	public String getOneItemName() {
		return oneItemName;
	}
	public void setOneItemName(String oneItemName) {
		this.oneItemName = oneItemName;
	}
	public String getTwoItemName() {
		return twoItemName;
	}
	public void setTwoItemName(String twoItemName) {
		this.twoItemName = twoItemName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClassTeachType() {
		return classTeachType;
	}
	public void setClassTeachType(String classTeachType) {
		this.classTeachType = classTeachType;
	}
	public String getCampusName() {
		return campusName;
	}
	public void setCampusName(String campusName) {
		this.campusName = campusName;
	}
	
	public ClassVo() {
		super();
	}
	public ClassVo(String itemPic, String oneItemName, String twoItemName, String name,
			String classTeachType, String campusName) {
		super();
		this.itemPic = itemPic;
		this.oneItemName = oneItemName;
		this.twoItemName = twoItemName;
		this.name = name;
		this.classTeachType = classTeachType;
		this.campusName = campusName;
	}
	public Integer getPlanEnrollStudents() {
		return planEnrollStudents;
	}
	public void setPlanEnrollStudents(Integer planEnrollStudents) {
		this.planEnrollStudents = planEnrollStudents;
	}
	public Integer getEnrollYetStudents() {
		return enrollYetStudents;
	}
	public void setEnrollYetStudents(Integer enrollYetStudents) {
		this.enrollYetStudents = enrollYetStudents;
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
	public Integer getOneItemId() {
		return oneItemId;
	}
	public void setOneItemId(Integer oneItemId) {
		this.oneItemId = oneItemId;
	}
	public Integer getTwoItemId() {
		return twoItemId;
	}
	public void setTwoItemId(Integer twoItemId) {
		this.twoItemId = twoItemId;
	}
	public Integer getTermId() {
		return termId;
	}
	public void setTermId(Integer termId) {
		this.termId = termId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getCampusId() {
		return campusId;
	}
	public void setCampusId(Integer campusId) {
		this.campusId = campusId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSelNo() {
		return selNo;
	}
	public void setSelNo(String selNo) {
		this.selNo = selNo;
	}
	public String getPublishStatus() {
		return publishStatus;
	}
	public void setPublishStatus(String publishStatus) {
		this.publishStatus = publishStatus;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getTotalHourse() {
		return totalHourse;
	}
	public void setTotalHourse(String totalHourse) {
		this.totalHourse = totalHourse;
	}
	public Integer getLessonTotal() {
		return lessonTotal;
	}
	public void setLessonTotal(Integer lessonTotal) {
		this.lessonTotal = lessonTotal;
	}
	public Integer getLessonPlan() {
		return lessonPlan;
	}
	public void setLessonPlan(Integer lessonPlan) {
		this.lessonPlan = lessonPlan;
	}
}
