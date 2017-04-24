package com.yuxin.wx.api.company;

import java.util.List;

import com.yuxin.wx.model.company.CompanyIndexModelPrivatepage;
import com.yuxin.wx.vo.company.CompanyIndexModelPrivatepageVo;
/**
 * Service Interface:CompanyIndexModelPrivatepage
 * @author chopin
 * @date 2015-5-18
 */
public interface ICompanyIndexModelPrivatepageService  {
	/**
	 * 
	* @Title: saveCompanyIndexModelPrivatepage
	* @Description: 新增CompanyIndexModelPrivatepage
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by wangzx
	 */
	void insert(CompanyIndexModelPrivatepage entity);
	
	/**
	 * 
	* @Title: batchSaveCompanyIndexModelPrivatepage 
	* @Description: 批量新增CompanyIndexModelPrivatepage
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by wangzx
	 */
	void batchInsert(List<CompanyIndexModelPrivatepage> list);
	
	/**
	 * 
	* @Title: updateCompanyIndexModelPrivatepage 
	* @Description: 编辑CompanyIndexModelPrivatepage
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by wangzx
	 */
	void update(CompanyIndexModelPrivatepage entity);
	
	/**
	 * 
	* @Title: deleteCompanyIndexModelPrivatepageById 
	* @Description: 根据id删除CompanyIndexModelPrivatepage
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by wangzx
	 */
	void deleteCompanyIndexModelPrivatepageById(Integer id);
	
	/**
	 * 
	* @Title: deleteCompanyIndexModelPrivatepageByIds 
	* @Description: 根据id批量删除CompanyIndexModelPrivatepage
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by wangzx
	 */
	void deleteCompanyIndexModelPrivatepageByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCompanyIndexModelPrivatepageById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by wangzx
	 */
	CompanyIndexModelPrivatepage findCompanyIndexModelPrivatepageById(Integer id);
	
	/**
	 * 
	* @Title: findCompanyIndexModelPrivatepageByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyIndexModelPrivatepage>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by wangzx
	 */
	List<CompanyIndexModelPrivatepage> findCompanyIndexModelPrivatepageByPage(CompanyIndexModelPrivatepage search);
	
	/**
	 * 
	* @Title: copyToCompany 
	* @Description: 复制系统配置信息到公司
	* @return
	* @return List<CompanyIndexModelPrivatepage>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	public void copyToCompany(Integer companyId,Integer schoolId,Integer oid,Integer nid);
	
	/**
	 * 
	* @Title: findCompanyIndexModelPrivatepageByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyIndexModelPrivatepage>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	public List<CompanyIndexModelPrivatepageVo> findCompanyConfigList(CompanyIndexModelPrivatepage search);
	

	/**
	 * 
	* @Title: findCompanyIndexModelPrivatepageByPage 
	* @Description: 根据模板查询对应配置
	* @return
	* @return List<CompanyIndexModelPrivatepage>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	public List<CompanyIndexModelPrivatepageVo> findByTemplate(CompanyIndexModelPrivatepage search);
}