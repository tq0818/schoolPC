package com.yuxin.wx.model.system;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:SysConfigVideoPrice
 * 
 * @author wang.zx
 * @date 2015-4-28
 */
@SuppressWarnings("serial")
public class SysConfigVideoPrice extends BaseEntity {
	
	
	private Integer	companyServiveLevel;		 /* 会员类型：专业版及以下；专业版以上(对应标准服务表service_level) */ 
	private Integer	buyType;		 /* 购买类型（空间：1；流量：2） */ 
	private Integer	scopeMin;		 /* 购买最小范围 */ 
	private Integer	scopeMax;		 /* 购买最大范围 */ 
	private Double	unitPrice;		 /* 单价(0:联系客服) */ 
	private Double	exceedPrice;		 /* 超出部分单价 (0:无法上传) */ 

	// Constructor
	public SysConfigVideoPrice() {
	}
	
	/**
	 * full Constructor
	 */
	public SysConfigVideoPrice(Integer id, Integer companyServiveLevel, Integer buyType, Integer scopeMin, Integer scopeMax, Double unitPrice, Double exceedPrice) {
		setId(id);
		this.companyServiveLevel = companyServiveLevel;
		this.buyType = buyType;
		this.scopeMin = scopeMin;
		this.scopeMax = scopeMax;
		this.unitPrice = unitPrice;
		this.exceedPrice = exceedPrice;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为SysConfigVideoPrice可以实现连缀设置属性
	
	public Integer getCompanyServiveLevel() {
		return companyServiveLevel;
	}

	public SysConfigVideoPrice setCompanyServiveLevel(Integer companyServiveLevel) {
		this.companyServiveLevel = companyServiveLevel;
		return this;
	}
	
	
	public Integer getBuyType() {
		return buyType;
	}

	public SysConfigVideoPrice setBuyType(Integer buyType) {
		this.buyType = buyType;
		return this;
	}
	
	
	public Integer getScopeMin() {
		return scopeMin;
	}

	public SysConfigVideoPrice setScopeMin(Integer scopeMin) {
		this.scopeMin = scopeMin;
		return this;
	}
	
	
	public Integer getScopeMax() {
		return scopeMax;
	}

	public SysConfigVideoPrice setScopeMax(Integer scopeMax) {
		this.scopeMax = scopeMax;
		return this;
	}
	
	
	public Double getUnitPrice() {
		return unitPrice;
	}

	public SysConfigVideoPrice setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
		return this;
	}
	
	
	public Double getExceedPrice() {
		return exceedPrice;
	}

	public SysConfigVideoPrice setExceedPrice(Double exceedPrice) {
		this.exceedPrice = exceedPrice;
		return this;
	}
	
	@Override
	public String toString() {
		return "SysConfigVideoPrice [" + "id=" + getId() + ", companyServiveLevel=" + companyServiveLevel + ", buyType=" + buyType + ", scopeMin=" + scopeMin + ", scopeMax=" + scopeMax + ", unitPrice=" + unitPrice + ", exceedPrice=" + exceedPrice +  "]";
	}
}
