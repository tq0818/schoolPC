package com.yuxin.wx.model.user;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:UserHistory
 * 
 * @author chopin
 * @date 2015-3-12
 */
@SuppressWarnings("serial")
public class UserHistory extends BaseEntity {
	
	
	private Integer	userId;		
	private Integer	commodityId;		 /* 商品id */ 
	private Integer	classTypeId;		 /* 班型id */ 
	private Integer	lectureId;		 /* 讲座id */ 
	private Date	studyDate;		 /* 学习时间 */ 
	private Integer	studyStatus;		 /* 学习状态（1：未学习；2：未完成；3：已学习） */ 
	private Integer studyLength;	/*学习时长(秒)*/
	
	// Constructor
	public UserHistory() {
	}
	
	/**
	 * full Constructor
	 */
	public UserHistory(Integer id, Integer userId, Integer commodityId, Integer classTypeId, Integer lectureId, Date studyDate, Integer studyStatus) {
		setId(id);
		this.userId = userId;
		this.commodityId = commodityId;
		this.classTypeId = classTypeId;
		this.lectureId = lectureId;
		this.studyDate = studyDate;
		this.studyStatus = studyStatus;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为UserHistory可以实现连缀设置属性
	
	public Integer getUserId() {
		return userId;
	}

	public UserHistory setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}
	
	
	public Integer getCommodityId() {
		return commodityId;
	}

	public UserHistory setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
		return this;
	}
	
	
	public Integer getClassTypeId() {
		return classTypeId;
	}

	public UserHistory setClassTypeId(Integer classTypeId) {
		this.classTypeId = classTypeId;
		return this;
	}
	
	
	public Integer getLectureId() {
		return lectureId;
	}

	public UserHistory setLectureId(Integer lectureId) {
		this.lectureId = lectureId;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getStudyDate() {
		return studyDate;
	}

	public UserHistory setStudyDate(Date studyDate) {
		this.studyDate = studyDate;
		return this;
	}
	
	
	public Integer getStudyStatus() {
		return studyStatus;
	}

	public UserHistory setStudyStatus(Integer studyStatus) {
		this.studyStatus = studyStatus;
		return this;
	}
	
	@Override
	public String toString() {
		return "UserHistory [" + "id=" + getId() + ", userId=" + userId + ", commodityId=" + commodityId + ", classTypeId=" + classTypeId + ", lectureId=" + lectureId + ", studyDate=" + studyDate + ", studyStatus=" + studyStatus +  "]";
	}

	public Integer getStudyLength() {
		return studyLength;
	}

	public void setStudyLength(Integer studyLength) {
		this.studyLength = studyLength;
	}
}
