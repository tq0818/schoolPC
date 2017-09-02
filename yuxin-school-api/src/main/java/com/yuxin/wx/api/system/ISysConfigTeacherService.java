package com.yuxin.wx.api.system;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.system.SysConfigTeacher;
import com.yuxin.wx.model.system.SysConfigTeacherLesson;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.vo.system.SysConfigTeachersVo;
import com.yuxin.wx.vo.system.TeachersVo;

/**
 * Service Interface:SysConfigTeacher
 * 
 * @author wang.zx
 * @date 2014-12-5
 */
public interface ISysConfigTeacherService {
    /**
     * 
     * @Title: saveSysConfigTeacher
     * @Description: 新增SysConfigTeacher
     * @return void 返回类型
     * @throws @exception
     * @date 2014-12-5
     * @user by wangzx
     */
    void insert(SysConfigTeacher sysConfigTeacher);

    void isnertTeaAndUse(SysConfigTeacher sysConfigTeacher);

    void updateTeaAndUse(SysConfigTeacher sysConfigTeacher);

    /**
     * 
     * @Title: batchSaveSysConfigTeacher
     * @Description: 批量新增SysConfigTeacher
     * @return void 返回类型
     * @throws @exception
     * @date 2014-12-5
     * @user by wangzx
     */
    void batchInsert(List<SysConfigTeacher> sysConfigTeacher);

    /**
     * 
     * @Title: updateSysConfigTeacher
     * @Description: 编辑SysConfigTeacher
     * @return void 返回类型
     * @throws @exception
     * @date 2014-12-5
     * @user by wangzx
     */
    void update(SysConfigTeacher sysConfigTeacher);

    /**
     * 
     * @Title: deleteSysConfigTeacherById
     * @Description: 根据id删除SysConfigTeacher
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2014-12-5
     * @user by wangzx
     */
    void deleteSysConfigTeacherById(Integer id);

    /**
     * 
     * @Title: deleteSysConfigTeacherByIds
     * @Description: 根据id批量删除SysConfigTeacher
     * @param ids
     * @return void 返回类型
     * @throws @exception
     * @date 2014-12-5
     * @user by wangzx
     */
    void deleteSysConfigTeacherByIds(Integer[] ids);

    /**
     * 
     * @Title: findSysConfigTeacherById
     * @Description: 根据id查询
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2014-12-5
     * @user by wangzx
     */
    SysConfigTeacher findSysConfigTeacherById(Integer id);

    /**
     * 
     * @Title: findSysConfigTeacherByPage
     * @Description: 分页查询
     * @return
     * @return List<SysConfigTeacher> 返回类型
     * @throws @exception
     * @date 2014-12-5
     * @user by wangzx
     */
    List<SysConfigTeacher> findSysConfigTeacherByPage(SysConfigTeacher search);

    /**
     * @Description: 根据手机号/教师名称/教师类型/教师所属分校 查询是否已经有相同的名称老师存在
     * @author zx.wang
     * @date 2015-9-9 下午7:19:24
     * @version 1.0
     * @param teacher
     * @return
     */
    List<SysConfigTeacher> findSysConfigTeacherByParam(SysConfigTeacher teacher);


    List<SysConfigTeacher> findByIds(String[] ids);

    /**
     * @Description:(根据模块Id和学校Id以及老师类型 ，查询对应的老师 ： 不关联老师授课表)
     * @author wang.zx
     * @date 2014-12-30 下午5:30:47
     * @version 1.0
     * @param schoolId
     * @param moduleId
     * @return
     */
    List<SysConfigTeacher> findTeacherBySchoolAndModuleId(Integer schoolId, Integer moduleId, String teacherType);

    /**
     * @Description: 查询教务人员(非老师)
     * @author wzx
     * @date 2015-5-26 下午6:42:41
     * @version 1.0
     * @param schoolId
     * @param moduleId
     * @param teacherType
     * @return
     */
    List<SysConfigTeacher> findNotTeacherBySchoolAndModuleId(Integer schoolId, Integer moduleId);

    /**
     * @Description:(根据模块Id和学校Id以及老师类型 ，查询对应的老师)
     * @author wang.zx
     * @date 2015-1-10 下午5:15:23
     * @version 1.0
     * @param schoolId
     * @param moduleId
     * @param teacherType
     * @return
     */
    List<SysConfigTeacher> findTeacherBySchoolAndModuleIdAndLesson(Integer schoolId, Integer moduleId, String teacherType);

