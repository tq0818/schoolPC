package com.yuxin.wx.company.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.ICompanyLiveCoursewareZsService;
import com.yuxin.wx.company.mapper.CompanyLiveCoursewareZsMapper;
import com.yuxin.wx.model.company.CompanyLiveCoursewareZs;


/**
 * Service Implementation:CompanyLiveCoursewareZs
 * @author wang.zx
 * @date 2015-11-28
 */
@Service
@Transactional
public class CompanyLiveCoursewareZsServiceImpl extends BaseServiceImpl implements ICompanyLiveCoursewareZsService {

	@Autowired
	private CompanyLiveCoursewareZsMapper companyLiveCoursewareZsMapper;
	
	/**
	 * 
	* @Title: saveCompanyLiveCoursewareZs
	* @Description: 新增CompanyLiveCoursewareZs
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-11-28
	* @user by chopin
	 */
	@Override
	public void insert(CompanyLiveCoursewareZs entity){
		companyLiveCoursewareZsMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCompanyLiveCoursewareZs 
	* @Description: 批量新增CompanyLiveCoursewareZs
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-11-28
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CompanyLiveCoursewareZs> T){
		companyLiveCoursewareZsMapper.batchInsert(T);
	};
	
	/**
	 * 
	* @Title: updateCompanyLiveCoursewareZs 
	* @Description: 编辑CompanyLiveCoursewareZs
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-11-28
	* @user by chopin
	 */
	@Override
	public void update(CompanyLiveCoursewareZs T){
		companyLiveCoursewareZsMapper.update(T);
	};
	
	/**
	 * 
	* @Title: deleteCompanyLiveCoursewareZsById 
	* @Description: 根据id删除CompanyLiveCoursewareZs
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-11-28
	* @user by chopin
	 */
	 @Override
	public void deleteCompanyLiveCoursewareZsById(Integer id){
		companyLiveCoursewareZsMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCompanyLiveCoursewareZsByIds 
	* @Description: 根据id批量删除CompanyLiveCoursewareZs
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-11-28
	* @user by chopin
	 */
	@Override
	public void deleteCompanyLiveCoursewareZsByIds(Integer[] ids){
		companyLiveCoursewareZsMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCompanyLiveCoursewareZsById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-11-28
	* @user by chopin
	 */
	@Override
	public CompanyLiveCoursewareZs findCompanyLiveCoursewareZsById(Integer id){
		return companyLiveCoursewareZsMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCompanyLiveCoursewareZsByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyLiveCoursewareZs>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-11-28
	* @user by chopin
	 */
	@Override
	public List<CompanyLiveCoursewareZs> findCompanyLiveCoursewareZsByPage(CompanyLiveCoursewareZs search){
		return companyLiveCoursewareZsMapper.page(search);
	}

	@Override
	public List<CompanyLiveCoursewareZs> findCourse(CompanyLiveCoursewareZs zs) {
		// TODO Auto-generated method stub
		return companyLiveCoursewareZsMapper.findCourse(zs);
	}

	@Override
	public Integer findCountCourse(CompanyLiveCoursewareZs zs) {
		// TODO Auto-generated method stub
		return companyLiveCoursewareZsMapper.findCountCourse(zs);
	};
}
