package com.yuxin.wx.controller.classes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.crypto.hash.Md5Hash;
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
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.classes.IClassModuleLessonService;
import com.yuxin.wx.api.classes.IClassModuleNoService;
import com.yuxin.wx.api.classes.IClassModuleService;
import com.yuxin.wx.api.classes.IClassPackageService;
import com.yuxin.wx.api.classes.IClassPackageStageService;
import com.yuxin.wx.api.classes.IClassTypeModuleRelationService;
import com.yuxin.wx.api.classes.IClassTypeService;
import com.yuxin.wx.api.commodity.ICommodityProductRealtionService;
import com.yuxin.wx.api.commodity.ICommodityService;
import com.yuxin.wx.api.company.ICompanyFunctionSetService;
import com.yuxin.wx.api.company.ICompanyMessageHistoryService;
import com.yuxin.wx.api.company.ICompanyRegisterConfigService;
import com.yuxin.wx.api.course.ICoursePotocolBindHistoryService;
import com.yuxin.wx.api.course.ICourseVideoChapterService;
import com.yuxin.wx.api.course.ICourseVideoLectureService;
import com.yuxin.wx.api.homework.IHomeworkService;
import com.yuxin.wx.api.student.IStudentPayMasterService;
import com.yuxin.wx.api.student.IStudentService;
import com.yuxin.wx.api.system.ISysConfigItemService;
import com.yuxin.wx.api.user.IUserLessonTimeService;
import com.yuxin.wx.api.user.IUsersFrontService;
import com.yuxin.wx.common.ExcelSheetEntity;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.common.SysConfigConstant;
import com.yuxin.wx.common.ViewFiles;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.classes.ClassPackage;
import com.yuxin.wx.model.classes.ClassPackageStage;
import com.yuxin.wx.model.commodity.Commodity;
import com.yuxin.wx.model.commodity.CommodityProductRealtion;
import com.yuxin.wx.model.company.CompanyFunctionSet;
import com.yuxin.wx.model.company.CompanyMessageHistory;
import com.yuxin.wx.model.company.CompanyRegisterConfig;
import com.yuxin.wx.model.course.CoursePotocolBindHistory;
import com.yuxin.wx.model.student.Student;
import com.yuxin.wx.model.student.StudentAgentMaterial;
import com.yuxin.wx.model.student.StudentFeeStage;
import com.yuxin.wx.model.student.StudentPayMaster;
import com.yuxin.wx.model.system.SysConfigItem;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.model.user.UsersFront;
import com.yuxin.wx.utils.EntityUtil;
import com.yuxin.wx.utils.PropertiesUtil;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.utils.smsClient.interfacej.SmsClientSend;
import com.yuxin.wx.vo.classes.ClassPackageConditionVo;
import com.yuxin.wx.vo.classes.ClassTypeVo;
import com.yuxin.wx.vo.classes.ClasspackageVo;
import com.yuxin.wx.vo.course.OrderManage;
import com.yuxin.wx.vo.student.StudentListVo;

/**
 * Controller of ClassPackage
 *
 * @author chopin
 * @date 2016-3-21
 */
@Controller
@RequestMapping("/classPackage")
public class ClassPackageController {
    Log log = LogFactory.getLog("log");
    Log log_student = LogFactory.getLog("student");
    @Autowired
    private IClassPackageService classPackageServiceImpl;
    @Autowired
    private ISysConfigItemService sysConfigItemServiceImpl;
    @Autowired
    private PropertiesUtil propertiesUtil;
    @Autowired
    private IClassTypeService classTypeServiceImpl;
    @Autowired
    private IStudentPayMasterService studentPayMasterServiceImpl;
    @Autowired
    private IStudentService studentServiceImpl;
    @Autowired
    private ICompanyFunctionSetService companyFunctionSetServiceImpl;
    @Autowired
    private IClassModuleService classModuleServiceImpl;
    @Autowired
    private IClassModuleNoService classModuleNoServiceImpl;
    @Autowired
    private IClassModuleLessonService classModuleLessonServiceImpl;
    @Autowired
    private ICourseVideoChapterService courseVideoChapterServiceImpl;
    @Autowired
    private ICommodityService commodityServiceImpl;
    @Autowired
    private ICommodityProductRealtionService commodityProductRealtionServiceImpl;
    @Autowired
    private IUsersFrontService usersFrontServiceImpl;
    @Autowired
    private ICompanyMessageHistoryService companyMessageHistoryServiceImpl;
    @Autowired
    private IClassPackageStageService classPackageStageServiceImpl;
    @Autowired
    private ICompanyRegisterConfigService companyRegisterConfigServiceImpl;
    @Autowired
    private IClassTypeModuleRelationService classTypeModuleRelationServiceImpl;
    @Autowired
    private ICourseVideoLectureService courseVideoLectureServiceImpl;
    @Autowired
    private IUserLessonTimeService userLessonTimeServiceImpl;
    @Autowired
    private ICoursePotocolBindHistoryService coursePotocolBindHistoryServiceImpl;
    @Autowired
    private IHomeworkService homeworkServiceImpl;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {

        return "coursePackage/list";
    }

    /**
     *
     * Class Name: ClassPackageController.java
     *
     * @Description: 查询课程包列表
     * @author zhang.zx
     * @date 2016年3月21日 下午8:35:52
     * @modifier
     * @modify-date 2016年3月21日 下午8:35:52
     * @version 1.0
     * @param search
     * @param model
     * @return
     */
    @RequestMapping(value = "/queryByList")
    public String queryClassPackageList(ClassPackage search, Model model) {
        String url = "http://" + this.propertiesUtil.getProjectImageUrl() + "/";
        search.setCompanyId(WebUtils.getCurrentCompanyId());
        search.setSchoolId(WebUtils.getCurrentSchoolId());
        search.setPageSize(7);
        PageFinder<ClassPackage> pageFinder = this.classPackageServiceImpl.findClassPackageLists(search);
        model.addAttribute("pageFinder", pageFinder);
        model.addAttribute("searchName", search.getName());
        model.addAttribute("packageCode", search.getCategoryCode());
        model.addAttribute("commodityPicUrl", url);
        return "coursePackage/packageAjaxList";
    }

