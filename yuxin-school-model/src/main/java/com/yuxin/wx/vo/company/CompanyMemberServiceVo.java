package com.yuxin.wx.vo.company;


import java.util.Date;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyMemberService
 * 
 * @author chopin
 * @date 2015-4-23
 */
@SuppressWarnings("serial")
public class CompanyMemberServiceVo extends BaseEntity {
	
	private Integer	liveConcurrentMax;		/* 直播最大并发数 */ 
	private Date	liveStartDate;			/* 直播服务开始时间 */ 
	private Date	liveEndDate;			/* 直播 结束时间 */
	private Integer	videoStorage;			/* 视频存储空间大小 */ 
	private Integer	videoFlow;				/* 视频流量大小 */ 
	private Date	videoStartDate;			/* 视频服务开始时间 */ 
	private Date	videoEndDate;			/* 视频结束时间*/
	private Integer	faceStudentAll;			/* 可招生的面授学员总数 */ 
	private Integer	messageTotal;			/* 可发送的短信总量 */ 
	private Integer	emailTotal;		 		/* 可发送的邮件总量 */ 
	private Integer	schoolTotal;			/* 可开通的分校总数 */ 
	private String	companyId;				/* 公司关联id*/
	private Integer giveLive;				/* 赠送的直播并发数*/
	private Integer giveVideoStorage;		/* 赠送的视频空间大小*/
	private Integer giveVideoFlow;			/* 赠送的视频流量大小*/
	private Integer giveMessage;			/* 赠送的短信条数*/
	private Integer giveEmail;				/* 赠送的邮件封数*/
	private Integer buyFlag;				/* 购买标记*/
	
	//company表
	private String companyName;				/* 公司名称*/
	private String serviceName;				/* 公司所享受服务名称*/
	private String companyLogo;				/* 公司LOGO*/
	private Date registTime;				/* 公司注册时间*/
	private Date memberEndDate;				/* 会员服务到期时间*/
	private Integer memberLevel; 			/* 会员级别:免费版,标准版,专业版,高级版,尊享版 */
	private String domain;					/* 公司域名*/

	private Double videoFlowPrice;		/*流量单价*/
	private Double videoStoragePrice;	/*空间单价*/
	private Double messagePrice;		/*短信单价*/
	private Double emailPrice;			/*邮件单价*/
	private Double liveConcurrentPrice;	/*并发单价*/
	
	private Integer giveLiveMounth;/*赠送直播月数*/
	private Integer giveVideoMounth;/*赠送录播月数*/
	
	//user表
	private String realName;				/*公司联系人*/
	private String mobile;					/*联系人电话*/
	
	//company_service_static表
	private Integer faceStudent;			/*已招面授人数*/
	private Integer messageSend;			/*已发送短信数*/
	private Integer emailSend;				/*已发送邮件数*/
	
	//company_authority表
	private Date auditTime;					/*审核时间*/
	
	//其他
	private Integer moreMessage;			/*剩余短信数*/
	private Integer moreEmail;				/*剩余邮件数*/
	private String reMark;/*备注*/
	private Double selPrice;/*售卖价格*/
	private Double servicePrice;/*服务价格*/
	private String playProvider;/*播放平台*/
	private Double memberPrice;/*saas服务费*/
	
	/*private Integer resourceStorge;
	private Integer resourceFlow;
	private Double resourceStoragePrice;
	private Double resourceFlowPrice;*/
	
