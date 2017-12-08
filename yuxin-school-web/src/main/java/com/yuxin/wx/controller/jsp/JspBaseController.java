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
     *
     * @return
     */
    @RequestMapping(value = "/basicInformationCourse")
    public String basicInformationCourse(){
        return "schoolResources/basicInformationCourse";
    }
}
