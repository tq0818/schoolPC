package com.yuxin.wx.controller.classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuxin.wx.api.classes.IClassModuleLessonService;
import com.yuxin.wx.api.classes.IClassModuleNoService;
import com.yuxin.wx.api.classes.IClassTypeModuleRelationService;
import com.yuxin.wx.api.classes.IClassTypeService;
import com.yuxin.wx.api.commodity.ICommodityProductRealtionService;
import com.yuxin.wx.api.commodity.ICommodityService;
import com.yuxin.wx.api.company.ICompanyFunctionSetService;
import com.yuxin.wx.api.company.ICompanyRegisterConfigService;
import com.yuxin.wx.api.course.ICourseVideoChapterService;
import com.yuxin.wx.api.course.ICourseVideoLectureService;
import com.yuxin.wx.api.homework.IHomeworkService;
import com.yuxin.wx.api.student.IStudentAgentMaterialService;
import com.yuxin.wx.api.student.IStudentPayMasterService;
import com.yuxin.wx.api.student.IStudentService;
import com.yuxin.wx.api.user.IUserLessonTimeService;
import com.yuxin.wx.api.user.IUsersFrontService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.commodity.Commodity;
import com.yuxin.wx.model.commodity.CommodityProductRealtion;
import com.yuxin.wx.model.company.CompanyFunctionSet;
import com.yuxin.wx.model.company.CompanyRegisterConfig;
import com.yuxin.wx.model.student.Student;
import com.yuxin.wx.model.student.StudentAgentMaterial;
import com.yuxin.wx.model.student.StudentPayMaster;
import com.yuxin.wx.model.user.UsersFront;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.classes.ClassTypeVo;
import com.yuxin.wx.vo.student.StudentListVo;

/**
 * Controller of ClassType
 *
 * @author wang.zx
 * @date 2014-12-5
 */
@Controller
@RequestMapping("/classStu")
public class ClassTypeStudentsController {

    private Log log_student = LogFactory.getLog("student");
    @Autowired
    private IClassTypeService classTypeServiceImpl;
    @Autowired
    private ICompanyFunctionSetService companyFunctionSetServiceImpl;
    @Autowired
    private IStudentService studentServiceImpl;
    @Autowired
    private IStudentPayMasterService studentPayMasterServiceImpl;
    @Autowired
    private IStudentAgentMaterialService studentAgentMaterialServiceImpl;
    @Autowired
    private IUsersFrontService usersFrontServiceImpl;
    @Autowired
    private ICompanyRegisterConfigService companyRegisterConfigServiceImpl;
    @Autowired
    private ICommodityProductRealtionService commodityProductRealtionServiceImpl;
    @Autowired
    private ICommodityService commodityServiceImpl;
    @Autowired
    private IClassTypeModuleRelationService classTypeModuleRelationServiceImpl;
    @Autowired
    private ICourseVideoChapterService courseVideoChapterServiceImpl;
    @Autowired
    private ICourseVideoLectureService courseVideoLectureServiceImpl;
    @Autowired
    private IUserLessonTimeService userLessonTimeServiceImpl;
    @Autowired
    private IHomeworkService homeworkServiceImpl;
    @Autowired
    private IClassModuleNoService classModuleNoServiceImpl;
    @Autowired
    private IClassModuleLessonService classModuleLessonServiceImpl;

    // 跳转到学员列表页面
    @RequestMapping(value = "/studentList/{id}/{lable}")
    public String forwardStudentList(Model model, @PathVariable Integer id, @PathVariable String lable) {
        // 根据班型id查询详情
        Map<String, String> map = new HashMap<String, String>();
        map.put("classId", "" + id);
        ClassTypeVo classType = this.classTypeServiceImpl.findClassTypeDetail(map);
        model.addAttribute("classType", classType);
        model.addAttribute("ct", classType);
        model.addAttribute("type", "update");
        model.addAttribute("lable", lable);
        CompanyFunctionSet search = new CompanyFunctionSet();
        search.setFunctionCode("COMPANY_FUNCTION_COURSE");
        search.setCompanyId(WebUtils.getCurrentCompanyId());
        CompanyFunctionSet cfs = this.companyFunctionSetServiceImpl.findCompanyUseCourse(search);
        // 查看该机构是否有删除学员的功能
        search.setFunctionCode("COMPANY_REMOVE_PAYMASTER");
        CompanyFunctionSet isDelete = this.companyFunctionSetServiceImpl.findCompanyUseCourse(search);
        if (null != isDelete && "1".equals(isDelete.getStatus())) {
            model.addAttribute("isDelete", isDelete.getStatus());
        } else {
            model.addAttribute("isDelete", 0);
        }
        // 查询公司注册设置
        CompanyRegisterConfig crc = new CompanyRegisterConfig();
        crc.setCompanyId(WebUtils.getCurrentCompanyId());
        crc = this.companyRegisterConfigServiceImpl.queryByCompanyId(crc);
        if (null != crc && crc.getCloseFlag() == 1) {
            crc.setMobileFlag(1);
        }
        model.addAttribute("registConfig", crc);
        search.setFunctionCode("CLASS_POTOCOL_SET");
        search = this.companyFunctionSetServiceImpl.findCompanyUseCourse(search);
        if (search != null && "1".equals(search.getStatus())) {
            model.addAttribute("courseProtocolConfig", true);
        } else {
            model.addAttribute("courseProtocolConfig", false);
        }
        if (cfs != null && "1".equals(cfs.getStatus())) {
            return "classType/manageStu/studentList-full";
        }
        return "classType/manageStu/studentList";
    }

