package com.yuxin.wx.company.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.ICompanyMemberLevelDetailService;
import com.yuxin.wx.company.mapper.CompanyMemberLevelDetailMapper;
import com.yuxin.wx.model.company.CompanyMemberLevelDetail;


/**
 * Service Implementation:CompanyMemberLevelDetail
 * @author chopin
 * @date 2016-5-17
 */
@Service
@Transactional
public class CompanyMemberLevelDetailServiceImpl extends BaseServiceImpl implements ICompanyMemberLevelDetailService {

	@Autowired
	private CompanyMemberLevelDetailMapper companyMemberLevelDetailMapper;
	
	/**
	 * 
	* @Title: saveCompanyMemberLevelDetail
	* @Description: 新增CompanyMemberLevelDetail
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by chopin
	 */
	@Override
	public void insert(CompanyMemberLevelDetail entity){
		companyMemberLevelDetailMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCompanyMemberLevelDetail 
	* @Description: 批量新增CompanyMemberLevelDetail
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CompanyMemberLevelDetail> entity){
		companyMemberLevelDetailMapper.batchInsert(entity);
	};
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
	public List<Integer> findHighDetailBuyLength(Map<String, Object> map){
		return companyMemberLevelDetailMapper.findHighDetailBuyLength(map);
	}
	/**
	 * 
	* @Title: updateCompanyMemberLevelDetail 
	* @Description: 编辑CompanyMemberLevelDetail
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by chopin
	 */
	@Override
	public void update(CompanyMemberLevelDetail entity){
		companyMemberLevelDetailMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteCompanyMemberLevelDetailById 
	* @Description: 根据id删除CompanyMemberLevelDetail
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by chopin
	 */
	 @Override
	public void deleteCompanyMemberLevelDetailById(Integer id){
		companyMemberLevelDetailMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCompanyMemberLevelDetailByIds 
	* @Description: 根据id批量删除CompanyMemberLevelDetail
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by chopin
	 */
	@Override
	public void deleteCompanyMemberLevelDetailByIds(Integer[] ids){
		companyMemberLevelDetailMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCompanyMemberLevelDetailById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by chopin
	 */
	@Override
	public CompanyMemberLevelDetail findCompanyMemberLevelDetailById(Integer id){
		return companyMemberLevelDetailMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCompanyMemberLevelDetailByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyMemberLevelDetail>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by chopin
	 */
	@Override
	public List<CompanyMemberLevelDetail> findCompanyMemberLevelDetailByPage(CompanyMemberLevelDetail search){
		return companyMemberLevelDetailMapper.page(search);
	}

	@Override
	public List<CompanyMemberLevelDetail> queryListByMemberId(
			CompanyMemberLevelDetail search) {
		return companyMemberLevelDetailMapper.queryListByMemberId(search);
	};
	
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
	public void deleteMemberLevelDetailByMemberId(Integer memberId){
		companyMemberLevelDetailMapper.deleteMemberLevelDetailByMemberId(memberId);
	}
}
