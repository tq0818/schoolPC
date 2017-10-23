package com.yuxin.wx.scheduled;



import com.google.gson.Gson;
import com.yuxin.wx.common.LiveRoomConstant;
import com.yuxin.wx.util.HttpPostRequest;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by Administrator on 2017/10/17.
 */
@Component
public class TestMessg {

    public  static void main(String[] arg) throws Exception {
        //设置查询时间
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_YEAR,-1);
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        Date liveDate = c.getTime();
        //获取当日 课次




        Map<String,Object> map = new HashMap();
        map.put("loginName", LiveRoomConstant.LOGIN_NAME);
        map.put("password",LiveRoomConstant.PASSWORD);



        map.put("roomId","sXrgZ8I92n");
//        map.put("startDate","2017-08-24 00:17:16");


        String result = HttpPostRequest.post(LiveRoomConstant.DOMIN_NAME+"/integration/site/training/export/history",map);
        System.out.println(result);
        Gson g = new Gson();
        MessResult re =  g.fromJson(result,MessResult.class);
//        MessResult re  = JSONObject.parseObject(result,MessResult.class);
//        System.out.println(re.get("list"));
//        System.out.println(re.get("code"));
//        JSONArray.toList(re.get("list"),new MessUser（）);
//        List<MessUser> list = JSONObject.parseArray(re.get("list").toString(),MessUser.class);
        System.out.println(re.getList().size());

    }

    class MessResult{
        private String code ;
        private List<MessUser> list;

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
    }
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
}
