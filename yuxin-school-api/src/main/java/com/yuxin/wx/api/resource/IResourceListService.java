package com.yuxin.wx.api.resource;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.model.resource.ResourceList;
/**
 * Service Interface:ResourceList
 * @author wang.zx
 * @date 2016-9-1
 */
public interface IResourceListService  {
	/**
	 * 
	* @Title: saveResourceList
	* @Description: 新增ResourceList
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by wangzx
	 */
	void insert(ResourceList entity);
	
	/**
	 * 
	* @Title: batchSaveResourceList 
	* @Description: 批量新增ResourceList
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by wangzx
	 */
	void batchInsert(List<ResourceList> list);
	
	/**
	 * 
	* @Title: updateResourceList 
	* @Description: 编辑ResourceList
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by wangzx
	 */
	void update(ResourceList entity);
	
	/**
	 * 
	* @Title: deleteResourceListById 
	* @Description: 根据id删除ResourceList
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by wangzx
	 */
	void deleteResourceListById(Integer id);
	
	/**
	 * 
	* @Title: deleteResourceListByIds 
	* @Description: 根据id批量删除ResourceList
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by wangzx
	 */
	void deleteResourceListByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findResourceListById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by wangzx
	 */
	ResourceList findResourceListById(Integer id);
	
	/**
	 * 
	* @Title: findResourceListByPage 
	* @Description: 分页查询
	* @return
	* @return List<ResourceList>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by wangzx
	 */
	List<ResourceList> findResourceListByPage(ResourceList search);
	
	/**
	 * 
	 * Class Name: IResourceListService.java
	 * @Description: 查询七牛存储空间每天使用情况
	 * @author 周文斌
	 * @date 2016-9-2 上午10:54:58
	 * @modify	2016-9-2 上午10:54:58
	 * @version 
	 * @param id
	 * @return
	 */
	String findUseSumStroage(Map<String, Object> param);
	
	Integer totalCount(ResourceList search);
	
	/**
	 * 
	 * Class Name: IResourceListService.java
	 * @Description: 查询filepath部位null 的
	 * @author 周文斌
	 * @date 2016-11-2 上午11:40:10
	 * @modify	2016-11-2 上午11:40:10
	 * @version 
	 * @param search
	 * @return
	 */
	List<ResourceList> pageByFilePath(ResourceList search);
	
	Integer pageByFilePathCount(ResourceList search);

	ResourceList findById(ResourceList resourceId);
}