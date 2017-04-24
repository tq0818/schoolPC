package com.yuxin.wx.api.system;

import java.util.List;

import com.yuxin.wx.model.system.SysSchoolItemRelation;
/**
 * Service Interface:SysSchoolItemRelation
 * @author chopin
 * @date 2015-3-12
 */
public interface ISysSchoolItemRelationService  {
	/**
	 * 
	* @Title: saveSysSchoolItemRelation
	* @Description: 新增SysSchoolItemRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-12
	* @user by wangzx
	 */
	void insert(SysSchoolItemRelation entity);
	
	/**
	 * 
	* @Title: batchSaveSysSchoolItemRelation 
	* @Description: 批量新增SysSchoolItemRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-12
	* @user by wangzx
	 */
	void batchInsert(List<SysSchoolItemRelation> list);
	
	/**
	 * 
	* @Title: updateSysSchoolItemRelation 
	* @Description: 编辑SysSchoolItemRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-12
	* @user by wangzx
	 */
	void update(SysSchoolItemRelation entity);
	
	/**
	 * 
	* @Title: deleteSysSchoolItemRelationById 
	* @Description: 根据id删除SysSchoolItemRelation
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-12
	* @user by wangzx
	 */
	void deleteSysSchoolItemRelationById(Integer id);
	
	/**
	 * 
	* @Title: deleteSysSchoolItemRelationByIds 
	* @Description: 根据id批量删除SysSchoolItemRelation
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-12
	* @user by wangzx
	 */
	void deleteSysSchoolItemRelationByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findSysSchoolItemRelationById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-12
	* @user by wangzx
	 */
	SysSchoolItemRelation findSysSchoolItemRelationById(Integer id);
	
	/**
	 * 
	* @Title: findSysSchoolItemRelationByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysSchoolItemRelation>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-12
	* @user by wangzx
	 */
	List<SysSchoolItemRelation> findSysSchoolItemRelationByPage(SysSchoolItemRelation search);
	/**
	 * 
	 * Class Name: ISysSchoolItemRelationService.java
	 * @Description: 根据学科id查询
	 * @author yuchanglong
	 * @date 2016年1月7日 上午10:29:49
	 * @version 1.0
	 * @param itemId
	 * @return
	 */
	SysSchoolItemRelation findSysSchoolItemRelationByItemId(SysSchoolItemRelation search);
}