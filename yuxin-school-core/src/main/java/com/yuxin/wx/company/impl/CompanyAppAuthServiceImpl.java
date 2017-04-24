package com.yuxin.wx.company.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.ICompanyAppAuthService;
import com.yuxin.wx.company.mapper.CompanyAppAuthMapper;
import com.yuxin.wx.model.company.CompanyAppAuth;


/**
 * Service Implementation:CompanyAppAuth
 * @author chopin
 * @date 2016-5-27
 */
@Service
@Transactional
public class CompanyAppAuthServiceImpl extends BaseServiceImpl implements ICompanyAppAuthService {

	@Autowired
	private CompanyAppAuthMapper companyAppAuthMapper;
	
	/**
	 * 
	* @Title: saveCompanyAppAuth
	* @Description: 新增CompanyAppAuth
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-27
	* @user by chopin
	 */
	@Override
	public void insert(CompanyAppAuth entity){
		companyAppAuthMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCompanyAppAuth 
	* @Description: 批量新增CompanyAppAuth
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-27
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CompanyAppAuth> entity){
		companyAppAuthMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateCompanyAppAuth 
	* @Description: 编辑CompanyAppAuth
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-27
	* @user by chopin
	 */
	@Override
	public void update(CompanyAppAuth entity){
		companyAppAuthMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteCompanyAppAuthById 
	* @Description: 根据id删除CompanyAppAuth
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-27
	* @user by chopin
	 */
	 @Override
	public void deleteCompanyAppAuthById(Integer id){
		companyAppAuthMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCompanyAppAuthByIds 
	* @Description: 根据id批量删除CompanyAppAuth
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-27
	* @user by chopin
	 */
	@Override
	public void deleteCompanyAppAuthByIds(Integer[] ids){
		companyAppAuthMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCompanyAppAuthById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-27
	* @user by chopin
	 */
	@Override
	public CompanyAppAuth findCompanyAppAuthById(Integer id){
		return companyAppAuthMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCompanyAppAuthByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyAppAuth>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-27
	* @user by chopin
	 */
	@Override
	public List<CompanyAppAuth> findCompanyAppAuthByPage(CompanyAppAuth search){
		return companyAppAuthMapper.page(search);
	}

	@Override
	public CompanyAppAuth findByParams(CompanyAppAuth caa) {
		// TODO Auto-generated method stub
		return companyAppAuthMapper.findByParams(caa);
	};
}
