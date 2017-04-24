package com.yuxin.wx.company.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.ICompanyIndexModelCustomService;
import com.yuxin.wx.company.mapper.CompanyIndexModelCustomMapper;
import com.yuxin.wx.model.company.CompanyIndexModelCustom;

/**
 * Service Implementation:CompanyIndexModelCustom
 * @author chopin
 * @date 2015-5-18
 */
@Service
@Transactional
public class CompanyIndexModelCustomServiceImpl extends BaseServiceImpl implements ICompanyIndexModelCustomService {

	@Autowired
	private CompanyIndexModelCustomMapper companyIndexModelCustomMapper;
	
	/**
	 * 
	* @Title: saveCompanyIndexModelCustom
	* @Description: 新增CompanyIndexModelCustom
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	@Override
	public void insert(CompanyIndexModelCustom entity){
		companyIndexModelCustomMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCompanyIndexModelCustom 
	* @Description: 批量新增CompanyIndexModelCustom
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CompanyIndexModelCustom> entity){
		companyIndexModelCustomMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateCompanyIndexModelCustom 
	* @Description: 编辑CompanyIndexModelCustom
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	@Override
	public void update(CompanyIndexModelCustom entity){
		companyIndexModelCustomMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteCompanyIndexModelCustomById 
	* @Description: 根据id删除CompanyIndexModelCustom
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	 @Override
	public void deleteCompanyIndexModelCustomById(Integer id){
		companyIndexModelCustomMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCompanyIndexModelCustomByIds 
	* @Description: 根据id批量删除CompanyIndexModelCustom
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	@Override
	public void deleteCompanyIndexModelCustomByIds(Integer[] ids){
		companyIndexModelCustomMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCompanyIndexModelCustomById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	@Override
	public CompanyIndexModelCustom findCompanyIndexModelCustomById(Integer id){
		return companyIndexModelCustomMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCompanyIndexModelCustomByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyIndexModelCustom>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	@Override
	public List<CompanyIndexModelCustom> findCompanyIndexModelCustomByPage(CompanyIndexModelCustom search){
		return companyIndexModelCustomMapper.page(search);
	};
}
