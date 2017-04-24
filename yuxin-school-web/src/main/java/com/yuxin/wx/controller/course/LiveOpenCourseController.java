package com.yuxin.wx.controller.course;

import com.yuxin.wx.api.auth.IAuthRoleService;
import com.yuxin.wx.api.company.*;
import com.yuxin.wx.api.course.ILiveOpenCourseService;
import com.yuxin.wx.api.system.ISysConfigItemService;
import com.yuxin.wx.api.system.ISysConfigSchoolService;
import com.yuxin.wx.api.system.ISysConfigTeacherService;
import com.yuxin.wx.classes.impl.CCLiveRoomServiceImpl;
import com.yuxin.wx.classes.impl.EketangLiveRoomServiceImpl;
import com.yuxin.wx.classes.impl.GenseeLiveRoomServiceImpl;
import com.yuxin.wx.common.*;
import com.yuxin.wx.model.classes.liveroom.ZsReturnInfo;
import com.yuxin.wx.model.company.*;
import com.yuxin.wx.model.course.LiveOpenCourse;
import com.yuxin.wx.model.system.SysConfigItem;
import com.yuxin.wx.model.system.SysConfigSchool;
import com.yuxin.wx.model.system.SysConfigTeacher;
import com.yuxin.wx.util.APIServiceFunction;
import com.yuxin.wx.util.HttpPostRequest;
import com.yuxin.wx.util.ImageUtils;
import com.yuxin.wx.util.TalkfunUtils;
import com.yuxin.wx.utils.DateUtil;
import com.yuxin.wx.utils.FileUtil;
import com.yuxin.wx.utils.PropertiesUtil;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.classes.LiveRoomInfo;
import com.yuxin.wx.vo.classes.TalkfunEntityVo;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Controller of LiveOpenCourse
 *
 * @author wang.zx
 * @date 2015-9-25
 */
@Controller
@RequestMapping("/liveOpenCourse")
public class LiveOpenCourseController {
    private Log log = LogFactory.getLog("log");

    @Autowired
    private ICompanyLiveConfigService companyLiveConfigServiceImpl;

