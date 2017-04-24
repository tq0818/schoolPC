package com.yuxin.wx.system.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.system.ISysPageHeadFooterTemplateService;
import com.yuxin.wx.model.system.SysPageHeadFooterTemplate;
import com.yuxin.wx.system.mapper.SysPageHeadFooterTemplateMapper;


/**
 * Service Implementation:SysPageHeadFooterTemplate
 * @author chopin
 * @date 2016-2-29
 */
@Service
@Transactional
public class SysPageHeadFooterTemplateServiceImpl extends BaseServiceImpl implements ISysPageHeadFooterTemplateService {

	@Autowired
	private SysPageHeadFooterTemplateMapper sysPageHeadFooterTemplateMapper;
	
	/**
	 * 
	* @Title: saveSysPageHeadFooterTemplate
	* @Description: 新增SysPageHeadFooterTemplate
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by chopin
	 */
	@Override
	public void insert(SysPageHeadFooterTemplate entity){
		sysPageHeadFooterTemplateMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveSysPageHeadFooterTemplate 
	* @Description: 批量新增SysPageHeadFooterTemplate
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<SysPageHeadFooterTemplate> entity){
		sysPageHeadFooterTemplateMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateSysPageHeadFooterTemplate 
	* @Description: 编辑SysPageHeadFooterTemplate
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by chopin
	 */
	@Override
	public void update(SysPageHeadFooterTemplate entity){
		sysPageHeadFooterTemplateMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteSysPageHeadFooterTemplateById 
	* @Description: 根据id删除SysPageHeadFooterTemplate
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by chopin
	 */
	 @Override
	public void deleteSysPageHeadFooterTemplateById(Integer id){
		sysPageHeadFooterTemplateMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteSysPageHeadFooterTemplateByIds 
	* @Description: 根据id批量删除SysPageHeadFooterTemplate
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by chopin
	 */
	@Override
	public void deleteSysPageHeadFooterTemplateByIds(Integer[] ids){
		sysPageHeadFooterTemplateMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findSysPageHeadFooterTemplateById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by chopin
	 */
	@Override
	public SysPageHeadFooterTemplate findSysPageHeadFooterTemplateById(Integer id){
		return sysPageHeadFooterTemplateMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findSysPageHeadFooterTemplateByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysPageHeadFooterTemplate>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by chopin
	 */
	@Override
	public List<SysPageHeadFooterTemplate> findSysPageHeadFooterTemplateByPage(SysPageHeadFooterTemplate search){
		return sysPageHeadFooterTemplateMapper.page(search);
	}

	@Override
	public List<SysPageHeadFooterTemplate> findFootTempletes(String templateType) {
		// TODO Auto-generated method stub
		return sysPageHeadFooterTemplateMapper.queryAll();
	}

	@Override
	public Integer queryIsUseingLink(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sysPageHeadFooterTemplateMapper.queryIsUseingLink(map);
	};
}
