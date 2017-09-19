package com.yuxin.wx.controller.student;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.classes.IClassModuleLessonService;
import com.yuxin.wx.api.classes.IClassModuleNoService;
import com.yuxin.wx.api.classes.IClassModuleService;
import com.yuxin.wx.api.classes.IClassPackageService;
import com.yuxin.wx.api.classes.IClassTypeModuleRelationService;
import com.yuxin.wx.api.classes.IClassTypeService;
import com.yuxin.wx.api.commodity.ICommodityProductRealtionService;
import com.yuxin.wx.api.commodity.ICommodityService;
import com.yuxin.wx.api.company.ICompanyCashFlowService;
import com.yuxin.wx.api.company.ICompanyFunctionSetService;
import com.yuxin.wx.api.company.ICompanyMemberServiceService;
import com.yuxin.wx.api.company.ICompanyMessageHistoryService;
import com.yuxin.wx.api.company.ICompanyRegisterConfigService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.api.course.ICourseVideoChapterService;
import com.yuxin.wx.api.course.ICourseVideoLectureService;
import com.yuxin.wx.api.homework.IHomeworkService;
import com.yuxin.wx.api.pay.IPayOrderService;
import com.yuxin.wx.api.student.IStudentAgentMaterialService;
import com.yuxin.wx.api.student.IStudentFeeStageService;
import com.yuxin.wx.api.student.IStudentPayMasterService;
import com.yuxin.wx.api.student.IStudentPaySlaveService;
import com.yuxin.wx.api.student.IStudentService;
import com.yuxin.wx.api.system.ISysConfigServiceService;
import com.yuxin.wx.api.system.ISysConfigTermService;
import com.yuxin.wx.api.user.IUserLessonTimeService;
import com.yuxin.wx.api.user.IUsersFrontService;
import com.yuxin.wx.api.weixin.IWeiXinService;
import com.yuxin.wx.common.Constant;
import com.yuxin.wx.common.JsonMsg;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.classes.ClassModuleLesson;
import com.yuxin.wx.model.classes.ClassModuleNo;
import com.yuxin.wx.model.classes.ClassType;
import com.yuxin.wx.model.commodity.Commodity;
import com.yuxin.wx.model.commodity.CommodityProductRealtion;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.CompanyCashFlow;
import com.yuxin.wx.model.company.CompanyFunctionSet;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.model.company.CompanyMessageHistory;
import com.yuxin.wx.model.company.CompanyRegisterConfig;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.model.pay.PayOrder;
import com.yuxin.wx.model.student.Student;
import com.yuxin.wx.model.student.StudentAgentMaterial;
import com.yuxin.wx.model.student.StudentFeeRefund;
import com.yuxin.wx.model.student.StudentFeeStage;
import com.yuxin.wx.model.student.StudentPayChangeInfo;
import com.yuxin.wx.model.student.StudentPayMaster;
import com.yuxin.wx.model.student.StudentPaySlave;
import com.yuxin.wx.model.system.SysConfigService;
import com.yuxin.wx.model.system.SysConfigTeacher;
import com.yuxin.wx.model.system.SysConfigTerm;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.model.user.UsersFront;
import com.yuxin.wx.pay.mapper.PayOrderMapper;
import com.yuxin.wx.util.DateUtil;
import com.yuxin.wx.utils.EntityUtil;
import com.yuxin.wx.utils.FileUtil;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.utils.smsClient.interfacej.SmsClientSend;
import com.yuxin.wx.vo.classes.ClassmNoVo;
import com.yuxin.wx.vo.student.StuPayMasterVo;
import com.yuxin.wx.vo.student.StudentPaySlaveVo;

/**
 * Controller of StudentPayMaster
 *
 * @author wang.zx
 * @date 2014-12-5
 */
@Controller
@RequestMapping("/studentPayMaster")
public class StudentPayMasterController {

    Log log = LogFactory.getLog("log");
    Log log_student = LogFactory.getLog("student");

    @Autowired
    private IStudentPayMasterService studentPayMasterServiceImpl;
    @Autowired
    private IStudentAgentMaterialService studentAgentMaterialServiceImpl;
    @Autowired
    private IStudentPaySlaveService studentPaySlaveServiceImpl;
    @Autowired
    private IStudentFeeStageService studentFeeStageServiceImpl;
    @Autowired
    private IStudentService studentServiceImpl;
    @Autowired
    private IUsersFrontService usersFrontServiceImpl;
    @Autowired
    private IClassTypeService classTypeServiceImpl;
    @Autowired
    private IPayOrderService payOrderServiceImpl;
    @Autowired
    private IClassModuleService classModuleServiceImpl;
    @Autowired
    private ICommodityService commodityServiceImpl;
    @Autowired
    private ICompanyMessageHistoryService companyMessageHistoryServiceImpl;
    @Autowired
    private IClassModuleNoService classModuleNoServiceImpl;
    @Autowired
    private ICommodityProductRealtionService commodityProductRealtionServiceImpl;
    @Autowired
    private ISysConfigServiceService SysConfigServiceImpl;

    @Autowired
    private ISysConfigTermService sysConfigTermServiceImpl;
    @Autowired
    private ICompanyFunctionSetService companyFunctionSetServiceImpl;
    @Autowired
    private ICompanyCashFlowService companyCashFlowServiceImpl;
    @Autowired
    private ICompanyRegisterConfigService companyRegisterConfigServiceImpl;
    @Autowired
    private PayOrderMapper payOrderMapper;
    @Autowired
    private IClassTypeModuleRelationService classTypeModuleRelationServiceImpl;
    @Autowired
    private ICourseVideoChapterService courseVideoChapterServiceImpl;
    @Autowired
    private ICourseVideoLectureService courseVideoLectureServiceImpl;
    @Autowired
    private IUserLessonTimeService userLessonTimeServiceImpl;

    @Autowired
    private ICompanyMemberServiceService companyMemberServiceServiceImpl;
    @Autowired
    private ICompanyServiceStaticService companyServiceStaticServiceImpl;
    @Autowired
    private ICompanyService companyService;
    @Autowired
    private IHomeworkService homeworkServiceImpl;
    @Autowired
    private IClassPackageService classPackageServiceImpl;
    @Autowired
    private IClassModuleLessonService classModuleLessonServiceImpl;
    @Autowired
    private IWeiXinService weiXinServiceImpl;
    
