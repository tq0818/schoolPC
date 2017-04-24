package com.yuxin.wx.model.company;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyHardBind
 * 
 * @author wang.zx
 * @date 2016-2-18
 */
@SuppressWarnings("serial")
public class CompanyHardbindData extends BaseEntity {
	
	
	private Integer	companyId;		 /* 公司ID */ 
	private String	ip;		 /* ip */ 
	private String	macAddress;		 /* mac地址 */ 
	private String	hdSerialnumber;		 /* 硬盘序列号 */ 
	private String	cpuSerialnumber;		 /* cpu序列号 */ 
	private String	domain;		 /* 域名 */ 
	private String  browser; /*浏览器*/

	// Constructor
	public CompanyHardbindData() {
	}
	
	/**
	 * full Constructor
	 */
	public CompanyHardbindData(Integer id, Integer companyId, String ip, String macAddress, String hdSerialnumber, String cpuSerialnumber, String domain) {
		setId(id);
		this.companyId = companyId;
		this.ip = ip;
		this.macAddress = macAddress;
		this.hdSerialnumber = hdSerialnumber;
		this.cpuSerialnumber = cpuSerialnumber;
		this.domain = domain;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CompanyHardBind可以实现连缀设置属性
	
	public Integer getCompanyId() {
		return companyId;
	}

	public CompanyHardbindData setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public String getIp() {
		return ip;
	}

	public CompanyHardbindData setIp(String ip) {
		this.ip = ip;
		return this;
	}
	
	
	public String getMacAddress() {
		return macAddress;
	}

	public CompanyHardbindData setMacAddress(String macAddress) {
		this.macAddress = macAddress;
		return this;
	}
	
	
	public String getHdSerialnumber() {
		return hdSerialnumber;
	}

	public CompanyHardbindData setHdSerialnumber(String hdSerialnumber) {
		this.hdSerialnumber = hdSerialnumber;
		return this;
	}
	
	
	public String getCpuSerialnumber() {
		return cpuSerialnumber;
	}

	public CompanyHardbindData setCpuSerialnumber(String cpuSerialnumber) {
		this.cpuSerialnumber = cpuSerialnumber;
		return this;
	}
	
	
	public String getDomain() {
		return domain;
	}

	public CompanyHardbindData setDomain(String domain) {
		this.domain = domain;
		return this;
	}
	
	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	@Override
	public String toString() {
		return "CompanyHardBind [" + "id=" + getId() + ", companyId=" + companyId + ", ip=" + ip + ", macAddress=" + macAddress + ", hdSerialnumber=" + hdSerialnumber + ", cpuSerialnumber=" + cpuSerialnumber + ", domain=" + domain +  "]";
	}
}
