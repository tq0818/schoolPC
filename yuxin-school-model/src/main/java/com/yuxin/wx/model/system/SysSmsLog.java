package com.yuxin.wx.model.system;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:SysSmsLog
 * 
 * @author chopin
 * @date 2015-9-15
 */
@SuppressWarnings("serial")
public class SysSmsLog extends BaseEntity {
	
	
	private Integer	userId;		 /* 用户id */ 
	private String	ip;		
	private String	mobile;		 /* 手机号 */ 
	private String	businessType;		 /* 业务类型：register：注册  rspwd：重置密码 */ 
	private Date	sendTime;		 /* 发送时间 */ 
	private String	content;		 /* 发送内容 */ 
	private String	sendStatus;		 /* 发送结果 */ 
	private Integer companyId;		/* 公司id*/

	// Constructor
	public SysSmsLog() {
	}
	
	/**
	 * full Constructor
	 */
	public SysSmsLog(Integer id, Integer userId, String ip, String mobile, String businessType, Date sendTime, String content, String sendStatus) {
		setId(id);
		this.userId = userId;
		this.ip = ip;
		this.mobile = mobile;
		this.businessType = businessType;
		this.sendTime = sendTime;
		this.content = content;
		this.sendStatus = sendStatus;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为SysSmsLog可以实现连缀设置属性
	
	public Integer getUserId() {
		return userId;
	}

	public SysSmsLog setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}
	
	
	public String getIp() {
		return ip;
	}

	public SysSmsLog setIp(String ip) {
		this.ip = ip;
		return this;
	}
	
	
	public String getMobile() {
		return mobile;
	}

	public SysSmsLog setMobile(String mobile) {
		this.mobile = mobile;
		return this;
	}
	
	
	public String getBusinessType() {
		return businessType;
	}

	public SysSmsLog setBusinessType(String businessType) {
		this.businessType = businessType;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getSendTime() {
		return sendTime;
	}

	public SysSmsLog setSendTime(Date sendTime) {
		this.sendTime = sendTime;
		return this;
	}
	
	
	public String getContent() {
		return content;
	}

	public SysSmsLog setContent(String content) {
		this.content = content;
		return this;
	}
	
	
	public String getSendStatus() {
		return sendStatus;
	}

	public SysSmsLog setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
		return this;
	}
	
	@Override
	public String toString() {
		return "SysSmsLog [" + "id=" + getId() + ", userId=" + userId + ", ip=" + ip + ", mobile=" + mobile + ", businessType=" + businessType + ", sendTime=" + sendTime + ", content=" + content + ", sendStatus=" + sendStatus +  "]";
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
}
