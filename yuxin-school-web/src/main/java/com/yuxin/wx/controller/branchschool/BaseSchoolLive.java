package com.yuxin.wx.controller.branchschool;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yuxin.wx.api.classes.IClassTypeService;
import com.yuxin.wx.api.company.ICompanyFunctionSetService;
import com.yuxin.wx.api.company.ICompanyPayConfigService;
import com.yuxin.wx.api.query.IStudentStatisticsService;
import com.yuxin.wx.api.query.ISysPlayLogsService;
import com.yuxin.wx.api.student.IStudentService;
import com.yuxin.wx.api.system.ISysConfigDictService;
import com.yuxin.wx.api.system.ISysConfigItemRelationService;
import com.yuxin.wx.api.system.ISysConfigItemService;
import com.yuxin.wx.api.user.IUserHistoryService;
import com.yuxin.wx.api.user.IUsersService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.common.PageFinder2;
import com.yuxin.wx.common.ViewFiles;
import com.yuxin.wx.model.company.CompanyFunctionSet;
import com.yuxin.wx.model.system.SysConfigDict;
import com.yuxin.wx.model.system.SysConfigItem;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.model.watchInfo.WatchInfoResult;
import com.yuxin.wx.utils.DateUtil;
import com.yuxin.wx.utils.EntityUtil;
import com.yuxin.wx.utils.ExcelUtil;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.course.UserVideoVo;
import com.yuxin.wx.vo.user.UsersAreaRelation;

@Controller
@RequestMapping("/baseSchool")
public class BaseSchoolLive {

    @Autowired
	private IStudentStatisticsService studentStatisticsServiceImpl;
    @Autowired
    private ISysConfigDictService sysConfigDictServiceImpl;
    @Autowired
    private ICompanyFunctionSetService companyFunctionSetServiceImpl;
    @Autowired
    private IUsersService usersServiceImpl;
    @Autowired
    private IStudentService studentServiceImpl;
    @Autowired
    private ISysConfigItemService sysConfigItemService;
    @Autowired
    private ISysConfigItemRelationService sysConfigItemRelationServiceImpl;

    @Autowired
    private ISysPlayLogsService sysPlayLogsServiceImpl;
    @Autowired
    private ICompanyPayConfigService companyPayConfigServiceImpl;
    @Autowired
    private IClassTypeService classTypeServiceImpl;
    @Autowired
    private IUserHistoryService userHistoryServiceImpl;


    @RequestMapping(value="/baseSchoolLive")
    public String studentWatchInfoList(Model model, HttpServletRequest request) throws Exception {
        // 查询课程的多课程单元和多班号功能
        CompanyFunctionSet search = new CompanyFunctionSet();
        search.setFunctionCode("COMPANY_FUNCTION_COURSE");
        search.setCompanyId(WebUtils.getCurrentCompanyId());
        //查询学校所在区域
        SysConfigDict areaDict = new SysConfigDict();
        areaDict.setDictCode("EDU_SCHOOL_AREA");

        List<SysConfigDict> areas = sysConfigDictServiceImpl.queryConfigDictListByDictCode(areaDict);
        model.addAttribute("areas", areas);
        //查询学段
        SysConfigDict stepDict = new SysConfigDict();
        stepDict.setDictCode("EDU_STEP");

        List<SysConfigDict> steps = sysConfigDictServiceImpl.queryConfigDictListByDictCode(stepDict);
        model.addAttribute("steps", steps);
        // 学员分组
        CompanyFunctionSet companyFunctionSet = new CompanyFunctionSet();
        companyFunctionSet.setCompanyId(WebUtils.getCurrentCompanyId());
        companyFunctionSet.setFunctionCode("STUDENT_GROUP");
        List<CompanyFunctionSet> companyFunctionSetList = companyFunctionSetServiceImpl.findCompanyFunctionSetByPage(companyFunctionSet);
        if (companyFunctionSetList != null && companyFunctionSetList.size() > 0) {
            model.addAttribute("sgOpen", companyFunctionSetList.get(0).getStatus());
        }

        // 查看该机构学员地址信息配置功能
        search.setFunctionCode("STUDENT_ADDRESS_INFO");
        CompanyFunctionSet address = companyFunctionSetServiceImpl.findCompanyUseCourse(search);
        if (address != null && "1".equals(address.getStatus())) {
            model.addAttribute("address", 1);
        } else {
            model.addAttribute("address", 0);
        }

        CompanyFunctionSet userorg_roleopenflag = WebUtils.getFunctionSet("USERORG_ROLEOPENFLAG");
        model.addAttribute("userorg_roleopenflag", userorg_roleopenflag==null?0:userorg_roleopenflag.getStatus());
        SysConfigItem item = new SysConfigItem();
        item.setParentCode("SUBJECT");
        item.setCompanyId(WebUtils.getCurrentCompanyId());
        item.setSchoolId(WebUtils.getCurrentSchoolId());
        List<SysConfigItem> items = sysConfigItemService.findByParentCode(item);
        model.addAttribute("subject", items);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        model.addAttribute("endTime",sdf.format(c.getTime()));
        c.add(Calendar.MONTH,-1);
        model.addAttribute("startTime",sdf.format(c.getTime()));


        Users user = WebUtils.getCurrentUser();
        UsersAreaRelation uersAreaRelation = usersServiceImpl.findUsersAreaRelation(user.getId());
        if(uersAreaRelation==null){
            model.addAttribute("role","all");
        }else{

            Subject subject = SecurityUtils.getSubject();
            if(subject.hasRole("学校负责人")) {
                model.addAttribute("role","school");
                model.addAttribute("area",uersAreaRelation.getEduArea());
                model.addAttribute("eduSchool",uersAreaRelation.getEduSchool());
            }else if(subject.hasRole("教科院") || subject.hasRole("文轩教育")){
                model.addAttribute("role","all");
            }else if(subject.hasRole("区县负责人")){
                model.addAttribute("role","area");
                model.addAttribute("area",uersAreaRelation.getEduArea());
                SysConfigDict dic  = new SysConfigDict();
                dic.setItemCode(uersAreaRelation.getEduArea());
                model.addAttribute("areaId", sysConfigDictServiceImpl.findSysConfigDictByCode(dic).getId());
                model.addAttribute("schoolType", sysConfigDictServiceImpl.findByDicCode("EDU_STEP_NEW"));
            }
        }

        return "/berkeley/baseSchoolLive";
    }

