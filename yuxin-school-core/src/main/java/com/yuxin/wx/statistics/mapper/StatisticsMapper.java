package com.yuxin.wx.statistics.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.statistics.QueryLessonByClassTypeVo;
import com.yuxin.wx.model.statistics.Statistics;
import com.yuxin.wx.model.statistics.Statistics2;

/**
 * Service Interface:Users
 * 
 * @author chopin
 * @date 2015-3-12
 */
public interface StatisticsMapper extends BaseMapper<Statistics> {

	/**
	 * Class Name: StatisticsMapper.java
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @author xukaiqiang
	 * @date 2016年5月19日 下午1:49:28
	 * @modifier
	 * @modify-date 2016年5月19日 下午1:49:28
	 * @version 1.0
	 * @return
	 */
	List statisticsList(Integer classLessionId);

	List<QueryLessonByClassTypeVo> queryLessonByClassTypeId(Integer classTypeId);

	List<Statistics> queryAll(Statistics statistics);

	List<Statistics2> queryAll2(Statistics statistics);

}