package com.yuxin.wx.controller.jsp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 本controller只供前端人员写页面，仅开发阶段用
 * @author zengdeqiang
 *
 */
@Controller
@RequestMapping("/jsp")
public class JspBaseController {
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
     *财务-分校收入查询
     * @return
     */
    @RequestMapping(value = "/incomeQuery")
    public String incomeQuery(){
        return "system/incomeQuery";
    }

    /**
     *财务-老师收入查询
     * @return
     */
    @RequestMapping(value = "/teacherIncome")
    public String teacherIncome(){
        return "system/teacherIncome";
    }

}
