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
     * @param pageSize
     * @return
     */
    List<Map<String,Object>> queryTopSchoolView(Map<String, Object> papamMap);

    /**
     * 根据视频学科查询前三
     * @param pageSize
     * @return
     */
    List<Map<String,Object>> queryTopSubjectView(Map<String, Object> papamMap);
}