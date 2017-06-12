package com.yuxin.wx.api.query;


import com.yuxin.wx.model.system.SysConfigTeacher;
import com.yuxin.wx.vo.user.UsersAreaRelation;

import java.util.List;
import java.util.Map;

/**
 * Service Interface:statistics
 *
 * @author huanglilin
 * @date 2017-6-6
 */
public interface IStudentStatisticsService {
    /**
     * 获取学生总数
     * @return
     */
    public Long getAllStudentNum(UsersAreaRelation uersAreaRelation);

    /**
     * 获取完善属性的学员总数
     * @return
     */
    public Long getAllStudentNumOfComplete(UsersAreaRelation uersAreaRelation);

    /**
     * 获取区域统计信息
     * @param areaCode
     * @return
     */
    public List<Map> getAreaStudentStatistics();

    /**
     * 获取学校统计信息(非直属校)
     * @return
     */
    public List<Map> getOrgStudentStatisticsByAreaAndStep(Map<String, Object> map);

    /**
     * 获取学校统计信息(直属校)
     * @return
     */
    public List<Map> getOrgStudentStatistics(Map<String, Object> map);
}