    /**
     * 分页条件查询教师相关信息 Class Name: ISysConfigTeacherService.java
     * 
     * @Description: TODO
     * @author Kevin
     * @date 2015年1月17日
     * @version 1.0
     * @param search
     * @return
     */
    PageFinder<SysConfigTeacher> queryTeachersByKeys(SysConfigTeacher search);

    List<SysConfigTeacher> queryForCheck(SysConfigTeacher search);

    List<SysConfigTeacher> queryForClassType(Map<String, String> teacherMap);

    /**
     * 
     * Class Name: ISysConfigTeacherService.java
     * 
     * @Description: 学校id 工作类型 查询 教师数量
     * @author zwb
     * @date 2015-4-30 下午12:04:01
     * @version 1.0
     * @param companyId
     * @return
     */
    Integer findTeacherBySchoolId(Integer schoolId);

    /**
     * 
     * Class Name: ISysConfigTeacherService.java
     * 
     * @Description: 学校id 查询教务员数量
     * @author zwb
     * @date 2015-4-30 下午12:04:50
     * @version 1.0
     * @param companyId
     * @return
     */
    Integer findSenateBySchoolId(Integer schoolId);
    // ------------------ 如果以上的方法没用的话，会直接删除 2015-05-04. 如果有新增加的方法，则写到该注释的下方
    // -------------------//

    /**
     * @Description: 根据项目ID查询该项目下的所有的老师
     * @author wzx
     * @date 2015-5-4 下午3:08:13
     * @version 1.0
     * @param itemOneId
     * @return
     */
    List<SysConfigTeacher> queryAllTeachersWithItemOneId(SysConfigTeacher teacher);

    /**
     * 
     * Class Name: ISysConfigTeacherService.java
     * 
     * @Description: 根据公司ID查询教务人员
     * @author ycl
     * @date 2015-5-4 下午2:58:58
     * @modifier
     * @modify-date 2015-5-4 下午2:58:58
     * @version 1.0
     * @param configTeacher
     * @return
     */
    List<SysConfigTeacher> findByCompany(SysConfigTeacher configTeacher);

    /**
     * 
     * Class Name: ISysConfigTeacherService.java
     * 
     * @Description: 根据手机号查询用户
     * @author ycl
     * @date 2015-5-5 下午12:54:20
     * @modifier
     * @modify-date 2015-5-5 下午12:54:20
     * @version 1.0
     * @param mobile
     * @return
     */
    SysConfigTeacher findTeacherIdByMobile(SysConfigTeacher search);

    /**
     * 
     * Class Name: ISysConfigTeacherService.java
     * 
     * @Description: 条件查询教师信息
     * @author zhang.zx
     * @date 2015-5-5 下午12:54:20
     * @modifier
     * @modify-date 2015-5-5 下午12:54:20
     * @version 1.0
     * @param mobile
     * @return
     */
    List<SysConfigTeacher> findTeachers(Map<String, String> teacherMap);

    /**
     * 
     * Class Name: ISysConfigTeacherService.java
     * 
     * @Description: 条件查询教务信息
     * @author ycl
     * @date 2015-5-5 下午12:54:20
     * @modifier
     * @modify-date 2015-5-5 下午12:54:20
     * @version 1.0
     * @param mobile
     * @return
     */
    List<SysConfigTeacher> findAssistants(Map<String, String> teacherMap);

    /**
     * 
     * Class Name: ISysConfigTeacherService.java
     * 
     * @Description: 查询当前学校的助教
     * @author 周文斌
     * @date 2015-5-12 下午3:02:45
     * @version 1.0
     * @param schoolId
     * @return
     */
    List<SysConfigTeacher> findAssistantBySchool(Integer schoolId);

    /**
     * 
     * Class Name: ISysConfigTeacherService.java
     * 
     * @Description: 根据教师名称查询教师信息
     * @author zhang.zx
     * @date 2015年5月19日 下午9:19:43
     * @modifier
     * @modify-date 2015年5月19日 下午9:19:43
     * @version 1.0
     * @param sysConfigTeacher
     * @return
     */
    List<SysConfigTeachersVo> findSysConfigTeachersByName(SysConfigTeachersVo sysConfigTeacher);

