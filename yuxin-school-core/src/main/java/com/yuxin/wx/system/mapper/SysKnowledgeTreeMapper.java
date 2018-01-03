package com.yuxin.wx.system.mapper;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.system.SysConfigItemRelation;
import com.yuxin.wx.model.system.SysKnowledgeTree;

import java.util.List;

/**
 * Created by Administrator on 2017/7/31.
 */
public interface SysKnowledgeTreeMapper extends BaseMapper<SysKnowledgeTree> {

    List<SysKnowledgeTree> findKnoledgeTreeByPhaseAndSub(SysKnowledgeTree sysKnowledgeTree);
}
