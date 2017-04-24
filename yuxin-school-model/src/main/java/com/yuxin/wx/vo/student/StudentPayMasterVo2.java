package com.yuxin.wx.vo.student;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.course.CourseVideo;
import com.yuxin.wx.model.student.Student;
import com.yuxin.wx.model.student.StudentAgentMaterial;
import com.yuxin.wx.model.student.StudentFeeStage;
import com.yuxin.wx.util.ShortDateSerializer;

/**
 * POJO:StudentPayMaster
 * @author wang.zx
 * @date 2014-12-5
 */
@SuppressWarnings("serial")
public class StudentPayMasterVo2 extends BaseEntity {
	
	
	private Date	applyTime;		 /* 报名时间。开单时可以调整。 */ 
	private Integer	examTermId;		 /* 考期id */ 
	private String	examTermName;		 /* 考期名称 */ 
	private Integer	itemOneId;		 /* 一级项目id */ 
	private String	itemOneName;		 /* 一级项目名称 */ 
	private Integer	itemSecondId;		 /* 二级项目id */ 
	private String	itemSecondName;		 /* 二级项目名称 */ 
	private Integer	classTypeId;		 /* 班型id */ 
	private String	classTypeName;		 /* 班型名称 */ 
	private Integer	classTypeHour;		 /* 班型总课时 */ 
	private Integer	stuId;		 /* 学生id */ 
	private String  schoolsId;		 /* 分校id */ 
	private Integer	campusId;		 /* 校区id */ 
	private Integer	applyPlaceId;		 /* 报名点代码 */ 
	private String	applyChannelCode;		 /* 报名渠道代码：在线报名、分校报名字典表 */ 
	private String	payStatusCode;		 /* 订单状态｛未支付、部分支付，已支付，已转人，已转班型，已延期，已冻结，已完成，已作废｝如果是分期缴费的，所有分期付完后更新该字段的状态为已支付，否则一直是部分支付；字典表 */ 
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
	private Integer	deleteFlag;		 /* 删除标记：1：已删除；0：未删除 */ 
	private Student student;

	public StudentPayMasterVo2() {
	}
	
	/**
	 * full Constructor
	 */
	public StudentPayMasterVo2(Integer id, Date applyTime, Integer examTermId, String examTermName, Integer itemOneId, String itemOneName, Integer itemSecondId, String itemSecondName, Integer classTypeId, String classTypeName, Integer classTypeHour, Integer stuId, Integer schoolId, Integer campusId, Integer applyPlaceId, String applyChannelCode, String payStatusCode, String bizStatusCode, String isAgent, Integer isAgentMaterialComplete, String isAgentComplete, String agentRemark, Double trainingFee, Double examAgentFee, String offerTypeCode, Double offerAmount, Double totalAmount, String paymentTypeCode, String remark, Integer relatedPayId, Date createTime, String creator, Date updateTime, String updator, Integer deleteFlag) {
		setId(id);
		this.applyTime = applyTime;
		this.examTermId = examTermId;
		this.examTermName = examTermName;
		this.itemOneId = itemOneId;
		this.itemOneName = itemOneName;
		this.itemSecondId = itemSecondId;
		this.itemSecondName = itemSecondName;
		this.classTypeId = classTypeId;
		this.classTypeName = classTypeName;
		this.classTypeHour = classTypeHour;
		this.stuId = stuId;
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
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为StudentPayMaster可以实现连缀设置属性
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getApplyTime() {
		return applyTime;
	}

	public StudentPayMasterVo2 setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
		return this;
	}
	
	
	public Integer getExamTermId() {
		return examTermId;
	}

	public StudentPayMasterVo2 setExamTermId(Integer examTermId) {
		this.examTermId = examTermId;
		return this;
	}
	
	
	public String getExamTermName() {
		return examTermName;
	}

	public StudentPayMasterVo2 setExamTermName(String examTermName) {
		this.examTermName = examTermName;
		return this;
	}
	
	
	public Integer getItemOneId() {
		return itemOneId;
	}

	public StudentPayMasterVo2 setItemOneId(Integer itemOneId) {
		this.itemOneId = itemOneId;
		return this;
	}
	
	
	public String getItemOneName() {
		return itemOneName;
	}

	public StudentPayMasterVo2 setItemOneName(String itemOneName) {
		this.itemOneName = itemOneName;
		return this;
	}
	
	
	public Integer getItemSecondId() {
		return itemSecondId;
	}

	public StudentPayMasterVo2 setItemSecondId(Integer itemSecondId) {
		this.itemSecondId = itemSecondId;
		return this;
	}
	
	
	public String getItemSecondName() {
		return itemSecondName;
	}

	public StudentPayMasterVo2 setItemSecondName(String itemSecondName) {
		this.itemSecondName = itemSecondName;
		return this;
	}
	
	
	public Integer getClassTypeId() {
		return classTypeId;
	}

	public StudentPayMasterVo2 setClassTypeId(Integer classTypeId) {
		this.classTypeId = classTypeId;
		return this;
	}
	
	
	public String getClassTypeName() {
		return classTypeName;
	}

	public StudentPayMasterVo2 setClassTypeName(String classTypeName) {
		this.classTypeName = classTypeName;
		return this;
	}
	
	
	public Integer getClassTypeHour() {
		return classTypeHour;
	}

	public StudentPayMasterVo2 setClassTypeHour(Integer classTypeHour) {
		this.classTypeHour = classTypeHour;
		return this;
	}
	
	
	public Integer getStuId() {
		return stuId;
	}

	public StudentPayMasterVo2 setStuId(Integer stuId) {
		this.stuId = stuId;
		return this;
	}
	
	
	
	public String getSchoolsId() {
		return schoolsId;
	}

	public void setSchoolsId(String schoolsId) {
		this.schoolsId = schoolsId;
	}

