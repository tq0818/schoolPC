package com.yuxin.wx.utils;

import org.springframework.beans.factory.annotation.Value;

public class PropertiesUtil {
	
	private@Value("${mongodb.server.name}")
	String serverName;
	
	private @Value("${timertask.switch.convert.pdf}")
	String convertPDF;
	
	private @Value("${yunduoketang.qn.domain}")
	String qndomain;
	

	private @Value("${baofengyun.callbackdomain}")
	String bfyCallBackDomain;
	
	private @Value("${baofengyun.accessKey1}")
	String bfyAccessKey1;
	
	private @Value("${baofengyun.secretKey1}")
	String bfySecretkey1;
	
	private @Value("${baofengyun.accessKey2}")
	String bfyAccessKey2;
	
	private @Value("${baofengyun.secretKey2}")
	String bfySecretkey2;
	
	private @Value("${swf.upload.cmd.path}")
	String swfUploadCmdPath;
	
	private @Value("${timertask.switch.coupons}")
	String timerTaskSwitchCoupons;
	
	private @Value("${file.storage.path}")
	String fileStoragePath;

	private @Value("${timertask.switch.email}")
	String timerTaskSwitchEmail;
	
	private @Value("${timertask.switch.cmc}")
	String timerTaskSwitchCmc;
	
	private @Value("${timertask.switch.message}")
	String timerTaskSwitchMessage;
	
	private @Value("${timertask.switch.video}")
	String timerTaskSwitchVideo;

	private @Value("${timertask.switch.face}")
	String timerTaskSwitchFace;
	
	private @Value("${timertask.switch.live}")
	String timerTaskSwitchLive;
	
	private @Value("${timertask.switch.zsurl}")
	String timerTaskSwitchZsurl;

	private @Value("${yunduoketang.oss.imagedomain}")
	String  projectImageUrl;
	
	private @Value("${imageServicePath}")
	String imageServicePath;
	
	private @Value("${imageServiceRealPath}")
	String imageServiceRealPath;
	
	private @Value("${classTypePath}")
	String classTypePath;
	
	private @Value("${eketang.interface.setver.customer}")
	String customer;
	
	private @Value("${eketang.interface.setver.free.customer}")
	String freeCustomer;
	
	private @Value("${eketang.interface.server.address}")
	String interfaceAddress;
	
	private @Value("${eketang.interface.server.secretKey}")
	String secretKey;
	
	private @Value("${eketang.interface.server.free.secretKey}")
	String freeSecretKey;

	private @Value("${eketang.live.server.address}")
	String liveAddress;
	
	
	//项目根路径
	private @Value("${hostUrl}")
	String hostUrl;
	
	private @Value("${timertask.switch.service}") 
	String companyServiceMsg;
	
	//excle文件存放路径
	private @Value("${server.excle.tempPath}")
	String exclePath;
	
	private @Value("admin@yunduowxjy.com")
	String  zsLoginName;
	
	private @Value("http://yunduowxjy.gensee.com")
	String  zsAddress;
	
	private @Value("Yunduo2017")
	String  zsPassWord;
	
	private @Value("${eketang.live.login.server}")
	String eketangLogin;
	
	private @Value("${yunduoketang.ewm.image}")
	String companyEwmImage;
	
	private @Value("${yunduoketang.download.iosUrl}")
	String companyIosDownloadUrl;
	
	private @Value("${yunduoketang.download.androidUrl}")
	String companyAndoridDownloadUrl;
	
	private @Value("${timertask.switch.servicewarningsend}")
	String serviceWarningSend;
	
	public String getImageServicePath() {
		return imageServicePath;
	}

	public void setImageServicePath(String imageServicePath) {
		this.imageServicePath = imageServicePath;
	}

	public String getImageServiceRealPath() {
		return imageServiceRealPath;
	}

	public void setImageServiceRealPath(String imageServiceRealPath) {
		this.imageServiceRealPath = imageServiceRealPath;
	}

	public String getClassTypePath() {
		return classTypePath;
	}

	public void setClassTypePath(String classTypePath) {
		this.classTypePath = classTypePath;
	}

	public String getProjectImageUrl() {
		return projectImageUrl;
	}

