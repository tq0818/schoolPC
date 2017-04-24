package com.yuxin.wx.model.company;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.util.ShortDateSerializer;
import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyCouponsPatch
 * 
 * @author wang.zx
 * @date 2016-6-21
 */
@SuppressWarnings("serial")
public class CompanyCouponsPatch extends BaseEntity {
	
	
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
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getSendDate() {
		return sendDate;
	}

	public CompanyCouponsPatch setSendDate(Date sendDate) {
		this.sendDate = sendDate;
		return this;
	}

	// Constructor
	public CompanyCouponsPatch() {
	}
	
	/**
	 * full Constructor
	 */
	

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CompanyCouponsPatch可以实现连缀设置属性
	
	public String getName() {
		return name;
	}
	public CompanyCouponsPatch(String name, Integer totalNum, Integer usedNum,
			Integer remainNum, String status, Integer issueWay,
			String forCrowd, Integer useWay, Integer useRange,
			Date timeLimitFrom, Date timeLimitTo, String promoCodePrefix,
			Integer promoCodeLength, Integer noticWay, String noticSmsContents,
			String noticMsgContents, Double insCashNum, Double overNum,
			Double overCutNum, Double discountNum, String memberList,
			String description, Integer rangeItemOne, Integer rangeItemSecond,
			Integer rangeItemCourse, String userList, Integer companyId,
			Integer delFlag,Date sendDate) {
		super();
		this.name = name;
		this.totalNum = totalNum;
		this.usedNum = usedNum;
		this.remainNum = remainNum;
		this.status = status;
		this.issueWay = issueWay;
		this.forCrowd = forCrowd;
		this.useWay = useWay;
		this.useRange = useRange;
		this.timeLimitFrom = timeLimitFrom;
		this.timeLimitTo = timeLimitTo;
		this.promoCodePrefix = promoCodePrefix;
		this.promoCodeLength = promoCodeLength;
		this.noticWay = noticWay;
		this.noticSmsContents = noticSmsContents;
		this.noticMsgContents = noticMsgContents;
		this.insCashNum = insCashNum;
		this.overNum = overNum;
		this.overCutNum = overCutNum;
		this.discountNum = discountNum;
		this.memberList = memberList;
		this.description = description;
		this.rangeItemOne = rangeItemOne;
		this.rangeItemSecond = rangeItemSecond;
		this.rangeItemCourse = rangeItemCourse;
		this.userList = userList;
		this.companyId = companyId;
		this.delFlag = delFlag;
		this.sendDate=sendDate;
	}

	public CompanyCouponsPatch setName(String name) {
		this.name = name;
		return this;
	}
	
	
	public Integer getTotalNum() {
		return totalNum;
	}

