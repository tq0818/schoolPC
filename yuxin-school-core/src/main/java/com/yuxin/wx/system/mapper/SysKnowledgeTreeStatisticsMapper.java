package com.yuxin.wx.system.mapper;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.system.SysKnowledgeTree;
import com.yuxin.wx.model.system.SysKnowledgeTreeStatistics;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/31.
 */
public interface SysKnowledgeTreeStatisticsMapper extends BaseMapper<SysKnowledgeTreeStatistics> {

    /**
     * 清空知识树节点
     * @param sysKnowledgeTreeList
     */
    void removeKnowledgeStatistics(List<SysKnowledgeTree> sysKnowledgeTreeList);

    List<SysKnowledgeTreeStatistics> findStatistics(Map map);

    Map findLessonInfo(Map map);

    void updateStatistics(SysKnowledgeTreeStatistics info);

    void addStatistics(SysKnowledgeTreeStatistics info);
}
