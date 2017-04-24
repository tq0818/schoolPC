package com.yuxin.wx.company.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.ICompanyEmailHistoryService;
import com.yuxin.wx.company.mapper.CompanyEmailHistoryMapper;
import com.yuxin.wx.model.company.CompanyEmailHistory;

/**
 * Service Implementation:CompanyEmailHistory
 * @author chopin
 * @date 2015-4-23
 */
@Service
@Transactional
public class CompanyEmailHistoryServiceImpl extends BaseServiceImpl implements ICompanyEmailHistoryService {

	@Autowired
	private CompanyEmailHistoryMapper companyEmailHistoryMapper;
	
	/**
	 * 
	* @Title: saveCompanyEmailHistory
	* @Description: 新增CompanyEmailHistory
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by chopin
	 */
	@Override
	public void insert(CompanyEmailHistory entity){
		companyEmailHistoryMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCompanyEmailHistory 
	* @Description: 批量新增CompanyEmailHistory
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CompanyEmailHistory> entity){
		companyEmailHistoryMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateCompanyEmailHistory 
	* @Description: 编辑CompanyEmailHistory
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by chopin
	 */
	@Override
	public void update(CompanyEmailHistory entity){
		companyEmailHistoryMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteCompanyEmailHistoryById 
	* @Description: 根据id删除CompanyEmailHistory
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by chopin
	 */
	 @Override
	public void deleteCompanyEmailHistoryById(Integer id){
		companyEmailHistoryMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCompanyEmailHistoryByIds 
	* @Description: 根据id批量删除CompanyEmailHistory
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by chopin
	 */
	@Override
	public void deleteCompanyEmailHistoryByIds(Integer[] ids){
		companyEmailHistoryMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCompanyEmailHistoryById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by chopin
	 */
	@Override
	public CompanyEmailHistory findCompanyEmailHistoryById(Integer id){
		return companyEmailHistoryMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCompanyEmailHistoryByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyEmailHistory>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by chopin
	 */
	@Override
	public List<CompanyEmailHistory> findCompanyEmailHistoryByPage(CompanyEmailHistory search){
		return companyEmailHistoryMapper.page(search);
	}

	@Override
	public Integer findEmailCount(CompanyEmailHistory email) {
		// TODO Auto-generated method stub
		return companyEmailHistoryMapper.findEmailCount(email);
	}

	@Override
	public Integer findByUserCount(CompanyEmailHistory cmh) {
		// TODO Auto-generated method stub
		return companyEmailHistoryMapper.findByUserCount(cmh);
	};
}
