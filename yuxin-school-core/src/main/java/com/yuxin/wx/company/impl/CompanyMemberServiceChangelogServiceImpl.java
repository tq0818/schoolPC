package com.yuxin.wx.company.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.ICompanyMemberServiceChangelogService;
import com.yuxin.wx.company.mapper.CompanyMemberServiceChangelogMapper;
import com.yuxin.wx.model.company.CompanyMemberServiceChangelog;

/**
 * Service Implementation:CompanyMemberServiceChangelog
 * @author wang.zx
 * @date 2015-6-25
 */
@Service
@Transactional
public class CompanyMemberServiceChangelogServiceImpl extends BaseServiceImpl implements ICompanyMemberServiceChangelogService {

	@Autowired
	private CompanyMemberServiceChangelogMapper companyMemberServiceChangelogMapper;
	
	/**
	 * 
	* @Title: saveCompanyMemberServiceChangelog
	* @Description: 新增CompanyMemberServiceChangelog
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-25
	* @user by chopin
	 */
	@Override
	public void insert(CompanyMemberServiceChangelog entity){
		companyMemberServiceChangelogMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCompanyMemberServiceChangelog 
	* @Description: 批量新增CompanyMemberServiceChangelog
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-25
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CompanyMemberServiceChangelog> entity){
		companyMemberServiceChangelogMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateCompanyMemberServiceChangelog 
	* @Description: 编辑CompanyMemberServiceChangelog
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-25
	* @user by chopin
	 */
	@Override
	public void update(CompanyMemberServiceChangelog entity){
		companyMemberServiceChangelogMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteCompanyMemberServiceChangelogById 
	* @Description: 根据id删除CompanyMemberServiceChangelog
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-25
	* @user by chopin
	 */
	 @Override
	public void deleteCompanyMemberServiceChangelogById(Integer id){
		companyMemberServiceChangelogMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCompanyMemberServiceChangelogByIds 
	* @Description: 根据id批量删除CompanyMemberServiceChangelog
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-25
	* @user by chopin
	 */
	@Override
	public void deleteCompanyMemberServiceChangelogByIds(Integer[] ids){
		companyMemberServiceChangelogMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCompanyMemberServiceChangelogById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-25
	* @user by chopin
	 */
	@Override
	public CompanyMemberServiceChangelog findCompanyMemberServiceChangelogById(Integer id){
		return companyMemberServiceChangelogMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCompanyMemberServiceChangelogByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyMemberServiceChangelog>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-25
	* @user by chopin
	 */
	@Override
	public List<CompanyMemberServiceChangelog> findCompanyMemberServiceChangelogByPage(CompanyMemberServiceChangelog search){
		return companyMemberServiceChangelogMapper.page(search);
	};
}
