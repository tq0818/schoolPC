package com.yuxin.wx.vo.course;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.util.ShortDateSerializer;
import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:PayOrder
 * 
 * @author chopin
 * @date 2015-3-12
 */
@SuppressWarnings("serial")
public class OrderManage extends BaseEntity {
	
	private String mobile;
	private String stuName;
	private Date	payTime;		 /* 支付时间 */ 
	private Date	orderTime;		 /* 订单创建时间 */ 
	private Integer	commodityId;		 /* 商品id */ 
	private Double	totalMount;		 
	private Double	payPrice;		 /* 支付价格 */ 
	private String	payType;		 /* 支付类型（支付宝；网上银行；语音支付），字典表数据 */

	private String commodityType;
	private String applyChannelCode;

	private Integer	companyId;		 /* 所属公司id */ 
	private Integer	schoolId;		 /* 所属分校id */ 
	
	private String startDate;
	private String endDate;
	private Integer timeLen;
	private String timeMark;
	private Integer stageId;
	private Integer userId;
	private String username;
	
	// Constructor
	public OrderManage() {
	}
	
	public OrderManage(Date payTime, Date orderTime, Integer commodityId,
			Double totalMount, Double payPrice, String payType,
			String commodityType, Integer companyId, Integer schoolId,
			String mobile, String stuName, String startDate, String endDate,
			Integer timeLen, String timeMark) {
		this.payTime = payTime;
		this.orderTime = orderTime;
		this.commodityId = commodityId;
		this.totalMount = totalMount;
		this.payPrice = payPrice;
		this.payType = payType;
		this.commodityType = commodityType;
		this.companyId = companyId;
		this.schoolId = schoolId;
		this.mobile = mobile;
		this.stuName = stuName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.timeLen = timeLen;
		this.timeMark = timeMark;
	}

	public Integer getStageId() {
		return stageId;
	}

	public void setStageId(Integer stageId) {
		this.stageId = stageId;
	}

	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Integer getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
	}

	public String getApplyChannelCode() {
		return applyChannelCode;
	}

	public void setApplyChannelCode(String applyChannelCode) {
		this.applyChannelCode = applyChannelCode;
	}
	
	public Double getTotalMount() {
		return totalMount;
	}

	public void setTotalMount(Double totalMount) {
		this.totalMount = totalMount;
	}

	public Double getPayPrice() {
		return payPrice;
	}

	public void setPayPrice(Double payPrice) {
		this.payPrice = payPrice;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getCommodityType() {
		return commodityType;
	}

	public void setCommodityType(String commodityType) {
		this.commodityType = commodityType;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getTimeLen() {
		return timeLen;
	}

	public void setTimeLen(Integer timeLen) {
		this.timeLen = timeLen;
	}

	public String getTimeMark() {
		return timeMark;
	}

	public void setTimeMark(String timeMark) {
		this.timeMark = timeMark;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
