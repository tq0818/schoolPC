package com.yuxin.wx.watchInfo.impl;

import com.yuxin.wx.api.watchInfo.IWatchInfoService;
import com.yuxin.wx.common.BaseServiceImpl;
import com.yuxin.wx.model.classes.ClassModuleLesson;
import com.yuxin.wx.model.watchInfo.ClassRoomRelation;
import com.yuxin.wx.model.watchInfo.WatchInfo;
import com.yuxin.wx.watchInfo.mapper.WatchInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/10/18.
 */
@Service
@Transactional
public class WatchInfoServiceImpl extends BaseServiceImpl implements IWatchInfoService{
    @Override
    public List<WatchInfo> getLessonByDate(String lessonDate) {
        return watchInfoMapper.getLessonByDate(lessonDate);
    }

    @Autowired
    private WatchInfoMapper watchInfoMapper;

    @Override
    public List<WatchInfo> getWatchInfo(WatchInfo watchInfo) {
        return null;
    }

    @Override
    public void addWatchInfo(WatchInfo watchInfo) {
        watchInfoMapper.addWatchInfo(watchInfo);
    }

    @Override
    public void addClassRoomRelation(List<ClassRoomRelation> list) {
        watchInfoMapper.addClassRoomRelation(list);
    }

    @Override
    public List<WatchInfo> getClassRoomRelation(ClassRoomRelation relation) {
        return watchInfoMapper.getClassRoomRelation(relation);
    }
}
