package com.yuxin.wx.api.company;

import java.util.List;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.company.CompanyRechargeLib;
import com.yuxin.wx.vo.company.CompanyRechargeLibVo;
/**
 * Service Interface:CompanyRechargeLib
 * @author chopin
 * @date 2017-4-10
 */
public interface ICompanyRechargeLibService  {
	/**
	 * 
	* @Title: saveCompanyRechargeLib
	* @Description: 新增CompanyRechargeLib
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-4-10
	* @user by wangzx
	 */
	void insert(CompanyRechargeLib entity);
	
	/**
	 * 
	* @Title: batchSaveCompanyRechargeLib 
	* @Description: 批量新增CompanyRechargeLib
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-4-10
	* @user by wangzx
	 */
	void batchInsert(List<CompanyRechargeLib> list);
	
	/**
	 * 
	* @Title: updateCompanyRechargeLib 
	* @Description: 编辑CompanyRechargeLib
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-4-10
	* @user by wangzx
	 */
	void update(CompanyRechargeLib entity);
	
	/**
	 * 
	* @Title: deleteCompanyRechargeLibById 
	* @Description: 根据id删除CompanyRechargeLib
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-4-10
	* @user by wangzx
	 */
	void deleteCompanyRechargeLibById(Integer id);
	
	/**
	 * 
	* @Title: deleteCompanyRechargeLibByIds 
	* @Description: 根据id批量删除CompanyRechargeLib
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-4-10
	* @user by wangzx
	 */
	void deleteCompanyRechargeLibByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCompanyRechargeLibById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-4-10
	* @user by wangzx
	 */
	CompanyRechargeLib findCompanyRechargeLibById(Integer id);
	
	/**
	 * 
	* @Title: findCompanyRechargeLibByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyRechargeLib>    返回类型 
	* @throws 
	* @exception 
	* @date 2017-4-10
	* @user by wangzx
	 */
	List<CompanyRechargeLib> findCompanyRechargeLibByPage(CompanyRechargeLib search);
	
	PageFinder<CompanyRechargeLibVo> queryByPatchId(CompanyRechargeLibVo search);
	
	List<CompanyRechargeLibVo> queryListByPatchId(CompanyRechargeLibVo search);
}