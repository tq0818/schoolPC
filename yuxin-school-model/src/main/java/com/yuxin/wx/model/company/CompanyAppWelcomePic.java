package com.yuxin.wx.model.company;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyAppWelcomePic
 * 
 * @author chopin
 * @date 2016-5-5
 */
@SuppressWarnings("serial")
public class CompanyAppWelcomePic extends BaseEntity {
	
	
	private String	url;		 /* 地址 */ 
	private Integer	seqence;		 /* 顺序 */ 
	private Integer	companyId;		 /* 公司ID */ 
	private Integer	status;		 /* 状态 0-无效 1-有效 */ 

	// Constructor
	public CompanyAppWelcomePic() {
	}
	
	/**
	 * full Constructor
	 */
	public CompanyAppWelcomePic(Integer id, String url, Integer seqence, Integer companyId, Integer status) {
		setId(id);
		this.url = url;
		this.seqence = seqence;
		this.companyId = companyId;
		this.status = status;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CompanyAppWelcomePic可以实现连缀设置属性
	
	public String getUrl() {
		return url;
	}

	public CompanyAppWelcomePic setUrl(String url) {
		this.url = url;
		return this;
	}
	
	
	public Integer getSeqence() {
		return seqence;
	}

	public CompanyAppWelcomePic setSeqence(Integer seqence) {
		this.seqence = seqence;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public CompanyAppWelcomePic setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public Integer getStatus() {
		return status;
	}

	public CompanyAppWelcomePic setStatus(Integer status) {
		this.status = status;
		return this;
	}
	
	@Override
	public String toString() {
		return "CompanyAppWelcomePic [" + "id=" + getId() + ", url=" + url + ", seqence=" + seqence + ", companyId=" + companyId + ", status=" + status +  "]";
	}
}