    /**
     * 
     * Class Name: StudentController.java
     * 
     * @Description: 查询学员列表数据
     * @author zhang.zx
     * @date 2015年9月29日 下午4:46:54
     * @modifier
     * @modify-date 2015年9月29日 下午4:46:54
     * @version 1.0
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryStudentsList")
    public PageFinder<StudentListVo> queryStudentsListData(StudentListVo search) {
        String flag = "";
        search.setCompanyId(WebUtils.getCurrentCompanyId());
        search.setPageSize(10);
        PageFinder<StudentListVo> pageFinder = this.studentServiceImpl.queryStudentsListByClassTypeId(search);
        Subject subject = SecurityUtils.getSubject();
        if (subject.isPermitted("student_agent")) {
            flag = "1";
        }
        List<StudentListVo> arr = pageFinder.getData();
        for (StudentListVo stu : arr) {
            if (null != stu) {
                List<StudentPayMaster> list = this.studentPayMasterServiceImpl.findByStuMbile(stu.getMobile(), WebUtils.getCurrentCompanyId(),
                        WebUtils.getCurrentSchoolId());
                if (list.size() > 0) {
                    // 需要补费
                    stu.setIspay("1");
                }
                stu.setAgentFlag(flag);
            }
        }
        return pageFinder;
    }

    /**
     * 
     * Class Name: StudentPayMasterController.java
     * 
     * @Description: ajax方式加载学员信息列表
     * @author zhang.zx
     * @date 2014年12月12日 下午3:33:04
     * @version 1.0
     * @param search
     * @return
     */
    @RequestMapping(value = "/showSignUp", method = RequestMethod.POST)
    public String showSignUp(Model model, Student student, Integer classTypeId, String lable) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("classId", "" + classTypeId);
        ClassTypeVo classType = this.classTypeServiceImpl.findClassTypeDetail(map);
        model.addAttribute("classType", classType);
        model.addAttribute("lable", lable);
        student.setCompanyId(WebUtils.getCurrentCompanyId());
        Student st = this.studentServiceImpl.findByMobile(student);
        if (st != null && st.getId() != null) {
            model.addAttribute("student", st);
        } else {
            model.addAttribute("student", student);
        }
        // 查询公司注册设置
        CompanyRegisterConfig crc = new CompanyRegisterConfig();
        crc.setCompanyId(WebUtils.getCurrentCompanyId());
        crc = this.companyRegisterConfigServiceImpl.queryByCompanyId(crc);
        if (null != crc && crc.getCloseFlag() == 1) {
            crc.setMobileFlag(1);
        }
        model.addAttribute("registConfig", crc);
        CompanyFunctionSet search = new CompanyFunctionSet();
        search.setFunctionCode("COMPANY_FUNCTION_COURSE");
        search.setCompanyId(WebUtils.getCurrentCompanyId());
        CompanyFunctionSet cfs = this.companyFunctionSetServiceImpl.findCompanyUseCourse(search);

