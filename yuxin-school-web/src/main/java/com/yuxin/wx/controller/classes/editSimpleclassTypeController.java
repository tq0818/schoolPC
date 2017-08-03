package com.yuxin.wx.controller.classes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.yuxin.wx.api.system.ISysConfigItemRelationService;
import com.yuxin.wx.model.system.SysConfigItemRelation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
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

import com.yuxin.wx.api.classes.IClassModuleNoService;
import com.yuxin.wx.api.classes.IClassModuleService;
import com.yuxin.wx.api.classes.IClassTypeModuleRelationService;
import com.yuxin.wx.api.classes.IClassTypeService;
import com.yuxin.wx.api.commodity.ICommodityProductRealtionService;
import com.yuxin.wx.api.commodity.ICommodityService;
import com.yuxin.wx.api.company.ICompanyFunctionSetService;
import com.yuxin.wx.api.course.ICoursePotocolBindHistoryService;
import com.yuxin.wx.api.system.ISysConfigItemService;
import com.yuxin.wx.api.system.ISysConfigItemTagService;
import com.yuxin.wx.api.system.ISysConfigTeacherService;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.classes.ClassType;
import com.yuxin.wx.model.classes.ClassTypeModuleRelation;
import com.yuxin.wx.model.commodity.Commodity;
import com.yuxin.wx.model.commodity.CommodityProductRealtion;
import com.yuxin.wx.model.company.CompanyFunctionSet;
import com.yuxin.wx.model.course.CoursePotocolBindHistory;
import com.yuxin.wx.model.course.CourseVideoChapter;
import com.yuxin.wx.model.system.SysConfigItem;
import com.yuxin.wx.model.system.SysConfigItemTag;
import com.yuxin.wx.model.system.SysConfigTeacher;
import com.yuxin.wx.model.user.Users;
//import com.yuxin.wx.utils.FileUtil;
import com.yuxin.wx.utils.PropertiesUtil;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.classes.ClassModuleNoListVo;
import com.yuxin.wx.vo.classes.ClassTypeVo;
import com.yuxin.wx.vo.course.ChapterAndLectureListVo;

/**
 * Controller of simpleClassType
 *
 * @author zhang.zx
 * @date 2014-12-5
 */
@Controller
@RequestMapping("/editSimpleCourse")
public class editSimpleclassTypeController {

    private Log log = LogFactory.getLog("log");

    @Autowired
    private IClassTypeService classTypeServiceImpl;
    @Autowired
    private IClassTypeModuleRelationService classTypeModuleRelationServiceImpl;
    @Autowired
    private IClassModuleService classModuleServiceImpl;
    @Autowired
    private PropertiesUtil propertiesUtil;
    @Autowired
    private ISysConfigTeacherService sysConfigTeacherServiceImpl;
    @Autowired
    private ICommodityService commodityServiceImpl;
    @Autowired
    private ICommodityProductRealtionService commodityProductRealtionServiceImpl;
    @Autowired
    private ISysConfigItemService sysConfigItemServiceImpl;
    @Autowired
    private IClassModuleNoService classModuleNoServiceImpl;
    @Autowired
    private ISysConfigItemTagService sysConfigItemTagServiceImpl;
    @Autowired
    private ICompanyFunctionSetService companyFunctionSetServiceImpl;
    @Autowired
    private ICoursePotocolBindHistoryService coursePotocolBindHistoryServiceImpl;
    @Autowired
    private ISysConfigItemRelationService sysConfigItemRelationServiceImpl;
    /**
     * 编辑班型(第一步)
     *
     * @param model
     * @param request
     * @param ct
     * @return
     */
    @RequestMapping(value = "/editClassTypeMessage", method = RequestMethod.POST)
    public String editClassTypeMessage(Model model, HttpServletRequest request, ClassType ct, String lable) {
        // 根据班型id查询详情
    	String type = request.getParameter("type");
        Map<String, String> map = new HashMap<String, String>();
        map.put("classId", "" + ct.getId());
        ClassTypeVo classType = this.classTypeServiceImpl.findClassTypeDetail(map);
        model.addAttribute("classType", classType);
        model.addAttribute("ct", classType);
        model.addAttribute("type", "update");
        SysConfigItemRelation relation = new SysConfigItemRelation();
        relation.setId(null);
        List<SysConfigItemRelation> relations = sysConfigItemRelationServiceImpl.findItemFront(relation);
        SysConfigItem item = new SysConfigItem();
        item.setCompanyId(WebUtils.getCurrentCompanyId());
        item.setSchoolId( WebUtils.getCurrentUserSchoolId(request));
        item.setItemType("2");
        item.setParentCode("TYPE");
        List<SysConfigItem> names = sysConfigItemServiceImpl.findByParentCode(item);
        for(SysConfigItemRelation re : relations){
            for(SysConfigItem name :names){
                if(re.getItemCode().equals(name.getItemCode())){
                    re.setItemName(name.getItemName());
                    break;
                }
            }
        }

        model.addAttribute("typeItems", relations);
        boolean flag = this.companyFunctionSetServiceImpl.isCurrentFuSheng(WebUtils.getCurrentCompanyId());
        Subject subject = SecurityUtils.getSubject();
        Users currentUser = WebUtils.getCurrentUser();
        if (flag && subject.hasRole("排课老师") && !(subject.hasRole("机构管理员") || subject.hasRole("分校管理员") || subject.hasRole("运营"))) {
            model.addAttribute("isFuShengPaikeTeac", true);
            List<SysConfigTeacher> teacherList = this.sysConfigTeacherServiceImpl.findTeachersByUserId(currentUser.getId());
            if (teacherList != null && teacherList.size() > 0) {
                SysConfigTeacher configTeacher = teacherList.get(0);
                ClassType cs = this.classTypeServiceImpl.findClassTypeById(ct.getId());
                if (cs != null && cs.getTeacherId() != null) {
                    SysConfigTeacher teacher = this.sysConfigTeacherServiceImpl.findSysConfigTeacherById(Integer.parseInt(cs.getTeacherId()));
                    if (configTeacher.getId() == teacher.getId()) {
                        model.addAttribute("configTeacher", configTeacher);
                    } else {
                        model.addAttribute("configTeacher", teacher);
                    }
                } else {
                    model.addAttribute("configTeacher", configTeacher);
                }
            }
        }
        if (null != classType) {
            if (classType.getLiveFlag() == 1 && classType.getFaceFlag() == 1) {
                model.addAttribute("ftype", "live,face");
            } else if (classType.getLiveFlag() == 1 && classType.getFaceFlag() == 0) {
                model.addAttribute("ftype", "live");
            } else if (classType.getLiveFlag() == 0 && classType.getFaceFlag() == 1) {
                model.addAttribute("ftype", "face");
            }
        }
        if (lable.contains(",")) {
            String[] strs = lable.split(",");
            String fla = strs[0];
            if (strs.length > 1 && !"remote".equals(fla)) {
                model.addAttribute("lable", "togther");
                return "simpleClasses/editClass/addClassTypeTogtherMessage_1";
            } else if ("face".equals(fla) || "live".equals(fla)) {
                model.addAttribute("lable", fla);
                return "simpleClasses/editClass/addClassTypeFaceOrLiveMessage";
            } else if ("video".equals(fla)) {
                ClassModule m = this.classModuleServiceImpl.queryModuleByClasstypeId(classType.getId());
                if (m != null) {
                    model.addAttribute("courseNum", m.getTotalClassHour());
                } else {
                    model.addAttribute("courseNum", 0);
                }
                model.addAttribute("lable", fla);
                return "simpleClasses/editClass/addClassTypeVideoMessage";
            } else {
                model.addAttribute("lable", "other");
                return "simpleClasses/editClass/addClassTypeOtherMessage";
            }
        } else {
            model.addAttribute("lable", lable);
            // 根据所属标签跳转不同页面
            if ("face".equals(lable) || "live".equals(lable)) {

                return "simpleClasses/editClass/addClassTypeFaceOrLiveMessage";
            } else if ("video".equals(lable)) {
                ClassModule m = this.classModuleServiceImpl.queryModuleByClasstypeId(classType.getId());
                if (m != null) {
                    model.addAttribute("courseNum", m.getTotalClassHour());
                } else {
                    model.addAttribute("courseNum", 0);
                }
                return "simpleClasses/editClass/addClassTypeVideoMessage";
            } else if ("togther".equals(lable)) {

                return "simpleClasses/editClass/addClassTypeTogtherMessage_1";
            } else {
                return "simpleClasses/editClass/addClassTypeOtherMessage";
            }
        }
    }

