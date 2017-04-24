package com.yuxin.wx.company.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.ICompanyAppBarConfigService;
import com.yuxin.wx.company.mapper.CompanyAppBarConfigMapper;
import com.yuxin.wx.model.company.CompanyAppBarConfig;


/**
 * Service Implementation:CompanyAppBarConfig
 * @author chopin
 * @date 2016-5-5
 */
@Service
@Transactional
public class CompanyAppBarConfigServiceImpl extends BaseServiceImpl implements ICompanyAppBarConfigService {

	@Autowired
	private CompanyAppBarConfigMapper companyAppBarConfigMapper;
	
	/**
	 * 
	* @Title: saveCompanyAppBarConfig
	* @Description: 新增CompanyAppBarConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-5
	* @user by chopin
	 */
	@Override
	public void insert(CompanyAppBarConfig entity){
		companyAppBarConfigMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCompanyAppBarConfig 
	* @Description: 批量新增CompanyAppBarConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-5
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CompanyAppBarConfig> entity){
		companyAppBarConfigMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateCompanyAppBarConfig 
	* @Description: 编辑CompanyAppBarConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-5
	* @user by chopin
	 */
	@Override
	public void update(CompanyAppBarConfig entity){
		companyAppBarConfigMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteCompanyAppBarConfigById 
	* @Description: 根据id删除CompanyAppBarConfig
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-5
	* @user by chopin
	 */
	 @Override
	public void deleteCompanyAppBarConfigById(Integer id){
		companyAppBarConfigMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCompanyAppBarConfigByIds 
	* @Description: 根据id批量删除CompanyAppBarConfig
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-5
	* @user by chopin
	 */
	@Override
	public void deleteCompanyAppBarConfigByIds(Integer[] ids){
		companyAppBarConfigMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCompanyAppBarConfigById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-5
	* @user by chopin
	 */
	@Override
	public CompanyAppBarConfig findCompanyAppBarConfigById(Integer id){
		return companyAppBarConfigMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCompanyAppBarConfigByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyAppBarConfig>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-5
	* @user by chopin
	 */
	@Override
	public List<CompanyAppBarConfig> findCompanyAppBarConfigByPage(CompanyAppBarConfig search){
		return companyAppBarConfigMapper.page(search);
	};
	/**
	 * 按条件查找
	 * @author licong
	 * @date 2016年5月11日 下午6:08:23
	 * @param  
	 * @param search
	 * @return
	 */
	@Override
	public List<CompanyAppBarConfig> findByCompanyAppBarConfig(CompanyAppBarConfig search){
		return companyAppBarConfigMapper.findByCompanyAppBarConfig(search);
	};
}
