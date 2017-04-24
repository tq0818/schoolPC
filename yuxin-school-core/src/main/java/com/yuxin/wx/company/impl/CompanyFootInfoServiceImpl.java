package com.yuxin.wx.company.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.ICompanyFootInfoService;
import com.yuxin.wx.company.mapper.CompanyFootInfoMapper;
import com.yuxin.wx.model.company.CompanyFootInfo;


/**
 * Service Implementation:CompanyFootInfo
 * @author chopin
 * @date 2016-2-29
 */
@Service
@Transactional
public class CompanyFootInfoServiceImpl extends BaseServiceImpl implements ICompanyFootInfoService {

	@Autowired
	private CompanyFootInfoMapper companyFootInfoMapper;
	
	/**
	 * 
	* @Title: saveCompanyFootInfo
	* @Description: 新增CompanyFootInfo
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by chopin
	 */
	@Override
	public void insert(CompanyFootInfo entity){
		companyFootInfoMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCompanyFootInfo 
	* @Description: 批量新增CompanyFootInfo
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CompanyFootInfo> entity){
		companyFootInfoMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateCompanyFootInfo 
	* @Description: 编辑CompanyFootInfo
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by chopin
	 */
	@Override
	public void update(CompanyFootInfo entity){
		companyFootInfoMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteCompanyFootInfoById 
	* @Description: 根据id删除CompanyFootInfo
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by chopin
	 */
	 @Override
	public void deleteCompanyFootInfoById(Integer id){
		companyFootInfoMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCompanyFootInfoByIds 
	* @Description: 根据id批量删除CompanyFootInfo
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by chopin
	 */
	@Override
	public void deleteCompanyFootInfoByIds(Integer[] ids){
		companyFootInfoMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCompanyFootInfoById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by chopin
	 */
	@Override
	public CompanyFootInfo findCompanyFootInfoById(Integer id){
		return companyFootInfoMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCompanyFootInfoByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyFootInfo>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-29
	* @user by chopin
	 */
	@Override
	public List<CompanyFootInfo> findCompanyFootInfoByPage(CompanyFootInfo search){
		return companyFootInfoMapper.page(search);
	}

	@Override
	public CompanyFootInfo findByCompanyId(Integer companyId) {
		return companyFootInfoMapper.findByCompanyId(companyId);
	}

	
	/**
	 * wz
	 * 根据公司id修改页尾信息
	 * @param entity
	 */
	@Override
	public void updateByCompanyId(CompanyFootInfo entity) {
		companyFootInfoMapper.updateByCompanyId(entity);
	};
}
