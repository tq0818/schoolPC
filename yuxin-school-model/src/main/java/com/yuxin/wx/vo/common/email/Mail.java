package com.yuxin.wx.vo.common.email;

public class Mail {
	
	//"verify_url","company_name","user_email","send_date"
	private String subject;		//发送邮件的主题
	private String from;	//发件人地址
	private String fromName;	//发件人名称

	private String to;	//群发邮件,收件人列表
	
	
	private String filePath;	//上传附件

	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	

	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	

	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	
}
