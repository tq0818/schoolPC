package com.yuxin.wx.model.company;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyMemberLevelConfig
 * 
 * @author chopin
 * @date 2016-5-17
 */
@SuppressWarnings("serial")
public class CompanyMemberLevelConfig extends BaseEntity {
	
	
	private String	name;		 /* 会员等级名称 */ 
	private Integer	openWay;		 /* 开通方式 */ 
	private double	discount;		 /* 折扣 */ 
	private String	ico;		 /* 图标 */ 
	private String	description;		 /* 简介 */ 
	private Integer	companyId;		 /* 公司ID */ 
	private Integer	consumption;		 /* 累积型会员-消费额 */ 
	private Integer	delFlag;		 /* 删除标记 */ 
	private Integer	sequence;		 /* 顺序 */ 
	private Integer discountType;/*打折类型 0-免费  1-打折*/
	private Integer useFlag;
	public CompanyMemberLevelConfig(String name, Integer openWay, double discount, String ico, String description,
			Integer companyId, Integer consumption, Integer delFlag, Integer sequence, Integer discountType,
			Integer useFlag) {
		super();
		this.name = name;
		this.openWay = openWay;
		this.discount = discount;
		this.ico = ico;
		this.description = description;
		this.companyId = companyId;
		this.consumption = consumption;
		this.delFlag = delFlag;
		this.sequence = sequence;
		this.discountType = discountType;
		this.useFlag = useFlag;
	}
	public CompanyMemberLevelConfig() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getOpenWay() {
		return openWay;
	}
	public void setOpenWay(Integer openWay) {
		this.openWay = openWay;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
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
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	public Integer getDiscountType() {
		return discountType;
	}
	public void setDiscountType(Integer discountType) {
		this.discountType = discountType;
	}
	public Integer getUseFlag() {
		return useFlag;
	}
	public void setUseFlag(Integer useFlag) {
		this.useFlag = useFlag;
	}
	
}