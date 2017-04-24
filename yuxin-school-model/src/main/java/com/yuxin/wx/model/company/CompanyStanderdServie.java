package com.yuxin.wx.model.company;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyStanderdServie
 * 
 * @author wang.zx
 * @date 2015-4-28
 */
@SuppressWarnings("serial")
public class CompanyStanderdServie extends BaseEntity {
	
	
	private String	serviceType;		 /* 服务类型：免费版、标准版、专业版、高级版、尊享版 */ 
	private String	serviceName;		 /* 服务名字：免费版、标准版、专业版、高级版、尊享版 */ 
	private Integer	giveVideoStorage;		 /* 赠送视频空间（GB/月） */ 
	private Integer	giveVideoFlow;		 /* 赠送流量（GB/月） */ 
	private Integer	faceStudentNum;		 /* 面授招生（累计招生人数） */ 
	private Integer	giveMessage;		 /* 短信开通赠送 */ 
	private Double	messagePrice;		 /* 短信服务（元/条） */ 
	private Integer	giveEmail;		 /* 邮件开通赠送 */ 
	private Double	emailPrice;		 /* 邮件服务（元/千封） */ 
	private Integer	crmSupport;		 /* 专业版CRM( -1代表无， 0代表免费试用，其他为具体价格) */ 
	private Integer	applySupport;		 /* 专业版报名平台( -1代表无， 0代表免费试用，其他为具体价格) */ 
	private Integer	operateSupport;		 /* 专业版教学运营( -1代表无， 0代表免费试用，其他为具体价格) */ 
	private Integer	dataSupport;		 /* 数据挖掘分析平台( -1代表无， 0代表免费试用，其他为具体价格) */ 
	private Integer	performance;		 /* 系统性能优化(0:不支持， 1：支持) */ 
	private Integer	selfDomain;		 /* 使用机构自己域名(0:不支持， 1：支持) */ 
	private Integer	selfCopyright;		 /* 机构自主版权信息(0:不支持， 1：支持) */ 
	private Integer	operationManual;		 /* 产品使用教程(0:不支持， 1：支持) */ 
	private Integer	dataBackup;		 /* 数据安全备份(0:不支持， 1：支持) */ 
	private Integer	dataMove;		 /* 数据库迁移导出(0:不支持， 1：支持) */ 
	private String	phoneSupport;		 /* 免费电话技术咨询 */ 
	private Integer	training;		 /* 使用培训(0:不支持， 1：支持) */ 
	private Integer	dataImoprtExport;		 /* 数据导入导出支持(0:不支持， 1：支持) */ 
	private Integer	cdnSupport;		 /* CDN加速(0:不支持， 1：支持) */ 
	private Integer	webMoreSupport;		 /* web负载均衡(0:不支持， 1：支持) */ 
	private Integer	liveRecord;		 /* 直播回放 */ 
	private Integer	liveRecordDownload;		 /* 直播录屏下载(0:不支持， 1：支持) */ 
	private Integer	videoEncrypt;		 /* 视频加密(0:不支持， 1：支持) */ 
	private Integer	videoLogo;		 /* 视频机构水印(0:不支持， 1：支持) */ 
	private Integer	servicePrice;		 /* 费用（元/年） */ 
	private Integer	serviceLevel;		 /* 服务级别，两位数字 */ 
	private Integer giveLive;			/* 赠送直播并发数*/
	private String serviceVersion;	/*版本*/
	
	/**
	 * 以下字段紧作为页面展示使用，与数据库表无任何关系
	 */
	private double spacePrice; /*空间不同服务级别最低价格 */
	private double flowPrice;  /*流量不同服务级别最低价格 */
	private double livePrice; /*直播不同服务级别最低价格 */

	// Constructor
	public CompanyStanderdServie() {
	}
	
