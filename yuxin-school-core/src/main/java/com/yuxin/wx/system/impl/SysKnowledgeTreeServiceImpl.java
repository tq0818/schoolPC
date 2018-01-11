package com.yuxin.wx.system.impl;

import com.yuxin.wx.api.system.ISysConfigItemRelationService;
import com.yuxin.wx.api.system.ISysKnowledgeTreeService;
import com.yuxin.wx.common.BaseServiceImpl;
import com.yuxin.wx.model.classes.ClassModuleLesson;
import com.yuxin.wx.model.system.SysConfigItemRelation;
import com.yuxin.wx.model.system.SysKnowledgeTree;
import com.yuxin.wx.model.system.SysKnowledgeTreeStatistics;
import com.yuxin.wx.system.mapper.SysConfigItemRelationMapper;
import com.yuxin.wx.system.mapper.SysKnowledgeTreeMapper;
import com.yuxin.wx.system.mapper.SysKnowledgeTreeStatisticsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/31.
 */
@Service
@Transactional
public class SysKnowledgeTreeServiceImpl extends BaseServiceImpl implements ISysKnowledgeTreeService {
    @Autowired
    private SysKnowledgeTreeMapper sysKnowledgeTreeMapper;

    @Autowired
    private SysKnowledgeTreeStatisticsMapper sysKnowledgeTreeStatisticsMapper;


    @Override
    public void insertKnowledgeTree(SysKnowledgeTree sysKnowledgeTree) {
        sysKnowledgeTreeMapper.insert(sysKnowledgeTree);
    }

    @Override
    public void updateKnowledgeTree(SysKnowledgeTree sysKnowledgeTree) {
        sysKnowledgeTreeMapper.update(sysKnowledgeTree);
    }

    @Override
    public List<SysKnowledgeTree> findKnoledgeTreeByPapam(SysKnowledgeTree sysKnowledgeTree) {
        return sysKnowledgeTreeMapper.findKnoledgeTreeByPapam(sysKnowledgeTree);
    }

    @Override
    public void removeKnowledge(SysKnowledgeTree sysKnowledgeTree) {
        List<SysKnowledgeTree> sysKnowledgeTreeList = findKnoledgeTreeByPapam(sysKnowledgeTree);
        if(sysKnowledgeTreeList!=null && sysKnowledgeTreeList.size()>0){
            sysKnowledgeTreeStatisticsMapper.removeKnowledgeStatistics(sysKnowledgeTreeList);
        }
        sysKnowledgeTreeMapper.removeKnowledge(sysKnowledgeTree);
    }

}
