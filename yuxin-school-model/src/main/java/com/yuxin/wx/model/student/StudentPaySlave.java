package com.yuxin.wx.model.student;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:StudentPaySlave
 * @author wang.zx
 * @date 2014-12-5
 */
@SuppressWarnings("serial")
public class StudentPaySlave extends BaseEntity {
	
	
	private Integer	stuId;		 /* 学生id */ 
	private Integer	payMasterId;		 /* 主订单id */ 
	private String	resourceType;		 /* 资源类型：直播、面授、视频、远程教育字典表 */ 
	private String	resourceId;		 /* 资 */ 
	private Integer	campusId;		 /* 所属校区id */ 
	private String	slaveStatusCode;		 /* 子订单状态，字典表 */ 
	private Integer moduleId;
	private Integer companyId;
	// Constructor
	public StudentPaySlave() {
	}
	
	/**
	 * full Constructor
	 */
	public StudentPaySlave(Integer id, Integer stuId, Integer payMasterId, String resourceType, String resourceId, Integer campusId, String slaveStatusCode,Integer companyId) {
		setId(id);
		this.stuId = stuId;
		this.payMasterId = payMasterId;
		this.resourceType = resourceType;
		this.resourceId = resourceId;
		this.campusId = campusId;
		this.slaveStatusCode = slaveStatusCode;
		this.companyId=companyId;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为StudentPaySlave可以实现连缀设置属性
	
	public Integer getStuId() {
		return stuId;
	}

	public StudentPaySlave setStuId(Integer stuId) {
		this.stuId = stuId;
		return this;
	}
	
	
	public Integer getPayMasterId() {
		return payMasterId;
	}

	public StudentPaySlave setPayMasterId(Integer payMasterId) {
		this.payMasterId = payMasterId;
		return this;
	}
	
	
	public String getResourceType() {
		return resourceType;
	}

	public StudentPaySlave setResourceType(String resourceType) {
		this.resourceType = resourceType;
		return this;
	}
	
	
	public String getResourceId() {
		return resourceId;
	}

	public StudentPaySlave setResourceId(String resourceId) {
		this.resourceId = resourceId;
		return this;
	}
	
	
	public Integer getCampusId() {
		return campusId;
	}

	public StudentPaySlave setCampusId(Integer campusId) {
		this.campusId = campusId;
		return this;
	}
	
	
	public String getSlaveStatusCode() {
		return slaveStatusCode;
	}

	public StudentPaySlave setSlaveStatusCode(String slaveStatusCode) {
		this.slaveStatusCode = slaveStatusCode;
		return this;
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
		return "StudentPaySlave [" + "id=" + getId() + ", stuId=" + stuId + ", payMasterId=" + payMasterId + ", resourceType=" + resourceType + ", resourceId=" + resourceId + ", campusId=" + campusId + ", slaveStatusCode=" + slaveStatusCode +  ",companyId="+companyId+"]";
	}
}
