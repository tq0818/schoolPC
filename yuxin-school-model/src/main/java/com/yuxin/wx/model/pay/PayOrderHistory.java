package com.yuxin.wx.model.pay;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:PayOrderHistory
 * 
 * @author chopin
 * @date 2015-3-12
 */
@SuppressWarnings("serial")
public class PayOrderHistory extends BaseEntity {
	
	
	private String	payOrderId;		
	private String	payStatus;		 /* 支付状态 */ 
	private String	payType;		 /* 支付类型 */ 
	private Date	payTime;		 /* 支付时间 */ 
	private Double	price;		 /* 支付价格 */ 
	private String	payNum;		 /* 支付流水号（银行交易号） */ 

	// Constructor
	public PayOrderHistory() {
	}
	
	/**
	 * full Constructor
	 */
	public PayOrderHistory(Integer id, String payOrderId, String payStatus, String payType, Date payTime, Double price, String payNum) {
		setId(id);
		this.payOrderId = payOrderId;
		this.payStatus = payStatus;
		this.payType = payType;
		this.payTime = payTime;
		this.price = price;
		this.payNum = payNum;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为PayOrderHistory可以实现连缀设置属性
	
	public String getPayOrderId() {
		return payOrderId;
	}

	public PayOrderHistory setPayOrderId(String payOrderId) {
		this.payOrderId = payOrderId;
		return this;
	}
	
	
	public String getPayStatus() {
		return payStatus;
	}

	public PayOrderHistory setPayStatus(String payStatus) {
		this.payStatus = payStatus;
		return this;
	}
	
	
	public String getPayType() {
		return payType;
	}

	public PayOrderHistory setPayType(String payType) {
		this.payType = payType;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getPayTime() {
		return payTime;
	}

	public PayOrderHistory setPayTime(Date payTime) {
		this.payTime = payTime;
		return this;
	}
	
	
	public Double getPrice() {
		return price;
	}

	public PayOrderHistory setPrice(Double price) {
		this.price = price;
		return this;
	}
	
	
	public String getPayNum() {
		return payNum;
	}

	public PayOrderHistory setPayNum(String payNum) {
		this.payNum = payNum;
		return this;
	}
	
	@Override
	public String toString() {
		return "PayOrderHistory [" + "id=" + getId() + ", payOrderId=" + payOrderId + ", payStatus=" + payStatus + ", payType=" + payType + ", payTime=" + payTime + ", price=" + price + ", payNum=" + payNum +  "]";
	}
}
