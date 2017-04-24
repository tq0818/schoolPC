package com.yuxin.wx.api.system;

import java.util.List;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.system.SysTeacherPersonalStatus;
import com.yuxin.wx.model.system.SysTeacherPersonalStatusReplay;
import com.yuxin.wx.vo.system.TeacherDynamicsReplayAndStatusVo;
import com.yuxin.wx.vo.system.TeacherDynamicsReplayVo;
/**
 * Service Interface:SysTeacherPersonalStatusReplay
 * @author chopin
 * @date 2015-10-26
 */
public interface ISysTeacherPersonalStatusReplayService  {
	/**
	 * 
	* @Title: saveSysTeacherPersonalStatusReplay
	* @Description: 新增SysTeacherPersonalStatusReplay
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-26
	* @user by wangzx
	 */
	void insert(SysTeacherPersonalStatusReplay entity);
	
	/**
	 * 
	* @Title: batchSaveSysTeacherPersonalStatusReplay 
	* @Description: 批量新增SysTeacherPersonalStatusReplay
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-26
	* @user by wangzx
	 */
	void batchInsert(List<SysTeacherPersonalStatusReplay> list);
	
	/**
	 * 
	* @Title: updateSysTeacherPersonalStatusReplay 
	* @Description: 编辑SysTeacherPersonalStatusReplay
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-26
	* @user by wangzx
	 */
	void update(SysTeacherPersonalStatusReplay entity);
	
	/**
	 * 
	* @Title: deleteSysTeacherPersonalStatusReplayById 
	* @Description: 根据id删除SysTeacherPersonalStatusReplay
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-26
	* @user by wangzx
	 */
	void deleteSysTeacherPersonalStatusReplayById(Integer id);
	
	/**
	 * 
	* @Title: deleteSysTeacherPersonalStatusReplayByIds 
	* @Description: 根据id批量删除SysTeacherPersonalStatusReplay
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-26
	* @user by wangzx
	 */
	void deleteSysTeacherPersonalStatusReplayByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findSysTeacherPersonalStatusReplayById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-26
	* @user by wangzx
	 */
	SysTeacherPersonalStatusReplay findSysTeacherPersonalStatusReplayById(Integer id);
	
	/**
	 * 
	* @Title: findSysTeacherPersonalStatusReplayByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysTeacherPersonalStatusReplay>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-26
	* @user by wangzx
	 */
	List<SysTeacherPersonalStatusReplay> findSysTeacherPersonalStatusReplayByPage(SysTeacherPersonalStatusReplay search);
	/**
	 * 
	 * @fileName : ISysTeacherPersonalStatusReplayService.java
	 * @author   : 杨延博
	 * @date     : 2015年11月3日上午10:33:20
	 * @description : 通过动态查询评论
	 */
	PageFinder<TeacherDynamicsReplayVo> findSysTeacherPersonalStatusReplayByStatusId(
			TeacherDynamicsReplayVo vo);
	
	List<TeacherDynamicsReplayVo> findSysTeacherPersonalStatusReplayByStatusId2(TeacherDynamicsReplayVo vo);
	/**
	 * 
	 * @fileName : ISysTeacherPersonalStatusReplayService.java
	 * @author   : 杨延博
	 * @date     : 2015年11月3日上午10:32:54
	 * @description : 逻辑删除评论的回复
	 */
	void updateByStatusId(
			SysTeacherPersonalStatusReplay sysTeacherPersonalStatusReplay);

	List<TeacherDynamicsReplayAndStatusVo> findTeacherStatusReplay(SysTeacherPersonalStatus vo);

}