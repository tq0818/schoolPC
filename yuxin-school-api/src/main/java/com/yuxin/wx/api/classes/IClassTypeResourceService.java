package com.yuxin.wx.api.classes;

import java.util.List;

import com.yuxin.wx.model.classes.ClassTypeResource;
import com.yuxin.wx.vo.classes.ClassTypeResourceVo;
/**
 * Service Interface:ClassTypeResource
 * @author wang.zx
 * @date 2015-8-11
 */
public interface IClassTypeResourceService  {
	/**
	 * 
	* @Title: saveClassTypeResource
	* @Description: 新增ClassTypeResource
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-11
	* @user by wangzx
	 */
	void insert(ClassTypeResource entity);
	
	/**
	 * 
	* @Title: batchSaveClassTypeResource 
	* @Description: 批量新增ClassTypeResource
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-11
	* @user by wangzx
	 */
	void batchInsert(List<ClassTypeResource> list);
	
	/**
	 * 
	* @Title: updateClassTypeResource 
	* @Description: 编辑ClassTypeResource
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-11
	* @user by wangzx
	 */
	void update(ClassTypeResource entity);
	
	/**
	 * 
	* @Title: deleteClassTypeResourceById 
	* @Description: 根据id删除ClassTypeResource
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-11
	* @user by wangzx
	 */
	void deleteClassTypeResourceById(Integer id);
	
	/**
	 * 
	* @Title: deleteClassTypeResourceByIds 
	* @Description: 根据id批量删除ClassTypeResource
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-11
	* @user by wangzx
	 */
	void deleteClassTypeResourceByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findClassTypeResourceById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-11
	* @user by wangzx
	 */
	ClassTypeResource findClassTypeResourceById(Integer id);
	
	/**
	 * 
	* @Title: findClassTypeResourceByPage 
	* @Description: 分页查询
	* @return
	* @return List<ClassTypeResource>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-11
	* @user by wangzx
	 */
	List<ClassTypeResource> findClassTypeResourceByPage(ClassTypeResource search);
	
	/**
	 * 
	 * Class Name: IClassTypeResourceService.java
	 * @Description: 分页查询列表
	 * @author 周文斌
	 * @date 2015-8-11 下午6:29:39
	 * @version 1.0
	 * @param res
	 * @return
	 */
	List<ClassTypeResourceVo> findResBy(ClassTypeResource res);
	
	/**
	 * 
	 * Class Name: IClassTypeResourceService.java
	 * @Description: 查询总数
	 * @author 周文斌
	 * @date 2015-8-11 下午6:32:49
	 * @version 1.0
	 * @param res
	 * @return
	 */
	Integer findResCountBy(ClassTypeResource res);
	
	/**
	 * 
	 * Class Name: IClassTypeResourceService.java
	 * @Description: 条件查询资料信息
	 * @author zhang.zx
	 * @date 2015年9月8日 下午7:06:40
	 * @modifier
	 * @modify-date 2015年9月8日 下午7:06:40
	 * @version 1.0
	 * @param res
	 * @return
	 */
	List<ClassTypeResourceVo> findResByCondition(ClassTypeResource res);
}