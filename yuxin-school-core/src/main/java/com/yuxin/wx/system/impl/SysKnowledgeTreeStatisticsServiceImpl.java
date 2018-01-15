package com.yuxin.wx.system.impl;

import com.yuxin.wx.api.system.ISysKnowledgeTreeStatisticsService;
import com.yuxin.wx.common.BaseServiceImpl;
import com.yuxin.wx.model.system.SysKnowledgeTreeStatistics;
import com.yuxin.wx.system.mapper.SysKnowledgeTreeStatisticsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/31.
 */
@Service
@Transactional
public class SysKnowledgeTreeStatisticsServiceImpl extends BaseServiceImpl implements ISysKnowledgeTreeStatisticsService {
    @Autowired
    private SysKnowledgeTreeStatisticsMapper sysKnowledgeTreeStatisticsMapper;


    @Override
    public List<SysKnowledgeTreeStatistics> findStatistics(Map map) {
        return sysKnowledgeTreeStatisticsMapper.findStatistics(map);
    }

    @Override
    public void updateStatistics(SysKnowledgeTreeStatistics info) {
        sysKnowledgeTreeStatisticsMapper.updateStatistics(info);
    }

    @Override
    public void addStatistics(SysKnowledgeTreeStatistics info) {
        sysKnowledgeTreeStatisticsMapper.addStatistics(info);
    }

    @Override
    public Map findLessonInfo(Map map) {
        return sysKnowledgeTreeStatisticsMapper.findLessonInfo(map);
    }


}
