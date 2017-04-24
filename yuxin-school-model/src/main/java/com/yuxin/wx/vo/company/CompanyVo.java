package com.yuxin.wx.vo.company;


import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.util.ShortDateSerializer;

/**
 * POJO:Company
 * 
 * @author chopin
 * @date 2015-4-23
 */
@SuppressWarnings("serial")
public class CompanyVo extends BaseEntity {
	
	private String	companyName;		 /* 公司名称 */ 
	private String	domain;		 /* 公司域名 */ 
	private String	companyNameEn;		 /* 公司英文名称 */ 
	private String	companyNameShort;		 /* 公司简称 */ 
	private String	loginUsername;		 /* 登录名称 */ 
	private String	loginName;		 /* 登录人姓名 */ 
	private String	loginTitle;		 /* 登录人头衔 */ 
	private String	loginEmail;		 /* 注册邮箱 */ 
	private String	loginMobile;		 /* 注册手机号 */ 
	private String	contactEmail;		 /* 联系人邮箱 */ 
	private String	contactAddress;		 /* 联系人地址 */ 
	private String	contactQq;		
	private String  contactPost;
	private String	companyLogo;		 /* 公司的logo */ 
	private String	companyIco;		 /* 公司的ico图标 */ 
	private String	companyWeixin;		 /* 公司微信公众账号 */ 
	private String	companyWeibo;		 /* 公司微博号 */ 
	private String	aboutUs;		 /* 关于我们 */ 
	private String	legalNotice;		 /* 法律声明 */ 
	private String	privacy;		 /* 隐私条款 */ 
	private String	contactUs;		 /* 联系我们 */ 
	private Date	createTime;		 /* 订单创建时间 */ 
	private String	creator;		 /* 订单创建人（课程顾问） */ 
	private Date	updateTime;		 /* 订单最后更新时间 */ 
	private String	updator;		 /* 操作员（当前操作员） */ 
	private Integer	memberLevel;		 /* 会员级别:免费版,标准版,专业版,高级版,尊享版 */ 
	private Integer	status;		 /* 机构审核状态:未提交审核,审核中,审核通过,审核不通过 */ 
	private String copyright;		/*公司版权信息*/
	private String registNo;		/*公司备案号*/
	private Date registTime;		/*注册时间*/
	
	private Date submitTime;		/*提交认证时间*/
	private Date auditTime;			/*审核时间*/
	private String	contacts;		 /* 联系人 */ 
	private String	contactMobile;		 /* 联系人电话 */
	private String legalPerson;		/*法人*/
	private String orgCode; 		/* 组织机构代码 */
	private String	businessLicenseCode;		 /* 营业执照代码 */ 
	private String serviceVersion;
	
	private String groupName;/*销售组的名称*/
	private Integer crmId;/*crm_company主键*/
	
	private Integer cId;
	private Date memberStartDate; /* 会员服务开始日期 */
	private Date memberEndDate; /* 会员服务结束日期 */
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getCompanyNameEn() {
		return companyNameEn;
	}
	public void setCompanyNameEn(String companyNameEn) {
		this.companyNameEn = companyNameEn;
	}
	public String getCompanyNameShort() {
		return companyNameShort;
	}
	public void setCompanyNameShort(String companyNameShort) {
		this.companyNameShort = companyNameShort;
	}
	public String getLoginUsername() {
		return loginUsername;
	}
	public void setLoginUsername(String loginUsername) {
		this.loginUsername = loginUsername;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getLoginTitle() {
		return loginTitle;
	}
	public void setLoginTitle(String loginTitle) {
		this.loginTitle = loginTitle;
	}
	public String getLoginEmail() {
		return loginEmail;
	}
	public void setLoginEmail(String loginEmail) {
		this.loginEmail = loginEmail;
	}
	public String getLoginMobile() {
		return loginMobile;
	}
	public void setLoginMobile(String loginMobile) {
		this.loginMobile = loginMobile;
	}
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	public String getContactMobile() {
		return contactMobile;
	}
	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}
	public String getContactEmail() {
		return contactEmail;
	}
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	public String getContactAddress() {
		return contactAddress;
	}
	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}
	public String getContactQq() {
		return contactQq;
	}
	public void setContactQq(String contactQq) {
		this.contactQq = contactQq;
	}
	public String getContactPost() {
		return contactPost;
	}
	public void setContactPost(String contactPost) {
		this.contactPost = contactPost;
	}
	public String getCompanyLogo() {
		return companyLogo;
	}
	public void setCompanyLogo(String companyLogo) {
		this.companyLogo = companyLogo;
	}
	public String getCompanyIco() {
		return companyIco;
	}
	public void setCompanyIco(String companyIco) {
		this.companyIco = companyIco;
	}
	public String getCompanyWeixin() {
		return companyWeixin;
	}
	public void setCompanyWeixin(String companyWeixin) {
		this.companyWeixin = companyWeixin;
	}
	public String getCompanyWeibo() {
		return companyWeibo;
	}
	public void setCompanyWeibo(String companyWeibo) {
		this.companyWeibo = companyWeibo;
	}
	public String getAboutUs() {
		return aboutUs;
	}
	public void setAboutUs(String aboutUs) {
		this.aboutUs = aboutUs;
	}
	public String getLegalNotice() {
		return legalNotice;
	}
	public void setLegalNotice(String legalNotice) {
		this.legalNotice = legalNotice;
	}
	public String getPrivacy() {
		return privacy;
	}
	public void setPrivacy(String privacy) {
		this.privacy = privacy;
	}
	public String getContactUs() {
		return contactUs;
	}
	public void setContactUs(String contactUs) {
		this.contactUs = contactUs;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getUpdator() {
		return updator;
	}
	public void setUpdator(String updator) {
		this.updator = updator;
	}
	public Integer getMemberLevel() {
		return memberLevel;
	}
	public void setMemberLevel(Integer memberLevel) {
		this.memberLevel = memberLevel;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getCopyright() {
		return copyright;
	}
	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}
	public String getRegistNo() {
		return registNo;
	}
	public void setRegistNo(String registNo) {
		this.registNo = registNo;
	}
	public Date getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}
	public Date getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	public String getLegalPerson() {
		return legalPerson;
	}
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getBusinessLicenseCode() {
		return businessLicenseCode;
	}
	public void setBusinessLicenseCode(String businessLicenseCode) {
		this.businessLicenseCode = businessLicenseCode;
	}
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getRegistTime() {
		return registTime;
	}
	public void setRegistTime(Date registTime) {
		this.registTime = registTime;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public Integer getCrmId() {
		return crmId;
	}
	public void setCrmId(Integer crmId) {
		this.crmId = crmId;
	}
	public Integer getcId() {
		return cId;
	}
	public void setcId(Integer cId) {
		this.cId = cId;
	}
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getMemberStartDate() {
		return memberStartDate;
	}
	public void setMemberStartDate(Date memberStartDate) {
		this.memberStartDate = memberStartDate;
	}
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getMemberEndDate() {
		return memberEndDate;
	}
	public void setMemberEndDate(Date memberEndDate) {
		this.memberEndDate = memberEndDate;
	}
	public String getServiceVersion() {
		return serviceVersion;
	}
	public void setServiceVersion(String serviceVersion) {
		this.serviceVersion = serviceVersion;
	}
	
}
