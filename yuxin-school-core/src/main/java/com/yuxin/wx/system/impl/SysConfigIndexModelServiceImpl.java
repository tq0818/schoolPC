package com.yuxin.wx.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.system.ISysConfigIndexModelService;
import com.yuxin.wx.model.system.SysConfigIndexModel;
import com.yuxin.wx.system.mapper.SysConfigIndexModelMapper;

/**
 * Service Implementation:SysConfigIndexModel
 * @author chopin
 * @date 2015-3-17
 */
@Service
@Transactional
public class SysConfigIndexModelServiceImpl extends BaseServiceImpl implements ISysConfigIndexModelService {

	@Autowired
	private SysConfigIndexModelMapper sysConfigIndexModelMapper;
	
	/**
	 * 
	* @Title: saveSysConfigIndexModel
	* @Description: 新增SysConfigIndexModel
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by chopin
	 */
	@Override
	public void insert(SysConfigIndexModel entity){
		sysConfigIndexModelMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveSysConfigIndexModel 
	* @Description: 批量新增SysConfigIndexModel
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<SysConfigIndexModel> entity){
		sysConfigIndexModelMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateSysConfigIndexModel 
	* @Description: 编辑SysConfigIndexModel
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by chopin
	 */
	@Override
	public void update(SysConfigIndexModel entity){
		sysConfigIndexModelMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteSysConfigIndexModelById 
	* @Description: 根据id删除SysConfigIndexModel
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by chopin
	 */
	 @Override
	public void deleteSysConfigIndexModelById(Integer id){
		sysConfigIndexModelMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteSysConfigIndexModelByIds 
	* @Description: 根据id批量删除SysConfigIndexModel
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by chopin
	 */
	@Override
	public void deleteSysConfigIndexModelByIds(Integer[] ids){
		sysConfigIndexModelMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findSysConfigIndexModelById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by chopin
	 */
	@Override
	public SysConfigIndexModel findSysConfigIndexModelById(Integer id){
		return sysConfigIndexModelMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findSysConfigIndexModelByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysConfigIndexModel>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by chopin
	 */
	@Override
	public List<SysConfigIndexModel> findSysConfigIndexModelByPage(SysConfigIndexModel search){
		return sysConfigIndexModelMapper.page(search);
	};
	
	/**
	 * 
	* @Title: findSysConfigIndexModelByPage 
	* @Description: 查询全部数据
	* @return
	* @return List<SysConfigIndexModel>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-17
	* @user by chopin
	 */
	@Override
	public List<SysConfigIndexModel> findAll(){
		return sysConfigIndexModelMapper.queryAll();
	}
	
}
