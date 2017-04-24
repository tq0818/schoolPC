package com.yuxin.wx.company.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.ICompanyCouponsConfigService;
import com.yuxin.wx.company.mapper.CompanyCouponsConfigMapper;
import com.yuxin.wx.model.company.CompanyCouponsConfig;


/**
 * Service Implementation:CompanyCouponsConfig
 * @author chopin
 * @date 2016-6-20
 */
@Service
@Transactional
public class CompanyCouponsConfigServiceImpl extends BaseServiceImpl implements ICompanyCouponsConfigService {

	@Autowired
	private CompanyCouponsConfigMapper companyCouponsConfigMapper;
	
	/**
	 * 
	* @Title: saveCompanyCouponsConfig
	* @Description: 新增CompanyCouponsConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-20
	* @user by chopin
	 */
	@Override
	public void insert(CompanyCouponsConfig entity){
		companyCouponsConfigMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCompanyCouponsConfig 
	* @Description: 批量新增CompanyCouponsConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-20
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CompanyCouponsConfig> entity){
		companyCouponsConfigMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateCompanyCouponsConfig 
	* @Description: 编辑CompanyCouponsConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-20
	* @user by chopin
	 */
	@Override
	public void update(CompanyCouponsConfig entity){
		companyCouponsConfigMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteCompanyCouponsConfigById 
	* @Description: 根据id删除CompanyCouponsConfig
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-20
	* @user by chopin
	 */
	 @Override
	public void deleteCompanyCouponsConfigById(Integer id){
		companyCouponsConfigMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCompanyCouponsConfigByIds 
	* @Description: 根据id批量删除CompanyCouponsConfig
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-20
	* @user by chopin
	 */
	@Override
	public void deleteCompanyCouponsConfigByIds(Integer[] ids){
		companyCouponsConfigMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCompanyCouponsConfigById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-20
	* @user by chopin
	 */
	@Override
	public CompanyCouponsConfig findCompanyCouponsConfigById(Integer id){
		return companyCouponsConfigMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCompanyCouponsConfigByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyCouponsConfig>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-20
	* @user by chopin
	 */
	@Override
	public List<CompanyCouponsConfig> findCompanyCouponsConfigByPage(CompanyCouponsConfig search){
		return companyCouponsConfigMapper.page(search);
	}
	
	
	@Override
	public CompanyCouponsConfig findByCompanyId(CompanyCouponsConfig search) {
		return companyCouponsConfigMapper.findByCompanyId(search);
	};
}
