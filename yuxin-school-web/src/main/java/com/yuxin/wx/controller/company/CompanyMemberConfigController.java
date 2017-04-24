package com.yuxin.wx.controller.company;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.crypto.hash.Md5Hash;
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

import com.yuxin.wx.api.company.ICompanyCashFlowService;
import com.yuxin.wx.api.company.ICompanyFunctionSetService;
import com.yuxin.wx.api.company.ICompanyIntegralConfigService;
import com.yuxin.wx.api.company.ICompanyMemberConfigService;
import com.yuxin.wx.api.company.ICompanyMemberLevelConfigService;
import com.yuxin.wx.api.company.ICompanyMemberLevelDetailService;
import com.yuxin.wx.api.company.ICompanyMemberServiceService;
import com.yuxin.wx.api.company.ICompanyRegisterConfigService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.api.pay.IPayOrderService;
import com.yuxin.wx.api.student.IStudentPayMasterService;
import com.yuxin.wx.api.student.IStudentService;
import com.yuxin.wx.api.system.ILongitudinalTableColDefineService;
import com.yuxin.wx.api.system.ILongitudinalTableDataService;
import com.yuxin.wx.api.system.ISysPageHeadFootService;
import com.yuxin.wx.api.user.IUsersFrontService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.common.ViewFiles;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.CompanyCashFlow;
import com.yuxin.wx.model.company.CompanyFunctionSet;
import com.yuxin.wx.model.company.CompanyIntegralConfig;
import com.yuxin.wx.model.company.CompanyMemberConfig;
import com.yuxin.wx.model.company.CompanyMemberLevelConfig;
import com.yuxin.wx.model.company.CompanyMemberLevelDetail;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.model.company.CompanyRegisterConfig;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.model.pay.PayOrder;
import com.yuxin.wx.model.student.Student;
import com.yuxin.wx.model.student.StudentPayMaster;
import com.yuxin.wx.model.system.LongitudinalTableColDefine;
import com.yuxin.wx.model.system.LongitudinalTableData;
import com.yuxin.wx.model.system.SysPageHeadFoot;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.model.user.UsersFront;
import com.yuxin.wx.utils.DateUtil;
import com.yuxin.wx.utils.DatetimeUtil;
import com.yuxin.wx.utils.EntityUtil;
import com.yuxin.wx.utils.ExcelUtil;
import com.yuxin.wx.utils.FileUtil;
import com.yuxin.wx.utils.PropertiesUtil;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.company.CompanyPicsVo;
import com.yuxin.wx.vo.company.UpgradeMemberVo;
import com.yuxin.wx.vo.user.UsersFrontVo;

import net.sf.json.JSONArray;

/**
 * Controller of CompanyMemberConfig
 * 
 * @author chopin
 * @date 2016-5-17
 */
@Controller
@RequestMapping("/companyMemberConfig")
public class CompanyMemberConfigController {
    private static Log log = LogFactory.getLog("log");

    @Autowired
    private ICompanyMemberConfigService companyMemberConfigServiceImpl;
    @Autowired
    private IStudentService studentServiceImpl;
    @Autowired
    private IUsersFrontService usersFrontServiceImpl;
    @Autowired
    private IPayOrderService payOrderServiceImpl;
    @Autowired
    private IStudentPayMasterService studentPayMasterServiceImpl;
    @Autowired
    private ICompanyCashFlowService companyCashFlowServiceImpl;
    @Autowired
    private ICompanyMemberLevelDetailService companyMemberLevelDetailServiceImpl;
    @Autowired
    private ICompanyMemberLevelConfigService companyMemberLevelConfigServiceImpl;
    @Autowired
    private ILongitudinalTableColDefineService longitudinalTableColDefineServiceImpl;
    @Autowired
    private ILongitudinalTableDataService longitudinalTableDataServiceImpl;
    @Autowired
    private PropertiesUtil propertiesUtil;
    @Autowired
    private ISysPageHeadFootService sysPageHeadFootServiceImpl;
    @Autowired
    private ICompanyService companyService;
    @Autowired
    private ICompanyMemberServiceService companyMemberServiceServiceImpl;
    @Autowired
    private ICompanyServiceStaticService companyServiceStaticServiceImpl;
    @Autowired
    private ICompanyRegisterConfigService companyRegisterConfigServiceImpl;
    @Autowired
    private ICompanyFunctionSetService companyFunctionSetServiceImpl;
    @Autowired
    private ICompanyIntegralConfigService companyIntegralConfigServiceImpl;

