package com.yuxin.wx.system.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.system.ISysSmsLogService;
import com.yuxin.wx.model.system.SysSmsLog;
import com.yuxin.wx.system.mapper.SysSmsLogMapper;

/**
 * Service Implementation:SysSmsLog
 * @author chopin
 * @date 2015-9-15
 */
@Service
@Transactional
public class SysSmsLogServiceImpl extends BaseServiceImpl implements ISysSmsLogService {

	@Autowired
	private SysSmsLogMapper sysSmsLogMapper;
	
	/**
	 * 
	* @Title: saveSysSmsLog
	* @Description: 新增SysSmsLog
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-15
	* @user by chopin
	 */
	@Override
	public void insert(SysSmsLog entity){
		sysSmsLogMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveSysSmsLog 
	* @Description: 批量新增SysSmsLog
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-15
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<SysSmsLog> entity){
		sysSmsLogMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateSysSmsLog 
	* @Description: 编辑SysSmsLog
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-15
	* @user by chopin
	 */
	@Override
	public void update(SysSmsLog entity){
		sysSmsLogMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteSysSmsLogById 
	* @Description: 根据id删除SysSmsLog
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-15
	* @user by chopin
	 */
	 @Override
	public void deleteSysSmsLogById(Integer id){
		sysSmsLogMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteSysSmsLogByIds 
	* @Description: 根据id批量删除SysSmsLog
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-15
	* @user by chopin
	 */
	@Override
	public void deleteSysSmsLogByIds(Integer[] ids){
		sysSmsLogMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findSysSmsLogById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-15
	* @user by chopin
	 */
	@Override
	public SysSmsLog findSysSmsLogById(Integer id){
		return sysSmsLogMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findSysSmsLogByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysSmsLog>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-15
	* @user by chopin
	 */
	@Override
	public List<SysSmsLog> findSysSmsLogByPage(SysSmsLog search){
		return sysSmsLogMapper.page(search);
	}

	@Override
	public Integer findByCompanyId(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return sysSmsLogMapper.findByCompanyId(param);
	};
}
