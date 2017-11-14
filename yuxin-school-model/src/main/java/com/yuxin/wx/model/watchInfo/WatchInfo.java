package com.yuxin.wx.model.watchInfo;

import com.yuxin.wx.common.BaseEntity;

import java.util.Date;

/**
 * Created by Administrator on 2017/10/18.
 */
public class WatchInfo extends BaseEntity {
    private String name;
    private Integer lessonId; //课次ID
    private String liveroomId;//直播房间ID
    private String lessonName;//课次名称
    private Date lessonDate;//课次日期
    private Integer classtypeId;// 课程ID
    private Integer commodityId;//商品ID
    private Integer userId;//用户ID
    private String joinTime;//加入时间
    private String leaveTime;//离开时间
    private Long watchTime;//观看时长
    private String classId;//课件ID
    private String device;//设备类型
    // 0 PC 客户端 1 PC Web 端,2 PC Web 端(http流),3 IPAD Web 端,4 IPHONE Web 端,5 APAD Web 端,6 APHONE Web 端,7 IPAD APP 端
    //8 IPHONE APP 端,9 APAD APP 端,10 APHONE APP 端,11 MAC 客户端,12 电话端,16 PLAYERSDK IOS 端,17 PLAYERSDK 安卓端
    private Integer companyId;
    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLessonId() {
        return lessonId;
    }

    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
    }

    public String getLiveroomId() {
        return liveroomId;
    }

    public void setLiveroomId(String liveroomId) {
        this.liveroomId = liveroomId;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public Date getLessonDate() {
        return lessonDate;
    }

    public void setLessonDate(Date lessonDate) {
        this.lessonDate = lessonDate;
    }

    public Integer getClasstypeId() {
        return classtypeId;
    }

    public void setClasstypeId(Integer classtypeId) {
        this.classtypeId = classtypeId;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(String joinTime) {
        this.joinTime = joinTime;
    }

    public String getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(String leaveTime) {
        this.leaveTime = leaveTime;
    }

    public Long getWatchTime() {
        return watchTime;
    }

    public void setWatchTime(Long watchTime) {
        this.watchTime = watchTime;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
}
