package com.yuxin.wx.company.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.company.CompanyCouponsPatch;
import com.yuxin.wx.model.user.UsersFront;
import com.yuxin.wx.vo.company.CompanyCouponsPatchVo;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface CompanyCouponsPatchMapper extends BaseMapper<CompanyCouponsPatch> {
	
	List<CompanyCouponsPatch> queryCouponsByCondition(CompanyCouponsPatch search);
	
	List<CompanyCouponsPatchVo> queryCouponsPatchListByCompanyId(CompanyCouponsPatchVo search);
	
	int queryCouponsPatchListCountByCompanyId(CompanyCouponsPatchVo search);
	
	CompanyCouponsPatchVo queryCouponsPatchById(Integer id);
	/**
	 * 
	 * Class Name: ICompanyCouponsPatchService.java
	 * @Description: 查询已过期 准备清理的  id
	 * @author 周文斌
	 * @date 2016-6-21 上午11:32:09
	 * @version 1.0
	 * @param cleantime
	 * @return
	 */
	List<Integer> findCouponsByDate(Date cleantime);

	/**
	 * 
	 * Class Name: ICompanyCouponsLibService.java
	 * @Description: 查询code
	 * @author 周文斌
	 * @date 2016-6-21 上午11:57:16
	 * @version 1.0
	 * @param id
	 * @return
	 */
	List<String> findCodeByPatchId(Integer id);

	/**
	 * 
	 * Class Name: ICompanyCouponsPatchService.java
	 * @Description: 根据code删除user下的优惠券
	 * @author 周文斌
	 * @date 2016-6-21 下午12:00:35
	 * @version 1.0
	 * @param codes
	 */
	void delUserByCode(List<String> codes);

	/**
	 * 
	 * Class Name: ICompanyCouponsPatchService.java
	 * @Description: 根据 patchid 删除lib
	 * @author 周文斌
	 * @date 2016-6-21 下午12:04:22
	 * @version 1.0
	 * @param id
	 */
	void delLibByPatch(Integer id);
	
	List<CompanyCouponsPatch> queryCouponseBycondtion(CompanyCouponsPatch search);
	
	
	List<UsersFront> queryAllUsers(Integer companyId);
	
	List<UsersFront> queryMemberUsers(Map<String, String> map);
	
	List<UsersFront> queryAlreadyBuyClassUsers(Integer companyId);
	
	CompanyCouponsPatch findCompanyCouponsPatchBySearch(CompanyCouponsPatch search);
}