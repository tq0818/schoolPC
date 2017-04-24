package com.yuxin.wx.model.course;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.model.tiku.TikuUserExercise;

/**
 * POJO:CourseAfterTestHis
 *
 * @author chopin
 * @date 2016-9-19
 */
@SuppressWarnings("serial")
public class CourseAfterTestHis extends BaseEntity {

	private Integer userId; /* 用户ID */
	private Integer testId; /* 测验ID */
	private Integer exerciseId; /* 练习ID */

	private TikuUserExercise tikuUserExercise;// 关联练习表

	// Constructor
	public CourseAfterTestHis() {
	}

	/**
	 * full Constructor
	 */
	public CourseAfterTestHis(Integer id, Integer userId, Integer testId, Integer exerciseId) {
		setId(id);
		this.userId = userId;
		this.testId = testId;
		this.exerciseId = exerciseId;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CourseAfterTestHis可以实现连缀设置属性

	public Integer getUserId() {
		return userId;
	}

	public CourseAfterTestHis setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}

	public Integer getTestId() {
		return testId;
	}

	public CourseAfterTestHis setTestId(Integer testId) {
		this.testId = testId;
		return this;
	}

	public Integer getExerciseId() {
		return exerciseId;
	}

	public CourseAfterTestHis setExerciseId(Integer exerciseId) {
		this.exerciseId = exerciseId;
		return this;
	}

	@Override
	public String toString() {
		return "CourseAfterTestHis [" + "id=" + getId() + ", userId=" + userId + ", testId=" + testId + ", exerciseId=" + exerciseId + "]";
	}

	public TikuUserExercise getTikuUserExercise() {
		return tikuUserExercise;
	}

	public void setTikuUserExercise(TikuUserExercise tikuUserExercise) {
		this.tikuUserExercise = tikuUserExercise;
	}
}
