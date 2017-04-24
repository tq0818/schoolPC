package com.yuxin.wx.vo.classes;

import java.util.Date;

public class ClassPackageCategoryVo {
	
	private String	code;		 /* 分类号，每3位是一个级别 */ 
	private String	name;		 /* 名称 */ 
	private Integer	parentId;		 /* 父ID */ 
	private Integer	companyId;		 /* 公司ID */ 
	private Integer	schoolId;		 /* 分校ID */ 
	private Integer	status;		 /* 状态 */ 
	private Integer	delFlag;		 /* 删除标记 */ 
	private Integer	creator;		 /* 创建人 */ 
	private Date	createTime;		 /* 创建时间 */ 
	private Integer	updator;		 /* 更新人 */ 
	private Date	updateTime;		 /* 更新时间 */ 
	private Integer	sort;		 /* 排序 */ 
	
	private String leve;		//级别
	private Integer leveLength;//级别长度，二级6，三级9
	private String likeCode;   //根据一级的code值，模糊查询以一级开头的三级分类

	public String getLikeCode() {
		return likeCode;
	}

	public void setLikeCode(String likeCode) {
		this.likeCode = likeCode;
	}

	public Integer getLeveLength() {
		return leveLength;
	}

	public void setLeveLength(Integer leveLength) {
		this.leveLength = leveLength;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
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

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getLeve() {
		return leve;
	}

	public void setLeve(String leve) {
		this.leve = leve;
	}
	
}
