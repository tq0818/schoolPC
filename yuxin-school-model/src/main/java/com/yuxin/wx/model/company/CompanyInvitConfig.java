package com.yuxin.wx.model.company;



import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyInvitConfig
 * 
 * @author chopin
 * @date 2016-7-29
 */
@SuppressWarnings("serial")
public class CompanyInvitConfig extends BaseEntity {
	
	
	private String	usePriv;		 /* 使用权限 */ 
	private Integer	openFlag;		 /* 开启标记  0-未开启 1-开启 */ 
	private Integer	oneBeinviteFlag;		 /* 被邀请人获得奖励开关 */ 
	private Double	oneBeinviteGetMoney;		 /* 被邀请人注册成功后获得代金券 */ 
	private Integer	oneBeinviteMoneyPeriod;		 /* 代金券使用有效,单位天 */ 
	private Double	oneInviteRgstMoney;		 /* 被邀请人注册成功后邀请人获得代金奖励,单位元 */ 
	private Integer	oneInviteRgstIntegral;		 /* 被邀请人注册成功后邀请人获得积分奖励,单位分 */ 
	private Double	oneInviteCsptMoney;		 /* 被邀请人首次消费后邀请人获得代金奖励,单位元 */ 
	private Integer	oneInviteCsptIntegral;		 /* 被邀请人首次消费后邀请人获得积分奖励,单位分 */ 
	private Integer	oneInviteCsptPercent;		 /* 被邀请人首次消费后邀请人获得提成奖励,单位百分之 */ 
	private Double	twoInviteRgstMoney;		 /* 二级被邀请人注册成功后邀请人获得代金奖励,单位元 */ 
	private Integer	twoInviteRgstIntegral;		 /* 二级被邀请人注册成功后邀请人获得积分奖励,单位分 */ 
	private Double	twoInviteCsptMoney;		 /* 二级被邀请人首次消费后邀请人获得代金奖励,单位元 */ 
	private Integer	twoInviteCsptIntegral;		 /* 二级被邀请人首次消费后邀请人获得积分奖励,单位分 */ 
	private Integer	twoInviteCsptPercent;		 /* 二级被邀请人首次消费后邀请人获得提成奖励,单位百分之 */ 
	private Integer	twoInviteFlag;		 /* 二级邀请获得奖励开关 */ 
	private Integer companyId;

	// Constructor
	public CompanyInvitConfig() {
	}
	
	public CompanyInvitConfig(String usePriv, Integer openFlag, Integer oneBeinviteFlag, Double oneBeinviteGetMoney,
			Integer oneBeinviteMoneyPeriod, Double oneInviteRgstMoney, Integer oneInviteRgstIntegral,
			Double oneInviteCsptMoney, Integer oneInviteCsptIntegral, Integer oneInviteCsptPercent,
			Double twoInviteRgstMoney, Integer twoInviteRgstIntegral, Double twoInviteCsptMoney,
			Integer twoInviteCsptIntegral, Integer twoInviteCsptPercent, Integer twoInviteFlag, Integer companyId) {
		super();
		this.usePriv = usePriv;
		this.openFlag = openFlag;
		this.oneBeinviteFlag = oneBeinviteFlag;
		this.oneBeinviteGetMoney = oneBeinviteGetMoney;
		this.oneBeinviteMoneyPeriod = oneBeinviteMoneyPeriod;
		this.oneInviteRgstMoney = oneInviteRgstMoney;
		this.oneInviteRgstIntegral = oneInviteRgstIntegral;
		this.oneInviteCsptMoney = oneInviteCsptMoney;
		this.oneInviteCsptIntegral = oneInviteCsptIntegral;
		this.oneInviteCsptPercent = oneInviteCsptPercent;
		this.twoInviteRgstMoney = twoInviteRgstMoney;
		this.twoInviteRgstIntegral = twoInviteRgstIntegral;
		this.twoInviteCsptMoney = twoInviteCsptMoney;
		this.twoInviteCsptIntegral = twoInviteCsptIntegral;
		this.twoInviteCsptPercent = twoInviteCsptPercent;
		this.twoInviteFlag = twoInviteFlag;
		this.companyId = companyId;
	}

	public String getUsePriv() {
		return usePriv;
	}

	public void setUsePriv(String usePriv) {
		this.usePriv = usePriv;
	}



	public Integer getOpenFlag() {
		return openFlag;
	}



	public void setOpenFlag(Integer openFlag) {
		this.openFlag = openFlag;
	}



	public Integer getOneBeinviteFlag() {
		return oneBeinviteFlag;
	}



	public void setOneBeinviteFlag(Integer oneBeinviteFlag) {
		this.oneBeinviteFlag = oneBeinviteFlag;
	}



	public Double getOneBeinviteGetMoney() {
		return oneBeinviteGetMoney;
	}



	public void setOneBeinviteGetMoney(Double oneBeinviteGetMoney) {
		this.oneBeinviteGetMoney = oneBeinviteGetMoney;
	}



	public Integer getOneBeinviteMoneyPeriod() {
		return oneBeinviteMoneyPeriod;
	}



	public void setOneBeinviteMoneyPeriod(Integer oneBeinviteMoneyPeriod) {
		this.oneBeinviteMoneyPeriod = oneBeinviteMoneyPeriod;
	}



	public Double getOneInviteRgstMoney() {
		return oneInviteRgstMoney;
	}



