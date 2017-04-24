package com.yuxin.wx.api.user;

import java.util.List;

import com.yuxin.wx.model.user.UserIntegralFlow;
import com.yuxin.wx.vo.user.UserIntegralFlowVO;
/**
 * Service Interface:UserIntegralFlow
 * @author chopin
 * @date 2016-5-17
 */
public interface IUserIntegralFlowService  {
	/**
	 * 
	* @Title: saveUserIntegralFlow
	* @Description: 新增UserIntegralFlow
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by wangzx
	 */
	void insert(UserIntegralFlow entity);
	
	/**
	 * 
	* @Title: batchSaveUserIntegralFlow 
	* @Description: 批量新增UserIntegralFlow
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by wangzx
	 */
	void batchInsert(List<UserIntegralFlow> list);
	
	/**
	 * 
	* @Title: updateUserIntegralFlow 
	* @Description: 编辑UserIntegralFlow
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by wangzx
	 */
	void update(UserIntegralFlow entity);
	
	/**
	 * 
	* @Title: deleteUserIntegralFlowById 
	* @Description: 根据id删除UserIntegralFlow
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by wangzx
	 */
	void deleteUserIntegralFlowById(Integer id);
	
	/**
	 * 
	* @Title: deleteUserIntegralFlowByIds 
	* @Description: 根据id批量删除UserIntegralFlow
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by wangzx
	 */
	void deleteUserIntegralFlowByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findUserIntegralFlowById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by wangzx
	 */
	UserIntegralFlow findUserIntegralFlowById(Integer id);
	
	/**
	 * 
	* @Title: findUserIntegralFlowByPage 
	* @Description: 分页查询
	* @return
	* @return List<UserIntegralFlow>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by wangzx
	 */
	List<UserIntegralFlow> findUserIntegralFlowByPage(UserIntegralFlow search);
	
	/**
	 * 
	 * Class Name: IUserIntegralFlowService.java
	 * @Description: 积分统计详情
	 * @author zhang.zx
	 * @date 2016年5月19日 上午11:43:39
	 * @modifier
	 * @modify-date 2016年5月19日 上午11:43:39
	 * @version 1.0
	 * @param search
	 * @return
	 */
	List<UserIntegralFlow> queryIntegralFlowByWhere(UserIntegralFlowVO search);
}