package com.yuxin.wx.tiku.mapper;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.tiku.TikuUserBatchTopic;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface TikuUserBatchTopicMapper extends BaseMapper<TikuUserBatchTopic> {
	

	/**
	 * 
	 * Class Name: ITikuUserBatchTopicService.java
	 * @Description: 查询当前做到第几题
	 * @author 周文斌
	 * @date 2015-8-31 下午5:11:27
	 * @version 1.0
	 * @param id
	 * @return
	 */
	Integer findCurrentById(Integer id);
}