package com.yuxin.wx.controller.branchschool;


import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/classManager")
public class ClassManager {

    /**
     * 获取直播课程列表
     * @param model
     * @param className
     * @param classStatus
     * @param classPerStatus
     * @return
     */
    @RequestMapping(value = "/getClassList")
    public String getClassList(Model model, String className, Integer classStatus, Integer classPerStatus){


        return "/berkeley/classManagement";
    }

    /**
     * 修改课程权限状态
     * @param classId
     * @param classPerStatus
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updatePerStatus")
    public String updatePerStatus(Integer classId, Integer classPerStatus){


        return "";
    }


    /**
     *获取课程表
     * @param classId
     * @return
     */
    @RequestMapping(value = "/getClassTable")
    public String getClassTable(Integer classId){

        return "";
    }

    /**
     * 添加课程
     * @return
     */
    @RequestMapping(value = "addClass")
    public String addClass(){
        return "";
    }

    /**
     * 搜索课程
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getClass")
    public List<T> findOtherClass(String className, String school){
        List<T> list = new ArrayList<>();
        return list;
    }


    /**
     * 查看课程详情，跳转到课程详情页面
     * @param classId
     * @param companyId
     * @return
     */
    @RequestMapping(value = "/getClassInfo")
    public String getClassInfo(Integer classId, Integer companyId){

        return "berkeley/berkeleyDetail";
    }

}
