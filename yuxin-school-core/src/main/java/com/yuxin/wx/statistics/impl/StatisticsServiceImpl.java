package com.yuxin.wx.statistics.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.statistics.IStatisticsService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.statistics.mapper.StatisticsMapper;
import com.yuxin.wx.vo.student.StudentListVo;
import com.yuxin.wx.model.statistics.QueryLessonByClassTypeVo;
import com.yuxin.wx.model.statistics.Statistics;
import com.yuxin.wx.model.statistics.Statistics2;

/**
 * Service Implementation:PayOrder
 * 
 * @author chopin
 * @date 2015-3-12
 */
@Service
@Transactional
public class StatisticsServiceImpl extends BaseServiceImpl implements IStatisticsService {

	@Autowired
	private StatisticsMapper statisticsMapper;

	@Override
	public List statisticsList(Integer classLessionId) {
		return statisticsMapper.statisticsList(classLessionId);
	}

	public List<QueryLessonByClassTypeVo> queryLessonByClassTypeId(Integer classTypeId) {
		return statisticsMapper.queryLessonByClassTypeId(classTypeId);
	}

	@Override
	public PageFinder<Statistics> queryAll(Statistics statistics) {
		List<Statistics> data = statisticsMapper.queryAll(statistics);
		PageFinder<Statistics> pageFinder = new PageFinder<Statistics>(1, 3, 4, data);
		return pageFinder;
	}

	@Override
	public PageFinder<Statistics2> queryAll2(Statistics statistics) {
		List<Statistics2> data = statisticsMapper.queryAll2(statistics);
		PageFinder<Statistics2> pageFinder = new PageFinder<Statistics2>(1, 3, 4, data);
		return pageFinder;
	}
}