    @RequestMapping(value = "/editClassBaseInfo/{id}/{lable}")
    public String editClassBaseInfo( HttpServletRequest request,  Model model, @PathVariable Integer id, @PathVariable String lable) {
        // 根据班型id查询详情
        Map<String, String> map = new HashMap<String, String>();
        map.put("classId", "" + id);
        ClassTypeVo classType = this.classTypeServiceImpl.findClassTypeDetail(map);
        model.addAttribute("classType", classType);
        model.addAttribute("ct", classType);
        model.addAttribute("type", "update");
        model.addAttribute("lable", lable);
        SysConfigItemRelation relation = new SysConfigItemRelation();
        relation.setId(null);
        List<SysConfigItemRelation> relations = sysConfigItemRelationServiceImpl.findItemFront(relation);
        SysConfigItem item = new SysConfigItem();
        item.setCompanyId(WebUtils.getCurrentCompanyId());
        item.setSchoolId( WebUtils.getCurrentUserSchoolId(request));
        item.setItemType("2");
        item.setParentCode("TYPE");
        List<SysConfigItem> names = sysConfigItemServiceImpl.findByParentCode(item);
        for(SysConfigItemRelation re : relations){
            for(SysConfigItem name :names){
                if(re.getItemCode().equals(name.getItemCode())){
                    re.setItemName(name.getItemName());
                    break;
                }
            }
        }

        model.addAttribute("typeItems", relations);
        if (null != classType) {
            if (classType.getLiveFlag() == 1 && classType.getFaceFlag() == 1) {
                model.addAttribute("ftype", "live,face");
            } else if (classType.getLiveFlag() == 1 && classType.getFaceFlag() == 0) {
                model.addAttribute("ftype", "live");
            } else if (classType.getLiveFlag() == 0 && classType.getFaceFlag() == 1) {
                model.addAttribute("ftype", "face");
            }
        }
        // 根据所属标签跳转不同页面
        if ("face".equals(lable) || "live".equals(lable)) {
            return "simpleClasses/editClass/addClassTypeFaceOrLiveMessage";
        } else if ("video".equals(lable)) {
            ClassModule m = this.classModuleServiceImpl.queryModuleByClasstypeId(classType.getId());
            if (m != null) {
                model.addAttribute("courseNum", m.getTotalClassHour());
            } else {
                model.addAttribute("courseNum", 0);
            }
            return "simpleClasses/editClass/addClassTypeVideoMessage";
        } else if ("togther".equals(lable)) {

            return "simpleClasses/editClass/addClassTypeTogtherMessage_1";
        } else {
            return "simpleClasses/editClass/addClassTypeOtherMessage";
        }
    }

