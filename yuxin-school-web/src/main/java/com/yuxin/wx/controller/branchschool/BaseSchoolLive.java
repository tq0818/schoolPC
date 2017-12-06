package com.yuxin.wx.controller.branchschool;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/baseSchool")
public class BaseSchoolLive {

    @RequestMapping(value = "/baseSchoolLive")
    public String baseSchoolLive(){
        return "berkeley/baseSchoolLive";
    }

    @RequestMapping(value = "/baseSchoolRecording")
    public String baseSchoolRecording(){
        return "berkeley/baseSchoolRecording";
    }
}
