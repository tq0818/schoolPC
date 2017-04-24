package com.yuxin.wx.api.resource;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.model.resource.ResourceUseRecord;
/**
 * Service Interface:ResourceUseRecord
 * @author wang.zx
 * @date 2016-9-1
 */
public interface IResourceUseRecordService  {
	/**
	 * 
	* @Title: saveResourceUseRecord
	* @Description: 新增ResourceUseRecord
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by wangzx
	 */
	void insert(ResourceUseRecord entity);
	
	/**
	 * 
	* @Title: batchSaveResourceUseRecord 
	* @Description: 批量新增ResourceUseRecord
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by wangzx
	 */
	void batchInsert(List<ResourceUseRecord> list);
	
	/**
	 * 
	* @Title: updateResourceUseRecord 
	* @Description: 编辑ResourceUseRecord
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by wangzx
	 */
	void update(ResourceUseRecord entity);
	
	/**
	 * 
	* @Title: deleteResourceUseRecordById 
	* @Description: 根据id删除ResourceUseRecord
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by wangzx
	 */
	void deleteResourceUseRecordById(Integer id);
	
	/**
	 * 
	* @Title: deleteResourceUseRecordByIds 
	* @Description: 根据id批量删除ResourceUseRecord
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by wangzx
	 */
	void deleteResourceUseRecordByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findResourceUseRecordById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by wangzx
	 */
	ResourceUseRecord findResourceUseRecordById(Integer id);
	
	/**
	 * 
	* @Title: findResourceUseRecordByPage 
	* @Description: 分页查询
	* @return
	* @return List<ResourceUseRecord>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by wangzx
	 */
	List<ResourceUseRecord> findResourceUseRecordByPage(ResourceUseRecord search);
	
	/**
	 * 
	 * Class Name: IResourceUseRecordService.java
	 * @Description: 查询七牛流量使用情况
	 * @author 周文斌
	 * @date 2016-9-2 上午11:11:54
	 * @modify	2016-9-2 上午11:11:54
	 * @version 
	 * @param param
	 * @return
	 */
	String findUseSumFlow(Map<String, Object> param);
}