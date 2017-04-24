package com.yuxin.wx.model.company;

import java.util.Date;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:Company
 * 
 * @author chopin
 * @date 2015-4-23
 */
@SuppressWarnings("serial")
public class Company extends BaseEntity {

	private String companyName; /* 公司名称 */
	private String domain; /* 公司域名 */
	private String companyNameEn; /* 公司英文名称 */
	private String companyNameShort; /* 公司简称 */
	private String companyLogo; /* 公司的logo */
	private String companyIco; /* 公司的ico图标 */
	private String companyWeixin; /* 公司微信公众账号 */
	private String companyWeibo; /* 公司微博号 */
	private String aboutUs; /* 关于我们 */
	private String legalNotice; /* 法律声明 */
	private String privacy; /* 隐私条款 */
	private String contactUs; /* 联系我们 */
	private Date createTime; /* 订单创建时间 */
	private String creator; /* 订单创建人（课程顾问） */
	private Date updateTime; /* 订单最后更新时间 */
	private String updator; /* 操作员（当前操作员） */
	private Integer memberLevel; /* 会员级别:免费版,标准版,专业版,高级版,尊享版 */
	private Integer status; /* 机构审核状态:未提交审核,审核中,审核通过,审核不通过 */
	private String copyright; /* 公司版权信息 */
	private String registNo; /* 公司备案号 */
	private Date memberStartDate; /* 会员服务开始日期 */
	private Date memberEndDate; /* 会员服务结束日期 */
	private Date registTime; /* 公司注册时间 */
	private Integer schoolApplyFlag; /* 申请开通更多分校状态（0：未申请；1：已申请） */
	private String companyDistrict;  /* 公司区域地址*/
	private String companyLogoType;  /* logo显示类型*/
	private String domainBackup;
	private Integer crmFlag;/*是否关联crm*/
	private Integer buyFlag;
	private String serviceVersion; /*版本*/
	
	//服务授权判断会员状态是否正常
	private Date normal;			/*正常（未到期）*/
	private Date unNormal;			/*不正常（到期）*/
	
	private String utmSource;   /* 推广来源*/
	private String utmMedium;   /* 推广媒介*/
	private String utmTerm;     /* 推广关键词*/
	private String utmContent;   /* 推广内容*/
	private String utmCampaign;  /* 推广计划*/
	private String utmUrl;
	
	private String comType;   /* 类型*/
	
	private Date startTime;/*起始时间*/
	private Date endTime;/*截至时间*/
	private String userMonile;/*用户手机*/
	
	private String myType;/*判断是公司库还是组内库*/
	private String isGL;/*判断当前用户是否是销售管理*/
	private Integer groupId;/*销售组id*/
	
	private String exam; /* 当前公司是否开启考试 */
	private String opClass;/*当前公司公开课是否开启*/
	private String classPackage;/*课程包*/
	private String vip;/*会员 */
	
	private String tiku;
	private String invite;
	
	// Constructor
	public Company() {
	}

