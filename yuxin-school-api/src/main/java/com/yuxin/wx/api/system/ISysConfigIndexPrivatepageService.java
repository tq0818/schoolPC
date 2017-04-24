package com.yuxin.wx.api.system;

import java.util.List;

import com.yuxin.wx.model.system.SysConfigIndexPrivatepage;
import com.yuxin.wx.vo.system.SysConfigPrivatePageVo;
/**
 * Service Interface:SysConfigIndexPrivatepage
 * @author chopin
 * @date 2015-3-17
 */
public interface ISysConfigIndexPrivatepageService  {
	/**
	 * 
	* @Title: saveSysConfigIndexPrivatepage
	* @Description: 新增SysConfigIndexPrivatepage
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by wangzx
	 */
	void insert(SysConfigIndexPrivatepage entity);
	
	/**
	 * 
	* @Title: batchSaveSysConfigIndexPrivatepage 
	* @Description: 批量新增SysConfigIndexPrivatepage
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by wangzx
	 */
	void batchInsert(List<SysConfigIndexPrivatepage> list);
	
	/**
	 * 
	* @Title: updateSysConfigIndexPrivatepage 
	* @Description: 编辑SysConfigIndexPrivatepage
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by wangzx
	 */
	void update(SysConfigIndexPrivatepage entity);
	
	/**
	 * 
	* @Title: deleteSysConfigIndexPrivatepageById 
	* @Description: 根据id删除SysConfigIndexPrivatepage
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by wangzx
	 */
	void deleteSysConfigIndexPrivatepageById(Integer id);
	
	/**
	 * 
	* @Title: deleteSysConfigIndexPrivatepageByIds 
	* @Description: 根据id批量删除SysConfigIndexPrivatepage
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by wangzx
	 */
	void deleteSysConfigIndexPrivatepageByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findSysConfigIndexPrivatepageById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by wangzx
	 */
	SysConfigIndexPrivatepage findSysConfigIndexPrivatepageById(Integer id);
	
	/**
	 * 
	* @Title: findSysConfigIndexPrivatepageByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysConfigIndexPrivatepage>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by wangzx
	 */
	List<SysConfigIndexPrivatepage> findSysConfigIndexPrivatepageByPage(SysConfigIndexPrivatepage search);
	
	/**
	 * 
	* @Title: findSysConfigIndexPrivatepageByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysConfigIndexPrivatepage>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by chopin
	 */
	List<SysConfigIndexPrivatepage> findSchoolConfigData(Integer schoolId);
	

	/**
	 * 
	* @Title: findSysConfigIndexPrivatepageByPage 
	* @Description: 查询所有信息
	* @return
	* @return List<SysConfigIndexPrivatepage>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by chopin
	 */
	List<SysConfigIndexPrivatepage> findAll();
	
	List<SysConfigPrivatePageVo> findList(SysConfigIndexPrivatepage search);
	
	/**
	 * 
	* @Title: findSysConfigIndexPrivatepageByPage 
	* @Description: 查询分校首页配置数据
	* @return
	* @return List<SysConfigIndexPrivatepage>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by chopin
	 */
	public List<SysConfigIndexPrivatepage> findList2(SysConfigIndexPrivatepage search);
}