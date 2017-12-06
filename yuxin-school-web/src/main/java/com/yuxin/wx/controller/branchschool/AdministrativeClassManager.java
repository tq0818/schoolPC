package com.yuxin.wx.controller.branchschool;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/administrativeClassManager")
public class AdministrativeClassManager {

    @RequestMapping(value = "administrativeClass")
    public String gotoAdministrativeClassManager(){
        return "company/administrativeClass";
    }


}
