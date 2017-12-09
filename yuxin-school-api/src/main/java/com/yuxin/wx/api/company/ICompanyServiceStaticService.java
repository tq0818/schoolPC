package com.yuxin.wx.api.company;

import java.util.List;

import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.vo.company.CompanyAlarmLogVo;
import com.yuxin.wx.vo.system.SystemConfigServiceVo;
/**
 * Service Interface:CompanyServiceStatic
 * @author chopin
 * @date 2015-4-23
 */
public interface ICompanyServiceStaticService  {
	/**
	 * 
	* @Title: saveCompanyServiceStatic
	* @Description: 新增CompanyServiceStatic
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by wangzx
	 */
	void insert(CompanyServiceStatic entity);
	
	/**
	 * 
	* @Title: batchSaveCompanyServiceStatic 
	* @Description: 批量新增CompanyServiceStatic
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by wangzx
	 */
	void batchInsert(List<CompanyServiceStatic> list);
	
	/**
	 * 
	* @Title: updateCompanyServiceStatic 
	* @Description: 编辑CompanyServiceStatic
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by wangzx
	 */
	void update(CompanyServiceStatic entity);
	
	/**
	 * 
	* @Title: deleteCompanyServiceStaticById 
	* @Description: 根据id删除CompanyServiceStatic
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by wangzx
	 */
	void deleteCompanyServiceStaticById(Integer id);
	
	/**
	 * 
	* @Title: deleteCompanyServiceStaticByIds 
	* @Description: 根据id批量删除CompanyServiceStatic
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by wangzx
	 */
	void deleteCompanyServiceStaticByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCompanyServiceStaticById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by wangzx
	 */
	CompanyServiceStatic findCompanyServiceStaticById(Integer id);
	
	/**
	 * 
	* @Title: findCompanyServiceStaticByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyServiceStatic>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by wangzx
	 */
	List<CompanyServiceStatic> findCompanyServiceStaticByPage(CompanyServiceStatic search);
	
	/**
	 * 根据companyId  查询 已用  服务
	 * @param companyId
	 * @return
	 */
	CompanyServiceStatic findByCompanyId(Integer companyId);
	
	/**
	 * 
	 * Class Name: ICompanyServiceStaticService.java
	 * @Description: 查询公司已使用服务
	 * @author zhang.zx
	 * @date 2015年7月21日 上午11:38:39
	 * @modifier
	 * @modify-date 2015年7月21日 上午11:38:39
	 * @version 1.0
	 * @return
	 */
	List<CompanyServiceStatic> queryCompanyServicesUsed();
	
	/**
	 * 
	 * Class Name: ICompanyServiceStaticService.java
	 * @Description:添加公司发送服务日志
	 * @author zhang.zx
	 * @date 2015年7月21日 下午5:44:19
	 * @modifier
	 * @modify-date 2015年7月21日 下午5:44:19
	 * @version 1.0
	 * @param alarm
	 */
	void insertCompanyAlarmLog(CompanyAlarmLogVo alarm);
	
	/**
	 * 
	 * Class Name: ICompanyServiceStaticService.java
	 * @Description: 查询公司服务消息是否存在
	 * @author zhang.zx
	 * @date 2015年7月23日 下午3:56:19
	 * @modifier
	 * @modify-date 2015年7月23日 下午3:56:19
	 * @version 1.0
	 * @param alarm
	 * @return
	 */
	List<CompanyAlarmLogVo> queryCompanyServiceLogExit(CompanyAlarmLogVo alarm);
	
	/**
	 * 
	 * Class Name: ICompanyServiceStaticService.java
	 * @Description: 查询公司不存在的服务
	 * @author zhang.zx
	 * @date 2015年8月12日 下午4:51:44
	 * @modifier
	 * @modify-date 2015年8月12日 下午4:51:44
	 * @version 1.0
	 * @param companyId
	 * @return
	 */
	List<SystemConfigServiceVo> queryCompanyNoService(Integer companyId);
	/**
	 * 
	 * @author jishangyang 2017年12月8日 下午11:36:24
	 * @Method: queryCompanyAllService 
	 * @Description: 查询所有服务根据分校ID
	 * @param companyId
	 * @return 
	 * @throws
	 */
	List<SystemConfigServiceVo> queryCompanyAllService(Integer companyId);
	
	
}