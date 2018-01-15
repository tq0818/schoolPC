package com.yuxin.wx.model.pay;

import java.util.Date;

import com.yuxin.wx.common.BaseEntity;
import org.apache.commons.lang.StringUtils;

/**
 * POJO:PayOrder
 * 
 * @author chopin
 * @date 2015-3-12
 */
@SuppressWarnings("serial")
public class PayOrder extends BaseEntity {

    private Integer userId;
    private String orderNum; /* 订单id，本系统的订单号，订单号是时间戳 */
    private String payNum; /* 支付流水id，支付宝或网银的流水号 */
    private Date payTime; /* 支付时间 */
    private Date orderTime; /* 订单创建时间 */
    private String commodityName; /* 商品名称 */
    private Integer commodityId; /* 商品id */
    private Double originalPrice; /* 原始价格 */
    private Double payPrice; /* 支付价格 */
    private String payType; /* 支付类型（支付宝；网上银行；语音支付），字典表数据 */
    private String payStatus; /* 支付状态（未支付；支付成功；支付失败）字典表数据 */
    private Integer itemOneId; /* 一级项目id */
    private Integer itemSecondId; /* 二级项目id */
    private Integer companyId; /* 所属公司id */
    private Integer schoolId; /* 所属分校id */
    private String discountNo; /* 优惠券号码 */
    private String remittanceNumber; /* 汇款编号 */
    private Double collectionAmount; /* 收款金额 */
    private Date collectionTime; /* 收款时间 */
    private Integer classPackageStageId; /* 课程包阶段ID */
    private String commdityType; /* 商品类型 */
    private String remark; /* 备注 */
    private Double integralInstead;/* 积分代金 */
    private Double memberInstead;/* 会员代金 */
    private Integer memberId;/* 会员id */
    private Integer integralUsed;/* 使用的积分数 */
    private Double memberDiscount;/* 会员折扣 */
    private Integer integralNum;/* 积分数量 */
    private Integer exchangeScale;/* 积分兑换比例 */
    private Integer memberLength;/* 购买会员时长 */
    private String bizCode;/* 业务类型 */
    private Double couponInstead;/* 优惠券代金 */
    private String couponCode;/* 优惠码 */
    private Double realPrice;
    private Double usedRecharge;

    private String mobile;
    private String stuName;
    private String startDate;
    private String endDate;
    private Integer timeLen;
    private String timeMark;

    private String aeraName;
    private String schoolName;
    private String totalMoney;
    private String fetchMoney;

    private String teacherName;
    private String sex;
    private String teacherLevel;
    private String handInMoney;

    public String getHandInMoney() {
        if(StringUtils.isNotBlank(totalMoney) && StringUtils.isNotBlank(fetchMoney)){
            this.handInMoney = String.valueOf(Integer.parseInt(totalMoney)-Integer.parseInt(fetchMoney));
        }
        if(StringUtils.isBlank(totalMoney) && StringUtils.isBlank(fetchMoney)){
            this.handInMoney = String.valueOf(0);
        }
        if(StringUtils.isNotBlank(totalMoney) && StringUtils.isBlank(fetchMoney)){
            this.handInMoney = String.valueOf(Integer.parseInt(totalMoney)-0);
        }
        return handInMoney;
    }

    public void setHandInMoney(String handInMoney) {
        this.handInMoney = handInMoney;
    }

    public String getTeacherLevel() {
        return teacherLevel;
    }