    /**
     *
     * Class Name: StudentPayMasterController.java
     *
     * @Description: 提交表单的方式获取学员信�?
     * @author Chopin
     * @date 2014�?12�?12�? 下午3:33:58
     * @version 1.0
     * @param model
     * @param search
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model, StudentPayMaster search) {
        if (EntityUtil.isNotBlank(search)) {
            search.setPageSize(100);
            search.setSchoolId(WebUtils.getCurrentSchoolId());
            search.setCompanyId(WebUtils.getCurrentCompanyId());
            model.addAttribute("list", this.studentPayMasterServiceImpl.findStudentPayMasterByPage(search));
        }
        return "student/queryByDate";
    }

    /**
     *
     * Class Name: StudentPayMasterController.java
     *
     * @Description: 提交表单的方式获取学员信�?
     * @author Chopin
     * @date 2014�?12�?12�? 下午3:33:58
     * @version 1.0
     * @param model
     * @param search
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryList/{stuId}", method = RequestMethod.POST)
    public List<StudentPayMaster> query(Model model, @PathVariable Integer stuId) {
        List<StudentPayMaster> pay = new ArrayList<StudentPayMaster>();
        if (stuId != null) {
            pay = this.studentPayMasterServiceImpl.findStudentPayMasterByStuId(stuId, WebUtils.getCurrentSchoolId());
        }
        return pay;
    }

    /**
     *
     * Class Name: StudentPayMasterController.java
     *
     * @Description: 查询学员未缴费的订单
     * @author Chopin
     * @date 2014�?12�?12�? 下午3:33:58
     * @version 1.0
     * @param model
     * @param search
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryUnpay", method = RequestMethod.POST)
    public List<StudentPayMaster> queryUnPay(Model model, Student student) {
        List<StudentPayMaster> pay = new ArrayList<StudentPayMaster>();
        student.setCompanyId(WebUtils.getCurrentCompanyId());
        pay = this.studentPayMasterServiceImpl.findUnPayMasterByStuId(student);
        return pay;
    }

    /**
     *
     * Class Name: StudentPayMasterController.java
     *
     * @Description: ajax方式加载学员信息列表
     * @author Chopin
     * @date 2014�?12�?12�? 下午3:33:04
     * @version 1.0
     * @param search
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/ajaxlist", method = RequestMethod.GET)
    public List<StudentPayMaster> list(StudentPayMaster search) {
        List<StudentPayMaster> al = new ArrayList<StudentPayMaster>();
        if (EntityUtil.isNotBlank(search)) {
            search.setPageSize(100);
            search.setSchoolId(WebUtils.getCurrentSchoolId());
            search.setCompanyId(WebUtils.getCurrentCompanyId());
            al = this.studentPayMasterServiceImpl.findStudentPayMasterByPage(search);
        }
        return al;
    }

    /**
     *
     * Class Name: StudentPayMasterController.java
     *
     * @Description: 报名，添加订单主逻辑
     * @author Chopin
     * @date 2014�?12�?12�? 下午3:33:04
     * @version 1.0
     * @param search
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/payMaster", method = RequestMethod.POST)
    public String PayMaster(HttpServletRequest request, StudentPayMaster payMaster) {
        JSONObject.parseArray(request.getParameter("slaves"), com.yuxin.wx.model.student.StudentPaySlave.class);
        JSONObject.parseArray(request.getParameter("videos"), com.yuxin.wx.model.course.CourseVideo.class);
        JSONObject.parseArray(request.getParameter("material"), com.yuxin.wx.model.student.StudentAgentMaterial.class);
        JSONObject.parseArray(request.getParameter("stage"), com.yuxin.wx.model.student.StudentFeeStage.class);
        WebUtils.getCurrentUser(request);

        return JsonMsg.SUCCESS;
    }

    /**
     *
     * Class Name: StudentPayMasterController.java
     *
     * @Description: �?查学员是否重复报名班�?
     * @author Chopin
     * @date 2014�?12�?12�? 下午3:33:04
     * @version 1.0
     * @param search
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/checkPayMaster", method = RequestMethod.POST)
    public Boolean checkPayMaster(HttpServletRequest request, StudentPayMaster payMaster) {
        payMaster.setCompanyId(WebUtils.getCurrentCompanyId());
        payMaster.setSchoolId(WebUtils.getCurrentSchoolId());
        List<StudentPayMaster> list = this.studentPayMasterServiceImpl.findByClassTypeAndStu(payMaster);
        if (list != null && list.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * Class Name: StudentPayMasterController.java
     *
     * @Description: 报名，添加订单主逻辑
     * @author Chopin
     * @date 2014�?12�?12�? 下午3:33:04
     * @version 1.0
     * @param search
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/savePayMaster", method = RequestMethod.POST)
    public StudentPayMaster savePayMaster(HttpServletRequest request, StudentPayMaster payMaster) {
        this.log_student.info("===");
        // 查询公司注册设置
        CompanyRegisterConfig crc = new CompanyRegisterConfig();
        crc.setCompanyId(WebUtils.getCurrentCompanyId());
        crc = this.companyRegisterConfigServiceImpl.queryByCompanyId(crc);
        payMaster.setSchoolId(WebUtils.getCurrentSchoolId());
        Student student = JSONObject.parseObject(request.getParameter("student"), Student.class);

        // 报�?�材�?
        List<StudentAgentMaterial> materials = JSONObject.parseArray(request.getParameter("materials"), com.yuxin.wx.model.student.StudentAgentMaterial.class);
        // 分期
        List<StudentFeeStage> stages = JSONObject.parseArray(request.getParameter("stage"), com.yuxin.wx.model.student.StudentFeeStage.class);

        Users user = WebUtils.getCurrentUser(request);
        payMaster.setStuId(student.getId());
        // 建立订单
        Boolean exists = this.studentPayMasterServiceImpl.savePayMaster(payMaster, user, materials, stages);
        if (!exists) {
            payMaster.setMessage("该课程没有班号，请先建立班号再报名�??");
            this.log_student.error(">>> [报名] " + "状�?�：success" + ", 信息�?" + "公司ID = " + WebUtils.getCurrentCompanyId() + ", 操作人ID = "
                    + WebUtils.getCurrentUserId(request) + ", 学生ID = " + student.getId() + ", 订单ID = " + payMaster.getId() + ", 课程ClassTypeID = "
                    + payMaster.getCommodityId() + ", 课程名称 = " + payMaster.getClassTypeName());
            return payMaster;
        }
        // 查询学员是否购买过此课程
        List<StudentPayMaster> list = this.studentPayMasterServiceImpl.findByClassTypeAndStu(payMaster);
        if (list != null && list.size() <= 1) {
            // 更新商品表购买人�?
            CommodityProductRealtion cpr = this.commodityProductRealtionServiceImpl.findByClassTypeId("" + payMaster.getCommodityId());
            this.commodityServiceImpl.updateBuyNumById(cpr.getComId());
        }
        // 更新前台用户�?
        UsersFront userfront = new UsersFront();
        userfront.setCompanyId(WebUtils.getCurrentCompanyId());
        userfront.setEmail(student.getEmail());
        userfront.setUsername(student.getUsername());
        userfront.setMobile(student.getMobile());
        userfront.setRegistTime(new Date());
        userfront.setStatus(1);
        userfront.setInterests("" + payMaster.getItemOneId());
        userfront.setVipFlag(1);
        userfront.setRegistType(2);
        userfront.setSchoolId(payMaster.getSchoolId());
        if (null != student.getMobile() && !"".equals(student.getMobile())) {
            userfront.setPassword(new Md5Hash(student.getMobile().substring(student.getMobile().length() - 6)).toHex());
        } else {
            userfront.setPassword(new Md5Hash("111111").toHex());
        }
        UsersFront u = new UsersFront();
        if (null != student.getUserId()) {
            u = this.usersFrontServiceImpl.findUsersFrontById(student.getUserId());
        } else {
            u = this.usersFrontServiceImpl.findUsersFrontByMobile(userfront);
        }
        // if(null!=crc && null!=crc.getUsernameFlag() &&
        // crc.getUsernameFlag()==1 && crc.getMobileFlag()!=1){
        // u=usersFrontServiceImpl.findUsersFrontById(student.getUserId());
        // }else{
        // u=usersFrontServiceImpl.findUsersFrontByMobile(userfront);
        // }

        // �?查报名完成是否要发短�?
        CompanyFunctionSet cfs = new CompanyFunctionSet();
        cfs.setStatus("1");
        cfs.setCompanyId(WebUtils.getCurrentCompanyId());
        cfs.setFunctionCode("COMPANY_FUNCTION_APPLY");
        // 获取用户短信配置
        List<CompanyFunctionSet> set = this.companyFunctionSetServiceImpl.findCompanyFunctionSet(cfs);
        Boolean needSendSms = false;
        Boolean companyMessageCount = false;
        String sms = "";
        for (CompanyFunctionSet s : set) {
            if ("COMPANY_FUNCTION_APPLY".equals(s.getFunctionCode())) {
                if ("1".equals(s.getStatus())) {
                    needSendSms = true;
                    if (StringUtils.isNotBlank(s.getContent())) {
                        sms = s.getContent().replace("【coursename�?", payMaster.getClassTypeName()) + "【在线网校�??";
                    }
                }
            }
        }
        if (needSendSms) {
            CompanyMemberService cms = this.companyMemberServiceServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
            CompanyServiceStatic css = this.companyServiceStaticServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
            Integer buy = cms.getMessageTotal() == null ? 0 : cms.getMessageTotal();
            Integer giv = cms.getGiveMessage() == null ? 0 : cms.getGiveMessage();
            Integer cos = css.getMessageSend() == null ? 0 : css.getMessageSend();
            if (((buy + giv) - cos) > 0) {
                companyMessageCount = true;
            }
        }
        if (needSendSms && companyMessageCount) {
            try {
                if (null != student && null != student.getMobile() && !"".equals(student.getMobile())) {// 2016/7/7
                                                                                                        // 无手机号则不发�?�短�?
                    // 发�?�用户配置的短信内容
                    // String send = SmsClientSend.sendSms(student.getMobile(),
                    // sms);
                    String send = SmsClientSend.sendSmsTwo(request, student.getMobile(), sms, student.getUserId(), "sys-notice");
                    int returnstatusSIndex = send.indexOf("<returnstatus>");
                    int returnstatusEIndex = send.indexOf("</returnstatus>");
                    String sendMessageStatus = send.substring((returnstatusSIndex + 14), returnstatusEIndex);
                    // 记录短信发�?�历�?
                    CompanyMessageHistory history = new CompanyMessageHistory();
                    history.setCompanyId(WebUtils.getCurrentCompanyId());

                    history.setContent(sms);
                    history.setBusinessType("BUSINESS_USER_REGIST");
                    history.setCostNum(1);
                    history.setReceiverMobile(student.getMobile());
                    history.setReceiverUserId("" + userfront.getId());
                    history.setSchoolId(WebUtils.getCurrentSchoolId());
                    history.setSendResult(send);
                    history.setSendTime(new Date());
                    if ("Faild".equals(sendMessageStatus)) {
                        history.setSendStatus(0);
                    } else {
                        history.setSendStatus(1);
                    }
                    history.setSendStatus(1);
                    this.companyMessageHistoryServiceImpl.insert(history);
                }
            } catch (Exception e) {
                this.log_student.error(">>> [报名] " + "状�?�：短信发�?�失�?" + ", 信息�?" + "公司ID = " + WebUtils.getCurrentCompanyId() + ", 操作人ID = "
                        + WebUtils.getCurrentUserId(request) + ", 学生ID = " + student.getId() + ", 订单ID = " + payMaster.getId() + ", 课程ClassTypeID = "
                        + payMaster.getCommodityId() + ", 课程名称 = " + payMaster.getClassTypeName() + ", 是否�?要发短信 = " + needSendSms + ", sms = " + sms, e);
                e.printStackTrace();
            }
        }
        if (u == null || u.getId() == null) {
            this.usersFrontServiceImpl.insert(userfront);
            // 更新用户
            student.setUserId(userfront.getId());
            this.studentServiceImpl.update(student);

        } else {
            u.setVipFlag(1);
            u.setInterests("" + payMaster.getItemOneId());
            this.usersFrontServiceImpl.update(u);
            // 更新用户
            student.setUserId(u.getId());
            this.studentServiceImpl.update(student);
        }
        Commodity comm = commodityServiceImpl.findCommodityById(payMaster.getCommodityId());
        sendWXTemplate(comm, student,u);//发�?�短信模�?
        this.log_student.info(">>> [报名] " + "状�?�：success" + ", 信息�?" + "公司ID = " + WebUtils.getCurrentCompanyId() + ", 操作人ID = "
                + WebUtils.getCurrentUserId(request) + ", 学生ID = " + student.getId() + ", 订单ID = " + payMaster.getId() + ", 课程ClassTypeID = "
                + payMaster.getCommodityId() + ", 课程名称 = " + payMaster.getClassTypeName() + ", 是否�?要发短信 = " + needSendSms + ", sms = " + sms);
        return payMaster;
    }

    /**
     *
     * Class Name: StudentPayMasterController.java
     *
     * @Description: 报名，添加订单主逻辑
     * @author Chopin
     * @date 2014�?12�?12�? 下午3:33:04
     * @version 1.0
     * @param search
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/full/savePayMaster", method = RequestMethod.POST)
    public StudentPayMaster fullSavePayMaster(HttpServletRequest request, StudentPayMaster payMaster) {
        this.log_student.info("===");
        payMaster.setSchoolId(WebUtils.getCurrentSchoolId());
        List<StudentPaySlave> slaves = JSONObject.parseArray(request.getParameter("slaves"), com.yuxin.wx.model.student.StudentPaySlave.class);
        Users user = WebUtils.getCurrentUser(request);
        StudentPayMaster master = this.studentPayMasterServiceImpl.findStudentPayMasterById(payMaster.getId());
        if (master != null && master.getId() != null) {
            this.studentPayMasterServiceImpl.updatePayMaster(payMaster, user, slaves);
        } else {
            this.studentPayMasterServiceImpl.newPayMaster(payMaster, user, slaves);
        }
        // 更新考期
        SysConfigTerm search = new SysConfigTerm();
        search.setCompanyId(WebUtils.getCurrentCompanyId());
        search.setItemOneId(payMaster.getItemOneId());
        search.setTermName(payMaster.getExamTermName());
        search.setSchoolId(WebUtils.getCurrentSchoolId());
        List<SysConfigTerm> terms = this.sysConfigTermServiceImpl.findTermByName(search);
        if (terms != null && terms.size() > 0) {
            payMaster.setExamTermId(terms.get(0).getId());
        } else {
            this.sysConfigTermServiceImpl.insert(search);
            payMaster.setExamTermId(search.getId());
        }
        List<StudentAgentMaterial> materials = JSONObject.parseArray(request.getParameter("materials"), com.yuxin.wx.model.student.StudentAgentMaterial.class);
        StudentPayMaster payMaster2 = this.studentPayMasterServiceImpl.findStudentPayMasterById(payMaster.getId());
        // �?查是否已经有记录�?
        List<StudentAgentMaterial> ms = this.studentAgentMaterialServiceImpl.findStudentAgentMaterialByStuId(payMaster2.getStuId(), payMaster2.getId());
        if (ms.size() > 0) {
            if (materials != null) {
                for (StudentAgentMaterial m : materials) {
                    m.setCompanyId(WebUtils.getCurrentCompanyId());
                    m.setCreateTime(new Date());
                    m.setCreator(WebUtils.getCurrentUserId(request));
                    this.studentAgentMaterialServiceImpl.update(m);
                }
            } else {
                materials = new ArrayList<StudentAgentMaterial>();
            }
            payMaster2.setUpdateTime(new Date());
            payMaster2.setUpdator("" + WebUtils.getCurrentUserId(request));
            payMaster2.setIsAgent(payMaster.getIsAgent());
            payMaster2.setIsAgentMaterialComplete(payMaster.getIsAgentMaterialComplete());
            payMaster2.setAgentRemark(payMaster.getAgentRemark());
            this.studentPayMasterServiceImpl.update(payMaster2);
        } else {
            // 没有则新�?
            if (materials != null) {
                for (StudentAgentMaterial m : materials) {
                    m.setCompanyId(WebUtils.getCurrentCompanyId());
                    m.setCreateTime(new Date());
                    m.setCreator(WebUtils.getCurrentUserId(request));
                    m.setPayMasterId(payMaster2.getId());
                    this.studentAgentMaterialServiceImpl.insertMaterial(m);
                }
            } else {
                materials = new ArrayList<StudentAgentMaterial>();
            }
            payMaster2.setUpdateTime(new Date());
            payMaster2.setUpdator("" + WebUtils.getCurrentUserId(request));
            payMaster2.setIsAgent(payMaster.getIsAgent());
            payMaster2.setIsAgentMaterialComplete(payMaster.getIsAgentMaterialComplete());
            payMaster2.setAgentRemark(payMaster.getAgentRemark());
            this.studentPayMasterServiceImpl.update(payMaster2);
        }
        this.log_student.info(">>> [报名-复杂版] " + "状�?�：success" + ", 信息�?" + "公司ID = " + WebUtils.getCurrentCompanyId() + ", 操作人ID = "
                + WebUtils.getCurrentUserId(request) + ", 学生ID = " + payMaster.getStuId() + ", 订单ID = " + payMaster.getId() + ", 课程ClassTypeID = "
                + payMaster.getCommodityId() + ", 课程名称 = " + payMaster.getClassTypeName());
        return payMaster;
    }

    /**
     *
     * @fileName : StudentPayMasterController.java
     * @date : 2015�?10�?20�? 下午2:25:22
     * @author : 杨延�?
     * @description :
     */
    @ResponseBody
    @RequestMapping(value = "/savePayMasterMany")
    public JSONObject savePayMasterMany(HttpServletRequest request, StudentPayMaster payMaster) {
        this.log_student.info("===");
        JSONObject json = new JSONObject();

        payMaster.setSchoolId(WebUtils.getCurrentSchoolId());
        // 查询公司注册设置
        CompanyRegisterConfig crc = new CompanyRegisterConfig();
        crc.setCompanyId(WebUtils.getCurrentCompanyId());
        crc = this.companyRegisterConfigServiceImpl.queryByCompanyId(crc);
        List<Student> studentlist = JSONObject.parseArray(request.getParameter("student"), Student.class);

        Company company = this.companyService.findCompanyById(WebUtils.getCurrentCompanyId());
        if (null == company.getServiceVersion() || "USER_COUNT".equals(company.getServiceVersion()) || "".equals(company.getServiceVersion())) {// 在线人数版不控制报名
            // 剩余招生学院数量
            CompanyMemberService cms = this.companyMemberServiceServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
            Integer stuCount = this.studentPayMasterServiceImpl.findStuCountByCompanyId(WebUtils.getCurrentCompanyId());

            int count = cms.getFaceStudentAll() - stuCount;

            int countTemp = 0;
            String studentNames = "";
            for (Student student : studentlist) {
                UsersFront uf = this.usersFrontServiceImpl.findUserFrontByStudentId(student.getId());
                if (null != uf && null != uf.getVipFlag() && !"".equals(uf.getVipFlag())) {
                    if (1 != uf.getVipFlag()) {
                        countTemp++;
                        studentNames += student.getId() + "-" + student.getName() + ",";
                    }
                } else {
                    countTemp++;
                    studentNames += student.getId() + "-" + student.getName() + ",";
                }
            }
            if (countTemp > count) {
                json.put("status", "error");
                json.put("result", "可招生学员超上限");
                json.put("wantNum", countTemp);
                json.put("wantList", studentNames);
                json.put("allNum", count);

                this.log_student.error(">>> [批量报名] " + "状�?�：error" + ", 问题：可招生学员超上�?" + ", 信息�?" + "公司ID = " + WebUtils.getCurrentCompanyId() + ", 操作人ID = "
                        + WebUtils.getCurrentUserId(request) + ", 课程ClassTypeID = " + payMaster.getCommodityId() + ", 课程名称 = " + payMaster.getClassTypeName()
                        + ", 报名人数 = " + studentlist.size() + ", 报名List = " + studentlist + ", 新增报名�?(未报过名) = " + countTemp + ", 新增报名�?(未报过名) = " + studentNames
                        + ", 剩余报名�? = " + count);
                return json;
            }
        }
        for (Student student : studentlist) {
            // 报�?�材�?
            List<StudentAgentMaterial> materials = JSONObject.parseArray(request.getParameter("materials"),
                    com.yuxin.wx.model.student.StudentAgentMaterial.class);
            if (materials != null) {
                for (StudentAgentMaterial studentAgentMaterial : materials) {
                    studentAgentMaterial.setStuId(student.getId().toString());
                }
            }
            // 分期
            List<StudentFeeStage> stages = JSONObject.parseArray(request.getParameter("stage"), com.yuxin.wx.model.student.StudentFeeStage.class);
            if (stages != null) {
                for (StudentFeeStage studentFeeStage : stages) {
                    studentFeeStage.setStuId(student.getId());
                }
            }

            Users user = WebUtils.getCurrentUser(request);
            payMaster.setStuId(student.getId());
            // 防止订单id重复
            payMaster.setId(null);
            // 建立订单
            Boolean exists = this.studentPayMasterServiceImpl.savePayMaster(payMaster, user, materials, stages);
            if (!exists) {
                this.log_student.error(">>> [批量报名] " + "状�?�：error" + ", 问题：该课程没有班号，请先建立班号再报名�?" + ", 信息�?" + "公司ID = " + WebUtils.getCurrentCompanyId() + ", 操作人ID = "
                        + WebUtils.getCurrentUserId(request) + ", 学生ID = " + student.getId() + ", 订单ID = " + payMaster.getId() + ", 课程ClassTypeID = "
                        + payMaster.getCommodityId() + ", 课程名称 = " + payMaster.getClassTypeName());
                json.put("status", "error");
                json.put("result", "该课程没有班号，请先建立班号再报名�??");
                return json;
            }
            List<StudentPayMaster> list = this.studentPayMasterServiceImpl.findByClassTypeAndStu(payMaster);
            if (list != null && list.size() <= 1) {
                // 更新商品表购买人�?
                CommodityProductRealtion cpr = this.commodityProductRealtionServiceImpl.findByClassTypeId("" + payMaster.getCommodityId());
                this.commodityServiceImpl.updateBuyNumById(cpr.getComId());
            }

            // 更新前台用户�?
            UsersFront userfront = new UsersFront();
            userfront.setCompanyId(WebUtils.getCurrentCompanyId());
            userfront.setEmail(student.getEmail());
            userfront.setMobile(student.getMobile());
            userfront.setRegistTime(new Date());
            userfront.setStatus(1);
            userfront.setInterests("" + payMaster.getItemOneId());
            userfront.setVipFlag(1);
            userfront.setRegistType(2);
            userfront.setSchoolId(payMaster.getSchoolId());
            if (null != student.getMobile() && !"".equals(student.getMobile())) {
                userfront.setPassword(new Md5Hash(student.getMobile().substring(5)).toHex());
            } else {
                userfront.setPassword(new Md5Hash("111111").toHex());
            }
            UsersFront u = new UsersFront();
            if (null != student.getUserId()) {
                u = this.usersFrontServiceImpl.findUsersFrontById(student.getUserId());
            } else {
                u = this.usersFrontServiceImpl.findUsersFrontByMobile(userfront);
            }

            // if(null!=crc && null!=crc.getUsernameFlag() &&
            // crc.getUsernameFlag()==1 && crc.getMobileFlag()!=1){
            // u=usersFrontServiceImpl.findUsersFrontById(student.getUserId());
            // }else{
            // u=usersFrontServiceImpl.findUsersFrontByMobile(userfront);
            // }
            // �?查报名完成是否要发短�?
            CompanyFunctionSet cfs = new CompanyFunctionSet();
            cfs.setStatus("1");
            cfs.setCompanyId(WebUtils.getCurrentCompanyId());
            cfs.setFunctionCode("COMPANY_FUNCTION_APPLY");
            // 获取用户短信配置
            List<CompanyFunctionSet> set = this.companyFunctionSetServiceImpl.findCompanyFunctionSet(cfs);
            Boolean needSendSms = false;
            Boolean companyMessageCount = false;
            String sms = "";
            for (CompanyFunctionSet s : set) {
                if ("COMPANY_FUNCTION_APPLY".equals(s.getFunctionCode())) {
                    if ("1".equals(s.getStatus())) {
                        needSendSms = true;
                        if (StringUtils.isNotBlank(s.getContent())) {
                            sms = s.getContent().replace("【coursename�?", payMaster.getClassTypeName()) + "【在线网校�??";
                        }
                    }
                }
            }
            if (needSendSms) {
                CompanyMemberService cms = this.companyMemberServiceServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
                CompanyServiceStatic css = this.companyServiceStaticServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
                Integer buy = cms.getMessageTotal() == null ? 0 : cms.getMessageTotal();
                Integer giv = cms.getGiveMessage() == null ? 0 : cms.getGiveMessage();
                Integer cos = css.getMessageSend() == null ? 0 : css.getMessageSend();
                if (((buy + giv) - cos) > 0) {
                    companyMessageCount = true;
                }
            }
            if (needSendSms && companyMessageCount) {
                try {
                    if (null != student && null != student.getMobile() && !"".equals(student.getMobile())) {// 2016/7/7
                                                                                                            // 无手机号则不发�?�短�?
                        // 发�?�用户配置的短信内容
                        // String send =
                        // SmsClientSend.sendSms(student.getMobile(), sms);
                        String send = SmsClientSend.sendSmsTwo(request, student.getMobile(), sms, student.getUserId(), "sys-notice");
                        int returnstatusSIndex = send.indexOf("<returnstatus>");
                        int returnstatusEIndex = send.indexOf("</returnstatus>");
                        String sendMessageStatus = send.substring((returnstatusSIndex + 14), returnstatusEIndex);
                        // 记录短信发�?�历�?
                        CompanyMessageHistory history = new CompanyMessageHistory();
                        history.setCompanyId(WebUtils.getCurrentCompanyId());

                        history.setContent(sms);
                        history.setBusinessType("BUSINESS_USER_REGIST");
                        history.setCostNum(1);
                        history.setReceiverMobile(student.getMobile());
                        history.setReceiverUserId("" + userfront.getId());
                        history.setSchoolId(WebUtils.getCurrentSchoolId());
                        history.setSendResult(send);
                        history.setSendTime(new Date());
                        if ("Faild".equals(sendMessageStatus)) {
                            history.setSendStatus(0);
                        } else {
                            history.setSendStatus(1);
                        }
                        history.setSendStatus(1);
                        this.companyMessageHistoryServiceImpl.insert(history);
                    }
                } catch (Exception e) {
                    this.log_student.error(">>> [批量报名] " + "状�?�：短信发�?�失�?" + ", 信息�?" + "公司ID = " + WebUtils.getCurrentCompanyId() + ", 操作人ID = "
                            + WebUtils.getCurrentUserId(request) + ", 课程ClassTypeID = " + payMaster.getCommodityId() + ", 课程名称 = "
                            + payMaster.getClassTypeName() + ", 报名�? = " + student.getId() + ", sms = " + sms, e);
                    e.printStackTrace();
                }
            }
            if (u == null || u.getId() == null) {
                this.usersFrontServiceImpl.insert(userfront);
                // 更新用户
                student.setUserId(userfront.getId());
                this.studentServiceImpl.update(student);

            } else {
                u.setVipFlag(1);
                u.setInterests("" + payMaster.getItemOneId());
                this.usersFrontServiceImpl.update(u);
                // 更新用户
                student.setUserId(u.getId());
                this.studentServiceImpl.update(student);
            }
        }
        this.log_student.info(">>> [批量报名] " + "状�?�：success" + ", 信息�?" + "公司ID = " + WebUtils.getCurrentCompanyId() + ", 操作人ID = "
                + WebUtils.getCurrentUserId(request) + ", 课程ClassTypeID = " + payMaster.getCommodityId() + ", 课程名称 = " + payMaster.getClassTypeName()
                + ", 报名人数 = " + studentlist.size() + ", 报名List = " + studentlist);
        json.put("status", "success");
        json.put("payMaster", payMaster);
        return json;
    }

