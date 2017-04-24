package com.yuxin.wx.api.company;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.company.CompanyCouponsLib;
import com.yuxin.wx.model.company.CompanyCouponsPatch;
import com.yuxin.wx.model.user.UsersFront;
import com.yuxin.wx.vo.company.CompanyCouponsPatchVo;
/**
 * Service Interface:CompanyCouponsPatch
 * @author chopin
 * @date 2016-6-20
 */
public interface ICompanyCouponsPatchService  {
	/**
	 * 
	* @Title: saveCompanyCouponsPatch
	* @Description: 新增CompanyCouponsPatch
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-20
	* @user by wangzx
	 */
	void insert(CompanyCouponsPatch entity);
	
	/**
	 * 
	* @Title: batchSaveCompanyCouponsPatch 
	* @Description: 批量新增CompanyCouponsPatch
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-20
	* @user by wangzx
	 */
	void batchInsert(List<CompanyCouponsPatch> list);
	
	/**
	 * 
	* @Title: updateCompanyCouponsPatch 
	* @Description: 编辑CompanyCouponsPatch
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-20
	* @user by wangzx
	 */
	void update(CompanyCouponsPatch entity);
	
	/**
	 * 
	* @Title: deleteCompanyCouponsPatchById 
	* @Description: 根据id删除CompanyCouponsPatch
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-20
	* @user by wangzx
	 */
	void deleteCompanyCouponsPatchById(Integer id);
	
	/**
	 * 
	* @Title: deleteCompanyCouponsPatchByIds 
	* @Description: 根据id批量删除CompanyCouponsPatch
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-20
	* @user by wangzx
	 */
	void deleteCompanyCouponsPatchByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCompanyCouponsPatchById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-20
	* @user by wangzx
	 */
	CompanyCouponsPatch findCompanyCouponsPatchById(Integer id);
	
	/**
	 * 
	* @Title: findCompanyCouponsPatchByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyCouponsPatch>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-20
	* @user by wangzx
	 */
	List<CompanyCouponsPatch> findCompanyCouponsPatchByPage(CompanyCouponsPatch search);
	
	/**
	 * 
	 * Class Name: ICompanyCouponsPatchService.java
	 * @Description: 条件查询
	 * @author zhang.zx
	 * @date 2016年6月20日 下午7:25:09
	 * @modifier
	 * @modify-date 2016年6月20日 下午7:25:09
	 * @version 1.0
	 * @param search
	 * @return
	 */
	List<CompanyCouponsPatch> queryCouponsByCondition(CompanyCouponsPatch search);

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
	
	/**
	 * 
	 * Class Name: ICompanyCouponsPatchService.java
	 * @Description: 添加批次优惠券
	 * @author zhang.zx
	 * @date 2016年6月21日 下午3:40:04
	 * @modifier
	 * @modify-date 2016年6月21日 下午3:40:04
	 * @version 1.0
	 * @return
	 */
	String manageCouponsPatch(CompanyCouponsPatch coupons,String type,Integer userId);

	/**
	 * 
	 * Class Name: ICompanyCouponsPatchService.java
	 * @Description: 优惠券批次列表
	 * @author dongshuai
	 * @date 2016年6月21日 上午11:38:21
	 * @modifier
	 * @modify-date 2016年6月21日 上午11:38:21
	 * @version 1.0
	 * @param search
	 * @return
	 */
	PageFinder<CompanyCouponsPatchVo> queryCouponsPatchListByCompanyId(CompanyCouponsPatchVo search);
	
	/**
	 * 
	 * Class Name: ICompanyCouponsPatchService.java
	 * @Description: searchPatchbyId
	 * @author dongshuai
	 * @date 2016年6月21日 下午6:13:36
	 * @modifier
	 * @modify-date 2016年6月21日 下午6:13:36
	 * @version 1.0
	 * @param id
	 * @return
	 */
	CompanyCouponsPatchVo queryCouponsPatchById(Integer id);
	
	/**
	 * 
	 * Class Name: ICompanyCouponsPatchService.java
	 * @Description: 条件查询优惠券批次
	 * @author zhang.zx
	 * @date 2016年6月22日 下午3:27:56
	 * @modifier
	 * @modify-date 2016年6月22日 下午3:27:56
	 * @version 1.0
	 * @param search
	 * @return
	 */
	List<CompanyCouponsPatch> queryCouponseBycondtion(CompanyCouponsPatch search);
	
	/**
	 * 
	 * Class Name: ICompanyCouponsPatchService.java
	 * @Description: 发放优惠码
	 * @author dongshuai
	 * @date 2016年6月22日 下午5:39:26
	 * @modifier
	 * @modify-date 2016年6月22日 下午5:39:26
	 * @version 1.0
	 * @param ccp
	 * @param cclList
	 * @return
	 */
	boolean sendCouponsPatch(CompanyCouponsPatch ccp,List<CompanyCouponsLib> cclList,List<String> userslist);
	
	/**
	 * 
	 * Class Name: ICompanyCouponsPatchService.java
	 * @Description: 发送优惠码消息
	 * @author dongshuai
	 * @date 2016年6月22日 下午7:18:01
	 * @modifier
	 * @modify-date 2016年6月22日 下午7:18:01
	 * @version 1.0
	 * @param ccp
	 * @param userIds
	 * @param createrId
	 * @param schoolId
	 * @param companyId
	 * @return
	 */
	boolean sendCouponsMessage(CompanyCouponsPatch ccp,String[] userIds,Integer createrId,Integer schoolId,Integer companyId);
	
	
	
	/**
	 * 
	 * Class Name: ICompanyCouponsPatchService.java
	 * @Description: 获取用户
	 * @author dongshuai
	 * @date 2016年6月22日 下午7:18:28
	 * @modifier
	 * @modify-date 2016年6月22日 下午7:18:28
	 * @version 1.0
	 * @param companyId
	 * @return
	 */
	List<UsersFront> queryAllUsers(Integer companyId);
	
	List<UsersFront> queryMemberUsers(Map<String, String> map);
	
	List<UsersFront> queryAlreadyBuyClassUsers(Integer companyId);
	
	/**
	 * 
	 * Class Name: ICompanyCouponsPatchService.java
	 * @Description: 发送短信
	 * @author dongshuai
	 * @date 2016年6月23日 上午11:32:13
	 * @modifier
	 * @modify-date 2016年6月23日 上午11:32:13
	 * @version 1.0
	 * @param ccp
	 * @param createrId
	 * @param schoolId
	 * @param companyId
	 * @return
	 */
	String sendCouponsMobileMessage(CompanyCouponsPatch ccp,String userId,Integer createrId,Integer schoolId,Integer companyId);
	
	CompanyCouponsPatch findCompanyCouponsPatchBySearch(CompanyCouponsPatch search);
}