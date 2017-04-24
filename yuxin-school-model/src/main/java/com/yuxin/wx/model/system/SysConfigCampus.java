package com.yuxin.wx.model.system;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.util.ShortDateSerializer;

/**
 * POJO:SysConfigCampus
 * @author wang.zx
 * @date 2014-12-5
 */
@SuppressWarnings("serial")
public class SysConfigCampus extends BaseEntity {
	
	
	private String	campusNo;		 /* 校区编号 */ 
	private String	campusName;		 /* 校区名称 */ 
	private Integer	schoolId;		
	private Integer	status;		 /* 校区状态(正常，停用)字典表数据 */ 
	private String	remark;		 /* 备注 */ 
	private Date	createTime;		
	private Integer	creator;		
	private Date	updateTime;		
	private Integer	updator;		
	private Integer	delFlag;		 /* 删除标记：1：已删除；0：未删除 */ 
	private Integer companyId;		/* 公司ID */


	// Constructor
	public SysConfigCampus() {
	}
	
	/**
	 * full Constructor
	 */
	public SysConfigCampus(Integer id, String campusNo, String campusName, Integer schoolId, Integer status, String remark, Date createTime, Integer creator, Date updateTime, Integer updator, Integer delFlag, Integer companyId) {
		setId(id);
		this.campusNo = campusNo;
		this.campusName = campusName;
		this.schoolId = schoolId;
		this.status = status;
		this.remark = remark;
		this.createTime = createTime;
		this.creator = creator;
		this.updateTime = updateTime;
		this.updator = updator;
		this.delFlag = delFlag;
		this.companyId = companyId;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为SysConfigCampus可以实现连缀设置属性
	
	public String getCampusNo() {
		return campusNo;
	}

	public SysConfigCampus setCampusNo(String campusNo) {
		this.campusNo = campusNo;
		return this;
	}
	
	
	public String getCampusName() {
		return campusName;
	}

	public SysConfigCampus setCampusName(String campusName) {
		this.campusName = campusName;
		return this;
	}
	
	
	public Integer getSchoolId() {
		return schoolId;
	}

	public SysConfigCampus setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
		return this;
	}
	
	
	public Integer getStatus() {
		return status;
	}

	public SysConfigCampus setStatus(Integer status) {
		this.status = status;
		return this;
	}
	
	
	public String getRemark() {
		return remark;
	}

	public SysConfigCampus setRemark(String remark) {
		this.remark = remark;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public SysConfigCampus setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	
	public Integer getCreator() {
		return creator;
	}

	public SysConfigCampus setCreator(Integer creator) {
		this.creator = creator;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getUpdateTime() {
		return updateTime;
	}

	public SysConfigCampus setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	
	
	public Integer getUpdator() {
		return updator;
	}

	public SysConfigCampus setUpdator(Integer updator) {
		this.updator = updator;
		return this;
	}
	
	
	public Integer getDelFlag() {
		return delFlag;
	}

	public SysConfigCampus setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
		return this;
	}
	
	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	@Override
	public String toString() {
		return "SysConfigCampus [" + "id=" + getId() + ", campusNo=" + campusNo + ", campusName=" + campusName + ", schoolId=" + schoolId + ", status=" + status + ", remark=" + remark + ", createTime=" + createTime + ", creator=" + creator + ", updateTime=" + updateTime + ", updator=" + updator + ", delFlag=" + delFlag + ", companyId=" + companyId + "]";
	}
}