	public Integer getLiveConcurrentMax() {
		return liveConcurrentMax;
	}
	public void setLiveConcurrentMax(Integer liveConcurrentMax) {
		this.liveConcurrentMax = liveConcurrentMax;
	}
	public Date getLiveStartDate() {
		return liveStartDate;
	}
	public void setLiveStartDate(Date liveStartDate) {
		this.liveStartDate = liveStartDate;
	}
	public Date getLiveEndDate() {
		return liveEndDate;
	}
	public void setLiveEndDate(Date liveEndDate) {
		this.liveEndDate = liveEndDate;
	}
	public Integer getVideoStorage() {
		return videoStorage;
	}
	public void setVideoStorage(Integer videoStorage) {
		this.videoStorage = videoStorage;
	}
	public Integer getVideoFlow() {
		return videoFlow;
	}
	public void setVideoFlow(Integer videoFlow) {
		this.videoFlow = videoFlow;
	}
	public Date getVideoStartDate() {
		return videoStartDate;
	}
	public void setVideoStartDate(Date videoStartDate) {
		this.videoStartDate = videoStartDate;
	}
	public Date getVideoEndDate() {
		return videoEndDate;
	}
	public void setVideoEndDate(Date videoEndDate) {
		this.videoEndDate = videoEndDate;
	}
	public Integer getFaceStudentAll() {
		return faceStudentAll;
	}
	public void setFaceStudentAll(Integer faceStudentAll) {
		this.faceStudentAll = faceStudentAll;
	}
	public Integer getMessageTotal() {
		return messageTotal;
	}
	public void setMessageTotal(Integer messageTotal) {
		this.messageTotal = messageTotal;
	}
	public Integer getEmailTotal() {
		return emailTotal;
	}
	public void setEmailTotal(Integer emailTotal) {
		this.emailTotal = emailTotal;
	}
	public Integer getSchoolTotal() {
		return schoolTotal;
	}
	public void setSchoolTotal(Integer schoolTotal) {
		this.schoolTotal = schoolTotal;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getFaceStudent() {
		return faceStudent;
	}
	public void setFaceStudent(Integer faceStudent) {
		this.faceStudent = faceStudent;
	}
	public Integer getMessageSend() {
		return messageSend;
	}
	public void setMessageSend(Integer messageSend) {
		this.messageSend = messageSend;
	}
	public Integer getEmailSend() {
		return emailSend;
	}
	public void setEmailSend(Integer emailSend) {
		this.emailSend = emailSend;
	}
	public Integer getMoreMessage() {
		if(this.messageTotal == null){
			this.messageTotal = 0;
		}
		if(this.messageSend == null){
			this.messageSend = 0;
		}
		return this.messageTotal - this.messageSend;
	}
	
	public Integer getMoreEmail() {
		if(this.emailTotal == null){
			this.emailTotal = 0;
		}
		if(this.emailSend == null){
			this.emailSend = 0;
		}
		return this.emailTotal - this.emailSend;
	}
	public String getCompanyLogo() {
		return companyLogo;
	}
	public void setCompanyLogo(String companyLogo) {
		this.companyLogo = companyLogo;
	}
	public Date getRegistTime() {
		return registTime;
	}
	public void setRegistTime(Date registTime) {
		this.registTime = registTime;
	}
	public Date getMemberEndDate() {
		return memberEndDate;
	}
	public void setMemberEndDate(Date memberEndDate) {
		this.memberEndDate = memberEndDate;
	}
	public Integer getMemberLevel() {
		return memberLevel;
	}
	public void setMemberLevel(Integer memberLevel) {
		this.memberLevel = memberLevel;
	}
	public Integer getGiveLive() {
		return giveLive;
	}
	public void setGiveLive(Integer giveLive) {
		this.giveLive = giveLive;
	}
	public Integer getGiveVideoStorage() {
		return giveVideoStorage;
	}
	public void setGiveVideoStorage(Integer giveVideoStorage) {
		this.giveVideoStorage = giveVideoStorage;
	}
	public Integer getGiveVideoFlow() {
		return giveVideoFlow;
	}
	public void setGiveVideoFlow(Integer giveVideoFlow) {
		this.giveVideoFlow = giveVideoFlow;
	}
	public Integer getGiveMessage() {
		return giveMessage;
	}
	public void setGiveMessage(Integer giveMessage) {
		this.giveMessage = giveMessage;
	}
	public Integer getGiveEmail() {
		return giveEmail;
	}
	public void setGiveEmail(Integer giveEmail) {
		this.giveEmail = giveEmail;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public Date getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	public Double getVideoFlowPrice() {
		return videoFlowPrice;
	}
	public void setVideoFlowPrice(Double videoFlowPrice) {
		this.videoFlowPrice = videoFlowPrice;
	}
	public Double getVideoStoragePrice() {
		return videoStoragePrice;
	}
	public void setVideoStoragePrice(Double videoStoragePrice) {
		this.videoStoragePrice = videoStoragePrice;
	}
	public Double getMessagePrice() {
		return messagePrice;
	}
	public void setMessagePrice(Double messagePrice) {
		this.messagePrice = messagePrice;
	}
	public Double getEmailPrice() {
		return emailPrice;
	}
	public void setEmailPrice(Double emailPrice) {
		this.emailPrice = emailPrice;
	}
	public Double getLiveConcurrentPrice() {
		return liveConcurrentPrice;
	}
	public void setLiveConcurrentPrice(Double liveConcurrentPrice) {
		this.liveConcurrentPrice = liveConcurrentPrice;
	}
	public void setMoreMessage(Integer moreMessage) {
		this.moreMessage = moreMessage;
	}
	public void setMoreEmail(Integer moreEmail) {
		this.moreEmail = moreEmail;
	}
	public Integer getBuyFlag() {
		return buyFlag;
	}
	public void setBuyFlag(Integer buyFlag) {
		this.buyFlag = buyFlag;
	}
	public Integer getGiveLiveMounth() {
		return giveLiveMounth;
	}
	public void setGiveLiveMounth(Integer giveLiveMounth) {
		this.giveLiveMounth = giveLiveMounth;
	}
	public Integer getGiveVideoMounth() {
		return giveVideoMounth;
	}
	public void setGiveVideoMounth(Integer giveVideoMounth) {
		this.giveVideoMounth = giveVideoMounth;
	}
	public String getReMark() {
		return reMark;
	}
	public void setReMark(String reMark) {
		this.reMark = reMark;
	}
	public Double getSelPrice() {
		return selPrice;
	}
	public void setSelPrice(Double selPrice) {
		this.selPrice = selPrice;
	}
	public String getPlayProvider() {
		return playProvider;
	}
	public void setPlayProvider(String playProvider) {
		this.playProvider = playProvider;
	}
	public Double getServicePrice() {
		return servicePrice;
	}
	public void setServicePrice(Double servicePrice) {
		this.servicePrice = servicePrice;
	}
	public Double getMemberPrice() {
		return memberPrice;
	}
	public void setMemberPrice(Double memberPrice) {
		this.memberPrice = memberPrice;
	}
}
