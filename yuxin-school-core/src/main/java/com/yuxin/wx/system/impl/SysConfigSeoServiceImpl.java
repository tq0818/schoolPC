package com.yuxin.wx.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.system.ISysConfigSeoService;
import com.yuxin.wx.model.system.SysConfigSeo;
import com.yuxin.wx.system.mapper.SysConfigSeoMapper;

/**
 * Service Implementation:SysConfigSeo
 * @author chopin
 * @date 2015-12-3
 */
@Service
@Transactional
public class SysConfigSeoServiceImpl extends BaseServiceImpl implements ISysConfigSeoService {

	@Autowired
	private SysConfigSeoMapper sysConfigSeoMapper;
	
	/**
	 * 
	* @Title: saveSysConfigSeo
	* @Description: 新增SysConfigSeo
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-3
	* @user by chopin
	 */
	@Override
	public void insert(SysConfigSeo entity){
		sysConfigSeoMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveSysConfigSeo 
	* @Description: 批量新增SysConfigSeo
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-3
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<SysConfigSeo> entity){
		sysConfigSeoMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateSysConfigSeo 
	* @Description: 编辑SysConfigSeo
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-3
	* @user by chopin
	 */
	@Override
	public void update(SysConfigSeo entity){
		sysConfigSeoMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteSysConfigSeoById 
	* @Description: 根据id删除SysConfigSeo
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-3
	* @user by chopin
	 */
	 @Override
	public void deleteSysConfigSeoById(Integer id){
		sysConfigSeoMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteSysConfigSeoByIds 
	* @Description: 根据id批量删除SysConfigSeo
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-3
	* @user by chopin
	 */
	@Override
	public void deleteSysConfigSeoByIds(Integer[] ids){
		sysConfigSeoMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findSysConfigSeoById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-3
	* @user by chopin
	 */
	@Override
	public SysConfigSeo findSysConfigSeoById(Integer id){
		return sysConfigSeoMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findSysConfigSeoByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysConfigSeo>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-3
	* @user by chopin
	 */
	@Override
	public List<SysConfigSeo> findSysConfigSeoByPage(SysConfigSeo search){
		return sysConfigSeoMapper.page(search);
	}

	@Override
	public List<SysConfigSeo> queryAllSeoContents(SysConfigSeo search) {
		// TODO Auto-generated method stub
		return sysConfigSeoMapper.queryAllSeoContents(search);
	};
}
