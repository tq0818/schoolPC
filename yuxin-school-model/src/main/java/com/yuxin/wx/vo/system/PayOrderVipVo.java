package com.yuxin.wx.vo.system;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.util.LongDateSerializer;
import com.yuxin.wx.util.ShortDateSerializer;
import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:PayOrder
 * 
 * @author chopin
 * @date 2015-3-12
 */
@SuppressWarnings("serial")
public class PayOrderVipVo extends BaseEntity {
	
	
	private Integer	userId;		
	private String	orderNum;		 /* 订单id，本系统的订单号，订单号是时间戳 */ 
	private String	payNum;		 /* 支付流水id，支付宝或网银的流水号 */ 
	private Date	payTime;		 /* 支付时间 */ 
	private Date	orderTime;		 /* 订单创建时间 */ 
	private String	commodityName;		 /* 商品名称 */ 
	private Integer	commodityId;		 /* 商品id */ 
	private Double	originalPrice;		 /* 原始价格 */ 
	private Double	payPrice;		 /* 支付价格 */ 
	private String	payType;		 /* 支付类型（支付宝；网上银行；语音支付），字典表数据 */ 
	private String	payStatus;		 /* 支付状态（未支付；支付成功；支付失败）字典表数据 */ 
	private Integer	companyId;		 /* 所属公司id */ 
	private Integer	schoolId;		 /* 所属分校id */ 
	private String	discountNo;		 /* 优惠券号码 */ 
	private String remittanceNumber;	/* 汇款编号 */ 
	private Double collectionAmount;	/* 收款金额 */ 
	private Date collectionTime;		/* 收款时间 */ 
	private Integer classPackageStageId;	/*课程包阶段ID*/
	private String commdityType;		/*商品类型*/
	private String remark;				/* 备注 */
	
	private String username;
	private String memberLevel;/*会员等级*/
	private Integer memberId;/*会员id*/
	private Integer memberBuyLength;/*会员购买的时长,累积型会员为-1*/
	private Date memberEndTime;/*会员结束时间*/
	private String mobile;
	private String stuName;
	private String startTime;
	private String endTime;
	private Integer timeLen;
	private String timeMark;
	private String applyChannelCode;
	private String orderMemberLevel;
	private Integer memberLength;
	
	
	private String orderVipEndTime; 
	private String orderVipMemberLength;
	
	
	// Constructor
	public PayOrderVipVo() {
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为PayOrder可以实现连缀设置属性
	
	public PayOrderVipVo(Integer userId, String orderNum, String payNum,
			Date payTime, Date orderTime, String commodityName,
			Integer commodityId, Double originalPrice, Double payPrice,
			String payType, String payStatus, Integer companyId,
			Integer schoolId, String discountNo, String remittanceNumber,
			Double collectionAmount, Date collectionTime,
			Integer classPackageStageId, String commdityType, String remark,
			String memberLevel, Integer memberId, Integer memberBuyLength, Date memberEndTime,String mobile, 
			Integer integralInstead, Integer integralNum, 
			String stuName, String startTime, String endTime, Integer timeLen,
			String timeMark,String applyChannelCode,String orderMemberLevel,Integer memberLength,String orderVipEndTime,String orderVipMemberLength,String username) {
		super();
		this.userId = userId;
		this.orderNum = orderNum;
		this.payNum = payNum;
		this.payTime = payTime;
		this.orderTime = orderTime;
		this.commodityName = commodityName;
		this.commodityId = commodityId;
		this.originalPrice = originalPrice;
		this.payPrice = payPrice;
		this.payType = payType;
		this.payStatus = payStatus;
		this.companyId = companyId;
		this.schoolId = schoolId;
		this.discountNo = discountNo;
		this.remittanceNumber = remittanceNumber;
		this.collectionAmount = collectionAmount;
		this.collectionTime = collectionTime;
		this.classPackageStageId = classPackageStageId;
		this.commdityType = commdityType;
		this.remark = remark;
		this.memberLevel = memberLevel;
		this.memberId = memberId;
		this.memberBuyLength = memberBuyLength;
		this.memberEndTime = memberEndTime;
		this.mobile = mobile;
		this.stuName = stuName;
		this.startTime = startTime;
		this.endTime = endTime;
		this.timeLen = timeLen;
		this.timeMark = timeMark;
		this.applyChannelCode=applyChannelCode;
		this.orderMemberLevel=orderMemberLevel;
		this.memberLength=memberLength;
		this.orderVipEndTime=orderVipEndTime;
		this.orderVipMemberLength=orderVipMemberLength;
		this.username=username;
		
	}


	public String getUsername() {
		return username;
	}

	public PayOrderVipVo setUsername(String username) {
		this.username = username;
		return this;
	}

	public String getOrderVipEndTime() {
		return orderVipEndTime;
	}

	public PayOrderVipVo setOrderVipEndTime(String orderVipEndTime) {
		this.orderVipEndTime = orderVipEndTime;
		return this;
	}

	public String getOrderVipMemberLength() {
		return orderVipMemberLength;
	}

	public PayOrderVipVo setOrderVipMemberLength(String orderVipMemberLength) {
		this.orderVipMemberLength = orderVipMemberLength;
		return this;
	}

	public Integer getMemberLength() {
		return memberLength;
	}

	public PayOrderVipVo setMemberLength(Integer memberLength) {
		this.memberLength = memberLength;
		return this;
	}

	public String getOrderMemberLevel() {
		return orderMemberLevel;
	}

	public PayOrderVipVo setOrderMemberLevel(String orderMemberLevel) {
		this.orderMemberLevel = orderMemberLevel;
		return this;
	}

	public Integer getUserId() {
		return userId;
	}

	public PayOrderVipVo setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}
	
	
	public String getOrderNum() {
		return orderNum;
	}

