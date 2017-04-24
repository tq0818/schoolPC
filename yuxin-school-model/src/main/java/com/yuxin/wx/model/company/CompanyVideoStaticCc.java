package com.yuxin.wx.model.company;

import java.util.Date;

import com.yuxin.wx.common.BaseEntity;

public class CompanyVideoStaticCc extends BaseEntity {
	
	private Integer companyId;			/* 公司id*/
	private Date	staticDate;			/* 统计日期*/
	private Double	totalFlow;			/* 总流量*/
	private Double	usedFlow;			/* 使用流量*/
	private Double	remainFlow;			/* 剩余流量*/
	private Double	totalStorage;		/* 总空间*/
	private Double	usedStorage;		/* 使用空间*/
	private Double	remainStorage;		/* 剩余空间*/
	
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public Date getStaticDate() {
		return staticDate;
	}
	public void setStaticDate(Date staticDate) {
		this.staticDate = staticDate;
	}
	public Double getTotalFlow() {
		return totalFlow;
	}
	public void setTotalFlow(Double totalFlow) {
		this.totalFlow = totalFlow;
	}
	public Double getUsedFlow() {
		return usedFlow;
	}
	public void setUsedFlow(Double usedFlow) {
		this.usedFlow = usedFlow;
	}
	public Double getRemainFlow() {
		return remainFlow;
	}
	public void setRemainFlow(Double remainFlow) {
		this.remainFlow = remainFlow;
	}
	public Double getTotalStorage() {
		return totalStorage;
	}
	public void setTotalStorage(Double totalStorage) {
		this.totalStorage = totalStorage;
	}
	public Double getUsedStorage() {
		return usedStorage;
	}
	public void setUsedStorage(Double usedStorage) {
		this.usedStorage = usedStorage;
	}
	public Double getRemainStorage() {
		return remainStorage;
	}
	public void setRemainStorage(Double remainStorage) {
		this.remainStorage = remainStorage;
	}
	
	public CompanyVideoStaticCc() {
		super();
	}
	public CompanyVideoStaticCc(Integer companyId, Date staticDate,
			Double totalFlow, Double usedFlow, Double remainFlow,
			Double totalStorage, Double usedStorage, Double remainStorage) {
		super();
		this.companyId = companyId;
		this.staticDate = staticDate;
		this.totalFlow = totalFlow;
		this.usedFlow = usedFlow;
		this.remainFlow = remainFlow;
		this.totalStorage = totalStorage;
		this.usedStorage = usedStorage;
		this.remainStorage = remainStorage;
	}
	
}
