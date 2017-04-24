package com.yuxin.wx.model.student;


import java.util.Date;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:StudentAgentMaterial
 * @author wang.zx
 * @date 2014-12-5
 */
@SuppressWarnings("serial")
public class StudentAgentMaterial extends BaseEntity {
	
	
	private String	stuId;		 /* 学生id */ 
	private Integer	payMasterId;		 /* 报名主订单id */ 
	private String	materialTypeCode;		 /* 资料类型（1寸；2寸；身份证；学历证；学位证；证明材料；其他），字典表 */ 
	private String	materialName;		 /* 资料名字 */ 
	private Integer	materialNumYet;		 /* 已提交数量 */ 
	private Integer	materialNumNot;		 /* 未提交数量 */ 
	private Date createTime;
	private Integer creator;
	private Date updateTime;
	private Integer updator;
	private Integer companyId;

	// Constructor
	public StudentAgentMaterial() {
	}
	
	/**
	 * full Constructor
	 */
	public StudentAgentMaterial(Integer id, String stuId, Integer payMasterId, String materialTypeCode, String materialName, Integer materialNumYet, Integer materialNumNot) {
		setId(id);
		this.stuId = stuId;
		this.payMasterId = payMasterId;
		this.materialTypeCode = materialTypeCode;
		this.materialName = materialName;
		this.materialNumYet = materialNumYet;
		this.materialNumNot = materialNumNot;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为StudentAgentMaterial可以实现连缀设置属性
	
	public String getStuId() {
		return stuId;
	}

	public StudentAgentMaterial setStuId(String stuId) {
		this.stuId = stuId;
		return this;
	}
	
	
	public Integer getPayMasterId() {
		return payMasterId;
	}

	public StudentAgentMaterial setPayMasterId(Integer payMasterId) {
		this.payMasterId = payMasterId;
		return this;
	}
	
	
	public String getMaterialTypeCode() {
		return materialTypeCode;
	}

	public StudentAgentMaterial setMaterialTypeCode(String materialTypeCode) {
		this.materialTypeCode = materialTypeCode;
		return this;
	}
	
	
	public String getMaterialName() {
		return materialName;
	}

	public StudentAgentMaterial setMaterialName(String materialName) {
		this.materialName = materialName;
		return this;
	}
	
	
	public Integer getMaterialNumYet() {
		return materialNumYet;
	}

	public StudentAgentMaterial setMaterialNumYet(Integer materialNumYet) {
		this.materialNumYet = materialNumYet;
		return this;
	}
	
	
	public Integer getMaterialNumNot() {
		return materialNumNot;
	}

	public StudentAgentMaterial setMaterialNumNot(Integer materialNumNot) {
		this.materialNumNot = materialNumNot;
		return this;
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

	@Override
	public String toString() {
		return "StudentAgentMaterial [" + "id=" + getId() + ", stuId=" + stuId + ", payMasterId=" + payMasterId + ", materialTypeCode=" + materialTypeCode + ", materialName=" + materialName + ", materialNumYet=" + materialNumYet + ", materialNumNot=" + materialNumNot +  "]";
	}
	
}
