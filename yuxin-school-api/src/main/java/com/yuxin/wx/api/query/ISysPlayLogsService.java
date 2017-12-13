package com.yuxin.wx.api.query;


import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.vo.course.UserVideoVo;
import com.yuxin.wx.vo.course.VideoCourseVo;
import com.yuxin.wx.vo.student.StudentListVo;
import com.yuxin.wx.vo.user.UsersAreaRelation;

import java.util.List;
import java.util.Map;

/**
 * Service Interface:statistics
 *
 * @author huanglilin
 * @date 2017-6-6
 */
public interface ISysPlayLogsService {


    /**
     * 查询用户观看视频资源情况
     * @param userVideoVo
     * @return
     */
    PageFinder<UserVideoVo> queryUserVideoPage(UserVideoVo userVideoVo);

    /**
     * 查询用户观看视频资源情况
     * @param userVideoVo
     * @return
     */
    List<UserVideoVo> queryUserVideoList(UserVideoVo userVideoVo);

    /**
     * 查询视频资源播放情况
     * @param videoCourseVo
     * @return
     */
    PageFinder<VideoCourseVo> queryVideoCoursePage(VideoCourseVo videoCourseVo);

    /**
     * 查询
     * @param videoCourseVo
     * @return
     */
    List<VideoCourseVo> queryVideoCourseList(VideoCourseVo videoCourseVo);

    /**
     * 查询区域的录播观看人数
     * @return
     */
    List<Map<String,Object>> queryTotleVideoCourse(Map<String, Object> papamMap);

    /**
     * 查询用户观看视频的总量
     * @return
     */
    Integer queryTotleUserVideoNum(Map<String, Object> papamMap);

    /**
     * 根据学校性质查询
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
     * 根据视频学科查询前三
     * @param papamMap
     * @return
     */
    List<Map<String,Object>> queryTopSubjectView(Map<String, Object> papamMap);

    /**
     * 查询单视频的信息
     * @param papamMap
     * @return
     */
    Map<String,Object> queryVideoCourseDetail(Map<String, Object> papamMap);

    /**
     * 根据课程查询它的浏览终端
     * @param papamMap
     * @return
     */
    List<Map<String,Object>> queryDeviceDetail(Map<String, Object> papamMap);

    /**
     * 查看视频的观看热点
     * @param papamMap
     * @return
     */
    List<Map<String,Object>> queryVideoCourseHourly(Map<String, Object> papamMap);

    /**
     * 通过数据查询对应的视频信息
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
     * 通过机构查询学员的点播记录
     * @param videoCourseVo
     * @return
     */
    PageFinder<VideoCourseVo> queryVideoListForSchool(VideoCourseVo videoCourseVo);

    /**
     * 查询学校的录播观看人数
     * @return
     */
    List<Map<String,Object>> queryTotleVideoCourseForSchool(Map<String, Object> papamMap);

    /**
     * 区县管理员概况导出
     * @param videoCourseVo
     * @return
     */
    List<VideoCourseVo> queryAreaCourseIndexList(VideoCourseVo videoCourseVo);

    /**
     * 教科院管理员概况-导出
     * @param videoCourseVo
     * @return
     */
    List<VideoCourseVo> queryCourseIndexList(VideoCourseVo videoCourseVo);


    /**
     * 获取用户总播放记录
     * @return
     */
    List<Map<String,Object>> queryHistoryAll();

    /**
     * 查询区域的录播观看人数
     * @return
     */
    List<Map<String,Object>> queryTotleVideoCourse1(Map<String, Object> papamMap);
}