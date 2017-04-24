package com.yuxin.wx.api.classes;

import java.util.List;

import com.yuxin.wx.model.classes.ClassPackageRelation;
/**
 * Service Interface:ClassPackageRelation
 * @author chopin
 * @date 2016-3-21
 */
public interface IClassPackageRelationService  {
	/**
	 * 
	* @Title: saveClassPackageRelation
	* @Description: 新增ClassPackageRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-21
	* @user by wangzx
	 */
	void insert(ClassPackageRelation entity);
	
	/**
	 * 
	* @Title: batchSaveClassPackageRelation 
	* @Description: 批量新增ClassPackageRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-21
	* @user by wangzx
	 */
	void batchInsert(List<ClassPackageRelation> list);
	
	/**
	 * 
	* @Title: updateClassPackageRelation 
	* @Description: 编辑ClassPackageRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-21
	* @user by wangzx
	 */
	void update(ClassPackageRelation entity);
	
	/**
	 * 
	* @Title: deleteClassPackageRelationById 
	* @Description: 根据id删除ClassPackageRelation
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-21
	* @user by wangzx
	 */
	void deleteClassPackageRelationById(Integer id);
	
	/**
	 * 
	* @Title: deleteClassPackageRelationByIds 
	* @Description: 根据id批量删除ClassPackageRelation
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-21
	* @user by wangzx
	 */
	void deleteClassPackageRelationByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findClassPackageRelationById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-21
	* @user by wangzx
	 */
	ClassPackageRelation findClassPackageRelationById(Integer id);
	
	/**
	 * 
	* @Title: findClassPackageRelationByPage 
	* @Description: 分页查询
	* @return
	* @return List<ClassPackageRelation>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-21
	* @user by wangzx
	 */
	List<ClassPackageRelation> findClassPackageRelationByPage(ClassPackageRelation search);
	
	/**
	 * 
	 * Class Name: IClassPackageRelationService.java
	 * @Description: 查询课程包和课程中关系列表
	 * @author zhang.zx
	 * @date 2016年3月23日 下午5:00:24
	 * @modifier
	 * @modify-date 2016年3月23日 下午5:00:24
	 * @version 1.0
	 * @param search
	 * @return
	 */
	List<ClassPackageRelation> queryClassPackageStageRelation(ClassPackageRelation search);
	/**
	 * 
	* @Title: findClassPackageRelationById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-21
	* @user by chopin
	 */
	List<ClassPackageRelation> findClassPackageRelations(ClassPackageRelation search);
}