	public void setProjectImageUrl(String projectImageUrl) {
		this.projectImageUrl = projectImageUrl;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getInterfaceAddress() {
		return interfaceAddress;
	}

	public void setInterfaceAddress(String interfaceAddress) {
		this.interfaceAddress = interfaceAddress;
	}

	public String getLiveAddress() {
		return liveAddress;
	}

	public void setLiveAddress(String liveAddress) {
		this.liveAddress = liveAddress;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getHostUrl() {
		return hostUrl;
	}

	public void setHostUrl(String hostUrl) {
		this.hostUrl = hostUrl;
	}

	public String getTimerTaskSwitchEmail() {
		return timerTaskSwitchEmail;
	}

	public void setTimerTaskSwitchEmail(String timerTaskSwitchEmail) {
		this.timerTaskSwitchEmail = timerTaskSwitchEmail;
	}

	public String getTimerTaskSwitchMessage() {
		return timerTaskSwitchMessage;
	}

	public void setTimerTaskSwitchMessage(String timerTaskSwitchMessage) {
		this.timerTaskSwitchMessage = timerTaskSwitchMessage;
	}

	public String getTimerTaskSwitchVideo() {
		return timerTaskSwitchVideo;
	}

	public void setTimerTaskSwitchVideo(String timerTaskSwitchVideo) {
		this.timerTaskSwitchVideo = timerTaskSwitchVideo;
	}

	public String getTimerTaskSwitchFace() {
		return timerTaskSwitchFace;
	}

	public void setTimerTaskSwitchFace(String timerTaskSwitchFace) {
		this.timerTaskSwitchFace = timerTaskSwitchFace;
	}

	public String getTimerTaskSwitchLive() {
		return timerTaskSwitchLive;
	}

	public void setTimerTaskSwitchLive(String timerTaskSwitchLive) {
		this.timerTaskSwitchLive = timerTaskSwitchLive;
	}

	public String getCompanyServiceMsg() {
		return companyServiceMsg;
	}

	public void setCompanyServiceMsg(String companyServiceMsg) {
		this.companyServiceMsg = companyServiceMsg;
	}

	public String getExclePath() {
		return exclePath;
	}

	public void setExclePath(String exclePath) {
		this.exclePath = exclePath;
	}

	public String getZsLoginName() {
		return zsLoginName;
	}

	public void setZsLoginName(String zsLoginName) {
		this.zsLoginName = zsLoginName;
	}

	public String getZsAddress() {
		return zsAddress;
	}

	public void setZsAddress(String zsAddress) {
		this.zsAddress = zsAddress;
	}

	public String getZsPassWord() {
		return zsPassWord;
	}

	public void setZsPassWord(String zsPassWord) {
		this.zsPassWord = zsPassWord;
	}

	public String getTimerTaskSwitchZsurl() {
		return timerTaskSwitchZsurl;
	}

	public void setTimerTaskSwitchZsurl(String timerTaskSwitchZsurl) {
		this.timerTaskSwitchZsurl = timerTaskSwitchZsurl;
	}

	public String getFileStoragePath() {
		return fileStoragePath;
	}

	public void setFileStoragePath(String fileStoragePath) {
		this.fileStoragePath = fileStoragePath;
	}

	public String getEketangLogin() {
		return eketangLogin;
	}

	public void setEketangLogin(String eketangLogin) {
		this.eketangLogin = eketangLogin;
	}

	public String getFreeCustomer() {
		return freeCustomer;
	}

	public void setFreeCustomer(String freeCustomer) {
		this.freeCustomer = freeCustomer;
	}

	public String getFreeSecretKey() {
		return freeSecretKey;
	}

	public void setFreeSecretKey(String freeSecretKey) {
		this.freeSecretKey = freeSecretKey;
	}

	public String getCompanyEwmImage() {
		return companyEwmImage;
	}

	public void setCompanyEwmImage(String companyEwmImage) {
		this.companyEwmImage = companyEwmImage;
	}

	public String getCompanyIosDownloadUrl() {
		return companyIosDownloadUrl;
	}

	public void setCompanyIosDownloadUrl(String companyIosDownloadUrl) {
		this.companyIosDownloadUrl = companyIosDownloadUrl;
	}

	public String getCompanyAndoridDownloadUrl() {
		return companyAndoridDownloadUrl;
	}

	public void setCompanyAndoridDownloadUrl(String companyAndoridDownloadUrl) {
		this.companyAndoridDownloadUrl = companyAndoridDownloadUrl;
	}

	public String getTimerTaskSwitchCmc() {
		return timerTaskSwitchCmc;
	}

	public void setTimerTaskSwitchCmc(String timerTaskSwitchCmc) {
		this.timerTaskSwitchCmc = timerTaskSwitchCmc;
	}

	public String getTimerTaskSwitchCoupons() {
		return timerTaskSwitchCoupons;
	}

	public void setTimerTaskSwitchCoupons(String timerTaskSwitchCoupons) {
		this.timerTaskSwitchCoupons = timerTaskSwitchCoupons;
	}

	public String getSwfUploadCmdPath() {
		return swfUploadCmdPath;
	}

	public void setSwfUploadCmdPath(String swfUploadCmdPath) {
		this.swfUploadCmdPath = swfUploadCmdPath;
	}

	public String getBfyAccessKey1() {
		return bfyAccessKey1;
	}

	public void setBfyAccessKey1(String bfyAccessKey1) {
		this.bfyAccessKey1 = bfyAccessKey1;
	}

	public String getBfySecretkey1() {
		return bfySecretkey1;
	}

	public void setBfySecretkey1(String bfySecretkey1) {
		this.bfySecretkey1 = bfySecretkey1;
	}

	public String getBfyAccessKey2() {
		return bfyAccessKey2;
	}

	public void setBfyAccessKey2(String bfyAccessKey2) {
		this.bfyAccessKey2 = bfyAccessKey2;
	}

	public String getBfySecretkey2() {
		return bfySecretkey2;
	}

	public void setBfySecretkey2(String bfySecretkey2) {
		this.bfySecretkey2 = bfySecretkey2;
	}

	public String getBfyCallBackDomain() {
		return bfyCallBackDomain;
	}

	public void setBfyCallBackDomain(String bfyCallBackDomain) {
		this.bfyCallBackDomain = bfyCallBackDomain;
	}

	public String getQndomain() {
		return qndomain;
	}

	public void setQndomain(String qndomain) {
		this.qndomain = qndomain;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getConvertPDF() {
		return convertPDF;
	}

	public void setConvertPDF(String convertPDF) {
		this.convertPDF = convertPDF;
	}

	public String getServiceWarningSend() {
		return serviceWarningSend;
	}

	public void setServiceWarningSend(String serviceWarningSend) {
		this.serviceWarningSend = serviceWarningSend;
	}
}
