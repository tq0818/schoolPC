package com.yuxin.wx.company.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.ICompanyServiceStaticDayService;
import com.yuxin.wx.company.mapper.CompanyServiceStaticDayMapper;
import com.yuxin.wx.model.company.CompanyEmailHistory;
import com.yuxin.wx.model.company.CompanyServiceStaticDay;

/**
 * Service Implementation:CompanyServiceStaticDay
 * @author wang.zx
 * @date 2015-5-21
 */
@Service
@Transactional
public class CompanyServiceStaticDayServiceImpl extends BaseServiceImpl implements ICompanyServiceStaticDayService {

	@Autowired
	private CompanyServiceStaticDayMapper companyServiceStaticDayMapper;
	
	/**
	 * 
	* @Title: saveCompanyServiceStaticDay
	* @Description: 新增CompanyServiceStaticDay
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-21
	* @user by chopin
	 */
	@Override
	public void insert(CompanyServiceStaticDay entity){
		companyServiceStaticDayMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCompanyServiceStaticDay 
	* @Description: 批量新增CompanyServiceStaticDay
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-21
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CompanyServiceStaticDay> entity){
		companyServiceStaticDayMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateCompanyServiceStaticDay 
	* @Description: 编辑CompanyServiceStaticDay
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-21
	* @user by chopin
	 */
	@Override
	public void update(CompanyServiceStaticDay entity){
		companyServiceStaticDayMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteCompanyServiceStaticDayById 
	* @Description: 根据id删除CompanyServiceStaticDay
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-21
	* @user by chopin
	 */
	 @Override
	public void deleteCompanyServiceStaticDayById(Integer id){
		companyServiceStaticDayMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCompanyServiceStaticDayByIds 
	* @Description: 根据id批量删除CompanyServiceStaticDay
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-21
	* @user by chopin
	 */
	@Override
	public void deleteCompanyServiceStaticDayByIds(Integer[] ids){
		companyServiceStaticDayMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCompanyServiceStaticDayById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-21
	* @user by chopin
	 */
	@Override
	public CompanyServiceStaticDay findCompanyServiceStaticDayById(Integer id){
		return companyServiceStaticDayMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCompanyServiceStaticDayByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyServiceStaticDay>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-21
	* @user by chopin
	 */
	@Override
	public List<CompanyServiceStaticDay> findCompanyServiceStaticDayByPage(CompanyServiceStaticDay search){
		return companyServiceStaticDayMapper.page(search);
	}

	@Override
	public List<CompanyServiceStaticDay> findInfoByCompanyId(Integer companyId) {
		// TODO Auto-generated method stub
		return companyServiceStaticDayMapper.findInfoByCompanyId(companyId);
	}

	@Override
	public CompanyServiceStaticDay findByDateAndCompanyId(
			CompanyServiceStaticDay cssd) {
		// TODO Auto-generated method stub
		return companyServiceStaticDayMapper.findByDateAndCompanyId(cssd);
	}

	@Override
	public List<CompanyServiceStaticDay> findInfoByDate(CompanyServiceStaticDay cssd) {
		// TODO Auto-generated method stub
		return companyServiceStaticDayMapper.findInfoByDate(cssd);
	}

	@Override
	public void updateByCompanyIdDate(CompanyServiceStaticDay cssd) {
		// TODO Auto-generated method stub
		companyServiceStaticDayMapper.updateByCompanyIdDate(cssd);
	}
}
