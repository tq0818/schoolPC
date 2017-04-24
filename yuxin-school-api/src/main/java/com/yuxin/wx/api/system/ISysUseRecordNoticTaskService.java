package com.yuxin.wx.api.system;

import java.util.List;

import com.yuxin.wx.model.system.SysUseRecordNoticTask;
import com.yuxin.wx.model.user.Users;
/**
 * Service Interface:SysUseRecordNoticTask
 * @author wang.zx
 * @date 2016-12-1
 */
public interface ISysUseRecordNoticTaskService  {
	/**
	 * 
	* @Title: saveSysUseRecordNoticTask
	* @Description: 新增SysUseRecordNoticTask
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-1
	* @user by wangzx
	 */
	void insert(SysUseRecordNoticTask entity);
	
	/**
	 * 
	* @Title: batchSaveSysUseRecordNoticTask 
	* @Description: 批量新增SysUseRecordNoticTask
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-1
	* @user by wangzx
	 */
	void batchInsert(List<SysUseRecordNoticTask> list);
	
	/**
	 * 
	* @Title: updateSysUseRecordNoticTask 
	* @Description: 编辑SysUseRecordNoticTask
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-1
	* @user by wangzx
	 */
	void update(SysUseRecordNoticTask entity);
	
	/**
	 * 
	* @Title: deleteSysUseRecordNoticTaskById 
	* @Description: 根据id删除SysUseRecordNoticTask
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-1
	* @user by wangzx
	 */
	void deleteSysUseRecordNoticTaskById(Integer id);
	
	/**
	 * 
	* @Title: deleteSysUseRecordNoticTaskByIds 
	* @Description: 根据id批量删除SysUseRecordNoticTask
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-1
	* @user by wangzx
	 */
	void deleteSysUseRecordNoticTaskByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findSysUseRecordNoticTaskById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-1
	* @user by wangzx
	 */
	SysUseRecordNoticTask findSysUseRecordNoticTaskById(Integer id);
	
	/**
	 * 
	* @Title: findSysUseRecordNoticTaskByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysUseRecordNoticTask>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-1
	* @user by wangzx
	 */
	List<SysUseRecordNoticTask> findSysUseRecordNoticTaskByPage(SysUseRecordNoticTask search);
	
	/**
	 * 
	 * Class Name: ISysUseRecordNoticTaskService.java
	 * @Description: 查询是否有任务记录
	 * @author 周文斌
	 * @date 2016-12-1 下午6:22:06
	 * @modify	2016-12-1 下午6:22:06
	 * @version 
	 * @param T
	 * @return
	 */
	SysUseRecordNoticTask findByCompanyId(SysUseRecordNoticTask T);
	
	/**
	 * 
	 * Class Name: ISysUseRecordNoticTaskService.java
	 * @Description: 机构管理员和运营
	 * @author dongshuai
	 * @date 2016年12月2日 下午1:14:02
	 * @modifier
	 * @modify-date 2016年12月2日 下午1:14:02
	 * @version 1.0
	 * @param companyId
	 * @return
	 */
	List<Users> queryCompanyManageUsers(Integer companyId);
	
	/**
	 * 
	 * Class Name: ISysUseRecordNoticTaskService.java
	 * @Description: 待发列表
	 * @author dongshuai
	 * @date 2016年12月2日 下午1:30:57
	 * @modifier
	 * @modify-date 2016年12月2日 下午1:30:57
	 * @version 1.0
	 * @return
	 */
	List<SysUseRecordNoticTask> queryNeedSendList();
}