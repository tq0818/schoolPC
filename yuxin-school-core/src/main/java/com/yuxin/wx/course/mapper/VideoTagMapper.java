package com.yuxin.wx.course.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.course.VideoTag;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface VideoTagMapper extends BaseMapper<VideoTag> {
	List<VideoTag> findList(Integer companyId);

	/**
	 * 
	 * Class Name: IVideoTagService.java
	 * @Description: 查询标签是否存在
	 * @author 周文斌
	 * @date 2016-9-28 下午3:06:16
	 * @modify	2016-9-28 下午3:06:16
	 * @version 
	 * @param vt
	 * @return
	 */
	VideoTag findExist(VideoTag vt);
}