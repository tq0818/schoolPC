package com.yuxin.wx.company.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.ICompanyVideoConfigService;
import com.yuxin.wx.company.mapper.CompanyVideoConfigMapper;
import com.yuxin.wx.model.company.CompanyVideoConfig;

/**
 * Service Implementation:CompanyVideoConfig
 * @author wang.zx
 * @date 2017-3-22
 */
@Service
@Transactional
public class CompanyVideoConfigServiceImpl extends BaseServiceImpl implements ICompanyVideoConfigService {

	@Autowired
	private CompanyVideoConfigMapper companyVideoConfigMapper;
	
	/**
	 * 
	* @Title: saveCompanyVideoConfig
	* @Description: 新增CompanyVideoConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-22
	* @user by chopin
	 */
	@Override
	public void insert(CompanyVideoConfig entity){
		companyVideoConfigMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCompanyVideoConfig 
	* @Description: 批量新增CompanyVideoConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-22
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CompanyVideoConfig> T){
		companyVideoConfigMapper.batchInsert(T);
	};
	
	/**
	 * 
	* @Title: updateCompanyVideoConfig 
	* @Description: 编辑CompanyVideoConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-22
	* @user by chopin
	 */
	@Override
	public void update(CompanyVideoConfig T){
		companyVideoConfigMapper.update(T);
	};
	
	/**
	 * 
	* @Title: deleteCompanyVideoConfigById 
	* @Description: 根据id删除CompanyVideoConfig
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-22
	* @user by chopin
	 */
	 @Override
	public void deleteCompanyVideoConfigById(Integer id){
		companyVideoConfigMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCompanyVideoConfigByIds 
	* @Description: 根据id批量删除CompanyVideoConfig
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-22
	* @user by chopin
	 */
	@Override
	public void deleteCompanyVideoConfigByIds(Integer[] ids){
		companyVideoConfigMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCompanyVideoConfigById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-22
	* @user by chopin
	 */
	@Override
	public CompanyVideoConfig findCompanyVideoConfigById(Integer id){
		return companyVideoConfigMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCompanyVideoConfigByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyVideoConfig>    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-22
	* @user by chopin
	 */
	@Override
	public List<CompanyVideoConfig> findCompanyVideoConfigByPage(CompanyVideoConfig search){
		return companyVideoConfigMapper.page(search);
	}

	@Override
	public CompanyVideoConfig findConfigByCompanyId(Integer companyId) {
		// TODO Auto-generated method stub
		return companyVideoConfigMapper.findConfigByCompanyId(companyId);
	};
}
