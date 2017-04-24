package com.yuxin.wx.system.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.system.ISysConfigIndexPageTemplateService;
import com.yuxin.wx.model.system.SysConfigIndexPageTemplate;
import com.yuxin.wx.system.mapper.SysConfigIndexPageTemplateMapper;


/**
 * Service Implementation:SysConfigIndexPageTemplate
 * @author chopin
 * @date 2017-3-24
 */
@Service
@Transactional
public class SysConfigIndexPageTemplateServiceImpl extends BaseServiceImpl implements ISysConfigIndexPageTemplateService {

	@Autowired
	private SysConfigIndexPageTemplateMapper sysConfigIndexPageTemplateMapper;
	
	/**
	 * 
	* @Title: saveSysConfigIndexPageTemplate
	* @Description: 新增SysConfigIndexPageTemplate
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-24
	* @user by chopin
	 */
	@Override
	public void insert(SysConfigIndexPageTemplate entity){
		sysConfigIndexPageTemplateMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveSysConfigIndexPageTemplate 
	* @Description: 批量新增SysConfigIndexPageTemplate
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-24
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<SysConfigIndexPageTemplate> entity){
		sysConfigIndexPageTemplateMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateSysConfigIndexPageTemplate 
	* @Description: 编辑SysConfigIndexPageTemplate
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-24
	* @user by chopin
	 */
	@Override
	public void update(SysConfigIndexPageTemplate entity){
		sysConfigIndexPageTemplateMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteSysConfigIndexPageTemplateById 
	* @Description: 根据id删除SysConfigIndexPageTemplate
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-24
	* @user by chopin
	 */
	 @Override
	public void deleteSysConfigIndexPageTemplateById(Integer id){
		sysConfigIndexPageTemplateMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteSysConfigIndexPageTemplateByIds 
	* @Description: 根据id批量删除SysConfigIndexPageTemplate
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-24
	* @user by chopin
	 */
	@Override
	public void deleteSysConfigIndexPageTemplateByIds(Integer[] ids){
		sysConfigIndexPageTemplateMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findSysConfigIndexPageTemplateById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-24
	* @user by chopin
	 */
	@Override
	public SysConfigIndexPageTemplate findSysConfigIndexPageTemplateById(Integer id){
		return sysConfigIndexPageTemplateMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findSysConfigIndexPageTemplateByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysConfigIndexPageTemplate>    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-24
	* @user by chopin
	 */
	@Override
	public List<SysConfigIndexPageTemplate> findSysConfigIndexPageTemplateByPage(SysConfigIndexPageTemplate search){
		return sysConfigIndexPageTemplateMapper.page(search);
	};
	
	
	@Override
	public List<SysConfigIndexPageTemplate> findBySearch(SysConfigIndexPageTemplate search){
		return sysConfigIndexPageTemplateMapper.findBySearch(search);
	};
}
