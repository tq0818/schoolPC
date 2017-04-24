package com.yuxin.wx.api.company;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.company.CompanyStudyCard;
import com.yuxin.wx.vo.company.CompanyStudyCardsVo;
/**
 * Service Interface:CompanyStudyCard
 * @author chopin
 * @date 2017-3-14
 */

public interface ICompanyStudyCardService  {
	/**
	 * 
	* @Title: saveCompanyStudyCard
	* @Description: 新增CompanyStudyCard
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-14
	* @user by wangzx
	 */
	void insert(CompanyStudyCard entity);
	
	/**
	 * 
	* @Title: batchSaveCompanyStudyCard 
	* @Description: 批量新增CompanyStudyCard
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-14
	* @user by wangzx
	 */
	void batchInsert(List<CompanyStudyCard> list);
	
	/**
	 * 
	* @Title: updateCompanyStudyCard 
	* @Description: 编辑CompanyStudyCard
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-14
	* @user by wangzx
	 */
	void update(CompanyStudyCard entity);
	
	/**
	 * 
	* @Title: deleteCompanyStudyCardById 
	* @Description: 根据id删除CompanyStudyCard
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-14
	* @user by wangzx
	 */
	void deleteCompanyStudyCardById(Integer id);
	
	/**
	 * 
	* @Title: deleteCompanyStudyCardByIds 
	* @Description: 根据id批量删除CompanyStudyCard
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-14
	* @user by wangzx
	 */
	void deleteCompanyStudyCardByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCompanyStudyCardById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-14
	* @user by wangzx
	 */
	CompanyStudyCard findCompanyStudyCardById(Integer id);
	
	/**
	 * 
	* @Title: findCompanyStudyCardByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyStudyCard>    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-14
	* @user by wangzx
	 */
	List<CompanyStudyCard> findCompanyStudyCardByPage(CompanyStudyCard search);
	
	PageFinder<CompanyStudyCardsVo> queryStudyCards(CompanyStudyCardsVo search);
	
	List<CompanyStudyCardsVo> queryStudyCardsList(CompanyStudyCardsVo search);

	boolean createHasProxyOrgStudyCards(CompanyStudyCard sc, String[] proxyOrgIds);
	
	int queryCountByNameOrPrefix(Map<String, Object> map);
}