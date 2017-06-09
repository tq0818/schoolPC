package com.yuxin.wx.query.mapper;

import com.sun.org.glassfish.external.probe.provider.annotations.ProbeParam;
import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.statistics.Statistics;
import com.yuxin.wx.model.system.SysConfigTeacher;

import java.util.List;
import java.util.Map;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface StudentStatisticsMapper extends BaseMapper<Statistics> {

	/**
	 * 获取学生总数
	 * @return
	 */
	public Long getAllStudentNum(SysConfigTeacher teacher);

	/**
	 * 获取完善属性的学员总数
	 * @return
	 */
	public Long getAllStudentNumOfComplete(SysConfigTeacher teacher);

	/**
	 * 获取区域统计信息
	 * @param areaCode
	 * @return
	 */
	public List<Map> getAreaStudentStatistics();

	/**
	 * 获取学校统计信息(非直属校)
	 * @return
	 */
	public List<Map> getOrgStudentStatisticsByAreaAndStep(Map<String, Object> map);

	/**
	 * 获取学校统计信息(直属校)
	 * @return
	 */
	public List<Map> getOrgStudentStatistics(Map<String, Object> map);
}