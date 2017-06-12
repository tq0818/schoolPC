package com.yuxin.wx.query.impl;

import com.yuxin.wx.api.query.IStudentStatisticsService;
import com.yuxin.wx.model.system.SysConfigTeacher;
import com.yuxin.wx.query.mapper.StudentStatisticsMapper;
import com.yuxin.wx.vo.user.UsersAreaRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/6.
 */
@Service
public class StudentStatisticsServiceImpl implements IStudentStatisticsService{

    @Autowired
    private StudentStatisticsMapper studentstatisticsMapper;
    @Override
    public Long getAllStudentNum(UsersAreaRelation usersAreaRelation) {
        return studentstatisticsMapper.getAllStudentNum(usersAreaRelation);
    }

    @Override
    public Long getAllStudentNumOfComplete(UsersAreaRelation usersAreaRelation) {
        return studentstatisticsMapper.getAllStudentNumOfComplete(usersAreaRelation);
    }

    @Override
    public List<Map> getAreaStudentStatistics() {
        return studentstatisticsMapper.getAreaStudentStatistics();
    }

    @Override
    public List<Map> getOrgStudentStatisticsByAreaAndStep(Map<String, Object> map) {
        return studentstatisticsMapper.getOrgStudentStatisticsByAreaAndStep(map);
    }

    @Override
    public List<Map> getOrgStudentStatistics(Map<String, Object> map) {
        return studentstatisticsMapper.getOrgStudentStatistics(map);
    }
}
