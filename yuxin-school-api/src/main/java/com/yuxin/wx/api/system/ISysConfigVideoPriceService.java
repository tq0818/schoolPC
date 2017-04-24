package com.yuxin.wx.api.system;

import java.util.List;

import com.yuxin.wx.model.system.SysConfigVideoPrice;
/**
 * Service Interface:SysConfigVideoPrice
 * @author wang.zx
 * @date 2015-4-28
 */
public interface ISysConfigVideoPriceService  {
	/**
	 * 
	* @Title: saveSysConfigVideoPrice
	* @Description: 新增SysConfigVideoPrice
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-28
	* @user by wangzx
	 */
	void insert(SysConfigVideoPrice entity);
	
	/**
	 * 
	* @Title: batchSaveSysConfigVideoPrice 
	* @Description: 批量新增SysConfigVideoPrice
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-28
	* @user by wangzx
	 */
	void batchInsert(List<SysConfigVideoPrice> list);
	
	/**
	 * 
	* @Title: updateSysConfigVideoPrice 
	* @Description: 编辑SysConfigVideoPrice
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-28
	* @user by wangzx
	 */
	void update(SysConfigVideoPrice entity);
	
	/**
	 * 
	* @Title: deleteSysConfigVideoPriceById 
	* @Description: 根据id删除SysConfigVideoPrice
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-28
	* @user by wangzx
	 */
	void deleteSysConfigVideoPriceById(Integer id);
	
	/**
	 * 
	* @Title: deleteSysConfigVideoPriceByIds 
	* @Description: 根据id批量删除SysConfigVideoPrice
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-28
	* @user by wangzx
	 */
	void deleteSysConfigVideoPriceByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findSysConfigVideoPriceById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-28
	* @user by wangzx
	 */
	SysConfigVideoPrice findSysConfigVideoPriceById(Integer id);
	
	/**
	 * 
	* @Title: findSysConfigVideoPriceByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysConfigVideoPrice>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-28
	* @user by wangzx
	 */
	List<SysConfigVideoPrice> findSysConfigVideoPriceByPage(SysConfigVideoPrice search);
	/**
	 * 
	 * Class Name: ISysConfigVideoPriceService.java
	 * @Description: 查询全部
	 * @author ycl
	 * @date 2015-4-28 下午3:59:54
	 * @modifier
	 * @modify-date 2015-4-28 下午3:59:54
	 * @version 1.0
	 * @return
	 */
	List<SysConfigVideoPrice> findSysConfigVideoPriceList();
	
	/**
	 * @Description: 查询录播标准价格表同服务级别中价格最低的配置
	 * @author wzx
	 * @date 2015-4-28 下午8:54:51
	 * @version 1.0
	 * @return
	 */
	List<SysConfigVideoPrice> findSysConfigVideoPriceLowerEastWithSameService();	
}