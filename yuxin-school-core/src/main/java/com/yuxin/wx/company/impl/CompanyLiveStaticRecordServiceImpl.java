package com.yuxin.wx.company.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.ICompanyLiveStaticRecordService;
import com.yuxin.wx.company.mapper.CompanyLiveStaticRecordMapper;
import com.yuxin.wx.model.company.CompanyLiveStaticRecord;

/**
 * Service Implementation:CompanyLiveStaticRecord
 * @author wang.zx
 * @date 2017-1-4
 */
@Service
@Transactional
public class CompanyLiveStaticRecordServiceImpl extends BaseServiceImpl implements ICompanyLiveStaticRecordService {

	@Autowired
	private CompanyLiveStaticRecordMapper companyLiveStaticRecordMapper;
	
	/**
	 * 
	* @Title: saveCompanyLiveStaticRecord
	* @Description: 新增CompanyLiveStaticRecord
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-1-4
	* @user by chopin
	 */
	@Override
	public void insert(CompanyLiveStaticRecord entity){
		companyLiveStaticRecordMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCompanyLiveStaticRecord 
	* @Description: 批量新增CompanyLiveStaticRecord
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-1-4
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CompanyLiveStaticRecord> T){
		companyLiveStaticRecordMapper.batchInsert(T);
	};
	
	/**
	 * 
	* @Title: updateCompanyLiveStaticRecord 
	* @Description: 编辑CompanyLiveStaticRecord
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-1-4
	* @user by chopin
	 */
	@Override
	public void update(CompanyLiveStaticRecord T){
		companyLiveStaticRecordMapper.update(T);
	};
	
	/**
	 * 
	* @Title: deleteCompanyLiveStaticRecordById 
	* @Description: 根据id删除CompanyLiveStaticRecord
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-1-4
	* @user by chopin
	 */
	 @Override
	public void deleteCompanyLiveStaticRecordById(Integer id){
		companyLiveStaticRecordMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCompanyLiveStaticRecordByIds 
	* @Description: 根据id批量删除CompanyLiveStaticRecord
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-1-4
	* @user by chopin
	 */
	@Override
	public void deleteCompanyLiveStaticRecordByIds(Integer[] ids){
		companyLiveStaticRecordMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCompanyLiveStaticRecordById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-1-4
	* @user by chopin
	 */
	@Override
	public CompanyLiveStaticRecord findCompanyLiveStaticRecordById(Integer id){
		return companyLiveStaticRecordMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCompanyLiveStaticRecordByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyLiveStaticRecord>    返回类型 
	* @throws 
	* @exception 
	* @date 2017-1-4
	* @user by chopin
	 */
	@Override
	public List<CompanyLiveStaticRecord> findCompanyLiveStaticRecordByPage(CompanyLiveStaticRecord search){
		return companyLiveStaticRecordMapper.page(search);
	};
}
