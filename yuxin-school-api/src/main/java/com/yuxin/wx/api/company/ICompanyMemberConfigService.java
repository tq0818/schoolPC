package com.yuxin.wx.api.company;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.model.company.CompanyMemberConfig;
import com.yuxin.wx.vo.company.MemberLevelVo;
/**
 * Service Interface:CompanyMemberConfig
 * @author chopin
 * @date 2016-5-17
 */
public interface ICompanyMemberConfigService  {
	/**
	 * 
	* @Title: saveCompanyMemberConfig
	* @Description: 新增CompanyMemberConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by wangzx
	 */
	void insert(CompanyMemberConfig entity);
	
	/**
	 * 
	* @Title: batchSaveCompanyMemberConfig 
	* @Description: 批量新增CompanyMemberConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by wangzx
	 */
	void batchInsert(List<CompanyMemberConfig> list);
	
	/**
	 * 
	* @Title: updateCompanyMemberConfig 
	* @Description: 编辑CompanyMemberConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by wangzx
	 */
	void update(CompanyMemberConfig entity);
	
	/**
	 * 
	* @Title: deleteCompanyMemberConfigById 
	* @Description: 根据id删除CompanyMemberConfig
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by wangzx
	 */
	void deleteCompanyMemberConfigById(Integer id);
	
	/**
	 * 
	* @Title: deleteCompanyMemberConfigByIds 
	* @Description: 根据id批量删除CompanyMemberConfig
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by wangzx
	 */
	void deleteCompanyMemberConfigByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCompanyMemberConfigById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by wangzx
	 */
	CompanyMemberConfig findCompanyMemberConfigById(Integer id);
	
	/**
	 * 
	* @Title: findCompanyMemberConfigByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyMemberConfig>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by wangzx
	 */
	List<CompanyMemberConfig> findCompanyMemberConfigByPage(CompanyMemberConfig search);
	
	/**
	 * 
	 * Class Name: ICompanyMemberConfigService.java
	 * @Description: 查询公司会员配置
	 * @author zhang.zx
	 * @date 2016年5月18日 下午6:32:26
	 * @modifier
	 * @modify-date 2016年5月18日 下午6:32:26
	 * @version 1.0
	 * @param search
	 * @return
	 */
	CompanyMemberConfig queryMemberSets(CompanyMemberConfig search);
	/**
	 * Class Name: ICompanyMemberConfigService.java
	 * @Description: 根据公司编号查询数据库中是否有该公司的会员设置
	 * @author xukaiqiang
	 * @date 2016年5月23日 下午7:09:49
	 * @modifier
	 * @modify-date 2016年5月23日 下午7:09:49
	 * @version 1.0
	 * @param companyId
	 * @return
	 */
	public CompanyMemberConfig findCompanyMemberConfigByCompanyId(Integer companyId);
	
	/**
	 * 
	 * @Description:添加保存会员,订单
	 * @author: dongshuai
	 * @date: 2016年5月23日
	 * @param companyId
	 * @param schoolId
	 * @param stuMobile
	 * @param memberId
	 * @param memberLevel
	 * @param buyLength
	 * @param buyName
	 * @param pos
	 * @param cash
	 * @param cheque
	 * @param remit
	 * @param userId
	 * @return
	 *
	 */
	boolean saveMemberForVip(String username,Integer companyId, Integer schoolId,
			String stuMobile, String memberId, String memberLevel,
			String buyLength, String buyName, String pos, String cash,
			String cheque, String remit, String needPay,Integer userId,String detailId,String detailOpenWay);
	boolean saveMemberForZeroVip(String username,Integer companyId, Integer schoolId,
			String stuMobile, String memberId, String memberLevel,
			String buyLength, String buyName, String pos, String cash,
			String cheque, String remit, String needPay,Integer userId,String detailId);
	
	/**
	 * 
	 * @Description: 查询会员等级以及该会员等级所包含的课程
	 * @author: dongshuai
	 * @date: 2016年5月26日
	 * @param param
	 * @return
	 *
	 */
	List<MemberLevelVo> findMemberLevelByCompanyId(Map<String, Object> param);
	
	/**
	 * 
	 * Class Name: ICompanyMemberConfigService.java
	 * @Description: 查询开通会员功能的公司配置
	 * @author 周文斌
	 * @date 2016-6-13 下午5:22:07
	 * @version 1.0
	 * @return
	 */
	List<CompanyMemberConfig> findCompanyMemberConfigta();

	Object findVipDateByCompanyId(Map<String, Object> param);

	CompanyMemberConfig findByCompanyId(Integer companyId);
}
