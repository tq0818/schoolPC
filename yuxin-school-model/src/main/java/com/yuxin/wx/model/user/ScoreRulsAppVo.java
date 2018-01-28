package com.yuxin.wx.model.user;

import com.yuxin.wx.common.BaseEntity;

/**
 * 积分规则model
 * @author zengdeqiang
 *
 */
public class ScoreRulsAppVo extends BaseEntity {
	/**
	 * 积分变更明目
	 */
	private String scoreTopic;
	/**
	 * 积分值
	 */
	private String score;
	/**
	 * 生效时间
	 */
	private String validTime;
	/**
	 * 失效时间
	 */
	private String invalidTime;
	/**
	 * 操作人员
	 */
	private String oprator;
	/**
	 * 状态
	 */
	private String ststus;
	
	private Integer bsstus;
	/**
	 * 更新时间
	 */
	private String updateTime;

	/**
	 * 排序
	 */
	private Integer sort;



	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getBsstus() {
		return bsstus;
	}
	public void setBsstus(Integer bsstus) {
		this.bsstus = bsstus;
	}
	public String getScoreTopic() {
		return scoreTopic;
	}
	public void setScoreTopic(String scoreTopic) {
		this.scoreTopic = scoreTopic;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getValidTime() {
		return validTime;
	}
	public void setValidTime(String validTime) {
		this.validTime = validTime;
	}
	public String getInvalidTime() {
		return invalidTime;
	}
	public void setInvalidTime(String invalidTime) {
		this.invalidTime = invalidTime;
	}
	public String getOprator() {
		return oprator;
	}
	public void setOprator(String oprator) {
		this.oprator = oprator;
	}
	public String getStstus() {
		return ststus;
	}
	public void setStstus(String ststus) {
		this.ststus = ststus;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	
}
