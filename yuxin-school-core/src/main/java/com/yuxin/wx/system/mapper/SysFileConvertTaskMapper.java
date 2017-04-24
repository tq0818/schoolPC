package com.yuxin.wx.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.resource.ResourceList;
import com.yuxin.wx.model.system.SysFileConvertTask;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface SysFileConvertTaskMapper extends BaseMapper<SysFileConvertTask> {

	/**
	 * 
	* @Title: findSysFileConvertTaskByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysFileConvertTask>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-10-31
	* @user by wangzx
	 */
	List<SysFileConvertTask> pageThreeHundred(@Param(value="version")Integer version);

	/**
	 * 
	* @Title: updateSysFileConvertTask 
	* @Description: 编辑SysFileConvertTask
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-10-31
	* @user by wangzx
	 */
	Integer updateReturn(SysFileConvertTask entity);
	
	/**
	 * 
	 * Class Name: SysFileConvertTaskMapper.java
	 * @Description: 修改资源表
	 * @author 周文斌
	 * @date 2016-10-31 下午6:29:54
	 * @modify	2016-10-31 下午6:29:54
	 * @version 
	 * @param rl
	 */
	void updateResourceList(ResourceList rl);
	
	/**
	 * 
	* @Title: findSysFileConvertTaskByPage 
	* @Description: 查询待执行的任务
	* @return
	* @return List<SysFileConvertTask>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-10-31
	* @user by chopin
	 */
	List<SysFileConvertTask> queryTaskNeedConvert(@Param(value="version")Integer version,@Param(value="limit")Integer limit);
	
	/**
	 * 
	* @Title: findSysFileConvertTaskByPage 
	* @Description: 更新任务
	* @return
	* @return List<SysFileConvertTask>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-10-31
	* @user by chopin
	 */
	int checkTaskVersion(SysFileConvertTask task);
	
}