package com.yuxin.wx.model.user;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:UserMessage
 * 
 * @author wang.zx
 * @date 2015-6-5
 */
@SuppressWarnings("serial")
public class UserMessage extends BaseEntity {
	
	
	private Integer	userId;		 /* 用户表id */ 
	private Integer	messageId;		 /* 学员通知表id */ 
	private Integer	readFlag;		 /* 阅读标记（1：已读；0：未读） */ 
	
	private String content;/*信息内容*/

	// Constructor
	public UserMessage() {
	}
	
	/**
	 * full Constructor
	 */
	public UserMessage(Integer id, Integer userId, Integer messageId, Integer readFlag) {
		setId(id);
		this.userId = userId;
		this.messageId = messageId;
		this.readFlag = readFlag;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为UserMessage可以实现连缀设置属性
	
	public Integer getUserId() {
		return userId;
	}

	public UserMessage setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}
	
	
	public Integer getMessageId() {
		return messageId;
	}

	public UserMessage setMessageId(Integer messageId) {
		this.messageId = messageId;
		return this;
	}
	
	
	public Integer getReadFlag() {
		return readFlag;
	}

	public UserMessage setReadFlag(Integer readFlag) {
		this.readFlag = readFlag;
		return this;
	}
	
	@Override
	public String toString() {
		return "UserMessage [" + "id=" + getId() + ", userId=" + userId + ", messageId=" + messageId + ", readFlag=" + readFlag +  "]";
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
