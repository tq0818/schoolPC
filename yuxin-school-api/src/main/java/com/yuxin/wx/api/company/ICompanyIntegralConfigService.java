package com.yuxin.wx.api.company;

import java.util.List;

import com.yuxin.wx.model.company.CompanyIntegralConfig;
import com.yuxin.wx.model.company.CompanyInvitConfig;
/**
 * Service Interface:CompanyIntegralConfig
 * @author chopin
 * @date 2016-5-18
 */
public interface ICompanyIntegralConfigService  {
	/**
	 * 
	* @Title: saveCompanyIntegralConfig
	* @Description: 新增CompanyIntegralConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-18
	* @user by wangzx
	 */
	void insert(CompanyIntegralConfig entity);
	
	/**
	 * 
	* @Title: batchSaveCompanyIntegralConfig 
	* @Description: 批量新增CompanyIntegralConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-18
	* @user by wangzx
	 */
	void batchInsert(List<CompanyIntegralConfig> list);
	
	/**
	 * 
	* @Title: updateCompanyIntegralConfig 
	* @Description: 编辑CompanyIntegralConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-18
	* @user by wangzx
	 */
	void update(CompanyIntegralConfig entity);
	
	/**
	 * 
	* @Title: deleteCompanyIntegralConfigById 
	* @Description: 根据id删除CompanyIntegralConfig
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-18
	* @user by wangzx
	 */
	void deleteCompanyIntegralConfigById(Integer id);
	
	/**
	 * 
	* @Title: deleteCompanyIntegralConfigByIds 
	* @Description: 根据id批量删除CompanyIntegralConfig
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-18
	* @user by wangzx
	 */
	void deleteCompanyIntegralConfigByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCompanyIntegralConfigById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-18
	* @user by wangzx
	 */
	CompanyIntegralConfig findCompanyIntegralConfigById(Integer id);
	
	/**
	 * 
	* @Title: findCompanyIntegralConfigByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyIntegralConfig>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-18
	* @user by wangzx
	 */
	List<CompanyIntegralConfig> findCompanyIntegralConfigByPage(CompanyIntegralConfig search);
	
	/**
	 * 
	 * Class Name: ICompanyIntegralConfigService.java
	 * @Description: 查询公司积分配置
	 * @author zhang.zx
	 * @date 2016年5月19日 上午10:02:45
	 * @modifier
	 * @modify-date 2016年5月19日 上午10:02:45
	 * @version 1.0
	 * @param search
	 * @return
	 */
	CompanyIntegralConfig findIntegralConfigByWhere(CompanyIntegralConfig search);
	
	/**
	 * 
	 * Class Name: ICompanyIntegralConfigService.java
	 * @Description: 根据公司id获取配置
	 * @author yuchanglong
	 * @date 2016年5月18日 下午4:48:36
	 * @version 1.0
	 * @param companyId
	 * @return
	 */
	CompanyIntegralConfig findByCompanyId(Integer companyId);
}