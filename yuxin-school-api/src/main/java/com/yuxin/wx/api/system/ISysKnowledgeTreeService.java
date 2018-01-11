package com.yuxin.wx.api.system;

import com.yuxin.wx.model.system.SysKnowledgeTree;
import com.yuxin.wx.model.user.Users;

import java.util.List;

/**
 * Created by Administrator on 2017/7/31.
 */
public interface ISysKnowledgeTreeService {

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

    /**
     * 根据前置条件查询知识树节点
     * @param sysKnowledgeTree
     * @return
     */
    List<SysKnowledgeTree> findKnoledgeTreeByPapam(SysKnowledgeTree sysKnowledgeTree);

    /**
     * 清空知识树
     * @param sysKnowledgeTree
     * @return
     */
    void removeKnowledge(SysKnowledgeTree sysKnowledgeTree);

    void addKnowledgeTree(String idstr, SysKnowledgeTree sysKnowledgeTree, Users user);
}
