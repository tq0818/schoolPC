package com.yuxin.wx.company.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.ICompanyMessageHistoryService;
import com.yuxin.wx.company.mapper.CompanyMessageHistoryMapper;
import com.yuxin.wx.model.company.CompanyMessageHistory;

/**
 * Service Implementation:CompanyMessageHistory
 * @author chopin
 * @date 2015-4-23
 */
@Service
@Transactional
public class CompanyMessageHistoryServiceImpl extends BaseServiceImpl implements ICompanyMessageHistoryService {

	@Autowired
	private CompanyMessageHistoryMapper companyMessageHistoryMapper;
	
	/**
	 * 
	* @Title: saveCompanyMessageHistory
	* @Description: 新增CompanyMessageHistory
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by chopin
	 */
	@Override
	public void insert(CompanyMessageHistory entity){
		companyMessageHistoryMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCompanyMessageHistory 
	* @Description: 批量新增CompanyMessageHistory
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CompanyMessageHistory> entity){
		companyMessageHistoryMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateCompanyMessageHistory 
	* @Description: 编辑CompanyMessageHistory
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by chopin
	 */
	@Override
	public void update(CompanyMessageHistory entity){
		companyMessageHistoryMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteCompanyMessageHistoryById 
	* @Description: 根据id删除CompanyMessageHistory
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by chopin
	 */
	 @Override
	public void deleteCompanyMessageHistoryById(Integer id){
		companyMessageHistoryMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCompanyMessageHistoryByIds 
	* @Description: 根据id批量删除CompanyMessageHistory
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by chopin
	 */
	@Override
	public void deleteCompanyMessageHistoryByIds(Integer[] ids){
		companyMessageHistoryMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCompanyMessageHistoryById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by chopin
	 */
	@Override
	public CompanyMessageHistory findCompanyMessageHistoryById(Integer id){
		return companyMessageHistoryMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCompanyMessageHistoryByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyMessageHistory>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by chopin
	 */
	@Override
	public List<CompanyMessageHistory> findCompanyMessageHistoryByPage(CompanyMessageHistory search){
		return companyMessageHistoryMapper.page(search);
	}

	@Override
	public Integer findMessageByDateAndCompanyId(
			CompanyMessageHistory cmh) {
		// TODO Auto-generated method stub
		return companyMessageHistoryMapper.findMessageByDateAndCompanyId(cmh);
	}

	@Override
	public String findResult(Integer id) {
		// TODO Auto-generated method stub
		return companyMessageHistoryMapper.findResult(id);
	}

	@Override
	public Integer findByUserCount(CompanyMessageHistory companyMessageHistory) {
		// TODO Auto-generated method stub
		return companyMessageHistoryMapper.findByUserCount(companyMessageHistory);
	};
}
