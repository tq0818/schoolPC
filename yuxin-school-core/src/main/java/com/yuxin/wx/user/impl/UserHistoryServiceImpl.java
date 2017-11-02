package com.yuxin.wx.user.impl;


import java.util.List;

import com.yuxin.wx.vo.user.UserHistoryAllVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.user.IUserHistoryService;
import com.yuxin.wx.model.user.UserHistory;
import com.yuxin.wx.user.mapper.UserHistoryMapper;


/**
 * Service Implementation:UserHistory
 * @author chopin
 * @date 2016-9-27
 */
@Service
@Transactional
public class UserHistoryServiceImpl extends BaseServiceImpl implements IUserHistoryService {

	@Autowired
	private UserHistoryMapper userHistoryMapper;
	
	/**
	 * 
	* @Title: saveUserHistory
	* @Description: 新增UserHistory
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-27
	* @user by chopin
	 */
	@Override
	public void insert(UserHistory entity){
		userHistoryMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveUserHistory 
	* @Description: 批量新增UserHistory
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-27
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<UserHistory> entity){
		userHistoryMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateUserHistory 
	* @Description: 编辑UserHistory
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-27
	* @user by chopin
	 */
	@Override
	public void update(UserHistory entity){
		userHistoryMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteUserHistoryById 
	* @Description: 根据id删除UserHistory
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-27
	* @user by chopin
	 */
	 @Override
	public void deleteUserHistoryById(Integer id){
		userHistoryMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteUserHistoryByIds 
	* @Description: 根据id批量删除UserHistory
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-27
	* @user by chopin
	 */
	@Override
	public void deleteUserHistoryByIds(Integer[] ids){
		userHistoryMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findUserHistoryById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-27
	* @user by chopin
	 */
	@Override
	public UserHistory findUserHistoryById(Integer id){
		return userHistoryMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findUserHistoryByPage 
	* @Description: 分页查询
	* @return
	* @return List<UserHistory>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-27
	* @user by chopin
	 */
	@Override
	public List<UserHistory> findUserHistoryByPage(UserHistory search){
		return userHistoryMapper.page(search);
	}

	@Override
	public int queryCountByClassTypeIdAndUserId(UserHistory search) {
		return userHistoryMapper.queryCountByClassTypeIdAndUserId(search);
	}

	@Override
	public void insertHistoryAll(UserHistoryAllVo userHistoryAllVo) {
		userHistoryMapper.insertHistoryAll(userHistoryAllVo);
	}

	@Override
	public void insertPlayLogs(UserHistoryAllVo uha) {
		userHistoryMapper.insertPlayLogs(uha);
	}




}
