package com.yuxin.wx.company.impl;

import java.util.List;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.ICompanyInvitConfigService;
import com.yuxin.wx.company.mapper.CompanyInvitConfigMapper;
import com.yuxin.wx.model.company.CompanyInvitConfig;


/**
 * Service Implementation:CompanyInvitConfig
 * @author chopin
 * @date 2016-7-29
 */
@Service
@Transactional
public class CompanyInvitConfigServiceImpl extends BaseServiceImpl implements ICompanyInvitConfigService {

	@Autowired
	private CompanyInvitConfigMapper companyInvitConfigMapper;
	
	/**
	 * 
	* @Title: saveCompanyInvitConfig
	* @Description: 新增CompanyInvitConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-29
	* @user by chopin
	 */
	@Override
	public void insert(CompanyInvitConfig entity){
		companyInvitConfigMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCompanyInvitConfig 
	* @Description: 批量新增CompanyInvitConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-29
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CompanyInvitConfig> entity){
		companyInvitConfigMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateCompanyInvitConfig 
	* @Description: 编辑CompanyInvitConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-29
	* @user by chopin
	 */
	@Override
	public void update(CompanyInvitConfig entity){
		companyInvitConfigMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteCompanyInvitConfigById 
	* @Description: 根据id删除CompanyInvitConfig
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-29
	* @user by chopin
	 */
	 @Override
	public void deleteCompanyInvitConfigById(Integer id){
		companyInvitConfigMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCompanyInvitConfigByIds 
	* @Description: 根据id批量删除CompanyInvitConfig
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-29
	* @user by chopin
	 */
	@Override
	public void deleteCompanyInvitConfigByIds(Integer[] ids){
		companyInvitConfigMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCompanyInvitConfigById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-29
	* @user by chopin
	 */
	@Override
	public CompanyInvitConfig findCompanyInvitConfigById(Integer id){
		return companyInvitConfigMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCompanyInvitConfigByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyInvitConfig>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-29
	* @user by chopin
	 */
	@Override
	public List<CompanyInvitConfig> findCompanyInvitConfigByPage(CompanyInvitConfig search){
		return companyInvitConfigMapper.page(search);
	}

	@Override
	public CompanyInvitConfig findInvitConfigByCompanyId(Integer companyId) {
		// TODO Auto-generated method stub
		return companyInvitConfigMapper.findInvitConfigByCompanyId(companyId);
	}

}
