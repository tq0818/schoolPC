package com.yuxin.wx.controller.branchschool;


import com.yuxin.wx.api.system.ISysConfigItemService;
import com.yuxin.wx.api.system.ISysConfigTeacherLessonService;
import com.yuxin.wx.api.system.ISysConfigTeacherService;
import com.yuxin.wx.common.Constant;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.common.SysConfigConstant;
import com.yuxin.wx.model.system.SysConfigItem;
import com.yuxin.wx.model.system.SysConfigTeacher;
import com.yuxin.wx.model.system.SysConfigTeacherLesson;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.utils.PropertiesUtil;
import com.yuxin.wx.utils.WebUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 分校教师管理
 */
@Controller
@RequestMapping("/teacherManger")
public class TeacherManger {

    @Autowired
    private ISysConfigTeacherService sysConfigTeacherServiceImpl;
    @Autowired
    private ISysConfigItemService sysConfigItemServiceImpl;
    @Autowired
    private ISysConfigTeacherLessonService sysConfigTeacherLessonServiceImpl;
    @Autowired
    private PropertiesUtil properties;

    /**
     * @param request 请求
     * @param model   向页面中需要添加的数据
     * @return
     * @Description: 加载改项下的所有的教师信息
     * @author wzx
     * @date 2015-5-4 下午2:20:00
     * @version 1.0
     */
    @RequestMapping(value = "/getTeacherList", method = RequestMethod.POST)
    public String getTeacherList(HttpServletRequest request, Model model,
                                 SysConfigTeacher teacher) {

        // 根据项目ID查询所有的老师
        teacher.setPageSize(10);
        Integer itemOneId = teacher.getItemOneId();
        PageFinder<SysConfigTeacher> pageFinder = null;
        if (itemOneId != null) {
            if (itemOneId == 0) {
                Users user = WebUtils.getCurrentUser();
                teacher.setSchoolId(user.getSchoolId());
                teacher.setCompanyId(user.getCompanyId());
                teacher.setTeacherType(Constant.PERSON_TEACHER);
                pageFinder = sysConfigTeacherServiceImpl.findTeacherPage(teacher);
            } else if (itemOneId > 0) {
                pageFinder = sysConfigTeacherServiceImpl.findTeacherPage(teacher);
            }
        }

        model.addAttribute("pageFinder", pageFinder);
        return "berkeley/teacherList";
    }

    @RequestMapping(value="/getFirstItems")
    public String getFirstItems(Model model,Integer companyId){
        // 查询当前分校中有哪些一级学科
        List<SysConfigItem> firstItems = sysConfigItemServiceImpl.findSysConfigItemByPid(SysConfigConstant.ITEMTYPE_FIRST, null, companyId, WebUtils.getCurrentSchoolId());
        model.addAttribute("items", firstItems);
        return "berkeley/teacherManagement";
    }


