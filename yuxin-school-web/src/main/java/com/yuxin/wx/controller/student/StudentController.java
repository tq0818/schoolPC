package com.yuxin.wx.controller.student;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;

import sun.misc.BASE64Encoder;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.classes.IClassModuleNoService;
import com.yuxin.wx.api.classes.IClassModuleService;
import com.yuxin.wx.api.classes.IClassPackageCategoryService;
import com.yuxin.wx.api.classes.IClassTypeService;
import com.yuxin.wx.api.company.ICompanyFunctionSetService;
import com.yuxin.wx.api.company.ICompanyMemberServiceService;
import com.yuxin.wx.api.company.ICompanyRegisterConfigService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.api.student.IStudentFeeRefundService;
import com.yuxin.wx.api.student.IStudentFeeStageService;
import com.yuxin.wx.api.student.IStudentGroupService;
import com.yuxin.wx.api.student.IStudentPayMasterService;
import com.yuxin.wx.api.student.IStudentPaySlaveService;
import com.yuxin.wx.api.student.IStudentService;
import com.yuxin.wx.api.system.ILongitudinalTableColDefineService;
import com.yuxin.wx.api.system.ILongitudinalTableDataService;
import com.yuxin.wx.api.system.ISysConfigDictService;
import com.yuxin.wx.api.system.ISysConfigItemRelationService;
import com.yuxin.wx.api.system.ISysConfigItemService;
import com.yuxin.wx.api.system.ISysConfigSchoolService;
import com.yuxin.wx.api.system.ISysConfigTeacherService;
import com.yuxin.wx.api.user.IUsersFrontService;
import com.yuxin.wx.common.ExcelSheetEntity;
import com.yuxin.wx.common.JsonMsg;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.common.PageFinder2;
import com.yuxin.wx.common.SysLoader;
import com.yuxin.wx.common.ViewFiles;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.classes.ClassModuleNo;
import com.yuxin.wx.model.classes.ClassPackageCategory;
import com.yuxin.wx.model.classes.ClassType;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.CompanyFunctionSet;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.model.company.CompanyRegisterConfig;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.model.student.Student;
import com.yuxin.wx.model.student.StudentFeeRefund;
import com.yuxin.wx.model.student.StudentFeeStage;
import com.yuxin.wx.model.student.StudentGroup;
import com.yuxin.wx.model.student.StudentPayMaster;
import com.yuxin.wx.model.system.LongitudinalTableColDefine;
import com.yuxin.wx.model.system.LongitudinalTableData;
import com.yuxin.wx.model.system.SysConfigDict;
import com.yuxin.wx.model.system.SysConfigItem;
import com.yuxin.wx.model.system.SysConfigItemRelation;
import com.yuxin.wx.model.system.SysConfigSchool;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.model.user.UsersFront;
import com.yuxin.wx.utils.DateUtil;
import com.yuxin.wx.utils.EntityUtil;
import com.yuxin.wx.utils.ExcelUtil;
import com.yuxin.wx.utils.FileUploadUtil;
import com.yuxin.wx.utils.ImportExcl;
import com.yuxin.wx.utils.ParameterUtil;
import com.yuxin.wx.utils.PropertiesUtil;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.company.CompanyOrgMessageVo;
import com.yuxin.wx.vo.student.SelectStudentOrUsersfrontVo;
import com.yuxin.wx.vo.student.StuVo;
import com.yuxin.wx.vo.student.StudentListDataVo;
import com.yuxin.wx.vo.student.StudentListVo;
import com.yuxin.wx.vo.student.StudentPayMaster4ClassPackageVo;
import com.yuxin.wx.vo.student.StudentPaySlaveVo;
import com.yuxin.wx.vo.student.StudentVo;

/**
 * Controller of Student
 *
 * @author wang.zx
 * @date 2014-12-5
 */
@Controller
@RequestMapping("/student")
public class StudentController {

    private static Log log_student = LogFactory.getLog("student");

    @Autowired
    private ICompanyMemberServiceService companyMemberServiceServiceImpl;

    @Autowired
    private ICompanyServiceStaticService companyServiceStaticServiceImpl;

    @Autowired
    private ICompanyService companyServiceImpl;

    @Autowired
    private IStudentService studentServiceImpl;
    @Autowired
    private IClassTypeService classTypeServiceImpl;

    @Autowired
    private IStudentPayMasterService studentPayMasterServiceImpl;
    @Autowired
    private IClassModuleNoService classModuleNoServiceImpl;
    @Autowired
    private ISysConfigItemService sysConfigItemServiceImpl;
    @Autowired
    private ISysConfigSchoolService sysConfigSchoolServiceImpl;
    @Autowired
    private IClassModuleService classModuleServiceImpl;
    @Autowired
    private IStudentFeeStageService studentFeeStageServiceImpl;
    @Autowired
    private IStudentPaySlaveService studentPaySlaveServiceImpl;
    @Autowired
    private IStudentFeeRefundService StudentFeeRefundServiceImpl;
    @Autowired
    private ICompanyFunctionSetService companyFunctionSetServiceImpl;
    @Autowired
    private PropertiesUtil propertiesUtil;
    @Autowired
    private ISysConfigDictService sysConfigDictServiceImpl;
    @Autowired
    private IUsersFrontService usersFrontServiceImpl;
    @Autowired
    private ILongitudinalTableColDefineService longitudinalTableColDefineServiceImpl;
    @Autowired
    private ILongitudinalTableDataService longitudinalTableDataServiceImpl;
    @Autowired
    private ICompanyRegisterConfigService companyRegisterConfigServiceImpl;
    @Autowired
    private IStudentGroupService studentGroupServiceImpl;
    @Autowired
    private IClassPackageCategoryService classPackageCategoryServiceImpl;
    @Autowired
    private ISysConfigTeacherService sysConfigTeacherServiceImpl;
    @Autowired
    private ISysConfigItemRelationService sysConfigItemRelationServiceImpl;

    private static Logger log = Logger.getLogger(StudentController.class);
    
    public static final String AFFICHE_TYPE = "AFFICHE";
    
    // 跳转到学员列表页面
    @RequestMapping(value = "/studentList")
    public String forwardStudentList(Model model) {
        // 查询课程的多课程单元和多班号功能
        CompanyFunctionSet search = new CompanyFunctionSet();
        search.setFunctionCode("COMPANY_FUNCTION_COURSE");
        search.setCompanyId(WebUtils.getCurrentCompanyId());
        CompanyFunctionSet cfs = companyFunctionSetServiceImpl.findCompanyUseCourse(search);
        // 查看该机构是否有取消报名的功能
        search.setFunctionCode("COMPANY_REMOVE_PAYMASTER");
        CompanyFunctionSet isDelete = companyFunctionSetServiceImpl.findCompanyUseCourse(search);
        if (null != isDelete && "1".equals(isDelete.getStatus())) {
            model.addAttribute("isDelete", isDelete.getStatus());
        } else {
            model.addAttribute("isDelete", 0);
        }
        // 导出学员报名数据 公司ID=1851
        if (WebUtils.getCurrentCompanyId() == 1851) {
            model.addAttribute("showFlag", "show");
        }
        // 查询公司注册配置
        CompanyRegisterConfig crc = new CompanyRegisterConfig();
        crc.setCompanyId(WebUtils.getCurrentCompanyId());
        crc = companyRegisterConfigServiceImpl.queryByCompanyId(crc);
        if (null != crc && crc.getCloseFlag() == 1) {
            crc.setMobileFlag(1);
        }
        model.addAttribute("registConfig", crc);
        // 查看该机构学员地址信息配置功能
        search.setFunctionCode("STUDENT_ADDRESS_INFO");
        CompanyFunctionSet address = companyFunctionSetServiceImpl.findCompanyUseCourse(search);
        if (address != null && "1".equals(address.getStatus())) {
            model.addAttribute("address", 1);
        } else {
            model.addAttribute("address", 0);
        }
        // 学员分组
        CompanyFunctionSet companyFunctionSet = new CompanyFunctionSet();
        companyFunctionSet.setCompanyId(WebUtils.getCurrentCompanyId());
        companyFunctionSet.setFunctionCode("STUDENT_GROUP");
        List<CompanyFunctionSet> companyFunctionSetList = companyFunctionSetServiceImpl.findCompanyFunctionSetByPage(companyFunctionSet);
        if (companyFunctionSetList != null && companyFunctionSetList.size() > 0) {
            model.addAttribute("sgOpen", companyFunctionSetList.get(0).getStatus());
        }
        // 代理机构权限
        Subject subject = SecurityUtils.getSubject();
        if (subject.hasRole("机构管理员") || subject.hasRole("代理机构")) {
            model.addAttribute("proxyOrgRole", 1);
        } else {
            model.addAttribute("proxyOrgRole", 0);
        }
        CompanyFunctionSet userorg_roleopenflag = WebUtils.getFunctionSet("USERORG_ROLEOPENFLAG");
        model.addAttribute("userorg_roleopenflag", userorg_roleopenflag==null?0:userorg_roleopenflag.getStatus());


        //查询学校所在区域
        SysConfigDict areaDict = new SysConfigDict();
        areaDict.setDictCode("EDU_SCHOOL_AREA");
        List<SysConfigDict> areas = sysConfigDictServiceImpl.queryConfigDictListByDictCode(areaDict);
        model.addAttribute("areas", areas);

        areaDict.setDictCode("EDU_STEP");
        List<SysConfigDict> steps = sysConfigDictServiceImpl.queryConfigDictListByDictCode(areaDict);
        model.addAttribute("steps", steps);
        //年份列表
        List<Integer> years = new ArrayList<Integer>();
        int curYear = DateUtil.getCurYear();
        for(int year = 0;year<12;year++){
            years.add(curYear-year);
        }
        model.addAttribute( "years", years);
        // 跳转多班号或单班号
        if (cfs != null && "1".equals(cfs.getStatus())) {
            return "student/studentList-full";
        }
        return "student/studentList";
    }

    @RequestMapping
    public String index(Model model) {
        CompanyFunctionSet search = new CompanyFunctionSet();
        search.setFunctionCode("COMPANY_FUNCTION_COURSE");
        search.setCompanyId(WebUtils.getCurrentCompanyId());
        CompanyFunctionSet cfs = companyFunctionSetServiceImpl.findCompanyUseCourse(search);




        if (cfs != null && "1".equals(cfs.getStatus())) {
            return "/student/index-full";
        }
        return "/student/index";
    }
    @ResponseBody
    @RequestMapping(value = "/getSchoolList/{id}")
    public List<SysConfigDict> getSchoolList(HttpServletRequest request,@PathVariable Integer id) {
        SysConfigDict areaDict = new SysConfigDict();
        areaDict.setDictCode("EDU_SCHOOL");
        areaDict.setParentItemId(id);
        List<SysConfigDict> areas = sysConfigDictServiceImpl.queryConfigDictListByDictCode(areaDict);
        return areas;
    }

    @ResponseBody
    @RequestMapping(value = "/getSchoolList")
    public List<SysConfigDict> getSchoolList(HttpServletRequest request,String schoolType,String area) {

        Map<String,Object>  map  = new HashMap<>();
        map.put("schoolType",schoolType);
        map.put("area",area);
        List<SysConfigDict> areas = sysConfigDictServiceImpl.findSchoolBySchoolType(map);
        return areas;
    }

    @ResponseBody
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public PageFinder<Student> query(Model model, Student search) {
        if (search == null) {
            search = new Student();
        }
        search.setCompanyId(WebUtils.getCurrentCompanyId());
        return studentServiceImpl.findStudentByPage(search);
    }

