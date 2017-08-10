package com.yuxin.wx.controller.system;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.classes.IClassModuleNoService;
import com.yuxin.wx.api.classes.IClassModuleService;
import com.yuxin.wx.api.classes.IClassTypeModuleRelationService;
import com.yuxin.wx.api.classes.IClassTypeService;
import com.yuxin.wx.api.commodity.ICommodityService;
import com.yuxin.wx.api.company.ICompanyFunctionSetService;
import com.yuxin.wx.api.course.ICourseRemoteService;
import com.yuxin.wx.api.course.ILiveOpenCourseService;
import com.yuxin.wx.api.system.ISysConfigItemCourseService;
import com.yuxin.wx.api.system.ISysConfigItemIconService;
import com.yuxin.wx.api.system.ISysConfigItemService;
import com.yuxin.wx.common.BaseWebController;
import com.yuxin.wx.common.JsonMsg;
import com.yuxin.wx.common.SysConfigConstant;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.classes.ClassType;
import com.yuxin.wx.model.commodity.Commodity;
import com.yuxin.wx.model.company.CompanyFunctionSet;
import com.yuxin.wx.model.course.CourseRemote;
import com.yuxin.wx.model.system.SysConfigItem;
import com.yuxin.wx.model.system.SysConfigItemCourse;
import com.yuxin.wx.model.system.SysConfigItemIcon;
import com.yuxin.wx.model.system.SysSchoolItemRelation;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.utils.FileUploadUtil;
import com.yuxin.wx.utils.PropertiesUtil;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.classes.ClassTypeModelNoVo;

/**
 * Controller of SysConfigItem
 * 
 * @author wang.zx
 * @date 2014-12-5
 */
@Controller
@RequestMapping("/sysConfigItem")
public class SysConfigItemController extends BaseWebController {

    @Autowired
    private ISysConfigItemCourseService sysConfigItemCourseServiceImpl;

    @Autowired
    private PropertiesUtil properties;

    @Autowired
    ISysConfigItemIconService sysConfigItemIconServiceImpl;

    @Autowired
    private IClassModuleNoService classModuleNoServiceImpl;

    @Autowired
    private IClassTypeModuleRelationService classTypeModuleRelationServiceImpl;

    @Autowired
    private PropertiesUtil propertiesUtil;

    private Log log = LogFactory.getLog("log");

    @Autowired
    private ISysConfigItemService sysConfigItemServiceImpl;

    @Autowired
    private IClassTypeService classTypeServiceImpl;

    @Autowired
    private IClassModuleService classModuleServiceImpl;

    @Autowired
    private ICourseRemoteService courseRemoteServiceImpl;

    @Autowired
    private ICommodityService commodityServiceImpl;

    @Autowired
    private ILiveOpenCourseService liveOpenCourseServiceImpl;

    @Autowired
    private ICompanyFunctionSetService companyFunctionSetServiceImpl;

    /**
     * 
     * Class Name: SysConfigItemController.java
     * 
     * @Description: 跳向项目管理列表页
     * @author liuxindong
     * @date 2014-12-13 下午12:33:56
     * @version 1.0
     * @param model
     * @return
     */
    @RequestMapping(value = "goItemList", method = RequestMethod.GET)
    public String goItemList(Model model, HttpServletRequest request) {
        Integer companyId = WebUtils.getCurrentCompanyId();
        SysConfigItem search = new SysConfigItem();
        search.setItemType(SysConfigConstant.ITEMTYPE_FIRST);
        if (companyId != null && companyId > 0) {
            search.setCompanyId(companyId);
        }

        List<SysConfigItem> firstItems = sysConfigItemServiceImpl.findItem(search);
        model.addAttribute("firstItems", firstItems);
        return "system/item/itemList";
    }

    /**
     * 
     * Class Name: SysConfigItemController.java
     * 
     * @Description: 获取一级项目列表
     * @author liuxindong
     * @date 2014-12-13 下午12:34:42
     * @version 1.0
     * @param model
     * @param search
     * @return
     */
    @RequestMapping(value = "getFirstItems", method = RequestMethod.GET)
    public String getFirstItems(Model model, SysConfigItem search) {
        search.setItemType(SysConfigConstant.ITEMTYPE_FIRST);
        List<SysConfigItem> firstItems = sysConfigItemServiceImpl.findItem(search);
        model.addAttribute("search", search);
        model.addAttribute("firstItems", firstItems);
        return "system/item/itemFirstAjax";
    }

    /**
     * 
     * Class Name: SysConfigItemController.java
     * 
     * @Description: 获取二级项目列表
     * @author liuxindong
     * @date 2014-12-13 下午12:38:14
     * @version 1.0
     * @param model
     * @param search
     * @return
     */
    @RequestMapping(value = "getSecondItems", method = RequestMethod.GET)
    public String getSecondItems(Model model, SysConfigItem search) {
        search.setItemType(SysConfigConstant.ITEMTYPE_SECOND);
        List<SysConfigItem> secondItems = sysConfigItemServiceImpl.findItem(search);
        model.addAttribute("search", search);
        model.addAttribute("secondItems", secondItems);
        model.addAttribute("firstItemId", search.getParentId());
        return "system/item/itemSecondAjax";
    }

    /**
     * 
     * Class Name: SysConfigItemController.java
     * 
     * @Description: 跳向新增或修改一级项目页面
     * @author liuxindong
     * @date 2014-12-13 下午12:38:32
     * @version 1.0
     * @param model
     * @param firstItemId
     * @return
     */
    @RequestMapping(value = "goManageFirstItem", method = RequestMethod.GET)
    public String goManageFirstItem(Model model, Integer firstItemId) {
        SysConfigItem firstItem = sysConfigItemServiceImpl.findSysConfigItemById(firstItemId);
        model.addAttribute("firstItem", firstItem);
        return "system/item/itemFirstManage";
    }

