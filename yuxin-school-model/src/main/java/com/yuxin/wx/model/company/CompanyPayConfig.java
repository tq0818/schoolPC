package com.yuxin.wx.model.company;



import com.yuxin.wx.common.BaseEntity;

/**
 * CompanyPayConfig
 * 
 * @author ycl
 * @date 2015-4-27
 */
@SuppressWarnings("serial")
public class CompanyPayConfig extends BaseEntity {
	private String zfbAccount;		/*支付宝账号*/
	private String zfbPartnerId;	/*支付宝合作者id*/
	private String zfbKey;			/*支付宝key*/
	private Integer companyId;		/*公司ID*/
	private	String	ccUserId;		/* cc用户id*/
	private String	ccApiKey;		/* cckey*/
	private String  payType;		/* 支付类型（例如： 支付宝及时到账/支付宝担保交易）*/
	private String bankAccountName;  /* 银行汇款开户行支行名称 */
	private String bankAccountNumber;  /* 银行汇款开户行账号 */
	private String bankCompanyName;  /* 银行开户名称 */
	private String personName;   /* 个人名称 */
	private String personZfbAccount; /* 个人支付宝账号 */
	private String personZfbUrl;  	  /* 个人支付宝二维码路径 */
	private String personWxAccount;  /* 个人微信号 */
	private String personWxUrl; 	 /* 个人微信二维码路径 */
	private String gzhWxAppID;	 /*应用ID*/
	private String gzhWxAppSecret;	 /*应用密钥*/
	private String gzhWxMchID;	 /*微信支付商户号*/
	private String gzhWxKey;	 /*自己生成的32位的MD5的加密key*/
	private String letvUserId;	 /* 乐视用户id */
	private String letvApiKey;	 /* 乐视秘钥 */
	private String letvUUID;/* 乐视UUID */
	private String letvPu;/* 乐视pu */
	private String sxyCode;/*首信易商户编号*/
	private String sxyKey;/*首信易密钥*/
	private Integer zhuCompanyId;	
	public String getLetvPu() {
		return letvPu;
	}

	public void setLetvPu(String letvPu) {
		this.letvPu = letvPu;
	}
	private String qiniuUsername;
	private String qiniuPassword;
	private Integer qiniuUid;
	
	public String getLetvUUID() {
		return letvUUID;
	}

	public void setLetvUUID(String letvUUID) {
		this.letvUUID = letvUUID;
	}





	public String getLetvUserId() {
		return letvUserId;
	}

	public void setLetvUserId(String letvUserId) {
		this.letvUserId = letvUserId;
	}

	public String getLetvApiKey() {
		return letvApiKey;
	}

	public void setLetvApiKey(String letvApiKey) {
		this.letvApiKey = letvApiKey;
	}


