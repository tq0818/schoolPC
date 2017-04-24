package com.yuxin.wx.company.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.ICompanyLiveWarmupZsService;
import com.yuxin.wx.company.mapper.CompanyLiveWarmupZsMapper;
import com.yuxin.wx.model.company.CompanyLiveWarmupZs;


/**
 * Service Implementation:CompanyLiveWarmupZs
 * @author wang.zx
 * @date 2015-12-11
 */
@Service
@Transactional
public class CompanyLiveWarmupZsServiceImpl extends BaseServiceImpl implements ICompanyLiveWarmupZsService {

	@Autowired
	private CompanyLiveWarmupZsMapper companyLiveWarmupZsMapper;
	
	/**
	 * 
	* @Title: saveCompanyLiveWarmupZs
	* @Description: 新增CompanyLiveWarmupZs
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-11
	* @user by chopin
	 */
	@Override
	public void insert(CompanyLiveWarmupZs entity){
		companyLiveWarmupZsMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCompanyLiveWarmupZs 
	* @Description: 批量新增CompanyLiveWarmupZs
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-11
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CompanyLiveWarmupZs> T){
		companyLiveWarmupZsMapper.batchInsert(T);
	};
	
	/**
	 * 
	* @Title: updateCompanyLiveWarmupZs 
	* @Description: 编辑CompanyLiveWarmupZs
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-11
	* @user by chopin
	 */
	@Override
	public void update(CompanyLiveWarmupZs T){
		companyLiveWarmupZsMapper.update(T);
	};
	
	/**
	 * 
	* @Title: deleteCompanyLiveWarmupZsById 
	* @Description: 根据id删除CompanyLiveWarmupZs
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-11
	* @user by chopin
	 */
	 @Override
	public void deleteCompanyLiveWarmupZsById(Integer id){
		companyLiveWarmupZsMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCompanyLiveWarmupZsByIds 
	* @Description: 根据id批量删除CompanyLiveWarmupZs
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-11
	* @user by chopin
	 */
	@Override
	public void deleteCompanyLiveWarmupZsByIds(Integer[] ids){
		companyLiveWarmupZsMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCompanyLiveWarmupZsById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-11
	* @user by chopin
	 */
	@Override
	public CompanyLiveWarmupZs findCompanyLiveWarmupZsById(Integer id){
		return companyLiveWarmupZsMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCompanyLiveWarmupZsByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyLiveWarmupZs>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-11
	* @user by chopin
	 */
	@Override
	public List<CompanyLiveWarmupZs> findCompanyLiveWarmupZsByPage(CompanyLiveWarmupZs search){
		return companyLiveWarmupZsMapper.page(search);
	};
}
