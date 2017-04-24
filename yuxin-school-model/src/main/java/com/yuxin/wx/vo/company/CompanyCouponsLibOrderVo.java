package com.yuxin.wx.vo.company;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.util.ShortDateSerializer;

/**
 * POJO:CompanyCouponsPatch
 * 
 * @author wang.zx
 * @date 2016-6-21
 */
@SuppressWarnings("serial")
public class CompanyCouponsLibOrderVo extends BaseEntity {
	
	private String code;
	private String status;
	private String userName;
	private String userMobile;
	private String commodityName;
	private String originalPrice;
	private String payPrice;
	private Date sendDate;
	private Date useTime;
	private Integer commodityType;
	private String rangeItemPackageCategory;
	

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserMobile() {
		return userMobile;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public String getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(String originalPrice) {
		this.originalPrice = originalPrice;
	}
	public String getPayPrice() {
		return payPrice;
	}
	public void setPayPrice(String payPrice) {
		this.payPrice = payPrice;
	}
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getUseTime() {
		return useTime;
	}
	public void setUseTime(Date useTime) {
		this.useTime = useTime;
	}
	public Integer getCommodityType() {
		return commodityType;
	}
	public void setCommodityType(Integer commodityType) {
		this.commodityType = commodityType;
	}
	public String getRangeItemPackageCategory() {
		return rangeItemPackageCategory;
	}
	public void setRangeItemPackageCategory(String rangeItemPackageCategory) {
		this.rangeItemPackageCategory = rangeItemPackageCategory;
	}
}
