package com.yuxin.wx.company.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.ICompanyCashFlowService;
import com.yuxin.wx.company.mapper.CompanyCashFlowMapper;
import com.yuxin.wx.model.company.CompanyCashFlow;

/**
 * Service Implementation:CompanyCashFlow
 * @author chopin
 * @date 2016-5-17
 */
@Service
@Transactional
public class CompanyCashFlowServiceImpl extends BaseServiceImpl implements ICompanyCashFlowService {

	@Autowired
	private CompanyCashFlowMapper companyCashFlowMapper;
	
	/**
	 * 
	* @Title: saveCompanyCashFlow
	* @Description: 新增CompanyCashFlow
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by chopin
	 */
	@Override
	public void insert(CompanyCashFlow entity){
		companyCashFlowMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCompanyCashFlow 
	* @Description: 批量新增CompanyCashFlow
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CompanyCashFlow> entity){
		companyCashFlowMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateCompanyCashFlow 
	* @Description: 编辑CompanyCashFlow
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by chopin
	 */
	@Override
	public void update(CompanyCashFlow entity){
		companyCashFlowMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteCompanyCashFlowById 
	* @Description: 根据id删除CompanyCashFlow
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by chopin
	 */
	 @Override
	public void deleteCompanyCashFlowById(Integer id){
		companyCashFlowMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCompanyCashFlowByIds 
	* @Description: 根据id批量删除CompanyCashFlow
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by chopin
	 */
	@Override
	public void deleteCompanyCashFlowByIds(Integer[] ids){
		companyCashFlowMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCompanyCashFlowById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by chopin
	 */
	@Override
	public CompanyCashFlow findCompanyCashFlowById(Integer id){
		return companyCashFlowMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCompanyCashFlowByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyCashFlow>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by chopin
	 */
	@Override
	public List<CompanyCashFlow> findCompanyCashFlowByPage(CompanyCashFlow search){
		return companyCashFlowMapper.page(search);
	};
}