    /**
     * 
     * Class Name: SysConfigItemController.java
     * 
     * @Description: 跳向新增或修改二级项目页面
     * @author liuxindong
     * @date 2014-12-13 下午12:38:56
     * @version 1.0
     * @param model
     * @param firstItemId
     * @param secondItemId
     * @return
     */
    @RequestMapping(value = "goManageSecondItem", method = RequestMethod.GET)
    public String goManageSecondItem(Model model, Integer firstItemId, Integer secondItemId) {
        SysConfigItem firstItem = sysConfigItemServiceImpl.findSysConfigItemById(firstItemId);
        SysConfigItem secondItem = sysConfigItemServiceImpl.findSysConfigItemById(secondItemId);
        model.addAttribute("firstItem", firstItem);
        model.addAttribute("secondItem", secondItem);
        return "system/item/itemSecondManage";
    }

    /**
     * 
     * Class Name: SysConfigItemController.java
     * 
     * @Description: 新增项目
     * @author ycl
     * @date 2015-03-30
     * @version 1.0
     * @param sysConfigItem
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addItem", method = RequestMethod.POST)
    public String addItem(SysConfigItem sysConfigItem, HttpServletRequest request, MultipartRequest multiPartRquest) {
        MultipartFile multipartFile = multiPartRquest.getFile("itemPicFile");
        String fileName = multipartFile.getOriginalFilename();
        int pointIndex = fileName.indexOf(".");
        Users user = WebUtils.getCurrentUser(request);
        int companyId = user.getCompanyId();
        String newFileName = fileName.substring(0, pointIndex);
        String realPath = FileUploadUtil.getlnstance().saveFileUpload(multipartFile, propertiesUtil.getImageServiceRealPath() + "/" + companyId + "/ico",
                newFileName);
        sysConfigItem.setItemPic(companyId + "/ico/" + fileName);
        try {
            wrapperSaveItem(sysConfigItem, SysConfigConstant.OPERATE_ADD, request);
            sysConfigItemServiceImpl.insert(sysConfigItem);
        } catch (Exception e) {
            log.error("新增项目出错！", e);
            e.printStackTrace();
            return "fail";
        }
        return JsonMsg.SUCCESS;
    }

    /**
     * 
     * Class Name: SysConfigItemController.java
     * 
     * @Description: 修改项目
     * @author ycl
     * @date 2015-3-31
     * @version 1.0
     * @param sysConfigItem
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/editItem", method = RequestMethod.POST)
    public String editItem(SysConfigItem sysConfigItem, HttpServletRequest request, MultipartRequest multiPartRquest) {
        MultipartFile multipartFile = multiPartRquest.getFile("itemPicFile");
        String fileName = multipartFile.getOriginalFilename();
        int pointIndex = fileName.indexOf(".");
        Users user = WebUtils.getCurrentUser(request);
        int companyId = user.getCompanyId();
        String newFileName = fileName.substring(0, pointIndex);
        String realPath = FileUploadUtil.getlnstance().saveFileUpload(multipartFile, propertiesUtil.getImageServiceRealPath() + "/" + companyId + "/ico",
                newFileName);
        sysConfigItem.setItemPic(companyId + "/ico/" + fileName);
        try {
            wrapperSaveItem(sysConfigItem, SysConfigConstant.OPERATE_EDIT, request);
            sysConfigItemServiceImpl.update(sysConfigItem);
        } catch (Exception e) {
            log.error("修改项目出错！", e);
            e.printStackTrace();
            return "fail";
        }
        return JsonMsg.SUCCESS;
    }

    /**
     * 
     * Class Name: SysConfigItemController.java
     * 
     * @Description: 更改项目状态
     * @author liuxindong
     * @date 2014-12-13 下午12:39:45
     * @version 1.0
     * @param sysConfigItem
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/changeItemStatus", method = RequestMethod.POST)
    public String changeItemStatus(SysConfigItem sysConfigItem, HttpServletRequest request) {
        try {
            // 项目下无上架班型、无已发布模块（远程教育、视频），允许停用
            List<ClassType> classTypes = null;
            List<ClassModule> classModules = null;
            List<CourseRemote> courseRemotes = null;

            // 查询项目下所有的班型
            SysConfigItem sysConfig = sysConfigItemServiceImpl.findSysConfigItemById(sysConfigItem.getId());
            if (sysConfig != null && sysConfig.getId() > 0 && sysConfig.getItemType() != null) {
                ClassType classType = new ClassType();
                if (sysConfig.getItemType().equals("1")) {
                    classType.setItemOneId(sysConfig.getId());
                } else if (sysConfig.getItemType().equals("2")) {
                    classType.setItemSecondId(sysConfig.getId());
                }
                classTypes = classTypeServiceImpl.findClassTypeList(classType);
            }
            if (classTypes != null && classTypes.size() > 0) {
                return "fail_sysConfig";
            }

            // 查询所有对应的模块
            if (sysConfig != null && sysConfig.getId() > 0 && sysConfig.getItemType() != null) {
                ClassModule classModule = new ClassModule();
                if (sysConfig.getItemType().equals("1")) {
                    classModule.setItemOneId(sysConfig.getId());
                } else if (sysConfig.getItemType().equals("2")) {
                    classModule.setItemSecondId(sysConfig.getId());
                }
                classModules = classModuleServiceImpl.findClassModuleByPage(classModule);
                ;
            }
            if (classModules != null && classModules.size() > 0) {
                return "fail_classModule";
            }

            // 查询所有对应远程教育的模块
            if (sysConfig != null && sysConfig.getId() > 0 && sysConfig.getItemType() != null) {
                CourseRemote courseRemote = new CourseRemote();
                if (sysConfig.getItemType().equals("1")) {
                    courseRemote.setItemOneId(sysConfig.getId());
                } else if (sysConfig.getItemType().equals("2")) {
                    courseRemote.setItemSecondId(sysConfig.getId());
                }
                courseRemotes = courseRemoteServiceImpl.findCourseRemoteByPage(courseRemote);
                ;
            }
            if (courseRemotes != null && courseRemotes.size() > 0) {
                return "fail_courseRemote";
            }

            wrapperSaveItem(sysConfigItem, SysConfigConstant.OPERATE_EDIT, request);
            sysConfigItemServiceImpl.update(sysConfigItem);
        } catch (Exception e) {
            log.error("修改项目状态出错！", e);
            e.printStackTrace();
            return "fail";
        }
        return JsonMsg.SUCCESS;
    }

    /**
     * 
     * Class Name: SysConfigItemController.java
     * 
     * @Description: 检查项目名称唯一性
     * @author liuxindong
     * @date 2014-12-13 下午12:39:58
     * @version 1.0
     * @param search
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/checkItemName", method = RequestMethod.POST)
    public String checkItemName(SysConfigItem search, HttpServletRequest request) {
        List<SysConfigItem> firstItems = sysConfigItemServiceImpl.findItem(search);
        if (firstItems != null && firstItems.size() > 0) {
            return "false";
        }
        return "true";
    }

    @ResponseBody
    @RequestMapping(value = "/getJson/{id}", method = RequestMethod.POST)
    public SysConfigItem getJson(Model model, @PathVariable Integer id) {
        return sysConfigItemServiceImpl.findSysConfigItemById(id);
    }

    /**
     * 
     * Class Name: SysConfigItemController.java
     * 
     * @Description: 查一二级项目
     * @author HOME
     * @date 2014年12月11日 上午12:14:02
     * @version 1.0
     * @param sysConfigItem
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getJsons", method = RequestMethod.POST)
    public List<SysConfigItem> getJsons(SysConfigItem sysConfigItem) {
        return sysConfigItemServiceImpl.findSysConfigItemByPid(sysConfigItem.getItemType(), sysConfigItem.getParentId(), WebUtils.getCurrentCompanyId(),
                WebUtils.getCurrentSchoolId());
    }

    /**
     * 
     * Class Name: SysConfigItemController.java
     * 
     * @Description:根据一级项目查询二级项目
     * @author zhang.zx
     * @date 2015年7月27日 上午10:30:54
     * @modifier
     * @modify-date 2015年7月27日 上午10:30:54
     * @version 1.0
     * @param sysConfigItem
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getJsonsSecondItem", method = RequestMethod.POST)
    public List<SysConfigItem> getJsonsSecondItem(SysConfigItem sysConfigItem) {
        sysConfigItem.setCompanyId(WebUtils.getCurrentCompanyId());
        if (null == sysConfigItem.getSchoolId() || "".equals(sysConfigItem.getSchoolId())) {
            sysConfigItem.setSchoolId(WebUtils.getCurrentSchoolId());
        }
        List<SysConfigItem> arr = sysConfigItemServiceImpl.selectSecondItem(sysConfigItem);
        return arr;
    }

    @ResponseBody
    @RequestMapping(value = "/queryItemOne", method = RequestMethod.POST)
    public List<SysConfigItem> queryItemOne() {
        SysConfigItem search = new SysConfigItem();
        search.setItemType(SysConfigConstant.ITEMTYPE_FIRST);
        return sysConfigItemServiceImpl.findItem(search);
    }

    /**
     * 后台接收Date转换
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    /**
     * Class Name: SysConfigItemController.java
     * 
     * @Description: 包装新增或修改的参数
     * @author liuxindong
     * @date 2014-12-13 下午12:40:30
     * @version 1.0
     * @param sysConfigItem
     * @param operate
     * @param request
     */
    private void wrapperSaveItem(SysConfigItem sysConfigItem, String operate, HttpServletRequest request) {

        if (operate.equals(SysConfigConstant.OPERATE_ADD)) {
            Integer companyId = WebUtils.getCurrentCompanyId();

            sysConfigItem.setCreateTime(new Date());
            sysConfigItem.setCreator(getCurrentUserId(request));
            sysConfigItem.setCompanyId(companyId);
            sysConfigItem.setUpdateTime(new Date());
            sysConfigItem.setUpdator(getCurrentUserId(request));
            sysConfigItem.setStatus("1");
            sysConfigItem.setDelFlag(0);
        } else if (operate.equals(SysConfigConstant.OPERATE_EDIT)) {
            sysConfigItem.setUpdateTime(new Date());
            sysConfigItem.setUpdator(getCurrentUserId(request));
        }
    }

