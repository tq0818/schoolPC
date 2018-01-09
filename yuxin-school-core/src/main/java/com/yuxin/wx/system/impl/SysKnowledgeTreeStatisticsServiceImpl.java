package com.yuxin.wx.system.impl;

import com.yuxin.wx.api.system.ISysKnowledgeTreeStatisticsService;
import com.yuxin.wx.common.BaseServiceImpl;
import com.yuxin.wx.system.mapper.SysKnowledgeTreeStatisticsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2017/7/31.
 */
@Service
@Transactional
public class SysKnowledgeTreeStatisticsServiceImpl extends BaseServiceImpl implements ISysKnowledgeTreeStatisticsService {
    @Autowired
    private SysKnowledgeTreeStatisticsMapper sysKnowledgeTreeStatisticsMapper;

}
