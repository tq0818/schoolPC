package com.yuxin.wx.vo.student;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.util.ShortDateSerializer;

public class StudentPayMaster4ClassPackageVo {
	
	private Integer id;
	private Date	applyTime;		 /* 报名时间。开单时可以调整。 */ 
	private Integer	examTermId;		 /* 考期id */ 
	private String	examTermName;		 /* 考期名称 */ 
	private Integer	itemOneId;		 /* 一级项目id */ 
	private String	itemOneName;		 /* 一级项目名称 */ 
	private Integer	itemSecondId;		 /* 二级项目id */ 
	private String	itemSecondName;		 /* 二级项目名称 */ 
	private String	classTypeName;		 /* 班型名称 */ 
	private Integer	classTypeHour;		 /* 班型总课时 */ 
	private Integer	stuId;		 /* 学生id */ 
	private Integer	schoolId;		 /* 分校id */ 
	private Integer	campusId;		 /* 校区id */ 
	private Integer	applyPlaceId;		 /* 报名点代码 */ 
	private String	applyChannelCode;		 /* 报名渠道代码：在线报名、分校报名
字典表 */ 
	private String	payStatusCode;		 /* 订单状态｛未支付、部分支付，已支付，已转人，已转班型，已延期，已冻结，已完成，已作废｝
如果是分期缴费的，所有分期付完后更新该字段的状态为已支付，否则一直是部分支付；
字典表 */ 
	private String	bizStatusCode;		 /* 订单业务类型(新报名、重修、转人、转班型。)，字典表 */ 
	private String	isAgent;		 /* 是否代报考（1：是；0：否） */ 
	private Integer	isAgentMaterialComplete;		 /* 代报考资料是否齐全（1：是；0：否） */ 
	private String	isAgentComplete;		 /* 是否完成代报考（1：是；0：否） */ 
	private String	agentRemark;		 /* 代报考备注 */ 
	private Double	trainingFee;		 /* 培训费用=班型定价 */ 
	private Double	examAgentFee;		 /* 代报费用 */ 
	private String	offerTypeCode;		 /* 优惠类型：优惠卷字典表，保留字段，暂时不用 */ 
	private Double	offerAmount;		 /* 优惠总额。有可能是多张优惠券的合计金额也有可能是内部员工学习、特批学员的优惠。保留字段，暂时不用 */ 
	private Double	totalAmount;		 /* 订单合计金额。订单合计=培训费用+代报费-优惠金额。 */ 
	private String	paymentTypeCode;		 /* 付款方式（全款、分期、贷款），字典表 */ 
	private String	remark;		 /* 订单备注 */ 
	private Integer	relatedPayId;		 /* 相关联的订单id，转人转班型可能会用上。 */ 
	private Date	createTime;		 /* 订单创建时间 */ 
	private String	creator;		 /* 订单创建人（课程顾问） */ 
	private Date	updateTime;		 /* 订单最后更新时间 */ 
	private String	updator;		 /* 操作员（当前操作员） */ 
	private Integer	deleteFlag;		 /* 删除标记：
1：已删除；0：未删除 */ 
	private Integer companyId;
	
	private Double hasPay;		/*已交费用*/
	private Double unPay;
	private Integer payOrderId;		/*相关联的订单*/
	
	private Double usedFee;
	
	//当前主订单班型对应的商品ID
	private Integer commodityId;
	
	private String message;
	
	private Integer classPackageStageId; /* 课程阶段id*/
	private String commodityType; /* 商品类型*/
	
	private Double integralInstead;/*积分代金*/
	private Double memberInstead;/*会员代金*/
	private Integer memberId;/*会员id*/
	private Integer integralUsed;/*使用的积分数*/
	private Integer memberDiscount;/*会员折扣*/
	private Integer integralNum;/*积分数量*/
	
	private Integer exchangeScale;/*积分兑换比例*/
	private Integer memberLength;/*会员购买时长*/
	private Double couponInstead;
	private String 	couponCode;
	
	private String categoryCode;
	private String classPackageStageTitle;
	
