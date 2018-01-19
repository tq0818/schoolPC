package com.yuxin.wx.controller.task;

import com.google.gson.Gson;
import com.yuxin.wx.api.classes.IClassModuleLessonService;
import com.yuxin.wx.api.company.ICompanyLiveConfigService;
import com.yuxin.wx.api.company.ICompanyPayConfigService;
import com.yuxin.wx.api.system.ISysKnowledgeTreeService;
import com.yuxin.wx.api.system.ISysKnowledgeTreeStatisticsService;
import com.yuxin.wx.api.user.IUserHistoryService;
import com.yuxin.wx.api.user.IUsersFrontService;
import com.yuxin.wx.api.watchInfo.IWatchInfoService;
import com.yuxin.wx.common.LiveRoomConstant;
import com.yuxin.wx.model.classes.ClassModuleLesson;
import com.yuxin.wx.model.company.CompanyLiveConfig;
import com.yuxin.wx.model.company.CompanyPayConfig;
import com.yuxin.wx.model.system.SysKnowledgeTree;
import com.yuxin.wx.model.system.SysKnowledgeTreeStatistics;
import com.yuxin.wx.model.user.UsersFront;
import com.yuxin.wx.model.watchInfo.ClassRoomRelation;
import com.yuxin.wx.model.watchInfo.WatchInfo;
import com.yuxin.wx.model.watchInfo.WatchInfoFromZSGet;
import com.yuxin.wx.model.watchInfo.WatchInfoFromZSResult;
import com.yuxin.wx.utils.HttpPostRequest;
import com.yuxin.wx.utils.MD5;
import com.yuxin.wx.vo.user.UserHistoryAllVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2017/10/19.
 */
@Controller
/*@Component
@Transactional*/
@RequestMapping("/watchInfo")
public class TestTask {
    @Autowired
    private IWatchInfoService watchInfoServiceImpl;
    @Autowired
    private IUserHistoryService userHistoryServiceImpl;
    @Autowired
    private ICompanyPayConfigService companyPayConfigServiceImpl;
    @Autowired
    private ICompanyLiveConfigService companyLiveConfigServiceImpl;
    @Autowired
    private ISysKnowledgeTreeService sysKnowledgeTreeServiceImpl;
    @Autowired
    private IClassModuleLessonService classModuleLessonServiceImpl;
    @Autowired
    private IUsersFrontService usersFrontServiceImpl;
    @Autowired
    private ISysKnowledgeTreeStatisticsService  sysKnowledgeTreeStatisticsServiceImpl;
    private Log log = LogFactory.getLog("log");
    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

    @RequestMapping(value="/getInfo")
//    @Scheduled(cron = "0 0/5 * * * ?") //4小时(参数分别为:秒、分、时、日期、月份、星期、年)0 0 0/4 * * ?
    public void test() {
        //获取当日的课次
//        Date date = new Date();
//        date.setTime(date.getTime()-(3600*24*1000));
        log.info("获取昨天直播观看信息-----执行时间：" + new Date());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DAY_OF_YEAR,-2);
        //ca.set(Calendar.MONTH,7);
        ca.add(Calendar.DAY_OF_MONTH,-1);
        String lessonDate = sdf.format(ca.getTime());
        Map dateMap = new HashMap();
        dateMap.put("lessonDate",lessonDate);
        List<WatchInfo> list = watchInfoServiceImpl.getLessonByDate(dateMap);
        Map<String,Object> map = new HashMap();
        CompanyLiveConfig config = companyLiveConfigServiceImpl.findByCompanyId(18113);
        String url ="";
       // if(config==null){
            map.put("loginName", LiveRoomConstant.LOGIN_NAME);
            map.put("password",LiveRoomConstant.PASSWORD);
            url = LiveRoomConstant.DOMIN_NAME;

