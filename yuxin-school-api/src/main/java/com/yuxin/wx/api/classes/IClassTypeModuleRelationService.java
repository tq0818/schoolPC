package com.yuxin.wx.api.classes;

import java.util.HashMap;
import java.util.List;

import com.yuxin.wx.model.classes.ClassType;
import com.yuxin.wx.model.classes.ClassTypeModuleRelation;
import com.yuxin.wx.vo.classes.ClassTypeModuleRelationVo;
/**
 * Service Interface:ClassTypeModuleRelation
 * @author wang.zx
 * @date 2014-12-5
 */
public interface IClassTypeModuleRelationService  {
	/**
	 * 
	* @Title: saveClassTypeModuleRelation
	* @Description: 新增ClassTypeModuleRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void insert(ClassTypeModuleRelation classTypeModuleRelation);
	
	/**
	 * 
	* @Title: batchSaveClassTypeModuleRelation 
	* @Description: 批量新增ClassTypeModuleRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void batchInsert(List<ClassTypeModuleRelation> classTypeModuleRelation);
	
	/**
	 * 
	* @Title: updateClassTypeModuleRelation 
	* @Description: 编辑ClassTypeModuleRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void update(ClassTypeModuleRelation classTypeModuleRelation);
	
	/**
	 * 
	* @Title: deleteClassTypeModuleRelationById 
	* @Description: 根据id删除ClassTypeModuleRelation
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void deleteClassTypeModuleRelationById(Integer id);
	
	/**
	 * 
	* @Title: deleteClassTypeModuleRelationByIds 
	* @Description: 根据id批量删除ClassTypeModuleRelation
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void deleteClassTypeModuleRelationByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findClassTypeModuleRelationById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	ClassTypeModuleRelation findClassTypeModuleRelationById(Integer id);
	
	/**
	 * 
	* @Title: findClassTypeModuleRelationByPage 
	* @Description: 分页查询
	* @return
	* @return List<ClassTypeModuleRelation>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	List<ClassTypeModuleRelation> findClassTypeModuleRelationByPage(ClassTypeModuleRelation search);
	
	/**
	 * 
	 * Class Name: IClassTypeModuleRelationService.java
	 * @Description: 根据班型id删除相关联 的模块
	 * @author Keyn
	 * @date 2015-1-5 下午3:38:39
	 * @version 1.0
	 * @param classTypeId
	 */
	public void deleteByClassTypeId(String classTypeId);
	
	/**
	 * 
	 * Class Name: IClassTypeModuleRelationService.java
	 * @Description: 根据班型id 查询模块id
	 * @author 周文斌
	 * @date 2015-5-4 下午5:44:49
	 * @version 1.0
	 * @param classTypeId
	 * @return
	 */
	List<Integer> findModelIdByClassTypeId(List<ClassType> classTypes);
	
	/**
	 * 
	 * Class Name: IClassTypeModuleRelationService.java
	 * @Description: 根据班型更新模块
	 * @author zhang.zx
	 * @date 2015-5-4 下午5:44:49
	 * @version 1.0
	 * @param classTypeId
	 * @return
	 */
	void updateModuleByClassTypeId(ClassTypeModuleRelation classTypeModuleRelation);
	
	/**
	 * @Description: 根据条件查询所有的模块关联信息
	 * @author wzx
	 * @date 2015-6-30 下午3:15:18
	 * @version 1.0
	 * @param map
	 * @return
	 */
	List<ClassTypeModuleRelationVo> findClassModuleRelationByClassTypeId(HashMap map);
	
	/**
	 * 
	 * Class Name: IClassTypeModuleRelationService.java
	 * @Description: 根据ClassTypeID查询全部ModuleID
	 * @author dongshuai
	 * @date 2016年8月31日 下午5:02:48
	 * @version 1.0
	 * @param id
	 * @return
	 */
	List<Integer> findClassModuleIdsByClassTypeId(Integer id);
}