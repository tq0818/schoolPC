package com.yuxin.wx.vo.student;

import java.util.Date;

import com.yuxin.wx.common.BaseEntity;

public class StudentXNVO extends BaseEntity{
	
	/**
	 * @Fields serialVersionUID : TODO(学员其他信息，虚拟表vo)
	*/
		
	private Integer id;
	private String gradeName;/*年级*/
	private String className;/*班级*/
	private String schoolName;/*学校*/
	private Integer stuId;/*学生id*/
	private Integer userFrontId;/*用户id*/
	private Integer rowId;/*表示属于一组数据*/
	private String tableName;/*虚拟表名*/
	private Integer companyId;/*公司id*/
	private Integer schoolId;/*分校id*/
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public Integer getStuId() {
		return stuId;
	}
	public void setStuId(Integer stuId) {
		this.stuId = stuId;
	}
	public Integer getUserFrontId() {
		return userFrontId;
	}
	public void setUserFrontId(Integer userFrontId) {
		this.userFrontId = userFrontId;
	}
	public Integer getRowId() {
		return rowId;
	}
	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
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
}
