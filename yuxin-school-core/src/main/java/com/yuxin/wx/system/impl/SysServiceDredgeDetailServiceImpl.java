package com.yuxin.wx.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.system.ISysServiceDredgeDetailService;
import com.yuxin.wx.model.system.SysServiceDredgeDetail;
import com.yuxin.wx.system.mapper.SysServiceDredgeDetailMapper;

/**
 * Service Implementation:SysServiceDredgeDetail
 * @author wang.zx
 * @date 2016-12-12
 */
@Service
@Transactional
public class SysServiceDredgeDetailServiceImpl extends BaseServiceImpl implements ISysServiceDredgeDetailService {

	@Autowired
	private SysServiceDredgeDetailMapper sysServiceDredgeDetailMapper;
	
	/**
	 * 
	* @Title: saveSysServiceDredgeDetail
	* @Description: 新增SysServiceDredgeDetail
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-12
	* @user by chopin
	 */
	@Override
	public void insert(SysServiceDredgeDetail entity){
		sysServiceDredgeDetailMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveSysServiceDredgeDetail 
	* @Description: 批量新增SysServiceDredgeDetail
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-12
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<SysServiceDredgeDetail> T){
		sysServiceDredgeDetailMapper.batchInsert(T);
	};
	
	/**
	 * 
	* @Title: updateSysServiceDredgeDetail 
	* @Description: 编辑SysServiceDredgeDetail
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-12
	* @user by chopin
	 */
	@Override
	public void update(SysServiceDredgeDetail T){
		sysServiceDredgeDetailMapper.update(T);
	};
	
	/**
	 * 
	* @Title: deleteSysServiceDredgeDetailById 
	* @Description: 根据id删除SysServiceDredgeDetail
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-12
	* @user by chopin
	 */
	 @Override
	public void deleteSysServiceDredgeDetailById(Integer id){
		sysServiceDredgeDetailMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteSysServiceDredgeDetailByIds 
	* @Description: 根据id批量删除SysServiceDredgeDetail
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-12
	* @user by chopin
	 */
	@Override
	public void deleteSysServiceDredgeDetailByIds(Integer[] ids){
		sysServiceDredgeDetailMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findSysServiceDredgeDetailById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-12
	* @user by chopin
	 */
	@Override
	public SysServiceDredgeDetail findSysServiceDredgeDetailById(Integer id){
		return sysServiceDredgeDetailMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findSysServiceDredgeDetailByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysServiceDredgeDetail>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-12
	* @user by chopin
	 */
	@Override
	public List<SysServiceDredgeDetail> findSysServiceDredgeDetailByPage(SysServiceDredgeDetail search){
		return sysServiceDredgeDetailMapper.page(search);
	};
}
