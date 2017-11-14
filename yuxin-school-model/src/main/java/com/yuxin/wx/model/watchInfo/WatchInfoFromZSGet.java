package com.yuxin.wx.model.watchInfo;

import com.yuxin.wx.common.BaseEntity;

import java.util.Date;

public class WatchInfoFromZSGet {
    private String id;
    private long maxConcurrent;
    private Date watchDate;
    private long startTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public Date getWatchDate() {
        return watchDate;
    }

    public void setWatchDate(Date watchDate) {
        this.watchDate = watchDate;
    }

    public long getMaxConcurrent() {
        return maxConcurrent;
    }

    public void setMaxConcurrent(long maxConcurrent) {
        this.maxConcurrent = maxConcurrent;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
}
