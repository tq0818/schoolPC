package com.yuxin.wx.model.company;


import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyAppAuth
 * 
 * @author chopin
 * @date 2016-5-5
 */
@SuppressWarnings("serial")
public class CompanyAppAuth extends BaseEntity {
	
	
	private Integer	companyId;		 /* 公司ID */ 
	private Date	authorizeTime;		 /* 授权时间 */ 
	private String	accessKey;		 /* 唯一授权码 */ 
	private Integer	status;		 /* 状态 0-无效 1-有效 */ 
	private Date	endTime;		 /* 到期时间 null为无期限 */ 
	private String	version;		 /* 授权版本 */ 
	private String	platform;		 /* 平台 0-ios&Android 1-ios 2-android  */ 
	private String	domain;		 /* 公司域名 */ 
	public Integer buyFlag;

	// Constructor
	public CompanyAppAuth() {
	}
	
	

	public CompanyAppAuth(Integer companyId, Date authorizeTime, String accessKey, Integer status, Date endTime,
			String version, String platform, String domain, Integer buyFlag) {
		super();
		this.companyId = companyId;
		this.authorizeTime = authorizeTime;
		this.accessKey = accessKey;
		this.status = status;
		this.endTime = endTime;
		this.version = version;
		this.platform = platform;
		this.domain = domain;
		this.buyFlag = buyFlag;
	}



	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CompanyAppAuth可以实现连缀设置属性
	
	public Integer getBuyFlag() {
		return buyFlag;
	}



	public void setBuyFlag(Integer buyFlag) {
		this.buyFlag = buyFlag;
	}



	public Integer getCompanyId() {
		return companyId;
	}

	public CompanyAppAuth setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getAuthorizeTime() {
		return authorizeTime;
	}

	public CompanyAppAuth setAuthorizeTime(Date authorizeTime) {
		this.authorizeTime = authorizeTime;
		return this;
	}
	
	
	public String getAccessKey() {
		return accessKey;
	}

	public CompanyAppAuth setAccessKey(String accessKey) {
		this.accessKey = accessKey;
		return this;
	}
	
	
	public Integer getStatus() {
		return status;
	}

	public CompanyAppAuth setStatus(Integer status) {
		this.status = status;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getEndTime() {
		return endTime;
	}

	public CompanyAppAuth setEndTime(Date endTime) {
		this.endTime = endTime;
		return this;
	}
	
	
	public String getVersion() {
		return version;
	}

	public CompanyAppAuth setVersion(String version) {
		this.version = version;
		return this;
	}
	
	
	public String getPlatform() {
		return platform;
	}

	public CompanyAppAuth setPlatform(String platform) {
		this.platform = platform;
		return this;
	}
	
	
	public String getDomain() {
		return domain;
	}

	public CompanyAppAuth setDomain(String domain) {
		this.domain = domain;
		return this;
	}
	
	@Override
	public String toString() {
		return "CompanyAppAuth [" + "id=" + getId() + ", companyId=" + companyId + ", authorizeTime=" + authorizeTime + ", accessKey=" + accessKey + ", status=" + status + ", endTime=" + endTime + ", version=" + version + ", platform=" + platform + ", domain=" + domain +  "]";
	}
}
