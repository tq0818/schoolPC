package com.yuxin.wx.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.user.IUsersLoginSessionService;
import com.yuxin.wx.model.user.UserLoginSession;
import com.yuxin.wx.model.user.UsersLoginSession;
import com.yuxin.wx.user.mapper.UsersLoginSessionMapper;
import com.yuxin.wx.vo.company.CompanyManageLoginHistoryVo;

/**
 * Service Implementation:UsersLoginSession
 * @author chopin
 * @date 2015-7-27
 */
@Service
@Transactional
public class UsersLoginSessionServiceImpl extends BaseServiceImpl implements IUsersLoginSessionService {

	@Autowired
	private UsersLoginSessionMapper usersLoginSessionMapper;
	
	/**
	 * 
	* @Title: saveUsersLoginSession
	* @Description: 新增UsersLoginSession
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-27
	* @user by chopin
	 */
	@Override
	public void insert(UsersLoginSession entity){
		usersLoginSessionMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveUsersLoginSession 
	* @Description: 批量新增UsersLoginSession
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-27
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<UsersLoginSession> entity){
		usersLoginSessionMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateUsersLoginSession 
	* @Description: 编辑UsersLoginSession
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-27
	* @user by chopin
	 */
	@Override
	public void update(UsersLoginSession entity){
		usersLoginSessionMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteUsersLoginSessionById 
	* @Description: 根据id删除UsersLoginSession
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-27
	* @user by chopin
	 */
	 @Override
	public void deleteUsersLoginSessionById(Integer id){
		usersLoginSessionMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteUsersLoginSessionByIds 
	* @Description: 根据id批量删除UsersLoginSession
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-27
	* @user by chopin
	 */
	@Override
	public void deleteUsersLoginSessionByIds(Integer[] ids){
		usersLoginSessionMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findUsersLoginSessionById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-27
	* @user by chopin
	 */
	@Override
	public UsersLoginSession findUsersLoginSessionById(Integer id){
		return usersLoginSessionMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findUsersLoginSessionByPage 
	* @Description: 分页查询
	* @return
	* @return List<UsersLoginSession>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-27
	* @user by chopin
	 */
	@Override
	public List<UsersLoginSession> findUsersLoginSessionByPage(UsersLoginSession search){
		return usersLoginSessionMapper.page(search);
	};
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
	@Override
	public UsersLoginSession findHistoryByUserId(String userId){
		return usersLoginSessionMapper.findHistoryByUserId(userId);
	}

	@Override
	public void insertManageLoginHistory(CompanyManageLoginHistoryVo comlogin) {
		usersLoginSessionMapper.insertManageLoginHistory(comlogin);
	}
}
