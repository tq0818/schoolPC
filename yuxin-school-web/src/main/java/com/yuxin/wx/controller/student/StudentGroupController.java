package com.yuxin.wx.controller.student;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import com.yuxin.wx.api.company.ICompanyFunctionSetService;
import com.yuxin.wx.api.company.ICompanyMemberServiceService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.api.student.IStudentGroupService;
import com.yuxin.wx.api.student.IStudentService;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.CompanyFunctionSet;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.model.student.StudentGroup;
import com.yuxin.wx.utils.WebUtils;

/**
 * Controller of StudentGroup
 * 
 * @author chopin
 * @date 2016-7-29
 */
@Controller
@RequestMapping("/studentGroup")
public class StudentGroupController {

    @Autowired
    private IStudentGroupService studentGroupServiceImpl;
    @Autowired
    private ICompanyMemberServiceService companyMemberServiceServiceImpl;
    @Autowired
    private ICompanyServiceStaticService companyServiceStaticServiceImpl;
    @Autowired
    private ICompanyService companyServiceImpl;
    @Autowired
    private ICompanyFunctionSetService companyFunctionSetImpl;
    @Autowired
    private ICompanyFunctionSetService companyFunctionSetServiceImpl;
    @Autowired
    private IStudentService studentServiceImpl;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model, StudentGroup search) {
        if (search == null) {
            search = new StudentGroup();
            // search.setPageSize(20);
        }
        model.addAttribute("list", studentGroupServiceImpl.findStudentGroupByPage(search));
        return "studentGroup/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(StudentGroup StudentGroup) {
        studentGroupServiceImpl.insert(StudentGroup);
        return "redirect:/studentGroup";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(StudentGroup StudentGroup) {
        studentGroupServiceImpl.update(StudentGroup);
        return "redirect:/studentGroup";
    }

    @RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
    public String del(Model model, @PathVariable Integer id) {
        studentGroupServiceImpl.deleteStudentGroupById(id);
        return "redirect:/studentGroup";
    }

    @ResponseBody
    @RequestMapping(value = "/{id:\\d+}", method = RequestMethod.GET)
    public StudentGroup getJson(Model model, @PathVariable Integer id) {
        return studentGroupServiceImpl.findStudentGroupById(id);
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
     * Class Name: StudentGroupController.java
     * 
     * @Description: 学员分组设置（入口）
     * @author dongshuai
     * @date 2016年7月29日 下午6:37:06
     * @modifier
     * @modify-date 2016年7月29日 下午6:37:06
     * @version 1.0
     * @param model
     * @return
     */
    @RequestMapping(value = "/setStudentGroup")
    public String setStudentGroup(Model model) {
        // 获取公司配置
        int companyId = WebUtils.getCurrentCompanyId();
        Company company = companyServiceImpl.findCompanyById(companyId);
        CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
        CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
        model.addAttribute("company", company);
        model.addAttribute("cms", cms);
        model.addAttribute("css", css);

        CompanyFunctionSet companyFunctionSet = new CompanyFunctionSet();
        companyFunctionSet.setCompanyId(WebUtils.getCurrentCompanyId());
        companyFunctionSet.setFunctionCode("STUDENT_GROUP");
        List<CompanyFunctionSet> companyFunctionSetList = companyFunctionSetServiceImpl.findCompanyFunctionSetByPage(companyFunctionSet);
        CompanyFunctionSet search = companyFunctionSetList != null && companyFunctionSetList.size() > 0 ? companyFunctionSetList.get(0) : null;
        if (null == search) {
            CompanyFunctionSet cfs = initStudentGroup();
            model.addAttribute("cfs", cfs);
        } else {
            model.addAttribute("cfs", search);
        }

        // -----2016-9-20 查询学生max 是否开启 (1 开启 0 关闭)
        CompanyFunctionSet cfs = new CompanyFunctionSet();
        cfs.setCompanyId(WebUtils.getCurrentCompanyId());
        cfs.setStatus("1");
        cfs.setFunctionCode("COMPANY_STUDENT_BIND");
        cfs = companyFunctionSetServiceImpl.getCompanyFunctionSet(cfs);
        if (cfs != null) {
            model.addAttribute("bind_status", "1");
        } else {
            model.addAttribute("bind_status", "0");
        }
        return "student/studentGroup";
    }

    /**
     * 
     * Class Name: StudentGroupController.java
     * 
     * @Description: 设置学员分组开关
     * @author dongshuai
     * @date 2016年8月1日 上午11:03:25
     * @modifier
     * @modify-date 2016年8月1日 上午11:03:25
     * @version 1.0
     * @param status
     * @param json
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/setGroupStatus", method = RequestMethod.POST)
    public String setGroupStatus(String status, CompanyFunctionSet search, String json) {
        CompanyFunctionSet companyFunctionSet = new CompanyFunctionSet();
        companyFunctionSet.setCompanyId(WebUtils.getCurrentCompanyId());
        companyFunctionSet.setFunctionCode("STUDENT_GROUP");
        List<CompanyFunctionSet> companyFunctionSetList = companyFunctionSetServiceImpl.findCompanyFunctionSetByPage(companyFunctionSet);
        search = companyFunctionSetList != null && companyFunctionSetList.size() > 0 ? companyFunctionSetList.get(0) : null;
        if (null == search) {
            initStudentGroup();
        } else {
            search.setStatus(status);
            companyFunctionSetImpl.update(search);
        }
        json = "{'msg':'success'}";
        return json;
    }

    /**
     * 
     * Class Name: StudentGroupController.java
     * 
     * @Description: 学员分组初始化
     * @author dongshuai
     * @date 2016年8月1日 上午11:19:55
     * @modifier
     * @modify-date 2016年8月1日 上午11:19:55
     * @version 1.0
     * @return
     */
    public CompanyFunctionSet initStudentGroup() {
        CompanyFunctionSet cfs = new CompanyFunctionSet();
        cfs.setCompanyId(WebUtils.getCurrentCompanyId());
        cfs.setFunctionCode("STUDENT_GROUP");
        cfs.setFunctionName("学员分组");
        cfs.setContent("0：关闭；1：开启");
        cfs.setStatus("0");
        companyFunctionSetImpl.insert(cfs);
        return cfs;
    }

    /**
     * 
     * Class Name: StudentGroupController.java
     * 
     * @Description: 查询学员一级分组
     * @author dongshuai
     * @date 2016年8月1日 下午2:26:12
     * @modifier
     * @modify-date 2016年8月1日 下午2:26:12
     * @version 1.0
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectStudentGroup1", method = RequestMethod.POST)
    public List<StudentGroup> selectStudentGroup1() {
        StudentGroup sg = new StudentGroup();
        sg.setCompanyId(WebUtils.getCurrentCompanyId());
        return studentGroupServiceImpl.findStudentGroup1ByCompanyId(sg);
    }

    /**
     * 
     * Class Name: StudentGroupController.java
     * 
     * @Description: 查询学员二级分组
     * @author dongshuai
     * @date 2016年8月1日 下午2:29:07
     * @modifier
     * @modify-date 2016年8月1日 下午2:29:07
     * @version 1.0
     * @param pId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectStudentGroup2/{pId}", method = RequestMethod.POST)
    public List<StudentGroup> selectStudentGroup2(@PathVariable String pId) {
        StudentGroup sg = new StudentGroup();
        sg.setCompanyId(WebUtils.getCurrentCompanyId());
        sg.setParentId(Integer.parseInt(pId));
        return studentGroupServiceImpl.findStudentGroup2ByCompanyIdAndPId(sg);

    }

    @ResponseBody
    @RequestMapping("/selGroupStu")
    public Object selGroupStu(Integer groupOneId, Integer groupTwoId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("companyId", WebUtils.getCurrentCompanyId());
        map.put("groupOneId", groupOneId);
        map.put("groupTwoId", groupTwoId);
        Integer count = studentServiceImpl.findByGroupId(map);
        map.clear();
        map.put("count", count);
        return map;
    }

    /**
     * 
     * Class Name: StudentGroupController.java
     * 
     * @Description: 添加分组（一级，二级）
     * @author dongshuai
     * @date 2016年8月1日 下午3:39:37
     * @modifier
     * @modify-date 2016年8月1日 下午3:39:37
     * @version 1.0
     * @param request
     * @param sg
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addStudentGroup", method = RequestMethod.POST)
    public String addStudentGroup(HttpServletRequest request, StudentGroup sg) {
        sg.setCompanyId(WebUtils.getCurrentCompanyId());
        sg.setCreateTime(new Date());
        sg.setUpdator(WebUtils.getCurrentUserId(request));
        studentGroupServiceImpl.insert(sg);
        return "{'msg':'success'}";
    }

    /**
     * 
     * Class Name: StudentGroupController.java
     * 
     * @Description: 修改分组
     * @author dongshuai
     * @date 2016年8月1日 下午3:44:19
     * @modifier
     * @modify-date 2016年8月1日 下午3:44:19
     * @version 1.0
     * @param request
     * @param sg
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/editStudentGroup", method = RequestMethod.POST)
    public String editStudentGroup(HttpServletRequest request, StudentGroup sg) {
        StudentGroup search = studentGroupServiceImpl.findStudentGroupById(sg.getId());
        if (null == search) {
            return "{'msg':'error'}";
        } else {
            search.setGroupName(sg.getGroupName());
            search.setUpdator(WebUtils.getCurrentUserId(request));
            studentGroupServiceImpl.update(search);
        }
        return "{'msg':'success'}";

    }

    /**
     * 
     * Class Name: StudentGroupController.java
     * 
     * @Description: 删除分组
     * @author dongshuai
     * @date 2016年8月1日 下午3:52:37
     * @modifier
     * @modify-date 2016年8月1日 下午3:52:37
     * @version 1.0
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delStudentGroup/{id}", method = RequestMethod.POST)
    public String delStudentGroup(@PathVariable String id) {
        studentGroupServiceImpl.deleteStudentGroupById(Integer.parseInt(id));
        // 二级删除
        studentGroupServiceImpl.deleteStudentGroupByParentId(Integer.parseInt(id));
        return "{'msg':'success'}";
    }

    /**
     * 
     * Class Name: StudentGroupController.java
     * 
     * @Description: 检查重名
     * @author dongshuai
     * @date 2016年8月1日 下午9:17:41
     * @modifier
     * @modify-date 2016年8月1日 下午9:17:41
     * @version 1.0
     * @param sg
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/checkStudentGroup", method = RequestMethod.POST)
    public boolean checkStudentGroup(StudentGroup sg) {
        sg.setCompanyId(WebUtils.getCurrentCompanyId());
        Integer count = 0;
        if (null != sg.getParentId() && !"".equals(sg.getParentId())) { // 二级
            count = studentGroupServiceImpl.checkGroupName2(sg);
        } else { // 一级
            count = studentGroupServiceImpl.checkGroupName1(sg);
        }
        return count > 0 ? false : true;
    }
}
