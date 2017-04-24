package com.yuxin.wx.vo.company;

import com.yuxin.wx.common.BaseEntity;

@SuppressWarnings("serial")
public class VipCourse extends BaseEntity {

	private String name;				/* 课程名*/
	private String coverUrl;			/* 图片路径*/
	private String overview;			/* 描述*/
	private Double originalPrice;		/* 原价*/
	private Double realPrice;			/* 现价*/
	private Integer baseNum;			/* 基数*/
	private Integer faceFlag;			/* 面授*/
	private Integer liveFlag;			/* 直播*/
	private Integer videoFlag;			/* 视频*/
	private Integer remoteFlag;			/* 远程*/
	private Integer buyNum;				/* 购买数*/
	
	private String vipName;				/* 会员等级*/
	private Float memberDiscount;		/* 折扣*/
	private Double currentPrice;		/* 折扣价*/
	
	public VipCourse() {
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
	public Integer getFaceFlag() {
		return faceFlag;
	}
	public void setFaceFlag(Integer faceFlag) {
		this.faceFlag = faceFlag;
	}
	public Integer getLiveFlag() {
		return liveFlag;
	}
	public void setLiveFlag(Integer liveFlag) {
		this.liveFlag = liveFlag;
	}
	public Integer getVideoFlag() {
		return videoFlag;
	}
	public void setVideoFlag(Integer videoFlag) {
		this.videoFlag = videoFlag;
	}
	public Integer getRemoteFlag() {
		return remoteFlag;
	}
	public void setRemoteFlag(Integer remoteFlag) {
		this.remoteFlag = remoteFlag;
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
	public Integer getBaseNum() {
		return baseNum;
	}
	public void setBaseNum(Integer baseNum) {
		this.baseNum = baseNum;
	}
	
}