	public PayOrderVipVo setOrderNum(String orderNum) {
		this.orderNum = orderNum;
		return this;
	}
	
	
	public String getPayNum() {
		return payNum;
	}

	public PayOrderVipVo setPayNum(String payNum) {
		this.payNum = payNum;
		return this;
	}
	
	@JsonSerialize(using = LongDateSerializer.class)
	public Date getPayTime() {
		return payTime;
	}

	public PayOrderVipVo setPayTime(Date payTime) {
		this.payTime = payTime;
		return this;
	}
	
	@JsonSerialize(using = LongDateSerializer.class)
	public Date getOrderTime() {
		return orderTime;
	}

	public PayOrderVipVo setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
		return this;
	}
	
	
	public String getCommodityName() {
		return commodityName;
	}

	public PayOrderVipVo setCommodityName(String commodityName) {
		this.commodityName = commodityName;
		return this;
	}
	
	
	public Integer getCommodityId() {
		return commodityId;
	}

	public PayOrderVipVo setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
		return this;
	}
	
	
	public Double getOriginalPrice() {
		return originalPrice;
	}

	public PayOrderVipVo setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
		return this;
	}
	
	
	public Double getPayPrice() {
		return payPrice;
	}

	public PayOrderVipVo setPayPrice(Double payPrice) {
		this.payPrice = payPrice;
		return this;
	}
	
	
	public String getPayType() {
		return payType;
	}

	public PayOrderVipVo setPayType(String payType) {
		this.payType = payType;
		return this;
	}
	
	
	public String getPayStatus() {
		return payStatus;
	}

	public PayOrderVipVo setPayStatus(String payStatus) {
		this.payStatus = payStatus;
		return this;
	}
	
	public Integer getCompanyId() {
		return companyId;
	}

	public PayOrderVipVo setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public Integer getSchoolId() {
		return schoolId;
	}

	public PayOrderVipVo setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
		return this;
	}
	
	
	public String getDiscountNo() {
		return discountNo;
	}

	public PayOrderVipVo setDiscountNo(String discountNo) {
		this.discountNo = discountNo;
		return this;
	}
	
	public String getRemittanceNumber() {
		return remittanceNumber;
	}

	public void setRemittanceNumber(String remittanceNumber) {
		this.remittanceNumber = remittanceNumber;
	}

	public Double getCollectionAmount() {
		return collectionAmount;
	}

	public void setCollectionAmount(Double collectionAmount) {
		this.collectionAmount = collectionAmount;
	}

	public Date getCollectionTime() {
		return collectionTime;
	}

	public void setCollectionTime(Date collectionTime) {
		this.collectionTime = collectionTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getClassPackageStageId() {
		return classPackageStageId;
	}

	public void setClassPackageStageId(Integer classPackageStageId) {
		this.classPackageStageId = classPackageStageId;
	}

	public String getCommdityType() {
		return commdityType;
	}

	public void setCommdityType(String commdityType) {
		this.commdityType = commdityType;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
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

	public String getMemberLevel() {
		return memberLevel;
	}

	public PayOrderVipVo setMemberLevel(String memberLevel) {
		this.memberLevel = memberLevel;
		return this;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public PayOrderVipVo setMemberId(Integer memberId) {
		this.memberId = memberId;
		return this;
	}

	public Integer getMemberBuyLength() {
		return memberBuyLength;
	}

	public PayOrderVipVo setMemberBuyLength(Integer memberBuyLength) {
		this.memberBuyLength = memberBuyLength;
		return this;
	}
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getMemberEndTime() {
		return memberEndTime;
	}

	public PayOrderVipVo setMemberEndTime(Date memberEndTime) {
		this.memberEndTime = memberEndTime;
		return this;
	}

	public String getMobile() {
		return mobile;
	}

	public PayOrderVipVo setMobile(String mobile) {
		this.mobile = mobile;
		return this;
	}

	public String getApplyChannelCode() {
		return applyChannelCode;
	}

	public PayOrderVipVo setApplyChannelCode(String applyChannelCode) {
		this.applyChannelCode = applyChannelCode;
		return this;
	}
	

}