	public Integer getCampusId() {
		return campusId;
	}

	public StudentPayMasterVo2 setCampusId(Integer campusId) {
		this.campusId = campusId;
		return this;
	}
	
	
	public Integer getApplyPlaceId() {
		return applyPlaceId;
	}

	public StudentPayMasterVo2 setApplyPlaceId(Integer applyPlaceId) {
		this.applyPlaceId = applyPlaceId;
		return this;
	}
	
	
	public String getApplyChannelCode() {
		return applyChannelCode;
	}

	public StudentPayMasterVo2 setApplyChannelCode(String applyChannelCode) {
		this.applyChannelCode = applyChannelCode;
		return this;
	}
	
	
	public String getPayStatusCode() {
		return payStatusCode;
	}

	public StudentPayMasterVo2 setPayStatusCode(String payStatusCode) {
		this.payStatusCode = payStatusCode;
		return this;
	}
	
	
	public String getBizStatusCode() {
		return bizStatusCode;
	}

	public StudentPayMasterVo2 setBizStatusCode(String bizStatusCode) {
		this.bizStatusCode = bizStatusCode;
		return this;
	}
	
	
	public String getIsAgent() {
		return isAgent;
	}

	public StudentPayMasterVo2 setIsAgent(String isAgent) {
		this.isAgent = isAgent;
		return this;
	}
	
	
	public Integer getIsAgentMaterialComplete() {
		return isAgentMaterialComplete;
	}

	public StudentPayMasterVo2 setIsAgentMaterialComplete(Integer isAgentMaterialComplete) {
		this.isAgentMaterialComplete = isAgentMaterialComplete;
		return this;
	}
	
	
	public String getIsAgentComplete() {
		return isAgentComplete;
	}

	public StudentPayMasterVo2 setIsAgentComplete(String isAgentComplete) {
		this.isAgentComplete = isAgentComplete;
		return this;
	}
	
	
	public String getAgentRemark() {
		return agentRemark;
	}

	public StudentPayMasterVo2 setAgentRemark(String agentRemark) {
		this.agentRemark = agentRemark;
		return this;
	}
	
	
	public Double getTrainingFee() {
		return trainingFee;
	}

	public StudentPayMasterVo2 setTrainingFee(Double trainingFee) {
		this.trainingFee = trainingFee;
		return this;
	}
	
	
	public Double getExamAgentFee() {
		return examAgentFee;
	}

	public StudentPayMasterVo2 setExamAgentFee(Double examAgentFee) {
		this.examAgentFee = examAgentFee;
		return this;
	}
	
	
	public String getOfferTypeCode() {
		return offerTypeCode;
	}

	public StudentPayMasterVo2 setOfferTypeCode(String offerTypeCode) {
		this.offerTypeCode = offerTypeCode;
		return this;
	}
	
	
	public Double getOfferAmount() {
		return offerAmount;
	}

	public StudentPayMasterVo2 setOfferAmount(Double offerAmount) {
		this.offerAmount = offerAmount;
		return this;
	}
	
	
	public Double getTotalAmount() {
		return totalAmount;
	}

	public StudentPayMasterVo2 setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
		return this;
	}
	
	
	public String getPaymentTypeCode() {
		return paymentTypeCode;
	}

	public StudentPayMasterVo2 setPaymentTypeCode(String paymentTypeCode) {
		this.paymentTypeCode = paymentTypeCode;
		return this;
	}
	
	
	public String getRemark() {
		return remark;
	}

	public StudentPayMasterVo2 setRemark(String remark) {
		this.remark = remark;
		return this;
	}
	
	
	public Integer getRelatedPayId() {
		return relatedPayId;
	}

	public StudentPayMasterVo2 setRelatedPayId(Integer relatedPayId) {
		this.relatedPayId = relatedPayId;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public StudentPayMasterVo2 setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	
	public String getCreator() {
		return creator;
	}

	public StudentPayMasterVo2 setCreator(String creator) {
		this.creator = creator;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getUpdateTime() {
		return updateTime;
	}

	public StudentPayMasterVo2 setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	
	
	public String getUpdator() {
		return updator;
	}

	public StudentPayMasterVo2 setUpdator(String updator) {
		this.updator = updator;
		return this;
	}
	
	
	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public StudentPayMasterVo2 setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
		return this;
	}
	
	

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "StudentPayMaster [" + "id=" + getId() + ", applyTime=" + applyTime + ", examTermId=" + examTermId + ", examTermName=" + examTermName + ", itemOneId=" + itemOneId + ", itemOneName=" + itemOneName + ", itemSecondId=" + itemSecondId + ", itemSecondName=" + itemSecondName + ", classTypeId=" + classTypeId + ", classTypeName=" + classTypeName + ", classTypeHour=" + classTypeHour + ", stuId=" + stuId + ", campusId=" + campusId + ", applyPlaceId=" + applyPlaceId + ", applyChannelCode=" + applyChannelCode + ", payStatusCode=" + payStatusCode + ", bizStatusCode=" + bizStatusCode + ", isAgent=" + isAgent + ", isAgentMaterialComplete=" + isAgentMaterialComplete + ", isAgentComplete=" + isAgentComplete + ", agentRemark=" + agentRemark + ", trainingFee=" + trainingFee + ", examAgentFee=" + examAgentFee + ", offerTypeCode=" + offerTypeCode + ", offerAmount=" + offerAmount + ", totalAmount=" + totalAmount + ", paymentTypeCode=" + paymentTypeCode + ", remark=" + remark + ", relatedPayId=" + relatedPayId + ", createTime=" + createTime + ", creator=" + creator + ", updateTime=" + updateTime + ", updator=" + updator + ", deleteFlag=" + deleteFlag +  "]";
	}
}
