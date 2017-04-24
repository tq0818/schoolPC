package com.yuxin.wx.model.system;


import java.util.Date;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:SysRegisterRequestLog
 * 
 * @author chopin
 * @date 2015-11-3
 */
@SuppressWarnings("serial")
public class SysRegisterRequestLog extends BaseEntity {
	
	
	private String	uuid;		
	private String	utmSource;		 /* 推广来源 */ 
	private String	utmMedium;		 /* 推广媒介 */ 
	private String	utmTerm;		 /* 推广关键词 */ 
	private String	utmContent;		 /* 推广内容 */ 
	private String	utmCampaign;		 /* 推广计划 */ 
	private Date	requestTime;		 /* 请求时间 */ 
	private String  utmUrl;

	// Constructor
	public SysRegisterRequestLog() {
	}
	
	/**
	 * full Constructor
	 */
	public SysRegisterRequestLog(Integer id, String uuid, String utmSource, String utmMedium, String utmTerm, String utmContent, String utmCampaign, Date requestTime) {
		setId(id);
		this.uuid = uuid;
		this.utmSource = utmSource;
		this.utmMedium = utmMedium;
		this.utmTerm = utmTerm;
		this.utmContent = utmContent;
		this.utmCampaign = utmCampaign;
		this.requestTime = requestTime;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为SysRegisterRequestLog可以实现连缀设置属性
	
	public String getUuid() {
		return uuid;
	}

	public SysRegisterRequestLog setUuid(String uuid) {
		this.uuid = uuid;
		return this;
	}
	
	
	public String getUtmSource() {
		return utmSource;
	}

	public SysRegisterRequestLog setUtmSource(String utmSource) {
		this.utmSource = utmSource;
		return this;
	}
	
	
	public String getUtmMedium() {
		return utmMedium;
	}

	public SysRegisterRequestLog setUtmMedium(String utmMedium) {
		this.utmMedium = utmMedium;
		return this;
	}
	
	
	public String getUtmTerm() {
		return utmTerm;
	}

	public SysRegisterRequestLog setUtmTerm(String utmTerm) {
		this.utmTerm = utmTerm;
		return this;
	}
	
	
	public String getUtmContent() {
		return utmContent;
	}

	public SysRegisterRequestLog setUtmContent(String utmContent) {
		this.utmContent = utmContent;
		return this;
	}
	
	
	public String getUtmCampaign() {
		return utmCampaign;
	}

	public SysRegisterRequestLog setUtmCampaign(String utmCampaign) {
		this.utmCampaign = utmCampaign;
		return this;
	}
	
	
	public Date getRequestTime() {
		return requestTime;
	}

	public SysRegisterRequestLog setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
		return this;
	}
	
	
	public String getUtmUrl() {
		return utmUrl;
	}

	public void setUtmUrl(String utmUrl) {
		this.utmUrl = utmUrl;
	}

	@Override
	public String toString() {
		return "SysRegisterRequestLog [" + "id=" + getId() + ", uuid=" + uuid + ", utmSource=" + utmSource + ", utmMedium=" + utmMedium + ", utmTerm=" + utmTerm + ", utmContent=" + utmContent + ", utmCampaign=" + utmCampaign + ", requestTime=" + requestTime +  "]";
	}
}