    @RequestMapping(value = "/editCourseDetail/{id}/{lable}")
    public String editCourseDetail(Model model, @PathVariable Integer id, @PathVariable String lable) {
        ClassType cs = this.classTypeServiceImpl.findClassTypeById(id);
        model.addAttribute("type2", "update");
        model.addAttribute("ct", cs);
        model.addAttribute("lable", lable);
        if (cs.getCover() != null && !"".equals(cs.getCover())) {
            String url = "http://" + this.propertiesUtil.getProjectImageUrl() + "/";
            cs.setCover(url + cs.getCover());
        } else {
            cs.setCover("");
        }
        if (null != cs) {
            if (cs.getLiveFlag() == 1 && cs.getFaceFlag() == 1) {
                model.addAttribute("ftype", "live,face");
            } else if (cs.getLiveFlag() == 1 && cs.getFaceFlag() == 0) {
                model.addAttribute("ftype", "live");
            } else if (cs.getLiveFlag() == 0 && cs.getFaceFlag() == 1) {
                model.addAttribute("ftype", "face");
            }
        }
        SysConfigItem item = this.sysConfigItemServiceImpl.findSysConfigItemById(cs.getItemOneId());
        SysConfigItem item2 = this.sysConfigItemServiceImpl.findSysConfigItemById(cs.getItemSecondId());
        if (item != null) {
            cs.setItemOneName(item.getItemName());
        }
        if (item2 != null) {
            cs.setItemSecondName(item2.getItemName());
        }
        model.addAttribute("ct", cs);
        model.addAttribute("ctype", cs);
        if (cs.getTeacherId() != null) {
            SysConfigTeacher teacher = this.sysConfigTeacherServiceImpl.findSysConfigTeacherById(Integer.parseInt(cs.getTeacherId()));
            model.addAttribute("teacher", teacher);
        }
        model.addAttribute("classTypeId", cs.getId());
        // 根据班型id查询商品信息id
        CommodityProductRealtion comm = this.commodityProductRealtionServiceImpl.findByClassTypeId(cs.getId() + "");
        Integer cId = comm.getComId();
        model.addAttribute("cId", cId);
        model.addAttribute("itemOneid", cs.getItemOneId());

        Map<String, String> teacherMap = new HashMap<String, String>();
        teacherMap.put("companyId", WebUtils.getCurrentCompanyId() + "");
        teacherMap.put("schoolId", WebUtils.getCurrentSchoolId() + "");
        Subject subject = SecurityUtils.getSubject();
        if((subject.hasRole("直播老师")&&subject.hasRole("排课老师"))&&!(subject.hasRole("机构管理员")||subject.hasRole("分校管理员")||subject.hasRole("运营")))
        {
        	Users user = WebUtils.getCurrentUser();
        	SysConfigTeacher teacher2 = new SysConfigTeacher();
	        	teacher2.setUserId(user.getId());
	        	teacher2.setCompanyId(WebUtils.getCurrentCompanyId());
	        	 List<SysConfigTeacher> list = this.sysConfigTeacherServiceImpl.findTeacherByUserId(teacher2);
	        	 if(list != null && list.size()>0){
	        		 teacherMap.put("id", list.get(0).getId()+"");
	        	 }
        }
        	model.addAttribute("teachers", this.sysConfigTeacherServiceImpl.findTeachers(teacherMap));
        // 根据所属标签跳转不同页面
        boolean flag = this.companyFunctionSetServiceImpl.isCurrentFuSheng(WebUtils.getCurrentCompanyId());
        Users currentUser = WebUtils.getCurrentUser();
        if (flag && subject.hasRole("排课老师") && !(subject.hasRole("机构管理员") || subject.hasRole("分校管理员") || subject.hasRole("运营"))) {
            model.addAttribute("isFuShengPaikeTeac", true);
            List<SysConfigTeacher> teacherList = this.sysConfigTeacherServiceImpl.findTeachersByUserId(currentUser.getId());
            if (teacherList != null && teacherList.size() > 0) {
                SysConfigTeacher configTeacher = teacherList.get(0);
                if (cs != null && cs.getTeacherId() != null) {
                    SysConfigTeacher teacher = this.sysConfigTeacherServiceImpl.findSysConfigTeacherById(Integer.parseInt(cs.getTeacherId()));
                    if (configTeacher.getId() == teacher.getId()) {
                        model.addAttribute("configTeacher", configTeacher);
                    } else {
                        model.addAttribute("configTeacher", teacher);
                    }
                } else {
                    model.addAttribute("configTeacher", configTeacher);
                }
            }
        }
        if ("face".equals(lable) || "live".equals(lable)) {

            return "simpleClasses/editClass/addClassTypeFaceOrLiveMessage_2";
        } else if ("video".equals(lable)) {
            // 查询班型下的模块信息
            ClassModule m = this.classModuleServiceImpl.queryModuleByClasstypeId(cs.getId());
            model.addAttribute("classTypeId", cs.getId());
            model.addAttribute("moduleName", cs.getName());
            model.addAttribute("itemOneId", cs.getItemOneId());
            model.addAttribute("itemSecondId", cs.getItemSecondId());
            if (m != null) {
                model.addAttribute("moduleId", m.getId());
            }
            model.addAttribute("id", cs.getId());
            return "simpleClasses/editClass/addClassTypeVideoMessage_2";
        } else if ("remote".equals(lable)) {

            return "simpleClasses/editClass/addClassTypeOtherMessage_2";
        } else {

            return "simpleClasses/editClass/addClassTypeTogtherMessage_2";
        }
    }

    @RequestMapping(value = "/editCourseVideo/{id}/{lable}")
    public String editCourseVideo(Model model, @PathVariable Integer id, @PathVariable String lable, HttpServletRequest request) {
        // Integer moduleId=Integer.parseInt(request.getParameter("moduleId"));
        String ftype = request.getParameter("ftype");
        ClassType ct = this.classTypeServiceImpl.findClassTypeById(id);
        SysConfigItem item = this.sysConfigItemServiceImpl.findSysConfigItemById(ct.getItemOneId());
        SysConfigItem item2 = this.sysConfigItemServiceImpl.findSysConfigItemById(ct.getItemSecondId());
        if (item != null) {
            ct.setItemOneName(item.getItemName());
        }
        if (item2 != null) {
            ct.setItemSecondName(item2.getItemName());
        }
        if (null != ct) {
            if (ct.getLiveFlag() == 1 && ct.getFaceFlag() == 1) {
                model.addAttribute("ftype", "live,face");
            } else if (ct.getLiveFlag() == 1 && ct.getFaceFlag() == 0) {
                model.addAttribute("ftype", "live");
            } else if (ct.getLiveFlag() == 0 && ct.getFaceFlag() == 1) {
                model.addAttribute("ftype", "face");
            }
        }
        ClassModule m = this.classModuleServiceImpl.queryModuleByClasstypeId(ct.getId());
        if (null != m && m.getId() != null) {
            model.addAttribute("moduleId", m.getId());
        }
        boolean flag = this.companyFunctionSetServiceImpl.isCurrentFuSheng(WebUtils.getCurrentCompanyId());
        Subject subject = SecurityUtils.getSubject();
        Users currentUser = WebUtils.getCurrentUser();
        if (flag && subject.hasRole("排课老师") && !(subject.hasRole("机构管理员") || subject.hasRole("分校管理员") || subject.hasRole("运营"))) {
            model.addAttribute("isFuShengPaikeTeac", true);
            List<SysConfigTeacher> teacherList = this.sysConfigTeacherServiceImpl.findTeachersByUserId(currentUser.getId());
            if (teacherList != null && teacherList.size() > 0) {
                SysConfigTeacher configTeacher = teacherList.get(0);
                if (ct != null && ct.getTeacherId() != null) {
                    SysConfigTeacher teacher = this.sysConfigTeacherServiceImpl.findSysConfigTeacherById(Integer.parseInt(ct.getTeacherId()));
                    if (configTeacher.getId() == teacher.getId()) {
                        model.addAttribute("configTeacher", configTeacher);
                    } else {
                        model.addAttribute("configTeacher", teacher);
                    }
                } else {
                    model.addAttribute("configTeacher", configTeacher);
                }
            }
        }
        model.addAttribute("ct", ct);
        model.addAttribute("lable", lable);
        model.addAttribute("type", "update");
        if ("togther".equals(lable)) {
            String[] lab = ftype.split(",");
            for (int i = 0; i < lab.length; i++) {
                if ("video".equals(lab[i])) {
                    return "simpleClasses/editClass/addClassTypeTogtherMessage_3";
                } else if ("live".equals(lab[i])) {
                    return "simpleClasses/addClassTypeTogtherMessage_4";
                } else if ("face".equals(lab[i])) {
                    return "simpleClasses/addClassTypeTogtherMessage_5";
                }
            }
            return "simpleClasses/editClass/addClassTypeTogtherMessage_3";
        }
        return "simpleClasses/editClass/addClassTypeTogtherMessage_3";
    }

