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
public class OrganLeaveMessageVo extends BaseEntity {
	
	private String	name;		 /* 姓名 */ 
	private String	mobile;		 /* 手机号 */ 
	private String	qq;		 /* QQ */ 
	private String	contents;		 /* 内容 */ 
	private Date  recordTime;  /* 留言时间 */
	private Integer timeLen;
	private String marks;
	
	private String startTime;
	private String endTime;
	
	
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
	public Date getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}
	public Integer getTimeLen() {
		return timeLen;
	}
	public void setTimeLen(Integer timeLen) {
		this.timeLen = timeLen;
	}
	public String getMarks() {
		return marks;
	}
	public void setMarks(String marks) {
		this.marks = marks;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	
}
