package com.yuxin.wx.vo.system;

import java.util.Date;

import com.yuxin.wx.common.BaseEntity;

public class SysConfigItemsVo extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	private String itemName; /* 项目名称 */
	private String itemType; /*
							 * 项目类型（1:一级项目；2:二级项目） 取字典表数据
							 */
	private Integer parentId; /* 父节点id */
	private String status; /* 项目启用状态：1（启用）；0（未启用） */
	private Integer delFlag; /* 删除标记：1（已删除）；0（未删除） */
	private String remark; /* 备注 */
	private Date createTime;
	private Integer creator;
	private Date updateTime;
	private Integer updator;
	private Integer companyId;
	private String itemPic;/* 图片URL */
	private Integer schoolId;
	private String itemBackPic; /*新地址*/

	private Integer relationStatus;
	private Integer sort;
	
	private String parentName;
	private String orderBy;

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getItemPic() {
		return itemPic;
	}

	public void setItemPic(String itemPic) {
		this.itemPic = itemPic;
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public String getItemBackPic() {
		return itemBackPic;
	}

	public void setItemBackPic(String itemBackPic) {
		this.itemBackPic = itemBackPic;
	}

	public Integer getRelationStatus() {
		return relationStatus;
	}

	public void setRelationStatus(Integer relationStatus) {
		this.relationStatus = relationStatus;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
}
