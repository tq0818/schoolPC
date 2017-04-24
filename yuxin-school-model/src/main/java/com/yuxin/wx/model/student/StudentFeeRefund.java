package com.yuxin.wx.model.student;


import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:StudentFeeRefund
 * 
 * @author chopin
 * @date 2015-5-7
 */
@SuppressWarnings("serial")
public class StudentFeeRefund extends BaseEntity {
	
	
	private Integer	stuId;		 /* 学生id */ 
	private Integer	payMasterId;		 /* 主订单id */ 
	private Date	refundDate;		 /* 退费日期 */ 
	private Double	refundFee;		 /* 分期费用 */ 
	private Double	posRefund;		 /* pos退费 */ 
	private Double	cashRefund;		 /* 现金退费 */ 
	private Double	checkRefund;		 /* 支票退费 */ 
	private Double	remitRefund;		 /* 汇款退费 */ 
	private String	remark;		
	private String	createType;		 /* 退费生成方式：解约、转班 */ 
	private Date	createTime;		 /* 生成日期 */ 
	private Integer	creator;		

	// Constructor
	public StudentFeeRefund() {
	}
	
	/**
	 * full Constructor
	 */
	public StudentFeeRefund(Integer id, Integer stuId, Integer payMasterId, Date refundDate, Double refundFee, Double posRefund, Double cashRefund, Double checkRefund, Double remitRefund, String remark, String createType, Date createTime, Integer creator) {
		setId(id);
		this.stuId = stuId;
		this.payMasterId = payMasterId;
		this.refundDate = refundDate;
		this.refundFee = refundFee;
		this.posRefund = posRefund;
		this.cashRefund = cashRefund;
		this.checkRefund = checkRefund;
		this.remitRefund = remitRefund;
		this.remark = remark;
		this.createType = createType;
		this.createTime = createTime;
		this.creator = creator;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为StudentFeeRefund可以实现连缀设置属性
	
	public Integer getStuId() {
		return stuId;
	}

	public StudentFeeRefund setStuId(Integer stuId) {
		this.stuId = stuId;
		return this;
	}
	
	
	public Integer getPayMasterId() {
		return payMasterId;
	}

	public StudentFeeRefund setPayMasterId(Integer payMasterId) {
		this.payMasterId = payMasterId;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getRefundDate() {
		return refundDate;
	}

	public StudentFeeRefund setRefundDate(Date refundDate) {
		this.refundDate = refundDate;
		return this;
	}
	
	
	public Double getRefundFee() {
		return refundFee;
	}

	public StudentFeeRefund setRefundFee(Double refundFee) {
		this.refundFee = refundFee;
		return this;
	}
	
	
	public Double getPosRefund() {
		return posRefund;
	}

	public StudentFeeRefund setPosRefund(Double posRefund) {
		this.posRefund = posRefund;
		return this;
	}
	
	
	public Double getCashRefund() {
		return cashRefund;
	}

	public StudentFeeRefund setCashRefund(Double cashRefund) {
		this.cashRefund = cashRefund;
		return this;
	}
	
	
	public Double getCheckRefund() {
		return checkRefund;
	}

	public StudentFeeRefund setCheckRefund(Double checkRefund) {
		this.checkRefund = checkRefund;
		return this;
	}
	
	
	public Double getRemitRefund() {
		return remitRefund;
	}

	public StudentFeeRefund setRemitRefund(Double remitRefund) {
		this.remitRefund = remitRefund;
		return this;
	}
	
	
	public String getRemark() {
		return remark;
	}

	public StudentFeeRefund setRemark(String remark) {
		this.remark = remark;
		return this;
	}
	
	
	public String getCreateType() {
		return createType;
	}

	public StudentFeeRefund setCreateType(String createType) {
		this.createType = createType;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public StudentFeeRefund setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	
	public Integer getCreator() {
		return creator;
	}

	public StudentFeeRefund setCreator(Integer creator) {
		this.creator = creator;
		return this;
	}
	
	@Override
	public String toString() {
		return "StudentFeeRefund [" + "id=" + getId() + ", stuId=" + stuId + ", payMasterId=" + payMasterId + ", refundDate=" + refundDate + ", refundFee=" + refundFee + ", posRefund=" + posRefund + ", cashRefund=" + cashRefund + ", checkRefund=" + checkRefund + ", remitRefund=" + remitRefund + ", remark=" + remark + ", createType=" + createType + ", createTime=" + createTime + ", creator=" + creator +  "]";
	}
}
