package com.yuxin.wx.vo.student;

import java.util.Date;







import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.util.ShortDateSerializer;

/**
 * POJO:StudentPayMaster
 * @author zhang.zx
 * @date 2015-6-4
 */
@SuppressWarnings("serial")
public class StuPayMasterVo extends BaseEntity {
	
	
	private Date	applyTime;		 /* 报名时间。开单时可以调整。 */ 
	private Integer	examTermId;		 /* 考期id */ 
	private String	examTermName;		 /* 考期名称 */ 
	private Integer	itemOneId;		 /* 一级项目id */ 
	private String	itemOneName;		 /* 一级项目名称 */ 
	private Integer	itemSecondId;		 /* 二级项目id */ 
	private String	itemSecondName;		 /* 二级项目名称 */ 
	private Integer	commodityId;		 /* 班型id */ 
	private String	classTypeName;		 /* 班型名称 */ 
	private Integer	classTypeHour;		 /* 班型总课时 */ 
	private Integer	stuId;		 /* 学生id */ 
	private String  stuName;
	private Integer	schoolId;		 /* 分校id */
	private String  schoolName;
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
	private String	offerTypeCode;		 /* 优惠类型：优惠卷
字典表，保留字段，暂时不用 */ 
	private Double	offerAmount;		 /* 优惠总额。有可能是多张优惠券的合计金额也有可能是内部员工学习、特批学员的优惠。
保留字段，暂时不用 */ 
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
	private Integer payOrderId;		/*相关联的订单*/
	
	private String startDate;
	private String endDate;
	
	private Double usedFee;
	private Integer timeLen;
	private String timeMark;
	
	private String username;
	
	private String proxyOrgName;
	private Integer proxyOrgId;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public StuPayMasterVo() {
	}
	
	/**
	 * full Constructor
	 */
	public StuPayMasterVo(Integer id, Date applyTime, Integer examTermId, String examTermName, Integer itemOneId, String itemOneName, Integer itemSecondId, String itemSecondName, Integer commodityId, String classTypeName, Integer classTypeHour, Integer stuId, Integer schoolId, Integer campusId, Integer applyPlaceId, String applyChannelCode, String payStatusCode, String bizStatusCode, String isAgent, Integer isAgentMaterialComplete, String isAgentComplete, String agentRemark, Double trainingFee, Double examAgentFee, String offerTypeCode, Double offerAmount, Double totalAmount, String paymentTypeCode, String remark, Integer relatedPayId, Date createTime, String creator, Date updateTime, String updator, Integer deleteFlag,String username) {
		setId(id);
		this.applyTime = applyTime;
		this.examTermId = examTermId;
		this.examTermName = examTermName;
		this.itemOneId = itemOneId;
		this.itemOneName = itemOneName;
		this.itemSecondId = itemSecondId;
		this.itemSecondName = itemSecondName;
		this.commodityId = commodityId;
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
		this.username = username;
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

	public Integer getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
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

	@JsonSerialize(using = ShortDateSerializer.class)
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

	public Integer getPayOrderId() {
		return payOrderId;
	}

	public void setPayOrderId(Integer payOrderId) {
		this.payOrderId = payOrderId;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
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

	public Double getUsedFee() {
		return usedFee;
	}

	public void setUsedFee(Double usedFee) {
		this.usedFee = usedFee;
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

	public String getProxyOrgName() {
		return proxyOrgName;
	}

	public void setProxyOrgName(String proxyOrgName) {
		this.proxyOrgName = proxyOrgName;
	}

	public Integer getProxyOrgId() {
		return proxyOrgId;
	}

	public void setProxyOrgId(Integer proxyOrgId) {
		this.proxyOrgId = proxyOrgId;
	}

}