       // }else{
         //   map.put("loginName", config.getLoginName());
        //    map.put("password",config.getPassword());
      //     url = config.getDomain();
       // }

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
            LiveResult re =  g.fromJson(result,LiveResult.class);
            if(!re.getCode().equals("0")){
                System.out.println(re.getMessage());
                continue;
            }
            //用户信息过滤并存入数据库
            for(MessUser mUser : re.getList()){
                if(Long.valueOf(mUser.getUid())-1000000000<1000000000){
                    lesson.setJoinTime(mUser.getJoinTime());
                    lesson.setLeaveTime(mUser.getLeaveTime());
                    lesson.setUserId(Integer.parseInt(mUser.getUid())-1000000000);
                    lesson.setLessonId(lesson.getLessonId());
                    lesson.setWatchTime(Long.parseLong(mUser.getLeaveTime())-Long.parseLong(mUser.getJoinTime()));
                    lesson.setDevice(mUser.getDevice());
                    lesson.setId(null);
                   // watchInfoServiceImpl.addWatchInfo(lesson);
                    try {
                        setLiveKnowledgeTreeStaticis(lesson,lesson.getUserId());
                    } catch (ParseException e) {
                        log.info("树结构数据转换出错");
                        e.printStackTrace();
                    }
                }
            }



        }
        //获取前一天课次下所有课件
        log.info("获取昨天直播观看信息-----结束");


        //getWatchInfoForClass(sdf,lessonDate,map);




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

    //获取回看记录
    public void getWatchInfoForClass(SimpleDateFormat sdf,String lessonDate, Map<String,Object> map){
        //获取前一天课次下所有课件
        Date beforeDate = null;
        try {
            beforeDate = sdf.parse(lessonDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_YEAR,-1);
        beforeDate = c.getTime();
        String before = sdf.format(beforeDate);
        Map dateMap = new HashMap();
        dateMap.put("lessonDate",lessonDate);
        List<WatchInfo> listBefor = watchInfoServiceImpl.getLessonByDate(dateMap);
        for(WatchInfo lesson :listBefor){
            List<ClassRoomRelation> relations = new ArrayList<>();
            map.put("roomId",lesson.getLiveroomId());
            String result = null;
            try {
                result = HttpPostRequest.post(LiveRoomConstant.DOMIN_NAME+"/integration/site/training/courseware/list",map);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(result);
            Gson g = new Gson();
            Relation re =  g.fromJson(result,Relation.class);
            if(!re.getCode().equals("0")){
                System.out.println(re.getMessage());
                continue;
            }
            if( re.getCoursewares()!=null){
                for (CourseWare course : re.getCoursewares()) {
                    ClassRoomRelation relation = new ClassRoomRelation();
                    relation.setClassId(course.getId());
                    relation.setLiveroomId(course.getRoomId());
                    watchInfoServiceImpl.addClassRoomRelation(relation);
                }
            }
        }
        //获取所有的 课次 课件 关系
        List<WatchInfo> relations = watchInfoServiceImpl.getClassRoomRelation(new ClassRoomRelation());
        //获取当天观看课件的所有用户
        map.remove("roomId");
        map.put("date",lessonDate+" 00:00:00");
        MessResult re = addWatchUser(map,relations);
        if(re.getPage().getPageNo()<re.getPage().getTotalPages()){
            for(int n = 2 ; n<=re.getPage().totalPages ; n++){
                map.put("pageNo",n);
                addWatchUser(map,relations);
            }
        }
    }


    //获取前一天录播观看个人信息
    @RequestMapping(value="/getPlayInfo")
//    @Scheduled(cron = "0 0 8 * * ?") //4小时(参数分别为:秒、分、时、日期、月份、星期、年)0 0 0/4 * * ?
    public void getPlayInfo() {
        log.info("获取昨天录播观看信息-----执行时间：" + new Date());
        String a = "";
        long b = System.currentTimeMillis()/1000L;
        String infoUrl ="";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        CompanyPayConfig companyPayConfig = companyPayConfigServiceImpl.findByCompanyId(18113);//暂时写死为数校公司id


        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_YEAR,-1);
        Date date  = c.getTime();

        addPlayLog(date,companyPayConfig,1,sdf);
//        map.put("date","2017-10-23");
        // a +="date=2017-10-23";
        //map.put("userid","7EFA9ED6F0ABB8DD");
        //a+="date="+ sdf.format(date);
        //a +="&num_per_page=1000";
        //a +="&page=1";
        //a +="&userid=" + companyPayConfig.getCcUserId();
        // map.put("time",b);
        //a +="&time="+b;
        // map.put("salt","G162ODWstqL4ekW9c3lB56ikyWaVSIxb");
        //infoUrl = a;
        /*a +="&salt=" + companyPayConfig.getCcApiKey();
        System.out.println(MD5.getMD5(a));
        infoUrl+="&hash="+MD5.getMD5(a);*/
//        map.put("hash",a);
       /* System.out.println((c));
        String result = null;
        try {
            result = HttpPostRequest.get("http://spark.bokecc.com/api/playlog/user/v2?"+infoUrl);
            System.out.println(result);
            Gson g = new Gson();
            PlayLogsResult plre =  g.fromJson(result,PlayLogsResult.class);
            System.out.println(plre.getPlay_logs().getPlay_log().size());
            List<PlayLog> playLog = plre.getPlay_logs().getPlay_log();
            for(int n  = 0 ; n < playLog.size() ; n++){
                PlayLog  play = playLog.get(n);
                UserHistoryAllVo uha =new UserHistoryAllVo();
                String  [] info = play.getCustom_id().split("_");
                uha.setUserId(Integer.parseInt(info[0]));
                uha.setCommodityId(Integer.parseInt(info[1]));
                uha.setClassTypeId(Integer.parseInt(info[2]));
                uha.setLectureId(Integer.parseInt(info[3]));
                uha.setStudyLength(play.getPlay_duration());
                uha.setStudyTime(date);
                uha.setDevice(play.getDevice());
                userHistoryServiceImpl.insertPlayLogs(uha);
            }
            log.info("获取昨天录播观看信息-----结束");
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }



    //获取前一天课次历史并发记录
//    @Scheduled(cron = "0 0 1 * * ?") //4小时(参数分别为:秒、分、时、日期、月份、星期、年)0 0 0/4 * * ?
    public void getWatchInfoHistory(){
        log.info("获取昨天直播并发信息-----执行时间：" + new Date());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DAY_OF_YEAR,-1);
        String lessonDate = sdf.format(ca.getTime());
        Map dateMap = new HashMap();
        dateMap.put("lessonDate",lessonDate);
        List<WatchInfo> lessonList = watchInfoServiceImpl.getLessonByDate(dateMap);


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



    public MessResult addWatchUser(Map<String,Object> map,List<WatchInfo> relations)  {
        String result = null;
        try {
            result = HttpPostRequest.post(LiveRoomConstant.DOMIN_NAME + "/integration/site/training/export/study/history", map);

            System.out.println(result);
            Gson g = new Gson();
            MessResult re = g.fromJson(result, MessResult.class);
            if(!re.getCode().equals(0)){
                log.info(re.getMessage());
                return null;
            }
            if (re.getPage() != null && re.getList() != null) {
                //剔除所有roomId 并与uid对应
                for (int n = 0; n < re.getList().size(); n++) {
                    WatchUser user = re.getList().get(n);
                    for (int m = 0; m < relations.size(); m++) {
                        WatchInfo relation = relations.get(m);
                        if (user.getId().equals(relation.getClassId()) && user.getUid() - 1000000000 > 0) {
                            relation.setJoinTime(user.getStartTime());
                            relation.setLeaveTime(user.getLeaveTime());
                            relation.setUserId(user.getUid() - 1000000000);
                            relation.setWatchTime(Long.parseLong(user.getLeaveTime()) - Long.parseLong(user.getStartTime()));
                            watchInfoServiceImpl.addWatchInfo(relation);
                            relation.setId(null);
                        }
                        break;
                    }
                }
            }
            return re;
        } catch (Exception e) {
            log.info(e.getMessage());
            e.printStackTrace();
        }
        return null;
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
            Gson g = new Gson();
            PlayLogsResult plre =  g.fromJson(result,PlayLogsResult.class);
            System.out.println(plre.getPlay_logs().getPlay_log().size());
            List<PlayLog> playLog = plre.getPlay_logs().getPlay_log();
            for(int n  = 0 ; n < playLog.size() ; n++){
                PlayLog  play = playLog.get(n);
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
                //userHistoryServiceImpl.insertPlayLogs(uha);
                setVideoKnowledgeTreeStaticis(uha.getLectureId(),uha.getUserId(),uha.getStudyLength());
            }
            if(playLog.size()==1000){
                addPlayLog(date,companyPayConfig,index+1,sdf);
            }

            log.info("获取昨天录播观看信息-----结束");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//回看信息获取
    class MessResult{
        private String code ;
        private List<WatchUser> list;
        private String message;
        private Page page;
        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public List<WatchUser> getList() {
            return list;
        }

        public void setList(List<WatchUser> list) {
            this.list = list;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Page getPage() {
            return page;
        }

        public void setPage(Page page) {
            this.page = page;
        }
    }
    //直播信息结果
    public class  LiveResult{
        private String code ;
        private List<MessUser> list;
        private String message;
        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public List<MessUser> getList() {
            return list;
        }

        public void setList(List<MessUser> list) {
            this.list = list;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    }

    class Page{
        private Integer pageNo;
        private Integer totalCounts;
        private Integer totalPages;

        public Integer getPageNo() {
            return pageNo;
        }

        public void setPageNo(Integer pageNo) {
            this.pageNo = pageNo;
        }

        public Integer getTotalCounts() {
            return totalCounts;
        }

        public void setTotalCounts(Integer totalCounts) {
            this.totalCounts = totalCounts;
        }

        public Integer getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(Integer totalPages) {
            this.totalPages = totalPages;
        }
    }
    //直播用户信息
    public class MessUser{
        private String area;
        private String company;
        private String device;
        private String ip;
        private String joinTime;
        private String leaveTime;
        private String mobile;
        private  String nickname;
        private String uid;

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }


        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }



        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getDevice() {
            return device;
        }

        public void setDevice(String device) {
            this.device = device;
        }

        public String getJoinTime() {
            return joinTime;
        }

        public void setJoinTime(String joinTime) {
            this.joinTime = joinTime;
        }

        public String getLeaveTime() {
            return leaveTime;
        }

        public void setLeaveTime(String leaveTime) {
            this.leaveTime = leaveTime;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }
    }
    //回看用户信息
    class WatchUser{
        private String id;
        private Integer uid;
        private String startTime;
        private String leaveTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Integer getUid() {
            return uid;
        }

        public void setUid(Integer uid) {
            this.uid = uid;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getLeaveTime() {
            return leaveTime;
        }

        public void setLeaveTime(String leaveTime) {
            this.leaveTime = leaveTime;
        }
    }
    class Relation{
        private String code;
        private String message;
        private List<CourseWare> coursewares;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public List<CourseWare> getCoursewares() {
            return coursewares;
        }

        public void setCoursewares(List<CourseWare> coursewares) {
            this.coursewares = coursewares;
        }
    }
    class CourseWare{
        private String id;
        private String createdTime;
        private String number;
        private String subject;
        private String token;
        private String url;
        private String roomId;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCreatedTime() {
            return createdTime;
        }

        public void setCreatedTime(String createdTime) {
            this.createdTime = createdTime;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getRoomId() {
            return roomId;
        }

        public void setRoomId(String roomId) {
            this.roomId = roomId;
        }
    }


    //录播观看记录
    public class  PlayLogsResult{
        private PlayLogs play_logs;

        public PlayLogs getPlay_logs() {
            return play_logs;
        }

        public void setPlay_logs(PlayLogs play_logs) {
            this.play_logs = play_logs;
        }
    }
    public class PlayLogs{
    private Integer total;
    private List<PlayLog> play_log;

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }

        public List<PlayLog> getPlay_log() {
            return play_log;
        }

        public void setPlay_log(List<PlayLog> play_log) {
            this.play_log = play_log;
        }
    }
    public class PlayLog{
        private String custom_id;
        private Integer play_duration;
        private String device;

        public String getCustom_id() {
            return custom_id;
        }

        public String getDevice() {
            return device;
        }

        public void setDevice(String device) {
            this.device = device;
        }

        public void setCustom_id(String custom_id) {
            this.custom_id = custom_id;
        }

        public Integer getPlay_duration() {
            return play_duration;
        }

        public void setPlay_duration(Integer play_duration) {
            this.play_duration = play_duration;
        }
    }

    /*public static void main(String[] arg){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar ca = Calendar.getInstance();
        //ca.set(Calendar.MONTH,7);
        ca.add(Calendar.DAY_OF_MONTH,-1);
        String lessonDate = sdf.format(ca.getTime());
        Map<String,Object> map = new HashMap();
        String url ="";
        map.put("loginName", LiveRoomConstant.LOGIN_NAME);
        map.put("password",LiveRoomConstant.PASSWORD);
        url = LiveRoomConstant.DOMIN_NAME;


        map.put("startTime","2017-11-15 00:00:00");
        map.put("endTime","2017-11-15 23:59:59");
        map.put("roomId","pS8MSqCml4");
        String result = null;
        try {
            result = HttpPostRequest.post(url+"/integration/site/training/export/history",map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.remove("startTime");
        map.remove("endTime");
        System.out.println(result);
        Gson g = new Gson();
        LiveResult re =  g.fromJson(result,LiveResult.class);
        if(!re.getCode().equals("0")){
            System.out.println(re.getMessage());
        }else{
            System.out.println(re.getList().size());
        }

int  n = 0 ;
        for(MessUser mUser : re.getList()){
            if(Long.valueOf(mUser.getUid())-1000000000<1000000000){
                    n++;
            }
        }
        System.out.println(n);
    }*/



    //录播数据获取测试
    public static void main(String[] arg) throws Exception {
        String a = "";
        long b = System.currentTimeMillis() / 1000L;
        String c = "";
        a += "date=2017-11-14";
        a += "&num_per_page=1000";
        a += "&userid=7EFA9ED6F0ABB8DD";
        a += "&time=" + b;
        c = a;
        a += "&salt=G162ODWstqL4ekW9c3lB56ikyWaVSIxb";
        System.out.println(MD5.getMD5(a));
        c += "&hash=" + MD5.getMD5(a);
        System.out.println((c));
        String result = HttpPostRequest.get("http://spark.bokecc.com/api/playlog/user/v2?" + c);
        System.out.println(result);
        Gson g = new Gson();
        PlayLogsResult re = g.fromJson(result, PlayLogsResult.class);
        System.out.println(re.getPlay_logs().getPlay_log().size());
        List<PlayLog> playLog = re.getPlay_logs().getPlay_log();
        for (int n = 0; n < playLog.size(); n++) {
            PlayLog play = playLog.get(n);
            UserHistoryAllVo uha = new UserHistoryAllVo();
            if(play.getCustom_id().indexOf("null")!=-1){
                continue;
            }
            String[] info = play.getCustom_id().split("_");
            uha.setUserId(Integer.parseInt(info[0]));
            uha.setCommodityId(Integer.parseInt(info[1]));
            uha.setClassTypeId(Integer.parseInt(info[2]));
            uha.setLectureId(Integer.parseInt(info[3]));
            uha.setStudyLength(play.getPlay_duration());
        }
    }
    //课堂历史并发数据获取
    /*public  static void main(String[] arg){
        //获取当日的课次
//        Date date = new Date();
//        date.setTime(date.getTime()-(3600*24*1000));
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //Calendar ca = Calendar.getInstance();
        //ca.set(Calendar.MONTH,7);
        //ca.add(Calendar.DAY_OF_MONTH,-1);
        //String lessonDate = sdf.format(ca.getTime());
        //List<WatchInfo> list = watchInfoServiceImpl.getLessonByDate(lessonDate);
        Map<String,Object> map = new HashMap();
        map.put("loginName", LiveRoomConstant.LOGIN_NAME);
        map.put("password",LiveRoomConstant.PASSWORD);
        map.put("startTime","2017-9-20 00:00:00");
        map.put("endTime","2017-9-25 00:00:00");
           // map.put("roomId",lesson.getLiveroomId());
            String result = null;
            try {
                result = HttpPostRequest.post(LiveRoomConstant.DOMIN_NAME+"/integration/site/training/export/room/usage ",map);
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
               // System.out.println(re.getMessage());
                //continue;
            }else{
            if(re.getList().size()>0){
                Collections.sort(list, new Comparator<WatchInfoFromZSGet>(){

                    *//*
                     * int compare(Student o1, Student o2) 返回一个基本类型的整型，
                     * 返回负数表示：o1 小于o2，
                     * 返回0 表示：o1和o2相等，
                     * 返回正数表示：o1大于o2。
                     *//*
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
            }
        }
            //用户信息过滤并存入数据库


    }*/

}
