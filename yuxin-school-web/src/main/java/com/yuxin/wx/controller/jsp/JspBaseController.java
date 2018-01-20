package com.yuxin.wx.controller.jsp;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.classes.IClassTypeOfBranchSchoolService;
import com.yuxin.wx.model.system.SysConfigDict;
import com.yuxin.wx.utils.WebUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 本controller只供前端人员写页面，仅开发阶段用
 * @author zengdeqiang
 *
 */
@Controller
@RequestMapping("/jsp")
public class JspBaseController {

    @Autowired
    private IClassTypeOfBranchSchoolService classTypeOfBranchSchoolService;

    /**
     * 分校开放课程管理
     * @return
     */
    @RequestMapping(value = "/OpenCourse")
    public String OpenCourse(){
        return "schoolResources/openCourse";
    }

    /**
     *课程基本信息
     * @return
     */
    @RequestMapping(value = "/basicInformationCourse")
    public String basicInformationCourse(){
        return "schoolResources/basicInformationCourse";
    }

    /**
     *财务-订单
     * @return
     */
    @RequestMapping(value = "/allOrder")
    public String allOrder(){
        return "system/allOrder";
    }

    /**
     *学校行政管理
     * @return
     */
    @RequestMapping(value = "/AdministrativeManagement")
    public String AdministrativeManagement(){
        return "query/administrativeManagement";
    }



    /**
     *财务-分校收入查询
     * @return
     */
    @RequestMapping(value = "/incomeQuery")
    public String incomeQuery(Model model){
        String isArea = WebUtils.getCurrentIsArea();
        if("0".equals(isArea)){
            List<SysConfigDict> areaList=classTypeOfBranchSchoolService.findAreaIds();
            model.addAttribute("firstItems", areaList);
        }
        model.addAttribute("isArea",isArea);
        return "system/incomeQuery";
    }

    /**
     * 查询分校信息
     * @param request
     * @param areaId
     * @return
     */
    @ResponseBody
    @RequestMapping("/selSchool")
    public JSONObject selSchool(HttpServletRequest request, String areaId){
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("parentId",areaId);
        List<SysConfigDict> schoolList = classTypeOfBranchSchoolService.findSchoolByCondition(paramMap);
        JSONObject json = new JSONObject();
        json.put("school", schoolList);
        return json;
    }

    /**
     *财务-老师收入查询
     * @return
     */
    @RequestMapping(value = "/teacherIncome")
    public String teacherIncome(Model model){
        String isArea = WebUtils.getCurrentIsArea();
        if("0".equals(isArea)){
            List<SysConfigDict> schoolList = classTypeOfBranchSchoolService.queryAllSchool();
            model.addAttribute("schoolList", schoolList);
        }
        model.addAttribute("isArea",isArea);
        return "system/teacherIncome";
    }


}
