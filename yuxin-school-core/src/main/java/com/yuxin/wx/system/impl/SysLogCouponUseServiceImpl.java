package com.yuxin.wx.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.system.ISysLogCouponUseService;
import com.yuxin.wx.model.system.SysLogCouponUse;
import com.yuxin.wx.system.mapper.SysLogCouponUseMapper;

/**
 * Service Implementation:SysLogCouponUse
 * @author chopin
 * @date 2016-6-28
 */
@Service
@Transactional
public class SysLogCouponUseServiceImpl extends BaseServiceImpl implements ISysLogCouponUseService {

	@Autowired
	private SysLogCouponUseMapper sysLogCouponUseMapper;
	
	/**
	 * 
	* @Title: saveSysLogCouponUse
	* @Description: 新增SysLogCouponUse
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-28
	* @user by chopin
	 */
	@Override
	public void insert(SysLogCouponUse entity){
		sysLogCouponUseMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveSysLogCouponUse 
	* @Description: 批量新增SysLogCouponUse
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-28
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<SysLogCouponUse> entity){
		sysLogCouponUseMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateSysLogCouponUse 
	* @Description: 编辑SysLogCouponUse
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-28
	* @user by chopin
	 */
	@Override
	public void update(SysLogCouponUse entity){
		sysLogCouponUseMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteSysLogCouponUseById 
	* @Description: 根据id删除SysLogCouponUse
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-28
	* @user by chopin
	 */
	 @Override
	public void deleteSysLogCouponUseById(Integer id){
		sysLogCouponUseMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteSysLogCouponUseByIds 
	* @Description: 根据id批量删除SysLogCouponUse
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-28
	* @user by chopin
	 */
	@Override
	public void deleteSysLogCouponUseByIds(Integer[] ids){
		sysLogCouponUseMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findSysLogCouponUseById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-28
	* @user by chopin
	 */
	@Override
	public SysLogCouponUse findSysLogCouponUseById(Integer id){
		return sysLogCouponUseMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findSysLogCouponUseByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysLogCouponUse>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-28
	* @user by chopin
	 */
	@Override
	public List<SysLogCouponUse> findSysLogCouponUseByPage(SysLogCouponUse search){
		return sysLogCouponUseMapper.page(search);
	};
}
