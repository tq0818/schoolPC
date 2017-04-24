package com.yuxin.wx.api.statistics;

import java.util.List;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.statistics.QueryLessonByClassTypeVo;
import com.yuxin.wx.model.statistics.Statistics;
import com.yuxin.wx.model.statistics.Statistics2;

/**
 * @ClassName: IStatisticsService
 * @Description: 统计直播
 * @author xukaiqiang
 * @date 2016年5月18日 下午8:02:16
 * @modifier
 * @modify-date 2016年5月18日 下午8:02:16
 * @version 1.0
 */
public interface IStatisticsService {
	/**
	 * Class Name: IStatisticsService.java
	 * 
	 * @Description: 参课人数查询
	 * @author xukaiqiang
	 * @date 2016年5月19日 下午1:47:07
	 * @modifier
	 * @modify-date 2016年5月19日 下午1:47:07
	 * @version 1.0
	 * @return
	 */
	public List statisticsList(Integer  classLessionId);
	
	/**
	 * Class Name: IStatisticsService.java
	 * @Description: 根据课程编号查询所有的课次
	 * @author xukaiqiang
	 * @date 2016年5月19日 下午3:57:17
	 * @modifier
	 * @modify-date 2016年5月19日 下午3:57:17
	 * @version 1.0
	 * @param classTypeId
	 * @return
	 */
	public List<QueryLessonByClassTypeVo>   queryLessonByClassTypeId(Integer classTypeId);

	public PageFinder<Statistics> queryAll(Statistics statistics);
	public PageFinder<Statistics2> queryAll2(Statistics statistics);
}