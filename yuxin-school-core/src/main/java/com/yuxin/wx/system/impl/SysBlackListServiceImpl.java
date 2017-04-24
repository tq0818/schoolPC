package com.yuxin.wx.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.system.ISysBlackListService;
import com.yuxin.wx.model.system.SysBlackList;
import com.yuxin.wx.system.mapper.SysBlackListMapper;

/**
 * Service Implementation:SysBlackList
 * @author chopin
 * @date 2015-9-15
 */
@Service
@Transactional
public class SysBlackListServiceImpl extends BaseServiceImpl implements ISysBlackListService {

	@Autowired
	private SysBlackListMapper sysBlackListMapper;
	
	/**
	 * 
	* @Title: saveSysBlackList
	* @Description: 新增SysBlackList
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-15
	* @user by chopin
	 */
	@Override
	public void insert(SysBlackList entity){
		sysBlackListMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveSysBlackList 
	* @Description: 批量新增SysBlackList
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-15
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<SysBlackList> entity){
		sysBlackListMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateSysBlackList 
	* @Description: 编辑SysBlackList
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-15
	* @user by chopin
	 */
	@Override
	public void update(SysBlackList entity){
		sysBlackListMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteSysBlackListById 
	* @Description: 根据id删除SysBlackList
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-15
	* @user by chopin
	 */
	 @Override
	public void deleteSysBlackListById(Integer id){
		sysBlackListMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteSysBlackListByIds 
	* @Description: 根据id批量删除SysBlackList
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-15
	* @user by chopin
	 */
	@Override
	public void deleteSysBlackListByIds(Integer[] ids){
		sysBlackListMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findSysBlackListById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-15
	* @user by chopin
	 */
	@Override
	public SysBlackList findSysBlackListById(Integer id){
		return sysBlackListMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findSysBlackListByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysBlackList>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-15
	* @user by chopin
	 */
	@Override
	public List<SysBlackList> findSysBlackListByPage(SysBlackList search){
		return sysBlackListMapper.page(search);
	};
}
