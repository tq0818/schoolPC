package com.yuxin.wx.system.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.system.SysConfigClassroom;
/**
 * Service Interface:SysConfigClassroom
 * @author wang.zx
 * @date 2014-12-5
 */
public interface SysConfigClassroomMapper extends BaseMapper<SysConfigClassroom> {
	
	/**
	 * 
	 * Class Name: SysConfigClassroomMapper.java
	 * @Description: 根据条件获取教室，不分页
	 * @author liuxindong
	 * @date 2014-12-21 下午2:46:31
	 * @version 1.0
	 * @param search
	 * @return
	 */
	List<SysConfigClassroom> queryClassroom(SysConfigClassroom search);
	
	/**
	 * 
	 * Class Name: SysConfigClassroomMapper.java
	 * @Description: 根据学校id 查询教室总数
	 * @author 周文斌
	 * @date 2015-4-30 下午4:20:45
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
	
	List<SysConfigClassroom> findClassroomByconditions(Map<String, Object> map);
}