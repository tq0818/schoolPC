package com.yuxin.wx.model.company;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyRegisterConfig
 * 
 * @author chopin
 * @date 2016-7-4
 */
@SuppressWarnings("serial")
public class CompanyRegisterConfig extends BaseEntity {
	
	
	private Integer	mobileFlag;		 /* 手机注册 */ 
	private Integer	usernameFlag;		 /* 用户名注册 */ 
	private Integer	emailFlag;		 /* 邮箱注册 */ 
	private Integer	qqFlag;		 /* qq注册 */ 
	private Integer	wechatFlag;		 /* 微信注册 */ 
	private Integer	weiboFlag;		 /* 微博注册 */ 
	private Integer	closeFlag;		 /* 关闭注册 */ 
	private Integer	companyId;		 /* 公司ID */ 
	private String registerAgreement;	 /* 注册协议内容 */
	private Integer registerAgreementFlag;	 /* 注册协议开启状态 */
	private Integer validateEmailFlag;   /*邮箱验证*/
	private Integer jjwLogFlag;   /*继教网登录开关*/
	// Constructor
	public CompanyRegisterConfig() {
	}
	
	/**
	 * full Constructor
	 */
	public CompanyRegisterConfig(Integer id, Integer mobileFlag, Integer usernameFlag, Integer emailFlag, Integer qqFlag, Integer wechatFlag, Integer weiboFlag, Integer closeFlag, Integer companyId ,String registerAgreement ,Integer registerAgreementFlag) {
		setId(id);
		this.mobileFlag = mobileFlag;
		this.usernameFlag = usernameFlag;
		this.emailFlag = emailFlag;
		this.qqFlag = qqFlag;
		this.wechatFlag = wechatFlag;
		this.weiboFlag = weiboFlag;
		this.closeFlag = closeFlag;
		this.companyId = companyId;
		this.registerAgreement = registerAgreement;
		this.registerAgreementFlag = registerAgreementFlag;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CompanyRegisterConfig可以实现连缀设置属性
	
	public Integer getValidateEmailFlag() {
		return validateEmailFlag;
	}

	public void setValidateEmailFlag(Integer validateEmailFlag) {
		this.validateEmailFlag = validateEmailFlag;
	}

	public Integer getJjwLogFlag() {
		return jjwLogFlag;
	}

	public void setJjwLogFlag(Integer jjwLogFlag) {
		this.jjwLogFlag = jjwLogFlag;
	}

	public Integer getMobileFlag() {
		return mobileFlag;
	}

	public String getRegisterAgreement() {
		return registerAgreement;
	}

	public void setRegisterAgreement(String registerAgreement) {
		this.registerAgreement = registerAgreement;
	}

	public Integer getRegisterAgreementFlag() {
		return registerAgreementFlag;
	}

	public void setRegisterAgreementFlag(Integer registerAgreementFlag) {
		this.registerAgreementFlag = registerAgreementFlag;
	}

	public CompanyRegisterConfig setMobileFlag(Integer mobileFlag) {
		this.mobileFlag = mobileFlag;
		return this;
	}
	
	
	public Integer getUsernameFlag() {
		return usernameFlag;
	}

	public CompanyRegisterConfig setUsernameFlag(Integer usernameFlag) {
		this.usernameFlag = usernameFlag;
		return this;
	}
	
	
	public Integer getEmailFlag() {
		return emailFlag;
	}

	public CompanyRegisterConfig setEmailFlag(Integer emailFlag) {
		this.emailFlag = emailFlag;
		return this;
	}
	
	
	public Integer getQqFlag() {
		return qqFlag;
	}

	public CompanyRegisterConfig setQqFlag(Integer qqFlag) {
		this.qqFlag = qqFlag;
		return this;
	}
	
	
	public Integer getWechatFlag() {
		return wechatFlag;
	}

	public CompanyRegisterConfig setWechatFlag(Integer wechatFlag) {
		this.wechatFlag = wechatFlag;
		return this;
	}
	
	
	public Integer getWeiboFlag() {
		return weiboFlag;
	}

	public CompanyRegisterConfig setWeiboFlag(Integer weiboFlag) {
		this.weiboFlag = weiboFlag;
		return this;
	}
	
	
	public Integer getCloseFlag() {
		return closeFlag;
	}

	public CompanyRegisterConfig setCloseFlag(Integer closeFlag) {
		this.closeFlag = closeFlag;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public CompanyRegisterConfig setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	@Override
	public String toString() {
		return "CompanyRegisterConfig [" + "id=" + getId() + ", mobileFlag=" + mobileFlag + ", usernameFlag=" + usernameFlag + ", emailFlag=" + emailFlag + ", qqFlag=" + qqFlag + ", wechatFlag=" + wechatFlag + ", weiboFlag=" + weiboFlag + ", closeFlag=" + closeFlag + ", companyId=" + companyId +  "]";
	}
}
