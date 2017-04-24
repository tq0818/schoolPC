package com.yuxin.wx.controller.queAns;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.queAns.IQuestionAnswerService;
import com.yuxin.wx.api.queAns.IQuestionService;
import com.yuxin.wx.api.system.ISysConfigTeacherService;
import com.yuxin.wx.api.user.IUsersFrontService;
import com.yuxin.wx.api.user.IUsersService;
import com.yuxin.wx.common.JsonMsg;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.queAns.QueQuestion;
import com.yuxin.wx.model.queAns.QuestionAnswer;
import com.yuxin.wx.model.system.SysConfigTeacher;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.model.user.UsersFront;
import com.yuxin.wx.utils.PropertiesUtil;
import com.yuxin.wx.utils.WebUtils;

@Controller
@RequestMapping("/questionanswermanager")
public class QAManagerController {

    private Log log = LogFactory.getLog("log");

    @Autowired
    private ISysConfigTeacherService sysConfigTeacherServiceImpl;

    @Autowired
    private IUsersFrontService usersFrontServiceImpl;

    @Autowired
    private IUsersService usersServiceImpl;

    @Autowired
    private PropertiesUtil propertiesUtil;

    @Autowired
    private IQuestionAnswerService questionAnswerServiceImpl;

    @Autowired
    private IQuestionService questionServiceImpl;

    @RequestMapping("/selAns")
    public String selAnswer(Model model, HttpServletRequest request, QuestionAnswer ans, String types) {
        log.info("qa：查询qid:" + ans.getQuestionId() + "，下的回答");
        try {
            List<QuestionAnswer> anlist = questionAnswerServiceImpl.findAnsByQueId(ans);
            // 查询头像
            for (QuestionAnswer a : anlist) {
                log.info("qa：一级回复：" + a);
                if (a.getAnswerType().equals("QUESTION_ANSWER_STUDENT")) {
                    UsersFront user = usersFrontServiceImpl.findUsersFrontById(a.getUserId());
                    if (user.getNickName() != null) {
                        a.setName(user.getNickName());
                    } else if (user.getMobile() != null) {
                        a.setName("******" + user.getMobile().substring(7));
                    } else {
                        a.setName(user.getUsername());
                    }
                    if (user.getHeadPicMax() != null) {
                        a.setImgurl(user.getHeadPicMax());
                    }
                } else {
                    SysConfigTeacher teacher = sysConfigTeacherServiceImpl.findByUserId(a.getUserId());
                    if (teacher != null) {
                        a.setName(teacher.getName() != null ? teacher.getName() : ("*******" + teacher.getMobile().substring(7)));
                        if (teacher.getHeadpicUrl() != null) {
                            a.setImgurl(teacher.getHeadpicUrl());
                        }
                    } else {
                        Users user = usersServiceImpl.findUsersById(a.getUserId());
                        a.setName(user.getRealName() != null ? user.getRealName() : ("*******" + user.getMobile().substring(7)));
                        if (user.getHeadPicUrl() != null) {
                            a.setImgurl(user.getHeadPicUrl());
                        }
                    }
                }
                String content = a.getAnswerDesc();
                String newcon = "";
                String u = "";
                while (content.indexOf("style") > 0) {
                    newcon = content.substring(content.indexOf("style"));
                    newcon = newcon.substring(0, newcon.indexOf(">"));
                    content = content.replace(newcon, "");
                }

                for (int i = 1; i <= 6; i++) {
                    content = content.replaceAll("h" + i, "span");
                }

                content = content.replaceAll("<br", "<span");
                content = content.replaceAll("<p", "<span");
                content = content.replaceAll("</p", "</span");
                content = content.replaceAll("<span", "<span style='font-size:14px'");

                while (content.indexOf("<img") > 0) {
                    newcon = content.substring(content.indexOf("<img"));
                    newcon = newcon.substring(0, newcon.indexOf(">") + 1);
                    u = newcon.substring(newcon.indexOf("src=\"") + 5);
                    u = u.substring(0, u.indexOf("\""));
                    content = content.replace(newcon, "<span class='icons' data-url='" + u + "' style='color:#0099ff;'>[图片]</span>");
                }
                a.setAnswerDesc(content);
                a.setTimes(secToTime(a.getCreateTime().getTime()));
            }

            log.info("qa：查询总数");

            Integer count = questionAnswerServiceImpl.findAnsCountByQueId(ans);

            PageFinder<QuestionAnswer> anPage = new PageFinder<QuestionAnswer>(ans.getPage(), ans.getPageSize(), count, anlist);

            model.addAttribute("types", types);
            model.addAttribute("anPage", anPage);
            model.addAttribute("imgpath", propertiesUtil.getProjectImageUrl());
        } catch (Exception e) {
            log.error("aq：出错了：" + e.getMessage(), e);
            e.printStackTrace();
        }
        return "queAns/QAAjax";
    }

