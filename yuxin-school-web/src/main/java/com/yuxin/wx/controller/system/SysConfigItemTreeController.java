package com.yuxin.wx.controller.system;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.system.ISysConfigItemRelationService;
import com.yuxin.wx.api.system.ISysConfigItemService;
import com.yuxin.wx.controller.task.Ex;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    /**
     *获取根目录
     */
    @ResponseBody
    @RequestMapping(value="/ajaxValue")
    public  JSONObject findFirstNodes(Model model,HttpServletRequest request){
        List<SysConfigItemRelation> list= sysConfigItemRelationServieImpl.findSysConfigItemRelationById(null,WebUtils.getCurrentCompanyId());
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
    /**
     * 添加节点
     * */
    @ResponseBody
    @RequestMapping(value="/insert")
    public String insert(Model model, HttpServletRequest request,Integer level,String parentCode,Integer parentId,String codes,String levelPath){
        try{
            SysConfigItemRelation relation = new SysConfigItemRelation();
            relation.setCompanyId(WebUtils.getCurrentCompanyId());
            relation.setLevel(level);
            relation.setParentCode(parentCode);
            relation.setParentId(parentId);
            relation.setLevelPath(levelPath);
            //修改子节点则需要删除原有节点
//            if(relation.getLevel()!=null && relation.getLevel()>0){
//                List<SysConfigItemRelation> oldChildren = sysConfigItemRelationServieImpl.findSysConfigItemRelationById(relation.getParentId());
//                sysConfigItemRelationServieImpl.deleteRelation(oldChildren);
//                SysConfigItemRelation root = new SysConfigItemRelation();
//                root.setId(relation.getParentId());
//                if(codes.length()>0){
//                    root.setIsParent(true);
//                }else{
//                    root.setIsParent(false);
//                }
//                sysConfigItemRelationServieImpl.update(root);
//            }
            SysConfigItemRelation root = new SysConfigItemRelation();
            root.setId(relation.getParentId());
            root.setIsParent(true);
            sysConfigItemRelationServieImpl.update(root);
            //添加根节点则需要设置父节点和父编码
            if(relation.getLevel()!=null &&relation.getLevel()==0){
                relation.setParentCode(null);
                relation.setParentId(null);
            }
            if(codes.length()>0){
                String[] itemCodes = codes.split(",");
                for (int n =  0 ; n <itemCodes.length;n++ ) {
                    relation.setItemCode(itemCodes[n]);
                    sysConfigItemRelationServieImpl.insert(relation);
                    relation.setId(null);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            return "fail";
        }
        return "success";
    }


    /**
     * 添加节点
     * */
    @ResponseBody
    @RequestMapping(value="/update")
    public String update(Model model, HttpServletRequest request,Integer id,String itemCode){
        try{
            SysConfigItemRelation relation = new SysConfigItemRelation();
            relation.setId(id);
            relation.setItemCode(itemCode);
            sysConfigItemRelationServieImpl.update(relation);
        }catch(Exception e){
            e.printStackTrace();
            return "fail";
        }
        return "success";
    }
    /**
     * 删除节点 delNodes
     * **/
    @ResponseBody
    @RequestMapping(value="/delNodes")
    public  String delNodes(Model model,HttpServletRequest request,String id,String parentId){
        try {
            List<SysConfigItemRelation> oldChildren = sysConfigItemRelationServieImpl.findSysConfigItemRelationById(Integer.parseInt(id),WebUtils.getCurrentCompanyId());
            sysConfigItemRelationServieImpl.deleteRelation(oldChildren);
            sysConfigItemRelationServieImpl.deleteById(Integer.parseInt(id));
            if(parentId!=null){
                SysConfigItemRelation relation = new SysConfigItemRelation();
                relation.setId(Integer.parseInt(parentId));
                relation.setIsParent(false);
                sysConfigItemRelationServieImpl.update(relation);
            }
        }catch(Exception e){
            e.printStackTrace();
            return "false";
        }
        return "true";
    }


/**
 * 获取节点
 * */
    @ResponseBody
    @RequestMapping(value="/getNodes")
    public JSONObject getNodes(Model model, HttpServletRequest request,String id,String level){
        List<SysConfigItemRelation> list= sysConfigItemRelationServieImpl.findSysConfigItemRelationById(Integer.parseInt(id),WebUtils.getCurrentCompanyId());
        JSONObject json = new JSONObject();
        json.put("list",list);
        SysConfigItem item = new SysConfigItem();
        item.setCompanyId(WebUtils.getCurrentCompanyId());
        item.setSchoolId( WebUtils.getCurrentUserSchoolId(request));
        item.setItemType("2");
        int le = Integer.parseInt(level)+1;
        if(le==1){
            item.setParentCode("GRADE");
            json.put("name",sysConfigItemServieImpl.findByParentCode(item));
        }else if(le==2){
            item.setParentCode("SUBJECT");
            json.put("name",sysConfigItemServieImpl.findByParentCode(item));
        }
        return json;
    }
    /**
     * 初始化页面
     * */
    @ResponseBody
    @RequestMapping(value="/publishRelation")
    public JSONObject publishRelation(Model model, HttpServletRequest request,String name){
        sysConfigItemRelationServieImpl.publishRelation(WebUtils.getCurrentCompanyId());
        JSONObject json = new JSONObject();
        json.put("result","ok");
        return json;
    }



    /**
     * 初始化页面
     * */
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


    @ResponseBody
    @RequestMapping(value="/queryItemSecond",method=RequestMethod.POST)
    public List<SysConfigItemRelation> findItemTwo(Integer pid,HttpServletRequest request){
        // return sysConfigItemServiceImpl.findSysConfigItemByPid(SysConfigConstant.ITEMTYPE_SECOND, pid,WebUtils.getCurrentCompanyId());
        SysConfigItemRelation relation = new SysConfigItemRelation();
        relation.setId(pid);
        relation.setCompanyId(WebUtils.getCurrentCompanyId());
        List<SysConfigItemRelation> list= sysConfigItemRelationServieImpl.findItemFront(relation);
        Map<String, Object> param = new HashMap<>();
        param.put("schoolId",WebUtils.getCurrentUserSchoolId(request));
        param.put("companyId",WebUtils.getCurrentCompanyId());
        List<SysConfigItem> names = sysConfigItemServieImpl.findItemBySchoolCompanyId(param);
        for(SysConfigItemRelation re : list){
            for(SysConfigItem name :names){
                if(re.getLevel()==3){
                    re.setItemName(re.getItemCode());
                    break;
                }else{
                    if(re.getItemCode().equals(name.getItemCode())){
                        re.setItemName(name.getItemName());
                        break;
                    }
                }
            }
        }
        return list;
    }
}
