package com.yuxin.wx.api.system;

import com.yuxin.wx.model.system.SysConfigItemRelation;

import java.util.List;

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
    List<SysConfigItemRelation> findSysConfigItemRelationById(Integer id);
    List<SysConfigItemRelation> findRelationByLevel(Integer level);
    List<SysConfigItemRelation>  findRelationByIds(List<Integer> id);
    void deleteRelation( List<SysConfigItemRelation> list);
    void deleteById(Integer id);
    void publishRelation();
    List<SysConfigItemRelation> findItemFront(SysConfigItemRelation item);
    List<SysConfigItemRelation> findAllItemFront();

    List<SysConfigItemRelation> findItemFrontByLevel(Integer level);

    List<SysConfigItemRelation> findChildByCode(SysConfigItemRelation relation);
}