   /**
    * 
    * @author jishangyang 2017年12月13日 上午12:45:23
    * @Method: queryStudentsWatchInfoList 
    * @Description: 直播
    * @param search
    * @return 
    * @throws
    */
    @SuppressWarnings("rawtypes")
    @ResponseBody
    @RequestMapping(value = "/queryStudentsWatchInfoList")
    public Map queryStudentsWatchInfoList(WatchInfoResult search) {
        search.setPageSize(10);
        search.setIsFromBrachSchool("1");
        Integer total = studentStatisticsServiceImpl.totalNewPayMasterCount(search);
        PageFinder2<WatchInfoResult> pageFinder = studentStatisticsServiceImpl.queryNewStudentsWatchInfoList(search);
        Map<String,Object> map = new HashMap<>();
        map.put("total",total);
        map.put("pageFinder",pageFinder);
        return map;
    }
    /**
     * 
     * @author jishangyang 2017年12月12日 下午11:57:09
     * @Method: queryStudentsWatchInfoList 
     * @Description: 录播
     * @param search
     * @return 
     * @throws
     */
    @ResponseBody
    @RequestMapping(value = "/recordWatch")
    public PageFinder<UserVideoVo> recordWatch(UserVideoVo userVideoVo) {
    	  userVideoVo.setCompanyId(WebUtils.getCurrentCompanyId());
    	  userVideoVo.setIsFromBrachSchool("1");
          // 分页调整
          if (userVideoVo.getPageSize() == null) {
              userVideoVo.setPageSize(10);
          }
          PageFinder<UserVideoVo> pageFinder = sysPlayLogsServiceImpl.queryNewUserVideoPage(userVideoVo);
          return pageFinder;
    }
    /**
     * 
     * @author jishangyang 2017年12月13日 上午12:46:09
     * @Method: videoCourseDetail 
     * @Description: 录播
     * @param model
     * @param request
     * @return
     * @throws Exception 
     * @throws
     */
    @RequestMapping(value="/baseSchoolRecording")
    public String videoCourseDetail(Model model, HttpServletRequest request) throws Exception {
    	//查询学校所在区域
        SysConfigDict areaDict = new SysConfigDict();
        areaDict.setDictCode("EDU_SCHOOL_AREA");
        List<SysConfigDict> areas = sysConfigDictServiceImpl.queryConfigDictListByDictCode(areaDict);
        model.addAttribute("areas", areas);
        Users user = WebUtils.getCurrentUser();
        UsersAreaRelation uersAreaRelation = usersServiceImpl.findUsersAreaRelation(user.getId());


        //查询学校所属学段
        SysConfigDict stepDict = new SysConfigDict();
        stepDict.setDictCode("EDU_STEP");
        List<SysConfigDict> stepNews = sysConfigDictServiceImpl.queryConfigDictListByDictCode(stepDict);
        model.addAttribute("stepNews", stepNews);

        //查询学段
        SysConfigDict pStepDict = new SysConfigDict();
        pStepDict.setDictCode("EDU_STEP");
        List<SysConfigDict> steps = sysConfigDictServiceImpl.queryConfigDictListByDictCode(pStepDict);
        model.addAttribute("steps",steps);
        
        //年份列表
        List<Integer> years = new ArrayList<Integer>();
        int curYear = DateUtil.getCurYear();
        for(int year = 0;year<12;year++){
            years.add(curYear-year);
        }
        model.addAttribute( "years", years);

        //计算时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        model.addAttribute("endTime" ,sdf.format(cal.getTime()));
        cal.add(Calendar.DAY_OF_MONTH, -6);
        model.addAttribute("startTime" ,sdf.format(cal.getTime()));

        return "/berkeley/baseSchoolRecording";
    }

/**
 * 
 * @author jishangyang 2017年12月13日 上午1:53:55
 * @Method: exportUserWatchExcle 
 * @Description: 直播导出
 * @param model
 * @param search
 * @return 
 * @throws
 */
    @SuppressWarnings({"unchecked", "rawtypes"})
    @RequestMapping(value = "/exportUserWatchExcle")
    public ModelAndView exportUserWatchExcle(Model model, WatchInfoResult search) {
        List<WatchInfoResult> list = new ArrayList<>();
        if (EntityUtil.isNotBlank(search)) {
            search.setPageSize(20000);
            list = studentStatisticsServiceImpl.exportNewStudentsWatchInfoList(search);//studentStatisticsServiceImpl.queryStudentsWatchInfoList(search);
        }
        List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
        for (WatchInfoResult v : list) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("className", v.getClassName());
            map.put("lessonName", v.getLessonName());
            map.put("userName", v.getUserName());
            map.put("studentName", v.getStudentName());
            map.put("stepName", v.getEduStep());//学段
            map.put("eduClass", v.getStudyClass());//班级sss
            map.put("areaName", v.getEduArea());//区域
            map.put("schoolName", v.getEduSchool());//学校
            map.put("schoolType", v.getSchoolType());
            map.put("stepName", v.getEduStep());//学段
            map.put("eduYear", v.getEduYear());//入学年份
            map.put("schoolName", v.getEduSchool());//学校
            map.put("schoolType", v.getSchoolType());
            map.put("stepName", v.getEduStep());//学段
            map.put("eduYear", v.getEduYear());//入学年份
            map.put("times", v.getTimes());//观看次数
            map.put("studyTime", v.getStudyTime());//观看时长
            lists.add(map);
        }
        String titles = "课程名称:className,课次名称:lessonName,用户名:userName,学员名称:studentName,区域:areaName,学校:schoolName,学校性质:schoolType,学段:stepName,入学年份:eduYear,班级:eduClass,观看累计次数:times,观看累计时长:studyTime";
        

