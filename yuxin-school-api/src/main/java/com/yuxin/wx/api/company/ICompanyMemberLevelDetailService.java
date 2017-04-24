package com.yuxin.wx.api.company;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.model.company.CompanyMemberLevelDetail;
/**
 * Service Interface:CompanyMemberLevelDetail
 * @author chopin
 * @date 2016-5-17
 */
public interface ICompanyMemberLevelDetailService  {
	/**
	 * 
	* @Title: saveCompanyMemberLevelDetail
	* @Description: 新增CompanyMemberLevelDetail
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by wangzx
	 */
	void insert(CompanyMemberLevelDetail entity);
	
	/**
	 * 
	* @Title: batchSaveCompanyMemberLevelDetail 
	* @Description: 批量新增CompanyMemberLevelDetail
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by wangzx
	 */
	void batchInsert(List<CompanyMemberLevelDetail> list);
	
	/**
	 * 
	* @Title: updateCompanyMemberLevelDetail 
	* @Description: 编辑CompanyMemberLevelDetail
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by wangzx
	 */
	void update(CompanyMemberLevelDetail entity);
	
	/**
	 * 
	* @Title: deleteCompanyMemberLevelDetailById 
	* @Description: 根据id删除CompanyMemberLevelDetail
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by wangzx
	 */
	void deleteCompanyMemberLevelDetailById(Integer id);
	
	/**
	 * 
	* @Title: deleteCompanyMemberLevelDetailByIds 
	* @Description: 根据id批量删除CompanyMemberLevelDetail
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by wangzx
	 */
	void deleteCompanyMemberLevelDetailByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCompanyMemberLevelDetailById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by wangzx
	 */
	CompanyMemberLevelDetail findCompanyMemberLevelDetailById(Integer id);
	
	/**
	 * 
	* @Title: findCompanyMemberLevelDetailByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyMemberLevelDetail>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by wangzx
	 */
	List<CompanyMemberLevelDetail> findCompanyMemberLevelDetailByPage(CompanyMemberLevelDetail search);
	
	/**
	 * @Description: 根据memberId查有效Detail
	 * @param search
	 * @return List<CompanyMemberLevelDetail> 返回类型
	 * @user by dongshuai
	 */
	List<CompanyMemberLevelDetail> queryListByMemberId(CompanyMemberLevelDetail search);
	
	/**
	 * Class Name: ICompanyMemberLevelDetailService.java
	 * @Description: 通过memberId删除会员等级详情
	 * @author xukaiqiang
	 * @date 2016年6月5日 上午10:56:15
	 * @modifier
	 * @modify-date 2016年6月5日 上午10:56:15
	 * @version 1.0
	 * @param companyMemberLevelDetail
	 */
	void deleteMemberLevelDetailByMemberId(Integer memberId);
	/**
	 * 
	 * Class Name: ICompanyMemberLevelDetailService.java
	 * @Description: 查询等级下的最大有效期
	 * @author xukaiqiang
	 * @date 2016年6月13日 上午2:54:50
	 * @modifier
	 * @modify-date 2016年6月13日 上午2:54:50
	 * @version 1.0
	 * @param map
	 * @return
	 */
	List<Integer> findHighDetailBuyLength(Map<String, Object> map);
}