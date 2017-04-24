package com.yuxin.wx.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.company.ICompanyEmailHistoryService;
import com.yuxin.wx.api.company.ICompanyFunctionSetService;
import com.yuxin.wx.api.company.ICompanyMemberServiceService;
import com.yuxin.wx.api.company.ICompanyMessageHistoryService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.api.company.ICompanyStudentMessageService;
import com.yuxin.wx.api.homework.IHomeworkService;
import com.yuxin.wx.api.homework.IHomeworkStudentCompleteService;
import com.yuxin.wx.api.homework.IHomeworkTeacherReadService;
import com.yuxin.wx.api.student.IStudentService;
import com.yuxin.wx.common.JsonMsg;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.CompanyEmailHistory;
import com.yuxin.wx.model.company.CompanyFunctionSet;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.model.company.CompanyMessageHistory;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.model.company.CompanyStudentMessage;
import com.yuxin.wx.model.homework.Homework;
import com.yuxin.wx.model.homework.HomeworkStudentComplete;
import com.yuxin.wx.model.homework.HomeworkTeacherRead;
import com.yuxin.wx.model.student.Student;
import com.yuxin.wx.model.user.UserMessage;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.util.MailUtil;
import com.yuxin.wx.utils.smsClient.interfacej.SmsClientSend;
import com.yuxin.wx.vo.common.email.Mail;
import com.yuxin.wx.vo.common.email.NoticeEmail;

public class WorkNotify extends GetInjectionClassForSpringContainerUtil {
    private static String url;
    private static String apiUser;
    private static String apiKey;
    private static boolean init = false;

    private static IHomeworkTeacherReadService homeworkTeacherReadImpl;
    private static IHomeworkStudentCompleteService homeworkStudentCompleteImpl;
    private static IHomeworkService homeworkServiceImpl;
    private static ICompanyMemberServiceService companyMemberServiceImpl;
    private static ICompanyServiceStaticService companyServiceStaticImpl;
    private static IStudentService studetnImpl;
    private static ICompanyStudentMessageService companyStudentMessageImpl;
    private static ICompanyMessageHistoryService companyMessageHistoryImpl;
    private static ICompanyService companyServiceImpl;
    private static ICompanyEmailHistoryService companyEmailServiecimpl;
    private static ICompanyFunctionSetService companyFunctionSetServiceImpl;

    static {
        init();
    }

