package com.yuxin.wx.api.system;

import com.yuxin.wx.model.system.SysKnowledgeTreeStatistics;
import com.yuxin.wx.vo.classes.ClassModuleLessonVo;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/8.
 */
public interface ISysKnowledgeTreeStatisticsService {
    List<SysKnowledgeTreeStatistics> findStatistics(Map map);
    void updateStatistics (SysKnowledgeTreeStatistics info);
    void addStatistics(SysKnowledgeTreeStatistics info);
    Map findLessonInfo(Map map);

}
