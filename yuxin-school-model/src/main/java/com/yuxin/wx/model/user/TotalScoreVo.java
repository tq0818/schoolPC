package com.yuxin.wx.model.user;

import com.yuxin.wx.common.BaseEntity;

import java.util.List;

@SuppressWarnings("serial")
public class TotalScoreVo extends BaseEntity {
	
	private String totalScoreId;
	/**
	 * 用户总积分
	 */
	private String totalScore;
	/**
	 * 用户总积分（冗余）
	 */
	private String totalScoreAll;
	/**
	 * 积分创建时间
	 */
	private String createTime;
	/**
	 * 积分更新时间
	 */
	private String updateTime;
	/**
	 * 用户标识号
	 */
	private String userId;
	/**
	 * 学员姓名
	 */
	private String stuName;
	/**
	 * 学生标识号
	 */
	private String stuId;
	
	
	
	public String getTotalScoreAll() {
		return totalScoreAll;
	}
	public void setTotalScoreAll(String totalScoreAll) {
		this.totalScoreAll = totalScoreAll;
	}
	public String getStuId() {
		return stuId;
	}
	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	/**
	 * 积分详细表
	 */
	private List<ScoreDetailsAppVo> scoreDetailsAppVos;
	
	public String getTotalScoreId() {
		return totalScoreId;
	}
	public void setTotalScoreId(String totalScoreId) {
		this.totalScoreId = totalScoreId;
	}
	public List<ScoreDetailsAppVo> getScoreDetailsAppVos() {
		return scoreDetailsAppVos;
	}
	public void setScoreDetailsAppVos(List<ScoreDetailsAppVo> scoreDetailsAppVos) {
		this.scoreDetailsAppVos = scoreDetailsAppVos;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(String totalScore) {
		this.totalScore = totalScore;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