    /**
     * 
     * Class Name: WorkNotice.java
     * 
     * @Description: 发送通知
     * @author 周文斌
     * @date 2016-12-15 下午6:17:45
     * @modify 2016-12-15 下午6:17:45
     * @version
     * @param id
     *            （homework_teacher_read）
     * @return (json.code)
     *         0:全部发送成功,1:短信-邮件发送成功,2:邮件-站内信发送成功,3:短信-站内信发送成功,4:短信发送成功,5:邮件发送成功,6:站内信发送成功,7:全部失败
     */
    public static JSONObject notify(HttpServletRequest request, Integer id) {
        if (init) {
            init();
        }
        JSONObject json = new JSONObject();
        Integer companyId = WebUtils.getCurrentCompanyId();
        Users user = WebUtils.getCurrentUser();

        HomeworkTeacherRead htr = homeworkTeacherReadImpl.findHomeworkTeacherReadById(id);
        HomeworkStudentComplete hsc = homeworkStudentCompleteImpl.findHomeworkStudentCompleteById(htr.getHomeworkSId());
        Homework work = homeworkServiceImpl.findHomeworkById(htr.getHomeworkId());
        CompanyMemberService cms = companyMemberServiceImpl.findByCompanyId(companyId);
        CompanyServiceStatic css = companyServiceStaticImpl.findByCompanyId(companyId);
        Company company = companyServiceImpl.findCompanyById(companyId);

        CompanyFunctionSet companyFunctionSet = new CompanyFunctionSet();
        companyFunctionSet.setCompanyId(companyId);
        companyFunctionSet.setFunctionCode("HOMEWORK_MESSAGE_INFORM");
        List<CompanyFunctionSet> companyFunctionSetList = companyFunctionSetServiceImpl.findCompanyFunctionSetByPage(companyFunctionSet);
        CompanyFunctionSet msgset = companyFunctionSetList != null && companyFunctionSetList.size() > 0 ? companyFunctionSetList.get(0) : null;

        companyFunctionSet = new CompanyFunctionSet();
        companyFunctionSet.setCompanyId(companyId);
        companyFunctionSet.setFunctionCode("HOMEWORK_EMAIL_INFORM");
        companyFunctionSetList = companyFunctionSetServiceImpl.findCompanyFunctionSetByPage(companyFunctionSet);
        CompanyFunctionSet emailset = companyFunctionSetList != null && companyFunctionSetList.size() > 0 ? companyFunctionSetList.get(0) : null;

        Student stu = studetnImpl.findStudentById(hsc.getStuId());
        String msg = "";
        boolean msgb = true;
        boolean emailb = true;
        boolean noticeb = true;
        Date date = new Date();
        if (msgset != null && "1".equals(msgset.getStatus())) {
            try {
                // 发送短信通知
                if (stu.getMobile() != null) {
                    String content = "亲爱的" + stu.getName() + ",您的" + work.getCourseName() + "课后作业" + "有了最新的动态,请您注意查看";
                    int msgcount = ((cms.getMessageTotal() != null ? cms.getMessageTotal() : 0) + (cms.getGiveMessage() != null ? cms.getGiveMessage() : 0));
                    int msguse = (css.getMessageSend() != null ? css.getMessageSend() : 0);
                    if ((msgcount - msguse) <= 0) {
                        msg += "短信用完发送失败;";
                        msgb = false;
                    } else {
                        Integer messageCost = content.length() % 70 == 0 ? content.length() / 70 : (content.length() / 70) + 1;
                        CompanyStudentMessage csm = new CompanyStudentMessage();
                        csm.setContent(content);
                        csm.setTitle("作业批阅通知");
                        csm.setMessageType("STUDENT_MESSAGE_OTHER");
                        csm.setCreator(user.getId());
                        csm.setCreatorName(user.getRealName() != null ? user.getRealName() : user.getUsername());
                        csm.setCreateTime(date);
                        csm.setCompanyId(companyId);
                        csm.setSchoolId(user.getSchoolId());
                        csm.setMessageCost(messageCost);

                        String res = SmsClientSend.sendSmsTwo(request, stu.getMobile(), content + "【在线网校】", stu.getUserId(), "sys-notice");

                        String status = res.substring(res.indexOf("<returnstatus>"), res.indexOf("</returnstatus>"));
                        status = status.substring(status.indexOf(">") + 1);
                        String message = res.substring(res.indexOf("<message>"), res.indexOf("</message>"));
                        message = message.substring(message.indexOf(">") + 1);
                        if (message.equals("ok")) {
                            message = "发送成功";
                            msg += "短信发送成功;";
                        } else {
                            msg += message + ";";
                            msgb = false;
                        }
                        CompanyMessageHistory cmh = new CompanyMessageHistory();
                        cmh.setReceiverUserId("" + stu.getId());
                        cmh.setReceiverMobile(stu.getMobile().trim());
                        cmh.setContent(content);
                        cmh.setSendTime(date);
                        if (status.equals("Success")) {
                            cmh.setSendStatus(1);
                            csm.setSendNum(1);
                            csm.setFailNum(0);
                        } else {
                            cmh.setSendStatus(0);
                            csm.setSendNum(0);
                            csm.setFailNum(1);
                        }
                        csm.setMessageStatus("STUDENT_MESSAGE_FINISH");
                        companyStudentMessageImpl.insert(csm);

                        cmh.setSendResult(message);
                        cmh.setBusinessType("BUSINESS_STUDENT_MESSAGE");
                        cmh.setCompanyId(companyId);
                        cmh.setSchoolId(user.getSchoolId());
                        cmh.setCostNum(messageCost);
                        cmh.setMessageId(csm.getId());
                        companyMessageHistoryImpl.insert(cmh);
                    }
                } else {
                    msg += "学员没有手机号;";
                    msgb = false;
                }
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
                msg += "短信发送异常;";
                msgb = false;
            }
        }

        try {
            // 发送站内信
            String content = "亲爱的" + stu.getName() + "学员，您好：<br>" + "&nbsp;&nbsp;&nbsp;" + "&nbsp;您的老师对您的" + work.getCourseName() + "课后作业" + "做出了评阅，请您注意查看";
            CompanyStudentMessage csm = new CompanyStudentMessage();
            csm.setContent(content);
            csm.setTitle("您的作业有最新动态");
            csm.setMessageType("STUDENT_MESSAGE_OTHER");
            csm.setCreator(user.getId());
            csm.setCreatorName(user.getRealName() != null ? user.getRealName() : user.getUsername());
            csm.setCreateTime(date);
            csm.setCompanyId(companyId);
            csm.setSchoolId(user.getSchoolId());
            csm.setSendNum(1);
            csm.setFailNum(0);
            csm.setMessageStatus("STUDENT_MESSAGE_FINISH");
            companyStudentMessageImpl.insert(csm);

            UserMessage um = new UserMessage();
            um.setUserId(stu.getUserId());
            um.setMessageId(csm.getId());
            um.setReadFlag(0);
            companyStudentMessageImpl.insertUserMessage(um);
            msg += "站内信发送完成;";
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            msg += "站内信发送异常;";
            noticeb = false;
        }

        if (emailset != null && "1".equals(emailset.getStatus())) {
            try {
                if (stu.getEmail() != null) {
                    String content = "亲爱的" + stu.getName() + "，您的" + work.getCourseName() + "课后作业有了最新的动态，请您注意查看";
                    int emailCount = ((cms.getEmailTotal() != null ? cms.getEmailTotal() : 0) + (cms.getGiveEmail() != null ? cms.getGiveEmail() : 0));
                    int emailUse = (css.getEmailSend() != null ? css.getEmailSend() : 0);
                    if ((emailCount - emailUse) <= 0) {
                        msg += "邮件已用完";
                        emailb = false;
                    } else {
                        String con = "<p>" + content + "</p>";
                        // 邮件通知
                        CompanyStudentMessage csm = new CompanyStudentMessage();
                        csm.setContent(con);
                        csm.setTitle("作业批阅通知");
                        csm.setEmailTitle("您的" + work.getCourseName() + "课后作业有最新动态");
                        csm.setMessageType("STUDENT_MESSAGE_OTHER");
                        csm.setCreator(user.getId());
                        csm.setCreatorName(user.getRealName() != null ? user.getRealName() : user.getUsername());
                        csm.setCreateTime(date);
                        csm.setCompanyId(companyId);
                        csm.setSchoolId(user.getSchoolId());
                        csm.setMessageStatus("STUDENT_MESSAGE_FINISH");

                        Mail mail = new Mail();
                        mail.setFrom("contact@yuuxin.com");
                        mail.setFromName(company.getCompanyName());
                        mail.setSubject(csm.getEmailTitle());
                        mail.setTo(stu.getEmail());
                        // 邮件模板
                        NoticeEmail model = new NoticeEmail();
                        model.setMsg(con);

                        MailUtil mu = new MailUtil(mail, model);
                        String res = mu.sendmail(url, apiUser, apiKey, MailUtil.NOTICE);

                        CompanyEmailHistory cmh = new CompanyEmailHistory();
                        cmh.setReceiverEmail(stu.getEmail());
                        cmh.setReceiverUserId(stu.getUserId());
                        cmh.setContent(con);
                        cmh.setSendTime(date);

                        if (res.equals("success")) {
                            cmh.setSendStatus(1);
                            msg += "邮件发送完成;";
                        } else {
                            cmh.setSendStatus(0);
                            msg += "邮件发送失败;";
                            emailb = false;
                        }
                        companyStudentMessageImpl.insert(csm);

                        cmh.setSendResult(res);
                        cmh.setBusinessType("BUSINESS_STUDENT_NOTICE");
                        cmh.setCompanyId(companyId);
                        cmh.setSchoolId(user.getSchoolId());
                        cmh.setTitle(csm.getEmailTitle());
                        cmh.setMessageId(csm.getId());
                        companyEmailServiecimpl.insert(cmh);
                    }
                } else {
                    msg += "学员没有邮箱;";
                    emailb = false;
                }
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
                msg += "邮件发送异常;";
                emailb = false;
            }
        }

        if (msgb && emailb && noticeb) {
            json.put(JsonMsg.CODE, JsonMsg.CODE_0);
        } else if (msgb && emailb) {
            json.put(JsonMsg.CODE, JsonMsg.CODE_1);
            json.put(JsonMsg.MSG, msg);
        } else if (emailb && noticeb) {
            json.put(JsonMsg.CODE, JsonMsg.CODE_2);
            json.put(JsonMsg.MSG, msg);
        } else if (msgb && noticeb) {
            json.put(JsonMsg.CODE, JsonMsg.CODE_3);
            json.put(JsonMsg.MSG, msg);
        } else if (msgb) {
            json.put(JsonMsg.CODE, JsonMsg.CODE_4);
            json.put(JsonMsg.MSG, msg);
        } else if (emailb) {
            json.put(JsonMsg.CODE, JsonMsg.CODE_5);
            json.put(JsonMsg.MSG, msg);
        } else if (noticeb) {
            json.put(JsonMsg.CODE, JsonMsg.CODE_6);
            json.put(JsonMsg.MSG, msg);
        } else {
            json.put(JsonMsg.CODE, JsonMsg.CODE_7);
            json.put(JsonMsg.MSG, msg);
        }
        return json;
    }

