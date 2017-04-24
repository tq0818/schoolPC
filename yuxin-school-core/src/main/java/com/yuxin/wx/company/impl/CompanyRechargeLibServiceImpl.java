package com.yuxin.wx.company.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.ICompanyRechargeLibService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.company.mapper.CompanyRechargeLibMapper;
import com.yuxin.wx.model.company.CompanyRechargeLib;
import com.yuxin.wx.vo.company.CompanyRechargeLibVo;


/**
 * Service Implementation:CompanyRechargeLib
 * @author chopin
 * @date 2017-4-10
 */
@Service
@Transactional
public class CompanyRechargeLibServiceImpl extends BaseServiceImpl implements ICompanyRechargeLibService {

	@Autowired
	private CompanyRechargeLibMapper companyRechargeLibMapper;
	
	/**
	 * 
	* @Title: saveCompanyRechargeLib
	* @Description: 新增CompanyRechargeLib
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-4-10
	* @user by chopin
	 */
	@Override
	public void insert(CompanyRechargeLib entity){
		companyRechargeLibMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCompanyRechargeLib 
	* @Description: 批量新增CompanyRechargeLib
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-4-10
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CompanyRechargeLib> entity){
		companyRechargeLibMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateCompanyRechargeLib 
	* @Description: 编辑CompanyRechargeLib
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-4-10
	* @user by chopin
	 */
	@Override
	public void update(CompanyRechargeLib entity){
		companyRechargeLibMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteCompanyRechargeLibById 
	* @Description: 根据id删除CompanyRechargeLib
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-4-10
	* @user by chopin
	 */
	 @Override
	public void deleteCompanyRechargeLibById(Integer id){
		companyRechargeLibMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCompanyRechargeLibByIds 
	* @Description: 根据id批量删除CompanyRechargeLib
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-4-10
	* @user by chopin
	 */
	@Override
	public void deleteCompanyRechargeLibByIds(Integer[] ids){
		companyRechargeLibMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCompanyRechargeLibById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-4-10
	* @user by chopin
	 */
	@Override
	public CompanyRechargeLib findCompanyRechargeLibById(Integer id){
		return companyRechargeLibMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCompanyRechargeLibByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyRechargeLib>    返回类型 
	* @throws 
	* @exception 
	* @date 2017-4-10
	* @user by chopin
	 */
	@Override
	public List<CompanyRechargeLib> findCompanyRechargeLibByPage(CompanyRechargeLib search){
		return companyRechargeLibMapper.page(search);
	}

	@Override
	public PageFinder<CompanyRechargeLibVo> queryByPatchId(CompanyRechargeLibVo search) {
		List<CompanyRechargeLibVo> data = companyRechargeLibMapper.queryByPatchId(search);
		int count = companyRechargeLibMapper.queryCountByPatchId(search);
		PageFinder<CompanyRechargeLibVo> pageFinder = new PageFinder<CompanyRechargeLibVo>(search.getPage(), search.getPageSize(), count, data);
		return pageFinder;
	}

	@Override
	public List<CompanyRechargeLibVo> queryListByPatchId(CompanyRechargeLibVo search) {
		return companyRechargeLibMapper.queryListByPatchId(search);
	};
}
