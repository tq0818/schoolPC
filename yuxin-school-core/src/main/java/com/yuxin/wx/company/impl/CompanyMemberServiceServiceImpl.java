package com.yuxin.wx.company.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.ICompanyMemberServiceService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.company.mapper.CompanyMemberServiceMapper;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.vo.company.CompanyMemberServiceVo;
import com.yuxin.wx.vo.company.CompanyMemberServicesTotalVo;
import com.yuxin.wx.vo.company.CompanyVo;

/**
 * Service Implementation:CompanyMemberService
 * @author chopin
 * @date 2015-4-23
 */
@Service
@Transactional
public class CompanyMemberServiceServiceImpl extends BaseServiceImpl implements ICompanyMemberServiceService {

	@Autowired
	private CompanyMemberServiceMapper companyMemberServiceMapper;
	
	/**
	 * 
	* @Title: saveCompanyMemberService
	* @Description: 新增CompanyMemberService
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by chopin
	 */
	@Override
	public void insert(CompanyMemberService entity){
		companyMemberServiceMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCompanyMemberService 
	* @Description: 批量新增CompanyMemberService
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CompanyMemberService> entity){
		companyMemberServiceMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateCompanyMemberService 
	* @Description: 编辑CompanyMemberService
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by chopin
	 */
	@Override
	public void update(CompanyMemberService entity){
		companyMemberServiceMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteCompanyMemberServiceById 
	* @Description: 根据id删除CompanyMemberService
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by chopin
	 */
	 @Override
	public void deleteCompanyMemberServiceById(Integer id){
		companyMemberServiceMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCompanyMemberServiceByIds 
	* @Description: 根据id批量删除CompanyMemberService
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by chopin
	 */
	@Override
	public void deleteCompanyMemberServiceByIds(Integer[] ids){
		companyMemberServiceMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCompanyMemberServiceById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by chopin
	 */
	@Override
	public CompanyMemberService findCompanyMemberServiceById(Integer id){
		return companyMemberServiceMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCompanyMemberServiceByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyMemberService>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by chopin
	 */
	@Override
	public List<CompanyMemberService> findCompanyMemberServiceByPage(CompanyMemberService search){
		return companyMemberServiceMapper.page(search);
	}

	@Override
	public CompanyMemberService findByCompanyId(Integer companyId) {
		// TODO Auto-generated method stub
		return companyMemberServiceMapper.findByCompanyId(companyId);
	}

	@Override
	public PageFinder<CompanyMemberServiceVo> findCompanyMemberServiceVoByPage(
			Company company) {
		// TODO Auto-generated method stub
		List<CompanyMemberServiceVo> list = companyMemberServiceMapper.findCompanyMemberServiceVoByPage(company);
		Integer totalCount = companyMemberServiceMapper.findTotalCount(company);
		PageFinder<CompanyMemberServiceVo> pageFinder=new PageFinder<CompanyMemberServiceVo>(company.getPage(), company.getPageSize(), totalCount, list);
		return pageFinder;
	}

	@Override
	public void updateByCompanyId(CompanyMemberService entity) {
		// TODO Auto-generated method stub
		companyMemberServiceMapper.updateByCompanyId(entity);
	}

	@Override
	public CompanyMemberServiceVo findCompanyMemberInfoByCompanyId(
			Integer companyId) {
		// TODO Auto-generated method stub
		return companyMemberServiceMapper.findCompanyMemberInfoByCompanyId(companyId);
	}

	@Override
	public List<CompanyMemberServicesTotalVo> queryCopanyServices() {
		// TODO Auto-generated method stub
		return companyMemberServiceMapper.queryCopanyServices();
	}

}
