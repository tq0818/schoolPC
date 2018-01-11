package com.yuxin.wx.controller.system;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.classes.IClassModuleLessonService;
import com.yuxin.wx.api.classes.IClassTypeService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.system.ISysConfigDictService;
import com.yuxin.wx.api.system.ISysConfigItemRelationService;
import com.yuxin.wx.api.system.ISysKnowledgeTreeService;
import com.yuxin.wx.common.BaseWebController;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.classes.ClassModuleLesson;
import com.yuxin.wx.model.classes.ClassType;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.system.SysConfigDict;
import com.yuxin.wx.model.system.SysConfigItem;
import com.yuxin.wx.model.system.SysConfigItemRelation;
import com.yuxin.wx.model.system.SysKnowledgeTree;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.classes.ClassTypeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
    private ISysConfigDictService sysConfigDictServiceImpl;

    @Autowired
    private IClassTypeService classTypeServiceImpl;

    @Autowired
    private IClassModuleLessonService classModuleLessonServiceImpl;

    @Autowired
    private ICompanyService companyServiceImpl;

    /**
     * 知识树首页跳转
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "knowledgeTreeIndex")
    public String knowledgeTreeIndex(Model model, HttpServletRequest request) {
        //查询学生学段
        List<SysConfigDict> eduSteps = sysConfigDictServiceImpl.findByDicCode("EDU_STEP");

        model.addAttribute("eduSteps", eduSteps);
        return "/resource/knowledgeTree/knowledgeIndex";
    }


    /**
     * 知识树列表查询
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "knowledgeTreeList")
    public JSONObject knowledgeTreeList(SysKnowledgeTree sysKnowledgeTree) {
        JSONObject jsonObject = new JSONObject();
        //获取知识树节点ID
        sysKnowledgeTree.setCompanyId(WebUtils.getCurrentCompanyId());
        List<SysKnowledgeTree> sysKnowledgeTreeList = sysKnowledgeTreeServiceImpl.findKnoledgeTreeByPapam(sysKnowledgeTree);
        String sysKnowledgeTreeIds = "";
        if(sysKnowledgeTreeList!=null && sysKnowledgeTreeList.size()>0){
            for(int i=0; i<sysKnowledgeTreeList.size(); i++){
                SysKnowledgeTree tree = sysKnowledgeTreeList.get(i);
                if(tree.getCommodityId()!=null){
                    sysKnowledgeTreeIds += tree.getCommodityId()+",";
                }
            }
        }

        jsonObject.put("ids", sysKnowledgeTreeIds);
        ClassType classType = new ClassType();
        classType.setCompanyId(WebUtils.getCurrentCompanyId());
        classType.setItemSecondCode(sysKnowledgeTree.getItemSecondCode());
        classType.setItemThirdCode(sysKnowledgeTree.getItemThreeCode());
        classType.setLiveFlag(1);
        classType.setPage(sysKnowledgeTree.getPage());
        classType.setPageSize(sysKnowledgeTree.getPageSize());
        PageFinder<ClassTypeVo> page = classTypeServiceImpl.findClassTypesByPage(classType);
        jsonObject.put("data", page);

        Company company = companyServiceImpl.findCompanyById(WebUtils.getCurrentCompanyId());
        jsonObject.put("company", company);
        return jsonObject;
    }

    /**
     * 知识树预览
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "viewknowledgeTreeList")
    public List<ClassModuleLesson> viewknowledgeTreeList(String idstr) {
        //获取知识树节点ID
        String[] ids = idstr.split(",");
        List<ClassModuleLesson> classModuleLessonList = classModuleLessonServiceImpl.findLessonByCommodityIds(ids);
        return classModuleLessonList;
    }

    /**
     * 清空知识树节点
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "removeKnowledge")
    public String removeKnowledge(SysKnowledgeTree sysKnowledgeTree) {
        try{
            //知识树节点清除
            sysKnowledgeTree.setCompanyId(WebUtils.getCurrentCompanyId());
            sysKnowledgeTreeServiceImpl.removeKnowledge(sysKnowledgeTree);
        }catch(Exception e){
            e.printStackTrace();
            return "false";
        }
        return "true";
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
