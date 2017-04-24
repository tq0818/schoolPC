package com.yuxin.wx.model.system;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:SysConfigLivePrice
 * 
 * @author wang.zx
 * @date 2015-4-28
 */
@SuppressWarnings("serial")
public class SysConfigLivePrice extends BaseEntity {
	
	
	private Integer	companyServiveLevel;		 /* 会员类型：专业版及以下；专业版以上(对应标准服务表中的service_level) */ 
	private Integer	liveNumMin;		 /* 直播最小范围 */ 
	private Integer	liveNumMax;		 /* 直播并发最大范围 */ 
	private Double	unitPrice;		 /* 并发单价 */ 
	private Integer	unitMonth;		 /* 单次购买月数 */ 

	// Constructor
	public SysConfigLivePrice() {
	}
	
	/**
	 * full Constructor
	 */
	public SysConfigLivePrice(Integer id, Integer companyServiveLevel, Integer liveNumMin, Integer liveNumMax, Double unitPrice, Integer unitMonth) {
		setId(id);
		this.companyServiveLevel = companyServiveLevel;
		this.liveNumMin = liveNumMin;
		this.liveNumMax = liveNumMax;
		this.unitPrice = unitPrice;
		this.unitMonth = unitMonth;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为SysConfigLivePrice可以实现连缀设置属性
	
	public Integer getCompanyServiveLevel() {
		return companyServiveLevel;
	}

	public SysConfigLivePrice setCompanyServiveLevel(Integer companyServiveLevel) {
		this.companyServiveLevel = companyServiveLevel;
		return this;
	}
	
	
	public Integer getLiveNumMin() {
		return liveNumMin;
	}

	public SysConfigLivePrice setLiveNumMin(Integer liveNumMin) {
		this.liveNumMin = liveNumMin;
		return this;
	}
	
	
	public Integer getLiveNumMax() {
		return liveNumMax;
	}

	public SysConfigLivePrice setLiveNumMax(Integer liveNumMax) {
		this.liveNumMax = liveNumMax;
		return this;
	}
	
	
	public Double getUnitPrice() {
		return unitPrice;
	}

	public SysConfigLivePrice setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
		return this;
	}
	
	
	public Integer getUnitMonth() {
		return unitMonth;
	}

	public SysConfigLivePrice setUnitMonth(Integer unitMonth) {
		this.unitMonth = unitMonth;
		return this;
	}
	
	@Override
	public String toString() {
		return "SysConfigLivePrice [" + "id=" + getId() + ", companyServiveLevel=" + companyServiveLevel + ", liveNumMin=" + liveNumMin + ", liveNumMax=" + liveNumMax + ", unitPrice=" + unitPrice + ", unitMonth=" + unitMonth +  "]";
	}
}