        StringBuffer title = new StringBuffer(titles);
        ViewFiles excel = new ViewFiles();
        HSSFWorkbook wb = new HSSFWorkbook();
        try {
            wb = ExcelUtil.newWorkbook(lists, "sheet1", title.toString());
        } catch (Exception ex) {

        }
        Map map = new HashMap();
        map.put("workbook", wb);
        map.put("fileName", "用户直播统计.xls");
        return new ModelAndView(excel, map);
    }
/**
 * 
 * @author jishangyang 2017年12月13日 上午1:53:42
 * @Method: RecordingWatchExcle 
 * @Description: 录播导出
 * @param model
 * @param userVideoVo
 * @return 
 * @throws
 */
    @SuppressWarnings({"unchecked", "rawtypes"})
    @RequestMapping(value = "/RecordingWatchExcle")
    public ModelAndView RecordingWatchExcle(Model model, UserVideoVo userVideoVo) {
    	List<UserVideoVo> al = new ArrayList<UserVideoVo>();
        if (EntityUtil.isNotBlank(userVideoVo)) {
            userVideoVo.setCompanyId(WebUtils.getCurrentCompanyId());
            userVideoVo.setPageSize(20000);
            al = sysPlayLogsServiceImpl.queryNewUserVideoList(userVideoVo);
        }
        List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
        for (UserVideoVo v : al) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("time", userVideoVo.getStartTime() + "至" + userVideoVo.getEndTime());
            map.put("areaName", v.getAreaName());
            map.put("schoolName", v.getSchoolName());
            map.put("stepName", v.getCourseStepName());
            map.put("subjectName", v.getSubjectName());
            map.put("courseName", v.getCourseName());
            map.put("username", v.getUsername());
            map.put("name", v.getName());
            map.put("yearName", v.getYearName()!=null ? v.getYearName()+"级":"");
            map.put("className", v.getClassName()!=null ? v.getClassName()+"班":"");
            map.put("totleStudyLength", v.getTotleStudyLength());
            map.put("studyRate", v.getStudyRate()!=null ? v.getStudyRate()+"%":"");
            map.put("viewNum", v.getViewNum());
            lists.add(map);
        }
        StringBuffer title = new StringBuffer(
                "日期:time,区域:areaName,学校:schoolName,课程学段:stepName,学科:subjectName,课程名:courseName,用户名:username,学员名:name,入学年份:yearName,班级:className,总播放时长:totleStudyLength,播完率:studyRate,观看次数:viewNum");
        ViewFiles excel = new ViewFiles();
        HSSFWorkbook wb = new HSSFWorkbook();
        try {
            wb = ExcelUtil.newWorkbook(lists, "sheet1", title.toString());
        } catch (Exception ex) {

        }
        Map map = new HashMap();
        map.put("workbook", wb);
        map.put("fileName", "用户点播统计.xls");
        return new ModelAndView(excel, map);
    }
    
    
   

    
}