    @ResponseBody
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.POST)
    public Student detail(Model model, @PathVariable Integer id) {
        Student student = new Student();
        if (id != null) {
            student = studentServiceImpl.findStudentById(id);
        }
        return student;
    }

    /**
     *
     * Class Name: StudentController.java
     *
     * @Description: 新增学员
     * @author Chopin
     * @date 2014年12月31日
     * @version 1.0
     * @param request
     * @param student
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(HttpServletRequest request, Student student) {
        Users user = WebUtils.getCurrentUser(request);
        student.setCreateTime(new Date());
        student.setCreator(user.getId());
        student.setDeleteFlag(0);
        student.setCompanyId(user.getCompanyId());
        studentServiceImpl.insert(student);
        return "{stuId:" + student.getId() + ",message:'success'}";
    }

    /**
     *
     * Class Name: StudentController.java
     *
     * @Description: 保存学员
     * @author Chopin
     * @date 2014年12月31日
     * @version 1.0
     * @param request
     * @param student
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveStudent", method = RequestMethod.POST)
    public Student saveStudent(HttpServletRequest request, Student student, ModelMap model) {
        if (null != student && null != student.getUsername()) {
            if (!ParameterUtil.isUserName(student.getUsername())) {
                return null;
            }
        }
        student.setCompanyId(WebUtils.getCurrentCompanyId());
        student.setSchoolId(WebUtils.getCurrentSchoolId());
        Student s = new Student();
        if (null != student && null != student.getId()) {
            s = studentServiceImpl.findStudentById(student.getId());
        } else {
            s = studentServiceImpl.findByMobile(student);
        }
        // Student s=studentServiceImpl.queryStuInfoByWhere(student);
        Users user = WebUtils.getCurrentUser(request);
        if (s != null && s.getId() != null) {// 修改
            student.setUpdateTime(new Date());
            student.setUpdator(user.getId());

            student.setId(s.getId());
            studentServiceImpl.update(student);
        } else {// 新增
            student.setCreateTime(new Date());
            student.setCreator(user.getId());
            student.setDeleteFlag(0);
            String password = student.getMobile();
            if (null != student.getMobile() && !"".equals(student.getMobile())) {
                student.setPassword(new Md5Hash(password.substring(password.length() - 6)).toHex());
            } else {
                student.setPassword(new Md5Hash("111111").toHex());
            }

            studentServiceImpl.insert(student);
        }
        Integer stuId = student.getId();

        // 更新前台用户表
        UsersFront userfront = new UsersFront();
        userfront.setCompanyId(WebUtils.getCurrentCompanyId());
        userfront.setEmail(student.getEmail());
        userfront.setUsername(student.getUsername());
        userfront.setMobile(student.getMobile());
        userfront.setSchoolId(student.getSchoolId());

        if (null != s && null != s.getUserId() && !"".equals(s.getUserId())) {// 修改
            userfront.setId(s.getUserId());
            usersFrontServiceImpl.update(userfront);
        } else {// 新增
            userfront.setRegistTime(new Date());
            userfront.setStatus(1);
            userfront.setRegistType(2);
            if (null != student.getMobile() && !"".equals(student.getMobile())) {
                userfront.setPassword(new Md5Hash(student.getMobile().substring(student.getMobile().length() - 6)).toHex());
            } else {
                userfront.setPassword(new Md5Hash("111111").toHex());
            }

            usersFrontServiceImpl.insert(userfront);
            // 更新用户
            student.setUserId(userfront.getId());
            studentServiceImpl.update(student);
        }
        try {
            Class clazz = Class.forName("com.yuxin.wx.model.student.Student");
            Object stu = clazz.newInstance();
            Field[] fields = clazz.getDeclaredFields();
            Map<String, String[]> map = request.getParameterMap();
            Set<Entry<String, String[]>> sets = map.entrySet();
            Integer row = 0;
            for (Entry<String, String[]> entry : sets) {
                if ("id".equals(entry.getKey()))
                    continue;
                String value = "";
                for (String v : entry.getValue()) {
                    value = v;
                }
                System.out.println(entry.getKey());
                int n = 0;
                for (Field field : fields) {
                    if (field.getName().equals(entry.getKey()) || "isUserFront".equals(entry.getKey())) {
                        n = 0;
                        break;
                    } else {
                        n++;
                        continue;
                    }
                }

                if (n > 0) {
                    // 往纵向表添加数据
                    LongitudinalTableData check = new LongitudinalTableData();
                    check.setColName("stu_id");
                    check.setColValue(stuId + "");
                    check.setCompanyId(WebUtils.getCurrentCompanyId());
                    check.setTableName("student");
                    List<LongitudinalTableData> ck = longitudinalTableDataServiceImpl.query(check);
                    if (ck != null && ck.size() > 0) {
                        // 说明已经有数据了
                        row = ck.get(0).getRowId();
                    }
                    for (String v : entry.getValue()) {
                        value = v;
                    }

                    LongitudinalTableData data = new LongitudinalTableData();
                    data.setColName(entry.getKey());
                    data.setRowId(row);
                    data.setCompanyId(WebUtils.getCurrentCompanyId());
                    data.setTableName("student");
                    List<LongitudinalTableData> ld = longitudinalTableDataServiceImpl.query(data);
                    if (ld != null && ld.size() > 0) {
                        LongitudinalTableData d = ld.get(0);
                        d.setColValue(value);
                        longitudinalTableDataServiceImpl.update(d);
                    } else {
                        if (row == 0) {
                            data.setRowId(null);
                            row = longitudinalTableDataServiceImpl.getCurrtRow(data);
                            if (row == null) {
                                row = 1;
                            }
                        }
                        data.setSchoolId(WebUtils.getCurrentSchoolId());
                        data.setColValue(value);
                        data.setRowId(row);
                        longitudinalTableDataServiceImpl.insert(data);
                    }

                    // 插入id、stu_id、userfront_id
                    List<LongitudinalTableData> check2 = new ArrayList<LongitudinalTableData>();
                    LongitudinalTableData d = new LongitudinalTableData();
                    d.setCompanyId(WebUtils.getCurrentCompanyId());
                    d.setTableName("student");
                    d.setColName("id");
                    d.setRowId(row);
                    check2 = longitudinalTableDataServiceImpl.query(d);
                    if (check2 == null || check2.size() == 0) {
                        Integer id = longitudinalTableDataServiceImpl.getSequence();
                        d.setColValue(id + "");
                        d.setSchoolId(WebUtils.getCurrentSchoolId());
                        longitudinalTableDataServiceImpl.insert(d);
                    }
                    d.setSchoolId(null);
                    d.setColName("stu_id");
                    d.setColValue(stuId + "");
                    check2.clear();
                    check2 = longitudinalTableDataServiceImpl.query(d);
                    if (check == null || check2.size() == 0) {
                        d.setSchoolId(WebUtils.getCurrentSchoolId());
                        longitudinalTableDataServiceImpl.insert(d);
                    }

                }

            }

        } catch (Exception e) {
            log_student.error(">>> [保存学员] " + "状态：存纵向表失败" + ", 信息：" + "公司ID = " + WebUtils.getCurrentCompanyId() + ", 操作人ID = "
                    + WebUtils.getCurrentUserId(request) + ", 学生ID = " + student.getId(), e);
            e.printStackTrace();
        }
        log_student.info(">>> [保存学员] " + "状态：success" + ", 信息：" + "公司ID = " + WebUtils.getCurrentCompanyId() + ", 操作人ID = " + WebUtils.getCurrentUserId(request)
                + ", 学生ID = " + student.getId());
        return studentServiceImpl.findStudentById(student.getId());
    }

    /**
     *
     * Class Name: StudentController.java
     *
     * @Description: 新增学员
     * @author Chopin
     * @date 2014年12月31日
     * @version 1.0
     * @param request
     * @param student
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/search/{identityId}", method = RequestMethod.POST)
    public Student search(HttpServletRequest request, @PathVariable String identityId) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("identityId", identityId);
        map.put("schoolId", "" + WebUtils.getCurrentSchoolId());
        map.put("companyId", "" + WebUtils.getCurrentCompanyId());
        Student student = studentServiceImpl.findByCode2(map);
        return student;
    }

    /**
     *
     * Class Name: StudentController.java
     *
     * @Description: 修改学员
     * @author Chopin
     * @date 2014年12月31日
     * @version 1.0
     * @param request
     * @param student
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(HttpServletRequest request, Student student) {
        Users user = WebUtils.getCurrentUser(request);
        student.setUpdateTime(new Date());
        student.setUpdator(user.getId());
        studentServiceImpl.update(student);
        return "{stuId:" + student.getId() + ",message:'success'}";
    }

    /**
     *
     * Class Name: StudentController.java
     *
     * @Description: 验证学员是否已经注册过，可以通过姓名，手机，证件号码验证
     * @author Chopin
     * @date 2014年12月19日
     * @version 1.0
     * @param student
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public Boolean check(Student student) {
        student.setCompanyId(WebUtils.getCurrentCompanyId());
        List<Student> list = studentServiceImpl.checkUserInfo(student);
        if (list == null || list.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * Class Name: StudentController.java
     *
     * @Description: 验证用户名是否存在
     * @author dongshuai
     * @date 2016年7月8日 下午2:09:22
     * @modifier
     * @modify-date 2016年7月8日 下午2:09:22
     * @version 1.0
     * @param student
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/checkByUsername", method = RequestMethod.POST)
    public Boolean checkByUsername(Student student) {
        Student stu = new Student();
        stu.setCompanyId(WebUtils.getCurrentCompanyId());
        stu.setUsername(student.getUsername());
        Integer count = studentServiceImpl.checkUsersFrontByUsername(stu);
        if (count > 0) {
            return false;
        } else {
            return true;
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Student student) {
        studentServiceImpl.update(student);
        return "redirect:/student";
    }

    @RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
    public String del(Model model, @PathVariable Integer id) {
        studentServiceImpl.deleteStudentById(id);
        return "redirect:/student";
    }

    @ResponseBody
    @RequestMapping(value = "/{id:\\d+}", method = RequestMethod.GET)
    public Student getDetail(Model model, @PathVariable Integer id) {
        return studentServiceImpl.findStudentById(id);
    }

    @RequestMapping(value = "/page/{page}", method = RequestMethod.GET)
    public String showPage(Model model, HttpServletRequest request, @PathVariable String page) {
        return "/student/" + page;
    }

    /**
     *
     * Class Name: StudentPayMasterController.java
     *
     * @Description: 根据手机号查询学员
     * @author Chopin
     * @date 2014年12月12日 下午3:33:04
     * @version 1.0
     * @param search
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/searchByMobile/{mobile}", method = RequestMethod.POST)
    public Map<String, Object> searchByMobile(@PathVariable String mobile) {
        Map<String, Object> map = new HashMap<String, Object>();
        Boolean reachMaxStudentNum = companyServiceImpl.reachMaxStudentNum(WebUtils.getCurrentCompanyId());
        if (reachMaxStudentNum) {
            // 达到招生最大人数
            map.put("message", "已超过所能招生的最大人数，请联系管理员增加招生人数");
            return map;
        }
        Boolean reachMaxStudentDate = companyServiceImpl.reachMaxStudentDate(WebUtils.getCurrentCompanyId());
        if (reachMaxStudentDate) {
            map.put("message", "已超过服务有效期，请联系管理员延长服务期限");
            return map;
        }
        Student student = new Student();
        student.setCompanyId(WebUtils.getCurrentCompanyId());
        student.setMobile(mobile);
        Student st = studentServiceImpl.findByMobile(student);
        if (st != null) {
            map.put("id", st.getId());
        }
        map.put("mobile", mobile);

        return map;
    }

    /**
     *
     * Class Name: StudentPayMasterController.java
     *
     * @Description: 根据手机号查询学员
     * @author Chopin
     * @date 2014年12月12日 下午3:33:04
     * @version 1.0
     * @param search
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/searchByMobile2/{mobile}", method = RequestMethod.POST)
    public Student searchByMobile2(@PathVariable String mobile) {
        Map<String, Object> map = new HashMap<String, Object>();
        Company com = companyServiceImpl.findCompanyById(WebUtils.getCurrentCompanyId());
        if (null != com && null != com.getServiceVersion() && "ONLINE_COUNT".equals(com.getServiceVersion())) {
            Student student = new Student();
            student.setCompanyId(WebUtils.getCurrentCompanyId());
            student.setMobile(mobile);
            Student st = studentServiceImpl.findByMobile(student);
            return st;
        } else {
            Boolean reachMaxStudentNum = companyServiceImpl.reachMaxStudentNum(WebUtils.getCurrentCompanyId());
            if (reachMaxStudentNum) {
                return null;
            }
            Boolean reachMaxStudentDate = companyServiceImpl.reachMaxStudentDate(WebUtils.getCurrentCompanyId());
            if (reachMaxStudentDate) {
                return null;
            }
            Student student = new Student();
            student.setCompanyId(WebUtils.getCurrentCompanyId());
            student.setMobile(mobile);
            Student st = studentServiceImpl.findByMobile(student);
            return st;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/searchByUsername1/{username}", method = RequestMethod.POST)
    public Student searchByUsername1(@PathVariable String username) {
        Map<String, Object> map = new HashMap<String, Object>();
        Company com = companyServiceImpl.findCompanyById(WebUtils.getCurrentCompanyId());
        if (null != com && null != com.getServiceVersion() && "ONLINE_COUNT".equals(com.getServiceVersion())) {
            Student student = new Student();
            student.setCompanyId(WebUtils.getCurrentCompanyId());
            student.setUsername(username);
            Student st = studentServiceImpl.queryByUsername(student);
            return st;
        } else {
            Boolean reachMaxStudentNum = companyServiceImpl.reachMaxStudentNum(WebUtils.getCurrentCompanyId());
            if (reachMaxStudentNum) {
                return null;
            }
            Boolean reachMaxStudentDate = companyServiceImpl.reachMaxStudentDate(WebUtils.getCurrentCompanyId());
            if (reachMaxStudentDate) {
                return null;
            }
            Student student = new Student();
            student.setCompanyId(WebUtils.getCurrentCompanyId());
            student.setUsername(username);
            Student st = studentServiceImpl.queryByUsername(student);
            return st;
        }
    }

    /**
     *
     * Class Name: StudentController.java
     *
     * @Description: 根据用户名查询学员
     * @author zhang.zx
     * @date 2016年7月7日 下午7:12:39
     * @modifier
     * @modify-date 2016年7月7日 下午7:12:39
     * @version 1.0
     * @param mobile
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/searchByUserName/{username}", method = RequestMethod.POST)
    public Student searchByUserName(@PathVariable String username) {
        Map<String, Object> map = new HashMap<String, Object>();
        Company com = companyServiceImpl.findCompanyById(WebUtils.getCurrentCompanyId());
        if (null != com && null != com.getServiceVersion() && "ONLINE_COUNT".equals(com.getServiceVersion())) {
            UsersFront u = new UsersFront();
            u.setCompanyId(WebUtils.getCurrentCompanyId());
            u.setUsername(username);
            u.setStatus(1);
            List<UsersFront> arr = usersFrontServiceImpl.findConponsUsersByCondition(u);
            if (null != arr && arr.size() > 0) {
                map.put("userId", arr.get(0).getId());
                map.put("companyId", WebUtils.getCurrentCompanyId());
                List<Student> stuArr = studentServiceImpl.findStuInfoByUserId(map);
                if (null != stuArr && null != stuArr.get(0)) {
                    return stuArr.get(0);
                }
            }
            return null;
        } else {
            Boolean reachMaxStudentNum = companyServiceImpl.reachMaxStudentNum(WebUtils.getCurrentCompanyId());
            if (reachMaxStudentNum) {
                return null;
            }
            Boolean reachMaxStudentDate = companyServiceImpl.reachMaxStudentDate(WebUtils.getCurrentCompanyId());
            if (reachMaxStudentDate) {
                return null;
            }
            UsersFront u = new UsersFront();
            u.setCompanyId(WebUtils.getCurrentCompanyId());
            u.setUsername(username);
            u.setStatus(1);
            List<UsersFront> arr = usersFrontServiceImpl.findConponsUsersByCondition(u);
            if (null != arr && arr.size() > 0) {
                map.put("userId", arr.get(0).getId());
                map.put("companyId", WebUtils.getCurrentCompanyId());
                List<Student> stuArr = studentServiceImpl.findStuInfoByUserId(map);
                if (null != stuArr && null != stuArr.get(0)) {
                    return stuArr.get(0);
                }
            }
            return null;
        }
    }

    /**
     *
     * Class Name: StudentPayMasterController.java
     *
     * @Description: ajax方式加载学员信息列表
     * @author Chopin
     * @date 2014年12月12日 下午3:33:04
     * @version 1.0
     * @param search
     * @return
     */
    @RequestMapping(value = "/showSignUp", method = RequestMethod.POST)
    public String showSignUp(Model model, Student student,HttpServletRequest request) {
        student.setCompanyId(WebUtils.getCurrentCompanyId());
        // student.setSchoolId(WebUtils.getCurrentSchoolId());
        // Student st=studentServiceImpl.findByMobile(student);
        Student st = studentServiceImpl.queryStuInfoByWhere(student);
        if (st != null && st.getId() != null) {
            if (null != st.getGroupOneId()) {
                StudentGroup g1 = studentGroupServiceImpl.findStudentGroupById(st.getGroupOneId());
                if (null != g1) {
                    model.addAttribute("G1Name", g1.getGroupName());
                }
            }
            if (null != st.getGroupTwoId()) {
                StudentGroup g2 = studentGroupServiceImpl.findStudentGroupById(st.getGroupTwoId());
                if (null != g2) {
                    model.addAttribute("G2Name", g2.getGroupName());
                }
            }
            model.addAttribute("student", st);
        } else {
            model.addAttribute("student", student);
        }
        // 查询公司注册设置
        CompanyRegisterConfig crc = new CompanyRegisterConfig();
        crc.setCompanyId(WebUtils.getCurrentCompanyId());
        crc = companyRegisterConfigServiceImpl.queryByCompanyId(crc);
        if (null != crc && crc.getCloseFlag() == 1) {
            crc.setMobileFlag(1);
        }
        model.addAttribute("registConfig", crc);

        CompanyFunctionSet companyFunctionSet = new CompanyFunctionSet();
        companyFunctionSet.setCompanyId(WebUtils.getCurrentCompanyId());
        companyFunctionSet.setFunctionCode("STUDENT_GROUP");
        List<CompanyFunctionSet> companyFunctionSetList = companyFunctionSetServiceImpl.findCompanyFunctionSetByPage(companyFunctionSet);
        if (companyFunctionSetList != null && companyFunctionSetList.size() > 0) {
            model.addAttribute("sgOpen", companyFunctionSetList.get(0).getStatus());
        }
        List<SysConfigItemRelation> relations = sysConfigItemRelationServiceImpl.findItemFront(new SysConfigItemRelation());
        SysConfigItem item = new SysConfigItem();
        item.setCompanyId(WebUtils.getCurrentCompanyId());
        item.setSchoolId( WebUtils.getCurrentUserSchoolId(request));
        item.setItemType("2");
        List<SysConfigItem> names = sysConfigItemServiceImpl.findByParentCode(item);
        for(SysConfigItemRelation re : relations) {
            for (SysConfigItem name : names) {
                if (re.getItemCode().equals(name.getItemCode())) {
                    re.setItemName(name.getItemName());
                    break;
                }
            }
        }
        model.addAttribute("relations", relations);
        return "student/student-signup";
    }

    /**
     *
     * @fileName : StudentController.java
     * @date : 2015年10月19日 下午4:18:25
     * @author : 杨延博
     * @description :批量报名页面跳转
     */
    @RequestMapping(value = "/showSignUpMany", method = RequestMethod.POST)
    public String showSignUpMany(Model model, String list,HttpServletRequest request) {

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
            Student st = studentServiceImpl.queryStuInfoByWhere(student);
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
        List<SysConfigItemRelation> relations = sysConfigItemRelationServiceImpl.findItemFront(new SysConfigItemRelation());
        SysConfigItem item = new SysConfigItem();
        item.setCompanyId(WebUtils.getCurrentCompanyId());
        item.setSchoolId( WebUtils.getCurrentUserSchoolId(request));
        item.setItemType("2");
        List<SysConfigItem> names = sysConfigItemServiceImpl.findByParentCode(item);
        for(SysConfigItemRelation re : relations) {
            for (SysConfigItem name : names) {
                if (re.getItemCode().equals(name.getItemCode())) {
                    re.setItemName(name.getItemName());
                    break;
                }
            }
        }
        model.addAttribute("relations", relations);
        model.addAttribute("student", resultList);
        return "student/student-signupMany";
    }
    @ResponseBody
    @RequestMapping(value = "/findItem", method = RequestMethod.POST)
    public JSONObject findItem(Model model, Integer id, HttpServletRequest request) {
        JSONObject jsObject = new JSONObject();
        SysConfigItemRelation relation = new SysConfigItemRelation();
        relation.setId(id);
        List<SysConfigItemRelation> relations = sysConfigItemRelationServiceImpl.findItemFront(relation);
        SysConfigItem item = new SysConfigItem();
        item.setCompanyId(WebUtils.getCurrentCompanyId());
        item.setSchoolId( WebUtils.getCurrentUserSchoolId(request));
        item.setItemType("2");
        List<SysConfigItem> names = sysConfigItemServiceImpl.findByParentCode(item);
        for(SysConfigItemRelation re : relations) {
            for (SysConfigItem name : names) {
                if (re.getItemCode().equals(name.getItemCode())) {
                    re.setItemName(name.getItemName());
                    break;
                }
            }
        }
        jsObject.put("data",relations);
        return jsObject;
    }

    /**
     *
     * Class Name: StudentPayMasterController.java
     *
     * @Description: ajax方式加载学员信息列表
     * @author Chopin
     * @date 2014年12月12日 下午3:33:04
     * @version 1.0
     * @param search
     * @return
     */
    @RequestMapping(value = "/full/showSignUp", method = RequestMethod.POST)
    public String fullShowSignUp(Model model, Student student) {
        student.setCompanyId(WebUtils.getCurrentCompanyId());
        // student.setSchoolId(WebUtils.getCurrentSchoolId());
        // Student st=studentServiceImpl.findByMobile(student);
        Student st = studentServiceImpl.queryStuInfoByWhere(student);
        if (st != null && st.getId() != null) {
            if (null != st.getGroupOneId()) {
                StudentGroup g1 = studentGroupServiceImpl.findStudentGroupById(st.getGroupOneId());
                if (null != g1) {
                    model.addAttribute("G1Name", g1.getGroupName());
                }
            }
            if (null != st.getGroupTwoId()) {
                StudentGroup g2 = studentGroupServiceImpl.findStudentGroupById(st.getGroupTwoId());
                if (null != g2) {
                    model.addAttribute("G2Name", g2.getGroupName());
                }
            }
            model.addAttribute("student", st);
        } else {
            model.addAttribute("student", student);
        }
        // 查询公司注册设置
        CompanyRegisterConfig crc = new CompanyRegisterConfig();
        crc.setCompanyId(WebUtils.getCurrentCompanyId());
        crc = companyRegisterConfigServiceImpl.queryByCompanyId(crc);
        if (null != crc && crc.getCloseFlag() == 1) {
            crc.setMobileFlag(1);
        }
        model.addAttribute("registConfig", crc);

        CompanyFunctionSet companyFunctionSet = new CompanyFunctionSet();
        companyFunctionSet.setCompanyId(WebUtils.getCurrentCompanyId());
        companyFunctionSet.setFunctionCode("STUDENT_GROUP");
        List<CompanyFunctionSet> companyFunctionSetList = companyFunctionSetServiceImpl.findCompanyFunctionSetByPage(companyFunctionSet);
        if (companyFunctionSetList != null && companyFunctionSetList.size() > 0) {
            model.addAttribute("sgOpen", companyFunctionSetList.get(0).getStatus());
        }
        return "student/student-signup-full";
    }

    /**
     *
     * @fileName : StudentController.java
     * @date : 2015年10月21日 上午10:19:13
     * @author : 杨延博
     * @description :复杂版批量报名
     */
    @RequestMapping(value = "/full/showSignUpMany", method = RequestMethod.POST)
    public String fullShowSignUpMany(Model model, String list) {

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
            Student st = studentServiceImpl.queryStuInfoByWhere(student);
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
        return "student/student-signupMany-full";
    }

    /**
     *
     * Class Name: StudentPayMasterController.java
     *
     * @Description: ajax方式加载学员信息列表
     * @author Chopin
     * @date 2014年12月12日 下午3:33:04
     * @version 1.0
     * @param search
     * @return
     */
    @RequestMapping(value = "/showAdd", method = RequestMethod.POST)
    public String showAdd(Model model) {
        return "student/student-add";
    }

    /**
     *
     * Class Name: StudentController.java
     *
     * @Description: 根据订单查询学员，同步
     * @author Chopin
     * @date 2014年12月12日
     * @version 1.0
     * @param model
     * @param search
     * @return
     */
    @RequestMapping(value = "/pageList", method = RequestMethod.POST)
    public String stuList(Model model, StudentVo search) {
        if (EntityUtil.isNotBlank(search)) {
            model.addAttribute("list", studentServiceImpl.findListByPayment(search));
        }
        return "student/queryByDate";
    }

    /**
     *
     * Class Name: StudentController.java
     *
     * @Description: 根据订单查询学员,datatable服务器分页
     * @author Chopin
     * @date 2014年12月12日
     * @version 1.0
     * @param model
     * @param search
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/ajaxList", method = RequestMethod.POST)
    public Map stuList(HttpServletRequest request, StudentVo search) {
        return null;
    }

    /**
     *
     * Class Name: StudentController.java
     *
     * @Description: 根据订单查询学员,异步
     * @author Chopin
     * @date 2014年12月12日
     * @version 1.0
     * @param model
     * @param search
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/ajaxList2", method = RequestMethod.POST)
    public PageFinder<StudentVo> stuList2(Model model, HttpServletRequest request, StudentVo search) {
        Users user = WebUtils.getCurrentUser(request);
        if (EntityUtil.isBlank(user)) {
            return null;
        }
        if (EntityUtil.isBlank(search)) {
            return null;
        }
        if (search.getItemOneId() == null) {
            return null;
        }
        search.setPageSize(5);
        search.setSchoolId(user.getSchoolId());
        search.setCompanyId(user.getCompanyId());
        return studentServiceImpl.findPageListByPayment(search);
    }

    /**
     *
     * Class Name: StudentController.java
     *
     * @Description: 根据班型查询学员,异步
     * @author Chopin
     * @date 2014年12月12日
     * @version 1.0
     * @param model
     * @param search
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/ajaxList3", method = RequestMethod.POST)
    public PageFinder<StudentVo> stuList3(HttpServletRequest request, StudentVo search) {
        PageFinder<StudentVo> al = null;
        if (EntityUtil.isNotBlank(search)) {
            search.setPageSize(5);
            search.setSchoolId(WebUtils.getCurrentSchoolId());
            search.setCompanyId(WebUtils.getCurrentCompanyId());
            al = studentServiceImpl.findPageListByClassType(search);
        }
        return al;
    }

    /**
     *
     * Class Name: StudentController.java
     *
     * @Description: 根据班号查询学员,异步
     * @author Chopin
     * @date 2014年12月12日
     * @version 1.0
     * @param model
     * @param search
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/ajaxList4", method = RequestMethod.POST)
    public PageFinder<StudentVo> stuList4(HttpServletRequest request, StudentVo search) {
        PageFinder<StudentVo> al = null;
        if (EntityUtil.isNotBlank(search)) {
            search.setPageSize(5);
            search.setSchoolId(WebUtils.getCurrentSchoolId());
            search.setCompanyId(WebUtils.getCurrentCompanyId());
            al = studentServiceImpl.findPageListByClassNo(search);
        }
        return al;
    }

    @RequestMapping(value = "/exportExcel", method = RequestMethod.POST)
    public ModelAndView exportExcel(Model model, StudentVo search) {

        List<StudentVo> al = new ArrayList<StudentVo>();
        if (EntityUtil.isNotBlank(search)) {
            search.setPageSize(1000);
            search.setSchoolId(WebUtils.getCurrentSchoolId());
            search.setCompanyId(WebUtils.getCurrentCompanyId());
            al = studentServiceImpl.findListByPayment(search);
        }
        ViewFiles excel = new ViewFiles();
        Map<String, Object> map = new HashMap<String, Object>();
        ExcelSheetEntity entity = ExcelSheetEntity.newInstance(
                "姓名:name,手机号:mobile,一级项目:itemOneName,二级项目:itemSecondName,班型:classTypeName,考期:examTermName,是否代报:isAgent,资料齐全:isAgentMaterialComplete,操作人:updator",
                al);
        map.put("entity", entity);
        map.put("fileName", "学员列表.xls");
        return new ModelAndView(excel, map);
    }

    @RequestMapping(value = "/exportExcel3", method = RequestMethod.POST)
    public ModelAndView exportExcel3(Model model, StudentVo search) {

        List<StudentVo> al = new ArrayList<StudentVo>();
        if (EntityUtil.isNotBlank(search)) {
            search.setPageSize(1000);
            search.setSchoolId(WebUtils.getCurrentSchoolId());
            search.setCompanyId(WebUtils.getCurrentCompanyId());
            al = studentServiceImpl.findListByClassType(search);
        }
        ViewFiles excel = new ViewFiles();
        Map<String, Object> map = new HashMap<String, Object>();
        ExcelSheetEntity entity = ExcelSheetEntity
                .newInstance("姓名:name,手机号:mobile,性别:sex,年龄:age,学历:educationCode,报名日期:applyTime,班型:classTypeName,报名费:totalAmount,操作人:updator", al);
        map.put("entity", entity);
        map.put("fileName", "学员列表.xls");
        return new ModelAndView(excel, map);
    }

    @RequestMapping(value = "/exportExcel4", method = RequestMethod.POST)
    public ModelAndView exportExcel4(Model model, StudentVo search) {

        List<StudentVo> al = new ArrayList<StudentVo>();
        if (EntityUtil.isNotBlank(search)) {
            search.setSchoolId(WebUtils.getCurrentSchoolId());
            search.setCompanyId(WebUtils.getCurrentCompanyId());
            al = studentServiceImpl.findListByClassNo(search);
        }
        ViewFiles excel = new ViewFiles();
        Map<String, Object> map = new HashMap<String, Object>();
        ExcelSheetEntity entity = ExcelSheetEntity.newInstance("姓名:name,手机号:mobile,班号:nname,班型:classTypeName,报名日期:applyTime,操作人:updator", al);
        map.put("entity", entity);
        map.put("fileName", "学员列表.xls");
        return new ModelAndView(excel, map);
    }

    @ResponseBody
    @RequestMapping(value = "/queryByClassType", method = RequestMethod.POST)
    public List<ClassType> queryByClassType(ClassType classType) {
        List<ClassType> list = new ArrayList<ClassType>();
        classTypeServiceImpl.findClassTypeList(classType);
        return list;
    }

    @ResponseBody
    @RequestMapping(value = "/queryByClassNo", method = RequestMethod.POST)
    public List<ClassModuleNo> querByClassNo(ClassModuleNo classNo) {
        List<ClassModuleNo> list = new ArrayList();
        classModuleNoServiceImpl.findClassModuleNoByPage(classNo);
        return list;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    private Map<String, Object> readRequst(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        Enumeration paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();

            String[] paramValues = request.getParameterValues(paramName);
            if (paramValues.length == 1) {
                String paramValue = paramValues[0];
                if (paramValue.length() != 0) {
                    System.out.println("参数：" + paramName + "=" + paramValue);
                    map.put(paramName, paramValue);
                }
            }
        }
        return map;
    }

    public static void main(String[] args) {
        String strImg = GetImageStr();
        System.out.println(strImg);
        // GenerateImage(strImg);
    }

    // 图片转化成base64字符串
    public static String GetImageStr() {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        String imgFile = "e://test.png";// 待处理的图片
        InputStream in = null;
        byte[] data = null;
        // 读取图片字节数组
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);// 返回Base64编码过的字节数组字符串
    }

    @RequestMapping(value = "toSearch")
    public String toSearch() {

        return "student/pay/toSearch";
    }

    @ResponseBody
    @RequestMapping(value = "search", method = RequestMethod.POST)
    public Integer search(String mobile, Model model) {
        Integer companyId = WebUtils.getCurrentCompanyId();
        Student student = new Student();
        student.setMobile(mobile);
        student.setCompanyId(WebUtils.getCurrentCompanyId());
        student = studentServiceImpl.findByMobile(student);
        if (student == null) {
            return -1;
        }
        List<StudentPayMaster> list = studentPayMasterServiceImpl.findByStuMbile(mobile, companyId, WebUtils.getCurrentSchoolId());
        if (list.size() > 0) {

            return list.get(0).getId();
        } else {
            return null;
        }

    }

    @RequestMapping(value = "toPayMessage")
    public String toPayMessage(Model model, String mobile) {

        Integer companyId = WebUtils.getCurrentCompanyId();
        // mobile="18292782950";
        model.addAttribute("mobile", mobile);
        List<StudentPayMaster> list = studentPayMasterServiceImpl.findByStuMbile(mobile, companyId, WebUtils.getCurrentSchoolId());
        // 优先上课校区
        Integer sch_id = list.get(0).getSchoolId();
        SysConfigSchool schoo = sysConfigSchoolServiceImpl.findSysConfigSchoolById(sch_id);
        model.addAttribute("schoo", schoo);
        Student student = studentServiceImpl.findMessageByMobile(mobile, WebUtils.getCurrentCompanyId(), WebUtils.getCurrentSchoolId());

        // 查询所报所有班型
        ClassType classType = classTypeServiceImpl.findClassTypeByStuId(student.getId(), companyId, list.get(0).getId());
        model.addAttribute("payMaster", list.get(0));
        model.addAttribute("student", student);
        // 查询班型所对应的一级项目和二级项目
        Integer oneId = classType.getItemOneId();
        Integer secondId = classType.getItemSecondId();
        SysConfigItem oneItem = sysConfigItemServiceImpl.findSysConfigItemById(oneId);
        SysConfigItem secondItem = sysConfigItemServiceImpl.findSysConfigItemById(secondId);
        model.addAttribute("classType", classType);
        model.addAttribute("oneItem", oneItem);
        model.addAttribute("secondItem", secondItem);
        // 查询出所属分校
        String schoolsId = classType.getSchoolsId();
        String[] schoolIds = schoolsId.split(",");
        List<SysConfigSchool> schoolList = new ArrayList<SysConfigSchool>();
        for (String schoolId : schoolIds) {
            SysConfigSchool school = sysConfigSchoolServiceImpl.findSysConfigSchoolById(Integer.parseInt(schoolId));
            schoolList.add(school);
        }
        // 所属分校
        model.addAttribute("schoolList", schoolList);
        // 查询课程所对应模块
        List<ClassModule> classModuleList = classModuleServiceImpl.findByClassTypeId(classType.getId());
        Integer classHour = 0;
        for (ClassModule classModule : classModuleList) {
            Integer totalClassHour = classModule.getTotalClassHour();
            classHour = classHour + totalClassHour;
        }
        model.addAttribute("classHour", classHour);
        model.addAttribute("classModuleList", classModuleList);
        model.addAttribute("studentPayMaster", list.get(0));
        // 查询出该订单下的所有交费的信息
        List<StudentFeeStage> studentFeeStageList = studentFeeStageServiceImpl.findAll(list.get(0).getId());

        model.addAttribute("studentFeeStageList", studentFeeStageList);
        double cashReal = 0; // 现金交费
        double postReal = 0; // 刷卡缴费
        double checkReal = 0; // 支票
        double remitReal = 0; // 汇款
        int count = 0;
        // 查询所有已交费用
        for (int i = 0; i < studentFeeStageList.size(); i++) {
            StudentFeeStage studentFeeStage = studentFeeStageList.get(i);
            if ("1".equals(studentFeeStage.getStageStatus())) {
                studentFeeStageList.remove(studentFeeStage);
                i--;
                count++;
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
        // 查询所有模块信息
        List<StudentPaySlaveVo> paySlaveList = studentPaySlaveServiceImpl.findStudentPaySlaveByPayMasterId2(list.get(0).getId());
        model.addAttribute("paySlaveList", paySlaveList);
        model.addAttribute("count", count + 1);
        model.addAttribute("cashReal", cashReal);
        model.addAttribute("checkReal", checkReal);
        model.addAttribute("postReal", postReal);
        model.addAttribute("remitReal", remitReal);
        return "student/pay/payMessage";
    }

    /**
     *
     * Class Name: StudentController.java
     *
     * @Description:
     * @author 权飞虎
     * @date 2015年4月30日 下午2:04:51
     * @modifier
     * @modify-date 2015年4月30日 下午2:04:51
     * @version 1.0
     * @param model
     * @param id
     *            feeStage的ID 必传
     * @param mobile
     *            学员电话 可不传
     * @param pid
     *            订单id 可不传
     * @return
     */
    @RequestMapping(value = "pay", method = RequestMethod.POST)
    public String pay(Model model, Integer id, String mobile, Integer pid, StudentPayMaster payMaster) {
        model.addAttribute("mobile", mobile);
        model.addAttribute("id", id);
        payMaster.setId(pid);
        model.addAttribute("payMaster", payMaster);
        StudentFeeStage feeStage = studentFeeStageServiceImpl.findStudentFeeStageById(id);
        Integer payMasterId = feeStage.getPayMasterId();
        // StudentPayMaster payMaster =
        // studentPayMasterServiceImpl.findStudentPayMasterById(payMasterId);
        if (payMaster == null || payMaster.getId() == null) {
            payMaster = studentPayMasterServiceImpl.findStudentPayMasterById(payMasterId);
        }
        model.addAttribute("total", payMaster.getTotalAmount());
        // 查询出该订单的所有交费信息
        List<StudentFeeStage> studentFeeStageList = studentFeeStageServiceImpl.findUnPayed(payMasterId);
        double cashReal = 0; // 现金交费
        double postReal = 0; // 刷卡缴费
        double checkReal = 0; // 支票
        double remitReal = 0; // 汇款
        // 查询所有已交费用
        for (StudentFeeStage studentFeeStage : studentFeeStageList) {
            if ("1".equals(studentFeeStage.getStageStatus())) {
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
        // 已交费用
        model.addAttribute("payed", cashReal + checkReal + postReal + remitReal);
        // 本期信息
        model.addAttribute("feeStage", feeStage);

        return "student/pay/pay";

    }

    @RequestMapping(value = "pay1", method = RequestMethod.POST)
    public String pay1(Model model, String id) {
        model.addAttribute("id", id);

        StudentFeeStage feeStage = studentFeeStageServiceImpl.findStudentFeeStageById(Integer.parseInt(id));
        Integer payMasterId = feeStage.getPayMasterId();
        StudentPayMaster payMaster = studentPayMasterServiceImpl.findStudentPayMasterById(payMasterId);
        model.addAttribute("total", payMaster.getTotalAmount());
        model.addAttribute("payMaster", payMaster);
        // 查询出该订单的所有交费信息
        List<StudentFeeStage> studentFeeStageList = studentFeeStageServiceImpl.findHasPayed(payMasterId);
        double cashReal = 0; // 现金交费
        double postReal = 0; // 刷卡缴费
        double checkReal = 0; // 支票
        double remitReal = 0; // 汇款
        double payed = 0;
        // 查询所有已交费用
        for (StudentFeeStage studentFeeStage : studentFeeStageList) {
            if ("1".equals(studentFeeStage.getStageStatus())) {
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
                payed += studentFeeStage.getStageFee();
            }
        }
        // 已交费用
        model.addAttribute("payed", payed);
        // 本期信息
        model.addAttribute("feeStage", feeStage);

        return "student/urgeFee/pay";

    }

    @ResponseBody
    @RequestMapping(value = "toPay", method = RequestMethod.POST)
    public String toPay(String posReal, HttpServletRequest request, String cashReal, String remitReal, String checkReal, Integer id, Integer payMasterId) {
        StudentFeeStage feeStage = studentFeeStageServiceImpl.findStudentFeeStageById(id);
        Double stageFee = feeStage.getStageFee();
        double pos = 0;
        double cash = 0;
        double remit = 0;
        double check = 0;
        double totalAmount = 0;
        if (posReal != null && posReal != "") {
            pos = Double.parseDouble(posReal);
        }
        if (cashReal != null && cashReal != "") {
            cash = Double.parseDouble(cashReal);
        }
        if (remitReal != null && remitReal != "") {
            remit = Double.parseDouble(remitReal);
        }
        if (checkReal != null && checkReal != "") {
            check = Double.parseDouble(checkReal);
        }
        if (stageFee != (pos + cash + remit + check)) {
            return "error";
        } else {
            feeStage.setStageStatus("1");
            feeStage.setPosReal(pos);
            feeStage.setCashReal(cash);
            feeStage.setRemitReal(remit);
            feeStage.setCheckReal(check);
            feeStage.setUpdateTime(new Date());

            feeStage.setUpdator(WebUtils.getCurrentUserId(request));
            feeStage.setPayDate(new Date());
            studentFeeStageServiceImpl.update(feeStage);

            // 更新主订单
            StudentPayMaster paymaster = studentPayMasterServiceImpl.findStudentPayMasterById(feeStage.getPayMasterId());
            List<StudentFeeStage> stages = studentFeeStageServiceImpl.findUnPayed(payMasterId);
            for (StudentFeeStage stage : stages) {
                if ("1".equals(stage.getStageStatus())) {
                    totalAmount += stage.getStageFee();
                }
            }
            if (paymaster.getTotalAmount().equals(totalAmount)) {
                paymaster.setPayStatusCode("ORDER_PAID");
                studentPayMasterServiceImpl.update(paymaster);
            }

            return JsonMsg.SUCCESS;
        }
    }

    @RequestMapping(value = "toMessage", method = RequestMethod.POST)
    public String toSearchBaseMessage(Model model, String mobile, Integer studentPayMasterId) {
        StudentPayMaster payMaster = studentPayMasterServiceImpl.findStudentPayMasterById(studentPayMasterId);
        model.addAttribute("payMaster", payMaster);
        Student student = studentServiceImpl.findMessageByMobile(mobile, WebUtils.getCurrentCompanyId(), WebUtils.getCurrentSchoolId());
        model.addAttribute("student", student);
        model.addAttribute("mobile", mobile);
        return "student/pay/payMessage";
    }

    /**
     *
     * Class Name: StudentController.java
     *
     * @Description: 查詢基本信息
     * @author 权飞虎
     * @date 2015年4月28日 下午12:50:25
     * @modifier
     * @modify-date 2015年4月28日 下午12:50:25
     * @version 1.0
     * @param model
     * @param mobile
     * @return
     */
    @RequestMapping(value = "baseMessage", method = RequestMethod.POST)
    public String baseMessage(Model model, String mobile) {
        Student student = studentServiceImpl.findMessageByMobile(mobile, WebUtils.getCurrentCompanyId(), WebUtils.getCurrentSchoolId());
        model.addAttribute("student", student);
        return "student/pay/ajaxBaseMessage";
    }

    /**
     *
     * Class Name: StudentController.java
     *
     * @Description: 查询班型相关信息
     * @author 权飞虎
     * @date 2015年4月30日 上午11:41:45
     * @modifier
     * @modify-date 2015年4月30日 上午11:41:45
     * @version 1.0
     * @param model
     * @param mobile
     *            学生电话
     * @param stuId
     *            学生id
     * @param payMaster
     *            学生订单
     * @return
     */
    @RequestMapping(value = "classTypeMessage", method = RequestMethod.POST)
    public String classTypeMessage(Model model, String mobile, StudentPayMaster payMaster) {
        Integer companyId = WebUtils.getCurrentCompanyId();
        // Student student = studentServiceImpl.findMessageByMobile(mobile);
        if (payMaster == null || payMaster.getId() == null || payMaster.getStuId() == null) {
            List<StudentPayMaster> list = studentPayMasterServiceImpl.findByStuMbile(mobile, companyId, WebUtils.getCurrentSchoolId());
            payMaster = list.get(0);
            // stuId=payMaster.getStuId();
        }

        // 查询所报所有班型
        ClassType classType = classTypeServiceImpl.findClassTypeByStuId(payMaster.getStuId(), companyId, payMaster.getId());
        // 查询班型所对应的一级项目和二级项目
        // 查询班型所对应的一级项目和二级项目
        Integer oneId = classType.getItemOneId();
        Integer secondId = classType.getItemSecondId();
        SysConfigItem oneItem = sysConfigItemServiceImpl.findSysConfigItemById(oneId);
        SysConfigItem secondItem = sysConfigItemServiceImpl.findSysConfigItemById(secondId);
        model.addAttribute("classType", classType);
        model.addAttribute("oneItem", oneItem);
        model.addAttribute("secondItem", secondItem);
        // 查询出所属分校
        // String schoolsId = classType.getSchoolsId();
        // String[] schoolIds = schoolsId.split(",");
        // List<SysConfigSchool> schoolList = new ArrayList<SysConfigSchool>();
        // for (String schoolId : schoolIds) {
        // SysConfigSchool school =
        // sysConfigSchoolServiceImpl.findSysConfigSchoolById(Integer.parseInt(schoolId));
        // schoolList.add(school);
        // }
        // //所属分校
        // model.addAttribute("schoolList", schoolList);
        SysConfigSchool createSchool = sysConfigSchoolServiceImpl.findSysConfigSchoolById(classType.getCreateSchoolId());
        model.addAttribute("createSchool", createSchool);
        // 查询课程所对应模块
        List<ClassModule> classModuleList = classModuleServiceImpl.findByClassTypeId(classType.getId());
        Integer classHour = 0;
        for (ClassModule classModule : classModuleList) {
            Integer totalClassHour = classModule.getTotalClassHour();
            classHour = classHour + totalClassHour;
        }
        model.addAttribute("classHour", classHour);
        // model.addAttribute("payMaster", list.get(0));
        // 优先上课校区
        Integer sch_id = payMaster.getSchoolId();
        SysConfigSchool schoo = sysConfigSchoolServiceImpl.findSysConfigSchoolById(sch_id);
        model.addAttribute("schoo", schoo);
        // 查询所有模块信息
        List<StudentPaySlaveVo> paySlaveList = studentPaySlaveServiceImpl.findStudentPaySlaveByPayMasterId(payMaster.getId());
        model.addAttribute("paySlaveList", paySlaveList);
        return "student/pay/ajaxClassType";
    }

    /**
     *
     * Class Name: StudentController.java
     *
     * @Description: 加載缴费信息
     * @author 权飞虎
     * @date 2015年4月28日 下午2:20:24
     * @modifier
     * @modify-date 2015年4月28日 下午2:20:24
     * @version 1.0
     * @return
     */
    @RequestMapping(value = "feeMessage", method = RequestMethod.POST)
    public String feeMessage(Model model, String mobile) {
        Integer companyId = WebUtils.getCurrentCompanyId();
        List<StudentPayMaster> list = studentPayMasterServiceImpl.findByStuMbile(mobile, companyId, WebUtils.getCurrentSchoolId());
        model.addAttribute("studentPayMaster", list.get(0));
        double cashReal = 0; // 现金交费
        double postReal = 0; // 刷卡缴费
        double checkReal = 0; // 支票
        double remitReal = 0; // 汇款
        int count = 0;
        // 查询出该订单下的所有交费的信息
        List<StudentFeeStage> studentFeeStageList = studentFeeStageServiceImpl.findAll(list.get(0).getId());
        // 查询所有已交费用
        for (int i = 0; i < studentFeeStageList.size(); i++) {
            StudentFeeStage studentFeeStage = studentFeeStageList.get(i);
            if ("1".equals(studentFeeStage.getStageStatus())) {
                studentFeeStageList.remove(studentFeeStage);
                i--;
                count++;
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
        model.addAttribute("count", count + 1);
        model.addAttribute("cashReal", cashReal);
        model.addAttribute("checkReal", checkReal);
        model.addAttribute("postReal", postReal);
        model.addAttribute("remitReal", remitReal);
        model.addAttribute("studentFeeStageList", studentFeeStageList);
        return "student/pay/ajaxFeeMessage";
    }

    @RequestMapping(value = "feeMessage2", method = RequestMethod.POST)
    public String feeMessage2(Model model, Integer payMasterId) {
        // Integer companyId=WebUtils.getCurrentCompanyId();
        StudentPayMaster payMaster = studentPayMasterServiceImpl.findStudentPayMasterById(payMasterId);
        model.addAttribute("studentPayMaster", payMaster);
        double cashReal = 0; // 现金交费
        double postReal = 0; // 刷卡缴费
        double checkReal = 0; // 支票
        double remitReal = 0; // 汇款
        int count = 0;
        // 查询出该订单下的所有交费的信息
        List<StudentFeeStage> studentFeeStageList = studentFeeStageServiceImpl.findAll(payMaster.getId());
        // 查询所有已交费用
        for (int i = 0; i < studentFeeStageList.size(); i++) {
            StudentFeeStage studentFeeStage = studentFeeStageList.get(i);
            if ("1".equals(studentFeeStage.getStageStatus())) {
                studentFeeStageList.remove(studentFeeStage);
                i--;
                count++;
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
        double recashReal = 0; // 现金交费
        double repostReal = 0; // 刷卡缴费
        double recheckReal = 0; // 支票
        double reremitReal = 0; // 汇款
        double countreFee = 0;
        List<StudentFeeRefund> list = StudentFeeRefundServiceImpl.findByPayMasterId(payMasterId);
        for (StudentFeeRefund sfr : list) {
            if (sfr.getCashRefund() != null) {
                recashReal += sfr.getCashRefund();
            }
            if (sfr.getCheckRefund() != null) {
                recheckReal += sfr.getCheckRefund();
            }
            if (sfr.getPosRefund() != null) {
                repostReal += sfr.getPosRefund();
            }
            if (sfr.getRemitRefund() != null) {
                reremitReal += sfr.getRemitRefund();
            }
            if (sfr.getRefundFee() != null) {
                countreFee += sfr.getRefundFee();
            }

        }
        model.addAttribute("count", count + 1);
        model.addAttribute("cashReal", cashReal);
        model.addAttribute("checkReal", checkReal);
        model.addAttribute("postReal", postReal);
        model.addAttribute("remitReal", remitReal);

        model.addAttribute("recount", list.size());
        model.addAttribute("countreFee", countreFee);
        model.addAttribute("recashReal", recashReal);
        model.addAttribute("repostReal", repostReal);
        model.addAttribute("recheckReal", recheckReal);
        model.addAttribute("reremitReal", reremitReal);

        model.addAttribute("studentFeeStageList", studentFeeStageList);
        return "student/pay/ajaxFeeMessage2";
    }

    @RequestMapping(value = "toStuSearch")
    public String toStuSearch() {
        CompanyFunctionSet search = new CompanyFunctionSet();
        search.setFunctionCode("COMPANY_FUNCTION_COURSE");
        search.setCompanyId(WebUtils.getCurrentCompanyId());
        CompanyFunctionSet cfs = companyFunctionSetServiceImpl.findCompanyUseCourse(search);
        if (cfs != null && "1".equals(cfs.getStatus())) {
            return "/student/transaction/full/search";
        }
        return "/student/transaction/search";

    }

    @RequestMapping(value = "toUrgeFee")
    public String toUrgeFee() {

        return "urgeFee";
    }

    /**
     *
     * Class Name: StudentController.java
     *
     * @Description: 异动时查询所有已缴费和部分交费用户
     * @author 权飞虎
     * @date 2015年5月4日 上午11:56:17
     * @modifier
     * @modify-date 2015年5月4日 上午11:56:17
     * @version 1.0
     * @param mobile
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "searchTransaction", method = RequestMethod.POST)
    public String searchTransaction(String mobile) {
        if (null != mobile) {
            List<StudentPayMaster> list = studentPayMasterServiceImpl.findByStuMbile2(mobile, WebUtils.getCurrentCompanyId(), WebUtils.getCurrentSchoolId());
            if (list.size() > 0) {
                return "yes";
            }
        }
        return "no";
    }

    @RequestMapping(value = "toTransaction", method = RequestMethod.POST)
    public String toTransaction(String mobile, Model model) {
        Student student = studentServiceImpl.findMessageByMobile(mobile, WebUtils.getCurrentCompanyId(), WebUtils.getCurrentSchoolId());
        model.addAttribute("student", student);
        return "/student/transaction/transaction";
    }

    /**
     *
     * Class Name: StudentController.java
     *
     * @Description: 学员详情
     * @author 杨延博
     * @date 2015年10月12日 上午11:56:17
     * @modifier
     * @modify-date 2015年10月12日 上午11:56:17
     * @version 1.0
     * @param mobile
     * @return
     */
    @RequestMapping(value = "studentDetailMessage", method = RequestMethod.POST)
    public String studentDetailMessage(String mobile, Model model) {
        Student student = studentServiceImpl.findMessageByMobile(mobile, WebUtils.getCurrentCompanyId(), WebUtils.getCurrentSchoolId());

        SysConfigDict dict = new SysConfigDict();
        //学段
        if (student.getEduStep() != null) {
            dict.setDictCode("EDU_STEP");
            dict.setItemCode(student.getEduStep());
            SysConfigDict obj = sysConfigDictServiceImpl.queryConfigDictValue(dict);
            if(obj!=null){
                student.setEduStep(obj.getItemValue());
            }
        }
        //区域
        if (student.getEduArea() != null) {
            dict.setDictCode("EDU_SCHOOL_AREA");
            dict.setItemCode(student.getEduArea());
            SysConfigDict obj = sysConfigDictServiceImpl.queryConfigDictValue(dict);
            if(obj!=null){
                student.setEduArea(obj.getItemValue());
            }
        }
        //学校
        if (student.getEduSchool() != null) {
            dict.setDictCode("EDU_SCHOOL");
            dict.setItemCode(student.getEduSchool());
            SysConfigDict obj = sysConfigDictServiceImpl.queryConfigDictValue(dict);
            if(obj!=null){
                student.setEduSchool(obj.getItemValue());
            }
        }

        model.addAttribute("student", student);
        if (null != student.getGroupOneId()) {
            StudentGroup g1 = studentGroupServiceImpl.findStudentGroupById(student.getGroupOneId());
            if (null != g1) {
                model.addAttribute("G1Name", g1.getGroupName());
            }
        }
        if (null != student.getGroupTwoId()) {
            StudentGroup g2 = studentGroupServiceImpl.findStudentGroupById(student.getGroupTwoId());
            if (null != g2) {
                model.addAttribute("G2Name", g2.getGroupName());
            }
        }

        CompanyFunctionSet companyFunctionSet = new CompanyFunctionSet();
        companyFunctionSet.setCompanyId(WebUtils.getCurrentCompanyId());
        companyFunctionSet.setFunctionCode("STUDENT_GROUP");
        List<CompanyFunctionSet> companyFunctionSetList = companyFunctionSetServiceImpl.findCompanyFunctionSetByPage(companyFunctionSet);
        if (companyFunctionSetList != null && companyFunctionSetList.size() > 0) {
            model.addAttribute("sgOpen", companyFunctionSetList.get(0).getStatus());
        }
        return "/student/studentDetailMessage";
    }

    @RequestMapping(value = "/full/toTransaction", method = RequestMethod.POST)
    public String toFullTransaction(String mobile, Model model) {
        Student student = studentServiceImpl.findMessageByMobile(mobile, WebUtils.getCurrentCompanyId(), WebUtils.getCurrentSchoolId());
        model.addAttribute("student", student);
        return "/student/transaction/full/transaction";
    }

    /**
     *
     * Class Name: StudentController.java
     *
     * @Description: 完全版-查询报名信息
     * @author 权飞虎
     * @date 2015年5月4日 下午3:13:46
     * @modifier
     * @modify-date 2015年5月4日 下午3:13:46
     * @version 1.0
     * @param mobile
     * @return
     */
    @RequestMapping(value = "/full/selectEntryMessage", method = RequestMethod.POST)
    public String fullSelectEntryMessage(Integer stuId, Model model, String mobile, String lable) {
        List<StudentPayMaster> payMasterList = null;
        if ("".equals(stuId) || stuId == null) {
            payMasterList = studentPayMasterServiceImpl.findByStuMbile2(mobile, WebUtils.getCurrentCompanyId(), WebUtils.getCurrentSchoolId());
        } else {
            payMasterList = studentPayMasterServiceImpl.findEntryMessage(stuId, WebUtils.getCurrentCompanyId());
        }
        // 查询已付款
        for (StudentPayMaster studentPayMaster : payMasterList) {
            Integer id = studentPayMaster.getId();
            Double hasPay = studentFeeStageServiceImpl.findSumPayed(id);
            studentPayMaster.setHasPay(hasPay);
            List<StudentFeeStage> stages = studentFeeStageServiceImpl.findUnPayed(id);
            Double unPay = 0.0;
            for (StudentFeeStage stage : stages) {
                if (stage != null && stage.getStageFee() != null) {
                    unPay += stage.getStageFee();
                }
            }
            studentPayMaster.setUnPay(unPay);
        }
        model.addAttribute("payMasterList", payMasterList);
        model.addAttribute("lable", lable);
        return "student/transaction/full/ajaxEntryMessage";
    }

    /**
     *
     * Class Name: StudentController.java
     *
     * @Description: 查询报名信息
     * @author 权飞虎
     * @date 2015年5月4日 下午3:13:46
     * @modifier
     * @modify-date 2015年5月4日 下午3:13:46
     * @version 1.0
     * @param mobile
     * @return
     */
    @RequestMapping(value = "selectEntryMessage", method = RequestMethod.POST)
    public String selectEntryMessage(Integer stuId, Model model, String mobile, String lable) {
        List<StudentPayMaster> payMasterList = null;
        if ("".equals(stuId) || stuId == null) {
            payMasterList = studentPayMasterServiceImpl.findByStuMbile2(mobile, WebUtils.getCurrentCompanyId(), WebUtils.getCurrentSchoolId());
        } else {
            payMasterList = studentPayMasterServiceImpl.findEntryMessage(stuId, WebUtils.getCurrentCompanyId());
        }
        // 查询已付款
        for (StudentPayMaster studentPayMaster : payMasterList) {
            Integer id = studentPayMaster.getId();
            Double hasPay = studentFeeStageServiceImpl.findSumPayed(id);
            studentPayMaster.setHasPay(hasPay);
            List<StudentFeeStage> stages = studentFeeStageServiceImpl.findUnPayed(id);
            Double unPay = 0.0;
            for (StudentFeeStage stage : stages) {
                if (stage != null && stage.getStageFee() != null) {
                    unPay += stage.getStageFee();
                }
            }
            studentPayMaster.setUnPay(unPay);
        }
        model.addAttribute("payMasterList", payMasterList);
        model.addAttribute("lable", lable);
        return "student/transaction/ajaxEntryMessage";
    }

    /**
     *
     * Class Name: StudentController.java
     *
     * @Description: 查询报名信息
     * @author 杨延博
     * @date 2015年5月4日 下午3:13:46
     * @modifier
     * @modify-date 2015年10月12日 下午3:13:46
     * @version 1.0
     * @param mobile
     * @return
     */
    @RequestMapping(value = "selectEntryMessage2", method = RequestMethod.POST)
    public String selectEntryMessage2(Integer stuId, Model model, String mobile) {
        List<StudentPayMaster> payMasterList = null;
        if ("".equals(stuId) || stuId == null) {
            payMasterList = studentPayMasterServiceImpl.findByStuMbile2(mobile, WebUtils.getCurrentCompanyId(), WebUtils.getCurrentSchoolId());
        } else {
            payMasterList = studentPayMasterServiceImpl.findEntryMessage(stuId, WebUtils.getCurrentCompanyId());
        }
        // 查询已付款
        for (StudentPayMaster studentPayMaster : payMasterList) {
            Integer id = studentPayMaster.getId();
            Double hasPay = studentFeeStageServiceImpl.findSumPayed(id);
            studentPayMaster.setHasPay(hasPay);
            List<StudentFeeStage> stages = studentFeeStageServiceImpl.findUnPayed(id);
            Double unPay = 0.0;
            for (StudentFeeStage stage : stages) {
                if (stage != null && stage.getStageFee() != null) {
                    unPay += stage.getStageFee();
                }
            }
            studentPayMaster.setUnPay(unPay);
        }
        model.addAttribute("payMasterList", payMasterList);
        return "student/transaction/ajaxEntryMessage2";
    }

    /**
     *
     * Class Name: StudentController.java
     *
     * @Description: 异动详情页面
     * @author 权飞虎
     * @date 2015年5月4日 下午7:59:51
     * @modifier
     * @modify-date 2015年5月4日 下午7:59:51
     * @version 1.0
     * @return
     */
    @RequestMapping(value = "selectEntryDetail", method = RequestMethod.POST)
    public String selectEntryDetail(Integer payMasterId, Model model, String lable) {
        StudentPayMaster payMaster = studentPayMasterServiceImpl.findStudentPayMasterById(payMasterId);
        if (null != payMaster) {
            Integer id = payMaster.getId();
            Double hasPay = studentFeeStageServiceImpl.findSumPayed(id);
            payMaster.setHasPay(hasPay);
            List<StudentFeeStage> stages = studentFeeStageServiceImpl.findUnPayed(id);
            Double unPay = 0.0;
            for (StudentFeeStage stage : stages) {
                if (stage != null && stage.getStageFee() != null) {
                    unPay += stage.getStageFee();
                }
            }
            payMaster.setUnPay(unPay);
            Student student = studentServiceImpl.findStudentById(payMaster.getStuId());
            model.addAttribute("student", student);
            model.addAttribute("payMaster", payMaster);
        }
        model.addAttribute("lable", lable);
        return "student/transaction/detail";
    }

    /**
     *
     * Class Name: StudentController.java
     *
     * @Description: 异动详情页面
     * @author 权飞虎
     * @date 2015年5月4日 下午7:59:51
     * @modifier
     * @modify-date 2015年5月4日 下午7:59:51
     * @version 1.0
     * @return
     */
    @RequestMapping(value = "/full/selectEntryDetail", method = RequestMethod.POST)
    public String selectFullEntryDetail(Integer payMasterId, Model model, String lable) {
        StudentPayMaster payMaster = studentPayMasterServiceImpl.findStudentPayMasterById(payMasterId);
        Integer id = payMaster.getId();
        Double hasPay = studentFeeStageServiceImpl.findSumPayed(id);
        payMaster.setHasPay(hasPay);
        List<StudentFeeStage> stages = studentFeeStageServiceImpl.findUnPayed(id);
        Double unPay = 0.0;
        for (StudentFeeStage stage : stages) {
            if (stage != null && stage.getStageFee() != null) {
                unPay += stage.getStageFee();
            }
        }
        payMaster.setUnPay(unPay);
        Student student = studentServiceImpl.findStudentById(payMaster.getStuId());
        model.addAttribute("student", student);
        model.addAttribute("payMaster", payMaster);
        model.addAttribute("lable", lable);
        return "student/transaction/full/detail";
    }

    @RequestMapping(value = "update2", method = RequestMethod.POST)
    public String update2(Student student, Model model) {
        studentServiceImpl.update(student);
        if (null != student.getId()) {
            student = studentServiceImpl.findStudentById(student.getId());
        }
        model.addAttribute("student", student);
        return "/student/transaction/student";
    }

    /**
     *
     * Class Name: StudentController.java
     *
     * @Description: 异动加载版型
     * @author 权飞虎
     * @date 2015年5月5日 下午9:06:47
     * @modifier
     * @modify-date 2015年5月5日 下午9:06:47
     * @version 1.0
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "selectClassType")
    public String selectClassType(Model model, Integer id) {
        Integer companyId = WebUtils.getCurrentCompanyId();
        // Student student = studentServiceImpl.findMessageByMobile(mobile);
        StudentPayMaster payMaster = studentPayMasterServiceImpl.findStudentPayMasterById(id);
        model.addAttribute("payMaster", payMaster);

        // 查询所报所有班型
        if (null != payMaster && null != payMaster.getStuId()) {
            ClassType classType = classTypeServiceImpl.findClassTypeByStuId(payMaster.getStuId(), companyId, payMaster.getId());
            // 查询班型所对应的一级项目和二级项目
            // 查询班型所对应的一级项目和二级项目
            if (null != classType) {
                Integer oneId = classType.getItemOneId();
                Integer secondId = classType.getItemSecondId();
                SysConfigItem oneItem = sysConfigItemServiceImpl.findSysConfigItemById(oneId);
                SysConfigItem secondItem = sysConfigItemServiceImpl.findSysConfigItemById(secondId);
                model.addAttribute("classType", classType);
                model.addAttribute("oneItem", oneItem);
                model.addAttribute("secondItem", secondItem);
            }
            // 优先上课校区
            Integer sch_id = payMaster.getSchoolId();
            SysConfigSchool schoo = sysConfigSchoolServiceImpl.findSysConfigSchoolById(sch_id);
            model.addAttribute("schoo", schoo);
            // 查询所有模块信息
            List<StudentPaySlaveVo> paySlaveList = studentPaySlaveServiceImpl.findStudentPaySlaveByPayMasterId(payMaster.getId());
            model.addAttribute("paySlaveList", paySlaveList);
        }

        // 查询出所属分校
        // String schoolsId = classType.getSchoolsId();
        // String[] schoolIds = schoolsId.split(",");
        // List<SysConfigSchool> schoolList = new ArrayList<SysConfigSchool>();
        // for (String schoolId : schoolIds) {
        // SysConfigSchool school =
        // sysConfigSchoolServiceImpl.findSysConfigSchoolById(Integer.parseInt(schoolId));
        // schoolList.add(school);
        // }
        // //所属分校
        // model.addAttribute("schoolList", schoolList);
        /*
         * //查询课程所对应模块 List<ClassModule> classModuleList =
         * classModuleServiceImpl.findByClassTypeId(classType.getId()); Integer
         * classHour=0; for (ClassModule classModule : classModuleList) {
         * Integer totalClassHour =classModule.getTotalClassHour();
         * classHour=classHour+totalClassHour; } model.addAttribute("classHour",
         * classHour);
         */
        // model.addAttribute("payMaster", list.get(0));
        return "/student/transaction/classType";
    }

    @RequestMapping(value = "loadStudent", method = RequestMethod.POST)
    public String loadStudent(Model model, String mobile) {
        Student student = studentServiceImpl.findMessageByMobile(mobile, WebUtils.getCurrentCompanyId(), WebUtils.getCurrentSchoolId());
        model.addAttribute("student", student);

        return "/student/transaction/student";
    }

    @RequestMapping(value = "topMessage", method = RequestMethod.POST)
    public String topMessage(Integer payMasterId, Model model) {
        StudentPayMaster payMaster = studentPayMasterServiceImpl.findStudentPayMasterById(payMasterId);
        Integer id = payMaster.getId();
        Double hasPay = studentFeeStageServiceImpl.findSumPayed(id);
        payMaster.setHasPay(hasPay);
        List<StudentFeeStage> stages = studentFeeStageServiceImpl.findUnPayed(id);
        Double unPay = 0.0;
        for (StudentFeeStage stage : stages) {
            if (stage != null && stage.getStageFee() != null) {
                unPay += stage.getStageFee();
            }
        }
        payMaster.setUnPay(unPay);
        Student student = studentServiceImpl.findStudentById(payMaster.getStuId());
        model.addAttribute("student", student);
        model.addAttribute("payMaster", payMaster);
        return "/student/transaction/top";
    }

    @RequestMapping(value = "changeStudent", method = RequestMethod.POST)
    public String changeStudent(Integer payMasterId, Model model, String lable) {
        StudentPayMaster payMaster = studentPayMasterServiceImpl.findStudentPayMasterById(payMasterId);
        Integer id = payMaster.getId();
        Double hasPay = studentFeeStageServiceImpl.findSumPayed(payMasterId);
        payMaster.setHasPay(hasPay);
        List<StudentFeeStage> stages = studentFeeStageServiceImpl.findUnPayed(id);
        Double unPay = 0.0;
        for (StudentFeeStage stage : stages) {
            if (stage != null && stage.getStageFee() != null) {
                unPay += stage.getStageFee();
            }
        }
        payMaster.setUnPay(unPay);
        Student student = studentServiceImpl.findStudentById(payMaster.getStuId());
        model.addAttribute("student", student);
        model.addAttribute("payMaster", payMaster);
        model.addAttribute("lable", lable);
        // 查看机构开启的前台注册规则
        Integer companyId = WebUtils.getCurrentCompanyId();
        model.addAttribute("crc", getCrc(companyId));

        return "/student/transaction/changeStudent";
    }

    @RequestMapping(value = "/full/changeStudent", method = RequestMethod.POST)
    public String fullchangeStudent(Integer payMasterId, Model model, String lable) {
        StudentPayMaster payMaster = studentPayMasterServiceImpl.findStudentPayMasterById(payMasterId);
        Integer id = payMaster.getId();
        Double hasPay = studentFeeStageServiceImpl.findSumPayed(payMasterId);
        payMaster.setHasPay(hasPay);
        List<StudentFeeStage> stages = studentFeeStageServiceImpl.findUnPayed(id);
        Double unPay = 0.0;
        for (StudentFeeStage stage : stages) {
            if (stage != null && stage.getStageFee() != null) {
                unPay += stage.getStageFee();
            }
        }
        payMaster.setUnPay(unPay);
        Student student = studentServiceImpl.findStudentById(payMaster.getStuId());
        model.addAttribute("student", student);
        model.addAttribute("payMaster", payMaster);
        model.addAttribute("lable", lable);
        // 查看机构开启的前台注册规则
        Integer companyId = WebUtils.getCurrentCompanyId();
        model.addAttribute("crc", getCrc(companyId));

        return "/student/transaction/full/changeStudent";
    }

    public CompanyRegisterConfig getCrc(Integer companyId) {
        CompanyRegisterConfig crc = new CompanyRegisterConfig();
        crc.setCompanyId(WebUtils.getCurrentCompanyId());
        crc = companyRegisterConfigServiceImpl.queryByCompanyId(crc);
        return crc;
    }

    /**
     *
     * Class Name: StudentController.java
     *
     * @Description: 查找学生
     * @author admin
     * @date 2015年5月7日 下午12:39:48
     * @modifier
     * @modify-date 2015年5月7日 下午12:39:48
     * @version 1.0
     * @param mobile
     * @param model
     * @return
     */
    @RequestMapping(value = "findStudent", method = RequestMethod.POST)
    public String findStudent(String mobile, Model model) {
        Student student = studentServiceImpl.findMessageByMobile(mobile, WebUtils.getCurrentCompanyId(), WebUtils.getCurrentSchoolId());
        model.addAttribute("student", student);

        CompanyFunctionSet companyFunctionSet = new CompanyFunctionSet();
        companyFunctionSet.setCompanyId(WebUtils.getCurrentCompanyId());
        companyFunctionSet.setFunctionCode("STUDENT_GROUP");
        List<CompanyFunctionSet> companyFunctionSetList = companyFunctionSetServiceImpl.findCompanyFunctionSetByPage(companyFunctionSet);
        if (companyFunctionSetList != null && companyFunctionSetList.size() > 0) {
            model.addAttribute("sgOpen", companyFunctionSetList.get(0).getStatus());
        }

        if (student == null) {
            model.addAttribute("mobile", mobile);
            return "/student/transaction/blankStudent";
        } else {
            if (null != student.getGroupOneId()) {
                StudentGroup g1 = studentGroupServiceImpl.findStudentGroupById(student.getGroupOneId());
                if (null != g1) {
                    model.addAttribute("G1Name", g1.getGroupName());
                }
            }
            if (null != student.getGroupTwoId()) {
                StudentGroup g2 = studentGroupServiceImpl.findStudentGroupById(student.getGroupTwoId());
                if (null != g2) {
                    model.addAttribute("G2Name", g2.getGroupName());
                }
            }
            return "/student/transaction/ajaxStudent";
        }

    }

    // 目前不用
    @ResponseBody
    @RequestMapping(value = "toStudent", method = RequestMethod.POST)
    public String toStudent(Student student, Integer payMasterId) {
        StudentPayMaster payMaster = studentPayMasterServiceImpl.findStudentPayMasterById(payMasterId);
        if (student.getId() == null) {
            student.setDeleteFlag(0);
            student.setCompanyId(WebUtils.getCurrentCompanyId());
            studentServiceImpl.insert(student);
            Student stu = studentServiceImpl.findMessageByMobile(student.getMobile(), WebUtils.getCurrentCompanyId(), WebUtils.getCurrentSchoolId());
            payMaster.setStuId(stu.getId());
            studentPayMasterServiceImpl.update(payMaster);
        } else {
            payMaster.setStuId(student.getId());
            studentPayMasterServiceImpl.update(payMaster);
        }

        return null;
    }

    @RequestMapping(value = "/transaction/{page}")
    public String toPage(@PathVariable String page, Model model, Integer payMasterId, String lable) {
        StudentPayMaster payMaster = studentPayMasterServiceImpl.findStudentPayMasterById(payMasterId);
        Integer id = payMaster.getId();
        Double hasPay = studentFeeStageServiceImpl.findSumPayed(payMasterId);
        payMaster.setHasPay(hasPay);
        List<StudentFeeStage> stages = studentFeeStageServiceImpl.findUnPayed(id);
        Double unPay = 0.0;
        for (StudentFeeStage stage : stages) {
            if (stage != null && stage.getStageFee() != null) {
                unPay += stage.getStageFee();
            }
        }
        payMaster.setUnPay(unPay);
        Student student = studentServiceImpl.findStudentById(payMaster.getStuId());
        model.addAttribute("student", student);
        model.addAttribute("payMaster", payMaster);
        model.addAttribute("lable", lable);
        return "/student/transaction/" + page;
    }

    @RequestMapping(value = "/transaction/full/{page}")
    public String toFullPage(@PathVariable String page, Model model, Integer payMasterId, String lable) {
        StudentPayMaster payMaster = studentPayMasterServiceImpl.findStudentPayMasterById(payMasterId);
        Integer id = payMaster.getId();
        Double hasPay = studentFeeStageServiceImpl.findSumPayed(payMasterId);
        payMaster.setHasPay(hasPay);
        List<StudentFeeStage> stages = studentFeeStageServiceImpl.findUnPayed(id);
        Double unPay = 0.0;
        for (StudentFeeStage stage : stages) {
            if (stage != null && stage.getStageFee() != null) {
                unPay += stage.getStageFee();
            }
        }
        payMaster.setUnPay(unPay);
        Student student = studentServiceImpl.findStudentById(payMaster.getStuId());
        model.addAttribute("student", student);
        model.addAttribute("payMaster", payMaster);
        model.addAttribute("lable", lable);
        return "/student/transaction/full/" + page;
    }

    @RequestMapping(value = "changeClass", method = RequestMethod.POST)
    public String changeClass(Integer payMasterId, Model model) {
        StudentPayMaster payMaster = studentPayMasterServiceImpl.findStudentPayMasterById(payMasterId);
        Integer id = payMaster.getId();
        Double hasPay = studentFeeStageServiceImpl.findSumPayed(payMasterId);
        payMaster.setHasPay(hasPay);
        List<StudentFeeStage> stages = studentFeeStageServiceImpl.findUnPayed(id);
        Double unPay = 0.0;
        for (StudentFeeStage stage : stages) {
            if (stage != null && stage.getStageFee() != null) {
                unPay += stage.getStageFee();
            }
        }
        payMaster.setUnPay(unPay);
        model.addAttribute("payMaster", payMaster);
        return null;
    }

    /**
     *
     * Class Name: StudentController.java
     *
     * @Description: 根据班号id 查询
     * @author 周文斌
     * @date 2015-5-15 下午7:29:35
     * @version 1.0
     * @param model
     * @param noId
     * @return
     */
    @RequestMapping("/selByNoId")
    public String selByNoId(HttpServletRequest request, Model model, StuVo stuVo) {
        stuVo.setPageSize(10);
        stuVo.setCompanyId(WebUtils.getCurrentCompanyId());
        stuVo.setSchoolId(WebUtils.getCurrentUserSchoolId(request));

        List<StuVo> stuVoList = studentServiceImpl.findStuByNoId(stuVo);

        // 查询总数
        Integer count = studentServiceImpl.findCountByNoId(stuVo);

        PageFinder<StuVo> stuPage = new PageFinder<StuVo>(stuVo.getPage(), stuVo.getPageSize(), count, stuVoList);

        model.addAttribute("noId", stuVo.getResourceId());
        model.addAttribute("stuPage", stuPage);
        return "classes/selStudent";
    }

    /**
     *
     * Class Name: StudentController.java
     *
     * @Description: 学院通知 列表
     * @author 周文斌
     * @date 2015-6-2 下午5:40:18
     * @version 1.0
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/notice")
    public String notice(Model model, HttpServletRequest request) {
        // 查询一级项目
        Integer companyId = WebUtils.getCurrentCompanyId();
        Integer schoolId = WebUtils.getCurrentUserSchoolId(request);

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("companyId", companyId);
        param.put("schoolId", schoolId);
        param.put("itemType", 1);
        param.put("functionCode", "COMPANY_FUNCTION_COURSE");

        // 查询多课程支持
        Integer status = studentServiceImpl.findClassMore(param);

        model.addAttribute("classMoreStatus", status);
        // 根据公司id 和学校id 查询 一级项目
        //List<SysConfigItem> oneItem = sysConfigItemServiceImpl.findItemBySchoolCompanyId(param);
        List<SysConfigItemRelation> allItem = sysConfigItemRelationServiceImpl.findAllItemFront(WebUtils.getCurrentCompanyId());
        List<SysConfigItemRelation> oneItem = new ArrayList<>();
        List<SysConfigItemRelation> twoItem = new ArrayList<>();
        List<SysConfigItemRelation> threeItem = new ArrayList<>();
        for(SysConfigItemRelation relation :allItem){
            if(relation.getLevel()==0){
                oneItem.add(relation);
            }else
            if(relation.getLevel()==1){
                twoItem.add(relation);
            }else
            if(relation.getLevel()==2){
                threeItem.add(relation);
            }
        }
        model.addAttribute("oneItem", oneItem);
        model.addAttribute("twoItem", twoItem);
        model.addAttribute("threeItem", threeItem);
        String goAffiche = request.getParameter("goAffiche");
        if(StringUtils.isNotBlank(goAffiche)){
        	model.addAttribute("goAffiche", goAffiche);
        }
        return "student/notice/notice";
    }

    @RequestMapping("/createNotice")
    public String createNotice(Model model, HttpServletRequest request) {
        // 根据公司 和 学校 查询 一级项目
        Integer companyId = WebUtils.getCurrentCompanyId();
        Integer schoolId = WebUtils.getCurrentUserSchoolId(request);

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("companyId", companyId);
        param.put("schoolId", schoolId);
        param.put("itemType", 1);
        param.put("functionCode", "COMPANY_FUNCTION_COURSE");

        // 查询多课程支持
        Integer status = studentServiceImpl.findClassMore(param);

        model.addAttribute("classMoreStatus", status);

        List<SysConfigItemRelation> relations = sysConfigItemRelationServiceImpl.findItemFront(new SysConfigItemRelation());
        SysConfigItem item = new SysConfigItem();
        item.setCompanyId(WebUtils.getCurrentCompanyId());
        item.setSchoolId( WebUtils.getCurrentUserSchoolId(request));
        item.setItemType("2");
        List<SysConfigItem> names = sysConfigItemServiceImpl.findByParentCode(item);
        for(SysConfigItemRelation re : relations) {
            for (SysConfigItem name : names) {
                if (re.getItemCode().equals(name.getItemCode())) {
                    re.setItemName(name.getItemName());
                    break;
                }
            }
        }



//        List<SysConfigItem> item = sysConfigItemServiceImpl.findItemBySchoolCompanyId(param);

        // 查询 公司服务

        CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
        CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);

        Integer msgCount = (cms.getMessageTotal() + cms.getGiveMessage() - css.getMessageSend());
        Integer emailCount = (cms.getEmailTotal() + cms.getGiveEmail() - css.getEmailSend());
        model.addAttribute("count", msgCount);
        model.addAttribute("emailCount", emailCount);
        model.addAttribute("oneItem", relations);
        String afficheFlag = request.getParameter("addAffiche");
        if(StringUtils.equals(afficheFlag, "addAffiche")){
        	String afficheId = request.getParameter("afficheId");
        	if(StringUtils.isNotBlank(afficheId)){
        		CompanyOrgMessageVo msg = companyServiceImpl.queryMessageById(Integer.parseInt(afficheId));
        		model.addAttribute("msg", msg);
            }
        	return "student/notice/addAffiche";// 学员公告添加页面
        }else{
        	return "student/notice/createNotice";
        }
        
    }

    @RequestMapping("/createWeixin")
    public String createWeixin(Model model, HttpServletRequest request) {
        List<SysConfigItemRelation> relations = sysConfigItemRelationServiceImpl.findItemFront(new SysConfigItemRelation());
        SysConfigItem item = new SysConfigItem();
        item.setCompanyId(WebUtils.getCurrentCompanyId());
        item.setSchoolId( WebUtils.getCurrentUserSchoolId(request));
        item.setItemType("2");
        List<SysConfigItem> names = sysConfigItemServiceImpl.findByParentCode(item);
        for(SysConfigItemRelation re : relations) {
            for (SysConfigItem name : names) {
                if (re.getItemCode().equals(name.getItemCode())) {
                    re.setItemName(name.getItemName());
                    break;
                }
            }
        }

        model.addAttribute("oneItem", relations);

        SysConfigDict sysConfigDict = new SysConfigDict();
        sysConfigDict.setDictCode("EDU_STEP");
        List<SysConfigDict> sysConfigDictList = sysConfigDictServiceImpl.queryConfigDictListByDictCode(sysConfigDict);
        model.addAttribute("steps", sysConfigDictList);
        //年份列表
        List<Integer> years = new ArrayList<Integer>();
        int curYear = DateUtil.getCurYear();
        for(int year = 0;year<12;year++){
            years.add(curYear-year);
        }
        model.addAttribute( "years", years);
        return "student/notice/createWeixin";

    }

    /**
     *
     * Class Name: StudentController.java
     **
     * @Description: 添加学员
     * @author 杨延博
     * @date 2015-10-8 下午5:40:18
     * @version 1.0
     * @param Student
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/insert")
    public String insertStudent(HttpServletRequest request, Student student, Boolean isUserFront, Model model) {
        log_student.info("===");
        // 查询公司注册设置
        CompanyRegisterConfig crc = new CompanyRegisterConfig();
        crc.setCompanyId(WebUtils.getCurrentCompanyId());
        crc = companyRegisterConfigServiceImpl.queryByCompanyId(crc);
        // 插入学员数据
        String result = "";
        String password = student.getMobile();
        if (null != password && !"".equals(password)) {
            student.setPassword(new Md5Hash(password.substring(password.length() - 6)).toHex());
        } else {
            student.setPassword(new Md5Hash("111111").toHex());
        }
        student.setSchoolId(WebUtils.getCurrentSchoolId());
        student.setCreator(WebUtils.getCurrentUserId(request));
        student.setCompanyId(WebUtils.getCurrentCompanyId());
        if (null != crc.getUsernameFlag() && crc.getUsernameFlag() == 1 && null != crc.getMobileFlag() && crc.getMobileFlag() == 1) {
            student.setAddFlag("all");
        } else if (null != crc.getUsernameFlag() && crc.getUsernameFlag() == 1 && null != crc.getMobileFlag() && crc.getMobileFlag() != 1) {
            student.setAddFlag("username");
        } else {
            student.setAddFlag("mobile");
        }

        result = studentServiceImpl.insertSupportExcel(student, isUserFront);
        Integer stuId = student.getId();
        try {
            Class clazz = Class.forName("com.yuxin.wx.model.student.Student");
            Object stu = clazz.newInstance();
            Field[] fields = clazz.getDeclaredFields();
            Map<String, String[]> map = request.getParameterMap();
            Set<Entry<String, String[]>> sets = map.entrySet();
            Integer row = 0;
            for (Entry<String, String[]> entry : sets) {
                if ("id".equals(entry.getKey()))
                    continue;
                String value = "";
                for (String v : entry.getValue()) {
                    value = v;
                }
                // System.out.println(entry.getKey());
                int n = 0;
                for (Field field : fields) {
                    if (field.getName().equals(entry.getKey()) || "isUserFront".equals(entry.getKey())) {
                        n = 0;
                        break;
                    } else {
                        n++;
                        continue;
                    }
                }

                if (n > 0) {
                    // 往纵向表添加数据
                    LongitudinalTableData check = new LongitudinalTableData();
                    check.setColName("stu_id");
                    check.setColValue(stuId + "");
                    check.setCompanyId(WebUtils.getCurrentCompanyId());
                    check.setTableName("student");
                    List<LongitudinalTableData> ck = longitudinalTableDataServiceImpl.query(check);
                    if (ck != null && ck.size() > 0) {
                        // 说明已经有数据了
                        row = ck.get(0).getRowId();
                    }
                    for (String v : entry.getValue()) {
                        value = v;
                    }

                    LongitudinalTableData data = new LongitudinalTableData();
                    data.setColName(entry.getKey());
                    data.setRowId(row);
                    data.setCompanyId(WebUtils.getCurrentCompanyId());
                    data.setTableName("student");
                    List<LongitudinalTableData> ld = longitudinalTableDataServiceImpl.query(data);
                    if (ld != null && ld.size() > 0) {
                        LongitudinalTableData d = ld.get(0);
                        d.setColValue(value);
                        longitudinalTableDataServiceImpl.update(d);
                    } else {
                        if (row == 0) {
                            data.setRowId(null);
                            row = longitudinalTableDataServiceImpl.getCurrtRow(data);
                            if (row == null) {
                                row = 1;
                            }
                        }
                        data.setSchoolId(WebUtils.getCurrentSchoolId());
                        data.setColValue(value);
                        data.setRowId(row);
                        longitudinalTableDataServiceImpl.insert(data);
                    }

                    // 插入id、stu_id、userfront_id
                    List<LongitudinalTableData> check2 = new ArrayList<LongitudinalTableData>();
                    LongitudinalTableData d = new LongitudinalTableData();
                    d.setCompanyId(WebUtils.getCurrentCompanyId());
                    d.setTableName("student");
                    d.setColName("id");
                    d.setRowId(row);
                    check2 = longitudinalTableDataServiceImpl.query(d);
                    if (check2 == null || check2.size() == 0) {
                        Integer id = longitudinalTableDataServiceImpl.getSequence();
                        d.setColValue(id + "");
                        d.setSchoolId(WebUtils.getCurrentSchoolId());
                        longitudinalTableDataServiceImpl.insert(d);
                    }
                    d.setSchoolId(null);
                    d.setColName("stu_id");
                    d.setColValue(stuId + "");
                    check2.clear();
                    check2 = longitudinalTableDataServiceImpl.query(d);
                    if (check == null || check2.size() == 0) {
                        d.setSchoolId(WebUtils.getCurrentSchoolId());
                        longitudinalTableDataServiceImpl.insert(d);
                    }

                }

            }

        } catch (Exception e) {
            log_student.error(">>> [添加学员] " + "状态：存纵向表失败" + ", 信息：" + "公司ID = " + WebUtils.getCurrentCompanyId() + ", 操作人ID = "
                    + WebUtils.getCurrentUserId(request) + ", 学生ID = " + student.getId(), e);
            e.printStackTrace();
        }
        log_student.info(">>> [添加学员] " + "状态：success" + ", 信息：" + "公司ID = " + WebUtils.getCurrentCompanyId() + ", 操作人ID = " + WebUtils.getCurrentUserId(request)
                + ", 学生ID = " + student.getId());
        return result;
    }

    // -----------------------学员管理-------------------------
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
    public PageFinder2<StudentListVo> queryStudentsListData(StudentListVo search) {
        String flag = "";
        search.setCompanyId(WebUtils.getCurrentCompanyId());
        // 分页调整
        if (search.getPageSize() == 12) {
            search.setPageSize(10);
        }
        // 代报考
        Subject subject = SecurityUtils.getSubject();
        if (subject.isPermitted("student_agent")) {
            flag = "1";
        }
        search.setAgentFlag(flag);

        if (subject.hasRole("代理机构")) {
            search.setProxyOrgId(WebUtils.getCurrentUser().getProxyOrgId());
        }
        PageFinder2<StudentListVo> pageFinder = studentServiceImpl.findStudentsList(search);
        return pageFinder;
    }

    @ResponseBody
    @RequestMapping(value = "/queryStudentsList1")
    public PageFinder<StudentListVo> exportDatas(StudentListVo search, HttpServletRequest request) {
        search.setCompanyId(WebUtils.getCurrentCompanyId());
        search.setUserId(WebUtils.getCurrentUserId(request));
        search.setPageSize(50);
        PageFinder<StudentListVo> pageFinder = studentServiceImpl.findStudentsList1(search);
        return pageFinder;
    }

    /**
     *
     * @Description: 跳转到导入学员列表页
     * @author zhang.zx
     */
    @RequestMapping(value = "/importPage")
    public String importStusPage(Model model) {
        CompanyFunctionSet companyFunctionSet = new CompanyFunctionSet();
        companyFunctionSet.setCompanyId(WebUtils.getCurrentCompanyId());
        companyFunctionSet.setFunctionCode("STUDENT_GROUP");
        List<CompanyFunctionSet> companyFunctionSetList = companyFunctionSetServiceImpl.findCompanyFunctionSetByPage(companyFunctionSet);
        if (companyFunctionSetList != null && companyFunctionSetList.size() > 0) {
            model.addAttribute("sgOpen", companyFunctionSetList.get(0).getStatus());
        }
        return "student/importStudents";
    }

    /**
     *
     * Class Name: StudentController.java
     *
     * @Description: excle模板文件下载
     * @author zhang.zx
     * @date 2015年10月8日 下午11:28:52
     * @modifier
     * @modify-date 2015年10月8日 下午11:28:52
     * @version 1.0
     * @param response
     * @return
     */
    @RequestMapping("/download")
    public String downloadexcleTemplete(HttpServletResponse response, HttpServletRequest request) {
        String path = request.getSession().getServletContext().getRealPath("/") + "user_example.xls";
        File file = new File(path);
        response.setHeader("Content-Disposition", "attachment; filename=user_example.xls");
        response.addHeader("Content-Length", "" + file.length());
        response.setContentType("application/octet-stream;charset=UTF-8");
        InputStream in = null;
        OutputStream toClient = null;
        try {
            // 以流的形式下载文件
            in = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer = new byte[in.available()];
            in.read(buffer);
            in.close();
            toClient = new BufferedOutputStream(response.getOutputStream());
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (Exception e) {
            log_student.error("文件下载失败", e);
        }
        return null;
    }

    /**
     *
     * Class Name: StudentController.java
     *
     * @Description: 校验学员列表信息
     * @author zhang.zx
     * @date 2015年10月8日 下午12:15:42
     * @modifier
     * @modify-date 2015年10月8日 下午12:15:42
     * @version 1.0
     * @param multiPartRquest
     * @param req
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/validateData")
    public JSONObject validateStudentData(MultipartRequest multiPartRquest, HttpServletRequest req) {
        Integer userId = WebUtils.getCurrentUserId(req);
        JSONObject json = new JSONObject();
        List<String> errorList = new ArrayList<String>();
        MultipartFile multipartFile = multiPartRquest.getFile("imgData");
        String name = multipartFile.getOriginalFilename();
        if (null != name && !"".equals(name)) {
            String suff = name.substring(name.lastIndexOf("."));
            if (!".xls".equals(suff) && !".xlsx".equals(suff)) {
                json.put("teletmperror", "文件格式不正确，请下载标准模板！");
            }
            String newFileName = FileUploadUtil.getlnstance().getNewFileName(name);
            File file1 = new File(propertiesUtil.getExclePath() + userId + "/");
            File file = new File(propertiesUtil.getExclePath() + userId + "/" + newFileName);
            try {
                if (!file1.exists()) {
                    file.mkdirs();
                }
                multipartFile.transferTo(file);
                try {
                    errorList = validDatas(propertiesUtil.getExclePath() + userId + "/" + newFileName);
                } catch (Exception e) {
                    log_student.error("模板不正确", e);
                    e.printStackTrace();
                    json.put("teletmperror", "模板错误,请先下载标准模板!");
                }
            } catch (IOException e) {
                log_student.error("文件上传失败", e);
                e.printStackTrace();
            }
            json.put("exclePath", propertiesUtil.getExclePath() + userId + "/" + newFileName);
        }
        json.put("error", errorList);
        return json;
    }

    // 校验数据
    private List<String> validDatas(String path) throws Exception {
        List<String> errorList = new ArrayList<String>();
        try {
            List<List<String>> list = ImportExcl.read(path);
            if (list != null) {
                for (int i = 1; i < list.size(); i++) {
                    List<String> cellList = list.get(i);
                    Student s = new Student();
                    s.setMobile(cellList.get(0));
                    s.setName(cellList.get(1));
                    s.setIdentityId(cellList.get(2)); // 身份证号
                    s.setEmail(cellList.get(3));
                    s.setQq(cellList.get(4));
                    s.setEmergencyContact(cellList.get(5));
                    s.setEmergencyPhone(cellList.get(6));
                    s.setUsername(cellList.get(8));
                    s.setIdentityTypeCode("ID_IDCARD");
                    s.setCompanyId(WebUtils.getCurrentCompanyId());
                    // s.setSchoolId(WebUtils.getCurrentSchoolId());
                    if ("".equals(cellList.get(0))) {
                        String str = "第" + (i + 1) + "行中手机号为空!";
                        errorList.add(str);
                    } else {
                        if (!ParameterUtil.isMobilePhone(cellList.get(0))) {
                            String str = "第" + (i + 1) + "行中手机号不正确!";
                            errorList.add(str);
                        } else {
                            String code = studentServiceImpl.querySupportExcel(s);
                            if ("0103".equals(code)) {
                                String str = "第" + (i + 1) + "行中手机号已存在!";
                                errorList.add(str);
                            }
                        }
                    }
                    if ("".equals(cellList.get(1))) {
                        String str = "第" + (i + 1) + "行中姓名为空!";
                        errorList.add(str);
                    }
                    if (!"".equals(cellList.get(2))) {
                        // 身份证号
                        if (!ParameterUtil.isNum(cellList.get(2)) || cellList.get(2).length() < 15) {
                            String str = "第" + (i + 1) + "行中身份证号不正确!";
                            errorList.add(str);
                        } else {
                            String code = studentServiceImpl.querySupportExcel(s);
                            if ("0303".equals(code)) {
                                String str = "第" + (i + 1) + "行中身份证号已存在!";
                                errorList.add(str);
                            }
                        }
                    }
                    if (!"".equals(cellList.get(3))) {
                        // 邮箱
                        if (!ParameterUtil.isEmail(cellList.get(3))) {
                            String str = "第" + (i + 1) + "行中邮箱不正确!";
                            errorList.add(str);
                        }
                        Student check = new Student();
                        check.setEmail(cellList.get(3));
                        check.setCompanyId(WebUtils.getCurrentCompanyId());
                        List<Student> lt = studentServiceImpl.checkUserInfo(check);
                        if (null != lt && lt.size() > 0) {
                            String str = "第" + (i + 1) + "行中邮箱已存在!";
                            errorList.add(str);
                        }
                    }
                    if (!"".equals(cellList.get(4))) {
                        // QQ号
                        if (!ParameterUtil.isNum(cellList.get(4)) || cellList.get(4).length() > 10) {
                            String str = "第" + (i + 1) + "行中QQ号不正确!";
                            errorList.add(str);
                        }
                    }
                    if (!"".equals(cellList.get(6))) {
                        // 紧急联系人电话
                        if (!ParameterUtil.isMobilePhone(cellList.get(6))) {
                            String str = "第" + (i + 1) + "行中紧急联系人电话不正确!";
                            errorList.add(str);
                        }
                    }
                    // 验证用户名
                    if (!"".equals(cellList.get(8))) {
                        String str = "第" + (i + 1) + "行中用户名为空!";
                        errorList.add(str);
                    } else {

                    }
                }
            }
        } catch (Exception e) {
            log_student.error("模板不正确", e);
            throw new Exception("模板不正确");
        }
        return errorList;
    }

    /**
     *
     * Class Name: StudentController.java
     *
     * @Description: 导入学员信息
     * @author zhang.zx
     * @date 2015年10月8日 下午4:34:10
     * @modifier
     * @modify-date 2015年10月8日 下午4:34:10
     * @version 1.0
     * @param filePath
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/insertStudents")
    public String insertExcleStudents(String filePath, String mark, HttpServletRequest request, String groupOneId, String groupTwoId) {
        try {
            log_student.info("===");
            // 查询公司注册设置
            CompanyRegisterConfig crc = new CompanyRegisterConfig();
            crc.setCompanyId(WebUtils.getCurrentCompanyId());
            crc = companyRegisterConfigServiceImpl.queryByCompanyId(crc);
            List<Student> arr = new ArrayList<Student>();
            List<List<String>> list = ImportExcl.read(filePath);
            if (list != null) {
                for (int i = 1; i < list.size(); i++) {
                    List<String> cellList = list.get(i);
                    // 封装数据
                    Student s = new Student();
                    s.setMobile(cellList.get(0));
                    s.setName(cellList.get(1));
                    s.setIdentityId(cellList.get(2)); // 身份证号
                    s.setEmail(cellList.get(3));
                    s.setQq(cellList.get(4));
                    s.setEmergencyContact(cellList.get(5));
                    s.setEmergencyPhone(cellList.get(6));
                    if (!"".equals(cellList.get(0))) {
                        if (!ParameterUtil.isMobilePhone(cellList.get(0))) {
                            return "error";
                        }
                    }
                    if (!"".equals(cellList.get(7))) {
                        s.setPassword(new Md5Hash(cellList.get(7)).toHex());
                    } else {
                        if ("".equals(cellList.get(0))) {
                            s.setPassword(new Md5Hash("111111").toHex());
                        } else {
                            s.setPassword(new Md5Hash(cellList.get(0).substring(5, 11)).toHex());
                        }
                    }
                    if (!"".equals(cellList.get(8))) {
                        if (!ParameterUtil.isUserName(cellList.get(8))) {
                            return "error";
                        }
                        s.setUsername(cellList.get(8));
                    }
                    if (null != cellList.get(2) && !"".equals(cellList.get(2))) {
                        s.setIdentityTypeCode("ID_IDCARD");
                    }
                    if (null != crc) {
                        if (null != crc.getMobileFlag() && crc.getMobileFlag() == 1 && "".equals(cellList.get(0))) {
                            return "error";
                        }
                        if (null != crc.getUsernameFlag() && crc.getUsernameFlag() == 1 && "".equals(cellList.get(8))) {
                            return "error";
                        }
                        if (null != crc.getUsernameFlag() && crc.getUsernameFlag() == 1 && null != crc.getMobileFlag() && crc.getMobileFlag() == 1) {
                            s.setAddFlag("all");
                        } else if (null != crc.getUsernameFlag() && crc.getUsernameFlag() == 1 && null != crc.getMobileFlag() && crc.getMobileFlag() != 1) {
                            s.setAddFlag("username");
                        } else {
                            s.setAddFlag("mobile");
                        }
                    }
                    s.setCompanyId(WebUtils.getCurrentCompanyId());
                    s.setSchoolId(WebUtils.getCurrentSchoolId());
                    s.setCreator(WebUtils.getCurrentUserId(request));
                    if (null != groupOneId && !"".equals(groupOneId)) {
                        s.setGroupOneId(Integer.parseInt(groupOneId));
                    }
                    if (null != groupTwoId && !"".equals(groupTwoId)) {
                        s.setGroupTwoId(Integer.parseInt(groupTwoId));
                    }

                    SelectStudentOrUsersfrontVo search = new SelectStudentOrUsersfrontVo();
                    search.setCompanyId(WebUtils.getCurrentCompanyId());
                    if (s.getMobile() != null && !"".equals(s.getMobile())) {
                        search.setMobile(s.getMobile());
                    }
                    if (s.getUsername() != null && !"".equals(s.getUsername())) {
                        search.setUsername(s.getUsername());
                    }

                    Integer usersfrontCount = usersFrontServiceImpl.findUsersfrontCountByMobileOrUsername(search);
                    Integer studentCount = studentServiceImpl.findStudentCountOnlyByMobile(search);

                    if ("update".equals(mark)) {
                        if (usersfrontCount <= 1 && studentCount <= 1) {
                            arr.add(s);
                        }
                    } else {
                        if (usersfrontCount == 0 && studentCount == 0) {
                            arr.add(s);
                        }
                    }

                }
            }

            for (int i = arr.size() - 1; i >= 0; i--) {
                for (int j = 0; j < arr.size(); j++) {
                    if (arr.get(i) != null && arr.get(j) != null) {
                        int a = 0;
                        if (i == j)
                            continue;
                        if (arr.get(i).getMobile() != null && !"".equals(arr.get(i).getMobile()) && arr.get(j).getMobile() != null
                                && !"".equals(arr.get(j).getMobile()) && arr.get(i).getMobile().equals(arr.get(j).getMobile())) {
                            a++;
                        }
                        if (arr.get(i).getUsername() != null && !"".equals(arr.get(i).getUsername()) && arr.get(j).getUsername() != null
                                && !"".equals(arr.get(j).getUsername()) && arr.get(i).getUsername().equals(arr.get(j).getUsername())) {
                            a++;
                        }
                        if (a > 0) {
                            arr.set(i, null);
                        }
                    }
                }
            }

            log_student.info(">>> [导入学员] " + "状态：success" + ", 信息：" + "公司ID = " + WebUtils.getCurrentCompanyId() + ", 操作人ID = "
                    + WebUtils.getCurrentUser().getId() + ", 学员List = num：" + arr.size() + " list：" + arr);
            return studentServiceImpl.importStuList(arr, mark, crc);
        } catch (Exception e) {
            log_student.error(">>> [导入学员] " + "状态：导入信息有误", e);
            e.printStackTrace();
            return "error";
        }
    }

    /**
     *
     * Class Name: StudentController.java
     *
     * @Description: 清空excle临时文件夹
     * @author zhang.zx
     * @date 2015年10月8日 下午5:17:37
     * @modifier
     * @modify-date 2015年10月8日 下午5:17:37
     * @version 1.0
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteFile")
    public String delteTempexcleFiles(HttpServletRequest request) {
        String url = propertiesUtil.getExclePath() + WebUtils.getCurrentUserId(request);
        File file = new File(url);
        if (file.exists()) { // 判断文件是否存在
            if (file.isFile()) { // 判断是否是文件
                file.delete(); // delete()方法 你应该知道 是删除的意思;
            } else if (file.isDirectory()) { // 否则如果它是一个目录
                File files[] = file.listFiles(); // 声明目录下所有的文件 files[];
                for (int i = 0; i < files.length; i++) { // 遍历目录下所有的文件
                    files[i].delete(); // 把每个文件 用这个方法进行迭代
                }
            }
            file.delete();
        }
        return JsonMsg.SUCCESS;
    }

    /**
     *
     * Class Name: StudentController.java
     *
     * @Description: 导出学员数据
     * @author zhang.zx
     * @date 2015年10月9日 上午11:18:44
     * @modifier
     * @modify-date 2015年10月9日 上午11:18:44
     * @version 1.0
     * @param model
     * @param search
     * @return
     */
    @RequestMapping(value = "/exportExcle")
    public ModelAndView exportStudentsExcle(Model model, StudentListVo search) {
        List<StudentListVo> al = new ArrayList<StudentListVo>();
        if (EntityUtil.isNotBlank(search)) {
            search.setCompanyId(WebUtils.getCurrentCompanyId());
            // search.setSchoolId(WebUtils.getCurrentSchoolId());
            search.setPageSize(20000);
            al = studentServiceImpl.findStudentsData(search);
        }
        List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
        // LongitudinalTableData sql=new LongitudinalTableData();
        // sql.setCompanyId(WebUtils.getCurrentCompanyId());
        // sql.setSchoolId(WebUtils.getCurrentSchoolId());
        // sql.setTableName("student");
        // List<LongitudinalTableData>
        // ns=longitudinalTableDataServiceImpl.query(sql);
        // List<LongitudinalTableData> ms= new
        // ArrayList<LongitudinalTableData>();
        // ms.addAll(ns);
        // log.info("自定义表记录["+ns.size()+"]条");
        Map<Integer, Map<String, Object>> customInfo = customStuInfo("stu_id");

        for (StudentListVo s : al) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("identityId", s.getIdentityId());
            map.put("birthday", s.getBirthday());
            map.put("age", s.getAge());
            map.put("username", s.getUsername());
            map.put("companyId", s.getCompanyId());
            map.put("deleteFlag", s.getDeleteFlag());
            map.put("educationCode", s.getEducationCode());
            map.put("email", s.getEmail());
            map.put("emergencyContact", s.getEmergencyContact());
            map.put("emergencyPhone", s.getEmergencyPhone());
            map.put("hkAddress", s.getHkAddress());
            map.put("identityId", s.getIdentityId());
            map.put("identityTypeCode", s.getIdentityTypeCode());
            map.put("mobile", s.getMobile());
            map.put("name", s.getName());
            map.put("sex", s.getSex());
            map.put("homePhone", s.getHomePhone());
            map.put("weixinId", s.getWeixinId());
            map.put("qq", s.getQq());
            map.put("province", s.getProvince());
            map.put("city", s.getCity());
            map.put("county", s.getCounty());
            map.put("addressDetail", s.getAddressDetail());
            String eduIdentity = "";
            if(s.getEduIdentity()!=null){
                if(s.getTeacherFlag()!=null && String.valueOf(s.getTeacherFlag()).equals("1")){
                    eduIdentity = "教师";
                }else{
                    eduIdentity = (String.valueOf(s.getEduIdentity()).equals("0"))?"学生":"普通用户";
                }
            }
            map.put("eduIdentity", eduIdentity);
            String area = "";
            String school = "";
            if(!StringUtils.isBlank(s.getEduArea())){
                area = s.getEduArea();
            }
            if(!StringUtils.isBlank(s.getEduSchool())){
                school = s.getEduSchool();
            }
            map.put("eduArea", area);
            map.put("eduSchool", school);
            if(!StringUtils.isBlank(s.getEduStep())){
                map.put("eduClass", s.getEduStep()+s.getEduYear()+"年"+s.getEduClass()+"班");
            }else{
                map.put("eduClass", "");
            }

            //
            // for(LongitudinalTableData n : ns){
            // if("stu_id".equals(n.getColName()) &&
            // (s.getId()==Integer.parseInt(n.getColValue()) ||
            // n.getColValue().equals(s.getId()))){
            // for(LongitudinalTableData v: ms){
            // int r=v.getRowId();
            // int rr=n.getRowId();
            // if(r==rr){
            // map.put(v.getColName(), v.getColValue());
            // }
            // }
            // }
            // }
            Map<String, Object> definedDataMap = customInfo.get(s.getId());
            if (definedDataMap != null)
                map.putAll(definedDataMap);
            lists.add(map);
        }
        StringBuffer title = new StringBuffer(
                "手机号:mobile,用户名:username,姓名:name,身份:eduIdentity,所在区域:eduArea,学校:eduSchool,所在班级:eduClass,邮箱:email,QQ号:qq,紧急联系人:emergencyContact,紧急联系人电话:emergencyPhone,省:province,市:city,区（县）:county,详细地址:addressDetail");
        List<LongitudinalTableColDefine> coldefine = longitudinalTableColDefineServiceImpl.findByCompany(WebUtils.getCurrentCompanyId(), "student");
        if (coldefine != null && coldefine.size() > 0) {
            for (LongitudinalTableColDefine d : coldefine) {
                if (!"id".equals(d.getColName()))
                    title.append("," + d.getColComment() + ":" + d.getColName());
            }
        }
        ViewFiles excel = new ViewFiles();
        HSSFWorkbook wb = new HSSFWorkbook();
        try {
            wb = ExcelUtil.newWorkbook(lists, "sheet1", title.toString());
        } catch (Exception ex) {

        }
        Map map = new HashMap();
        map.put("workbook", wb);
        map.put("fileName", "学员列表.xls");
        return new ModelAndView(excel, map);
    }

    /**
     *
     * Class Name: StudentController.java
     *
     * @Description: 验证手机号是否已存在
     * @author 杨延博
     * @date 2015年10月9日 下午5:17:37
     * @modifier
     * @modify-date 2015年10月8日 下午5:17:37
     * @version 1.0
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/checkMobileExist")
    public Boolean checkMobileExist(HttpServletRequest request, String mobile) {
        Student student = new Student();
        student.setMobile(mobile);
        student.setCompanyId(WebUtils.getCurrentCompanyId());
        Student result = studentServiceImpl.queryByMobile(student);
        if (result != null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     *
     * Class Name: StudentController.java
     *
     * @Description: 验证身份证是否已存在
     * @author 杨延博
     * @date 2015年10月9日 下午5:17:37
     * @modifier
     * @modify-date 2015年10月8日 下午5:17:37
     * @version 1.0
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/checkIdentityExist")
    public Boolean checkIdentityExist(HttpServletRequest request, String identityNum, String identityType) {
        Student student = new Student();
        if ("ID_IDCARD".equals(identityType)) {
            student.setIdentityTypeCode("ID_IDCARD");
            student.setIdentityId(identityNum);
            student.setCompanyId(WebUtils.getCurrentCompanyId());
            Student result = studentServiceImpl.queryByIdentityId(student);
            if (result != null) {
                return false;
            } else {
                return true;
            }
        }
        return true;
    }

    /**
     *
     * @fileName : StudentController.java
     * @date : 2015年10月14日 下午12:06:49
     * @author : 杨延博
     * @description :更新时校验身份证是否重复
     */
    @ResponseBody
    @RequestMapping(value = "/checkUpdateIdentityExist")
    public Boolean checkUpdateIdentityExist(HttpServletRequest request, String identityNum, String identityType, Integer id) {
        Student student = new Student();
        if ("ID_IDCARD".equals(identityType)) {
            student.setIdentityTypeCode("ID_IDCARD");
            student.setIdentityId(identityNum);
            student.setCompanyId(WebUtils.getCurrentCompanyId());
            Student result = studentServiceImpl.queryByIdentityId(student);
            if (result != null) {
                int id1 = result.getId();
                int id2 = studentServiceImpl.findStudentById(id).getId();
                if (id1 != id2) {
                    return false;
                } else {
                    return true;
                }
            } else {
                return true;
            }
        }
        return true;
    }

    /**
     *
     * Class Name: StudentController.java
     *
     * @Description: 查询身份证类型
     * @author 杨延博
     * @date 2015年10月9日 下午5:17:37
     * @modifier
     * @modify-date 2015年10月8日 下午5:17:37
     * @version 1.0
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryIdentityType")
    public List<SysConfigDict> queryIdentityType() {
        SysConfigDict sysConfigDict = new SysConfigDict();
        sysConfigDict.setDictCode("IDENTITY_TYPE");
        return sysConfigDictServiceImpl.queryConfigDictListByDictCode(sysConfigDict);
    }

    /**
     *
     * Class Name: StudentController.java
     *
     * @Description: 查询学历类型
     * @author 杨延博
     * @date 2015年10月9日 下午5:17:37
     * @modifier
     * @modify-date 2015年10月8日 下午5:17:37
     * @version 1.0
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryEducationType")
    public List<SysConfigDict> queryEducationType() {
        SysConfigDict sysConfigDict = new SysConfigDict();
        sysConfigDict.setDictCode("EDUCATION");
        return sysConfigDictServiceImpl.queryConfigDictListByDictCode(sysConfigDict);
    }

    /**
     *
     * Class Name: StudentController.java
     *
     * @Description: 修改用户密码
     * @author 杨延博
     * @date 2015年10月9日 下午5:17:37
     * @modifier
     * @modify-date 2015年10月8日 下午5:17:37
     * @version 1.0
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/changePassword")
    public String changePassword(Integer id, String password) {
        log_student.info("===");
        UsersFront userFront = new UsersFront();
        userFront.setId(id);
        userFront.setPassword(new Md5Hash(password).toHex());
        usersFrontServiceImpl.update(userFront);
        log_student.info(">>> [修改密码] " + "状态：success" + ", 信息：" + "公司ID = " + WebUtils.getCurrentCompanyId() + ", 操作人ID = " + WebUtils.getCurrentUser().getId()
                + ", 用户ID = " + id);
        return JsonMsg.SUCCESS;
    }

    @ResponseBody
    @RequestMapping(value = "/updateStatus")
    public String updateStatus(Integer userId, String status) {
        log_student.info("===");
        UsersFront userFront = new UsersFront();
        userFront.setId(userId);
        if ("1".equals(status)) {
            userFront.setStatus(0);
        } else if ("0".equals(status)) {
            userFront.setStatus(1);
        }
        usersFrontServiceImpl.update(userFront);
        log_student.info(">>> [学员启用/禁用] " + "状态：success" + ", 信息：" + "公司ID = " + WebUtils.getCurrentCompanyId() + ", 操作人ID = "
                + WebUtils.getCurrentUser().getId() + ", 用户ID = " + userId + ", userFront.status 更改为 = " + userFront.getStatus());
        return JsonMsg.SUCCESS;
    }

    /**
     *
     * Class Name: StudentController.java
     *
     * @Description: 查询Student
     * @author 杨延博
     * @date 2015年10月9日 下午5:17:37
     * @modifier
     * @modify-date 2015年10月8日 下午5:17:37
     * @version 1.0
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findById")
    public Student findById(Integer id) {
        return studentServiceImpl.findStudentById(id);
    }

    /**
     *
     * Class Name: StudentController.java
     *
     * @Description: 更新student
     * @author 杨延博
     * @date 2015年10月9日 下午5:17:37
     * @modifier
     * @modify-date 2015年10月8日 下午5:17:37
     * @version 1.0
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateStudent")
    public String updateStudent(HttpServletRequest request, Student student, Boolean isUserFront) {
        log_student.info("===");
        // String mobile = student.getMobile();
        String identityTypeCode = student.getIdentityTypeCode();
        String identityId = student.getIdentityId();
        String name = student.getName();
        String email = student.getEmail();
        String qq = student.getQq();
        String emergencyPhone = student.getEmergencyPhone();
        String emergencyName = student.getEmergencyContact();

        // 判断是否为身份证
        if ("ID_IDCARD".equals(identityTypeCode)) {
            if (!(identityId == null || "".equals(identityId))) {
                // 判断是否含有非数字
                if (!identityId.matches("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])")) {
                    return "0302";
                }
                // 判断字符串是否为15或者18
                if (identityId.trim().length() != 15 && identityId.trim().length() != 18) {
                    return "0302";
                }
                Student result = studentServiceImpl.queryByIdentityId(student);
                Student result2 = studentServiceImpl.findStudentById(student.getId());
                // 判断身份证号是否已存在
                if (result != null && result != result2) {
                    return "0303";
                }
            }
        }
        // 判断姓名是否合法
        if (name == null || "".equals(name)) {
            return "0201";
        }
        // if (!name.matches("([0-9A-Za-z]|[\\u4e00-\\u9fa5])+")) {
        // return "0202";
        // }
        // 判断email
        if (!(email == null || "".equals(email))) {
            if (!ParameterUtil.isEmail(email)) {
                return "0402";
            }
        }

        // 判断紧急联系人
        if (!(emergencyName == null || "".equals(emergencyName))) {
            if (!emergencyName.matches("([0-9A-Za-z]|[\\u4e00-\\u9fa5])+")) {
                return "0602";
            }
        }

        // 判断紧急联系人电话
        if (!(emergencyPhone == null || "".equals(emergencyPhone))) {
            // 判断紧急手机号格式是否正确
            if (!ParameterUtil.isMobilePhone(emergencyPhone)) {
                return "0702";
            }
        }
        student.setSchoolId(WebUtils.getCurrentSchoolId());
        student.setCompanyId(WebUtils.getCurrentCompanyId());
        student.setUpdator(WebUtils.getCurrentUserId(request));
        studentServiceImpl.update(student);

        try {
            Class clazz = Class.forName("com.yuxin.wx.model.student.Student");
            Object stu = clazz.newInstance();
            Field[] fields = clazz.getDeclaredFields();
            Map<String, String[]> map = request.getParameterMap();
            Set<Entry<String, String[]>> sets = map.entrySet();
            Integer row = 0;
            for (Entry<String, String[]> entry : sets) {
                if ("id".equals(entry.getKey()))
                    continue;
                String value = "";
                for (String v : entry.getValue()) {
                    value = v;
                }
                System.out.println(entry.getKey());
                int n = 0;
                for (Field field : fields) {
                    if (field.getName().equals(entry.getKey()) || "isUserFront".equals(entry.getKey())) {
                        n = 0;
                        break;
                    } else {
                        n++;
                        continue;
                    }
                }
                Integer stuId = student.getId();

                if (n > 0) {
                    // 往纵向表添加数据
                    LongitudinalTableData check = new LongitudinalTableData();
                    check.setColName("stu_id");
                    check.setColValue(stuId + "");
                    check.setCompanyId(WebUtils.getCurrentCompanyId());
                    check.setTableName("student");
                    List<LongitudinalTableData> ck = longitudinalTableDataServiceImpl.query(check);
                    if (ck != null && ck.size() > 0) {
                        // 说明已经有数据了
                        row = ck.get(0).getRowId();
                    }
                    for (String v : entry.getValue()) {
                        value = v;
                    }

                    LongitudinalTableData data = new LongitudinalTableData();
                    data.setColName(entry.getKey());
                    data.setRowId(row);
                    data.setCompanyId(WebUtils.getCurrentCompanyId());
                    data.setTableName("student");
                    List<LongitudinalTableData> ld = longitudinalTableDataServiceImpl.query(data);
                    if (ld != null && ld.size() > 0) {
                        LongitudinalTableData d = ld.get(0);
                        d.setColValue(value);
                        longitudinalTableDataServiceImpl.update(d);
                    } else {
                        if (row == 0) {
                            data.setRowId(null);
                            row = longitudinalTableDataServiceImpl.getCurrtRow(data);
                            if (row == 0) {
                                row = 1;
                            }
                        }
                        data.setSchoolId(WebUtils.getCurrentSchoolId());
                        data.setColValue(value);
                        data.setRowId(row);
                        longitudinalTableDataServiceImpl.insert(data);
                    }

                    // 插入id、stu_id、userfront_id
                    List<LongitudinalTableData> check2 = new ArrayList<LongitudinalTableData>();
                    LongitudinalTableData d = new LongitudinalTableData();
                    d.setCompanyId(WebUtils.getCurrentCompanyId());
                    d.setTableName("student");
                    d.setColName("id");
                    d.setRowId(row);
                    check2 = longitudinalTableDataServiceImpl.query(d);
                    if (check2 == null || check2.size() == 0) {
                        Integer id = longitudinalTableDataServiceImpl.getSequence();
                        d.setColValue(id + "");
                        d.setSchoolId(WebUtils.getCurrentSchoolId());
                        longitudinalTableDataServiceImpl.insert(d);
                    }
                    d.setSchoolId(null);
                    d.setColName("stu_id");
                    d.setColValue(null);
                    check2.clear();
                    check2 = longitudinalTableDataServiceImpl.query(d);
                    if (check == null || check2.size() == 0) {
                        d.setColValue(stuId + "");
                        d.setSchoolId(WebUtils.getCurrentSchoolId());
                        longitudinalTableDataServiceImpl.insert(d);
                    }

                }

            }

        } catch (Exception e) {
            log_student.error(">>> [学员更新] " + "状态：存纵向表失败" + ", 信息：" + "公司ID = " + WebUtils.getCurrentCompanyId() + ", 操作人ID = "
                    + WebUtils.getCurrentUserId(request) + ", 学生ID = " + student.getId(), e);
            e.printStackTrace();
        }
        student = studentServiceImpl.findStudentById(student.getId());
        if (isUserFront) {
            if (student != null && student.getUserId() == null) {
                CompanyRegisterConfig crc = new CompanyRegisterConfig();
                crc.setCompanyId(WebUtils.getCurrentCompanyId());
                crc = companyRegisterConfigServiceImpl.queryByCompanyId(crc);
                UsersFront usersFront = new UsersFront();
                Date nowTime = new Date();
                String password = student.getMobile();
                if (null != password && !"".equals(password)) {
                    student.setPassword(new Md5Hash(password.substring(password.length() - 6)).toHex());
                    usersFront.setMobile(student.getMobile());
                } else {
                    student.setPassword(new Md5Hash("111111").toHex());
                }
                usersFront.setPassword(student.getPassword());
                // usersFront.setMobile(mobile);
                usersFront.setEmail(student.getEmail());
                usersFront.setCompanyId(student.getCompanyId());
                usersFront.setVipFlag(0);
                usersFront.setStatus(1);
                usersFront.setSchoolId(student.getSchoolId());
                usersFront.setRegistTime(nowTime);
                usersFront.setRegistType(2);
                UsersFront isExists = new UsersFront();
                if (null != crc && null != crc.getUsernameFlag() && crc.getUsernameFlag() == 1 && crc.getMobileFlag() != 1) {
                    isExists = usersFrontServiceImpl.findUsersFrontById(student.getUserId());
                } else {
                    isExists = usersFrontServiceImpl.findUsersFrontByMobile(usersFront);
                }
                // UsersFront
                // isExists=usersFrontServiceImpl.findUsersFrontByMobile(usersFront);
                if (isExists != null) {
                    usersFront.setId(isExists.getId());
                    usersFrontServiceImpl.update(usersFront);
                } else {
                    usersFrontServiceImpl.insert(usersFront);
                }
                student.setUserId(usersFront.getId());
                studentServiceImpl.update(student);
                log_student.info(">>> [学员更新] " + "状态：success" + ", 信息：" + "公司ID = " + WebUtils.getCurrentCompanyId() + ", 操作人ID = "
                        + WebUtils.getCurrentUserId(request) + ", 学生ID = " + student.getId());
                return JsonMsg.SUCCESS;
            }
        }
        log_student.info(">>> [学员更新] " + "状态：success" + ", 信息：" + "公司ID = " + WebUtils.getCurrentCompanyId() + ", 操作人ID = " + WebUtils.getCurrentUserId(request)
                + ", 学生ID = " + student.getId());
        return JsonMsg.SUCCESS;
    }

    // 转换code
    private String codetoString(String code) {
        String str = "";
        if (!"".equals(code) && null != code) {
            if ("0101".equals(code)) {
                str = "手机号不能为空！";
            } else if ("0102".equals(code)) {
                str = "手机号格式不正确";
            } else if ("0301".equals(code) || "0302".equals(code)) {
                str = "身份证号不正确";
            } else if ("0303".equals(code)) {
                str = "身份证号已存在";
            } else if ("0103".equals(code)) {
                str = "手机号已存在";
            } else if ("0401".equals(code)) {
                str = "邮箱不能为空";
            } else if ("0402".equals(code)) {
                str = "邮箱不正确";
            } else if ("0501".equals(code)) {
                str = "qq不能为空";
            } else if ("0601".equals(code)) {
                str = "紧急联系人不能为空";
            } else if ("0702".equals(code)) {
                str = "紧急联系电话不正确";
            } else {
                str = code;
            }
        }
        return str;
    }

    /**
     *
     * Class Name: StudentController.java
     *
     * @Description: 查询购买数
     * @author chopin
     * @date 2015年10月9日 下午5:17:37
     * @modifier
     * @modify-date 2015年10月8日 下午5:17:37
     * @version 1.0
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryBuyNum")
    public Integer queryBuyNum() {
        Integer stuCount = studentPayMasterServiceImpl.findStuCountByCompanyId(WebUtils.getCurrentCompanyId());
        return stuCount;
    }

    // 导入成功后查询导入数据页面
    @RequestMapping(value = "/queryData")
    public String importStudentData(Model model, String stuMobiles) {
        CompanyFunctionSet search = new CompanyFunctionSet();
        search.setFunctionCode("COMPANY_FUNCTION_COURSE");
        search.setCompanyId(WebUtils.getCurrentCompanyId());
        CompanyFunctionSet cfs = companyFunctionSetServiceImpl.findCompanyUseCourse(search);
        if (cfs != null && "1".equals(cfs.getStatus())) {
            model.addAttribute("isFull", 1);
        } else {
            model.addAttribute("isFull", 0);
        }
        String newstr = "";
        if (null != stuMobiles && !"".equals(stuMobiles)) {
            newstr = stuMobiles.substring(0, stuMobiles.length() - 1);
        }
        model.addAttribute("stuMobiles", newstr);
        // 查询公司注册设置
        CompanyRegisterConfig crc = new CompanyRegisterConfig();
        crc.setCompanyId(WebUtils.getCurrentCompanyId());
        crc = companyRegisterConfigServiceImpl.queryByCompanyId(crc);
        if (null != crc && crc.getCloseFlag() == 1) {
            crc.setMobileFlag(1);
        }
        model.addAttribute("registConfig", crc);
        return "student/importStudentData";
    }

    /**
     * 。、
     *
     * Class Name: StudentController.java
     *
     * @Description: 查询导入学员
     * @author zhang.zx
     * @date 2016年3月11日 下午6:48:58
     * @modifier
     * @modify-date 2016年3月11日 下午6:48:58
     * @version 1.0
     * @param search
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryStudentsList2")
    public List<StudentListVo> queryExportData(StudentListVo search, HttpServletRequest request) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        search.setCompanyId(WebUtils.getCurrentCompanyId());
        search.setUserId(WebUtils.getCurrentUserId(request));
        search.setStartTime(sdf.format(new Date()));
        List<StudentListVo> pageFinder = studentServiceImpl.queryStudentsList2(search);
        return pageFinder;
    }

    /**
     *
     * Class Name: StudentController.java
     *
     * @Description: 定制导出学员
     * @author zhang.zx
     * @date 2016年6月14日 下午8:27:48
     * @modifier
     * @modify-date 2016年6月14日 下午8:27:48
     * @version 1.0
     * @param model
     * @return
     */
    @RequestMapping(value = "/exportStudentPayMaster")
    public ModelAndView exportStudentPayMaster(Model model, StudentListVo search) {
        Integer companyId = WebUtils.getCurrentCompanyId();
        List<StudentListDataVo> al = new ArrayList<StudentListDataVo>();
        if (EntityUtil.isNotBlank(search)) {
            search.setCompanyId(companyId);
            // search.setSchoolId(WebUtils.getCurrentSchoolId());
            search.setPageSize(10000);
            al = studentServiceImpl.queryPayListByStuId(search);
        }
        List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
        // LongitudinalTableData sql=new LongitudinalTableData();
        // sql.setCompanyId(WebUtils.getCurrentCompanyId());
        // sql.setSchoolId(WebUtils.getCurrentSchoolId());
        // sql.setTableName("student");
        // List<LongitudinalTableData>
        // ns=longitudinalTableDataServiceImpl.query(sql);
        // List<LongitudinalTableData> ms= new
        // ArrayList<LongitudinalTableData>();
        // ms.addAll(ns);
        Map<Integer, Map<String, Object>> customInfo = customStuInfo("stu_id");

        // log.info("自定义表记录["+ns.size()+"]条");
        for (StudentListDataVo s : al) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("identityId", s.getIdentityId());
            map.put("birthday", s.getBirthday());
            map.put("age", s.getAge());
            map.put("username", s.getUsername());
            map.put("companyId", s.getCompanyId());
            map.put("deleteFlag", s.getDeleteFlag());
            map.put("educationCode", s.getEducationCode());
            map.put("email", s.getEmail());
            map.put("emergencyContact", s.getEmergencyContact());
            map.put("emergencyPhone", s.getEmergencyPhone());
            map.put("hkAddress", s.getHkAddress());
            map.put("identityId", s.getIdentityId());
            map.put("identityTypeCode", s.getIdentityTypeCode());
            map.put("mobile", s.getMobile());
            map.put("name", s.getName());
            map.put("sex", s.getSex());
            map.put("homePhone", s.getHomePhone());
            map.put("weixinId", s.getWeixinId());
            map.put("qq", s.getQq());
            map.put("province", s.getProvince());
            map.put("city", s.getCity());
            map.put("county", s.getCounty());
            map.put("addressDetail", s.getAddressDetail());
            map.put("payType", s.getPayType());
            map.put("payMoney", s.getPayMoney());
            map.put("coursesName", s.getCoursesName());
            map.put("schoolName", s.getSchoolName());
            map.put("createTime", s.getCreateTime());
            // for(LongitudinalTableData n : ns){
            // if("stu_id".equals(n.getColName()) &&
            // (s.getId()==Integer.parseInt(n.getColValue()) ||
            // n.getColValue().equals(s.getId()))){
            // for(LongitudinalTableData v: ms){
            // int r=v.getRowId();
            // int rr=n.getRowId();
            // if(r==rr){
            // map.put(v.getColName(), v.getColValue());
            // }
            // }
            // }
            // }
            Map<String, Object> definedDataMap = customInfo.get(s.getId());
            if (definedDataMap != null)
                map.putAll(definedDataMap);
            lists.add(map);
        }
        StringBuffer title = new StringBuffer(
                "报名(缴费)时间:createTime,手机号:mobile,姓名:name,证件号码:identityId,省:province,市:city,县:county,详细地址:addressDetail,课程:coursesName,缴费方式:payType,金额:payMoney,分校:schoolName");
        List<LongitudinalTableColDefine> coldefine = longitudinalTableColDefineServiceImpl.findByCompany(WebUtils.getCurrentCompanyId(), "student");
        if (coldefine != null && coldefine.size() > 0) {
            for (LongitudinalTableColDefine d : coldefine) {
                if (!"id".equals(d.getColName()))
                    title.append("," + d.getColComment() + ":" + d.getColName());
            }
        }
        ViewFiles excel = new ViewFiles();
        HSSFWorkbook wb = new HSSFWorkbook();
        try {
            wb = ExcelUtil.newWorkbook(lists, "sheet1", title.toString());
        } catch (Exception ex) {

        }
        Map map = new HashMap();
        map.put("workbook", wb);
        map.put("fileName", "学员列表.xls");
        return new ModelAndView(excel, map);
    }

    /**
     *
     * @author licong
     * @date 2016年8月15日 下午6:19:39
     * @param
     * @param key
     *            自定义表中必然会出现的列名
     * @return
     */
    public Map<Integer, Map<String, Object>> customStuInfo(String key) {
        Map<Integer, Map<String, Object>> resultMap = new HashMap<Integer, Map<String, Object>>();
        LongitudinalTableData sql = new LongitudinalTableData();
        sql.setCompanyId(WebUtils.getCurrentCompanyId());
        // sql.setSchoolId(WebUtils.getCurrentSchoolId());
        sql.setTableName("student");
        List<LongitudinalTableData> ns = longitudinalTableDataServiceImpl.query(sql);
        if (ns == null || ns.size() == 0)
            return resultMap;

        // 键值对为rowId-dataInfo
        Map<Integer, Map<String, Object>> rowToDataMap = new HashMap<Integer, Map<String, Object>>();

        for (LongitudinalTableData longitudinalTableData : ns) {
            Integer row = longitudinalTableData.getRowId();
            Map<String, Object> dataMap = null;
            if (!rowToDataMap.containsKey(row)) {
                dataMap = new HashMap<String, Object>();
                rowToDataMap.put(row, dataMap);
            } else {
                dataMap = rowToDataMap.get(row);
            }
            // 过滤掉名称为id的列
            if (!"id".equals(longitudinalTableData.getColName()))
                dataMap.put(longitudinalTableData.getColName(), longitudinalTableData.getColValue());
        }
        // 删除列名为key的键值对,转换键值对为stuId-dataInfo
        for (Map.Entry<Integer, Map<String, Object>> entry : rowToDataMap.entrySet()) {
            Map<String, Object> data = entry.getValue();
            String stuId = (String) data.get(key);
            if (StringUtils.isNotBlank(stuId) && !"null".equals(stuId)) {
                Integer value = Integer.parseInt(stuId);
                data.remove(key);
                resultMap.put(value, data);
            }
        }

        return resultMap;
    }

    /**
     *
     * Class Name: StudentController.java
     *
     * @Description: 根据用户id查询学员信息
     * @author zhang.zx
     * @date 2016年6月28日 下午2:02:15
     * @modifier
     * @modify-date 2016年6月28日 下午2:02:15
     * @version 1.0
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryByUId")
    public List<Student> queryStuByUserId(String userId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("companyId", WebUtils.getCurrentCompanyId());
        map.put("userId", userId);
        return studentServiceImpl.findStuInfoByUserId(map);
    }

    /**
     *
     * Class Name: StudentController.java
     *
     * @Description: 验证前台用户名
     * @author zhang.zx
     * @date 2016年7月7日 下午3:25:39
     * @modifier
     * @modify-date 2016年7月7日 下午3:25:39
     * @version 1.0
     * @param u
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/checkFrontUserName")
    public boolean checkUserFrontName(UsersFront u) {
        u.setCompanyId(WebUtils.getCurrentCompanyId());
        List<UsersFront> arr = usersFrontServiceImpl.findConponsUsersByCondition(u);
        if (null != arr && arr.size() > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * @author licong
     * @date 2016年7月18日 下午12:19:33
     * @param 验证前台手机号
     */
    @ResponseBody
    @RequestMapping(value = "/checkUserFrontMobile")
    public boolean checkUserFrontMobile(UsersFront u) {
        Integer companyId = WebUtils.getCurrentCompanyId();
        u.setCompanyId(companyId);
        List<UsersFront> arr = usersFrontServiceImpl.findConponsUsersByCondition(u);
        if (null != arr && arr.size() > 0) {
            return false;
        }
        Student search = new Student();
        search.setCompanyId(companyId);
        search.setMobile(u.getMobile());
        List<Student> arr1 = studentServiceImpl.checkUserInfo(search);
        if (null != arr1 && arr1.size() > 0) {
            return false;
        }
        return true;
    }

    /**
     * 
     * Class Name: StudentController.java
     * 
     * @Description: TODO(学员详情课程包)
     * @author dongshuai
     * @date 2016年11月1日 上午10:56:32
     * @modifier
     * @modify-date 2016年11月1日 上午10:56:32
     * @version 1.0
     * @param stuId
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "selectClassPackageEntryMessage", method = RequestMethod.POST)
    public List<StudentPayMaster4ClassPackageVo> selectClassPackageEntryMessage(Integer stuId, Model model) {
        StudentPayMaster spm = new StudentPayMaster();
        spm.setStuId(stuId);
        spm.setCompanyId(WebUtils.getCurrentCompanyId());
        List<StudentPayMaster4ClassPackageVo> payMasterList = studentPayMasterServiceImpl.findClassPackageEntryMessage(spm);
        // 查询已付款
        for (StudentPayMaster4ClassPackageVo studentPayMaster : payMasterList) {
            studentPayMaster.setHasPay(studentPayMaster.getTotalAmount());
            Double unPay = 0.0;
            studentPayMaster.setUnPay(unPay);
            studentPayMaster.setApplyChannelCode(SysLoader.dictCode2Name(studentPayMaster.getApplyChannelCode()));
            studentPayMaster.setPayStatusCode(SysLoader.dictCode2Name(studentPayMaster.getPayStatusCode()));

            // 获取分类名称
            studentPayMaster = selectClassPackageCategoryName(studentPayMaster, studentPayMaster.getCategoryCode());

        }
        return payMasterList;
    }

    private StudentPayMaster4ClassPackageVo selectClassPackageCategoryName(StudentPayMaster4ClassPackageVo studentPayMaster, String code) {
        Map<String, Object> map = null;
        if (code.length() == 9) {
            map = new HashMap<String, Object>();
            map.put("companyId", WebUtils.getCurrentCompanyId());
            map.put("schoolId", WebUtils.getCurrentSchoolId());
            map.put("code", code);
            ClassPackageCategory cpc = studentPayMasterServiceImpl.selectClassPackageCategoryCode(map);
            studentPayMaster.setThreeCategory(cpc.getName());
            ClassPackageCategory cpc2 = classPackageCategoryServiceImpl.findClassPackageCategoryById(cpc.getParentId());
            studentPayMaster.setTwoCategory(cpc2.getName());
            ClassPackageCategory cpc3 = classPackageCategoryServiceImpl.findClassPackageCategoryById(cpc2.getParentId());
            studentPayMaster.setOneCategory(cpc3.getName());
        } else if (code.length() == 6) {
            map = new HashMap<String, Object>();
            map.put("companyId", WebUtils.getCurrentCompanyId());
            map.put("schoolId", WebUtils.getCurrentSchoolId());
            map.put("code", code);
            ClassPackageCategory cpc = studentPayMasterServiceImpl.selectClassPackageCategoryCode(map);
            studentPayMaster.setThreeCategory(null);
            studentPayMaster.setTwoCategory(cpc.getName());
            ClassPackageCategory cpc3 = classPackageCategoryServiceImpl.findClassPackageCategoryById(cpc.getParentId());
            studentPayMaster.setOneCategory(cpc3.getName());
        } else if (code.length() == 3) {
            map = new HashMap<String, Object>();
            map.put("companyId", WebUtils.getCurrentCompanyId());
            map.put("schoolId", WebUtils.getCurrentSchoolId());
            map.put("code", code);
            ClassPackageCategory cpc = studentPayMasterServiceImpl.selectClassPackageCategoryCode(map);
            studentPayMaster.setThreeCategory(null);
            studentPayMaster.setTwoCategory(null);
            studentPayMaster.setOneCategory(cpc.getName());
        }
        return studentPayMaster;
    };

    /**
     * 
     * Class Name: StudentController.java
     * 
     * @Description: TODO(学员详情课程)
     * @author dongshuai
     * @date 2016年11月1日 上午10:57:01
     * @modifier
     * @modify-date 2016年11月1日 上午10:57:01
     * @version 1.0
     * @param stuId
     * @param model
     * @param mobile
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "selectEntryMessage3", method = RequestMethod.POST)
    public List<StudentPayMaster> selectEntryMessage3(Integer stuId, Model model, String mobile) {
        List<StudentPayMaster> payMasterList = null;
        if ("".equals(stuId) || stuId == null) {
            payMasterList = studentPayMasterServiceImpl.findByStuMbile2(mobile, WebUtils.getCurrentCompanyId(), WebUtils.getCurrentSchoolId());
        } else {
            payMasterList = studentPayMasterServiceImpl.findEntryMessage(stuId, WebUtils.getCurrentCompanyId());
        }
        // 查询已付款
        for (StudentPayMaster studentPayMaster : payMasterList) {
            Integer id = studentPayMaster.getId();
            Double hasPay = studentFeeStageServiceImpl.findSumPayed(id);
            studentPayMaster.setHasPay(hasPay);
            studentPayMaster.setApplyChannelCode(SysLoader.dictCode2Name(studentPayMaster.getApplyChannelCode()));
            studentPayMaster.setPayStatusCode(SysLoader.dictCode2Name(studentPayMaster.getPayStatusCode()));
        }
        return payMasterList;
    }

    /**
     * 学员公告列表页面
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("goAffichePage")
    public String goAffichePage(HttpServletRequest request,HttpServletResponse response,ModelMap model){
    	int pageNum = 1;
    	String pageNumStr = request.getParameter("page");
    	if(StringUtils.isNotBlank(pageNumStr)){
    		pageNum = Integer.parseInt(pageNumStr);
    	}
    	CompanyOrgMessageVo search = new CompanyOrgMessageVo();
    	search.setPage(pageNum);
    	search.setPageSize(5);
    	search.setMessageType(AFFICHE_TYPE);
    	PageFinder<CompanyOrgMessageVo> msgPage = companyServiceImpl.findMessageList(search);
		model.addAttribute("msgPage", msgPage);
    	return "student/notice/affiche";
    }
    /**
     * 添加公告
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("addAffiche")
    public String addAffiche(HttpServletRequest request,HttpServletResponse response,ModelMap model){
    	String url = "redirect:/student/notice?goAffiche=goAffiche";
    	try{
    		Users user = WebUtils.getCurrentUser(request);
        	CompanyOrgMessageVo message = new CompanyOrgMessageVo();
        	String content = request.getParameter("content");
        	if(StringUtils.isBlank(content) || content.length() >200){
        	   return url;
        	}
        	message.setContent(content);
        	message.setSender(String.valueOf(user.getId()));
        	message.setSenderName(user.getUsername());
        	message.setMessageType("AFFICHE");
        	message.setCompanyId(user.getCompanyId());
        	message.setSendTime(new Date());
        	message.setStatus(0);//0:下架，1上架
        	companyServiceImpl.insertMsg(message);
    	}catch(Exception e){
    		log.error("add affiche is error :", e);
    	}
        return url;
    }
    
    /**
     * 返回公告页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("goBackAffiche")
    public String goBackAffiche(HttpServletRequest request,HttpServletResponse response){
    	 return "redirect:/student/notice?goAffiche=goAffiche";
    }
    
    /**
     * 公告上下架
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("afficheShelving")
    public String afficheShelving(HttpServletRequest request,HttpServletResponse response){
    	String result = "failed";
    	try{
    		int id = Integer.parseInt(request.getParameter("id"));
    		int status = Integer.parseInt(request.getParameter("status"));
    		CompanyOrgMessageVo msg = new CompanyOrgMessageVo();
    		msg.setId(id);
    		msg.setStatus(status);
    		msg.setUpdateTime(new Date());
    		companyServiceImpl.updateMsg(msg);
    		result = "success";
    	}catch(Exception e){
    		log.error("afficheShelving is error :", e);
    	}
    	return result;
    }

    @ResponseBody
    @RequestMapping(value = "/getSchoolListByStep")
    public List<SysConfigDict> getSchoolListByStep(HttpServletRequest request,SysConfigDict areaDict) {
        areaDict.setDictCode("EDU_SCHOOL");
        List<SysConfigDict> areas = sysConfigDictServiceImpl.querySchoolListByStepCode(areaDict);
        return areas;
    }
}
