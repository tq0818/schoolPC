package com.yuxin.wx.controller.student;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuxin.wx.api.classes.IClassModuleLessonService;
import com.yuxin.wx.api.classes.IClassModuleNoService;
import com.yuxin.wx.api.classes.IClassTypeModuleRelationService;
import com.yuxin.wx.api.commodity.ICommodityService;
import com.yuxin.wx.api.company.ICompanyCashFlowService;
import com.yuxin.wx.api.course.ICourseVideoChapterService;
import com.yuxin.wx.api.course.ICourseVideoLectureService;
import com.yuxin.wx.api.homework.IHomeworkService;
import com.yuxin.wx.api.student.IStudentFeeRefundService;
import com.yuxin.wx.api.student.IStudentFeeStageService;
import com.yuxin.wx.api.student.IStudentPayChangeInfoService;
import com.yuxin.wx.api.student.IStudentPayMasterService;
import com.yuxin.wx.api.student.IStudentPaySlaveService;
import com.yuxin.wx.api.student.IStudentService;
import com.yuxin.wx.api.user.IUserLessonTimeService;
import com.yuxin.wx.api.user.IUsersFrontService;
import com.yuxin.wx.model.commodity.Commodity;
import com.yuxin.wx.model.company.CompanyCashFlow;
import com.yuxin.wx.model.student.Student;
import com.yuxin.wx.model.student.StudentFeeRefund;
import com.yuxin.wx.model.student.StudentFeeStage;
import com.yuxin.wx.model.student.StudentPayChangeInfo;
import com.yuxin.wx.model.student.StudentPayMaster;
import com.yuxin.wx.model.student.StudentPaySlave;
import com.yuxin.wx.model.user.UsersFront;
import com.yuxin.wx.utils.WebUtils;

/**
 * Controller of StudentFeeRefund
 *
 * @author chopin
 * @date 2015-5-7
 */
