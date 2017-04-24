package com.yuxin.wx.vo.system;

import java.util.Date;

import com.yuxin.wx.common.BaseEntity;

/**
 * @ClassName: TermItemTableVo
 * @Description:(用于校区列表查询)
 * @author ycl
 * @date 2015-05-05
 * @version 1.0
 */

@SuppressWarnings("serial")
public class ConfigCampusVo extends BaseEntity{
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
	private Integer classRoomNum;/*该校区下租用的教室数量*/
	public String getCampusNo() {
		return campusNo;
	}
	public void setCampusNo(String campusNo) {
		this.campusNo = campusNo;
	}
	public ConfigCampusVo(String campusNo, String campusName, Integer schoolId,
			Integer status, String remark, Date createTime, Integer creator,
			Date updateTime, Integer updator, Integer delFlag,
			Integer companyId, Integer classRoomNum) {
		super();
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
		this.classRoomNum = classRoomNum;
	}
	public ConfigCampusVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCampusName() {
		return campusName;
	}
	public void setCampusName(String campusName) {
		this.campusName = campusName;
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
	public Integer getClassRoomNum() {
		return classRoomNum;
	}
	public void setClassRoomNum(Integer classRoomNum) {
		this.classRoomNum = classRoomNum;
	}
}
