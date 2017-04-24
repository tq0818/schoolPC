package com.yuxin.wx.api.classes;

import java.util.List;

import com.yuxin.wx.model.classes.ClassTypeRemoteRelation;
import com.yuxin.wx.vo.classes.ModuleRemoteVo;
/**
 * Service Interface:ClassTypeRemoteRelation
 * @author wang.zx
 * @date 2014-12-5
 */
public interface IClassTypeRemoteRelationService  {
	/**
	 * 
	* @Title: saveClassTypeRemoteRelation
	* @Description: 新增ClassTypeRemoteRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void insert(ClassTypeRemoteRelation classTypeRemoteRelation);
	
	/**
	 * 
	* @Title: batchSaveClassTypeRemoteRelation 
	* @Description: 批量新增ClassTypeRemoteRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void batchInsert(List<ClassTypeRemoteRelation> classTypeRemoteRelation);
	
	/**
	 * 
	* @Title: updateClassTypeRemoteRelation 
	* @Description: 编辑ClassTypeRemoteRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void update(ClassTypeRemoteRelation classTypeRemoteRelation);
	
	/**
	 * 
	* @Title: deleteClassTypeRemoteRelationById 
	* @Description: 根据id删除ClassTypeRemoteRelation
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void deleteClassTypeRemoteRelationById(Integer id);
	
	/**
	 * 
	* @Title: deleteClassTypeRemoteRelationByIds 
	* @Description: 根据id批量删除ClassTypeRemoteRelation
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void deleteClassTypeRemoteRelationByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findClassTypeRemoteRelationById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	ClassTypeRemoteRelation findClassTypeRemoteRelationById(Integer id);
	
	/**
	 * 
	* @Title: findClassTypeRemoteRelationByPage 
	* @Description: 分页查询
	* @return
	* @return List<ClassTypeRemoteRelation>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	List<ClassTypeRemoteRelation> findClassTypeRemoteRelationByPage(ClassTypeRemoteRelation search);
	
	/**
	 * 
	 * Class Name: IClassTypeRemoteRelationService.java
	 * @Description: 根据班型id删除相关联的远程教育模块
	 * @author Keyn
	 * @date 2015-1-5 下午3:40:52
	 * @version 1.0
	 * @param classTypeId
	 */
	public void deleteByClassTypeId(String classTypeId);
	
	/**
	 * 
	 * Class Name: IClassTypeModuleRelationService.java
	 * @Description:根据班型id查询远程模块
	 * @author zhang.zx
	 * @date 2015年5月26日 下午6:56:14
	 * @modifier
	 * @modify-date 2015年5月26日 下午6:56:14
	 * @version 1.0
	 * @param classTypeId
	 * @return
	 */
	List<ModuleRemoteVo> queryRomoteListByClassTypeId(Integer classTypeId);
}