package com.yuxin.wx.vo.company;



import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyMemberServiceTotalVo
 * 
 * @author zhang.zx
 * @date 2015-4-23
 */
@SuppressWarnings("serial")
public class CompanyMemberServiceTotalVo extends BaseEntity {
	
	private Integer messageLen;
	private Integer emailLen;
	private Integer liveConcurrentMaxLen;   /* 直播最大并发数 剩余*/ 
	private Double	videoStorageLen;			/* 视频存储空间大小剩余 */ 
	private Double	videoFlowLen;				/* 视频流量大小剩余 */ 
	private Integer	faceStudentAllLen;			/* 可招生的面授学员总数剩余*/ 
	private Integer	liveDateLen;			/* 直播服务有效期 */
	private Integer giveDateLen;           
	private Integer	videoDateLen;			/* 视频服务有效期*/
	private Integer giveVideoDateLen;
	private Integer memberService;          /* 会员服务有效期剩余*/
	
	private Integer liveConcurrentMax;     /* 直播最大并发数*/
	private Integer messageLenUsed;       /* 已发送短信条数*/
	private Integer emailLenUsed;         /* 已发送邮件数量*/
	private Double videoFlowLenUsed;      /* 已使用流量*/
	private Double videoStorageLenUsed;   /* 已使用视频空间*/
	private Integer faceStudentAllLenUsed; /* 已招学员数量*/
	
	
	//company表
	private String companyName;				/* 公司名称*/
	private String serviceName;				/* 公司所享受服务名称*/
	private Integer companyId;
	
	//user表
	private String realName;				/*公司联系人*/
	private String mobile;					/*联系人电话*/
	private String serviceVersion;
	
	public CompanyMemberServiceTotalVo() {
	
	}


	public Integer getMessageLen() {
		return messageLen;
	}

	public void setMessageLen(Integer messageLen) {
		this.messageLen = messageLen;
	}

	public Integer getEmailLen() {
		return emailLen;
	}

	public void setEmailLen(Integer emailLen) {
		this.emailLen = emailLen;
	}

	public Integer getLiveConcurrentMaxLen() {
		return liveConcurrentMaxLen;
	}

	public void setLiveConcurrentMaxLen(Integer liveConcurrentMaxLen) {
		this.liveConcurrentMaxLen = liveConcurrentMaxLen;
	}

	public Double getVideoStorageLen() {
		return videoStorageLen;
	}

	public void setVideoStorageLen(Double videoStorageLen) {
		this.videoStorageLen = videoStorageLen;
	}

	public Double getVideoFlowLen() {
		return videoFlowLen;
	}

	public void setVideoFlowLen(Double videoFlowLen) {
		this.videoFlowLen = videoFlowLen;
	}

	public Integer getFaceStudentAllLen() {
		return faceStudentAllLen;
	}

	public void setFaceStudentAllLen(Integer faceStudentAllLen) {
		this.faceStudentAllLen = faceStudentAllLen;
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

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
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

	public Integer getLiveConcurrentMax() {
		return liveConcurrentMax;
	}

	public void setLiveConcurrentMax(Integer liveConcurrentMax) {
		this.liveConcurrentMax = liveConcurrentMax;
	}

	public Integer getMessageLenUsed() {
		return messageLenUsed;
	}

	public void setMessageLenUsed(Integer messageLenUsed) {
		this.messageLenUsed = messageLenUsed;
	}

	public Integer getEmailLenUsed() {
		return emailLenUsed;
	}

	public void setEmailLenUsed(Integer emailLenUsed) {
		this.emailLenUsed = emailLenUsed;
	}

	public Double getVideoFlowLenUsed() {
		return videoFlowLenUsed;
	}

	public void setVideoFlowLenUsed(Double videoFlowLenUsed) {
		this.videoFlowLenUsed = videoFlowLenUsed;
	}

	public Double getVideoStorageLenUsed() {
		return videoStorageLenUsed;
	}

	public void setVideoStorageLenUsed(Double videoStorageLenUsed) {
		this.videoStorageLenUsed = videoStorageLenUsed;
	}

	public Integer getFaceStudentAllLenUsed() {
		return faceStudentAllLenUsed;
	}

	public void setFaceStudentAllLenUsed(Integer faceStudentAllLenUsed) {
		this.faceStudentAllLenUsed = faceStudentAllLenUsed;
	}


	public Integer getMemberService() {
		return memberService;
	}


	public void setMemberService(Integer memberService) {
		this.memberService = memberService;
	}


	public Integer getGiveDateLen() {
		return giveDateLen;
	}


	public void setGiveDateLen(Integer giveDateLen) {
		this.giveDateLen = giveDateLen;
	}


	public Integer getGiveVideoDateLen() {
		return giveVideoDateLen;
	}


	public void setGiveVideoDateLen(Integer giveVideoDateLen) {
		this.giveVideoDateLen = giveVideoDateLen;
	}


	public String getServiceVersion() {
		return serviceVersion;
	}


	public void setServiceVersion(String serviceVersion) {
		this.serviceVersion = serviceVersion;
	}
	
}
