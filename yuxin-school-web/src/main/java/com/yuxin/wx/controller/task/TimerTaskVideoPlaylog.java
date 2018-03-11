package com.yuxin.wx.controller.task;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.google.gson.Gson;
import com.yuxin.wx.api.company.ICompanyPayConfigService;
import com.yuxin.wx.api.system.ISysKnowledgeTreeService;
import com.yuxin.wx.api.system.ISysKnowledgeTreeStatisticsService;
import com.yuxin.wx.api.user.IUserHistoryService;
import com.yuxin.wx.model.company.CompanyPayConfig;
import com.yuxin.wx.model.system.SysKnowledgeTree;
import com.yuxin.wx.model.system.SysKnowledgeTreeStatistics;
import com.yuxin.wx.model.user.SysPlayLogsVo;
import com.yuxin.wx.util.HttpPostRequest;
import com.yuxin.wx.utils.MD5;
import com.yuxin.wx.vo.user.UserHistoryAllVo;

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
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_YEAR,-1);
            Date date  = c.getTime();
            if(comapnyIds!=null&&comapnyIds.size()>0){
            	for(Integer companyId:comapnyIds){
            		try{
			            CompanyPayConfig companyPayConfig = companyPayConfigServiceImpl.findByCompanyId(companyId);
			            addPlayLog(date,companyPayConfig,1,sdf);
            		}catch(Exception e){
            			e.printStackTrace();
            		}
            	}
            }
            //获取用户在APP的观看点播数据到2B中汇总
            String mode=companyPayConfigServiceImpl.findGetAPPDateMode();
            synchronizedAppUserHistory(date,mode);
            log.info("获取昨天录播观看信息-----结束");
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
            if(StringUtils.isNotEmpty(result)&&result.contains("INVALID_REQUEST")){
            	return;
            }
            Gson g = new Gson();
            TestTask.PlayLogsResult plre =  g.fromJson(result,TestTask.PlayLogsResult.class);
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
                setVideoKnowledgeTreeStaticis(uha.getCommodityId(),uha.getUserId(),uha.getStudyLength(),uha.getLectureId());
            }
            if(playLog.size()==1000){
                addPlayLog(date,companyPayConfig,index+1,sdf);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //同步用户在app上看的直播数据
    public void synchronizedAppUserHistory(Date date,String mode){
    	//从app处获取学生观看录播数据并插入到sys_play_logs表中
        //1、查询所有相关数据
        List<SysPlayLogsVo> sysPlayLogsVos=userHistoryServiceImpl.querySysPlayLogsVosByDate(date,mode);
        //2、根据sys_play_logs标识判断是更新还是插入
        if(sysPlayLogsVos!=null&&sysPlayLogsVos.size()>0){
        	for(SysPlayLogsVo sysPlayLogsVo:sysPlayLogsVos){
        		if(sysPlayLogsVo.getSplId()!=null){
        			//更新
        			try{
        				userHistoryServiceImpl.updatePlayLogs(sysPlayLogsVo);
        			}catch(Exception e){
        				//如果更新失败，则记录该数据
        				log.error("数据更新失败，id为"+sysPlayLogsVo.getSourceId(),e);
        			}
        		}else{
        			//插入
        			try{
        				userHistoryServiceImpl.insertPlayLogs(sysPlayLogsVo);
        			}catch(Exception e){
        				//如果插入失败，则记录该数据
        				log.error("数据插入失败，id为"+sysPlayLogsVo.getSourceId(),e);
        			}
        		}
        		//更新知识树数据
        		try{
        			setVideoKnowledgeTreeStaticis(sysPlayLogsVo.getCommodityId()==null?null:Integer.valueOf(sysPlayLogsVo.getCommodityId()),
        					sysPlayLogsVo.getUserId()==null?null:Integer.valueOf(sysPlayLogsVo.getUserId()),
        					sysPlayLogsVo.getStudyLength()==null?null:Integer.valueOf(sysPlayLogsVo.getStudyLength()),
        					sysPlayLogsVo.getLectureId()==null?null:Integer.valueOf(sysPlayLogsVo.getLectureId()));
        		}catch(Exception e){
        			e.printStackTrace();
        		}
        	}
        }
    }
    public void setVideoKnowledgeTreeStaticis (Integer commodityId,Integer userId,Integer watchLength,Integer lectureId) throws ParseException {
        //统计数据写入
        log.info("知识树统计数据写入-----开始");
        SysKnowledgeTree tree = new SysKnowledgeTree();
        tree.setCommodityIdHuikan(commodityId);
        List<SysKnowledgeTree> nodes = sysKnowledgeTreeServiceImpl.findKnowledgeTreeByClass(tree);
        String flag = "huikan";
        if(nodes.size()==0){
            tree.setCommodityIdHuikan(null);
            tree.setCommodityIdWeike(commodityId);
            nodes = sysKnowledgeTreeServiceImpl.findKnowledgeTreeByClass(tree);
            flag="weike";
        }
        if(nodes.size()>0){
            SysKnowledgeTree node = nodes.get(0);
            //获取总时长
            Map map = new HashMap();
            map.put("id",commodityId);
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
