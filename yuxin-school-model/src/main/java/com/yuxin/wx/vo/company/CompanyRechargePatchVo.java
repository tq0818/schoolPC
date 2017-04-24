package com.yuxin.wx.vo.company;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.util.ShortDateSerializer;

public class CompanyRechargePatchVo extends BaseEntity{
	
	private Integer	totalNum;		 /* 总数量 */ 
	private Integer	usedNum;		 /* 已使用数量 */ 
	private Integer	remainNum;		 /* 剩余数量 */ 
	private Integer	status;		 /* 状态 0-禁用 1-启用 */ 
	private Integer	issueWay;		 /* 发放方式  0-线上发放 1-线下发放 */ 
	private Double	cashAmount;		 /* 现金金额   */ 
	private Date	timeLimitFrom;		 /* 使用期限从 */ 
	private Date	timeLimitTo;		 /* 使用期限至 */ 
	private String	promoCodePrefix;		 /* 充值卡前缀 */ 
	private Integer	orgId;		 /* 关联代理机构 */ 
	private Integer	companyId;		 /* 公司ID */ 
	private Date	createDate;		
	private Integer	creator;	
	
	private String orgName;
	
	private Integer timeLen;
	private String timeMark;
	
	private String startTime;
	private String endTime;
	private String startDate;
	private String endDate;
	private String minPrice;
	private String maxPrice;
	
	public Integer getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	public Integer getUsedNum() {
		return usedNum;
	}
	public void setUsedNum(Integer usedNum) {
		this.usedNum = usedNum;
	}
	public Integer getRemainNum() {
		return remainNum;
	}
	public void setRemainNum(Integer remainNum) {
		this.remainNum = remainNum;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getIssueWay() {
		return issueWay;
	}
	public void setIssueWay(Integer issueWay) {
		this.issueWay = issueWay;
	}
	public Double getCashAmount() {
		return cashAmount;
	}
	public void setCashAmount(Double cashAmount) {
		this.cashAmount = cashAmount;
	}
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getTimeLimitFrom() {
		return timeLimitFrom;
	}
	public void setTimeLimitFrom(Date timeLimitFrom) {
		this.timeLimitFrom = timeLimitFrom;
	}
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getTimeLimitTo() {
		return timeLimitTo;
	}
	public void setTimeLimitTo(Date timeLimitTo) {
		this.timeLimitTo = timeLimitTo;
	}
	public String getPromoCodePrefix() {
		return promoCodePrefix;
	}
	public void setPromoCodePrefix(String promoCodePrefix) {
		this.promoCodePrefix = promoCodePrefix;
	}
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Integer getCreator() {
		return creator;
	}
	public void setCreator(Integer creator) {
		this.creator = creator;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public Integer getTimeLen() {
		return timeLen;
	}
	public void setTimeLen(Integer timeLen) {
		this.timeLen = timeLen;
	}
	public String getTimeMark() {
		return timeMark;
	}
	public void setTimeMark(String timeMark) {
		this.timeMark = timeMark;
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
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(String minPrice) {
		this.minPrice = minPrice;
	}
	public String getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(String maxPrice) {
		this.maxPrice = maxPrice;
	}
	
	
	
}
