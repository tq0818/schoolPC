package com.yuxin.wx.api.system;

import java.util.List;

import com.yuxin.wx.model.system.SysTeacherPersonalStatusPic;
/**
 * Service Interface:SysTeacherPersonalStatusPic
 * @author chopin
 * @date 2015-10-28
 */
public interface ISysTeacherPersonalStatusPicService  {
	/**
	 * 
	* @Title: saveSysTeacherPersonalStatusPic
	* @Description: 新增SysTeacherPersonalStatusPic
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-28
	* @user by wangzx
	 */
	void insert(SysTeacherPersonalStatusPic entity);
	
	/**
	 * 
	* @Title: batchSaveSysTeacherPersonalStatusPic 
	* @Description: 批量新增SysTeacherPersonalStatusPic
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-28
	* @user by wangzx
	 */
	void batchInsert(List<SysTeacherPersonalStatusPic> list);
	
	/**
	 * 
	* @Title: updateSysTeacherPersonalStatusPic 
	* @Description: 编辑SysTeacherPersonalStatusPic
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-28
	* @user by wangzx
	 */
	void update(SysTeacherPersonalStatusPic entity);
	
	/**
	 * 
	* @Title: deleteSysTeacherPersonalStatusPicById 
	* @Description: 根据id删除SysTeacherPersonalStatusPic
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-28
	* @user by wangzx
	 */
	void deleteSysTeacherPersonalStatusPicById(Integer id);
	
	/**
	 * 
	* @Title: deleteSysTeacherPersonalStatusPicByIds 
	* @Description: 根据id批量删除SysTeacherPersonalStatusPic
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-28
	* @user by wangzx
	 */
	void deleteSysTeacherPersonalStatusPicByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findSysTeacherPersonalStatusPicById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-28
	* @user by wangzx
	 */
	SysTeacherPersonalStatusPic findSysTeacherPersonalStatusPicById(Integer id);
	
	/**
	 * 
	* @Title: findSysTeacherPersonalStatusPicByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysTeacherPersonalStatusPic>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-28
	* @user by wangzx
	 */
	List<SysTeacherPersonalStatusPic> findSysTeacherPersonalStatusPicByPage(SysTeacherPersonalStatusPic search);
}