package com.yuxin.wx.model.system;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:SysFrontLoginApi
 * 
 * @author chopin
 * @date 2015-12-9
 */
@SuppressWarnings("serial")
public class SysFrontLoginApi extends BaseEntity {
	
	
	private String	domain;		 /* 域名 */ 
	private Integer	companyId;		 /* 公司ID */ 

	// Constructor
	public SysFrontLoginApi() {
	}
	
	/**
	 * full Constructor
	 */
	public SysFrontLoginApi(Integer id, String domain, Integer companyId) {
		setId(id);
		this.domain = domain;
		this.companyId = companyId;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为SysFrontLoginApi可以实现连缀设置属性
	
	public String getDomain() {
		return domain;
	}

	public SysFrontLoginApi setDomain(String domain) {
		this.domain = domain;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public SysFrontLoginApi setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	@Override
	public String toString() {
		return "SysFrontLoginApi [" + "id=" + getId() + ", domain=" + domain + ", company=" + companyId +  "]";
	}
}
