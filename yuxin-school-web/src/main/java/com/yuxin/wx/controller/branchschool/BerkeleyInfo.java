package com.yuxin.wx.controller.branchschool;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/berkeleyInfo")
public class BerkeleyInfo {

    @RequestMapping(value = "/goToBerkeleyInfo")
    public  String goToBerkeleyInfo(){
        return "berkeley/berkeleyDetail";
    }
}
