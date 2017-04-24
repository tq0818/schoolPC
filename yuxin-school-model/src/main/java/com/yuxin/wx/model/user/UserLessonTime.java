package com.yuxin.wx.model.user;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:UserLessonTime
 * 
 * @author wang.zx
 * @date 2015-9-25
 */
@SuppressWarnings("serial")
public class UserLessonTime extends BaseEntity {
	
	
	private Integer	userId;		
	private String	lessonType;		
	private Integer	lessonId;		
	private Integer	studyNum;		

	// Constructor
	public UserLessonTime() {
	}
	
	/**
	 * full Constructor
	 */
	public UserLessonTime(Integer id, Integer userId, String lessonType, Integer lessonId, Integer studyNum) {
		setId(id);
		this.userId = userId;
		this.lessonType = lessonType;
		this.lessonId = lessonId;
		this.studyNum = studyNum;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为UserLessonTime可以实现连缀设置属性
	
	public Integer getUserId() {
		return userId;
	}

	public UserLessonTime setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}
	
	
	public String getLessonType() {
		return lessonType;
	}

	public UserLessonTime setLessonType(String lessonType) {
		this.lessonType = lessonType;
		return this;
	}
	
	
	public Integer getLessonId() {
		return lessonId;
	}

	public UserLessonTime setLessonId(Integer lessonId) {
		this.lessonId = lessonId;
		return this;
	}
	
	
	public Integer getStudyNum() {
		return studyNum;
	}

	public UserLessonTime setStudyNum(Integer studyNum) {
		this.studyNum = studyNum;
		return this;
	}
	
	@Override
	public String toString() {
		return "UserLessonTime [" + "id=" + getId() + ", userId=" + userId + ", lessonType=" + lessonType + ", lessonId=" + lessonId + ", studyNum=" + studyNum +  "]";
	}
}