	public CompanyCouponsPatch setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
		return this;
	}
	
	
	public Integer getUsedNum() {
		return usedNum;
	}

	public CompanyCouponsPatch setUsedNum(Integer usedNum) {
		this.usedNum = usedNum;
		return this;
	}
	
	
	public Integer getRemainNum() {
		return remainNum;
	}

	public CompanyCouponsPatch setRemainNum(Integer remainNum) {
		this.remainNum = remainNum;
		return this;
	}
	
	
	public String getStatus() {
		return status;
	}

	public CompanyCouponsPatch setStatus(String status) {
		this.status = status;
		return this;
	}
	
	
	public Integer getIssueWay() {
		return issueWay;
	}

	public CompanyCouponsPatch setIssueWay(Integer issueWay) {
		this.issueWay = issueWay;
		return this;
	}
	
	
	public String getForCrowd() {
		return forCrowd;
	}

	public CompanyCouponsPatch setForCrowd(String forCrowd) {
		this.forCrowd = forCrowd;
		return this;
	}
	
	
	public Integer getUseWay() {
		return useWay;
	}

	public CompanyCouponsPatch setUseWay(Integer useWay) {
		this.useWay = useWay;
		return this;
	}
	
	
	public Integer getUseRange() {
		return useRange;
	}

	public CompanyCouponsPatch setUseRange(Integer useRange) {
		this.useRange = useRange;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getTimeLimitFrom() {
		return timeLimitFrom;
	}

	public CompanyCouponsPatch setTimeLimitFrom(Date timeLimitFrom) {
		this.timeLimitFrom = timeLimitFrom;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getTimeLimitTo() {
		return timeLimitTo;
	}

	public CompanyCouponsPatch setTimeLimitTo(Date timeLimitTo) {
		this.timeLimitTo = timeLimitTo;
		return this;
	}
	
	
	public String getPromoCodePrefix() {
		return promoCodePrefix;
	}

	public CompanyCouponsPatch setPromoCodePrefix(String promoCodePrefix) {
		this.promoCodePrefix = promoCodePrefix;
		return this;
	}
	
	
	public Integer getPromoCodeLength() {
		return promoCodeLength;
	}

	public CompanyCouponsPatch setPromoCodeLength(Integer promoCodeLength) {
		this.promoCodeLength = promoCodeLength;
		return this;
	}
	
	
	public Integer getNoticWay() {
		return noticWay;
	}

	public CompanyCouponsPatch setNoticWay(Integer noticWay) {
		this.noticWay = noticWay;
		return this;
	}
	
	
	public String getNoticSmsContents() {
		return noticSmsContents;
	}

	public CompanyCouponsPatch setNoticSmsContents(String noticSmsContents) {
		this.noticSmsContents = noticSmsContents;
		return this;
	}
	
	
	public String getNoticMsgContents() {
		return noticMsgContents;
	}

	public CompanyCouponsPatch setNoticMsgContents(String noticMsgContents) {
		this.noticMsgContents = noticMsgContents;
		return this;
	}
	
	
	public Double getInsCashNum() {
		return insCashNum;
	}

	public CompanyCouponsPatch setInsCashNum(Double insCashNum) {
		this.insCashNum = insCashNum;
		return this;
	}
	
	
	public Double getOverNum() {
		return overNum;
	}

	public CompanyCouponsPatch setOverNum(Double overNum) {
		this.overNum = overNum;
		return this;
	}
	
	
	public Double getOverCutNum() {
		return overCutNum;
	}

	public CompanyCouponsPatch setOverCutNum(Double overCutNum) {
		this.overCutNum = overCutNum;
		return this;
	}
	
	
	public Double getDiscountNum() {
		return discountNum;
	}

	public CompanyCouponsPatch setDiscountNum(Double discountNum) {
		this.discountNum = discountNum;
		return this;
	}
	
	
	public String getMemberList() {
		return memberList;
	}

	public CompanyCouponsPatch setMemberList(String memberList) {
		this.memberList = memberList;
		return this;
	}
	
	
	public String getDescription() {
		return description;
	}

	public CompanyCouponsPatch setDescription(String description) {
		this.description = description;
		return this;
	}
	
	
	public Integer getRangeItemOne() {
		return rangeItemOne;
	}

	public CompanyCouponsPatch setRangeItemOne(Integer rangeItemOne) {
		this.rangeItemOne = rangeItemOne;
		return this;
	}
	
	
	public Integer getRangeItemSecond() {
		return rangeItemSecond;
	}

	public CompanyCouponsPatch setRangeItemSecond(Integer rangeItemSecond) {
		this.rangeItemSecond = rangeItemSecond;
		return this;
	}
	
	
	public Integer getRangeItemCourse() {
		return rangeItemCourse;
	}

	public CompanyCouponsPatch setRangeItemCourse(Integer rangeItemCourse) {
		this.rangeItemCourse = rangeItemCourse;
		return this;
	}
	
	
	public String getUserList() {
		return userList;
	}

	public CompanyCouponsPatch setUserList(String userList) {
		this.userList = userList;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public CompanyCouponsPatch setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	@Override
	public String toString() {
		return "CompanyCouponsPatch [" + "id=" + getId() + ", name=" + name + ", totalNum=" + totalNum + ", usedNum=" + usedNum + ", remainNum=" + remainNum + ", status=" + status + ", issueWay=" + issueWay + ", forCrowd=" + forCrowd + ", useWay=" + useWay + ", useRange=" + useRange + ", timeLimitFrom=" + timeLimitFrom + ", timeLimitTo=" + timeLimitTo + ", promoCodePrefix=" + promoCodePrefix + ", promoCodeLength=" + promoCodeLength + ", noticWay=" + noticWay + ", noticSmsContents=" + noticSmsContents + ", noticMsgContents=" + noticMsgContents + ", insCashNum=" + insCashNum + ", overNum=" + overNum + ", overCutNum=" + overCutNum + ", discountNum=" + discountNum + ", memberList=" + memberList + ", description=" + description + ", rangeItemOne=" + rangeItemOne + ", rangeItemSecond=" + rangeItemSecond + ", rangeItemCourse=" + rangeItemCourse + ", userList=" + userList + ", companyId=" + companyId +  "]";
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
