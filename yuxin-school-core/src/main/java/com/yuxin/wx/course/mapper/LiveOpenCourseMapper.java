package com.yuxin.wx.course.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.course.LiveOpenCourse;
import com.yuxin.wx.vo.classes.CmlVo;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface LiveOpenCourseMapper extends BaseMapper<LiveOpenCourse> {
	LiveOpenCourse findByLiveOpenCourse(LiveOpenCourse course);

	/**
	 * 
	 * Class Name: ILiveOpenCourseService.java
	 * @Description: 查询直播教室id 展示统计用
	 * @author 周文斌
	 * @date 2015-12-24 上午11:10:24
	 * @version 1.0
	 * @param param
	 * @return
	 */
	List<CmlVo> findLiveRoomIds(Map<String, Object> param);

	/**
	 * 
	 * Class Name: ILiveOpenCourseService.java
	 * @Description: 修改公开课状态
	 * @author 周文斌
	 * @date 2015-12-31 下午4:10:59
	 * @version 1.0
	 * @param param
	 */
	void updateOpenclassByItem(Map<String, Object> param);

	/**
	 * 私有版本的查询直播 展示
	 * @Description: 修改公开课状态
	 * @author hanrb
	 * @date 2017-01-17
	 * @version 1.0
	 */
	List<String> findLiveIdByPrivateZS(LiveOpenCourse param);

	LiveOpenCourse findByLiveRoomId(@Param(value="roomId") String roomId);

	List<LiveOpenCourse> findAfterTodayByPage(LiveOpenCourse search);
}