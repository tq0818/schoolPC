package com.yuxin.wx.controller.branchschool;


import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.vo.company.CompanyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/berkeleyIndex")
public class BerkeleyIndex {
    @Autowired
    private ICompanyService iCompanyService;

    @RequestMapping(value="/getBerkeleyList")
    public String getBerkeleyList(Model model, CompanyVo companyVo, Integer page){
       if(companyVo == null){
           companyVo = new CompanyVo();
       }

       /*//分校学校列表
        List<CompanyVo> schoolList = iCompanyService.getBerkeleySchoolList(companyVo);
       //分校总数
        int count = iCompanyService.getBerkeleySchoolListCount(companyVo);
        // 分页
        PageFinder<CompanyVo> payPage = new PageFinder<CompanyVo>(page, 5, count, schoolList);
        //数据存放在payPage中的data属性中
        model.addAttribute("payPage", payPage);*/
        return "";
    }
}
