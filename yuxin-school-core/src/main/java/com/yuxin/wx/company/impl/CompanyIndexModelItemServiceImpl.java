package com.yuxin.wx.company.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.ICompanyIndexModelItemService;
import com.yuxin.wx.company.mapper.CompanyIndexModelItemMapper;
import com.yuxin.wx.model.company.CompanyIndexModelItem;

/**
 * Service Implementation:CompanyIndexModelItem
 * @author chopin
 * @date 2015-5-18
 */
@Service
@Transactional
public class CompanyIndexModelItemServiceImpl extends BaseServiceImpl implements ICompanyIndexModelItemService {

	@Autowired
	private CompanyIndexModelItemMapper companyIndexModelItemMapper;
	
	/**
	 * 
	* @Title: saveCompanyIndexModelItem
	* @Description: 新增CompanyIndexModelItem
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	@Override
	public void insert(CompanyIndexModelItem entity){
		companyIndexModelItemMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCompanyIndexModelItem 
	* @Description: 批量新增CompanyIndexModelItem
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CompanyIndexModelItem> entity){
		companyIndexModelItemMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateCompanyIndexModelItem 
	* @Description: 编辑CompanyIndexModelItem
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	@Override
	public void update(CompanyIndexModelItem entity){
		companyIndexModelItemMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteCompanyIndexModelItemById 
	* @Description: 根据id删除CompanyIndexModelItem
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	 @Override
	public void deleteCompanyIndexModelItemById(Integer id){
		companyIndexModelItemMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCompanyIndexModelItemByIds 
	* @Description: 根据id批量删除CompanyIndexModelItem
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	@Override
	public void deleteCompanyIndexModelItemByIds(Integer[] ids){
		companyIndexModelItemMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCompanyIndexModelItemById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	@Override
	public CompanyIndexModelItem findCompanyIndexModelItemById(Integer id){
		return companyIndexModelItemMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCompanyIndexModelItemByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyIndexModelItem>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	@Override
	public List<CompanyIndexModelItem> findCompanyIndexModelItemByPage(CompanyIndexModelItem search){
		return companyIndexModelItemMapper.page(search);
	};
	/**
	 * 
	* @Title: findByConfigId 
	* @Description: 分页查询
	* @return
	* @return List<CompanyIndexModelItem>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	@Override
	public CompanyIndexModelItem findByConfigId(Integer configId){
		return companyIndexModelItemMapper.findById(configId);
	}
}
