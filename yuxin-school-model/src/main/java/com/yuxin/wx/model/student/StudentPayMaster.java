package com.yuxin.wx.model.student;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.util.ShortDateSerializer;

/**
 * POJO:StudentPayMaster
 *
 * @author wang.zx
 * @date 2014-12-5
 */
@SuppressWarnings("serial")
public class StudentPayMaster extends BaseEntity {

    private Date applyTime; /* 报名时间。开单时可以调整。 */
    private Integer examTermId; /* 考期id */
    private String examTermName; /* 考期名称 */
    private Integer itemOneId; /* 一级项目id */
    private String itemOneName; /* 一级项目名称 */
    private Integer itemSecondId; /* 二级项目id */
    private String itemSecondName; /* 二级项目名称 */
    private String classTypeName; /* 班型名称 */
    private Integer classTypeHour; /* 班型总课时 */
    private Integer userId; /* 用户id */
    private Integer stuId; /* 学生id */
    private Integer schoolId; /* 分校id */
    private Integer campusId; /* 校区id */
    private Integer applyPlaceId; /* 报名点代码 */
    private String applyChannelCode; /*
                                      * 报名渠道代码：在线报名、分校报名 字典表
                                      */
    private String payStatusCode; /*
                                   * 订单状态｛未支付、部分支付，已支付，已转人，已转班型，已延期，已冻结，已完成，已作废｝
                                   * 如果是分期缴费的，所有分期付完后更新该字段的状态为已支付，否则一直是部分支付； 字典表
                                   */
    private String bizStatusCode; /* 订单业务类型(新报名、重修、转人、转班型。)，字典表 */
    private String isAgent; /* 是否代报考（1：是；0：否） */
    private Integer isAgentMaterialComplete; /* 代报考资料是否齐全（1：是；0：否） */
    private String isAgentComplete; /* 是否完成代报考（1：是；0：否） */
    private String agentRemark; /* 代报考备注 */
    private Double trainingFee; /* 培训费用=班型定价 */
    private Double examAgentFee; /* 代报费用 */
    private String offerTypeCode; /* 优惠类型：优惠卷字典表，保留字段，暂时不用 */
    private Double offerAmount; /*
                                 * 优惠总额。有可能是多张优惠券的合计金额也有可能是内部员工学习、特批学员的优惠。保留字段，
                                 * 暂时不用
                                 */
    private Double totalAmount; /* 订单合计金额。订单合计=培训费用+代报费-优惠金额。 */
    private String paymentTypeCode; /* 付款方式（全款、分期、贷款），字典表 */
    private String remark; /* 订单备注 */
    private Integer relatedPayId; /* 相关联的订单id，转人转班型可能会用上。 */
    private Date createTime; /* 订单创建时间 */
    private String creator; /* 订单创建人（课程顾问） */
    private Date updateTime; /* 订单最后更新时间 */
    private String updator; /* 操作员（当前操作员） */
    private Integer deleteFlag; /*
                                 * 删除标记： 1：已删除；0：未删除
                                 */
    private Integer companyId;

    private Double hasPay; /* 已交费用 */
    private Double unPay;
    private Integer payOrderId; /* 相关联的订单 */

    private Double usedFee;

    // 当前主订单班型对应的商品ID
    private Integer commodityId;

    private String message;

    private Integer classPackageStageId; /* 课程阶段id */
    private String commodityType; /* 商品类型 */

    private Double integralInstead;/* 积分代金 */
    private Double memberInstead;/* 会员代金 */
    private Integer memberId;/* 会员id */
    private Integer integralUsed;/* 使用的积分数 */
    private Integer memberDiscount;/* 会员折扣 */
    private Integer integralNum;/* 积分数量 */
    private Integer exchangeScale;/* 积分兑换比例 */
    private Integer memberLength;/* 会员购买时长 */
    private Double couponInstead;
    private String couponCode;
    private String itemSecondCode;
    private String itemThirdCode;

    private Date someDayBegin;//和someDayEnd配合使用，一个时间段查询

    private Date someDayEnd;//和someDayBegin配合使用，一个时间段查询

    private Date earlyDay;//someDayEnd更早以前时间

    private int startIndex;//特殊使用，分页启始偏移量

    // private Student student;
    // Constructor
    public StudentPayMaster() {
    }

