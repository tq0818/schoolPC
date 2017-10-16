package com.yuxin.wx.model.user;

import com.yuxin.wx.common.BaseEntity;

/**
 * Created by Administrator on 2017/10/11.
 */
public class UsersLoginFromOther extends BaseEntity{
    private Integer	userId;
    private String	platfrom;		 /* 平台：qq、wechat、weibo */
    private Integer companyId;
    private Integer schoolId;


    private String	levelName; //学段
    private String	 subjectName;//学科
    private String userCode;//继教网账号
    private String userName;//用户名称
    private String areaName;//所在区域
    private String unitName;//所在学校
    private boolean loginState;//登录状态
    private String pwd;
    private String pwdS;
    private String userGuid;

    public String getUserGuid() {
        return userGuid;
    }

    public void setUserGuid(String userGuid) {
        this.userGuid = userGuid;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPlatfrom() {
        return platfrom;
    }

    public void setPlatfrom(String platfrom) {
        this.platfrom = platfrom;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getLevelName() {
        return levelName;
    }


    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }



    public String getPwd() {
        return pwd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPwdS() {
        return pwdS;
    }

    public void setPwdS(String pwdS) {
        this.pwdS = pwdS;
    }

    public boolean isLoginState() {
        return loginState;
    }

    public void setLoginState(boolean loginState) {
        this.loginState = loginState;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }
}
