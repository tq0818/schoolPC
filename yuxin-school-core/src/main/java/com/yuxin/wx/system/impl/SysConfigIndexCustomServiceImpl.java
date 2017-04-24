package com.yuxin.wx.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.system.ISysConfigIndexCustomService;
import com.yuxin.wx.model.system.SysConfigIndexCustom;
import com.yuxin.wx.system.mapper.SysConfigIndexCustomMapper;

/**
 * Service Implementation:SysConfigIndexCustom
 * @author chopin
 * @date 2015-3-17
 */
@Service
@Transactional
public class SysConfigIndexCustomServiceImpl extends BaseServiceImpl implements ISysConfigIndexCustomService {

	@Autowired
	private SysConfigIndexCustomMapper sysConfigIndexCustomMapper;
	
	/**
	 * 
	* @Title: saveSysConfigIndexCustom
	* @Description: 新增SysConfigIndexCustom
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by chopin
	 */
	@Override
	public void insert(SysConfigIndexCustom entity){
		sysConfigIndexCustomMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveSysConfigIndexCustom 
	* @Description: 批量新增SysConfigIndexCustom
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<SysConfigIndexCustom> entity){
		sysConfigIndexCustomMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateSysConfigIndexCustom 
	* @Description: 编辑SysConfigIndexCustom
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by chopin
	 */
	@Override
	public void update(SysConfigIndexCustom entity){
		sysConfigIndexCustomMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteSysConfigIndexCustomById 
	* @Description: 根据id删除SysConfigIndexCustom
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by chopin
	 */
	 @Override
	public void deleteSysConfigIndexCustomById(Integer id){
		sysConfigIndexCustomMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteSysConfigIndexCustomByIds 
	* @Description: 根据id批量删除SysConfigIndexCustom
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by chopin
	 */
	@Override
	public void deleteSysConfigIndexCustomByIds(Integer[] ids){
		sysConfigIndexCustomMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findSysConfigIndexCustomById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by chopin
	 */
	@Override
	public SysConfigIndexCustom findSysConfigIndexCustomById(Integer id){
		return sysConfigIndexCustomMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findSysConfigIndexCustomByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysConfigIndexCustom>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by chopin
	 */
	@Override
	public List<SysConfigIndexCustom> findSysConfigIndexCustomByPage(SysConfigIndexCustom search){
		return sysConfigIndexCustomMapper.page(search);
	};
	/**
	 * 
	* @Title: findSysConfigIndexCustomByPage 
	* @Description: 查询全部
	* @return
	* @return List<SysConfigIndexCustom>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by chopin
	 */
	@Override
	public List<SysConfigIndexCustom> findAll(){
		return sysConfigIndexCustomMapper.queryAll();
	};
}