    /**
     * 向分校添加教师
     *
     * @param model            向页面添加的数据
     * @param request          请求
     * @param sysConfigTeacher 教师bean
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addBerkeleyTeacher", method = RequestMethod.POST)
    public String add(Model model, HttpServletRequest request,
                      SysConfigTeacher sysConfigTeacher) {
        sysConfigTeacher.setPwd(new Md5Hash(sysConfigTeacher.getPwd(), ByteSource.Util.bytes(sysConfigTeacher.getUserName() + "salt")).toHex());
        Users user = WebUtils.getCurrentUser(request);
        sysConfigTeacher.setSchoolId(user.getSchoolId());
        sysConfigTeacher.setCreator(user.getId());
        sysConfigTeacher.setCreateTime(new Date());
        sysConfigTeacher.setUpdateTime(new Date());
        sysConfigTeacher.setUpdator(user.getId());
        sysConfigTeacher.setCompanyId(user.getCompanyId());
        sysConfigTeacher.setDelFlag(0);
        sysConfigTeacher.setTeacherType(Constant.PERSON_TEACHER);
        sysConfigTeacher.setStatusCode(Constant.TEACHER_USERD);
        sysConfigTeacherServiceImpl.isnertTeaAndUse(sysConfigTeacher);

        SysConfigTeacherLesson lesson = new SysConfigTeacherLesson();
        Integer teaId = sysConfigTeacher.getId();
        Integer itemOneId = sysConfigTeacher.getItemOneId();
        String strTwoId = sysConfigTeacher.getItemSecondId();
        if (strTwoId == null || strTwoId == "") {
            strTwoId = "0";
        }
        Integer itemTwoId = Integer.parseInt(strTwoId);
        lesson.setItemOneId(itemOneId);
        lesson.setItemSecondId(itemTwoId);
        lesson.setTeacherId(teaId);
        sysConfigTeacherLessonServiceImpl.insert(lesson);
        return "success";
    }

    /**
     * 修改教师排序
     *
     * @param teacher 教师bean
     * @param type
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateSortByTeacherId")
    public String updateSortByTeacherId(SysConfigTeacher teacher, String type) {
        int count;
        if (teacher.getSortId() == 0) {
            teacher.setSortId(null);
        }
        if (type.equals("new")) {
            count = checkSortCount();
            if (count >= 8) {
                return "排序设置已超过8个，请修改后再设置";
            }
        }
        int index = sysConfigTeacherServiceImpl.updateSortId(teacher);
        if (index == 1) {
            return "保存成功";
        } else {
            return "保存失败";
        }

    }

    public int checkSortCount() {
        return sysConfigTeacherServiceImpl.checkSortCount();
    }

    /**
     * 修改、添加分校教师信息
     *
     * @param request
     * @param model
     * @param teacherId
     * @return
     */
    @RequestMapping(value = "/updateOrAddTeacher/{teacherId}", method = RequestMethod.GET)
    public String toTeacherUpdate(HttpServletRequest request, Model model,
                                  @PathVariable Integer teacherId) {
        Users user = WebUtils.getCurrentUser(request);
        // 根据老师ID查询对应的老师
        SysConfigTeacher teacher = sysConfigTeacherServiceImpl.findTeacherAndUserById(teacherId);

        // 根据老师ID查询该老师所属的公司所有的一级二级项目
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("companyId", user.getCompanyId());
        param.put("schoolId", user.getSchoolId());
        //根据公司id 和学校id 查询 一级项目
        List<SysConfigItem> items = sysConfigItemServiceImpl.findItemBySchoolCompanyId(param);

        List<SysConfigItem> firstItems = new ArrayList<SysConfigItem>();
        List<SysConfigItem> secondItems = new ArrayList<SysConfigItem>();

        if (items != null && items.size() > 0) {
            for (SysConfigItem item : items) {
                if (item.getItemType().equals("1")) {
                    firstItems.add(item);
                } else if (item.getItemType().equals("2")) {
                    secondItems.add(item);
                }
            }
        }

        // 循环二级项目放入map中
        //Map<Integer,List<SysConfigItem>> secondItemMap = new TreeMap<Integer, List<SysConfigItem>>();
        //指定排序器
        TreeMap<Integer, List<SysConfigItem>> secondItemMap = new TreeMap<Integer, List<SysConfigItem>>(new Comparator<Integer>() {
            @Override
            public int compare(Integer arg0, Integer arg1) {
                return arg0.compareTo(arg1);
            }
        });

        List<SysConfigItem> dateList = null;
        for (SysConfigItem item : secondItems) {
            Integer keyMap = item.getParentId();
            if (secondItemMap.containsKey(keyMap)) {
                dateList = secondItemMap.get(keyMap);
                dateList.add(item);
            } else {
                dateList = new ArrayList<SysConfigItem>();
                dateList.add(item);
                secondItemMap.put(keyMap, dateList);
            }
        }
        model.addAttribute("firstItems", firstItems);
        model.addAttribute("secondItemMap", secondItemMap);
        model.addAttribute("imgUrl", "http://" + properties.getProjectImageUrl() + "/");
        if (teacher == null) {
            teacher = new SysConfigTeacher();
            teacher.setId(0);
            model.addAttribute("teacher", teacher);
            return "berkeley/addTeacher";
        } else {
            SysConfigTeacherLesson les = sysConfigTeacherLessonServiceImpl.findSysConfigTeacherLessonByTeaId(teacherId);
            if (les != null && les.getItemOneId() != null && les.getItemOneId().toString().length() > 0) {
                teacher.setItemOneId(les.getItemOneId());
            }
            if (les != null && les.getItemSecondId() != null && les.getItemSecondId().toString().length() > 0) {
                teacher.setItemSecondId(les.getItemSecondId().toString());
            }
        }
        model.addAttribute("teacher", teacher);
        return "berkeley/updatetTeacher";
    }

