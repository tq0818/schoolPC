package com.yuxin.wx.model.watchInfo;

import com.yuxin.wx.common.BaseEntity;

/**
 * Created by Administrator on 2017/10/19.
 */
public class ClassRoomRelation extends BaseEntity{
    private String liveroomId;
    private String classId;

    public String getLiveroomId() {
        return liveroomId;
    }

    public void setLiveroomId(String liveroomId) {
        this.liveroomId = liveroomId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }
}
