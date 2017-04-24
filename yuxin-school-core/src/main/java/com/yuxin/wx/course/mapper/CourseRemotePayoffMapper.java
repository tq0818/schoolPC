package com.yuxin.wx.course.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.course.CourseRemotePayoff;

public interface CourseRemotePayoffMapper extends BaseMapper<CourseRemotePayoff> {

    List<CourseRemotePayoff> findListByClassType(CourseRemotePayoff search);
    void updateByClassType(CourseRemotePayoff search);
}
