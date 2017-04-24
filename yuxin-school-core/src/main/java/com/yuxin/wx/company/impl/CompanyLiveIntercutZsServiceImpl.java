package com.yuxin.wx.company.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.ICompanyLiveIntercutZsService;
import com.yuxin.wx.company.mapper.CompanyLiveIntercutZsMapper;
import com.yuxin.wx.model.company.CompanyLiveIntercutZs;


/**
 * Service Implementation:CompanyLiveIntercutZs
 * @author wang.zx
 * @date 2015-12-11
 */
@Service
@Transactional
public class CompanyLiveIntercutZsServiceImpl extends BaseServiceImpl implements ICompanyLiveIntercutZsService {

	@Autowired
	private CompanyLiveIntercutZsMapper companyLiveIntercutZsMapper;
	
	/**
	 * 
	* @Title: saveCompanyLiveIntercutZs
	* @Description: 新增CompanyLiveIntercutZs
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-11
	* @user by chopin
	 */
	@Override
	public void insert(CompanyLiveIntercutZs entity){
		companyLiveIntercutZsMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCompanyLiveIntercutZs 
	* @Description: 批量新增CompanyLiveIntercutZs
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-11
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CompanyLiveIntercutZs> T){
		companyLiveIntercutZsMapper.batchInsert(T);
	};
	
	/**
	 * 
	* @Title: updateCompanyLiveIntercutZs 
	* @Description: 编辑CompanyLiveIntercutZs
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-11
	* @user by chopin
	 */
	@Override
	public void update(CompanyLiveIntercutZs T){
		companyLiveIntercutZsMapper.update(T);
	};
	
	/**
	 * 
	* @Title: deleteCompanyLiveIntercutZsById 
	* @Description: 根据id删除CompanyLiveIntercutZs
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-11
	* @user by chopin
	 */
	 @Override
	public void deleteCompanyLiveIntercutZsById(Integer id){
		companyLiveIntercutZsMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCompanyLiveIntercutZsByIds 
	* @Description: 根据id批量删除CompanyLiveIntercutZs
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-11
	* @user by chopin
	 */
	@Override
	public void deleteCompanyLiveIntercutZsByIds(Integer[] ids){
		companyLiveIntercutZsMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCompanyLiveIntercutZsById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-11
	* @user by chopin
	 */
	@Override
	public CompanyLiveIntercutZs findCompanyLiveIntercutZsById(Integer id){
		return companyLiveIntercutZsMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCompanyLiveIntercutZsByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyLiveIntercutZs>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-11
	* @user by chopin
	 */
	@Override
	public List<CompanyLiveIntercutZs> findCompanyLiveIntercutZsByPage(CompanyLiveIntercutZs search){
		return companyLiveIntercutZsMapper.page(search);
	}

	@Override
	public List<CompanyLiveIntercutZs> findAllByCont(
			CompanyLiveIntercutZs search) {
		// TODO Auto-generated method stub
		return companyLiveIntercutZsMapper.findAllByCont(search);
	}

	@Override
	public Integer findCountAllByCont(CompanyLiveIntercutZs search) {
		// TODO Auto-generated method stub
		return companyLiveIntercutZsMapper.findCountAllByCont(search);
	}

	@Override
	public void delByCont(CompanyLiveIntercutZs search) {
		// TODO Auto-generated method stub
		companyLiveIntercutZsMapper.delByCont(search);
	}

	@Override
	public List<CompanyLiveIntercutZs> findExist(CompanyLiveIntercutZs search) {
		// TODO Auto-generated method stub
		return companyLiveIntercutZsMapper.findExist(search);
	};
}