    @Autowired
    private ILiveOpenCourseService liveOpenCourseServiceImpl;
    @Autowired
    private PropertiesUtil propertiesUtil;
    @Autowired
    private ICompanyService companyService;
    @Autowired
    private GenseeLiveRoomServiceImpl genseeLiveRoomServiceImpl;
    @Autowired
    private EketangLiveRoomServiceImpl eketangLiveRoomServiceImpl;
    @Autowired
    private CCLiveRoomServiceImpl CCLiveRoomServiceImpl;
    @Autowired
    private ISysConfigItemService sysConfigItemServiceImpl;
    @Autowired
    private ISysConfigTeacherService sysConfigTeacherServiceImpl;
    @Autowired
    private ISysConfigSchoolService sysConfigSchoolServiceImpl;
    @Autowired
    private ICompanyMemberServiceService companyMemberServiceServiceImpl;
    @Autowired
    private ICompanyServiceStaticService companyServiceStaticServiceImpl;
    @Autowired
    private PropertiesUtil properties;
    @Autowired
    private IAuthRoleService authRoleServiceImpl;
    @Autowired
    private ICompanyFunctionSetService companyFunctionSetServiceImpl;

    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model, LiveOpenCourse search) {
        if (search == null) {
            search = new LiveOpenCourse();
            // search.setPageSize(20);
        }
        model.addAttribute("list", liveOpenCourseServiceImpl.findLiveOpenCourseByPage(search));
        return "liveOpenCourse/list";
    }

    @ResponseBody
    @RequestMapping(value = "/add")
    public com.alibaba.fastjson.JSONObject add(HttpServletRequest request, LiveOpenCourse liveOpenCoursem, Integer barrage, Integer modetype) {
        com.alibaba.fastjson.JSONObject json = new com.alibaba.fastjson.JSONObject();
        Integer companyId = WebUtils.getCurrentCompanyId();
        Integer schoolId = WebUtils.getCurrentSchoolId();
        Date date = new Date();
        Integer userId = WebUtils.getCurrentUserId(request);
        // 如果是新增的话，需要判断当前公司的直播服务提供商是哪家
        CompanyMemberService liveProvider = companyMemberServiceServiceImpl.findByCompanyId(companyId);

        Integer assint = liveOpenCoursem.getAssistantId();
        if (assint.equals(-1)) {
            liveOpenCoursem.setAssistantId(null);
        }
        liveOpenCoursem.setCompanyId(companyId);
        liveOpenCoursem.setSchoolId(schoolId);
        liveOpenCoursem.setPublishTime(date);
        liveOpenCoursem.setDelFlag(0);
        Integer id = liveOpenCoursem.getId();
        liveOpenCoursem.setBarrage(barrage != null ? barrage : 0);
        if ("cc".equals(liveOpenCoursem.getLiveServiceProvider())) {
            liveOpenCoursem.setModetype(modetype != null ? modetype : 1);
        } else {
            liveOpenCoursem.setModetype(modetype != null ? modetype : 3);
        }

        if (id == null) {
            liveOpenCoursem.setCreateTime(date);
            liveOpenCoursem.setCreator(userId);
            liveOpenCoursem.setUpdateTime(date);
            liveOpenCoursem.setUpdator(userId);
            if (liveProvider != null && liveProvider.getLiveServiceProvider() != null) {
                liveOpenCoursem.setLiveServiceProvider(liveProvider.getLiveServiceProvider());
            }
            log.info("新建公开课");
            liveOpenCoursem.setLiveRoom(UUID.randomUUID().toString().replaceAll("-", ""));
            liveOpenCourseServiceImpl.insert(liveOpenCoursem);
        } else {
            liveOpenCoursem.setUpdateTime(date);
            liveOpenCoursem.setUpdator(userId);
            liveOpenCourseServiceImpl.update(liveOpenCoursem);

        }
        Integer liveId = liveOpenCoursem.getId();
        LiveOpenCourse eLiveInfo = liveOpenCourseServiceImpl.findLiveOpenCourseById(liveId);
        // 判断当前公司是E课堂还是展示互动
        if (liveProvider == null || liveProvider.getLiveServiceProvider() == null || liveProvider.getLiveServiceProvider().equals("")
                || liveProvider.getLiveServiceProvider().equals("gh")) {
            try {
                // E课堂
                String liveRoom = eketangLiveRoomServiceImpl.createLiveRoom(eLiveInfo, companyId);
                LiveRoomInfo bean = (LiveRoomInfo) JSONObject.toBean(JSONObject.fromObject(liveRoom), LiveRoomInfo.class);
                if (bean != null && ("ok").equals(bean.getMsg())) {
                    eLiveInfo.setLiveroomIdGh(bean.getLiveRoomId());
                    if (liveProvider != null && liveProvider.getLiveServiceTemplate() != null
                            && liveProvider.getLiveServiceTemplate().equals(Constant.EKETANG_TEMPALATE_SOOONER)) {
                        eLiveInfo.setStudentUrlGh(bean.getStudentUrl());
                    } else {
                        eLiveInfo.setStudentUrlGh(bean.getStudentUrl().replace("soooner", "taobao"));
                    }
                    eLiveInfo.setTeacherUrlGh(bean.getTeacherUrl());
                    eLiveInfo.setAssistantUrlGh(bean.getAssistantUrl());
                    eLiveInfo.setReplayUrlGh(bean.getReplayUrl());
                    log.info("op///e课堂成功创建课程");
                    json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
                } else {
                    log.info("op///e课堂创建失败，" + liveRoom);
                    json.put(JsonMsg.MSG, "公开课创建失败");
                }
                log.info("op///返回的信息:code=" + bean.getCode() + ",message=" + bean.getMsg());
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
                log.error("创建直播课异常，" + e.getMessage());
                json.put(JsonMsg.MSG, "公开课创建失败");
                return json;
            }

        } else if (liveProvider.getLiveServiceProvider().equals("zs")) {
            try {
                // 展示互动创建直播教室
                String returnLiveRoom = genseeLiveRoomServiceImpl.createLiveRoom(eLiveInfo, companyId);
                ZsReturnInfo bean = null;
                try {
                    bean = (ZsReturnInfo) JSONObject.toBean(JSONObject.fromObject(returnLiveRoom), ZsReturnInfo.class);
                } catch (Exception e) {
                    log.error("创建直播教室出现异常，当前bean信息为：" + bean + ", 教室的ID为：" + eLiveInfo.getId() + ", 教室的名称：" + eLiveInfo.getOpenCourseName(), e);
                    e.printStackTrace();
                    json.put(JsonMsg.MSG, "公开课创建失败");
                    return json;
                }
                eLiveInfo.setLiveroomIdGh(bean.getId());
                eLiveInfo.setStudentUrlGh(bean.getStudentJoinUrl());
                eLiveInfo.setTeacherUrlGh(bean.getTeacherJoinUrl());
                eLiveInfo.setAssistantUrlGh(bean.getTeacherJoinUrl());
                json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
                log.error("创建直播课异常，" + e.getMessage());
                json.put(JsonMsg.MSG, "公开课创建失败");
                return json;
            }
        } else if (liveProvider.getLiveServiceProvider().equals("ht")) {
            try {
                CompanyLiveConfig clc = companyLiveConfigServiceImpl.findByCompanyId(companyId);
                String openId = null;
                String openToken = null;
                if (clc != null && clc.getLiveType().equals(3)) {
                    openId = clc.getLoginName();
                    openToken = clc.getPassword();
                }
                String res = "";
                // 老师介绍
                SysConfigTeacher teacher = sysConfigTeacherServiceImpl.findSysConfigTeacherById(eLiveInfo.getTeacherId());
                Map<Object, Object> param = new HashMap<Object, Object>();
                param.put("course_name", eLiveInfo.getOpenCourseName());
                param.put("account", teacher.getId());
                param.put("start_time", new SimpleDateFormat("yyyy-MM-dd").format(eLiveInfo.getStartOpenData()) + " " + eLiveInfo.getStartTime() + ":00");
                param.put("end_time", new SimpleDateFormat("yyyy-MM-dd").format(eLiveInfo.getEndOpenData()) + " " + eLiveInfo.getEndTime() + ":00");
                param.put("nickname", teacher.getName());
                param.put("accountIntro", teacher.getResume());

                Map<Object, Object> options = new HashMap<Object, Object>();
                options.put("departmentId", companyId);
                options.put("barrage", eLiveInfo.getBarrage());
                options.put("modetype", eLiveInfo.getModetype());
                param.put("options", options);

                if (eLiveInfo.getLiveroomIdGh() != null && eLiveInfo.getLiveroomIdGh().length() > 0) {
                    param.put("course_id", eLiveInfo.getLiveroomIdGh());
                    res = TalkfunUtils.getRsult(param, "course.update", openId, openToken);
                } else {
                    res = TalkfunUtils.getRsult(param, "course.add", openId, openToken);
                }
                com.alibaba.fastjson.JSONObject rjson = com.alibaba.fastjson.JSONObject.parseObject(res);
                if (rjson.getInteger("code").equals(0)) {
                    json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
                } else {
                    if (eLiveInfo.getLiveroomIdGh() == null) {
                        liveOpenCourseServiceImpl.deleteLiveOpenCourseById(eLiveInfo.getId());
                    }
                    json.put(JsonMsg.MSG, rjson.getString("msg"));
                    return json;
                }
                if (rjson.get("data") != null && !rjson.get("data").equals("")) {
                    TalkfunEntityVo tf = com.alibaba.fastjson.JSONObject.parseObject(rjson.getJSONObject("data").toJSONString(), TalkfunEntityVo.class);
                    eLiveInfo.setLiveroomIdGh(tf.getCourse_id());
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                e.printStackTrace();
                log.error("创建直播课异常，" + e.getMessage());
                json.put(JsonMsg.MSG, "公开课创建失败");
                return json;
            }
        } else {
            try {
                String returnLiveRoom = null;
                if (eLiveInfo.getLiveroomIdGh() != null && eLiveInfo.getLiveroomIdGh().length() > 0) {
                    returnLiveRoom = CCLiveRoomServiceImpl.updateLiveRoom(eLiveInfo, companyId);
                    if ("OK".equals(returnLiveRoom)) {
                        json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
                    } else {
                        json.put(JsonMsg.MSG, "修改直播课程失败");
                        return json;
                    }
                } else {
                    returnLiveRoom = CCLiveRoomServiceImpl.createLiveRoom(eLiveInfo, companyId);
                    if (returnLiveRoom != null) {
                        com.alibaba.fastjson.JSONObject rj = com.alibaba.fastjson.JSONObject.parseObject(returnLiveRoom);
                        eLiveInfo.setLiveroomIdGh(rj.getString("roomid"));
                        eLiveInfo.setStudentUrlGh(rj.getString("s"));
                        eLiveInfo.setTeacherUrlGh(rj.getString("t"));
                        eLiveInfo.setAssistantUrlGh(rj.getString("a"));
                        eLiveInfo.setLiveServiceProvider("cc");
                        json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
                    } else {
                        json.put(JsonMsg.MSG, "创建直播课程失败");
                        return json;
                    }
                }
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
                log.debug("创建cc直播课失败," + e.getMessage());
                json.put(JsonMsg.MSG, "创建直播课程失败");
                return json;
            }
        }
        liveOpenCourseServiceImpl.update(eLiveInfo);
        log.info("op///修改成功");
        return json;
    }

    /**
     * @Description: 创建直播课教室
     * @author yuchanglong
     * @date 2015年10月12日 下午6:26:43
     * @version 1.0
     * @param cml
     * @return
     */
    // public String liveRoom(LiveOpenCourse cml) {
    // //查询 光慧直播 id
    // String customer = properties.getCustomer();
    // long timestamp = System.currentTimeMillis();
    // String secretKey = properties.getSecretKey();
    //
    // String str = MD5.getMD5ofStr(customer+timestamp+secretKey);
    // str = str.substring(0,10)+str.substring(str.length() - 10);
    //
    // String url = null;
    //
    // //如果该课次还没有创建直播教室，则走创建直播教室接口，否则修改
    // if(cml.getLiveroomIdGh() != null && cml.getLiveroomIdGh().length() > 0){
    // url = properties.getInterfaceAddress() +
    // LiveRoomConstant.UPDATE_LIVEROOM;
    // }else{
    // url = properties.getInterfaceAddress() + LiveRoomConstant.CREATELIVEROOM;
    // }
    //
    //
    // //教师 和 助教 信息
    // SysConfigTeacher teacher = null;
    // SysConfigTeacher assistant = null;
    //
    // if(cml.getTeacherId() != null){
    // teacher =
    // sysConfigTeacherServiceImpl.findSysConfigTeacherById(cml.getTeacherId());
    // }
    // if(cml.getAssistantId() != null && !cml.getAssistantId().equals(-1)){
    // assistant =
    // sysConfigTeacherServiceImpl.findSysConfigTeacherById(cml.getAssistantId());
    // }
    //
    // TeacherVo tv = null;
    // TeacherVo av = null;
    // List<TeacherVo> avs = new ArrayList<TeacherVo>();
    // List<TeacherVo> tvs = new ArrayList<TeacherVo>();
    // if(teacher != null){
    // tv = new
    // TeacherVo(cml.getTeacherId().toString(),teacher.getName(),teacher.getName(),teacher.getHeadpicUrl(),"",teacher.getResume());
    // tvs.add(tv);
    // }
    // if(assistant != null){
    // av = new
    // TeacherVo(cml.getAssistantId().toString(),assistant.getName(),assistant.getName(),assistant.getHeadpicUrl(),"",assistant.getResume());
    // avs.add(av);
    // }
    // String teachers = com.alibaba.fastjson.JSONArray.toJSONString(tvs);
    // String assistants = com.alibaba.fastjson.JSONObject.toJSONString(avs);
    //
    // Map<String,Object> param = new HashMap<String, Object>();
    // if(cml.getLiveRoom() == null || cml.getLiveRoom() == ""){
    // param.put("id", cml.getId());
    // }else{
    // param.put("id", cml.getLiveRoom());
    // }
    // param.put("title", cml.getOpenCourseName());
    // param.put("introduce", cml.getOpenCourseName());
    // //判断是否支持手机端
    // Integer supportMobile = cml.getSupportMobile();
    // if(supportMobile == 1){
    // param.put("supportMobile", "1");
    // param.put("liveMode", "2");
    // }else{
    // param.put("supportMobile", "0");
    // param.put("liveMode", "1");
    // }
    //
    // //学生不需要鉴权
    // param.put("fee", 1);
    //
    // String day = format.format(cml.getStartOpenData());
    // String startTime = cml.getStartTime();
    // String endTime = cml.getEndTime();
    // long beginTimeLong = DateUtil.parseDateTime(day,startTime);
    // long endTimeLong = DateUtil.parseDateTime(day,endTime);
    //
    // param.put("beginTime", beginTimeLong);
    // param.put("endTime", endTimeLong);
    //
    //
    // param.put("customer", customer);
    // param.put("timestamp", timestamp);
    // param.put("s", str);
    //
    // param.put("teachers", teachers);
    // if(assistant != null){
    // param.put("assistants", assistants);
    // }
    // String detail;
    // try {
    // detail = HttpPostRequest.post(url, param);
    // log.info("调用E课堂创建直播教室接口,返回信息如下：" + detail + ",
    // 删除的课堂名称为："+cml.getOpenCourseName()+", 课堂Id为："+cml.getId());
    // return detail;
    // } catch (Exception e) {
    // e.printStackTrace();
    // log.info("调用E课堂创建直播教室接口失败,课次Id为：" + cml.getId() + ",
    // 删除的课堂名称为："+cml.getOpenCourseName());
    // return null;
    // }
    // }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(LiveOpenCourse LiveOpenCourse) {
        LiveOpenCourse loc = liveOpenCourseServiceImpl.findLiveOpenCourseById(LiveOpenCourse.getId());
        CompanyLiveConfig clc = companyLiveConfigServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
        if ("ht".equals(loc.getLiveServiceProvider())) {
            String openId = null;
            String openToken = null;
            if (clc != null && clc.getLiveType().equals(3)) {
                openId = clc.getLoginName();
                openToken = clc.getPassword();
            }
            String res = "";
            Map<Object, Object> param = new HashMap<Object, Object>();
            param.put("course_id", loc.getLiveroomIdGh());
            try {
                res = TalkfunUtils.getRsult(param, "course.delete", openId, openToken);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return "faild";
            }
            log.debug("删除课次，" + res);
        }
        if ("cc".equals(loc.getLiveServiceProvider())) {
            if (clc == null || !clc.getLiveType().equals(4)) {
                clc = null;
            }
            Map<String, String> map = new HashMap<String, String>();
            map.put("roomid", loc.getLiveroomIdGh());
            map.put("userid", (clc != null ? clc.getLoginName() : CCLiveInterface.USER_ID));

            long timestimp = System.currentTimeMillis();
            String thqs = APIServiceFunction.createHashedQueryString(map, timestimp, clc != null ? clc.getPassword() : CCLiveInterface.API_KEY);
            String url = CCLiveInterface.CLOSE + thqs;
            try {
                String res = HttpPostRequest.get(url);
                log.debug("删除cc直播 信息," + res);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                log.debug("cc直播间关闭失败," + e.getMessage());
            }
        }
        liveOpenCourseServiceImpl.update(LiveOpenCourse);
        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "/del")
    public String del(Model model, Integer id) {
        liveOpenCourseServiceImpl.deleteLiveOpenCourseById(id);
        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "/{id:\\d+}", method = RequestMethod.GET)
    public LiveOpenCourse getJson(Model model, @PathVariable Integer id) {
        return liveOpenCourseServiceImpl.findLiveOpenCourseById(id);
    }

    /**
     *
     * Class Name: LiveOpenCourseController.java
     *
     * @Description: 添加直播公开课页面
     * @author yuchanglong
     * @date 2015年9月25日 下午5:21:42
     * @version 1.0
     * @param model
     * @return
     */
    @RequestMapping(value = "/toAddOpenClass/{id}/{isCopy}")
    public String toAddOpenClass(Model model, @PathVariable Integer id, @PathVariable Integer isCopy, HttpServletRequest request) {
        List<SysConfigItem> firstItems = sysConfigItemServiceImpl.findSysConfigItemByPid(SysConfigConstant.ITEMTYPE_FIRST, null, WebUtils.getCurrentCompanyId(),
                WebUtils.getCurrentSchoolId());
        model.addAttribute("oneItems", firstItems);
        model.addAttribute("isCopy", isCopy);
        String imgUrl = propertiesUtil.getProjectImageUrl();
        model.addAttribute("imgUrl", imgUrl);
        model.addAttribute("isEdit", id);
        CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
        String tg = cms.getLiveServiceProvider();
        model.addAttribute("tg", tg);
        if (id != 0) {
            LiveOpenCourse course = liveOpenCourseServiceImpl.findLiveOpenCourseById(id);
            String startOpenData = request.getParameter("startOpenData");
            String endOpenData = request.getParameter("endOpenData");
            if (startOpenData != null) {
                String ymd = startOpenData.substring(0, 10);
                String hm = startOpenData.substring(11);
                course.setStartOpenData(DateUtil.getdate(ymd));
                course.setStartTime(hm);
            }
            if (endOpenData != null) {
                String ymd = endOpenData.substring(0, 10);
                String hm = endOpenData.substring(11);
                course.setEndOpenData(DateUtil.getdate(ymd));
                course.setEndTime(hm);
            }
            model.addAttribute("course", course);
        }

        return "openClass/addOpenClass";
    }

    /**
     *
     * Class Name: LiveOpenCourseController.java
     *
     * @Description: 获得教师信息
     * @author yuchanglong
     * @date 2015年9月25日 下午5:22:13
     * @version 1.0
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getTeachers")
    public List<SysConfigTeacher> getTeachers() {
        Integer companyId = WebUtils.getCurrentCompanyId();
        Integer schoolId = WebUtils.getCurrentSchoolId();
        SysConfigTeacher configTeacher = new SysConfigTeacher();
        configTeacher.setCompanyId(companyId);
        configTeacher.setSchoolId(schoolId);
        List<SysConfigTeacher> teachers = null;
        Map<String, String> teacherMap = new HashMap<String, String>();
        teacherMap.put("companyId", companyId + "");
        teacherMap.put("schoolId", schoolId + "");
        teachers = sysConfigTeacherServiceImpl.findTeachers(teacherMap);
        return teachers;
    }

    /**
     *
     * Class Name: LiveOpenCourseController.java
     *
     * @Description: 获得助教信息
     * @author yuchanglong
     * @date 2015年9月25日 下午5:22:13
     * @version 1.0
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAssistant")
    public List<SysConfigTeacher> getAssistant() {
        Integer companyId = WebUtils.getCurrentCompanyId();
        Integer schoolId = WebUtils.getCurrentSchoolId();
        SysConfigTeacher configTeacher = new SysConfigTeacher();
        configTeacher.setCompanyId(companyId);
        configTeacher.setSchoolId(schoolId);
        List<SysConfigTeacher> teachers = null;
        Map<String, String> teacherMap = new HashMap<String, String>();
        teacherMap.put("companyId", companyId + "");
        teacherMap.put("schoolId", schoolId + "");
        teachers = sysConfigTeacherServiceImpl.findAssistants(teacherMap);
        return teachers;
    }

    /**
     *
     * Class Name: LiveOpenCourseController.java
     *
     * @Description: 通过一级item获得二级item
     * @author yuchanglong
     * @date 2015年9月25日 下午5:57:43
     * @version 1.0
     * @param pId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getTwoItemsByOneId")
    public List<SysConfigItem> getTwoItemsByOneId(Integer pId) {
        Integer companyId = WebUtils.getCurrentCompanyId();
        Integer schoolId = WebUtils.getCurrentSchoolId();
        List<SysConfigItem> configItems = null;
        configItems = sysConfigItemServiceImpl.findSysConfigItemByPid("2", pId, companyId, schoolId);
        return configItems;
    }

    @ResponseBody
    @RequestMapping(value = "/saveCutPic")
    public String saveCutPic(HttpServletRequest request) {
        log.info("初始化截图开始===============");
        Resource resource = new ClassPathResource("config.properties");
        Properties props = null;
        try {
            props = PropertiesLoaderUtils.loadProperties(resource);
        } catch (Exception e) {
            log.error(e, e);
            e.printStackTrace();
        }
        String path = request.getParameter("path");
        String fileName = path.substring(path.lastIndexOf("/") + 1);
        String tempPath = props.getProperty("server.imageupload.tempPath") + "/source/" + fileName;
        String target = props.getProperty("server.imageupload.tempPath") + "/target/" + fileName;
        String header = "http://" + props.getProperty("yunduoketang.oss.imagedomain") + "/";

        path = path.replace(header, "");
        System.out.println("oss临时文件路径[" + path + "]=====本地磁盘临时文件路径[" + tempPath + "]======切图后临时文件路径[" + target + "]");
        FileUtil.download("temp", path, tempPath);
        // 选中尺寸
        double x = Double.parseDouble(request.getParameter("x"));
        double y = Double.parseDouble(request.getParameter("y"));
        double w = Double.parseDouble(request.getParameter("w"));
        double h = Double.parseDouble(request.getParameter("h"));
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(tempPath));
        } catch (Exception e) {
            log.error("读取图片失败:" + e, e);
            e.printStackTrace();
        }
        // 原图尺寸
        double realW = img.getWidth();
        double realH = img.getHeight();
        // 示例图尺寸
        double slW = 0;
        double slH = 0;
        if (realW / realH > 516.00 / 282.00) {
            // 过宽
            slH = 516 * realH / realW;
            slW = 516;
        } else {
            // 过高
            slH = 282;
            slW = 282 * realW / realH;
        }
        // 原图所选中位置和区域

        int xx = (new Double(x * realW / slW)).intValue();
        int yy = (new Double(y * realH / slH)).intValue();
        int ww = (new Double(w * realW / slW)).intValue();
        int hh = (new Double(h * realH / slH)).intValue();
        System.out.println("选中区域:[" + x + "," + y + "," + w + "," + h + "]----原图选中区域:[" + xx + "," + yy + "," + ww + "," + hh + "]");
        // 在原图中切图
        String cutImgPath = ImageUtils.cutImage(tempPath, target, xx, yy, ww, hh);
        // 切好的图缩放到规定比例
        // ImageUtils.scale2(target,target,241,446,true);
        ImageUtils.resize(target, target, 446);
        log.info("截图完成===============");
        log.info("上传图片开始===============");
        String realPath = null;
        try {
            realPath = FileUtil.upload(cutImgPath, "course", WebUtils.getCurrentCompanyId() + "");
        } catch (Exception e) {
            log.error("上传文件失败", e);
            e.printStackTrace();
        }
        log.info("上传图片后路径===============" + realPath);
        FileUtil.deleteFile(target);
        FileUtil.deleteFile(cutImgPath);

        // String
        // picUrl="http://"+propertiesUtil.getProjectImageUrl()+"/"+realPath;
        // log.info("图片回显路径==============="+picUrl);

        return realPath;
    }

    /**
     *
     * Class Name: LiveOpenCourseController.java
     *
     * @Description: 直播公开课展示页面
     * @author yuchanglong
     * @date 2015年9月28日 下午12:01:44
     * @version 1.0
     * @param model
     * @return
     */
    @RequestMapping(value = "/toLiveShow")
    public String toLiveShow(Model model, HttpServletRequest request) {
        Integer companyId = WebUtils.getCurrentCompanyId();
        CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
        List<SysConfigItem> oneItems = sysConfigItemServiceImpl.findSysConfigItemByPid(SysConfigConstant.ITEMTYPE_FIRST, null, WebUtils.getCurrentCompanyId(),
                WebUtils.getCurrentSchoolId());
        model.addAttribute("oneItems", oneItems);
        model.addAttribute("cms", cms);
        return "openClass/openClass";
    }

    /**
     *
     * Class Name: LiveOpenCourseController.java
     *
     * @Description: 异步加载
     * @author yuchanglong
     * @date 2015年9月29日 下午3:29:19
     * @version 1.0
     * @param model
     * @param course
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/ajaxInfo")
    public String ajaxInfo(Model model, LiveOpenCourse course) throws ParseException {
        course.setPageSize(8);
        course.setCompanyId(WebUtils.getCurrentCompanyId());
        course.setSchoolId(WebUtils.getCurrentSchoolId());
        course.setDelFlag(0);
        PageFinder<LiveOpenCourse> pageFinder = liveOpenCourseServiceImpl.findLiveOpenCourseByPage(course);
        setPlayStatus(pageFinder.getData());
        // 遍历处理课程名字过长,截取前半部分，剩余...展示，解决导致前端显示异常问题
        for (LiveOpenCourse i : pageFinder.getData()) {
            String courseName = i.getOpenCourseName();
            if (courseName.length() > 10) {
                courseName = courseName.substring(0, 10) + "...";
                i.setOpenCourseName(courseName);
            }
        }
        model.addAttribute("pageFinder", pageFinder);
        String imgUrl = propertiesUtil.getProjectImageUrl();
        model.addAttribute("imgUrl", imgUrl);
        return "openClass/ajaxInfo";
    }

    /**
     *
     * Class Name: LiveOpenCourseController.java
     *
     * @Description: 查询公开课时设置播放状态
     * @author yuchanglong
     * @date 2015年10月10日 下午2:21:10
     * @version 1.0
     * @param courses
     * @throws ParseException
     */
    private void setPlayStatus(List<LiveOpenCourse> courses) throws ParseException {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (LiveOpenCourse loc : courses) {
            System.out.println(sdf.format(now));
            now = sdf.parse(sdf.format(now));
            Date openDate = loc.getStartOpenData();
            openDate = sdf.parse(sdf.format(openDate));
            Long openLong = openDate.getTime();
            Long nowLong = now.getTime();
            if (openLong < nowLong) {
                loc.setPlayStatus(0);
            }
            if (openLong > nowLong) {
                loc.setPlayStatus(-1);
            }
            if (openLong.equals(nowLong)) {
                Date nowHF = new Date();
                Integer nowH = nowHF.getHours();
                Integer nowF = nowHF.getMinutes();
                String sTimeStr = loc.getStartTime();
                String eTimeStr = loc.getEndTime();
                Integer sTime = Integer.parseInt(sTimeStr.substring(0, 2));
                Integer eTime = Integer.parseInt(eTimeStr.substring(0, 2));

                Integer sTimeF = Integer.parseInt(sTimeStr.substring(3));
                Integer eTimeF = Integer.parseInt(eTimeStr.substring(3));

                if (nowH > sTime && nowH < eTime) {
                    loc.setPlayStatus(1);
                }
                if (nowH < sTime) {
                    loc.setPlayStatus(-1);
                }
                if (nowH == sTime) {
                    if (nowF >= sTimeF) {
                        loc.setPlayStatus(1);
                    } else {
                        loc.setPlayStatus(-1);
                    }
                }
                if (nowH > eTime) {
                    loc.setPlayStatus(0);
                }
                if (nowH == eTime) {
                    if (nowF <= eTimeF) {
                        loc.setPlayStatus(1);
                    } else {
                        loc.setPlayStatus(0);
                    }
                }
            }
        }
    }

    /**
     *
     * Class Name: LiveOpenCourseController.java
     *
     * @Description: 复制功能
     * @author yuchanglong
     * @date 2015年9月29日 下午5:08:19
     * @version 1.0
     * @param course
     * @return
     * @throws ParseException
     */
    @ResponseBody
    @RequestMapping(value = "/copyInfo")
    public String copyInfo(Integer id, String startOpenData, String endOpenData, Integer barrage, Integer modetype) throws ParseException {

        String startDate = startOpenData.substring(0, 10);
        String startTime = startOpenData.substring(11);
        String endDate = endOpenData.substring(0, 10);
        String endTime = endOpenData.substring(11);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        LiveOpenCourse search = new LiveOpenCourse();
        search.setId(id);
        search.setStartOpenData(sdf.parse(startDate));
        search.setStartTime(startTime);
        search.setEndOpenData(sdf.parse(endDate));
        search.setEndTime(endTime);
        LiveOpenCourse openCourse = liveOpenCourseServiceImpl.findByLiveOpenCourse(search);
        Integer companyId = WebUtils.getCurrentCompanyId();
        if (openCourse == null) {
            // 如果是新增的话，需要判断当前公司的直播服务提供商是哪家
            CompanyMemberService liveProvider = companyMemberServiceServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());

            LiveOpenCourse newCourse = liveOpenCourseServiceImpl.findLiveOpenCourseById(search.getId());
            newCourse.setStartOpenData(search.getStartOpenData());
            newCourse.setStartTime(search.getStartTime());
            newCourse.setEndOpenData(search.getEndOpenData());
            newCourse.setEndTime(search.getEndTime());
            newCourse.setId(null);
            newCourse.setLiveroomIdGh(null);
            newCourse.setStudentUrlGh(null);
            newCourse.setAssistantUrlGh(null);
            newCourse.setTeacherUrlGh(null);
            newCourse.setReplayUrlGh(null);
            newCourse.setLiveRoom(UUID.randomUUID().toString().replaceAll("-", ""));
            newCourse.setBarrage(barrage);
            newCourse.setModetype(modetype);

            liveOpenCourseServiceImpl.insert(newCourse);

            // 判断当前公司是E课堂还是展示互动
            if (liveProvider == null || liveProvider.getLiveServiceProvider() == null || liveProvider.getLiveServiceProvider().equals("gh")
                    || liveProvider.getLiveServiceProvider().equals("")) {
                // E课堂
                String liveRoom = eketangLiveRoomServiceImpl.createLiveRoom(newCourse, companyId);
                LiveRoomInfo bean = (LiveRoomInfo) JSONObject.toBean(JSONObject.fromObject(liveRoom), LiveRoomInfo.class);
                if (bean != null && ("ok").equals(bean.getMsg())) {
                    newCourse.setLiveroomIdGh(bean.getLiveRoomId());
                    if (liveProvider != null && liveProvider.getLiveServiceTemplate() != null
                            && liveProvider.getLiveServiceTemplate().equals(Constant.EKETANG_TEMPALATE_SOOONER)) {
                        newCourse.setStudentUrlGh(bean.getStudentUrl());
                    } else {
                        newCourse.setStudentUrlGh(bean.getStudentUrl().replace("soooner", "taobao"));
                    }
                    newCourse.setTeacherUrlGh(bean.getTeacherUrl());
                    newCourse.setAssistantUrlGh(bean.getAssistantUrl());
                    newCourse.setReplayUrlGh(bean.getReplayUrl());
                } else {
                    log.info("op///e课堂创建失败，" + liveRoom);
                }
                log.info("op///返回的信息:code=" + bean.getCode() + ",message=" + bean.getMsg());

            } else if (liveProvider.getLiveServiceProvider().equals("zs")) {

                // 展示互动创建直播教室
                String returnLiveRoom = genseeLiveRoomServiceImpl.createLiveRoom(newCourse, companyId);
                ZsReturnInfo bean = null;
                try {
                    bean = (ZsReturnInfo) JSONObject.toBean(JSONObject.fromObject(returnLiveRoom), ZsReturnInfo.class);
                } catch (Exception e) {
                    log.error("创建直播教室出现异常，当前bean信息为：" + bean + ", 教室的ID为：" + newCourse.getId() + ", 教室的名称：" + newCourse.getOpenCourseName(), e);
                    e.printStackTrace();
                }
                newCourse.setLiveroomIdGh(bean.getId());
                newCourse.setStudentUrlGh(bean.getStudentJoinUrl());
                newCourse.setTeacherUrlGh(bean.getTeacherJoinUrl());
                newCourse.setAssistantUrlGh(bean.getTeacherJoinUrl());
            } else if (liveProvider.getLiveServiceProvider().equals("ht")) {
                try {
                    CompanyLiveConfig clc = companyLiveConfigServiceImpl.findByCompanyId(companyId);
                    String openId = null;
                    String openToken = null;
                    if (clc != null && clc.getLiveType().equals(3)) {
                        openId = clc.getLoginName();
                        openToken = clc.getPassword();
                    }
                    String res = "";
                    // 老师介绍
                    SysConfigTeacher teacher = sysConfigTeacherServiceImpl.findSysConfigTeacherById(newCourse.getTeacherId());
                    Map<Object, Object> param = new HashMap<Object, Object>();
                    param.put("course_name", newCourse.getOpenCourseName());
                    param.put("account", teacher.getId());
                    param.put("start_time", new SimpleDateFormat("yyyy-MM-dd").format(newCourse.getStartOpenData()) + " " + newCourse.getStartTime() + ":00");
                    param.put("end_time", new SimpleDateFormat("yyyy-MM-dd").format(newCourse.getEndOpenData()) + " " + newCourse.getEndTime() + ":00");
                    param.put("nickname", teacher.getName());
                    param.put("accountIntro", teacher.getResume());

                    Map<Object, Object> options = new HashMap<Object, Object>();
                    options.put("departmentId", companyId);
                    options.put("barrage", newCourse.getBarrage());
                    options.put("modetype", newCourse.getModetype());
                    param.put("options", options);

                    if (newCourse.getLiveroomIdGh() != null && newCourse.getLiveroomIdGh().length() > 0) {
                        param.put("course_id", newCourse.getLiveroomIdGh());
                        res = TalkfunUtils.getRsult(param, "course.update", openId, openToken);
                    } else {
                        res = TalkfunUtils.getRsult(param, "course.add", openId, openToken);
                    }
                    com.alibaba.fastjson.JSONObject rjson = com.alibaba.fastjson.JSONObject.parseObject(res);
                    if (!rjson.getInteger("code").equals(0)) {
                        if (newCourse.getLiveroomIdGh() == null) {
                            liveOpenCourseServiceImpl.deleteLiveOpenCourseById(newCourse.getId());
                        }
                        return rjson.getString("msg");
                    }
                    if (rjson.get("data") != null && !rjson.get("data").equals("")) {
                        TalkfunEntityVo tf = com.alibaba.fastjson.JSONObject.parseObject(rjson.getJSONObject("data").toJSONString(), TalkfunEntityVo.class);
                        newCourse.setLiveroomIdGh(tf.getCourse_id());
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    e.printStackTrace();
                    log.error("创建直播课异常，" + e.getMessage());
                    return "fail";
                }
            } else {
                try {
                    String returnLiveRoom = null;
                    if (newCourse.getLiveroomIdGh() != null && newCourse.getLiveroomIdGh().length() > 0) {
                        returnLiveRoom = CCLiveRoomServiceImpl.updateLiveRoom(newCourse, companyId);
                        if ("OK".equals(returnLiveRoom)) {
                            log.info("修改公开课成功");
                        } else {
                            log.info("修改直播课程失败");
                            return "fail";
                        }
                    } else {
                        returnLiveRoom = CCLiveRoomServiceImpl.createLiveRoom(newCourse, companyId);
                        if (returnLiveRoom != null) {
                            com.alibaba.fastjson.JSONObject rj = com.alibaba.fastjson.JSONObject.parseObject(returnLiveRoom);
                            newCourse.setLiveroomIdGh(rj.getString("roomid"));
                            newCourse.setStudentUrlGh(rj.getString("s"));
                            newCourse.setTeacherUrlGh(rj.getString("t"));
                            newCourse.setAssistantUrlGh(rj.getString("a"));
                            newCourse.setLiveServiceProvider("cc");
                        } else {
                            log.info("创建直播课程失败");
                            return "fail";
                        }
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                    log.debug("创建cc直播课失败," + e.getMessage());
                    return "fail";
                }
            }

            liveOpenCourseServiceImpl.update(newCourse);
            return "success";
        }
        return "fail";
    }

    /**
     * 后台接收Date转换
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @RequestMapping(value = "/showBannerPic", method = RequestMethod.GET)
    public String showBannerPic(HttpServletRequest request, Model model) {
        Integer companyId = WebUtils.getCurrentCompanyId();
        Integer schoolId = WebUtils.getCurrentSchoolId();

        Subject subject = SecurityUtils.getSubject();
        if (authRoleServiceImpl.hasRoleFlag(WebUtils.getCurrentUser().getId())) {
            List<SysConfigSchool> schoolList = sysConfigSchoolServiceImpl.findSysConfigSchoolByCompanyId(companyId);
            model.addAttribute("schoolId", schoolId);
            model.addAttribute("schoolList", schoolList);
        } else {
            SysConfigSchool school = sysConfigSchoolServiceImpl.findSysConfigSchoolById(schoolId);
            model.addAttribute("school", school);
        }
        log.info(request.getServerName() + "====================" + request.getSession().getId());
        Company company = companyService.findCompanyById(companyId);

        CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
        CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);
        model.addAttribute("company", company);
        model.addAttribute("cms", cms);
        model.addAttribute("css", css);
        // return "openClass/bannerPic";
        return "openClass/systemOpenclassCycle";
    }

    /**
     * 
     * Class Name: LiveOpenCourseController.java
     * 
     * @Description: TODO(公开课配置)
     * @author dongshuai
     * @date 2016年10月17日 上午10:37:33
     * @modifier
     * @modify-date 2016年10月17日 上午10:37:33
     * @version 1.0
     * @param model
     * @return
     */
    @RequestMapping(value = "/showLiveOpenClassSetting")
    public String showLiveOpenClassSetting(Model model) {
        Integer companyId = WebUtils.getCurrentCompanyId();
        // 公司配置
        Company company = companyService.findCompanyById(companyId);
        CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
        CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
        model.addAttribute("company", company);
        model.addAttribute("cms", cms);
        model.addAttribute("css", css);

        // OPENCLASS_SETTING_NEEDLOGIN 公开课需要登陆观看开关
        CompanyFunctionSet companyFunctionSet = new CompanyFunctionSet();
        companyFunctionSet.setCompanyId(companyId);
        companyFunctionSet.setFunctionCode("OPENCLASS_SETTING_NEEDLOGIN");
        List<CompanyFunctionSet> companyFunctionSetList = companyFunctionSetServiceImpl.findCompanyFunctionSetByPage(companyFunctionSet);
        CompanyFunctionSet cfs = companyFunctionSetList != null && companyFunctionSetList.size() > 0 ? companyFunctionSetList.get(0) : null;
        if (null == cfs) {
            cfs = new CompanyFunctionSet();
            cfs.setCompanyId(companyId);
            cfs.setFunctionCode("OPENCLASS_SETTING_NEEDLOGIN");
            cfs.setFunctionName("公开课设置需要登陆观看");
            cfs.setContent("0：关闭；1：开启");
            cfs.setStatus("0");
            companyFunctionSetServiceImpl.insert(cfs);
        }
        model.addAttribute("cfs", cfs);
        return "openClass/showLiveOpenClassSetting";
    }

    /**
     * 
     * Class Name: LiveOpenCourseController.java
     * 
     * @Description: TODO(修改公开课需要登陆配置)
     * @author dongshuai
     * @date 2016年10月17日 上午11:39:05
     * @modifier
     * @modify-date 2016年10月17日 上午11:39:05
     * @version 1.0
     * @param status
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateLiveOpenClassSettingNeedLogin", method = RequestMethod.POST)
    public JSONObject updateLiveOpenClassSettingNeedLogin(String status) {
        JSONObject json = new JSONObject();
        try {
            Integer companyId = WebUtils.getCurrentCompanyId();

            CompanyFunctionSet companyFunctionSet = new CompanyFunctionSet();
            companyFunctionSet.setCompanyId(companyId);
            companyFunctionSet.setFunctionCode("OPENCLASS_SETTING_NEEDLOGIN");
            List<CompanyFunctionSet> companyFunctionSetList = companyFunctionSetServiceImpl.findCompanyFunctionSetByPage(companyFunctionSet);
            CompanyFunctionSet cfs = companyFunctionSetList != null && companyFunctionSetList.size() > 0 ? companyFunctionSetList.get(0) : null;
            if (cfs != null) {
                cfs.setStatus(status);
                companyFunctionSetServiceImpl.update(cfs);
                json.put("status", "success");
            }
        } catch (Exception e) {
            log.error("设置公开课需要登陆失败", e);
            json.put("status", "error");
            json.put("result", "设置失败，请刷新后重试");
        }
        return json;
    }

    /**
     * 今天以及以后的公开课
     * 
     * @param model
     * @param request
     * @param course
     * @return
     * @throws ParseException
     */
    @ResponseBody
    @RequestMapping(value = "/findTodayAndAfter")
    public Object findTodayAndAfter(Model model, HttpServletRequest request, LiveOpenCourse course) throws ParseException {
        Integer companyId = WebUtils.getCurrentCompanyId();
        // Integer schoolId =
        // Integer.parseInt(request.getParameter("schoolId"));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date toDay = new Date();
        toDay = sdf.parse(sdf.format(toDay));
        course.setStartOpenData(toDay);
        course.setCompanyId(companyId);
        // course.setSchoolId(schoolId);
        course.setDelFlag(0);
        // List<LiveOpenCourse> coursesCount =
        // liveOpenCourseServiceImpl.findAfterToday(course);
        // model.addAttribute("totalCount",coursesCount.size());
        List<LiveOpenCourse> courses = null;
        // course.setPageSize(8);
        courses = liveOpenCourseServiceImpl.findAfterTodayByPage(course);
        setPlayStatus(courses);
        model.addAttribute("courses", courses);

        return courses;
    }
}
