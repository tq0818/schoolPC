package com.yuxin.wx.api.system;

import java.util.List;

import com.yuxin.wx.model.system.SysCcAccount;
/**
 * Service Interface:SysCcAccount
 * @author wang.zx
 * @date 2015-6-17
 */
public interface ISysCcAccountService  {
	/**
	 * 
	* @Title: saveSysCcAccount
	* @Description: 新增SysCcAccount
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-17
	* @user by wangzx
	 */
	void insert(SysCcAccount entity);
	
	/**
	 * 
	* @Title: batchSaveSysCcAccount 
	* @Description: 批量新增SysCcAccount
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-17
	* @user by wangzx
	 */
	void batchInsert(List<SysCcAccount> list);
	
	/**
	 * 
	* @Title: updateSysCcAccount 
	* @Description: 编辑SysCcAccount
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-17
	* @user by wangzx
	 */
	void update(SysCcAccount entity);
	
	/**
	 * 
	* @Title: deleteSysCcAccountById 
	* @Description: 根据id删除SysCcAccount
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-17
	* @user by wangzx
	 */
	void deleteSysCcAccountById(Integer id);
	
	/**
	 * 
	* @Title: deleteSysCcAccountByIds 
	* @Description: 根据id批量删除SysCcAccount
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-17
	* @user by wangzx
	 */
	void deleteSysCcAccountByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findSysCcAccountById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-17
	* @user by wangzx
	 */
	SysCcAccount findSysCcAccountById(Integer id);
	
	/**
	 * 
	* @Title: findSysCcAccountByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysCcAccount>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-17
	* @user by wangzx
	 */
	List<SysCcAccount> findSysCcAccountByPage(SysCcAccount search);
	/**
	 * 
	 * Class Name: ISysCcAccountService.java
	 * @Description: 查询use_status为0得一条数据
	 * @author yuchanglong
	 * @date 2015年6月17日 下午1:09:42
	 * @version 1.0
	 * @return
	 */
	SysCcAccount findOne();
}