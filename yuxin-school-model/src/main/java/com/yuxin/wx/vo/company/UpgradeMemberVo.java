package com.yuxin.wx.vo.company;

/**
 * @ClassName: UpgradeMemberVo
 * @Description: 会员升级Vo
 * @author xukaiqiang
 * @date 2016年5月29日 下午5:38:36
 * @modifier
 * @modify-date 2016年5月29日 下午5:38:36
 * @version 1.0
 */
public class UpgradeMemberVo {
	private String leaveMoney;/* 剩余多少钱 */
	private String ownPrice;/* 欠缴多少钱 */
	private Integer memberId;/* 会员等级 */
	private Integer memberBuyLength;/* 有效期 */
	private String memberLevel;/* 会员等级名称 */
	private double payMoney;/* 支付金额 */
	private Integer usersFrontId;/* 用户编号 */
	private String payMethod;/* 支付方式 */
	private double originPrice;/*原始价格*/
	

	public double getOriginPrice() {
		return originPrice;
	}

	public void setOriginPrice(double originPrice) {
		this.originPrice = originPrice;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Integer getMemberBuyLength() {
		return memberBuyLength;
	}

	public void setMemberBuyLength(Integer memberBuyLength) {
		this.memberBuyLength = memberBuyLength;
	}

	public String getMemberLevel() {
		return memberLevel;
	}

	public void setMemberLevel(String memberLevel) {
		this.memberLevel = memberLevel;
	}

	public double getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(double payMoney) {
		this.payMoney = payMoney;
	}

	public Integer getUsersFrontId() {
		return usersFrontId;
	}

	public void setUsersFrontId(Integer usersFrontId) {
		this.usersFrontId = usersFrontId;
	}

	public UpgradeMemberVo() {
		super();
	}

	public UpgradeMemberVo(String leaveMoney, String ownPrice) {
		super();
		this.leaveMoney = leaveMoney;
		this.ownPrice = ownPrice;
	}

	public String getLeaveMoney() {
		return leaveMoney;
	}

	public void setLeaveMoney(String leaveMoney) {
		this.leaveMoney = leaveMoney;
	}

	public String getOwnPrice() {
		return ownPrice;
	}

	public void setOwnPrice(String ownPrice) {
		this.ownPrice = ownPrice;
	}
}
