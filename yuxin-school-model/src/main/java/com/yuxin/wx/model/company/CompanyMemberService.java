package com.yuxin.wx.model.company;


import java.util.Date;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyMemberService
 * 
 * @author chopin
 * @date 2015-4-23
 */
@SuppressWarnings("serial")
public class CompanyMemberService extends BaseEntity {
	
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
	
	private Integer giveLive;				/*赠送的直播并发数*/
	private Date giveLiveDate;				/*赠送的直播截止日期*/
	private Integer giveVideoStorage;		/*赠送的视频空间大小*/
	private Date giveVideoStorageDate;		/*赠送的视频空间截止日期*/
	private Integer giveVideoFlow;			/*赠送的视频流量大小*/
	private Integer giveMessage;			/*赠送的短信条数*/
	private Integer giveEmail;				/*赠送的邮件封数*/
	private String videoServiceProvider;    /*视频服务商：cc、qiniu*/
	private String liveServiceProvider;    	/*直播服务商：gs （E课堂）、zs （展示互动）*/
	private String liveServiceTemplate;		/*如果是E课堂直播的话， 选择模板（1. taobao 2. sooner ）*/

	private Double videoFlowPrice;		/*流量单价*/
	private Double videoStoragePrice;	/*空间单价*/
	private Double messagePrice;		/*短信单价*/
	private Double emailPrice;			/*邮件单价*/
	private Double liveConcurrentPrice;	/*并发单价*/
	private Double memberPrice;/*saas服务费*/
	private Integer zhuCompanyId;
	/*private Integer resourceStorge;
	private Integer resourceFlow;
	private Double resourceStoragePrice;
	private Double resourceFlowPrice;*/
	// Constructor
	public CompanyMemberService() {
	}
	
	/**
	 * full Constructor
	 */
	

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CompanyMemberService可以实现连缀设置属性

	public CompanyMemberService(Integer liveConcurrentMax,Integer giveEmail,Integer giveMessage,Integer giveVideoFlow,Date giveVideoStorageDate,Integer giveVideoStorage,Integer giveLive, Date giveLiveDate,Date liveStartDate,
			Date liveEndDate, Integer videoStorage, Integer videoFlow,
			Date videoStartDate, Date videoEndDate, Integer faceStudentAll,
			Integer messageTotal, Integer emailTotal, Integer schoolTotal,
			String companyId, String liveServiceTemplate) {
		super();
		this.giveEmail = giveEmail;
		this.giveMessage = giveMessage;
		this.giveVideoFlow = giveVideoFlow;
		this.giveVideoStorageDate = giveVideoStorageDate;
		this.giveVideoStorage = giveVideoStorage;
		this.giveLiveDate = giveLiveDate;
		this.giveLive = giveLive;
		this.liveConcurrentMax = liveConcurrentMax;
		this.liveStartDate = liveStartDate;
		this.liveEndDate = liveEndDate;
		this.videoStorage = videoStorage;
		this.videoFlow = videoFlow;
		this.videoStartDate = videoStartDate;
		this.videoEndDate = videoEndDate;
		this.faceStudentAll = faceStudentAll;
		this.messageTotal = messageTotal;
		this.emailTotal = emailTotal;
		this.schoolTotal = schoolTotal;
		this.companyId = companyId;
		this.liveServiceTemplate = liveServiceTemplate;
	}

	public Integer getLiveConcurrentMax() {
		return liveConcurrentMax;
	}

	public CompanyMemberService setLiveConcurrentMax(Integer liveConcurrentMax) {
		this.liveConcurrentMax = liveConcurrentMax;
		return this;
	}

	public Date getLiveStartDate() {
		return liveStartDate;
	}

	public CompanyMemberService setLiveStartDate(Date liveStartDate) {
		this.liveStartDate = liveStartDate;
		return this;
	}

	public Date getLiveEndDate() {
		return liveEndDate;
	}

	public CompanyMemberService setLiveEndDate(Date liveEndDate) {
		this.liveEndDate = liveEndDate;
		return this;
	}

