package com.yuxin.wx.company.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.ICompanyLiveStaticDayDetailService;
import com.yuxin.wx.company.mapper.CompanyLiveStaticDayDetailMapper;
import com.yuxin.wx.model.company.CompanyLiveStaticDayDetail;

/**
 * Service Implementation:CompanyLiveStaticDayDetail
 * @author wang.zx
 * @date 2015-6-8
 */
@Service
@Transactional
public class CompanyLiveStaticDayDetailServiceImpl extends BaseServiceImpl implements ICompanyLiveStaticDayDetailService {

	@Autowired
	private CompanyLiveStaticDayDetailMapper companyLiveStaticDayDetailMapper;
	
	/**
	 * 
	* @Title: saveCompanyLiveStaticDayDetail
	* @Description: 新增CompanyLiveStaticDayDetail
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-8
	* @user by chopin
	 */
	@Override
	public void insert(CompanyLiveStaticDayDetail entity){
		companyLiveStaticDayDetailMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCompanyLiveStaticDayDetail 
	* @Description: 批量新增CompanyLiveStaticDayDetail
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-8
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CompanyLiveStaticDayDetail> entity){
		companyLiveStaticDayDetailMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateCompanyLiveStaticDayDetail 
	* @Description: 编辑CompanyLiveStaticDayDetail
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-8
	* @user by chopin
	 */
	@Override
	public void update(CompanyLiveStaticDayDetail entity){
		companyLiveStaticDayDetailMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteCompanyLiveStaticDayDetailById 
	* @Description: 根据id删除CompanyLiveStaticDayDetail
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-8
	* @user by chopin
	 */
	 @Override
	public void deleteCompanyLiveStaticDayDetailById(Integer id){
		companyLiveStaticDayDetailMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCompanyLiveStaticDayDetailByIds 
	* @Description: 根据id批量删除CompanyLiveStaticDayDetail
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-8
	* @user by chopin
	 */
	@Override
	public void deleteCompanyLiveStaticDayDetailByIds(Integer[] ids){
		companyLiveStaticDayDetailMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCompanyLiveStaticDayDetailById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-8
	* @user by chopin
	 */
	@Override
	public CompanyLiveStaticDayDetail findCompanyLiveStaticDayDetailById(Integer id){
		return companyLiveStaticDayDetailMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCompanyLiveStaticDayDetailByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyLiveStaticDayDetail>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-8
	* @user by chopin
	 */
	@Override
	public List<CompanyLiveStaticDayDetail> findCompanyLiveStaticDayDetailByPage(CompanyLiveStaticDayDetail search){
		return companyLiveStaticDayDetailMapper.page(search);
	}

	@Override
	public Integer findByCompanyId(
			CompanyLiveStaticDayDetail companyLiveStaticDayDetail) {
		// TODO Auto-generated method stub
		return companyLiveStaticDayDetailMapper.findByCompanyId(companyLiveStaticDayDetail);
	}

	@Override
	public Integer findByDateAndCompanyId(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return companyLiveStaticDayDetailMapper.findByDateAndCompanyId(param);
	};
}
