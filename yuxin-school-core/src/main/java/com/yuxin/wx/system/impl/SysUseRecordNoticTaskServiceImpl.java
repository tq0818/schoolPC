package com.yuxin.wx.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.system.ISysUseRecordNoticTaskService;
import com.yuxin.wx.model.system.SysUseRecordNoticTask;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.system.mapper.SysUseRecordNoticTaskMapper;


/**
 * Service Implementation:SysUseRecordNoticTask
 * @author wang.zx
 * @date 2016-12-1
 */
@Service
@Transactional
public class SysUseRecordNoticTaskServiceImpl extends BaseServiceImpl implements ISysUseRecordNoticTaskService {

	@Autowired
	private SysUseRecordNoticTaskMapper sysUseRecordNoticTaskMapper;
	
	/**
	 * 
	* @Title: saveSysUseRecordNoticTask
	* @Description: 新增SysUseRecordNoticTask
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-1
	* @user by chopin
	 */
	@Override
	public void insert(SysUseRecordNoticTask entity){
		sysUseRecordNoticTaskMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveSysUseRecordNoticTask 
	* @Description: 批量新增SysUseRecordNoticTask
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-1
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<SysUseRecordNoticTask> T){
		sysUseRecordNoticTaskMapper.batchInsert(T);
	};
	
	/**
	 * 
	* @Title: updateSysUseRecordNoticTask 
	* @Description: 编辑SysUseRecordNoticTask
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-1
	* @user by chopin
	 */
	@Override
	public void update(SysUseRecordNoticTask T){
		sysUseRecordNoticTaskMapper.update(T);
	};
	
	/**
	 * 
	* @Title: deleteSysUseRecordNoticTaskById 
	* @Description: 根据id删除SysUseRecordNoticTask
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-1
	* @user by chopin
	 */
	 @Override
	public void deleteSysUseRecordNoticTaskById(Integer id){
		sysUseRecordNoticTaskMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteSysUseRecordNoticTaskByIds 
	* @Description: 根据id批量删除SysUseRecordNoticTask
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-1
	* @user by chopin
	 */
	@Override
	public void deleteSysUseRecordNoticTaskByIds(Integer[] ids){
		sysUseRecordNoticTaskMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findSysUseRecordNoticTaskById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-1
	* @user by chopin
	 */
	@Override
	public SysUseRecordNoticTask findSysUseRecordNoticTaskById(Integer id){
		return sysUseRecordNoticTaskMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findSysUseRecordNoticTaskByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysUseRecordNoticTask>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-1
	* @user by chopin
	 */
	@Override
	public List<SysUseRecordNoticTask> findSysUseRecordNoticTaskByPage(SysUseRecordNoticTask search){
		return sysUseRecordNoticTaskMapper.page(search);
	}

	@Override
	public SysUseRecordNoticTask findByCompanyId(SysUseRecordNoticTask T) {
		// TODO Auto-generated method stub
		return sysUseRecordNoticTaskMapper.findByCompanyId(T);
	}

	@Override
	public List<Users> queryCompanyManageUsers(Integer companyId) {
		return sysUseRecordNoticTaskMapper.queryCompanyManageUsers(companyId);
	}

	@Override
	public List<SysUseRecordNoticTask> queryNeedSendList() {
		return sysUseRecordNoticTaskMapper.queryNeedSendList();
	};
}