    @RequestMapping(value = "/editClassTypeVideo/{id}/{lable}")
    public String editClassTypeVideo(Model model, @PathVariable Integer id, @PathVariable String lable) {
        ClassType ct = this.classTypeServiceImpl.findClassTypeById(id);
        SysConfigItem item = this.sysConfigItemServiceImpl.findSysConfigItemById(ct.getItemOneId());
        SysConfigItem item2 = this.sysConfigItemServiceImpl.findSysConfigItemById(ct.getItemSecondId());
        boolean flag = this.companyFunctionSetServiceImpl.isCurrentFuSheng(WebUtils.getCurrentCompanyId());
        Subject subject = SecurityUtils.getSubject();
        Users currentUser = WebUtils.getCurrentUser();
        if (flag && subject.hasRole("排课老师") && !(subject.hasRole("机构管理员") || subject.hasRole("分校管理员") || subject.hasRole("运营"))) {
            model.addAttribute("isFuShengPaikeTeac", true);
            List<SysConfigTeacher> teacherList = this.sysConfigTeacherServiceImpl.findTeachersByUserId(currentUser.getId());
            if (teacherList != null && teacherList.size() > 0) {
                SysConfigTeacher configTeacher = teacherList.get(0);
                if (configTeacher.getTeacherId() != null) {
                    if (ct != null && ct.getTeacherId() != null) {
                        SysConfigTeacher teacher = this.sysConfigTeacherServiceImpl.findSysConfigTeacherById(Integer.parseInt(ct.getTeacherId()));
                        if (configTeacher.getId() == teacher.getId()) {
                            model.addAttribute("configTeacher", configTeacher);
                        } else {
                            model.addAttribute("configTeacher", teacher);
                        }
                    }
                } else {
                    model.addAttribute("configTeacher", configTeacher);
                }
            }
        }
        if (item != null) {
            ct.setItemOneName(item.getItemName());
        }
        if (item2 != null) {
            ct.setItemSecondName(item2.getItemName());
        }
        ClassModule m = this.classModuleServiceImpl.queryModuleByClasstypeId(ct.getId());
        if (null != m) {
            model.addAttribute("moduleId", m.getId());
        }
        model.addAttribute("ct", ct);
        model.addAttribute("lable", lable);
        model.addAttribute("type", "update");
        return "simpleClasses/editClass/addClassTypeVideoMessage_3";
    }

    // -----------------------新增内容上----------------------------

