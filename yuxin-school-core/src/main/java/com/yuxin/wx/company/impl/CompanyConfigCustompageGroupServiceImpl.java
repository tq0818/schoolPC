package com.yuxin.wx.company.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.ICompanyConfigCustompageGroupService;
import com.yuxin.wx.company.mapper.CompanyConfigCustompageGroupMapper;
import com.yuxin.wx.model.company.CompanyConfigCustompageGroup;


/**
 * Service Implementation:CompanyConfigCustompageGroup
 * @author chopin
 * @date 2016-4-25
 */
@Service
@Transactional
public class CompanyConfigCustompageGroupServiceImpl extends BaseServiceImpl implements ICompanyConfigCustompageGroupService {

	@Autowired
	private CompanyConfigCustompageGroupMapper companyConfigCustompageGroupMapper;
	
	/**
	 * 
	* @Title: saveCompanyConfigCustompageGroup
	* @Description: 新增CompanyConfigCustompageGroup
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-4-25
	* @user by chopin
	 */
	@Override
	public void insert(CompanyConfigCustompageGroup entity){
		companyConfigCustompageGroupMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCompanyConfigCustompageGroup 
	* @Description: 批量新增CompanyConfigCustompageGroup
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-4-25
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CompanyConfigCustompageGroup> entity){
		companyConfigCustompageGroupMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateCompanyConfigCustompageGroup 
	* @Description: 编辑CompanyConfigCustompageGroup
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-4-25
	* @user by chopin
	 */
	@Override
	public void update(CompanyConfigCustompageGroup entity){
		companyConfigCustompageGroupMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteCompanyConfigCustompageGroupById 
	* @Description: 根据id删除CompanyConfigCustompageGroup
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-4-25
	* @user by chopin
	 */
	 @Override
	public void deleteCompanyConfigCustompageGroupById(Integer id){
		companyConfigCustompageGroupMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCompanyConfigCustompageGroupByIds 
	* @Description: 根据id批量删除CompanyConfigCustompageGroup
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-4-25
	* @user by chopin
	 */
	@Override
	public void deleteCompanyConfigCustompageGroupByIds(Integer[] ids){
		companyConfigCustompageGroupMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCompanyConfigCustompageGroupById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-4-25
	* @user by chopin
	 */
	@Override
	public CompanyConfigCustompageGroup findCompanyConfigCustompageGroupById(Integer id){
		return companyConfigCustompageGroupMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCompanyConfigCustompageGroupByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyConfigCustompageGroup>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-4-25
	* @user by chopin
	 */
	@Override
	public List<CompanyConfigCustompageGroup> findCompanyConfigCustompageGroupByPage(CompanyConfigCustompageGroup search){
		return companyConfigCustompageGroupMapper.page(search);
	}

	@Override
	public List<CompanyConfigCustompageGroup> queryCustomGroupList(CompanyConfigCustompageGroup search) {
		// TODO Auto-generated method stub
		return companyConfigCustompageGroupMapper.queryCustomGroupList(search);
	}

	@Override
	public List<CompanyConfigCustompageGroup> queryCustomGroupByCondition(
			Map<String, Object> map) {
		// TODO Auto-generated method stub
		return companyConfigCustompageGroupMapper.queryCustomGroupByCondition(map);
	};
}
