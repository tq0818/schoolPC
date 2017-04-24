package com.yuxin.wx.company.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.ICompanyRegisterConfigService;
import com.yuxin.wx.company.mapper.CompanyRegisterConfigMapper;
import com.yuxin.wx.model.company.CompanyRegisterConfig;


/**
 * Service Implementation:CompanyRegisterConfig
 * @author chopin
 * @date 2016-7-4
 */
@Service
@Transactional
public class CompanyRegisterConfigServiceImpl extends BaseServiceImpl implements ICompanyRegisterConfigService {

	@Autowired
	private CompanyRegisterConfigMapper companyRegisterConfigMapper;
	
	/**
	 * 
	* @Title: saveCompanyRegisterConfig
	* @Description: 新增CompanyRegisterConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-4
	* @user by chopin
	 */
	@Override
	public void insert(CompanyRegisterConfig entity){
		companyRegisterConfigMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCompanyRegisterConfig 
	* @Description: 批量新增CompanyRegisterConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-4
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CompanyRegisterConfig> entity){
		companyRegisterConfigMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateCompanyRegisterConfig 
	* @Description: 编辑CompanyRegisterConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-4
	* @user by chopin
	 */
	@Override
	public void update(CompanyRegisterConfig entity){
		companyRegisterConfigMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteCompanyRegisterConfigById 
	* @Description: 根据id删除CompanyRegisterConfig
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-4
	* @user by chopin
	 */
	 @Override
	public void deleteCompanyRegisterConfigById(Integer id){
		companyRegisterConfigMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCompanyRegisterConfigByIds 
	* @Description: 根据id批量删除CompanyRegisterConfig
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-4
	* @user by chopin
	 */
	@Override
	public void deleteCompanyRegisterConfigByIds(Integer[] ids){
		companyRegisterConfigMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCompanyRegisterConfigById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-4
	* @user by chopin
	 */
	@Override
	public CompanyRegisterConfig findCompanyRegisterConfigById(Integer id){
		return companyRegisterConfigMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCompanyRegisterConfigByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyRegisterConfig>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-4
	* @user by chopin
	 */
	@Override
	public List<CompanyRegisterConfig> findCompanyRegisterConfigByPage(CompanyRegisterConfig search){
		return companyRegisterConfigMapper.page(search);
	}

	@Override
	public CompanyRegisterConfig queryByCompanyId(CompanyRegisterConfig crc) {
		return companyRegisterConfigMapper.queryByCompanyId(crc);
	};
}
