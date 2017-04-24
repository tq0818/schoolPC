package com.yuxin.wx.api.user;

import java.util.List;

import com.yuxin.wx.model.user.UsersFrontMyCoupons;
/**
 * Service Interface:UsersFrontMyCoupons
 * @author chopin
 * @date 2016-6-20
 */
public interface IUsersFrontMyCouponsService  {
	/**
	 * 
	* @Title: saveUsersFrontMyCoupons
	* @Description: 新增UsersFrontMyCoupons
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-20
	* @user by wangzx
	 */
	void insert(UsersFrontMyCoupons entity);
	
	/**
	 * 
	* @Title: batchSaveUsersFrontMyCoupons 
	* @Description: 批量新增UsersFrontMyCoupons
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-20
	* @user by wangzx
	 */
	void batchInsert(List<UsersFrontMyCoupons> list);
	
	/**
	 * 
	* @Title: updateUsersFrontMyCoupons 
	* @Description: 编辑UsersFrontMyCoupons
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-20
	* @user by wangzx
	 */
	void update(UsersFrontMyCoupons entity);
	
	/**
	 * 
	* @Title: deleteUsersFrontMyCouponsById 
	* @Description: 根据id删除UsersFrontMyCoupons
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-20
	* @user by wangzx
	 */
	void deleteUsersFrontMyCouponsById(Integer id);
	
	/**
	 * 
	* @Title: deleteUsersFrontMyCouponsByIds 
	* @Description: 根据id批量删除UsersFrontMyCoupons
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-20
	* @user by wangzx
	 */
	void deleteUsersFrontMyCouponsByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findUsersFrontMyCouponsById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-20
	* @user by wangzx
	 */
	UsersFrontMyCoupons findUsersFrontMyCouponsById(Integer id);
	
	/**
	 * 
	* @Title: findUsersFrontMyCouponsByPage 
	* @Description: 分页查询
	* @return
	* @return List<UsersFrontMyCoupons>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-20
	* @user by wangzx
	 */
	List<UsersFrontMyCoupons> findUsersFrontMyCouponsByPage(UsersFrontMyCoupons search);
	List<UsersFrontMyCoupons> findUsersFrontMyCouponsNoPage(UsersFrontMyCoupons search);
	void updateUsedCouponCount(Integer patchId);
}