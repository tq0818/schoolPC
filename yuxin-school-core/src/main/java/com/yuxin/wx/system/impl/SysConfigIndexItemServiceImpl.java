package com.yuxin.wx.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.system.ISysConfigIndexItemService;
import com.yuxin.wx.model.system.SysConfigIndexItem;
import com.yuxin.wx.system.mapper.SysConfigIndexItemMapper;

/**
 * Service Implementation:SysConfigIndexItem
 * @author chopin
 * @date 2015-3-17
 */
@Service
@Transactional
public class SysConfigIndexItemServiceImpl extends BaseServiceImpl implements ISysConfigIndexItemService {

	@Autowired
	private SysConfigIndexItemMapper sysConfigIndexItemMapper;
	
	/**
	 * 
	* @Title: saveSysConfigIndexItem
	* @Description: 新增SysConfigIndexItem
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by chopin
	 */
	@Override
	public void insert(SysConfigIndexItem entity){
		sysConfigIndexItemMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveSysConfigIndexItem 
	* @Description: 批量新增SysConfigIndexItem
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<SysConfigIndexItem> entity){
		sysConfigIndexItemMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateSysConfigIndexItem 
	* @Description: 编辑SysConfigIndexItem
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by chopin
	 */
	@Override
	public void update(SysConfigIndexItem entity){
		sysConfigIndexItemMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteSysConfigIndexItemById 
	* @Description: 根据id删除SysConfigIndexItem
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by chopin
	 */
	 @Override
	public void deleteSysConfigIndexItemById(Integer id){
		sysConfigIndexItemMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteSysConfigIndexItemByIds 
	* @Description: 根据id批量删除SysConfigIndexItem
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by chopin
	 */
	@Override
	public void deleteSysConfigIndexItemByIds(Integer[] ids){
		sysConfigIndexItemMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findSysConfigIndexItemById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by chopin
	 */
	@Override
	public SysConfigIndexItem findSysConfigIndexItemById(Integer id){
		return sysConfigIndexItemMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findSysConfigIndexItemByPage 
	* @Description: 查询全部
	* @return
	* @return List<SysConfigIndexItem>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by chopin
	 */
	@Override
	public List<SysConfigIndexItem> findAll(){
		return sysConfigIndexItemMapper.queryAll();
	}
}
