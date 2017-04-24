package com.yuxin.wx.company.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.ICompanyPicsService;
import com.yuxin.wx.company.mapper.CompanyPicsMapper;
import com.yuxin.wx.model.company.CompanyPics;

/**
 * Service Implementation:CompanyPics
 * @author chopin
 * @date 2015-5-14
 */
@Service
@Transactional
public class CompanyPicsServiceImpl extends BaseServiceImpl implements ICompanyPicsService {

	@Autowired
	private CompanyPicsMapper companyPicsMapper;
	
	/**
	 * 
	* @Title: saveCompanyPics
	* @Description: 新增CompanyPics
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-14
	* @user by chopin
	 */
	@Override
	public void insert(CompanyPics entity){
		companyPicsMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCompanyPics 
	* @Description: 批量新增CompanyPics
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-14
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CompanyPics> entity){
		companyPicsMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateCompanyPics 
	* @Description: 编辑CompanyPics
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-14
	* @user by chopin
	 */
	@Override
	public void update(CompanyPics entity){
		companyPicsMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteCompanyPicsById 
	* @Description: 根据id删除CompanyPics
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-14
	* @user by chopin
	 */
	 @Override
	public void deleteCompanyPicsById(Integer id){
		companyPicsMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCompanyPicsByIds 
	* @Description: 根据id批量删除CompanyPics
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-14
	* @user by chopin
	 */
	@Override
	public void deleteCompanyPicsByIds(Integer[] ids){
		companyPicsMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCompanyPicsById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-14
	* @user by chopin
	 */
	@Override
	public CompanyPics findCompanyPicsById(Integer id){
		return companyPicsMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCompanyPicsByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyPics>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-14
	* @user by chopin
	 */
	@Override
	public List<CompanyPics> findCompanyPicsByPage(CompanyPics search){
		return companyPicsMapper.page(search);
	};
}
