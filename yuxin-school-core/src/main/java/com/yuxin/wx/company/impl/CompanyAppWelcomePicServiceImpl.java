package com.yuxin.wx.company.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.ICompanyAppWelcomePicService;
import com.yuxin.wx.company.mapper.CompanyAppWelcomePicMapper;
import com.yuxin.wx.model.company.CompanyAppWelcomePic;


/**
 * Service Implementation:CompanyAppWelcomePic
 * @author chopin
 * @date 2016-5-27
 */
@Service
@Transactional
public class CompanyAppWelcomePicServiceImpl extends BaseServiceImpl implements ICompanyAppWelcomePicService {

	@Autowired
	private CompanyAppWelcomePicMapper companyAppWelcomePicMapper;
	
	/**
	 * 
	* @Title: saveCompanyAppWelcomePic
	* @Description: 新增CompanyAppWelcomePic
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-27
	* @user by chopin
	 */
	@Override
	public void insert(CompanyAppWelcomePic entity){
		companyAppWelcomePicMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCompanyAppWelcomePic 
	* @Description: 批量新增CompanyAppWelcomePic
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-27
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CompanyAppWelcomePic> entity){
		companyAppWelcomePicMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateCompanyAppWelcomePic 
	* @Description: 编辑CompanyAppWelcomePic
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-27
	* @user by chopin
	 */
	@Override
	public void update(CompanyAppWelcomePic entity){
		companyAppWelcomePicMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteCompanyAppWelcomePicById 
	* @Description: 根据id删除CompanyAppWelcomePic
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-27
	* @user by chopin
	 */
	 @Override
	public void deleteCompanyAppWelcomePicById(Integer id){
		companyAppWelcomePicMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCompanyAppWelcomePicByIds 
	* @Description: 根据id批量删除CompanyAppWelcomePic
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-27
	* @user by chopin
	 */
	@Override
	public void deleteCompanyAppWelcomePicByIds(Integer[] ids){
		companyAppWelcomePicMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCompanyAppWelcomePicById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-27
	* @user by chopin
	 */
	@Override
	public CompanyAppWelcomePic findCompanyAppWelcomePicById(Integer id){
		return companyAppWelcomePicMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCompanyAppWelcomePicByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyAppWelcomePic>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-27
	* @user by chopin
	 */
	@Override
	public List<CompanyAppWelcomePic> findCompanyAppWelcomePicByPage(CompanyAppWelcomePic search){
		return companyAppWelcomePicMapper.page(search);
	}

	@Override
	public CompanyAppWelcomePic findByparam(Integer companyId) {
		// TODO Auto-generated method stub
		return companyAppWelcomePicMapper.findByparam(companyId);
	};
}