    /**
     *
     * Class Name: ClassPackageController.java
     *
     * @Description: 管理课程包基本信息
     * @author zhang.zx
     * @date 2016年3月22日 上午11:25:10
     * @modifier
     * @modify-date 2016年3月22日 上午11:25:10
     * @version 1.0
     * @param id
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/manageBaseInfo/{lable}")
    public String manageClassPackageBaseInfo(@PathVariable String lable, HttpServletRequest request, Model model) {
        String id = request.getParameter("id");
        if (null != id) {
            ClassPackage classPackage = this.classPackageServiceImpl.findClassPackageById(Integer.parseInt(id));
            model.addAttribute("classPackage", classPackage);
        }
        model.addAttribute("lable", lable);
        return "coursePackage/packageInfo";
    }

    /**
     *
     * Class Name: ClassPackageController.java
     *
     * @Description: 添加课程包
     * @author zhang.zx
     * @date 2016年3月22日 下午1:36:03
     * @modifier
     * @modify-date 2016年3月22日 下午1:36:03
     * @version 1.0
     * @param classPackage
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveBaseInfo", method = RequestMethod.POST)
    public Integer add(ClassPackage classPackage, HttpServletRequest request) {
        classPackage.setCompanyId(WebUtils.getCurrentCompanyId());
        classPackage.setSchoolId(WebUtils.getCurrentSchoolId());
        classPackage.setDelFlag(0);
        classPackage.setOriginalPrice(0.00);
        classPackage.setPublishStatus("CLASS_UNPUBLISHED");
        classPackage.setUpdateTime(new Date());
        classPackage.setUpdator(WebUtils.getCurrentUserId(request));
        classPackage.setCreateTime(new Date());
        classPackage.setCreator(WebUtils.getCurrentUserId(request));
        this.classPackageServiceImpl.insert(classPackage);
        // 添加商品
        Commodity commodity = new Commodity();
        commodity.setCerateTime(new Date());
        commodity.setCompanyId(WebUtils.getCurrentCompanyId());
        commodity.setType("COMMODITY_PACKAGE");
        commodity.setUpdator(WebUtils.getCurrentUserId(request));
        commodity.setSchoolId(WebUtils.getCurrentSchoolId());
        commodity.setBaseNum(classPackage.getBaseNum());
        commodity.setStatus(0 + "");
        commodity.setOriginalPrice(0.00);
        commodity.setRealPrice(classPackage.getRealPrice());
        commodity.setName(classPackage.getName());
        commodity.setUpdateTime(new Date());
        commodity.setCreator(WebUtils.getCurrentUserId(request));
        commodity.setBuyNum(0);
        this.commodityServiceImpl.insert(commodity);
        // 添加关联关系
        CommodityProductRealtion commodityProductRealtion = new CommodityProductRealtion();
        commodityProductRealtion.setProductId(classPackage.getId());
        commodityProductRealtion.setProductType(2 + "");
        commodityProductRealtion.setComId(commodity.getId());
        this.commodityProductRealtionServiceImpl.insert(commodityProductRealtion);
        return classPackage.getId();
    }

    /**
     *
     * Class Name: ClassPackageController.java
     *
     * @Description: 课程包课程管理
     * @author DELL.COM
     * @date 2016年3月23日 下午2:33:24
     * @modifier
     * @modify-date 2016年3月23日 下午2:33:24
     * @version 1.0
     * @param id
     * @param lable
     * @param model
     * @return
     */
    @RequestMapping(value = "/manageClassPackage/{id}/{lable}")
    public String forwardClassPackageCourseSet(@PathVariable Integer id, @PathVariable String lable, Model model) {
        ClassPackage classPackage = this.classPackageServiceImpl.findClassPackageById(id);
        model.addAttribute("classPackage", classPackage);
        model.addAttribute("lable", lable);
        return "coursePackage/packageManage";
    }

    /**
     *
     * Class Name: ClassPackageController.java
     *
     * @Description: 课程包详情页
     * @author zhang.zx
     * @date 2016年3月23日 下午2:27:50
     * @modifier
     * @modify-date 2016年3月23日 下午2:27:50
     * @version 1.0
     * @param id
     * @param lable
     * @param model
     * @return
     */
    @RequestMapping(value = "/setDetail/{id}/{lable}")
    public String forwardClassPackageDetail(@PathVariable Integer id, @PathVariable String lable, Model model) {
        String url = "http://" + this.propertiesUtil.getProjectImageUrl() + "/";
        ClassPackage classPackage = this.classPackageServiceImpl.findClassPackageById(id);
        if (null != classPackage && null != classPackage.getCover() && !classPackage.getCover().contains("http://")) {
            model.addAttribute("realPath", classPackage.getCover());
            classPackage.setCover(url + classPackage.getCover());
        }
        // 查询当前公司的学科(上传图片时使用)
        SysConfigItem search = new SysConfigItem();
        search.setCompanyId(WebUtils.getCurrentCompanyId());
        search.setSchoolId(WebUtils.getCurrentSchoolId());
        search.setItemType(SysConfigConstant.ITEMTYPE_FIRST);
        List<SysConfigItem> firstItems = this.sysConfigItemServiceImpl.findItem(search);
        if (null != firstItems && firstItems.size() > 0) {
            model.addAttribute("itemOneId", firstItems.get(0).getId());
        }
        model.addAttribute("classPackage", classPackage);
        model.addAttribute("lable", lable);
        return "coursePackage/packageDetail";
    }

