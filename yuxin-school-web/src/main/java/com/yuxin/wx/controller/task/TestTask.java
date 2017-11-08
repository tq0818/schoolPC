package com.yuxin.wx.controller.task;

import com.google.gson.Gson;
import com.yuxin.wx.api.company.ICompanyPayConfigService;
import com.yuxin.wx.api.user.IUserHistoryService;
import com.yuxin.wx.api.watchInfo.IWatchInfoService;
import com.yuxin.wx.common.LiveRoomConstant;
import com.yuxin.wx.model.classes.ClassModuleLesson;
import com.yuxin.wx.model.company.CompanyPayConfig;
import com.yuxin.wx.model.watchInfo.ClassRoomRelation;
import com.yuxin.wx.model.watchInfo.WatchInfo;
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
//@Controller
@Component
@Transactional
//@RequestMapping("/watchInfo")
public class TestTask {
    @Autowired
    private IWatchInfoService watchInfoServiceImpl;
    @Autowired
    private IUserHistoryService userHistoryServiceImpl;
    @Autowired
    private ICompanyPayConfigService companyPayConfigServiceImpl;

    private Log log = LogFactory.getLog("log");

//    @RequestMapping(value="/getInfo")
    @Scheduled(cron = "0 0 8 * * ?") //4小时(参数分别为:秒、分、时、日期、月份、星期、年)0 0 0/4 * * ?
    public void test() {
        //获取当日的课次
//        Date date = new Date();
//        date.setTime(date.getTime()-(3600*24*1000));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar ca = Calendar.getInstance();
        //ca.set(Calendar.MONTH,7);
        ca.add(Calendar.DAY_OF_MONTH,-1);
        String lessonDate = sdf.format(ca.getTime());
        List<WatchInfo> list = watchInfoServiceImpl.getLessonByDate(lessonDate);
        Map<String,Object> map = new HashMap();
        map.put("loginName", LiveRoomConstant.LOGIN_NAME);
        map.put("password",LiveRoomConstant.PASSWORD);
        map.put("startTime",lessonDate+" 00:00:00");
        map.put("endTime",lessonDate+" 23:59:59");
        for(WatchInfo lesson :list){
            map.put("roomId",lesson.getLiveroomId());
            String result = null;
            try {
                result = HttpPostRequest.post(LiveRoomConstant.DOMIN_NAME+"/integration/site/training/export/history",map);
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
                    watchInfoServiceImpl.addWatchInfo(lesson);
                }
            }
        }
        //获取前一天课次下所有课件
        Date beforeDate = null;
        try {
            beforeDate = sdf.parse(lessonDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(beforeDate);
        ca.add(Calendar.DAY_OF_YEAR,-1);
        beforeDate = ca.getTime();
        String before = sdf.format(beforeDate);
        List<WatchInfo> listBefor = watchInfoServiceImpl.getLessonByDate(lessonDate);
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
//    @RequestMapping(value="/getPlayInfo")
    @Scheduled(cron = "0 0 8 * * ?") //4小时(参数分别为:秒、分、时、日期、月份、星期、年)0 0 0/4 * * ?
    public void getPlayInfo() {
        String a = "";
        long b = System.currentTimeMillis()/1000L;
        String infoUrl ="";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ss");
        CompanyPayConfig companyPayConfig = companyPayConfigServiceImpl.findByCompanyId(18113);//暂时写死为数校公司id
       Calendar c = Calendar.getInstance();
       c.add(Calendar.DAY_OF_YEAR,-1);
        Date date  = c.getTime();
//        map.put("date","2017-10-23");
        // a +="date=2017-10-23";
        //map.put("userid","7EFA9ED6F0ABB8DD");
        a+="date="+ sdf.format(date);
        a +="&num_per_page=1000";
        a +="&userid=" + companyPayConfig.getCcUserId();
        // map.put("time",b);
        a +="&time="+b;
        // map.put("salt","G162ODWstqL4ekW9c3lB56ikyWaVSIxb");
        infoUrl = a;
        a +="&salt=" + companyPayConfig.getCcApiKey();
        System.out.println(MD5.getMD5(a));
        infoUrl+="&hash="+MD5.getMD5(a);
//        map.put("hash",a);
        System.out.println((c));
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
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    public void addPlayLog(){

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
    class PlayLogsResult{
        private PlayLogs play_logs;

        public PlayLogs getPlay_logs() {
            return play_logs;
        }

        public void setPlay_logs(PlayLogs play_logs) {
            this.play_logs = play_logs;
        }
    }
    class PlayLogs{
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
    class PlayLog{
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
    public static void main(String[] arg) throws Exception {
        Map<String,Object> map = new HashMap();
//         PropertiesUtil util = new PropertiesUtil();
//        map.put("loginName",util.getZsLoginName());
//        map.put("password",util.getZsPassWord());
//
//        map.put("roomId","sXrgZ8I92n");
//        map.put("startDate","2017-08-24 00:17:16");
        String a = "";
        long b = System.currentTimeMillis()/1000L;
        String c ="";
//        map.put("date","2017-10-23");
        // a +="date=2017-10-23";
        //map.put("userid","7EFA9ED6F0ABB8DD");
        a+="date=2017-10-31";
        a +="&num_per_page=1000";
        a +="&userid=7EFA9ED6F0ABB8DD";
        // map.put("time",b);
        a +="&time="+b;
        // map.put("salt","G162ODWstqL4ekW9c3lB56ikyWaVSIxb");
        c = a;
        a +="&salt=G162ODWstqL4ekW9c3lB56ikyWaVSIxb";
        System.out.println(MD5.getMD5(a));
        c+="&hash="+MD5.getMD5(a);
//        map.put("hash",a);
        System.out.println((c));
        String result = HttpPostRequest.get("http://spark.bokecc.com/api/playlog/user/v2?"+c);
        System.out.println(result);
        Gson g = new Gson();
        PlayLogsResult re =  g.fromJson(result,PlayLogsResult.class);
        System.out.println(re.getPlay_logs().getPlay_log().size());
        List<PlayLog> playLog = re.getPlay_logs().getPlay_log();
        for(int n  = 0 ; n < playLog.size() ; n++){
            PlayLog  play = playLog.get(n);
            UserHistoryAllVo uha =new UserHistoryAllVo();
            String  [] info = play.getCustom_id().split("_");
            uha.setUserId(Integer.parseInt(info[0]));
            uha.setCommodityId(Integer.parseInt(info[1]));
            uha.setClassTypeId(Integer.parseInt(info[2]));
            uha.setLectureId(Integer.parseInt(info[3]));
            uha.setStudyLength(play.getPlay_duration());
//            uha.setStudyTime();
        }


    }


}
