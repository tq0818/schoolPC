package com.yuxin.wx.controller.system;

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

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.classes.IClassTypeService;
import com.yuxin.wx.api.company.ICompanyFunctionSetService;
import com.yuxin.wx.api.company.ICompanyMemberServiceService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.api.student.IStudentPayMasterService;
import com.yuxin.wx.api.student.IStudentService;
import com.yuxin.wx.api.system.ISysConfigCampusService;
import com.yuxin.wx.api.system.ISysConfigClassroomService;
import com.yuxin.wx.api.system.ISysConfigDivisionService;
import com.yuxin.wx.api.system.ISysConfigItemService;
import com.yuxin.wx.api.system.ISysConfigSchoolService;
import com.yuxin.wx.api.system.ISysConfigTeacherService;
import com.yuxin.wx.common.JsonMsg;
import com.yuxin.wx.model.company.CompanyFunctionSet;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.model.system.SysConfigDivision;
import com.yuxin.wx.model.system.SysConfigSchool;
import com.yuxin.wx.utils.PropertiesUtil;
import com.yuxin.wx.utils.WebUtils;

/**
 * Controller of SysConfigSchool
 * 
 * @author wang.zx
 * @date 2014-12-5
 */
@Controller
@RequestMapping("/sysConfigSchool")
public class SysConfigSchoolController {

    @Autowired
    private IStudentPayMasterService studentPayMasterServiceImpl;

    @Autowired
    private ISysConfigDivisionService sysConfigDivisionServiceImpl;

    @Autowired
    private ICompanyService companyServiceImpl;

    @Autowired
    private IClassTypeService classTypeServiceImpl;

    @Autowired
    private IStudentService studentServiceImpl;

    @Autowired
    private ICompanyMemberServiceService companyMemberServiceServiceImpl;

    @Autowired
    private ICompanyServiceStaticService companyServiceStaticServiceImpl;

    @Autowired
    private ISysConfigClassroomService sysConfigClassroomServiceImpl;

    @Autowired
    private ISysConfigCampusService sysConfigCampusServiceImpl;

    @Autowired
    private ISysConfigItemService sysConfigItemServiceImpl;

    @Autowired
    private ISysConfigTeacherService sysConfigTeacherServiceImpl;

    @Autowired
    private ISysConfigSchoolService sysConfigSchoolServiceImpl;

    @Autowired
    private ICompanyFunctionSetService companyFunctionSetServiceImpl;

    @Autowired
    private PropertiesUtil propertiesUtil;

