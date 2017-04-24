package com.yuxin.wx.company.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.ICompanyLiveConcurrentService;
import com.yuxin.wx.company.mapper.CompanyLiveConcurrentMapper;
import com.yuxin.wx.model.company.CompanyLiveConcurrent;

/**
 * Service Implementation:CompanyLiveConcurrent
 * @author wang.zx
 * @date 2016-3-28
 */
@Service
@Transactional
public class CompanyLiveConcurrentServiceImpl extends BaseServiceImpl implements ICompanyLiveConcurrentService {

	@Autowired
	private CompanyLiveConcurrentMapper companyLiveConcurrentMapper;
	
	/**
	 * 
	* @Title: saveCompanyLiveConcurrent
	* @Description: 新增CompanyLiveConcurrent
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-28
	* @user by chopin
	 */
	@Override
	public void insert(CompanyLiveConcurrent entity){
		companyLiveConcurrentMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCompanyLiveConcurrent 
	* @Description: 批量新增CompanyLiveConcurrent
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-28
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CompanyLiveConcurrent> T){
		companyLiveConcurrentMapper.batchInsert(T);
	};
	
	/**
	 * 
	* @Title: updateCompanyLiveConcurrent 
	* @Description: 编辑CompanyLiveConcurrent
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-28
	* @user by chopin
	 */
	@Override
	public void update(CompanyLiveConcurrent T){
		companyLiveConcurrentMapper.update(T);
	};
	
	/**
	 * 
	* @Title: deleteCompanyLiveConcurrentById 
	* @Description: 根据id删除CompanyLiveConcurrent
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-28
	* @user by chopin
	 */
	 @Override
	public void deleteCompanyLiveConcurrentById(Integer id){
		companyLiveConcurrentMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCompanyLiveConcurrentByIds 
	* @Description: 根据id批量删除CompanyLiveConcurrent
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-28
	* @user by chopin
	 */
	@Override
	public void deleteCompanyLiveConcurrentByIds(Integer[] ids){
		companyLiveConcurrentMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCompanyLiveConcurrentById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-28
	* @user by chopin
	 */
	@Override
	public CompanyLiveConcurrent findCompanyLiveConcurrentById(Integer id){
		return companyLiveConcurrentMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCompanyLiveConcurrentByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyLiveConcurrent>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-28
	* @user by chopin
	 */
	@Override
	public List<CompanyLiveConcurrent> findCompanyLiveConcurrentByPage(CompanyLiveConcurrent search){
		return companyLiveConcurrentMapper.page(search);
	}

	@Override
	public CompanyLiveConcurrent findLiveByComidAndDate(
			Map<String, Object> param) {
		// TODO Auto-generated method stub
		return companyLiveConcurrentMapper.findLiveByComidAndDate(param);
	}

	@Override
	public List<CompanyLiveConcurrent> findMoreByComidAndDate(
			Map<String, Object> param) {
		// TODO Auto-generated method stub
		return companyLiveConcurrentMapper.findMoreByComidAndDate(param);
	}

	@Override
	public void updatelive(Map<String, Object> param) {
		// TODO Auto-generated method stub
		companyLiveConcurrentMapper.updatelive(param);
	}
}
