package com.yuxin.wx.user.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.user.IUserLessonTimeService;
import com.yuxin.wx.model.user.UserLessonTime;
import com.yuxin.wx.user.mapper.UserLessonTimeMapper;


/**
 * Service Implementation:UserLessonTime
 * @author wang.zx
 * @date 2015-9-25
 */
@Service
@Transactional
public class UserLessonTimeServiceImpl extends BaseServiceImpl implements IUserLessonTimeService {

	@Autowired
	private UserLessonTimeMapper userLessonTimeMapper;
	
	/**
	 * 
	* @Title: saveUserLessonTime
	* @Description: 新增UserLessonTime
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-25
	* @user by chopin
	 */
	@Override
	public void insert(UserLessonTime entity){
		userLessonTimeMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveUserLessonTime 
	* @Description: 批量新增UserLessonTime
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-25
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<UserLessonTime> T){
		userLessonTimeMapper.batchInsert(T);
	};
	
	/**
	 * 
	* @Title: updateUserLessonTime 
	* @Description: 编辑UserLessonTime
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-25
	* @user by chopin
	 */
	@Override
	public void update(UserLessonTime T){
		userLessonTimeMapper.update(T);
	};
	
	/**
	 * 
	* @Title: deleteUserLessonTimeById 
	* @Description: 根据id删除UserLessonTime
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-25
	* @user by chopin
	 */
	 @Override
	public void deleteUserLessonTimeById(Integer id){
		userLessonTimeMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteUserLessonTimeByIds 
	* @Description: 根据id批量删除UserLessonTime
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-25
	* @user by chopin
	 */
	@Override
	public void deleteUserLessonTimeByIds(Integer[] ids){
		userLessonTimeMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findUserLessonTimeById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-25
	* @user by chopin
	 */
	@Override
	public UserLessonTime findUserLessonTimeById(Integer id){
		return userLessonTimeMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findUserLessonTimeByPage 
	* @Description: 分页查询
	* @return
	* @return List<UserLessonTime>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-25
	* @user by chopin
	 */
	@Override
	public List<UserLessonTime> findUserLessonTimeByPage(UserLessonTime search){
		return userLessonTimeMapper.page(search);
	}

	@Override
	public void deleteByUserIdAndLessonId(Map<String, Integer> map) {
		userLessonTimeMapper.deleteByUserIdAndLessonId(map);
	}

	@Override
	public List<UserLessonTime> findByUserIdAndLessonId(Map<String, Integer> map) {
		return userLessonTimeMapper.findByUserIdAndLessonId(map);
	};
}
