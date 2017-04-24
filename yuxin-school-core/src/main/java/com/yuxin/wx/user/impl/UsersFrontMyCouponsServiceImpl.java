package com.yuxin.wx.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.user.IUsersFrontMyCouponsService;
import com.yuxin.wx.company.mapper.CompanyCouponsPatchMapper;
import com.yuxin.wx.model.company.CompanyCouponsPatch;
import com.yuxin.wx.model.user.UsersFrontMyCoupons;
import com.yuxin.wx.user.mapper.UsersFrontMyCouponsMapper;


/**
 * Service Implementation:UsersFrontMyCoupons
 * @author chopin
 * @date 2016-6-20
 */
@Service
@Transactional
public class UsersFrontMyCouponsServiceImpl extends BaseServiceImpl implements IUsersFrontMyCouponsService {

	@Autowired
	private UsersFrontMyCouponsMapper usersFrontMyCouponsMapper;
	
	@Autowired
	private CompanyCouponsPatchMapper companyCouponsPathMapper;
	
	/**
	 * 
	* @Title: saveUsersFrontMyCoupons
	* @Description: 新增UsersFrontMyCoupons
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-20
	* @user by chopin
	 */
	@Override
	public void insert(UsersFrontMyCoupons entity){
		usersFrontMyCouponsMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveUsersFrontMyCoupons 
	* @Description: 批量新增UsersFrontMyCoupons
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-20
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<UsersFrontMyCoupons> entity){
		usersFrontMyCouponsMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateUsersFrontMyCoupons 
	* @Description: 编辑UsersFrontMyCoupons
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-20
	* @user by chopin
	 */
	@Override
	public void update(UsersFrontMyCoupons entity){
		usersFrontMyCouponsMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteUsersFrontMyCouponsById 
	* @Description: 根据id删除UsersFrontMyCoupons
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-20
	* @user by chopin
	 */
	 @Override
	public void deleteUsersFrontMyCouponsById(Integer id){
		usersFrontMyCouponsMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteUsersFrontMyCouponsByIds 
	* @Description: 根据id批量删除UsersFrontMyCoupons
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-20
	* @user by chopin
	 */
	@Override
	public void deleteUsersFrontMyCouponsByIds(Integer[] ids){
		usersFrontMyCouponsMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findUsersFrontMyCouponsById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-20
	* @user by chopin
	 */
	@Override
	public UsersFrontMyCoupons findUsersFrontMyCouponsById(Integer id){
		return usersFrontMyCouponsMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findUsersFrontMyCouponsByPage 
	* @Description: 分页查询
	* @return
	* @return List<UsersFrontMyCoupons>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-20
	* @user by chopin
	 */
	@Override
	public List<UsersFrontMyCoupons> findUsersFrontMyCouponsByPage(UsersFrontMyCoupons search){
		return usersFrontMyCouponsMapper.page(search);
	}

	@Override
	public List<UsersFrontMyCoupons> findUsersFrontMyCouponsNoPage(
			UsersFrontMyCoupons search) {
		// TODO Auto-generated method stub
		return usersFrontMyCouponsMapper.findUsersFrontMyCouponsNoPage(search);
	};
	public void updateUsedCouponCount(Integer patchId) {
		CompanyCouponsPatch ccp = companyCouponsPathMapper.findById(patchId);
		if(ccp == null)
			return;
		Integer usedNum = ccp.getUsedNum()==null? 0 : ccp.getUsedNum();
		Integer totalNum = ccp.getTotalNum()== null? 0 : ccp.getTotalNum();
		ccp.setUsedNum(usedNum + 1);
		ccp.setRemainNum(totalNum - usedNum - 1);
		companyCouponsPathMapper.update(ccp);
	};
}
