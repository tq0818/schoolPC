package com.yuxin.wx.controller.student;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.classes.IClassModuleService;
import com.yuxin.wx.api.classes.IClassTypeService;
import com.yuxin.wx.api.commodity.ICommodityProductRealtionService;
import com.yuxin.wx.api.commodity.ICommodityService;
import com.yuxin.wx.api.company.ICompanyFunctionSetService;
import com.yuxin.wx.api.company.ICompanyMessageHistoryService;
import com.yuxin.wx.api.company.ICompanyRegisterConfigService;
import com.yuxin.wx.api.pay.IPayOrderService;
import com.yuxin.wx.api.student.IStudentAgentMaterialService;
import com.yuxin.wx.api.student.IStudentFeeStageService;
import com.yuxin.wx.api.student.IStudentPayMasterService;
import com.yuxin.wx.api.student.IStudentPaySlaveService;
import com.yuxin.wx.api.student.IStudentService;
import com.yuxin.wx.api.system.ISysConfigItemService;
import com.yuxin.wx.api.system.ISysConfigServiceService;
import com.yuxin.wx.api.system.ISysConfigTermService;
import com.yuxin.wx.api.user.IUsersFrontService;
import com.yuxin.wx.common.JsonMsg;
import com.yuxin.wx.model.classes.ClassType;
import com.yuxin.wx.model.commodity.CommodityProductRealtion;
import com.yuxin.wx.model.company.CompanyFunctionSet;
import com.yuxin.wx.model.company.CompanyMessageHistory;
import com.yuxin.wx.model.company.CompanyRegisterConfig;
import com.yuxin.wx.model.student.Student;
import com.yuxin.wx.model.student.StudentAgentMaterial;
import com.yuxin.wx.model.student.StudentFeeStage;
import com.yuxin.wx.model.student.StudentPayMaster;
import com.yuxin.wx.model.system.SysConfigItem;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.model.user.UsersFront;
import com.yuxin.wx.utils.FileUploadUtil;
import com.yuxin.wx.utils.ImportExcl;
import com.yuxin.wx.utils.ParameterUtil;
import com.yuxin.wx.utils.PropertiesUtil;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.utils.smsClient.interfacej.SmsClientSend;
import com.yuxin.wx.vo.classes.ClassTypeVo;

/**
 *
 * @ClassName: StudentImportController
 * @Description: 学员导入并报名
 * @author DELL.COM
 * @date 2016年1月12日 下午1:42:40
 * @modifier
 * @modify-date 2016年1月12日 下午1:42:40
 * @version 1.0
 */
@Controller
@RequestMapping("/manageStudent")
public class StudentImportController {
    private static Log log = LogFactory.getLog("log");
    @Autowired
    private IStudentService studentServiceImpl;
    @Autowired
    private IClassTypeService classTypeServiceImpl;
    @Autowired
    private IStudentPayMasterService studentPayMasterServiceImpl;
    @Autowired
    private IStudentAgentMaterialService studentAgentMaterialServiceImpl;
    @Autowired
    private IStudentPaySlaveService studentPaySlaveServiceImpl;
    @Autowired
    private IStudentFeeStageService studentFeeStageServiceImpl;
    @Autowired
    private IUsersFrontService usersFrontServiceImpl;
    @Autowired
    private IPayOrderService payOrderServiceImpl;
    @Autowired
    private IClassModuleService classModuleServiceImpl;
    @Autowired
    private ICommodityService commodityServiceImpl;
    @Autowired
    private ICompanyMessageHistoryService companyMessageHistoryServiceImpl;

    @Autowired
    private ICommodityProductRealtionService commodityProductRealtionServiceImpl;
    @Autowired
    private ISysConfigServiceService SysConfigServiceImpl;

    @Autowired
    private ISysConfigTermService sysConfigTermServiceImpl;
    @Autowired
    private ICompanyFunctionSetService companyFunctionSetServiceImpl;
    @Autowired
    private ISysConfigItemService sysConfigItemServiceImpl;
    @Autowired
    private ICompanyRegisterConfigService companyRegisterConfigServiceImpl;
    @Autowired
    private PropertiesUtil propertiesUtil;