    /**
     * full Constructor
     */
    public StudentPayMaster(Integer payOrderId, Integer id, Date applyTime, Integer examTermId, String examTermName, Integer itemOneId, String itemOneName,
            Integer itemSecondId, String itemSecondName, String classTypeName, Integer classTypeHour, Integer stuId, Integer schoolId, Integer campusId,
            Integer applyPlaceId, String applyChannelCode, String payStatusCode, String bizStatusCode, String isAgent, Integer isAgentMaterialComplete,
            String isAgentComplete, String agentRemark, Double trainingFee, Double examAgentFee, String offerTypeCode, Double offerAmount, Double totalAmount,
            String paymentTypeCode, String remark, Integer relatedPayId, Date createTime, String creator, Date updateTime, String updator, Integer deleteFlag) {
        this.setId(id);
        this.applyTime = applyTime;
        this.examTermId = examTermId;
        this.examTermName = examTermName;
        this.itemOneId = itemOneId;
        this.itemOneName = itemOneName;
        this.itemSecondId = itemSecondId;
        this.itemSecondName = itemSecondName;
        this.classTypeName = classTypeName;
        this.classTypeHour = classTypeHour;
        this.stuId = stuId;
        this.schoolId = schoolId;
        this.campusId = campusId;
        this.applyPlaceId = applyPlaceId;
        this.applyChannelCode = applyChannelCode;
        this.payStatusCode = payStatusCode;
        this.bizStatusCode = bizStatusCode;
        this.isAgent = isAgent;
        this.isAgentMaterialComplete = isAgentMaterialComplete;
        this.isAgentComplete = isAgentComplete;
        this.agentRemark = agentRemark;
        this.trainingFee = trainingFee;
        this.examAgentFee = examAgentFee;
        this.offerTypeCode = offerTypeCode;
        this.offerAmount = offerAmount;
        this.totalAmount = totalAmount;
        this.paymentTypeCode = paymentTypeCode;
        this.remark = remark;
        this.relatedPayId = relatedPayId;
        this.createTime = createTime;
        this.creator = creator;
        this.updateTime = updateTime;
        this.updator = updator;
        this.deleteFlag = deleteFlag;
        this.payOrderId = payOrderId;
    }

    // getter && setter
    // 在setter方法最后加上"return this;"并把返回参数改为StudentPayMaster可以实现连缀设置属性
    @JsonSerialize(using = ShortDateSerializer.class)
    public Date getApplyTime() {
        return this.applyTime;
    }

