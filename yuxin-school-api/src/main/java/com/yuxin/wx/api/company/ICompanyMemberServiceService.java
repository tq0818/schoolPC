package com.yuxin.wx.api.company;

import java.util.List;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.vo.company.CompanyMemberServiceVo;
import com.yuxin.wx.vo.company.CompanyMemberServicesTotalVo;
/**
 * Service Interface:CompanyMemberService
 * @author chopin
 * @date 2015-4-23
 */
public interface ICompanyMemberServiceService  {
	/**
	 * 
	* @Title: saveCompanyMemberService
	* @Description: 新增CompanyMemberService
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by wangzx
	 */
	void insert(CompanyMemberService entity);
	
	/**
	 * 
	* @Title: batchSaveCompanyMemberService 
	* @Description: 批量新增CompanyMemberService
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by wangzx
	 */
	void batchInsert(List<CompanyMemberService> list);
	
	/**
	 * 
	* @Title: updateCompanyMemberService 
	* @Description: 编辑CompanyMemberService
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by wangzx
	 */
	void update(CompanyMemberService entity);
	
	/**
	 * 
	* @Title: deleteCompanyMemberServiceById 
	* @Description: 根据id删除CompanyMemberService
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by wangzx
	 */
	void deleteCompanyMemberServiceById(Integer id);
	
	/**
	 * 
	* @Title: deleteCompanyMemberServiceByIds 
	* @Description: 根据id批量删除CompanyMemberService
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by wangzx
	 */
	void deleteCompanyMemberServiceByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCompanyMemberServiceById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by wangzx
	 */
	CompanyMemberService findCompanyMemberServiceById(Integer id);
	
	/**
	 * 
	* @Title: findCompanyMemberServiceByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyMemberService>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by wangzx
	 */
	List<CompanyMemberService> findCompanyMemberServiceByPage(CompanyMemberService search);
	
	/**
	 * 根据companyId 查询已拥有服务
	 * @param id
	 * @return
	 */
	CompanyMemberService findByCompanyId(Integer companyId);
	/**
	 * 
	 * Class Name: ICompanyMemberServiceService.java
	 * @Description: 机构管理服务授权页面根据公司名称查询信息
	 * @author ycl
	 * @date 2015-5-13 下午5:28:58
	 * @modifier
	 * @modify-date 2015-5-13 下午5:28:58
	 * @version 1.0
	 * @param company
	 * @return
	 */
	PageFinder<CompanyMemberServiceVo> findCompanyMemberServiceVoByPage(Company company);
	/**
	 * 
	 * Class Name: ICompanyMemberServiceService.java
	 * @Description: 根据公司id更新数据
	 * @author ycl
	 * @date 2015-5-15 下午3:09:39
	 * @modifier
	 * @modify-date 2015-5-15 下午3:09:39
	 * @version 1.0
	 * @param entity
	 */
	void updateByCompanyId(CompanyMemberService entity);
	/**
	 * 
	 * Class Name: ICompanyMemberServiceService.java
	 * @Description: 根据公司Id查找公司服务信息
	 * @author ycl
	 * @date 2015-5-16 上午11:28:58
	 * @modifier
	 * @modify-date 2015-5-16 上午11:28:58
	 * @version 1.0
	 * @param companyId
	 * @return
	 */
	CompanyMemberServiceVo findCompanyMemberInfoByCompanyId(Integer companyId);
	
	/**
	 * 
	 * Class Name: ICompanyMemberServiceService.java
	 * @Description: 所有公司服务信息
	 * @author zhang.zx
	 * @date 2015年7月21日 上午11:33:15
	 * @modifier
	 * @modify-date 2015年7月21日 上午11:33:15
	 * @version 1.0
	 * @return
	 */
	List<CompanyMemberServicesTotalVo> queryCopanyServices();
}