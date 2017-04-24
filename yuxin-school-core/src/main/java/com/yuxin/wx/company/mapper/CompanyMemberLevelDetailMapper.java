package com.yuxin.wx.company.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.company.CompanyMemberLevelDetail;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface CompanyMemberLevelDetailMapper extends BaseMapper<CompanyMemberLevelDetail> {
	
	List<CompanyMemberLevelDetail> queryListByMemberId(CompanyMemberLevelDetail search);
	/**
	 * Class Name: CompanyMemberLevelDetailMapper.java
	 * @Description: 根据member_id删除会员等级详情
	 * @author xukaiqiang
	 * @date 2016年6月5日 上午10:57:45
	 * @modifier
	 * @modify-date 2016年6月5日 上午10:57:45
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