    /**
     * 
     * Class Name: ISysConfigTeacherService.java
     * 
     * @Description: 根据用户id 查询 教师信息
     * @author 周文斌
     * @date 2015-6-5 下午9:46:11
     * @version 1.0
     * @param sysConfigTeacher
     * @return
     */
    SysConfigTeacher findByUserId(Integer userId);

    /**
     * 
     * Class Name: ISysConfigTeacherService.java
     * 
     * @Description: 排课时新增教师
     * @author yuchanglong
     * @date 2015年6月6日 下午3:54:20
     * @version 1.0
     * @param sysConfigTeacher
     * @param sysConfigTeacherLesson
     */
    void insertTeacherAndTecherLesson(SysConfigTeacher sysConfigTeacher, SysConfigTeacherLesson sysConfigTeacherLesson);

    /**
     * 
     * Class Name: ISysConfigTeacherService.java
     * 
     * @Description: 添加教师
     * @author zhang.zx
     * @date 2015年8月12日 下午3:28:39
     * @modifier
     * @modify-date 2015年8月12日 下午3:28:39
     * @version 1.0
     * @param tname
     * @param password
     * @param realName
     * @param tMobile
     */
    Integer addClassTypeTeacher(Users user);

    /**
     * 
     * Class Name: ISysConfigTeacherService.java
     * 
     * @Description: 根据用户查询所有教师
     * @author zhang.zx
     * @date 2015年8月13日 下午12:04:45
     * @modifier
     * @modify-date 2015年8月13日 下午12:04:45
     * @version 1.0
     * @param userId
     * @return
     */
    List<SysConfigTeacher> findTeachersByUserId(Integer userId);

    /**
     * 
     * Class Name: ISysConfigTeacherService.java
     * 
     * @Description:查询教务分页
     * @author yuchanglong
     * @date 2015年8月31日 下午4:53:07
     * @version 1.0
     * @param search
     * @return
     */
    PageFinder<SysConfigTeacher> findByCompanyPage(SysConfigTeacher search);

    /**
     * 
     * Class Name: ISysConfigTeacherService.java
     * 
     * @Description: 查询教师分页
     * @author yuchanglong
     * @date 2015年9月6日 下午12:37:56
     * @version 1.0
     * @param search
     * @return
     */
    PageFinder<SysConfigTeacher> findTeacherPage(SysConfigTeacher search);

    /**
     * 
     * Class Name: ISysConfigTeacherService.java
     * 
     * @Description: 条件查询教师信息
     * @author zhang.zx
     * @date 2015年9月9日 下午8:27:41
     * @modifier
     * @modify-date 2015年9月9日 下午8:27:41
     * @version 1.0
     * @param teacherMap
     * @return
     */
    List<SysConfigTeacher> queryTeachersBycondition(Map<String, String> teacherMap);

    List<SysConfigTeacher> findSysConfigTeacherByCompany(SysConfigTeacher sysConfigTeahcer);

    List<SysConfigTeacher> findSysConfigTeacherByName(Map<String, String> map);

    /**
     * 
     * Class Name: ISysConfigTeacherService.java
     * 
     * @Description: 查询教师及用户信息
     * @author yuchanglong
     * @date 2015年11月12日 下午5:59:55
     * @version 1.0
     * @param id
     * @return
     */
    SysConfigTeacher findTeacherAndUserById(Integer id);

    /**
     * 
     * Class Name: ISysConfigTeacherService.java
     * 
     * @Description: 修改老师
     * @author zhang.zx
     * @date 2016年1月5日 下午12:47:00
     * @modifier
     * @modify-date 2016年1月5日 下午12:47:00
     * @version 1.0
     * @param teacher
     */
    void updateauthTeacher(SysConfigTeacher teacher);

    /**
     * 
     * Class Name: ISysConfigTeacherService.java
     * 
     * @Description: 查询教师手机号个数
     * @author dongshuai
     * @date 2016年10月19日 下午5:57:00
     * @modifier
     * @modify-date 2016年10月19日 下午5:57:00
     * @version 1.0
     * @param sct
     * @return
     */
    Integer findMobileCount(SysConfigTeacher sct);

    List<SysConfigTeacher> findTeacherByUserId(SysConfigTeacher search);

    SysConfigTeacher findByInviteCode(SysConfigTeacher search);

	PageFinder<TeachersVo> findTeachersBycondition(TeachersVo teacher);
	
	int updateSortId(SysConfigTeacher search);

	int checkSortCount();
	
	List<SysConfigTeacher> findTeacherBySubject(Map<String, Object> map);
}