package com.yuxin.wx.controller.task;

import com.google.gson.Gson;
import com.yuxin.wx.api.company.ICompanyLiveConfigService;
import com.yuxin.wx.api.company.ICompanyPayConfigService;
import com.yuxin.wx.api.system.ISysKnowledgeTreeService;
import com.yuxin.wx.api.system.ISysKnowledgeTreeStatisticsService;
import com.yuxin.wx.api.user.IUserHistoryService;
import com.yuxin.wx.api.user.IUsersFrontService;
import com.yuxin.wx.api.watchInfo.IWatchInfoService;
import com.yuxin.wx.common.LiveRoomConstant;
import com.yuxin.wx.model.company.CompanyLiveConfig;
import com.yuxin.wx.model.company.CompanyPayConfig;
import com.yuxin.wx.model.system.SysKnowledgeTree;
import com.yuxin.wx.model.system.SysKnowledgeTreeStatistics;
import com.yuxin.wx.model.watchInfo.WatchInfo;
import com.yuxin.wx.model.watchInfo.WatchInfoFromZSGet;
import com.yuxin.wx.model.watchInfo.WatchInfoFromZSResult;
import com.yuxin.wx.utils.HttpPostRequest;
import com.yuxin.wx.utils.MD5;
import com.yuxin.wx.vo.user.UserHistoryAllVo;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TimerTaskLiveRoomUsage extends QuartzJobBean implements Serializable {
    private Logger log = Logger.getLogger(getClass());
    private ICompanyLiveConfigService companyLiveConfigServiceImpl;
    private IWatchInfoService watchInfoServiceImpl;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("获取昨天直播并发信息-----执行时间：" + new Date());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DAY_OF_YEAR,-1);
        String lessonDate = sdf.format(ca.getTime());
        Map dateMap = new HashMap();
        dateMap.put("lessonDate",lessonDate);
        List<WatchInfo> lessonList = new ArrayList<WatchInfo>();
        try{
            ApplicationContext ctx = (ApplicationContext) context.getScheduler().getContext().get("applicationContext");
            companyLiveConfigServiceImpl= (ICompanyLiveConfigService) ctx.getBean("companyLiveConfigServiceImpl");
            watchInfoServiceImpl= (IWatchInfoService) ctx.getBean("watchInfoServiceImpl");
            lessonList = watchInfoServiceImpl.getLessonByDate(dateMap);
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


        for(WatchInfo lesson : lessonList){
            String theDate = sdf.format(lesson.getLessonDate());
            map.put("startTime",theDate+" 00:00:00");
            map.put("endTime",theDate+" 23:59:59");
            map.put("roomId",lesson.getLiveroomId());

            String result = null;
            try {
                result = com.yuxin.wx.utils.HttpPostRequest.post(url+"/integration/site/training/export/room/usage ",map);
            } catch (Exception e) {
                e.printStackTrace();
            }
            map.remove("startTime");
            map.remove("endTime");
            System.out.println(result);
            Gson g = new Gson();
            WatchInfoFromZSResult re =  g.fromJson(result,WatchInfoFromZSResult.class);
            List<WatchInfoFromZSGet> list  = re.getList();
            if(!re.getCode().equals("0")){
                System.out.println(re.getMessage());
                log.error(re.getMessage());
                continue;
            }else{
                if(re.getList().size()>0){
                    Collections.sort(list, new Comparator<WatchInfoFromZSGet>(){

                        /*
                         * int compare(Student o1, Student o2) 返回一个基本类型的整型，
                         * 返回负数表示：o1 小于o2，
                         * 返回0 表示：o1和o2相等，
                         * 返回正数表示：o1大于o2。
                         */
                        public int compare(WatchInfoFromZSGet o1, WatchInfoFromZSGet o2) {

                            //按照学生的年龄进行升序排列
                            if(o1.getMaxConcurrent() > o2.getMaxConcurrent()){
                                return -1;
                            }
                            if(o1.getMaxConcurrent() == o2.getMaxConcurrent()){
                                return 0;
                            }
                            return 1;
                        }
                    });
                    System.out.println(list.get(0).getMaxConcurrent());

                    watchInfoServiceImpl.addWatchInfoFromZSResult(list.get(0));

                }
            }
        }
        log.info("获取昨天直播并发信息-----结束");
    }
}
