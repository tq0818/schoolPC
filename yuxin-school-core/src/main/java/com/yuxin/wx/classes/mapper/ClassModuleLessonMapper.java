package com.yuxin.wx.classes.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yuxin.wx.model.classes.ClassModuleLesson;
import com.yuxin.wx.model.system.SysConfigClassroom;
import com.yuxin.wx.vo.classes.ClassModuleLessonVo;
import com.yuxin.wx.vo.classes.CmlVo;
import com.yuxin.wx.vo.classes.LessonVo;
import com.yuxin.wx.common.BaseMapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

/**
 * Service Interface:ClassModuleLesson
 * 
 * @author wang.zx
 * @date 2014-12-5
 */
public interface ClassModuleLessonMapper extends BaseMapper<ClassModuleLesson> {

	List<ClassModuleLesson> findClassModuleLessonByClassroomId(Integer classroomId);
	
	List<ClassModuleLesson> findClassModuleLessonByModuleNoId(Integer moduleNoId);

	List<ClassModuleLesson> findClassModuleLessonByModuleNoId1(Integer moduleNoId);

	List<ClassModuleLessonVo> findClassModuleLessonByKeys(ClassModuleLessonVo search);

	int findClassModuleLessonByKeysCount(ClassModuleLessonVo search);

	ClassModuleLessonVo findClassModuleLessonById(ClassModuleLessonVo search);

	List<ClassModuleLesson> findLessonsByRoomIdAndDate(
			@Param(value = "classroomId") Integer classroomId,
			@Param(value = "startDate") Date startDate,
			@Param(value = "endDate") Date endDate);
	
	List<ClassModuleLesson> findLessonByTeachers(ClassModuleLesson classModuleLesson);
	/**
	 * 
	 * Class Name: IClassModuleLessonService.java
	 * @Description: 查询今天以后教室占用情况
	 * @author 周文斌
	 * @date 2015-5-7 上午10:55:03
	 * @version 1.0
	 * @param sysConfigClassroom
	 * @return
	 */
	List<ClassModuleLesson> findLessonInfoByRoomIdAndDate(ClassModuleLesson cml);

	/**
	 * 
	 * Class Name: IClassModuleLessonService.java
	 * @Description: 定时任务 查询 昨天所有课次
	 * @author 周文斌
	 * @date 2015-5-26 下午5:45:02
	 * @version 1.0
	 * @param date
	 * @return
	 */
	List<LessonVo> findByDate(String date);

	/**
	 * 
	 * Class Name: IClassModuleLessonService.java
	 * @Description: 查询直播id
	 * @author 周文斌
	 * @date 2015-6-8 下午6:06:49
	 * @version 1.0
	 * @param param
	 * @return
	 */
	List<String> findLiveByTime(Map<String,Object> param);
	
	void mvLesson(Map<String, Object> param);
	
	List<ClassModuleLesson> findclassLessonByName(Map<String, Object> map);

	/**
	 * 
	 * Class Name: IClassModuleLessonService.java
	 * @Description: 查询zs回看为空的课堂
	 * @author 周文斌
	 * @date 2015-11-28 下午2:18:45
	 * @version 1.0
	 * @param param
	 * @return
	 */
	List<CmlVo> findZsLiveByTime(Map<String, Object> param);

	List<ClassModuleLesson> findcmlByModuleNoId(Integer moduleNoId);
	
	List<Integer> findClassModuleLessonIdsByModuleNoId(Integer id);
	@ResultMap("classModuleLessonResultMap")
	@Select("select l.live_room,l.live_company_type from class_module_lesson l inner join class_module_no n on l.module_no_id = n.id where (l.lesson_date = #{lessonDate} and str_to_date(#{currentTime},'%H:%i') > str_to_date(l.lesson_time_start,'%H:%i') and str_to_date(l.lesson_time_end,'%H:%i') > str_to_date(#{currentTime},'%H:%i')) union all select loc.live_room,loc.live_service_provider live_company_type from live_open_course loc where (loc.start_open_data = #{lessonDate} and str_to_date(#{currentTime},'%H:%i') > str_to_date(loc.start_time,'%H:%i') and str_to_date(loc.end_time,'%H:%i') > str_to_date(#{currentTime},'%H:%i'))")
	List<ClassModuleLesson> findLiveByAop(Map<String, Object> param);

    List<ClassModuleLesson> findLessonByCommodityId(Map<String, Object> id);

    List<ClassModuleLesson> findLessonByCommodityIdNotDel(Map<String, Object> map);
    
    void insert1(ClassModuleLesson classModuleLesson);
}