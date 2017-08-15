package com.yuxin.wx.model.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.util.ShortDateSerializer;

/**
 * POJO:SysConfigItem
 * @author wang.zx
 * @date 2014-12-5
 */
@SuppressWarnings("serial")
public class SysConfigItem extends BaseEntity {

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
	private String itemCode;
	private String parentCode;
	
	private Integer level;
	
	private String levelPath;
	

	private List<SysConfigItem> childrenList = new ArrayList<SysConfigItem>();
	
	public String getLevelPath() {
		return levelPath;
	}

	public void setLevelPath(String levelPath) {
		this.levelPath = levelPath;
	}

	
	
	
	public List<SysConfigItem> getChildrenList() {
		return childrenList;
	}

	public void setChildrenList(List<SysConfigItem> childrenList) {
		this.childrenList = childrenList;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	// Constructor
	public SysConfigItem() {
	}

	/**
	 * full Constructor
	 */
	public SysConfigItem(Integer id, String itemName, String itemType,
			Integer parentId, String status, Integer delFlag, String remark,
			Date createTime, Integer creator, Date updateTime, Integer updator) {
		setId(id);
		this.itemName = itemName;
		this.itemType = itemType;
		this.parentId = parentId;
		this.status = status;
		this.delFlag = delFlag;
		this.remark = remark;
		this.createTime = createTime;
		this.creator = creator;
		this.updateTime = updateTime;
		this.updator = updator;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为SysConfigItem可以实现连缀设置属性

	public String getItemName() {
		return itemName;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public SysConfigItem setItemName(String itemName) {
		this.itemName = itemName;
		return this;
	}

	public String getItemType() {
		return itemType;
	}

	public SysConfigItem setItemType(String itemType) {
		this.itemType = itemType;
		return this;
	}

	public Integer getParentId() {
		return parentId;
	}

	public SysConfigItem setParentId(Integer parentId) {
		this.parentId = parentId;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public SysConfigItem setStatus(String status) {
		this.status = status;
		return this;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public SysConfigItem setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
		return this;
	}

	public String getRemark() {
		return remark;
	}

	public SysConfigItem setRemark(String remark) {
		this.remark = remark;
		return this;
	}

	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public SysConfigItem setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}

	public Integer getCreator() {
		return creator;
	}

	public SysConfigItem setCreator(Integer creator) {
		this.creator = creator;
		return this;
	}

	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getUpdateTime() {
		return updateTime;
	}

	public SysConfigItem setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public Integer getUpdator() {
		return updator;
	}

	public SysConfigItem setUpdator(Integer updator) {
		this.updator = updator;
		return this;
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

	@Override
	public String toString() {
		return "SysConfigItem [" + "id=" + getId() + ", itemName=" + itemName
				+ ", itemType=" + itemType + ", parentId=" + parentId
				+ ", status=" + status + ", delFlag=" + delFlag + ", remark="
				+ remark + ", createTime=" + createTime + ", creator="
				+ creator + ", updateTime=" + updateTime + ", updator="
				+ updator + "]";
	}

	public Integer getRelationStatus() {
		return relationStatus;
	}

	public void setRelationStatus(Integer relationStatus) {
		this.relationStatus = relationStatus;
	}

	public String getItemBackPic() {
		return itemBackPic;
	}

	public void setItemBackPic(String itemBackPic) {
		this.itemBackPic = itemBackPic;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

}
