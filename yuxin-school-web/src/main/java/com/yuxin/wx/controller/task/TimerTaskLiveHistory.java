package com.yuxin.wx.controller.task;

import com.google.gson.Gson;
import com.yuxin.wx.api.company.ICompanyLiveConfigService;
import com.yuxin.wx.api.system.ISysKnowledgeTreeService;
import com.yuxin.wx.api.system.ISysKnowledgeTreeStatisticsService;
import com.yuxin.wx.api.system.ISysTaskLogService;
import com.yuxin.wx.api.user.IUsersFrontService;
import com.yuxin.wx.api.watchInfo.IWatchInfoService;
import com.yuxin.wx.common.LiveRoomConstant;
import com.yuxin.wx.model.company.CompanyLiveConfig;
import com.yuxin.wx.model.system.SysKnowledgeTree;
import com.yuxin.wx.model.system.SysKnowledgeTreeStatistics;
import com.yuxin.wx.model.system.SysTaskLog;
import com.yuxin.wx.model.user.UsersFront;
import com.yuxin.wx.model.watchInfo.WatchInfo;
import com.yuxin.wx.scheduled.TikuStatisticsTask;
import com.yuxin.wx.utils.HttpPostRequest;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TimerTaskLiveHistory extends QuartzJobBean implements Serializable {
    private Logger log = Logger.getLogger(getClass());
    private IWatchInfoService watchInfoServiceImpl;
    private ICompanyLiveConfigService companyLiveConfigServiceImpl;
    private ISysKnowledgeTreeStatisticsService sysKnowledgeTreeStatisticsServiceImpl;
    private ISysKnowledgeTreeService sysKnowledgeTreeServiceImpl;
    private IUsersFrontService usersFrontServiceImpl;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        //获取当日的课次
//        Date date = new Date();
//        date.setTime(date.getTime()-(3600*24*1000));
        log.info("获取昨天直播观看信息-----执行时间：" + new Date());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar ca = Calendar.getInstance();
        //ca.set(Calendar.MONTH,7);
        ca.add(Calendar.DAY_OF_MONTH,-1);
        String lessonDate = sdf.format(ca.getTime());
        Map dateMap = new HashMap();
        dateMap.put("lessonDate",lessonDate);
        List<WatchInfo> list = new ArrayList<WatchInfo>();
        try{
            ApplicationContext ctx = (ApplicationContext) context.getScheduler().getContext().get("applicationContext");
            watchInfoServiceImpl= (IWatchInfoService) ctx.getBean("watchInfoServiceImpl");
            companyLiveConfigServiceImpl= (ICompanyLiveConfigService) ctx.getBean("companyLiveConfigServiceImpl");
            sysKnowledgeTreeServiceImpl= (ISysKnowledgeTreeService) ctx.getBean("sysKnowledgeTreeServiceImpl");
            sysKnowledgeTreeStatisticsServiceImpl= (ISysKnowledgeTreeStatisticsService) ctx.getBean("sysKnowledgeTreeStatisticsServiceImpl");
            usersFrontServiceImpl= (IUsersFrontService) ctx.getBean("usersFrontServiceImpl");
            list = watchInfoServiceImpl.getLessonByDate(dateMap);
        }catch (Exception e){
            e.printStackTrace();
            log.info("初始化watchInfoServiceImpl和companyLiveConfigServiceImpl出现错误！");
        }

        Map<String,Object> map = new HashMap();
        CompanyLiveConfig config = companyLiveConfigServiceImpl.findByCompanyId(18113);
        String url ="";
        if(config==null){
            map.put("loginName", LiveRoomConstant.LOGIN_NAME);
            map.put("password",LiveRoomConstant.PASSWORD);
            url = LiveRoomConstant.DOMIN_NAME;

        }else{
            map.put("loginName", config.getLoginName());
            map.put("password",config.getPassword());
            url = config.getDomain();
        }

        map.put("startTime",lessonDate+" 00:00:00");
        map.put("endTime",lessonDate+" 23:59:59");
        for(WatchInfo lesson :list){
            map.put("roomId",lesson.getLiveroomId());
            String result = null;
            try {
                result = HttpPostRequest.post(url+"/integration/site/training/export/history",map);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(result);
            Gson g = new Gson();
            TestTask.LiveResult re =  g.fromJson(result,TestTask.LiveResult.class);
            if(!re.getCode().equals("0")){
                System.out.println(re.getMessage());
                continue;
            }
            //用户信息过滤并存入数据库
            for(TestTask.MessUser mUser : re.getList()){
                if(Long.valueOf(mUser.getUid())-1000000000<1000000000){
                    lesson.setJoinTime(mUser.getJoinTime());
                    lesson.setLeaveTime(mUser.getLeaveTime());
                    lesson.setUserId(Integer.parseInt(mUser.getUid())-1000000000);
                    lesson.setLessonId(lesson.getLessonId());
                    lesson.setWatchTime(Long.parseLong(mUser.getLeaveTime())-Long.parseLong(mUser.getJoinTime()));
                    lesson.setDevice(mUser.getDevice());
                    lesson.setId(null);
                    watchInfoServiceImpl.addWatchInfo(lesson);
                }
            }
        }
        Map queryDate = new HashMap();
        queryDate.put("queryDate",lessonDate);
        List<WatchInfo> sumWatchInfo = watchInfoServiceImpl.findSumInfoByDate(queryDate);
        for(WatchInfo info  : sumWatchInfo){
            try {
                setLiveKnowledgeTreeStaticis(info,info.getUserId());
            } catch (ParseException e) {
                log.info("树结构数据转换出错");
                e.printStackTrace();
            }
        }
        //获取前一天课次下所有课件
        log.info("获取昨天直播观看信息-----结束");
    }

    public void setLiveKnowledgeTreeStaticis (WatchInfo lesson,Integer userId) throws ParseException {
        //统计数据写入
        log.info("知识树统计数据写入-----开始");
        SysKnowledgeTree tree = new SysKnowledgeTree();
        tree.setLessonId(lesson.getLessonId());
        List<SysKnowledgeTree> nodes = sysKnowledgeTreeServiceImpl.findKnowledgeTreeByClass(tree);
        if(nodes.size()>0){
            SysKnowledgeTree node = nodes.get(0);
            //获取总时长
            Map map = new HashMap();
            map.put("lessonId",lesson.getLessonId());
            // Map classInfo = sysKnowledgeTreeStatisticsServiceImpl.findLessonInfo(map);

            //获取所有用户
            // List<UsersFront> users  = usersFrontServiceImpl.queryAll();

            map.put("userId",userId);
            List<SysKnowledgeTreeStatistics> list =  sysKnowledgeTreeStatisticsServiceImpl.findStatistics(map);
            if(list.size()>0){
                SysKnowledgeTreeStatistics obj = list.get(0);
                //计算观看比例
                if((float)(lesson.getWatchTime()/1000/60)>=30){
                    obj.setLiveFlag(2);
                }else{
                    obj.setLiveFlag(1);
                }
                sysKnowledgeTreeStatisticsServiceImpl.updateStatistics(obj);
            }else{
                SysKnowledgeTreeStatistics obj = new SysKnowledgeTreeStatistics();
                obj.setUserId(userId);
                obj.setKnowledgeTreeId(node.getId());
                obj.setCommodityId(node.getCommodityId());
                obj.setClasstypeId(node.getClasstypeId());
                obj.setLessonId(node.getLessonId());
                obj.setVideoFlag(0);
                obj.setVideoLectrueId(node.getCommodityIdHuikan());
                obj.setVideoWeikeFlag(0);
                obj.setVideoLectrueWeikeId(node.getCommodityIdWeike());
                obj.setPaperFlag(0);
                obj.setPaperId(node.getPaperId());

                if((float)(lesson.getWatchTime()/1000/60)>30){
                    obj.setLiveFlag(2);
                }else{
                    obj.setLiveFlag(1);
                }
                sysKnowledgeTreeStatisticsServiceImpl.addStatistics(obj);
            }
        }
    }
}
