package com.yuxin.wx.vo.classes;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.util.ShortDateSerializer;
import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:ClassModuleLesson
 * @author wang.zx
 * @date 2014-12-5
 */
@SuppressWarnings("serial")
public class AllLiveCountModuleLessonVo extends BaseEntity {
	
	private Date	lessonDate;		 /* 上课日期 */ 
	private String	lessonTimeEnd;		 /* 课次结束日期 */ 
	private String	liveRoom;		 /* 直播教室id，直播课使用该字段 */ 
	private String	liveCompanyType;		 /* 使用的直播公司类型（光慧：gh；展视：zs） */ 
	private Integer companyId;
	private String	liveroomIdGh;	
	public Date getLessonDate() {
		return lessonDate;
	}
	public void setLessonDate(Date lessonDate) {
		this.lessonDate = lessonDate;
	}
	public String getLessonTimeEnd() {
		return lessonTimeEnd;
	}
	public void setLessonTimeEnd(String lessonTimeEnd) {
		this.lessonTimeEnd = lessonTimeEnd;
	}
	public String getLiveRoom() {
		return liveRoom;
	}
	public void setLiveRoom(String liveRoom) {
		this.liveRoom = liveRoom;
	}
	public String getLiveCompanyType() {
		return liveCompanyType;
	}
	public void setLiveCompanyType(String liveCompanyType) {
		this.liveCompanyType = liveCompanyType;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	
	public AllLiveCountModuleLessonVo() {
		super();
	}
	public AllLiveCountModuleLessonVo(Date lessonDate, String lessonTimeEnd,
			String liveRoom, String liveCompanyType, Integer companyId) {
		super();
		this.lessonDate = lessonDate;
		this.lessonTimeEnd = lessonTimeEnd;
		this.liveRoom = liveRoom;
		this.liveCompanyType = liveCompanyType;
		this.companyId = companyId;
	}
	public String getLiveroomIdGh() {
		return liveroomIdGh;
	}
	public void setLiveroomIdGh(String liveroomIdGh) {
		this.liveroomIdGh = liveroomIdGh;
	}
	
	
}