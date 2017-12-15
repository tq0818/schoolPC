package com.yuxin.wx.model.company;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyFootInfo
 * 
 * @author chopin
 * @date 2016-2-29
 */
@SuppressWarnings("serial")
public class CompanyFootInfo extends BaseEntity {
	
	
	private Integer	companyId;		 /* 公司ID */ 
	private String	tencentWeibo;		 /* 腾讯微博 */ 
	private String	sinaWeibo;		 /* 新浪微博 */ 
	private String	tencentWechat;		 /* 微信 */ 
	private String	logoType;		 /* LOGO类型 */ 
	private String	logoPic;		 /* LOGO图片 */ 
	private String	logoText;		 /* LOGO文字 */ 
	private String	copyright;		 /* 版权 */ 
	private String	companyName;		 /* 公司名称 */ 
	private String	overview;		 /* 其他信息 */ 
	private String	icpNo;		 /* icp备案号 */ 
	private Integer tencentWeiboFlag;
	private Integer sinaWeiboFlag;
	private Integer tencentWechatFlag;
	private String themes;
	
	private String securityIco;
	private String securityRegno;
	private String securityLink;
	
	private String icoPath;
	
	private String contactNumber;
	private String zhuCompanyId;

	public String getIcoPath() {
		return icoPath;
	}

	public void setIcoPath(String icoPath) {
		this.icoPath = icoPath;
	}

	// Constructor
	public CompanyFootInfo() {
	}
	
	/**
	 * full Constructor
	 */
	public CompanyFootInfo(Integer id, Integer companyId, String tencentWeibo, String sinaWeibo, String tencentWechat, String logoType, String logoPic, String logoText, String copyright, String companyName, String overview, String icpNo) {
		setId(id);
		this.companyId = companyId;
		this.tencentWeibo = tencentWeibo;
		this.sinaWeibo = sinaWeibo;
		this.tencentWechat = tencentWechat;
		this.logoType = logoType;
		this.logoPic = logoPic;
		this.logoText = logoText;
		this.copyright = copyright;
		this.companyName = companyName;
		this.overview = overview;
		this.icpNo = icpNo;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CompanyFootInfo可以实现连缀设置属性
	
	public String getSecurityIco() {
		return securityIco;
	}

	public void setSecurityIco(String securityIco) {
		this.securityIco = securityIco;
	}

	public String getSecurityRegno() {
		return securityRegno;
	}

	public void setSecurityRegno(String securityRegno) {
		this.securityRegno = securityRegno;
	}

	public String getSecurityLink() {
		return securityLink;
	}

	public void setSecurityLink(String securityLink) {
		this.securityLink = securityLink;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public String getThemes() {
		return themes;
	}

	public void setThemes(String themes) {
		this.themes = themes;
	}

	public CompanyFootInfo setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	public String getTencentWeibo() {
		return tencentWeibo;
	}

	public void setTencentWeibo(String tencentWeibo) {
		this.tencentWeibo = tencentWeibo;
	}

	public String getSinaWeibo() {
		return sinaWeibo;
	}

	public CompanyFootInfo setSinaWeibo(String sinaWeibo) {
		this.sinaWeibo = sinaWeibo;
		return this;
	}
	
	
	public String getTencentWechat() {
		return tencentWechat;
	}

	public CompanyFootInfo setTencentWechat(String tencentWechat) {
		this.tencentWechat = tencentWechat;
		return this;
	}
	
	
	public String getLogoType() {
		return logoType;
	}

	public CompanyFootInfo setLogoType(String logoType) {
		this.logoType = logoType;
		return this;
	}
	
	
	public String getLogoPic() {
		return logoPic;
	}

	public CompanyFootInfo setLogoPic(String logoPic) {
		this.logoPic = logoPic;
		return this;
	}
	
	
	public String getLogoText() {
		return logoText;
	}

	public CompanyFootInfo setLogoText(String logoText) {
		this.logoText = logoText;
		return this;
	}
	
	
	public String getCopyright() {
		return copyright;
	}

	public CompanyFootInfo setCopyright(String copyright) {
		this.copyright = copyright;
		return this;
	}
	
	
	public String getCompanyName() {
		return companyName;
	}

	public CompanyFootInfo setCompanyName(String companyName) {
		this.companyName = companyName;
		return this;
	}
	
	
	public String getOverview() {
		return overview;
	}

	public CompanyFootInfo setOverview(String overview) {
		this.overview = overview;
		return this;
	}
	
	
	public String getIcpNo() {
		return icpNo;
	}

	public CompanyFootInfo setIcpNo(String icpNo) {
		this.icpNo = icpNo;
		return this;
	}
	
	public Integer getTencentWeiboFlag() {
		return tencentWeiboFlag;
	}

	public void setTencentWeiboFlag(Integer tencentWeiboFlag) {
		this.tencentWeiboFlag = tencentWeiboFlag;
	}

	public Integer getSinaWeiboFlag() {
		return sinaWeiboFlag;
	}

	public void setSinaWeiboFlag(Integer sinaWeiboFlag) {
		this.sinaWeiboFlag = sinaWeiboFlag;
	}

	public Integer getTencentWechatFlag() {
		return tencentWechatFlag;
	}

	public void setTencentWechatFlag(Integer tencentWechatFlag) {
		this.tencentWechatFlag = tencentWechatFlag;
	}

	@Override
	public String toString() {
		return "CompanyFootInfo [" + "id=" + getId() + ", companyId=" + companyId + ", tencentWeibo=" + tencentWeibo + ", sinaWeibo=" + sinaWeibo + ", tencentWechat=" + tencentWechat + ", logoType=" + logoType + ", logoPic=" + logoPic + ", logoText=" + logoText + ", copyright=" + copyright + ", companyName=" + companyName + ", overview=" + overview + ", icpNo=" + icpNo +  "]";
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	
    public String getZhuCompanyId() {
    	return zhuCompanyId;
    }

	
    public void setZhuCompanyId(String zhuCompanyId) {
    	this.zhuCompanyId = zhuCompanyId;
    }
	
}
