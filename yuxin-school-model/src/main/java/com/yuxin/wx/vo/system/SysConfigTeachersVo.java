package com.yuxin.wx.vo.system;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:SysConfigTeacherLesson
 * @author wang.zx
 * @date 2014-12-5
 */
@SuppressWarnings("serial")
public class SysConfigTeachersVo extends BaseEntity {
	
	private String teacherName;
	private String mobile;
	private String sex;
	private Integer	itemOneId;		 /* 所属一级项目 */ 
	private Integer	itemSecondId;		 /* 所属二级项目 */ 
	private String	itemOneName;		 /* 所属一级项目 名称*/ 
	private String	itemSecondName;		 /* 所属二级项目 名称*/ 
	private Integer	moduleId;		 /* 授课模块 */ 
	private String	moduleName;		 /* 授课模块 */ 
	private Integer userId;
	private Integer schoolId;
	private Integer companyId;
	private Integer teacherType;

	// Constructor
	public SysConfigTeachersVo() {
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getItemOneId() {
		return itemOneId;
	}

	public void setItemOneId(Integer itemOneId) {
		this.itemOneId = itemOneId;
	}

	public Integer getItemSecondId() {
		return itemSecondId;
	}

	public void setItemSecondId(Integer itemSecondId) {
		this.itemSecondId = itemSecondId;
	}

	public String getItemOneName() {
		return itemOneName;
	}

	public void setItemOneName(String itemOneName) {
		this.itemOneName = itemOneName;
	}

	public String getItemSecondName() {
		return itemSecondName;
	}

	public void setItemSecondName(String itemSecondName) {
		this.itemSecondName = itemSecondName;
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public Integer getTeacherType() {
		return teacherType;
	}

	public void setTeacherType(Integer teacherType) {
		this.teacherType = teacherType;
	}
	
}