    /**
     *
     * @fileName : StudentPayMasterController.java
     * @date : 2015�?10�?21�? 下午4:41:20
     * @author : 杨延�?
     * @description :批量报名复杂�?
     */
    @ResponseBody
    @RequestMapping(value = "/full/savePayMasterMany", method = RequestMethod.POST)
    public JSONObject fullSavePayMasterMany(HttpServletRequest request, StudentPayMaster payMaster) {
        this.log_student.info("===");
        JSONObject json = new JSONObject();

        payMaster.setSchoolId(WebUtils.getCurrentSchoolId());
        Users user = WebUtils.getCurrentUser(request);
        // 查询公司注册设置
        CompanyRegisterConfig crc = new CompanyRegisterConfig();
        crc.setCompanyId(WebUtils.getCurrentCompanyId());
        crc = this.companyRegisterConfigServiceImpl.queryByCompanyId(crc);
        List<Student> studentList = JSONObject.parseArray(request.getParameter("student"), Student.class);

        Company company = this.companyService.findCompanyById(WebUtils.getCurrentCompanyId());
        if (null == company.getServiceVersion() || "USER_COUNT".equals(company.getServiceVersion()) || "".equals(company.getServiceVersion())) {// 在线人数版不控制报名
            // 剩余招生学院数量
            CompanyMemberService cms = this.companyMemberServiceServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
            Integer stuCount = this.studentPayMasterServiceImpl.findStuCountByCompanyId(WebUtils.getCurrentCompanyId());
            int count = cms.getFaceStudentAll() - stuCount;
            int countTemp = 0;
            String studentNames = "";
            for (Student student : studentList) {
                UsersFront uf = this.usersFrontServiceImpl.findUserFrontByStudentId((student.getId()));
                if (null != uf && null != uf.getVipFlag() && !"".equals(uf.getVipFlag())) {
                    if (1 != uf.getVipFlag()) {
                        countTemp++;
                        studentNames += student.getId() + "-" + student.getName() + ",";
                    }
                } else {
                    countTemp++;
                    studentNames += student.getId() + "-" + student.getName() + ",";
                }
            }
            if (countTemp > count) {
                json.put("status", "error");
                json.put("result", "可招生学员超上限");
                json.put("wantNum", countTemp);
                json.put("wantList", studentNames);
                json.put("allNum", count);

                this.log_student.error(">>> [批量报名-复杂版] " + "状�?�：error" + ", 问题：可招生学员超上�?" + ", 信息�?" + "公司ID = " + WebUtils.getCurrentCompanyId() + ", 操作人ID = "
                        + WebUtils.getCurrentUserId(request) + ", 课程ClassTypeID = " + payMaster.getCommodityId() + ", 课程名称 = " + payMaster.getClassTypeName()
                        + ", 报名人数 = " + studentList.size() + ", 报名List = " + studentList + ", 新增报名�?(未报过名) = " + countTemp + ", 新增报名�?(未报过名) = " + studentNames
                        + ", 剩余报名�? = " + count);
                return json;
            }
        }
        for (Student student : studentList) {
            // StudentPayMaster
            // master=studentPayMasterServiceImpl.findStudentPayMasterById(payMaster.getId());
            // if(master!=null && master.getId()!=null){
            // studentPayMasterServiceImpl.updatePayMaster(payMaster, user,
            // slaves);
            // }else{
            // studentPayMasterServiceImpl.newPayMaster(payMaster,user,slaves);
            // }
            List<StudentPaySlave> slaves = JSONObject.parseArray(request.getParameter("slaves"), com.yuxin.wx.model.student.StudentPaySlave.class);
            // 分期
            List<StudentFeeStage> stages = JSONObject.parseArray(request.getParameter("stage"), com.yuxin.wx.model.student.StudentFeeStage.class);

            payMaster.setStuId(student.getId());
            // 防止订单id重复
            payMaster.setId(null);
            this.studentPayMasterServiceImpl.newPayMasterMany(payMaster, user, slaves);
            // 补订单payOrder
            Student stu = this.studentServiceImpl.findStudentById(payMaster.getStuId());
            StringBuffer orderIdBuffer = new StringBuffer(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
            String orderNum = orderIdBuffer.append(RandomUtils.nextInt(1000)).toString();
            PayOrder payOrder = new PayOrder();
            payOrder.setUserId(stu.getUserId());
            payOrder.setOrderNum(orderNum);
            payOrder.setOrderTime(new Date());
            payOrder.setCommodityName(payMaster.getClassTypeName());
            payOrder.setCommodityId(payMaster.getCommodityId());
            payOrder.setCommdityType("COMMODITY_CLASS");
            payOrder.setOriginalPrice(payMaster.getTotalAmount());
            payOrder.setPayPrice(payMaster.getTotalAmount()==null?0:payMaster.getTotalAmount());
            payOrder.setPayStatus(Constant.PAY_STATUS_SUCCESS);
            payOrder.setItemOneId(payMaster.getItemOneId());
            payOrder.setItemSecondId(payMaster.getItemSecondId());
            payOrder.setCompanyId(payMaster.getCompanyId());
            payOrder.setSchoolId(payMaster.getSchoolId());
            this.payOrderMapper.insert(payOrder);
            Integer payOrderId = this.payOrderMapper.findPayOrderByOrderNum(orderNum).getId();
            payMaster.setPayOrderId(payOrderId);
            // 更新考期
            SysConfigTerm search = new SysConfigTerm();
            search.setCompanyId(WebUtils.getCurrentCompanyId());
            search.setItemOneId(payMaster.getItemOneId());
            search.setTermName(payMaster.getExamTermName());
            search.setSchoolId(WebUtils.getCurrentSchoolId());
            List<SysConfigTerm> terms = this.sysConfigTermServiceImpl.findTermByName(search);
            if (terms != null && terms.size() > 0) {
                payMaster.setExamTermId(terms.get(0).getId());
            } else {
                this.sysConfigTermServiceImpl.insert(search);
                payMaster.setExamTermId(search.getId());
            }

            for (StudentFeeStage stage : stages) {
                StudentFeeStage s = this.studentFeeStageServiceImpl.findStudentFeeStageById(stage.getId());
                stage.setPayMasterId(payMaster.getId());
                if (stage.getStageDate() == null) {// 没有分期日期就说明是当期付款，所以当前日�?=支付日期
                    stage.setPayDate(new Date());
                }
                stage.setCreateTime(new Date());
                stage.setCreator(user.getId());
                stage.setCreateType("STAGE_TYPE_SIGN");
                stage.setUpdateTime(new Date());
                stage.setUpdator(user.getId());
                stage.setCompanyId(user.getCompanyId());
                if (s != null && s.getId() != null) {
                    this.studentFeeStageServiceImpl.update(stage);
                } else {
                    this.studentFeeStageServiceImpl.insert(stage);
                }
            }

            List<StudentAgentMaterial> materials = JSONObject.parseArray(request.getParameter("materials"),
                    com.yuxin.wx.model.student.StudentAgentMaterial.class);
            StudentPayMaster payMaster2 = this.studentPayMasterServiceImpl.findStudentPayMasterById(payMaster.getId());
            // �?查是否已经有记录�?
            List<StudentAgentMaterial> ms = this.studentAgentMaterialServiceImpl.findStudentAgentMaterialByStuId(payMaster2.getStuId(), payMaster2.getId());
            if (ms.size() > 0) {
                if (materials != null) {
                    for (StudentAgentMaterial m : materials) {
                        m.setCompanyId(WebUtils.getCurrentCompanyId());
                        m.setCreateTime(new Date());
                        m.setCreator(WebUtils.getCurrentUserId(request));
                        this.studentAgentMaterialServiceImpl.update(m);
                    }
                } else {
                    materials = new ArrayList<StudentAgentMaterial>();
                }
                payMaster2.setUpdateTime(new Date());
                payMaster2.setUpdator("" + WebUtils.getCurrentUserId(request));
                payMaster2.setIsAgent(payMaster.getIsAgent());
                payMaster2.setIsAgentMaterialComplete(payMaster.getIsAgentMaterialComplete());
                payMaster2.setAgentRemark(payMaster.getAgentRemark());
                payMaster2.setPayOrderId(payMaster.getPayOrderId());
                payMaster2.setPayStatusCode(payMaster.getPayStatusCode());
                payMaster2.setPaymentTypeCode(payMaster.getPaymentTypeCode());
                payMaster2.setExamAgentFee(payMaster.getExamAgentFee());
                payMaster2.setTotalAmount(payMaster.getTotalAmount());

                this.studentPayMasterServiceImpl.update(payMaster2);
            } else {
                // 没有则新�?
                if (materials != null) {
                    for (StudentAgentMaterial m : materials) {
                        m.setCompanyId(WebUtils.getCurrentCompanyId());
                        m.setCreateTime(new Date());
                        m.setCreator(WebUtils.getCurrentUserId(request));
                        m.setPayMasterId(payMaster2.getId());
                        this.studentAgentMaterialServiceImpl.insertMaterial(m);
                    }
                } else {
                    materials = new ArrayList<StudentAgentMaterial>();
                }
                payMaster2.setUpdateTime(new Date());
                payMaster2.setUpdator("" + WebUtils.getCurrentUserId(request));
                payMaster2.setIsAgent(payMaster.getIsAgent());
                payMaster2.setIsAgentMaterialComplete(payMaster.getIsAgentMaterialComplete());
                payMaster2.setAgentRemark(payMaster.getAgentRemark());
                payMaster2.setPayOrderId(payMaster.getPayOrderId());
                payMaster2.setPayStatusCode(payMaster.getPayStatusCode());
                payMaster2.setPaymentTypeCode(payMaster.getPaymentTypeCode());
                payMaster2.setExamAgentFee(payMaster.getExamAgentFee());
                payMaster2.setTotalAmount(payMaster.getTotalAmount());
                this.studentPayMasterServiceImpl.update(payMaster2);
            }

            List<StudentPayMaster> list = this.studentPayMasterServiceImpl.findByClassTypeAndStu(payMaster2);
            if (list != null && list.size() <= 1) {
                CommodityProductRealtion cpr = this.commodityProductRealtionServiceImpl.findByClassTypeId("" + payMaster2.getCommodityId());
                this.commodityServiceImpl.updateBuyNumById(cpr.getComId());
            }
            // 更新前台用户�?
            UsersFront userfront = new UsersFront();
            userfront.setCompanyId(WebUtils.getCurrentCompanyId());
            userfront.setEmail(student.getEmail());
            userfront.setMobile(student.getMobile());
            userfront.setRegistTime(new Date());
            userfront.setStatus(1);
            userfront.setInterests("" + payMaster2.getItemOneId());
            // userfront.setUsername(student.getMobile());
            userfront.setVipFlag(1);
            userfront.setRegistType(2);
            userfront.setSchoolId(payMaster2.getSchoolId());
            if (null != student.getMobile() && !"".equals(student.getMobile())) {
                userfront.setPassword(new Md5Hash(student.getMobile().substring(5)).toHex());
            } else {
                userfront.setPassword(new Md5Hash("111111").toHex());
            }
            UsersFront u = new UsersFront();
            if (null != student.getUserId()) {
                u = this.usersFrontServiceImpl.findUsersFrontById(student.getUserId());
            } else {
                u = this.usersFrontServiceImpl.findUsersFrontByMobile(userfront);
            }
            // if(null!=crc && null!=crc.getUsernameFlag() &&
            // crc.getUsernameFlag()==1 && crc.getMobileFlag()!=1){
            // u=usersFrontServiceImpl.findUsersFrontById(student.getUserId());
            // }else{
            // u=usersFrontServiceImpl.findUsersFrontByMobile(userfront);
            // }
            // �?查报名完成是否要发短�?
            CompanyFunctionSet search1 = new CompanyFunctionSet();
            search1.setStatus("1");
            search1.setCompanyId(WebUtils.getCurrentCompanyId());
            search1.setFunctionCode("COMPANY_FUNCTION_APPLY");
            // 获取用户短信配置
            List<CompanyFunctionSet> set = this.companyFunctionSetServiceImpl.findCompanyFunctionSet(search1);
            Boolean needSendSms = false;
            Boolean companyMessageCount = false;
            String sms = "";
            for (CompanyFunctionSet s : set) {
                if ("COMPANY_FUNCTION_APPLY".equals(s.getFunctionCode())) {
                    if ("1".equals(s.getStatus())) {
                        needSendSms = true;
                        if (StringUtils.isNotBlank(s.getContent())) {
                            sms = s.getContent().replace("【coursename�?", payMaster2.getClassTypeName()) + "【在线网校�??";
                        }
                    }
                }
            }
            if (needSendSms) {
                CompanyMemberService cms = this.companyMemberServiceServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
                CompanyServiceStatic css = this.companyServiceStaticServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
                Integer buy = cms.getMessageTotal() == null ? 0 : cms.getMessageTotal();
                Integer giv = cms.getGiveMessage() == null ? 0 : cms.getGiveMessage();
                Integer cos = css.getMessageSend() == null ? 0 : css.getMessageSend();
                if (((buy + giv) - cos) > 0) {
                    companyMessageCount = true;
                }
            }
            if (needSendSms && companyMessageCount) {
                try {
                    if (null != student && null != student.getMobile() && !"".equals(student.getMobile())) {// 2016/7/7
                                                                                                            // 无手机号则不发�?�短�?
                        // 发�?�用户配置的短信内容
                        // String send =
                        // SmsClientSend.sendSms(student.getMobile(), sms);
                        String send = SmsClientSend.sendSmsTwo(request, student.getMobile(), sms, student.getUserId(), "sys-notice");
                        int returnstatusSIndex = send.indexOf("<returnstatus>");
                        int returnstatusEIndex = send.indexOf("</returnstatus>");
                        String sendMessageStatus = send.substring((returnstatusSIndex + 14), returnstatusEIndex);
                        // 记录短信发�?�历�?
                        CompanyMessageHistory history = new CompanyMessageHistory();
                        history.setCompanyId(WebUtils.getCurrentCompanyId());

                        history.setContent(sms);
                        history.setBusinessType("BUSINESS_USER_REGIST");
                        history.setCostNum(1);
                        history.setReceiverMobile(student.getMobile());
                        history.setReceiverUserId("" + userfront.getId());
                        history.setSchoolId(WebUtils.getCurrentSchoolId());
                        history.setSendResult(send);
                        history.setSendTime(new Date());
                        if ("Faild".equals(sendMessageStatus)) {
                            history.setSendStatus(0);
                        } else {
                            history.setSendStatus(1);
                        }
                        history.setSendStatus(1);
                        this.companyMessageHistoryServiceImpl.insert(history);
                    }
                } catch (Exception e) {
                    this.log_student.error(">>> [批量报名] " + "状�?�：短信发�?�失�?" + ", 信息�?" + "公司ID = " + WebUtils.getCurrentCompanyId() + ", 操作人ID = "
                            + WebUtils.getCurrentUserId(request) + ", 课程ClassTypeID = " + payMaster.getCommodityId() + ", 课程名称 = "
                            + payMaster.getClassTypeName() + ", 报名�? = " + student.getId() + ", sms = " + sms, e);
                    e.printStackTrace();
                }
            }
            if (u == null || u.getId() == null) {
                this.usersFrontServiceImpl.insert(userfront);
                // 更新用户
                student.setUserId(userfront.getId());
                this.studentServiceImpl.update(student);

            } else {
                u.setVipFlag(1);
                u.setInterests("" + payMaster2.getItemOneId());
                this.usersFrontServiceImpl.update(u);
                // 更新用户
                student.setUserId(u.getId());
                this.studentServiceImpl.update(student);
            }

        }
        this.log_student.info(">>> [批量报名-复杂版] " + "状�?�：success" + ", 信息�?" + "公司ID = " + WebUtils.getCurrentCompanyId() + ", 操作人ID = "
                + WebUtils.getCurrentUserId(request) + ", 课程ClassTypeID = " + payMaster.getCommodityId() + ", 课程名称 = " + payMaster.getClassTypeName()
                + ", 报名人数 = " + studentList.size() + ", 报名List = " + studentList);
        json.put("status", "success");
        json.put("payMaster", payMaster);
        return json;
    }

    @ResponseBody
    @RequestMapping(value = "/savePayMaster2", method = RequestMethod.POST)
    public StudentPayMaster savePayMaster2(HttpServletRequest request, StudentPayMaster payMaster) {
        payMaster.setSchoolId(WebUtils.getCurrentSchoolId());
        List<StudentPaySlave> slaves = JSONObject.parseArray(request.getParameter("slaves"), com.yuxin.wx.model.student.StudentPaySlave.class);
        Users user = WebUtils.getCurrentUser(request);
        StudentPayMaster master = this.studentPayMasterServiceImpl.findStudentPayMasterById(payMaster.getId());
        if (master != null && master.getId() != null) {
            this.studentPayMasterServiceImpl.updatePayMaster(payMaster, user, slaves);
        } else {
            this.studentPayMasterServiceImpl.newPayMaster(payMaster, user, slaves);
        }

        return payMaster;
    }

    /**
     *
     * Class Name: StudentAgentMaterialController.java
     *
     * @Description: 保存代报�?
     * @author zhang.zx
     * @date 2015�?4�?25�? 下午6:36:10
     * @modifier
     * @modify-date 2015�?4�?25�? 下午6:36:10
     * @version 1.0
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveStuMaterial", method = RequestMethod.POST)
    public List<StudentAgentMaterial> addStuMaterial(HttpServletRequest request, StudentPayMaster payMaster) {
        List<StudentAgentMaterial> materials = JSONObject.parseArray(request.getParameter("materials"), com.yuxin.wx.model.student.StudentAgentMaterial.class);
        StudentPayMaster payMaster2 = this.studentPayMasterServiceImpl.findStudentPayMasterById(payMaster.getId());
        // �?查是否已经有记录�?
        List<StudentAgentMaterial> ms = this.studentAgentMaterialServiceImpl.findStudentAgentMaterialByStuId(payMaster2.getStuId(), payMaster2.getId());
        if (ms.size() > 0) {
            if (materials != null) {
                for (StudentAgentMaterial m : materials) {
                    m.setCompanyId(WebUtils.getCurrentCompanyId());
                    m.setCreateTime(new Date());
                    m.setCreator(WebUtils.getCurrentUserId(request));
                    this.studentAgentMaterialServiceImpl.update(m);
                }
            } else {
                materials = new ArrayList<StudentAgentMaterial>();
            }
            payMaster2.setUpdateTime(new Date());
            payMaster2.setUpdator("" + WebUtils.getCurrentUserId(request));
            payMaster2.setIsAgent(payMaster.getIsAgent());
            payMaster2.setIsAgentMaterialComplete(payMaster.getIsAgentMaterialComplete());
            payMaster2.setAgentRemark(payMaster.getAgentRemark());
            this.studentPayMasterServiceImpl.update(payMaster2);
        } else {
            // 没有则新�?
            if (materials != null) {
                for (StudentAgentMaterial m : materials) {
                    m.setCompanyId(WebUtils.getCurrentCompanyId());
                    m.setCreateTime(new Date());
                    m.setCreator(WebUtils.getCurrentUserId(request));
                    this.studentAgentMaterialServiceImpl.insertMaterial(m);
                }
            } else {
                materials = new ArrayList<StudentAgentMaterial>();
            }
            payMaster2.setUpdateTime(new Date());
            payMaster2.setUpdator("" + WebUtils.getCurrentUserId(request));
            payMaster2.setIsAgent(payMaster.getIsAgent());
            payMaster2.setIsAgentMaterialComplete(payMaster.getIsAgentMaterialComplete());
            payMaster2.setAgentRemark(payMaster.getAgentRemark());
            this.studentPayMasterServiceImpl.update(payMaster2);
        }

        return materials;
    }

    /**
     *
     * Class Name: StudentPayMasterController.java
     *
     * @Description: 报名，添加订单分�?
     * @author Chopin
     * @date 2014�?12�?12�? 下午3:33:04
     * @version 1.0
     * @param search
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveStage", method = RequestMethod.POST)
    public List<StudentFeeStage> saveStage(HttpServletRequest request, StudentPayMaster payMaster) {
        // 查询公司注册设置
        CompanyRegisterConfig crc = new CompanyRegisterConfig();
        crc.setCompanyId(WebUtils.getCurrentCompanyId());
        crc = this.companyRegisterConfigServiceImpl.queryByCompanyId(crc);
        Users user = WebUtils.getCurrentUser(request);
        Student student = JSONObject.parseObject(request.getParameter("student"), Student.class);
        // 分期
        List<StudentFeeStage> stages = JSONObject.parseArray(request.getParameter("stage"), com.yuxin.wx.model.student.StudentFeeStage.class);

        for (StudentFeeStage stage : stages) {
            StudentFeeStage s = this.studentFeeStageServiceImpl.findStudentFeeStageById(stage.getId());
            stage.setPayMasterId(payMaster.getId());
            if (stage.getStageDate() == null) {// 没有分期日期就说明是当期付款，所以当前日�?=支付日期
                stage.setPayDate(new Date());
            }
            stage.setCreateTime(new Date());
            stage.setCreator(user.getId());
            stage.setCreateType("STAGE_TYPE_SIGN");
            stage.setUpdateTime(new Date());
            stage.setUpdator(user.getId());
            stage.setCompanyId(user.getCompanyId());
            if (s != null && s.getId() != null) {
                this.studentFeeStageServiceImpl.update(stage);
            } else {
                this.studentFeeStageServiceImpl.insert(stage);
            }
            // 更新公司流水�?
            try {
                CompanyCashFlow t = new CompanyCashFlow();
                Student st = this.studentServiceImpl.findStudentById(payMaster.getStuId());
                if (null != st && null != st.getUserId()) {
                    t.setUserId(st.getUserId());
                }
                t.setStuId(payMaster.getStuId());
                t.setCompanyId(user.getCompanyId());
                t.setTradeAmount(stage.getStageFee());
                if (stage.getStageDate() == null) {// 没有分期日期就说明是当期付款，所以当前日�?=支付日期
                    t.setTradeDate(new Date());
                } else {
                    t.setTradeDate(stage.getStageDate());
                }
                t.setTradeReason("CHANNEL_OFFLINE");
                t.setTradeType("TRADE_IN");
                if (null != stage.getStageStatus() && "1".equals(stage.getStageStatus())) {
                    t.setTradeResult("success");
                } else {
                    t.setTradeResult("failed");
                }
                t.setTradeWay("PAY_OFFLINE");
                t.setTradeSource("PAY_OFFLINE");
                t.setUpdateTime(new Date());
                t.setUpdator(user.getId());
                t.setSchoolId(user.getSchoolId());
                t.setPayMasterId(payMaster.getId());
                this.companyCashFlowServiceImpl.insert(t);
            } catch (Exception e) {
                this.log.error("添加公司流水表失�?", e);
                e.printStackTrace();
            }
        }
        StudentPayMaster payMaster2 = this.studentPayMasterServiceImpl.findStudentPayMasterById(payMaster.getId());
        // 更新订单
        // if(stages.size()>0 &&
        // stages.get(0).getStageFee().equals(payMaster.getTotalAmount())){
        // payMaster2.setPayStatusCode("ORDER_PAID");
        // }else if(stages.size()>1){
        // payMaster2.setPayStatusCode("ORDER_PART_PAY");
        // }
        payMaster2.setUpdateTime(new Date());
        payMaster2.setUpdator("" + WebUtils.getCurrentUserId(request));
        payMaster2.setPayStatusCode(payMaster.getPayStatusCode());
        payMaster2.setPaymentTypeCode(payMaster.getPaymentTypeCode());
        payMaster2.setExamAgentFee(payMaster.getExamAgentFee());
        payMaster2.setTotalAmount(payMaster.getTotalAmount());

        ClassType ct = this.classTypeServiceImpl.findClassTypeById(payMaster.getCommodityId());
        /* 存子订单�? */
        StudentPaySlave slave = new StudentPaySlave();
        // slave.setCompanyId(user.getCompanyId());
        // slave.setStuId(payMaster.getStuId());
        slave.setPayMasterId(payMaster.getId());
        List<StudentPaySlave> findStudentPaySlaveByPage = this.studentPaySlaveServiceImpl.findStudentPaySlaveByPage(slave);
        for (StudentPaySlave studentPaySlave : findStudentPaySlaveByPage) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("erbi", "SUB_ORDER_FINISHED");
            map.put("nimei", studentPaySlave.getId());
            this.studentPaySlaveServiceImpl.updateStatus(map);
        }

