package com.yuxin.wx.vo.student;



import com.yuxin.wx.common.BaseEntity;


public class StudentClassLeanDetailVo extends BaseEntity{
	private static final long serialVersionUID = 1L;
	/**
	 * 学生ID
	 */
	private Integer stuId;
	/**
	 * 版型ID
	 */
	private Integer classTypeId;
	private String classTypeName;
	/**
	 * 课次名称
	 */
	private String lectureName;
	/**
	 * 课次ID
	 */
	private Integer id;
	/**
	 * 课程属性
	 */
	private String teachMethod;
	/**
	 * 学习时间
	 */
	private String studyTime; 
	/**
	 * 完成状态
	 */
	private String studyStatus;
	/**
	 * 学习次数
	 */
	private Integer count;
	/**
	 * 学习时长
	 */
	private String lastTimeLone;
	/**
	 * 课程类型
	 */
	private String type;
	/**
	 * 课程包Id
	 */
	private Integer classPackageId;
	private Integer companyId;
	private Integer schoolId;
	
	private Integer commodityId;//商品id
	private String commodityType;//商品类型
	
	
	public String getClassTypeName() {
		return classTypeName;
	}
	public void setClassTypeName(String classTypeName) {
		this.classTypeName = classTypeName;
	}
	public Integer getCommodityId() {
		return commodityId;
	}
	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
	}
	public String getCommodityType() {
		return commodityType;
	}
	public void setCommodityType(String commodityType) {
		this.commodityType = commodityType;
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
	public Integer getClassPackageId() {
		return classPackageId;
	}
	public void setClassPackageId(Integer classPackageId) {
		this.classPackageId = classPackageId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStudyTime() {
		return studyTime;
	}
	public void setStudyTime(String studyTime) {
		this.studyTime = studyTime;
	}
	public String getLastTimeLone() {
		return lastTimeLone;
	}
	public void setLastTimeLone(String lastTimeLone) {
		this.lastTimeLone = lastTimeLone;
	}
	public Integer getStuId() {
		return stuId;
	}
	public void setStuId(Integer stuId) {
		this.stuId = stuId;
	}
	public Integer getClassTypeId() {
		return classTypeId;
	}
	public void setClassTypeId(Integer classTypeId) {
		this.classTypeId = classTypeId;
	}
	public String getLectureName() {
		return lectureName;
	}
	public void setLectureName(String lectureName) {
		this.lectureName = lectureName;
	}
	public String getTeachMethod() {
		return teachMethod;
	}
	public void setTeachMethod(String teachMethod) {
		this.teachMethod = teachMethod;
	}
	public String getStudyStatus() {
		return studyStatus;
	}
	public void setStudyStatus(String studyStatus) {
		this.studyStatus = studyStatus;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
