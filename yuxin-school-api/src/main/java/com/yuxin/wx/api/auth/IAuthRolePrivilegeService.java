package com.yuxin.wx.api.auth;

import java.util.List;
import java.util.Set;

import com.yuxin.wx.model.auth.AuthPrivilege;
import com.yuxin.wx.model.auth.AuthRole;
import com.yuxin.wx.model.auth.AuthRolePrivilege;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.vo.privilege.PrivilegeVo;
import com.yuxin.wx.vo.privilege.TreeNode;

/**
 * Service Interface:AuthRolePrivilege
 * @author wang.zx
 * @date 2015-1-26
 */
public interface IAuthRolePrivilegeService  {
	/**
	 * 
	* @Title: saveAuthRolePrivilege
	* @Description: 新增AuthRolePrivilege
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-26
	* @user by wangzx
	 */
	void insert(AuthRolePrivilege T);
	
	/**
	 * 
	* @Title: batchSaveAuthRolePrivilege 
	* @Description: 批量新增AuthRolePrivilege
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-26
	* @user by wangzx
	 */
	void batchInsert(List<AuthRolePrivilege> T);
	
	/**
	 * 
	* @Title: updateAuthRolePrivilege 
	* @Description: 编辑AuthRolePrivilege
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-26
	* @user by wangzx
	 */
	void update(AuthRolePrivilege T);
	
	/**
	 * 
	* @Title: deleteAuthRolePrivilegeById 
	* @Description: 根据id删除AuthRolePrivilege
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-26
	* @user by wangzx
	 */
	void deleteAuthRolePrivilegeById(Integer id);
	
	/**
	 * 
	* @Title: deleteAuthRolePrivilegeByIds 
	* @Description: 根据id批量删除AuthRolePrivilege
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-26
	* @user by wangzx
	 */
	void deleteAuthRolePrivilegeByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findAuthRolePrivilegeById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-26
	* @user by wangzx
	 */
	AuthRolePrivilege findAuthRolePrivilegeById(Integer id);
	
	/**
	 * 
	* @Title: findAuthRolePrivilegeByPage 
	* @Description: 分页查询
	* @return
	* @return List<AuthRolePrivilege>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-26
	* @user by wangzx
	 */
	List<AuthRolePrivilege> findAuthRolePrivilegeByPage(AuthRolePrivilege search);
	
	/**
	 * 
	 * Class Name: IAuthRolePrivilegeService.java
	 * @Description: 获取角色下的权限列表
	 * @author Chopin
	 * @date 2015年2月2日
	 * @version 1.0
	 * @param roleId
	 * @return
	 */
	List<AuthRolePrivilege> findPrivilegeByRole(String roleId);
	
	
 /**
  * 
  * Class Name: IAuthRolePrivilegeService.java
  * @Description: 保存角色权限配置
  * @author Chopin
  * @date 2015年2月2日
  * @version 1.0
  * @param roleId
  * @return
  */
	String saveRolePrivilege(List<TreeNode> nodes,String roleId,Users loginUser);
	
	/**
	 * 
	 * Class Name: IAuthRolePrivilegeService.java
	 * @Description: 查询角色菜单
	 * @author zhang.zx
	 * @date 2015年5月19日 下午6:01:28
	 * @modifier
	 * @modify-date 2015年5月19日 下午6:01:28
	 * @version 1.0
	 * @param roleId
	 * @return
	 */
	List<PrivilegeVo> findUserPrivileges(String roleId);
	List<PrivilegeVo> findNewUserPrivileges(String roleId);
	
	
}