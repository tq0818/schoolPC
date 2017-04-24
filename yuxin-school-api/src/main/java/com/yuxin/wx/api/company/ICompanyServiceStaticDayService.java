package com.yuxin.wx.api.company;

import java.util.List;

import com.yuxin.wx.model.company.CompanyEmailHistory;
import com.yuxin.wx.model.company.CompanyServiceStaticDay;
/**
 * Service Interface:CompanyServiceStaticDay
 * @author wang.zx
 * @date 2015-5-21
 */
public interface ICompanyServiceStaticDayService  {
	/**
	 * 
	* @Title: saveCompanyServiceStaticDay
	* @Description: 新增CompanyServiceStaticDay
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-21
	* @user by wangzx
	 */
	void insert(CompanyServiceStaticDay entity);
	
	/**
	 * 
	* @Title: batchSaveCompanyServiceStaticDay 
	* @Description: 批量新增CompanyServiceStaticDay
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-21
	* @user by wangzx
	 */
	void batchInsert(List<CompanyServiceStaticDay> list);
	
	/**
	 * 
	* @Title: updateCompanyServiceStaticDay 
	* @Description: 编辑CompanyServiceStaticDay
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-21
	* @user by wangzx
	 */
	void update(CompanyServiceStaticDay entity);
	
	/**
	 * 
	* @Title: deleteCompanyServiceStaticDayById 
	* @Description: 根据id删除CompanyServiceStaticDay
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-21
	* @user by wangzx
	 */
	void deleteCompanyServiceStaticDayById(Integer id);
	
	/**
	 * 
	* @Title: deleteCompanyServiceStaticDayByIds 
	* @Description: 根据id批量删除CompanyServiceStaticDay
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-21
	* @user by wangzx
	 */
	void deleteCompanyServiceStaticDayByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCompanyServiceStaticDayById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-21
	* @user by wangzx
	 */
	CompanyServiceStaticDay findCompanyServiceStaticDayById(Integer id);
	
	/**
	 * 
	* @Title: findCompanyServiceStaticDayByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyServiceStaticDay>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-21
	* @user by wangzx
	 */
	List<CompanyServiceStaticDay> findCompanyServiceStaticDayByPage(CompanyServiceStaticDay search);
	
	/**
	 * 
	 * Class Name: ICompanyServiceStaticDayService.java
	 * @Description: 根据公司Id查询信息
	 * @author ycl
	 * @date 2015-5-21 下午8:14:11
	 * @modifier
	 * @modify-date 2015-5-21 下午8:14:11
	 * @version 1.0
	 * @param companyId
	 * @return
	 */
	List<CompanyServiceStaticDay> findInfoByCompanyId(Integer companyId);

	/**
	 * 
	 * Class Name: ICompanyServiceStaticDayService.java
	 * @Description: 定时任务  查询 昨天的 服务
	 * @author 周文斌
	 * @date 2015-5-21 下午7:30:30
	 * @version 1.0
	 * @param cssd
	 * @return
	 */
	CompanyServiceStaticDay findByDateAndCompanyId(CompanyServiceStaticDay cssd);
	
	/**
	 * 
	 * Class Name: ICompanyServiceStaticDayService.java
	 * @Description: 根据开始 和 结束日期查询
	 * @author ycl
	 * @date 2015-5-23 上午11:55:13
	 * @modifier
	 * @modify-date 2015-5-23 上午11:55:13
	 * @version 1.0
	 * @param start
	 * @param end
	 * @return
	 */
	List<CompanyServiceStaticDay> findInfoByDate(CompanyServiceStaticDay cssd);
	
	/**
	 * 
	 * Class Name: ICompanyServiceStaticDayService.java
	 * @Description: 修改根据公司id 和日期
	 * @author 周文斌
	 * @date 2015-12-8 下午8:43:01
	 * @version 1.0
	 * @param cssd
	 */
	void updateByCompanyIdDate(CompanyServiceStaticDay cssd);
}