    private static void init() {
        Properties prop = new Properties();
        InputStream in = MailUtil.class.getClassLoader().getResourceAsStream("config.properties");
        try {
            prop.load(in);
            url = prop.getProperty("url");
            apiUser = prop.getProperty("apiUser");
            apiKey = prop.getProperty("apiKey");

            homeworkTeacherReadImpl = application.getBean(IHomeworkTeacherReadService.class);
            homeworkStudentCompleteImpl = application.getBean(IHomeworkStudentCompleteService.class);
            homeworkServiceImpl = application.getBean(IHomeworkService.class);
            companyMemberServiceImpl = application.getBean(ICompanyMemberServiceService.class);
            companyServiceStaticImpl = application.getBean(ICompanyServiceStaticService.class);
            studetnImpl = application.getBean(IStudentService.class);
            companyStudentMessageImpl = application.getBean(ICompanyStudentMessageService.class);
            companyStudentMessageImpl = application.getBean(ICompanyStudentMessageService.class);
            companyMessageHistoryImpl = application.getBean(ICompanyMessageHistoryService.class);
            companyServiceImpl = application.getBean(ICompanyService.class);
            companyEmailServiecimpl = application.getBean(ICompanyEmailHistoryService.class);
            companyFunctionSetServiceImpl = application.getBean(ICompanyFunctionSetService.class);

            init = false;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            init = true;
        }
    }
}