	public void setOneInviteRgstMoney(Double oneInviteRgstMoney) {
		this.oneInviteRgstMoney = oneInviteRgstMoney;
	}



	public Integer getOneInviteRgstIntegral() {
		return oneInviteRgstIntegral;
	}



	public void setOneInviteRgstIntegral(Integer oneInviteRgstIntegral) {
		this.oneInviteRgstIntegral = oneInviteRgstIntegral;
	}



	public Double getOneInviteCsptMoney() {
		return oneInviteCsptMoney;
	}



	public void setOneInviteCsptMoney(Double oneInviteCsptMoney) {
		this.oneInviteCsptMoney = oneInviteCsptMoney;
	}



	public Integer getOneInviteCsptIntegral() {
		return oneInviteCsptIntegral;
	}



	public void setOneInviteCsptIntegral(Integer oneInviteCsptIntegral) {
		this.oneInviteCsptIntegral = oneInviteCsptIntegral;
	}



	public Integer getOneInviteCsptPercent() {
		return oneInviteCsptPercent;
	}



	public void setOneInviteCsptPercent(Integer oneInviteCsptPercent) {
		this.oneInviteCsptPercent = oneInviteCsptPercent;
	}



	public Double getTwoInviteRgstMoney() {
		return twoInviteRgstMoney;
	}



	public void setTwoInviteRgstMoney(Double twoInviteRgstMoney) {
		this.twoInviteRgstMoney = twoInviteRgstMoney;
	}



	public Integer getTwoInviteRgstIntegral() {
		return twoInviteRgstIntegral;
	}



	public void setTwoInviteRgstIntegral(Integer twoInviteRgstIntegral) {
		this.twoInviteRgstIntegral = twoInviteRgstIntegral;
	}



	public Double getTwoInviteCsptMoney() {
		return twoInviteCsptMoney;
	}



	public void setTwoInviteCsptMoney(Double twoInviteCsptMoney) {
		this.twoInviteCsptMoney = twoInviteCsptMoney;
	}



	public Integer getTwoInviteCsptIntegral() {
		return twoInviteCsptIntegral;
	}



	public void setTwoInviteCsptIntegral(Integer twoInviteCsptIntegral) {
		this.twoInviteCsptIntegral = twoInviteCsptIntegral;
	}



	public Integer getTwoInviteCsptPercent() {
		return twoInviteCsptPercent;
	}



	public void setTwoInviteCsptPercent(Integer twoInviteCsptPercent) {
		this.twoInviteCsptPercent = twoInviteCsptPercent;
	}



	public Integer getTwoInviteFlag() {
		return twoInviteFlag;
	}



	public void setTwoInviteFlag(Integer twoInviteFlag) {
		this.twoInviteFlag = twoInviteFlag;
	}



	public Integer getCompanyId() {
		return companyId;
	}



	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}



	@Override
	public String toString() {
		StringBuffer str=new StringBuffer();
		str.append("{");
		if(getId()!=null){
			str.append("\"id\":\"").append(getId()).append("\",");
		}
		if(openFlag!=null){
			str.append("\"openFlag\":\"").append(openFlag).append("\",");
		}
		if(oneBeinviteFlag!=null){
			str.append("\"oneBeinviteFlag\":\"").append(oneBeinviteFlag).append("\",");
		}
		if(oneBeinviteGetMoney!=null){
			str.append("\"oneBeinviteGetMoney\":\"").append(oneBeinviteGetMoney).append("\",");
		}
		if(oneInviteRgstMoney!=null){
			str.append("\"oneInviteRgstMoney\":\"").append(oneInviteRgstMoney).append("\",");
		}
		if(oneInviteRgstIntegral!=null){
			str.append("\"oneInviteRgstIntegral\":\"").append(oneInviteRgstIntegral).append("\",");
		}
		if(oneInviteCsptMoney!=null){
			str.append("\"oneInviteCsptMoney\":\"").append(oneInviteCsptMoney).append("\",");
		}
		if(oneInviteCsptIntegral!=null){
			str.append("\"oneInviteCsptIntegral\":\"").append(oneInviteCsptIntegral).append("\",");
		}
		if(oneInviteCsptPercent!=null){
			str.append("\"oneInviteCsptPercent\":\"").append(oneInviteCsptPercent).append("\",");
		}
		if(twoInviteRgstMoney!=null){
			str.append("\"twoInviteRgstMoney\":\"").append(twoInviteRgstMoney).append("\",");
		}
		if(twoInviteRgstIntegral!=null){
			str.append("\"twoInviteRgstIntegral\":\"").append(twoInviteRgstIntegral).append("\",");
		}
		if(twoInviteCsptMoney!=null){
			str.append("\"twoInviteCsptMoney\":\"").append(twoInviteCsptMoney).append("\",");
		}
		if(twoInviteCsptIntegral!=null){
			str.append("\"twoInviteCsptIntegral\":\"").append(twoInviteCsptIntegral).append("\",");
		}
		if(twoInviteCsptPercent!=null){
			str.append("\"twoInviteCsptPercent\":\"").append(twoInviteCsptPercent).append("\",");
		}
		if(twoInviteFlag!=null){
			str.append("\"twoInviteFlag\":\"").append(twoInviteFlag).append("\",");
		}
		return str.substring(0, str.length()-1).trim()+"}";
	}
}
