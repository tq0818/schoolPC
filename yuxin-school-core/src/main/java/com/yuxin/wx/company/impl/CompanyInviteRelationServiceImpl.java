package com.yuxin.wx.company.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.ICompanyInviteRelationService;
import com.yuxin.wx.company.mapper.CompanyInviteRelationMapper;
import com.yuxin.wx.model.company.CompanyInviteRelation;


/**
 * Service Implementation:CompanyInviteRelation
 * @author chopin
 * @date 2016-7-29
 */
@Service
@Transactional
public class CompanyInviteRelationServiceImpl extends BaseServiceImpl implements ICompanyInviteRelationService {

	@Autowired
	private CompanyInviteRelationMapper companyInviteRelationMapper;
	
	/**
	 * 
	* @Title: saveCompanyInviteRelation
	* @Description: 新增CompanyInviteRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-29
	* @user by chopin
	 */
	@Override
	public void insert(CompanyInviteRelation entity){
		companyInviteRelationMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCompanyInviteRelation 
	* @Description: 批量新增CompanyInviteRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-29
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CompanyInviteRelation> entity){
		companyInviteRelationMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateCompanyInviteRelation 
	* @Description: 编辑CompanyInviteRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-29
	* @user by chopin
	 */
	@Override
	public void update(CompanyInviteRelation entity){
		companyInviteRelationMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteCompanyInviteRelationById 
	* @Description: 根据id删除CompanyInviteRelation
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-29
	* @user by chopin
	 */
	 @Override
	public void deleteCompanyInviteRelationById(Integer id){
		companyInviteRelationMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCompanyInviteRelationByIds 
	* @Description: 根据id批量删除CompanyInviteRelation
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-29
	* @user by chopin
	 */
	@Override
	public void deleteCompanyInviteRelationByIds(Integer[] ids){
		companyInviteRelationMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCompanyInviteRelationById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-29
	* @user by chopin
	 */
	@Override
	public CompanyInviteRelation findCompanyInviteRelationById(Integer id){
		return companyInviteRelationMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCompanyInviteRelationByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyInviteRelation>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-29
	* @user by chopin
	 */
	@Override
	public List<CompanyInviteRelation> findCompanyInviteRelationByPage(CompanyInviteRelation search){
		return companyInviteRelationMapper.page(search);
	};
}
