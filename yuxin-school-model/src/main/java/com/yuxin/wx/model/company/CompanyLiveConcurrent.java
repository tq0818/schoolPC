package com.yuxin.wx.model.company;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyLiveConcurrent
 * 
 * @author wang.zx
 * @date 2016-3-28
 */
@SuppressWarnings("serial")
public class CompanyLiveConcurrent extends BaseEntity {
	
	
	private Integer	companyId;		
	private Integer	concurrentMonth;		 /* 并发月份，例如201603 */ 
	private Integer	concurrentMax;		 /* 最大并发数 */ 
	private Integer	zhuCompanyId;		 

	// Constructor
	public CompanyLiveConcurrent() {
	}
	
	/**
	 * full Constructor
	 */
	public CompanyLiveConcurrent(Integer id, Integer companyId, Integer concurrentMonth, Integer concurrentMax) {
		setId(id);
		this.companyId = companyId;
		this.concurrentMonth = concurrentMonth;
		this.concurrentMax = concurrentMax;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CompanyLiveConcurrent可以实现连缀设置属性
	
	public Integer getCompanyId() {
		return companyId;
	}

	public CompanyLiveConcurrent setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public Integer getConcurrentMonth() {
		return concurrentMonth;
	}

	public CompanyLiveConcurrent setConcurrentMonth(Integer concurrentMonth) {
		this.concurrentMonth = concurrentMonth;
		return this;
	}
	
	
	public Integer getConcurrentMax() {
		return concurrentMax;
	}

	public CompanyLiveConcurrent setConcurrentMax(Integer concurrentMax) {
		this.concurrentMax = concurrentMax;
		return this;
	}
	
	
    public Integer getZhuCompanyId() {
    	return zhuCompanyId;
    }

	
    public void setZhuCompanyId(Integer zhuCompanyId) {
    	this.zhuCompanyId = zhuCompanyId;
    }

	@Override
	public String toString() {
		return "CompanyLiveConcurrent [" + "id=" + getId() + ", companyId=" + companyId + ", concurrentMonth=" + concurrentMonth + ", concurrentMax=" + concurrentMax +  "]";
	}
}
