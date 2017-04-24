package com.yuxin.wx.vo.common.email;

public class VerifyEmailYunduo extends MailBaseModel{
	
	private  String verifyUrl;
	private String companyName;
	private String userEmail;
	private String sendDate;
	

	/*@Override
	public String getModelJson() {
		// TODO 组成json格式
		return null;
	}*/

	public String getVerifyUrl() {
		return verifyUrl;
	}

	public void setVerifyUrl(String verifyUrl) {
		this.verifyUrl = verifyUrl;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	@Override
	public String toString() {
		return companyName+
				"亲爱的"+userEmail+":"+
				"感谢您申请注册"+companyName+"！请点击链接完成注册："+
				"点击验证账号正确性"+
				"如上述文字点击无效，请把下面网页地址复制到浏览器地址栏中打开："+
				verifyUrl+
				sendDate+
				"(本邮件由系统自动发出，请勿回复。)";
	}


	
	
}
