package com.yuxin.wx.model.system;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.util.ShortDateSerializer;

/**
 * POJO:SysConfigTerm
 * @author wang.zx
 * @date 2014-12-5
 */
@SuppressWarnings("serial")
public class SysConfigTerm extends BaseEntity {
	
	
	private Integer	itemOneId;		 /* 所属一级项目id */ 
	private String	termName;		 /* 考期名称 */ 
	private String	remark;		 	 /* 备注 */ 
	private Date	createTime;		
	private Integer	creator;		
	private Date	updateTime;		
	private Integer	updator;		
	private Integer	delFlag;		 /* 删除标记：1：已删除；0：未删除 */ 
	private Integer schoolId;
	private String schoolName;
	private Integer companyId;
	
	// Constructor
	public SysConfigTerm() {
	}
	
	/**
	 * full Constructor
	 */
	public SysConfigTerm(Integer id, Integer itemOneId, String termName, String remark, Date createTime, Integer creator, Date updateTime, Integer updator, Integer delFlag) {
		setId(id);
		this.itemOneId = itemOneId;
		this.termName = termName;
		this.remark = remark;
		this.createTime = createTime;
		this.creator = creator;
		this.updateTime = updateTime;
		this.updator = updator;
		this.delFlag = delFlag;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为SysConfigTerm可以实现连缀设置属性
	
	public Integer getItemOneId() {
		return itemOneId;
	}

	public SysConfigTerm setItemOneId(Integer itemOneId) {
		this.itemOneId = itemOneId;
		return this;
	}
	
	
	public String getTermName() {
		return termName;
	}

	public SysConfigTerm setTermName(String termName) {
		this.termName = termName;
		return this;
	}
	
	
	public String getRemark() {
		return remark;
	}

	public SysConfigTerm setRemark(String remark) {
		this.remark = remark;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public SysConfigTerm setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	
	public Integer getCreator() {
		return creator;
	}

	public SysConfigTerm setCreator(Integer creator) {
		this.creator = creator;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getUpdateTime() {
		return updateTime;
	}

	public SysConfigTerm setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	
	
	public Integer getUpdator() {
		return updator;
	}

	public SysConfigTerm setUpdator(Integer updator) {
		this.updator = updator;
		return this;
	}
	
	
	public Integer getDelFlag() {
		return delFlag;
	}

	public SysConfigTerm setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
		return this;
	}
	
	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	@Override
	public String toString() {
		return "SysConfigTerm [" + "id=" + getId() + ", itemOneId=" + itemOneId + ", termName=" + termName + ", remark=" + remark + ", createTime=" + createTime + ", creator=" + creator + ", updateTime=" + updateTime + ", updator=" + updator + ", delFlag=" + delFlag +  "]";
	}
}
