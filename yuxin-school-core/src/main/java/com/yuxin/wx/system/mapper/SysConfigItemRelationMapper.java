package com.yuxin.wx.system.mapper;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.system.SysConfigItem;
import com.yuxin.wx.model.system.SysConfigItemRelation;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/31.
 */
public interface SysConfigItemRelationMapper extends BaseMapper<SysConfigItemRelation> {
    void insert(SysConfigItemRelation entity);
    void update(SysConfigItemRelation entity);
    void deleteSysConfigItemRelationById(Integer id);
    List<SysConfigItemRelation> findSysConfigItemRelationById(Integer id);

    List<SysConfigItemRelation> findFirstLevel(Map<String,Object> params);

    List<SysConfigItemRelation> findRelationByParentId(Map<String,Object> params);
    
    List<SysConfigItemRelation> findRelationByCode(Map<String,Object> params);

    List<SysConfigItemRelation> findRelationByLevel(Map<String,Object> params);

    List<SysConfigItemRelation> findRelationByIds(List<Integer> ids);

    void publish(Integer companyId);

    void deleteFront(Integer companyId);

    List<SysConfigItemRelation> findFirstLevelFront(Integer companyId);

    List<SysConfigItemRelation> findChildrenFront(SysConfigItemRelation item);

    List<SysConfigItemRelation> findAllItemFront(Integer companyId);
    List<SysConfigItemRelation> findAllItemRelation(Map<String,Object> params);

    List<SysConfigItemRelation> findItemFrontByLevel(Map<String,Object> params);

    List<SysConfigItemRelation> findChildByCode(SysConfigItemRelation relation);

    List<SysConfigItem> findItemByEduStep(Map<String, Object> eduStep);

    List<SysConfigItemRelation> findRelationFrontByParentCode(Map<String, Object> params);
}
