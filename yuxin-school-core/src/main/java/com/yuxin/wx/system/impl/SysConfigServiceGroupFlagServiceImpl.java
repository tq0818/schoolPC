package com.yuxin.wx.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.system.ISysConfigServiceGroupFlagService;
import com.yuxin.wx.model.system.SysConfigServiceGroupFlag;
import com.yuxin.wx.system.mapper.SysConfigServiceGroupFlagMapper;


/**
 * Service Implementation:SysConfigServiceGroupFlag
 * @author chopin
 * @date 2017-3-24
 */
@Service
@Transactional
public class SysConfigServiceGroupFlagServiceImpl extends BaseServiceImpl implements ISysConfigServiceGroupFlagService {

	@Autowired
	private SysConfigServiceGroupFlagMapper sysConfigServiceGroupFlagMapper;
	
	/**
	 * 
	* @Title: saveSysConfigServiceGroupFlag
	* @Description: 新增SysConfigServiceGroupFlag
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-24
	* @user by chopin
	 */
	@Override
	public void insert(SysConfigServiceGroupFlag entity){
		sysConfigServiceGroupFlagMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveSysConfigServiceGroupFlag 
	* @Description: 批量新增SysConfigServiceGroupFlag
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-24
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<SysConfigServiceGroupFlag> entity){
		sysConfigServiceGroupFlagMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateSysConfigServiceGroupFlag 
	* @Description: 编辑SysConfigServiceGroupFlag
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-24
	* @user by chopin
	 */
	@Override
	public void update(SysConfigServiceGroupFlag entity){
		sysConfigServiceGroupFlagMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteSysConfigServiceGroupFlagById 
	* @Description: 根据id删除SysConfigServiceGroupFlag
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-24
	* @user by chopin
	 */
	 @Override
	public void deleteSysConfigServiceGroupFlagById(Integer id){
		sysConfigServiceGroupFlagMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteSysConfigServiceGroupFlagByIds 
	* @Description: 根据id批量删除SysConfigServiceGroupFlag
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-24
	* @user by chopin
	 */
	@Override
	public void deleteSysConfigServiceGroupFlagByIds(Integer[] ids){
		sysConfigServiceGroupFlagMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findSysConfigServiceGroupFlagById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-24
	* @user by chopin
	 */
	@Override
	public SysConfigServiceGroupFlag findSysConfigServiceGroupFlagById(Integer id){
		return sysConfigServiceGroupFlagMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findSysConfigServiceGroupFlagByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysConfigServiceGroupFlag>    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-24
	* @user by chopin
	 */
	@Override
	public List<SysConfigServiceGroupFlag> findSysConfigServiceGroupFlagByPage(SysConfigServiceGroupFlag search){
		return sysConfigServiceGroupFlagMapper.page(search);
	}

	@Override
	public List<SysConfigServiceGroupFlag> findAll() {
		return sysConfigServiceGroupFlagMapper.findAll();
	};
}
