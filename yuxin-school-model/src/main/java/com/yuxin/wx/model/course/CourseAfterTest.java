package com.yuxin.wx.model.course;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CourseAfterTest
 * 
 * @author wang.zx
 * @date 2016-9-6
 */
@SuppressWarnings("serial")
public class CourseAfterTest extends BaseEntity {
	
	
	private Integer	courseId;		 /* 课程ID */ 
	private Integer	chapterId;		 /* 章ID */ 
	private Integer	sort;		 /* 排序 */ 
	private Integer	testType;		 /* 测验类型 0-章节 1-试卷 */ 
	private String	testName;		 /* 测验名称 */ 
	private Integer	testScore;		 /* 测验合格率 */ 
	private Integer	delFlag;		 /* 删除标记 */ 
	private Integer	updater;		 /* 更新人 */ 
	private Date	updateTime;		 /* 更新时间 */ 
	private Integer	testTotalNum;		 /* 题目数 */ 
	private Integer	allowContinue;		 /* 是否允许未完成测验继续听课  0-不允许 1-允许 */ 
	private Integer	score;		 /* 得分 */ 
	private Integer	passFlag;		 /* 通过标记  0-未通过 1-通过 */ 
	private Integer type;
	

	// Constructor
	public CourseAfterTest() {
	}
	
	/**
	 * full Constructor
	 */
	public CourseAfterTest(Integer id, Integer courseId, Integer chapterId, Integer sort, Integer testType, String testName, Integer testScore, Integer delFlag, Integer updater, Date updateTime, Integer testTotalNum, Integer allowContinue, Integer score, Integer passFlag) {
		setId(id);
		this.courseId = courseId;
		this.chapterId = chapterId;
		this.sort = sort;
		this.testType = testType;
		this.testName = testName;
		this.testScore = testScore;
		this.delFlag = delFlag;
		this.updater = updater;
		this.updateTime = updateTime;
		this.testTotalNum = testTotalNum;
		this.allowContinue = allowContinue;
		this.score = score;
		this.passFlag = passFlag;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CourseAfterTest可以实现连缀设置属性
	
	
	public Integer getType() {
		return type;
	}

	public CourseAfterTest setType(Integer type) {
		this.type = type;
		return this;
	}
	
	
	public Integer getCourseId() {
		return courseId;
	}

	public CourseAfterTest setCourseId(Integer courseId) {
		this.courseId = courseId;
		return this;
	}
	
	
	public Integer getChapterId() {
		return chapterId;
	}

	public CourseAfterTest setChapterId(Integer chapterId) {
		this.chapterId = chapterId;
		return this;
	}
	
	
	public Integer getSort() {
		return sort;
	}

	public CourseAfterTest setSort(Integer sort) {
		this.sort = sort;
		return this;
	}
	
	
	public Integer getTestType() {
		return testType;
	}

	public CourseAfterTest setTestType(Integer testType) {
		this.testType = testType;
		return this;
	}
	
	
	public String getTestName() {
		return testName;
	}

	public CourseAfterTest setTestName(String testName) {
		this.testName = testName;
		return this;
	}
	
	
	public Integer getTestScore() {
		return testScore;
	}

	public CourseAfterTest setTestScore(Integer testScore) {
		this.testScore = testScore;
		return this;
	}
	
	
	public Integer getDelFlag() {
		return delFlag;
	}

	public CourseAfterTest setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
		return this;
	}
	
	
	public Integer getUpdater() {
		return updater;
	}

	public CourseAfterTest setUpdater(Integer updater) {
		this.updater = updater;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getUpdateTime() {
		return updateTime;
	}

	public CourseAfterTest setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	
	
	public Integer getTestTotalNum() {
		return testTotalNum;
	}

	public CourseAfterTest setTestTotalNum(Integer testTotalNum) {
		this.testTotalNum = testTotalNum;
		return this;
	}
	
	
	public Integer getAllowContinue() {
		return allowContinue;
	}

	public CourseAfterTest setAllowContinue(Integer allowContinue) {
		this.allowContinue = allowContinue;
		return this;
	}
	
	
	public Integer getScore() {
		return score;
	}

	public CourseAfterTest setScore(Integer score) {
		this.score = score;
		return this;
	}
	
	
	public Integer getPassFlag() {
		return passFlag;
	}

	public CourseAfterTest setPassFlag(Integer passFlag) {
		this.passFlag = passFlag;
		return this;
	}
	
	@Override
	public String toString() {
		return "CourseAfterTest [" + "id=" + getId() + ", courseId=" + courseId + ", chapterId=" + chapterId + ", sort=" + sort + ", testType=" + testType + ", testName=" + testName + ", testScore=" + testScore + ", delFlag=" + delFlag + ", updater=" + updater + ", updateTime=" + updateTime + ", testTotalNum=" + testTotalNum + ", allowContinue=" + allowContinue + ", score=" + score + ", passFlag=" + passFlag +  "]";
	}
}
