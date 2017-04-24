package com.yuxin.wx.system.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.system.SysTeacherPersonalStatusPic;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface SysTeacherPersonalStatusPicMapper extends BaseMapper<SysTeacherPersonalStatusPic> {
	
	List<SysTeacherPersonalStatusPic> queryPicByStatusId(Integer statusId);
}