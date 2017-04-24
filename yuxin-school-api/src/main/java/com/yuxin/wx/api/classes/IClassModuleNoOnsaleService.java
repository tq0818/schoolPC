package com.yuxin.wx.api.classes;

import java.util.List;

import com.yuxin.wx.model.classes.ClassModuleNoOnsale;
/**
 * Service Interface:ClassModuleNoOnsale
 * @author wang.zx
 * @date 2015-8-14
 */
public interface IClassModuleNoOnsaleService  {
	/**
	 * 
	* @Title: saveClassModuleNoOnsale
	* @Description: 新增ClassModuleNoOnsale
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-14
	* @user by wangzx
	 */
	void insert(ClassModuleNoOnsale entity);
	
	/**
	 * 
	* @Title: batchSaveClassModuleNoOnsale 
	* @Description: 批量新增ClassModuleNoOnsale
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-14
	* @user by wangzx
	 */
	void batchInsert(List<ClassModuleNoOnsale> list);
	
	/**
	 * 
	* @Title: updateClassModuleNoOnsale 
	* @Description: 编辑ClassModuleNoOnsale
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-14
	* @user by wangzx
	 */
	void update(ClassModuleNoOnsale entity);
	
	/**
	 * 
	* @Title: deleteClassModuleNoOnsaleById 
	* @Description: 根据id删除ClassModuleNoOnsale
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-14
	* @user by wangzx
	 */
	void deleteClassModuleNoOnsaleById(Integer id);
	
	/**
	 * 
	* @Title: deleteClassModuleNoOnsaleByIds 
	* @Description: 根据id批量删除ClassModuleNoOnsale
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-14
	* @user by wangzx
	 */
	void deleteClassModuleNoOnsaleByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findClassModuleNoOnsaleById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-14
	* @user by wangzx
	 */
	ClassModuleNoOnsale findClassModuleNoOnsaleById(Integer id);
	
	/**
	 * 
	* @Title: findClassModuleNoOnsaleByPage 
	* @Description: 分页查询
	* @return
	* @return List<ClassModuleNoOnsale>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-14
	* @user by wangzx
	 */
	List<ClassModuleNoOnsale> findClassModuleNoOnsaleByPage(ClassModuleNoOnsale search);
	
	/**
	 * 
	 * Class Name: IClassModuleNoOnsaleService.java
	 * @Description: 条件查询在售班号
	 * @author zhang.zx
	 * @date 2015年9月10日 下午8:40:06
	 * @modifier
	 * @modify-date 2015年9月10日 下午8:40:06
	 * @version 1.0
	 * @param moduleOnsale
	 * @return
	 */
	ClassModuleNoOnsale queryClassModuleOnSale(ClassModuleNoOnsale moduleOnsale);
	
	/**
	 * 
	 * Class Name: IClassModuleNoOnsaleService.java
	 * @Description: 根据班型和模块查询在售班号
	 * @author zhang.zx
	 * @date 2016年3月23日 下午7:43:22
	 * @modifier
	 * @modify-date 2016年3月23日 下午7:43:22
	 * @version 1.0
	 * @param moduleOnsale
	 * @return
	 */
	ClassModuleNoOnsale queryClassNoOnSale(ClassModuleNoOnsale moduleOnsale);

	ClassModuleNoOnsale queryModuleOnSaleModuleNo(Integer moduleId);
}