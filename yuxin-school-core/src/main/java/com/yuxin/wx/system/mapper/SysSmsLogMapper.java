package com.yuxin.wx.system.mapper;

import java.util.Map;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.system.SysSmsLog;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface SysSmsLogMapper extends BaseMapper<SysSmsLog> {

	/**
	 * 
	 * Class Name: ISysSmsLogService.java
	 * @Description: 查询是否有
	 * @author 周文斌
	 * @date 2016-6-1 下午6:40:31
	 * @version 1.0
	 * @param param
	 * @return
	 */
	Integer findByCompanyId(Map<String, Object> param);
	
}