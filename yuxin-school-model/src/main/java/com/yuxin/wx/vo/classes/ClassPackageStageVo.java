package com.yuxin.wx.vo.classes;

import java.util.Date;

import com.yuxin.wx.common.BaseEntity;

public class ClassPackageStageVo extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private Integer	classPackageId;		 /* 课程包ID */ 
	private String	title;		 /* 标题 */ 
	private Integer	creator;		 /* 创建人 */ 
	private Date	createTime;		 /* 创建时间 */ 
	private Integer	updator;		 /* 更新人 */ 
	private Date	updateTime;		 /* 更新时间 */ 
	private String	description;		 /* 描述 */ 
	private Integer	companyId;		 /* 公司ID */ 
	private Integer	schoolId;		 /* 分校ID */ 
	private Integer	sort;		 /* 阶段 */ 
	private Double originalPrice; /* 原价*/
	private Double realPrice;  /* 现价*/
	private String publishStatus;
	private Integer delFlag; 
	
	
	private Integer isBuy;  /*是否购买*/
	public Integer getClassPackageId() {
		return classPackageId;
	}
	public void setClassPackageId(Integer classPackageId) {
		this.classPackageId = classPackageId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getCreator() {
		return creator;
	}
	public void setCreator(Integer creator) {
		this.creator = creator;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getUpdator() {
		return updator;
	}
	public void setUpdator(Integer updator) {
		this.updator = updator;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public Double getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
	}
	public Double getRealPrice() {
		return realPrice;
	}
	public void setRealPrice(Double realPrice) {
		this.realPrice = realPrice;
	}
	public Integer getIsBuy() {
		return isBuy;
	}
	public void setIsBuy(Integer isBuy) {
		this.isBuy = isBuy;
	}
	public String getPublishStatus() {
		return publishStatus;
	}
	public void setPublishStatus(String publishStatus) {
		this.publishStatus = publishStatus;
	}
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

}
