package com.yuxin.wx.watchInfo.impl;

import com.yuxin.wx.api.watchInfo.IWatchInfoService;
import com.yuxin.wx.common.BaseServiceImpl;
import com.yuxin.wx.model.classes.ClassModuleLesson;
import com.yuxin.wx.model.watchInfo.ClassRoomRelation;
import com.yuxin.wx.model.watchInfo.WatchInfo;
import com.yuxin.wx.model.watchInfo.WatchInfoFromZSGet;
import com.yuxin.wx.model.watchInfo.WatchInfoFromZSSend;
import com.yuxin.wx.watchInfo.mapper.WatchInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/18.
 */
@Service
@Transactional
public class WatchInfoServiceImpl extends BaseServiceImpl implements IWatchInfoService{
    @Autowired
    private WatchInfoMapper watchInfoMapper;
    private SimpleDateFormat sdf = new SimpleDateFormat();

    @Override
    public List<WatchInfo> getLessonByDate(Map map) {
        return watchInfoMapper.getLessonByDate(map);
    }

    @Override
    public List<WatchInfo> getWatchInfo(WatchInfo watchInfo) {
        return null;
    }

    @Override
    public void addWatchInfo(WatchInfo watchInfo) {
        watchInfoMapper.addWatchInfo(watchInfo);
    }

    @Override
    public void addClassRoomRelation(ClassRoomRelation list) {
        watchInfoMapper.addClassRoomRelation(list);
    }

    @Override
    public List<WatchInfo> getClassRoomRelation(ClassRoomRelation relation) {
        return watchInfoMapper.getClassRoomRelation(relation);
    }
    //添加直播历史并发数据
    @Override
    public void addWatchInfoFromZSResult(WatchInfoFromZSGet watchInfo) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(watchInfo.getStartTime());
        watchInfo.setWatchDate(c.getTime());
        watchInfoMapper.addWatchInfoFromZSResult(watchInfo);
    }
    //添加直播实时并发数据

    @Override
    public void addWatchInfoFromZSSent(WatchInfoFromZSSend watchInfo) {
        watchInfoMapper.addWatchInfoFromZSSent(watchInfo);
    }

    @Override
    public Map getCurrentByRoom(Map map) {
        return watchInfoMapper.getCurrentByRoom(map);
    }


}
