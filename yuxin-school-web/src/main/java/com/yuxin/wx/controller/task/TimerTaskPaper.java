package com.yuxin.wx.controller.task;

import com.yuxin.wx.api.system.ISysTaskLogService;
import com.yuxin.wx.model.system.SysTaskLog;
import com.yuxin.wx.scheduled.TikuStatisticsTask;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.io.Serializable;
import java.util.Date;

public class TimerTaskPaper extends QuartzJobBean implements Serializable {
    private Logger log = Logger.getLogger(getClass());
    private TikuStatisticsTask tikuStatisticsTask;
    private ISysTaskLogService sysTaskLogServiceImpl;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        SysTaskLog stl = new SysTaskLog();
        try {
            ApplicationContext ctx = (ApplicationContext) context.getScheduler().getContext().get("applicationContext");
            tikuStatisticsTask= (TikuStatisticsTask) ctx.getBean("tikuStatisticsTask");
            sysTaskLogServiceImpl= (ISysTaskLogService) ctx.getBean("sysTaskLogServiceImpl");
            stl.setExecuteDate(new Date());
            stl.setStartTime(new Date());
            stl.setTaskName("用户课后练习试卷统计");
            stl.setOperator(0);
            stl.setOperateTime(new Date());
            log.info("用户课后练习试卷统计任务-----执行时间：" + new Date());
            tikuStatisticsTask.tikuStatistics();
            log.info("用户课后练习试卷统计任务-----处理：完成");
            stl.setEndTime(new Date());
            stl.setResult("统计成功");
            stl.setErrorLog("无错误");
        } catch (Exception e) {
            e.printStackTrace();
            log.info("用户课后练习试卷统计-----异常：" + e.getMessage());
            stl.setEndTime(new Date());
            stl.setResult("统计中出错");
            stl.setErrorLog(e.getMessage());
        } finally {
            sysTaskLogServiceImpl.insert(stl);
        }
    }
}
