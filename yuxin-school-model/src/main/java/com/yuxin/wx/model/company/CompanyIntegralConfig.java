package com.yuxin.wx.model.company;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyIntegralConfig
 * 
 * @author chopin
 * @date 2016-5-24
 */
@SuppressWarnings("serial")
public class CompanyIntegralConfig extends BaseEntity {
	
	
	private String	name;		 /* 名称 */ 
	private String	description;		 /* 描述 */ 
	private String	ico;		 /* 图标 */ 
	private Integer	exchangeScale;		 /* 兑换比例 */ 
	private Integer	buyIntegralSwitch;		 /* 购买积分开关 */ 
	private Integer	getIntegralSwitch;		 /* 获取积分开关 */ 
	private Integer	registNum;		 /* 注册累积积分 */ 
	private Integer	loginNum;		 /* 登录累积积分 */ 
	private Integer	continueLoginNum;		 /* 连续登录累积积分 */ 
	private Integer	consumeNum;		 /* 消费累积积分 */ 
	private Integer	inviteNum;		 /* 邀请码累积积分 */ 
	private Integer	commentsNum;		 /* 评论累积积分 */ 
	private Integer	questionNum;		 /* 提问累积积分 */ 
	private Integer	answerNum;		 /* 回答累积积分 */ 
	private Integer	classCost;		 /* 课程使用积分开关 */ 
	private Integer	classCostMaxScale;		 /* 课程使用积分最大抵现 */ 
	private Integer	classPackageCost;		 /* 课程包使用积分开关 */ 
	private Integer	classPackageCostMaxScale;		 /* 课程包使用积分最大抵现 */ 
	private Integer	companyId;		 /* 公司ID */ 
	
	private Double	costMoreThen;		 /* 单笔消费大于*元 */ 
	private Integer	quesMaxNum;		 /* 每日提问最多赠送*积分 */ 
	private Integer	anseMaxNum;		 /* 回答每日最多赠送*积分 */ 
	private Integer	registFlag;		 /* 注册赠送积分标记，0-未开启 1-开启 */ 
	private Integer	loginFlag;		 /* 登录赠送积分标记  0-关闭 1-开启 */ 
	private Integer	consumeFlag;		 /* 单次消费累计积分标记 0-关闭 1-开启 */ 
	private Integer	inviteFlag;		 /* 邀请累计积分标记 0-关闭 1-开启 */ 
	private Integer	commentsFlag;		 /* 课程评论累计积分标记 0-关闭 1-开启 */ 
	private Integer	questionFlag;		 /* 提问累计积分标记 0-关闭 -1开启 */ 
	private Integer	answerFlag;		 /* 回答问题累计积分 0-关闭 1-开启 */ 

	// Constructor
	public CompanyIntegralConfig() {
	}
	