    /**
     *
     * Class Name: ClassPackageController.java
     *
     * @Description: 查询课程详情
     * @author zhang.zx
     * @date 2016年3月23日 下午2:27:50
     * @modifier
     * @modify-date 2016年3月23日 下午2:27:50
     * @version 1.0
     * @param id
     * @param lable
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryStageDetail/{id}")
    public ClassPackageStage queryStageDetail(@PathVariable Integer id) {
        return this.classPackageStageServiceImpl.findClassPackageStageById(id);
    }

    /**
     *
     * Class Name: ClassPackageController.java
     *
     * @Description: 查询课程详情
     * @author zhang.zx
     * @date 2016年3月23日 下午2:27:50
     * @modifier
     * @modify-date 2016年3月23日 下午2:27:50
     * @version 1.0
     * @param id
     * @param lable
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryPackageDetail/{id}")
    public ClassPackage queryPackageDetail(@PathVariable Integer id) {
        return this.classPackageServiceImpl.findClassPackageById(id);
    }

    /**
     *
     * Class Name: ClassPackageController.java
     *
     * @Description: 更新课程包信息
     * @author zhang.zx
     * @date 2016年3月22日 下午1:37:41
     * @modifier
     * @modify-date 2016年3月22日 下午1:37:41
     * @version 1.0
     * @param classPackage
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateClassPackageInfo", method = RequestMethod.POST)
    public Integer update(ClassPackage classPackage, HttpServletRequest request) {
        // 协议id有变化时更新协议记录表
        ClassPackage oldCp = this.classPackageServiceImpl.findClassPackageById(classPackage.getId());
        Integer oldProtocolId = oldCp.getProtocolId();
        Integer protocolId = null;

        // 课程绑定协议插入协议绑定历史表
        if (classPackage.getProtocolId() != null && classPackage.getProtocolId() != 0) {
            if (oldProtocolId != null && oldProtocolId != 0 && oldProtocolId != classPackage.getProtocolId()) {
                protocolId = oldProtocolId;
            }
        } else {
            if (oldProtocolId != null && oldProtocolId != 0) {
                protocolId = oldProtocolId;
            }
        }
        if (protocolId != null) {
            CoursePotocolBindHistory cbh = new CoursePotocolBindHistory();
            cbh.setClassPackageId(classPackage.getId());
            cbh.setBindDate(new Date());
            cbh.setBinder(WebUtils.getCurrentUserId(request));
            cbh.setCompanyId(WebUtils.getCurrentCompanyId());
            cbh.setPotocolId(protocolId);
            this.coursePotocolBindHistoryServiceImpl.insert(cbh);
        }

        classPackage.setCompanyId(WebUtils.getCurrentCompanyId());
        classPackage.setSchoolId(WebUtils.getCurrentSchoolId());
        classPackage.setUpdateTime(new Date());
        classPackage.setUpdator(WebUtils.getCurrentUserId(request));
        if (null != classPackage.getPublishStatus() && !"".equals(classPackage.getPublishStatus())) {
            if ("CLASS_ON_SALE".equals(classPackage.getPublishStatus())) {
                classPackage.setPublishTime(new Date());
            }
        }
        this.classPackageServiceImpl.update(classPackage);

        CommodityProductRealtion comm1 = this.commodityProductRealtionServiceImpl.findByClassPackageId(classPackage.getId() + "");
        if (null != comm1) {
            Integer cId = comm1.getComId();
            Commodity commodity = this.commodityServiceImpl.findCommodityById(cId);
            if (null != classPackage.getPublishStatus() && !"".equals(classPackage.getPublishStatus())) {
                if ("CLASS_ON_SALE".equals(classPackage.getPublishStatus())) {
                    commodity.setStatus(1 + "");
                } else if ("CLASS_STOP_SALE".equals(classPackage.getPublishStatus())) {
                    commodity.setStatus(0 + "");
                }
            }
            if (null != classPackage.getOriginalPrice() && !"".equals(classPackage.getOriginalPrice())) {
                commodity.setOriginalPrice(Double.parseDouble(classPackage.getOriginalPrice() + ""));
            }
            if (null != classPackage.getRealPrice() && !"".equals(classPackage.getRealPrice())) {
                commodity.setRealPrice(Double.parseDouble(classPackage.getRealPrice() + ""));
            }
            commodity.setUpdateTime(new Date());
            commodity.setCoverUrl(classPackage.getCover());
            commodity.setOverview(classPackage.getDescription());
            commodity.setUpdator(WebUtils.getCurrentUserId(request));
            this.commodityServiceImpl.update(commodity);
        }
        return classPackage.getId();
    }

    /**
     *
     * Class Name: ClassPackageController.java
     *
     * @Description: 删除课程包
     * @author zhang.zx
     * @date 2016年3月23日 上午10:51:34
     * @modifier
     * @modify-date 2016年3月23日 上午10:51:34
     * @version 1.0
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/delClassPackage/{id}")
    public String del(Model model, @PathVariable Integer id, HttpServletRequest request) {
        ClassPackage cp = this.classPackageServiceImpl.findClassPackageById(id);
        if (null == cp) {
            return "coursePackage/list";
        }
        ClassPackage classPackage = new ClassPackage();
        classPackage.setId(cp.getId());
        classPackage.setDelFlag(1);
        classPackage.setPublishStatus("CLASS_STOP_SALE");
        classPackage.setUpdateTime(new Date());
        classPackage.setUpdator(WebUtils.getCurrentUserId(request));
        this.classPackageServiceImpl.update(classPackage);
        return "coursePackage/list";
    }

    @ResponseBody
    @RequestMapping(value = "/{id:\\d+}")
    public ClassPackage getJson(Model model, @PathVariable Integer id) {
        return this.classPackageServiceImpl.findClassPackageById(id);
    }

    /**
     *
     * Class Name: ClassPackageController.java
     *
     * @Description:查询课程包名称是否存在
     * @author zhang.zx
     * @date 2016年3月22日 下午1:54:37
     * @modifier
     * @modify-date 2016年3月22日 下午1:54:37
     * @version 1.0
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/checkedClassPackageName", method = RequestMethod.POST)
    public boolean checkedClassPackageName(String name) {
        ClassPackage search = new ClassPackage();
        search.setCompanyId(WebUtils.getCurrentCompanyId());
        search.setSchoolId(WebUtils.getCurrentSchoolId());
        search.setName(name);
        List<ClassPackage> arr = this.classPackageServiceImpl.queryClassPackageByWhere(search);
        if (arr != null && arr.size() > 0) {
            return false;
        }
        return true;
    }

    @ResponseBody
    @RequestMapping(value = "/checkUpdateClassPackageName", method = RequestMethod.POST)
    public boolean checkUpdateClassPackageName(String name, Integer id) {
        ClassPackage pac = this.classPackageServiceImpl.findClassPackageById(id);
        if (name.equals(pac.getName())) {
            return true;
        } else {
            ClassPackage search = new ClassPackage();
            search.setCompanyId(WebUtils.getCurrentCompanyId());
            search.setSchoolId(WebUtils.getCurrentSchoolId());
            search.setName(name);
            List<ClassPackage> arr = this.classPackageServiceImpl.queryClassPackageByWhere(search);
            if (arr != null && arr.size() > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * Class Name: ClassPackageController.java
     *
     * @Description: 修改课程包状态
     * @author zhang.zx
     * @date 2016年3月23日 上午11:23:05
     * @modifier
     * @modify-date 2016年3月23日 上午11:23:05
     * @version 1.0
     * @param cp
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/changClassPackageCollect", method = RequestMethod.POST)
    public ClassPackage changCollectClassTypeStatus(ClassPackage cp, HttpServletRequest request) {
        cp.setUpdateTime(new Date());
        cp.setUpdator(WebUtils.getCurrentUserId(request));
        if (null != cp.getPublishStatus() && !"".equals(cp.getPublishStatus())) {
            if ("CLASS_ON_SALE".equals(cp.getPublishStatus())) {
                cp.setPublishTime(new Date());
            }
        }
        this.classPackageServiceImpl.update(cp);
        // 修改课程包关联商品
        CommodityProductRealtion comm1 = this.commodityProductRealtionServiceImpl.findByClassPackageId(cp.getId() + "");
        if (null != comm1) {
            Integer cId = comm1.getComId();
            Commodity commodity = this.commodityServiceImpl.findCommodityById(cId);
            if (null != cp.getPublishStatus() && !"".equals(cp.getPublishStatus())) {
                if ("CLASS_ON_SALE".equals(cp.getPublishStatus())) {
                    commodity.setStatus(1 + "");
                } else if ("CLASS_STOP_SALE".equals(cp.getPublishStatus())) {
                    commodity.setStatus(0 + "");
                }
            }
            commodity.setUpdateTime(new Date());
            commodity.setUpdator(WebUtils.getCurrentUserId(request));
            this.commodityServiceImpl.update(commodity);
        }
        return this.classPackageServiceImpl.findClassPackageById(cp.getId());
    }

    // ---------------课程包管理----------------
    /**
     *
     * Class Name: ClassPackageController.java
     *
     * @Description: 查询课程包下的课程（阶段和单独课程）
     *               如果阶段id为空，则查询课程包下除阶段外的课程，不过阶段id不为空，则查询的是当前阶段下的课程
     * @author zhang.zx
     * @date 2016年3月23日 下午4:33:05
     * @modifier
     * @modify-date 2016年3月23日 下午4:33:05
     * @version 1.0
     * @param search
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/searchCourse")
    public List<ClassTypeVo> queryClassPackageCourses(ClassPackageConditionVo search) {
        String url = "http://" + this.propertiesUtil.getProjectImageUrl() + "/";
        search.setCompanyId(WebUtils.getCurrentCompanyId());
        search.setSchoolId(WebUtils.getCurrentSchoolId());
        List<ClassTypeVo> data = this.classTypeServiceImpl.queryClassTypesByClassPackage(search);
        if (null != data && data.size() > 0) {
            for (ClassTypeVo ct : data) {
                if (null != ct) {
                    if (null != ct.getCover()) {
                        ct.setCover(url + ct.getCover());
                    }
                    Integer num = 0;
                    // 根据班型查询模块
                    List<ClassModule> listModule = this.classModuleServiceImpl.findModulesByClassTypeId(ct.getId());
                    if (null != listModule && listModule.size() > 0) {
                        for (ClassModule moudules : listModule) {
                            if (null != moudules && null != moudules.getTotalClassHour()) {
                                num += moudules.getTotalClassHour();
                            }
                        }
                    }
                    ct.setTotalClass(num);
                }
            }
        }
        return data;
    }

    /**
     *
     * Class Name: ClassPackageController.java
     *
     * @Description: 返回学员列表页
     * @author chopin.sun
     * @date 2016年3月23日 下午4:33:05
     * @modifier
     * @modify-date 2016年3月23日 下午4:33:05
     * @version 1.0
     * @param search
     * @return
     */
    @RequestMapping(value = "/studentList/{id}")
    public String studentList(Model model, @PathVariable Integer id) {
        ClassPackage search = this.classPackageServiceImpl.findClassPackageById(id);
        model.addAttribute("classPackage", search);
        // 查看该机构是否有删除学员的功能
        CompanyFunctionSet search1 = new CompanyFunctionSet();
        search1.setCompanyId(WebUtils.getCurrentCompanyId());
        search1.setFunctionCode("COMPANY_REMOVE_PAYMASTER");
        CompanyFunctionSet isDelete = this.companyFunctionSetServiceImpl.findCompanyUseCourse(search1);
        if (null != isDelete && "1".equals(isDelete.getStatus())) {
            model.addAttribute("isDelete", isDelete.getStatus());
        } else {
            model.addAttribute("isDelete", 0);
        }

        search1.setFunctionCode("CLASSPACKAGE_POTOCOL_SET");
        search1 = this.companyFunctionSetServiceImpl.findCompanyUseCourse(search1);
        if (search1 != null && "1".equals(search1.getStatus())) {
            model.addAttribute("courseProtocolConfig", true);
        } else {
            model.addAttribute("courseProtocolConfig", false);
        }
        // 查询公司注册设置
        CompanyRegisterConfig crc = new CompanyRegisterConfig();
        crc.setCompanyId(WebUtils.getCurrentCompanyId());
        crc = this.companyRegisterConfigServiceImpl.queryByCompanyId(crc);
        if (null != crc && crc.getCloseFlag() == 1) {
            crc.setMobileFlag(1);
        }
        model.addAttribute("registConfig", crc);
        return "coursePackage/studentManage";
    }

