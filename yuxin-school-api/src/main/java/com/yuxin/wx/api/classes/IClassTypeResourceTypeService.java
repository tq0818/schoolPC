package com.yuxin.wx.api.classes;

import java.util.List;

import com.yuxin.wx.model.classes.ClassTypeResourceType;
/**
 * Service Interface:ClassTypeResourceType
 * @author wang.zx
 * @date 2015-8-11
 */
public interface IClassTypeResourceTypeService  {
	/**
	 * 
	* @Title: saveClassTypeResourceType
	* @Description: 新增ClassTypeResourceType
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-11
	* @user by wangzx
	 */
	void insert(ClassTypeResourceType entity);
	
	/**
	 * 
	* @Title: batchSaveClassTypeResourceType 
	* @Description: 批量新增ClassTypeResourceType
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-11
	* @user by wangzx
	 */
	void batchInsert(List<ClassTypeResourceType> list);
	
	/**
	 * 
	* @Title: updateClassTypeResourceType 
	* @Description: 编辑ClassTypeResourceType
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-11
	* @user by wangzx
	 */
	void update(ClassTypeResourceType entity);
	
	/**
	 * 
	* @Title: deleteClassTypeResourceTypeById 
	* @Description: 根据id删除ClassTypeResourceType
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-11
	* @user by wangzx
	 */
	void deleteClassTypeResourceTypeById(Integer id);
	
	/**
	 * 
	* @Title: deleteClassTypeResourceTypeByIds 
	* @Description: 根据id批量删除ClassTypeResourceType
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-11
	* @user by wangzx
	 */
	void deleteClassTypeResourceTypeByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findClassTypeResourceTypeById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-11
	* @user by wangzx
	 */
	ClassTypeResourceType findClassTypeResourceTypeById(Integer id);
	
	/**
	 * 
	* @Title: findClassTypeResourceTypeByPage 
	* @Description: 分页查询
	* @return
	* @return List<ClassTypeResourceType>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-11
	* @user by wangzx
	 */
	List<ClassTypeResourceType> findClassTypeResourceTypeByPage(ClassTypeResourceType search);
	
	/**
	 * 
	 * Class Name: IClassTypeResourceTypeService.java
	 * @Description: 根据公司id查询资源类型
	 * @author 周文斌
	 * @date 2015-8-11 下午5:49:56
	 * @version 1.0
	 * @param compnayId
	 * @return
	 */
	List<ClassTypeResourceType> findResourceTypeByCompanpyId(Integer companyId);
	
	/**
	 * 
	 * Class Name: IClassTypeResourceTypeService.java
	 * @Description: 查询是否存在
	 * @author 周文斌
	 * @date 2015-8-12 上午10:44:43
	 * @version 1.0
	 * @param ctrt
	 * @return
	 */
	ClassTypeResourceType findResourceTypeBy(ClassTypeResourceType ctrt);
}