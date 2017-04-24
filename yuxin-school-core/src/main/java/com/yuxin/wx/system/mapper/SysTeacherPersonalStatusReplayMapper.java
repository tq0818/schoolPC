package com.yuxin.wx.system.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.system.SysTeacherPersonalStatus;
import com.yuxin.wx.model.system.SysTeacherPersonalStatusReplay;
import com.yuxin.wx.vo.system.TeacherDynamicsReplayAndStatusVo;
import com.yuxin.wx.vo.system.TeacherDynamicsReplayVo;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface SysTeacherPersonalStatusReplayMapper extends BaseMapper<SysTeacherPersonalStatusReplay> {
	/**
	 * 
	 * @fileName : SysTeacherPersonalStatusReplayMapper.java
	 * @author   : 杨延博
	 * @date     : 2015年11月3日上午10:37:28
	 * @description :查询动态的评论
	 */
	List<TeacherDynamicsReplayVo> findSysTeacherPersonalStatusReplayByStatusId(
			TeacherDynamicsReplayVo vo);
	/**
	 * 
	 * @fileName : SysTeacherPersonalStatusReplayMapper.java
	 * @author   : 杨延博
	 * @date     : 2015年11月3日上午10:37:04
	 * @description :查询评论数量
	 */
	Integer findReplayCountBystatusId(TeacherDynamicsReplayVo vo);
	/**
	 * 
	 * @fileName : SysTeacherPersonalStatusReplayMapper.java
	 * @author   : 杨延博
	 * @date     : 2015年11月3日上午10:36:45
	 * @description :逻辑删除动态的评论
	 */
	void updateByStatusId(
			SysTeacherPersonalStatusReplay sysTeacherPersonalStatusReplay);
	
	List<TeacherDynamicsReplayVo> findSysTeacherPersonalStatusReplayByStatusId2(TeacherDynamicsReplayVo vo);
	List<TeacherDynamicsReplayAndStatusVo> findTeacherStatusReplay(SysTeacherPersonalStatus vo);
	
	
}