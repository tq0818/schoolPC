package com.yuxin.wx.api.system;

import com.yuxin.wx.model.system.SysConfigItemRelation;
import com.yuxin.wx.model.system.SysKnowledgeTree;

import java.util.List;

/**
 * Created by Administrator on 2017/7/31.
 */
public interface ISysKnowledgeTreeService {

    /**
     * 查询所有的知识树节点（根据学科学段）
     * @param sysKnowledgeTree
     * @return
     */
    List<SysKnowledgeTree> findKnoledgeTreeByPhaseAndSub(SysKnowledgeTree sysKnowledgeTree);

    /**
     * 添加知识树节点
     * @param sysKnowledgeTree
     */
    void insertKnowledgeTree(SysKnowledgeTree sysKnowledgeTree);

    /**
     * 修改知识树节点
     * @param sysKnowledgeTree
     */
    void updateKnowledgeTree(SysKnowledgeTree sysKnowledgeTree);
}
