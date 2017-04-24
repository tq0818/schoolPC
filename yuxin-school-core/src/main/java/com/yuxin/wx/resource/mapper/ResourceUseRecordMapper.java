package com.yuxin.wx.resource.mapper;

import java.util.Map;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.resource.ResourceUseRecord;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface ResourceUseRecordMapper extends BaseMapper<ResourceUseRecord> {

	/**
	 * 
	 * Class Name: IResourceUseRecordService.java
	 * @Description: 查询七牛流量使用情况
	 * @author 周文斌
	 * @date 2016-9-2 上午11:11:54
	 * @modify	2016-9-2 上午11:11:54
	 * @version 
	 * @param param
	 * @return
	 */
	String findUseSumFlow(Map<String, Object> param);
	
}