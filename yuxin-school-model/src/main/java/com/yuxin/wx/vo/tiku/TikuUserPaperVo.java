package com.yuxin.wx.vo.tiku;

import com.yuxin.wx.common.BaseEntity;

@SuppressWarnings("serial")
public class TikuUserPaperVo extends BaseEntity {
	
	private Integer useTime;   //用户做题时长
	private Integer exerciseScore;    	//试卷得题分数
	private String status;				//做题状态
	private Integer totalTopic;			//总题数
	private Integer correctTopic;		//总做题数
	private String paperName; 			//试卷名称
	private String paperType;			//试卷状态
	private Integer examTime; 			//时间答题总时间
	private Integer totalScore;			//总分数
	private Integer currentNum;			//做到第几题
	private Integer exerid;				//
	private Integer userId;
	
	private String usetimes;		/*耗时 */
	
	public Integer getUseTime() {
		return useTime;
	}
	public void setUseTime(Integer useTime) {
		this.useTime = useTime;
	}
	public Integer getExerciseScore() {
		return exerciseScore;
	}
	public void setExerciseScore(Integer exerciseScore) {
		this.exerciseScore = exerciseScore;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getTotalTopic() {
		return totalTopic;
	}
	public void setTotalTopic(Integer totalTopic) {
		this.totalTopic = totalTopic;
	}
	public Integer getCorrectTopic() {
		return correctTopic;
	}
	public void setCorrectTopic(Integer correctTopic) {
		this.correctTopic = correctTopic;
	}
	public String getPaperName() {
		return paperName;
	}
	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}
	public String getPaperType() {
		return paperType;
	}
	public void setPaperType(String paperType) {
		this.paperType = paperType;
	}
	public Integer getExamTime() {
		return examTime;
	}
	public void setExamTime(Integer examTime) {
		this.examTime = examTime;
	}
	public Integer getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}
	public Integer getCurrentNum() {
		return currentNum;
	}
	public void setCurrentNum(Integer currentNum) {
		this.currentNum = currentNum;
	}
	public Integer getExerid() {
		return exerid;
	}
	public void setExerid(Integer exerid) {
		this.exerid = exerid;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUsetimes() {
		return usetimes;
	}
	public void setUsetimes(String usetimes) {
		this.usetimes = usetimes;
	}
	
}
