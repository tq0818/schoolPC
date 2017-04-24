package com.yuxin.wx.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.system.ISysConfigIcoService;
import com.yuxin.wx.model.system.SysConfigIco;
import com.yuxin.wx.system.mapper.SysConfigIcoMapper;

/**
 * Service Implementation:SysConfigIco
 * @author chopin
 * @date 2015-12-4
 */
@Service
@Transactional
public class SysConfigIcoServiceImpl extends BaseServiceImpl implements ISysConfigIcoService {

	@Autowired
	private SysConfigIcoMapper sysConfigIcoMapper;
	
	/**
	 * 
	* @Title: saveSysConfigIco
	* @Description: 新增SysConfigIco
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-4
	* @user by chopin
	 */
	@Override
	public void insert(SysConfigIco entity){
		sysConfigIcoMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveSysConfigIco 
	* @Description: 批量新增SysConfigIco
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-4
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<SysConfigIco> entity){
		sysConfigIcoMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateSysConfigIco 
	* @Description: 编辑SysConfigIco
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-4
	* @user by chopin
	 */
	@Override
	public void update(SysConfigIco entity){
		sysConfigIcoMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteSysConfigIcoById 
	* @Description: 根据id删除SysConfigIco
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-4
	* @user by chopin
	 */
	 @Override
	public void deleteSysConfigIcoById(Integer id){
		sysConfigIcoMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteSysConfigIcoByIds 
	* @Description: 根据id批量删除SysConfigIco
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-4
	* @user by chopin
	 */
	@Override
	public void deleteSysConfigIcoByIds(Integer[] ids){
		sysConfigIcoMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findSysConfigIcoById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-4
	* @user by chopin
	 */
	@Override
	public SysConfigIco findSysConfigIcoById(Integer id){
		return sysConfigIcoMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findSysConfigIcoByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysConfigIco>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-4
	* @user by chopin
	 */
	@Override
	public List<SysConfigIco> findSysConfigIcoByPage(SysConfigIco search){
		return sysConfigIcoMapper.page(search);
	}

	@Override
	public List<SysConfigIco> queryAllIcoLists(SysConfigIco search) {
		// TODO Auto-generated method stub
		return sysConfigIcoMapper.queryAllIcoLists(search);
	}

	@Override
	public List<SysConfigIco> queryUseIco(Integer companyId) {
		// TODO Auto-generated method stub
		return sysConfigIcoMapper.queryUseIco(companyId);
	}

	
}