        // 补订单pay_order�?
        PayOrder payOrder = new PayOrder();
        StringBuffer orderIdBuffer = new StringBuffer(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        String orderNum = orderIdBuffer.append(RandomUtils.nextInt(1000)).toString();
        payOrder.setUserId(student.getUserId());
        payOrder.setOrderNum(orderNum);
        payOrder.setOrderTime(new Date());
        payOrder.setCommodityName(ct.getName());
        payOrder.setCommodityId(payMaster.getCommodityId());
        payOrder.setOriginalPrice(ct.getOriginalPrice());
        payOrder.setPayPrice(payMaster.getTotalAmount()==null?0:payMaster.getTotalAmount());
        payOrder.setPayStatus("PAY_SUCCESS");
        payOrder.setCommdityType("COMMODITY_CLASS");
        payOrder.setItemOneId(payMaster.getItemOneId());
        payOrder.setItemSecondId(payMaster.getItemSecondId());
        payOrder.setCompanyId(user.getCompanyId());
        payOrder.setSchoolId(user.getSchoolId());
        this.payOrderServiceImpl.insert(payOrder);
        PayOrder payOrder1 = this.payOrderServiceImpl.findPayOrderByOrderNum(orderNum);
        payMaster2.setPayOrderId(payOrder1.getId());

        this.studentPayMasterServiceImpl.update(payMaster2);
        // 查询学员是否购买过此课程
        List<StudentPayMaster> list = this.studentPayMasterServiceImpl.findByClassTypeAndStu(payMaster2);
        if (list != null && list.size() <= 1) {
            // 更新商品表购买人�?
            CommodityProductRealtion cpr = this.commodityProductRealtionServiceImpl.findByClassTypeId("" + payMaster2.getCommodityId());
            this.commodityServiceImpl.updateBuyNumById(cpr.getComId());
        }
        // 更新前台用户�?
        UsersFront userfront = new UsersFront();
        userfront.setCompanyId(WebUtils.getCurrentCompanyId());
        userfront.setEmail(student.getEmail());
        userfront.setMobile(student.getMobile());
        userfront.setRegistTime(new Date());
        userfront.setStatus(1);
        userfront.setInterests("" + payMaster2.getItemOneId());
        userfront.setUsername(student.getName());
        userfront.setVipFlag(1);
        userfront.setRegistType(2);
        userfront.setSchoolId(payMaster2.getSchoolId());
        if (null != student.getMobile() && !"".equals(student.getMobile())) {
            userfront.setPassword(new Md5Hash("111111").toHex());
        } else {
            userfront.setPassword(new Md5Hash("111111").toHex());
        }

        UsersFront u = new UsersFront();
        if (null != student.getUserId()) {
            u = this.usersFrontServiceImpl.findUsersFrontById(student.getUserId());
        } else {
            u = this.usersFrontServiceImpl.findUsersFrontByMobile(userfront);
        }
        // if(null!=crc && null!=crc.getUsernameFlag() &&
        // crc.getUsernameFlag()==1 && crc.getMobileFlag()!=1){
        // u=usersFrontServiceImpl.findUsersFrontById(student.getUserId());
        // }else{
        // u=usersFrontServiceImpl.findUsersFrontByMobile(userfront);
        // }
        // �?查报名完成是否要发短�?
        CompanyFunctionSet search = new CompanyFunctionSet();
        search.setStatus("1");
        search.setCompanyId(WebUtils.getCurrentCompanyId());
        search.setFunctionCode("COMPANY_FUNCTION_APPLY");
        // 获取用户短信配置
        List<CompanyFunctionSet> set = this.companyFunctionSetServiceImpl.findCompanyFunctionSet(search);
        Boolean needSendSms = false;
        Boolean companyMessageCount = false;
        String sms = "";
        for (CompanyFunctionSet s : set) {
            if ("COMPANY_FUNCTION_APPLY".equals(s.getFunctionCode())) {
                if ("1".equals(s.getStatus())) {
                    needSendSms = true;
                    if (StringUtils.isNotBlank(s.getContent())) {
                        sms = s.getContent().replace("【coursename�?", payMaster2.getClassTypeName()) + "【在线网校�??";
                    }
                }
            }
        }
        this.log.info("****************报名已完�?*****************");
        this.log.info(sms);
        if (needSendSms) {
            CompanyMemberService cms = this.companyMemberServiceServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
            CompanyServiceStatic css = this.companyServiceStaticServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
            Integer buy = cms.getMessageTotal() == null ? 0 : cms.getMessageTotal();
            Integer giv = cms.getGiveMessage() == null ? 0 : cms.getGiveMessage();
            Integer cos = css.getMessageSend() == null ? 0 : css.getMessageSend();
            if (((buy + giv) - cos) > 0) {
                companyMessageCount = true;
            }
        }

        if (needSendSms && companyMessageCount) {
            try {
                if (null != student && null != student.getMobile() && !"".equals(student.getMobile())) {// 2016/7/7
                                                                                                        // 无手机号则不发�?�短�?
                    // 发�?�用户配置的短信内容
                    // String send = SmsClientSend.sendSms(student.getMobile(),
                    // sms);
                    String send = SmsClientSend.sendSmsTwo(request, student.getMobile(), sms, student.getUserId(), "sys-notice");
                    int returnstatusSIndex = send.indexOf("<returnstatus>");
                    int returnstatusEIndex = send.indexOf("</returnstatus>");
                    String sendMessageStatus = send.substring((returnstatusSIndex + 14), returnstatusEIndex);
                    // 记录短信发�?�历�?
                    CompanyMessageHistory history = new CompanyMessageHistory();
                    history.setCompanyId(WebUtils.getCurrentCompanyId());

                    history.setContent(sms);
                    history.setBusinessType("BUSINESS_USER_REGIST");
                    history.setCostNum(1);
                    history.setReceiverMobile(student.getMobile());
                    history.setReceiverUserId("" + userfront.getId());
                    history.setSchoolId(WebUtils.getCurrentSchoolId());
                    history.setSendResult(send);
                    history.setSendTime(new Date());
                    if ("Faild".equals(sendMessageStatus)) {
                        history.setSendStatus(0);
                    } else {
                        history.setSendStatus(1);
                    }
                    history.setSendStatus(1);
                    this.companyMessageHistoryServiceImpl.insert(history);
                }
            } catch (Exception e) {
                this.log.error("短信发�?�失�?", e);
                e.printStackTrace();
            }
        }
        if (u == null || u.getId() == null) {
            this.usersFrontServiceImpl.insert(userfront);
            // 更新用户
            student.setUserId(userfront.getId());
            this.studentServiceImpl.update(student);

        } else {
            u.setVipFlag(1);
            u.setInterests("" + payMaster2.getItemOneId());
            this.usersFrontServiceImpl.update(u);
            // 更新用户
            student.setUserId(u.getId());
            this.studentServiceImpl.update(student);
        }
        return stages;
    }

