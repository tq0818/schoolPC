package com.yuxin.wx.controller.task;

import com.yuxin.wx.api.system.ISysTaskLogService;
import com.yuxin.wx.model.system.SysTaskLog;
import com.yuxin.wx.scheduled.SendWeixinMsgTask;
import com.yuxin.wx.scheduled.TikuStatisticsTask;
import com.yuxin.wx.utils.FileUtil;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.io.Serializable;
import java.util.Date;

public class TimerTaskSendWeixinMsg extends QuartzJobBean implements Serializable {
    private Logger log = Logger.getLogger(getClass());
    private SendWeixinMsgTask sendWeixinMsgTask;
    private ISysTaskLogService sysTaskLogServiceImpl;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        SysTaskLog stl = new SysTaskLog();
        try {
            ApplicationContext ctx = (ApplicationContext) context.getScheduler().getContext().get("applicationContext");
            sendWeixinMsgTask= (SendWeixinMsgTask) ctx.getBean("sendWeixinMsgTask");
            sysTaskLogServiceImpl= (ISysTaskLogService) ctx.getBean("sysTaskLogServiceImpl");
            stl.setExecuteDate(new Date());
            stl.setStartTime(new Date());
            stl.setTaskName("定时微信账号通知");
            stl.setOperator(0);
            stl.setOperateTime(new Date());
            log.info("定时微信账号通知任务-----执行时间：" + new Date());
            sendWeixinMsgTask.sendWeixinMsg(FileUtil.props);
            log.info("定时微信账号通知任务-----处理：完成");
            stl.setEndTime(new Date());
            stl.setResult("发送成功");
            stl.setErrorLog("无错误");
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            log.info("定时微信账号通知-----异常：" + e.getMessage());
            stl.setEndTime(new Date());
            stl.setResult("发送中出错");
            stl.setErrorLog(e.getMessage());
        } finally {
            sysTaskLogServiceImpl.insert(stl);
        }
    }
}