    /**
     *
     * Class Name: ClassTypeController.java
     *
     * @Description: 修改班型基本信息并跳转第二步
     * @author zhang.zx
     * @date 2015年5月9日 下午3:31:22
     * @modifier
     * @modify-date 2015年5月9日 下午3:31:22
     * @version 1.0
     * @param model
     * @param request
     * @param ct
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateClassTypeMessage", method = RequestMethod.POST)
    public String updateClassTypeMessage(Model model, HttpServletRequest request, Integer oneId, Integer twoId, String type1, ClassType ct, String mark,
            String lable, String ltype, Integer courseNum) {
        // 协议id有变化时更新协议记录表
        ClassType oldCt = this.classTypeServiceImpl.findClassTypeById(ct.getId());
        Integer oldProtocolId = oldCt.getProtocolId();
        Integer protocolId = null;

        // 课程绑定协议插入协议绑定历史表
        if (ct.getProtocolId() != null && ct.getProtocolId() != 0) {
            if (oldProtocolId != null && oldProtocolId != 0 && oldProtocolId != ct.getProtocolId()) {
                protocolId = oldProtocolId;
            }
        } else {
            if (oldProtocolId != null && oldProtocolId != 0) {
                protocolId = oldProtocolId;
            }
        }
        if (protocolId != null) {
            CoursePotocolBindHistory cbh = new CoursePotocolBindHistory();
            cbh.setCourseId(ct.getId());
            cbh.setBindDate(new Date());
            cbh.setBinder(WebUtils.getCurrentUserId(request));
            cbh.setCompanyId(WebUtils.getCurrentCompanyId());
            cbh.setPotocolId(protocolId);
            this.coursePotocolBindHistoryServiceImpl.insert(cbh);
        }

        if (null != ltype && !"".equals(ltype)) {
            if ("togther".equals(lable)) {
                String r = ltype.substring(0, ltype.length() - 1);
                String[] b = r.split(",");
                for (int i = 0; i < b.length; i++) {
                    if ("face".equals(b[i])) {
                        ct.setFaceFlag(1);
                    }
                    if ("live".equals(b[i])) {
                        ct.setLiveFlag(1);
                    }
                    if ("video".equals(b[i])) {
                        ct.setVideoFlag(1);
                    }
                }
                if (null == ct.getFaceFlag() || "".equals(ct.getFaceFlag())) {
                    ct.setFaceFlag(0);
                }
                if (null == ct.getLiveFlag() || "".equals(ct.getLiveFlag())) {
                    ct.setLiveFlag(0);
                }
                if (null == ct.getVideoFlag() || "".equals(ct.getVideoFlag())) {
                    ct.setVideoFlag(0);
                }
                ct.setRemoteFlag(0);
            }
        }
        ct.setItemOneId(oneId);
        ct.setItemSecondId(twoId);
        ct.setUpdateTime(new Date());
        ct.setUpdator(WebUtils.getCurrentUserId(request));

        // 如果开启标签库则将标签存库
        CompanyFunctionSet conditon = new CompanyFunctionSet();
        conditon.setCompanyId(WebUtils.getCurrentCompanyId());
        conditon.setFunctionCode("COURSE_THIRD_CATEGORY");
        conditon.setStatus("1");
        CompanyFunctionSet sets = this.companyFunctionSetServiceImpl.findCompanyUseCourse(conditon);
        // 如果开启标签库则将标签存库
        conditon.setFunctionCode("COURSE_SECOND_CATEGORY");
        CompanyFunctionSet sets1 = this.companyFunctionSetServiceImpl.findCompanyUseCourse(conditon);
        if (null != sets || null != sets1) {
            if (null != ct.getTagName() && !"".equals(ct.getTagName())) {
                try {
                    String tagId = "";
                    String str[] = ct.getTagName().split(",");
                    for (int i = 0; i < str.length; i++) {
                        if (str[i] != null && !"null".equals(str[i])) {
                            SysConfigItemTag search = new SysConfigItemTag();
                            search.setCompanyId(WebUtils.getCurrentCompanyId());
                            search.setSchoolId(WebUtils.getCurrentSchoolId());
                            search.setTagName(str[i]);
                            if (i == 0) {
                                search.setLevel(1);
                            } else {
                                search.setLevel(2);
                            }
                            List<SysConfigItemTag> arr = this.sysConfigItemTagServiceImpl.queryTagsBycondition(search);
                            if (arr.size() > 0) {
                                tagId += arr.get(0).getId() + ",";
                            } else {
                                search.setItemOneId(ct.getItemOneId());
                                search.setItemSecondId(ct.getItemSecondId());
                                search.setCreateTime(new Date());
                                search.setUpdateTime(new Date());
                                search.setCreator(WebUtils.getCurrentUserId(request));
                                search.setUpdator(WebUtils.getCurrentUserId(request));
                                this.sysConfigItemTagServiceImpl.insert(search);
                                tagId += search.getId() + ",";
                            }
                        } else {
                            tagId += "null" + ",";
                        }
                    }
                    if (null != tagId && !"".equals(tagId) && !"null,null,".equals(tagId)) {
                        tagId = tagId.substring(0, tagId.length() - 1);
                        ct.setItemTag(tagId);
                    }
                } catch (Exception e) {
                    this.log.error("添加标签库失败", e);
                    e.printStackTrace();
                }
            }
        }
        // 更新班型信息
        this.classTypeServiceImpl.update(ct);

        // 更新总课时
        try {
            ClassModule cm = this.classModuleServiceImpl.queryModuleByClasstypeId(ct.getId());
            ClassModule module = new ClassModule();
            module.setId(cm.getId());
            module.setTotalClassHour(courseNum);
            this.classModuleServiceImpl.update(module);
        } catch (Exception e) {
            this.log.error("修改总课时失败", e);
            e.printStackTrace();
        }

        // 根据班型id查询商品信息
        CommodityProductRealtion commodityProductRealtion = new CommodityProductRealtion();
        commodityProductRealtion.setProductId(ct.getId());

        commodityProductRealtion = this.commodityProductRealtionServiceImpl.findByProduyctId(commodityProductRealtion);
        // 更新商品信息
        Commodity commodity = new Commodity();
        commodity.setId(commodityProductRealtion.getComId());
        commodity.setUpdator(WebUtils.getCurrentUserId(request));
        commodity.setUpdateTime(new Date());
        commodity.setItemOneCode(ct.getItemOneCode());
        commodity.setItemSecondCode(ct.getItemSecondCode());
        commodity.setItemThirdCode(ct.getItemThirdCode());
        commodity.setItemFourthCode(ct.getItemFourthCode());
        commodity.setBaseNum(ct.getBaseNum());
        commodity.setOriginalPrice(ct.getOriginalPrice());
        commodity.setRealPrice(ct.getRealPrice());
        commodity.setName(ct.getName());
        commodity.setFaceFlag(ct.getFaceFlag());
        commodity.setLiveFlag(ct.getLiveFlag());
        commodity.setVideoFlag(ct.getVideoFlag());
        commodity.setRecommendFlag(ct.getRecommendFlag());
        if (null != sets || null != sets1) {
            commodity.setItemTag(ct.getItemTag());
        }
        commodity.setIntegralFlag(ct.getIntegralFlag());
        commodity.setMemberFlag(ct.getMemberFlag());
        this.commodityServiceImpl.update(commodity);
        return "success";
    }

    /**
     *
     * Class Name: ClassTypeController.java
     *
     * @Description: 班型打包
     * @author zhang.zx
     * @date 2015年5月5日 下午9:23:50
     * @modifier
     * @modify-date 2015年5月5日 下午9:23:50
     * @version 1.0
     * @param model
     * @param classType
     * @return
     */
    @RequestMapping(value = "/addClassPackage", method = RequestMethod.POST)
    public String addClassPackage(Model model, HttpServletRequest request, ClassType classType, String mark, String lable, Integer moduleId) {
        // 保存并退出
        if ("saveandtui".equals(mark)) {
            return "redirect:showClassTypePage";
        }
        ClassType cst = this.classTypeServiceImpl.findClassTypeById(classType.getId());
        model.addAttribute("ct", cst);
        model.addAttribute("lable", lable);

        // 根据班型id查询商品信息id
        CommodityProductRealtion comm = this.commodityProductRealtionServiceImpl.findByClassTypeId(classType.getId() + "");
        Integer cId = comm.getComId();
        Map<String, String> teacherMap = new HashMap<String, String>();
        teacherMap.put("companyId", WebUtils.getCurrentCompanyId() + "");
        teacherMap.put("schoolId", WebUtils.getCurrentSchoolId() + "");

        model.addAttribute("cId", cId);
        model.addAttribute("itemOneid", classType.getItemOneId());
        model.addAttribute("classTypeId", cst.getId());
        model.addAttribute("itemSecondId", cst.getItemSecondId());
        Subject subject = SecurityUtils.getSubject();
        if((subject.hasRole("直播老师")&&subject.hasRole("排课老师"))&&!(subject.hasRole("机构管理员")||subject.hasRole("分校管理员")||subject.hasRole("运营")))
        {
        	Users user = WebUtils.getCurrentUser();
        	SysConfigTeacher teacher2 = new SysConfigTeacher();
	        	teacher2.setUserId(user.getId());
	        	teacher2.setCompanyId(WebUtils.getCurrentCompanyId());
	        	 List<SysConfigTeacher> list = this.sysConfigTeacherServiceImpl.findTeacherByUserId(teacher2);
	        	 if(list != null && list.size()>0){
	        		 teacherMap.put("id", list.get(0).getId()+"");
	        	 }
        }
        model.addAttribute("teachers", this.sysConfigTeacherServiceImpl.findTeachers(teacherMap));
        // 根据所属标签跳转不同页面
        if ("face".equals(lable) || "live".equals(lable)) {

            return "simpleClasses/addClassTypeFaceOrLiveMessage_2";
        } else if ("video".equals(lable)) {
            model.addAttribute("moduleId", moduleId);
            return "simpleClasses/addClassTypeVideoMessage_2";
        } else if ("remote".equals(lable)) {

            return "simpleClasses/addClassTypeOtherMessage_2";
        } else {
            return "simpleClasses/addClassTypeTogtherMessage_2";
        }
    }

    // 添加班型基本信息
    @ResponseBody
    @RequestMapping(value = "/addBaseInfo", method = RequestMethod.POST)
    public ClassType addBaseInfo(HttpServletRequest request, ClassType classType, String lable, Integer courseNum, String mark, String ltype) {
        // 给所属标签赋值
        if ("face".equals(lable) || "live".equals(lable)) {
            if ("face".equals(lable)) {
                classType.setFaceFlag(1);
                classType.setLiveFlag(0);
                classType.setVideoFlag(0);
                classType.setRemoteFlag(0);
            } else {
                classType.setFaceFlag(0);
                classType.setLiveFlag(1);
                classType.setVideoFlag(0);
                classType.setRemoteFlag(0);
            }
        } else if ("video".equals(lable)) {
            classType.setFaceFlag(0);
            classType.setLiveFlag(0);
            classType.setVideoFlag(1);
            classType.setRemoteFlag(0);
        } else if ("togther".equals(lable)) {
            String r = ltype.substring(0, ltype.length() - 1);
            String[] b = r.split(",");
            for (int i = 0; i < b.length; i++) {
                if ("face".equals(b[i])) {
                    classType.setFaceFlag(1);
                }
                if ("live".equals(b[i])) {
                    classType.setLiveFlag(1);
                }
                if ("video".equals(b[i])) {
                    classType.setVideoFlag(1);
                }
            }
            if (null == classType.getFaceFlag() || "".equals(classType.getFaceFlag())) {
                classType.setFaceFlag(0);
            }
            if (null == classType.getLiveFlag() || "".equals(classType.getLiveFlag())) {
                classType.setLiveFlag(0);
            }
            if (null == classType.getVideoFlag() || "".equals(classType.getVideoFlag())) {
                classType.setVideoFlag(0);
            }
            classType.setRemoteFlag(0);
        } else {
            classType.setFaceFlag(0);
            classType.setLiveFlag(0);
            classType.setVideoFlag(0);
            classType.setRemoteFlag(0);
        }
        ClassType classTypes = this.addClassTypeCommMethod(request, classType, lable, courseNum);
        return classTypes;
    }

