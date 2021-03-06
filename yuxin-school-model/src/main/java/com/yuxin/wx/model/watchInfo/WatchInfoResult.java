package com.yuxin.wx.model.watchInfo;

import java.util.List;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.model.classes.EduMasterClass;
/**
 * Created by Administrator on 2017/10/18.
 */
@SuppressWarnings("serial")
public class WatchInfoResult extends BaseEntity {
    private String className;//课程名称
    private String lessonName;//课次名称
    private String userName;//用户名称
    private String studentName;//学员名称
    private String studyClass;//所在班级
    private Integer times;//累计观看次数
    private long watchTime;//累计观看时长
    private String  eduArea; //所在区域
    private String  eduSchool;//所在学校
    private String  eduStep;//所在学段
    private String  eduYear;//所在年纪
    private String  eduClass;//所在班级
    private  String itemThirdCode;//学科
    private Integer commodityId;//商品ID
    private Integer lessonId;//课次ID
    private String  startTime;//开始日期
    private String  endTime;//结束日期
    private String userNameOrMobile;
    private String studyTime;
    private String schoolType;
    private String orderBy;
    private String userId;
    private String isBaseSchool;//基地校
    private String videoFlag;//录播
    private String liveFlag;//直播
    private String isFromBrachSchool;
    private Integer companyId;//机构标识号
    private List<EduMasterClass> eduMasterClass;

    public List<EduMasterClass> getEduMasterClass() {
		return eduMasterClass;
	}

	public void setEduMasterClass(List<EduMasterClass> eduMasterClass) {
		this.eduMasterClass = eduMasterClass;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getIsFromBrachSchool() {
		return isFromBrachSchool;
	}

	public void setIsFromBrachSchool(String isFromBrachSchool) {
		this.isFromBrachSchool = isFromBrachSchool;
	}

	public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getStudyTime() {
        return studyTime;
    }

    public void setStudyTime(String studyTime) {
        this.studyTime = studyTime;
    }

    public String getUserNameOrMobile() {
        return userNameOrMobile;
    }

    public void setUserNameOrMobile(String userNameOrMobile) {
        this.userNameOrMobile = userNameOrMobile;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getEduClass() {
        return eduClass;
    }

    public void setEduClass(String eduClass) {
        this.eduClass = eduClass;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public long getWatchTime() {
        return watchTime;
    }

    public void setWatchTime(long watchTime) {
        this.watchTime = watchTime;
    }

    public String getStudyClass() {
        return studyClass;
    }

    public void setStudyClass(String studyClass) {
        this.studyClass = studyClass;
    }

    public String getEduArea() {
        return eduArea;
    }

    public void setEduArea(String eduArea) {
        this.eduArea = eduArea;
    }

    public String getEduSchool() {
        return eduSchool;
    }

    public void setEduSchool(String eduSchool) {
        this.eduSchool = eduSchool;
    }

    public String getEduStep() {
        return eduStep;
    }

    public void setEduStep(String eduStep) {
        this.eduStep = eduStep;
    }

    public String getEduYear() {
        return eduYear;
    }

    public void setEduYear(String eduYear) {
        this.eduYear = eduYear;
    }

    public String getItemThirdCode() {
        return itemThirdCode;
    }

    public void setItemThirdCode(String itemThirdCode) {
        this.itemThirdCode = itemThirdCode;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public Integer getLessonId() {
        return lessonId;
    }

    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(String schoolType) {
        this.schoolType = schoolType;
    }

	
    public String getIsBaseSchool() {
    	return isBaseSchool;
    }

	
    public void setIsBaseSchool(String isBaseSchool) {
    	this.isBaseSchool = isBaseSchool;
    }

	
    public String getVideoFlag() {
    	return videoFlag;
    }

	
    public void setVideoFlag(String videoFlag) {
    	this.videoFlag = videoFlag;
    }

	
    public String getLiveFlag() {
    	return liveFlag;
    }

	
    public void setLiveFlag(String liveFlag) {
    	this.liveFlag = liveFlag;
    }
    
}
