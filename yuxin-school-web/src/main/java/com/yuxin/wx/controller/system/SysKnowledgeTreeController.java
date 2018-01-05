package com.yuxin.wx.controller.system;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.system.ISysConfigDictService;
import com.yuxin.wx.api.system.ISysConfigItemRelationService;
import com.yuxin.wx.api.system.ISysConfigItemService;
import com.yuxin.wx.api.system.ISysKnowledgeTreeService;
import com.yuxin.wx.common.BaseWebController;
import com.yuxin.wx.common.ViewFiles;
import com.yuxin.wx.model.system.SysConfigDict;
import com.yuxin.wx.model.system.SysConfigItem;
import com.yuxin.wx.model.system.SysConfigItemRelation;
import com.yuxin.wx.model.system.SysKnowledgeTree;
import com.yuxin.wx.utils.EntityUtil;
import com.yuxin.wx.utils.ExcelUtil;
import com.yuxin.wx.utils.WebUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller of SysConfigItem
 * 
 * @author wang.zx
 * @date 2014-12-5
 */
@Controller
@RequestMapping("/sysKnowledgeTree")
public class SysKnowledgeTreeController extends BaseWebController {

    @Autowired
    private ISysConfigItemRelationService sysConfigItemRelationServiceImpl;

    @Autowired
    private ISysKnowledgeTreeService sysKnowledgeTreeServiceImpl;

    @Autowired
    private ISysConfigDictService sysConfigDictService;

    /**
     * 知识树首页跳转
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "treeIndex")
    public String goTreeIndex(Model model, HttpServletRequest request) {
        Integer companyId = WebUtils.getCurrentCompanyId();
        //查询学生学段
        List<SysConfigDict> eduSteps = sysConfigDictService.findByDicCode("EDU_STEP");

        model.addAttribute("eduSteps", eduSteps);
        return "/resource/knowledgeTree/treeIndex";
    }


    /**
     * 知识树列表查询
     * @return
     */
    @RequestMapping(value = "knowledgeTreeList")
    public String knowledgeTreeList(SysKnowledgeTree sysKnowledgeTree) {
        List<SysKnowledgeTree> sysKnowledgeTreeList = null;
        return "/resource/knowledgeTree/treeList";
    }

    /**
     * 知识树插入
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addKnowledgeTree")
    public String addKnowledgeTree(SysKnowledgeTree sysKnowledgeTree) {
        try{
            if(sysKnowledgeTree.getId() == null){
                sysKnowledgeTreeServiceImpl.insertKnowledgeTree(sysKnowledgeTree);
            }else{
                sysKnowledgeTreeServiceImpl.updateKnowledgeTree(sysKnowledgeTree);
            }
        }catch(Exception e){
            e.printStackTrace();
            return "false";
        }
        return "true";
    }


    /**
     * 根据学员学段，查询课程年级
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "findItemSecondCode")
    public List<SysConfigItem> findItemSecondCode(String eduStep) {
        Map<String, Object> searchMap = new HashMap<String, Object>();
        searchMap.put("eduStep", eduStep);
        searchMap.put("companyId", WebUtils.getCurrentCompanyId());
        List<SysConfigItem> sysConfigItemList = sysConfigItemRelationServiceImpl.findItemByEduStep(searchMap);

        return sysConfigItemList;
    }


    /**
     * 根据学员学段，查询课程年级
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "findItemThreeCode")
    public List<SysConfigItemRelation> findItemThreeCode(HttpServletRequest request,String parentCode) {
        List<SysConfigItemRelation> list= sysConfigItemRelationServiceImpl.findSysConfigItemRelationFrontByPCode(parentCode,WebUtils.getCurrentCompanyId());
        return list;
    }
}
