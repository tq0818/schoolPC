//package com.yuxin.wx.controller.task;
//
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.yuxin.wx.api.company.ICompanyServiceStaticService;
//import com.yuxin.wx.api.course.IVideoService;
//import com.yuxin.wx.api.system.ISysTaskLogService;
//import com.yuxin.wx.common.CCVideoConstant;
//import com.yuxin.wx.company.mapper.CompanyPayConfigMapper;
//import com.yuxin.wx.company.mapper.CompanyServiceStaticDayMapper;
//import com.yuxin.wx.company.mapper.CompanyServiceStaticMapper;
//import com.yuxin.wx.model.company.CompanyPayConfig;
//import com.yuxin.wx.model.company.CompanyServiceStatic;
//import com.yuxin.wx.model.company.CompanyServiceStaticDay;
//import com.yuxin.wx.model.system.SysFileConvertTask;
//import com.yuxin.wx.model.system.SysTaskLog;
//import com.yuxin.wx.scheduled.*;
//import com.yuxin.wx.system.mapper.SysFileConvertTaskMapper;
//import com.yuxin.wx.utils.*;
//import com.yuxin.wx.utils.FileUtil;
//import org.apache.log4j.Logger;
//import org.quartz.JobExecutionContext;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.support.PropertiesLoaderUtils;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//import sun.net.ftp.FtpClient;
//
//import java.io.*;
//import java.math.BigDecimal;
//import java.text.SimpleDateFormat;
//import java.util.*;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//import java.util.zip.GZIPInputStream;
//
//public class TimerTaskPrimary {
//    @Autowired
//    private ISysTaskLogService sysTaskLogServiceImpl;
//    @Autowired
//    private TikuStatisticsTask tikuStatisticsTask;
//
//    private Logger log = Logger.getLogger(getClass());
//
//    /**
//     * 统计用户答题结果（只针对单选和多选）
//     *
//     */
//    public void taskTikuUserExerciseAnswer(JobExecutionContext context) {
//        SysTaskLog stl = new SysTaskLog();
//        try {
//            stl.setExecuteDate(new Date());
//            stl.setStartTime(new Date());
//            stl.setTaskName("用户课后练习试卷统计");
//            stl.setOperator(0);
//            stl.setOperateTime(new Date());
//            log.info("用户课后练习试卷统计任务-----执行时间：" + new Date());
//            tikuStatisticsTask.tikuStatistics();
//            log.info("用户课后练习试卷统计任务-----处理：完成");
//            stl.setEndTime(new Date());
//            stl.setResult("统计成功");
//            stl.setErrorLog("无错误");
//        } catch (Exception e) {
//            // TODO: handle exception
//            e.printStackTrace();
//            log.info("用户课后练习试卷统计-----异常：" + e.getMessage());
//            stl.setEndTime(new Date());
//            stl.setResult("统计中出错");
//            stl.setErrorLog(e.getMessage());
//        } finally {
//            sysTaskLogServiceImpl.insert(stl);
//        }
//    }
//}
