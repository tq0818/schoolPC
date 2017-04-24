package com.yuxin.wx.vo.company;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.util.ShortDateSerializer;

public class CompanyCouponsLibClassVo extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	
	private String itemOneName;
	private String itemTwoName;
	private String classTypeName;
	
	private Integer type; /*查询条件*/
	
	private Integer userFrontCouponsId;

	// Constructor
	public CompanyCouponsLibClassVo() {
	}
	
	/**
	 * full Constructor
	 */
	public CompanyCouponsLibClassVo(Integer id, String code, Date timeLimitFrom, Date timeLimitTo, Integer status, Integer patchId, Integer userId, Date useTime, Double insCashNum, Double overNum, Double overCutNum, Double discountNum, String description, Integer useRange, String useWay, Integer rangeItemOne, Integer rangeItemSecond, Integer rangeItemCourse, Integer delFlag) {
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
	
	public Integer getType() {
		return type;
	}

	public Integer getUserFrontCouponsId() {
		return userFrontCouponsId;
	}

	public void setUserFrontCouponsId(Integer userFrontCouponsId) {
		this.userFrontCouponsId = userFrontCouponsId;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public String getItemOneName() {
		return itemOneName;
	}

	public void setItemOneName(String itemOneName) {
		this.itemOneName = itemOneName;
	}

	public String getItemTwoName() {
		return itemTwoName;
	}

	public void setItemTwoName(String itemTwoName) {
		this.itemTwoName = itemTwoName;
	}

	public String getClassTypeName() {
		return classTypeName;
	}

	public void setClassTypeName(String classTypeName) {
		this.classTypeName = classTypeName;
	}

	public CompanyCouponsLibClassVo setCode(String code) {
		this.code = code;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getTimeLimitFrom() {
		return timeLimitFrom;
	}

	public CompanyCouponsLibClassVo setTimeLimitFrom(Date timeLimitFrom) {
		this.timeLimitFrom = timeLimitFrom;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getTimeLimitTo() {
		return timeLimitTo;
	}

	public CompanyCouponsLibClassVo setTimeLimitTo(Date timeLimitTo) {
		this.timeLimitTo = timeLimitTo;
		return this;
	}
	
	
	public Integer getStatus() {
		return status;
	}

	public CompanyCouponsLibClassVo setStatus(Integer status) {
		this.status = status;
		return this;
	}
	
	
	public Integer getPatchId() {
		return patchId;
	}

	public CompanyCouponsLibClassVo setPatchId(Integer patchId) {
		this.patchId = patchId;
		return this;
	}
	
	
	public Integer getUserId() {
		return userId;
	}

	public CompanyCouponsLibClassVo setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getUseTime() {
		return useTime;
	}

	public CompanyCouponsLibClassVo setUseTime(Date useTime) {
		this.useTime = useTime;
		return this;
	}
	
	
	public Double getInsCashNum() {
		return insCashNum;
	}

	public CompanyCouponsLibClassVo setInsCashNum(Double insCashNum) {
		this.insCashNum = insCashNum;
		return this;
	}
	
	
	public Double getOverNum() {
		return overNum;
	}

	public CompanyCouponsLibClassVo setOverNum(Double overNum) {
		this.overNum = overNum;
		return this;
	}
	
	
	public Double getOverCutNum() {
		return overCutNum;
	}

	public CompanyCouponsLibClassVo setOverCutNum(Double overCutNum) {
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

	public CompanyCouponsLibClassVo setDescription(String description) {
		this.description = description;
		return this;
	}
	
	
	public Integer getUseRange() {
		return useRange;
	}

	public CompanyCouponsLibClassVo setUseRange(Integer useRange) {
		this.useRange = useRange;
		return this;
	}
	
	
	public String getUseWay() {
		return useWay;
	}

	public CompanyCouponsLibClassVo setUseWay(String useWay) {
		this.useWay = useWay;
		return this;
	}
	
	
	public Integer getRangeItemOne() {
		return rangeItemOne;
	}

	public CompanyCouponsLibClassVo setRangeItemOne(Integer rangeItemOne) {
		this.rangeItemOne = rangeItemOne;
		return this;
	}
	
	
	public Integer getRangeItemSecond() {
		return rangeItemSecond;
	}

	public CompanyCouponsLibClassVo setRangeItemSecond(Integer rangeItemSecond) {
		this.rangeItemSecond = rangeItemSecond;
		return this;
	}
	
	
	public Integer getRangeItemCourse() {
		return rangeItemCourse;
	}

	public CompanyCouponsLibClassVo setRangeItemCourse(Integer rangeItemCourse) {
		this.rangeItemCourse = rangeItemCourse;
		return this;
	}
	
	
	public Integer getDelFlag() {
		return delFlag;
	}

	public CompanyCouponsLibClassVo setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
		return this;
	}
	
	@Override
	public String toString() {
		return "CompanyCouponsLibClassVo [" + "id=" + getId() + ", code=" + code + ", timeLimitFrom=" + timeLimitFrom + ", timeLimitTo=" + timeLimitTo + ", status=" + status + ", patchId=" + patchId + ", userId=" + userId + ", useTime=" + useTime + ", insCashNum=" + insCashNum + ", overNum=" + overNum + ", overCutNum=" + overCutNum + ", discountNum=" + discountNum + ", description=" + description + ", useRange=" + useRange + ", useWay=" + useWay + ", rangeItemOne=" + rangeItemOne + ", rangeItemSecond=" + rangeItemSecond + ", rangeItemCourse=" + rangeItemCourse + ", delFlag=" + delFlag +  "]";
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
