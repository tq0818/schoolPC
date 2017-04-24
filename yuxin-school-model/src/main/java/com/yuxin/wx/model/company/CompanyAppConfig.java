package com.yuxin.wx.model.company;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyAppConfig
 * 
 * @author wang.zx
 * @date 2016-5-30
 */
@SuppressWarnings("serial")
public class CompanyAppConfig extends BaseEntity {
	
	
	private String	color;		 /* 颜色 */ 
	private Integer	appMenuFlag;		 /* app菜单开关 */ 
	private String	iosDownUrl;		 /* ios下载地址 */ 
	private String	androidDownUrl;		 /* 安卓下载地址 */ 
	private String	appPageLink;		 /* app宣传页链接地址 */ 
	private String	qrcodeUrl;		 /* 二维码地址 */ 
	private Integer	appPageType;		 /* 宣传页类型 0-默认 1-自定义 */ 
	private Integer	companyId;		 /* 公司ID */ 

	// Constructor
	public CompanyAppConfig() {
	}
	
	/**
	 * full Constructor
	 */
	public CompanyAppConfig(Integer id, String color, Integer appMenuFlag, String iosDownUrl, String androidDownUrl, String appPageLink, String qrcodeUrl, Integer appPageType, Integer companyId) {
		setId(id);
		this.color = color;
		this.appMenuFlag = appMenuFlag;
		this.iosDownUrl = iosDownUrl;
		this.androidDownUrl = androidDownUrl;
		this.appPageLink = appPageLink;
		this.qrcodeUrl = qrcodeUrl;
		this.appPageType = appPageType;
		this.companyId = companyId;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CompanyAppConfig可以实现连缀设置属性
	
	public String getColor() {
		return color;
	}

	public CompanyAppConfig setColor(String color) {
		this.color = color;
		return this;
	}
	
	
	public Integer getAppMenuFlag() {
		return appMenuFlag;
	}

	public CompanyAppConfig setAppMenuFlag(Integer appMenuFlag) {
		this.appMenuFlag = appMenuFlag;
		return this;
	}
	
	
	public String getIosDownUrl() {
		return iosDownUrl;
	}

	public CompanyAppConfig setIosDownUrl(String iosDownUrl) {
		this.iosDownUrl = iosDownUrl;
		return this;
	}
	
	
	public String getAndroidDownUrl() {
		return androidDownUrl;
	}

	public CompanyAppConfig setAndroidDownUrl(String androidDownUrl) {
		this.androidDownUrl = androidDownUrl;
		return this;
	}
	
	
	public String getAppPageLink() {
		return appPageLink;
	}

	public CompanyAppConfig setAppPageLink(String appPageLink) {
		this.appPageLink = appPageLink;
		return this;
	}
	
	
	public String getQrcodeUrl() {
		return qrcodeUrl;
	}

	public CompanyAppConfig setQrcodeUrl(String qrcodeUrl) {
		this.qrcodeUrl = qrcodeUrl;
		return this;
	}
	
	
	public Integer getAppPageType() {
		return appPageType;
	}

	public CompanyAppConfig setAppPageType(Integer appPageType) {
		this.appPageType = appPageType;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public CompanyAppConfig setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	@Override
	public String toString() {
		return "CompanyAppConfig [" + "id=" + getId() + ", color=" + color + ", appMenuFlag=" + appMenuFlag + ", iosDownUrl=" + iosDownUrl + ", androidDownUrl=" + androidDownUrl + ", appPageLink=" + appPageLink + ", qrcodeUrl=" + qrcodeUrl + ", appPageType=" + appPageType + ", companyId=" + companyId +  "]";
	}
}