    // 添加班型公共方法
    private ClassType addClassTypeCommMethod(HttpServletRequest request, ClassType classType, String lable, Integer courseNum) {
        Integer fla = 0;
        // 如果开启标签库则将标签存库
        CompanyFunctionSet conditon = new CompanyFunctionSet();
        conditon.setCompanyId(WebUtils.getCurrentCompanyId());
        conditon.setFunctionCode("COURSE_THIRD_CATEGORY");
        conditon.setStatus("1");
        CompanyFunctionSet sets = this.companyFunctionSetServiceImpl.findCompanyUseCourse(conditon);
        conditon.setFunctionCode("COURSE_SECOND_CATEGORY");
        CompanyFunctionSet sets1 = this.companyFunctionSetServiceImpl.findCompanyUseCourse(conditon);
        if (null != sets || null != sets1) {
            // 添加标签库
            if (null != classType.getTagName() && !"".equals(classType.getTagName())) {
                try {
                    String tagId = "";
                    String str[] = classType.getTagName().split(",");
                    for (int i = 0; i < str.length; i++) {
                        if (null != str[i] && !"null".equals(str[i])) {
                            SysConfigItemTag search = new SysConfigItemTag();
                            search.setCompanyId(WebUtils.getCurrentCompanyId());
                            search.setSchoolId(WebUtils.getCurrentSchoolId());
                            search.setTagName(str[i]);
                            if (i == 0) {
                                search.setLevel(1);
                            } else {
                                search.setLevel(2);
                            }
                            List<SysConfigItemTag> arr = this.sysConfigItemTagServiceImpl.queryTagsBycondition(search);
                            if (arr.size() > 0) {
                                tagId += arr.get(0).getId() + ",";
                            } else {
                                search.setItemOneId(classType.getItemOneId());
                                search.setItemSecondId(classType.getItemSecondId());
                                search.setCreateTime(new Date());
                                search.setUpdateTime(new Date());
                                search.setCreator(WebUtils.getCurrentUserId(request));
                                search.setUpdator(WebUtils.getCurrentUserId(request));
                                this.sysConfigItemTagServiceImpl.insert(search);
                                tagId += search.getId() + ",";
                            }
                        } else {
                            tagId += null + ",";
                        }
                    }
                    if (null != tagId && !"".equals(tagId)) {
                        tagId = tagId.substring(0, tagId.length() - 1);
                        classType.setItemTag(tagId);
                    }
                } catch (Exception e) {
                    this.log.error("添加标签库失败", e);
                    e.printStackTrace();
                }
            }
        }

        if (classType.getId() == null || "".equals(classType.getId())) {
            classType.setCreateTime(new Date());
            classType.setUpdateTime(new Date());
            classType.setCreator(WebUtils.getCurrentUserId(request));
            classType.setUpdator(WebUtils.getCurrentUserId(request));
            classType.setPublishStatus("CLASS_UNPUBLISHED");
            classType.setTypeCode("CLASS_TYPE_NOMAL");
            classType.setCompanyId(WebUtils.getCurrentCompanyId());
            classType.setDelFlag(0);
            classType.setCreateSchoolId(WebUtils.getCurrentSchoolId());
            if (classType.getFaceFlag() != null) {
                classType.setFaceFlag(classType.getFaceFlag());
            } else {
                classType.setFaceFlag(classType.getFaceFlag());
            }
            if (classType.getVideoFlag() != null) {
                classType.setVideoFlag(classType.getVideoFlag());
            } else {
                classType.setVideoFlag(classType.getVideoFlag());
            }
            if (classType.getLiveFlag() != null) {
                classType.setLiveFlag(classType.getLiveFlag());
            } else {
                classType.setLiveFlag(classType.getLiveFlag());
            }
            if (classType.getRemoteFlag() != null && classType.getRemoteFlag().equals(1)) {
                classType.setRemoteFlag(classType.getRemoteFlag());
                classType.setFaceFlag(classType.getFaceFlag());
                classType.setVideoFlag(classType.getVideoFlag());
                classType.setLiveFlag(classType.getLiveFlag());
                classType.setTypeCode("CLASS_TYPE_REMOTE");
            } else {
                classType.setRemoteFlag(classType.getRemoteFlag());
            }
            // if(null!=classType.getValidityDay()&&!"".equals(classType.getValidityDay())){
            // classType.setValidityDate(DateUtil.addDate(new
            // Date(),classType.getValidityDay()));
            // }
            this.classTypeServiceImpl.insert(classType);

            Commodity commodity = new Commodity();

            commodity.setCerateTime(new Date());
            commodity.setCompanyId(WebUtils.getCurrentCompanyId());
            commodity.setItemOneId(classType.getItemOneId());
            commodity.setItemSecondId(classType.getItemSecondId());
            commodity.setType("COMMODITY_CLASS");
            commodity.setUpdator(WebUtils.getCurrentUserId(request));
            commodity.setSchoolId(WebUtils.getCurrentSchoolId());
            commodity.setBaseNum(classType.getBaseNum());
            commodity.setStatus(0 + "");
            commodity.setRemoteFlag(classType.getRecommendFlag());
            commodity.setOriginalPrice(Double.parseDouble(classType.getOriginalPrice() + ""));
            commodity.setRealPrice(classType.getRealPrice());
            commodity.setName(classType.getName());
            commodity.setUpdateTime(new Date());
            commodity.setCreator(WebUtils.getCurrentUserId(request));
            commodity.setFaceFlag(classType.getFaceFlag());
            commodity.setLiveFlag(classType.getLiveFlag());
            commodity.setVideoFlag(classType.getVideoFlag());
            commodity.setRemoteFlag(classType.getRemoteFlag());
            commodity.setRecommendFlag(classType.getRecommendFlag());
            commodity.setBuyNum(0);
            if (null != sets || null != sets1) {
                commodity.setItemTag(classType.getItemTag());
                commodity.setIntegralFlag(classType.getIntegralFlag());
                commodity.setMemberFlag(classType.getMemberFlag());
            }
            this.commodityServiceImpl.insert(commodity);

            CommodityProductRealtion commodityProductRealtion = new CommodityProductRealtion();
            commodityProductRealtion.setProductId(classType.getId());
            commodityProductRealtion.setProductType(1 + "");
            commodityProductRealtion.setComId(commodity.getId());
            this.commodityProductRealtionServiceImpl.insert(commodityProductRealtion);
        }
        if ("video".equals(lable)) {
            ClassModule module = new ClassModule();
            module.setName(classType.getName());
            module.setTeachMethod("TEACH_METHOD_VIDEO");
            module.setCreateTime(new Date());
            module.setCreator(WebUtils.getCurrentUserId(request));
            module.setItemOneId(classType.getItemOneId());
            module.setItemSecondId(classType.getItemSecondId());
            module.setTotalClassHour(courseNum);
            module.setDelFlag(0);
            module.setSchoolId(WebUtils.getCurrentSchoolId());
            module.setCompanyId(WebUtils.getCurrentCompanyId());
            this.classModuleServiceImpl.insert(module);
            ClassTypeModuleRelation ctmr = new ClassTypeModuleRelation();
            ctmr.setModuleId(module.getId());
            ctmr.setClassTypeId(classType.getId());
            this.classTypeModuleRelationServiceImpl.insert(ctmr);
            fla = module.getId();
        }
        if (fla > 0) {
            // 模块id
            classType.setDelFlag(fla);
        }
        classType.setId(classType.getId());
        return classType;
    }

