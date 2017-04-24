package com.yuxin.wx.model.company;

import java.util.List;

import com.yuxin.wx.common.BaseEntity;

public class CompanyMemberLevelConfigVo extends BaseEntity {

	private static final long serialVersionUID = 8993874405593274471L;
	private String name; /* 会员等级名称 */
	private Integer openWay; /* 开通方式 */
	private double discount; /* 折扣 */
	private String ico; /* 图标 */
	private String description; /* 简介 */
	private Integer companyId; /* 公司ID */
	private Integer consumption; /* 累积型会员-消费额 */
	private Integer delFlag; /* 删除标记 */
	private Integer sequence; /* 顺序 */
	private String listCmld;/* CompanyMemberLevelDetail集合 */
	private Integer memberId;
	private Integer  unit;/*单位  integral 积分 ， money  元 */
	private List<CompanyMemberLevelDetail> list;
	private String method;/*请求的方法*/
	private Integer discountType;/*打折类型 0-免费  1-打折*/
	private Integer  becomeMember;/**成为会员的方式 0 购买  1  累计*/
	
	public Integer getUnit() {
		return unit;
	}

	public Integer getBecomeMember() {
		return becomeMember;
	}

	public void setBecomeMember(Integer becomeMember) {
		this.becomeMember = becomeMember;
	}

	public Integer getDiscountType() {
		return discountType;
	}

	public void setDiscountType(Integer discountType) {
		this.discountType = discountType;
	}

	public void setUnit(Integer unit) {
		this.unit = unit;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
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

	public String getListCmld() {
		return listCmld;
	}

	public void setListCmld(String listCmld) {
		this.listCmld = listCmld;
	}

	public List<CompanyMemberLevelDetail> getList() {
		return list;
	}

	public void setList(List<CompanyMemberLevelDetail> list) {
		this.list = list;
	}

}