	public Integer getVideoStorage() {
		return videoStorage;
	}

	public CompanyMemberService setVideoStorage(Integer videoStorage) {
		this.videoStorage = videoStorage;
		return this;
	}

	public Integer getVideoFlow() {
		return videoFlow;
	}

	public CompanyMemberService setVideoFlow(Integer videoFlow) {
		this.videoFlow = videoFlow;
		return this;
	}

	public Date getVideoStartDate() {
		return videoStartDate;
	}

	public CompanyMemberService setVideoStartDate(Date videoStartDate) {
		this.videoStartDate = videoStartDate;
		return this;
	}

	public Date getVideoEndDate() {
		return videoEndDate;
	}

	public CompanyMemberService setVideoEndDate(Date videoEndDate) {
		this.videoEndDate = videoEndDate;
		return this;
	}

	public Integer getFaceStudentAll() {
		return faceStudentAll;
	}

	public CompanyMemberService setFaceStudentAll(Integer faceStudentAll) {
		this.faceStudentAll = faceStudentAll;
		return this;
	}

	public Integer getMessageTotal() {
		return messageTotal;
	}

	public CompanyMemberService setMessageTotal(Integer messageTotal) {
		this.messageTotal = messageTotal;
		return this;
	}

	public Integer getEmailTotal() {
		return emailTotal;
	}

	public CompanyMemberService setEmailTotal(Integer emailTotal) {
		this.emailTotal = emailTotal;
		return this;
	}

	public Integer getSchoolTotal() {
		return schoolTotal;
	}

	public CompanyMemberService setSchoolTotal(Integer schoolTotal) {
		this.schoolTotal = schoolTotal;
		return this;
	}

	public String getCompanyId() {
		return companyId;
	}

	public CompanyMemberService setCompanyId(String companyId) {
		this.companyId = companyId;
		return this;
	}

	public Integer getGiveLive() {
		return giveLive;
	}

	public void setGiveLive(Integer giveLive) {
		this.giveLive = giveLive;
	}

	public Date getGiveLiveDate() {
		return giveLiveDate;
	}

	public void setGiveLiveDate(Date giveLiveDate) {
		this.giveLiveDate = giveLiveDate;
	}

	public Integer getGiveVideoStorage() {
		return giveVideoStorage;
	}

	public void setGiveVideoStorage(Integer giveVideoStorage) {
		this.giveVideoStorage = giveVideoStorage;
	}

	public Date getGiveVideoStorageDate() {
		return giveVideoStorageDate;
	}

	public void setGiveVideoStorageDate(Date giveVideoStorageDate) {
		this.giveVideoStorageDate = giveVideoStorageDate;
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

	public Integer getGiveVideoFlow() {
		return giveVideoFlow;
	}

	public void setGiveVideoFlow(Integer giveVideoFlow) {
		this.giveVideoFlow = giveVideoFlow;
	}

	public String getVideoServiceProvider() {
		return videoServiceProvider;
	}

	public void setVideoServiceProvider(String videoServiceProvider) {
		this.videoServiceProvider = videoServiceProvider;
	}

	public String getLiveServiceProvider() {
		return liveServiceProvider;
	}

	public void setLiveServiceProvider(String liveServiceProvider) {
		this.liveServiceProvider = liveServiceProvider;
	}

	public String getLiveServiceTemplate() {
		return liveServiceTemplate;
	}

	public void setLiveServiceTemplate(String liveServiceTemplate) {
		this.liveServiceTemplate = liveServiceTemplate;
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

	public Double getMemberPrice() {
		return memberPrice;
	}

	public void setMemberPrice(Double memberPrice) {
		this.memberPrice = memberPrice;
	}

	
    public Integer getZhuCompanyId() {
    	return zhuCompanyId;
    }

	
    public void setZhuCompanyId(Integer zhuCompanyId) {
    	this.zhuCompanyId = zhuCompanyId;
    }
	
}
