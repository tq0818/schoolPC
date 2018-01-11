package com.yuxin.wx.watchInfo.mapper;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.classes.ClassModuleLesson;
import com.yuxin.wx.model.watchInfo.ClassRoomRelation;
import com.yuxin.wx.model.watchInfo.WatchInfo;
import com.yuxin.wx.model.watchInfo.WatchInfoFromZSGet;
import com.yuxin.wx.model.watchInfo.WatchInfoFromZSSend;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/18.
 */
public interface WatchInfoMapper extends BaseMapper<WatchInfo> {
    List<WatchInfo> getWatchInfo(WatchInfo watchInfo);

    void addWatchInfo(WatchInfo watchInfo);

    void addClassRoomRelation(ClassRoomRelation relation);

    List<WatchInfo> getClassRoomRelation(ClassRoomRelation relation);

    List<WatchInfo> getLessonByDate(Map map);

    void addWatchInfoFromZSResult(WatchInfoFromZSGet watchInfo);

    void addWatchInfoFromZSSent(WatchInfoFromZSSend watchInfo);

    Map getCurrentByRoom(Map map);

    List<Map<String, Object>> queryWatchInfoByParam(Map<String, Object> param);
}
