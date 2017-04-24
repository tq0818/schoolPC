package com.yuxin.wx.vo.company;

import java.util.List;

import com.yuxin.wx.common.BaseEntity;

@SuppressWarnings("serial")
public class VipDateVo extends BaseEntity {
	
	private String vipName;			/* 会员名*/
	private Integer openWay;			/* 开通方式*/
	private Float discount;		/* 折扣*/
	private String ico;				/* 图标地址*/
	private String description;		/* 简介*/
	private Integer consumption;		/* 累计消费 ，累计型消费使用*/
	
	private List<CompanyMemberLevelDetailVo> cmld; /* 购买升级时，购买信息*/

	
	public VipDateVo() {
		super();
	}

	public String getVipName() {
		return vipName;
	}

	public void setVipName(String vipName) {
		this.vipName = vipName;
	}

	public Float getDiscount() {
		return discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	public String getIco() {
		return ico;
	}

	public void setIco(String ico) {
		this.ico = ico;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getConsumption() {
		return consumption;
	}

	public void setConsumption(Integer consumption) {
		this.consumption = consumption;
	}

	public List<CompanyMemberLevelDetailVo> getCmld() {
		return cmld;
	}

	public void setCmld(List<CompanyMemberLevelDetailVo> cmld) {
		this.cmld = cmld;
	}

	public Integer getOpenWay() {
		return openWay;
	}

	public void setOpenWay(Integer openWay) {
		this.openWay = openWay;
	}
}
