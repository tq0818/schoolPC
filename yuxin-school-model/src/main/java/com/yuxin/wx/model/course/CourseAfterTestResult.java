package com.yuxin.wx.model.course;



import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CourseAfterTestResult
 * 
 * @author chopin
 * @date 2016-9-19
 */
@SuppressWarnings("serial")
public class CourseAfterTestResult extends BaseEntity {
	
	
	private Integer	userId;		 /* 用户ID */ 
	private Integer	testId;		 /* 测验ID */ 
	private Integer	passFlag;		 /* 通过标记 */ 

	// Constructor
	public CourseAfterTestResult() {
	}
	
	/**
	 * full Constructor
	 */
	public CourseAfterTestResult(Integer id, Integer userId, Integer testId, Integer passFlag) {
		setId(id);
		this.userId = userId;
		this.testId = testId;
		this.passFlag = passFlag;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CourseAfterTestResult可以实现连缀设置属性
	
	public Integer getUserId() {
		return userId;
	}

	public CourseAfterTestResult setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}
	
	
	public Integer getTestId() {
		return testId;
	}

	public CourseAfterTestResult setTestId(Integer testId) {
		this.testId = testId;
		return this;
	}
	
	
	public Integer getPassFlag() {
		return passFlag;
	}

	public CourseAfterTestResult setPassFlag(Integer passFlag) {
		this.passFlag = passFlag;
		return this;
	}
	
	@Override
	public String toString() {
		return "CourseAfterTestResult [" + "id=" + getId() + ", userId=" + userId + ", testId=" + testId + ", passFlag=" + passFlag +  "]";
	}
}
