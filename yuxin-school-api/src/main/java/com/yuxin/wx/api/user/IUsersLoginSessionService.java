package com.yuxin.wx.api.user;

import java.util.List;

import com.yuxin.wx.model.user.UsersLoginSession;
import com.yuxin.wx.vo.company.CompanyManageLoginHistoryVo;
/**
 * Service Interface:UsersLoginSession
 * @author chopin
 * @date 2015-7-27
 */
public interface IUsersLoginSessionService  {
	/**
	 * 
	* @Title: saveUsersLoginSession
	* @Description: 新增UsersLoginSession
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-27
	* @user by wangzx
	 */
	void insert(UsersLoginSession entity);
	
	/**
	 * 
	* @Title: batchSaveUsersLoginSession 
	* @Description: 批量新增UsersLoginSession
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-27
	* @user by wangzx
	 */
	void batchInsert(List<UsersLoginSession> list);
	
	/**
	 * 
	* @Title: updateUsersLoginSession 
	* @Description: 编辑UsersLoginSession
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-27
	* @user by wangzx
	 */
	void update(UsersLoginSession entity);
	
	/**
	 * 
	* @Title: deleteUsersLoginSessionById 
	* @Description: 根据id删除UsersLoginSession
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-27
	* @user by wangzx
	 */
	void deleteUsersLoginSessionById(Integer id);
	
	/**
	 * 
	* @Title: deleteUsersLoginSessionByIds 
	* @Description: 根据id批量删除UsersLoginSession
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-27
	* @user by wangzx
	 */
	void deleteUsersLoginSessionByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findUsersLoginSessionById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-27
	* @user by wangzx
	 */
	UsersLoginSession findUsersLoginSessionById(Integer id);
	
	/**
	 * 
	* @Title: findUsersLoginSessionByPage 
	* @Description: 分页查询
	* @return
	* @return List<UsersLoginSession>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-27
	* @user by wangzx
	 */
	List<UsersLoginSession> findUsersLoginSessionByPage(UsersLoginSession search);
	
	/**
	 * 
	* @Title: findUsersLoginSessionByPage 
	* @Description: 根据用户ID查询最后一次登录记录
	* @return
	* @return List<UsersLoginSession>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-27
	* @user by chopin
	 */
	public UsersLoginSession findHistoryByUserId(String userId);
	
	/**
	 * 
	 * Class Name: IUsersLoginSessionService.java
	 * @Description: 添加登录管理日志
	 * @author zhang.zx
	 * @date 2015年8月11日 下午12:41:31
	 * @modifier
	 * @modify-date 2015年8月11日 下午12:41:31
	 * @version 1.0
	 */
	void insertManageLoginHistory(CompanyManageLoginHistoryVo comlogin);
}