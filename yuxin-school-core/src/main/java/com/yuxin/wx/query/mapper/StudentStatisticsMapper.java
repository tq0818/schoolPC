package com.yuxin.wx.query.mapper;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.statistics.Statistics;
import com.yuxin.wx.model.watchInfo.WatchInfoResult;
import com.yuxin.wx.vo.student.StudentListVo;
import com.yuxin.wx.vo.user.UsersAreaRelation;

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
	public Long getAllStudentNum(UsersAreaRelation usersAreaRelation);

	/**
	 * 获取完善属性的学员总数
	 * @return
	 */
	public Long getAllStudentNumOfComplete(UsersAreaRelation usersAreaRelation);

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

	/**
	 * 获取学校已完善总数人数
	 * @return
	 */
    Map<String, Object> getOrgStudentTotalStatisticsByAreaAndStep(Map<String, Object> map);

	/**
	 * 区域导出统计信息
	 * @param search
	 * @return
	 */
    List<Map<String, Object>> getAreaStudentCountList(StudentListVo search);

    List<Map> watchInfoIndex(Map<String, Object> map);

	List<Map> watchInfoAll(Map<String, Object> map);

	List<Map> watchIndexChartData(Map<String, Object> map);

	List<Map> watchAllChartData(Map<String, Object> map);

    List<WatchInfoResult> queryStudentsWatchInfoList(WatchInfoResult search);

	Integer queryStudentsWatchInfoListCount(WatchInfoResult search);

    List<WatchInfoResult> exportStudentsWatchInfoList(WatchInfoResult search);

    Integer totalPayMasterCount(WatchInfoResult search);

    List<Map> getEduYearBySchool(Map<String, Object> map);

	Integer getWatchNumBySchool(Map<String, Object> map);

	long getWatchTimeLengthBySchool(Map<String, Object> map);

	Integer getWatchTotalBySchool(Map<String, Object> map);

	List<Map> watchSchoolChartData(Map<String, Object> map);

	List<Map> getAllBuyNum(Map<String, Object> map);

	List<Map> getStudentWatchInfo(Map<String, Object> map);

    List<Map> queryStudentsWatchInfoCountCurrent(Map<String, Object> map);

	Integer queryStudentsWatchInfoCountCurrentCount(Map<String, Object> map);

    List<Map> queryStudentsWatchInfoTime(Map<String, Object> map);

	List<Map> exportStudentsWatchInfoCountCurrent(Map<String, Object> map);

    List<Map> exportStudentsWatchInfoCountData(Map<String, Object> map);

    List<Map<String,Object>> getAreaTotalStatistics(Map<String, Object> map);
}