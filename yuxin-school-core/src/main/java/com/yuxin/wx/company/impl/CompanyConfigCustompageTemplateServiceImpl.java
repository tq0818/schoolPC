package com.yuxin.wx.company.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.ICompanyConfigCustompageTemplateService;
import com.yuxin.wx.company.mapper.CompanyConfigCustompageTemplateMapper;
import com.yuxin.wx.model.company.CompanyConfigCustompageTemplate;


/**
 * Service Implementation:CompanyConfigCustompageTemplate
 * @author chopin
 * @date 2016-4-25
 */
@Service
@Transactional
public class CompanyConfigCustompageTemplateServiceImpl extends BaseServiceImpl implements ICompanyConfigCustompageTemplateService {

	@Autowired
	private CompanyConfigCustompageTemplateMapper companyConfigCustompageTemplateMapper;
	
	/**
	 * 
	* @Title: saveCompanyConfigCustompageTemplate
	* @Description: 新增CompanyConfigCustompageTemplate
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-4-25
	* @user by chopin
	 */
	@Override
	public void insert(CompanyConfigCustompageTemplate entity){
		companyConfigCustompageTemplateMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCompanyConfigCustompageTemplate 
	* @Description: 批量新增CompanyConfigCustompageTemplate
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-4-25
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CompanyConfigCustompageTemplate> entity){
		companyConfigCustompageTemplateMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateCompanyConfigCustompageTemplate 
	* @Description: 编辑CompanyConfigCustompageTemplate
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-4-25
	* @user by chopin
	 */
	@Override
	public void update(CompanyConfigCustompageTemplate entity){
		companyConfigCustompageTemplateMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteCompanyConfigCustompageTemplateById 
	* @Description: 根据id删除CompanyConfigCustompageTemplate
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-4-25
	* @user by chopin
	 */
	 @Override
	public void deleteCompanyConfigCustompageTemplateById(Integer id){
		companyConfigCustompageTemplateMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCompanyConfigCustompageTemplateByIds 
	* @Description: 根据id批量删除CompanyConfigCustompageTemplate
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-4-25
	* @user by chopin
	 */
	@Override
	public void deleteCompanyConfigCustompageTemplateByIds(Integer[] ids){
		companyConfigCustompageTemplateMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCompanyConfigCustompageTemplateById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-4-25
	* @user by chopin
	 */
	@Override
	public CompanyConfigCustompageTemplate findCompanyConfigCustompageTemplateById(Integer id){
		return companyConfigCustompageTemplateMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCompanyConfigCustompageTemplateByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyConfigCustompageTemplate>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-4-25
	* @user by chopin
	 */
	@Override
	public List<CompanyConfigCustompageTemplate> findCompanyConfigCustompageTemplateByPage(CompanyConfigCustompageTemplate search){
		return companyConfigCustompageTemplateMapper.page(search);
	}

	@Override
	public List<CompanyConfigCustompageTemplate> queryCustomList(
			CompanyConfigCustompageTemplate search) {
		// TODO Auto-generated method stub
		return companyConfigCustompageTemplateMapper.queryCustomList(search);
	};
}
