package com.yuxin.wx.company.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.ICompanyRechargePatchService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.company.mapper.CompanyRechargeLibMapper;
import com.yuxin.wx.company.mapper.CompanyRechargePatchMapper;
import com.yuxin.wx.model.company.CompanyRechargeLib;
import com.yuxin.wx.model.company.CompanyRechargePatch;
import com.yuxin.wx.util.RandomCodesUtil;
import com.yuxin.wx.vo.company.CompanyRechargePatchVo;


/**
 * Service Implementation:CompanyRechargePatch
 * @author chopin
 * @date 2017-4-10
 */
@Service
@Transactional
public class CompanyRechargePatchServiceImpl extends BaseServiceImpl implements ICompanyRechargePatchService {

	@Autowired
	private CompanyRechargePatchMapper companyRechargePatchMapper;
	@Autowired
	private CompanyRechargeLibMapper companyRechargeLibMapper;
	
	/**
	 * 
	* @Title: saveCompanyRechargePatch
	* @Description: 新增CompanyRechargePatch
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-4-10
	* @user by chopin
	 */
	@Override
	public void insert(CompanyRechargePatch entity){
		companyRechargePatchMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCompanyRechargePatch 
	* @Description: 批量新增CompanyRechargePatch
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-4-10
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CompanyRechargePatch> entity){
		companyRechargePatchMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateCompanyRechargePatch 
	* @Description: 编辑CompanyRechargePatch
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-4-10
	* @user by chopin
	 */
	@Override
	public void update(CompanyRechargePatch entity){
		companyRechargePatchMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteCompanyRechargePatchById 
	* @Description: 根据id删除CompanyRechargePatch
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-4-10
	* @user by chopin
	 */
	 @Override
	public void deleteCompanyRechargePatchById(Integer id){
		companyRechargePatchMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCompanyRechargePatchByIds 
	* @Description: 根据id批量删除CompanyRechargePatch
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-4-10
	* @user by chopin
	 */
	@Override
	public void deleteCompanyRechargePatchByIds(Integer[] ids){
		companyRechargePatchMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCompanyRechargePatchById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-4-10
	* @user by chopin
	 */
	@Override
	public CompanyRechargePatch findCompanyRechargePatchById(Integer id){
		return companyRechargePatchMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCompanyRechargePatchByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyRechargePatch>    返回类型 
	* @throws 
	* @exception 
	* @date 2017-4-10
	* @user by chopin
	 */
	@Override
	public List<CompanyRechargePatch> findCompanyRechargePatchByPage(CompanyRechargePatch search){
		return companyRechargePatchMapper.page(search);
	}

	@Override
	public int queryPrefixCount(CompanyRechargePatch search) {
		return companyRechargePatchMapper.queryPrefixCount(search);
	}

	@Override
	public boolean createRechargeLibs(CompanyRechargePatch crp) {
		
		if(crp == null) return false;
		
		int count = crp.getTotalNum();
		
		List<String> codes = RandomCodesUtil.getCodes(RandomCodesUtil.CODETYPE_RECHARGECARD, count);
		
		if(codes.size() != count) return false;
		
		CompanyRechargeLib crl = null;
		for(int i=0;i<count;i++){
			crl = new CompanyRechargeLib();
			crl.setCode(crp.getPromoCodePrefix()+codes.get(i));
			crl.setTimeLimitFrom(crp.getTimeLimitFrom());
			crl.setTimeLimitTo(crp.getTimeLimitTo());
			crl.setStatus(0);
			crl.setPatchId(crp.getId());
			crl.setCompanyId(crp.getCompanyId());
			companyRechargeLibMapper.insert(crl);
		}
		
		return true;
	}

	@Override
	public PageFinder<CompanyRechargePatchVo> queryPatchList(CompanyRechargePatchVo search) {
		List<CompanyRechargePatchVo> data = companyRechargePatchMapper.queryPatchList(search);
		int count = companyRechargePatchMapper.queryPatchListCount(search);
		PageFinder<CompanyRechargePatchVo> pageFinder = new PageFinder<CompanyRechargePatchVo>(search.getPage(), search.getPageSize(), count, data);
		return pageFinder;
	}

	@Override
	public List<CompanyRechargePatchVo> queryPatchsList(CompanyRechargePatchVo search) {
		return companyRechargePatchMapper.queryPatchsList(search);
	};
}