    /**
     *
     * @Description: 跳转到导入学员列表页
     * @author zhang.zx
     */
    @RequestMapping(value = "/importStudents/{id}/{lable}")
    public String importStusPage(Model model, @PathVariable Integer id, @PathVariable String lable) {
        // 根据班型id查询详情
        Map<String, String> map = new HashMap<String, String>();
        map.put("classId", "" + id);
        ClassTypeVo classType = classTypeServiceImpl.findClassTypeDetail(map);
        model.addAttribute("classType", classType);
        model.addAttribute("ct", classType);
        model.addAttribute("type", "update");
        model.addAttribute("lable", lable);

        CompanyFunctionSet companyFunctionSet = new CompanyFunctionSet();
        companyFunctionSet.setCompanyId(WebUtils.getCurrentCompanyId());
        companyFunctionSet.setFunctionCode("STUDENT_GROUP");
        List<CompanyFunctionSet> companyFunctionSetList = companyFunctionSetServiceImpl.findCompanyFunctionSetByPage(companyFunctionSet);
        CompanyFunctionSet search = companyFunctionSetList != null && companyFunctionSetList.size() > 0 ? companyFunctionSetList.get(0) : null;
        if (null != search) {
            model.addAttribute("sgOpen", search.getStatus());
        }
        return "classType/manageStu/StudentsManage";
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
            log.error("文件下载失败", e);
            e.printStackTrace();
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
    public String validateStudentData(MultipartRequest multiPartRquest, HttpServletRequest req, HttpServletResponse reponse) {
        Integer userId = WebUtils.getCurrentUserId(req);
        JSONObject json = new JSONObject();
        // List<String> errorList=new ArrayList<String>();
        // List<Integer> stuIds=new ArrayList<Integer>();
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
                    JSONObject jsonData = validDatas(propertiesUtil.getExclePath() + userId + "/" + newFileName);
                    if (null != jsonData) {
                        json.put("error", jsonData.get("errorList"));
                        json.put("stusMobile", jsonData.get("stusMobile"));
                    }
                } catch (Exception e) {
                    log.error("模板不正确", e);
                    e.printStackTrace();
                    json.put("teletmperror", "模板错误,请先下载标准模板!");
                }
            } catch (IOException e) {
                log.error("文件上传失败", e);
                e.printStackTrace();
            }
            json.put("exclePath", propertiesUtil.getExclePath() + userId + "/" + newFileName);
        }
        reponse.setContentType("text/html");
        try {
            reponse.getWriter().write(json.toJSONString());
        } catch (IOException e) {
            log.error("学员校验数据返回结果错误", e);
            e.printStackTrace();
        }
        return null;
    }

    // 校验数据
    private JSONObject validDatas(String path) throws Exception {
        // 查询公司注册设置
        CompanyRegisterConfig crc = new CompanyRegisterConfig();
        crc.setCompanyId(WebUtils.getCurrentCompanyId());
        crc = companyRegisterConfigServiceImpl.queryByCompanyId(crc);
        JSONObject json = new JSONObject();
        List<String> errorList = new ArrayList<String>();
        List<String> stusMobile = new ArrayList<String>();
        try {
            UsersFront u = new UsersFront();
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
                    // 公司开启用户名
                    if (null != crc && null != crc.getUsernameFlag() && crc.getUsernameFlag() == 1 && null != crc.getMobileFlag() && crc.getMobileFlag() != 1) {
                        if (!"".equals(cellList.get(0))) {
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
                        if ("".equals(cellList.get(8))) {
                            String str = "第" + (i + 1) + "行中用户名为空!";
                            errorList.add(str);
                        } else {
                            if (!ParameterUtil.isUserName(cellList.get(8))) {
                                String str = "第" + (i + 1) + "行中用户名格式不正确!";
                                errorList.add(str);
                            } else {
                                u.setCompanyId(WebUtils.getCurrentCompanyId());
                                u.setUsername(cellList.get(8));
                                List<UsersFront> uarr = usersFrontServiceImpl.findConponsUsersByCondition(u);
                                if (null != uarr && uarr.size() > 0) {
                                    String str = "第" + (i + 1) + "行中用户名已存在!";
                                    errorList.add(str);
                                    stusMobile.add(uarr.get(0).getUsername());
                                }
                            }
                        }
                    } else {
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
                                Student stu = studentServiceImpl.queryStuIsExists(s);
                                if (null != stu) {
                                    stusMobile.add(stu.getMobile());
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
                        if (null != crc && null != crc.getUsernameFlag() && crc.getUsernameFlag() == 1) {
                            if ("".equals(cellList.get(8))) {
                                String str = "第" + (i + 1) + "行中用户名为空!";
                                errorList.add(str);
                            }
                        }
                        if (!"".equals(cellList.get(8))) {
                            if (!ParameterUtil.isUserName(cellList.get(8))) {
                                String str = "第" + (i + 1) + "行中用户名格式不正确!";
                                errorList.add(str);
                            } else {
                                u.setCompanyId(WebUtils.getCurrentCompanyId());
                                u.setUsername(cellList.get(8));
                                List<UsersFront> uarr = usersFrontServiceImpl.findConponsUsersByCondition(u);
                                if (null != uarr && uarr.size() > 0) {
                                    String str = "第" + (i + 1) + "行中用户名已存在!";
                                    errorList.add(str);
                                }
                            }
                        }
                    }

                }
            }
        } catch (Exception e) {
            log.error("模板不正确", e);
            e.printStackTrace();
            throw new Exception("模板不正确");
        }
        json.put("errorList", errorList);
        json.put("stusMobile", stusMobile);
        return json;
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
    public String insertStudents(String list, Integer classtypeId, String lable, HttpServletRequest request, Model model) {
        // 根据班型id查询详情
        Map<String, String> map1 = new HashMap<String, String>();
        map1.put("classId", "" + classtypeId);
        ClassTypeVo classType = classTypeServiceImpl.findClassTypeDetail(map1);
        model.addAttribute("classType", classType);
        model.addAttribute("ct", classType);
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
        CompanyFunctionSet search = new CompanyFunctionSet();
        search.setFunctionCode("COMPANY_FUNCTION_COURSE");
        search.setCompanyId(WebUtils.getCurrentCompanyId());
        CompanyFunctionSet cfs = companyFunctionSetServiceImpl.findCompanyUseCourse(search);
        if (cfs != null && "1".equals(cfs.getStatus())) {
            return "classType/manageStu/student-signupMany-full";
        }
        return "classType/manageStu/student-signupMany";
    }

    /**
     *
     * Class Name: StudentController.java
     * 
     * @Description: 批量报名学员
     * @author zhang.zx
     * @date 2015年10月8日 下午4:34:10
     * @modifier
     * @modify-date 2015年10月8日 下午4:34:10
     * @version 1.0
     * @param filePath
     * @return
     */
    private void signUpStudents(List<Student> studentlist, Integer classtypeId, Double trainingFee, HttpServletRequest request) {
        ClassType classtype = classTypeServiceImpl.findClassTypeById(classtypeId);
        SysConfigItem item = sysConfigItemServiceImpl.findSysConfigItemById(classtype.getItemOneId());
        SysConfigItem item2 = sysConfigItemServiceImpl.findSysConfigItemById(classtype.getItemSecondId());
        if (item != null) {
            classtype.setItemOneName(item.getItemName());
        }
        if (item2 != null) {
            classtype.setItemSecondName(item2.getItemName());
        }
        StudentPayMaster payMaster = new StudentPayMaster();
        payMaster.setItemOneId(classtype.getItemOneId());
        payMaster.setItemOneName(classtype.getItemOneName());
        payMaster.setItemSecondId(classtype.getItemSecondId());
        payMaster.setItemSecondName(classtype.getItemSecondName());
        payMaster.setCommodityId(classtype.getId());
        payMaster.setCommodityType("COMMODITY_CLASS");
        payMaster.setClassTypeName(classtype.getName());
        payMaster.setApplyTime(new Date());
        payMaster.setSchoolId(WebUtils.getCurrentSchoolId());
        payMaster.setCompanyId(WebUtils.getCurrentCompanyId());
        payMaster.setApplyChannelCode("CHANNEL_ONLINE");
        payMaster.setPayStatusCode("ORDER_PAID");
        payMaster.setBizStatusCode("ORDER_BIZ_NEW_ORDER");
        payMaster.setIsAgent("0");
        payMaster.setTrainingFee(trainingFee);
        payMaster.setTotalAmount(trainingFee);
        payMaster.setPaymentTypeCode("PAY_TYPE_ALL");
        payMaster.setCreateTime(new Date());
        payMaster.setDeleteFlag(0);
        List<StudentAgentMaterial> materials = null;
        for (Student student : studentlist) {
            try {
                Users user = WebUtils.getCurrentUser(request);
                payMaster.setStuId(student.getId());
                List<StudentFeeStage> stages = new ArrayList<StudentFeeStage>();
                StudentFeeStage s1 = new StudentFeeStage();
                s1.setStuId(student.getId());
                s1.setStageStatus("1");
                s1.setStageFee(trainingFee);
                s1.setStageDate(new Date());
                s1.setDelFlag(0);
                s1.setCompanyId(WebUtils.getCurrentCompanyId());
                s1.setCreateTime(new Date());
                s1.setCashReal(trainingFee);
                s1.setPayDate(new Date());
                stages.add(s1);
                // 防止订单id重复
                payMaster.setId(null);
                // 建立订单
                studentPayMasterServiceImpl.savePayMaster(payMaster, user, materials, stages);
                // 更新商品表购买人数
                CommodityProductRealtion cpr = commodityProductRealtionServiceImpl.findByClassTypeId("" + payMaster.getCommodityId());
                commodityServiceImpl.updateBuyNumById(cpr.getComId());
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
                userfront.setPassword(new Md5Hash(student.getMobile().substring(5)).toHex());
                UsersFront u = usersFrontServiceImpl.findUsersFrontByMobile(userfront);
                // 检查报名完成是否要发短信
                CompanyFunctionSet cfs = new CompanyFunctionSet();
                cfs.setStatus("1");
                cfs.setCompanyId(WebUtils.getCurrentCompanyId());
                cfs.setFunctionCode("COMPANY_FUNCTION_APPLY");
                // 获取用户短信配置
                List<CompanyFunctionSet> set = companyFunctionSetServiceImpl.findCompanyFunctionSet(cfs);
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
                log.info("****************报名已完成*****************");
                log.info(sms);
                if (needSendSms) {
                    try {
                        if (null != student && null != student && null != student.getMobile() && !"".equals(student.getMobile())) {// 2016/7/7
                                                                                                                                   // 无手机号则不发送短信
                            // 发送用户配置的短信内容
                            String send = SmsClientSend.sendSmsTwo(request, student.getMobile(), sms, user.getId(), "sys-notice");
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
                            companyMessageHistoryServiceImpl.insert(history);
                        }
                    } catch (Exception e) {
                        log.error("短信发送失败", e);
                        System.out.println(e);
                    }
                }
                if (u == null || u.getId() == null) {
                    usersFrontServiceImpl.insert(userfront);
                    // 更新用户
                    student.setUserId(userfront.getId());
                    studentServiceImpl.update(student);

                } else {
                    u.setVipFlag(1);
                    u.setInterests("" + payMaster.getItemOneId());
                    usersFrontServiceImpl.update(u);
                    // 更新用户
                    student.setUserId(u.getId());
                    studentServiceImpl.update(student);
                }
            } catch (Exception e) {
                log.error(e, e);
                e.printStackTrace();
                if (null != student && student.getName() != null && !"".equals(student.getName())) {
                    log.error(student.getName() + "学员报名出现异常，可能出现报名失败，请注意检查------------");
                } else {
                    log.error("学员id为" + student.getId() + "的学员报名出现异常，可能出现报名失败，请注意检查------------");
                }

            }
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
     * Class Name: StudentImportController.java
     * 
     * @Description: 批量报名
     * @author zhang.zx
     * @date 2016年3月11日 上午10:36:48
     * @modifier
     * @modify-date 2016年3月11日 上午10:36:48
     * @version 1.0
     * @param classtypeId
     * @param lable
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/list")
    public String insertStudents(Integer classtypeId, String lable, HttpServletRequest request, Model model) {
        // 根据班型id查询详情
        Map<String, String> map1 = new HashMap<String, String>();
        map1.put("classId", "" + classtypeId);
        ClassTypeVo classType = classTypeServiceImpl.findClassTypeDetail(map1);
        model.addAttribute("classType", classType);
        model.addAttribute("ct", classType);
        model.addAttribute("lable", lable);

        Integer companyId = WebUtils.getCurrentCompanyId();
        Integer id = studentServiceImpl.queryMaxIdByCompany(companyId);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("companyId", companyId);
        map.put("id", id);
        List<Student> studentlist = studentServiceImpl.queryImportStudentsBycondition(map);
        for (Student st : studentlist) {
            if (st != null && st.getId() != null) {
                if (st.getAge() == null || "".equals(st.getAge())) {
                    st.setAge(0);
                }
            }
        }
        model.addAttribute("student", studentlist);
        CompanyFunctionSet search = new CompanyFunctionSet();
        search.setFunctionCode("COMPANY_FUNCTION_COURSE");
        search.setCompanyId(WebUtils.getCurrentCompanyId());
        CompanyFunctionSet cfs = companyFunctionSetServiceImpl.findCompanyUseCourse(search);
        if (cfs != null && "1".equals(cfs.getStatus())) {
            return "classType/manageStu/student-signupMany-full";
        }
        return "classType/manageStu/student-signupMany";
    }

}
