package com.yuxin.wx.company.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.ICompanyIndexModelNewsService;
import com.yuxin.wx.company.mapper.CompanyIndexModelNewsMapper;
import com.yuxin.wx.model.company.CompanyIndexModelNews;

/**
 * Service Implementation:CompanyIndexModelNews
 * @author chopin
 * @date 2015-5-18
 */
@Service
@Transactional
public class CompanyIndexModelNewsServiceImpl extends BaseServiceImpl implements ICompanyIndexModelNewsService {

	@Autowired
	private CompanyIndexModelNewsMapper companyIndexModelNewsMapper;
	
	/**
	 * 
	* @Title: saveCompanyIndexModelNews
	* @Description: 新增CompanyIndexModelNews
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	@Override
	public void insert(CompanyIndexModelNews entity){
		companyIndexModelNewsMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCompanyIndexModelNews 
	* @Description: 批量新增CompanyIndexModelNews
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CompanyIndexModelNews> entity){
		companyIndexModelNewsMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateCompanyIndexModelNews 
	* @Description: 编辑CompanyIndexModelNews
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	@Override
	public void update(CompanyIndexModelNews entity){
		companyIndexModelNewsMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteCompanyIndexModelNewsById 
	* @Description: 根据id删除CompanyIndexModelNews
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	 @Override
	public void deleteCompanyIndexModelNewsById(Integer id){
		companyIndexModelNewsMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCompanyIndexModelNewsByIds 
	* @Description: 根据id批量删除CompanyIndexModelNews
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	@Override
	public void deleteCompanyIndexModelNewsByIds(Integer[] ids){
		companyIndexModelNewsMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCompanyIndexModelNewsById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	@Override
	public CompanyIndexModelNews findCompanyIndexModelNewsById(Integer id){
		return companyIndexModelNewsMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCompanyIndexModelNewsByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyIndexModelNews>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	@Override
	public List<CompanyIndexModelNews> findCompanyIndexModelNewsByPage(CompanyIndexModelNews search){
		return companyIndexModelNewsMapper.page(search);
	};
}
