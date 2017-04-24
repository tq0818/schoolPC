package com.yuxin.wx.company.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.ICompanyIntegralConfigService;
import com.yuxin.wx.company.mapper.CompanyIntegralConfigMapper;
import com.yuxin.wx.model.company.CompanyIntegralConfig;


/**
 * Service Implementation:CompanyIntegralConfig
 * @author chopin
 * @date 2016-5-18
 */
@Service
@Transactional
public class CompanyIntegralConfigServiceImpl extends BaseServiceImpl implements ICompanyIntegralConfigService {

	@Autowired
	private CompanyIntegralConfigMapper companyIntegralConfigMapper;
	
	/**
	 * 
	* @Title: saveCompanyIntegralConfig
	* @Description: 新增CompanyIntegralConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-18
	* @user by chopin
	 */
	@Override
	public void insert(CompanyIntegralConfig entity){
		companyIntegralConfigMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCompanyIntegralConfig 
	* @Description: 批量新增CompanyIntegralConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-18
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CompanyIntegralConfig> entity){
		companyIntegralConfigMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateCompanyIntegralConfig 
	* @Description: 编辑CompanyIntegralConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-18
	* @user by chopin
	 */
	@Override
	public void update(CompanyIntegralConfig entity){
		companyIntegralConfigMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteCompanyIntegralConfigById 
	* @Description: 根据id删除CompanyIntegralConfig
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-18
	* @user by chopin
	 */
	 @Override
	public void deleteCompanyIntegralConfigById(Integer id){
		companyIntegralConfigMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCompanyIntegralConfigByIds 
	* @Description: 根据id批量删除CompanyIntegralConfig
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-18
	* @user by chopin
	 */
	@Override
	public void deleteCompanyIntegralConfigByIds(Integer[] ids){
		companyIntegralConfigMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCompanyIntegralConfigById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-18
	* @user by chopin
	 */
	@Override
	public CompanyIntegralConfig findCompanyIntegralConfigById(Integer id){
		return companyIntegralConfigMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCompanyIntegralConfigByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyIntegralConfig>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-18
	* @user by chopin
	 */
	@Override
	public List<CompanyIntegralConfig> findCompanyIntegralConfigByPage(CompanyIntegralConfig search){
		return companyIntegralConfigMapper.page(search);
	}

	@Override
	public CompanyIntegralConfig findIntegralConfigByWhere(
			CompanyIntegralConfig search) {
		// TODO Auto-generated method stub
		return companyIntegralConfigMapper.findIntegralConfigByWhere(search);
	}

	@Override
	public CompanyIntegralConfig findByCompanyId(Integer companyId) {
		// TODO Auto-generated method stub
		return companyIntegralConfigMapper.findByCompanyId(companyId);
	};
}
