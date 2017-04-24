package com.yuxin.wx.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.system.ISysConfigIndexNewsService;
import com.yuxin.wx.model.system.SysConfigIndexNews;
import com.yuxin.wx.system.mapper.SysConfigIndexNewsMapper;

/**
 * Service Implementation:SysConfigIndexNews
 * @author chopin
 * @date 2015-5-18
 */
@Service
@Transactional
public class SysConfigIndexNewsServiceImpl extends BaseServiceImpl implements ISysConfigIndexNewsService {

	@Autowired
	private SysConfigIndexNewsMapper sysConfigIndexNewsMapper;
	
	/**
	 * 
	* @Title: saveSysConfigIndexNews
	* @Description: 新增SysConfigIndexNews
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	@Override
	public void insert(SysConfigIndexNews entity){
		sysConfigIndexNewsMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveSysConfigIndexNews 
	* @Description: 批量新增SysConfigIndexNews
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<SysConfigIndexNews> entity){
		sysConfigIndexNewsMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateSysConfigIndexNews 
	* @Description: 编辑SysConfigIndexNews
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	@Override
	public void update(SysConfigIndexNews entity){
		sysConfigIndexNewsMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteSysConfigIndexNewsById 
	* @Description: 根据id删除SysConfigIndexNews
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	 @Override
	public void deleteSysConfigIndexNewsById(Integer id){
		sysConfigIndexNewsMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteSysConfigIndexNewsByIds 
	* @Description: 根据id批量删除SysConfigIndexNews
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	@Override
	public void deleteSysConfigIndexNewsByIds(Integer[] ids){
		sysConfigIndexNewsMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findSysConfigIndexNewsById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	@Override
	public SysConfigIndexNews findSysConfigIndexNewsById(Integer id){
		return sysConfigIndexNewsMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findSysConfigIndexNewsByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysConfigIndexNews>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	@Override
	public List<SysConfigIndexNews> findSysConfigIndexNewsByPage(SysConfigIndexNews search){
		return sysConfigIndexNewsMapper.page(search);
	};
}
