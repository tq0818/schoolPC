package com.yuxin.wx.api.system;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.model.system.SysSmsLog;
/**
 * Service Interface:SysSmsLog
 * @author chopin
 * @date 2015-9-15
 */
public interface ISysSmsLogService  {
	/**
	 * 
	* @Title: saveSysSmsLog
	* @Description: 新增SysSmsLog
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-15
	* @user by wangzx
	 */
	void insert(SysSmsLog entity);
	
	/**
	 * 
	* @Title: batchSaveSysSmsLog 
	* @Description: 批量新增SysSmsLog
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-15
	* @user by wangzx
	 */
	void batchInsert(List<SysSmsLog> list);
	
	/**
	 * 
	* @Title: updateSysSmsLog 
	* @Description: 编辑SysSmsLog
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-15
	* @user by wangzx
	 */
	void update(SysSmsLog entity);
	
	/**
	 * 
	* @Title: deleteSysSmsLogById 
	* @Description: 根据id删除SysSmsLog
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-15
	* @user by wangzx
	 */
	void deleteSysSmsLogById(Integer id);
	
	/**
	 * 
	* @Title: deleteSysSmsLogByIds 
	* @Description: 根据id批量删除SysSmsLog
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-15
	* @user by wangzx
	 */
	void deleteSysSmsLogByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findSysSmsLogById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-15
	* @user by wangzx
	 */
	SysSmsLog findSysSmsLogById(Integer id);
	
	/**
	 * 
	* @Title: findSysSmsLogByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysSmsLog>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-15
	* @user by wangzx
	 */
	List<SysSmsLog> findSysSmsLogByPage(SysSmsLog search);
	
	/**
	 * 
	 * Class Name: ISysSmsLogService.java
	 * @Description: 查询是否有
	 * @author 周文斌
	 * @date 2016-6-1 下午6:40:31
	 * @version 1.0
	 * @param param
	 * @return
	 */
	Integer findByCompanyId(Map<String, Object> param);
}