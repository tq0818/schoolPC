package com.yuxin.wx.model.watchInfo;

import com.yuxin.wx.common.BaseEntity;

import java.util.Date;


public class WatchInfoFromZSSend extends BaseEntity {
    private String classNo;
    private String operator;
    private String action;
    private String affected;
    private String totalusernum;
    private Date watchDate;


    public WatchInfoFromZSSend(String ClassNo, String Operator,String Action,String Affected,String totalusernum){
        this.classNo = ClassNo;
        this.operator = Operator;
        this.action = Action;
        this.affected = Affected;
        this.totalusernum = totalusernum;
        this.watchDate = new Date();
    }


    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAffected() {
        return affected;
    }

    public void setAffected(String affected) {
        this.affected = affected;
    }

    public String getTotalusernum() {
        return totalusernum;
    }

    public void setTotalusernum(String totalusernum) {
        this.totalusernum = totalusernum;
    }

    public Date getWatchDate() {
        return watchDate;
    }

    public void setWatchDate(Date watchDate) {
        this.watchDate = watchDate;
    }
}
