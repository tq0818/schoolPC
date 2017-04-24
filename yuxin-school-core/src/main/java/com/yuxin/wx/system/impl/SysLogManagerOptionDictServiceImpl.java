package com.yuxin.wx.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.system.ISysLogManagerOptionDictService;
import com.yuxin.wx.model.system.SysLogManagerOptionDict;
import com.yuxin.wx.system.mapper.SysLogManagerOptionDictMapper;


/**
 * Service Implementation:SysLogManagerOptionDict
 * @author chopin
 * @date 2017-3-13
 */
@Service
@Transactional
public class SysLogManagerOptionDictServiceImpl extends BaseServiceImpl implements ISysLogManagerOptionDictService {

	@Autowired
	private SysLogManagerOptionDictMapper sysLogManagerOptionDictMapper;
	
	/**
	 * 
	* @Title: saveSysLogManagerOptionDict
	* @Description: 新增SysLogManagerOptionDict
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-13
	* @user by chopin
	 */
	@Override
	public void insert(SysLogManagerOptionDict entity){
		sysLogManagerOptionDictMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveSysLogManagerOptionDict 
	* @Description: 批量新增SysLogManagerOptionDict
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-13
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<SysLogManagerOptionDict> entity){
		sysLogManagerOptionDictMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateSysLogManagerOptionDict 
	* @Description: 编辑SysLogManagerOptionDict
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-13
	* @user by chopin
	 */
	@Override
	public void update(SysLogManagerOptionDict entity){
		sysLogManagerOptionDictMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteSysLogManagerOptionDictById 
	* @Description: 根据id删除SysLogManagerOptionDict
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-13
	* @user by chopin
	 */
	 @Override
	public void deleteSysLogManagerOptionDictById(Integer id){
		sysLogManagerOptionDictMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteSysLogManagerOptionDictByIds 
	* @Description: 根据id批量删除SysLogManagerOptionDict
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-13
	* @user by chopin
	 */
	@Override
	public void deleteSysLogManagerOptionDictByIds(Integer[] ids){
		sysLogManagerOptionDictMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findSysLogManagerOptionDictById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-13
	* @user by chopin
	 */
	@Override
	public SysLogManagerOptionDict findSysLogManagerOptionDictById(Integer id){
		return sysLogManagerOptionDictMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findSysLogManagerOptionDictByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysLogManagerOptionDict>    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-13
	* @user by chopin
	 */
	@Override
	public List<SysLogManagerOptionDict> findSysLogManagerOptionDictByPage(SysLogManagerOptionDict search){
		return sysLogManagerOptionDictMapper.page(search);
	}

	@Override
	public String queryOperationByAction(String action) {
		return sysLogManagerOptionDictMapper.queryOperationByAction(action);
	}

	@Override
	public SysLogManagerOptionDict queryByAction(String action) {
		return sysLogManagerOptionDictMapper.queryByAction(action);
	};
}
