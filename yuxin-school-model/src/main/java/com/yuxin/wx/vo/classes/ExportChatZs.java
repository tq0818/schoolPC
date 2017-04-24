package com.yuxin.wx.vo.classes;

public class ExportChatZs {
	
	private Long	time;		/*聊天时间*/
	private String	sender;		/*发送者姓名*/
	private String	receiver;	/*接受者姓名*/
	private Long	senderId;	/*发送者id*/
	private	Long	receiverId;	/*接受者id*/
	private String	msg;		/*内容*/
	
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public Long getSenderId() {
		return senderId;
	}
	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}
	public Long getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public ExportChatZs() {
		super();
	}
	public ExportChatZs(Long time, String sender, String receiver,
			Long senderId, Long receiverId, String msg) {
		super();
		this.time = time;
		this.sender = sender;
		this.receiver = receiver;
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.msg = msg;
	}
}
