package com.yuxin.wx.vo.company;

import java.util.Date;

import com.yuxin.wx.common.BaseEntity;

public class CompanyStudentMessageVo extends BaseEntity {
	
	private String	title;		 /* 通知标题 */ 
	private String	content;		 /* 通知内容 */ 
	private Date	createTime;		 /* 操作时间 */
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
