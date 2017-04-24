package com.yuxin.wx.vo.user;

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
public class UserHistoryVo extends BaseEntity {

	private Integer id;
	private Integer userId;
	private Integer commodityId; /* 商品id */
	private Integer classTypeId; /* 班型id */
	private Integer lectureId; /* 讲座id */
	private Date studyDate; /* 学习时间 */
	private Integer studyStatus; /* 学习状态（1：已完成；0：未完成） */
	private String commodityName; /* 商品名称 */
	private String coverUrl; /* 商品封面url */
	private String status; /* 商品上架状态（1：上架；0：未上架） */
	private String lableType; /* 班级授课类型：直播、面授、视频 */
	private Integer historyCount;/* 观看记录数量 */
	private Date todayDate;/* 当前日期 */
	private Date agoDate;/* 一周前日期 */
	private Integer faceFlag; /* 是否属于面授 */
	private Integer liveFlag; /* 是否属于直播 */
	private Integer videoFlag; /* 是否属于视频 */
	private Integer remoteFlag; /* 是否属于远程 */

	// Constructor
	public UserHistoryVo() {
	}

	/**
	 * full Constructor
	 */
	public UserHistoryVo(Integer id, Integer userId, Integer commodityId,
			Integer classTypeId, Integer lectureId, Date studyDate,
			Integer studyStatus) {
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

	public UserHistoryVo setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}

	public Integer getCommodityId() {
		return commodityId;
	}

	public UserHistoryVo setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
		return this;
	}

	public Integer getClassTypeId() {
		return classTypeId;
	}

	public UserHistoryVo setClassTypeId(Integer classTypeId) {
		this.classTypeId = classTypeId;
		return this;
	}

	public Integer getLectureId() {
		return lectureId;
	}

	public UserHistoryVo setLectureId(Integer lectureId) {
		this.lectureId = lectureId;
		return this;
	}

	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getStudyDate() {
		return studyDate;
	}

	public UserHistoryVo setStudyDate(Date studyDate) {
		this.studyDate = studyDate;
		return this;
	}

	public Integer getStudyStatus() {
		return studyStatus;
	}

	public UserHistoryVo setStudyStatus(Integer studyStatus) {
		this.studyStatus = studyStatus;
		return this;
	}

	@Override
	public String toString() {
		return "UserHistory [" + "id=" + getId() + ", userId=" + userId
				+ ", commodityId=" + commodityId + ", classTypeId="
				+ classTypeId + ", lectureId=" + lectureId + ", studyDate="
				+ studyDate + ", studyStatus=" + studyStatus + "]";
	}

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public String getCoverUrl() {
		return coverUrl;
	}

	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLableType() {
		return lableType;
	}

	public void setLableType(String lableType) {
		this.lableType = lableType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getHistoryCount() {
		return historyCount;
	}

	public void setHistoryCount(Integer historyCount) {
		this.historyCount = historyCount;
	}

	public Date getTodayDate() {
		return todayDate;
	}

	public void setTodayDate(Date todayDate) {
		this.todayDate = todayDate;
	}

	public Date getAgoDate() {
		return agoDate;
	}

	public void setAgoDate(Date agoDate) {
		this.agoDate = agoDate;
	}

	public Integer getFaceFlag() {
		return faceFlag;
	}

	public void setFaceFlag(Integer faceFlag) {
		this.faceFlag = faceFlag;
	}

	public Integer getLiveFlag() {
		return liveFlag;
	}

	public void setLiveFlag(Integer liveFlag) {
		this.liveFlag = liveFlag;
	}

	public Integer getVideoFlag() {
		return videoFlag;
	}

	public void setVideoFlag(Integer videoFlag) {
		this.videoFlag = videoFlag;
	}

	public Integer getRemoteFlag() {
		return remoteFlag;
	}

	public void setRemoteFlag(Integer remoteFlag) {
		this.remoteFlag = remoteFlag;
	}
}
