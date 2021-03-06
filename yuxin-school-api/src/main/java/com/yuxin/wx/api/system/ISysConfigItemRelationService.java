package com.yuxin.wx.api.system;

import com.yuxin.wx.model.system.SysConfigItem;
import com.yuxin.wx.model.system.SysConfigItemRelation;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/31.
 */
public interface ISysConfigItemRelationService {
    /**
     *
     * @Title: saveSysConfigItemRelation
     * @Description: 新增SysConfigItemRelation
     * @return void    返回类型
     * @throws
     * @exception
     * @date 2017-7-31
     * @user by XY
     */
    void insert(SysConfigItemRelation entity);

    /**
     *
     * @Title: updateSysConfigItemRelation
     * @Description: 编辑SysConfigItemRelation
     * @return void    返回类型
     * @throws
     * @exception
     * @date 2015-3-17
     * @user by wangzx
     */
    void update(SysConfigItemRelation entity);
    /**
     *
     * @Title: deleteSysConfigItemRelationById
     * @Description: 根据id删除SysConfigItemRelation及子节点
     * @param id
     * @return void    返回类型
     * @throws
     * @exception
     * @date 2015-3-17
     * @user by wangzx
     */
    void deleteSysConfigItemRelationById(Integer id);
    /**
     *
     * @Title: findSysConfigIndexItemById
     * @Description: 根据id查询
     * @param id
     * @return void    返回类型
     * @throws
     * @exception
     * @date 2015-3-17
     * @user by wangzx
     */
    List<SysConfigItemRelation> findSysConfigItemRelationById(Integer id,Integer companyId);
    
    List<SysConfigItemRelation> findSysConfigItemRelationByCode(SysConfigItemRelation item);
    
    List<SysConfigItemRelation> findRelationByLevel(Integer level,Integer companyId);
    List<SysConfigItemRelation>  findRelationByIds(List<Integer> id);
    void deleteRelation( List<SysConfigItemRelation> list);
    void deleteById(Integer id);
    void publishRelation(Integer companyId);
    List<SysConfigItemRelation> findItemFront(SysConfigItemRelation item);
    List<SysConfigItemRelation> findAllItemFront(Integer companyId);
    List<SysConfigItemRelation> findAllItemRelation(Map<String,Object> params);

    List<SysConfigItemRelation> findItemFrontByLevel(Integer level,Integer companyId);

    List<SysConfigItemRelation> findChildByCode(SysConfigItemRelation relation);

    List<SysConfigItem> findItemByEduStep(Map<String, Object> eduStep);

    List<SysConfigItemRelation> findSysConfigItemRelationFrontByPCode(String parentCode, Integer currentCompanyId);
}
