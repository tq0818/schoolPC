package com.yuxin.wx.api.classes;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.model.classes.ClassPackage;
import com.yuxin.wx.model.classes.ClassPackageCategory;
/**
 * Service Interface:ClassPackageCategory
 * @author chopin
 * @date 2016-3-21
 */
public interface IClassPackageCategoryService  {
	/**
	 * 
	* @Title: saveClassPackageCategory
	* @Description: 新增ClassPackageCategory
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-21
	* @user by wangzx
	 */
	void insert(ClassPackageCategory entity);
	
	/**
	 * 
	* @Title: batchSaveClassPackageCategory 
	* @Description: 批量新增ClassPackageCategory
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-21
	* @user by wangzx
	 */
	void batchInsert(List<ClassPackageCategory> list);
	
	/**
	 * 
	* @Title: updateClassPackageCategory 
	* @Description: 编辑ClassPackageCategory
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-21
	* @user by wangzx
	 */
	void update(ClassPackageCategory entity);
	
	/**
	 * 
	* @Title: deleteClassPackageCategoryById 
	* @Description: 根据id删除ClassPackageCategory
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-21
	* @user by wangzx
	 */
	void deleteClassPackageCategoryById(Integer id);
	
	/**
	 * 
	* @Title: deleteClassPackageCategoryByIds 
	* @Description: 根据id批量删除ClassPackageCategory
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-21
	* @user by wangzx
	 */
	void deleteClassPackageCategoryByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findClassPackageCategoryById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-21
	* @user by wangzx
	 */
	ClassPackageCategory findClassPackageCategoryById(Integer id);
	
	/**
	 * 
	* @Title: findClassPackageCategoryByPage 
	* @Description: 分页查询
	* @return
	* @return List<ClassPackageCategory>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-21
	* @user by wangzx
	 */
	List<ClassPackageCategory> findClassPackageCategoryByPage(ClassPackageCategory search);
	
	/**
	 * 
	 * Class Name: IClassPackageCategoryService.java
	 * @Description: 条件查询分类列表
	 * @author zhang.zx
	 * @date 2016年3月21日 下午5:19:26
	 * @modifier
	 * @modify-date 2016年3月21日 下午5:19:26
	 * @version 1.0
	 * @param search
	 * @return
	 */
	List<ClassPackageCategory> queryClassCategoryLists(ClassPackageCategory search);
	
	/**
	 * 
	 * Class Name: IClassPackageCategoryService.java
	 * @Description: 查询分类下最大id
	 * @author zhang.zx
	 * @date 2016年3月21日 下午5:20:40
	 * @modifier
	 * @modify-date 2016年3月21日 下午5:20:40
	 * @version 1.0
	 * @param search
	 * @return
	 */
	Integer queryMaxIdByCondition(ClassPackageCategory search);
	
	/**
	 * 
	 * Class Name: IClassPackageCategoryService.java
	 * @Description: 查询分类下是否还存在课程包
	 * @author zhang.zx
	 * @date 2016年3月21日 下午7:03:36
	 * @modifier
	 * @modify-date 2016年3月21日 下午7:03:36
	 * @version 1.0
	 * @param map
	 * @return
	 */
	Integer queryIsExistClassPackage(ClassPackage search);
	/**
	 * 
	 * Class Name: IClassPackageCategoryService.java
	 * @Description: 查询所有分类
	 * @author chopin.sun
	 * @date 2016年3月21日 下午7:03:36
	 * @modifier
	 * @modify-date 2016年3月21日 下午7:03:36
	 * @version 1.0
	 * @param map
	 * @return
	 */
	public List<ClassPackageCategory> findAll(Integer companyId);
	
	/**
	 * 
	 * Class Name: IClassPackageCategoryService.java
	 * @Description: 条件查询某个类型(companyId and code)
	 * @author zhang.zx
	 * @date 2016年4月6日 下午1:42:24
	 * @modifier
	 * @modify-date 2016年4月6日 下午1:42:24
	 * @version 1.0
	 * @param search
	 * @return
	 */
	List<ClassPackageCategory> queryClassCategoryCodeByWhere(ClassPackageCategory search);
	
	ClassPackageCategory queryClassPackageCategoryByCode(ClassPackageCategory search);
}