package com.yuxin.wx.model.company;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyInvitConfigTeacher
 * 
 * @author chopin
 * @date 2017-3-13
 */
@SuppressWarnings("serial")
public class CompanyInvitConfigTeacher extends BaseEntity {
	
	
	private Integer	openFlag;		
	private Double	invitRgstAward;		 /* 邀请学员注册后获得奖励   代金券*元   */ 
	private Integer	invitRgstAwardPeriod;		 /* 代金券有效期  单位天 */ 
	private Integer	invitCastAwardFlag;		 /* 被邀请人消费提成方式 0-每次消费 1-首次消费 */ 
	private Double	invitCastAward;		 /* 被邀请人消费，提成比例 */ 
	private Integer	castTypeCourse;		 /* 消费类型:购买课程 (0 or null)-否   1-是 */ 
	private Integer	castTypePackage;		 /* 消费类型:购买课程包 (0 or null)-否   1-是 */ 
	private Integer	castTypeMember;		 /* 消费类型:购买会员 (0 or null)-否   1-是 */ 
	private Integer	castTypeIntegral;		 /* 消费类型:购买积分 (0 or null)-否   1-是 */ 
	private Integer stuRewardsFlag;
	private Integer	companyId;		

	// Constructor
	public CompanyInvitConfigTeacher() {
	}
	
	/**
	 * full Constructor
	 */
	public CompanyInvitConfigTeacher(Integer id, Integer openFlag, Double invitRgstAward, Integer invitRgstAwardPeriod, Integer invitCastAwardFlag, Double invitCastAward, Integer castTypeCourse, Integer castTypePackage, Integer castTypeMember, Integer castTypeIntegral, Integer companyId) {
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
	// 在setter方法最后加上"return this;"并把返回参数改为CompanyInvitConfigTeacher可以实现连缀设置属性
	
	public Integer getOpenFlag() {
		return openFlag;
	}

	public CompanyInvitConfigTeacher setOpenFlag(Integer openFlag) {
		this.openFlag = openFlag;
		return this;
	}
	
	
	public Double getInvitRgstAward() {
		return invitRgstAward;
	}

	public CompanyInvitConfigTeacher setInvitRgstAward(Double invitRgstAward) {
		this.invitRgstAward = invitRgstAward;
		return this;
	}
	
	
	public Integer getInvitRgstAwardPeriod() {
		return invitRgstAwardPeriod;
	}

	public CompanyInvitConfigTeacher setInvitRgstAwardPeriod(Integer invitRgstAwardPeriod) {
		this.invitRgstAwardPeriod = invitRgstAwardPeriod;
		return this;
	}
	
	
	public Integer getInvitCastAwardFlag() {
		return invitCastAwardFlag;
	}

	public CompanyInvitConfigTeacher setInvitCastAwardFlag(Integer invitCastAwardFlag) {
		this.invitCastAwardFlag = invitCastAwardFlag;
		return this;
	}
	
	
	public Double getInvitCastAward() {
		return invitCastAward;
	}

	public CompanyInvitConfigTeacher setInvitCastAward(Double invitCastAward) {
		this.invitCastAward = invitCastAward;
		return this;
	}
	
	
	public Integer getCastTypeCourse() {
		return castTypeCourse;
	}

	public CompanyInvitConfigTeacher setCastTypeCourse(Integer castTypeCourse) {
		this.castTypeCourse = castTypeCourse;
		return this;
	}
	
	
	public Integer getCastTypePackage() {
		return castTypePackage;
	}

	public CompanyInvitConfigTeacher setCastTypePackage(Integer castTypePackage) {
		this.castTypePackage = castTypePackage;
		return this;
	}
	
	
	public Integer getCastTypeMember() {
		return castTypeMember;
	}

	public CompanyInvitConfigTeacher setCastTypeMember(Integer castTypeMember) {
		this.castTypeMember = castTypeMember;
		return this;
	}
	
	
	public Integer getCastTypeIntegral() {
		return castTypeIntegral;
	}

	public CompanyInvitConfigTeacher setCastTypeIntegral(Integer castTypeIntegral) {
		this.castTypeIntegral = castTypeIntegral;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public CompanyInvitConfigTeacher setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public Integer getStuRewardsFlag() {
		return stuRewardsFlag;
	}

	public void setStuRewardsFlag(Integer stuRewardsFlag) {
		this.stuRewardsFlag = stuRewardsFlag;
	}

	@Override
	public String toString() {
		return "CompanyInvitConfigTeacher [openFlag=" + openFlag + ", invitRgstAward=" + invitRgstAward
				+ ", invitRgstAwardPeriod=" + invitRgstAwardPeriod + ", invitCastAwardFlag=" + invitCastAwardFlag
				+ ", invitCastAward=" + invitCastAward + ", castTypeCourse=" + castTypeCourse + ", castTypePackage="
				+ castTypePackage + ", castTypeMember=" + castTypeMember + ", castTypeIntegral=" + castTypeIntegral
				+ ", stuRewardsFlag=" + stuRewardsFlag + ", companyId=" + companyId + "]";
	}

	
}
