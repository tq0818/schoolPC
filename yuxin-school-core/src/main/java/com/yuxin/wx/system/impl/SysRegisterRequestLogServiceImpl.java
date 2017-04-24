package com.yuxin.wx.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.system.ISysRegisterRequestLogService;
import com.yuxin.wx.model.system.SysRegisterRequestLog;
import com.yuxin.wx.system.mapper.SysRegisterRequestLogMapper;

/**
 * Service Implementation:SysRegisterRequestLog
 * @author chopin
 * @date 2015-11-3
 */
@Service
@Transactional
public class SysRegisterRequestLogServiceImpl extends BaseServiceImpl implements ISysRegisterRequestLogService {

	@Autowired
	private SysRegisterRequestLogMapper sysRegisterRequestLogMapper;
	
	/**
	 * 
	* @Title: saveSysRegisterRequestLog
	* @Description: 新增SysRegisterRequestLog
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-11-3
	* @user by chopin
	 */
	@Override
	public void insert(SysRegisterRequestLog entity){
		sysRegisterRequestLogMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveSysRegisterRequestLog 
	* @Description: 批量新增SysRegisterRequestLog
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-11-3
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<SysRegisterRequestLog> entity){
		sysRegisterRequestLogMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateSysRegisterRequestLog 
	* @Description: 编辑SysRegisterRequestLog
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-11-3
	* @user by chopin
	 */
	@Override
	public void update(SysRegisterRequestLog entity){
		sysRegisterRequestLogMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: updateSysRegisterRequestLog 
	* @Description: 编辑SysRegisterRequestLog
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-11-3
	* @user by chopin
	 */
	@Override
	public void updateByUuid(SysRegisterRequestLog entity){
		sysRegisterRequestLogMapper.updateByUuid(entity);
	};
	
	/**
	 * 
	* @Title: deleteSysRegisterRequestLogById 
	* @Description: 根据id删除SysRegisterRequestLog
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-11-3
	* @user by chopin
	 */
	 @Override
	public void deleteSysRegisterRequestLogById(Integer id){
		sysRegisterRequestLogMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteSysRegisterRequestLogByIds 
	* @Description: 根据id批量删除SysRegisterRequestLog
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-11-3
	* @user by chopin
	 */
	@Override
	public void deleteSysRegisterRequestLogByIds(Integer[] ids){
		sysRegisterRequestLogMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findSysRegisterRequestLogById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-11-3
	* @user by chopin
	 */
	@Override
	public SysRegisterRequestLog findSysRegisterRequestLogById(Integer id){
		return sysRegisterRequestLogMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findSysRegisterRequestLogByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysRegisterRequestLog>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-11-3
	* @user by chopin
	 */
	@Override
	public List<SysRegisterRequestLog> findSysRegisterRequestLogByPage(SysRegisterRequestLog search){
		return sysRegisterRequestLogMapper.page(search);
	};
}
