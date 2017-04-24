package com.yuxin.wx.model.company;


import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.util.LongDateSerializer;
import com.yuxin.wx.util.ShortDateSerializer;

/**
 * POJO:OrganLeaveMessage
 * 
 * @author chopin
 * @date 2016-2-18
 */
@SuppressWarnings("serial")
public class OrganLeaveMessage extends BaseEntity {
	
	
	private String	name;		 /* 姓名 */ 
	private String	mobile;		 /* 手机号 */ 
	private String	qq;		 /* QQ */ 
	private String	contents;		 /* 内容 */ 
	private Date  recordTime;  /* 留言时间 */
	private String utmUrl;    /* 记录推广链接 */
	private String ip;    /* 记录推广链接 */

	

	// Constructor
	public OrganLeaveMessage() {
	}
	
	/**
	 * full Constructor
	 */
	public OrganLeaveMessage(Integer id, String name, String mobile, String qq, String contents,Date  recordTime,String utmUrl,String ip) {
		setId(id);
		this.name = name;
		this.mobile = mobile;
		this.qq = qq;
		this.contents = contents;
		this.recordTime = recordTime;
		this.utmUrl= utmUrl;
		this.ip= ip;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为OrganLeaveMessage可以实现连缀设置属性
	public String getIp() {
		return ip;
	}

	public OrganLeaveMessage setIp(String ip) {
		this.ip = ip;
		return this;
	}
	public String getUtmUrl() {
		return utmUrl;
	}

	public OrganLeaveMessage setUtmUrl(String utmUrl) {
		this.utmUrl = utmUrl;
		return this;
	}
	
	@JsonSerialize(using = LongDateSerializer.class)
	public Date getRecordTime() {
		return recordTime;
	}

	public OrganLeaveMessage setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
		return this;
	}

	public String getName() {
		return name;
	}

	public OrganLeaveMessage setName(String name) {
		this.name = name;
		return this;
	}
	
	
	public String getMobile() {
		return mobile;
	}

	public OrganLeaveMessage setMobile(String mobile) {
		this.mobile = mobile;
		return this;
	}
	
	
	public String getQq() {
		return qq;
	}

	public OrganLeaveMessage setQq(String qq) {
		this.qq = qq;
		return this;
	}
	
	
	public String getContents() {
		return contents;
	}

	public OrganLeaveMessage setContents(String contents) {
		this.contents = contents;
		return this;
	}
	
	@Override
	public String toString() {
		return "OrganLeaveMessage [" + "id=" + getId() + ", name=" + name + ", mobile=" + mobile + ", qq=" + qq + ", contents=" + contents + ", recordTime=" + recordTime +  ", utmUrl=" + utmUrl +", ip=" + ip +   "]";
	}
}
