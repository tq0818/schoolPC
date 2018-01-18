package com.yuxin.wx.model.pay;

import java.io.Serializable;
/**
 * 支付地址记录表
 * @author admin
 *
 */
public class PaymentUrl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String orderNum;
	private String wxCodeImgUrl;
	private String zfbCodeImgUrl;
	private String payInfo;
	private Integer userId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getWxCodeImgUrl() {
		return wxCodeImgUrl;
	}
	public void setWxCodeImgUrl(String wxCodeImgUrl) {
		this.wxCodeImgUrl = wxCodeImgUrl;
	}
	public String getZfbCodeImgUrl() {
		return zfbCodeImgUrl;
	}
	public void setZfbCodeImgUrl(String zfbCodeImgUrl) {
		this.zfbCodeImgUrl = zfbCodeImgUrl;
	}
	public String getPayInfo() {
		return payInfo;
	}
	public void setPayInfo(String payInfo) {
		this.payInfo = payInfo;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
}