    /**
     *
     * Class Name: SimpleclassTypeController.java
     *
     * @Description: 添加直播或面授
     * @author zhang.zx
     * @date 2015年9月8日 下午9:28:58
     * @modifier
     * @modify-date 2015年9月8日 下午9:28:58
     * @version 1.0
     * @param request
     * @param ltype
     * @return
     */
    @RequestMapping(value = "/addliveOrface/{classtypeId}")
    public String addliveOrface(HttpServletRequest request, Model model, @PathVariable Integer classtypeId) {
        ClassType ct = this.classTypeServiceImpl.findClassTypeById(classtypeId);
        SysConfigItem item = this.sysConfigItemServiceImpl.findSysConfigItemById(ct.getItemOneId());
        SysConfigItem item2 = this.sysConfigItemServiceImpl.findSysConfigItemById(ct.getItemSecondId());
        if (item != null) {
            ct.setItemOneName(item.getItemName());
        }
        if (item2 != null) {
            ct.setItemSecondName(item2.getItemName());
        }
        if (null != ct) {
            if (ct.getLiveFlag() == 1 && ct.getFaceFlag() == 1) {
                model.addAttribute("ftype", "live,face");
            } else if (ct.getLiveFlag() == 1 && ct.getFaceFlag() == 0) {
                model.addAttribute("ftype", "live");
            } else if (ct.getLiveFlag() == 0 && ct.getFaceFlag() == 1) {
                model.addAttribute("ftype", "face");
            }
        }
        String type = request.getParameter("type");
        String ftype = request.getParameter("ftype");
        model.addAttribute("type", type);
        model.addAttribute("ct", ct);
        model.addAttribute("lable", "togther");
        String[] lab = ftype.split(",");
        model.addAttribute("lLength", lab.length);
        for (int i = 0; i < lab.length; i++) {
            if ("live".equals(lab[i])) {

                return "simpleClasses/editClass/addClassTypeTogtherMessage_4";
            } else if ("face".equals(lab[i])) {

                return "simpleClasses/editClass/addClassTypeTogtherMessage_4";
            }
        }
        return "simpleClasses/editClass/addClassTypeTogtherMessage_4";
    }

    @RequestMapping(value = "/addlive/{classtypeId}")
    public String addlive(HttpServletRequest request, Model model, @PathVariable Integer classtypeId) {
        ClassType ct = this.classTypeServiceImpl.findClassTypeById(classtypeId);
        SysConfigItem item = this.sysConfigItemServiceImpl.findSysConfigItemById(ct.getItemOneId());
        SysConfigItem item2 = this.sysConfigItemServiceImpl.findSysConfigItemById(ct.getItemSecondId());

        boolean flag = this.companyFunctionSetServiceImpl.isCurrentFuSheng(WebUtils.getCurrentCompanyId());
        Users currentUser = WebUtils.getCurrentUser();
        Subject subject = SecurityUtils.getSubject();
        boolean isJigou = subject.hasRole("机构管理员");
        boolean isFenxiao = subject.hasRole("分校管理员");
        model.addAttribute("isJigou", isJigou);
        model.addAttribute("isFenxiao", isFenxiao);
        if (subject.hasRole("排课老师")) {
            // search.setTeacherId();
            List<SysConfigTeacher> teacherList = this.sysConfigTeacherServiceImpl.findTeachersByUserId(currentUser.getId());
            if (teacherList != null && teacherList.size() > 0) {
                SysConfigTeacher configTeacher = teacherList.get(0);
                model.addAttribute("teacherId", configTeacher.getId());
            }
        }
        model.addAttribute("isFuSheng", flag);
        if (item != null) {
            ct.setItemOneName(item.getItemName());
        }
        if (item2 != null) {
            ct.setItemSecondName(item2.getItemName());
        }
        if (null != ct) {
            if (ct.getLiveFlag() == 1 && ct.getFaceFlag() == 1) {
                model.addAttribute("ftype", "live,face");
            } else if (ct.getLiveFlag() == 1 && ct.getFaceFlag() == 0) {
                model.addAttribute("ftype", "live");
            } else if (ct.getLiveFlag() == 0 && ct.getFaceFlag() == 1) {
                model.addAttribute("ftype", "face");
            }
        }
        String type = request.getParameter("type");
        String ftype = request.getParameter("ftype");
        model.addAttribute("type", type);
        model.addAttribute("ct", ct);
        model.addAttribute("lable", "togther");
        String[] lab = ftype.split(",");
        model.addAttribute("lLength", lab.length);
        for (int i = 0; i < lab.length; i++) {
            if ("live".equals(lab[i])) {

                return "simpleClasses/editClass/addClassTypeTogtherMessage_live";
            } else if ("face".equals(lab[i])) {

                return "simpleClasses/editClass/addClassTypeTogtherMessage_face";
            }
        }
        return "simpleClasses/editClass/addClassTypeTogtherMessage_live";
    }

