package com.yuxin.wx.company.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.ICompanyLoginConfigService;
import com.yuxin.wx.company.mapper.CompanyLoginConfigMapper;
import com.yuxin.wx.model.company.CompanyLoginConfig;

/**
 * Service Implementation:CompanyLoginConfig
 * @author chopin
 * @date 2016-7-4
 */
@Service
@Transactional
public class CompanyLoginConfigServiceImpl extends BaseServiceImpl implements ICompanyLoginConfigService {

	@Autowired
	private CompanyLoginConfigMapper companyLoginConfigMapper;
	
	/**
	 * 
	* @Title: saveCompanyLoginConfig
	* @Description: 新增CompanyLoginConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-4
	* @user by chopin
	 */
	@Override
	public void insert(CompanyLoginConfig entity){
		companyLoginConfigMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCompanyLoginConfig 
	* @Description: 批量新增CompanyLoginConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-4
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CompanyLoginConfig> entity){
		companyLoginConfigMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateCompanyLoginConfig 
	* @Description: 编辑CompanyLoginConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-4
	* @user by chopin
	 */
	@Override
	public void update(CompanyLoginConfig entity){
		companyLoginConfigMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteCompanyLoginConfigById 
	* @Description: 根据id删除CompanyLoginConfig
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-4
	* @user by chopin
	 */
	 @Override
	public void deleteCompanyLoginConfigById(Integer id){
		companyLoginConfigMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCompanyLoginConfigByIds 
	* @Description: 根据id批量删除CompanyLoginConfig
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-4
	* @user by chopin
	 */
	@Override
	public void deleteCompanyLoginConfigByIds(Integer[] ids){
		companyLoginConfigMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCompanyLoginConfigById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-4
	* @user by chopin
	 */
	@Override
	public CompanyLoginConfig findCompanyLoginConfigById(Integer id){
		return companyLoginConfigMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCompanyLoginConfigByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyLoginConfig>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-4
	* @user by chopin
	 */
	@Override
	public List<CompanyLoginConfig> findCompanyLoginConfigByPage(CompanyLoginConfig search){
		return companyLoginConfigMapper.page(search);
	}

	@Override
	public CompanyLoginConfig queryByCompanyId(CompanyLoginConfig search) {
		return companyLoginConfigMapper.queryByCompanyId(search);
	};
}
