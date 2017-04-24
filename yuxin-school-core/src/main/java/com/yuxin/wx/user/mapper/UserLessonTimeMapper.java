package com.yuxin.wx.user.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.user.UserLessonTime;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface UserLessonTimeMapper extends BaseMapper<UserLessonTime> {
	
	void deleteByUserIdAndLessonId(Map<String, Integer> map);
	List<UserLessonTime> findByUserIdAndLessonId(Map<String, Integer> map);
	
}