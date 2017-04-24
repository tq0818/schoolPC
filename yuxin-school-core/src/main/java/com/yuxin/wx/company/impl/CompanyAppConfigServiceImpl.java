package com.yuxin.wx.company.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.ICompanyAppConfigService;
import com.yuxin.wx.company.mapper.CompanyAppConfigMapper;
import com.yuxin.wx.model.company.CompanyAppConfig;

/**
 * Service Implementation:CompanyAppConfig
 * @author chopin
 * @date 2016-5-27
 */
@Service
@Transactional
public class CompanyAppConfigServiceImpl extends BaseServiceImpl implements ICompanyAppConfigService {

	@Autowired
	private CompanyAppConfigMapper companyAppConfigMapper;

	/**
	 * 
	* @Title: saveCompanyAppConfig
	* @Description: 新增CompanyAppConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-27
	* @user by chopin
	 */
	@Override
	public void insert(CompanyAppConfig entity){
		companyAppConfigMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCompanyAppConfig 
	* @Description: 批量新增CompanyAppConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-27
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CompanyAppConfig> entity){
		companyAppConfigMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateCompanyAppConfig 
	* @Description: 编辑CompanyAppConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-27
	* @user by chopin
	 */
	@Override
	public void update(CompanyAppConfig entity){
		companyAppConfigMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteCompanyAppConfigById 
	* @Description: 根据id删除CompanyAppConfig
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-27
	* @user by chopin
	 */
	 @Override
	public void deleteCompanyAppConfigById(Integer id){
		companyAppConfigMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCompanyAppConfigByIds 
	* @Description: 根据id批量删除CompanyAppConfig
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-27
	* @user by chopin
	 */
	@Override
	public void deleteCompanyAppConfigByIds(Integer[] ids){
		companyAppConfigMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCompanyAppConfigById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-27
	* @user by chopin
	 */
	@Override
	public CompanyAppConfig findCompanyAppConfigById(Integer id){
		return companyAppConfigMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCompanyAppConfigByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyAppConfig>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-27
	* @user by chopin
	 */
	@Override
	public List<CompanyAppConfig> findCompanyAppConfigByPage(CompanyAppConfig search){
		return companyAppConfigMapper.page(search);
	}

	@Override
	public CompanyAppConfig queryCompanyConfig(Integer companyId) {
		// TODO Auto-generated method stub
		return companyAppConfigMapper.queryCompanyConfig(companyId);
	};
	
}
