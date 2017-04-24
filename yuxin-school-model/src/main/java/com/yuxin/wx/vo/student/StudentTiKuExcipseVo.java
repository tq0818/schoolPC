package com.yuxin.wx.vo.student;

import com.yuxin.wx.common.BaseEntity;

public class StudentTiKuExcipseVo extends BaseEntity{
	private static final long serialVersionUID = 1L;
	/**
	 * 开始时间
	 */
	private String startTime;
	/**
	 * 题库名称
	 */
	private String tiKuName;
	/**
	 * 题库ID
	 */
	private String tiKuId;
	/**
	 * 科目ID
	 */
	private Integer subjectId;
	/**
	 * 科目
	 */
	private String subjectName;
	/**
	 * 类型名称
	 */
	private String exerciseType;
	/**
	 * 总题数
	 */
	private Integer totalTopic;
	/**
	 * 正确数
	 */
	private Integer rigCount;
	/**
	 * 错误数
	 */
	private Integer errorCount;
	private Integer userDo;
	/**
	 *	学生ID
	 */
	private Integer stuId;
	/**
	 * 版型ID
	 */
	private Integer classTypeId;
	/**
	 * 公司ID
	 */
	private Integer companyId;
	
	private Double exerciseScore;
	/**
	 * 练习的id
	 */
	private Integer exerciseId;
	
	
	public Double getExerciseScore() {
		return exerciseScore;
	}
	public void setExerciseScore(Double exerciseScore) {
		this.exerciseScore = exerciseScore;
	}
	public Integer getUserDo() {
		return userDo;
	}
	public void setUserDo(Integer userDo) {
		this.userDo = userDo;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getTiKuName() {
		return tiKuName;
	}
	public void setTiKuName(String tiKuName) {
		this.tiKuName = tiKuName;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getExerciseType() {
		return exerciseType;
	}
	public void setExerciseType(String exerciseType) {
		this.exerciseType = exerciseType;
	}
	public Integer getTotalTopic() {
		return totalTopic;
	}
	public void setTotalTopic(Integer totalTopic) {
		this.totalTopic = totalTopic;
	}
	public Integer getRigCount() {
		return rigCount;
	}
	public void setRigCount(Integer rigCount) {
		this.rigCount = rigCount;
	}
	public Integer getErrorCount() {
		return errorCount;
	}
	public void setErrorCount(Integer errorCount) {
		this.errorCount = errorCount;
	}
	public Integer getStuId() {
		return stuId;
	}
	public void setStuId(Integer stuId) {
		this.stuId = stuId;
	}
	public Integer getClassTypeId() {
		return classTypeId;
	}
	public void setClassTypeId(Integer classTypeId) {
		this.classTypeId = classTypeId;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getTiKuId() {
		return tiKuId;
	}
	public void setTiKuId(String tiKuId) {
		this.tiKuId = tiKuId;
	}
	public Integer getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}
	public Integer getExerciseId() {
		return exerciseId;
	}
	public void setExerciseId(Integer exerciseId) {
		this.exerciseId = exerciseId;
	}
	
}