    /**
     * 
     * Class Name: QAController.java
     * 
     * @Description: 删除回复
     * @author 周文斌
     * @date 2015-12-10 下午12:13:00
     * @version 1.0
     * @param request
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/delAns")
    public JSONObject delAns(HttpServletRequest request, Integer id, String types) {
        JSONObject json = new JSONObject();
        log.info("qa：当前要删除的回复等级:" + types);
        try {
            if (types.equals("1")) {
                QuestionAnswer ans = questionAnswerServiceImpl.findQuestionAnswerById(id);
                // 查询问题
                QueQuestion que = questionServiceImpl.findQuestionById(ans.getQuestionId());
                que.setAnswerCount(que.getAnswerCount() - 1);
                questionServiceImpl.update(que);
                // 查询当前回复下所有的其他回复
                List<Integer> alist = questionAnswerServiceImpl.findTwoAns(id);
                alist.add(id);
                log.info("qa：回复id:" + alist);
                questionAnswerServiceImpl.updateList(alist);
                json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
                return json;
            } else {
                List<Integer> alist = new ArrayList<Integer>();
                alist.add(id);
                log.info("qa：回复id:" + alist);
                questionAnswerServiceImpl.updateList(alist);
                QuestionAnswer ans = questionAnswerServiceImpl.findQuestionAnswerById(id);
                QuestionAnswer one = questionAnswerServiceImpl.findQuestionAnswerById(ans.getAnswerId());
                one.setCommentCount(one.getCommentCount() - 1);
                questionAnswerServiceImpl.update(one);

                json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
                return json;
            }
        } catch (Exception e) {
            log.error("qa：批量删除异常:" + e.getMessage(), e);
            e.printStackTrace();
            json.put(JsonMsg.MSG, JsonMsg.INFORMATION);
            return json;
        }
    }

    /**
     * 
     * Class Name: QAController.java
     * 
     * @Description: 添加二级回复
     * @author 周文斌
     * @date 2015-12-10 下午3:45:56
     * @version 1.0
     * @param request
     * @param ans
     * @return
     */
    @ResponseBody
    @RequestMapping("/addAns")
    public JSONObject addAns(HttpServletRequest request, QuestionAnswer ans) {
        JSONObject json = new JSONObject();
        Users user = WebUtils.getCurrentUser();
        ans.setUserId(user.getId());
        ans.setAnswerLevel(2);
        ans.setDelFlag(1);
        ans.setReadFlag(0);
        ans.setCreateTime(new Date());
        if (ans.getAnswerType().equals("3")) {
            ans.setAnswerType("QUESTION_ANSWER_MANAGE");
        } else {
            ans.setAnswerType("QUESTION_ANSWER_TEACHER");
        }
        log.info("qa：添加回复：" + ans);

        try {
            questionAnswerServiceImpl.insert(ans);
            // 查询一级回复
            QuestionAnswer one = questionAnswerServiceImpl.findQuestionAnswerById(ans.getAnswerId());
            one.setCommentCount(one.getCommentCount() + 1);

            questionAnswerServiceImpl.update(one);

            json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
            return json;
        } catch (Exception e) {
            log.error("qa：添加回复出错：" + e.getMessage(), e);
            e.printStackTrace();
            json.put(JsonMsg.MSG, JsonMsg.INFORMATION);
            return json;
        }
    }

