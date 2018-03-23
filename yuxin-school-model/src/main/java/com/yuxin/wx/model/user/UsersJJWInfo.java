package com.yuxin.wx.model.user;

import com.yuxin.wx.common.BaseEntity;

import java.util.Date;

@SuppressWarnings("serial")
public class UsersJJWInfo extends BaseEntity {
    private String classType; //课程类型  直播 微课  录播
    private Integer lessonTime;//课程时长
    private String lessonName;//课程名称(课次名称、节名称)
    private Integer studyTime;//学习时长
    private Integer userId;//继教网用户在系统内ID
    private String studyState;//是否完成学习



    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public Integer getLessonTime() {
        return lessonTime;
    }

    public void setLessonTime(Integer lessonTime) {
        this.lessonTime = lessonTime;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public Integer getStudyTime() {
        return studyTime;
    }

    public void setStudyTime(Integer studyTime) {
        this.studyTime = studyTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public String getStudyState() {
        return studyState;
    }

    public void setStudyState(String studyState) {
        this.studyState = studyState;
    }
}
