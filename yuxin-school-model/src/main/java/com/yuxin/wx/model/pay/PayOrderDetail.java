package com.yuxin.wx.model.pay;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:PayOrderDetail
 * 
 * @author chopin
 * @date 2015-3-12
 */
@SuppressWarnings("serial")
public class PayOrderDetail extends BaseEntity {
	
	
	private Integer	payOrderId;		 /* 订单id */ 
	private Integer	comId;		 /* 商品id */ 
	private String	comName;		 /* 商品名称 */ 
	private Double	comOriginalPrice;		
	private Double	comRealPrice;		
	private String	comCoverUrl;		
	private Integer	comNum;		
	private String	comType;		

	// Constructor
	public PayOrderDetail() {
	}
	
	/**
	 * full Constructor
	 */
	public PayOrderDetail(Integer id, Integer payOrderId, Integer comId, String comName, Double comOriginalPrice, Double comRealPrice, String comCoverUrl, Integer comNum, String comType) {
		setId(id);
		this.payOrderId = payOrderId;
		this.comId = comId;
		this.comName = comName;
		this.comOriginalPrice = comOriginalPrice;
		this.comRealPrice = comRealPrice;
		this.comCoverUrl = comCoverUrl;
		this.comNum = comNum;
		this.comType = comType;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为PayOrderDetail可以实现连缀设置属性
	
	public Integer getPayOrderId() {
		return payOrderId;
	}

	public PayOrderDetail setPayOrderId(Integer payOrderId) {
		this.payOrderId = payOrderId;
		return this;
	}
	
	
	public Integer getComId() {
		return comId;
	}

	public PayOrderDetail setComId(Integer comId) {
		this.comId = comId;
		return this;
	}
	
	
	public String getComName() {
		return comName;
	}

	public PayOrderDetail setComName(String comName) {
		this.comName = comName;
		return this;
	}
	
	
	public Double getComOriginalPrice() {
		return comOriginalPrice;
	}

	public PayOrderDetail setComOriginalPrice(Double comOriginalPrice) {
		this.comOriginalPrice = comOriginalPrice;
		return this;
	}
	
	
	public Double getComRealPrice() {
		return comRealPrice;
	}

	public PayOrderDetail setComRealPrice(Double comRealPrice) {
		this.comRealPrice = comRealPrice;
		return this;
	}
	
	
	public String getComCoverUrl() {
		return comCoverUrl;
	}

	public PayOrderDetail setComCoverUrl(String comCoverUrl) {
		this.comCoverUrl = comCoverUrl;
		return this;
	}
	
	
	public Integer getComNum() {
		return comNum;
	}

	public PayOrderDetail setComNum(Integer comNum) {
		this.comNum = comNum;
		return this;
	}
	
	
	public String getComType() {
		return comType;
	}

	public PayOrderDetail setComType(String comType) {
		this.comType = comType;
		return this;
	}
	
	@Override
	public String toString() {
		return "PayOrderDetail [" + "id=" + getId() + ", payOrderId=" + payOrderId + ", comId=" + comId + ", comName=" + comName + ", comOriginalPrice=" + comOriginalPrice + ", comRealPrice=" + comRealPrice + ", comCoverUrl=" + comCoverUrl + ", comNum=" + comNum + ", comType=" + comType +  "]";
	}
}