    @RequestMapping(value = "/addface/{classtypeId}")
    public String addface(HttpServletRequest request, Model model, @PathVariable Integer classtypeId) {
        ClassType ct = this.classTypeServiceImpl.findClassTypeById(classtypeId);
        SysConfigItem item = this.sysConfigItemServiceImpl.findSysConfigItemById(ct.getItemOneId());
        SysConfigItem item2 = this.sysConfigItemServiceImpl.findSysConfigItemById(ct.getItemSecondId());
        boolean flag = this.companyFunctionSetServiceImpl.isCurrentFuSheng(WebUtils.getCurrentCompanyId());
        Users currentUser = WebUtils.getCurrentUser();
        Subject subject = SecurityUtils.getSubject();
        boolean isJigou = subject.hasRole("机构管理员");
        boolean isFenxiao = subject.hasRole("分校管理员");
        model.addAttribute("isJigou", isJigou);
        model.addAttribute("isFenxiao", isFenxiao);
        if (subject.hasRole("排课老师")) {
            // search.setTeacherId();
            List<SysConfigTeacher> teacherList = this.sysConfigTeacherServiceImpl.findTeachersByUserId(currentUser.getId());
            if (teacherList != null && teacherList.size() > 0) {
                SysConfigTeacher configTeacher = teacherList.get(0);
                model.addAttribute("teacherId", configTeacher.getId());
            }
        }
        model.addAttribute("isFuSheng", flag);
        if (item != null) {
            ct.setItemOneName(item.getItemName());
        }
        if (item2 != null) {
            ct.setItemSecondName(item2.getItemName());
        }
        if (null != ct) {
            if (ct.getLiveFlag() == 1 && ct.getFaceFlag() == 1) {
                model.addAttribute("ftype", "live,face");
            } else if (ct.getLiveFlag() == 1 && ct.getFaceFlag() == 0) {
                model.addAttribute("ftype", "live");
            } else if (ct.getLiveFlag() == 0 && ct.getFaceFlag() == 1) {
                model.addAttribute("ftype", "face");
            }
        }
        String type = request.getParameter("type");
        String ftype = request.getParameter("ftype");
        model.addAttribute("type", type);
        model.addAttribute("ct", ct);
        model.addAttribute("lable", "togther");
        String[] lab = ftype.split(",");
        model.addAttribute("lLength", lab.length);
        for (int i = 0; i < lab.length; i++) {
            if ("face".equals(lab[i])) {

                return "simpleClasses/editClass/addClassTypeTogtherMessage_face";
            } else if ("live".equals(lab[i])) {

                return "simpleClasses/editClass/addClassTypeTogtherMessage_live";
            }
        }
        return "simpleClasses/editClass/addClassTypeTogtherMessage_face";
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

    // 排课表相关信息查询
    @RequestMapping(value = "/{id}/showClassCourseDetail", method = RequestMethod.GET)
    public String showClassCourseDetail(@PathVariable Integer id, Model model) {
        ClassType ct = this.classTypeServiceImpl.findClassTypeById(id);
        SysConfigItem item = this.sysConfigItemServiceImpl.findSysConfigItemById(ct.getItemOneId());
        SysConfigItem item2 = this.sysConfigItemServiceImpl.findSysConfigItemById(ct.getItemSecondId());
        if (item != null) {
            ct.setItemOneName(item.getItemName());
        }
        if (item2 != null) {
            ct.setItemSecondName(item2.getItemName());
        }
        model.addAttribute("ct", ct);
        // 根据班型查询对应的模块
        List<ClassModule> modulesVoList = this.classModuleServiceImpl.findModulesByClassTypeId(id);
        // 根据模块查询对应的班号
        List<ClassModuleNoListVo> moduleNoListVo = null;
        for (ClassModule module : modulesVoList) {
            if (module.getTeachMethod() != "TEACH_METHOD_VIDEO" && !"".equals(module.getTeachMethod())) {
                moduleNoListVo = this.classModuleNoServiceImpl.findModuleNoListByClassType(module.getId());
                module.setClassModules(moduleNoListVo);
            }
        }

        model.addAttribute("modulesVoList", modulesVoList);

        List<ChapterAndLectureListVo> chapterList = new ArrayList<ChapterAndLectureListVo>();
        Map<String, String> map = new HashMap<String, String>();
        map.put("classId", "" + ct.getId());
        map.put("schoolId", "" + WebUtils.getCurrentSchoolId());
        ClassTypeVo cts = this.classTypeServiceImpl.findClassTypeDetail(map);
        // 视频的章信息
        List<CourseVideoChapter> arr = cts.getVideos();
        for (CourseVideoChapter cou : arr) {
            ChapterAndLectureListVo chapVo = new ChapterAndLectureListVo();
            chapVo.setId(cou.getId());
            chapVo.setChapterOrder(cou.getChapterOrder());
            chapVo.setChapterName(cou.getChapterName());
            chapterList.add(chapVo);
        }
        model.addAttribute("chapterList", chapterList);
        return "simpleClasses/ClassTypeCourseDetail";
    }

    // 添加单个直播或面授
    @RequestMapping(value = "/editliveOrface/{id}/{lable}")
    public String editliveOrface(@PathVariable Integer id, @PathVariable String lable, Model model) {
        ClassType ct = this.classTypeServiceImpl.findClassTypeById(id);
        SysConfigItem item = this.sysConfigItemServiceImpl.findSysConfigItemById(ct.getItemOneId());
        SysConfigItem item2 = this.sysConfigItemServiceImpl.findSysConfigItemById(ct.getItemSecondId());
        if (item != null) {
            ct.setItemOneName(item.getItemName());
        }
        if (item2 != null) {
            ct.setItemSecondName(item2.getItemName());
        }

        boolean flag = this.companyFunctionSetServiceImpl.isCurrentFuSheng(WebUtils.getCurrentCompanyId());
        Users currentUser = WebUtils.getCurrentUser();
        Subject subject = SecurityUtils.getSubject();
        boolean isJigou = subject.hasRole("机构管理员");
        boolean isFenxiao = subject.hasRole("分校管理员");
        boolean isYunYing = subject.hasRole("运营");
        model.addAttribute("isJigou", isJigou);
        model.addAttribute("isFenxiao", isFenxiao);
        model.addAttribute("isYunYing", isYunYing);
        if (subject.hasRole("排课老师")) {
            // search.setTeacherId();
            List<SysConfigTeacher> teacherList = this.sysConfigTeacherServiceImpl.findTeachersByUserId(currentUser.getId());
            if (teacherList != null && teacherList.size() > 0) {
                SysConfigTeacher configTeacher = teacherList.get(0);
                model.addAttribute("teacherId", configTeacher.getId());
            }
        }
        if (isJigou || isFenxiao || isYunYing) {
            model.addAttribute("guanliyuan", true);
        }
        model.addAttribute("isFuSheng", flag);
        model.addAttribute("ct", ct);
        model.addAttribute("lable", lable);
        if ("live".equals(lable)) {
            return "simpleClasses/editClass/addClassTypeLiveMessage_3";
        }
        return "simpleClasses/editClass/addClassTypeFaceMessage_3";
    }

    @ResponseBody
    @RequestMapping(value="/isCommonTeacher",method=RequestMethod.POST)
    public String isCommonTeacher(HttpServletRequest request){
    	Subject subject = SecurityUtils.getSubject();
    	if((subject.hasRole("排课老师")&&subject.hasRole("直播老师"))&&!(subject.hasRole("机构管理员")||subject.hasRole("分校管理员")||subject.hasRole("运营")))
    			return "1";
    	else if((subject.hasRole("排课老师")||subject.hasRole("直播老师"))&&!(subject.hasRole("机构管理员")||subject.hasRole("分校管理员")||subject.hasRole("运营")))
    		return "2";
    	else
    		return "3";
    }
}
