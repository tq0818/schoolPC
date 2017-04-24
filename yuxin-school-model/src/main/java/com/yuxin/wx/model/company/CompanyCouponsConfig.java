package com.yuxin.wx.model.company;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyCouponsConfig
 * 
 * @author wang.zx
 * @date 2016-6-21
 */
@SuppressWarnings("serial")
public class CompanyCouponsConfig extends BaseEntity {
	
	
	private Integer	companyId;		
	private Integer	useWay;		 /* 使用类型：0-不允许叠加使用 1-允许叠加使用 */ 

	// Constructor
	public CompanyCouponsConfig() {
	}
	
	/**
	 * full Constructor
	 */
	public CompanyCouponsConfig(Integer id, Integer companyId, Integer useWay) {
		setId(id);
		this.companyId = companyId;
		this.useWay = useWay;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CompanyCouponsConfig可以实现连缀设置属性
	
	public Integer getCompanyId() {
		return companyId;
	}

	public CompanyCouponsConfig setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public Integer getUseWay() {
		return useWay;
	}

	public CompanyCouponsConfig setUseWay(Integer useWay) {
		this.useWay = useWay;
		return this;
	}
	
	@Override
	public String toString() {
		return "CompanyCouponsConfig [" + "id=" + getId() + ", companyId=" + companyId + ", useWay=" + useWay +  "]";
	}
}
