package com.yuxin.wx.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.system.ISysLiveReverseAuthService;
import com.yuxin.wx.model.system.SysLiveReverseAuth;
import com.yuxin.wx.system.mapper.SysLiveReverseAuthMapper;


/**
 * Service Implementation:SysLiveReverseAuth
 * @author wang.zx
 * @date 2015-11-6
 */
@Service
@Transactional
public class SysLiveReverseAuthServiceImpl extends BaseServiceImpl implements ISysLiveReverseAuthService {

	@Autowired
	private SysLiveReverseAuthMapper sysLiveReverseAuthMapper;
	
	/**
	 * 
	* @Title: saveSysLiveReverseAuth
	* @Description: 新增SysLiveReverseAuth
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-11-6
	* @user by chopin
	 */
	@Override
	public void insert(SysLiveReverseAuth entity){
		sysLiveReverseAuthMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveSysLiveReverseAuth 
	* @Description: 批量新增SysLiveReverseAuth
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-11-6
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<SysLiveReverseAuth> T){
		sysLiveReverseAuthMapper.batchInsert(T);
	};
	
	/**
	 * 
	* @Title: updateSysLiveReverseAuth 
	* @Description: 编辑SysLiveReverseAuth
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-11-6
	* @user by chopin
	 */
	@Override
	public void update(SysLiveReverseAuth T){
		sysLiveReverseAuthMapper.update(T);
	};
	
	/**
	 * 
	* @Title: deleteSysLiveReverseAuthById 
	* @Description: 根据id删除SysLiveReverseAuth
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-11-6
	* @user by chopin
	 */
	 @Override
	public void deleteSysLiveReverseAuthById(Integer id){
		sysLiveReverseAuthMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteSysLiveReverseAuthByIds 
	* @Description: 根据id批量删除SysLiveReverseAuth
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-11-6
	* @user by chopin
	 */
	@Override
	public void deleteSysLiveReverseAuthByIds(Integer[] ids){
		sysLiveReverseAuthMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findSysLiveReverseAuthById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-11-6
	* @user by chopin
	 */
	@Override
	public SysLiveReverseAuth findSysLiveReverseAuthById(Integer id){
		return sysLiveReverseAuthMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findSysLiveReverseAuthByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysLiveReverseAuth>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-11-6
	* @user by chopin
	 */
	@Override
	public List<SysLiveReverseAuth> findSysLiveReverseAuthByPage(SysLiveReverseAuth search){
		return sysLiveReverseAuthMapper.page(search);
	};
}
