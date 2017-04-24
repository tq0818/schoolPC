package com.yuxin.wx.model.company;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyHeadFootConfig
 * 
 * @author chopin
 * @date 2016-2-29
 */
@SuppressWarnings("serial")
public class CompanyHeadFootConfig extends BaseEntity {
	
	
	private Integer	companyId;		 /* 公司编号 */ 
	private Integer	templeteId;		 /* 模板编号 */ 
	private Integer	status;		 /* 状态 */ 

	// Constructor
	public CompanyHeadFootConfig() {
	}
	
	/**
	 * full Constructor
	 */
	public CompanyHeadFootConfig(Integer id, Integer companyId, Integer templeteId, Integer status) {
		setId(id);
		this.companyId = companyId;
		this.templeteId = templeteId;
		this.status = status;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CompanyHeadFootConfig可以实现连缀设置属性
	
	public Integer getCompanyId() {
		return companyId;
	}

	public CompanyHeadFootConfig setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public Integer getTempleteId() {
		return templeteId;
	}

	public CompanyHeadFootConfig setTempleteId(Integer templeteId) {
		this.templeteId = templeteId;
		return this;
	}
	
	
	public Integer getStatus() {
		return status;
	}

	public CompanyHeadFootConfig setStatus(Integer status) {
		this.status = status;
		return this;
	}
	
	@Override
	public String toString() {
		return "CompanyHeadFootConfig [" + "id=" + getId() + ", companyId=" + companyId + ", templeteId=" + templeteId + ", status=" + status +  "]";
	}
}
