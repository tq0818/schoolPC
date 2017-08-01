package com.yuxin.wx.controller.system;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.system.ISysConfigItemRelationService;
import com.yuxin.wx.api.system.ISysConfigItemService;
import com.yuxin.wx.model.system.SysConfigItem;
import com.yuxin.wx.model.system.SysConfigItemRelation;
import com.yuxin.wx.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2017/7/31.
 */
@Controller
@RequestMapping("/itemTree")
public class SysConfigItemTreeController {
    /**
     *
     */
    @Autowired
    private ISysConfigItemRelationService sysConfigItemRelationServieImpl;
    @Autowired
    private ISysConfigItemService sysConfigItemServieImpl;

    @ResponseBody
    @RequestMapping(value="/ajaxValue")
    public  JSONObject findFirstNodes(Model model,HttpServletRequest request){
        List<SysConfigItemRelation> list= sysConfigItemRelationServieImpl.findSysConfigItemRelationById(null);
        JSONObject json = new JSONObject();
        json.put("list",list);
        SysConfigItem item = new SysConfigItem();
        item.setCompanyId(WebUtils.getCurrentCompanyId());
        item.setSchoolId( WebUtils.getCurrentUserSchoolId(request));
        item.setItemType("2");
        item.setParentCode("TYPE");
        json.put("type",sysConfigItemServieImpl.findByParentCode(item));
        return json;
    }

    @RequestMapping(value="/itemTree")

    public String itemTree(Model model, HttpServletRequest request){
        SysConfigItem item = new SysConfigItem();
        item.setCompanyId(WebUtils.getCurrentCompanyId());
        item.setSchoolId( WebUtils.getCurrentUserSchoolId(request));
        item.setItemType("2");
        item.setParentCode("SUBJECT");
        model.addAttribute("subjectList", sysConfigItemServieImpl.findByParentCode(item));
        item.setParentCode("GRADE");
        model.addAttribute("gradeList", sysConfigItemServieImpl.findByParentCode(item));
        item.setParentCode("TYPE");
        model.addAttribute("typeList", sysConfigItemServieImpl.findByParentCode(item));
        return "system/itemTree";
    }
}
