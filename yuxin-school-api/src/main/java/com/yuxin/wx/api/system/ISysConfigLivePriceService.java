package com.yuxin.wx.api.system;

import java.util.List;

import com.yuxin.wx.model.system.SysConfigLivePrice;
/**
 * Service Interface:SysConfigLivePrice
 * @author wang.zx
 * @date 2015-4-28
 */
public interface ISysConfigLivePriceService  {
	/**
	 * 
	* @Title: saveSysConfigLivePrice
	* @Description: 新增SysConfigLivePrice
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-28
	* @user by wangzx
	 */
	void insert(SysConfigLivePrice entity);
	
	/**
	 * 
	* @Title: batchSaveSysConfigLivePrice 
	* @Description: 批量新增SysConfigLivePrice
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-28
	* @user by wangzx
	 */
	void batchInsert(List<SysConfigLivePrice> list);
	
	/**
	 * 
	* @Title: updateSysConfigLivePrice 
	* @Description: 编辑SysConfigLivePrice
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-28
	* @user by wangzx
	 */
	void update(SysConfigLivePrice entity);
	
	/**
	 * 
	* @Title: deleteSysConfigLivePriceById 
	* @Description: 根据id删除SysConfigLivePrice
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-28
	* @user by wangzx
	 */
	void deleteSysConfigLivePriceById(Integer id);
	
	/**
	 * 
	* @Title: deleteSysConfigLivePriceByIds 
	* @Description: 根据id批量删除SysConfigLivePrice
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-28
	* @user by wangzx
	 */
	void deleteSysConfigLivePriceByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findSysConfigLivePriceById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-28
	* @user by wangzx
	 */
	SysConfigLivePrice findSysConfigLivePriceById(Integer id);
	
	/**
	 * 
	* @Title: findSysConfigLivePriceByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysConfigLivePrice>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-28
	* @user by wangzx
	 */
	List<SysConfigLivePrice> findSysConfigLivePriceByPage(SysConfigLivePrice search);
	
	/**
	 * 
	 * Class Name: ISysConfigLivePriceService.java
	 * @Description: 查询全部直播u价格信息
	 * @author zwb
	 * @date 2015-4-28 下午5:30:34
	 * @version 1.0
	 * @return
	 */
	List<SysConfigLivePrice> findList();
	
	/**
	 * @Description: 查询直播价格表中不同服务类型价格最低的对象
	 * @author wzx
	 * @date 2015-4-29 下午8:41:07
	 * @version 1.0
	 * @return
	 */
	List<SysConfigLivePrice> findLivesLowerEast();
}