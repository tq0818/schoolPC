package com.yuxin.wx.model.student;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:StudentFeeStage
 * @author wang.zx
 * @date 2014-12-5
 */
@SuppressWarnings("serial")
public class StudentFeeStage extends BaseEntity {
	
	
	private Integer	stuId;		 /* 学生id */ 
	private Integer	payMasterId;		 /* 主订单id */ 
	private Date	stageDate;		 /* 分期日期 */ 
	private Double	stageFee;		 /* 分期费用 */ 
	private String	stageStatus;		 /* 分期支付状态（1：已缴费；0：未缴费） */ 
	private String	payMethod;		 /* 支付方式（pos；现金；支票；汇款），字典表 */ 
	private String	remark;		
	private String	createType;		 /* 分期 */ 
	private Double posReal;
	private Double cashReal;
	private Double checkReal;
	private Double remitReal;
	private Date payDate;
	private Date createTime;
	private Integer creator;
	private Date updateTime;
	private Integer updator;
	private Integer companyId;
	private Integer delFlag;		//删除

	// Constructor
	public StudentFeeStage() {
	}
	
	/**
	 * full Constructor
	 */
	public StudentFeeStage(Integer id, Integer stuId, Integer payMasterId, Date stageDate, Double stageFee, String stageStatus, String payMethod, String remark, String createType) {
		setId(id);
		this.stuId = stuId;
		this.payMasterId = payMasterId;
		this.stageDate = stageDate;
		this.stageFee = stageFee;
		this.stageStatus = stageStatus;
		this.payMethod = payMethod;
		this.remark = remark;
		this.createType = createType;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为StudentFeeStage可以实现连缀设置属性
	
	public Integer getStuId() {
		return stuId;
	}

	public StudentFeeStage setStuId(Integer stuId) {
		this.stuId = stuId;
		return this;
	}
	
	
	public Integer getPayMasterId() {
		return payMasterId;
	}

	public StudentFeeStage setPayMasterId(Integer payMasterId) {
		this.payMasterId = payMasterId;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getStageDate() {
		return stageDate;
	}

	public StudentFeeStage setStageDate(Date stageDate) {
		this.stageDate = stageDate;
		return this;
	}
	
	
	public Double getStageFee() {
		return stageFee;
	}

	public StudentFeeStage setStageFee(Double stageFee) {
		this.stageFee = stageFee;
		return this;
	}
	
	
	public String getStageStatus() {
		return stageStatus;
	}

	public StudentFeeStage setStageStatus(String stageStatus) {
		this.stageStatus = stageStatus;
		return this;
	}
	
	
	public String getPayMethod() {
		return payMethod;
	}

	public StudentFeeStage setPayMethod(String payMethod) {
		this.payMethod = payMethod;
		return this;
	}
	
	
	public String getRemark() {
		return remark;
	}

	public StudentFeeStage setRemark(String remark) {
		this.remark = remark;
		return this;
	}
	
	
	public String getCreateType() {
		return createType;
	}

	public StudentFeeStage setCreateType(String createType) {
		this.createType = createType;
		return this;
	}
	
	
	public Double getPosReal() {
		return posReal;
	}

	public void setPosReal(Double posReal) {
		this.posReal = posReal;
	}

	public Double getCashReal() {
		return cashReal;
	}

	public void setCashReal(Double cashReal) {
		this.cashReal = cashReal;
	}

	public Double getCheckReal() {
		return checkReal;
	}

	public void setCheckReal(Double checkReal) {
		this.checkReal = checkReal;
	}

	public Double getRemitReal() {
		return remitReal;
	}

	public void setRemitReal(Double remitReal) {
		this.remitReal = remitReal;
	}



	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getUpdator() {
		return updator;
	}

	public void setUpdator(Integer updator) {
		this.updator = updator;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	
	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	@Override
	public String toString() {
		return "StudentFeeStage [" + "id=" + getId() + ", stuId=" + stuId + ", payMasterId=" + payMasterId + ", stageDate=" + stageDate + ", stageFee=" + stageFee + ", stageStatus=" + stageStatus + ", payMethod=" + payMethod + ", remark=" + remark + ", createType=" + createType +  "]";
	}
}