    /**
     * 
     * Class Name: SysConfigSchoolController.java
     * 
     * @Description: 获得公司 一级项目
     * @author 周文斌
     * @date 2015-5-4 上午11:36:48
     * @version 1.0
     * @param
     * @return
     */
    @RequestMapping("/project")
    public String project() {
        return "resource/project/project";
    }

    /**
     * @Description: ajax加载公司所有一级项目
     * @author licong
     * @date 2016年4月25日 下午3:53:33
     * @param
     * @return
     */
    @RequestMapping("/ajaxProject")
    public String ajaxProject(Model model, HttpServletRequest request) {
        Integer companyId = WebUtils.getCurrentCompanyId();
        Integer schoolId = WebUtils.getCurrentUserSchoolId(request);
        SysConfigItem item = new SysConfigItem();
        item.setCompanyId(companyId);
        item.setSchoolId(schoolId);
        item.setItemType("1");

        // 根据 项目 id 和学校id 查询 关系表
        List<SysConfigItem> newOneList = sysConfigItemServiceImpl.findStatus(item);

        List<Integer> companyIds = new ArrayList<Integer>();
        for (SysConfigItem s : newOneList) {
            companyIds.add(s.getId());
        }
        Map<String, Object> param = new HashMap<String, Object>();
        if (companyIds.size() > 0) {
            param.put("companyIds", companyIds);
        }
        param.put("companyId", companyId);
        param.put("itemType", 1);

        // 根据公司id 查询 项目
        List<SysConfigItem> oneList = sysConfigItemServiceImpl.findNotInByItemId(param);
        /*
         * //加载项目图标 List<SysConfigItemIcon> itemIcon =
         * sysConfigItemIconServiceImpl.findByCompanyId(companyId); for
         * (SysConfigItemIcon s : itemIcon) {
         * s.setIconBackUrl(properties.getProjectImageUrl() + "/" +
         * s.getIconBackUrl()); s.setIconUrl(properties.getProjectImageUrl() +
         * "/" + s.getIconUrl()); }
         */

        model.addAttribute("ImagePath", properties.getProjectImageUrl());
        model.addAttribute("newOneList", newOneList);
        /* model.addAttribute("itemIcon", itemIcon); */
        model.addAttribute("oneList", oneList);
        model.addAllAttributes(selIcon(1, 8));

        CompanyFunctionSet companyFunctionSet = new CompanyFunctionSet();
        companyFunctionSet.setCompanyId(WebUtils.getCurrentCompanyId());
        companyFunctionSet.setFunctionCode("COURSE_THIRD_CATEGORY");
        List<CompanyFunctionSet> companyFunctionSetList = companyFunctionSetServiceImpl.findCompanyFunctionSetByPage(companyFunctionSet);
        CompanyFunctionSet set = companyFunctionSetList != null && companyFunctionSetList.size() > 0 ? companyFunctionSetList.get(0) : null;
        if (null != set && ("1".equals(set.getStatus()) || "2".equals(set.getStatus()))) {
            model.addAttribute("tag3Show", "1");
        }

        companyFunctionSet = new CompanyFunctionSet();
        companyFunctionSet.setCompanyId(WebUtils.getCurrentCompanyId());
        companyFunctionSet.setFunctionCode("COURSE_SECOND_CATEGORY");
        companyFunctionSetList = companyFunctionSetServiceImpl.findCompanyFunctionSetByPage(companyFunctionSet);
        CompanyFunctionSet categoryset = companyFunctionSetList != null && companyFunctionSetList.size() > 0 ? companyFunctionSetList.get(0) : null;

        if (null != set && ("1".equals(set.getStatus()) || "2".equals(set.getStatus())) && null != categoryset
                && ("1".equals(categoryset.getStatus()) || "2".equals(categoryset.getStatus()))) {
            model.addAttribute("tag4Show", "1");
        }

        return "resource/project/ajaxProject";
    }
    /**
     * 
     * Class Name: SysConfigItemController.java
     * 
     * @Description: 可以做更多操作
     * @author 周文斌
     * @date 2015-5-12 下午9:17:40
     * @version 1.0
     * @param model
     * @param request
     * @return
     */
    /*
     * @RequestMapping("/moreProject") public String moreProject(Model
     * model,HttpServletRequest request){ Integer companyId =
     * WebUtils.getCurrentCompanyId(); //根据学校id 查询 项目 List<SysConfigItem>
     * oneList = sysConfigItemServiceImpl .findItemByCompanyId(1, companyId);
     * 
     * //加载项目图标 List<SysConfigItemIcon> itemIcon =
     * sysConfigItemIconServiceImpl.findByCompanyId(companyId); for
     * (SysConfigItemIcon s : itemIcon) {
     * s.setIconUrl(properties.getProjectImageUrl() + "/" + s.getIconUrl()); }
     * 
     * model.addAttribute("ImagePath", properties.getProjectImageUrl());
     * model.addAttribute("itemIcon", itemIcon); model.addAttribute("oneList",
     * oneList); return "system/project"; }
     */

