package com.yuxin.wx.model.course;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CourseRemote
 * @author wang.zx
 * @date 2014-12-5
 */
@SuppressWarnings("serial")
public class CourseRemote extends BaseEntity {
	
	
	private String	name;		 /* 远程教育名称 */ 
	private Integer	itemOneId;		 /* 一级项目主键 */ 
	private Integer	itemSecondId;		 /* 二级项目主键 */ 
	private String  remoteDesc;		/*详情/描述*/
	private String  major;		/*专业*/
	private Integer	schoolId;
	private String	status;		 /* 课程状态（已启用；已停用）
取字典表数据 */ 
	private Date	createTime;		
	private Integer	creator;		
	private Date	updateTime;		
	private Integer	updator;		
	private Integer	delFlag;		 /* 删除标记：1：已删除；0：未删除 */ 
	private Integer	companyId;		 /* 公司id*/
	private String itemOneName;
	private String itemSecondName;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getItemOneId() {
		return itemOneId;
	}
	public void setItemOneId(Integer itemOneId) {
		this.itemOneId = itemOneId;
	}
	public Integer getItemSecondId() {
		return itemSecondId;
	}
	public void setItemSecondId(Integer itemSecondId) {
		this.itemSecondId = itemSecondId;
	}
	public Integer getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public CourseRemote(String name, Integer itemOneId, Integer itemSecondId,
			Integer schoolId, String status, Date createTime, Integer creator,
			Date updateTime, Integer updator, Integer delFlag, Integer companyId) {
		super();
		this.name = name;
		this.itemOneId = itemOneId;
		this.itemSecondId = itemSecondId;
		this.schoolId = schoolId;
		this.status = status;
		this.createTime = createTime;
		this.creator = creator;
		this.updateTime = updateTime;
		this.updator = updator;
		this.delFlag = delFlag;
		this.companyId = companyId;
	}
	public CourseRemote() {
		super();
	}
	
	public String getRemoteDesc() {
		return remoteDesc;
	}
	public void setRemoteDesc(String remoteDesc) {
		this.remoteDesc = remoteDesc;
	}
	
	public String getItemOneName() {
		return itemOneName;
	}
	public void setItemOneName(String itemOneName) {
		this.itemOneName = itemOneName;
	}
	public String getItemSecondName() {
		return itemSecondName;
	}
	public void setItemSecondName(String itemSecondName) {
		this.itemSecondName = itemSecondName;
	}
	@Override
	public String toString() {
		return "CourseRemote [name=" + name + ", itemOneId=" + itemOneId
				+ ", itemSecondId=" + itemSecondId + ", schoolId=" + schoolId
				+ ", status=" + status + ", createTime=" + createTime
				+ ", creator=" + creator + ", updateTime=" + updateTime
				+ ", updator=" + updator + ", delFlag=" + delFlag
				+ ", companyId=" + companyId + "]";
	} 
}
