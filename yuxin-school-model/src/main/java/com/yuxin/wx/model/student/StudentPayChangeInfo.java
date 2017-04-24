package com.yuxin.wx.model.student;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:StudentPayChangeInfo
 * @author wang.zx
 * @date 2014-12-5
 */
@SuppressWarnings("serial")
public class StudentPayChangeInfo extends BaseEntity {
	
	
	private Integer	stuId;		 /* 学生id */ 
	private Integer	payMasterId;		 /* 学员报名主订单id */ 
	private String	changeType;		 /* 改变类型（解约；转人），字典表 */ 
	private String	reasonType;		 /* 原因类型（个人原因；机构原因），字典表 */ 
	private String	reasonCode;		 /* 具体原因，字典表 */ 
	private String	dutySectionCode;		 /* 责任部门，字典表 */ 
	private String	remark;		 /* 备注 */ 
	private Double	refundNum;		 /* 退费金额 */ 
	private Integer companyId;

	// Constructor
	public StudentPayChangeInfo() {
	}
	
	/**
	 * full Constructor
	 */
	public StudentPayChangeInfo(Integer id, Integer stuId, Integer payMasterId, String changeType, String reasonType, String reasonCode, String dutySectionCode, String remark, Double refundNum) {
		setId(id);
		this.stuId = stuId;
		this.payMasterId = payMasterId;
		this.changeType = changeType;
		this.reasonType = reasonType;
		this.reasonCode = reasonCode;
		this.dutySectionCode = dutySectionCode;
		this.remark = remark;
		this.refundNum = refundNum;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为StudentPayChangeInfo可以实现连缀设置属性
	
	public Integer getStuId() {
		return stuId;
	}

	public StudentPayChangeInfo setStuId(Integer stuId) {
		this.stuId = stuId;
		return this;
	}
	
	
	public Integer getPayMasterId() {
		return payMasterId;
	}

	public StudentPayChangeInfo setPayMasterId(Integer payMasterId) {
		this.payMasterId = payMasterId;
		return this;
	}
	
	
	public String getChangeType() {
		return changeType;
	}

	public StudentPayChangeInfo setChangeType(String changeType) {
		this.changeType = changeType;
		return this;
	}
	
	
	public String getReasonType() {
		return reasonType;
	}

	public StudentPayChangeInfo setReasonType(String reasonType) {
		this.reasonType = reasonType;
		return this;
	}
	
	
	public String getReasonCode() {
		return reasonCode;
	}

	public StudentPayChangeInfo setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
		return this;
	}
	
	
	public String getDutySectionCode() {
		return dutySectionCode;
	}

	public StudentPayChangeInfo setDutySectionCode(String dutySectionCode) {
		this.dutySectionCode = dutySectionCode;
		return this;
	}
	
	
	public String getRemark() {
		return remark;
	}

	public StudentPayChangeInfo setRemark(String remark) {
		this.remark = remark;
		return this;
	}
	
	
	public Double getRefundNum() {
		return refundNum;
	}

	public StudentPayChangeInfo setRefundNum(Double refundNum) {
		this.refundNum = refundNum;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	@Override
	public String toString() {
		return "StudentPayChangeInfo [" + "id=" + getId() + ", stuId=" + stuId + ", payMasterId=" + payMasterId + ", changeType=" + changeType + ", reasonType=" + reasonType + ", reasonCode=" + reasonCode + ", dutySectionCode=" + dutySectionCode + ", remark=" + remark + ", refundNum=" + refundNum +  "]";
	}
}
