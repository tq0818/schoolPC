package com.yuxin.wx.model.company;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyMemberConfig
 * 
 * @author chopin
 * @date 2016-5-17
 */
@SuppressWarnings("serial")
public class CompanyMemberConfig extends BaseEntity {
	
	
	private Integer	memberPage;		 /* 会员专区页面开关 */ 
	private Integer	becomeMember;		 /* 成为会员方式：0-购买 1-累积 */ 
	private Integer	remind;		 /* 到期提醒 */ 
	private Integer	remindBeforeDay;		 /* 到期前多少天提醒 */ 
	private String	remindContent;		 /* 提醒内容 */ 
	private Integer	classDiscount;		 /* 课程使用会员折扣 */ 
	private Integer	classPackageDiscount;		 /* 课程包使用会员折扣 */ 
	private Integer	upgradeType;		 /* 升级方式： 0-原价购买 1-补差价购买 */ 
	private Integer	buyWithIntegral;		 /* 使用积分购买 */ 
	private Integer	companyId;		
	private String	memberPageBanner;		/* 图片路径*/
	private Integer buyFlag;

	

	// Constructor
	public CompanyMemberConfig() {
	}
	
	/**
	 * full Constructor
	 */
	public CompanyMemberConfig(Integer id, Integer memberPage, Integer becomeMember, Integer remind, Integer remindBeforeDay, String remindContent, Integer classDiscount, Integer classPackageDiscount, Integer upgradeType, Integer buyWithIntegral, Integer companyId, Integer buyFlag) {
		setId(id);
		this.memberPage = memberPage;
		this.becomeMember = becomeMember;
		this.remind = remind;
		this.remindBeforeDay = remindBeforeDay;
		this.remindContent = remindContent;
		this.classDiscount = classDiscount;
		this.classPackageDiscount = classPackageDiscount;
		this.upgradeType = upgradeType;
		this.buyWithIntegral = buyWithIntegral;
		this.companyId = companyId;
		this.buyFlag = buyFlag;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CompanyMemberConfig可以实现连缀设置属性
	
	public Integer getBuyFlag() {
		return buyFlag;
	}

	public CompanyMemberConfig setBuyFlag(Integer buyFlag) {
		this.buyFlag = buyFlag;
		return this;
	}
	
	public Integer getMemberPage() {
		return memberPage;
	}

	public CompanyMemberConfig setMemberPage(Integer memberPage) {
		this.memberPage = memberPage;
		return this;
	}
	
	
	public Integer getBecomeMember() {
		return becomeMember;
	}

	public CompanyMemberConfig setBecomeMember(Integer becomeMember) {
		this.becomeMember = becomeMember;
		return this;
	}
	
	
	public Integer getRemind() {
		return remind;
	}

	public CompanyMemberConfig setRemind(Integer remind) {
		this.remind = remind;
		return this;
	}
	
	
	public Integer getRemindBeforeDay() {
		return remindBeforeDay;
	}

	public CompanyMemberConfig setRemindBeforeDay(Integer remindBeforeDay) {
		this.remindBeforeDay = remindBeforeDay;
		return this;
	}
	
	
	public String getRemindContent() {
		return remindContent;
	}

	public CompanyMemberConfig setRemindContent(String remindContent) {
		this.remindContent = remindContent;
		return this;
	}
	
	
	public Integer getClassDiscount() {
		return classDiscount;
	}

	public CompanyMemberConfig setClassDiscount(Integer classDiscount) {
		this.classDiscount = classDiscount;
		return this;
	}
	
	
	public Integer getClassPackageDiscount() {
		return classPackageDiscount;
	}

	public CompanyMemberConfig setClassPackageDiscount(Integer classPackageDiscount) {
		this.classPackageDiscount = classPackageDiscount;
		return this;
	}
	
	
	public Integer getUpgradeType() {
		return upgradeType;
	}

	public CompanyMemberConfig setUpgradeType(Integer upgradeType) {
		this.upgradeType = upgradeType;
		return this;
	}
	
	
	public Integer getBuyWithIntegral() {
		return buyWithIntegral;
	}

	public CompanyMemberConfig setBuyWithIntegral(Integer buyWithIntegral) {
		this.buyWithIntegral = buyWithIntegral;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public CompanyMemberConfig setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	@Override
	public String toString() {
		return "CompanyMemberConfig [" + "id=" + getId() + ", memberPage=" + memberPage + ", becomeMember=" + becomeMember + ", remind=" + remind + ", remindBeforeDay=" + remindBeforeDay + ", remindContent=" + remindContent + ", classDiscount=" + classDiscount + ", classPackageDiscount=" + classPackageDiscount + ", upgradeType=" + upgradeType + ", buyWithIntegral=" + buyWithIntegral + ", companyId=" + companyId +  ", buyFlag=" + buyFlag + "]";
	}

	public String getMemberPageBanner() {
		return memberPageBanner;
	}

	public void setMemberPageBanner(String memberPageBanner) {
		this.memberPageBanner = memberPageBanner;
	}
}
