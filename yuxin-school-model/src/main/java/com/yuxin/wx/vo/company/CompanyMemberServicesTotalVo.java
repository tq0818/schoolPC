package com.yuxin.wx.vo.company;


import java.util.Date;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyMemberService
 * 
 * @author zhang.zx
 * @date 2015-4-23
 */
@SuppressWarnings("serial")
public class CompanyMemberServicesTotalVo extends BaseEntity {
	
	private Integer	liveConcurrentMax;		/* 直播最大并发数 */ 
	private Date	liveStartDate;			/* 直播服务开始时间 */ 
	private Date	liveEndDate;			/* 直播 结束时间 */
	private Double	videoStorage;			/* 视频存储空间大小 */ 
	private Double	videoFlow;				/* 视频流量大小 */ 
	private Date	videoStartDate;			/* 视频服务开始时间 */ 
	private Date	videoEndDate;			/* 视频结束时间*/
	private Integer	faceStudentAll;			/* 可招生的面授学员总数 */ 
	private Integer	messageTotal;			/* 可发送的短信总量 */ 
	private Integer	emailTotal;		 		/* 可发送的邮件总量 */ 
	private Integer	schoolTotal;			/* 可开通的分校总数 */ 
	private Integer	companyId;				/* 公司关联id*/
	
	private Integer giveLive;				/*赠送的直播并发数*/
	private Date giveLiveDate;				/*赠送的直播截止日期*/
	private Double giveVideoStorage;		/*赠送的视频空间大小*/
	private Date giveVideoStorageDate;		/*赠送的视频空间截止日期*/
	private Double giveVideoFlow;			/*赠送的视频流量大小*/
	private Integer giveMessage;			/*赠送的短信条数*/
	private Integer giveEmail;				/*赠送的邮件封数*/
	private Integer liveDateLen;
	private Integer videoDateLen;
	private String companyName;
	private Integer memberDateLen;
	private Integer giveLiveLen;
	private Integer giveVideoLen;
	
	private Integer resourceStorge;
	private Integer resourceFlow;
	private Double resourceStoragePrice;
	private Double resourceFlowPrice;
	
	private String serviceVersion;

	// Constructor
	public CompanyMemberServicesTotalVo() {
	}


	public Integer getLiveConcurrentMax() {
		return liveConcurrentMax;
	}

	public CompanyMemberServicesTotalVo setLiveConcurrentMax(Integer liveConcurrentMax) {
		this.liveConcurrentMax = liveConcurrentMax;
		return this;
	}

	public Date getLiveStartDate() {
		return liveStartDate;
	}

	public CompanyMemberServicesTotalVo setLiveStartDate(Date liveStartDate) {
		this.liveStartDate = liveStartDate;
		return this;
	}

	public Date getLiveEndDate() {
		return liveEndDate;
	}

	public CompanyMemberServicesTotalVo setLiveEndDate(Date liveEndDate) {
		this.liveEndDate = liveEndDate;
		return this;
	}

	public Double getVideoStorage() {
		return videoStorage;
	}


	public void setVideoStorage(Double videoStorage) {
		this.videoStorage = videoStorage;
	}


	public Double getVideoFlow() {
		return videoFlow;
	}

	public void setVideoFlow(Double videoFlow) {
		this.videoFlow = videoFlow;
	}

	public Date getVideoStartDate() {
		return videoStartDate;
	}

	public CompanyMemberServicesTotalVo setVideoStartDate(Date videoStartDate) {
		this.videoStartDate = videoStartDate;
		return this;
	}

	public Date getVideoEndDate() {
		return videoEndDate;
	}

	public CompanyMemberServicesTotalVo setVideoEndDate(Date videoEndDate) {
		this.videoEndDate = videoEndDate;
		return this;
	}

	public Integer getFaceStudentAll() {
		return faceStudentAll;
	}

	public CompanyMemberServicesTotalVo setFaceStudentAll(Integer faceStudentAll) {
		this.faceStudentAll = faceStudentAll;
		return this;
	}

	public Integer getMessageTotal() {
		return messageTotal;
	}

	public CompanyMemberServicesTotalVo setMessageTotal(Integer messageTotal) {
		this.messageTotal = messageTotal;
		return this;
	}

	public Integer getEmailTotal() {
		return emailTotal;
	}

	public CompanyMemberServicesTotalVo setEmailTotal(Integer emailTotal) {
		this.emailTotal = emailTotal;
		return this;
	}

	public Integer getSchoolTotal() {
		return schoolTotal;
	}

	public CompanyMemberServicesTotalVo setSchoolTotal(Integer schoolTotal) {
		this.schoolTotal = schoolTotal;
		return this;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getLiveDateLen() {
		return liveDateLen;
	}

	public void setLiveDateLen(Integer liveDateLen) {
		this.liveDateLen = liveDateLen;
	}

	public Integer getVideoDateLen() {
		return videoDateLen;
	}

	public void setVideoDateLen(Integer videoDateLen) {
		this.videoDateLen = videoDateLen;
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

	public Double getGiveVideoStorage() {
		return giveVideoStorage;
	}


	public void setGiveVideoStorage(Double giveVideoStorage) {
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

	public Double getGiveVideoFlow() {
		return giveVideoFlow;
	}

	public void setGiveVideoFlow(Double giveVideoFlow) {
		this.giveVideoFlow = giveVideoFlow;
	}


	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	public Integer getMemberDateLen() {
		return memberDateLen;
	}

	public void setMemberDateLen(Integer memberDateLen) {
		this.memberDateLen = memberDateLen;
	}


	public Integer getGiveLiveLen() {
		return giveLiveLen;
	}


	public void setGiveLiveLen(Integer giveLiveLen) {
		this.giveLiveLen = giveLiveLen;
	}


	public Integer getGiveVideoLen() {
		return giveVideoLen;
	}


	public void setGiveVideoLen(Integer giveVideoLen) {
		this.giveVideoLen = giveVideoLen;
	}


	public Integer getResourceStorge() {
		return resourceStorge;
	}


	public void setResourceStorge(Integer resourceStorge) {
		this.resourceStorge = resourceStorge;
	}


	public Integer getResourceFlow() {
		return resourceFlow;
	}


	public void setResourceFlow(Integer resourceFlow) {
		this.resourceFlow = resourceFlow;
	}


	public Double getResourceStoragePrice() {
		return resourceStoragePrice;
	}


	public void setResourceStoragePrice(Double resourceStoragePrice) {
		this.resourceStoragePrice = resourceStoragePrice;
	}


	public Double getResourceFlowPrice() {
		return resourceFlowPrice;
	}


	public void setResourceFlowPrice(Double resourceFlowPrice) {
		this.resourceFlowPrice = resourceFlowPrice;
	}


	public String getServiceVersion() {
		return serviceVersion;
	}


	public void setServiceVersion(String serviceVersion) {
		this.serviceVersion = serviceVersion;
	}
	
}
