package com.yuxin.wx.model.company;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyLoginConfig
 * 
 * @author chopin
 * @date 2016-7-4
 */
@SuppressWarnings("serial")
public class CompanyLoginConfig extends BaseEntity {
	
	
	private Integer	multiOnline;		 /* 是否允许多人在线  0-不允许 1-允许 */ 
	private Integer	qqLogin;		 /* 是否允许qq登录 */ 
	private Integer	wechatLogin;		 /* 是否允许微信登录 */ 
	private Integer	weiboLogin;		 /* 是否允许微博登陆 */ 
	private Integer	bingMobile;		 /* 是否必须绑定手机号 */ 
	private String	qqAppid;		 /* qq登录配置- appid */ 
	private String	qqKey;		 /* qq登登录配置-key */ 
	private String	wechatAppid;		 /* 微信登录配置-appid */ 
	private String	wechatKey;		 /* 微信登录配置-key */ 
	private String	weiboKey;		 /* 微博登录配置-key */ 
	private String	weiboSercet;		 /* 微博登录配置-密钥 */ 
	private Integer	companyId;		 /* 公司ID */ 
	private Integer thirdLoginFlag; /* 第三方登录开关 0-未开启（默认） 1-开启 */
	private String qqValidateCode;
	private String weiboValidateCode;
	private Integer useLoginPage;	/*是否使用登录页面 0-否 1-是*/
	
	private String wechatOpenAppid;
	private String wechatOpenSecret;

	public String getQqValidateCode() {
		return qqValidateCode;
	}

	public CompanyLoginConfig setQqValidateCode(String qqValidateCode) {
		this.qqValidateCode = qqValidateCode;
		return this;
	}

	public String getWeiboValidateCode() {
		return weiboValidateCode;
	}

	public CompanyLoginConfig setWeiboValidateCode(String weiboValidateCode) {
		this.weiboValidateCode = weiboValidateCode;
		return this;
	}

	public Integer getThirdLoginFlag() {
		return thirdLoginFlag;
	}

	public CompanyLoginConfig setThirdLoginFlag(Integer thirdLoginFlag) {
		this.thirdLoginFlag = thirdLoginFlag;
		return this;
	}

	// Constructor
	public CompanyLoginConfig() {
	}
	
	/**
	 * full Constructor
	 */
	public CompanyLoginConfig(Integer id, Integer multiOnline, Integer qqLogin, Integer wechatLogin, Integer weiboLogin, Integer bingMobile, String qqAppid, String qqKey, String wechatAppid, String wechatKey, String weiboKey, String weiboSercet, Integer companyId,Integer thirdLoginFlag,String qqValidateCode,String weiboValidateCode) {
		setId(id);
		this.multiOnline = multiOnline;
		this.qqLogin = qqLogin;
		this.wechatLogin = wechatLogin;
		this.weiboLogin = weiboLogin;
		this.bingMobile = bingMobile;
		this.qqAppid = qqAppid;
		this.qqKey = qqKey;
		this.wechatAppid = wechatAppid;
		this.wechatKey = wechatKey;
		this.weiboKey = weiboKey;
		this.weiboSercet = weiboSercet;
		this.companyId = companyId;
		this.thirdLoginFlag=thirdLoginFlag;
		this.qqValidateCode=qqValidateCode;
		this.weiboValidateCode=weiboValidateCode;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CompanyLoginConfig可以实现连缀设置属性
	
	public String getWechatOpenAppid() {
		return wechatOpenAppid;
	}

	public Integer getUseLoginPage() {
		return useLoginPage;
	}

	public void setUseLoginPage(Integer useLoginPage) {
		this.useLoginPage = useLoginPage;
	}

	public void setWechatOpenAppid(String wechatOpenAppid) {
		this.wechatOpenAppid = wechatOpenAppid;
	}

	public String getWechatOpenSecret() {
		return wechatOpenSecret;
	}

	public void setWechatOpenSecret(String wechatOpenSecret) {
		this.wechatOpenSecret = wechatOpenSecret;
	}

	public Integer getMultiOnline() {
		return multiOnline;
	}

	public CompanyLoginConfig setMultiOnline(Integer multiOnline) {
		this.multiOnline = multiOnline;
		return this;
	}
	
	
	public Integer getQqLogin() {
		return qqLogin;
	}

	public CompanyLoginConfig setQqLogin(Integer qqLogin) {
		this.qqLogin = qqLogin;
		return this;
	}
	
	
	public Integer getWechatLogin() {
		return wechatLogin;
	}

	public CompanyLoginConfig setWechatLogin(Integer wechatLogin) {
		this.wechatLogin = wechatLogin;
		return this;
	}
	
	
	public Integer getWeiboLogin() {
		return weiboLogin;
	}

	public CompanyLoginConfig setWeiboLogin(Integer weiboLogin) {
		this.weiboLogin = weiboLogin;
		return this;
	}
	
	
	public Integer getBingMobile() {
		return bingMobile;
	}

	public CompanyLoginConfig setBingMobile(Integer bingMobile) {
		this.bingMobile = bingMobile;
		return this;
	}
	
	
	public String getQqAppid() {
		return qqAppid;
	}

	public CompanyLoginConfig setQqAppid(String qqAppid) {
		this.qqAppid = qqAppid;
		return this;
	}
	
	
	public String getQqKey() {
		return qqKey;
	}

	public CompanyLoginConfig setQqKey(String qqKey) {
		this.qqKey = qqKey;
		return this;
	}
	
	
	public String getWechatAppid() {
		return wechatAppid;
	}

	public CompanyLoginConfig setWechatAppid(String wechatAppid) {
		this.wechatAppid = wechatAppid;
		return this;
	}
	
	
	public String getWechatKey() {
		return wechatKey;
	}

	public CompanyLoginConfig setWechatKey(String wechatKey) {
		this.wechatKey = wechatKey;
		return this;
	}
	
	
	public String getWeiboKey() {
		return weiboKey;
	}

	public CompanyLoginConfig setWeiboKey(String weiboKey) {
		this.weiboKey = weiboKey;
		return this;
	}
	
	
	public String getWeiboSercet() {
		return weiboSercet;
	}

	public CompanyLoginConfig setWeiboSercet(String weiboSercet) {
		this.weiboSercet = weiboSercet;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public CompanyLoginConfig setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	@Override
	public String toString() {
		return "CompanyLoginConfig [" + "id=" + getId() + ", multiOnline=" + multiOnline + ", qqLogin=" + qqLogin + ", wechatLogin=" + wechatLogin + ", weiboLogin=" + weiboLogin + ", bingMobile=" + bingMobile + ", qqAppid=" + qqAppid + ", qqKey=" + qqKey + ", wechatAppid=" + wechatAppid + ", wechatKey=" + wechatKey + ", weiboKey=" + weiboKey + ", weiboSercet=" + weiboSercet + ", companyId=" + companyId +  ", thirdLoginFlag=" + thirdLoginFlag +  ", qqValidateCode=" + qqValidateCode +  ", weiboValidateCode=" + weiboValidateCode +  "]";
	}
}