	public CompanyPayConfig(String zfbAccount, String zfbPartnerId,
			String zfbKey, Integer companyId, String payType, String bankAccountName, String bankAccountNumber, String bankCompanyName,
			String personName, String personZfbAccount, String personWxAccount, String personZfbUrl, String personWxUrl, String gzhWxAppID, String gzhWxAppSecret, String gzhWxKey, String gzhWxMchID) {
		super();
		this.zfbAccount = zfbAccount;
		this.zfbPartnerId = zfbPartnerId;
		this.zfbKey = zfbKey;
		this.companyId = companyId;
		this.payType = payType;
		this.bankAccountName = bankAccountName;
		this.bankAccountNumber = bankAccountNumber;
		this.bankCompanyName = bankCompanyName;
		this.personName = personName;
		this.personZfbAccount = personZfbAccount;
		this.personWxAccount = personWxAccount;
		this.personZfbUrl = personZfbUrl;
		this.personWxUrl = personWxUrl;
		this.gzhWxAppID = gzhWxAppID;
		this.gzhWxAppSecret = gzhWxAppSecret;
		this.gzhWxKey = gzhWxKey;
		this.gzhWxMchID = gzhWxMchID;
	}
	public CompanyPayConfig() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getZfbAccount() {
		return zfbAccount;
	}
	public void setZfbAccount(String zfbAccount) {
		this.zfbAccount = zfbAccount;
	}
	public String getZfbPartnerId() {
		return zfbPartnerId;
	}
	public void setZfbPartnerId(String zfbPartnerId) {
		this.zfbPartnerId = zfbPartnerId;
	}
	public String getZfbKey() {
		return zfbKey;
	}
	public void setZfbKey(String zfbKey) {
		this.zfbKey = zfbKey;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getCcUserId() {
		return ccUserId;
	}
	public void setCcUserId(String ccUserId) {
		this.ccUserId = ccUserId;
	}
	public String getCcApiKey() {
		return ccApiKey;
	}
	public void setCcApiKey(String ccApiKey) {
		this.ccApiKey = ccApiKey;
	}
	
	public String getBankAccountName() {
		return bankAccountName;
	}
	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}
	public String getBankAccountNumber() {
		return bankAccountNumber;
	}
	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}
	public String getBankCompanyName() {
		return bankCompanyName;
	}
	public void setBankCompanyName(String bankCompanyName) {
		this.bankCompanyName = bankCompanyName;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getPersonZfbAccount() {
		return personZfbAccount;
	}
	public void setPersonZfbAccount(String personZfbAccount) {
		this.personZfbAccount = personZfbAccount;
	}
	public String getPersonWxAccount() {
		return personWxAccount;
	}
	public void setPersonWxAccount(String personWxAccount) {
		this.personWxAccount = personWxAccount;
	}
	public String getPersonZfbUrl() {
		return personZfbUrl;
	}
	public void setPersonZfbUrl(String personZfbUrl) {
		this.personZfbUrl = personZfbUrl;
	}
	public String getPersonWxUrl() {
		return personWxUrl;
	}
	public void setPersonWxUrl(String personWxUrl) {
		this.personWxUrl = personWxUrl;
	}
	@Override
	public String toString() {
		return "CompanyPayConfig [" + "zfbAccount=" + zfbAccount + ", zfbPartnerId=" + zfbPartnerId + ", zfbKey=" + zfbKey + ", companyId=" + companyId + ", ccUserId=" + ccUserId + ", ccApiKey=" + ccApiKey + ", payType=" + payType + ", personName=" + personName +", personZfbAccount=" + personZfbAccount +", personWxAccount=" + personWxAccount + 
				", personZfbUrl=" + personZfbUrl +", personWxUrl=" + personWxUrl + ", gzhWxAppID=" + gzhWxAppID +", gzhWxAppSecret=" + gzhWxAppSecret + ", gzhWxKey=" + gzhWxKey +", gzhWxMchID=" + gzhWxMchID + "]";
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getGzhWxAppID() {
		return gzhWxAppID;
	}
	public void setGzhWxAppID(String gzhWxAppID) {
		this.gzhWxAppID = gzhWxAppID;
	}
	public String getGzhWxAppSecret() {
		return gzhWxAppSecret;
	}
	public void setGzhWxAppSecret(String gzhWxAppSecret) {
		this.gzhWxAppSecret = gzhWxAppSecret;
	}
	public String getGzhWxMchID() {
		return gzhWxMchID;
	}
	public void setGzhWxMchID(String gzhWxMchID) {
		this.gzhWxMchID = gzhWxMchID;
	}
	public String getGzhWxKey() {
		return gzhWxKey;
	}
	public void setGzhWxKey(String gzhWxKey) {
		this.gzhWxKey = gzhWxKey;
	}

	public String getSxyCode() {
		return sxyCode;
	}

	public void setSxyCode(String sxyCode) {
		this.sxyCode = sxyCode;
	}

	public String getSxyKey() {
		return sxyKey;
	}

	public void setSxyKey(String sxyKey) {
		this.sxyKey = sxyKey;
	}

	public String getQiniuUsername() {
		return qiniuUsername;
	}

	public void setQiniuUsername(String qiniuUsername) {
		this.qiniuUsername = qiniuUsername;
	}

	public String getQiniuPassword() {
		return qiniuPassword;
	}

	public void setQiniuPassword(String qiniuPassword) {
		this.qiniuPassword = qiniuPassword;
	}

	public Integer getQiniuUid() {
		return qiniuUid;
	}

	public void setQiniuUid(Integer qiniuUid) {
		this.qiniuUid = qiniuUid;
	}

	
    public Integer getZhuCompanyId() {
    	return zhuCompanyId;
    }

	
    public void setZhuCompanyId(Integer zhuCompanyId) {
    	this.zhuCompanyId = zhuCompanyId;
    }
	
}
