package com.yuxin.wx.model.company;

import java.io.Serializable;
import java.util.Date;

import com.yuxin.wx.common.BaseEntity;

public class CompanyVo extends BaseEntity implements Serializable {
	/**
	 * 分校ID
	 */
	private String companyId;
	/**
	 * 组织机构代码
	 */
	private String eduAreaSchool;
	/**
	 * 分校名称
	 */
	private String companyName;
	/**
	 * 所属区域
	 */
	private String eduArea;
	/**
	 * 创建时间
	 */
	private String registTime;
	private Date registTimes;
	/**
	 * 注册人数
	 */
	private String registStudentCounts;
	/**
	 * 班级数
	 */
	private String classCounts;
	/**
	 * 课程数
	 */
	private String classTypeCounts;
	/**
	 * 查询条件：起始时间
	 */
	private String startTime;
	/**
	 * 查询条件：结束时间
	 */
	private String endTime;
	/**
	 * 访问地址
	 */
	private String domain;
	/**
	 * 分校代码类别
	 */
	private String dictCode;
	
	/**
	 */
	private String schoolProperty;//学校性质
	
	private String linkPerson;//联系人
	
	private String linkPhone;//联系方式
	
	private String domainManage;//分校后台域名
	
	private String privateCost;//私有收费比例
	
	private String publicCost;//开放收费比例
	
	private String schoolSummary;//分校简介
	
	private String companyLogoType;//企业logo类型
	
	private String companyLogo;//公司的logo
	private String memberLevel;//会员级别
	private String schooLapplyFlag;//申请开通更多分校状态
	private String utmSource;//推广来源
	private String conType;//注册类型
	private String buyFlag;//是否收费用户
	private String serviceVersion;//服务版本
	
	private String domainBackup;//后台域名访问地址
	private String status;//机构审核状态
	private Date memberStartDate;//会员服务开始日期
	private Date memberEndDate;//会员服务结束日期
	private String schoolApplyFlag;//申请开通更多分校状态
	private String isArea;//分校类型
	private String companyNameShot;//公司简称
	
	
	
    public String getCompanyNameShot() {
    	return companyNameShot;
    }
	
    public void setCompanyNameShot(String companyNameShot) {
    	this.companyNameShot = companyNameShot;
    }
	public String getDictCode() {
		return dictCode;
	}
	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getEduAreaSchool() {
		return eduAreaSchool;
	}
	public void setEduAreaSchool(String eduAreaSchool) {
		this.eduAreaSchool = eduAreaSchool;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getEduArea() {
		return eduArea;
	}
	public void setEduArea(String eduArea) {
		this.eduArea = eduArea;
	}
	public String getRegistTime() {
		return registTime;
	}
	public void setRegistTime(String registTime) {
		this.registTime = registTime;
	}
	public String getRegistStudentCounts() {
		return registStudentCounts;
	}
	public void setRegistStudentCounts(String registStudentCounts) {
		this.registStudentCounts = registStudentCounts;
	}
	public String getClassCounts() {
		return classCounts;
	}
	public void setClassCounts(String classCounts) {
		this.classCounts = classCounts;
	}
	public String getClassTypeCounts() {
		return classTypeCounts;
	}
	public void setClassTypeCounts(String classTypeCounts) {
		this.classTypeCounts = classTypeCounts;
	}
	
    public String getSchoolProperty() {
    	return schoolProperty;
    }
	
    public void setSchoolProperty(String schoolProperty) {
    	this.schoolProperty = schoolProperty;
    }
	
    public String getLinkPerson() {
    	return linkPerson;
    }
	
    public void setLinkPerson(String linkPerson) {
    	this.linkPerson = linkPerson;
    }
	
    public String getLinkPhone() {
    	return linkPhone;
    }
	
    public void setLinkPhone(String linkPhone) {
    	this.linkPhone = linkPhone;
    }
	
    public String getDomainManage() {
    	return domainManage;
    }
	
    public void setDomainManage(String domainManage) {
    	this.domainManage = domainManage;
    }
	
    public String getPrivateCost() {
    	return privateCost;
    }
	
    public void setPrivateCost(String privateCost) {
    	this.privateCost = privateCost;
    }
	
    public String getPublicCost() {
    	return publicCost;
    }
	
    public void setPublicCost(String publicCost) {
    	this.publicCost = publicCost;
    }
	
    public String getSchoolSummary() {
    	return schoolSummary;
    }
	
    public void setSchoolSummary(String schoolSummary) {
    	this.schoolSummary = schoolSummary;
    }
	
    public String getDomainBackup() {
    	return domainBackup;
    }
	
    public void setDomainBackup(String domainBackup) {
    	this.domainBackup = domainBackup;
    }
	
    public String getStatus() {
    	return status;
    }
	
    public void setStatus(String status) {
    	this.status = status;
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
	public String getSchoolApplyFlag() {
    	return schoolApplyFlag;
    }
	
    public void setSchoolApplyFlag(String schoolApplyFlag) {
    	this.schoolApplyFlag = schoolApplyFlag;
    }
	
    public String getIsArea() {
    	return isArea;
    }
	
    public void setIsArea(String isArea) {
    	this.isArea = isArea;
    }
	
    public Date getRegistTimes() {
    	return registTimes;
    }
	
    public void setRegistTimes(Date registTimes) {
    	this.registTimes = registTimes;
    }
	
    public String getCompanyLogoType() {
    	return companyLogoType;
    }
	
    public void setCompanyLogoType(String companyLogoType) {
    	this.companyLogoType = companyLogoType;
    }
	
    public String getCompanyLogo() {
    	return companyLogo;
    }
	
    public void setCompanyLogo(String companyLogo) {
    	this.companyLogo = companyLogo;
    }
	
    public String getMemberLevel() {
    	return memberLevel;
    }
	
    public void setMemberLevel(String memberLevel) {
    	this.memberLevel = memberLevel;
    }
	
    public String getSchooLapplyFlag() {
    	return schooLapplyFlag;
    }
	
    public void setSchooLapplyFlag(String schooLapplyFlag) {
    	this.schooLapplyFlag = schooLapplyFlag;
    }
	
    public String getUtmSource() {
    	return utmSource;
    }
	
    public void setUtmSource(String utmSource) {
    	this.utmSource = utmSource;
    }
	
    public String getConType() {
    	return conType;
    }
	
    public void setConType(String conType) {
    	this.conType = conType;
    }
	
    public String getBuyFlag() {
    	return buyFlag;
    }
	
    public void setBuyFlag(String buyFlag) {
    	this.buyFlag = buyFlag;
    }
	
    public String getServiceVersion() {
    	return serviceVersion;
    }
	
    public void setServiceVersion(String serviceVersion) {
    	this.serviceVersion = serviceVersion;
    }
	
	
}
