package com.yuxin.wx.model.system;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:SysLogCouponUse
 * 
 * @author wang.zx
 * @date 2016-6-21
 */
@SuppressWarnings("serial")
public class SysLogCouponUse extends BaseEntity {
	
	
	private Integer	userId;		 /* 用户ID */ 
	private Date	useTime;		 /* 使用时间 */ 
	private Integer	useWay;		 /* 使用途径 0-web  1-微信 2-app */ 
	private String	couponCode;		 /* 优惠码 */ 
	private Integer	commodityId;		 /* 商品ID */ 
	private String	commodityType;		 /* 商品类型   */ 

	// Constructor
	public SysLogCouponUse() {
	}
	
	/**
	 * full Constructor
	 */
	public SysLogCouponUse(Integer id, Integer userId, Date useTime, Integer useWay, String couponCode, Integer commodityId, String commodityType) {
		setId(id);
		this.userId = userId;
		this.useTime = useTime;
		this.useWay = useWay;
		this.couponCode = couponCode;
		this.commodityId = commodityId;
		this.commodityType = commodityType;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为SysLogCouponUse可以实现连缀设置属性
	
	public Integer getUserId() {
		return userId;
	}

	public SysLogCouponUse setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getUseTime() {
		return useTime;
	}

	public SysLogCouponUse setUseTime(Date useTime) {
		this.useTime = useTime;
		return this;
	}
	
	
	public Integer getUseWay() {
		return useWay;
	}

	public SysLogCouponUse setUseWay(Integer useWay) {
		this.useWay = useWay;
		return this;
	}
	
	
	public String getCouponCode() {
		return couponCode;
	}

	public SysLogCouponUse setCouponCode(String couponCode) {
		this.couponCode = couponCode;
		return this;
	}
	
	
	public Integer getCommodityId() {
		return commodityId;
	}

	public SysLogCouponUse setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
		return this;
	}
	
	
	public String getCommodityType() {
		return commodityType;
	}

	public SysLogCouponUse setCommodityType(String commodityType) {
		this.commodityType = commodityType;
		return this;
	}
	
	@Override
	public String toString() {
		return "SysLogCouponUse [" + "id=" + getId() + ", userId=" + userId + ", useTime=" + useTime + ", useWay=" + useWay + ", couponCode=" + couponCode + ", commodityId=" + commodityId + ", commodityType=" + commodityType +  "]";
	}
}
