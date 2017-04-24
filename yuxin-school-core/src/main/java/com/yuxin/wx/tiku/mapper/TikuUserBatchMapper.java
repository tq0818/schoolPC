package com.yuxin.wx.tiku.mapper;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.tiku.TikuUserBatch;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface TikuUserBatchMapper extends BaseMapper<TikuUserBatch> {
	

	/**
	 * 
	 * Class Name: ITikuUserBatchService.java
	 * @Description: 查询名字
	 * @author 周文斌
	 * @date 2015-8-31 下午4:41:12
	 * @version 1.0
	 * @param id
	 * @return
	 */
	String findNameById(Integer id);
}