    /**
     * 根据教师ID删除分校教师
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
    public String del(Model model, @PathVariable Integer id) {
        sysConfigTeacherServiceImpl.deleteSysConfigTeacherById(id);
        return "redirect:/sysConfigTeacher";
    }

    /**
     * 保存教师信息
     * @param model
     * @param request
     * @param sysConfigTeacher
     * @param moduleIds
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(HttpServletRequest request,
                      SysConfigTeacher sysConfigTeacher, String moduleIds) {
        sysConfigTeacher.setPwd(new Md5Hash(sysConfigTeacher.getPwd(),ByteSource.Util.bytes(sysConfigTeacher.getUserName()+"salt")).toHex());
        Users user = WebUtils.getCurrentUser(request);
        sysConfigTeacher.setSchoolId(user.getSchoolId());
        sysConfigTeacher.setCreator(user.getId());
        sysConfigTeacher.setCreateTime(new Date());
        sysConfigTeacher.setUpdateTime(new Date());
        sysConfigTeacher.setUpdator(user.getId());
        sysConfigTeacher.setCompanyId(user.getCompanyId());
        sysConfigTeacher.setDelFlag(0);
        sysConfigTeacher.setTeacherType(Constant.PERSON_TEACHER);
        sysConfigTeacher.setStatusCode(Constant.TEACHER_USERD);
        sysConfigTeacherServiceImpl.isnertTeaAndUse(sysConfigTeacher);

        SysConfigTeacherLesson lesson = new SysConfigTeacherLesson();
        Integer teaId = sysConfigTeacher.getId();
        Integer itemOneId = sysConfigTeacher.getItemOneId();
        String strTwoId = sysConfigTeacher.getItemSecondId();
        if(strTwoId == null || strTwoId == ""){
            strTwoId = "0";
        }
        Integer itemTwoId = Integer.parseInt(strTwoId);
        lesson.setItemOneId(itemOneId);
        lesson.setItemSecondId(itemTwoId);
        lesson.setTeacherId(teaId);
        sysConfigTeacherLessonServiceImpl.insert(lesson);
        return "success";
    }


    /**
     * 保存修改教师信息
     * @param request
     * @param model
     * @param sysConfigTeacher
     * @param moduleIds
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(HttpServletRequest request, Model model,
                         SysConfigTeacher sysConfigTeacher, String moduleIds, Integer companyId) {

        String pwd = sysConfigTeacher.getPwd();
        if(pwd != null && pwd != ""){
            sysConfigTeacher.setPwd(new Md5Hash(pwd,ByteSource.Util.bytes(sysConfigTeacher.getUserName()+"salt")).toHex());
        }
        sysConfigTeacher.setUpdateTime(new Date());
        sysConfigTeacher.setUpdator(WebUtils.getCurrentUserId(request));
        //默认为老师
        if(sysConfigTeacher.getTeaOrAdu() == null || "".equals(sysConfigTeacher.getTeaOrAdu())){
            sysConfigTeacher.setTeaOrAdu("tea");
        }
        sysConfigTeacher.setCreator(WebUtils.getCurrentUser(request).getId());
        sysConfigTeacher.setSchoolId(WebUtils.getCurrentSchoolId());
        sysConfigTeacher.setCompanyId(companyId);
        sysConfigTeacherServiceImpl.updateTeaAndUse(sysConfigTeacher);
        return "success";
    }

}
