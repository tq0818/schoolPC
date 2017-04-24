package com.yuxin.wx.system.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.system.ISysLogInvitLogService;
import com.yuxin.wx.model.system.SysLogInvitLog;
import com.yuxin.wx.system.mapper.SysLogInvitLogMapper;


/**
 * Service Implementation:SysLogInvitLog
 * @author chopin
 * @date 2016-8-12
 */
@Service
@Transactional
public class SysLogInvitLogServiceImpl extends BaseServiceImpl implements ISysLogInvitLogService {

	@Autowired
	private SysLogInvitLogMapper sysLogInvitLogMapper;
	
	/**
	 * 
	* @Title: saveSysLogInvitLog
	* @Description: 新增SysLogInvitLog
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-8-12
	* @user by chopin
	 */
	@Override
	public void insert(SysLogInvitLog entity){
		sysLogInvitLogMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveSysLogInvitLog 
	* @Description: 批量新增SysLogInvitLog
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-8-12
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<SysLogInvitLog> entity){
		sysLogInvitLogMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateSysLogInvitLog 
	* @Description: 编辑SysLogInvitLog
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-8-12
	* @user by chopin
	 */
	@Override
	public void update(SysLogInvitLog entity){
		sysLogInvitLogMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteSysLogInvitLogById 
	* @Description: 根据id删除SysLogInvitLog
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-8-12
	* @user by chopin
	 */
	 @Override
	public void deleteSysLogInvitLogById(Integer id){
		sysLogInvitLogMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteSysLogInvitLogByIds 
	* @Description: 根据id批量删除SysLogInvitLog
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-8-12
	* @user by chopin
	 */
	@Override
	public void deleteSysLogInvitLogByIds(Integer[] ids){
		sysLogInvitLogMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findSysLogInvitLogById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-8-12
	* @user by chopin
	 */
	@Override
	public SysLogInvitLog findSysLogInvitLogById(Integer id){
		return sysLogInvitLogMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findSysLogInvitLogByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysLogInvitLog>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-8-12
	* @user by chopin
	 */
	@Override
	public List<SysLogInvitLog> findSysLogInvitLogByPage(SysLogInvitLog search){
		return sysLogInvitLogMapper.page(search);
	};
}