@Controller
@RequestMapping("/studentFeeRefund")
public class StudentFeeRefundController {
    Log log_student = LogFactory.getLog("student");
    @Autowired
    private IStudentFeeRefundService studentFeeRefundServiceImpl;
    @Autowired
    private IStudentPayMasterService studentPayMasterServiceImpl;
    @Autowired
    private IStudentFeeStageService studentFeeStageServiceImpl;
    @Autowired
    private IStudentPayChangeInfoService studentPayChangeInfoServiceImpl;
    @Autowired
    private ICompanyCashFlowService companyCashFlowServiceImpl;
    @Autowired
    private ICommodityService commodityServiceImpl;
    @Autowired
    private IStudentService studentServieImpl;
    @Autowired
    private IUsersFrontService usersFrontServiceImpl;
    @Autowired
    private IClassTypeModuleRelationService classTypeModuleRelationServiceImpl;
    @Autowired
    private ICourseVideoChapterService courseVideoChapterServiceImpl;
    @Autowired
    private ICourseVideoLectureService courseVideoLectureServiceImpl;
    @Autowired
    private IUserLessonTimeService userLessonTimeServiceImpl;
    @Autowired
    private IStudentPaySlaveService studentPaySlaveServiceImpl;
    @Autowired
    private IHomeworkService homeworkServiceImpl;
    @Autowired
    private IClassModuleNoService classModuleNoServiceImpl;
    @Autowired
    private IClassModuleLessonService classModuleLessonServiceImpl;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model, StudentFeeRefund search) {
        if (search == null) {
            search = new StudentFeeRefund();
            // search.setPageSize(20);
        }
        model.addAttribute("list", this.studentFeeRefundServiceImpl.findStudentFeeRefundByPage(search));
        return "studentFeeRefund/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(StudentFeeRefund StudentFeeRefund) {
        this.studentFeeRefundServiceImpl.insert(StudentFeeRefund);
        return "redirect:/studentFeeRefund";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(StudentFeeRefund StudentFeeRefund) {
        this.studentFeeRefundServiceImpl.update(StudentFeeRefund);
        return "redirect:/studentFeeRefund";
    }

    @RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
    public String del(Model model, @PathVariable Integer id) {
        this.studentFeeRefundServiceImpl.deleteStudentFeeRefundById(id);
        return "redirect:/studentFeeRefund";
    }

    @ResponseBody
    @RequestMapping(value = "/{id:\\d+}", method = RequestMethod.GET)
    public StudentFeeRefund getJson(Model model, @PathVariable Integer id) {
        return this.studentFeeRefundServiceImpl.findStudentFeeRefundById(id);
    }

    @ResponseBody
    @RequestMapping(value = "returnFee", method = RequestMethod.POST)
    public String returnFee(HttpServletRequest request, StudentFeeRefund studentFeeRefund, StudentPayChangeInfo changeInfo, Double hasUsed) {
        this.log_student.info("===");
        StudentPayMaster payMaster = this.studentPayMasterServiceImpl.findStudentPayMasterById(studentFeeRefund.getPayMasterId());
        payMaster.setPayStatusCode("ORDER_DELTED");
        payMaster.setDeleteFlag(1);
        this.studentPayMasterServiceImpl.update(payMaster);

        // 删除观看记录
        List<Integer> classModuleList = this.classTypeModuleRelationServiceImpl.findClassModuleIdsByClassTypeId(payMaster.getCommodityId());
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
                                userLessonTimeMap.put("userId", this.usersFrontServiceImpl.findUserFrontByStudentId(payMaster.getStuId()).getId());
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
                        userLessonTimeMap.put("userId", this.usersFrontServiceImpl.findUserFrontByStudentId(payMaster.getStuId()).getId());
                        userLessonTimeMap.put("lessonId", classModuleLessonId);
                        this.userLessonTimeServiceImpl.deleteByUserIdAndLessonId(userLessonTimeMap);
                    }
                }
            }
        }
        // 删除作业记录
        Map<String, Object> homeworkMap = new HashMap<String, Object>();
        homeworkMap.put("companyId", WebUtils.getCurrentCompanyId());
        homeworkMap.put("stuId", payMaster.getStuId());
        homeworkMap.put("courseId", payMaster.getCommodityId());
        this.homeworkServiceImpl.deleteHomeworkHistory(homeworkMap);

        StudentPaySlave se = new StudentPaySlave();
        se.setCompanyId(WebUtils.getCurrentCompanyId());
        se.setPayMasterId(payMaster.getId());
        se.setStuId(payMaster.getStuId());
        List<StudentPaySlave> ar = this.studentPaySlaveServiceImpl.queryStuSlaveBywhere(se);
        if (null != ar && ar.size() > 0) {
            for (StudentPaySlave s : ar) {
                if (null != s) {
                    this.studentPaySlaveServiceImpl.updateStuPaySlave(s.getId());
                }
            }
        }
        this.studentFeeStageServiceImpl.deleteByMasterId(studentFeeRefund.getPayMasterId());
        double cashReal = 0; // 现金交费
        double postReal = 0; // 刷卡缴费
        double checkReal = 0; // 支票
        double remitReal = 0; // 汇款
        // 查询出该订单下的所有交费的信息
        List<StudentFeeStage> studentFeeStageList = this.studentFeeStageServiceImpl.findAll(payMaster.getId());
        // 查询所有已交费用
        for (int i = 0; i < studentFeeStageList.size(); i++) {
            StudentFeeStage studentFeeStage = studentFeeStageList.get(i);
            if ("1".equals(studentFeeStage.getStageStatus())) {
                studentFeeStageList.remove(studentFeeStage);
                i--;
                if (studentFeeStage.getCashReal() != null) {
                    cashReal = cashReal + studentFeeStage.getCashReal();
                }
                if (studentFeeStage.getCheckReal() != null) {
                    checkReal = checkReal + studentFeeStage.getCheckReal();
                }
                if (studentFeeStage.getPosReal() != null) {
                    postReal = postReal + studentFeeStage.getPosReal();
                }
                if (studentFeeStage.getRemitReal() != null) {
                    remitReal = remitReal + studentFeeStage.getRemitReal();
                }
            }
        }
        if (hasUsed == null) {
            hasUsed = 0.0;
        }
        Double total = cashReal + checkReal + postReal + remitReal;
        if ((total - studentFeeRefund.getRefundFee()) != hasUsed) {
            return "error";
        }
        Double posRefund = studentFeeRefund.getPosRefund() == null ? 0 : studentFeeRefund.getPosRefund();
        Double checkRefund = studentFeeRefund.getCheckRefund() == null ? 0 : studentFeeRefund.getCheckRefund();
        Double cashRefund = studentFeeRefund.getCashRefund() == null ? 0 : studentFeeRefund.getCashRefund();
        Double remitRefund = studentFeeRefund.getRemitRefund() == null ? 0 : studentFeeRefund.getRemitRefund();

        if (posRefund > postReal) {
            return "pos";
        }
        if (checkRefund > checkReal) {
            return "check";
        }
        if (cashRefund > cashReal) {
            return "cash";
        }
        if (remitRefund > remitReal) {
            return "remit";
        }
        studentFeeRefund.setCreateTime(new Date());
        studentFeeRefund.setRefundDate(new Date());
        studentFeeRefund.setCreator(WebUtils.getCurrentUserId(request));
        this.studentFeeRefundServiceImpl.insert(studentFeeRefund);
        changeInfo.setRefundNum(studentFeeRefund.getRefundFee());
        changeInfo.setCompanyId(WebUtils.getCurrentCompanyId());
        this.studentPayChangeInfoServiceImpl.insert(changeInfo);
        // 更新公司流水表
        try {
            CompanyCashFlow t = new CompanyCashFlow();
            t.setTradeReason("REFUND");
            t.setStuId(payMaster.getStuId());
            t.setCompanyId(WebUtils.getCurrentCompanyId());
            t.setTradeAmount(-(studentFeeRefund.getRefundFee()));
            t.setTradeDate(new Date());
            t.setTradeWay("ORDER_OPERTE_STOPED");
            t.setTradeSource("PAY_OFFLINE");
            t.setUpdateTime(new Date());
            t.setUpdator(WebUtils.getCurrentUserId(request));
            t.setSchoolId(WebUtils.getCurrentSchoolId());
            this.companyCashFlowServiceImpl.insert(t);
        } catch (Exception e) {
            this.log_student.error(">>> [退费] " + "状态：添加公司流水表失败" + ", 信息：" + "公司ID = " + WebUtils.getCurrentCompanyId() + ", 操作人ID = "
                    + WebUtils.getCurrentUserId(request) + ", 学生ID = " + studentFeeRefund.getStuId() + ", 订单ID = " + studentFeeRefund.getPayMasterId(), e);
            e.printStackTrace();
        }

        // 维护usersfront(vip_flag) commodity(buy_num)
        Integer commodityId = this.studentPayMasterServiceImpl.queryCommodityIdById(studentFeeRefund.getPayMasterId());
        Commodity commodity = this.commodityServiceImpl.findCommodityById(this.commodityServiceImpl.findCommodityIdByClassTypeId(commodityId));
        if (commodity.getBuyNum() > 0) {
            commodity.setBuyNum(commodity.getBuyNum() - 1);
        } else {
            commodity.setBuyNum(0);
        }
        this.commodityServiceImpl.update(commodity);

        Student student = this.studentServieImpl.findStudentById(studentFeeRefund.getStuId());
        UsersFront usersFront = this.usersFrontServiceImpl.findUsersFrontById(student.getUserId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("stuId", student.getId());
        Integer count = this.studentPayMasterServiceImpl.queryPayMasterBuyCommodityByStuId(map);
        if (count > 0) {
            usersFront.setVipFlag(1);
        } else {
            usersFront.setVipFlag(0);
        }
        this.usersFrontServiceImpl.update(usersFront);

        this.log_student.info(">>> [退费] " + "状态：success" + ", 信息：" + "公司ID = " + WebUtils.getCurrentCompanyId() + ", 操作人ID = "
                + WebUtils.getCurrentUserId(request) + ", 学生ID = " + studentFeeRefund.getStuId() + ", 订单ID = " + studentFeeRefund.getPayMasterId());
        return "success";
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
}
