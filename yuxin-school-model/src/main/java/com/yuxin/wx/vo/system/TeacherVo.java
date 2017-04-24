package com.yuxin.wx.vo.system;

public class TeacherVo {
	
	private String account;				/* 账号*/
	private String name;				/* 教室姓名*/
	private String nick;				/* 昵称*/
	private String headImg;				/* 头像地址*/
	private String signature;			/* 个性签名 */
	private String introduce;			/* 简介*/
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
	public TeacherVo() {
		super();
	}
	public TeacherVo(String account, String name, String nick, String headImg,
			String signature, String introduce) {
		super();
		this.account = account;
		this.name = name;
		this.nick = nick;
		this.headImg = headImg;
		this.signature = signature;
		this.introduce = introduce;
	}
}
