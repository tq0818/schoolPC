package com.yuxin.wx.vo.company;

import java.util.List;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.model.classes.ClassTypeMemberDiscount;

@SuppressWarnings("serial")
public class MemberLevelVo extends BaseEntity {
	

	private String	name;		 /* 会员等级名称 */ 
	private String	openWay;		 /* 开通方式 */ 
	private Integer	discount;		 /* 折扣 */ 
	private String	ico;		 /* 图标 */ 
	private String	description;		 /* 简介 */ 
	private Integer	companyId;		 /* 公司ID */ 
	private Integer	consumption;		 /* 累积型会员-消费额 */ 
	
	private List<ClassTypeMemberDiscount> ctmd;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOpenWay() {
		return openWay;
	}

	public void setOpenWay(String openWay) {
		this.openWay = openWay;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
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

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getConsumption() {
		return consumption;
	}

	public void setConsumption(Integer consumption) {
		this.consumption = consumption;
	}

	public List<ClassTypeMemberDiscount> getCtmd() {
		return ctmd;
	}

	public void setCtmd(List<ClassTypeMemberDiscount> ctmd) {
		this.ctmd = ctmd;
	}


}
