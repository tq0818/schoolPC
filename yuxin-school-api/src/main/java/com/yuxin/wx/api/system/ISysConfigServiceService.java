package com.yuxin.wx.api.system;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.model.system.SysConfigService;
/**
 * Service Interface:SysConfigService
 * @author chopin
 * @date 2015-8-12
 */
public interface ISysConfigServiceService  {
	/**
	 * 
	* @Title: saveSysConfigService
	* @Description: 新增SysConfigService
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-12
	* @user by wangzx
	 */
	void insert(SysConfigService entity);
	
	/**
	 * 
	* @Title: batchSaveSysConfigService 
	* @Description: 批量新增SysConfigService
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-12
	* @user by wangzx
	 */
	void batchInsert(List<SysConfigService> list);
	
	/**
	 * 
	* @Title: updateSysConfigService 
	* @Description: 编辑SysConfigService
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-12
	* @user by wangzx
	 */
	void update(SysConfigService entity);
	
	/**
	 * 
	* @Title: deleteSysConfigServiceById 
	* @Description: 根据id删除SysConfigService
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-12
	* @user by wangzx
	 */
	void deleteSysConfigServiceById(Integer id);
	
	/**
	 * 
	* @Title: deleteSysConfigServiceByIds 
	* @Description: 根据id批量删除SysConfigService
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-12
	* @user by wangzx
	 */
	void deleteSysConfigServiceByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findSysConfigServiceById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-12
	* @user by wangzx
	 */
	SysConfigService findSysConfigServiceById(Integer id);
	
	/**
	 * 
	* @Title: findSysConfigServiceByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysConfigService>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-12
	* @user by wangzx
	 */
	List<SysConfigService> findSysConfigServiceByPage(SysConfigService search);
	/**
	 * 
	* @Title: findSysConfigServiceByPage 
	* @Description: 根据条件查询列表，不分页
	* @return
	* @return List<SysConfigService>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-12
	* @user by chopin
	 */
	public List<SysConfigService> findSysConfigServiceByCompany(SysConfigService search);

	/**
	 * 
	 * Class Name: ISysConfigServiceService.java
	 * @Description: 查询是否存在
	 * @author 周文斌
	 * @date 2015-8-13 上午11:53:07
	 * @version 1.0
	 * @param param
	 * @return
	 */
	SysConfigService findByCodeId(Map<String, Object> param);
	
	/**
	 * 
	 * Class Name: ISysConfigServiceService.java
	 * @Description: 查询这个公司已开通服务
	 * @author 周文斌
	 * @date 2015-8-13 下午12:20:05
	 * @version 1.0
	 * @param companyId
	 * @return
	 */
	List<SysConfigService> findByCompanyId(Integer companyId);
	
	/**
	 * 
	 * Class Name: ISysConfigServiceService.java
	 * @Description: 查询是否存在
	 * @author 周文斌
	 * @date 2015-8-17 下午9:18:56
	 * @version 1.0
	 * @param serv
	 * @return
	 */
	SysConfigService findExist(SysConfigService serv);
}