package com.yuxin.wx.api.user;

import java.util.Date;
import java.util.List;

import com.yuxin.wx.model.user.SysPlayLogsVo;
import com.yuxin.wx.model.user.UserHistory;
import com.yuxin.wx.vo.user.UserHistoryAllVo;

/**
 * Service Interface:UserHistory
 * @author chopin
 * @date 2016-9-27
 */
public interface IUserHistoryService  {
	/**
	 * 
	* @Title: saveUserHistory
	* @Description: 新增UserHistory
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-27
	* @user by wangzx
	 */
	void insert(UserHistory entity);
	
	/**
	 * 
	* @Title: batchSaveUserHistory 
	* @Description: 批量新增UserHistory
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-27
	* @user by wangzx
	 */
	void batchInsert(List<UserHistory> list);
	
	/**
	 * 
	* @Title: updateUserHistory 
	* @Description: 编辑UserHistory
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-27
	* @user by wangzx
	 */
	void update(UserHistory entity);
	
	/**
	 * 
	* @Title: deleteUserHistoryById 
	* @Description: 根据id删除UserHistory
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-27
	* @user by wangzx
	 */
	void deleteUserHistoryById(Integer id);
	
	/**
	 * 
	* @Title: deleteUserHistoryByIds 
	* @Description: 根据id批量删除UserHistory
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-27
	* @user by wangzx
	 */
	void deleteUserHistoryByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findUserHistoryById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-27
	* @user by wangzx
	 */
	UserHistory findUserHistoryById(Integer id);
	
	/**
	 * 
	* @Title: findUserHistoryByPage 
	* @Description: 分页查询
	* @return
	* @return List<UserHistory>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-27
	* @user by wangzx
	 */
	List<UserHistory> findUserHistoryByPage(UserHistory search);
	
	int queryCountByClassTypeIdAndUserId(UserHistory search);

	void insertHistoryAll(UserHistoryAllVo userHistoryAllVo);

    void insertPlayLogs(UserHistoryAllVo uha);
    
    void updatePlayLogs(SysPlayLogsVo sysPlayLogsVo);
    
    void insertPlayLogs(SysPlayLogsVo sysPlayLogsVo);
    /**
     * 
     * @param date 获取制定时间的录播数据
     * @return 需要操作的录播数据集合
     */
    List<SysPlayLogsVo> querySysPlayLogsVosByDate(Date date,String mode);
}