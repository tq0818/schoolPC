package com.yuxin.wx.system.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.system.SysUseRecordNoticTask;
import com.yuxin.wx.model.user.Users;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface SysUseRecordNoticTaskMapper extends BaseMapper<SysUseRecordNoticTask> {
	
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
	 * Class Name: SysUseRecordNoticTaskMapper.java
	 * @Description: 机构管理员 和 运营
	 * @author dongshuai
	 * @date 2016年12月2日 下午1:12:41
	 * @modifier
	 * @modify-date 2016年12月2日 下午1:12:41
	 * @version 1.0
	 * @param companyId
	 * @return
	 */
	List<Users> queryCompanyManageUsers(Integer companyId);
	
	/**
	 * 
	 * Class Name: SysUseRecordNoticTaskMapper.java
	 * @Description: 待发列表
	 * @author dongshuai
	 * @date 2016年12月2日 下午1:30:35
	 * @modifier
	 * @modify-date 2016年12月2日 下午1:30:35
	 * @version 1.0
	 * @return
	 */
	List<SysUseRecordNoticTask> queryNeedSendList();
	
}