package com.yuxin.wx.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.system.ISysConfigIndexClasstypeService;
import com.yuxin.wx.model.system.SysConfigIndexClasstype;
import com.yuxin.wx.system.mapper.SysConfigIndexClasstypeMapper;

/**
 * Service Implementation:SysConfigIndexClasstype
 * @author chopin
 * @date 2015-3-17
 */
@Service
@Transactional
public class SysConfigIndexClasstypeServiceImpl extends BaseServiceImpl implements ISysConfigIndexClasstypeService {

	@Autowired
	private SysConfigIndexClasstypeMapper sysConfigIndexClasstypeMapper;
	
	/**
	 * 
	* @Title: saveSysConfigIndexClasstype
	* @Description: 新增SysConfigIndexClasstype
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by chopin
	 */
	@Override
	public void insert(SysConfigIndexClasstype entity){
		sysConfigIndexClasstypeMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveSysConfigIndexClasstype 
	* @Description: 批量新增SysConfigIndexClasstype
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<SysConfigIndexClasstype> entity){
		sysConfigIndexClasstypeMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateSysConfigIndexClasstype 
	* @Description: 编辑SysConfigIndexClasstype
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by chopin
	 */
	@Override
	public void update(SysConfigIndexClasstype entity){
		sysConfigIndexClasstypeMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteSysConfigIndexClasstypeById 
	* @Description: 根据id删除SysConfigIndexClasstype
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by chopin
	 */
	 @Override
	public void deleteSysConfigIndexClasstypeById(Integer id){
		sysConfigIndexClasstypeMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteSysConfigIndexClasstypeByIds 
	* @Description: 根据id批量删除SysConfigIndexClasstype
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by chopin
	 */
	@Override
	public void deleteSysConfigIndexClasstypeByIds(Integer[] ids){
		sysConfigIndexClasstypeMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findSysConfigIndexClasstypeById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by chopin
	 */
	@Override
	public SysConfigIndexClasstype findSysConfigIndexClasstypeById(Integer id){
		return sysConfigIndexClasstypeMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findSysConfigIndexClasstypeByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysConfigIndexClasstype>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by chopin
	 */
	@Override
	public List<SysConfigIndexClasstype> findSysConfigIndexClasstypeByPage(SysConfigIndexClasstype search){
		return sysConfigIndexClasstypeMapper.page(search);
	};
	
	/**
	 * 
	* @Title: findAll 
	* @Description: 查询所有数据
	* @return
	* @return List<SysConfigIndexClasstype>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by chopin
	 */
	@Override
	public List<SysConfigIndexClasstype> findAll(){
		return sysConfigIndexClasstypeMapper.queryAll();
	}
}
