package com.yuxin.wx.vo.company;

import com.yuxin.wx.common.BaseEntity;

@SuppressWarnings("serial")
public class VipClassPackage extends BaseEntity {
	
	private String name;				/* 课程名*/
	private String coverUrl;			/* 图片路径*/
	private String overview;			/* 描述*/
	private Double originalPrice;		/* 原价*/
	private Double realPrice;			/* 现价*/
	private Integer baseNum;			/* 基数*/
	private Integer stage;				/* 阶段数*/
	private Integer course;				/* 课程数*/

	private Integer buyNum;				/* 购买数*/
	
	private String vipName;				/* 会员等级*/
	private Float memberDiscount;		/* 折扣*/
	private Double currentPrice;		/* 折扣价*/
	
	
	public VipClassPackage() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCoverUrl() {
		return coverUrl;
	}
	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	public Double getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
	}
	public Double getRealPrice() {
		return realPrice;
	}
	public void setRealPrice(Double realPrice) {
		this.realPrice = realPrice;
	}
	public Integer getBuyNum() {
		return buyNum;
	}
	public void setBuyNum(Integer buyNum) {
		this.buyNum = buyNum;
	}
	public String getVipName() {
		return vipName;
	}
	public void setVipName(String vipName) {
		this.vipName = vipName;
	}
	public Float getMemberDiscount() {
		return memberDiscount;
	}
	public void setMemberDiscount(Float memberDiscount) {
		this.memberDiscount = memberDiscount;
	}
	public Double getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(Double currentPrice) {
		this.currentPrice = currentPrice;
	}
	public Integer getStage() {
		return stage;
	}
	public void setStage(Integer stage) {
		this.stage = stage;
	}
	public Integer getCourse() {
		return course;
	}
	public void setCourse(Integer course) {
		this.course = course;
	}
	public Integer getBaseNum() {
		return baseNum;
	}
	public void setBaseNum(Integer baseNum) {
		this.baseNum = baseNum;
	}
	
}
