package com.yuxin.wx.company.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.ICompanyTotalLiveStaticDetailService;
import com.yuxin.wx.company.mapper.CompanyTotalLiveStaticDetailMapper;
import com.yuxin.wx.model.company.CompanyTotalLiveStaticDetail;

/**
 * Service Implementation:CompanyTotalLiveStaticDetail
 * @author wang.zx
 * @date 2016-2-29
 */
@Service
@Transactional
public class CompanyTotalLiveStaticDetailServiceImpl extends BaseServiceImpl implements ICompanyTotalLiveStaticDetailService {

	@Autowired
	private CompanyTotalLiveStaticDetailMapper companyTotalLiveStaticDetailMapper;
	
	/**
	 * 
	* @Title: saveCompanyTotalLiveStaticDetail
	* @Description: 新增CompanyTotalLiveStaticDetail
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by chopin
	 */
	@Override
	public void insert(CompanyTotalLiveStaticDetail entity){
		companyTotalLiveStaticDetailMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCompanyTotalLiveStaticDetail 
	* @Description: 批量新增CompanyTotalLiveStaticDetail
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CompanyTotalLiveStaticDetail> T){
		companyTotalLiveStaticDetailMapper.batchInsert(T);
	};
	
	/**
	 * 
	* @Title: updateCompanyTotalLiveStaticDetail 
	* @Description: 编辑CompanyTotalLiveStaticDetail
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by chopin
	 */
	@Override
	public void update(CompanyTotalLiveStaticDetail T){
		companyTotalLiveStaticDetailMapper.update(T);
	};
	
	/**
	 * 
	* @Title: deleteCompanyTotalLiveStaticDetailById 
	* @Description: 根据id删除CompanyTotalLiveStaticDetail
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by chopin
	 */
	 @Override
	public void deleteCompanyTotalLiveStaticDetailById(Integer id){
		companyTotalLiveStaticDetailMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCompanyTotalLiveStaticDetailByIds 
	* @Description: 根据id批量删除CompanyTotalLiveStaticDetail
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by chopin
	 */
	@Override
	public void deleteCompanyTotalLiveStaticDetailByIds(Integer[] ids){
		companyTotalLiveStaticDetailMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCompanyTotalLiveStaticDetailById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by chopin
	 */
	@Override
	public CompanyTotalLiveStaticDetail findCompanyTotalLiveStaticDetailById(Integer id){
		return companyTotalLiveStaticDetailMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCompanyTotalLiveStaticDetailByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyTotalLiveStaticDetail>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by chopin
	 */
	@Override
	public List<CompanyTotalLiveStaticDetail> findCompanyTotalLiveStaticDetailByPage(CompanyTotalLiveStaticDetail search){
		return companyTotalLiveStaticDetailMapper.page(search);
	};
}