	private String oneCategory;
	private String twoCategory;
	private String threeCategory;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	public Integer getExamTermId() {
		return examTermId;
	}
	public void setExamTermId(Integer examTermId) {
		this.examTermId = examTermId;
	}
	public String getExamTermName() {
		return examTermName;
	}
	public void setExamTermName(String examTermName) {
		this.examTermName = examTermName;
	}
	public Integer getItemOneId() {
		return itemOneId;
	}
	public void setItemOneId(Integer itemOneId) {
		this.itemOneId = itemOneId;
	}
	public String getItemOneName() {
		return itemOneName;
	}
	public void setItemOneName(String itemOneName) {
		this.itemOneName = itemOneName;
	}
	public Integer getItemSecondId() {
		return itemSecondId;
	}
	public void setItemSecondId(Integer itemSecondId) {
		this.itemSecondId = itemSecondId;
	}
	public String getItemSecondName() {
		return itemSecondName;
	}
	public void setItemSecondName(String itemSecondName) {
		this.itemSecondName = itemSecondName;
	}
	public String getClassTypeName() {
		return classTypeName;
	}
	public void setClassTypeName(String classTypeName) {
		this.classTypeName = classTypeName;
	}
	public Integer getClassTypeHour() {
		return classTypeHour;
	}
	public void setClassTypeHour(Integer classTypeHour) {
		this.classTypeHour = classTypeHour;
	}
	public Integer getStuId() {
		return stuId;
	}
	public void setStuId(Integer stuId) {
		this.stuId = stuId;
	}
	public Integer getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	public Integer getCampusId() {
		return campusId;
	}
	public void setCampusId(Integer campusId) {
		this.campusId = campusId;
	}
	public Integer getApplyPlaceId() {
		return applyPlaceId;
	}
	public void setApplyPlaceId(Integer applyPlaceId) {
		this.applyPlaceId = applyPlaceId;
	}
	public String getApplyChannelCode() {
		return applyChannelCode;
	}
	public void setApplyChannelCode(String applyChannelCode) {
		this.applyChannelCode = applyChannelCode;
	}
	public String getPayStatusCode() {
		return payStatusCode;
	}
	public void setPayStatusCode(String payStatusCode) {
		this.payStatusCode = payStatusCode;
	}
	public String getBizStatusCode() {
		return bizStatusCode;
	}
	public void setBizStatusCode(String bizStatusCode) {
		this.bizStatusCode = bizStatusCode;
	}
	public String getIsAgent() {
		return isAgent;
	}
	public void setIsAgent(String isAgent) {
		this.isAgent = isAgent;
	}
	public Integer getIsAgentMaterialComplete() {
		return isAgentMaterialComplete;
	}
	public void setIsAgentMaterialComplete(Integer isAgentMaterialComplete) {
		this.isAgentMaterialComplete = isAgentMaterialComplete;
	}
	public String getIsAgentComplete() {
		return isAgentComplete;
	}
	public void setIsAgentComplete(String isAgentComplete) {
		this.isAgentComplete = isAgentComplete;
	}
	public String getAgentRemark() {
		return agentRemark;
	}
	public void setAgentRemark(String agentRemark) {
		this.agentRemark = agentRemark;
	}
	public Double getTrainingFee() {
		return trainingFee;
	}
	public void setTrainingFee(Double trainingFee) {
		this.trainingFee = trainingFee;
	}
	public Double getExamAgentFee() {
		return examAgentFee;
	}
	public void setExamAgentFee(Double examAgentFee) {
		this.examAgentFee = examAgentFee;
	}
	public String getOfferTypeCode() {
		return offerTypeCode;
	}
	public void setOfferTypeCode(String offerTypeCode) {
		this.offerTypeCode = offerTypeCode;
	}
	public Double getOfferAmount() {
		return offerAmount;
	}
	public void setOfferAmount(Double offerAmount) {
		this.offerAmount = offerAmount;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getPaymentTypeCode() {
		return paymentTypeCode;
	}
	public void setPaymentTypeCode(String paymentTypeCode) {
		this.paymentTypeCode = paymentTypeCode;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getRelatedPayId() {
		return relatedPayId;
	}
	public void setRelatedPayId(Integer relatedPayId) {
		this.relatedPayId = relatedPayId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getUpdator() {
		return updator;
	}
	public void setUpdator(String updator) {
		this.updator = updator;
	}
	public Integer getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public Double getHasPay() {
		return hasPay;
	}
	public void setHasPay(Double hasPay) {
		this.hasPay = hasPay;
	}
	public Double getUnPay() {
		return unPay;
	}
	public void setUnPay(Double unPay) {
		this.unPay = unPay;
	}
	public Integer getPayOrderId() {
		return payOrderId;
	}
	public void setPayOrderId(Integer payOrderId) {
		this.payOrderId = payOrderId;
	}
	public Double getUsedFee() {
		return usedFee;
	}
	public void setUsedFee(Double usedFee) {
		this.usedFee = usedFee;
	}
	public Integer getCommodityId() {
		return commodityId;
	}
	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getClassPackageStageId() {
		return classPackageStageId;
	}
	public void setClassPackageStageId(Integer classPackageStageId) {
		this.classPackageStageId = classPackageStageId;
	}
	public String getCommodityType() {
		return commodityType;
	}
	public void setCommodityType(String commodityType) {
		this.commodityType = commodityType;
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
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getClassPackageStageTitle() {
		return classPackageStageTitle;
	}
	public void setClassPackageStageTitle(String classPackageStageTitle) {
		this.classPackageStageTitle = classPackageStageTitle;
	}
	@Override
	public String toString() {
		return "StudentPayMaster4ClassPackageVo [id=" + id + ", applyTime=" + applyTime + ", examTermId=" + examTermId
				+ ", examTermName=" + examTermName + ", itemOneId=" + itemOneId + ", itemOneName=" + itemOneName
				+ ", itemSecondId=" + itemSecondId + ", itemSecondName=" + itemSecondName + ", classTypeName="
				+ classTypeName + ", classTypeHour=" + classTypeHour + ", stuId=" + stuId + ", schoolId=" + schoolId
				+ ", campusId=" + campusId + ", applyPlaceId=" + applyPlaceId + ", applyChannelCode=" + applyChannelCode
				+ ", payStatusCode=" + payStatusCode + ", bizStatusCode=" + bizStatusCode + ", isAgent=" + isAgent
				+ ", isAgentMaterialComplete=" + isAgentMaterialComplete + ", isAgentComplete=" + isAgentComplete
				+ ", agentRemark=" + agentRemark + ", trainingFee=" + trainingFee + ", examAgentFee=" + examAgentFee
				+ ", offerTypeCode=" + offerTypeCode + ", offerAmount=" + offerAmount + ", totalAmount=" + totalAmount
				+ ", paymentTypeCode=" + paymentTypeCode + ", remark=" + remark + ", relatedPayId=" + relatedPayId
				+ ", createTime=" + createTime + ", creator=" + creator + ", updateTime=" + updateTime + ", updator="
				+ updator + ", deleteFlag=" + deleteFlag + ", companyId=" + companyId + ", hasPay=" + hasPay
				+ ", unPay=" + unPay + ", payOrderId=" + payOrderId + ", usedFee=" + usedFee + ", commodityId="
				+ commodityId + ", message=" + message + ", classPackageStageId=" + classPackageStageId
				+ ", commodityType=" + commodityType + ", integralInstead=" + integralInstead + ", memberInstead="
				+ memberInstead + ", memberId=" + memberId + ", integralUsed=" + integralUsed + ", memberDiscount="
				+ memberDiscount + ", integralNum=" + integralNum + ", exchangeScale=" + exchangeScale
				+ ", memberLength=" + memberLength + ", couponInstead=" + couponInstead + ", couponCode=" + couponCode
				+ ", categoryCode=" + categoryCode + ", classPackageStageTitle=" + classPackageStageTitle + "]";
	}
	public String getOneCategory() {
		return oneCategory;
	}
	public void setOneCategory(String oneCategory) {
		this.oneCategory = oneCategory;
	}
	public String getTwoCategory() {
		return twoCategory;
	}
	public void setTwoCategory(String twoCategory) {
		this.twoCategory = twoCategory;
	}
	public String getThreeCategory() {
		return threeCategory;
	}
	public void setThreeCategory(String threeCategory) {
		this.threeCategory = threeCategory;
	}
	
	
}
