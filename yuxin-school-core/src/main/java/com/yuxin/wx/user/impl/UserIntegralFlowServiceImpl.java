package com.yuxin.wx.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.user.IUserIntegralFlowService;
import com.yuxin.wx.model.user.UserIntegralFlow;
import com.yuxin.wx.user.mapper.UserIntegralFlowMapper;
import com.yuxin.wx.vo.user.UserIntegralFlowVO;


/**
 * Service Implementation:UserIntegralFlow
 * @author chopin
 * @date 2016-5-17
 */
@Service
@Transactional
public class UserIntegralFlowServiceImpl extends BaseServiceImpl implements IUserIntegralFlowService {

	@Autowired
	private UserIntegralFlowMapper userIntegralFlowMapper;
	
	/**
	 * 
	* @Title: saveUserIntegralFlow
	* @Description: 新增UserIntegralFlow
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by chopin
	 */
	@Override
	public void insert(UserIntegralFlow entity){
		userIntegralFlowMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveUserIntegralFlow 
	* @Description: 批量新增UserIntegralFlow
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<UserIntegralFlow> entity){
		userIntegralFlowMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateUserIntegralFlow 
	* @Description: 编辑UserIntegralFlow
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by chopin
	 */
	@Override
	public void update(UserIntegralFlow entity){
		userIntegralFlowMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteUserIntegralFlowById 
	* @Description: 根据id删除UserIntegralFlow
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by chopin
	 */
	 @Override
	public void deleteUserIntegralFlowById(Integer id){
		userIntegralFlowMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteUserIntegralFlowByIds 
	* @Description: 根据id批量删除UserIntegralFlow
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by chopin
	 */
	@Override
	public void deleteUserIntegralFlowByIds(Integer[] ids){
		userIntegralFlowMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findUserIntegralFlowById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by chopin
	 */
	@Override
	public UserIntegralFlow findUserIntegralFlowById(Integer id){
		return userIntegralFlowMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findUserIntegralFlowByPage 
	* @Description: 分页查询
	* @return
	* @return List<UserIntegralFlow>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by chopin
	 */
	@Override
	public List<UserIntegralFlow> findUserIntegralFlowByPage(UserIntegralFlow search){
		return userIntegralFlowMapper.page(search);
	}

	@Override
	public List<UserIntegralFlow> queryIntegralFlowByWhere(
			UserIntegralFlowVO search) {
		// TODO Auto-generated method stub
		return userIntegralFlowMapper.queryIntegralFlowByWhere(search);
	};
}
