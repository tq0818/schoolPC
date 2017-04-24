package com.yuxin.wx.model.company;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyLiveStaticDayDetail
 * 
 * @author wang.zx
 * @date 2015-6-8
 */
@SuppressWarnings("serial")
public class CompanyLiveStaticDayDetail extends BaseEntity {
	
	
	private Integer	userId;		 /* 用户id */ 
	private Date	addTime;		 /* 添加时间 */ 
	private Date	addDate;		 /* 添加日期 */ 
	private Integer	liveMaxNum;		 /* 当时最大并发数 */ 
	private Integer	companyId;		 /* 公司id */ 

	// Constructor
	public CompanyLiveStaticDayDetail() {
	}
	
	/**
	 * full Constructor
	 */
	public CompanyLiveStaticDayDetail(Integer id, Integer userId, Date addTime, Date addDate, Integer liveMaxNum, Integer companyId) {
		setId(id);
		this.userId = userId;
		this.addTime = addTime;
		this.addDate = addDate;
		this.liveMaxNum = liveMaxNum;
		this.companyId = companyId;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CompanyLiveStaticDayDetail可以实现连缀设置属性
	
	public Integer getUserId() {
		return userId;
	}

	public CompanyLiveStaticDayDetail setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getAddTime() {
		return addTime;
	}

	public CompanyLiveStaticDayDetail setAddTime(Date addTime) {
		this.addTime = addTime;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getAddDate() {
		return addDate;
	}

	public CompanyLiveStaticDayDetail setAddDate(Date addDate) {
		this.addDate = addDate;
		return this;
	}
	
	
	public Integer getLiveMaxNum() {
		return liveMaxNum;
	}

	public CompanyLiveStaticDayDetail setLiveMaxNum(Integer liveMaxNum) {
		this.liveMaxNum = liveMaxNum;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public CompanyLiveStaticDayDetail setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	@Override
	public String toString() {
		return "CompanyLiveStaticDayDetail [" + "id=" + getId() + ", userId=" + userId + ", addTime=" + addTime + ", addDate=" + addDate + ", liveMaxNum=" + liveMaxNum + ", companyId=" + companyId +  "]";
	}
}