    /**
     * 
     * Class Name: QAController.java
     * 
     * @Description: 查询二级回复
     * @author 周文斌
     * @date 2015-12-10 下午5:29:13
     * @version 1.0
     * @param model
     * @param request
     * @param id
     * @param types
     * @return
     */
    @RequestMapping("/selTwoAns")
    public String selTwoAns(Model model, HttpServletRequest request, Integer id, String types) {
        log.info("qa：查询二级回复,根据一级回复id：" + id);
        try {
            List<QuestionAnswer> alist = questionAnswerServiceImpl.findEntityTwoAns(id);
            for (QuestionAnswer a : alist) {
                log.info("qa：查询出的二级回复:" + a);
                if (a.getAnswerType().equals("QUESTION_ANSWER_STUDENT")) {
                    UsersFront user = usersFrontServiceImpl.findUsersFrontById(a.getUserId());
                    if (user.getNickName() != null) {
                        a.setName(user.getNickName());
                    } else if (user.getMobile() != null) {
                        a.setName("******" + user.getMobile().substring(7));
                    } else {
                        a.setName(user.getUsername());
                    }
                    if (user.getHeadPicMax() != null) {
                        a.setImgurl(user.getHeadPicMax());
                    }
                } else {
                    SysConfigTeacher teacher = sysConfigTeacherServiceImpl.findByUserId(a.getUserId());
                    if (teacher != null) {
                        a.setName(teacher.getName() != null ? teacher.getName() : ("*******" + teacher.getMobile().substring(7)));
                        if (teacher.getHeadpicUrl() != null) {
                            a.setImgurl(teacher.getHeadpicUrl());
                        }
                    } else {
                        Users user = usersServiceImpl.findUsersById(a.getUserId());
                        a.setName(user.getRealName() != null ? user.getRealName() : ("*******" + user.getMobile().substring(7)));
                        if (user.getHeadPicUrl() != null) {
                            a.setImgurl(user.getHeadPicUrl());
                        }
                    }
                }
                a.setTimes(secToTime(a.getCreateTime().getTime()));
                if (a.getReplyUserId() != null) {
                    if (a.getReplyUserType().equals("QUESTION_ANSWER_STUDENT")) {
                        UsersFront user = usersFrontServiceImpl.findUsersFrontById(a.getReplyUserId());
                        if (user.getNickName() != null) {
                            a.setReplyUserName(user.getNickName());
                        } else if (user.getMobile() != null) {
                            a.setReplyUserName("******" + user.getMobile().substring(7));
                        } else {
                            a.setReplyUserName(user.getUsername());
                        }
                    } else {
                        SysConfigTeacher teacher = sysConfigTeacherServiceImpl.findByUserId(a.getUserId());
                        if (teacher != null) {
                            a.setReplyUserName(teacher.getName() != null ? teacher.getName() : ("*******" + teacher.getMobile().substring(7)));
                        } else {
                            Users user = usersServiceImpl.findUsersById(a.getReplyUserId());
                            a.setReplyUserName(user.getRealName() != null ? user.getRealName() : ("*******" + user.getMobile().substring(7)));
                        }
                    }
                }
            }
            model.addAttribute("alist", alist);
            model.addAttribute("types", types);
            model.addAttribute("imgpath", propertiesUtil.getProjectImageUrl());
        } catch (Exception e) {
            log.error("qa：查询二级回复出错了：" + e.getMessage(), e);
            e.printStackTrace();
        }
        return "queAns/QATwoAjax";
    }

    /**
     * 
     * Class Name: QAController.java
     * 
     * @Description: 时间转换
     * @author 周文斌
     * @date 2015-12-9 下午7:18:59
     * @version 1.0
     * @param time
     * @return
     */
    private static String secToTime(long time) {

        long curtime = new Date().getTime();
        long cha = ((curtime - time) / 1000);
        long m = 60;
        long h = 60 * 60;
        long d = 60 * 60 * 24;
        long yd = 60 * 60 * 24 * 3;
        if (cha < m) {
            return cha + " 秒之前";
        } else if (cha >= m && cha < h) {
            return (cha / m) + " 分钟之前";
        } else if (cha >= h && cha < d) {
            return (cha / h) + " 小时之前";
        } else if (cha >= d && cha < yd) {
            return (cha / d) + " 天之前";
        } else {
            String t = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(time));
            return t;
        }
    }
}
