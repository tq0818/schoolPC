package com.yuxin.wx.api.classes;

import java.util.List;
import com.yuxin.wx.model.classes.ClassModuleVideoRelation;
/**
 * Service Interface:ClassModuleVideoRelation
 * @author wang.zx
 * @date 2014-12-5
 */
public interface IClassModuleVideoRelationService  {
	/**
	 * 
	* @Title: saveClassModuleVideoRelation
	* @Description: 新增ClassModuleVideoRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void insert(ClassModuleVideoRelation classModuleVideoRelation);
	
	/**
	 * 
	* @Title: batchSaveClassModuleVideoRelation 
	* @Description: 批量新增ClassModuleVideoRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void batchInsert(List<ClassModuleVideoRelation> classModuleVideoRelation);
	
	/**
	 * 
	* @Title: updateClassModuleVideoRelation 
	* @Description: 编辑ClassModuleVideoRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void update(ClassModuleVideoRelation classModuleVideoRelation);
	
	/**
	 * 
	* @Title: deleteClassModuleVideoRelationById 
	* @Description: 根据id删除ClassModuleVideoRelation
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void deleteClassModuleVideoRelationById(Integer id);
	
	/**
	 * 
	* @Title: deleteClassModuleVideoRelationByIds 
	* @Description: 根据id批量删除ClassModuleVideoRelation
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void deleteClassModuleVideoRelationByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findClassModuleVideoRelationById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	ClassModuleVideoRelation findClassModuleVideoRelationById(Integer id);
	
	/**
	 * 
	* @Title: findClassModuleVideoRelationByPage 
	* @Description: 分页查询
	* @return
	* @return List<ClassModuleVideoRelation>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	List<ClassModuleVideoRelation> findClassModuleVideoRelationByPage(ClassModuleVideoRelation search);
	
	/**
	 * 
	 * Class Name: IClassModuleVideoRelationService.java
	 * @Description: 根据模块id删除相关模块视频关系信息
	 * @author Keyn
	 * @date 2015-1-6 下午2:24:03
	 * @version 1.0
	 * @param moduleId
	 */
	void deleteByModuleId(String moduleId);
	
}