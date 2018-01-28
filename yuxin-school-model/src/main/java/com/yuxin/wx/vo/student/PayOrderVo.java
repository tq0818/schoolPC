package com.yuxin.wx.vo.student;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.util.LongDateSerializer;

public class PayOrderVo {
    private Integer id;
    private Integer userId;
    private String orderNum; /* 订单id，本系统的订单号，订单号是时间戳 */
    private String payNum; /* 支付流水id，支付宝或网银的流水号 */
    private Date payTime; /* 支付时间 */
    private Date orderTime; /* 订单创建时间 */
    private String commodityName; /* 商品名称 */
    private String commdityType;/* 商品类型 */
    private String commodityId; /* 商品id */
    private Double originalPrice; /* 原始价格 */
    private Double payPrice; /* 支付价格 */
    private Double realPrice;/* 原价 */
    private String payType; /* 支付类型（支付宝；网上银行；语音支付），字典表数据 */
    private String payStatus; /* 支付状态（未支付；支付成功；支付失败）字典表数据 */
    private Integer itemOneId; /* 一级项目id */
    private Integer itemSecondId; /* 二级项目id */
    private Integer companyId; /* 所属公司id */
    private Integer schoolId; /* 所属分校id */
    private String discountNo; /* 优惠券号码 */
    private Integer classPackageStageId; /* 课程包阶段ID */
    private String lableType; /* 班级授课类型 */
    private String coverUrl; /* 商品封面图片 */
    private Integer faceFlag; /* 是否属于面授 */
    private Integer liveFlag; /* 是否属于直播 */
    private Integer videoFlag; /* 是否属于视频 */
    private Integer remoteFlag; /* 是否属于远程 */
    private Integer spmId;/* stuent_pay_master的主键 */
    private Double integralInstead;/* 积分代金 */
    private Double memberInstead;/* 会员代金 */
    private Integer memberId;/* 会员id */
    private Integer integralUsed;/* 使用的积分数 */
    private Integer memberDiscount;/* 会员折扣 */
    private Integer integralNum;/* 积分数量 */
    private Integer exchangeScale;/* 积分兑换比例 */
    private Integer memberLength;/* 购买会员时长 */
    private String bizCode;/* 业务类型 */

    private String userName;/* 关联用户表的用户姓名 */
    private String nickName;/* 关联用户表的用户昵称 */
    private String userMobile;/* 关联用户表的用户手机号 */
    private Double couponInstead;/* 优惠券代金 */
    private String couponCode;/* 优惠码 */
    private Double usedRecharge;
    private String remark;
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

    @JsonSerialize(using = LongDateSerializer.class)
    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    @JsonSerialize(using = LongDateSerializer.class)
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

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
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

    public Double getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(Double realPrice) {
        this.realPrice = realPrice;
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

    public Integer getClassPackageStageId() {
        return classPackageStageId;
    }

    public void setClassPackageStageId(Integer classPackageStageId) {
        this.classPackageStageId = classPackageStageId;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLableType() {
        return lableType;
    }

    public void setLableType(String lableType) {
        this.lableType = lableType;
    }

    public Integer getFaceFlag() {
        return faceFlag;
    }

    public void setFaceFlag(Integer faceFlag) {
        this.faceFlag = faceFlag;
    }

    public Integer getLiveFlag() {
        return liveFlag;
    }

    public void setLiveFlag(Integer liveFlag) {
        this.liveFlag = liveFlag;
    }

    public Integer getVideoFlag() {
        return videoFlag;
    }

    public void setVideoFlag(Integer videoFlag) {
        this.videoFlag = videoFlag;
    }

    public Integer getRemoteFlag() {
        return remoteFlag;
    }

    public void setRemoteFlag(Integer remoteFlag) {
        this.remoteFlag = remoteFlag;
    }

    public Integer getSpmId() {
        return spmId;
    }

    public void setSpmId(Integer spmId) {
        this.spmId = spmId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
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

    public Integer getMemberDiscount() {
        return memberDiscount;
    }

    public void setMemberDiscount(Integer memberDiscount) {
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

    public String getCommdityType() {
        return commdityType;
    }

    public void setCommdityType(String commdityType) {
        this.commdityType = commdityType;
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

    public Double getUsedRecharge() {
        return usedRecharge;
    }

    public void setUsedRecharge(Double usedRecharge) {
        this.usedRecharge = usedRecharge;
    }

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
    
}