    /**
     *
     * Class Name: StudentPayMasterController.java
     *
     * @Description: 跳转转班页面
     * @author Chopin
     * @date 2014�?12�?12�? 下午3:33:04
     * @version 1.0
     * @param search
     * @return
     */

    @RequestMapping(value = "/showChangeClass", method = RequestMethod.GET)
    public String showChangeClass(Model model, Student student, @RequestParam("mid") Integer mid) {
        model.addAttribute("student", student);
        StudentPayMaster payMaster = this.studentPayMasterServiceImpl.findStudentPayMasterById(mid);
        List<StudentPaySlaveVo> paySlave = this.studentPaySlaveServiceImpl.findStudentPaySlaveByPayMasterId(mid);
        model.addAttribute("omaster", payMaster);
        model.addAttribute("oslave", paySlave);
        return "student/changeClass";
    }

    /**
     *
     * Class Name: StudentPayMasterController.java
     *
     * @Description: 计算原订单各种费�?
     * @author Chopin
     * @date 2014�?12�?12�? 下午3:33:04
     * @version 1.0
     * @param search
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/countPay/{id}", method = RequestMethod.POST)
    public Map<String, Double> countPay(@PathVariable Integer id) {
        Map<String, Double> map = new HashMap<String, Double>();
        List<StudentFeeStage> ostages = this.studentFeeStageServiceImpl.findHasPayed(id);
        Double opos = 0.0;
        Double ocash = 0.0;
        Double orimit = 0.0;
        Double ocheck = 0.0;
        Double opayed = 0.0;
        for (StudentFeeStage stage : ostages) {
            if (stage.getPosReal() != null) {
                opos += stage.getPosReal();
            }
            if (stage.getCashReal() != null) {
                ocash += stage.getCashReal();
            }
            if (stage.getCheckReal() != null) {
                ocheck += stage.getCheckReal();
            }
            if (stage.getRemitReal() != null) {
                orimit += stage.getRemitReal();
            }
            opayed += stage.getStageFee();
        }
        map.put("opos", opos);
        map.put("ocash", ocash);
        map.put("orimit", orimit);
        map.put("ocheck", ocheck);
        map.put("opayed", opayed);
        return map;
    }

    /**
     *
     * Class Name: StudentPayMasterController.java
     *
     * @Description: 转班
     * @author Chopin
     * @date 2014�?12�?12�? 下午3:33:04
     * @version 1.0
     * @param search
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/full/changeClass", method = RequestMethod.POST)
    public StudentPayMaster fullChangeClass(Model model, HttpServletRequest request, StudentPayMaster payMaster, @RequestParam("omasterId") Integer oMasterId) {
        this.log_student.info("===");
        Users user = WebUtils.getCurrentUser();
        payMaster.setSchoolId(WebUtils.getCurrentSchoolId());
        // 模块
        List<StudentPaySlave> slaves = JSONObject.parseArray(request.getParameter("slaves"), com.yuxin.wx.model.student.StudentPaySlave.class);
        // 资料
        List<StudentAgentMaterial> materials = JSONObject.parseArray(request.getParameter("materials"), com.yuxin.wx.model.student.StudentAgentMaterial.class);
        // 分期
        List<StudentFeeStage> stages = JSONObject.parseArray(request.getParameter("stage"), com.yuxin.wx.model.student.StudentFeeStage.class);

        StudentFeeRefund refund = JSONObject.parseObject(request.getParameter("refund"), StudentFeeRefund.class);
        this.log_student.info(">>> [转班-复杂版]" + "状�?�：success " + ", Step�?1 " + ", 信息�?" + "公司ID = " + WebUtils.getCurrentCompanyId() + ", 操作人ID = "
                + WebUtils.getCurrentUserId(request) + ", 学生ID = " + payMaster.getStuId());
        this.studentPayMasterServiceImpl.fullChangeClass(payMaster, oMasterId, user, slaves, materials, stages, refund);
        Student student = this.studentServiceImpl.findStudentById(payMaster.getStuId());
        model.addAttribute("student", student);
        return payMaster;
    }

    /**
     *
     * Class Name: StudentPayMasterController.java
     *
     * @Description: 转班
     * @author Chopin
     * @date 2014�?12�?12�? 下午3:33:04
     * @version 1.0
     * @param search
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/changeClass", method = RequestMethod.POST)
    public StudentPayMaster changeClass(Model model, HttpServletRequest request, StudentPayMaster payMaster, @RequestParam("omasterId") Integer oMasterId) {
        this.log_student.info("===");
        Users user = WebUtils.getCurrentUser();
        payMaster.setSchoolId(WebUtils.getCurrentSchoolId());
        // 资料
        List<StudentAgentMaterial> materials = JSONObject.parseArray(request.getParameter("materials"), com.yuxin.wx.model.student.StudentAgentMaterial.class);
        // 分期
        List<StudentFeeStage> stages = JSONObject.parseArray(request.getParameter("stage"), com.yuxin.wx.model.student.StudentFeeStage.class);

        StudentFeeRefund refund = JSONObject.parseObject(request.getParameter("refund"), StudentFeeRefund.class);
        this.log_student.info(">>> [转班]" + "状�?�：success " + ", Step�?1 " + ", 信息�?" + "公司ID = " + WebUtils.getCurrentCompanyId() + ", 操作人ID = "
                + WebUtils.getCurrentUserId(request) + ", 学生ID = " + payMaster.getStuId());
        this.studentPayMasterServiceImpl.changeClass(payMaster, oMasterId, user, materials, stages, refund);
        Student student = this.studentServiceImpl.findStudentById(payMaster.getStuId());
        model.addAttribute("student", student);
        return payMaster;
    }

    /**
     *
     * Class Name: StudentPayMasterController.java
     *
     * @Description: 跳转转人页面
     * @author Chopin
     * @date 2014�?12�?12�? 下午3:33:04
     * @version 1.0
     * @param search
     * @return
     */
    @RequestMapping(value = "/showChangeStudent", method = RequestMethod.GET)
    public String showChangeStudent(Model model, Student student, Integer mid) {
        model.addAttribute("student", student);
        StudentPayMaster payMaster = this.studentPayMasterServiceImpl.findStudentPayMasterById(mid);
        List<StudentPaySlaveVo> paySlave = this.studentPaySlaveServiceImpl.findStudentPaySlaveByPayMasterId(mid);
        model.addAttribute("omaster", payMaster);
        model.addAttribute("oslave", paySlave);
        return "student/changeStudent";
    }

