package com.yuxin.wx.company.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.ICompanyStudyCardService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.company.mapper.CompanyConfigProxyOrgMapper;
import com.yuxin.wx.company.mapper.CompanyStudyCardMapper;
import com.yuxin.wx.model.company.CompanyConfigProxyOrg;
import com.yuxin.wx.model.company.CompanyStudyCard;
import com.yuxin.wx.vo.company.CompanyStudyCardsVo;


/**
 * Service Implementation:CompanyStudyCard
 * @author chopin
 * @date 2017-3-14
 */
@Service
@Transactional
public class CompanyStudyCardServiceImpl extends BaseServiceImpl implements ICompanyStudyCardService {

	@Autowired
	private CompanyStudyCardMapper companyStudyCardMapper;
	
	@Autowired
	private CompanyConfigProxyOrgMapper companyConfigProxyOrgMapper;
	
	/**
	 * 
	* @Title: saveCompanyStudyCard
	* @Description: 新增CompanyStudyCard
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-14
	* @user by chopin
	 */
	@Override
	public void insert(CompanyStudyCard entity){
		companyStudyCardMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCompanyStudyCard 
	* @Description: 批量新增CompanyStudyCard
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-14
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CompanyStudyCard> entity){
		companyStudyCardMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateCompanyStudyCard 
	* @Description: 编辑CompanyStudyCard
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-14
	* @user by chopin
	 */
	@Override
	public void update(CompanyStudyCard entity){
		companyStudyCardMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteCompanyStudyCardById 
	* @Description: 根据id删除CompanyStudyCard
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-14
	* @user by chopin
	 */
	 @Override
	public void deleteCompanyStudyCardById(Integer id){
		companyStudyCardMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCompanyStudyCardByIds 
	* @Description: 根据id批量删除CompanyStudyCard
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-14
	* @user by chopin
	 */
	@Override
	public void deleteCompanyStudyCardByIds(Integer[] ids){
		companyStudyCardMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCompanyStudyCardById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-14
	* @user by chopin
	 */
	@Override
	public CompanyStudyCard findCompanyStudyCardById(Integer id){
		return companyStudyCardMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCompanyStudyCardByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyStudyCard>    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-14
	* @user by chopin
	 */
	@Override
	public List<CompanyStudyCard> findCompanyStudyCardByPage(CompanyStudyCard search){
		return companyStudyCardMapper.page(search);
	}

	@Override
	public PageFinder<CompanyStudyCardsVo> queryStudyCards(CompanyStudyCardsVo search) {
		List<CompanyStudyCardsVo> data = companyStudyCardMapper.queryStudyCards(search);
		int count = companyStudyCardMapper.queryStudyCardsCount(search);
		PageFinder<CompanyStudyCardsVo> pageFinder = new PageFinder<CompanyStudyCardsVo>(search.getPage(), search.getPageSize(), count, data);
 		return pageFinder;
	}

	@Override
	public List<CompanyStudyCardsVo> queryStudyCardsList(CompanyStudyCardsVo search) {
		return companyStudyCardMapper.queryStudyCardsList(search);
	}

	@Override
	public boolean createHasProxyOrgStudyCards(CompanyStudyCard sc, String[] proxyOrgIds) {
		Boolean isSuccess = false;
		for (String id : proxyOrgIds) {
			CompanyConfigProxyOrg proxyOrg = this.companyConfigProxyOrgMapper.findById(Integer.parseInt(id));
			sc.setProxyOrgId(id);
			sc.setProxyOrgName(proxyOrg.getName());
			this.companyStudyCardMapper.insert(sc);
			sc.setId(null);
			sc.setProxyOrgId(null);
			sc.setProxyOrgName(null);
		}
		isSuccess = true;
		return isSuccess;
	}

	@Override
	public int queryCountByNameOrPrefix(Map<String, Object> map) {
		return this.companyStudyCardMapper.queryCountByNameOrPrefix(map);
	};
}
