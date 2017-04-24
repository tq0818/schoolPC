package com.yuxin.wx.course.mapper;

import java.util.List;

import com.yuxin.wx.model.course.CourseRemote;
import com.yuxin.wx.vo.course.CourseRemoteVo;
import com.yuxin.wx.common.BaseMapper;
/**
 * Service Interface:CourseRemote
 * @author wang.zx
 * @date 2014-12-5
 */
public interface CourseRemoteMapper extends BaseMapper<CourseRemote> {
	//自定义VO分页查询
	public List<CourseRemoteVo> queryCourseRemoteVoByKeys(CourseRemote search);
	// 根据班型id获取绑定远程教育 
	List<CourseRemote> findRemotesByClassTypeId(Integer classTypeId);
	//根据id获取对象
	CourseRemoteVo findByOneId(Integer id);
	Integer checkStopById(Integer id);
	public List<CourseRemote> findByMajor(CourseRemote remote);
	List<CourseRemote> queryCourseByItem(CourseRemote courseRemote);
}