    /**
     *
     * Class Name: StudentPayMasterController.java
     *
     * @Description: 转人
     * @author 权飞�?
     * @date 2014�?12�?12�? 下午3:33:04
     * @version 1.0
     * @param search
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/changeStudent", method = RequestMethod.POST)
    public String changeStudent(HttpServletRequest request, Integer studentId, Student student, StudentPayChangeInfo changeInfo, Integer mid) {
        this.log_student.info("===");
        // StudentPayMaster payMaster =
        // studentPayMasterServiceImpl.findStudentPayMasterById(mid);
        Integer companyId = WebUtils.getCurrentCompanyId();
        Integer schoolId = WebUtils.getCurrentSchoolId();
        student.setDeleteFlag(0);
        student.setRemark(null);
        student.setCompanyId(companyId);
        student.setCreateTime(new Date());
        student.setCreator(WebUtils.getCurrentUserId(request));
        if (studentId == null || "".equals(studentId)) {
            CompanyRegisterConfig crc = this.getCrc(companyId);
            String password = "111111";
            // 机构没有设置注册方式 || 只以手机号注册时,学员的前台账号初始密码为手机号后六位
            if (crc == null || (crc.getMobileFlag() == 1 && crc.getUsernameFlag() == 0)) {
                password = new Md5Hash(student.getMobile().substring(student.getMobile().length() - 6, student.getMobile().length())).toHex();
            } else {
                password = new Md5Hash(password).toHex();
            }
            UsersFront front = new UsersFront();
            front.setCompanyId(companyId);
            if (StringUtils.isNotBlank(student.getEmail())) {
                front.setEmail(student.getEmail());
            }
            if (StringUtils.isNotBlank(student.getMobile())) {
                front.setMobile(student.getMobile());
                student.setMobile(student.getMobile());
            }
            if (StringUtils.isNotBlank(student.getUsername())) {
                front.setUsername(student.getUsername());
            }
            front.setPassword(password);
            front.setStatus(1);
            front.setSchoolId(schoolId);
            front.setRegistType(2);
            front.setRegistTime(new Date());
            this.usersFrontServiceImpl.insert(front);

            student.setUserId(front.getId());
            this.studentServiceImpl.insert(student);
        } else {
            student = this.studentServiceImpl.findStudentById(studentId);
        }
        Users user = WebUtils.getCurrentUser(request);
        this.log_student.info(">>> [转人] " + "状�?�：success" + ", Step�?1" + ", 信息�?" + "公司ID�?" + WebUtils.getCurrentCompanyId() + ", 操作人ID�?"
                + WebUtils.getCurrentUserId(request) + ", 学生ID�?" + student.getId());
        return this.studentPayMasterServiceImpl.changeStudent(user, student, changeInfo, mid);
    }

    public CompanyRegisterConfig getCrc(Integer companyId) {
        CompanyRegisterConfig crc = new CompanyRegisterConfig();
        crc.setCompanyId(WebUtils.getCurrentCompanyId());
        crc = this.companyRegisterConfigServiceImpl.queryByCompanyId(crc);
        return crc;
    }

    /**
     *
     * Class Name: StudentPayMasterController.java
     *
     * @Description: 跳转解约页面
     * @author Chopin
     * @date 2014�?12�?12�? 下午3:33:04
     * @version 1.0
     * @param search
     * @return
     */
    @RequestMapping(value = "/showUnwind", method = RequestMethod.GET)
    public String showUnWind(Model model, Student student, Integer mid) {
        model.addAttribute("student", student);
        StudentPayMaster payMaster = this.studentPayMasterServiceImpl.findStudentPayMasterById(mid);
        List<StudentPaySlaveVo> paySlave = this.studentPaySlaveServiceImpl.findStudentPaySlaveByPayMasterId(mid);
        model.addAttribute("omaster", payMaster);
        model.addAttribute("oslave", paySlave);
        return "student/unwind";
    }