    /**
     * 
     * Class Name: SysConfigItemController.java
     * 
     * @Description: 获取二级项目列表
     * @author 周文斌
     * @date 2015-5-4 下午5:12:11
     * @version 1.0
     * @param model
     * @param oneItemId
     * @return
     */
    @RequestMapping("/twoProListAjax")
    public String twoProListAjax(Model model, Integer oneItemId,String oneItemCode, HttpServletRequest request) {
        Integer companyId = WebUtils.getCurrentCompanyId();
        Integer schoolId = WebUtils.getCurrentUserSchoolId(request);
        SysConfigItem item = new SysConfigItem();
        item.setCompanyId(companyId);
        item.setSchoolId(schoolId);
        item.setItemType("2");
        item.setParentId(oneItemId);
        item.setParentCode(oneItemCode);
        List<SysConfigItem> list = sysConfigItemServiceImpl.findStatus(item);
        // 二级项目id
        List<Integer> twoIds = new ArrayList<Integer>();

        for (SysConfigItem s : list) {
            twoIds.add(s.getId());
        }
        Map<String, Object> param = new HashMap<String, Object>();

        if (twoIds.size() > 0) {
            param.put("twoIds", twoIds);
        }
        param.put("parentId", oneItemId);
        param.put("itemType", 2);
        param.put("companyId", companyId);
        List<SysConfigItem> newTwoProList = sysConfigItemServiceImpl.findNotInByItemId(param);

        // 查询一级项目关系是否存在
        param.clear();
        param.put("schoolId", WebUtils.getCurrentUserSchoolId(request));
        param.put("itemId", oneItemId);
        SysSchoolItemRelation oneRel = sysConfigItemServiceImpl.findExist(param);

        model.addAttribute("onerel", oneRel);
        model.addAttribute("twoProList", list);
        model.addAttribute("oneItemId", oneItemId);
        model.addAttribute("oneItemCode", oneItemCode);
        model.addAttribute("newTowProList", newTwoProList);
        return "resource/project/towProjectList";
    }

    /**
     * @Description: 一级学科排序
     * @author licong
     * @date 2016年4月25日 下午6:17:36
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/orderOneItem")
    public boolean orderOneItem(HttpServletRequest request) {
        List<SysConfigItem> items = JSONArray.parseArray(request.getParameter("list"), SysConfigItem.class);
        if (items == null || items.size() == 0)
            return true;
        sysConfigItemServiceImpl.update(items);
        // for (SysConfigItem sysConfigItem : items) {
        // sysConfigItemServiceImpl.update(sysConfigItem);
        // }
        return true;
    }

    /*
    *//**
       * 
       * Class Name: SysConfigItemController.java
       * 
       * @Description:
       * @author 周文斌
       * @date 2015-5-25 下午5:21:43
       * @version 1.0
       * @param oneItemId
       * @return
       *//*
         * @RequestMapping("/moreTwoProListAjax") public String
         * moreTwoProListAjax(Model model, Integer oneItemId) {
         * model.addAttribute("twoProList", towProList(oneItemId));
         * model.addAttribute("oneItemId", oneItemId); return
         * "system/towProjectList"; }
         */

    @ResponseBody
    @RequestMapping("/twoProByClass")
    public JSONObject twoProListAjax(HttpServletRequest request, Integer oneItemId) {
        JSONObject json = new JSONObject();
        json.put("twoItem", towProList(request, oneItemId));
        return json;
    }

