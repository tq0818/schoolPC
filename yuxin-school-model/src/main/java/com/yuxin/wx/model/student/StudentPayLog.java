package com.yuxin.wx.model.student;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:StudentPayLog
 * @author wang.zx
 * @date 2014-12-5
 */
@SuppressWarnings("serial")
public class StudentPayLog extends BaseEntity {
	
	
	private Integer	payMasterId;		 /* 学员报名主订单id */ 
	private String	operateType;		 /* 操作类型，字典表 */ 
	private String	description;		 /* 详细操作描述，记录字段内容的修改 */ 
	private Date	operateTime;		 /* 操作时间 */ 
	private Integer	operator;		 /* 操作人 */ 
	private Integer companyId;

	// Constructor
	public StudentPayLog() {
	}
	
	/**
	 * full Constructor
	 */
	public StudentPayLog(Integer id, Integer payMasterId, String operateType, String description, Date operateTime, Integer operator) {
		setId(id);
		this.payMasterId = payMasterId;
		this.operateType = operateType;
		this.description = description;
		this.operateTime = operateTime;
		this.operator = operator;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为StudentPayLog可以实现连缀设置属性
	
	public Integer getPayMasterId() {
		return payMasterId;
	}

	public StudentPayLog setPayMasterId(Integer payMasterId) {
		this.payMasterId = payMasterId;
		return this;
	}
	
	
	public String getOperateType() {
		return operateType;
	}

	public StudentPayLog setOperateType(String operateType) {
		this.operateType = operateType;
		return this;
	}
	
	
	public String getDescription() {
		return description;
	}

	public StudentPayLog setDescription(String description) {
		this.description = description;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getOperateTime() {
		return operateTime;
	}

	public StudentPayLog setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
		return this;
	}
	
	
	public Integer getOperator() {
		return operator;
	}

	public StudentPayLog setOperator(Integer operator) {
		this.operator = operator;
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
		return "StudentPayLog [" + "id=" + getId() + ", payMasterId=" + payMasterId + ", operateType=" + operateType + ", description=" + description + ", operateTime=" + operateTime + ", operator=" + operator +  "]";
	}
}
