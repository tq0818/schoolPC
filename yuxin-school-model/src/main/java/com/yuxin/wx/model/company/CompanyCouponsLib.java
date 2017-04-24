package com.yuxin.wx.model.company;

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
public class CompanyCouponsLib extends BaseEntity {
	
	
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
	private Integer sortNum;/*排序字段*/
	private Integer creater;/*创建人*/
	private Integer orderId;/*订单id*/
	
	private Integer commodityType;
	private String rangeItemPackageCategory;

	// Constructor
	public CompanyCouponsLib() {
	}
	
	/**
	 * full Constructor
	 */
	public CompanyCouponsLib(Integer id, String code, Date timeLimitFrom, Date timeLimitTo, Integer status, Integer patchId, Integer userId, Date useTime, Double insCashNum, Double overNum, Double overCutNum, Double discountNum, String description, Integer useRange, String useWay, Integer rangeItemOne, Integer rangeItemSecond, Integer rangeItemCourse, Integer delFlag) {
		setId(id);
		this.code = code;
		this.timeLimitFrom = timeLimitFrom;
		this.timeLimitTo = timeLimitTo;
		this.status = status;
		this.patchId = patchId;
		this.userId = userId;
		this.useTime = useTime;
		this.insCashNum = insCashNum;
		this.overNum = overNum;
		this.overCutNum = overCutNum;
		this.discountNum = discountNum;
		this.description = description;
		this.useRange = useRange;
		this.useWay = useWay;
		this.rangeItemOne = rangeItemOne;
		this.rangeItemSecond = rangeItemSecond;
		this.rangeItemCourse = rangeItemCourse;
		this.delFlag = delFlag;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CompanyCouponsLib可以实现连缀设置属性
	
	public String getCode() {
		return code;
	}

	public CompanyCouponsLib setCode(String code) {
		this.code = code;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getTimeLimitFrom() {
		return timeLimitFrom;
	}

	public CompanyCouponsLib setTimeLimitFrom(Date timeLimitFrom) {
		this.timeLimitFrom = timeLimitFrom;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getTimeLimitTo() {
		return timeLimitTo;
	}

	public CompanyCouponsLib setTimeLimitTo(Date timeLimitTo) {
		this.timeLimitTo = timeLimitTo;
		return this;
	}
	
	
	public Integer getStatus() {
		return status;
	}

	public CompanyCouponsLib setStatus(Integer status) {
		this.status = status;
		return this;
	}
	
	
	public Integer getPatchId() {
		return patchId;
	}

	public CompanyCouponsLib setPatchId(Integer patchId) {
		this.patchId = patchId;
		return this;
	}
	
	
	public Integer getUserId() {
		return userId;
	}

	public CompanyCouponsLib setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getUseTime() {
		return useTime;
	}

	public CompanyCouponsLib setUseTime(Date useTime) {
		this.useTime = useTime;
		return this;
	}
	
	
	public Double getInsCashNum() {
		return insCashNum;
	}

	public CompanyCouponsLib setInsCashNum(Double insCashNum) {
		this.insCashNum = insCashNum;
		return this;
	}
	
	
	public Double getOverNum() {
		return overNum;
	}

	public CompanyCouponsLib setOverNum(Double overNum) {
		this.overNum = overNum;
		return this;
	}
	
	
	public Double getOverCutNum() {
		return overCutNum;
	}

	public CompanyCouponsLib setOverCutNum(Double overCutNum) {
		this.overCutNum = overCutNum;
		return this;
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

	public CompanyCouponsLib setDescription(String description) {
		this.description = description;
		return this;
	}
	
	
	public Integer getUseRange() {
		return useRange;
	}

	public CompanyCouponsLib setUseRange(Integer useRange) {
		this.useRange = useRange;
		return this;
	}
	
	
	public String getUseWay() {
		return useWay;
	}

	public CompanyCouponsLib setUseWay(String useWay) {
		this.useWay = useWay;
		return this;
	}
	
	
	public Integer getRangeItemOne() {
		return rangeItemOne;
	}

	public CompanyCouponsLib setRangeItemOne(Integer rangeItemOne) {
		this.rangeItemOne = rangeItemOne;
		return this;
	}
	
	
	public Integer getRangeItemSecond() {
		return rangeItemSecond;
	}

	public CompanyCouponsLib setRangeItemSecond(Integer rangeItemSecond) {
		this.rangeItemSecond = rangeItemSecond;
		return this;
	}
	
	
	public Integer getRangeItemCourse() {
		return rangeItemCourse;
	}

	public CompanyCouponsLib setRangeItemCourse(Integer rangeItemCourse) {
		this.rangeItemCourse = rangeItemCourse;
		return this;
	}
	
	
	public Integer getDelFlag() {
		return delFlag;
	}

	public CompanyCouponsLib setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
		return this;
	}
	
	@Override
	public String toString() {
		return "CompanyCouponsLib [" + "id=" + getId() + ", code=" + code + ", timeLimitFrom=" + timeLimitFrom + ", timeLimitTo=" + timeLimitTo + ", status=" + status + ", patchId=" + patchId + ", userId=" + userId + ", useTime=" + useTime + ", insCashNum=" + insCashNum + ", overNum=" + overNum + ", overCutNum=" + overCutNum + ", discountNum=" + discountNum + ", description=" + description + ", useRange=" + useRange + ", useWay=" + useWay + ", rangeItemOne=" + rangeItemOne + ", rangeItemSecond=" + rangeItemSecond + ", rangeItemCourse=" + rangeItemCourse + ", delFlag=" + delFlag +  "]";
	}

	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

	public Integer getCreater() {
		return creater;
	}

	public void setCreater(Integer creater) {
		this.creater = creater;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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
