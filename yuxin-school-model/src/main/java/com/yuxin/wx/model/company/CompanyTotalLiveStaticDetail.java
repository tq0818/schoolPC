package com.yuxin.wx.model.company;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyTotalLiveStaticDetail
 * 
 * @author wang.zx
 * @date 2016-2-29
 */
@SuppressWarnings("serial")
public class CompanyTotalLiveStaticDetail extends BaseEntity {
	
	
	private Integer	userId;		 /* 用户ID */ 
	private Integer	companyId;		 /* 公司ID */ 
	private String	liveroomId;		 /* 课次Id */ 
	private Date	liveData;		 /* 直播日期 */ 
	private Date	liveDataTime;		 /* 用户上直播具体时间 */ 
	private Integer	liveNum;		 /* 总直播并发数 */ 
	private Integer	liveType;		 /* 展示互动： 0   E课堂：1 */ 

	// Constructor
	public CompanyTotalLiveStaticDetail() {
	}

	public CompanyTotalLiveStaticDetail(Integer userId, Integer companyId,
			String liveroomId, Date liveData, Date liveDataTime,
			Integer liveNum, Integer liveType) {
		super();
		this.userId = userId;
		this.companyId = companyId;
		this.liveroomId = liveroomId;
		this.liveData = liveData;
		this.liveDataTime = liveDataTime;
		this.liveNum = liveNum;
		this.liveType = liveType;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getLiveroomId() {
		return liveroomId;
	}

	public void setLiveroomId(String liveroomId) {
		this.liveroomId = liveroomId;
	}

	public Date getLiveData() {
		return liveData;
	}

	public void setLiveData(Date liveData) {
		this.liveData = liveData;
	}

	public Date getLiveDataTime() {
		return liveDataTime;
	}

	public void setLiveDataTime(Date liveDataTime) {
		this.liveDataTime = liveDataTime;
	}

	public Integer getLiveNum() {
		return liveNum;
	}

	public void setLiveNum(Integer liveNum) {
		this.liveNum = liveNum;
	}

	public Integer getLiveType() {
		return liveType;
	}

	public void setLiveType(Integer liveType) {
		this.liveType = liveType;
	}
}
