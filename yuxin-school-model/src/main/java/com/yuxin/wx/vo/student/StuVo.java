package com.yuxin.wx.vo.student;

import java.util.Date;

import com.yuxin.wx.common.BaseEntity;

public class StuVo extends BaseEntity {
	
	private Date	applyTime;			/* 报名时间*/
	private String	classTypeName;		/* 班型*/
	private String	name;				/* 学员姓名*/
	private String	mobile;				/* 手机号*/
	private String	applyChannelCode;	/* 报名来源*/
	
	private Integer	resourceId;
	private Integer	companyId;
	private Integer	schoolId;
	
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	public String getClassTypeName() {
		return classTypeName;
	}
	public void setClassTypeName(String classTypeName) {
		this.classTypeName = classTypeName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getApplyChannelCode() {
		return applyChannelCode;
	}
	public void setApplyChannelCode(String applyChannelCode) {
		this.applyChannelCode = applyChannelCode;
	}
	
	public StuVo() {
		super();
	}
	public StuVo(Date applyTime, String classTypeName, String name,
			String mobile, String applyChannelCode) {
		super();
		this.applyTime = applyTime;
		this.classTypeName = classTypeName;
		this.name = name;
		this.mobile = mobile;
		this.applyChannelCode = applyChannelCode;
	}
	public Integer getResourceId() {
		return resourceId;
	}
	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
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