	/**
	 * full Constructor
	 */
	public CompanyStanderdServie(Integer id, String serviceType, String serviceName, Integer giveVideoStorage, Integer giveVideoFlow, Integer faceStudentNum, Integer giveMessage, Double messagePrice, Integer giveEmail, Double emailPrice, Integer crmSupport, Integer applySupport, Integer operateSupport, Integer dataSupport, Integer performance, Integer selfDomain, Integer selfCopyright, Integer operationManual, Integer dataBackup, Integer dataMove, String phoneSupport, Integer training, Integer dataImoprtExport, Integer cdnSupport, Integer webMoreSupport, Integer liveRecord, Integer liveRecordDownload, Integer videoEncrypt, Integer videoLogo, Integer servicePrice, Integer serviceLevel) {
		setId(id);
		this.serviceType = serviceType;
		this.serviceName = serviceName;
		this.giveVideoStorage = giveVideoStorage;
		this.giveVideoFlow = giveVideoFlow;
		this.faceStudentNum = faceStudentNum;
		this.giveMessage = giveMessage;
		this.messagePrice = messagePrice;
		this.giveEmail = giveEmail;
		this.emailPrice = emailPrice;
		this.crmSupport = crmSupport;
		this.applySupport = applySupport;
		this.operateSupport = operateSupport;
		this.dataSupport = dataSupport;
		this.performance = performance;
		this.selfDomain = selfDomain;
		this.selfCopyright = selfCopyright;
		this.operationManual = operationManual;
		this.dataBackup = dataBackup;
		this.dataMove = dataMove;
		this.phoneSupport = phoneSupport;
		this.training = training;
		this.dataImoprtExport = dataImoprtExport;
		this.cdnSupport = cdnSupport;
		this.webMoreSupport = webMoreSupport;
		this.liveRecord = liveRecord;
		this.liveRecordDownload = liveRecordDownload;
		this.videoEncrypt = videoEncrypt;
		this.videoLogo = videoLogo;
		this.servicePrice = servicePrice;
		this.serviceLevel = serviceLevel;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CompanyStanderdServie可以实现连缀设置属性
	
	public String getServiceType() {
		return serviceType;
	}

	public CompanyStanderdServie setServiceType(String serviceType) {
		this.serviceType = serviceType;
		return this;
	}
	
	
	public String getServiceName() {
		return serviceName;
	}

	public CompanyStanderdServie setServiceName(String serviceName) {
		this.serviceName = serviceName;
		return this;
	}
	
	
	public Integer getGiveVideoStorage() {
		return giveVideoStorage;
	}

	public CompanyStanderdServie setGiveVideoStorage(Integer giveVideoStorage) {
		this.giveVideoStorage = giveVideoStorage;
		return this;
	}
	
	
	public Integer getGiveVideoFlow() {
		return giveVideoFlow;
	}

	public CompanyStanderdServie setGiveVideoFlow(Integer giveVideoFlow) {
		this.giveVideoFlow = giveVideoFlow;
		return this;
	}
	
	
	public Integer getFaceStudentNum() {
		return faceStudentNum;
	}

	public CompanyStanderdServie setFaceStudentNum(Integer faceStudentNum) {
		this.faceStudentNum = faceStudentNum;
		return this;
	}
	
	
	public Integer getGiveMessage() {
		return giveMessage;
	}

	public CompanyStanderdServie setGiveMessage(Integer giveMessage) {
		this.giveMessage = giveMessage;
		return this;
	}
	
	
	public Double getMessagePrice() {
		return messagePrice;
	}

	public CompanyStanderdServie setMessagePrice(Double messagePrice) {
		this.messagePrice = messagePrice;
		return this;
	}
	
	
	public Integer getGiveEmail() {
		return giveEmail;
	}

	public CompanyStanderdServie setGiveEmail(Integer giveEmail) {
		this.giveEmail = giveEmail;
		return this;
	}
	
	
	public Double getEmailPrice() {
		return emailPrice;
	}

	public CompanyStanderdServie setEmailPrice(Double emailPrice) {
		this.emailPrice = emailPrice;
		return this;
	}
	
	
	public Integer getCrmSupport() {
		return crmSupport;
	}

	public CompanyStanderdServie setCrmSupport(Integer crmSupport) {
		this.crmSupport = crmSupport;
		return this;
	}
	
	
	public Integer getApplySupport() {
		return applySupport;
	}

	public CompanyStanderdServie setApplySupport(Integer applySupport) {
		this.applySupport = applySupport;
		return this;
	}
	
	
	public Integer getOperateSupport() {
		return operateSupport;
	}

	public CompanyStanderdServie setOperateSupport(Integer operateSupport) {
		this.operateSupport = operateSupport;
		return this;
	}
	
	
	public Integer getDataSupport() {
		return dataSupport;
	}

	public CompanyStanderdServie setDataSupport(Integer dataSupport) {
		this.dataSupport = dataSupport;
		return this;
	}
	
	
	public Integer getPerformance() {
		return performance;
	}

	public CompanyStanderdServie setPerformance(Integer performance) {
		this.performance = performance;
		return this;
	}
	
	
	public Integer getSelfDomain() {
		return selfDomain;
	}

	public CompanyStanderdServie setSelfDomain(Integer selfDomain) {
		this.selfDomain = selfDomain;
		return this;
	}
	
	
	public Integer getSelfCopyright() {
		return selfCopyright;
	}

	public CompanyStanderdServie setSelfCopyright(Integer selfCopyright) {
		this.selfCopyright = selfCopyright;
		return this;
	}
	
	
	public Integer getOperationManual() {
		return operationManual;
	}

	public CompanyStanderdServie setOperationManual(Integer operationManual) {
		this.operationManual = operationManual;
		return this;
	}
	
	
	public Integer getDataBackup() {
		return dataBackup;
	}

	public CompanyStanderdServie setDataBackup(Integer dataBackup) {
		this.dataBackup = dataBackup;
		return this;
	}
	
	
	public Integer getDataMove() {
		return dataMove;
	}

	public CompanyStanderdServie setDataMove(Integer dataMove) {
		this.dataMove = dataMove;
		return this;
	}
	
	
	public String getPhoneSupport() {
		return phoneSupport;
	}

	public CompanyStanderdServie setPhoneSupport(String phoneSupport) {
		this.phoneSupport = phoneSupport;
		return this;
	}
	
	
	public Integer getTraining() {
		return training;
	}

	public CompanyStanderdServie setTraining(Integer training) {
		this.training = training;
		return this;
	}
	
	
	public Integer getDataImoprtExport() {
		return dataImoprtExport;
	}

	public CompanyStanderdServie setDataImoprtExport(Integer dataImoprtExport) {
		this.dataImoprtExport = dataImoprtExport;
		return this;
	}
	
	
	public Integer getCdnSupport() {
		return cdnSupport;
	}

	public CompanyStanderdServie setCdnSupport(Integer cdnSupport) {
		this.cdnSupport = cdnSupport;
		return this;
	}
	
	
	public Integer getWebMoreSupport() {
		return webMoreSupport;
	}

	public CompanyStanderdServie setWebMoreSupport(Integer webMoreSupport) {
		this.webMoreSupport = webMoreSupport;
		return this;
	}
	
	
	public Integer getLiveRecord() {
		return liveRecord;
	}

	public CompanyStanderdServie setLiveRecord(Integer liveRecord) {
		this.liveRecord = liveRecord;
		return this;
	}
	
	
	public Integer getLiveRecordDownload() {
		return liveRecordDownload;
	}

	public CompanyStanderdServie setLiveRecordDownload(Integer liveRecordDownload) {
		this.liveRecordDownload = liveRecordDownload;
		return this;
	}
	
	
	public Integer getVideoEncrypt() {
		return videoEncrypt;
	}

	public CompanyStanderdServie setVideoEncrypt(Integer videoEncrypt) {
		this.videoEncrypt = videoEncrypt;
		return this;
	}
	
	
	public Integer getVideoLogo() {
		return videoLogo;
	}

	public CompanyStanderdServie setVideoLogo(Integer videoLogo) {
		this.videoLogo = videoLogo;
		return this;
	}
	
	
	public Integer getServicePrice() {
		return servicePrice;
	}

	public CompanyStanderdServie setServicePrice(Integer servicePrice) {
		this.servicePrice = servicePrice;
		return this;
	}
	
	
	public Integer getServiceLevel() {
		return serviceLevel;
	}

	public CompanyStanderdServie setServiceLevel(Integer serviceLevel) {
		this.serviceLevel = serviceLevel;
		return this;
	}
	
	public double getSpacePrice() {
		return spacePrice;
	}

	public void setSpacePrice(double spacePrice) {
		this.spacePrice = spacePrice;
	}

	public double getFlowPrice() {
		return flowPrice;
	}

	public void setFlowPrice(double flowPrice) {
		this.flowPrice = flowPrice;
	}

	public double getLivePrice() {
		return livePrice;
	}

	public void setLivePrice(double livePrice) {
		this.livePrice = livePrice;
	}

	@Override
	public String toString() {
		return "CompanyStanderdServie [" + "id=" + getId() + ", serviceType=" + serviceType + ", serviceName=" + serviceName + ", giveVideoStorage=" + giveVideoStorage + ", giveVideoFlow=" + giveVideoFlow + ", faceStudentNum=" + faceStudentNum + ", giveMessage=" + giveMessage + ", messagePrice=" + messagePrice + ", giveEmail=" + giveEmail + ", emailPrice=" + emailPrice + ", crmSupport=" + crmSupport + ", applySupport=" + applySupport + ", operateSupport=" + operateSupport + ", dataSupport=" + dataSupport + ", performance=" + performance + ", selfDomain=" + selfDomain + ", selfCopyright=" + selfCopyright + ", operationManual=" + operationManual + ", dataBackup=" + dataBackup + ", dataMove=" + dataMove + ", phoneSupport=" + phoneSupport + ", training=" + training + ", dataImoprtExport=" + dataImoprtExport + ", cdnSupport=" + cdnSupport + ", webMoreSupport=" + webMoreSupport + ", liveRecord=" + liveRecord + ", liveRecordDownload=" + liveRecordDownload + ", videoEncrypt=" + videoEncrypt + ", videoLogo=" + videoLogo + ", servicePrice=" + servicePrice + ", serviceLevel=" + serviceLevel +  "]";
	}

	public Integer getGiveLive() {
		return giveLive;
	}

	public void setGiveLive(Integer giveLive) {
		this.giveLive = giveLive;
	}

	public String getServiceVersion() {
		return serviceVersion;
	}

	public void setServiceVersion(String serviceVersion) {
		this.serviceVersion = serviceVersion;
	}
}
