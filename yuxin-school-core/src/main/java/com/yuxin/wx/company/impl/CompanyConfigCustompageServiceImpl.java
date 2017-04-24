package com.yuxin.wx.company.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.mchange.v2.c3p0.QueryConnectionTester;
import com.yuxin.wx.api.company.ICompanyConfigCustompageService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.company.mapper.CompanyConfigCustompageMapper;
import com.yuxin.wx.model.company.CompanyConfigCustompage;


/**
 * Service Implementation:CompanyConfigCustompage
 * @author chopin
 * @date 2016-4-25
 */
@Service
@Transactional
public class CompanyConfigCustompageServiceImpl extends BaseServiceImpl implements ICompanyConfigCustompageService {

	@Autowired
	private CompanyConfigCustompageMapper companyConfigCustompageMapper;
	
	/**
	 * 
	* @Title: saveCompanyConfigCustompage
	* @Description: 新增CompanyConfigCustompage
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-4-25
	* @user by chopin
	 */
	@Override
	public void insert(CompanyConfigCustompage entity){
		companyConfigCustompageMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCompanyConfigCustompage 
	* @Description: 批量新增CompanyConfigCustompage
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-4-25
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CompanyConfigCustompage> entity){
		companyConfigCustompageMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateCompanyConfigCustompage 
	* @Description: 编辑CompanyConfigCustompage
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-4-25
	* @user by chopin
	 */
	@Override
	public void update(CompanyConfigCustompage entity){
		companyConfigCustompageMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteCompanyConfigCustompageById 
	* @Description: 根据id删除CompanyConfigCustompage
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-4-25
	* @user by chopin
	 */
	 @Override
	public void deleteCompanyConfigCustompageById(Integer id){
		companyConfigCustompageMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCompanyConfigCustompageByIds 
	* @Description: 根据id批量删除CompanyConfigCustompage
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-4-25
	* @user by chopin
	 */
	@Override
	public void deleteCompanyConfigCustompageByIds(Integer[] ids){
		companyConfigCustompageMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCompanyConfigCustompageById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-4-25
	* @user by chopin
	 */
	@Override
	public CompanyConfigCustompage findCompanyConfigCustompageById(Integer id){
		return companyConfigCustompageMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCompanyConfigCustompageByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyConfigCustompage>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-4-25
	* @user by chopin
	 */
	@Override
	public List<CompanyConfigCustompage> findCompanyConfigCustompageByPage(CompanyConfigCustompage search){
		return companyConfigCustompageMapper.page(search);
	}

	@Override
	public PageFinder<CompanyConfigCustompage> queryCompanyCustomList(
			CompanyConfigCustompage search) {
		List<CompanyConfigCustompage> data=companyConfigCustompageMapper.queryCompanyCustomList(search);
		Integer count=companyConfigCustompageMapper.queryCompanyCustomListCount(search);
		PageFinder<CompanyConfigCustompage> pageFinder=new PageFinder<CompanyConfigCustompage>(search.getPage(), search.getPageSize(), count, data);
		return pageFinder;
	}

	@Override
	public Integer templeteCount(CompanyConfigCustompage search) {
		// TODO Auto-generated method stub
		return companyConfigCustompageMapper.templeteCount(search);
	}

	@Override
	public List<CompanyConfigCustompage> queryCompanyCustomListByCondition(
			CompanyConfigCustompage search) {
		// TODO Auto-generated method stub
		return companyConfigCustompageMapper.queryCompanyCustomListByCondition(search);
	};
}
