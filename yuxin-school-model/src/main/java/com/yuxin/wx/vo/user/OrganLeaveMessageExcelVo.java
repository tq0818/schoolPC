package com.yuxin.wx.vo.user;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.util.ShortDateSerializer;
import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:Users
 * 
 * @author zhang.zx
 * @date 2015-5-9
 */
@SuppressWarnings("serial")
public class OrganLeaveMessageExcelVo extends BaseEntity {
	
	private String	name;		 /* 姓名 */ 
	private String	mobile;		 /* 手机号 */ 
	private String	qq;		 /* QQ */ 
	private String	contents;		 /* 内容 */ 
	private String  utmUrl;
	private String  recordTime;  /* 留言时间 */
	
	
	
	public String getUtmUrl() {
		return utmUrl;
	}
	public void setUtmUrl(String utmUrl) {
		this.utmUrl = utmUrl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}
}
