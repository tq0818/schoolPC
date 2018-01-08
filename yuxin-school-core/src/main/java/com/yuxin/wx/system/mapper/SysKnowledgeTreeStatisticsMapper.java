package com.yuxin.wx.system.mapper;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.classes.ClassModuleLesson;
import com.yuxin.wx.model.system.SysKnowledgeTree;

import java.util.List;

/**
 * Created by Administrator on 2017/7/31.
 */
public interface SysKnowledgeTreeStatisticsMapper extends BaseMapper<SysKnowledgeTree> {

    /**
     * 清空知识树节点
     * @param sysKnowledgeTreeList
     */
    void removeKnowledgeStatistics(List<SysKnowledgeTree> sysKnowledgeTreeList);
}
