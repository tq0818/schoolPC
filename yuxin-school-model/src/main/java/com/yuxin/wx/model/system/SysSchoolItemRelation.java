package com.yuxin.wx.model.system;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.util.ShortDateSerializer;

/**
 * POJO:SysSchoolItemRelation
 * 
 * @author chopin
 * @date 2015-3-12
 */
@SuppressWarnings("serial")
public class SysSchoolItemRelation extends BaseEntity {
	
	
	private Integer	schoolId;		 /* 分校id */ 
	private Integer	itemId;		 /* 项目id */ 
	private Integer	delFlag;		 /* 启用标记 */ 
	private Date	createTime;		 /* 订单创建时间 */ 
	private String	creator;		 /* 订单创建人（课程顾问） */ 
	private Date	updateTime;		 /* 订单最后更新时间 */ 
	private String	updator;		 /* 操作员（当前操作员） */ 
	private Integer trueDelFlag;		/* 删除标记*/

	// Constructor
	public SysSchoolItemRelation() {
	}
	
	/**
	 * full Constructor
	 */
	public SysSchoolItemRelation(Integer id, Integer schoolId, Integer itemId, Integer delFlag, Date createTime, String creator, Date updateTime, String updator) {
		setId(id);
		this.schoolId = schoolId;
		this.itemId = itemId;
		this.delFlag = delFlag;
		this.createTime = createTime;
		this.creator = creator;
		this.updateTime = updateTime;
		this.updator = updator;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为SysSchoolItemRelation可以实现连缀设置属性
	
	public Integer getSchoolId() {
		return schoolId;
	}

	public SysSchoolItemRelation setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
		return this;
	}
	
	
	public Integer getItemId() {
		return itemId;
	}

	public SysSchoolItemRelation setItemId(Integer itemId) {
		this.itemId = itemId;
		return this;
	}
	
	
	public Integer getDelFlag() {
		return delFlag;
	}

	public SysSchoolItemRelation setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public SysSchoolItemRelation setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	
	public String getCreator() {
		return creator;
	}

	public SysSchoolItemRelation setCreator(String creator) {
		this.creator = creator;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getUpdateTime() {
		return updateTime;
	}

	public SysSchoolItemRelation setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	
	
	public String getUpdator() {
		return updator;
	}

	public SysSchoolItemRelation setUpdator(String updator) {
		this.updator = updator;
		return this;
	}
	
	@Override
	public String toString() {
		return "SysSchoolItemRelation [" + "id=" + getId() + ", schoolId=" + schoolId + ", itemId=" + itemId + ", delFlag=" + delFlag + ", createTime=" + createTime + ", creator=" + creator + ", updateTime=" + updateTime + ", updator=" + updator +  "]";
	}

	public Integer getTrueDelFlag() {
		return trueDelFlag;
	}

	public void setTrueDelFlag(Integer trueDelFlag) {
		this.trueDelFlag = trueDelFlag;
	}
}
