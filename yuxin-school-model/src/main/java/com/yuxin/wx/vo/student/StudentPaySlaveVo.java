package com.yuxin.wx.vo.student;


import java.util.Date;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:StudentPaySlave
 * @author wang.zx
 * @date 2014-12-5
 */
@SuppressWarnings("serial")
public class StudentPaySlaveVo extends BaseEntity {
	
	
	private Integer	stuId;		 /* 学生id */ 
	private Integer	payMasterId;		 /* 主订单id */ 
	private String	resourceType;		 /* 资源类型：直播、面授、视频、远程教育字典表 */ 
	private String	resourceId;		 /* 资 */ 
	private Integer	campusId;		 /* 所属校区id */ 
	private String	slaveStatusCode;		 /* 子订单状态，字典表 */ 
	private Integer moduleId;
	private Integer companyId;
	
	private String	name;		 /* 模块名称 */ 
	private Integer	itemOneId;		 /* 一级项目id */ 
	private Integer	itemSecondId;		 /* 二级项目id */ 
	private Integer	totalClassHour;		 /* 模块总课时 */ 
	private String	teachMethod;		 /* 授课方式（面授；直播；视频）字典表数据 */ 
	private String	moduleType;		 /* 模块类型，保留字段；前导课；理论课；实操课；串讲课；.。。。取字典表数据 */ 
	private String	moduleDesc;		 /* 模块的描述 */ 
	private Integer	schoolId;		 /* 所属分校 */ 
	private String	publishStatus;		 /* 模块发布状态（已发布；未发布；停用）取字典表数据 */ 
	private Date	publishTime;		 /* 模块发布时间 */ 
	private Integer	delFlag;		 /* 删除标记 */ 
	private Date	createTime;		 /* 创建时间 */ 
	private Integer	creator;		 /* 创建人 */ 
	private Date	updateTime;		 /* 修改时间 */ 
	private Integer	updator;		 /* 修改人 */ 
	private String moduleNo;
	
	private Integer pastLession;
	private Integer totalLession;
	
	//4.4版本添加，查询购买当前班号的学员数量，包括购买课程和课程包里面包含这个班号的学员数量
	private Integer classTypeId;

	// Constructor
	public StudentPaySlaveVo() {
	}
	
	/**
	 * full Constructor
	 */
	public StudentPaySlaveVo(Integer id, Integer stuId, Integer payMasterId,
			String resourceType, String resourceId, Integer campusId,
			String slaveStatusCode,String	name,Integer	itemOneId,Integer	itemSecondId,
			Integer	totalClassHour,String	teachMethod,String	moduleType,String	moduleDesc,
			Integer	schoolId,String	publishStatus,Date	publishTime,Integer	delFlag,Date	createTime,
			Integer	creator,Date	updateTime,Integer	updator,String moduleNo) {
		setId(id);
		this.stuId = stuId;
		this.payMasterId = payMasterId;
		this.resourceType = resourceType;
		this.resourceId = resourceId;
		this.campusId = campusId;
		this.slaveStatusCode = slaveStatusCode;
		this.name=name;
		this.itemOneId=itemOneId;
		this.itemSecondId=itemSecondId;
		this.totalClassHour=totalClassHour;
		this.teachMethod=teachMethod;
		this.moduleType=moduleType;
		this.moduleDesc=moduleDesc;
		this.moduleNo=moduleNo;
		this.schoolId=schoolId;
		this.publishStatus=publishStatus;
		this.publishTime=publishTime;
		this.delFlag=delFlag;
		this.createTime=createTime;
		this.creator=creator;
		this.updateTime=updateTime;
		this.updator=updator;
		this.moduleNo=moduleNo;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为StudentPaySlave可以实现连缀设置属性
	
	public Integer getClassTypeId() {
		return classTypeId;
	}

	public void setClassTypeId(Integer classTypeId) {
		this.classTypeId = classTypeId;
	}

	public Integer getStuId() {
		return stuId;
	}

	public StudentPaySlaveVo setStuId(Integer stuId) {
		this.stuId = stuId;
		return this;
	}
	
	
	public Integer getPayMasterId() {
		return payMasterId;
	}

	public StudentPaySlaveVo setPayMasterId(Integer payMasterId) {
		this.payMasterId = payMasterId;
		return this;
	}
	
	
	public String getResourceType() {
		return resourceType;
	}

	public StudentPaySlaveVo setResourceType(String resourceType) {
		this.resourceType = resourceType;
		return this;
	}
	
	
	public String getResourceId() {
		return resourceId;
	}

	public StudentPaySlaveVo setResourceId(String resourceId) {
		this.resourceId = resourceId;
		return this;
	}
	
	
	public Integer getCampusId() {
		return campusId;
	}

	public StudentPaySlaveVo setCampusId(Integer campusId) {
		this.campusId = campusId;
		return this;
	}
	
	
	public String getSlaveStatusCode() {
		return slaveStatusCode;
	}

	public StudentPaySlaveVo setSlaveStatusCode(String slaveStatusCode) {
		this.slaveStatusCode = slaveStatusCode;
		return this;
	}
	
	
	
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

	public Integer getTotalClassHour() {
		return totalClassHour;
	}

	public void setTotalClassHour(Integer totalClassHour) {
		this.totalClassHour = totalClassHour;
	}

	public String getTeachMethod() {
		return teachMethod;
	}

	public void setTeachMethod(String teachMethod) {
		this.teachMethod = teachMethod;
	}

	public String getModuleType() {
		return moduleType;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}

	public String getModuleDesc() {
		return moduleDesc;
	}

	public void setModuleDesc(String moduleDesc) {
		this.moduleDesc = moduleDesc;
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public String getPublishStatus() {
		return publishStatus;
	}

	public void setPublishStatus(String publishStatus) {
		this.publishStatus = publishStatus;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
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

	
	public String getModuleNo() {
		return moduleNo;
	}

	public void setModuleNo(String moduleNo) {
		this.moduleNo = moduleNo;
	}

	
	
	public Integer getPastLession() {
		return pastLession;
	}

	public void setPastLession(Integer pastLession) {
		this.pastLession = pastLession;
	}

	public Integer getTotalLession() {
		return totalLession;
	}

	public void setTotalLession(Integer totalLession) {
		this.totalLession = totalLession;
	}
	



	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	@Override
	public String toString() {
		return "StudentPaySlave [" + "id=" + getId() + ", stuId=" + stuId + ", payMasterId=" + payMasterId + ", resourceType=" + resourceType + ", resourceId=" + resourceId + ", campusId=" + campusId + ", slaveStatusCode=" + slaveStatusCode +  "]";
	}
}