    /**
     *
     * Class Name: StudentPayMasterController.java
     *
     * @Description: 解约
     * @author Chopin
     * @date 2014�?12�?12�? 下午3:33:04
     * @version 1.0
     * @param search
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/unwind", method = RequestMethod.POST)
    public String unWind(HttpServletRequest request, StudentPayChangeInfo changeInfo, Integer mid) {
        Users user = WebUtils.getCurrentUser(request);
        return this.studentPayMasterServiceImpl.unWind(user, changeInfo, mid);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(StudentPayMaster studentPayMaster) {
        this.studentPayMasterServiceImpl.insert(studentPayMaster);
        return "redirect:/studentPayMaster";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(StudentPayMaster studentPayMaster) {
        this.studentPayMasterServiceImpl.update(studentPayMaster);
        return "redirect:/studentPayMaster";
    }

    @RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
    public String del(Model model, @PathVariable Integer id) {
        this.studentPayMasterServiceImpl.deleteStudentPayMasterById(id);
        return "redirect:/studentPayMaster";
    }

    @ResponseBody
    @RequestMapping(value = "/{id:\\d+}")
    public StudentPayMaster getJson(Model model, @PathVariable Integer id) {
        return this.studentPayMasterServiceImpl.findStudentPayMasterById(id);
    }

    @RequestMapping(value = "getList", method = RequestMethod.POST)
    public String getList(StudentPayMaster payMaster, Model model) {
        payMaster.setCompanyId(WebUtils.getCurrentCompanyId());
        // List<StudentPayMaster> findStudentPayMasterByPage =
        // studentPayMasterServiceImpl.findStudentPayMasterByPage(payMaster);
        PageFinder<StudentPayMaster> pageFinder = this.studentPayMasterServiceImpl.findList(payMaster);
        model.addAttribute("pageFinder", pageFinder);
        return "/student/order/message";
    }

    @RequestMapping(value = "detail", method = RequestMethod.POST)
    public String detail(Integer id, Model model) {
        StudentPayMaster payMaster = this.studentPayMasterServiceImpl.findStudentPayMasterById(id);
        Student student = this.studentServiceImpl.findStudentById(payMaster.getStuId());
        model.addAttribute("payMaster", payMaster);
        model.addAttribute("student", student);
        // 查询相关联的payOrder
        PayOrder payOrder = this.payOrderServiceImpl.findPayOrderById(payMaster.getPayOrderId());
        model.addAttribute("payOrder", payOrder);
        return "/student/order/detail";
    }

    /**
     *
     * Class Name: StudentPayMasterController.java
     *
     * @Description: 跳转到课程安排页�?
     * @author 权飞�?
     * @date 2015�?5�?16�? 上午11:15:34
     * @modifier
     * @modify-date 2015�?5�?16�? 上午11:15:34
     * @version 1.0
     * @param id
     * @return
     */
    @RequestMapping(value = "toArrangeCourse", method = RequestMethod.POST)
    public String toArrangeCourse(Integer id, Model model, HttpServletRequest request) {
        // String id2 = request.getParameter("id");

        StudentPayMaster payMaster = this.studentPayMasterServiceImpl.findStudentPayMasterById(id);
        Student student = this.studentServiceImpl.findStudentById(payMaster.getStuId());
        model.addAttribute("payMaster", payMaster);
        model.addAttribute("student", student);
        Double hasPay = 0d;
        if ("PAY_TYPE_ALL".equals(payMaster.getPaymentTypeCode())) {
            hasPay = payMaster.getTotalAmount();
        } else {
            hasPay = this.studentFeeStageServiceImpl.findSumPayed(id);
        }
        model.addAttribute("hasPay", hasPay);
        return "/student/order/arrangeCourse";
    }

    @RequestMapping(value = "toSearch", method = RequestMethod.POST)
    public String toSearch(ClassModuleNo moduleNo, Model model) {
        moduleNo.setCompanyId(WebUtils.getCurrentCompanyId());
        List<ClassModule> classModules = this.classModuleServiceImpl.findClassModules(moduleNo);
        model.addAttribute("list", classModules);
        return "/student/order/classModuleNos";
    }

    @ResponseBody
    @RequestMapping(value = "arrangeCourse", method = RequestMethod.POST)
    public String arrangeCourse(Integer stuId, Integer payMasterId, String slaves, String campusId, String examTerm, String termName) {
        List<StudentPaySlave> list = JSONArray.parseArray(slaves, StudentPaySlave.class);
        for (StudentPaySlave paySlave : list) {
            paySlave.setCompanyId(WebUtils.getCurrentCompanyId());
            paySlave.setStuId(stuId);
            paySlave.setPayMasterId(payMasterId);
            this.studentPaySlaveServiceImpl.insert(paySlave);
        }
        StudentPayMaster studentPayMaster = new StudentPayMaster();
        studentPayMaster.setId(payMasterId);
        studentPayMaster.setPayStatusCode("ORDER_PAID");
        if (StringUtils.isNotBlank(campusId)) {
            studentPayMaster.setCampusId(Integer.parseInt(campusId));
        }
        if (StringUtils.isNotBlank(examTerm)) {
            studentPayMaster.setExamTermId(Integer.parseInt(examTerm));
        }
        this.studentPayMasterServiceImpl.update(studentPayMaster);
        return JsonMsg.SUCCESS;
    }

