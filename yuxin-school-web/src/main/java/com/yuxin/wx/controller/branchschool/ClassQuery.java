package com.yuxin.wx.controller.branchschool;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/classQuery")
public class ClassQuery {

    @RequestMapping(value = "/getClassList")
    public String getClassList(){

        return"berkeley/classQuery";
    }

}
