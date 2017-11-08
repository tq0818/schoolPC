package com.yuxin.wx.query.impl;

import com.yuxin.wx.api.query.IStudentStatisticsService;
import com.yuxin.wx.common.PageFinder2;
import com.yuxin.wx.model.system.SysConfigTeacher;
import com.yuxin.wx.model.watchInfo.WatchInfoResult;
import com.yuxin.wx.query.mapper.StudentStatisticsMapper;
import com.yuxin.wx.vo.student.StudentListVo;
import com.yuxin.wx.vo.user.UsersAreaRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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

    @Override
    public Map<String, Object> getOrgStudentTotalStatisticsByAreaAndStep(Map<String, Object> map) {
        return studentstatisticsMapper.getOrgStudentTotalStatisticsByAreaAndStep(map);
    }

    @Override
    public List<Map<String, Object>> getAreaStudentCountList(StudentListVo search) {
        return studentstatisticsMapper.getAreaStudentCountList(search);
    }

    @Override
    public List<Map> getWatchInfoIndex(Map<String, Object> map) {
        return studentstatisticsMapper.watchInfoIndex(map);
    }

    @Override
    public List<Map> getWatchInfoAll(Map<String, Object> map) {
        return studentstatisticsMapper.watchInfoAll(map);
    }

    @Override
    public List<Map> watchIndexChartData(Map<String, Object> map) {
        return studentstatisticsMapper.watchIndexChartData(map);
    }

    @Override
    public List<Map> watchAllChartData(Map<String, Object> map) {
        return studentstatisticsMapper.watchAllChartData(map);
    }

    @Override
    public PageFinder2<WatchInfoResult> queryStudentsWatchInfoList(WatchInfoResult search) {
        List<WatchInfoResult> data = studentstatisticsMapper.queryStudentsWatchInfoList(search);
        for(WatchInfoResult re  : data){
            re.setWatchTime(re.getWatchTime()/1000);
            int s = (int) (re.getWatchTime() % 60);
            int m = (int) (re.getWatchTime() / 60 % 60);
            int h = (int) (re.getWatchTime() / 3600);
            re.setStudyTime( h + "小时" + m + "分" + s +"秒");
        }
        Integer count = studentstatisticsMapper.queryStudentsWatchInfoListCount(search);
        PageFinder2<WatchInfoResult> pageFinder = new PageFinder2<WatchInfoResult>(
                search.getPage(), search.getPageSize(), count, data);
        return pageFinder;
    }

    @Override
    public List<WatchInfoResult> exportStudentsWatchInfoList(WatchInfoResult search) {
        List<WatchInfoResult> data  =studentstatisticsMapper.exportStudentsWatchInfoList(search);
        for(WatchInfoResult re  : data){
            re.setWatchTime(re.getWatchTime()/1000);
            int s = (int) (re.getWatchTime() % 60);
            int m = (int) (re.getWatchTime() / 60 % 60);
            int h = (int) (re.getWatchTime() / 3600);
            re.setStudyTime( h + "小时" + m + "分" + s +"秒");
        }
        return  data;
    }

    @Override
    public Integer totalPayMasterCount(WatchInfoResult search) {
        return studentstatisticsMapper.totalPayMasterCount(search);
    }
    @Override
    public List<Map> getEduYearBySchool(Map<String, Object> map) {
        return studentstatisticsMapper.getEduYearBySchool(map);
    }

    @Override
    public Integer getWatchNumBySchool(Map<String, Object> map) {
        return studentstatisticsMapper.getWatchNumBySchool(map);
    }

    @Override
    public String getWatchTimeLengthBySchool(Map<String, Object> map) {
        WatchInfoResult re  = studentstatisticsMapper.getWatchTimeLengthBySchool(map);
        re.setWatchTime(re.getWatchTime()/1000);
        int s = (int) (re.getWatchTime() % 60);
        int m = (int) (re.getWatchTime() / 60 % 60);
        int h = (int) (re.getWatchTime() / 3600);
        re.setStudyTime( h + "小时" + m + "分" + s +"秒");
        return (h + "小时" + m + "分" + s +"秒");
    }

    @Override
    public Integer getWatchTotalBySchool(Map<String, Object> map) {
        return    studentstatisticsMapper.getWatchTotalBySchool(map);
    }

    @Override
    public List<Map> watchSchoolChartData(Map<String, Object> map) {
        return studentstatisticsMapper.watchSchoolChartData(map);
    }

}