    /**
     *
     * Class Name: StudentPayMasterController.java
     *
     * @Description: 条件查询订单信息
     * @author zhang.zx
     * @date 2015�?6�?4�? 下午12:00:24
     * @modifier
     * @modify-date 2015�?6�?4�? 下午12:00:24
     * @version 1.0
     * @param search
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryStuPayMasterList")
    public PageFinder<StuPayMasterVo> queryStuPayList(StuPayMasterVo search) {
        if ("today".equals(search.getTimeMark())) {
            search.setTimeLen(0);
        } else if ("yesday".equals(search.getTimeMark())) {
            // search.setTimeLen(1);
            search.setStartDate(DateUtil.getYesterDay("yyyy-MM-dd"));
            search.setEndDate(DateUtil.getYesterDay("yyyy-MM-dd"));
        } else if ("sevnday".equals(search.getTimeMark())) {
            search.setTimeLen(7);
        } else if ("thirty".equals(search.getTimeMark())) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String str = sdf.format(new Date());
            search.setTimeLen(Integer.parseInt(str.substring(8, str.length())) - 1);
        } else if ("thirmonth".equals(search.getTimeMark())) {
            search.setTimeLen(90);
        }
        search.setCompanyId(WebUtils.getCurrentCompanyId());
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("代理机构")){
        	search.setProxyOrgId(WebUtils.getCurrentUser().getProxyOrgId());
        }
        return this.studentPayMasterServiceImpl.findStudentPayMasterList(search);
    }

    // 查询订单合计金额
    @ResponseBody
    @RequestMapping(value = "/queryTotal")
    public String queryorderTotalMoney(StuPayMasterVo search) {
        if ("today".equals(search.getTimeMark())) {
            search.setTimeLen(0);
        } else if ("yesday".equals(search.getTimeMark())) {
            // search.setTimeLen(1);
            search.setStartDate(DateUtil.getYesterDay("yyyy-MM-dd"));
            search.setEndDate(DateUtil.getYesterDay("yyyy-MM-dd"));
        } else if ("sevnday".equals(search.getTimeMark())) {
            search.setTimeLen(7);
        } else if ("thirty".equals(search.getTimeMark())) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String str = sdf.format(new Date());
            search.setTimeLen(Integer.parseInt(str.substring(8, str.length())) - 1);
        } else if ("thirmonth".equals(search.getTimeMark())) {
            search.setTimeLen(90);
        }
        String str = "0";
        search.setCompanyId(WebUtils.getCurrentCompanyId());
        List<Map> map = this.studentPayMasterServiceImpl.queryorderTotalMoney(search);
        if (!map.isEmpty()) {
            str = map.get(0).get("orderTotalMoney").toString();
        }
        return str;
    }

    // 查询订单折线�?
    @ResponseBody
    @RequestMapping(value = "/queryChart")
    public List<Map> orderChart(StuPayMasterVo search) {
        if ("today".equals(search.getTimeMark())) {
            search.setTimeLen(0);
        } else if ("yesday".equals(search.getTimeMark())) {
            // search.setTimeLen(1);
            search.setStartDate(DateUtil.getYesterDay("yyyy-MM-dd"));
            search.setEndDate(DateUtil.getYesterDay("yyyy-MM-dd"));
        } else if ("sevnday".equals(search.getTimeMark())) {
            search.setTimeLen(7);
        } else if ("thirty".equals(search.getTimeMark())) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String str = sdf.format(new Date());
            search.setTimeLen(Integer.parseInt(str.substring(8, str.length())) - 1);
        } else if ("thirmonth".equals(search.getTimeMark())) {
            search.setTimeLen(90);
        }
        search.setCompanyId(WebUtils.getCurrentCompanyId());
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("代理机构")){
        	search.setProxyOrgId(WebUtils.getCurrentUser().getProxyOrgId());
        }
        return this.studentPayMasterServiceImpl.countOrderByDate(search);
    }

    // 查询订单折线�?
    @ResponseBody
    @RequestMapping(value = "/queryChartBySeven")
    public List<Map> orderChart2() {
        StuPayMasterVo search = new StuPayMasterVo();
        search.setTimeLen(7);
        search.setCompanyId(WebUtils.getCurrentCompanyId());
        Subject subject = SecurityUtils.getSubject();
        if (!subject.hasRole("机构管理�?")) {
            search.setSchoolId(WebUtils.getCurrentSchoolId());
        }
        return this.studentPayMasterServiceImpl.countOrderByDate(search);
    }

    // 作废订单
    @ResponseBody
    @RequestMapping(value = "/abolishPayMaster")
    public Boolean abolishPayMaster(Integer stuId) {
        this.log_student.info("===");
        if (stuId == null) {
            this.log_student.error(
                    ">>> [取消报名] " + "状�?�：error" + ", 信息�?" + "公司ID = " + WebUtils.getCurrentCompanyId() + ", 操作人ID = " + WebUtils.getCurrentUser().getId());
            return false;
        }
        // 课程
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("commodityType", "COMMODITY_CLASS");
        map.put("stuId", stuId);
        List<StudentPayMaster> arr = this.studentPayMasterServiceImpl.findpayIdByStudentsId(map);
        // 课程�?
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("commodityType", "COMMODITY_PACKAGE");
        map1.put("stuId", stuId);
        List<StudentPayMaster> arr1 = this.studentPayMasterServiceImpl.findpayIdByStudentsId(map1);

        List<Integer> paymasterIdList = new ArrayList<Integer>();
        if (null != arr && arr.size() > 0) {
            for (StudentPayMaster paymaster : arr) {
                paymasterIdList.add(paymaster.getId());
                if (null != paymaster) {
                    // 减去商品学习购买人数
                    if (null != paymaster.getCommodityId()) {
                        // 根据班型id查询商品信息id
                        CommodityProductRealtion comm = this.commodityProductRealtionServiceImpl.findByClassTypeId(paymaster.getCommodityId() + "");
                        if (null != comm && null != comm.getComId()) {
                            Commodity com = this.commodityServiceImpl.findCommodityById(comm.getComId());
                            if (null != com && null != com.getBuyNum() && com.getBuyNum() > 0) {
                                com.setBuyNum(com.getBuyNum() - 1);
                                this.commodityServiceImpl.update(com);
                            }
                        }
                    }
                }
            }
        }
        StudentPayMaster studentPayMaster = new StudentPayMaster();
        studentPayMaster.setCompanyId(WebUtils.getCurrentCompanyId());
        studentPayMaster.setStuId(stuId);
        studentPayMaster.setDeleteFlag(1);// 作废
        this.studentPayMasterServiceImpl.updateByStuId(studentPayMaster);
        Student stu = this.studentServiceImpl.findStudentById(stuId);
        UsersFront uf = this.usersFrontServiceImpl.findUsersFrontById(stu.getUserId());
        if (uf != null) {
            uf.setVipFlag(0);// 取消VIP标记
        }
        this.usersFrontServiceImpl.update(uf);

        // 删除观看记录
        if (null != arr && arr.size() > 0) {
            for (StudentPayMaster paymaster : arr) {
                List<Integer> classModuleList = this.classTypeModuleRelationServiceImpl.findClassModuleIdsByClassTypeId(paymaster.getCommodityId());
                if (null != classModuleList && classModuleList.size() > 0) {
                    for (Integer classModuleId : classModuleList) {
                        // 删除录播记录
                        List<Integer> chapterList = this.courseVideoChapterServiceImpl.findIdByModuleId(classModuleId);
                        if (null != chapterList && chapterList.size() > 0) {
                            for (Integer chapterId : chapterList) {
                                List<Integer> lectureList = this.courseVideoLectureServiceImpl.findIdByChapterId(chapterId);
                                if (null != lectureList && lectureList.size() > 0) {
                                    for (Integer lessonId : lectureList) {
                                        Map<String, Integer> userLessonTimeMap = new HashMap<String, Integer>();
                                        userLessonTimeMap.put("userId", this.usersFrontServiceImpl.findUserFrontByStudentId(stuId).getId());
                                        userLessonTimeMap.put("lessonId", lessonId);
                                        this.userLessonTimeServiceImpl.deleteByUserIdAndLessonId(userLessonTimeMap);
                                    }
                                }
                            }
                        }

                        // 删除直播记录
                        List<Integer> classModuleNolist = this.classModuleNoServiceImpl.findClassModuleNoIdsByModuleId(classModuleId);
                        for (Integer classModuleNoId : classModuleNolist) {
                            List<Integer> classModuleLessonlist = this.classModuleLessonServiceImpl.findClassModuleLessonIdsByModuleNoId(classModuleNoId);
                            for (Integer classModuleLessonId : classModuleLessonlist) {
                                Map<String, Integer> userLessonTimeMap = new HashMap<String, Integer>();
                                userLessonTimeMap.put("userId", this.usersFrontServiceImpl.findUserFrontByStudentId(stuId).getId());
                                userLessonTimeMap.put("lessonId", classModuleLessonId);
                                this.userLessonTimeServiceImpl.deleteByUserIdAndLessonId(userLessonTimeMap);
                            }
                        }
                    }
                }
            }
        }
        // 删除作业记录
        // 删除作业记录：开�?
        Map<String, Object> homeworkMap = null;
        if (null != arr && arr.size() > 0) {
            for (StudentPayMaster paymaster : arr) {
                homeworkMap = new HashMap<String, Object>();
                homeworkMap.put("companyId", WebUtils.getCurrentCompanyId());
                homeworkMap.put("stuId", stuId);
                homeworkMap.put("courseId", paymaster.getCommodityId());
                this.homeworkServiceImpl.deleteHomeworkHistory(homeworkMap);
            }
        }
        // 删除作业记录：课程包
        if (null != arr1 && arr1.size() > 0) {
            for (StudentPayMaster paymaster : arr1) {
                // 课程包包含的课程
                List<Integer> list = this.classPackageServiceImpl.findClassPackageAllClasses(paymaster.getCommodityId());
                if (null != list && list.size() > 0) {
                    for (Integer i : list) {
                        homeworkMap = new HashMap<String, Object>();
                        homeworkMap.put("companyId", WebUtils.getCurrentCompanyId());
                        homeworkMap.put("stuId", stuId);
                        homeworkMap.put("courseId", i);
                        this.homeworkServiceImpl.deleteHomeworkHistory(homeworkMap);
                    }
                }
            }
        }
        // 删除作业记录：结�?
        this.log_student.info(">>> [取消报名] " + "状�?�：success" + ", 信息�?" + "公司ID = " + WebUtils.getCurrentCompanyId() + ", 操作人ID = "
                + WebUtils.getCurrentUser().getId() + ", 学生ID = " + stuId + ", 订单List = num�?" + paymasterIdList.size() + " list�?" + paymasterIdList);
        return true;
    }

    // 使用�?
    @ResponseBody
    @RequestMapping(value = "/isDisabled")
    public Boolean isDisabled(String code) {
        SysConfigService search = new SysConfigService();
        search.setCompanyId(WebUtils.getCurrentCompanyId());
        search.setDelFlag(0);
        search.setGroupCode(code);
        List<SysConfigService> list = this.SysConfigServiceImpl.findSysConfigServiceByCompany(search);
        if (list != null && list.size() > 0) {
            return true;
        }
        return false;
    }

    /**
     * 后台接收Date转换
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @ResponseBody
    @RequestMapping(value = "/checkHasCommodity", method = RequestMethod.POST)
    public String checkHasCommodity(String commodityId, String stuId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("stuId", stuId);
        map.put("commodityId", Integer.parseInt(commodityId));
        Integer count = this.studentPayMasterServiceImpl.queryPayMasterBuyCommodityByStuIdAndCommodityId(map);
        if (count > 0) {
            return "error";
        }
        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "/checkHasCommodityByChangeStudent", method = RequestMethod.POST)
    public String checkHasCommodityByChangeStudent(Integer studentId, Integer mid) {
        StudentPayMaster payMaster = this.studentPayMasterServiceImpl.findStudentPayMasterById(mid);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("stuId", studentId);
        map.put("commodityId", payMaster.getCommodityId());
        Integer count = this.studentPayMasterServiceImpl.queryPayMasterBuyCommodityByStuIdAndCommodityId(map);
        if (count > 0) {
            return "error";
        }
        return "success";
    }
    
    
    private void sendWXTemplate(Commodity comm,Student stu,UsersFront uf){
		try{
		    String openId = uf.getWxOpenId();
		    if(StringUtils.isBlank(openId)){
		    	log.info("sendWXTemplate openId is null by user :"+uf.getId());
		        return;
		    }
			ClassType classType = classTypeServiceImpl.findClassTypeByCommodity(comm.getId());
			String token = weiXinServiceImpl.wxGetToken(FileUtil.props.getProperty("wxBaseUrl"), FileUtil.props.getProperty("wxAppId"), FileUtil.props.getProperty("wxSecret"));
			String template = FileUtil.props.getProperty("signUpResultTemplateMsg");//报名结果通知
			com.alibaba.fastjson.JSONObject paramsJson = new com.alibaba.fastjson.JSONObject();
			paramsJson.put("first", "尊敬�?"+stu.getUsername()+":您好");
			paramsJson.put("class", comm.getName());
			paramsJson.put("add", "http://www.cdds365.com");
			paramsJson.put("remark", "请准时上�?");
			List<ClassModuleLesson> cmlList = new ArrayList<ClassModuleLesson>();
			List<ClassModule> modulesVoList=classModuleServiceImpl.findModulesByClassTypeId(classType.getId());
			for(ClassModule module:modulesVoList){
				if(StringUtils.equals(module.getTeachMethod(),"TEACH_METHOD_LIVE")){
					//查询模块对应的班�?
					List<ClassModuleNo> list=classModuleNoServiceImpl.findByCmId(module.getId(),classType.getId());
					if(!list.isEmpty()&&list.size()>0){
						ClassModuleNo mNo=list.get(0);
						//查询班号对应的课�?
						List<ClassModuleLesson> lessonList=classModuleLessonServiceImpl.findClassModuleLessonByModuleNoId(mNo.getId());
						if(cmlList.size() > 0){
							cmlList.addAll(lessonList);
						}
					
					}
					
				}
			}
			if(cmlList.size() > 0){
				paramsJson.put("time", cmlList.get(0).getLessonDate());//获取课次上课时间
			}
			weiXinServiceImpl.wxSendTemplate(token, openId, template, paramsJson, FileUtil.props.getProperty("wxBaseUrl"));
		}catch(Exception e){
			log.error("sendWXTemplate is error :", e);
		}
		
    }

}
