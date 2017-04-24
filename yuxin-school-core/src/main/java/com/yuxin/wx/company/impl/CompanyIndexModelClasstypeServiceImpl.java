package com.yuxin.wx.company.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.ICompanyIndexModelClasstypeService;
import com.yuxin.wx.company.mapper.CompanyIndexModelClasstypeMapper;
import com.yuxin.wx.model.company.CompanyIndexModelClasstype;

/**
 * Service Implementation:CompanyIndexModelClasstype
 * @author chopin
 * @date 2015-5-18
 */
@Service
@Transactional
public class CompanyIndexModelClasstypeServiceImpl extends BaseServiceImpl implements ICompanyIndexModelClasstypeService {

	@Autowired
	private CompanyIndexModelClasstypeMapper companyIndexModelClasstypeMapper;
	
	/**
	 * 
	* @Title: saveCompanyIndexModelClasstype
	* @Description: 新增CompanyIndexModelClasstype
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	@Override
	public void insert(CompanyIndexModelClasstype entity){
		companyIndexModelClasstypeMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCompanyIndexModelClasstype 
	* @Description: 批量新增CompanyIndexModelClasstype
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CompanyIndexModelClasstype> entity){
		companyIndexModelClasstypeMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateCompanyIndexModelClasstype 
	* @Description: 编辑CompanyIndexModelClasstype
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	@Override
	public void update(CompanyIndexModelClasstype entity){
		companyIndexModelClasstypeMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteCompanyIndexModelClasstypeById 
	* @Description: 根据id删除CompanyIndexModelClasstype
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	 @Override
	public void deleteCompanyIndexModelClasstypeById(Integer id){
		companyIndexModelClasstypeMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCompanyIndexModelClasstypeByIds 
	* @Description: 根据id批量删除CompanyIndexModelClasstype
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	@Override
	public void deleteCompanyIndexModelClasstypeByIds(Integer[] ids){
		companyIndexModelClasstypeMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCompanyIndexModelClasstypeById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	@Override
	public CompanyIndexModelClasstype findCompanyIndexModelClasstypeById(Integer id){
		return companyIndexModelClasstypeMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCompanyIndexModelClasstypeByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyIndexModelClasstype>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	@Override
	public List<CompanyIndexModelClasstype> findCompanyIndexModelClasstypeByPage(CompanyIndexModelClasstype search){
		return companyIndexModelClasstypeMapper.page(search);
	};
}