    /**
     *
     * Class Name: ClassPackageController.java
     *
     * @Description: 查询课程包下的学员列表
     * @author chopin.sun
     * @date 2016年3月23日 下午4:33:05
     * @modifier
     * @modify-date 2016年3月23日 下午4:33:05
     * @version 1.0
     * @param search
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryStudentsList")
    public PageFinder<StudentListVo> queryStudentsList(Model model, StudentListVo search) {
        CompanyFunctionSet search1 = new CompanyFunctionSet();
        search1.setCompanyId(WebUtils.getCurrentCompanyId());
        search1.setFunctionCode("CLASSPACKAGE_POTOCOL_SET");
        search1 = this.companyFunctionSetServiceImpl.findCompanyUseCourse(search1);
        if (search1 != null && "1".equals(search1.getStatus())) {
            search.setProtocolConfig(1);
        }
        search.setCompanyId(WebUtils.getCurrentCompanyId());
        // search.setSchoolId(WebUtils.getCurrentSchoolId());
        List<StudentListVo> data = this.studentServiceImpl.findStudentListBuyPackage(search);
        Integer rowCount = this.studentServiceImpl.findCountByCommodityId(search);
        PageFinder<StudentListVo> pf = new PageFinder<StudentListVo>(search.getPage(), search.getPageSize(), rowCount, data);
        return pf;
    }

    /**
     *
     * Class Name: ClassPackageController.java
     *
     * @Description: 点击创建课程
     * @author zhang.zx
     * @date 2016年3月25日 下午4:54:41
     * @modifier
     * @modify-date 2016年3月25日 下午4:54:41
     * @version 1.0
     * @param model
     * @return
     */
    @RequestMapping(value = "/addClassType")
    public String forwdAddCourse(Model model) {
        CompanyFunctionSet search = new CompanyFunctionSet();
        search.setFunctionCode("COMPANY_FUNCTION_COURSE");
        search.setCompanyId(WebUtils.getCurrentCompanyId());
        CompanyFunctionSet cfs = this.companyFunctionSetServiceImpl.findCompanyUseCourse(search);
        List<SysConfigItem> firstItems = this.sysConfigItemServiceImpl.findSysConfigItemByPid(SysConfigConstant.ITEMTYPE_FIRST, null,
                WebUtils.getCurrentCompanyId(), WebUtils.getCurrentSchoolId());
        model.addAttribute("firstItems", firstItems);
        if (cfs != null && "1".equals(cfs.getStatus())) {
            return "classType/classIndex";
        }
        return "simpleClasses/classIndex";
    }