    public void setTeacherLevel(String teacherLevel) {
        this.teacherLevel = teacherLevel;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAeraName() {
        return aeraName;
    }

    public void setAeraName(String aeraName) {
        this.aeraName = aeraName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getFetchMoney() {
        return fetchMoney;
    }

    public void setFetchMoney(String fetchMoney) {
        this.fetchMoney = fetchMoney;
    }

    public PayOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

    public PayOrder(Integer userId, String orderNum, String payNum, Date payTime, Date orderTime, String commodityName, Integer commodityId,
            Double originalPrice, Double payPrice, String payType, String payStatus, Integer itemOneId, Integer itemSecondId, Integer companyId,
            Integer schoolId, String discountNo, String remittanceNumber, Double collectionAmount, Date collectionTime, Integer classPackageStageId,
            String commdityType, String remark, Double integralInstead, Double memberInstead, Integer memberId, Integer integralUsed, Double memberDiscount,
            Integer integralNum, Integer exchangeScale, Integer memberLength, String bizCode, Double couponInstead, String couponCode, Double realPrice,
            Double usedRecharge, String mobile, String stuName, String startDate, String endDate, Integer timeLen, String timeMark) {
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
        this.itemOneId = itemOneId;
        this.itemSecondId = itemSecondId;
        this.companyId = companyId;
        this.schoolId = schoolId;
        this.discountNo = discountNo;
        this.remittanceNumber = remittanceNumber;
        this.collectionAmount = collectionAmount;
        this.collectionTime = collectionTime;
        this.classPackageStageId = classPackageStageId;
        this.commdityType = commdityType;
        this.remark = remark;
        this.integralInstead = integralInstead;
        this.memberInstead = memberInstead;
        this.memberId = memberId;
        this.integralUsed = integralUsed;
        this.memberDiscount = memberDiscount;
        this.integralNum = integralNum;
        this.exchangeScale = exchangeScale;
        this.memberLength = memberLength;
        this.bizCode = bizCode;
        this.couponInstead = couponInstead;
        this.couponCode = couponCode;
        this.realPrice = realPrice;
        this.usedRecharge = usedRecharge;
        this.mobile = mobile;
        this.stuName = stuName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.timeLen = timeLen;
        this.timeMark = timeMark;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getPayNum() {
        return payNum;
    }

    public void setPayNum(String payNum) {
        this.payNum = payNum;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Double getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(Double payPrice) {
        this.payPrice = payPrice;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public Integer getItemOneId() {
        return itemOneId;
    }

    public void setItemOneId(Integer itemOneId) {
        this.itemOneId = itemOneId;
    }

    public Integer getItemSecondId() {
        return itemSecondId;
    }

    public void setItemSecondId(Integer itemSecondId) {
        this.itemSecondId = itemSecondId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getDiscountNo() {
        return discountNo;
    }

    public void setDiscountNo(String discountNo) {
        this.discountNo = discountNo;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Double getIntegralInstead() {
        return integralInstead;
    }

    public void setIntegralInstead(Double integralInstead) {
        this.integralInstead = integralInstead;
    }

    public Double getMemberInstead() {
        return memberInstead;
    }

    public void setMemberInstead(Double memberInstead) {
        this.memberInstead = memberInstead;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getIntegralUsed() {
        return integralUsed;
    }

    public void setIntegralUsed(Integer integralUsed) {
        this.integralUsed = integralUsed;
    }

    public Double getMemberDiscount() {
        return memberDiscount;
    }

    public void setMemberDiscount(Double memberDiscount) {
        this.memberDiscount = memberDiscount;
    }

    public Integer getIntegralNum() {
        return integralNum;
    }

    public void setIntegralNum(Integer integralNum) {
        this.integralNum = integralNum;
    }

    public Integer getExchangeScale() {
        return exchangeScale;
    }

    public void setExchangeScale(Integer exchangeScale) {
        this.exchangeScale = exchangeScale;
    }

    public Integer getMemberLength() {
        return memberLength;
    }

    public void setMemberLength(Integer memberLength) {
        this.memberLength = memberLength;
    }

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public Double getCouponInstead() {
        return couponInstead;
    }

    public void setCouponInstead(Double couponInstead) {
        this.couponInstead = couponInstead;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public Double getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(Double realPrice) {
        this.realPrice = realPrice;
    }

    public Double getUsedRecharge() {
        return usedRecharge;
    }

    public void setUsedRecharge(Double usedRecharge) {
        this.usedRecharge = usedRecharge;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
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

    @Override
    public String toString() {
        return "PayOrder [userId=" + userId + ", orderNum=" + orderNum + ", payNum=" + payNum + ", payTime=" + payTime + ", orderTime=" + orderTime
                + ", commodityName=" + commodityName + ", commodityId=" + commodityId + ", originalPrice=" + originalPrice + ", payPrice=" + payPrice
                + ", payType=" + payType + ", payStatus=" + payStatus + ", itemOneId=" + itemOneId + ", itemSecondId=" + itemSecondId + ", companyId="
                + companyId + ", schoolId=" + schoolId + ", discountNo=" + discountNo + ", remittanceNumber=" + remittanceNumber + ", collectionAmount="
                + collectionAmount + ", collectionTime=" + collectionTime + ", classPackageStageId=" + classPackageStageId + ", commdityType=" + commdityType
                + ", remark=" + remark + ", integralInstead=" + integralInstead + ", memberInstead=" + memberInstead + ", memberId=" + memberId
                + ", integralUsed=" + integralUsed + ", memberDiscount=" + memberDiscount + ", integralNum=" + integralNum + ", exchangeScale=" + exchangeScale
                + ", memberLength=" + memberLength + ", bizCode=" + bizCode + ", couponInstead=" + couponInstead + ", couponCode=" + couponCode + ", realPrice="
                + realPrice + ", usedRecharge=" + usedRecharge + ", mobile=" + mobile + ", stuName=" + stuName + ", startDate=" + startDate + ", endDate="
                + endDate + ", timeLen=" + timeLen + ", timeMark=" + timeMark + ", id=" + id + ", getUserId()=" + getUserId() + ", getOrderNum()="
                + getOrderNum() + ", getPayNum()=" + getPayNum() + ", getPayTime()=" + getPayTime() + ", getOrderTime()=" + getOrderTime()
                + ", getCommodityName()=" + getCommodityName() + ", getCommodityId()=" + getCommodityId() + ", getOriginalPrice()=" + getOriginalPrice()
                + ", getPayPrice()=" + getPayPrice() + ", getPayType()=" + getPayType() + ", getPayStatus()=" + getPayStatus() + ", getItemOneId()="
                + getItemOneId() + ", getItemSecondId()=" + getItemSecondId() + ", getCompanyId()=" + getCompanyId() + ", getSchoolId()=" + getSchoolId()
                + ", getDiscountNo()=" + getDiscountNo() + ", getRemittanceNumber()=" + getRemittanceNumber() + ", getCollectionAmount()="
                + getCollectionAmount() + ", getCollectionTime()=" + getCollectionTime() + ", getClassPackageStageId()=" + getClassPackageStageId()
                + ", getCommdityType()=" + getCommdityType() + ", getRemark()=" + getRemark() + ", getIntegralInstead()=" + getIntegralInstead()
                + ", getMemberInstead()=" + getMemberInstead() + ", getMemberId()=" + getMemberId() + ", getIntegralUsed()=" + getIntegralUsed()
                + ", getMemberDiscount()=" + getMemberDiscount() + ", getIntegralNum()=" + getIntegralNum() + ", getExchangeScale()=" + getExchangeScale()
                + ", getMemberLength()=" + getMemberLength() + ", getBizCode()=" + getBizCode() + ", getCouponInstead()=" + getCouponInstead()
                + ", getCouponCode()=" + getCouponCode() + ", getRealPrice()=" + getRealPrice() + ", getUsedRecharge()=" + getUsedRecharge() + ", getMobile()="
                + getMobile() + ", getStuName()=" + getStuName() + ", getStartDate()=" + getStartDate() + ", getEndDate()=" + getEndDate() + ", getTimeLen()="
                + getTimeLen() + ", getTimeMark()=" + getTimeMark() + ", getId()=" + getId() + ", toString()=" + super.toString() + ", hashCode()=" + hashCode()
                + ", getFirstIndex()=" + getFirstIndex() + ", getTotalPages()=" + getTotalPages() + ", getLastPageNo()=" + getLastPageNo()
                + ", getPreviousPageNo()=" + getPreviousPageNo() + ", getNextPageNo()=" + getNextPageNo() + ", getTotalRecords()=" + getTotalRecords()
                + ", getPage()=" + getPage() + ", getPageSize()=" + getPageSize() + ", getStart()=" + getStart() + ", getLimit()=" + getLimit()
                + ", getClass()=" + getClass() + "]";
    }

}
