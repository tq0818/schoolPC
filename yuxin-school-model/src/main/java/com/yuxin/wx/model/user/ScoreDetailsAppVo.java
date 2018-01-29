package com.yuxin.wx.model.user;

import com.yuxin.wx.common.BaseEntity;

public class ScoreDetailsAppVo extends BaseEntity {
	
	/**
	 * 积分分数
	 */
	private String itemScore;
	/**
	 * 积分变更名目
	 */
	private String origin;
	/**
	 * 积分创建时间
	 */
	private String createTime;
	
	private String url;
	/**
	 * 修正分数
	 */
	private String fixedScore;
	/**
	 * 修正人员
	 */
	private String fixedPerson;
	/**
	 * 修正时间
	 */
	private String fixedTime;
	/**
	 * 总积分变更标识号
	 */
	private String totalScoreId;
	/**
	 * 是消耗还是增加，0减少 1增加
	 */
	private String reduceAddFlag;

	/**
	 * 是否来自APP的积分 1：是APP，0：是tob
	 */
	private Integer isApp;

	public Integer getIsApp() {
		return isApp;
	}

	public void setIsApp(Integer isApp) {
		this.isApp = isApp;
	}
	public String getItemScore() {
		return itemScore;
	}
	public void setItemScore(String itemScore) {
		this.itemScore = itemScore;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getFixedScore() {
		return fixedScore;
	}
	public void setFixedScore(String fixedScore) {
		this.fixedScore = fixedScore;
	}
	public String getFixedPerson() {
		return fixedPerson;
	}
	public void setFixedPerson(String fixedPerson) {
		this.fixedPerson = fixedPerson;
	}
	public String getFixedTime() {
		return fixedTime;
	}
	public void setFixedTime(String fixedTime) {
		this.fixedTime = fixedTime;
	}
	public String getTotalScoreId() {
		return totalScoreId;
	}
	public void setTotalScoreId(String totalScoreId) {
		this.totalScoreId = totalScoreId;
	}
	public String getReduceAddFlag() {
		return reduceAddFlag;
	}
	public void setReduceAddFlag(String reduceAddFlag) {
		this.reduceAddFlag = reduceAddFlag;
	}
	
	
}
