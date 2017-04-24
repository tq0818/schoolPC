package com.yuxin.wx.company.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.ICompanyLiveRecordZsService;
import com.yuxin.wx.company.mapper.CompanyLiveRecordZsMapper;
import com.yuxin.wx.model.company.CompanyLiveRecordZs;


/**
 * Service Implementation:CompanyLiveRecordZs
 * @author wang.zx
 * @date 2015-12-7
 */
@Service
@Transactional
public class CompanyLiveRecordZsServiceImpl extends BaseServiceImpl implements ICompanyLiveRecordZsService {

	@Autowired
	private CompanyLiveRecordZsMapper companyLiveRecordZsMapper;
	
	/**
	 * 
	* @Title: saveCompanyLiveRecordZs
	* @Description: 新增CompanyLiveRecordZs
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-7
	* @user by chopin
	 */
	@Override
	public void insert(CompanyLiveRecordZs entity){
		companyLiveRecordZsMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCompanyLiveRecordZs 
	* @Description: 批量新增CompanyLiveRecordZs
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-7
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CompanyLiveRecordZs> T){
		companyLiveRecordZsMapper.batchInsert(T);
	};
	
	/**
	 * 
	* @Title: updateCompanyLiveRecordZs 
	* @Description: 编辑CompanyLiveRecordZs
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-7
	* @user by chopin
	 */
	@Override
	public void update(CompanyLiveRecordZs T){
		companyLiveRecordZsMapper.update(T);
	};
	
	/**
	 * 
	* @Title: deleteCompanyLiveRecordZsById 
	* @Description: 根据id删除CompanyLiveRecordZs
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-7
	* @user by chopin
	 */
	 @Override
	public void deleteCompanyLiveRecordZsById(Integer id){
		companyLiveRecordZsMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCompanyLiveRecordZsByIds 
	* @Description: 根据id批量删除CompanyLiveRecordZs
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-7
	* @user by chopin
	 */
	@Override
	public void deleteCompanyLiveRecordZsByIds(Integer[] ids){
		companyLiveRecordZsMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCompanyLiveRecordZsById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-7
	* @user by chopin
	 */
	@Override
	public CompanyLiveRecordZs findCompanyLiveRecordZsById(Integer id){
		return companyLiveRecordZsMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCompanyLiveRecordZsByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyLiveRecordZs>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-7
	* @user by chopin
	 */
	@Override
	public List<CompanyLiveRecordZs> findCompanyLiveRecordZsByPage(CompanyLiveRecordZs search){
		return companyLiveRecordZsMapper.page(search);
	}

	@Override
	public List<CompanyLiveRecordZs> findRecord(CompanyLiveRecordZs lrzs) {
		// TODO Auto-generated method stub
		return companyLiveRecordZsMapper.findRecord(lrzs);
	}

	@Override
	public Integer findCountRecord(CompanyLiveRecordZs lrzs) {
		// TODO Auto-generated method stub
		return companyLiveRecordZsMapper.findCountRecord(lrzs);
	}

	@Override
	public String findNameByRecordId(String recordId) {
		// TODO Auto-generated method stub
		return companyLiveRecordZsMapper.findNameByRecordId(recordId);
	}

}
