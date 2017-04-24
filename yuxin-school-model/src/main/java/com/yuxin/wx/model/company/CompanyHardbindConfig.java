package com.yuxin.wx.model.company;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyHardbindConfig
 * 
 * @author wang.zx
 * @date 2016-2-18
 */
@SuppressWarnings("serial")
public class CompanyHardbindConfig extends BaseEntity {
	
	private String	name;		
	private Integer	companyId;		

	// Constructor
	public CompanyHardbindConfig() {
	}
	
	/**
	 * full Constructor
	 */
	public CompanyHardbindConfig(Integer id, String name, Integer companyId) {
		this.id = id;
		this.name = name;
		this.companyId = companyId;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CompanyHardbindConfig可以实现连缀设置属性
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	@Override
	public String toString() {
		return "CompanyHardbindConfig [id="+id+", name=" + name + ", companyId=" + companyId + "]";
	}
	
}
