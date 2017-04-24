package com.yuxin.wx.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.system.ISysConfigHelpService;
import com.yuxin.wx.model.system.SysConfigHelp;
import com.yuxin.wx.system.mapper.SysConfigHelpMapper;

/**
 * Service Implementation:SysConfigHelp
 * @author chopin
 * @date 2015-7-14
 */
@Service
@Transactional
public class SysConfigHelpServiceImpl extends BaseServiceImpl implements ISysConfigHelpService {

	@Autowired
	private SysConfigHelpMapper sysConfigHelpMapper;
	
	/**
	 * 
	* @Title: saveSysConfigHelp
	* @Description: 新增SysConfigHelp
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-14
	* @user by chopin
	 */
	@Override
	public void insert(SysConfigHelp entity){
		sysConfigHelpMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveSysConfigHelp 
	* @Description: 批量新增SysConfigHelp
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-14
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<SysConfigHelp> entity){
		sysConfigHelpMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateSysConfigHelp 
	* @Description: 编辑SysConfigHelp
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-14
	* @user by chopin
	 */
	@Override
	public void update(SysConfigHelp entity){
		sysConfigHelpMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteSysConfigHelpById 
	* @Description: 根据id删除SysConfigHelp
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-14
	* @user by chopin
	 */
	 @Override
	public void deleteSysConfigHelpById(Integer id){
		sysConfigHelpMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteSysConfigHelpByIds 
	* @Description: 根据id批量删除SysConfigHelp
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-14
	* @user by chopin
	 */
	@Override
	public void deleteSysConfigHelpByIds(Integer[] ids){
		sysConfigHelpMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findSysConfigHelpById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-14
	* @user by chopin
	 */
	@Override
	public SysConfigHelp findSysConfigHelpById(Integer id){
		return sysConfigHelpMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findSysConfigHelpByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysConfigHelp>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-14
	* @user by chopin
	 */
	@Override
	public List<SysConfigHelp> findSysConfigHelpByPage(SysConfigHelp search){
		return sysConfigHelpMapper.page(search);
	}

	@Override
	public SysConfigHelp findByLocation(String location) {
		return sysConfigHelpMapper.findByLocation(location);
	};
}
