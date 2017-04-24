package com.yuxin.wx.model.company;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.util.ShortDateSerializer;

/**
 * POJO:CompanyServiceStaticDay
 *
 * @author wang.zx
 * @date 2015-5-21
 */
@SuppressWarnings("serial")
public class CompanyServiceStaticDay extends BaseEntity {

	private Integer companyId;
	private Date useDate; /* 使用日期 */
	private Integer liveNum; /* 直播最大并发数 */
	private Double videoPcFlow; /* pc端播放总流量 */
	private Double videoMobileFlow; /* 移动端播放总流量 */
	private Double videoTotalFlow; /* 视频总播放总流量 */
	private Double videoStorageNum; /* 视频占用总空间 */
	private Integer emailNum; /* 发送的邮件总数 */
	private Integer messageNum; /* 发送的短信总数 */

	private String startTime; /* 流量图表的起始日期 */
	private String endTime; /* 流量图表的结束日期 */

	private String resourceFlow;
	private String resourceStorageNum;

	private String searchType;

	// Constructor
	public CompanyServiceStaticDay() {
	}

	/**
	 * full Constructor
	 */
	public CompanyServiceStaticDay(Integer id, Integer companyId, Date useDate, Integer liveNum, Double videoPcFlow, Double videoMobileFlow,
	        Double videoTotalFlow, Double videoStorageNum, Integer emailNum, Integer messageNum) {
		setId(id);
		this.companyId = companyId;
		this.useDate = useDate;
		this.liveNum = liveNum;
		this.videoPcFlow = videoPcFlow;
		this.videoMobileFlow = videoMobileFlow;
		this.videoTotalFlow = videoTotalFlow;
		this.videoStorageNum = videoStorageNum;
		this.emailNum = emailNum;
		this.messageNum = messageNum;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CompanyServiceStaticDay可以实现连缀设置属性

	public Integer getCompanyId() {
		return companyId;
	}

	public CompanyServiceStaticDay setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}

	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getUseDate() {
		return useDate;
	}

	public CompanyServiceStaticDay setUseDate(Date useDate) {
		this.useDate = useDate;
		return this;
	}

	public Integer getLiveNum() {
		return liveNum;
	}

	public CompanyServiceStaticDay setLiveNum(Integer liveNum) {
		this.liveNum = liveNum;
		return this;
	}

	public Double getVideoPcFlow() {
		return videoPcFlow;
	}

	public CompanyServiceStaticDay setVideoPcFlow(Double videoPcFlow) {
		this.videoPcFlow = videoPcFlow;
		return this;
	}

	public Double getVideoMobileFlow() {
		return videoMobileFlow;
	}

	public CompanyServiceStaticDay setVideoMobileFlow(Double videoMobileFlow) {
		this.videoMobileFlow = videoMobileFlow;
		return this;
	}

	public Double getVideoTotalFlow() {
		return videoTotalFlow;
	}

	public CompanyServiceStaticDay setVideoTotalFlow(Double videoTotalFlow) {
		this.videoTotalFlow = videoTotalFlow;
		return this;
	}

	public Double getVideoStorageNum() {
		return videoStorageNum;
	}

	public CompanyServiceStaticDay setVideoStorageNum(Double videoStorageNum) {
		this.videoStorageNum = videoStorageNum;
		return this;
	}

	public Integer getEmailNum() {
		return emailNum;
	}

	public CompanyServiceStaticDay setEmailNum(Integer emailNum) {
		this.emailNum = emailNum;
		return this;
	}

	public Integer getMessageNum() {
		return messageNum;
	}

	public CompanyServiceStaticDay setMessageNum(Integer messageNum) {
		this.messageNum = messageNum;
		return this;
	}

	@Override
	public String toString() {
		return "CompanyServiceStaticDay [" + "id=" + getId() + ", companyId=" + companyId + ", useDate=" + useDate + ", liveNum=" + liveNum + ", videoPcFlow="
		        + videoPcFlow + ", videoMobileFlow=" + videoMobileFlow + ", videoTotalFlow=" + videoTotalFlow + ", videoStorageNum=" + videoStorageNum
		        + ", emailNum=" + emailNum + ", messageNum=" + messageNum + "]";
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

	public String getResourceFlow() {
		return resourceFlow;
	}

	public void setResourceFlow(String resourceFlow) {
		this.resourceFlow = resourceFlow;
	}

	public String getResourceStorageNum() {
		return resourceStorageNum;
	}

	public void setResourceStorageNum(String resourceStorageNum) {
		this.resourceStorageNum = resourceStorageNum;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
}