	/**
	 * full Constructor
	 */
	public Company(Integer id, Date memberEndDate, Integer schoolApplyFlag,
			Date registTime, Date memberStartDate, String companyName,
			String domain, String companyNameEn, String companyNameShort,
			 String companyLogo, String companyIco,
			String companyWeixin, String companyWeibo, String copyright,
			String registNo, Integer memberLevel, String status) {
		setId(id);
		this.schoolApplyFlag = schoolApplyFlag;
		this.registTime = registTime;
		this.memberEndDate = memberEndDate;
		this.memberStartDate = memberStartDate;
		this.companyName = companyName;
		this.domain = domain;
		this.companyNameEn = companyNameEn;
		this.companyNameShort = companyNameShort;
		this.companyLogo = companyLogo;
		this.companyIco = companyIco;
		this.companyWeixin = companyWeixin;
		this.companyWeibo = companyWeibo;

		this.copyright = copyright;
		this.registNo = registNo;
		this.memberLevel = memberLevel;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为Company可以实现连缀设置属性

	public String getCompanyName() {
		return companyName;
	}

	public Company setCompanyName(String companyName) {
		this.companyName = companyName;
		return this;
	}

	public String getDomain() {
		return domain;
	}

	public Company setDomain(String domain) {
		this.domain = domain;
		return this;
	}

	public String getCompanyNameEn() {
		return companyNameEn;
	}

	public Company setCompanyNameEn(String companyNameEn) {
		this.companyNameEn = companyNameEn;
		return this;
	}

	public String getCompanyNameShort() {
		return companyNameShort;
	}

	public Company setCompanyNameShort(String companyNameShort) {
		this.companyNameShort = companyNameShort;
		return this;
	}

	public String getCompanyLogo() {
		return companyLogo;
	}

	public Company setCompanyLogo(String companyLogo) {
		this.companyLogo = companyLogo;
		return this;
	}

	public String getCompanyIco() {
		return companyIco;
	}

	public Company setCompanyIco(String companyIco) {
		this.companyIco = companyIco;
		return this;
	}

	public String getCompanyWeixin() {
		return companyWeixin;
	}

	public Company setCompanyWeixin(String companyWeixin) {
		this.companyWeixin = companyWeixin;
		return this;
	}

	public String getCompanyWeibo() {
		return companyWeibo;
	}

	public Company setCompanyWeibo(String companyWeibo) {
		this.companyWeibo = companyWeibo;
		return this;
	}

	public String getAboutUs() {
		return aboutUs;
	}

	public String getCopyright() {
		return copyright;
	}

	public Company setCopyright(String copyright) {
		this.copyright = copyright;
		return this;
	}

	public String getRegistNo() {
		return registNo;
	}

	public Company setRegistNo(String registNo) {
		this.registNo = registNo;
		return this;
	}

	public Integer getMemberLevel() {
		return memberLevel;
	}

	public Company setMemberLevel(Integer memberLevel) {
		this.memberLevel = memberLevel;
		return this;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {

		return "Company [companyName=" + companyName + ", domain=" + domain
				+ ", companyNameEn=" + companyNameEn + ", companyNameShort="
				+ companyNameShort
				+ ", memberStartDate=" + memberStartDate
				+ ", memberEndDate=" + memberEndDate
				+ ", registTime=" + registTime
				+ ", schoolApplyFlag=" + schoolApplyFlag
				+ ", companyLogo=" + companyLogo + ", companyIco=" + companyIco
				+ ", companyWeixin=" + companyWeixin + ", companyWeibo="
				+ companyWeibo + ", createTime=" + createTime + ", creator="
				+ creator + ", updateTime=" + updateTime + ", updator="
				+ updator + ", copyright=" + copyright + ", registNo="
				+ registNo + "]";

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

	public void setAboutUs(String aboutUs) {
		this.aboutUs = aboutUs;
	}

	public Date getMemberStartDate() {
		return memberStartDate;
	}

	public void setMemberStartDate(Date memberStartDate) {
		this.memberStartDate = memberStartDate;
	}

	public Date getMemberEndDate() {
		return memberEndDate;
	}

	public void setMemberEndDate(Date memberEndDate) {
		this.memberEndDate = memberEndDate;
	}

	public Date getRegistTime() {
		return registTime;
	}

	public void setRegistTime(Date registTime) {
		this.registTime = registTime;
	}

	public Integer getSchoolApplyFlag() {
		return schoolApplyFlag;
	}

	public void setSchoolApplyFlag(Integer schoolApplyFlag) {
		this.schoolApplyFlag = schoolApplyFlag;
	}

	public String getCompanyDistrict() {
		return companyDistrict;
	}

	public void setCompanyDistrict(String companyDistrict) {
		this.companyDistrict = companyDistrict;
	}

	public Date getNormal() {
		return normal;
	}

	public void setNormal(Date normal) {
		this.normal = normal;
	}

	public Date getUnNormal() {
		return unNormal;
	}

	public void setUnNormal(Date unNormal) {
		this.unNormal = unNormal;
	}

	public String getCompanyLogoType() {
		return companyLogoType;
	}

	public void setCompanyLogoType(String companyLogoType) {
		this.companyLogoType = companyLogoType;
	}

	public String getDomainBackup() {
		return domainBackup;
	}

	public void setDomainBackup(String domainBackup) {
		this.domainBackup = domainBackup;
	}

	public String getUtmSource() {
		return utmSource;
	}

	public void setUtmSource(String utmSource) {
		this.utmSource = utmSource;
	}

	public String getUtmMedium() {
		return utmMedium;
	}

	public void setUtmMedium(String utmMedium) {
		this.utmMedium = utmMedium;
	}

	public String getUtmTerm() {
		return utmTerm;
	}

	public void setUtmTerm(String utmTerm) {
		this.utmTerm = utmTerm;
	}

	public String getUtmContent() {
		return utmContent;
	}

	public void setUtmContent(String utmContent) {
		this.utmContent = utmContent;
	}

	public String getUtmCampaign() {
		return utmCampaign;
	}

	public void setUtmCampaign(String utmCampaign) {
		this.utmCampaign = utmCampaign;
	}

	public Integer getCrmFlag() {
		return crmFlag;
	}

	public void setCrmFlag(Integer crmFlag) {
		this.crmFlag = crmFlag;
	}

	public String getComType() {
		return comType;
	}

	public void setComType(String comType) {
		this.comType = comType;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getUserMonile() {
		return userMonile;
	}

	public void setUserMonile(String userMonile) {
		this.userMonile = userMonile;
	}

	public Integer getBuyFlag() {
		return buyFlag;
	}

	public void setBuyFlag(Integer buyFlag) {
		this.buyFlag = buyFlag;
	}


	public String getIsGL() {
		return isGL;
	}

	public void setIsGL(String isGL) {
		this.isGL = isGL;
	}

	public String getMyType() {
		return myType;
	}

	public void setMyType(String myType) {
		this.myType = myType;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getUtmUrl() {
		return utmUrl;
	}

	public void setUtmUrl(String utmUrl) {
		this.utmUrl = utmUrl;
	}

	public String getExam() {
		return exam;
	}

	public void setExam(String exam) {
		this.exam = exam;
	}

	public String getServiceVersion() {
		return serviceVersion;
	}

	public void setServiceVersion(String serviceVersion) {
		this.serviceVersion = serviceVersion;
	}

	public String getOpClass() {
		return opClass;
	}

	public void setOpClass(String opClass) {
		this.opClass = opClass;
	}

	public String getTiku() {
		return tiku;
	}

	public void setTiku(String tiku) {
		this.tiku = tiku;
	}

	public String getInvite() {
		return invite;
	}

	public void setInvite(String invite) {
		this.invite = invite;
	}

	public String getClassPackage() {
		return classPackage;
	}

	public void setClassPackage(String classPackage) {
		this.classPackage = classPackage;
	}

	public String getVip() {
		return vip;
	}

	public void setVip(String vip) {
		this.vip = vip;
	}

	
}
