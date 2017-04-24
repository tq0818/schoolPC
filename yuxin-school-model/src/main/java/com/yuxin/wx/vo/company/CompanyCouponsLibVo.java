package com.yuxin.wx.vo.company;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.util.ShortDateSerializer;
import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyCouponsLib
 * 
 * @author wang.zx
 * @date 2016-6-21
 */
@SuppressWarnings("serial")
public class CompanyCouponsLibVo extends BaseEntity {
	
	
	private String	code;		
	private Date	timeLimitFrom;		 /* 使用期限从 */ 
	private Date	timeLimitTo;		 /* 使用期限至 */ 
	private Integer	status;		 /* 使用状态 0-未使用 1-已使用 */ 
	private Integer	patchId;		 /* 批次号 */ 
	private Integer	userId;		 /* 所属人ID 后台用户 */ 
	private Date	useTime;		 /* 使用日期  如果状态为已使用，则该字段有值 */ 
	private Double	insCashNum;		 /* 抵现金额 */ 
	private Double	overNum;		 /* 满* 元减 */ 
	private Double	overCutNum;		 /* 满减*元 */ 
	private Double	discountNum;		 /* 折扣 */ 
	private String	description;		 /* 描述 */ 
	private Integer	useRange;		 /* 优惠范围 0-全部课程  1-学科 2-课程 */ 
	private String	useWay;		 /* 优惠方式 0-抵现 1-满减 2-打折 */ 
	private Integer	rangeItemOne;		 /* 范围-学科ID */ 
	private Integer	rangeItemSecond;		 /* 范围-学科小类ID */ 
	private Integer	rangeItemCourse;		 /* 范围-课程的商品ID */ 
	private Integer	delFlag;		 /* 删除标记 */ 
	private Integer commodityType;
	private String rangeItemPackageCategory;

	private String statusOrder;
	private String usetimeOrder;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getTimeLimitFrom() {
		return timeLimitFrom;
	}
	public void setTimeLimitFrom(Date timeLimitFrom) {
		this.timeLimitFrom = timeLimitFrom;
	}
	public Date getTimeLimitTo() {
		return timeLimitTo;
	}
	public void setTimeLimitTo(Date timeLimitTo) {
		this.timeLimitTo = timeLimitTo;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getPatchId() {
		return patchId;
	}
	public void setPatchId(Integer patchId) {
		this.patchId = patchId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Date getUseTime() {
		return useTime;
	}
	public void setUseTime(Date useTime) {
		this.useTime = useTime;
	}
	public Double getInsCashNum() {
		return insCashNum;
	}
	public void setInsCashNum(Double insCashNum) {
		this.insCashNum = insCashNum;
	}
	public Double getOverNum() {
		return overNum;
	}
	public void setOverNum(Double overNum) {
		this.overNum = overNum;
	}
	public Double getOverCutNum() {
		return overCutNum;
	}
	public void setOverCutNum(Double overCutNum) {
		this.overCutNum = overCutNum;
	}
	public Double getDiscountNum() {
		return discountNum;
	}
	public void setDiscountNum(Double discountNum) {
		this.discountNum = discountNum;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getUseRange() {
		return useRange;
	}
	public void setUseRange(Integer useRange) {
		this.useRange = useRange;
	}
	public String getUseWay() {
		return useWay;
	}
	public void setUseWay(String useWay) {
		this.useWay = useWay;
	}
	public Integer getRangeItemOne() {
		return rangeItemOne;
	}
	public void setRangeItemOne(Integer rangeItemOne) {
		this.rangeItemOne = rangeItemOne;
	}
	public Integer getRangeItemSecond() {
		return rangeItemSecond;
	}
	public void setRangeItemSecond(Integer rangeItemSecond) {
		this.rangeItemSecond = rangeItemSecond;
	}
	public Integer getRangeItemCourse() {
		return rangeItemCourse;
	}
	public void setRangeItemCourse(Integer rangeItemCourse) {
		this.rangeItemCourse = rangeItemCourse;
	}
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	public String getStatusOrder() {
		return statusOrder;
	}
	public void setStatusOrder(String statusOrder) {
		this.statusOrder = statusOrder;
	}
	public String getUsetimeOrder() {
		return usetimeOrder;
	}
	public void setUsetimeOrder(String usetimeOrder) {
		this.usetimeOrder = usetimeOrder;
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
