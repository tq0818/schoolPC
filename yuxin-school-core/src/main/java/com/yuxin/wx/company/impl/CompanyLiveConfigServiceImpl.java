package com.yuxin.wx.company.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.ICompanyLiveConfigService;
import com.yuxin.wx.company.mapper.CompanyLiveConfigMapper;
import com.yuxin.wx.model.company.CompanyLiveConfig;

/**
 * Service Implementation:CompanyLiveConfig
 * @author wang.zx
 * @date 2016-2-29
 */
@Service
@Transactional
public class CompanyLiveConfigServiceImpl extends BaseServiceImpl implements ICompanyLiveConfigService {

	@Autowired
	private CompanyLiveConfigMapper companyLiveConfigMapper;
	
	/**
	 * 
	* @Title: saveCompanyLiveConfig
	* @Description: 新增CompanyLiveConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by chopin
	 */
	@Override
	public void insert(CompanyLiveConfig entity){
		companyLiveConfigMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCompanyLiveConfig 
	* @Description: 批量新增CompanyLiveConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CompanyLiveConfig> T){
		companyLiveConfigMapper.batchInsert(T);
	};
	
	/**
	 * 
	* @Title: updateCompanyLiveConfig 
	* @Description: 编辑CompanyLiveConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by chopin
	 */
	@Override
	public void update(CompanyLiveConfig T){
		companyLiveConfigMapper.update(T);
	};
	
	/**
	 * 
	* @Title: deleteCompanyLiveConfigById 
	* @Description: 根据id删除CompanyLiveConfig
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by chopin
	 */
	 @Override
	public void deleteCompanyLiveConfigById(Integer id){
		companyLiveConfigMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCompanyLiveConfigByIds 
	* @Description: 根据id批量删除CompanyLiveConfig
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by chopin
	 */
	@Override
	public void deleteCompanyLiveConfigByIds(Integer[] ids){
		companyLiveConfigMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCompanyLiveConfigById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by chopin
	 */
	@Override
	public CompanyLiveConfig findCompanyLiveConfigById(Integer id){
		return companyLiveConfigMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCompanyLiveConfigByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyLiveConfig>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by chopin
	 */
	@Override
	public List<CompanyLiveConfig> findCompanyLiveConfigByPage(CompanyLiveConfig search){
		return companyLiveConfigMapper.page(search);
	}

	@Override
	public CompanyLiveConfig findByCompanyId(Integer companyId) {
		// TODO Auto-generated method stub
		return companyLiveConfigMapper.findByCompanyId(companyId);
	};
}
