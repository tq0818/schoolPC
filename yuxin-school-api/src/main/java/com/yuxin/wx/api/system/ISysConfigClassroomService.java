package com.yuxin.wx.api.system;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.model.system.SysConfigClassroom;
/**
 * Service Interface:SysConfigClassroom
 * @author wang.zx
 * @date 2014-12-5
 */
public interface ISysConfigClassroomService  {
	/**
	 * 
	* @Title: saveSysConfigClassroom
	* @Description: 新增SysConfigClassroom
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void insert(SysConfigClassroom sysConfigClassroom);
	
	/**
	 * 
	* @Title: batchSaveSysConfigClassroom 
	* @Description: 批量新增SysConfigClassroom
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void batchInsert(List<SysConfigClassroom> sysConfigClassroom);
	
	/**
	 * 
	* @Title: updateSysConfigClassroom 
	* @Description: 编辑SysConfigClassroom
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void update(SysConfigClassroom sysConfigClassroom);
	
	/**
	 * 
	* @Title: deleteSysConfigClassroomById 
	* @Description: 根据id删除SysConfigClassroom
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void deleteSysConfigClassroomById(Integer id);
	
	/**
	 * 
	* @Title: deleteSysConfigClassroomByIds 
	* @Description: 根据id批量删除SysConfigClassroom
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void deleteSysConfigClassroomByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findSysConfigClassroomById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	SysConfigClassroom findSysConfigClassroomById(Integer id);
	
	/**
	 * 
	* @Title: findSysConfigClassroomByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysConfigClassroom>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	List<SysConfigClassroom> findSysConfigClassroomByPage(SysConfigClassroom search);
	
	/**
	 * 
	 * Class Name: ISysConfigClassroomService.java
	 * @Description: 根据条件获取教室，不分页
	 * @author liuxindong
	 * @date 2014-12-21 下午2:38:17
	 * @version 1.0
	 * @param search
	 * @return
	 */
	List<SysConfigClassroom> findClassroom(SysConfigClassroom search);
	
	/**
	 * 
	 * Class Name: ISysConfigClassroomService.java
	 * @Description: 根据学校id 查询 教室总数
	 * @author 周文斌
	 * @date 2015-4-30 下午4:20:04
	 * @version 1.0
	 * @param schoolId
	 * @return
	 */
	Integer findClassroomBySchoolId(Integer schoolId);
	
	/**
	 * 
	 * Class Name: ISysConfigClassroomService.java
	 * @Description: 根据校区id 查询  教室 
	 * @author 周文斌
	 * @date 2015-5-5 下午9:22:54
	 * @version 1.0
	 * @param campusId
	 * @return
	 */
	List<SysConfigClassroom> findClassroomByCampusId(Integer campusId);
	
	/**
	 * 
	 * Class Name: ISysConfigClassroomService.java
	 * @Description: 条件查询教室信息
	 * @author zhang.zx
	 * @date 2015年9月13日 上午10:09:52
	 * @modifier
	 * @modify-date 2015年9月13日 上午10:09:52
	 * @version 1.0
	 * @param map
	 * @return
	 */
	List<SysConfigClassroom> findClassroomByconditions(Map<String, Object> map);
}