    /**
     * 
     * Class Name: SysConfigItemController.java
     * 
     * @Description: 获取二级项目列表
     * @author 周文斌
     * @date 2015-5-12 下午9:12:07
     * @version 1.0
     * @param
     * @param oneItemId
     * @return
     */
    private List<SysConfigItem> towProList(HttpServletRequest request, Integer oneItemId) {
        return sysConfigItemServiceImpl.findSysConfigItemByPid("2", oneItemId, WebUtils.getCurrentCompanyId(), WebUtils.getCurrentUserSchoolId(request));
    }

    /**
     * 
     * Class Name: SysConfigItemController.java
     * 
     * @Description: 更改项目状态
     * @author 周文斌
     * @date 2015-5-4 下午3:46:22
     * @version 1.0
     * @param request
     * @param sysConfigItem
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/changeStatus", method = RequestMethod.POST)
    public JSONObject changeStatus(HttpServletRequest request, SysConfigItem sysConfigItem) {
        JSONObject json = new JSONObject();
        Integer companyId = WebUtils.getCurrentCompanyId();
        try {
            // 查询 学校关系
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("schoolId", WebUtils.getCurrentUserSchoolId(request));
            params.put("itemId", sysConfigItem.getId());
            SysSchoolItemRelation rel = sysConfigItemServiceImpl.findExist(params);
            if (rel != null && rel.getDelFlag() == 0) {
                // 项目下无上架班型允许停用
                List<ClassType> classTypes = null;
                // 查询项目下所有的班型
                SysConfigItem sysConfig = sysConfigItemServiceImpl.findSysConfigItemById(sysConfigItem.getId());
                json.put("name", sysConfig.getItemName());
                if (sysConfig != null && sysConfig.getId() > 0 && sysConfig.getItemType() != null) {
                    ClassType classType = new ClassType();
                    classType.setCompanyId(companyId);
                    classType.setCreateSchoolId(WebUtils.getCurrentUserSchoolId(request));
                    if (sysConfig.getItemType().equals("1")) {
                        classType.setItemOneId(sysConfig.getId());
                    } else if (sysConfig.getItemType().equals("2")) {
                        classType.setItemSecondId(sysConfig.getId());
                    }
                    classTypes = classTypeServiceImpl.findClassTypeList(classType);
                }
                if (classTypes != null && classTypes.size() > 0) {

                    List<ClassTypeModelNoVo> ctmnList = new ArrayList<ClassTypeModelNoVo>();
                    for (int i = 0; i < (classTypes.size()); i++) {
                        if (classTypes.size() < 2) {
                            ClassTypeModelNoVo ctmn = new ClassTypeModelNoVo();
                            ctmn.setClassName(classTypes.get(i).getName());
                            ctmn.setClassNo("");
                            ctmnList.add(ctmn);
                        } else if (classTypes.size() == 2) {
                            if (i % 2 == 0) {
                                ClassTypeModelNoVo ctmn = new ClassTypeModelNoVo();
                                ctmn.setClassName(classTypes.get(i).getName());
                                ctmn.setClassNo(classTypes.get(i + 1).getName());
                                ctmnList.add(ctmn);
                            }
                        } else if (classTypes.size() > 2) {
                            if (i % 2 == 0) {
                                if ((classTypes.size() % 2) != 0 && (i + 1) == classTypes.size()) {
                                    ClassTypeModelNoVo ctmn = new ClassTypeModelNoVo();
                                    ctmn.setClassName(classTypes.get(i).getName());
                                    ctmn.setClassNo("");
                                    ctmnList.add(ctmn);
                                } else {
                                    ClassTypeModelNoVo ctmn = new ClassTypeModelNoVo();
                                    ctmn.setClassName(classTypes.get(i).getName());
                                    ctmn.setClassNo(classTypes.get(i + 1).getName());
                                    ctmnList.add(ctmn);
                                }
                            }
                        }
                    }
                    json.put("fail", ctmnList);
                    return json;
                }
            }
            json.put("flag", true);
            return json;
        } catch (Exception e) {
            log.error("修改项目状态出错！", e);
            e.printStackTrace();
            json.put("flag", JsonMsg.INFORMATION);
            return json;
        }
    }

    /**
     * 
     * Class Name: SysConfigItemController.java
     * 
     * @Description: 添加或更改 项目关系表
     * @author 周文斌
     * @date 2015-5-12 下午7:59:22
     * @version 1.0
     * @param request
     * @param sysConfigItem
     */
    private void addOrUpdateRelation(HttpServletRequest request, SysConfigItem sysConfigItem) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("schoolId", WebUtils.getCurrentUserSchoolId(request));
        params.put("itemId", sysConfigItem.getId());
        SysSchoolItemRelation rel = sysConfigItemServiceImpl.findExist(params);
        // 添加一级项目关系
        if (rel == null) {
            params.clear();
            params.put("schoolId", WebUtils.getCurrentUserSchoolId(request));
            params.put("itemId", sysConfigItem.getId());
            params.put("delFlag", 0);
            params.put("trueDelFlag", 0);
            params.put("creator", sysConfigItem.getCreator());
            params.put("createTime", sysConfigItem.getCreateTime());
            params.put("updator", sysConfigItem.getUpdator());
            params.put("updateTime", sysConfigItem.getUpdateTime());
            sysConfigItemServiceImpl.insertRelation(params);
        } else if (rel.getDelFlag() == 1) {
            params.clear();
            params.put("id", rel.getId());
            params.put("delFlag", 0);
            params.put("trueDelFlag", 0);
            params.put("updator", sysConfigItem.getUpdator());
            params.put("updateTime", sysConfigItem.getUpdateTime());
            sysConfigItemServiceImpl.updateRelation(params);

            if (sysConfigItem.getItemType().equals("1")) {
                // 修改配置
                SysConfigItemCourse scic = new SysConfigItemCourse();
                scic.setCompanyId(WebUtils.getCurrentCompanyId());
                scic.setDelFlag(0);
                scic.setConfigItemId(sysConfigItem.getId());
                scic.setLiveWatchCount(0);
                scic.setValidityDay(0);
                scic.setVideoWatchCount(0);
                sysConfigItemCourseServiceImpl.updateStatus(scic);

            }
        } else if (rel.getDelFlag() == 0) {
            params.clear();
            params.put("id", rel.getId());
            params.put("delFlag", 1);
            params.put("trueDelFlag", 0);
            params.put("updator", sysConfigItem.getUpdator());
            params.put("updateTime", sysConfigItem.getUpdateTime());
            sysConfigItemServiceImpl.updateRelation(params);

            if (sysConfigItem.getItemType().equals("1")) {
                // 修改配置
                SysConfigItemCourse scic = new SysConfigItemCourse();
                scic.setCompanyId(WebUtils.getCurrentCompanyId());
                scic.setDelFlag(1);
                scic.setConfigItemId(sysConfigItem.getId());
                scic.setLiveWatchCount(0);
                scic.setValidityDay(0);
                scic.setVideoWatchCount(0);
                sysConfigItemCourseServiceImpl.updateStatus(scic);
            }
        }
    }

    /**
     * 
     * Class Name: SysConfigItemController.java
     * 
     * @Description: 新增一级项目
     * @author 周文斌
     * @date 2015-5-5 上午11:43:14
     * @version 1.0
     * @param sysConfigItem
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/addPro")
    public String addPro(SysConfigItem sysConfigItem, HttpServletRequest request) {
        try {
            // 查询是否是空或者删除
            wrapperSaveItem(sysConfigItem, SysConfigConstant.OPERATE_ADD, request);
            SysConfigItem sitem = sysConfigItemServiceImpl.findDelNullByName(sysConfigItem);
            Map<String, Object> param = new HashMap<String, Object>();
            if (sitem != null && sitem.getStatus().equals("1")) {
                // 查询关系表是否删除存在
                param.clear();
                param.put("schoolId", sysConfigItem.getSchoolId());
                param.put("itemId", sysConfigItem.getId());
                SysSchoolItemRelation ssir = sysConfigItemServiceImpl.findExist(param);
                if (ssir != null) {
                    param.clear();
                    param.put("id", ssir.getId());
                    param.put("delFlag", 0);
                    param.put("trueDelFlag", 0);
                    param.put("updator", sysConfigItem.getUpdator());
                    param.put("updateTime", sysConfigItem.getUpdateTime());
                    sysConfigItemServiceImpl.updateRelation(param);
                } else {
                    param.clear();
                    param.put("schoolId", WebUtils.getCurrentUserSchoolId(request));
                    param.put("itemId", sysConfigItem.getId());
                    param.put("delFlag", 0);
                    param.put("trueDelFlag", 0);
                    param.put("creator", sysConfigItem.getCreator());
                    param.put("createTime", sysConfigItem.getCreateTime());
                    param.put("updator", sysConfigItem.getUpdator());
                    param.put("updateTime", sysConfigItem.getUpdateTime());
                    sysConfigItemServiceImpl.insertRelation(param);
                }
                // 修改学科次数配置表删除标记
                SysConfigItemCourse scic = new SysConfigItemCourse();
                scic.setCompanyId(WebUtils.getCurrentCompanyId());
                scic.setDelFlag(0);
                scic.setConfigItemId(sysConfigItem.getId());
                sysConfigItemCourseServiceImpl.updateStatus(scic);
            } else {
                sysConfigItemServiceImpl.insert(sysConfigItem);
                // 添加关系
                addOrUpdateRelation(request, sysConfigItem);
                SysConfigItemCourse scic = new SysConfigItemCourse();
                scic.setCompanyId(WebUtils.getCurrentCompanyId());
                scic.setDelFlag(0);
                scic.setConfigItemId(sysConfigItem.getId());
                scic.setValidityDate(new Date());
                scic.setLiveWatchCount(0);
                scic.setValidityDay(0);
                scic.setVideoWatchCount(0);
                sysConfigItemCourseServiceImpl.insert(scic);
            }
        } catch (Exception e) {
            log.error("新增分类出错！", e);
            e.printStackTrace();
            return "false";
        }
        return "true";
    }

    /**
     * 
     * Class Name: SysConfigItemController.java
     * 
     * @Description: 根据公司id 检查项目唯一名
     * @author 周文斌
     * @date 2015-5-7 下午4:45:02
     * @version 1.0
     * @param
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/checkName", method = RequestMethod.POST)
    public String checkName(Integer id,String itemCode, String itemName, HttpServletRequest request, Integer parentId, String status, Integer itemType) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("id", id);
        param.put("itemName", itemName);
        param.put("companyId", WebUtils.getCurrentCompanyId());
        param.put("itemType", itemType);
        param.put("itemCode", itemCode);
        param.put("schoolId", WebUtils.getCurrentUserSchoolId(request));
        if (parentId != null) {
            param.put("parentId", parentId);
        }
        Integer firstItems = null;
        if (status.equals("add")) { // 查询整个库 是否有重名
            firstItems = sysConfigItemServiceImpl.findUnquieItem(param);
            if (firstItems != null && firstItems > 0) {
                return "false";
            }
        } else if (status.equals("update")) { // 查询这个库 其他是否有重名
            firstItems = sysConfigItemServiceImpl.findUnquieItemByUpdate(param);
            if (firstItems != null && firstItems > 0) {
                return "false";
            }
        }
        return "true";
    }

    @ResponseBody
    @RequestMapping(value = "/itemList", method = RequestMethod.POST)
    public List<SysConfigItem> queryItemOneList() {
        // SysConfigItem search = new SysConfigItem();
        // search.setItemType(SysConfigConstant.ITEMTYPE_FIRST);
        // search.setCompanyId(WebUtils.getCurrentCompanyId());
        // search.setSchoolId(WebUtils.getCurrentSchoolId());
        return sysConfigItemServiceImpl.findSysConfigItemByPid(SysConfigConstant.ITEMTYPE_FIRST, null, WebUtils.getCurrentCompanyId(),
                WebUtils.getCurrentSchoolId());
    }

    @ResponseBody
    @RequestMapping(value = "getSecondItemByOne", method = RequestMethod.POST)
    public List<SysConfigItem> getSecondItemByOne(Integer itemOneId) {
        List<SysConfigItem> secondItemList = sysConfigItemServiceImpl.findTwoByOneId(itemOneId);
        return secondItemList;

    }
    /*
     * @ResponseBody
     * 
     * @RequestMapping("/changeStatusCompany") public String
     * changeStatusCompany(HttpServletRequest request,SysConfigItem
     * sysConfigItem){ //查询是否还有分校在使用 该项目 Map<String,Object> params = new
     * HashMap<String, Object>(); params.put("schoolId",
     * WebUtils.getCurrentUserSchoolId(request)); params.put("itemId",
     * sysConfigItem.getId()); SysSchoolItemRelation rel =
     * sysConfigItemServiceImpl.findExist(params);
     * if(sysConfigItem.getStatus().equals("1")){ if(rel != null && rel.getId()
     * != null && rel.getDelFlag() == 0){ return "error"; }else{
     * sysConfigItem.setStatus("0"); sysConfigItem.setUpdateTime(new Date());
     * sysConfigItem.setUpdator(getCurrentUserId(request));
     * sysConfigItemServiceImpl.update(sysConfigItem); SysConfigItem sci = new
     * SysConfigItem(); sci.setParentId(sysConfigItem.getId());
     * sci.setStatus(sysConfigItem.getStatus());
     * sci.setUpdateTime(sysConfigItem.getUpdateTime());
     * sci.setUpdator(sysConfigItem.getUpdator());
     * sysConfigItemServiceImpl.updateTwoByOne(sci); return JsonMsg.SUCCESS; }
     * }else{ sysConfigItem.setStatus("1"); sysConfigItem.setUpdateTime(new
     * Date()); sysConfigItem.setUpdator(getCurrentUserId(request));
     * sysConfigItemServiceImpl.update(sysConfigItem); SysConfigItem sci = new
     * SysConfigItem(); sci.setParentId(sysConfigItem.getId());
     * sci.setStatus(sysConfigItem.getStatus());
     * sci.setUpdateTime(sysConfigItem.getUpdateTime());
     * sci.setUpdator(sysConfigItem.getUpdator());
     * sysConfigItemServiceImpl.updateTwoByOne(sci); return JsonMsg.SUCCESS; } }
     */

    /**
     * 
     * Class Name: SysConfigItemController.java
     * 
     * @Description: 查询二级项目
     * @author chopin
     * @date 2015年4月8日 下午3:37:12
     * @modifier
     * @modify-date 2015年4月8日 下午3:37:12
     * @version 1.0
     * @param
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findItemSecond/{itemOne}/{schoolId}", method = RequestMethod.POST)
    public List<SysConfigItem> findItemSecond(HttpServletRequest request, Model model, @PathVariable Integer itemOne, @PathVariable Integer schoolId) {
        List<SysConfigItem> itemTwoList = sysConfigItemServiceImpl.findSysConfigItemByPid("2", itemOne, WebUtils.getCurrentCompanyId(), schoolId);
        return itemTwoList;
    }

    /**
     * 
     * Class Name: SysConfigItemController.java
     * 
     * @Description: 编辑
     * @author 周文斌
     * @date 2015-6-17 下午12:19:53
     * @version 1.0
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/findById")
    public JSONObject findById(Integer id) {
        JSONObject json = new JSONObject();
        SysConfigItem item = sysConfigItemServiceImpl.findSysConfigItemById(id);

        json.put("id", item.getId());
        json.put("name", item.getItemName());
        json.put("remark", item.getRemark());
        json.put("pic", item.getItemPic());
        json.put("itemCode", item.getItemCode());
        return json;
    }

    @ResponseBody
    @RequestMapping("/update")
    public String update(HttpServletRequest request, SysConfigItem sysConfigItem) {
        try {
            wrapperSaveItem(sysConfigItem, SysConfigConstant.OPERATE_EDIT, request);
            sysConfigItemServiceImpl.update(sysConfigItem);

        } catch (Exception e) {
            log.error("新增项目出错！", e);
            e.printStackTrace();
            return "false";
        }
        return "true";
    }

    /**
     * 
     * Class Name: SysConfigItemController.java
     * 
     * @Description: 起停用
     * @author 周文斌
     * @date 2015-8-14 下午2:28:31
     * @version 1.0
     * @return
     */
    @ResponseBody
    @RequestMapping("/starStopUpdate")
    public JSONObject starStopUpdate(HttpServletRequest request, SysConfigItem sysConfigItem) {
        JSONObject json = new JSONObject();
        try {
            sysConfigItem = sysConfigItemServiceImpl.findSysConfigItemById(sysConfigItem.getId());
            addOrUpdateRelation(request, sysConfigItem);
            // 查询当前项目的二级项目
            List<SysConfigItem> twoProList = sysConfigItemServiceImpl.findTwoByOneId(sysConfigItem.getId());
            for (SysConfigItem scit : twoProList) {
                addOrUpdateRelation(request, scit);
            }
            json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
            return json;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            json.put(JsonMsg.MSG, JsonMsg.INFORMATION);
            return json;
        }
    }

    /**
     * 
     * Class Name: SysConfigItemController.java
     * 
     * @Description: 删除
     * @author 周文斌
     * @date 2015-8-14 下午3:43:35
     * @version 1.0
     * @param request
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/delpro")
    public JSONObject delpro(HttpServletRequest request, Integer id) {
        JSONObject json = new JSONObject();
        Integer companyId = WebUtils.getCurrentCompanyId();
        Integer schoolId = WebUtils.getCurrentUserSchoolId(request);
        try {
            // 删除
            // 查询是否还有其他分校在使用
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("itemId", id);
            param.put("schoolId", schoolId);
            SysSchoolItemRelation rel = sysConfigItemServiceImpl.findExist(param);
            List<SysSchoolItemRelation> relList = sysConfigItemServiceImpl.findUseByItemId(param);
            SysConfigItem item = sysConfigItemServiceImpl.findSysConfigItemById(id);
            if (item.getItemType().equals("1")) {
                // 修改配置
                SysConfigItemCourse scic = new SysConfigItemCourse();
                scic.setCompanyId(companyId);
                scic.setDelFlag(1);
                scic.setConfigItemId(item.getId());
                scic.setLiveWatchCount(0);
                scic.setValidityDay(0);
                scic.setVideoWatchCount(0);
                sysConfigItemCourseServiceImpl.updateStatus(scic);
            }
            if (relList != null && relList.size() > 0) {
                // 修改关系表删除标记
                param.clear();
                param.put("id", rel.getId());
                param.put("delFlag", 1);
                param.put("trueDelFlag", 1);
                param.put("updator", WebUtils.getCurrentUserId(request));
                param.put("updateTime", new Date());
                sysConfigItemServiceImpl.updateRelation(param);
                // 查询是否是一级项目
                if (item.getItemType().equals("1")) {
                    List<SysConfigItem> twoProList = sysConfigItemServiceImpl.findTwoByOneId(id);
                    for (SysConfigItem s : twoProList) {
                        param.clear();
                        param.put("itemId", s.getId());
                        param.put("schoolId", schoolId);
                        SysSchoolItemRelation rels = sysConfigItemServiceImpl.findExist(param);

                        param.put("id", rels.getId());
                        param.put("delFlag", 1);
                        param.put("trueDelFlag", 1);
                        param.put("updator", WebUtils.getCurrentUserId(request));
                        param.put("updateTime", new Date());
                        sysConfigItemServiceImpl.updateRelation(param);

                        updateCom(s.getId(), s.getParentId(), schoolId, companyId);
                    }
                } else {
                    updateCom(item.getId(), item.getParentId(), schoolId, companyId);
                }
            } else {
                // 修改项目和关系表
                param.clear();
                param.put("id", rel.getId());
                param.put("delFlag", 1);
                param.put("trueDelFlag", 1);
                param.put("updator", WebUtils.getCurrentUserId(request));
                param.put("updateTime", new Date());
                sysConfigItemServiceImpl.updateRelation(param);

                item.setStatus("0");
                item.setUpdateTime(new Date());
                item.setUpdator(WebUtils.getCurrentUserId(request));
                sysConfigItemServiceImpl.update(item);
                if (item.getItemType().equals("1")) {
                    item.setParentId(id);
                    sysConfigItemServiceImpl.updateTwoByOne(item);

                    List<SysConfigItem> twoProList = sysConfigItemServiceImpl.findTwoByOneId(id);
                    for (SysConfigItem s : twoProList) {
                        param.clear();
                        param.put("itemId", s.getId());
                        param.put("schoolId", schoolId);
                        SysSchoolItemRelation rels = sysConfigItemServiceImpl.findExist(param);

                        param.put("id", rels.getId());
                        param.put("delFlag", 1);
                        param.put("trueDelFlag", 1);
                        param.put("updator", WebUtils.getCurrentUserId(request));
                        param.put("updateTime", new Date());
                        sysConfigItemServiceImpl.updateRelation(param);

                        updateCom(s.getId(), s.getParentId(), schoolId, companyId);
                    }
                } else {
                    updateCom(item.getId(), item.getParentId(), schoolId, companyId);
                }
            }
            json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
            return json;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            json.put(JsonMsg.MSG, JsonMsg.INFORMATION);
            return json;
        }
    }

    /**
     * 
     * Class Name: SysConfigItemController.java
     * 
     * @Description: 图标
     * @author 周文斌
     * @date 2015-8-18 下午12:41:17
     * @version 1.0
     * @param page
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping("/selIconList")
    public JSONObject selIconList(Integer page, Integer pageSize) {
        JSONObject json = new JSONObject();
        try {
            json.put(JsonMsg.MSG, selIcon(page, pageSize));
            json.put(JsonMsg.URL, propertiesUtil.getProjectImageUrl());
            return json;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            json.put(JsonMsg.MSG, JsonMsg.INFORMATION);
            return json;
        }

    }

    private Map<String, Object> selIcon(Integer page, Integer pageSize) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("page", ((page - 1) * pageSize));
        param.put("pageSize", pageSize);
        // 分页查询
        List<SysConfigItemIcon> iconList = sysConfigItemIconServiceImpl.findByPage(param);
        // 查询总数
        Integer count = sysConfigItemIconServiceImpl.findByPageCount(param);
        // 总页数
        Integer pagecount = (count % pageSize) == 0 ? (count / pageSize) : ((count / pageSize) + 1);

        param.put("nowpage", page);
        param.put("pagecount", pagecount);
        param.put("iconList", iconList);
        return param;
    }

    private void updateCom(Integer twoItemId, Integer oneItemId, Integer schoolId, Integer companyId) {
        // 修改商品表
        // 查询分校下所有商品的id
        Commodity com = new Commodity();
        com.setItemOneId(oneItemId);
        com.setItemSecondId(twoItemId);
        com.setSchoolId(schoolId);
        com.setCompanyId(companyId);
        com.setStatus("1");
        Map<String, Object> param = new HashMap<String, Object>();
        List<Integer> commId = commodityServiceImpl.findComId(com);
        if (commId != null && commId.size() > 0) {
            param.put("status", 0);
            param.put("comId", commId);
            commodityServiceImpl.updateStatus(param);
        }
        // 查询班型
        ClassType ct = new ClassType();
        ct.setItemOneId(oneItemId);
        ct.setItemSecondId(twoItemId);
        ct.setCompanyId(companyId);
        ct.setCreateSchoolId(schoolId);
        ct.setDelFlag(0);
        List<Integer> classId = classTypeServiceImpl.findClassTypeByClass(ct);
        if (classId != null && classId.size() > 0) {
            param.put("delFlag", 1);
            param.put("classId", classId);
            classTypeServiceImpl.updateDeflag(param);
        }

        // 修改所有module
        param.put("oneItemId", oneItemId);
        param.put("twoItemId", twoItemId);
        classModuleServiceImpl.updateModelByItem(param);

        // 修改所有公开课
        liveOpenCourseServiceImpl.updateOpenclassByItem(param);
    }

    @ResponseBody
    @RequestMapping(value = "/getItems", method = RequestMethod.POST)
    public List<SysConfigItem> getItems(HttpServletRequest request, String ids) {
        List<SysConfigItem> result = null;
        if (StringUtils.isNotBlank(ids)) {
            List<Integer> list = new ArrayList<Integer>();
            String[] arr = ids.split(",");
            for (String s : arr) {
                list.add(Integer.parseInt(s));
            }
            if (list.size() > 0) {
                result = sysConfigItemServiceImpl.findItemByIds(list);
            }
        }
        return result;
    }
}
