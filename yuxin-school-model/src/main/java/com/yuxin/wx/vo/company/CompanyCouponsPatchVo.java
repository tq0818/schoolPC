package com.yuxin.wx.vo.company;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.model.company.CompanyCouponsLib;

/**
 * POJO:CompanyCouponsPatch
 * 
 * @author wang.zx
 * @date 2016-6-21
 */
@SuppressWarnings("serial")
public class CompanyCouponsPatchVo extends BaseEntity {
	
	
	private String	name;		 /* 名称 */ 
	private Integer	totalNum;		 /* 总发放量 */ 
	private Integer	usedNum;		 /* 已使用 */ 
	private Integer	remainNum;		 /* 剩余 */ 
	private String	status;		 /* 状态 0-未发放 1-已发放 2-已过期 见字典表 */ 
	private Integer	issueWay;		 /* 发放方式 0-线上发放 1-线下发放 */ 
	private String	forCrowd;		 /* 优惠对象 见字典表 */ 
	private Integer	useWay;		 /* 优惠方式 0-抵现 1-满减 2-打折 */ 
	private Integer	useRange;		 /* 优惠范围 0-全部课程  1-学科 2-课程 */ 
	private Date	timeLimitFrom;		 /* 使用期限从 */ 
	private Date	timeLimitTo;		 /* 使用期限至 */ 
	private String	promoCodePrefix;		 /* 优惠码前缀 */ 
	private Integer	promoCodeLength;		 /* 优惠码长度 */ 
	private Integer	noticWay;		 /* 通知方式 0-站内信 1-短信  2-站内信和短信 */ 
	private String	noticSmsContents;		 /* 短信内容 */ 
	private String	noticMsgContents;		 /* 站内信内容 */ 
	private Double	insCashNum;		 /* 抵现金额 */ 
	private Double	overNum;		 /* 满* 元减 */ 
	private Double	overCutNum;		 /* 满减*元 */ 
	private Double	discountNum;		 /* 折扣 */ 
	private String	memberList;		 /* 当选择优惠对象为会员时使用该字段，存会员ID，以逗号分隔 */ 
	private String	description;		 /* 优惠券说明 */ 
	private Integer	rangeItemOne;		 /* 范围-学科 */ 
	private Integer	rangeItemSecond;		 /* 范围-学科小类 */ 
	private Integer	rangeItemCourse;		 /* 范围-课程 */ 
	private String	userList;		 /* 优惠对象是指定用户时存指定的用户列表，以逗号分隔 */ 
	private Integer	companyId;		 /* 公司ID */ 
	private Integer delFlag;
	private Date sendDate;
	private Integer commodityType;
	private String rangeItemPackageCategory;

	private String rangeItemOneName;

	private String rangeItemSecondName;
	private String rangeItemCourseName;
	
	
	
	private String startStatus;
	
	private String startDate;
	private String endDate;
	
	public String getStartStatus() {
		return startStatus;
	}
	public void setStartStatus(String startStatus) {
		this.startStatus = startStatus;
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
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getIssueWay() {
		return issueWay;
	}
	public void setIssueWay(Integer issueWay) {
		this.issueWay = issueWay;
	}
	public String getForCrowd() {
		return forCrowd;
	}
	public void setForCrowd(String forCrowd) {
		this.forCrowd = forCrowd;
	}
	public Integer getUseWay() {
		return useWay;
	}
	public void setUseWay(Integer useWay) {
		this.useWay = useWay;
	}
	public Integer getUseRange() {
		return useRange;
	}
	public void setUseRange(Integer useRange) {
		this.useRange = useRange;
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
	public Integer getPromoCodeLength() {
		return promoCodeLength;
	}
	public void setPromoCodeLength(Integer promoCodeLength) {
		this.promoCodeLength = promoCodeLength;
	}
	public Integer getNoticWay() {
		return noticWay;
	}
	public void setNoticWay(Integer noticWay) {
		this.noticWay = noticWay;
	}
	public String getNoticSmsContents() {
		return noticSmsContents;
	}
	public void setNoticSmsContents(String noticSmsContents) {
		this.noticSmsContents = noticSmsContents;
	}
	public String getNoticMsgContents() {
		return noticMsgContents;
	}
	public void setNoticMsgContents(String noticMsgContents) {
		this.noticMsgContents = noticMsgContents;
	}
	public Double getInsCashNum() {
		return insCashNum;
	}
	public void setInsCashNum(Double insCashNum) {
		this.insCashNum = insCashNum;
	}
	public Double getOverNum() {
		return overNum;
	}
	public void setOverNum(Double overNum) {
		this.overNum = overNum;
	}
	public Double getOverCutNum() {
		return overCutNum;
	}
	public void setOverCutNum(Double overCutNum) {
		this.overCutNum = overCutNum;
	}
	public Double getDiscountNum() {
		return discountNum;
	}
	public void setDiscountNum(Double discountNum) {
		this.discountNum = discountNum;
	}
	public String getMemberList() {
		return memberList;
	}
	public void setMemberList(String memberList) {
		this.memberList = memberList;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getRangeItemOne() {
		return rangeItemOne;
	}
	public void setRangeItemOne(Integer rangeItemOne) {
		this.rangeItemOne = rangeItemOne;
	}
	public Integer getRangeItemSecond() {
		return rangeItemSecond;
	}
	public void setRangeItemSecond(Integer rangeItemSecond) {
		this.rangeItemSecond = rangeItemSecond;
	}
	public Integer getRangeItemCourse() {
		return rangeItemCourse;
	}
	public void setRangeItemCourse(Integer rangeItemCourse) {
		this.rangeItemCourse = rangeItemCourse;
	}
	public String getUserList() {
		return userList;
	}
	public void setUserList(String userList) {
		this.userList = userList;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getRangeItemOneName() {
		return rangeItemOneName;
	}
	public void setRangeItemOneName(String rangeItemOneName) {
		this.rangeItemOneName = rangeItemOneName;
	}
	public String getRangeItemSecondName() {
		return rangeItemSecondName;
	}
	public void setRangeItemSecondName(String rangeItemSecondName) {
		this.rangeItemSecondName = rangeItemSecondName;
	}
	public String getRangeItemCourseName() {
		return rangeItemCourseName;
	}
	public void setRangeItemCourseName(String rangeItemCourseName) {
		this.rangeItemCourseName = rangeItemCourseName;
	}
	public Integer getCommodityType() {
		return commodityType;
	}
	public void setCommodityType(Integer commodityType) {
		this.commodityType = commodityType;
	}
	public String getRangeItemPackageCategory() {
		return rangeItemPackageCategory;
	}
	public void setRangeItemPackageCategory(String rangeItemPackageCategory) {
		this.rangeItemPackageCategory = rangeItemPackageCategory;
	}
}
