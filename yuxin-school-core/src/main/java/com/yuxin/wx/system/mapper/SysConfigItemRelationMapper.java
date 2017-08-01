package com.yuxin.wx.system.mapper;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.system.SysConfigItemRelation;

import java.util.List;

/**
 * Created by Administrator on 2017/7/31.
 */
public interface SysConfigItemRelationMapper extends BaseMapper<SysConfigItemRelation> {
    void insert(SysConfigItemRelation entity);
    void update(SysConfigItemRelation entity);
    void deleteSysConfigItemRelationById(Integer id);
    List<SysConfigItemRelation> findSysConfigItemRelationById(Integer id);

    List<SysConfigItemRelation> findFirstLevel();

    List<SysConfigItemRelation> findRelationByParentId(Integer id);

    List<SysConfigItemRelation> findRelationByLevel(Integer level);
}
