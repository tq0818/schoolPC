package com.yuxin.wx.query.mapper;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.statistics.Statistics;
import com.yuxin.wx.vo.course.UserVideoVo;
import com.yuxin.wx.vo.course.VideoCourseVo;
import com.yuxin.wx.vo.student.StudentListVo;
import com.yuxin.wx.vo.user.UsersAreaRelation;

import java.util.List;
import java.util.Map;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface SysPlayLogsMapper extends BaseMapper<Statistics> {


	/**
	 * 查询视频资源播放情况
	 * @param userVideVo
	 * @return
	 */
	List<UserVideoVo> queryUserVideoList(UserVideoVo userVideVo);

	/**
	 * 查询视频资源播放情况
	 * @param userVideVo
	 * @return
	 */
	Integer queryUserVideoCount(UserVideoVo userVideVo);

	/**
	 * 查询视频资源播放情况
	 * @param videoCourseVo
	 * @return
	 */
	List<VideoCourseVo> queryVideoCourseList(VideoCourseVo videoCourseVo);

	/**
	 * 查询视频资源播放情况
	 * @param videoCourseVo
	 * @return
	 */
	Integer queryVideoCourseCount(VideoCourseVo videoCourseVo);

	/**
	 * 查询视频资源在区域下播放情况
	 * @return
	 */
	List<Map<String,Object>> queryTotleVideoCourse(Map<String, Object> papamMap);

	/**
	 * 查询视频播放的总人数
	 * @return
	 */
	Integer queryTotleUserVideoNum(Map<String, Object> papamMap);

	/**
	 * 根据学校性质查询用户
	 * @return
	 */
	List<Map<String,Object>> queryTotleSchoolStep(Map<String, Object> papamMap);

    /**
     * 观看点播前五
     * @param papamMap
     * @return
     */
    List<Map<String,Object>> queryTopSchoolView(Map<String, Object> papamMap);

	/**
	 * 根据视频查询前三
	 * @param papamMap
	 * @return
	 */
    List<Map<String,Object>> queryTopSubjectView(Map<String, Object> papamMap);

	/**
	 * 查询单个视频资源
	 * @param papamMap
	 * @return
	 */
	Map<String,Object> queryVideoCourseDetail(Map<String, Object> papamMap);

	/**
	 * 查询单个视频的浏览终端
	 * @param papamMap
	 * @return
	 */
    List<Map<String,Object>> queryDeviceDetail(Map<String, Object> papamMap);

	/**
	 * 查询视频的观看热点
	 * @param papamMap
	 * @return
	 */
    List<Map<String,Object>> queryVideoCourseHourly(Map<String, Object> papamMap);

	/**
	 * 查询单个视频资源
	 * @param papamMap
	 * @return
	 */
    Map<String,Object> queryVideo(Map<String, Object> papamMap);

	/**
	 * 教科院管理员概况-导出
	 * @param papamMap
	 * @return
	 */
    List<Map<String,Object>> queryTotleVideo(Map<String, Object> papamMap);

	/**
	 * 查看点播总时长+人次
	 * @param papamMap
	 * @return
	 */
	Map<String,Object> queryTotleStudyLengthAndPersonNum(Map<String, Object> papamMap);

	/**
	 * 通过学校查询学员点播记录
	 * @param userVideoVo
	 * @return
	 */
	List<VideoCourseVo> queryVideoListForSchool(VideoCourseVo userVideoVo);

	/**
	 * 通过学校查询学员点播记录数
	 * @param videoCourseVo
	 * @return
	 */
	Integer queryVideoListForSchoolCount(VideoCourseVo videoCourseVo);

	/**
	 * 查询视频资源在学校下播放情况
	 * @return
	 */
	List<Map<String,Object>> queryTotleVideoCourseForSchool(Map<String, Object> papamMap);

	/**
	 * 区县概况导出
	 * @param videoCourseVo
	 * @return
	 */
    List<VideoCourseVo> queryAreaCourseIndexList(VideoCourseVo videoCourseVo);

	/**
	 * 教科院概况-导出
	 * @param videoCourseVo
	 * @return
	 */
    List<VideoCourseVo> queryCourseIndexList(VideoCourseVo videoCourseVo);

	/**
	 * 获取用户播放记录
	 * @return
	 */
    List<Map<String,Object>> queryHistoryAll();

	/**
	 * 查询视频资源在区域下播放情况人次
	 * @return
	 */
    List<Map<String,Object>> queryTotleVideoCourse1(Map<String, Object> papamMap);
}