package com.yuxin.wx.api.classes;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.classes.ClassModuleLesson;
import com.yuxin.wx.model.company.CompanyLiveConfig;
import com.yuxin.wx.model.system.SysConfigClassroom;
import com.yuxin.wx.vo.classes.ClassModuleLessonVo;
import com.yuxin.wx.vo.classes.CmlVo;
import com.yuxin.wx.vo.classes.LessonVo;
/**
 * Service Interface:ClassModuleLesson
 * @author wang.zx
 * @date 2014-12-5
 */
public interface IClassModuleLessonService  {
	/**
	 * 
	* @Title: saveClassModuleLesson
	* @Description: 新增ClassModuleLesson
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void insert(ClassModuleLesson classModuleLesson);
	

	void insert1(ClassModuleLesson classModuleLesson);

	/**
	 * 
	* @Title: batchSaveClassModuleLesson 
	* @Description: 批量新增ClassModuleLesson
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void batchInsert(List<ClassModuleLesson> classModuleLesson);
	
	/**
	 * 
	* @Title: updateClassModuleLesson 
	* @Description: 编辑ClassModuleLesson
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void update(ClassModuleLesson classModuleLesson);
	
	/**
	 * 
	* @Title: deleteClassModuleLessonById 
	* @Description: 根据id删除ClassModuleLesson
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void deleteClassModuleLessonById(Integer id);
	
	/**
	 * 
	* @Title: deleteClassModuleLessonByIds 
	* @Description: 根据id批量删除ClassModuleLesson
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void deleteClassModuleLessonByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findClassModuleLessonById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	ClassModuleLesson findClassModuleLessonById(Integer id);
	
	/**
	 * 
	* @Title: findClassModuleLessonByPage 
	* @Description: 分页查询
	* @return
	* @return List<ClassModuleLesson>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	List<ClassModuleLesson> findClassModuleLessonByPage(ClassModuleLesson search);
	
	/**
	 * @Description:(根据教室Id查询对应的课次)
	 * @author wang.zx 
	 * @date 2015-1-13 下午5:56:38
	 * @version 1.0
	 * @param classroomId
	 * @return
	 */
	List<ClassModuleLesson> findClassModuleLessonByClassroomId(Integer classroomId);
	
	/**
	 * @Description:(根据班号Id查询对应的课次)
	 * @author wang.zx 
	 * @date 2015-2-1 下午6:22:59
	 * @version 1.0
	 * @param moduleNoId
	 * @return
	 */
	
	List<ClassModuleLesson> findClassModuleLessonByModuleNoId(Integer moduleNoId);

	List<ClassModuleLesson> findClassModuleLessonByModuleNoId1(Integer moduleNoId);

	
	/**
	 * @Description:根据教室Id、开始、结束时间获取教室的使用情况
	 * @author wang.zx 
	 * @date 2015-1-29 下午11:55:44
	 * @version 1.0
	 * @param roomId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<ClassModuleLesson> findLessonsByRoomIdAndDate(Integer roomId, Date startDate, Date endDate);
	
	/**
	 * Class Name: IClassModuleLessonService.java
	 * @Description: 教师排课详情查询
	 * @author Keyn
	 * @date 2015-1-21 下午3:22:27
	 * @version 1.0
	 * @param search
	 * @return
	 */
	PageFinder<ClassModuleLessonVo> findClassModuleLessonByKeys(ClassModuleLessonVo search);
	
	/**
	 * 
	 * Class Name: IClassModuleLessonService.java
	 * @Description: 查询单个课程信息
	 * @author Keyn
	 * @date 2015-1-22 上午10:59:42
	 * @version 1.0
	 * @param search
	 * @return
	 */
	ClassModuleLessonVo findClassModuleLessonById(ClassModuleLessonVo search);
	/**
	 * 
	 * Class Name: IClassModuleLessonService.java
	 * @Description: 查询老师的课次
	 * @author ycl
	 * @date 2015-5-7 上午10:30:07
	 * @modifier
	 * @modify-date 2015-5-7 上午10:30:07
	 * @version 1.0
	 * @param classModuleLesson
	 * @return
	 */
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
	
	/**
	 * 移动课次到某个班号 
	 * zhang.zx
	 * @param param
	 */
	void mvLesson(Map<String, Object> param);
	
	/**
	 * 
	 * Class Name: IClassModuleLessonService.java
	 * @Description: 根据课次名称查询课次
	 * @author zhang.zx
	 * @date 2015年9月15日 下午3:14:10
	 * @modifier
	 * @modify-date 2015年9月15日 下午3:14:10
	 * @version 1.0
	 * @param lessonName
	 * @return
	 */
	List<ClassModuleLesson> findclassLessonByName(String lessonName,Integer moduleNo);
	
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

    List<ClassModuleLesson> findLessonByCommodityId(Integer id);
	List<ClassModuleLesson> findLessonByCommodityIdNotDel(Integer id);


	/**
	 * 根据商品id查询商品下所有的课次
	 * @param ids
     * @return
	 */
	List<ClassModuleLesson> findLessonByCommodityIds(String[] ids);

	/**
	 * 通过companyId获取直播配置
	 * @param companyId 机构标识号
	 * @return
	 */
	CompanyLiveConfig queryCompanyLiveConfigByCompanyId(String companyId);
	/**
 * 通过用户标识号获取用户名称
 * @param userid 用户标识号
 * @return
 */
String findNickNameByUserFrontId(String userid);
}