        CompanyFunctionSet CompanyFunctionSet = new CompanyFunctionSet();
        CompanyFunctionSet.setCompanyId(WebUtils.getCurrentCompanyId());
        CompanyFunctionSet.setFunctionCode("STUDENT_GROUP");
        List<CompanyFunctionSet> CompanyFunctionSetList = companyFunctionSetServiceImpl.findCompanyFunctionSetByPage(CompanyFunctionSet);
        CompanyFunctionSet groupsearch = CompanyFunctionSetList != null && CompanyFunctionSetList.size() > 0 ? CompanyFunctionSetList.get(0) : null;
        if (null != groupsearch) {
            model.addAttribute("sgOpen", groupsearch.getStatus());
        }
        if (cfs != null && "1".equals(cfs.getStatus())) {
            return "classType/manageStu/student-signup-full";
        }
        return "classType/manageStu/student-signup";
    }

    /**
     * 
     * Class Name: ClassTypeStudentsController.java
     * 
     * @Description: 批量报名
     * @author zhang.zx
     * @date 2016年3月8日 下午3:06:06
     * @modifier
     * @modify-date 2016年3月8日 下午3:06:06
     * @version 1.0
     * @param model
     * @param list
     * @return
     */
    @RequestMapping(value = "/showSignUpMany", method = RequestMethod.POST)
    public String showSignUpMany(Model model, String list) {
        String[] mobileList;
        if (list != null && !"".equals(list)) {
            mobileList = list.split(",");
        } else {
            mobileList = new String[1];
        }
        List<Student> resultList = new ArrayList<Student>();
        for (String mobile : mobileList) {
            Student student = new Student();
            student.setCompanyId(WebUtils.getCurrentCompanyId());
            student.setMobile(mobile);
            Student st = this.studentServiceImpl.findByMobile(student);
            if (st != null && st.getId() != null) {
                if (st.getAge() == null || "".equals(st.getAge())) {
                    st.setAge(0);
                }
                resultList.add(st);
            } else {
                student.setAge(0);
                resultList.add(student);
            }
        }
        model.addAttribute("student", resultList);
        return "student/student-signupMany";
    }

    /**
     * 
     * Class Name: ClassTypeStudentsController.java
     * 
     * @Description: 移除此课程
     * @author zhang.zx
     * @date 2016年3月8日 下午1:45:50
     * @modifier
     * @modify-date 2016年3月8日 下午1:45:50
     * @version 1.0
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteStuPaymaster", method = RequestMethod.POST)
    public String deleteStuPaymaster(Integer id, Integer classTypeId, Model model) {
        this.log_student.info("===");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("stuId", id);
        map.put("commodityId", classTypeId);
        map.put("commodityType", "COMMODITY_CLASS");
        List<StudentPayMaster> arr = this.studentPayMasterServiceImpl.findpayIdByClassTypeStudent(map);
        if (null != arr && arr.size() > 0) {
            for (StudentPayMaster paymaster : arr) {
                if (null != paymaster) {
                    StudentPayMaster master = new StudentPayMaster();
                    master.setId(paymaster.getId());
                    master.setDeleteFlag(1);
                    master.setPayStatusCode("ORDER_DELTED");
                    this.studentPayMasterServiceImpl.update(master);
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
            // 删除观看记录
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
                                        userLessonTimeMap.put("userId", this.usersFrontServiceImpl.findUserFrontByStudentId(id).getId());
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
                                userLessonTimeMap.put("userId", this.usersFrontServiceImpl.findUserFrontByStudentId(id).getId());
                                userLessonTimeMap.put("lessonId", classModuleLessonId);
                                this.userLessonTimeServiceImpl.deleteByUserIdAndLessonId(userLessonTimeMap);
                            }
                        }
                    }
                }
            }
            // 删除作业记录
            for (StudentPayMaster paymaster : arr) {
                Map<String, Object> homeworkMap = new HashMap<String, Object>();
                homeworkMap.put("companyId", WebUtils.getCurrentCompanyId());
                homeworkMap.put("stuId", id);
                homeworkMap.put("courseId", paymaster.getCommodityId());
                this.homeworkServiceImpl.deleteHomeworkHistory(homeworkMap);
            }
            this.log_student.info(">>> [移出课程] " + "状态：success" + ", 信息：" + "公司ID = " + WebUtils.getCurrentCompanyId() + ", 操作人ID = "
                    + WebUtils.getCurrentUser().getId() + ", 学生ID = " + id + ", 课程ID = " + classTypeId);
            return "success";
        }
        this.log_student.error(">>> [移出课程] " + "状态：error" + ", 信息：" + "公司ID = " + WebUtils.getCurrentCompanyId() + ", 操作人ID = "
                + WebUtils.getCurrentUser().getId() + ", 学生ID = " + id + ", 课程ID = " + classTypeId);
        return "error";
    }

    /**
     * 
     * Class Name: ClassTypeStudentsController.java
     * 
     * @Description: 学员异动
     * @author zhang.zx
     * @date 2016年3月9日 上午10:16:57
     * @modifier
     * @modify-date 2016年3月9日 上午10:16:57
     * @version 1.0
     * @param mobile
     * @param model
     * @return
     */
    @RequestMapping(value = "/toTransaction", method = RequestMethod.POST)
    public String toTransaction(String mobile, Model model) {
        Student student = this.studentServiceImpl.findMessageByMobile(mobile, WebUtils.getCurrentCompanyId(), WebUtils.getCurrentSchoolId());
        model.addAttribute("student", student);
        CompanyFunctionSet search = new CompanyFunctionSet();
        search.setFunctionCode("COMPANY_FUNCTION_COURSE");
        search.setCompanyId(WebUtils.getCurrentCompanyId());
        CompanyFunctionSet cfs = this.companyFunctionSetServiceImpl.findCompanyUseCourse(search);
        if (cfs != null && "1".equals(cfs.getStatus())) {
            return "classType/manageStu/transaction-full";
        }
        return "classType/manageStu/transaction";
    }

    /**
     * 
     * Class Name: ClassTypeStudentsController.java
     * 
     * @Description: 补费
     * @author zhang.zx
     * @date 2016年3月9日 上午10:42:59
     * @modifier
     * @modify-date 2016年3月9日 上午10:42:59
     * @version 1.0
     * @param model
     * @param mobile
     * @param studentPayMasterId
     * @return
     */
    @RequestMapping(value = "/toMessage", method = RequestMethod.POST)
    public String toSearchBaseMessage(Model model, String mobile, Integer studentPayMasterId) {
        StudentPayMaster payMaster = this.studentPayMasterServiceImpl.findStudentPayMasterById(studentPayMasterId);
        model.addAttribute("payMaster", payMaster);
        Student student = this.studentServiceImpl.findMessageByMobile(mobile, WebUtils.getCurrentCompanyId(), WebUtils.getCurrentSchoolId());
        model.addAttribute("student", student);
        model.addAttribute("mobile", mobile);
        return "classType/manageStu/payMessage";
    }

    /**
     * 
     * Class Name: StudentAgentMaterialController.java
     * 
     * @Description: 跳转到学员报考材料页面
     * @author zhang.zx
     * @date 2015年10月12日 上午10:05:56
     * @modifier
     * @modify-date 2015年4月25日 上午10:05:56
     * @version 1.0
     * @return
     */
    @RequestMapping(value = "/stuMaterial2", method = RequestMethod.POST)
    public String forwardStuMaterial2(String mobile, Model model) {
        model.addAttribute("mobile", mobile);
        return "classType/manageStu/studentMaterial2";
    }

    /**
     * 
     * Class Name: StudentAgentMaterialController.java
     * 
     * @Description: 跳转到学员报考材料页面
     * @author zhang.zx
     * @date 2015年4月25日 下午2:44:35
     * @modifier
     * @modify-date 2015年4月25日 下午2:44:35
     * @version 1.0
     * @param model
     * @param mobile
     * @param studentPayMasterId
     * @return
     */
    @RequestMapping(value = "/showStuMaterial", method = RequestMethod.POST)
    public String showStuMaterial(Model model, String mobile, Integer studentPayMasterId) {
        if (studentPayMasterId != null) {
            StudentPayMaster payMaster = this.studentPayMasterServiceImpl.findStudentPayMasterById(studentPayMasterId);
            model.addAttribute("payMaster", payMaster);
            Student student = this.studentServiceImpl.findStudentById(payMaster.getStuId());
            model.addAttribute("student", student);
            model.addAttribute("mobile", mobile);
            List<StudentAgentMaterial> stuMaterialList = this.studentAgentMaterialServiceImpl.findStudentAgentMaterialByStuId(student.getId(),
                    studentPayMasterId);
            model.addAttribute("stuMaterialList", stuMaterialList);
        }
        return "classType/manageStu/studentMaterialDetailList";
    }

    // 导入成功后查询导入数据页面
    @RequestMapping(value = "/queryData")
    public String importStudentData(Model model, Integer id, String lable, String stuMobiles) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("classId", "" + id);
        ClassTypeVo classType = this.classTypeServiceImpl.findClassTypeDetail(map);
        model.addAttribute("classType", classType);
        model.addAttribute("lable", lable);
        /*
         * String newstr = ""; if (null != stuMobiles && !"".equals(stuMobiles))
         * { newstr = stuMobiles.substring(0, stuMobiles.length() - 1); }
         */
        model.addAttribute("stuMobiles", stuMobiles);
        // 查询公司注册设置
        CompanyRegisterConfig crc = new CompanyRegisterConfig();
        crc.setCompanyId(WebUtils.getCurrentCompanyId());
        crc = this.companyRegisterConfigServiceImpl.queryByCompanyId(crc);
        if (null != crc && crc.getCloseFlag() == 1) {
            crc.setMobileFlag(1);
        }
        model.addAttribute("registConfig", crc);
        return "classType/manageStu/importStudentData";
    }

    @ResponseBody
    @RequestMapping(value = "/queryStupaymater")
    public String queryStuPaymaterNum(Integer stuId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("stuId", stuId);
        List<StudentPayMaster> arr = this.studentPayMasterServiceImpl.findpayIdByStudentsId(map);
        if (arr.size() <= 0) {
            // 如果学员已经没有订单，修改users_fornt中的vip_flag
            UsersFront usersFront = this.usersFrontServiceImpl.findUserFrontByStudentId(stuId);
            if (null != usersFront) {
                UsersFront user_front = new UsersFront();
                user_front.setId(usersFront.getId());
                user_front.setVipFlag(0);
                this.usersFrontServiceImpl.update(user_front);
            }
        }
        return "success";
    }
}
