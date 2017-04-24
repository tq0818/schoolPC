package com.yuxin.wx.model.system;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:SysServiceDredgeDetail
 * 
 * @author wang.zx
 * @date 2016-12-12
 */
@SuppressWarnings("serial")
public class SysServiceDredgeDetail extends BaseEntity {
	
	
	private Integer	privilegeId;		 /* 权限编码 */ 
	private Integer	display;		 /* 是否显示  0-不显示  1-显示 */ 
	private Double	payPrice;		 /* 实际支付价格 */ 
	private Integer	companyId;		
	private Integer	updator;		 /* 操作人 */ 
	private Date	updateTime;		

	// Constructor
	public SysServiceDredgeDetail() {
	}
	
	/**
	 * full Constructor
	 */
	public SysServiceDredgeDetail(Integer id, Integer privilegeId, Integer display, Double payPrice, Integer companyId, Integer updator, Date updateTime) {
		setId(id);
		this.privilegeId = privilegeId;
		this.display = display;
		this.payPrice = payPrice;
		this.companyId = companyId;
		this.updator = updator;
		this.updateTime = updateTime;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为SysServiceDredgeDetail可以实现连缀设置属性
	
	public Integer getPrivilegeId() {
		return privilegeId;
	}

	public SysServiceDredgeDetail setPrivilegeId(Integer privilegeId) {
		this.privilegeId = privilegeId;
		return this;
	}
	
	
	public Integer getDisplay() {
		return display;
	}

	public SysServiceDredgeDetail setDisplay(Integer display) {
		this.display = display;
		return this;
	}
	
	
	public Double getPayPrice() {
		return payPrice;
	}

	public SysServiceDredgeDetail setPayPrice(Double payPrice) {
		this.payPrice = payPrice;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public SysServiceDredgeDetail setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public Integer getUpdator() {
		return updator;
	}

	public SysServiceDredgeDetail setUpdator(Integer updator) {
		this.updator = updator;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getUpdateTime() {
		return updateTime;
	}

	public SysServiceDredgeDetail setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	
	@Override
	public String toString() {
		return "SysServiceDredgeDetail [" + "id=" + getId() + ", privilegeId=" + privilegeId + ", display=" + display + ", payPrice=" + payPrice + ", companyId=" + companyId + ", updator=" + updator + ", updateTime=" + updateTime +  "]";
	}
}