    /**
     *
     * Class Name: ClassPackageController.java
     *
     * @Description:查询课程下总课时
     * @author zhang.zx
     * @date 2016年3月25日 下午5:34:15
     * @modifier
     * @modify-date 2016年3月25日 下午5:34:15
     * @version 1.0
     * @param classTypeId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getLessonNum")
    public Integer getTotalCourseLessonNum(Integer classTypeId) {
        Integer num = 0;
        if (null == classTypeId) {
            return num;
        }
        // 根据班型查询模块
        List<ClassModule> listModule = this.classModuleServiceImpl.findModulesByClassTypeId(classTypeId);
        if (null != listModule && listModule.size() > 0) {
            for (ClassModule moudules : listModule) {
                if (null != moudules && null != moudules.getTotalClassHour()) {
                    num += moudules.getTotalClassHour();
                }
            }
        }
        return num;
    }

    /**
     * 查询课程包下课程数量
     */
    @ResponseBody
    @RequestMapping(value = "/getCourseNum")
    public Integer getClassPackageCourse(ClassPackageConditionVo search) {
        search.setCompanyId(WebUtils.getCurrentCompanyId());
        search.setSchoolId(WebUtils.getCurrentSchoolId());
        List<ClassTypeVo> data = this.classTypeServiceImpl.queryIsExistByClassPackage(search);
        if (null != data) {
            // for(ClassTypeVo ct:data){
            // if(null!=ct && null!=ct.getLiveFlag() && ct.getLiveFlag()==1){
            // List<ClassmoduleNoOnsaleVo>
            // arr=classModuleServiceImpl.queryModuleNoByClasstypeId(ct.getId());
            // if(null==arr || arr.size()<=0){
            // return -1;
            // }
            // }
            // }
            return data.size();
        }
        return 0;
    }

