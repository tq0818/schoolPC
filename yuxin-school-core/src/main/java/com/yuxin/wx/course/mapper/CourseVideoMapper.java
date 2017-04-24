package com.yuxin.wx.course.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.model.course.CourseVideo;
import com.yuxin.wx.vo.course.CourseVideoVo;
import com.yuxin.wx.common.BaseMapper;
/**
 * Service Interface:CourseVideo
 * @author wang.zx
 * @date 2014-12-5
 */
public interface CourseVideoMapper extends BaseMapper<CourseVideo> {
	List<CourseVideo> findListByModuleId(Integer ModuleId);
	List<CourseVideo> findListByClassId(Map map);
	List<CourseVideoVo> queryVideoByKeys(CourseVideo search);
	
	
}