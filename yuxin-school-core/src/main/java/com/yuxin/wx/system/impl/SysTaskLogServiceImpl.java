package com.yuxin.wx.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.system.ISysTaskLogService;
import com.yuxin.wx.model.system.SysTaskLog;
import com.yuxin.wx.system.mapper.SysTaskLogMapper;

/**
 * Service Implementation:SysTaskLog
 * @author wang.zx
 * @date 2015-6-17
 */
@Service
@Transactional
public class SysTaskLogServiceImpl extends BaseServiceImpl implements ISysTaskLogService {

	@Autowired
	private SysTaskLogMapper sysTaskLogMapper;
	
	/**
	 * 
	* @Title: saveSysTaskLog
	* @Description: 新增SysTaskLog
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-17
	* @user by chopin
	 */
	@Override
	public void insert(SysTaskLog entity){
		sysTaskLogMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveSysTaskLog 
	* @Description: 批量新增SysTaskLog
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-17
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<SysTaskLog> entity){
		sysTaskLogMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateSysTaskLog 
	* @Description: 编辑SysTaskLog
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-17
	* @user by chopin
	 */
	@Override
	public void update(SysTaskLog entity){
		sysTaskLogMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteSysTaskLogById 
	* @Description: 根据id删除SysTaskLog
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-17
	* @user by chopin
	 */
	 @Override
	public void deleteSysTaskLogById(Integer id){
		sysTaskLogMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteSysTaskLogByIds 
	* @Description: 根据id批量删除SysTaskLog
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-17
	* @user by chopin
	 */
	@Override
	public void deleteSysTaskLogByIds(Integer[] ids){
		sysTaskLogMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findSysTaskLogById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-17
	* @user by chopin
	 */
	@Override
	public SysTaskLog findSysTaskLogById(Integer id){
		return sysTaskLogMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findSysTaskLogByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysTaskLog>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-17
	* @user by chopin
	 */
	@Override
	public List<SysTaskLog> findSysTaskLogByPage(SysTaskLog search){
		return sysTaskLogMapper.page(search);
	};
}
