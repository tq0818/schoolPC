package com.yuxin.wx.vo.classes;

import java.io.Serializable;

public class ClassLessonVO implements Serializable {
	/*
	 * spl.`class_type_id`, spl.`commodity_id`, spl.`id`, spl.`lecture_id`,
	 * spl.len, spl.`user_id`, ct.`item_third_code`, cvl.`lecture_name`,
	 * v.`video_time`, st.`name`, st.`edu_year`, st.`edu_class`, st.`edu_step`,
	 * st.`is_in_school`, ct.`origin_type`
	 */

	private Integer classTypeId;
	// private Integer commodityId;
	private Integer id;
	private Integer lectureId;
	private Integer len;
	private Integer userId;
	private String itemThirdCode;
	private String lectureName;
	private String videoTime;
	private String name;
	private String eduYear;
	private String eduClass;
	private String eduStep;
	private Integer isInSchool;
	private String originType;

	private Integer liveTime;
	private Integer liveWatchTime;
	
	
	public Integer getClassTypeId() {
		return classTypeId;
	}

	public void setClassTypeId(Integer classTypeId) {
		this.classTypeId = classTypeId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLectureId() {
		return lectureId;
	}

	public void setLectureId(Integer lectureId) {
		this.lectureId = lectureId;
	}

	public Integer getLen() {
		return len;
	}

	public void setLen(Integer len) {
		this.len = len;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getItemThirdCode() {
		return itemThirdCode;
	}

	public void setItemThirdCode(String itemThirdCode) {
		this.itemThirdCode = itemThirdCode;
	}

	public String getLectureName() {
		return lectureName;
	}

	public void setLectureName(String lectureName) {
		this.lectureName = lectureName;
	}

	public String getVideoTime() {
		return videoTime;
	}

	public void setVideoTime(String videoTime) {
		this.videoTime = videoTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEduYear() {
		return eduYear;
	}

	public void setEduYear(String eduYear) {
		this.eduYear = eduYear;
	}

	public String getEduClass() {
		return eduClass;
	}

	public void setEduClass(String eduClass) {
		this.eduClass = eduClass;
	}

	public String getEduStep() {
		return eduStep;
	}

	public void setEduStep(String eduStep) {
		this.eduStep = eduStep;
	}

	public Integer getIsInSchool() {
		return isInSchool;
	}

	public void setIsInSchool(Integer isInSchool) {
		this.isInSchool = isInSchool;
	}

	public String getOriginType() {
		return originType;
	}

	public void setOriginType(String originType) {
		this.originType = originType;
	}

	public Integer getLiveTime() {
		return liveTime;
	}

	public void setLiveTime(Integer liveTime) {
		this.liveTime = liveTime;
	}

	public Integer getLiveWatchTime() {
		return liveWatchTime;
	}

	public void setLiveWatchTime(Integer liveWatchTime) {
		this.liveWatchTime = liveWatchTime;
	}

	@Override
	public String toString() {
		return "ClassLessonVO [classTypeId=" + classTypeId + ", id=" + id + ", lectureId=" + lectureId + ", len=" + len
				+ ", userId=" + userId + ", itemThirdCode=" + itemThirdCode + ", lectureName=" + lectureName
				+ ", videoTime=" + videoTime + ", name=" + name + ", eduYear=" + eduYear + ", eduClass=" + eduClass
				+ ", eduStep=" + eduStep + ", isInSchool=" + isInSchool + ", originType=" + originType + "]";
	}

}
