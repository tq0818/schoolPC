package com.yuxin.wx.model.company;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyFeedback
 * 
 * @author chopin
 * @date 2015-7-15
 */
@SuppressWarnings("serial")
public class CompanyFeedback extends BaseEntity {
	
	
	private Integer	companyId;		
	private String	content;		 /* 反馈内容 */ 
	private Date	addTime;		 /* 反馈时间 */ 
	private String	contactType;		 /* 联系方式 */ 
	private Integer	userId;		 /* 用户id */ 

	// Constructor
	public CompanyFeedback() {
	}
	
	/**
	 * full Constructor
	 */
	public CompanyFeedback(Integer id, Integer companyId, String content, Date addTime, String contactType, Integer userId) {
		setId(id);
		this.companyId = companyId;
		this.content = content;
		this.addTime = addTime;
		this.contactType = contactType;
		this.userId = userId;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CompanyFeedback可以实现连缀设置属性
	
	public Integer getCompanyId() {
		return companyId;
	}

	public CompanyFeedback setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public String getContent() {
		return content;
	}

	public CompanyFeedback setContent(String content) {
		this.content = content;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getAddTime() {
		return addTime;
	}

	public CompanyFeedback setAddTime(Date addTime) {
		this.addTime = addTime;
		return this;
	}
	
	
	public String getContactType() {
		return contactType;
	}

	public CompanyFeedback setContactType(String contactType) {
		this.contactType = contactType;
		return this;
	}
	
	
	public Integer getUserId() {
		return userId;
	}

	public CompanyFeedback setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}
	
	@Override
	public String toString() {
		return "CompanyFeedback [" + "id=" + getId() + ", companyId=" + companyId + ", content=" + content + ", addTime=" + addTime + ", contactType=" + contactType + ", userId=" + userId +  "]";
	}
}
