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

    List<SysConfigItemRelation> findRelationByIds(List<Integer> ids);

    void publish();

    void deleteFront();

    List<SysConfigItemRelation> findFirstLevelFront();

    List<SysConfigItemRelation> findChildrenFront(SysConfigItemRelation item);

    List<SysConfigItemRelation> findAllItemFront();

    List<SysConfigItemRelation> findItemFrontByLevel(Integer level);

    List<SysConfigItemRelation> findChildByCode(SysConfigItemRelation relation);
}
