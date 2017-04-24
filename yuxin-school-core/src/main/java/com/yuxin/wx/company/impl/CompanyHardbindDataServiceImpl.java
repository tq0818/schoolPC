package com.yuxin.wx.company.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.ICompanyHardbindDataService;
import com.yuxin.wx.company.mapper.CompanyHardbindDataMapper;
import com.yuxin.wx.model.company.CompanyHardbindData;


/**
 * Service Implementation:CompanyHardbindData
 * @author chopin
 * @date 2016-9-1
 */
@Service
@Transactional
public class CompanyHardbindDataServiceImpl extends BaseServiceImpl implements ICompanyHardbindDataService {

	@Autowired
	private CompanyHardbindDataMapper companyHardbindDataMapper;
	
	/**
	 * 
	* @Title: saveCompanyHardbindData
	* @Description: 新增CompanyHardbindData
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by chopin
	 */
	@Override
	public void insert(CompanyHardbindData entity){
		companyHardbindDataMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCompanyHardbindData 
	* @Description: 批量新增CompanyHardbindData
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CompanyHardbindData> entity){
		companyHardbindDataMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateCompanyHardbindData 
	* @Description: 编辑CompanyHardbindData
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by chopin
	 */
	@Override
	public void update(CompanyHardbindData entity){
		companyHardbindDataMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteCompanyHardbindDataById 
	* @Description: 根据id删除CompanyHardbindData
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by chopin
	 */
	 @Override
	public void deleteCompanyHardbindDataById(Integer id){
		companyHardbindDataMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCompanyHardbindDataByIds 
	* @Description: 根据id批量删除CompanyHardbindData
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by chopin
	 */
	@Override
	public void deleteCompanyHardbindDataByIds(Integer[] ids){
		companyHardbindDataMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCompanyHardbindDataById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by chopin
	 */
	@Override
	public CompanyHardbindData findCompanyHardbindDataById(Integer id){
		return companyHardbindDataMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCompanyHardbindDataByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyHardbindData>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by chopin
	 */
	@Override
	public List<CompanyHardbindData> findCompanyHardbindDataByPage(CompanyHardbindData search){
		return companyHardbindDataMapper.page(search);
	}

	@Override
	public Integer findCompanyHardbindDataCountByPage(CompanyHardbindData search) {
		// TODO Auto-generated method stub
		return companyHardbindDataMapper.pageCount(search);
	};
}
