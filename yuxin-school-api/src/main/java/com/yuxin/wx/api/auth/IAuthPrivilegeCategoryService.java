package com.yuxin.wx.api.auth;

import java.util.List;

import com.yuxin.wx.model.auth.AuthPrivilegeCategory;
import com.yuxin.wx.vo.privilege.PrivilegeListVo;

/**
 * Service Interface:AuthPrivilegeCategory
 * @author wang.zx
 * @date 2015-1-26
 */
public interface IAuthPrivilegeCategoryService  {
	/**
	 * 
	* @Title: saveAuthPrivilegeCategory
	* @Description: 新增AuthPrivilegeCategory
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-26
	* @user by wangzx
	 */
	void insert(AuthPrivilegeCategory T);
	
	/**
	 * 
	* @Title: batchSaveAuthPrivilegeCategory 
	* @Description: 批量新增AuthPrivilegeCategory
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-26
	* @user by wangzx
	 */
	void batchInsert(List<AuthPrivilegeCategory> T);
	
	/**
	 * 
	* @Title: updateAuthPrivilegeCategory 
	* @Description: 编辑AuthPrivilegeCategory
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-26
	* @user by wangzx
	 */
	void update(AuthPrivilegeCategory T);
	
	/**
	 * 
	* @Title: deleteAuthPrivilegeCategoryById 
	* @Description: 根据id删除AuthPrivilegeCategory
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-26
	* @user by wangzx
	 */
	void deleteAuthPrivilegeCategoryById(Integer id);
	
	/**
	 * 
	* @Title: deleteAuthPrivilegeCategoryByIds 
	* @Description: 根据id批量删除AuthPrivilegeCategory
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-26
	* @user by wangzx
	 */
	void deleteAuthPrivilegeCategoryByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findAuthPrivilegeCategoryById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-26
	* @user by wangzx
	 */
	AuthPrivilegeCategory findAuthPrivilegeCategoryById(Integer id);
	
	/**
	 * 
	* @Title: findAuthPrivilegeCategoryByPage 
	* @Description: 分页查询
	* @return
	* @return List<AuthPrivilegeCategory>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-26
	* @user by wangzx
	 */
	List<AuthPrivilegeCategory> findAuthPrivilegeCategoryByPage(AuthPrivilegeCategory search);
	
 /**
  * 
 * @Title: findAuthPrivilegeCategoryByPage 
 * @Description: 查询所有信息
 * @return
 * @return List<AuthPrivilegeCategory>    返回类型 
 * @throws 
 * @exception 
 * @date 2015-1-26
 * @user by chopin
  */
	List<AuthPrivilegeCategory> finAll();
	
	/**
	 * 
	 * Class Name: IAuthPrivilegeCategoryService.java
	 * @Description: 查询菜单列表
	 * @author zhang.zx
	 * @date 2015年5月19日 下午3:36:39
	 * @modifier
	 * @modify-date 2015年5月19日 下午3:36:39
	 * @version 1.0
	 * @return
	 */
	List<PrivilegeListVo> queryOnePrivilege(PrivilegeListVo privilege);
	/**
	 * 
	 * Class Name: IAuthPrivilegeCategoryService.java
	 * @Description: 根据公司Id查询
	 * @author ycl
	 * @date 2015-5-22 下午5:54:34
	 * @modifier
	 * @modify-date 2015-5-22 下午5:54:34
	 * @version 1.0
	 * @param companyId
	 * @return
	 */
	List<AuthPrivilegeCategory> findByCompanyId(Integer companyId);
	
	List<PrivilegeListVo> queryOnePrivilegeByCompanys(PrivilegeListVo privilege);
	
}