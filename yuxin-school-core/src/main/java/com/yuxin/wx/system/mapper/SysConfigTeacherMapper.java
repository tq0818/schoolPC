package com.yuxin.wx.system.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.system.SysConfigTeacher;
import com.yuxin.wx.vo.system.SysConfigTeachersVo;
import com.yuxin.wx.vo.system.TeachersVo;

/**
 * Service Interface:SysConfigTeacher
 * 
 * @author wang.zx
 * @date 2014-12-5
 */
public interface SysConfigTeacherMapper extends BaseMapper<SysConfigTeacher> {

    List<SysConfigTeacher> findTeacherBySchoolAndModuleId(@Param(value = "schoolId") Integer schoolId, @Param(value = "moduleId") Integer moduleId,
            @Param(value = "teacherType") String teacherType);

    List<SysConfigTeacher> findNotTeacherBySchoolAndModuleId(@Param(value = "schoolId") Integer schoolId, @Param(value = "moduleId") Integer moduleId);

    List<SysConfigTeacher> findTeacherBySchoolAndModuleIdAndLesson(@Param(value = "schoolId") Integer schoolId, @Param(value = "moduleId") Integer moduleId,
            @Param(value = "teacherType") String teacherType);

    List<SysConfigTeacher> queryTeachersByKeys(SysConfigTeacher search);

    List<SysConfigTeacher> queryForCheck(SysConfigTeacher search);

    int queryTeachersByKeysCount(SysConfigTeacher search);

    public List<SysConfigTeacher> queryForClassType(Map<String, String> teacherMap);

    public List<SysConfigTeacher> findSysConfigTeacherByParam(SysConfigTeacher search);

    /**
     * 
     * Class Name: SysConfigTeacherMapper.java
     * 
     * @Description: 学校id 工作类型 查询 教师数量
     * @author 周文斌
     * @date 2015-4-30 下午12:06:22
     * @version 1.0
     * @param companyId
     * @return
     */
    Integer findTeacherBySchoolId(Integer schoolId);

    /**
     * 
     * Class Name: SysConfigTeacherMapper.java
     * 
     * @Description: 学校id 查询教务员数量
     * @author 周文斌
     * @date 2015-4-30 下午12:06:37
     * @version 1.0
     * @param companyId
     * @return
     */
    Integer findSenateBySchoolId(Integer schoolId);

    // ------------------ 如果以上的方法没用的话，会直接删除 2015-05-04. 如果有新增加的方法，则写到该注释的下方
    // -------------------//

    public List<SysConfigTeacher> queryAllTeachersWithItemOneId(SysConfigTeacher teacher);
    public List<SysConfigTeacher> queryAllNewTeachersWithItemOneId(SysConfigTeacher teacher);

    Integer queryAllTeachersCountWithItemOneId(SysConfigTeacher teacher);

    List<SysConfigTeacher> findByCompany(SysConfigTeacher configTeacher);
    List<SysConfigTeacher> findByNewCompany(SysConfigTeacher configTeacher);

    SysConfigTeacher findTeacherIdByMobile(SysConfigTeacher search);

    List<SysConfigTeacher> queryTeachers(Map<String, String> teacherMap);

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

    List<SysConfigTeachersVo> queryTeacherByName(SysConfigTeachersVo sysConfigTeacher);

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

    List<SysConfigTeacher> findteachersByUserId(Integer userId);

    /**
     * 
     * Class Name: SysConfigTeacherMapper.java
     * 
     * @Description: 教务查询时分页总数量
     * @author yuchanglong
     * @date 2015年8月31日 下午4:58:22
     * @version 1.0
     * @param search
     * @return
     */
    Integer findByCompanyCount(SysConfigTeacher search);

    List<SysConfigTeacher> queryTeachersBycondition(Map<String, String> teacherMap);

    List<SysConfigTeacher> findSysConfigTeacherByCompany(SysConfigTeacher sysConfigTeahcer);

    List<SysConfigTeacher> findSysConfigTeacherByName(Map<String, String> map);

    SysConfigTeacher findTeacherAndUserById(Integer id);

    void updateauthTeacher(SysConfigTeacher teacher);

    Integer findMobileCount(SysConfigTeacher sct);

    List<SysConfigTeacher> findTeacherByUserId(SysConfigTeacher search);

    SysConfigTeacher findByInviteCode(SysConfigTeacher search);

	List<TeachersVo> queryTeachersData(TeachersVo teacher);

	Integer queryTeachersDataCount(TeachersVo teacher);
	
	int updateSortId(SysConfigTeacher search);

	int checkSortCount();
	
	List<SysConfigTeacher> findTeacherBySubject(Map<String, Object> map);

    List<SysConfigTeacher> findByIds(String[] ids);
}