	/**
	 * full Constructor
	 */
	public CompanyIntegralConfig(Integer id, String name, String description, String ico, Integer exchangeScale, Integer buyIntegralSwitch, Integer getIntegralSwitch, Integer registNum, Integer loginNum, Integer continueLoginNum, Integer consumeNum, Integer InviteNum, Integer commentsNum, Integer questionNum, Integer answerNum, Integer classCost, Integer classCostMaxScale, Integer classPackageCost, Integer classPackageCostMaxScale, Integer companyId, Double costMoreThen, Integer quesMaxNum, Integer anseMaxNum, Integer registFlag, Integer loginFlag, Integer consumeFlag, Integer inviteFlag, Integer commentsFlag, Integer questionFlag, Integer answerFlag) {
		setId(id);
		this.name = name;
		this.description = description;
		this.ico = ico;
		this.exchangeScale = exchangeScale;
		this.buyIntegralSwitch = buyIntegralSwitch;
		this.getIntegralSwitch = getIntegralSwitch;
		this.registNum = registNum;
		this.loginNum = loginNum;
		this.continueLoginNum = continueLoginNum;
		this.consumeNum = consumeNum;
		this.inviteNum = InviteNum;
		this.commentsNum = commentsNum;
		this.questionNum = questionNum;
		this.answerNum = answerNum;
		this.classCost = classCost;
		this.classCostMaxScale = classCostMaxScale;
		this.classPackageCost = classPackageCost;
		this.classPackageCostMaxScale = classPackageCostMaxScale;
		this.companyId = companyId;
		this.costMoreThen = costMoreThen;
		this.quesMaxNum = quesMaxNum;
		this.anseMaxNum = anseMaxNum;
		this.registFlag = registFlag;
		this.loginFlag = loginFlag;
		this.consumeFlag = consumeFlag;
		this.inviteFlag = inviteFlag;
		this.commentsFlag = commentsFlag;
		this.questionFlag = questionFlag;
		this.answerFlag = answerFlag;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CompanyIntegralConfig可以实现连缀设置属性
	
	public String getName() {
		return name;
	}

	public CompanyIntegralConfig setName(String name) {
		this.name = name;
		return this;
	}
	
	
	public String getDescription() {
		return description;
	}

	public CompanyIntegralConfig setDescription(String description) {
		this.description = description;
		return this;
	}
	
	
	public String getIco() {
		return ico;
	}

	public CompanyIntegralConfig setIco(String ico) {
		this.ico = ico;
		return this;
	}
	
	
	public Integer getExchangeScale() {
		return exchangeScale;
	}

	public CompanyIntegralConfig setExchangeScale(Integer exchangeScale) {
		this.exchangeScale = exchangeScale;
		return this;
	}
	
	
	public Integer getBuyIntegralSwitch() {
		return buyIntegralSwitch;
	}

	public CompanyIntegralConfig setBuyIntegralSwitch(Integer buyIntegralSwitch) {
		this.buyIntegralSwitch = buyIntegralSwitch;
		return this;
	}
	
	
	public Integer getGetIntegralSwitch() {
		return getIntegralSwitch;
	}

	public CompanyIntegralConfig setGetIntegralSwitch(Integer getIntegralSwitch) {
		this.getIntegralSwitch = getIntegralSwitch;
		return this;
	}
	
	
	public Integer getRegistNum() {
		return registNum;
	}

	public CompanyIntegralConfig setRegistNum(Integer registNum) {
		this.registNum = registNum;
		return this;
	}
	
	
	public Integer getLoginNum() {
		return loginNum;
	}

	public CompanyIntegralConfig setLoginNum(Integer loginNum) {
		this.loginNum = loginNum;
		return this;
	}
	
	
	public Integer getContinueLoginNum() {
		return continueLoginNum;
	}

	public CompanyIntegralConfig setContinueLoginNum(Integer continueLoginNum) {
		this.continueLoginNum = continueLoginNum;
		return this;
	}
	
	
	public Integer getConsumeNum() {
		return consumeNum;
	}

	public CompanyIntegralConfig setConsumeNum(Integer consumeNum) {
		this.consumeNum = consumeNum;
		return this;
	}
	
	
	public Integer getInviteNum() {
		return inviteNum;
	}

	public CompanyIntegralConfig setInviteNum(Integer InviteNum) {
		this.inviteNum = InviteNum;
		return this;
	}
	
	
	public Integer getCommentsNum() {
		return commentsNum;
	}

	public CompanyIntegralConfig setCommentsNum(Integer commentsNum) {
		this.commentsNum = commentsNum;
		return this;
	}
	
	
	public Integer getQuestionNum() {
		return questionNum;
	}

	public CompanyIntegralConfig setQuestionNum(Integer questionNum) {
		this.questionNum = questionNum;
		return this;
	}
	
	
	public Integer getAnswerNum() {
		return answerNum;
	}

	public CompanyIntegralConfig setAnswerNum(Integer answerNum) {
		this.answerNum = answerNum;
		return this;
	}
	
	
	public Integer getClassCost() {
		return classCost;
	}

	public CompanyIntegralConfig setClassCost(Integer classCost) {
		this.classCost = classCost;
		return this;
	}
	
	
	public Integer getClassCostMaxScale() {
		return classCostMaxScale;
	}

	public CompanyIntegralConfig setClassCostMaxScale(Integer classCostMaxScale) {
		this.classCostMaxScale = classCostMaxScale;
		return this;
	}
	
	
	public Integer getClassPackageCost() {
		return classPackageCost;
	}

	public CompanyIntegralConfig setClassPackageCost(Integer classPackageCost) {
		this.classPackageCost = classPackageCost;
		return this;
	}
	
	
	public Integer getClassPackageCostMaxScale() {
		return classPackageCostMaxScale;
	}

	public CompanyIntegralConfig setClassPackageCostMaxScale(Integer classPackageCostMaxScale) {
		this.classPackageCostMaxScale = classPackageCostMaxScale;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public CompanyIntegralConfig setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public Double getCostMoreThen() {
		return costMoreThen;
	}

	public CompanyIntegralConfig setCostMoreThen(Double costMoreThen) {
		this.costMoreThen = costMoreThen;
		return this;
	}
	
	
	public Integer getQuesMaxNum() {
		return quesMaxNum;
	}

	public CompanyIntegralConfig setQuesMaxNum(Integer quesMaxNum) {
		this.quesMaxNum = quesMaxNum;
		return this;
	}
	
	
	public Integer getAnseMaxNum() {
		return anseMaxNum;
	}

	public CompanyIntegralConfig setAnseMaxNum(Integer anseMaxNum) {
		this.anseMaxNum = anseMaxNum;
		return this;
	}
	
	
	public Integer getRegistFlag() {
		return registFlag;
	}

	public CompanyIntegralConfig setRegistFlag(Integer registFlag) {
		this.registFlag = registFlag;
		return this;
	}
	
	
	public Integer getLoginFlag() {
		return loginFlag;
	}

	public CompanyIntegralConfig setLoginFlag(Integer loginFlag) {
		this.loginFlag = loginFlag;
		return this;
	}
	
	
	public Integer getConsumeFlag() {
		return consumeFlag;
	}

	public CompanyIntegralConfig setConsumeFlag(Integer consumeFlag) {
		this.consumeFlag = consumeFlag;
		return this;
	}
	
	
	public Integer getInviteFlag() {
		return inviteFlag;
	}

	public CompanyIntegralConfig setInviteFlag(Integer inviteFlag) {
		this.inviteFlag = inviteFlag;
		return this;
	}
	
	
	public Integer getCommentsFlag() {
		return commentsFlag;
	}

	public CompanyIntegralConfig setCommentsFlag(Integer commentsFlag) {
		this.commentsFlag = commentsFlag;
		return this;
	}
	
	
	public Integer getQuestionFlag() {
		return questionFlag;
	}

	public CompanyIntegralConfig setQuestionFlag(Integer questionFlag) {
		this.questionFlag = questionFlag;
		return this;
	}
	
	
	public Integer getAnswerFlag() {
		return answerFlag;
	}

	public CompanyIntegralConfig setAnswerFlag(Integer answerFlag) {
		this.answerFlag = answerFlag;
		return this;
	}
	
	@Override
	public String toString() {
		return "CompanyIntegralConfig [" + "id=" + getId() + ", name=" + name + ", description=" + description + ", ico=" + ico + ", exchangeScale=" + exchangeScale + ", buyIntegralSwitch=" + buyIntegralSwitch + ", getIntegralSwitch=" + getIntegralSwitch + ", registNum=" + registNum + ", loginNum=" + loginNum + ", continueLoginNum=" + continueLoginNum + ", consumeNum=" + consumeNum + ", InviteNum=" + inviteNum + ", commentsNum=" + commentsNum + ", questionNum=" + questionNum + ", answerNum=" + answerNum + ", classCost=" + classCost + ", classCostMaxScale=" + classCostMaxScale + ", classPackageCost=" + classPackageCost + ", classPackageCostMaxScale=" + classPackageCostMaxScale + ", companyId=" + companyId + ", costMoreThen=" + costMoreThen + ", quesMaxNum=" + quesMaxNum + ", anseMaxNum=" + anseMaxNum + ", registFlag=" + registFlag + ", loginFlag=" + loginFlag + ", consumeFlag=" + consumeFlag + ", inviteFlag=" + inviteFlag + ", commentsFlag=" + commentsFlag + ", questionFlag=" + questionFlag + ", answerFlag=" + answerFlag +  "]";
	}
}