    /**
     * Class Name: CompanyMemberConfigController.java
     * 
     * @Description:跳转到设置会员界面
     * @author xukaiqiang
     * @date 2016年5月21日 上午9:02:29
     * @modifier
     * @modify-date 2016年5月21日 上午9:02:29
     * @version 1.0
     * @param CompanyMemberConfig
     * @return
     */
    @RequestMapping(value = "/addUI")
    public String addUI(Model model) {
        // commonDomain.jsp中的信息
        Integer companyId = WebUtils.getCurrentCompanyId();
        Company company = companyService.findCompanyById(companyId);
        model.addAttribute("company", company);
        CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
        CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);
        model.addAttribute("cms", cms);
        model.addAttribute("css", css);
        log.info("进入设置会员界面addUI,companyMemberConfig/addUI");
        // 根据公司编号进行查询
        CompanyMemberConfig companyMemberConfig = companyMemberConfigServiceImpl.findCompanyMemberConfigByCompanyId(companyId);
        if (companyMemberConfig != null) {
            companyMemberConfig.setMemberPageBanner("http://" + propertiesUtil.getProjectImageUrl() + "/" + companyMemberConfig.getMemberPageBanner());
            model.addAttribute("companyMemberConfig", companyMemberConfig);
        } else {
            CompanyMemberConfig entity = new CompanyMemberConfig();
            entity.setBuyWithIntegral(0);
            entity.setRemindBeforeDay(0);
            entity.setClassPackageDiscount(0);
            entity.setMemberPage(0);
            entity.setClassDiscount(0);
            entity.setRemind(0);
            entity.setRemindContent("亲爱的【username】，您好，您购买的会员将在【yyyy-MM-dd】日到期。为方便您的使用请及时续费。");
            entity.setBecomeMember(0);
            entity.setUpgradeType(0);
            entity.setMemberPageBanner("");
            entity.setCompanyId(companyId);
            entity.setBuyFlag(1);
            companyMemberConfigServiceImpl.insert(entity);
            model.addAttribute("companyMemberConfig", entity);
        }
        log.info("离开设置会员界面addUI,companyMemberConfig/addUI");
        return "company/vip/vip";
    }

    @ResponseBody
    @RequestMapping(value = "/memberAreaSwitch")
    public String memberAreaSwitch(CompanyMemberConfig companyMemberConfig) {
        // 会员专区开关打开
        log.info("会员专区开关操作.....根据公司companyId=" + WebUtils.getCurrentCompanyId() + "和page_head_type=SYS_PAGE_HEAD_MEMBER进行查询.....");
        SysPageHeadFoot sysPageHeadFoot = new SysPageHeadFoot();
        sysPageHeadFoot.setCompanyId(WebUtils.getCurrentCompanyId());
        sysPageHeadFoot.setPageHeadType("SYS_PAGE_HEAD_MEMBER");
        SysPageHeadFoot sysPageHeadFootDB = sysPageHeadFootServiceImpl.findEntityByCompanyIdAndType(sysPageHeadFoot);
        // sysPageHeadFootDB如果不为空，修改数据库中该条数据的valid_flag
        if (sysPageHeadFootDB != null) {
            log.info("sysPageHeadFootDB不为空，修改数据库中该条数据的valid_flag.........................");
            // 如果会员专区打开
            if (companyMemberConfig.getMemberPage() == 1) {
                sysPageHeadFootDB.setValidFlag(1);
            }
            if (companyMemberConfig.getMemberPage() == 0) {
                sysPageHeadFootDB.setValidFlag(0);
            }
            sysPageHeadFootServiceImpl.update(sysPageHeadFootDB);
            log.info("修改完成");
        } else {
            // 向数据库中添加一条字段
            log.info("sysPageHeadFootDB为空，向数据库中添加一条数据");
            SysPageHeadFoot sysPageHeadFootEntity = new SysPageHeadFoot();
            sysPageHeadFootEntity.setName("会员专区");
            sysPageHeadFootEntity.setParentId(0);
            sysPageHeadFootEntity.setUrl("companyMemberConfig/toVip");
            sysPageHeadFootEntity.setPageType("head");
            sysPageHeadFootEntity.setSchoolId(WebUtils.getCurrentSchoolId());
            sysPageHeadFootEntity.setCompanyId(WebUtils.getCurrentCompanyId());
            if (companyMemberConfig.getMemberPage() == 1) {
                sysPageHeadFootEntity.setValidFlag(1);
            }
            if (companyMemberConfig.getMemberPage() == 0) {
                sysPageHeadFootEntity.setValidFlag(0);
            }
            sysPageHeadFootEntity.setSequence(15);
            sysPageHeadFootEntity.setPageHeadType("SYS_PAGE_HEAD_MEMBER");
            sysPageHeadFootEntity.setCreateTime(new Date());
            sysPageHeadFootServiceImpl.insert(sysPageHeadFootEntity);
            log.info("添加完成");
        }

        // 更新会员专区开关
        CompanyMemberConfig companyMemberConfigDB = companyMemberConfigServiceImpl.findCompanyMemberConfigById(companyMemberConfig.getId());
        companyMemberConfigDB.setMemberPage(companyMemberConfig.getMemberPage() == 1 ? 1 : 0);
        companyMemberConfigServiceImpl.update(companyMemberConfigDB);
        return "";
    }

    /**
     * Class Name: CompanyMemberConfigController.java
     * 
     * @Description: 提醒开关
     * @author xukaiqiang
     * @date 2016年6月4日 下午12:17:19
     * @modifier
     * @modify-date 2016年6月4日 下午12:17:19
     * @version 1.0
     * @param companyMemberConfig
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/memberRemindSwitch")
    public String memberRemindSwitch(CompanyMemberConfig companyMemberConfig) {
        // 更新会员专区开关
        CompanyMemberConfig companyMemberConfigDB = companyMemberConfigServiceImpl.findCompanyMemberConfigById(companyMemberConfig.getId());
        companyMemberConfigDB.setRemind(companyMemberConfig.getRemind() == 1 ? 1 : 0);
        companyMemberConfigServiceImpl.update(companyMemberConfigDB);
        return "";
    }

    /**
     * Class Name: CompanyMemberConfigController.java
     * 
     * @Description: 是否允许积分购买的开关
     * @author xukaiqiang
     * @date 2016年6月12日 下午12:50:23
     * @modifier
     * @modify-date 2016年6月12日 下午12:50:23
     * @version 1.0
     * @param companyMemberConfig
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/buyWithIntegralSwitch")
    public String buyWithIntegralSwitch(CompanyMemberConfig companyMemberConfig) {
        // 更新会员专区开关
        CompanyMemberConfig companyMemberConfigDB = companyMemberConfigServiceImpl.findCompanyMemberConfigById(companyMemberConfig.getId());
        companyMemberConfigDB.setBuyWithIntegral(companyMemberConfig.getBuyWithIntegral() == 1 ? 1 : 0);
        companyMemberConfigServiceImpl.update(companyMemberConfigDB);
        return "";
    }

    /**
     * 
     * Class Name: CompanyMemberConfigController.java
     * 
     * @Description: 只允许会员购买会员课程
     * @author dongshuai
     * @date 2016年8月9日 下午4:01:35
     * @modifier
     * @modify-date 2016年8月9日 下午4:01:35
     * @version 1.0
     * @param companyMemberConfig
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/buyFlag")
    public String buyFlag(CompanyMemberConfig companyMemberConfig) {
        CompanyMemberConfig companyMemberConfigDB = companyMemberConfigServiceImpl.findCompanyMemberConfigById(companyMemberConfig.getId());
        companyMemberConfigDB.setBuyFlag(companyMemberConfig.getBuyFlag());
        companyMemberConfigServiceImpl.update(companyMemberConfigDB);
        return "success";
    }

    /**
     * Class Name: CompanyMemberConfigController.java
     * 
     * @Description: 折扣开关
     * @author xukaiqiang
     * @date 2016年6月4日 下午12:42:04
     * @modifier
     * @modify-date 2016年6月4日 下午12:42:04
     * @version 1.0
     * @param companyMemberConfig
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/classDiscountSwitch")
    public String classDiscountSwitch(CompanyMemberConfig companyMemberConfig) {
        // 更新会员专区开关
        CompanyMemberConfig companyMemberConfigDB = companyMemberConfigServiceImpl.findCompanyMemberConfigById(companyMemberConfig.getId());
        companyMemberConfigDB.setClassDiscount(companyMemberConfig.getClassDiscount() == 1 ? 1 : 0);
        companyMemberConfigServiceImpl.update(companyMemberConfigDB);
        return "";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model, CompanyMemberConfig search) {
        if (search == null) {
            search = new CompanyMemberConfig();
            // search.setPageSize(20);
        }
        model.addAttribute("list", companyMemberConfigServiceImpl.findCompanyMemberConfigByPage(search));
        return "companyMemberConfig/list";
    }

    /**
     * Class Name: CompanyMemberConfigController.java
     * 
     * @Description: 添加会员基本设置
     * @author xukaiqiang
     * @date 2016年5月22日 下午10:35:55
     * @modifier
     * @modify-date 2016年5月22日 下午10:35:55
     * @version 1.0
     * @param companyMemberConfig
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Model model, CompanyMemberConfig companyMemberConfig) {
        return "";
    }

    /**
     * Class Name: CompanyMemberConfigController.java
     * 
     * @Description: 添加或者更新会员
     * @author xukaiqiang
     * @date 2016年6月3日 下午3:51:30
     * @modifier
     * @modify-date 2016年6月3日 下午3:51:30
     * @version 1.0
     * @param model
     * @param companyMemberConfig
     * @return
     */
    @ResponseBody
    @RequestMapping("/addOrUpdate")
    public String addOrUpdate(Model model, CompanyMemberConfig companyMemberConfig) {
        Integer companyId = WebUtils.getCurrentCompanyId();
        // 判断数据库中是否有数据
        if (companyMemberConfig.getId() != null) {
            // 修改
            companyMemberConfigServiceImpl.update(companyMemberConfig);
        } else {
            // 添加
            companyMemberConfig.setCompanyId(companyId);
            companyMemberConfigServiceImpl.insert(companyMemberConfig);
        }
        return "";
    }

    @ResponseBody
    @RequestMapping(value = "/queryMemberConfig")
    public CompanyMemberConfig queryIntegralConfigByCondition(CompanyMemberConfig search) {
        search.setCompanyId(WebUtils.getCurrentCompanyId());
        CompanyMemberConfig config = companyMemberConfigServiceImpl.queryMemberSets(search);
        return config;

    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(CompanyMemberConfig CompanyMemberConfig) {
        companyMemberConfigServiceImpl.update(CompanyMemberConfig);
        return "redirect:/companyMemberConfig";
    }

    /**
     * Class Name: CompanyMemberConfigController.java
     * 
     * @Description: 禁用会员
     * @author xukaiqiang
     * @date 2016年6月11日 下午11:28:27
     * @modifier
     * @modify-date 2016年6月11日 下午11:28:27
     * @version 1.0
     * @param stuId
     * @return
     */
    @ResponseBody
    @RequestMapping("/forbiddenMember/{stuId}")
    public int forbiddenMember(@PathVariable Integer stuId) {
        UsersFront usersFront = usersFrontServiceImpl.findUsersFrontById(stuId);
        if (null != usersFront.getMemberStatus() & usersFront.getMemberStatus() == 1) {
            usersFront.setMemberStatus(0);
            usersFrontServiceImpl.update(usersFront);
            return 1;
        } else {
            usersFront.setMemberStatus(1);
            usersFrontServiceImpl.update(usersFront);
            return 0;
        }

    }

    @RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
    public String del(Model model, @PathVariable Integer id) {
        companyMemberConfigServiceImpl.deleteCompanyMemberConfigById(id);
        return "redirect:/companyMemberConfig";
    }

    @ResponseBody
    @RequestMapping(value = "/{id:\\d+}", method = RequestMethod.GET)
    public CompanyMemberConfig getJson(Model model, @PathVariable Integer id) {
        return companyMemberConfigServiceImpl.findCompanyMemberConfigById(id);
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

    /**
     * Class Name: CompanyMemberConfigController.java
     * 
     * @Description: 上传会员基本设置宣传图
     * @author xukaiqiang
     * @date 2016年5月22日 下午10:40:22
     * @modifier
     * @modify-date 2016年5月22日 下午10:40:22
     * @version 1.0
     * @param multiPartRquest
     * @param req
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public CompanyPicsVo upload(MultipartRequest multiPartRquest, HttpServletRequest req) {
        // log.info("进入上传会员基本设置宣传图..............请求的url为：/companyMemberConfig/upload");
        // String realPath = null;
        // String picPath = null;
        // MultipartFile multipartFile = multiPartRquest.getFile("imgDatas");
        // String name = multipartFile.getOriginalFilename();
        // if (name != null && !"".equals(name)) {
        // try {
        // realPath = FileUtil.upload(multipartFile, "company",
        // WebUtils.getCurrentCompanyId() + "");
        // log.info("上传会员基本设置宣传图成功..............realPath为：" + realPath);
        // } catch (Exception e) {
        // log.info("文件上传失败");
        // log.info(e.getStackTrace());
        // e.printStackTrace();
        // }
        // }
        // picPath = "http://" + propertiesUtil.getProjectImageUrl() + "/" +
        // realPath;
        // log.info("离开上传会员基本设置宣传图..............返回picPath为：" + picPath);
        // return "{\"url\":\"" + picPath + "\",\"picPath\":\"" + realPath +
        // "\"}";
        MultipartFile multipartFile = multiPartRquest.getFile("imgDatas");
        String realPath = null;
        try {
            realPath = FileUtil.upload(multipartFile, "company", WebUtils.getCurrentCompanyId() + "");
        } catch (Exception e) {
            log.error("文件上传失败!", e);
            e.printStackTrace();
        }
        req.getSession().setAttribute("imgDatas", multipartFile);
        String url = "http://" + propertiesUtil.getProjectImageUrl() + "/" + realPath;

        CompanyPicsVo pics = new CompanyPicsVo();
        pics.setPicOriginalUrl(url);
        pics.setRealPath(realPath);
        return pics;
    }

    /**
     * 
     * @Description:会员管理
     * @author: dongshuai
     * @date: 2016年5月19日
     * @param model
     * @param request
     * @return
     *
     */
    @RequestMapping("/companyMemberVip")
    public String companyMemberVip(Model model, HttpServletRequest request) {

        return "system/vip-manage";
    }

    /**
     * 
     * @Description: 查询会员
     * @author: dongshuai
     * @date: 2016年5月21日
     * @param search
     * @return
     *
     */
    @ResponseBody
    @RequestMapping(value = "/queryVipsList")
    public PageFinder<UsersFrontVo> queryVipsList(UsersFrontVo search) {
        if ("all".equals(search.getTimeMark())) {
            search.setTimeLen(-1);
        } else if ("ing".equals(search.getTimeMark())) {
            int remindDay = 30;
            CompanyMemberConfig cmc = companyMemberConfigServiceImpl.findCompanyMemberConfigByCompanyId(WebUtils.getCurrentCompanyId());
            if (null != cmc && null != cmc.getRemindBeforeDay() && !"".equals(cmc.getRemindBeforeDay())) {
                remindDay = cmc.getRemindBeforeDay();
            }
            search.setTimeLen(remindDay);
        } else if ("old".equals(search.getTimeMark())) {
            search.setTimeLen(-1);
        }
        search.setCompanyId(WebUtils.getCurrentCompanyId());
        search.setPageSize(10);
        PageFinder<UsersFrontVo> pageFinder = usersFrontServiceImpl.findUserVipList(search);

        return pageFinder;
    }

    /**
     * 
     * @Description: 查询会员数量
     * @author: dongshuai
     * @date: 2016年5月23日
     * @param search
     * @return
     *
     */
    @ResponseBody
    @RequestMapping(value = "/queryVipsNum")
    public Integer queryVipsNum(UsersFrontVo search) {
        search.setCompanyId(WebUtils.getCurrentCompanyId());
        Integer stuCount = usersFrontServiceImpl.findUserVipListCount(search);

        return stuCount;
    }

    /**
     * 
     * @Description: 导出会员
     * @author: dongshuai
     * @date: 2016年5月23日
     * @param model
     * @param search
     * @return
     *
     */
    @RequestMapping(value = "/exportExcel/{mark}")
    public ModelAndView exportStudentsExcle(Model model, UsersFrontVo search, @PathVariable String mark) {
        if ("all".equals(mark)) {
            search.setTimeLen(-1);
            search.setTimeMark(mark);
        } else if ("ing".equals(mark)) {
            int remindDay = 30;
            CompanyMemberConfig cmc = companyMemberConfigServiceImpl.findCompanyMemberConfigByCompanyId(WebUtils.getCurrentCompanyId());
            if (null != cmc && null != cmc.getRemindBeforeDay() && !"".equals(cmc.getRemindBeforeDay())) {
                remindDay = cmc.getRemindBeforeDay();
            }
            search.setTimeLen(remindDay);
            search.setTimeMark(mark);
        } else if ("old".equals(mark)) {
            search.setTimeLen(-1);
            search.setTimeMark(mark);
        }
        List<UsersFrontVo> al = new ArrayList<UsersFrontVo>();
        if (EntityUtil.isNotBlank(search)) {
            search.setCompanyId(WebUtils.getCurrentCompanyId());
            al = usersFrontServiceImpl.findUserVipListForExportExcel(search);
        }
        List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
        LongitudinalTableData sql = new LongitudinalTableData();
        sql.setCompanyId(WebUtils.getCurrentCompanyId());
        sql.setSchoolId(WebUtils.getCurrentSchoolId());
        sql.setTableName("vip");
        List<LongitudinalTableData> ns = longitudinalTableDataServiceImpl.query(sql);
        List<LongitudinalTableData> ms = new ArrayList<LongitudinalTableData>();
        ms.addAll(ns);
        for (UsersFrontVo s : al) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("username", s.getUsername());
            map.put("name", s.getName());
            map.put("mobile", s.getMobile());
            map.put("email", s.getEmail());
            map.put("identityId", s.getIdentityId());
            map.put("memberLevel", s.getMemberLevel());
            if (null != s.getMemberBuyLength()) {
                if (s.getMemberBuyLength() == -1) {
                    map.put("memberBuyLength", "-");
                    map.put("memberEndTime", "无限期");
                } else {
                    map.put("memberBuyLength", s.getMemberBuyLength());
                    map.put("memberEndTime", s.getMemberEndTime());
                }
            }

            for (LongitudinalTableData n : ns) {
                if ("stu_id".equals(n.getColName()) && (s.getId() == Integer.parseInt(n.getColValue()) || n.getColValue().equals(s.getId()))) {
                    for (LongitudinalTableData v : ms) {
                        int r = v.getRowId();
                        int rr = n.getRowId();
                        if (r == rr) {
                            map.put(v.getColName(), v.getColValue());
                        }
                    }
                }
            }
            lists.add(map);
        }
        StringBuffer title = new StringBuffer(
                "用户名:username,会员姓名:name,手机号:mobile,邮箱:email,证件号:identityId,会员等级:memberLevel,有效期:memberBuyLength,到期日:memberEndTime");
        List<LongitudinalTableColDefine> coldefine = longitudinalTableColDefineServiceImpl.findByCompany(WebUtils.getCurrentCompanyId(), "vip");
        if (coldefine != null && coldefine.size() > 0) {
            for (LongitudinalTableColDefine d : coldefine) {
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
        map.put("fileName", "会员列表.xls");
        return new ModelAndView(excel, map);
    }

    /**
     * 
     * @Description: 添加会员
     * @author: dongshuai
     * @date: 2016年5月19日
     * @param model
     * @param student
     * @return
     *
     */
    @RequestMapping(value = "/addMemberVip")
    public String addMemberVip(Model model, Student student) {
        student.setCompanyId(WebUtils.getCurrentCompanyId());
        Student st = studentServiceImpl.findByMobile(student);
        if (st != null && st.getId() != null) {
            model.addAttribute("student", st);
        } else {
            model.addAttribute("student", student);
        }
        CompanyMemberConfig cmc = companyMemberConfigServiceImpl.findCompanyMemberConfigByCompanyId(WebUtils.getCurrentCompanyId());
        if (null != cmc && null != cmc.getBecomeMember() && !"".equals(cmc.getBecomeMember())) {
            model.addAttribute("becomeMember", cmc.getBecomeMember());
        } else {
            model.addAttribute("becomeMember", 0);
        }

        // 查询注册配置
        CompanyRegisterConfig crc = new CompanyRegisterConfig();
        crc.setCompanyId(WebUtils.getCurrentCompanyId());
        crc = companyRegisterConfigServiceImpl.queryByCompanyId(crc);
        model.addAttribute("crc", crc);

        CompanyFunctionSet companyFunctionSet = new CompanyFunctionSet();
        companyFunctionSet.setCompanyId(WebUtils.getCurrentCompanyId());
        companyFunctionSet.setFunctionCode("STUDENT_GROUP");
        List<CompanyFunctionSet> companyFunctionSetList = companyFunctionSetServiceImpl.findCompanyFunctionSetByPage(companyFunctionSet);
        CompanyFunctionSet set = companyFunctionSetList != null && companyFunctionSetList.size() > 0 ? companyFunctionSetList.get(0) : null;
        if (null != set) {
            model.addAttribute("sgOpen", set.getStatus());
        }
        return "classType/manageStu/memberVip-signup";
    }

    /**
     * Class Name: CompanyMemberConfigController.java
     * 
     * @Description: 跳转到升级页面中
     * @author xukaiqiang
     * @date 2016年5月24日 下午5:03:09
     * @modifier
     * @modify-date 2016年5月24日 下午5:03:09
     * @version 1.0
     * @return
     */
    @RequestMapping("/upgradeMemberPage/{usersFrontId}")
    public String upgradeMemberPage(Model model, @PathVariable Integer usersFrontId) {
        log.info("进入会员升级页面中：/upgradeMemberPage/{memberId}");
        model.addAttribute("usersFrontId", usersFrontId);
        Integer companyId = WebUtils.getCurrentCompanyId();
        // 查询会员设置中的值，如果是购买型会员，有效期就正常显示，如果是累积型会员，有效期为终生
        // 通过公司的companyId查找该公司下会员的信息
        CompanyMemberConfig companyMemberConfig = companyMemberConfigServiceImpl.findCompanyMemberConfigByCompanyId(companyId);
        model.addAttribute("companyMemberConfig", companyMemberConfig);
        // 获取会员等级的价格
        return "classType/manageStu/upgradeMember";
    }

    /**
     * 
     * Class Name: CompanyMemberConfigController.java
     * 
     * @Description: 判断是否是最高等级
     * @author xukaiqiang
     * @date 2016年6月13日 上午12:01:28
     * @modifier
     * @modify-date 2016年6月13日 上午12:01:28
     * @version 1.0
     * @return
     */
    @ResponseBody
    @RequestMapping("/highLevelCheck/{id}")
    public Map<String, Object> highLevelCheck(@PathVariable Integer id) {
        Map<String, Object> map = new HashMap<String, Object>();
        Integer companyId = WebUtils.getCurrentCompanyId();
        // 查询会员设置中的值，如果是购买型会员，购买方式为购买，如果是累积型会员，购买方式显示累积消费

        // 根据id获取当前会员的等级
        UsersFront usersFront = usersFrontServiceImpl.findUsersFrontById(id);
        CompanyMemberLevelConfig companyMemberLevelConfig = companyMemberLevelConfigServiceImpl.findCompanyMemberLevelConfigById(usersFront.getMemberId());
        // 获取当前公司下的会员最高等级
        Integer sequence2 = companyMemberLevelConfigServiceImpl.findHighLevel(companyId);
        // 外部
        // 购买型会员
        // 比较会员等级
        if (sequence2.equals(companyMemberLevelConfig.getSequence())) {
            map.put("flag", true);
        } else {
            map.put("flag", false);
        }
        map.put("memberStatus", usersFront.getMemberStatus());
        return map;
    }

    /**
     * Class Name: CompanyMemberConfigController.java
     * 
     * @Description: 查询所有的会员等级名称
     * @author xukaiqiang
     * @date 2016年5月27日 下午4:43:51
     * @modifier
     * @modify-date 2016年5月27日 下午4:43:51
     * @version 1.0
     * @param model
     * @param memberId
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryMemberlevelNames/{usersFrontId}")
    public List<CompanyMemberLevelConfig> queryMemberlevelNames(Model model, @PathVariable Integer usersFrontId) {
        List<CompanyMemberLevelConfig> list = new ArrayList<CompanyMemberLevelConfig>();
        // 根据usersFrontId获取用户的信息
        UsersFront usersFront = usersFrontServiceImpl.findUsersFrontById(usersFrontId);
        // 通过当前会员等级编号查找会员等级的sequence，然后查找大于sequence的所有等级的名称
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("memberLevelId", usersFront.getMemberId());
        map.put("companyId", WebUtils.getCurrentCompanyId());
        // 封装数据，传递到前台页面中
        // 查询当前公司会员的设置
        Integer companyId = WebUtils.getCurrentCompanyId();
        CompanyMemberConfig companyMemberConfig = companyMemberConfigServiceImpl.findCompanyMemberConfigByCompanyId(companyId);
        // 查询要升级的等级，判断新等级是否允许内部开通
        CompanyMemberLevelConfig companyMemberLevelConfigForOpenWay = companyMemberLevelConfigServiceImpl
                .findCompanyMemberLevelConfigById(usersFront.getMemberId());
        // 外部
        if (companyMemberLevelConfigForOpenWay.getOpenWay() == 0) {
            // 购买
            if (companyMemberConfig.getBecomeMember() == 0) {
                list = companyMemberLevelConfigServiceImpl.queryAllCompanyMemberLevelName(map);
            } else {
                // 累积
                list = companyMemberLevelConfigServiceImpl.queryAllCompanyMemberLevelNameNoSelf(map);
            }
        } else {
            // 内部
            list = companyMemberLevelConfigServiceImpl.queryAllCompanyMemberLevelNameNoSelf(map);
        }
        return list;
    }

    /**
     * Class Name: CompanyMemberConfigController.java
     * 
     * @Description: 查询当前会员等级下面的所有的有效期
     * @author xukaiqiang
     * @date 2016年5月27日 下午6:16:21
     * @modifier
     * @modify-date 2016年5月27日 下午6:16:21
     * @version 1.0
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryMemberLevelDetails/{memberLevelId}/{usersFrontId}")
    public Map<String, Object> queryMemberLevelDetails(@PathVariable Integer memberLevelId, @PathVariable Integer usersFrontId) {
        List<CompanyMemberLevelDetail> list = new ArrayList<CompanyMemberLevelDetail>();
        UsersFront usersFront = usersFrontServiceImpl.findUsersFrontById(usersFrontId);
        CompanyMemberLevelConfig companyMemberLevelConfig = companyMemberLevelConfigServiceImpl.findCompanyMemberLevelConfigById(memberLevelId);
        if (companyMemberLevelConfig.getOpenWay() != 1) {
            // 如果用户选择的memberId和数据库中的memberId相同，就查该等级下面>当前有效期的
            Map<String, Integer> map = new HashMap<String, Integer>();
            if (usersFront.getMemberId().equals(memberLevelId)) {
                List<CompanyMemberLevelDetail> details = new ArrayList<CompanyMemberLevelDetail>();
                map.put("usersFrontId", usersFrontId);
                map.put("memberLevelId", memberLevelId);
                // 如果当前会员等级有效期为终生，就不查了。
                if (usersFront.getMemberBuyLength() != -1) {
                    // 查该等级下面>当前有效期的
                    list = companyMemberLevelConfigServiceImpl.queryMemberLevelDetails(map);
                    // 查询会员等级下的所有有效期
                    details = companyMemberLevelConfigServiceImpl.queryMemberLevelAllDetails(map);
                    for (CompanyMemberLevelDetail companyMemberLevelDetail : details) {
                        if (companyMemberLevelDetail.getLength() == -1) {
                            CompanyMemberLevelDetail companyMemberLevelDetail2 = new CompanyMemberLevelDetail();
                            companyMemberLevelDetail2.setLength(-1);
                            companyMemberLevelDetail2.setName("终生");
                            list.add(companyMemberLevelDetail2);
                        }
                    }
                }
            } else {
                // 查询该等级下的所有有效期
                map.put("memberLevelId", memberLevelId);
                list = companyMemberLevelConfigServiceImpl.queryMemberLevelAllDetails(map);
            }
        }

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("list", list);
        result.put("openWay", companyMemberLevelConfig.getOpenWay());
        return result;
    }

    /**
     * Class Name: CompanyMemberConfigController.java
     * 
     * @Description: 会员升级欠缴和剩余费用计算
     * @author xukaiqiang
     * @date 2016年5月29日 下午5:07:08
     * @modifier
     * @modify-date 2016年5月29日 下午5:07:08
     * @version 1.0
     * @return
     */
    @ResponseBody
    @RequestMapping("/leaveAndOweCalc/{usersFrontId}/{memberId}/{memberBuyLength}")
    public UpgradeMemberVo leaveAndOweCalc(@PathVariable Integer usersFrontId, @PathVariable Integer memberId, @PathVariable Integer memberBuyLength) {
        UpgradeMemberVo upgradeMemberVo = new UpgradeMemberVo();
        // 查询当前公司会员的设置
        Integer companyId = WebUtils.getCurrentCompanyId();
        CompanyMemberConfig companyMemberConfig = companyMemberConfigServiceImpl.findCompanyMemberConfigByCompanyId(companyId);
        // 查询要升级的等级，判断新等级是否允许内部开通
        CompanyMemberLevelConfig companyMemberLevelConfigForOpenWay = companyMemberLevelConfigServiceImpl.findCompanyMemberLevelConfigById(memberId);
        // 如果是外部开通
        if (companyMemberLevelConfigForOpenWay.getOpenWay() == 0) {
            // 购买成为会员
            if (companyMemberConfig.getBecomeMember() == 0) {
                UsersFront usersFront = usersFrontServiceImpl.findUsersFrontById(usersFrontId);
                Map<String, Integer> map = new HashMap<String, Integer>();
                map.put("memberId", usersFront.getMemberId());
                map.put("memberBuyLength", usersFront.getMemberBuyLength());
                // 查询当前会员有效期价格
                Double currentPrice = companyMemberLevelConfigServiceImpl.queryMemberLevelValidPrice(map);
                // 计算剩余多少钱
                String memberEndTimeString = "";
                if (usersFront.getMemberEndTime() != null) {
                    memberEndTimeString = DatetimeUtil.DateToString(usersFront.getMemberEndTime(), "yyyy-MM-dd");
                }
                Double leaveMoney = 0d;
                if (currentPrice != null) {
                    // 如果会员为购买型会员，之前等级有效期为终生，终生还有价格，memberEndTimeString为空，现在升级为其他的
                    // 因为终生会员没有结束时间，所以无法计算剩下多少钱，所以剩余的钱就是购买时候的钱。这里暂时不考虑终生的。
                    // if(companyMemberConfig.getBecomeMember()==0&&memberEndTimeString==""&&usersFront.getMemberBuyLength()==-1){
                    // leaveMoney=currentPrice;
                    // }
                    // 如果会员等级详情被删除，得到当前的currentPrice为空的时候，剩余的钱为0d
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    if (memberEndTimeString != "") {
                        try {
                            leaveMoney = (DatetimeUtil.diffDate(DatetimeUtil.getCurrentDate("yyyy-MM-dd"), memberEndTimeString)) * (currentPrice / (DatetimeUtil
                                    .diffDate(sdf.format(DateUtil.addMonthsToDate(usersFront.getMemberEndTime(), -usersFront.getMemberBuyLength())),
                                            memberEndTimeString)));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        leaveMoney = 0d;
                    }
                } else {
                    leaveMoney = 0d;
                }
                // 获取当前用户选取的等级有效期价格
                Map<String, Integer> map2 = new HashMap<String, Integer>();
                map2.put("memberId", memberId);
                map2.put("memberBuyLength", memberBuyLength);

                // 当前选择的会员有效期的价格
                double currentSelectPrice = companyMemberLevelConfigServiceImpl.queryMemberLevelValidPrice(map2);
                // 欠缴费用
                double ownPrice = currentSelectPrice - leaveMoney;
                // 如果黄铜4级升级到白银1级，ownPrice<0,不退费,如果
                if (ownPrice < 0) {
                    ownPrice = 0;
                }
                // 格式化
                DecimalFormat decimalFormat = new DecimalFormat("#0.00");
                String leaveMoneyString = decimalFormat.format(Double.valueOf(leaveMoney));
                String ownPriceString = decimalFormat.format(Double.valueOf(ownPrice));
                upgradeMemberVo.setLeaveMoney(leaveMoneyString);
                upgradeMemberVo.setOwnPrice(ownPriceString);
                if (currentPrice == null) {
                    upgradeMemberVo.setOriginPrice(0);
                } else {
                    upgradeMemberVo.setOriginPrice(currentPrice);
                }
            } else {
                // 累积成为会员，生成0元订单
                upgradeMemberVo.setLeaveMoney("0");
                upgradeMemberVo.setOwnPrice("0");
                upgradeMemberVo.setOriginPrice(0);
            }
        } else {
            // 内部开通，生成0元订单
            upgradeMemberVo.setLeaveMoney("0");
            upgradeMemberVo.setOwnPrice("0");
            upgradeMemberVo.setOriginPrice(0);
        }
        return upgradeMemberVo;

    }

    @ResponseBody
    @RequestMapping("/createOrder")
    public String createOrder(UpgradeMemberVo upgradeMemberVo, HttpServletRequest request) {
        log.info("进入创建订单");
        // 成为会员方式
        Integer companyId = WebUtils.getCurrentCompanyId();
        CompanyMemberConfig companyMemberConfig = companyMemberConfigServiceImpl.findCompanyMemberConfigByCompanyId(companyId);
        // 查询要升级的等级，判断新等级是否允许内部开通，如果允许==1，生成0元订单，如果不允许，购买型会员计算等操作生成订单,累积型会员生成0元订单
        CompanyMemberLevelConfig companyMemberLevelConfigForOpenWay = companyMemberLevelConfigServiceImpl
                .findCompanyMemberLevelConfigById(upgradeMemberVo.getMemberId());
        UsersFront usersFront = usersFrontServiceImpl.findUsersFrontById(upgradeMemberVo.getUsersFrontId());
        Integer memberIdOld = usersFront.getMemberId();
        Integer memberBuyLengthOld = usersFront.getMemberBuyLength();
        CompanyMemberLevelConfig companyMemberLevelConfigForOpenWayOld = companyMemberLevelConfigServiceImpl.findCompanyMemberLevelConfigById(memberIdOld);
        // 如果是外部开通################################################
        if (companyMemberLevelConfigForOpenWay.getOpenWay() == 0) {
            // 如果是购买型会员################################################
            if (companyMemberConfig.getBecomeMember() == 0) {
                // 更新会员状态
                usersFront.setMemberBuyLength(upgradeMemberVo.getMemberBuyLength());
                // 如果允许内部开通，不设置，如果是终生也不设置
                Date newMemberEndTime = DateUtil.addMonthsToDate(new Date(), upgradeMemberVo.getMemberBuyLength());
                if (upgradeMemberVo.getMemberBuyLength().intValue() == -1) {
                    usersFront.setMemberEndTime(null);
                } else {
                    usersFront.setMemberEndTime(newMemberEndTime);
                }
                usersFront.setMemberId(upgradeMemberVo.getMemberId());
                usersFront.setMemberLevel(upgradeMemberVo.getMemberLevel());
                usersFrontServiceImpl.updateNull(usersFront);
                // 作废旧订单
                // 查询旧订单
                // Map<String, Object> map = new HashMap<String, Object>();
                // map.put("companyId", WebUtils.getCurrentCompanyId());
                // map.put("memberId", memberIdOld);
                // map.put("stuId", upgradeMemberVo.getUsersFrontId());
                // map.put("payStatusCode", "ORDER_FINISHED");
                // map.put("memberBuyLength", memberBuyLengthOld);
                // map.put("creator", WebUtils.getCurrentUserId(request));
                // StudentPayMaster studentPayMasterOld =
                // studentPayMasterServiceImpl.findOrder(map);
                // studentPayMasterOld.setPayStatusCode("ORDER_DELTED");/* 已作废
                // */
                // studentPayMasterServiceImpl.update(studentPayMasterOld);
                // 生成payOrder
                PayOrder payOrder = new PayOrder();
                payOrder.setUserId(upgradeMemberVo.getUsersFrontId());
                payOrder.setOrderNum(new StringBuffer(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())).toString());// *生成时间戳
                payOrder.setOrderTime(new Date());
                payOrder.setCommodityName("升级会员");// *商品名称
                payOrder.setOriginalPrice(upgradeMemberVo.getOriginPrice());// 原始价格
                payOrder.setPayPrice(upgradeMemberVo.getPayMoney());
                StringBuilder payMethod = new StringBuilder();
                String payMethodString = upgradeMemberVo.getPayMethod();
                String[] payMethodArray = payMethodString.split(",");
                for (int i = 0; i < payMethodArray.length; i++) {
                    if (payMethodArray[i].equals("pos")) {
                        payMethod.append("PAY_METHOD_POS,");
                        continue;
                    }
                    if (payMethodArray[i].equals("cash")) {
                        payMethod.append("PAY_METHOD_CASH,");
                        continue;
                    }
                    if (payMethodArray[i].equals("cheque")) {
                        payMethod.append("PAY_METHOD_CHECK,");
                        continue;
                    }
                    if (payMethodArray[i].equals("remit")) {
                        payMethod.append("PAY_METHOD_TRANSFE,");
                        continue;
                    }
                }
                payOrder.setPayType(payMethod.toString());
                payOrder.setMemberLength(usersFront.getMemberBuyLength());
                payOrder.setPayStatus("PAY_SUCCESS");// *支付状态
                payOrder.setCompanyId(WebUtils.getCurrentCompanyId());/* 公司ID */
                payOrder.setCommodityId(usersFront.getMemberId());// *商品id
                payOrder.setCollectionTime(new Date());// 收款时间
                payOrder.setCommdityType("MEMBER");// *商品类型
                payOrder.setMemberId(upgradeMemberVo.getMemberId());// *会员Id
                payOrder.setCollectionAmount(upgradeMemberVo.getPayMoney());// 收款金额
                payOrder.setSchoolId(WebUtils.getCurrentSchoolId());
                payOrderServiceImpl.insert(payOrder);
                // 生成新的订单student_pay_master
                StudentPayMaster studentPayMaster = new StudentPayMaster();
                studentPayMaster.setCompanyId(WebUtils.getCurrentCompanyId());
                studentPayMaster.setPayOrderId(payOrder.getId());
                // studentPayMaster.setRelatedPayId(studentPayMasterOld.getId());
                studentPayMaster.setMemberId(upgradeMemberVo.getMemberId());
                studentPayMaster.setCreateTime(new Date());
                studentPayMaster.setCreator(WebUtils.getCurrentUserId(request).toString());
                studentPayMaster.setApplyTime(new Date());
                studentPayMaster.setStuId(usersFront.getId());
                studentPayMaster.setPayStatusCode("ORDER_FINISHED");/* 已支付 */
                studentPayMaster.setApplyChannelCode("CHANNEL_OFFLINE");/* 线下报名 */
                studentPayMaster.setTrainingFee(
                        upgradeMemberVo.getPayMoney());/* 培训费用=班型定价 */
                studentPayMaster.setTotalAmount(upgradeMemberVo
                        .getPayMoney());/* 订单合计金额。订单合计=培训费用+代报费-优惠金额。 */
                studentPayMaster.setDeleteFlag(0);
                studentPayMaster.setMemberLength(upgradeMemberVo.getMemberBuyLength());
                studentPayMaster.setCommodityType("MEMBER");/* 课程 */
                studentPayMaster.setCommodityId(WebUtils.getCurrentCompanyId());
                studentPayMaster.setPaymentTypeCode("PAY_TYPE_ALL");/* 全额付款 */
                studentPayMaster.setSchoolId(WebUtils.getCurrentSchoolId());
                studentPayMasterServiceImpl.insert(studentPayMaster);
                log.info("生成studentPayMaster成功");
                // 生成流水
                CompanyCashFlow companyCashFlow = new CompanyCashFlow();
                companyCashFlow.setTradeReason("UPDATE_MEMBER");
                companyCashFlow.setTradeSource("PAY_OFFLINE");
                companyCashFlow.setTradeWay(payMethod.toString());
                companyCashFlow.setTradeAmount(
                        upgradeMemberVo.getPayMoney());/* 交易金额，为负数 */
                companyCashFlow.setTradeDate(new Date());
                companyCashFlow.setUserId(upgradeMemberVo.getUsersFrontId());
                companyCashFlow.setTradeResult("success");
                companyCashFlow.setCompanyId(WebUtils.getCurrentCompanyId());
                if (upgradeMemberVo.getPayMoney() < 0) {
                    companyCashFlow.setTradeType("TRADE_OUT");/* 转入，转出 */
                }
                if (upgradeMemberVo.getPayMoney() > 0 || upgradeMemberVo.getPayMoney() == 0) {
                    companyCashFlow.setTradeType("TRADE_IN");/* 转入，转出 */
                }
                companyCashFlow.setStuId(usersFront.getId());
                companyCashFlow.setPayMasterId(studentPayMaster.getId());
                companyCashFlowServiceImpl.insert(companyCashFlow);
            } else {
                // 累积成为会员################################################
                // 更新会员状态
                usersFront.setMemberId(upgradeMemberVo.getMemberId());
                usersFront.setMemberLevel(upgradeMemberVo.getMemberLevel());
                usersFront.setMemberBuyLength(-1);
                usersFront.setMemberEndTime(null);
                usersFrontServiceImpl.updateNull(usersFront);
                // 作废旧订单
                // Map<String, Object> map = new HashMap<String, Object>();
                // map.put("companyId", WebUtils.getCurrentCompanyId());
                // map.put("memberId", memberIdOld);
                // map.put("stuId", upgradeMemberVo.getUsersFrontId());
                // map.put("payStatusCode", "ORDER_FINISHED");
                // map.put("creator", WebUtils.getCurrentUserId(request));
                // map.put("memberBuyLength", -1);
                // StudentPayMaster studentPayMasterOld =
                // studentPayMasterServiceImpl.findOrder(map);
                // studentPayMasterOld.setPayStatusCode("ORDER_DELTED");/* 已作废
                // */
                // studentPayMasterServiceImpl.update(studentPayMasterOld);
                // 生成payOrder
                PayOrder payOrder = new PayOrder();
                payOrder.setUserId(upgradeMemberVo.getUsersFrontId());
                payOrder.setOrderNum(new StringBuffer(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())).toString());// *生成时间戳
                payOrder.setOrderTime(new Date());
                payOrder.setCommodityName("升级会员");// *商品名称
                payOrder.setOriginalPrice(0d);// 原始价格
                payOrder.setPayPrice(0d);
                payOrder.setPayType("PAY_METHOD_POS");
                payOrder.setPayStatus("PAY_SUCCESS");// *支付状态
                payOrder.setCompanyId(WebUtils.getCurrentCompanyId());/* 公司ID */
                payOrder.setCommodityId(usersFront.getMemberId());// *商品id
                payOrder.setCollectionTime(new Date());// 收款时间
                payOrder.setCommdityType("MEMBER");// *商品类型
                payOrder.setMemberId(upgradeMemberVo.getMemberId());// *会员Id
                payOrder.setCollectionAmount(0d);// 收款金额
                payOrder.setSchoolId(WebUtils.getCurrentSchoolId());
                payOrder.setMemberLength(-1);
                payOrderServiceImpl.insert(payOrder);
                // 生成新的订单student_pay_master
                StudentPayMaster studentPayMaster = new StudentPayMaster();
                studentPayMaster.setCompanyId(WebUtils.getCurrentCompanyId());
                studentPayMaster.setPayOrderId(payOrder.getId());
                // studentPayMaster.setRelatedPayId(studentPayMasterOld.getId());
                studentPayMaster.setMemberId(upgradeMemberVo.getMemberId());
                studentPayMaster.setCreateTime(new Date());
                studentPayMaster.setMemberLength(-1);
                studentPayMaster.setCreator(WebUtils.getCurrentUserId(request).toString());
                studentPayMaster.setApplyTime(new Date());
                studentPayMaster.setStuId(usersFront.getId());
                studentPayMaster.setPayStatusCode("ORDER_FINISHED");/* 已支付 */
                studentPayMaster.setApplyChannelCode("CHANNEL_OFFLINE");/* 线下报名 */
                studentPayMaster.setTrainingFee(0d);/* 培训费用=班型定价 */
                studentPayMaster
                        .setTotalAmount(0d);/* 订单合计金额。订单合计=培训费用+代报费-优惠金额。 */
                studentPayMaster.setDeleteFlag(0);
                studentPayMaster.setCommodityType("MEMBER");/* 课程 */
                studentPayMaster.setCommodityId(WebUtils.getCurrentCompanyId());
                studentPayMaster.setPaymentTypeCode("PAY_TYPE_ALL");/* 全额付款 */
                studentPayMaster.setSchoolId(WebUtils.getCurrentSchoolId());
                studentPayMasterServiceImpl.insert(studentPayMaster);
                log.info("生成studentPayMaster成功");
                // 生成流水
                CompanyCashFlow companyCashFlow = new CompanyCashFlow();
                companyCashFlow.setTradeReason("UPDATE_MEMBER");
                companyCashFlow.setTradeSource("PAY_OFFLINE");
                companyCashFlow.setTradeWay("PAY_METHOD_POS");
                companyCashFlow.setTradeAmount(0d);/* 交易金额，为负数 */
                companyCashFlow.setTradeDate(new Date());
                companyCashFlow.setUserId(upgradeMemberVo.getUsersFrontId());
                companyCashFlow.setTradeResult("success");
                companyCashFlow.setCompanyId(WebUtils.getCurrentCompanyId());
                companyCashFlow.setTradeType("TRADE_IN");/* 转入，转出 */
                companyCashFlow.setStuId(usersFront.getId());
                companyCashFlow.setPayMasterId(studentPayMaster.getId());
                companyCashFlowServiceImpl.insert(companyCashFlow);
            }
        } else {
            // 如果是内部开通################################################
            // 更新会员状态
            usersFront.setMemberId(upgradeMemberVo.getMemberId());
            usersFront.setMemberLevel(upgradeMemberVo.getMemberLevel());
            usersFront.setMemberBuyLength(-1);
            usersFront.setMemberEndTime(null);
            usersFrontServiceImpl.updateNull(usersFront);
            // 作废旧订单
            // Map<String, Object> map = new HashMap<String, Object>();
            // map.put("companyId", WebUtils.getCurrentCompanyId());
            // map.put("memberId", memberIdOld);
            // map.put("stuId", upgradeMemberVo.getUsersFrontId());
            // map.put("payStatusCode", "ORDER_FINISHED");
            // if(companyMemberLevelConfigForOpenWayOld.getOpenWay()==0){
            // map.put("memberBuyLength", memberBuyLengthOld);
            // }else{
            // map.put("memberBuyLength", -1);
            // }
            // map.put("creator", WebUtils.getCurrentUserId(request));
            // StudentPayMaster studentPayMasterOld =
            // studentPayMasterServiceImpl.findOrder(map);
            // studentPayMasterOld.setPayStatusCode("ORDER_DELTED");/* 已作废 */
            // studentPayMasterServiceImpl.update(studentPayMasterOld);
            // 生成payOrder
            PayOrder payOrder = new PayOrder();
            payOrder.setUserId(upgradeMemberVo.getUsersFrontId());
            payOrder.setOrderNum(new StringBuffer(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())).toString());// *生成时间戳
            payOrder.setOrderTime(new Date());
            payOrder.setCommodityName("升级会员");// *商品名称
            payOrder.setOriginalPrice(0d);// 原始价格
            payOrder.setPayPrice(0d);
            payOrder.setPayType("PAY_METHOD_POS");
            payOrder.setPayStatus("PAY_SUCCESS");// *支付状态
            payOrder.setCompanyId(WebUtils.getCurrentCompanyId());/* 公司ID */
            payOrder.setCommodityId(usersFront.getMemberId());// *商品id
            payOrder.setCollectionTime(new Date());// 收款时间
            payOrder.setCommdityType("MEMBER");// *商品类型
            payOrder.setMemberId(upgradeMemberVo.getMemberId());// *会员Id
            payOrder.setCollectionAmount(0d);// 收款金额
            payOrder.setMemberLength(-1);
            payOrder.setSchoolId(WebUtils.getCurrentSchoolId());
            payOrderServiceImpl.insert(payOrder);
            // 生成新的订单student_pay_master
            StudentPayMaster studentPayMaster = new StudentPayMaster();
            studentPayMaster.setCompanyId(WebUtils.getCurrentCompanyId());
            studentPayMaster.setPayOrderId(payOrder.getId());
            // studentPayMaster.setRelatedPayId(studentPayMasterOld.getId());
            studentPayMaster.setMemberId(upgradeMemberVo.getMemberId());
            studentPayMaster.setCreateTime(new Date());
            studentPayMaster.setCreator(WebUtils.getCurrentUserId(request).toString());
            studentPayMaster.setApplyTime(new Date());
            studentPayMaster.setStuId(usersFront.getId());
            studentPayMaster.setPayStatusCode("ORDER_FINISHED");/* 已支付 */
            studentPayMaster.setApplyChannelCode("CHANNEL_OFFLINE");/* 线下报名 */
            studentPayMaster.setTrainingFee(0d);/* 培训费用=班型定价 */
            studentPayMaster.setTotalAmount(0d);/* 订单合计金额。订单合计=培训费用+代报费-优惠金额。 */
            studentPayMaster.setDeleteFlag(0);
            studentPayMaster.setMemberLength(-1);
            studentPayMaster.setCommodityType("MEMBER");/* 课程 */
            studentPayMaster.setCommodityId(WebUtils.getCurrentCompanyId());
            studentPayMaster.setPaymentTypeCode("PAY_TYPE_ALL");/* 全额付款 */
            studentPayMaster.setSchoolId(WebUtils.getCurrentSchoolId());
            studentPayMasterServiceImpl.insert(studentPayMaster);
            log.info("生成studentPayMaster成功");
            // 生成流水
            CompanyCashFlow companyCashFlow = new CompanyCashFlow();
            companyCashFlow.setTradeReason("UPDATE_MEMBER");
            companyCashFlow.setTradeSource("PAY_OFFLINE");
            companyCashFlow.setTradeWay("PAY_METHOD_POS");
            companyCashFlow.setTradeAmount(0d);/* 交易金额，为负数 */
            companyCashFlow.setTradeDate(new Date());
            companyCashFlow.setUserId(upgradeMemberVo.getUsersFrontId());
            companyCashFlow.setTradeResult("success");
            companyCashFlow.setCompanyId(WebUtils.getCurrentCompanyId());
            companyCashFlow.setTradeType("TRADE_IN");/* 转入，转出 */
            companyCashFlow.setStuId(usersFront.getId());
            companyCashFlow.setPayMasterId(studentPayMaster.getId());
            companyCashFlowServiceImpl.insert(companyCashFlow);
        }
        return "success";
    }

    /**
     * 
     * @Description: 查询会员等级列表
     * @author: dongshuai
     * @date: 2016年5月19日
     * @param request
     * @param search
     * @return
     *
     */
    @ResponseBody
    @RequestMapping(value = "/selectVips", method = RequestMethod.POST)
    public JSONArray selectVips(HttpServletRequest request, CompanyMemberLevelConfig search) {
        JSONArray json = new JSONArray();
        search.setCompanyId(WebUtils.getCurrentCompanyId());
        List<CompanyMemberLevelConfig> list = companyMemberLevelConfigServiceImpl.queryMemberLevelList(search);
        json = JSONArray.fromObject(list);
        return json;
    }

    /**
     * 
     * @Description: 查询会员时长列表
     * @author: dongshuai
     * @date: 2016年5月19日
     * @param request
     * @param id
     * @param search
     * @return
     *
     */
    @ResponseBody
    @RequestMapping(value = "/selectVipDetail", method = RequestMethod.POST)
    public JSONArray selectVips(HttpServletRequest request, String id, CompanyMemberLevelDetail search) {
        JSONArray json = new JSONArray();
        search.setMemberId(Integer.parseInt(id));
        List<CompanyMemberLevelDetail> list = companyMemberLevelDetailServiceImpl.queryListByMemberId(search);
        json = JSONArray.fromObject(list);
        return json;
    }

    /**
     * 
     * @Description:查询vipPrice
     * @author: dongshuai
     * @date: 2016年5月24日
     * @param request
     * @param id
     * @return
     *
     */
    @ResponseBody
    @RequestMapping(value = "/selectvipPrice", method = RequestMethod.POST)
    public CompanyMemberLevelDetail selectvipPrice(HttpServletRequest request, String id) {
        CompanyMemberLevelDetail companyMemberLevelDetail = companyMemberLevelDetailServiceImpl.findCompanyMemberLevelDetailById(Integer.parseInt(id));
        return companyMemberLevelDetail;
    }

    @ResponseBody
    @RequestMapping(value = "/checkMemberLevelExist")
    public boolean checkMemberLevelExist() {
        Integer companyId = WebUtils.getCurrentCompanyId();
        return companyMemberLevelConfigServiceImpl.checkMemberLevelExist(companyId);
    }

    /**
     * 
     * @Description: 检查是否为会员
     * @author: dongshuai
     * @date: 2016年5月19日
     * @param request
     * @param mobile
     * @return
     *
     */
    @ResponseBody
    @RequestMapping(value = "/checkVipByMobile", method = RequestMethod.POST)
    public String checkVipByMobile(HttpServletRequest request, String mobile) {
        UsersFront uf = new UsersFront();
        uf.setCompanyId(WebUtils.getCurrentCompanyId());
        uf.setMobile(mobile);
        uf = usersFrontServiceImpl.findUsersFrontByMobile(uf);
        if (null != uf) {
            if (null != uf.getMemberId() && !"".equals(uf.getCompanyId()) && !"0".equals(uf)) {
                return "true";
            }
        } else {
            return "false";
        }
        return "false";
    }

    @ResponseBody
    @RequestMapping(value = "/checkVip", method = RequestMethod.POST)
    public String checkVip(HttpServletRequest request, String mobile, String username) {
        UsersFront uf = new UsersFront();
        uf.setCompanyId(WebUtils.getCurrentCompanyId());
        if (!"".equals(mobile) && null != mobile) {
            uf.setMobile(mobile);
        }
        if (!"".equals(username) && null != username) {
            uf.setUsername(username);
        }
        Integer count = usersFrontServiceImpl.findUsersFrontByMobileAndUsername(uf);
        if (count > 0) {
            return "true";
        }
        return "false";
    }

    /**
     * 
     * @Description: 保存学员，用户
     * @author: dongshuai
     * @date: 2016年5月19日
     * @param request
     * @param student
     * @param model
     * @return
     *
     */
    @ResponseBody
    @RequestMapping(value = "/saveStudent", method = RequestMethod.POST)
    public Student saveStudent(HttpServletRequest request, Student student, ModelMap model) {
        // 查询注册配置
        CompanyRegisterConfig crc = new CompanyRegisterConfig();
        crc.setCompanyId(WebUtils.getCurrentCompanyId());
        crc = companyRegisterConfigServiceImpl.queryByCompanyId(crc);
        if (null != student.getMobile()) {
            // String isExist = checkVipByMobile(request, student.getMobile());
            // if ("false".equals(isExist)) {
            student.setId(null);
            // }
            student.setCompanyId(WebUtils.getCurrentCompanyId());
            student.setSchoolId(WebUtils.getCurrentSchoolId());
            Student s = studentServiceImpl.findByMobile(student);
            Student s1 = studentServiceImpl.queryByUsername(student);

            if (s != null && s.getId() != null) {
                student.setUpdateTime(new Date());
                student.setUpdator(WebUtils.getCurrentUserId(request));

                student.setId(s.getId());
                studentServiceImpl.update(student);
            } else if (s1 != null && s1.getId() != null) {
                student.setUpdateTime(new Date());
                student.setUpdator(WebUtils.getCurrentUserId(request));

                student.setId(s1.getId());
                studentServiceImpl.update(student);
            } else {
                student.setCreateTime(new Date());
                student.setCreator(WebUtils.getCurrentUserId(request));
                student.setDeleteFlag(0);
                String password = student.getMobile();
                student.setPassword(new Md5Hash(password.substring(password.length() - 6)).toHex());

                studentServiceImpl.insert(student);
            }
        } else {// 用户名
            student.setCompanyId(WebUtils.getCurrentCompanyId());
            student.setSchoolId(WebUtils.getCurrentSchoolId());
            Student s = studentServiceImpl.queryByUsername(student);
            Users user = WebUtils.getCurrentUser(request);

            if (s != null && s.getId() != null) {
                student.setUpdateTime(new Date());
                student.setUpdator(user.getId());

                student.setId(s.getId());
                studentServiceImpl.update(student);
            } else {
                student.setCreateTime(new Date());
                student.setCreator(user.getId());
                student.setDeleteFlag(0);
                student.setPassword(new Md5Hash("111111").toHex());

                studentServiceImpl.insert(student);
            }
        }
        Student stemp = student;
        UsersFront stutemp = new UsersFront();
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
            e.printStackTrace();
        }
        // 添加用户
        UsersFront usersFront_new = new UsersFront();
        usersFront_new.setCompanyId(WebUtils.getCurrentCompanyId());
        if (null != student.getMobile()) {
            usersFront_new.setMobile(student.getMobile());
            usersFront_new = usersFrontServiceImpl.findUsersFrontByMobile(usersFront_new);
            if (usersFront_new == null && null != student.getUsername()) {
                usersFront_new = new UsersFront();
                usersFront_new.setCompanyId(WebUtils.getCurrentCompanyId());
                usersFront_new.setUsername(student.getUsername());
                usersFront_new = usersFrontServiceImpl.findUsersFrontByUsername(usersFront_new);
            }
        } else {
            if (null != student.getUsername()) {
                usersFront_new.setUsername(student.getUsername());
                usersFront_new = usersFrontServiceImpl.findUsersFrontByUsername(usersFront_new);
            }
        }
        UsersFront usersFront = new UsersFront();
        if (usersFront_new == null) {
            usersFront = new UsersFront();
            usersFront.setCompanyId(WebUtils.getCurrentCompanyId());
            usersFront.setMobile(student.getMobile());
            usersFront.setUsername(student.getUsername());
            if (null != crc && null != crc.getMobileFlag() && crc.getMobileFlag() == 1) {
                usersFront.setPassword(new Md5Hash(student.getMobile().substring(student.getMobile().length() - 6, student.getMobile().length())).toHex());
            }
            if (null != crc && null != crc.getMobileFlag() && crc.getMobileFlag() == 0 && null != crc.getUsernameFlag() && crc.getUsernameFlag() == 1) {
                usersFront.setPassword(new Md5Hash("111111").toHex());
            }
            if (null != crc && null != crc.getMobileFlag() && crc.getMobileFlag() == 0 && null != crc.getUsernameFlag() && crc.getUsernameFlag() == 0) {
                if (null != student && null != student.getMobile()) {
                    usersFront.setPassword(new Md5Hash(student.getMobile().substring(student.getMobile().length() - 6, student.getMobile().length())).toHex());
                } else if (null != student && null != student.getUsername()) {
                    usersFront.setPassword(new Md5Hash("111111").toHex());
                }
            }
            usersFront.setSchoolId(WebUtils.getCurrentUserSchoolId(request));
            usersFront.setStatus(1);
            Date now = new Date();
            usersFront.setRegistTime(now);
            usersFront.setRegistType(2);
            usersFront.setVipFlag(0);

            usersFrontServiceImpl.insert(usersFront);
            stutemp = usersFront;
        } else {
            if (null != student.getMobile() && !"".equals(student.getMobile())) {
                usersFront_new.setMobile(student.getMobile());
                if (null != student.getUsername() && !"".equals(student.getUsername())) {
                    usersFront_new.setUsername(student.getUsername());
                }
                usersFrontServiceImpl.update(usersFront_new);
                stutemp = usersFront_new;
            }
        }
        // Student student1=new Student();
        if (usersFront != null) {
            // if(null!=student && null!=student.getMobile()){
            // student1 = studentServiceImpl.findByMobile(student);
            // if(student1 ==null && null!=student.getUsername()){
            // student1=new Student();
            // student1 = studentServiceImpl.queryByUsername(student);
            // }
            // }else{
            // if(null!=student && null!=student.getUsername()){
            // student1 = studentServiceImpl.queryByUsername(student);
            // }
            // }
            stemp.setUserId(stutemp.getId());
            stemp.setUpdateTime(new Date());
            stemp.setUpdator(WebUtils.getCurrentUserId(request));
            studentServiceImpl.update(stemp);
        }
        return stemp;
    }

    /**
     * 
     * @Description: 保存会员，同时生成订单
     * @author: dongshuai
     * @date: 2016年5月19日
     * @param request
     * @param stuMobile
     * @param memberId
     * @param memberLevel
     * @param buyLength
     * @param buyName
     * @param pos
     * @param cash
     * @param cheque
     * @param remit
     * @return
     *
     */
    @ResponseBody
    @RequestMapping(value = "/insertCompanyMember", method = RequestMethod.POST)
    public String insertCompanyMember(HttpServletRequest request, String username, String stuMobile, String memberId, String memberLevel, String buyLength,
            String buyName, String pos, String cash, String cheque, String remit, String needPay, String detailId, String detailOpenWay) {

        Integer companyId = WebUtils.getCurrentCompanyId();
        Integer schoolId = WebUtils.getCurrentSchoolId();
        Integer userId = WebUtils.getCurrentUserId(request);
        int becomeMember = 0;
        CompanyMemberConfig cmc = companyMemberConfigServiceImpl.findCompanyMemberConfigByCompanyId(WebUtils.getCurrentCompanyId());
        if (null != cmc && null != cmc.getBecomeMember() && !"".equals(cmc.getBecomeMember())) {
            becomeMember = cmc.getBecomeMember();
        }
        boolean b = false;
        if (becomeMember == 1) {
            b = companyMemberConfigServiceImpl.saveMemberForZeroVip(username, companyId, schoolId, stuMobile, memberId, memberLevel, buyLength, buyName, pos,
                    cash, cheque, remit, needPay, userId, detailId);
        } else {
            b = companyMemberConfigServiceImpl.saveMemberForVip(username, companyId, schoolId, stuMobile, memberId, memberLevel, buyLength, buyName, pos, cash,
                    cheque, remit, needPay, userId, detailId, detailOpenWay);
        }

        if (!b) {
            return "false";
        }
        return "true";
    }

    @ResponseBody
    @RequestMapping(value = "/queryUsers", method = RequestMethod.POST)
    public Student queryUsers(String userId, ModelMap model) {
        Student student = studentServiceImpl.findStudentById(Integer.parseInt(userId));
        return student;
    }

    /**
     * 
     * Class Name: CompanyMemberConfigController.java
     * 
     * @Description: 查询会员ById
     * @author dongshuai
     * @date 2016年6月23日 上午10:26:06
     * @modifier
     * @modify-date 2016年6月23日 上午10:26:06
     * @version 1.0
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryMemberLevelById", method = RequestMethod.POST)
    public CompanyMemberLevelConfig queryMemberLevelById(String id) {
        CompanyMemberLevelConfig cmc = companyMemberLevelConfigServiceImpl.findCompanyMemberLevelConfigById(Integer.parseInt(id));
        CompanyMemberLevelConfig cmcNull = new CompanyMemberLevelConfig();
        cmcNull.setName("");
        return null != cmc ? cmc : cmcNull;
    }

    @ResponseBody
    @RequestMapping("/getVipStage")
    public Object getVipStage(HttpServletRequest request, String curorder) {
        Map<String, Object> result = new HashMap<String, Object>();
        Integer companyId = WebUtils.getCurrentCompanyId();

        CompanyMemberConfig cmc = getCompanyMemberConfig(companyId);
        result.put("cmc", cmc);

        if (cmc.getBuyWithIntegral() != null && cmc.getBuyWithIntegral().equals(1)) {
            CompanyIntegralConfig cic = companyIntegralConfigServiceImpl.findByCompanyId(companyId);
            result.put("cic", cic);
        }

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("companyId", companyId);
        param.put("cusorder", curorder);
        result.put("vipList", companyMemberConfigServiceImpl.findVipDateByCompanyId(param));

        return result;
    }

    private CompanyMemberConfig getCompanyMemberConfig(Integer companyId) {
        return companyMemberConfigServiceImpl.findByCompanyId(companyId);
    }

}
