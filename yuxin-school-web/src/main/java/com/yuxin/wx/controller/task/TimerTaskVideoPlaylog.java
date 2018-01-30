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
import com.yuxin.wx.model.user.UsersFront;
import com.yuxin.wx.model.watchInfo.WatchInfo;
import com.yuxin.wx.utils.HttpPostRequest;
import com.yuxin.wx.utils.MD5;
import com.yuxin.wx.vo.user.UserHistoryAllVo;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TimerTaskVideoPlaylog extends QuartzJobBean implements Serializable {
    private Logger log = Logger.getLogger(getClass());
    private ICompanyPayConfigService companyPayConfigServiceImpl;
    private IUserHistoryService userHistoryServiceImpl;
    private ISysKnowledgeTreeStatisticsService sysKnowledgeTreeStatisticsServiceImpl;
    private ISysKnowledgeTreeService sysKnowledgeTreeServiceImpl;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        try{
            ApplicationContext ctx = (ApplicationContext) context.getScheduler().getContext().get("applicationContext");
            companyPayConfigServiceImpl= (ICompanyPayConfigService) ctx.getBean("companyPayConfigServiceImpl");
            userHistoryServiceImpl= (IUserHistoryService) ctx.getBean("userHistoryServiceImpl");
            sysKnowledgeTreeStatisticsServiceImpl= (ISysKnowledgeTreeStatisticsService) ctx.getBean("sysKnowledgeTreeStatisticsServiceImpl");
            sysKnowledgeTreeServiceImpl= (ISysKnowledgeTreeService) ctx.getBean("sysKnowledgeTreeServiceImpl");

            log.info("获取昨天录播观看信息-----执行时间：" + new Date());
            String a = "";
            long b = System.currentTimeMillis()/1000L;
            String infoUrl ="";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //查询出所有的机构
            List<Integer> comapnyIds=companyPayConfigServiceImpl.findAllCompanyId();
            if(comapnyIds!=null&&comapnyIds.size()>0){
            	for(Integer companyId:comapnyIds){
            		try{
			            CompanyPayConfig companyPayConfig = companyPayConfigServiceImpl.findByCompanyId(companyId);//暂时写死为数校公司id
			            Calendar c = Calendar.getInstance();
			            c.add(Calendar.DAY_OF_YEAR,-1);
			            Date date  = c.getTime();
			            addPlayLog(date,companyPayConfig,1,sdf);
            		}catch(Exception e){
            			e.printStackTrace();
            		}
            	}
            }
        }catch (Exception e){
            e.printStackTrace();
            log.info("初始化watchInfoServiceImpl和companyLiveConfigServiceImpl出现错误！");
        }
    }

    public void addPlayLog(Date date,CompanyPayConfig companyPayConfig,int index,SimpleDateFormat sdf){
        String a = "";
        String infoUrl ="";
        long b = System.currentTimeMillis()/1000L;
        a+="date="+ sdf.format(date);
        a +="&num_per_page=1000";
        a +="&page="+index;
        a +="&userid=" + companyPayConfig.getCcUserId();
        // map.put("time",b);
        a +="&time="+b;
        // map.put("salt","G162ODWstqL4ekW9c3lB56ikyWaVSIxb");
        infoUrl = a;
        a +="&salt=" + companyPayConfig.getCcApiKey();
        System.out.println(MD5.getMD5(a));
        infoUrl+="&hash="+MD5.getMD5(a);
//        map.put("hash",a);
        //System.out.println((c));
        String result = null;
        try {
            result = HttpPostRequest.get("http://spark.bokecc.com/api/playlog/user/v2?"+infoUrl);
            System.out.println(result);
            if(StringUtils.isNotEmpty(result)&&result.contains("INVALID_REQUEST")){
            	return;
            }
            Gson g = new Gson();
            TestTask.PlayLogsResult plre =  g.fromJson(result,TestTask.PlayLogsResult.class);
            System.out.println(plre.getPlay_logs().getPlay_log().size());
            List<TestTask.PlayLog> playLog = plre.getPlay_logs().getPlay_log();
            for(int n  = 0 ; n < playLog.size() ; n++){
                TestTask.PlayLog play = playLog.get(n);
                UserHistoryAllVo uha =new UserHistoryAllVo();
                if(play.getCustom_id().indexOf("null")!=-1 || play.getCustom_id().indexOf("NaN")!=-1){
                    continue;
                }
                String  [] info = play.getCustom_id().split("_");
                uha.setUserId(Integer.parseInt(info[0]));
                uha.setCommodityId(Integer.parseInt(info[1]));
                uha.setClassTypeId(Integer.parseInt(info[2]));
                uha.setLectureId(Integer.parseInt(info[3]));
                uha.setStudyLength(play.getPlay_duration());
                uha.setStudyTime(date);
                uha.setDevice(play.getDevice());
                userHistoryServiceImpl.insertPlayLogs(uha);
                setVideoKnowledgeTreeStaticis(uha.getCommodityId(),uha.getUserId(),uha.getStudyLength());
            }
            if(playLog.size()==1000){
                addPlayLog(date,companyPayConfig,index+1,sdf);
            }
            log.info("获取昨天录播观看信息-----结束");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setVideoKnowledgeTreeStaticis (Integer lectureId,Integer userId,Integer watchLength) throws ParseException {
        //统计数据写入
        log.info("知识树统计数据写入-----开始");
        SysKnowledgeTree tree = new SysKnowledgeTree();
        tree.setCommodityIdHuikan(lectureId);
        List<SysKnowledgeTree> nodes = sysKnowledgeTreeServiceImpl.findKnowledgeTreeByClass(tree);
        String flag = "huikan";
        if(nodes.size()==0){
            tree.setCommodityIdHuikan(null);
            tree.setCommodityIdWeike(lectureId);
            nodes = sysKnowledgeTreeServiceImpl.findKnowledgeTreeByClass(tree);
            flag="weike";
        }
        if(nodes.size()>0){
            SysKnowledgeTree node = nodes.get(0);
            //获取总时长
            Map map = new HashMap();
            map.put("id",lectureId);
            Map classInfo = sysKnowledgeTreeStatisticsServiceImpl.findLessonInfo(map);
            long total = (long)classInfo.get("times");
            //获取所有用户
            //List<UsersFront> users  = usersFrontServiceImpl.queryAll();


            map.clear();
            /*if(flag.equals("huikan")){
                map.put("videoLectrueId",lectureId);
            }
            else if(flag.equals("weike")){
                map.put("videoLectrueWeikeId",lectureId);
            }*/
            map.put("userId",userId);
            map.put("knowledgeTreeId",node.getId());
            List<SysKnowledgeTreeStatistics> list =  sysKnowledgeTreeStatisticsServiceImpl.findStatistics(map);
            if(list.size()>0){
                SysKnowledgeTreeStatistics obj = list.get(0);
                //计算观看比例
                if(flag.equals("huikan")){
                    obj.setVideoLectrueId(lectureId);
                    if((float)(watchLength/total)>=0.7){
                        obj.setVideoFlag(2);
                    }else{
                        obj.setVideoFlag(1);
                    }
                } else if(flag.equals("weike")){
                    obj.setVideoLectrueWeikeId(lectureId);
                    if((float)(watchLength/total)>=0.8){
                        obj.setVideoWeikeFlag(2);
                    }else{
                        obj.setVideoWeikeFlag(1);
                    }
                }

                sysKnowledgeTreeStatisticsServiceImpl.updateStatistics(obj);
            }else{
                SysKnowledgeTreeStatistics obj = new SysKnowledgeTreeStatistics();
                obj.setUserId(userId);
                obj.setCommodityId(node.getCommodityId());
                obj.setClasstypeId(node.getClasstypeId());
                obj.setKnowledgeTreeId(node.getId());
                obj.setLessonId(node.getLessonId());
                obj.setVideoLectrueId(node.getCommodityIdHuikan());
                obj.setVideoLectrueWeikeId(node.getCommodityIdWeike());
                obj.setPaperFlag(0);
                obj.setPaperId(node.getPaperId());
                obj.setLiveFlag(0);
                if(flag.equals("huikan")){
                    obj.setVideoWeikeFlag(0);
                    if((float)(watchLength/total)>=0.7){
                        obj.setVideoFlag(2);
                    }else{
                        obj.setVideoFlag(1);
                    }
                } else if(flag.equals("weike")){
                    obj.setVideoFlag(0);
                    if((float)(watchLength/total)>=0.8){
                        obj.setVideoWeikeFlag(2);
                    }else{
                        obj.setVideoWeikeFlag(1);
                    }
                }
                sysKnowledgeTreeStatisticsServiceImpl.addStatistics(obj);
            }

        }
    }
}
