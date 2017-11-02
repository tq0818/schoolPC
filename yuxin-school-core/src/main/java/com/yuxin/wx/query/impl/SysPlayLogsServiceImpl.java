package com.yuxin.wx.query.impl;

import com.yuxin.wx.api.query.ISysPlayLogsService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.query.mapper.SysPlayLogsMapper;
import com.yuxin.wx.vo.course.UserVideoVo;
import com.yuxin.wx.vo.course.VideoCourseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/30.
 */
@Service
public class SysPlayLogsServiceImpl implements ISysPlayLogsService{
    @Autowired
    private SysPlayLogsMapper sysPlayLogsMapper;

    @Override
    public PageFinder<UserVideoVo> queryUserVideoPage(UserVideoVo userVideoVo) {
        List<UserVideoVo> al = sysPlayLogsMapper.queryUserVideoList(userVideoVo);
        Integer rowCount = sysPlayLogsMapper.queryUserVideoCount(userVideoVo);
        PageFinder<UserVideoVo> pf = new PageFinder<UserVideoVo>(userVideoVo.getPage(),
                userVideoVo.getPageSize(), rowCount, al);
        return pf;
    }

    @Override
    public List<UserVideoVo> queryUserVideoList(UserVideoVo userVideoVo) {
        return sysPlayLogsMapper.queryUserVideoList(userVideoVo);
    }

    @Override
    public PageFinder<VideoCourseVo> queryVideoCoursePage(VideoCourseVo videoCourseVo) {
        List<VideoCourseVo> al = sysPlayLogsMapper.queryVideoCourseList(videoCourseVo);
        Integer rowCount = sysPlayLogsMapper.queryVideoCourseCount(videoCourseVo);
        PageFinder<VideoCourseVo> pf = new PageFinder<VideoCourseVo>(videoCourseVo.getPage(),
                videoCourseVo.getPageSize(), rowCount, al);
        return pf;
    }

    @Override
    public List<VideoCourseVo> queryVideoCourseList(VideoCourseVo videoCourseVo) {
        return sysPlayLogsMapper.queryVideoCourseList(videoCourseVo);
    }

    @Override
    public List<Map<String, Object>> queryTotleVideoCourse(Map<String, Object> papamMap) {
        return sysPlayLogsMapper.queryTotleVideoCourse(papamMap);
    }

    @Override
    public Integer queryTotleUserVideoNum(Map<String, Object> papamMap) {
        return sysPlayLogsMapper.queryTotleUserVideoNum(papamMap);
    }

    @Override
    public List<Map<String, Object>> queryTotleSchoolStep(Map<String, Object> papamMap) {
        return sysPlayLogsMapper.queryTotleSchoolStep(papamMap);
    }

    @Override
    public List<Map<String, Object>> queryTopSchoolView(Map<String, Object> papamMap) {
        return sysPlayLogsMapper.queryTopSchoolView(papamMap);
    }

    @Override
    public List<Map<String, Object>> queryTopSubjectView(Map<String, Object> papamMap) {
        return sysPlayLogsMapper.queryTopSubjectView(papamMap);
    }
}
