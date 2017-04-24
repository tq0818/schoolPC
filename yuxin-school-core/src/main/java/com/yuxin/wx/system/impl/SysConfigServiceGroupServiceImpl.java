package com.yuxin.wx.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.system.ISysConfigServiceGroupService;
import com.yuxin.wx.model.system.SysConfigServiceGroup;
import com.yuxin.wx.system.mapper.SysConfigServiceGroupMapper;

/**
 * Service Implementation:SysConfigServiceGroup
 * @author chopin
 * @date 2015-8-12
 */
@Service
@Transactional
public class SysConfigServiceGroupServiceImpl extends BaseServiceImpl implements ISysConfigServiceGroupService {

	@Autowired
	private SysConfigServiceGroupMapper sysConfigServiceGroupMapper;
	
	/**
	 * 
	* @Title: saveSysConfigServiceGroup
	* @Description: 新增SysConfigServiceGroup
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-12
	* @user by chopin
	 */
	@Override
	public void insert(SysConfigServiceGroup entity){
		sysConfigServiceGroupMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveSysConfigServiceGroup 
	* @Description: 批量新增SysConfigServiceGroup
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-12
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<SysConfigServiceGroup> entity){
		sysConfigServiceGroupMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateSysConfigServiceGroup 
	* @Description: 编辑SysConfigServiceGroup
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-12
	* @user by chopin
	 */
	@Override
	public void update(SysConfigServiceGroup entity){
		sysConfigServiceGroupMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteSysConfigServiceGroupById 
	* @Description: 根据id删除SysConfigServiceGroup
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-12
	* @user by chopin
	 */
	 @Override
	public void deleteSysConfigServiceGroupById(Integer id){
		sysConfigServiceGroupMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteSysConfigServiceGroupByIds 
	* @Description: 根据id批量删除SysConfigServiceGroup
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-12
	* @user by chopin
	 */
	@Override
	public void deleteSysConfigServiceGroupByIds(Integer[] ids){
		sysConfigServiceGroupMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findSysConfigServiceGroupById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-12
	* @user by chopin
	 */
	@Override
	public SysConfigServiceGroup findSysConfigServiceGroupById(Integer id){
		return sysConfigServiceGroupMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findSysConfigServiceGroupByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysConfigServiceGroup>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-12
	* @user by chopin
	 */
	@Override
	public List<SysConfigServiceGroup> findSysConfigServiceGroupByPage(SysConfigServiceGroup search){
		return sysConfigServiceGroupMapper.page(search);
	}

	@Override
	public List<SysConfigServiceGroup> findByCode(String code) {
		// TODO Auto-generated method stub
		return sysConfigServiceGroupMapper.findByCode(code);
	};
}
