package com.yuxin.wx.company.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.ICompanyMarketSetService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.company.mapper.CompanyMarketSetMapper;
import com.yuxin.wx.model.company.CompanyMarketSet;
import com.yuxin.wx.vo.company.CompanyTotalVo;
import com.yuxin.wx.vo.query.MarketingVo;

/**
 * Service Implementation:CompanyMarketSet
 * @author chopin
 * @date 2015-8-3
 */
@Service
@Transactional
public class CompanyMarketSetServiceImpl extends BaseServiceImpl implements ICompanyMarketSetService {

	@Autowired
	private CompanyMarketSetMapper companyMarketSetMapper;
	
	/**
	 * 
	* @Title: saveCompanyMarketSet
	* @Description: 新增CompanyMarketSet
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-3
	* @user by chopin
	 */
	@Override
	public void insert(CompanyMarketSet entity){
		companyMarketSetMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCompanyMarketSet 
	* @Description: 批量新增CompanyMarketSet
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-3
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CompanyMarketSet> entity){
		companyMarketSetMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateCompanyMarketSet 
	* @Description: 编辑CompanyMarketSet
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-3
	* @user by chopin
	 */
	@Override
	public void update(CompanyMarketSet entity){
		companyMarketSetMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteCompanyMarketSetById 
	* @Description: 根据id删除CompanyMarketSet
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-3
	* @user by chopin
	 */
	 @Override
	public void deleteCompanyMarketSetById(Integer id){
		companyMarketSetMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCompanyMarketSetByIds 
	* @Description: 根据id批量删除CompanyMarketSet
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-3
	* @user by chopin
	 */
	@Override
	public void deleteCompanyMarketSetByIds(Integer[] ids){
		companyMarketSetMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCompanyMarketSetById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-3
	* @user by chopin
	 */
	@Override
	public CompanyMarketSet findCompanyMarketSetById(Integer id){
		return companyMarketSetMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCompanyMarketSetById 
	* @Description: 根据公司id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-3
	* @user by chopin
	 */
	@Override
	public CompanyMarketSet findCompanyMarketSetByCompanyId(Integer id){
		return companyMarketSetMapper.findByCompanyId(id);
	};
	
	/**
	 * 
	* @Title: findCompanyMarketSetByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyMarketSet>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-3
	* @user by chopin
	 */
	@Override
	public List<CompanyMarketSet> findCompanyMarketSetByPage(CompanyMarketSet search){
		return companyMarketSetMapper.page(search);
	}

	@Override
	public CompanyMarketSet findByCompanyId(Integer companyId) {
		// TODO Auto-generated method stub
		return companyMarketSetMapper.findByCompanyId(companyId);
	}
	@Override
	public PageFinder<MarketingVo> findMarketingPageByDate(CompanyTotalVo map){
		List<MarketingVo> data=companyMarketSetMapper.findMarketingPageByDate(map);
		int count=companyMarketSetMapper.pageCount2(map);
		PageFinder<MarketingVo> pageFinder=new PageFinder<MarketingVo>(map.getPage(), map.getPageSize(), count,data);
		return pageFinder;
	}
	@Override
	public List<MarketingVo> findMarketingByDate(CompanyTotalVo map){
		return companyMarketSetMapper.findMarketingByDate(map);
	}
}
