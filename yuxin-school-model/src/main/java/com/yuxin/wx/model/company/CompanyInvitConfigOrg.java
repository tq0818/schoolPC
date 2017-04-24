package com.yuxin.wx.model.company;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyInvitConfigOrg
 * 
 * @author chopin
 * @date 2017-3-13
 */
@SuppressWarnings("serial")
public class CompanyInvitConfigOrg extends BaseEntity {
	
	
	private Integer	openFlag;		
	private Double	invitRgstAward;		
	private Integer	invitRgstAwardPeriod;		
	private Integer	invitCastAwardFlag;		
	private Double	invitCastAward;		
	private Integer	castTypeCourse;		 /* 消费类型:购买课程 (0 or null)-否   1-是 */ 
	private Integer	castTypePackage;		 /* 消费类型:购买课程包 (0 or null)-否   1-是 */ 
	private Integer	castTypeMember;		 /* 消费类型:购买会员 (0 or null)-否   1-是 */ 
	private Integer	castTypeIntegral;		 /* 消费类型:购买积分 (0 or null)-否   1-是 */ 
	private Integer	companyId;		 /* 公司ID */ 
	private Integer stuRewardsFlag;
	private Integer rewardsCustomSetting;
	

	// Constructor
	public CompanyInvitConfigOrg() {
	}
	
	/**
	 * full Constructor
	 */
	public CompanyInvitConfigOrg(Integer id, Integer openFlag, Double invitRgstAward, Integer invitRgstAwardPeriod, Integer invitCastAwardFlag, Double invitCastAward, Integer castTypeCourse, Integer castTypePackage, Integer castTypeMember, Integer castTypeIntegral, Integer companyId) {
		setId(id);
		this.openFlag = openFlag;
		this.invitRgstAward = invitRgstAward;
		this.invitRgstAwardPeriod = invitRgstAwardPeriod;
		this.invitCastAwardFlag = invitCastAwardFlag;
		this.invitCastAward = invitCastAward;
		this.castTypeCourse = castTypeCourse;
		this.castTypePackage = castTypePackage;
		this.castTypeMember = castTypeMember;
		this.castTypeIntegral = castTypeIntegral;
		this.companyId = companyId;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CompanyInvitConfigOrg可以实现连缀设置属性
	
	public Integer getOpenFlag() {
		return openFlag;
	}

	public Integer getStuRewardsFlag() {
		return stuRewardsFlag;
	}

	public void setStuRewardsFlag(Integer stuRewardsFlag) {
		this.stuRewardsFlag = stuRewardsFlag;
	}

	public Integer getRewardsCustomSetting() {
		return rewardsCustomSetting;
	}

	public void setRewardsCustomSetting(Integer rewardsCustomSetting) {
		this.rewardsCustomSetting = rewardsCustomSetting;
	}

	public CompanyInvitConfigOrg setOpenFlag(Integer openFlag) {
		this.openFlag = openFlag;
		return this;
	}
	
	
	public Double getInvitRgstAward() {
		return invitRgstAward;
	}

	public CompanyInvitConfigOrg setInvitRgstAward(Double invitRgstAward) {
		this.invitRgstAward = invitRgstAward;
		return this;
	}
	
	
	public Integer getInvitRgstAwardPeriod() {
		return invitRgstAwardPeriod;
	}

	public CompanyInvitConfigOrg setInvitRgstAwardPeriod(Integer invitRgstAwardPeriod) {
		this.invitRgstAwardPeriod = invitRgstAwardPeriod;
		return this;
	}
	
	
	public Integer getInvitCastAwardFlag() {
		return invitCastAwardFlag;
	}

	public CompanyInvitConfigOrg setInvitCastAwardFlag(Integer invitCastAwardFlag) {
		this.invitCastAwardFlag = invitCastAwardFlag;
		return this;
	}
	
	
	
	
	public Double getInvitCastAward() {
		return invitCastAward;
	}

	public void setInvitCastAward(Double invitCastAward) {
		this.invitCastAward = invitCastAward;
	}

	public Integer getCastTypeCourse() {
		return castTypeCourse;
	}

	public CompanyInvitConfigOrg setCastTypeCourse(Integer castTypeCourse) {
		this.castTypeCourse = castTypeCourse;
		return this;
	}
	
	
	public Integer getCastTypePackage() {
		return castTypePackage;
	}

	public CompanyInvitConfigOrg setCastTypePackage(Integer castTypePackage) {
		this.castTypePackage = castTypePackage;
		return this;
	}
	
	
	public Integer getCastTypeMember() {
		return castTypeMember;
	}

	public CompanyInvitConfigOrg setCastTypeMember(Integer castTypeMember) {
		this.castTypeMember = castTypeMember;
		return this;
	}
	
	
	public Integer getCastTypeIntegral() {
		return castTypeIntegral;
	}

	public CompanyInvitConfigOrg setCastTypeIntegral(Integer castTypeIntegral) {
		this.castTypeIntegral = castTypeIntegral;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public CompanyInvitConfigOrg setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	@Override
	public String toString() {
		return "CompanyInvitConfigOrg [" + "id=" + getId() + ", openFlag=" + openFlag + ", invitRgstAward=" + invitRgstAward + ", invitRgstAwardPeriod=" + invitRgstAwardPeriod + ", invitCastAwardFlag=" + invitCastAwardFlag + ", invitCastAward=" + invitCastAward + ", castTypeCourse=" + castTypeCourse + ", castTypePackage=" + castTypePackage + ", castTypeMember=" + castTypeMember + ", castTypeIntegral=" + castTypeIntegral + ", companyId=" + companyId +  "]";
	}
}
