package com.yuxin.wx.vo.company;


import java.util.Date;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:Company
 * 
 * @author zhang.zx
 * @date 2015-6-8
 */
public class companySpecialDomain{
	
	private Integer id;
	private String domainName;   /* 域名*/
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	
}
