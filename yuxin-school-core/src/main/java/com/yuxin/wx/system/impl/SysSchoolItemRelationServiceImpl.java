package com.yuxin.wx.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.system.ISysSchoolItemRelationService;
import com.yuxin.wx.model.system.SysSchoolItemRelation;
import com.yuxin.wx.system.mapper.SysSchoolItemRelationMapper;

/**
 * Service Implementation:SysSchoolItemRelation
 * @author chopin
 * @date 2015-3-12
 */
@Service
@Transactional
public class SysSchoolItemRelationServiceImpl extends BaseServiceImpl implements ISysSchoolItemRelationService {

	@Autowired
	private SysSchoolItemRelationMapper sysSchoolItemRelationMapper;
	
	/**
	 * 
	* @Title: saveSysSchoolItemRelation
	* @Description: 新增SysSchoolItemRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-12
	* @user by chopin
	 */
	@Override
	public void insert(SysSchoolItemRelation entity){
		sysSchoolItemRelationMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveSysSchoolItemRelation 
	* @Description: 批量新增SysSchoolItemRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-12
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<SysSchoolItemRelation> entity){
		sysSchoolItemRelationMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateSysSchoolItemRelation 
	* @Description: 编辑SysSchoolItemRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-12
	* @user by chopin
	 */
	@Override
	public void update(SysSchoolItemRelation entity){
		sysSchoolItemRelationMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteSysSchoolItemRelationById 
	* @Description: 根据id删除SysSchoolItemRelation
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-12
	* @user by chopin
	 */
	 @Override
	public void deleteSysSchoolItemRelationById(Integer id){
		sysSchoolItemRelationMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteSysSchoolItemRelationByIds 
	* @Description: 根据id批量删除SysSchoolItemRelation
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-12
	* @user by chopin
	 */
	@Override
	public void deleteSysSchoolItemRelationByIds(Integer[] ids){
		sysSchoolItemRelationMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findSysSchoolItemRelationById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-12
	* @user by chopin
	 */
	@Override
	public SysSchoolItemRelation findSysSchoolItemRelationById(Integer id){
		return sysSchoolItemRelationMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findSysSchoolItemRelationByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysSchoolItemRelation>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-12
	* @user by chopin
	 */
	@Override
	public List<SysSchoolItemRelation> findSysSchoolItemRelationByPage(SysSchoolItemRelation search){
		return sysSchoolItemRelationMapper.page(search);
	}

	@Override
	public SysSchoolItemRelation findSysSchoolItemRelationByItemId(SysSchoolItemRelation search) {
		// TODO Auto-generated method stub
		return sysSchoolItemRelationMapper.findSysSchoolItemRelationByItemId(search);
	};
}
