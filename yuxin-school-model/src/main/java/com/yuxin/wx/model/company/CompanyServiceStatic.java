package com.yuxin.wx.model.company;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyServiceStatic
 * 
 * @author chopin
 * @date 2015-4-23
 */
@SuppressWarnings("serial")
public class CompanyServiceStatic extends BaseEntity {
	
	
	private Integer	companyId;		 /* 公司表id */ 
	private Integer	liveConcurrent;		 /* 已使用直播最大并发数 */ 
	private Double	videoStorage;		 /* 已使用视频存储 */ 
	private Double	videoFlow;		 /* 已使用视频流量 */ 
	private Integer	faceStudent;		 /* 已招生面授学员数 */ 
	private Integer	messageSend;		 /* 已发送的短信数 */ 
	private Integer	emailSend;		 /* 已发送的邮件数 */ 
	private Integer	schoolNum;		 /* 已开通的分校数量 */ 
	private Integer onlineStudent;	/*在线版人数*/
	private String resourceStorage;
	private String resourceFlow;

	// Constructor
	public CompanyServiceStatic() {
	}
	
	/**
	 * full Constructor
	 */
	public CompanyServiceStatic(Integer id, Integer companyId, Integer liveConcurrent, Double videoStorage, Double videoFlow, Integer faceStudent, Integer messageSend, Integer emailSend, Integer schoolNum) {
		setId(id);
		this.companyId = companyId;
		this.liveConcurrent = liveConcurrent;
		this.videoStorage = videoStorage;
		this.videoFlow = videoFlow;
		this.faceStudent = faceStudent;
		this.messageSend = messageSend;
		this.emailSend = emailSend;
		this.schoolNum = schoolNum;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CompanyServiceStatic可以实现连缀设置属性
	
	public Integer getCompanyId() {
		return companyId;
	}

	public CompanyServiceStatic setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public Integer getLiveConcurrent() {
		return liveConcurrent;
	}

	public CompanyServiceStatic setLiveConcurrent(Integer liveConcurrent) {
		this.liveConcurrent = liveConcurrent;
		return this;
	}
	
	
	public Double getVideoStorage() {
		return videoStorage;
	}

	public CompanyServiceStatic setVideoStorage(Double videoStorage) {
		this.videoStorage = videoStorage;
		return this;
	}
	
	
	public Double getVideoFlow() {
		return videoFlow;
	}

	public CompanyServiceStatic setVideoFlow(Double videoFlow) {
		this.videoFlow = videoFlow;
		return this;
	}
	
	
	public Integer getFaceStudent() {
		return faceStudent;
	}

	public CompanyServiceStatic setFaceStudent(Integer faceStudent) {
		this.faceStudent = faceStudent;
		return this;
	}
	
	
	public Integer getMessageSend() {
		return messageSend;
	}

	public CompanyServiceStatic setMessageSend(Integer messageSend) {
		this.messageSend = messageSend;
		return this;
	}
	
	
	public Integer getEmailSend() {
		return emailSend;
	}

	public CompanyServiceStatic setEmailSend(Integer emailSend) {
		this.emailSend = emailSend;
		return this;
	}
	
	
	public Integer getSchoolNum() {
		return schoolNum;
	}

	public CompanyServiceStatic setSchoolNum(Integer schoolNum) {
		this.schoolNum = schoolNum;
		return this;
	}
	
	@Override
	public String toString() {
		return "CompanyServiceStatic [" + "id=" + getId() + ", companyId=" + companyId + ", liveConcurrent=" + liveConcurrent + ", videoStorage=" + videoStorage + ", videoFlow=" + videoFlow + ", faceStudent=" + faceStudent + ", messageSend=" + messageSend + ", emailSend=" + emailSend + ", schoolNum=" + schoolNum +  "]";
	}

	public Integer getOnlineStudent() {
		return onlineStudent;
	}

	public void setOnlineStudent(Integer onlineStudent) {
		this.onlineStudent = onlineStudent;
	}

	public String getResourceStorage() {
		return resourceStorage;
	}

	public void setResourceStorage(String resourceStorage) {
		this.resourceStorage = resourceStorage;
	}

	public String getResourceFlow() {
		return resourceFlow;
	}

	public void setResourceFlow(String resourceFlow) {
		this.resourceFlow = resourceFlow;
	}
}
