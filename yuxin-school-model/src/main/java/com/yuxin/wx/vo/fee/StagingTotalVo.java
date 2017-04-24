package com.yuxin.wx.vo.fee;

import com.yuxin.wx.common.BaseEntity;


@SuppressWarnings("serial")
public class StagingTotalVo extends BaseEntity{

	private Double thFee; /* 分期费用应缴*/
	private Double posFee; /* pos支付总额*/
	private Double checkFee; /* 支票总额*/
	private Double remitFee; /* 转账总额*/
	private Double cashFee; /* 现金支付总额*/
	private Double totalMoney; /* 合计金额*/
	public Double getThFee() {
		return thFee;
	}
	public void setThFee(Double thFee) {
		this.thFee = thFee;
	}
	public Double getPosFee() {
		return posFee;
	}
	public void setPosFee(Double posFee) {
		this.posFee = posFee;
	}
	public Double getCheckFee() {
		return checkFee;
	}
	public void setCheckFee(Double checkFee) {
		this.checkFee = checkFee;
	}
	public Double getRemitFee() {
		return remitFee;
	}
	public void setRemitFee(Double remitFee) {
		this.remitFee = remitFee;
	}
	public Double getCashFee() {
		return cashFee;
	}
	public void setCashFee(Double cashFee) {
		this.cashFee = cashFee;
	}
	public Double getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}
	
}