    @ResponseBody
    @RequestMapping(value = "/customClassPackage", method = RequestMethod.POST)
    public Map queryCustomChapterName() {
        CompanyFunctionSet search = new CompanyFunctionSet();
        search.setCompanyId(WebUtils.getCurrentCompanyId());
        search.setFunctionCode("CLASS_PACKAGE_SET");
        search.setStatus("1");
        Map<String, String> map = new HashMap<String, String>();
        List<CompanyFunctionSet> sets = this.companyFunctionSetServiceImpl.findCompanyFunctions(search);
        if (sets != null && sets.size() > 0) {
            map.put("classPackageName", sets.get(0).getColumn1());
            map.put("classStageName", sets.get(0).getColumn2());
        }
        return map;
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
    public String showSignUp(Model model, Student student, Integer commodityId) {
        student.setCompanyId(WebUtils.getCurrentCompanyId());
        Student st = this.studentServiceImpl.findByMobile(student);
        if (st != null && st.getId() != null) {
            model.addAttribute("student", st);
        } else {
            model.addAttribute("student", new Student());
        }
        // 查询公司注册设置
        CompanyRegisterConfig crc = new CompanyRegisterConfig();
        crc.setCompanyId(WebUtils.getCurrentCompanyId());
        crc = this.companyRegisterConfigServiceImpl.queryByCompanyId(crc);
        if (null != crc && crc.getCloseFlag() == 1) {
            crc.setMobileFlag(1);
        }
        model.addAttribute("registConfig", crc);
        model.addAttribute("packageId", commodityId);

        CompanyFunctionSet CompanyFunctionSet = new CompanyFunctionSet();
        CompanyFunctionSet.setCompanyId(WebUtils.getCurrentCompanyId());
        CompanyFunctionSet.setFunctionCode("STUDENT_GROUP");
        List<CompanyFunctionSet> CompanyFunctionSetList = companyFunctionSetServiceImpl.findCompanyFunctionSetByPage(CompanyFunctionSet);
        CompanyFunctionSet set = CompanyFunctionSetList != null && CompanyFunctionSetList.size() > 0 ? CompanyFunctionSetList.get(0) : null;

        if (null != set) {
            model.addAttribute("sgOpen", set.getStatus());
        }
        return "coursePackage/student-signup";
    }

    @ResponseBody
    @RequestMapping(value = "/queryPackage/{code}")
    public List<ClassPackage> queryPackage(@PathVariable String code) {
        ClassPackage search = new ClassPackage();
        search.setCompanyId(WebUtils.getCurrentCompanyId());
        search.setSchoolId(WebUtils.getCurrentSchoolId());
        search.setCategoryCode(code);
        return this.classPackageServiceImpl.queryClassPackageByWhere(search);
    }

    // ---------------课程包订单----------------------
    /**
     *
     * Class Name: ClassPackageController.java
     *
     * @Description: 进入订单管理页
     * @author zhang.zx
     * @date 2016年3月29日 上午11:10:04
     * @modifier
     * @modify-date 2016年3月29日 上午11:10:04
     * @version 1.0
     * @param model
     * @param id
     * @param lable
     * @return
     */
    @RequestMapping(value = "/orderManage/{id}/{lable}")
    public String orderManage(Model model, @PathVariable Integer id, @PathVariable String lable) {
        ClassPackage cp = this.classPackageServiceImpl.findClassPackageById(id);
        if (null == cp) {
            return "coursePackage/list";
        }
        model.addAttribute("classPackage", cp);
        model.addAttribute("lable", lable);
        return "coursePackage/orderManage";
    }

    /**
     *
     * Class Name: ClassPackageController.java
     *
     * @Description: 查询课程包订单
     * @author zhang.zx
     * @date 2016年3月29日 上午11:12:31
     * @modifier
     * @modify-date 2016年3月29日 上午11:12:31
     * @version 1.0
     * @param cp
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/searchOrderList")
    public PageFinder<OrderManage> searchOrders(OrderManage search, Model model) {
        Integer companyId = WebUtils.getCurrentCompanyId();
        if ("sevnday".equals(search.getTimeMark())) {
            search.setTimeLen(7);
        }
        search.setCompanyId(companyId);
        PageFinder<OrderManage> pageFinder = this.classPackageServiceImpl.findClassPackageOrderList(search);
        return pageFinder;
    }

    /**
     *
     * Class Name: ClassPackageController.java
     *
     * @Description: 检查课程包订单
     * @author zhang.zx
     * @date 2016年3月29日 上午11:12:31
     * @modifier
     * @modify-date 2016年3月29日 上午11:12:31
     * @version 1.0
     * @param cp
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/checkPayMaster")
    public Boolean checkPayMaster(StudentPayMaster payMaster) {
        List<StudentPayMaster> list = this.studentPayMasterServiceImpl.findByClassPackageAndStu(payMaster);
        return list.size() > 0;
    }

    /**
     *
     * Class Name: ClassPackageController.java
     *
     * @Description: 检查课程包订单
     * @author zhang.zx
     * @date 2016年3月29日 上午11:12:31
     * @modifier
     * @modify-date 2016年3月29日 上午11:12:31
     * @version 1.0
     * @param cp
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/savePayMaster")
    public StudentPayMaster savePayMaster(HttpServletRequest request, StudentPayMaster payMaster) {
        payMaster.setSchoolId(WebUtils.getCurrentSchoolId());
        payMaster.setCompanyId(WebUtils.getCurrentCompanyId());
        // 查询公司注册设置
        CompanyRegisterConfig crc = new CompanyRegisterConfig();
        crc.setCompanyId(WebUtils.getCurrentCompanyId());
        crc = this.companyRegisterConfigServiceImpl.queryByCompanyId(crc);
        Student student = JSONObject.parseObject(request.getParameter("student"), Student.class);
        // 报考材料
        List<StudentAgentMaterial> materials = JSONObject.parseArray(request.getParameter("materials"), com.yuxin.wx.model.student.StudentAgentMaterial.class);
        // 分期
        List<StudentFeeStage> stages = JSONObject.parseArray(request.getParameter("stage"), com.yuxin.wx.model.student.StudentFeeStage.class);

        Users user = WebUtils.getCurrentUser(request);
        payMaster.setStuId(student.getId());
        // 建立订单
        Boolean exists = this.studentPayMasterServiceImpl.savePackagePayMaster(payMaster, user, materials, stages);
        if (!exists) {
            payMaster.setMessage("课程包里有课程还没有班号，请先建立班号再报名。");
            return payMaster;
        }
        List<StudentPayMaster> list = this.studentPayMasterServiceImpl.findByClassPackageAndStu(payMaster);
        if (list != null && list.size() <= 1) {
            // 更新商品表购买人数
            CommodityProductRealtion cpr = this.commodityProductRealtionServiceImpl.findByClassPackageId("" + payMaster.getCommodityId());
            this.commodityServiceImpl.updateBuyNumById(cpr.getComId());
        }
        // 更新前台用户表
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
        if (null != crc && null != crc.getUsernameFlag() && crc.getUsernameFlag() == 1 && crc.getMobileFlag() != 1) {
            u = this.usersFrontServiceImpl.findUsersFrontById(student.getUserId());
        } else {
            u = this.usersFrontServiceImpl.findUsersFrontByMobile(userfront);
        }
        // UsersFront u=usersFrontServiceImpl.findUsersFrontByMobile(userfront);
        // 检查报名完成是否要发短信
        CompanyFunctionSet cfs = new CompanyFunctionSet();
        cfs.setStatus("1");
        cfs.setCompanyId(WebUtils.getCurrentCompanyId());
        cfs.setFunctionCode("COMPANY_FUNCTION_APPLY");
        // 获取用户短信配置
        List<CompanyFunctionSet> set = this.companyFunctionSetServiceImpl.findCompanyFunctionSet(cfs);
        Boolean needSendSms = false;
        String sms = "";
        for (CompanyFunctionSet s : set) {
            if ("COMPANY_FUNCTION_APPLY".equals(s.getFunctionCode())) {
                if ("1".equals(s.getStatus())) {
                    needSendSms = true;
                    if (StringUtils.isNotBlank(s.getContent())) {
                        sms = s.getContent().replace("【coursename】", payMaster.getClassTypeName()) + "【在线网校】";
                    }
                }
            }
        }
        this.log.info("****************报名已完成*****************");
        this.log.info(sms);
        if (needSendSms) {
            try {
                if (null != student && null != student.getMobile() && !"".equals(student.getMobile())) {// 2016/7/7
                                                                                                        // 无手机号则不发送短信
                    // 发送用户配置的短信内容
                    String send = SmsClientSend.sendSmsTwo(request, student.getMobile(), sms, user.getId(), " stu-notice");
                    int returnstatusSIndex = send.indexOf("<returnstatus>");
                    int returnstatusEIndex = send.indexOf("</returnstatus>");
                    String sendMessageStatus = send.substring((returnstatusSIndex + 14), returnstatusEIndex);
                    // 记录短信发送历史
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
                this.log.error("短信发送失败", e);
                System.out.println(e);
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

        return payMaster;
    }

    /**
     *
     * Class Name: ClassTypeStudentsController.java
     *
     * @Description: 移除此课程包
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
    public String deleteStuPaymaster(Integer id, Integer commodityId, Model model) {
        this.log_student.info("===");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("stuId", id);
        map.put("commodityId", commodityId);
        map.put("commodityType", "COMMODITY_PACKAGE");
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
                        Commodity com = this.commodityServiceImpl.findCommodityById(paymaster.getCommodityId());
                        if (null != com && null != com.getBuyNum() && com.getBuyNum() > 0) {
                            com.setBuyNum(com.getBuyNum() - 1);
                            this.commodityServiceImpl.update(com);
                        }
                    }
                }
            }

            // 删除观看记录
            for (StudentPayMaster paymaster : arr) {
                // 课程包包含的课程
                List<Integer> classtypelist = this.classPackageServiceImpl.findClassPackageAllClasses(paymaster.getCommodityId());
                for (Integer classtypeId : classtypelist) {
                    List<Integer> classModuleList = this.classTypeModuleRelationServiceImpl.findClassModuleIdsByClassTypeId(classtypeId);
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

                    // 删除作业记录
                    Map<String, Object> homeworkMap = new HashMap<String, Object>();
                    homeworkMap.put("companyId", WebUtils.getCurrentCompanyId());
                    homeworkMap.put("stuId", id);
                    homeworkMap.put("courseId", classtypeId);
                    this.homeworkServiceImpl.deleteHomeworkHistory(homeworkMap);
                }
            }

            this.log_student.info(">>> [移出课程] " + "状态：success" + ", 信息：" + "公司ID = " + WebUtils.getCurrentCompanyId() + ", 操作人ID = "
                    + WebUtils.getCurrentUser().getId() + ", 学生ID = " + id + ", 课程ID = " + commodityId);
            return "success";
        }
        this.log_student.error(">>> [移出课程] " + "状态：error" + ", 信息：" + "公司ID = " + WebUtils.getCurrentCompanyId() + ", 操作人ID = "
                + WebUtils.getCurrentUser().getId() + ", 学生ID = " + id + ", 课程ID = " + commodityId);
        return "error";
    }

    /**
     *
     * @Description: 跳转到导入学员列表页
     * @author zhang.zx
     */
    @RequestMapping(value = "/importPage/{id}")
    public String importStusPage(Model model, @PathVariable Integer id) {
        model.addAttribute("packageId", id);

        CompanyFunctionSet CompanyFunctionSet = new CompanyFunctionSet();
        CompanyFunctionSet.setCompanyId(WebUtils.getCurrentCompanyId());
        CompanyFunctionSet.setFunctionCode("STUDENT_GROUP");
        List<CompanyFunctionSet> CompanyFunctionSetList = companyFunctionSetServiceImpl.findCompanyFunctionSetByPage(CompanyFunctionSet);
        CompanyFunctionSet set = CompanyFunctionSetList != null && CompanyFunctionSetList.size() > 0 ? CompanyFunctionSetList.get(0) : null;
        if (null != set) {
            model.addAttribute("sgOpen", set.getStatus());
        }
        return "coursePackage/importStudents";
    }

    // 导入成功后查询导入数据页面
    @RequestMapping(value = "/queryData")
    public String importStudentData(Model model, String stuMobiles, Integer packageId) {
        CompanyFunctionSet search = new CompanyFunctionSet();
        search.setFunctionCode("COMPANY_FUNCTION_COURSE");
        search.setCompanyId(WebUtils.getCurrentCompanyId());
        CompanyFunctionSet cfs = this.companyFunctionSetServiceImpl.findCompanyUseCourse(search);
        if (cfs != null && "1".equals(cfs.getStatus())) {
            model.addAttribute("isFull", 1);
        } else {
            model.addAttribute("isFull", 0);
        }
        /*
         * String newstr = ""; if (null != stuMobiles && !"".equals(stuMobiles))
         * { newstr = stuMobiles.substring(0, stuMobiles.length() - 1); }
         */
        model.addAttribute("stuMobiles", stuMobiles);
        model.addAttribute("packageId", packageId);
        // 查询公司注册设置
        CompanyRegisterConfig crc = new CompanyRegisterConfig();
        crc.setCompanyId(WebUtils.getCurrentCompanyId());
        crc = this.companyRegisterConfigServiceImpl.queryByCompanyId(crc);
        if (null != crc && crc.getCloseFlag() == 1) {
            crc.setMobileFlag(1);
        }
        model.addAttribute("registConfig", crc);
        return "coursePackage/importStudentData";
    }

    /**
     *
     * Class Name: StudentImportController.java
     *
     * @Description: 导入学员信息
     * @author zhang.zx
     * @date 2016年1月12日 下午3:22:42
     * @modifier
     * @modify-date 2016年1月12日 下午3:22:42
     * @version 1.0
     * @return
     */
    @RequestMapping(value = "/insertStudents")
    public String insertStudents(String list, Integer packageId, String lable, HttpServletRequest request, Model model) {
        // 根据班型id查询详情
        ClasspackageVo classpackage = this.classPackageServiceImpl.findClassPackageVoById(packageId);
        model.addAttribute("classpackage", classpackage != null ? classpackage : new ClasspackageVo());
        model.addAttribute("lable", lable);
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
            // student.setSchoolId(WebUtils.getCurrentSchoolId());
            // Student st=studentServiceImpl.findByMobile(student);
            Student st = this.studentServiceImpl.queryStuInfoByWhere(student);
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
        // CompanyFunctionSet search=new CompanyFunctionSet();
        // search.setFunctionCode("COMPANY_FUNCTION_COURSE");
        // search.setCompanyId(WebUtils.getCurrentCompanyId());
        // CompanyFunctionSet
        // cfs=companyFunctionSetServiceImpl.findCompanyUseCourse(search);
        // if(cfs!=null && "1".equals(cfs.getStatus())){
        // return "coursePackage/student-signupMany-full";
        // }
        return "coursePackage/student-signupMany";
    }

    /**
     *
     * @fileName : StudentPayMasterController.java
     * @date : 2015年10月20日 下午2:25:22
     * @author : 杨延博
     * @description :
     */
    @ResponseBody
    @RequestMapping(value = "/savePayMasterMany")
    public StudentPayMaster savePayMasterMany(HttpServletRequest request, StudentPayMaster payMaster) {
        payMaster.setSchoolId(WebUtils.getCurrentSchoolId());
        // 查询公司注册设置
        CompanyRegisterConfig crc = new CompanyRegisterConfig();
        crc.setCompanyId(WebUtils.getCurrentCompanyId());
        crc = this.companyRegisterConfigServiceImpl.queryByCompanyId(crc);
        List<Student> studentlist = JSONObject.parseArray(request.getParameter("student"), Student.class);
        for (Student student : studentlist) {
            // 报考材料
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
            this.studentPayMasterServiceImpl.savePackagePayMaster(payMaster, user, materials, stages);
            List<StudentPayMaster> list = this.studentPayMasterServiceImpl.findByClassPackageAndStu(payMaster);
            if (list != null && list.size() <= 1) {
                // 更新商品表购买人数
                CommodityProductRealtion cpr = this.commodityProductRealtionServiceImpl.findByClassPackageId("" + payMaster.getCommodityId());
                this.commodityServiceImpl.updateBuyNumById(cpr.getComId());
            }
            // 更新前台用户表
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
            if (null != crc && null != crc.getUsernameFlag() && crc.getUsernameFlag() == 1 && crc.getMobileFlag() != 1) {
                u = this.usersFrontServiceImpl.findUsersFrontById(student.getUserId());
            } else {
                u = this.usersFrontServiceImpl.findUsersFrontByMobile(userfront);
            }
            // UsersFront
            // u=usersFrontServiceImpl.findUsersFrontByMobile(userfront);
            // 检查报名完成是否要发短信
            CompanyFunctionSet cfs = new CompanyFunctionSet();
            cfs.setStatus("1");
            cfs.setCompanyId(WebUtils.getCurrentCompanyId());
            cfs.setFunctionCode("COMPANY_FUNCTION_APPLY");
            // 获取用户短信配置
            List<CompanyFunctionSet> set = this.companyFunctionSetServiceImpl.findCompanyFunctionSet(cfs);
            Boolean needSendSms = false;
            String sms = "";
            for (CompanyFunctionSet s : set) {
                if ("COMPANY_FUNCTION_APPLY".equals(s.getFunctionCode())) {
                    if ("1".equals(s.getStatus())) {
                        needSendSms = true;
                        if (StringUtils.isNotBlank(s.getContent())) {
                            sms = s.getContent().replace("【coursename】", payMaster.getClassTypeName()) + "【在线网校】";
                        }
                    }
                }
            }
            this.log.info("****************报名已完成*****************");
            this.log.info(sms);
            if (needSendSms) {
                try {
                    if (null != student && null != student.getMobile() && !"".equals(student.getMobile())) {// 2016/7/7
                                                                                                            // 无手机号则不发送短信
                        // 发送用户配置的短信内容
                        String send = SmsClientSend.sendSmsTwo(request, student.getMobile(), sms, user.getId(), "stu-notice");
                        int returnstatusSIndex = send.indexOf("<returnstatus>");
                        int returnstatusEIndex = send.indexOf("</returnstatus>");
                        String sendMessageStatus = send.substring((returnstatusSIndex + 14), returnstatusEIndex);
                        // 记录短信发送历史
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
                    this.log.error("短信发送失败", e);
                    System.out.println(e);
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
        return payMaster;
    }

    @RequestMapping(value = "/exportExcle", method = RequestMethod.POST)
    public ModelAndView exportStudentsExcle(Model model, StudentListVo search) {
        List<StudentListVo> al = new ArrayList<StudentListVo>();
        if (EntityUtil.isNotBlank(search)) {
            search.setPageSize(1000);
            // search.setSchoolId(WebUtils.getCurrentSchoolId());
            search.setCompanyId(WebUtils.getCurrentCompanyId());
            al = this.studentServiceImpl.exportClassPackageByCommodityId(search);
        }
        ViewFiles excel = new ViewFiles();
        Map<String, Object> map = new HashMap<String, Object>();
        ExcelSheetEntity entity = ExcelSheetEntity
                .newInstance("手机号:mobile,姓名:name,身份证号:identityId,邮箱:email,QQ号:qq,紧急联系人:emergencyContact,紧急联系人电话:emergencyPhone", al);

        map.put("entity", entity);
        map.put("fileName", "学员列表.xls");
        return new ModelAndView(excel, map);
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

    /**
     * 后台接收Date转换
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @ResponseBody
    @RequestMapping("/getJson")
    public Object getJson(ClasspackageVo search, HttpServletRequest request, Model model) {
        Map<String, Object> result = new HashMap<String, Object>();

        search.setCompanyId(WebUtils.getCurrentCompanyId());
        search.setSchoolId(WebUtils.getCurrentSchoolId());
        String commodityPicUrl = "http://" + this.propertiesUtil.getProjectImageUrl() + "/";
        result.put("commodityPicUrl", commodityPicUrl);
        result.put("pageFind", this.classPackageServiceImpl.findByCondition(search));
        return result;
    }
}
