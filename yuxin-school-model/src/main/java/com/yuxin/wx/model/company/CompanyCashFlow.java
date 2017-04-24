package com.yuxin.wx.model.company;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyCashFlow
 * 
 * @author chopin
 * @date 2016-5-17
 */
@SuppressWarnings("serial")
public class CompanyCashFlow extends BaseEntity {
	
	
	private String	tradeReason;		 /* 流转原因  购买课程、购买课程包、购买会员、购买积分、转班补差价、退费、移出课程 */ 
	private String	tradeSource;		 /* 资金来源  在线付款、线下交易 */ 
	private String	tradeWay;		 /* 交易渠道  支付宝转账、微信转账、微信支付、个人担保交易、银联在线、POS、支票、现金、汇款 */ 
	private Double	tradeAmount;		 /* 交易金额，可以为负数 */ 
	private Date	tradeDate;		 /* 交易日期 */ 
	private Integer	userId;		 /* 用户ID */ 
	private String	tradeResult;		 /* 交易结果：success-成功  failed-失败 */ 
	private Integer	companyId;		 /* 公司ID */ 
	private Integer	schoolId;		 /* 分校ID */ 
	private String	tradeType;		 /* 交易类型： 转入、转出 */ 
	private Double	lastAmount;		 /* 交易完成后当前金额 */ 
	private Integer	stuId;		 /* 学员ID */ 
	private Integer	updator;		 /* 操作人 */ 
	private Date	updateTime;		 /* 操作时间 */ 
	private Integer  payMasterId;   /*订单编号*/
	// Constructor
	public CompanyCashFlow() {
	}
	
	/**
	 * full Constructor
	 */
	public CompanyCashFlow(Integer id, String tradeReason, String tradeSource, String tradeWay, Double tradeAmount, Date tradeDate, Integer userId, String tradeResult, Integer companyId, Integer schoolId, String tradeType, Double lastAmount, Integer stuId, Integer updator, Date updateTime) {
		setId(id);
		this.tradeReason = tradeReason;
		this.tradeSource = tradeSource;
		this.tradeWay = tradeWay;
		this.tradeAmount = tradeAmount;
		this.tradeDate = tradeDate;
		this.userId = userId;
		this.tradeResult = tradeResult;
		this.companyId = companyId;
		this.schoolId = schoolId;
		this.tradeType = tradeType;
		this.lastAmount = lastAmount;
		this.stuId = stuId;
		this.updator = updator;
		this.updateTime = updateTime;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CompanyCashFlow可以实现连缀设置属性
	
	public String getTradeReason() {
		return tradeReason;
	}


	public CompanyCashFlow setTradeReason(String tradeReason) {
		this.tradeReason = tradeReason;
		return this;
	}
	
	
	public String getTradeSource() {
		return tradeSource;
	}

	public CompanyCashFlow setTradeSource(String tradeSource) {
		this.tradeSource = tradeSource;
		return this;
	}
	
	
	public String getTradeWay() {
		return tradeWay;
	}

	public CompanyCashFlow setTradeWay(String tradeWay) {
		this.tradeWay = tradeWay;
		return this;
	}
	
	
	public Double getTradeAmount() {
		return tradeAmount;
	}

	public CompanyCashFlow setTradeAmount(Double tradeAmount) {
		this.tradeAmount = tradeAmount;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getTradeDate() {
		return tradeDate;
	}

	public CompanyCashFlow setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
		return this;
	}
	
	
	public Integer getUserId() {
		return userId;
	}

	public CompanyCashFlow setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}
	
	
	public String getTradeResult() {
		return tradeResult;
	}

	public CompanyCashFlow setTradeResult(String tradeResult) {
		this.tradeResult = tradeResult;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public CompanyCashFlow setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public Integer getSchoolId() {
		return schoolId;
	}

	public CompanyCashFlow setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
		return this;
	}
	
	
	public String getTradeType() {
		return tradeType;
	}

	public CompanyCashFlow setTradeType(String tradeType) {
		this.tradeType = tradeType;
		return this;
	}
	
	
	public Double getLastAmount() {
		return lastAmount;
	}

	public CompanyCashFlow setLastAmount(Double lastAmount) {
		this.lastAmount = lastAmount;
		return this;
	}
	
	
	public Integer getStuId() {
		return stuId;
	}

	public CompanyCashFlow setStuId(Integer stuId) {
		this.stuId = stuId;
		return this;
	}
	
	
	public Integer getUpdator() {
		return updator;
	}

	public CompanyCashFlow setUpdator(Integer updator) {
		this.updator = updator;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getUpdateTime() {
		return updateTime;
	}

	public CompanyCashFlow setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	
	@Override
	public String toString() {
		return "CompanyCashFlow [" + "id=" + getId() + ", tradeReason=" + tradeReason + ", tradeSource=" + tradeSource + ", tradeWay=" + tradeWay + ", tradeAmount=" + tradeAmount + ", tradeDate=" + tradeDate + ", userId=" + userId + ", tradeResult=" + tradeResult + ", companyId=" + companyId + ", schoolId=" + schoolId + ", tradeType=" + tradeType + ", lastAmount=" + lastAmount + ", stuId=" + stuId + ", updator=" + updator + ", updateTime=" + updateTime +  "]";
	}

	public Integer getPayMasterId() {
		return payMasterId;
	}

	public void setPayMasterId(Integer payMasterId) {
		this.payMasterId = payMasterId;
	}
}
