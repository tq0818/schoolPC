package com.yuxin.wx.controller.task;

import com.google.gson.Gson;
import com.yuxin.wx.api.watchInfo.IWatchInfoService;
import com.yuxin.wx.common.LiveRoomConstant;
import com.yuxin.wx.model.classes.ClassModuleLesson;
import com.yuxin.wx.model.watchInfo.ClassRoomRelation;
import com.yuxin.wx.model.watchInfo.WatchInfo;
import com.yuxin.wx.utils.HttpPostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2017/10/19.
 */
@Controller
@RequestMapping("/watchInfo")
public class TestTask {
@Autowired
private IWatchInfoService watchInfoServiceImpl;
    @RequestMapping(value="/getInfo")
    public void test() throws Exception {
        //获取当日的课次
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lessonDate = sdf.format(date);
        List<WatchInfo> list = watchInfoServiceImpl.getLessonByDate(lessonDate);
        Map<String,Object> map = new HashMap();
        map.put("loginName", LiveRoomConstant.LOGIN_NAME);
        map.put("password",LiveRoomConstant.PASSWORD);
        map.put("startTime",lessonDate+" 00:00:00");
        map.put("endTime",lessonDate+" 23:59:59");
        for(WatchInfo lesson :list){
            map.put("roomId",lesson.getLiveroomId());
            String result = HttpPostRequest.post(LiveRoomConstant.DOMIN_NAME+"/integration/site/training/export/history",map);
            map.remove("startTime");
            map.remove("endTime");
            System.out.println(result);
            Gson g = new Gson();
            LiveResult re =  g.fromJson(result,LiveResult.class);
            if(!re.getCode().equals("200")){
                System.out.println(re.getMessage());
                continue;
            }
            //用户信息过滤并存入数据库
            for(MessUser mUser : re.getList()){
                if(Integer.parseInt(mUser.getUid())-1000000000>0){
                    lesson.setJoinTime(mUser.getJoinTime());
                    lesson.setLeaveTime(mUser.getLeaveTime());
                    lesson.setUserId(Integer.parseInt(mUser.getUid())-1000000000);
                    lesson.setLessonId(lesson.getId());
                    lesson.setWatchTime(Integer.parseInt(mUser.getLeaveTime())-Integer.parseInt(mUser.getJoinTime()));
                    watchInfoServiceImpl.addWatchInfo(lesson);
                }
            }
        }
        //获取前一天课次下所有课件
        Date beforeDate = sdf.parse(lessonDate);
        Calendar c = Calendar.getInstance();
        c.setTime(beforeDate);
        c.add(Calendar.DAY_OF_YEAR,-1);
        beforeDate = c.getTime();
        String before = sdf.format(beforeDate);
        List<WatchInfo> listBefor = watchInfoServiceImpl.getLessonByDate(lessonDate);
        for(WatchInfo lesson :listBefor){
            List<ClassRoomRelation> relations = new ArrayList<>();
            map.put("roomId",lesson.getLiveroomId());
            String result = HttpPostRequest.post(LiveRoomConstant.DOMIN_NAME+"/integration/site/training/export/history",map);
            System.out.println(result);
            Gson g = new Gson();
            Relation re =  g.fromJson(result,Relation.class);
            if(!re.getCode().equals("200")){
                System.out.println(re.getMessage());
                continue;
            }
            for (CourseWare course : re.getCoursewares()) {
                ClassRoomRelation relation = new ClassRoomRelation();
                relation.setClassId(course.getId());
                relation.setLiveroomId(course.getRoomId());
                relations.add(relation);
            }
            watchInfoServiceImpl.addClassRoomRelation(relations);
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
    public MessResult addWatchUser(Map<String,Object> map,List<WatchInfo> relations) throws Exception {
        String result = HttpPostRequest.post(LiveRoomConstant.DOMIN_NAME + "/integration/site/training/export/study/history", map);
        System.out.println(result);
        Gson g = new Gson();
        MessResult re = g.fromJson(result, MessResult.class);
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
                        relation.setWatchTime(Integer.parseInt(user.getLeaveTime()) - Integer.parseInt(user.getStartTime()));
                        watchInfoServiceImpl.addWatchInfo(relation);
                    }
                    break;
                }
            }
        }
        return re;
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
    class LiveResult{
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
    class MessUser{
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
}
