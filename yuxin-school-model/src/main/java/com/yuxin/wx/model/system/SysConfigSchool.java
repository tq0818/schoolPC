package com.yuxin.wx.model.system;

import java.util.Date;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:SysConfigSchool
 * @author wang.zx
 * @date 2014-12-5
 */
@SuppressWarnings("serial")
public class SysConfigSchool extends BaseEntity {
	
	
	private String	schoolName;		 /* 分校名称 */ 
	private String	schoolDesc;		 /* 分校描述 */ 
	private Date	createTime;		
	private Integer	creator;		
	private Date	updateTime;		
	private Integer	updator;		
	private Integer	delFlag;		 /* 删除标识,0:未删除;1:已删除 默认值为0 */ 
	private Integer	companyId;       /*机构id*/
	private Integer defaultFlag;
	
	private String overview;		/* 封面图*/
	private String indexDomain;		/* 域名*/
	/*private String category;		 类型  0：学校，1：机构*/
	private String xzqhCode;		/* 代码*/
	private String schoolType;     /* 分校类型： organ-机构 ， school-学校*/
	private String cusorder;
	private Integer schoolNum;  /* 分校招生人数*/
	private String mark;
	private String suffix;		/* 分校后缀*/
	
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getSchoolDesc() {
		return schoolDesc;
	}
	public void setSchoolDesc(String schoolDesc) {
		this.schoolDesc = schoolDesc;
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
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getUpdator() {
		return updator;
	}
	public void setUpdator(Integer updator) {
		this.updator = updator;
	}
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public Integer getDefaultFlag() {
		return defaultFlag;
	}
	public void setDefaultFlag(Integer defaultFlag) {
		this.defaultFlag = defaultFlag;
	}
	@Override
	public String toString() {
		return "SysConfigSchool [schoolName=" + schoolName + ", schoolDesc="
				+ schoolDesc + ", createTime=" + createTime + ", creator="
				+ creator + ", updateTime=" + updateTime + ", updator="
				+ updator + ", delFlag=" + delFlag + ", companyId=" + companyId
				+ "]";
	}
	public SysConfigSchool(String schoolName, String schoolDesc,
			Date createTime, Integer creator, Date updateTime, Integer updator,
			Integer delFlag, Integer companyId) {
		super();
		this.schoolName = schoolName;
		this.schoolDesc = schoolDesc;
		this.createTime = createTime;
		this.creator = creator;
		this.updateTime = updateTime;
		this.updator = updator;
		this.delFlag = delFlag;
		this.companyId = companyId;
	}
	public SysConfigSchool() {
		super();
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	public String getIndexDomain() {
		return indexDomain;
	}
	public void setIndexDomain(String indexDomain) {
		this.indexDomain = indexDomain;
	}
	/*public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}*/
	public String getXzqhCode() {
		return xzqhCode;
	}
	public void setXzqhCode(String xzqhCode) {
		this.xzqhCode = xzqhCode;
	}
	public String getCusorder() {
		return cusorder;
	}
	public void setCusorder(String cusorder) {
		this.cusorder = cusorder;
	}
	public Integer getSchoolNum() {
		return schoolNum;
	}
	public void setSchoolNum(Integer schoolNum) {
		this.schoolNum = schoolNum;
	}
	public String getSchoolType() {
		return schoolType;
	}
	public void setSchoolType(String schoolType) {
		this.schoolType = schoolType;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
}
