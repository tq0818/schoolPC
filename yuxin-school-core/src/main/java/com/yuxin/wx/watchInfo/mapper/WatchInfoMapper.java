package com.yuxin.wx.watchInfo.mapper;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.classes.ClassModuleLesson;
import com.yuxin.wx.model.watchInfo.ClassRoomRelation;
import com.yuxin.wx.model.watchInfo.WatchInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/10/18.
 */
public interface WatchInfoMapper extends BaseMapper<WatchInfo> {
    List<WatchInfo> getWatchInfo(WatchInfo watchInfo);

    void addWatchInfo(WatchInfo watchInfo);

    void addClassRoomRelation(List<ClassRoomRelation> list);

    List<WatchInfo> getClassRoomRelation(ClassRoomRelation relation);

    List<WatchInfo> getLessonByDate(String lessonDate);
}
