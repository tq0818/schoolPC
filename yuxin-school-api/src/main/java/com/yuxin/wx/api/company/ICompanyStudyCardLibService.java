package com.yuxin.wx.api.company;

import java.util.List;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.company.CompanyStudyCard;
import com.yuxin.wx.model.company.CompanyStudyCardLib;
import com.yuxin.wx.vo.company.CompanyStudyCardLibVo;
/**
 * Service Interface:CompanyStudyCardLib
 * @author chopin
 * @date 2017-3-14
 */
public interface ICompanyStudyCardLibService  {
	/**
	 * 
	* @Title: saveCompanyStudyCardLib
	* @Description: 新增CompanyStudyCardLib
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-14
	* @user by wangzx
	 */
	void insert(CompanyStudyCardLib entity);
	
	/**
	 * 
	* @Title: batchSaveCompanyStudyCardLib 
	* @Description: 批量新增CompanyStudyCardLib
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-14
	* @user by wangzx
	 */
	void batchInsert(List<CompanyStudyCardLib> list);
	
	/**
	 * 
	* @Title: updateCompanyStudyCardLib 
	* @Description: 编辑CompanyStudyCardLib
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-14
	* @user by wangzx
	 */
	void update(CompanyStudyCardLib entity);
	
	/**
	 * 
	* @Title: deleteCompanyStudyCardLibById 
	* @Description: 根据id删除CompanyStudyCardLib
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-14
	* @user by wangzx
	 */
	void deleteCompanyStudyCardLibById(Integer id);
	
	/**
	 * 
	* @Title: deleteCompanyStudyCardLibByIds 
	* @Description: 根据id批量删除CompanyStudyCardLib
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-14
	* @user by wangzx
	 */
	void deleteCompanyStudyCardLibByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCompanyStudyCardLibById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-14
	* @user by wangzx
	 */
	CompanyStudyCardLib findCompanyStudyCardLibById(Integer id);
	
	/**
	 * 
	* @Title: findCompanyStudyCardLibByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyStudyCardLib>    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-14
	* @user by wangzx
	 */
	List<CompanyStudyCardLib> findCompanyStudyCardLibByPage(CompanyStudyCardLib search);
	
	List<CompanyStudyCardLibVo> queryStudyCardLibsListByCardId(CompanyStudyCardLibVo lib);
	
	PageFinder<CompanyStudyCardLibVo> queryStudyCardLibs(CompanyStudyCardLibVo search);

	boolean createStudyCardLibs(CompanyStudyCard studyCard);

}