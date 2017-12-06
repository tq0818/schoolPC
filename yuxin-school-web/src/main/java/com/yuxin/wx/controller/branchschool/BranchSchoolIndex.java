package com.yuxin.wx.controller.branchschool;


import com.yuxin.wx.api.system.ISysConfigDictService;
import com.yuxin.wx.common.PageFinder2;
import com.yuxin.wx.model.system.SysConfigDict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/berkeley")
public class BranchSchoolIndex {
	 @Autowired
	 private ISysConfigDictService sysConfigDictServiceImpl;

	 /*@Autowired
	 private ICompanyManageService companyManageServiceImpl;*/
    /**
     * 跳转到订单列表
     *
     * @return
     */
    @RequestMapping(value = "/berkeleyOrder")
    public String gotobranchSchoolOrder() {
        return "/berkeley/berkeleyOrder";
    }
    /**
     * 跳转到老师管理
     *
     * @return
     */
    @RequestMapping(value = "/teacherManagement")
    public String gototeacherManage(){

        return "berkeley/teacherManagement";
    }
    /**
     * 跳转到添加老师页面
     *
     * @return
     */
    @RequestMapping(value = "/addTeacher")
    public String gotoaddTeacher(){

        return "berkeley/addTeacher";
    }
    /**
     * 跳转到权限管理
     *
     * @return
     */
    @RequestMapping(value = "/permissionManagement")
    public String gotopermissionManagement(){

        return "/berkeley/permissionManagement";
    }
    /**
     * 跳转到添加用户页面
     *
     * @return
     */
    @RequestMapping(value = "/addUsers")
    public String gotoaddUsers(){

        return "/berkeley/addUsers";
    }
    /**
     * 跳转到计算资源管理
     *
     * @return
     */
    @RequestMapping(value = "/resourceManagement")
    public String gotoresourceManagement(){

        return "/berkeley/resourceManagement";
    }

    /**
     * 跳转到服务管理
     *
     * @return
     */
    @RequestMapping(value = "serviceManagement")
    public String gotoserviceManagement(){

        return "/berkeley/serviceManagement";
    }
    /**
     * 跳转到分校首页
     *
     * @return
     */
    @RequestMapping(value = "berkeleyIndex")
    public String berkeleyIndex(Model model){
    	//查询学校所在区域
        SysConfigDict areaDict = new SysConfigDict();
        areaDict.setDictCode("EDU_SCHOOL_AREA");
        List<SysConfigDict> areas = sysConfigDictServiceImpl.queryConfigDictListByDictCode(areaDict);
        areaDict.setDictCode("SCHOOL_PROPERTY");
        List<SysConfigDict> schoolPros = sysConfigDictServiceImpl.queryConfigDictListByDictCode(areaDict);
        model.addAttribute("areas", areas);
        model.addAttribute("schoolPros",schoolPros);
        return "/berkeley/berkeleyIndex";
    }
   /* *//**
     * 根据条件查询所有符合条件的分校
     *//*
    @ResponseBody
    @RequestMapping(value = "/queryCompanyList")
    public PageFinder2<CompanyVo> queryCompanyList(Model model, CompanyVo search){
    	return companyManageServiceImpl.queryCompanyVoListByCondition(search);
    }
    
    *//**
     * 根据学校代码查询对应的分校
     *//*
    @ResponseBody
    @RequestMapping(value = "/queryCompanyVo")
    public CompanyVo queryCompanyVo(Model model,String brachCode){
    	if(brachCode==null||brachCode=="") return null;
    	CompanyVo dto=companyManageServiceImpl.queryCompanyVoByCondition(brachCode);
    	return dto;
    }*/
}
