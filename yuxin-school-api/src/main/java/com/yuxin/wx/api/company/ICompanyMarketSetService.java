package com.yuxin.wx.api.company;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.company.CompanyMarketSet;
import com.yuxin.wx.vo.company.CompanyTotalVo;
import com.yuxin.wx.vo.query.MarketingVo;
/**
 * Service Interface:CompanyMarketSet
 * @author chopin
 * @date 2015-8-3
 */
public interface ICompanyMarketSetService  {
	/**
	 * 
	* @Title: saveCompanyMarketSet
	* @Description: 新增CompanyMarketSet
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-3
	* @user by wangzx
	 */
	void insert(CompanyMarketSet entity);
	
	/**
	 * 
	* @Title: batchSaveCompanyMarketSet 
	* @Description: 批量新增CompanyMarketSet
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-3
	* @user by wangzx
	 */
	void batchInsert(List<CompanyMarketSet> list);
	
	/**
	 * 
	* @Title: updateCompanyMarketSet 
	* @Description: 编辑CompanyMarketSet
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-3
	* @user by wangzx
	 */
	void update(CompanyMarketSet entity);
	
	/**
	 * 
	* @Title: deleteCompanyMarketSetById 
	* @Description: 根据id删除CompanyMarketSet
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-3
	* @user by wangzx
	 */
	void deleteCompanyMarketSetById(Integer id);
	
	/**
	 * 
	* @Title: deleteCompanyMarketSetByIds 
	* @Description: 根据id批量删除CompanyMarketSet
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-3
	* @user by wangzx
	 */
	void deleteCompanyMarketSetByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCompanyMarketSetById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-3
	* @user by wangzx
	 */
	CompanyMarketSet findCompanyMarketSetById(Integer id);
	
	/**
	 * 
	* @Title: findCompanyMarketSetByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyMarketSet>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-3
	* @user by wangzx
	 */
	List<CompanyMarketSet> findCompanyMarketSetByPage(CompanyMarketSet search);
	
	/**
	 * 
	 * Class Name: ICompanyMarketSetService.java
	 * @Description: 根据公司id查询营销信息
	 * @author zhang.zx
	 * @date 2015年8月4日 上午9:43:29
	 * @modifier
	 * @modify-date 2015年8月4日 上午9:43:29
	 * @version 1.0
	 * @param companyId
	 * @return
	 */
	CompanyMarketSet findByCompanyId(Integer companyId);
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
	public CompanyMarketSet findCompanyMarketSetByCompanyId(Integer id);
	
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
	public PageFinder<MarketingVo> findMarketingPageByDate(CompanyTotalVo map);
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
	public List<MarketingVo> findMarketingByDate(CompanyTotalVo map);
	
}