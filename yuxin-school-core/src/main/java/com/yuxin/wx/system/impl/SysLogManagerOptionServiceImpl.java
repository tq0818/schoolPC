package com.yuxin.wx.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.system.ISysLogManagerOptionService;
import com.yuxin.wx.model.system.SysLogManagerOption;
import com.yuxin.wx.system.mapper.SysLogManagerOptionMapper;


/**
 * Service Implementation:SysLogManagerOption
 * @author chopin
 * @date 2017-3-10
 */
@Service
@Transactional
public class SysLogManagerOptionServiceImpl extends BaseServiceImpl implements ISysLogManagerOptionService {

	@Autowired
	private SysLogManagerOptionMapper sysLogManagerOptionMapper;
	
	/**
	 * 
	* @Title: saveSysLogManagerOption
	* @Description: 新增SysLogManagerOption
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-10
	* @user by chopin
	 */
	@Override
	public void insert(SysLogManagerOption entity){
		sysLogManagerOptionMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveSysLogManagerOption 
	* @Description: 批量新增SysLogManagerOption
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-10
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<SysLogManagerOption> entity){
		sysLogManagerOptionMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateSysLogManagerOption 
	* @Description: 编辑SysLogManagerOption
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-10
	* @user by chopin
	 */
	@Override
	public void update(SysLogManagerOption entity){
		sysLogManagerOptionMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteSysLogManagerOptionById 
	* @Description: 根据id删除SysLogManagerOption
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-10
	* @user by chopin
	 */
	 @Override
	public void deleteSysLogManagerOptionById(Integer id){
		sysLogManagerOptionMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteSysLogManagerOptionByIds 
	* @Description: 根据id批量删除SysLogManagerOption
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-10
	* @user by chopin
	 */
	@Override
	public void deleteSysLogManagerOptionByIds(Integer[] ids){
		sysLogManagerOptionMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findSysLogManagerOptionById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-10
	* @user by chopin
	 */
	@Override
	public SysLogManagerOption findSysLogManagerOptionById(Integer id){
		return sysLogManagerOptionMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findSysLogManagerOptionByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysLogManagerOption>    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-10
	* @user by chopin
	 */
	@Override
	public List<SysLogManagerOption> findSysLogManagerOptionByPage(SysLogManagerOption search){
		return sysLogManagerOptionMapper.page(search);
	};
}
