package com.yuxin.wx.api.system;

import java.util.List;

import com.yuxin.wx.model.system.SysServiceDredgeConfig;
import com.yuxin.wx.vo.system.SysServiceDredgeVo;
/**
 * Service Interface:SysServiceDredgeConfig
 * @author wang.zx
 * @date 2016-12-12
 */
public interface ISysServiceDredgeConfigService  {
	/**
	 * 
	* @Title: saveSysServiceDredgeConfig
	* @Description: 新增SysServiceDredgeConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-12
	* @user by wangzx
	 */
	void insert(SysServiceDredgeConfig entity);
	
	/**
	 * 
	* @Title: batchSaveSysServiceDredgeConfig 
	* @Description: 批量新增SysServiceDredgeConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-12
	* @user by wangzx
	 */
	void batchInsert(List<SysServiceDredgeConfig> list);
	
	/**
	 * 
	* @Title: updateSysServiceDredgeConfig 
	* @Description: 编辑SysServiceDredgeConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-12
	* @user by wangzx
	 */
	void update(SysServiceDredgeConfig entity);
	
	/**
	 * 
	* @Title: deleteSysServiceDredgeConfigById 
	* @Description: 根据id删除SysServiceDredgeConfig
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-12
	* @user by wangzx
	 */
	void deleteSysServiceDredgeConfigById(Integer id);
	
	/**
	 * 
	* @Title: deleteSysServiceDredgeConfigByIds 
	* @Description: 根据id批量删除SysServiceDredgeConfig
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-12
	* @user by wangzx
	 */
	void deleteSysServiceDredgeConfigByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findSysServiceDredgeConfigById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-12
	* @user by wangzx
	 */
	SysServiceDredgeConfig findSysServiceDredgeConfigById(Integer id);
	
	/**
	 * 
	* @Title: findSysServiceDredgeConfigByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysServiceDredgeConfig>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-12
	* @user by wangzx
	 */
	List<SysServiceDredgeConfig> findSysServiceDredgeConfigByPage(SysServiceDredgeConfig search);
	
	/**
	 * 
	 * Class Name: ISysServiceDredgeConfigService.java
	 * @Description: 查询是否开启
	 * @author 周文斌
	 * @date 2016-12-12 下午7:28:23
	 * @modify	2016-12-12 下午7:28:23
	 * @version 
	 * @param companyId
	 * @return
	 */
	List<SysServiceDredgeVo> findDredgeByCom(Integer companyId);
}