    private Log log = LogFactory.getLog("log");

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model, SysConfigSchool search) {
        if (search == null) {
            search = new SysConfigSchool();
            // search.setPageSize(20);
        }
        model.addAttribute("list", sysConfigSchoolServiceImpl.findSysConfigSchoolByPage(search));
        return "sysConfigSchool/list";
    }

    /**
     * 
     * Class Name: SysConfigSchoolController.java
     * 
     * @Description: 添加分校
     * @author 周文斌
     * @date 2015-5-9 下午5:55:04
     * @version 1.0
     * @param sysConfigSchool
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public JSONObject add(HttpServletRequest request, SysConfigSchool sysConfigSchool, String county, String porv, String city) {
        JSONObject json = new JSONObject();
        Integer userId = WebUtils.getCurrentUserId(request);
        Integer companyId = WebUtils.getCurrentCompanyId();
        sysConfigSchool.setCompanyId(companyId);

        try {
            // 查询当前域名是否存在
            if (sysConfigSchool.getSuffix() != null && !sysConfigSchool.getSuffix().equals("")) {
                List<Integer> sid = sysConfigSchoolServiceImpl.findExistBySuffix(sysConfigSchool);
                if (sid != null && sid.size() > 0) {
                    json.put(JsonMsg.MSG, "当前分校域名已存在");
                    return json;
                }
            }

            String code = getCode(county, porv, city);
            sysConfigSchool.setXzqhCode(code);
            sysConfigSchool.setCreateTime(new Date());
            sysConfigSchool.setCreator(userId);
            sysConfigSchool.setDelFlag(0);
            sysConfigSchool.setUpdateTime(new Date());
            sysConfigSchool.setUpdator(userId);
            sysConfigSchoolServiceImpl.insert(sysConfigSchool);

            // 更改服务表
            CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);

            css.setSchoolNum(css.getSchoolNum() + 1);
            companyServiceStaticServiceImpl.update(css);
            json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
            return json;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            json.put(JsonMsg.MSG, "添加分校时出现错误");
            return json;
        }
    }

    /**
     * 
     * Class Name: SysConfigSchoolController.java
     * 
     * @Description: 更改分校名称
     * @author 周文斌
     * @date 2015-5-9 下午5:50:44
     * @version 1.0
     * @param sysConfigSchool
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public JSONObject update(HttpServletRequest request, SysConfigSchool sysConfigSchool, String county, String porv, String city) {
        JSONObject json = new JSONObject();
        Integer userId = WebUtils.getCurrentUserId(request);
        Integer companyId = WebUtils.getCurrentCompanyId();
        sysConfigSchool.setCompanyId(companyId);
        SysConfigSchool scss = sysConfigSchoolServiceImpl.findSysConfigSchoolById(sysConfigSchool.getId());

        try {
            // 查询当前域名是否存在
            if (sysConfigSchool.getSuffix() != null && !sysConfigSchool.getSuffix().equals("")) {
                List<Integer> sid = sysConfigSchoolServiceImpl.findExistBySuffix(sysConfigSchool);
                if (sid != null && sid.size() > 0 && scss.getSuffix() != null && !scss.getSuffix().equals(sysConfigSchool.getSuffix())) {
                    json.put(JsonMsg.MSG, "当前分校域名已存在");
                    return json;
                }
            }

            String code = getCode(county, porv, city);
            sysConfigSchool.setXzqhCode(code);

            sysConfigSchool.setUpdateTime(new Date());
            sysConfigSchool.setUpdator(userId);
            sysConfigSchoolServiceImpl.update(sysConfigSchool);
            json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
            return json;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            json.put(JsonMsg.MSG, "修改分校时出现错误");
            return json;
        }
    }

    @RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
    public String del(Model model, @PathVariable Integer id) {
        sysConfigSchoolServiceImpl.deleteSysConfigSchoolById(id);
        return "redirect:/sysConfigSchool";
    }

    @ResponseBody
    @RequestMapping(value = "/{id:\\d+}", method = RequestMethod.POST)
    public SysConfigSchool getJson(Model model, @PathVariable Integer id) {
        return sysConfigSchoolServiceImpl.findSysConfigSchoolById(id);
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
     * 
     * Class Name: SysConfigSchoolController.java
     * 
     * @Description: 分校页面
     * @author 周文斌
     * @date 2015-4-30 下午5:37:16
     * @version 1.0
     * @param model
     * @return
     */
    @RequestMapping("/school")
    public String school(Model model, HttpServletRequest request) {
        // 获得公司id
        Integer companyId = WebUtils.getCurrentCompanyId();
        Integer schoolId = WebUtils.getCurrentUserSchoolId(request);
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("companyId", companyId);
        param.put("schoolId", schoolId);
        // 查询所属分校根据 公司id 和
        SysConfigSchool scs = sysConfigSchoolServiceImpl.findSchoolByMap(param);
        // 根据公司id 查询 服务
        // 公司拥有的服务
        CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
        // 公司 已使用的服务
        CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);

        model.addAttribute("cms", cms);
        model.addAttribute("css", css);
        model.addAttribute("scs", scs);
        return "/resource/school/school";
    }

    /**
     * 
     * Class Name: SysConfigSchoolController.java
     * 
     * @Description: 加载分校 各个信息数量
     * @author 周文斌
     * @date 2015-5-4 上午10:20:46
     * @version 1.0
     * @param schoolId
     * @return
     */
    @ResponseBody
    @RequestMapping("/ajaxSel")
    public JSONObject ajaxSel(HttpServletRequest request, Integer schoolId) {
        JSONObject json = new JSONObject();
        // 统计 教务人员数量 根据分校 id
        Integer senate = sysConfigTeacherServiceImpl.findSenateBySchoolId(schoolId);
        json.put("senate", senate);
        // 统计教师数量
        Integer teacher = sysConfigTeacherServiceImpl.findTeacherBySchoolId(schoolId);
        json.put("teacher", teacher);
        // 统计项目数
        Integer project = sysConfigItemServiceImpl.findProjectBySchoolId(schoolId);
        json.put("project", project);
        // 统计校区 数
        Integer campus = sysConfigCampusServiceImpl.findCampusBySchoolId(schoolId);
        json.put("campus", campus);
        // 统计 教室数量
        Integer classroom = sysConfigClassroomServiceImpl.findClassroomBySchoolId(schoolId);
        json.put("classroom", classroom);
        // 统计学生数量
        Integer student = studentPayMasterServiceImpl.findStuCountBySchoolId(WebUtils.getCurrentCompanyId(), schoolId);
        json.put("student", student);
        return json;
    }

    /**
     * ajax获取分校列表json
     * 
     * @param model
     * @param search
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getSchoolJson", method = RequestMethod.POST)
    public List<SysConfigSchool> getSchoolJson(Model model, SysConfigSchool search) {
        return sysConfigSchoolServiceImpl.findAllSysConfigSchool(WebUtils.getCurrentCompanyId());
    }

    @ResponseBody
    @RequestMapping("/sel")
    public JSONObject sel(HttpServletRequest request) {
        Integer companyId = WebUtils.getCurrentCompanyId();
        // 公司拥有的服务
        CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
        // 公司 已使用的服务
        CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);

        /* Company company = companyServiceImpl.findCompanyById(companyId); */
        JSONObject json = new JSONObject();
        /* if(company.getStatus() == 3){ */
        if ((cms.getSchoolTotal() - css.getSchoolNum()) > 0) {
            json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
            return json;
        } else {
            json.put(JsonMsg.MSG, JsonMsg.ERROR);
            return json;
        } /*
           * }else{ json.put(JsonMsg.MSG, "authority"); return json; }
           */
    }

    /**
     * 
     * Class Name: SysConfigItemController.java
     * 
     * @Description: 根据公司id 检查学校名字
     * @author 周文斌
     * @date 2015-5-7 下午4:45:02
     * @version 1.0
     * @param search
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/checkName", method = RequestMethod.POST)
    public String checkName(String schoolName, HttpServletRequest request) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("schoolName", schoolName);
        param.put("companyId", WebUtils.getCurrentCompanyId());
        Integer firstItems = sysConfigSchoolServiceImpl.findUnquieSchool(param);
        if (firstItems != null && firstItems > 0) {
            return "false";
        }
        return "true";
    }

    @ResponseBody
    @RequestMapping("/changeStatus")
    public JSONObject changeStatus(SysConfigSchool sysConfigSchool, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        Integer userId = WebUtils.getCurrentUserId(request);
        Integer companyId = WebUtils.getCurrentCompanyId();
        try {
            if (sysConfigSchool.getDelFlag() == 1) {
                sysConfigSchool.setDelFlag(0);
                sysConfigSchool.setUpdateTime(new Date());
                sysConfigSchool.setUpdator(userId);
                sysConfigSchoolServiceImpl.update(sysConfigSchool);

                json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
                return json;
            } else {
                // 查询 是否还有 在售 班型
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("schoolId", sysConfigSchool.getId());
                param.put("companyId", companyId);
                List<String> nameList = classTypeServiceImpl.findNameById(param);
                if (nameList != null && nameList.size() > 0) {
                    json.put(JsonMsg.MSG, nameList);
                    return json;
                } else {
                    sysConfigSchool.setDelFlag(1);
                    sysConfigSchool.setUpdateTime(new Date());
                    sysConfigSchool.setUpdator(userId);
                    sysConfigSchoolServiceImpl.update(sysConfigSchool);
                    json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
                    return json;
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            json.put(JsonMsg.MSG, JsonMsg.ERROR);
            return json;
        }
    }

    /**
     * 
     * Class Name: SysConfigSchoolController.java
     * 
     * @Description: 编辑时查询分校信息
     * @author 周文斌
     * @date 2016-7-7 上午11:45:18
     * @version 1.0
     * @param model
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/getAddressById")
    public String getAddressById(Model model, HttpServletRequest request, Integer id) {
        try {
            SysConfigSchool school = sysConfigSchoolServiceImpl.findSysConfigSchoolById(id);
            if (school == null) {
                school = new SysConfigSchool();
            } else {
                if (school.getXzqhCode() != null) {
                    SysConfigDivision address = getEntity(school.getXzqhCode());
                    if (address != null) {
                        model.addAttribute("address", address.getName());
                        SysConfigDivision city = getParentEntity(address.getParentId());
                        if (city != null) {
                            model.addAttribute("city", city.getName());
                        }
                        SysConfigDivision prov = getParentEntity(city.getParentId());
                        if (prov != null) {
                            model.addAttribute("province", prov.getName());
                        }
                    }
                }
            }
            CompanyFunctionSet companyFunctionSet = new CompanyFunctionSet();
            companyFunctionSet.setCompanyId(WebUtils.getCurrentCompanyId());
            companyFunctionSet.setFunctionCode("COMPANY_MULTI_SCHOOL");
            List<CompanyFunctionSet> companyFunctionSetList = companyFunctionSetServiceImpl.findCompanyFunctionSetByPage(companyFunctionSet);
            CompanyFunctionSet cfs = companyFunctionSetList != null && companyFunctionSetList.size() > 0 ? companyFunctionSetList.get(0) : null;

            String domain = companyServiceImpl.findDomainById(WebUtils.getCurrentCompanyId());

            model.addAttribute("domain", domain);
            model.addAttribute("cfs", cfs);
            model.addAttribute("imgpath", propertiesUtil.getProjectImageUrl());
            model.addAttribute("school", school);
        } catch (Exception e) {
            log.error("查询分校信息错误," + e.getMessage(), e);
            e.printStackTrace();
        }
        return "system/create";
    }

    private String getCode(String county, String prov, String city) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("dname", county);
        param.put("cname", city);
        param.put("sname", prov);
        return sysConfigDivisionServiceImpl.findXcode(param);
    }

    private SysConfigDivision getEntity(String code) {
        return sysConfigDivisionServiceImpl.findEntity(code);
    }

    private SysConfigDivision getParentEntity(Integer parentId) {
        return sysConfigDivisionServiceImpl.findParentEntity(parentId);
    }
}
