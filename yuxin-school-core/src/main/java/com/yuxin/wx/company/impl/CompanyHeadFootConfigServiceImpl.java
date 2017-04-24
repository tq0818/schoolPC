package com.yuxin.wx.company.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.ICompanyHeadFootConfigService;
import com.yuxin.wx.company.mapper.CompanyHeadFootConfigMapper;
import com.yuxin.wx.model.company.CompanyHeadFootConfig;


/**
 * Service Implementation:CompanyHeadFootConfig
 * @author chopin
 * @date 2016-2-29
 */
@Service
@Transactional
public class CompanyHeadFootConfigServiceImpl extends BaseServiceImpl implements ICompanyHeadFootConfigService {

	@Autowired
	private CompanyHeadFootConfigMapper companyHeadFootConfigMapper;
	
	/**
	 * 
	* @Title: saveCompanyHeadFootConfig
	* @Description: 新增CompanyHeadFootConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by chopin
	 */
	@Override
	public void insert(CompanyHeadFootConfig entity){
		companyHeadFootConfigMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCompanyHeadFootConfig 
	* @Description: 批量新增CompanyHeadFootConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CompanyHeadFootConfig> entity){
		companyHeadFootConfigMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateCompanyHeadFootConfig 
	* @Description: 编辑CompanyHeadFootConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by chopin
	 */
	@Override
	public void update(CompanyHeadFootConfig entity){
		companyHeadFootConfigMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteCompanyHeadFootConfigById 
	* @Description: 根据id删除CompanyHeadFootConfig
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by chopin
	 */
	 @Override
	public void deleteCompanyHeadFootConfigById(Integer id){
		companyHeadFootConfigMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCompanyHeadFootConfigByIds 
	* @Description: 根据id批量删除CompanyHeadFootConfig
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by chopin
	 */
	@Override
	public void deleteCompanyHeadFootConfigByIds(Integer[] ids){
		companyHeadFootConfigMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCompanyHeadFootConfigById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by chopin
	 */
	@Override
	public CompanyHeadFootConfig findCompanyHeadFootConfigById(Integer id){
		return companyHeadFootConfigMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCompanyHeadFootConfigByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyHeadFootConfig>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by chopin
	 */
	@Override
	public List<CompanyHeadFootConfig> findCompanyHeadFootConfigByPage(CompanyHeadFootConfig search){
		return companyHeadFootConfigMapper.page(search);
	}

	@Override
	public CompanyHeadFootConfig findFootConfigByCompany(
			CompanyHeadFootConfig search) {
		// TODO Auto-generated method stub
		return companyHeadFootConfigMapper.findFootConfigByCompany(search);
	}

	@Override
	public List<CompanyHeadFootConfig> findTemplatesById(Integer companyId) {
		// TODO Auto-generated method stub
		return companyHeadFootConfigMapper.findTemplatesById(companyId);
	};
}
