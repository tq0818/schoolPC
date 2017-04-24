package com.yuxin.wx.api.auth;

import java.util.List;

import com.yuxin.wx.model.auth.AuthPrivilege;
import com.yuxin.wx.model.auth.AuthPrivilegeCategory;

/**
 * Service Interface:AuthPrivilege
 * @author wang.zx
 * @date 2015-1-26
 */
public interface IAuthPrivilegeService  {
	/**
	 * 
	* @Title: saveAuthPrivilege
	* @Description: 新增AuthPrivilege
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-26
	* @user by wangzx
	 */
	void insert(AuthPrivilege T);
	
	/**
	 * 
	* @Title: batchSaveAuthPrivilege 
	* @Description: 批量新增AuthPrivilege
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-26
	* @user by wangzx
	 */
	void batchInsert(List<AuthPrivilege> T);
	
	/**
	 * 
	* @Title: updateAuthPrivilege 
	* @Description: 编辑AuthPrivilege
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-26
	* @user by wangzx
	 */
	void update(AuthPrivilege T);
	
	/**
	 * 
	* @Title: deleteAuthPrivilegeById 
	* @Description: 根据id删除AuthPrivilege
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-26
	* @user by wangzx
	 */
	void deleteAuthPrivilegeById(Integer id);
	
	/**
	 * 
	* @Title: deleteAuthPrivilegeByIds 
	* @Description: 根据id批量删除AuthPrivilege
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-26
	* @user by wangzx
	 */
	void deleteAuthPrivilegeByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findAuthPrivilegeById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-26
	* @user by wangzx
	 */
	AuthPrivilege findAuthPrivilegeById(Integer id);
	
	/**
	 * 
	* @Title: findAuthPrivilegeByPage 
	* @Description: 分页查询
	* @return
	* @return List<AuthPrivilege>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-26
	* @user by wangzx
	 */
	List<AuthPrivilege> findAuthPrivilegeByPage(AuthPrivilege search);
	
	/**
	 * 
	 * Class Name: IAuthPrivilegeService.java
	 * @Description: 获取权限子菜单
	 * @author Chopin
	 * @date 2015年2月2日
	 * @version 1.0
	 * @param categoryId
	 * @return
	 */
	List<AuthPrivilege> findAuthPrivilegeByCategory(Integer categoryId);
	/**
	 * 
	 * Class Name: IAuthPrivilegeService.java
	 * @Description: 根据公司Id查询
	 * @author ycl
	 * @date 2015-5-22 下午5:43:39
	 * @modifier
	 * @modify-date 2015-5-22 下午5:43:39
	 * @version 1.0
	 * @param companyId
	 * @return
	 */
	List<AuthPrivilege> findByCompanyId(Integer companyId);
	
	/**
	 * 
	 * Class Name: IAuthPrivilegeService.java
	 * @Description: 根据id查询权限名
	 * @author 周文斌
	 * @date 2015-8-20 下午1:52:07
	 * @version 1.0
	 * @param list
	 * @return
	 */
	List<String> findNameById(List<Integer> list);
	
}