    public StudentPayMaster setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
        return this;
    }

    public Integer getExamTermId() {
        return this.examTermId;
    }

    public StudentPayMaster setExamTermId(Integer examTermId) {
        this.examTermId = examTermId;
        return this;
    }

    public String getExamTermName() {
        return this.examTermName;
    }

    public StudentPayMaster setExamTermName(String examTermName) {
        this.examTermName = examTermName;
        return this;
    }

    public Integer getItemOneId() {
        return this.itemOneId;
    }

    public StudentPayMaster setItemOneId(Integer itemOneId) {
        this.itemOneId = itemOneId;
        return this;
    }

    public String getItemOneName() {
        return this.itemOneName;
    }

    public StudentPayMaster setItemOneName(String itemOneName) {
        this.itemOneName = itemOneName;
        return this;
    }

    public Integer getItemSecondId() {
        return this.itemSecondId;
    }

    public StudentPayMaster setItemSecondId(Integer itemSecondId) {
        this.itemSecondId = itemSecondId;
        return this;
    }

    public String getItemSecondName() {
        return this.itemSecondName;
    }

    public StudentPayMaster setItemSecondName(String itemSecondName) {
        this.itemSecondName = itemSecondName;
        return this;
    }

    public String getClassTypeName() {
        return this.classTypeName;
    }

    public StudentPayMaster setClassTypeName(String classTypeName) {
        this.classTypeName = classTypeName;
        return this;
    }

    public Integer getClassTypeHour() {
        return this.classTypeHour;
    }

    public StudentPayMaster setClassTypeHour(Integer classTypeHour) {
        this.classTypeHour = classTypeHour;
        return this;
    }

    public Integer getStuId() {
        return this.stuId;
    }

    public StudentPayMaster setStuId(Integer stuId) {
        this.stuId = stuId;
        return this;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSchoolId() {
        return this.schoolId;
    }

    public StudentPayMaster setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
        return this;
    }

    public Integer getCampusId() {
        return this.campusId;
    }

    public StudentPayMaster setCampusId(Integer campusId) {
        this.campusId = campusId;
        return this;
    }

    public Integer getApplyPlaceId() {
        return this.applyPlaceId;
    }

    public StudentPayMaster setApplyPlaceId(Integer applyPlaceId) {
        this.applyPlaceId = applyPlaceId;
        return this;
    }

    public String getApplyChannelCode() {
        return this.applyChannelCode;
    }

    public StudentPayMaster setApplyChannelCode(String applyChannelCode) {
        this.applyChannelCode = applyChannelCode;
        return this;
    }

    public String getPayStatusCode() {
        return this.payStatusCode;
    }

    public StudentPayMaster setPayStatusCode(String payStatusCode) {
        this.payStatusCode = payStatusCode;
        return this;
    }

    public String getBizStatusCode() {
        return this.bizStatusCode;
    }

    public StudentPayMaster setBizStatusCode(String bizStatusCode) {
        this.bizStatusCode = bizStatusCode;
        return this;
    }

    public String getIsAgent() {
        return this.isAgent;
    }

    public StudentPayMaster setIsAgent(String isAgent) {
        this.isAgent = isAgent;
        return this;
    }

    public Integer getIsAgentMaterialComplete() {
        return this.isAgentMaterialComplete;
    }

    public StudentPayMaster setIsAgentMaterialComplete(Integer isAgentMaterialComplete) {
        this.isAgentMaterialComplete = isAgentMaterialComplete;
        return this;
    }

    public String getIsAgentComplete() {
        return this.isAgentComplete;
    }

    public StudentPayMaster setIsAgentComplete(String isAgentComplete) {
        this.isAgentComplete = isAgentComplete;
        return this;
    }

    public String getAgentRemark() {
        return this.agentRemark;
    }

    public StudentPayMaster setAgentRemark(String agentRemark) {
        this.agentRemark = agentRemark;
        return this;
    }

    public Double getTrainingFee() {
        return this.trainingFee;
    }

    public StudentPayMaster setTrainingFee(Double trainingFee) {
        this.trainingFee = trainingFee;
        return this;
    }

    public Double getExamAgentFee() {
        return this.examAgentFee;
    }

    public StudentPayMaster setExamAgentFee(Double examAgentFee) {
        this.examAgentFee = examAgentFee;
        return this;
    }

    public String getOfferTypeCode() {
        return this.offerTypeCode;
    }

    public StudentPayMaster setOfferTypeCode(String offerTypeCode) {
        this.offerTypeCode = offerTypeCode;
        return this;
    }

    public Double getOfferAmount() {
        return this.offerAmount;
    }

    public StudentPayMaster setOfferAmount(Double offerAmount) {
        this.offerAmount = offerAmount;
        return this;
    }

    public Double getTotalAmount() {
        return this.totalAmount;
    }

    public StudentPayMaster setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public String getPaymentTypeCode() {
        return this.paymentTypeCode;
    }

    public StudentPayMaster setPaymentTypeCode(String paymentTypeCode) {
        this.paymentTypeCode = paymentTypeCode;
        return this;
    }

    public String getRemark() {
        return this.remark;
    }

    public StudentPayMaster setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public Integer getRelatedPayId() {
        return this.relatedPayId;
    }

    public StudentPayMaster setRelatedPayId(Integer relatedPayId) {
        this.relatedPayId = relatedPayId;
        return this;
    }

    @JsonSerialize(using = ShortDateSerializer.class)
    public Date getCreateTime() {
        return this.createTime;
    }

    public StudentPayMaster setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getCreator() {
        return this.creator;
    }

    public StudentPayMaster setCreator(String creator) {
        this.creator = creator;
        return this;
    }

    @JsonSerialize(using = ShortDateSerializer.class)
    public Date getUpdateTime() {
        return this.updateTime;
    }

    public StudentPayMaster setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public String getUpdator() {
        return this.updator;
    }

    public StudentPayMaster setUpdator(String updator) {
        this.updator = updator;
        return this;
    }

    public Integer getDeleteFlag() {
        return this.deleteFlag;
    }

    public StudentPayMaster setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
        return this;
    }

    public Integer getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Double getHasPay() {
        return this.hasPay;
    }

    public void setHasPay(Double hasPay) {
        this.hasPay = hasPay;
    }

    /*
     * public Student getStudent() { return student; }
     *
     * public void setStudent(Student student) { this.student = student; }
     */

    public Integer getPayOrderId() {
        return this.payOrderId;
    }

    public void setPayOrderId(Integer payOrderId) {
        this.payOrderId = payOrderId;
    }

    public Double getUsedFee() {
        return this.usedFee;
    }

    public void setUsedFee(Double usedFee) {
        this.usedFee = usedFee;
    }

    public Double getUnPay() {
        return this.unPay;
    }

    public void setUnPay(Double unPay) {
        this.unPay = unPay;
    }

    public Integer getCommodityId() {
        return this.commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getClassPackageStageId() {
        return this.classPackageStageId;
    }

    public void setClassPackageStageId(Integer classPackageStageId) {
        this.classPackageStageId = classPackageStageId;
    }

    public String getCommodityType() {
        return this.commodityType;
    }

    public void setCommodityType(String commodityType) {
        this.commodityType = commodityType;
    }

    @Override
    public String toString() {
        return "StudentPayMaster [" + "id=" + this.getId() + ", applyTime=" + this.applyTime + ", examTermId=" + this.examTermId + ", examTermName="
                + this.examTermName + ", itemOneId=" + this.itemOneId + ", itemOneName=" + this.itemOneName + ", itemSecondId=" + this.itemSecondId
                + ", itemSecondName=" + this.itemSecondName + ", classTypeName=" + this.classTypeName + ", classTypeHour=" + this.classTypeHour + ", stuId="
                + this.stuId + ", schoolId=" + this.schoolId + ", campusId=" + this.campusId + ", applyPlaceId=" + this.applyPlaceId + ", applyChannelCode="
                + this.applyChannelCode + ", payStatusCode=" + this.payStatusCode + ", bizStatusCode=" + this.bizStatusCode + ", isAgent=" + this.isAgent
                + ", isAgentMaterialComplete=" + this.isAgentMaterialComplete + ", isAgentComplete=" + this.isAgentComplete + ", agentRemark="
                + this.agentRemark + ", trainingFee=" + this.trainingFee + ", examAgentFee=" + this.examAgentFee + ", offerTypeCode=" + this.offerTypeCode
                + ", offerAmount=" + this.offerAmount + ", totalAmount=" + this.totalAmount + ", paymentTypeCode=" + this.paymentTypeCode + ", remark="
                + this.remark + ", relatedPayId=" + this.relatedPayId + ", createTime=" + this.createTime + ", creator=" + this.creator + ", updateTime="
                + this.updateTime + ", updator=" + this.updator + ", deleteFlag=" + this.deleteFlag + ",companyId=" + this.companyId + ",commodityId="
                + this.commodityId + "]";
    }

    public Double getIntegralInstead() {
        return this.integralInstead;
    }

    public void setIntegralInstead(Double integralInstead) {
        this.integralInstead = integralInstead;
    }

    public Double getMemberInstead() {
        return this.memberInstead;
    }

    public void setMemberInstead(Double memberInstead) {
        this.memberInstead = memberInstead;
    }

    public Integer getMemberId() {
        return this.memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getIntegralUsed() {
        return this.integralUsed;
    }

    public void setIntegralUsed(Integer integralUsed) {
        this.integralUsed = integralUsed;
    }

    public Integer getMemberDiscount() {
        return this.memberDiscount;
    }

    public void setMemberDiscount(Integer memberDiscount) {
        this.memberDiscount = memberDiscount;
    }

    public Integer getIntegralNum() {
        return this.integralNum;
    }

    public void setIntegralNum(Integer integralNum) {
        this.integralNum = integralNum;
    }

    public Integer getExchangeScale() {
        return this.exchangeScale;
    }

    public void setExchangeScale(Integer exchangeScale) {
        this.exchangeScale = exchangeScale;
    }

    public Integer getMemberLength() {
        return this.memberLength;
    }

    public void setMemberLength(Integer memberLength) {
        this.memberLength = memberLength;
    }

    public Double getCouponInstead() {
        return this.couponInstead;
    }

    public void setCouponInstead(Double couponInstead) {
        this.couponInstead = couponInstead;
    }

    public String getCouponCode() {
        return this.couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getItemSecondCode() {
        return itemSecondCode;
    }

    public void setItemSecondCode(String itemSecondCode) {
        this.itemSecondCode = itemSecondCode;
    }

    public String getItemThirdCode() {
        return itemThirdCode;
    }

    public void setItemThirdCode(String itemThirdCode) {
        this.itemThirdCode = itemThirdCode;
    }

	public Date getSomeDayBegin() {
		return someDayBegin;
	}

	public void setSomeDayBegin(Date someDayBegin) {
		this.someDayBegin = someDayBegin;
	}

	public Date getSomeDayEnd() {
		return someDayEnd;
	}

	public void setSomeDayEnd(Date someDayEnd) {
		this.someDayEnd = someDayEnd;
	}

	public Date getEarlyDay() {
		return earlyDay;
	}

	public void setEarlyDay(Date earlyDay) {
		this.earlyDay = earlyDay;
	}


	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}


}
