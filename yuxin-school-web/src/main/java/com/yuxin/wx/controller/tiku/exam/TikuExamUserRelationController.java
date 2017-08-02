package com.yuxin.wx.controller.tiku.exam;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.yuxin.wx.api.system.ISysConfigDictService;
import com.yuxin.wx.api.tiku.*;
import com.yuxin.wx.model.system.SysConfigDict;
import com.yuxin.wx.model.tiku.*;
import org.apache.commons.lang.StringUtils;
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
import org.springframework.web.servlet.ModelAndView;

import com.yuxin.wx.api.student.IStudentPayMasterService;
import com.yuxin.wx.api.system.ILongitudinalTableDataService;
import com.yuxin.wx.api.tiku.exam.ITikuExamUserRelationService;
import com.yuxin.wx.common.ExcelSheetEntity;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.common.ViewFiles;
import com.yuxin.wx.model.student.StudentPayMaster;
import com.yuxin.wx.model.system.LongitudinalTableData;
import com.yuxin.wx.model.tiku.exam.TikuExamUserRelation;
import com.yuxin.wx.utils.DateUtil;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.tiku.TikuUserExerciseVo;
import com.yuxin.wx.vo.tiku.exam.TikuExamUserRelationVo;

/**
 * Controller of TikuExamUserRelation
 *
 * @author wang.zx
 * @date 2016-2-17
 */
@Controller
@RequestMapping("/tikuExamUserRelation")
public class TikuExamUserRelationController {

    @Autowired
    private ITikuExamUserRelationService tikuExamUserRelationServiceImpl;

    @Autowired
    private ILongitudinalTableDataService dataServiceImpl;

    @Autowired
    private IStudentPayMasterService payMasterServiceImpl;

    @Autowired
    private ITikuUserExerciseService tikuUserExerciseServiceImpl;
    @Autowired
    private ISysConfigDictService sysConfigDictServiceImpl;
    @Autowired
    private ITikuTopicService tikuTopicServiceImpl;
    @Autowired
    private ITikuPaperService tikuPaperServiceImpl;
    @Autowired
    private ITikuTopicOptionService tikuTopicOptionServiceImpl;
    @Autowired
    private ITikuUserExerciseAnswerService tikuUserExerciseAnswerServiceImpl;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model, TikuExamUserRelation search) {
        if (search == null) {
            search = new TikuExamUserRelation();
            // search.setPageSize(20);
        }
        model.addAttribute("list", this.tikuExamUserRelationServiceImpl.findTikuExamUserRelationByPage(search));
        return "tikuExamUserRelation/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(TikuExamUserRelation TikuExamUserRelation) {
        this.tikuExamUserRelationServiceImpl.insert(TikuExamUserRelation);
        return "redirect:/tikuExamUserRelation";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(TikuExamUserRelation TikuExamUserRelation) {
        this.tikuExamUserRelationServiceImpl.update(TikuExamUserRelation);
        return "redirect:/tikuExamUserRelation";
    }

    @RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
    public String del(Model model, @PathVariable Integer id) {
        this.tikuExamUserRelationServiceImpl.deleteTikuExamUserRelationById(id);
        return "redirect:/tikuExamUserRelation";
    }

    @ResponseBody
    @RequestMapping(value = "/{id:\\d+}", method = RequestMethod.GET)
    public TikuExamUserRelation getJson(Model model, @PathVariable Integer id) {
        return this.tikuExamUserRelationServiceImpl.findTikuExamUserRelationById(id);
    }

    /**
     *
     * Class Name: TikuExamUserRelationController.java
     *
     * @Description: 统计详情列表
     * @author yuchanglong
     * @date 2016年2月18日 下午3:53:49
     * @version 1.0
     * @param relationVo
     * @param model
     * @return
     */
    @RequestMapping(value = "/loadDetailAjaxInfo")
    public String loadDetailAjaxInfo(TikuExamUserRelationVo relationVo, Model model) {
        relationVo.setPageSize(6);
        Date endTime = relationVo.getEndTime();
        if (endTime != null) {
            endTime = DateUtil.addDate(endTime, 1);
            relationVo.setEndTime(endTime);
        }
        PageFinder<TikuExamUserRelationVo> pageFinder = this.tikuExamUserRelationServiceImpl.findByPage(relationVo);
        model.addAttribute("pageFinder", pageFinder);
        return "tiku/exam/statisticsDetailAjax";
    }

    /**
     *
     * Class Name: TikuExamUserRelationController.java
     *
     * @Description: 查询试卷做题详细情况
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getPaperRspdInfo")
    public Map<String, Object> getPaperRspdInfo(TikuUserExercise exercise, Model model) {
        Map<String, Object> rlt = new HashMap<String, Object>();
        if (exercise.getPageSize() == null) {
            exercise.setPageSize(6);
        }
        Date endTime = exercise.getEndTime();
        if (endTime != null) {
            endTime = DateUtil.addDate(endTime, 1);
            exercise.setEndTime(endTime);
        }
        rlt.put("data", this.tikuUserExerciseServiceImpl.findPaperRspdInfo(exercise));
        return rlt;
    }

    /**
     *
     * Class Name: TikuPaperController.java
     *
     * @date 2015年7月9日 下午4:02:58
     * @version 1.0
     * @return
     */
    @RequestMapping(value = "paperStatisticsIndex")
    public String paperStatisticsIndex(TikuPaper paper, Model model) {
        WebUtils.getCurrentCompanyId();
        // model.addAttribute("statistics",
        // this.tikuExamUserRelationServiceImpl.statisticRspdByPaper(paper));
        model.addAttribute("statistics", this.tikuUserExerciseServiceImpl.statisticRspdByPaper(paper));
        model.addAttribute("paperId", paper.getId());
        model.addAttribute("tikuId", paper.getTikuCategoryId());

        //查询学校所在区域
        SysConfigDict areaDict = new SysConfigDict();
        areaDict.setDictCode("EDU_SCHOOL_AREA");
        List<SysConfigDict> areas = sysConfigDictServiceImpl.queryConfigDictListByDictCode(areaDict);
        model.addAttribute("areas", areas);

        // 根据试卷id查询试卷信息
        paper = this.tikuPaperServiceImpl.findTikuPaperById(paper.getId());
        String topicTypes = paper.getContainTopicType();
        Map<String,List<TikuTopic>> topicMap = new LinkedHashMap<String,List<TikuTopic>>();
        if(StringUtils.isNotBlank(topicTypes)){
            String types[] = topicTypes.split(",");
            for(String type : types){
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("paperId", paper.getId());
                param.put("topicType", type);
                List<TikuTopic> topics = this.tikuTopicServiceImpl.findTopicByPaperId(param);
                if(StringUtils.equals(type, "TOPIC_TYPE_RADIO") || StringUtils.equals(type, "TOPIC_TYPE_MULTIPLE") || StringUtils.equals(type, "TOPIC_TYPE_UNDEFINED")){
                    List<Integer> idList = new ArrayList<Integer>();
                    for(TikuTopic topic : topics){
                        idList.add(topic.getId());
                    }
                    if(idList.size() > 0){
                        List<TikuTopicOption> optionList = tikuTopicOptionServiceImpl.findOptionByListTopicId(idList);
                        for(TikuTopic topic : topics){
                            for(int i = 0; i < optionList.size(); i++){
                                TikuTopicOption option = optionList.get(i);
                                if(option.getTopicId() == topic.getId()){
                                    topic.getOptionList().add(option);
                                    optionList.remove(i);
                                    i--;
                                }
                            }
                        }
                    }

                }
//                tikuTopicOptionServiceImpl.findOptionByTopicId(1);
                topicMap.put(type, topics);
            }
        }
        model.addAttribute("topicMap", topicMap);

        return "tiku/paper/paperStatisticsIndex";
    }

    /**
     *
     * Class Name: TikuExamUserRelationController.java
     *
     * @Description: 导出数据
     * @author yuchanglong
     * @date 2016年3月17日 下午5:15:56
     * @version 1.0
     * @param model
     * @param search
     * @return
     */
    @RequestMapping(value = "/exportExcle")
    public ModelAndView exportStudentsExcle(HttpServletRequest request, Model model, TikuExamUserRelationVo relationVo) {
        List<TikuExamUserRelationVo> al = new ArrayList<TikuExamUserRelationVo>();
        relationVo.setPageSize(1000);
        Integer companyId = WebUtils.getCurrentCompanyId();
        Integer schoolId = WebUtils.getCurrentSchoolId();
        Date endTime = relationVo.getEndTime();
        if (endTime != null) {
            endTime = DateUtil.addDate(endTime, 1);
            relationVo.setEndTime(endTime);
        }
        PageFinder<TikuExamUserRelationVo> pageFinder = this.tikuExamUserRelationServiceImpl.findByPage(relationVo);
        al = pageFinder.getData();
        Integer status = 0;
        String createTimeStr = "";
        String sex = "";
        Integer stuId = 0;
        String title = "考试时间:createTime,学员名称:userName,性别:sex,手机号:userMobile,证件号码:identityId,所报课程:className,";
        if (companyId.equals(1851)) {	
        	title += "省:province,市:city,县:county,";
        }
        title += "地址:addressDetail,当前试卷分数:score,考试情况:statusStr,试卷名称:tikuPaperName";
        if (companyId.equals(1851)) {
            title += ",单位名称:companyName";
        }
        for (TikuExamUserRelationVo tv : al) {
            status = tv.getStatus();
            createTimeStr = DateUtil.format(tv.getCreateTime());
            tv.setCreateTimeStr(createTimeStr);
            if (status.equals(1)) {
                tv.setStatusStr("通过");
            }
            if (!status.equals(1)) {
                tv.setStatusStr("未通过");
            }
            sex = tv.getSex();
            if (sex != null) {
                if (sex.equals("MALE")) {
                    tv.setSex("男");
                } else {
                    tv.setSex("女");
                }
            }
            stuId = tv.getStuId();
            if (stuId != null) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("stuId", stuId);
                map.put("commodityType", "COMMODITY_CLASS");
                List<StudentPayMaster> stuMaster = this.payMasterServiceImpl.findpayIdByStudentsId(map);
                String className = "";
                if (stuMaster != null) {
                    for (StudentPayMaster sm : stuMaster) {
                        if (className.equals("")) {
                            className += sm.getClassTypeName();
                        } else {
                            className += "," + sm.getClassTypeName();
                        }

                    }
                    tv.setClassName(className);
                }
                if (companyId.equals(1851)) {
                    LongitudinalTableData search = new LongitudinalTableData();
                    search.setColName("stu_id");
                    search.setColValue(stuId.toString());
                    search.setCompanyId(companyId);
                    search.setSchoolId(schoolId);
                    search.setTableName("student");
                    List<LongitudinalTableData> datas = this.dataServiceImpl.findLongitudinalTableDataByPage(search);
                    if (datas.size() > 0) {
                        Integer rowId = datas.get(0).getRowId();
                        LongitudinalTableData search1 = new LongitudinalTableData();
                        search1.setColName("org_name");
                        search1.setCompanyId(companyId);
                        search1.setSchoolId(schoolId);
                        search1.setRowId(rowId);
                        search1.setTableName("student");
                        List<LongitudinalTableData> datas1 = this.dataServiceImpl.findLongitudinalTableDataByPage(search1);
                        if (datas1.size() > 0) {
                            tv.setCompanyName(datas1.get(0).getColValue());
                        }
                    }
                }
            }
        }
        ViewFiles excel = new ViewFiles();
        Map<String, Object> map = new HashMap<String, Object>();

        ExcelSheetEntity entity = ExcelSheetEntity.newInstance(title, al);
        map.put("entity", entity);
        map.put("fileName", "学员列表.xls");
        return new ModelAndView(excel, map);
    }

    /**
     *
     * Class Name: TikuExamUserRelationController.java
     *
     * @Description: 导出数据试卷统计
     * @param model
     * @param search
     * @return
     */
    @RequestMapping(value = "/exportExcle1")
    public ModelAndView exportStudentsExcle1(HttpServletRequest request, Model model, TikuUserExercise exercise) {
        List<TikuUserExerciseVo> al = new ArrayList<TikuUserExerciseVo>();
        exercise.setPageSize(1000);
        WebUtils.getCurrentCompanyId();
        WebUtils.getCurrentSchoolId();
        Date endTime = exercise.getEndTime();
        if (endTime != null) {
            endTime = DateUtil.addDate(endTime, 1);
            exercise.setEndTime(endTime);
        }
        PageFinder<TikuUserExerciseVo> pageFinder = this.tikuUserExerciseServiceImpl.findAllPaperRspdInfo(exercise);
        al = pageFinder.getData();
        String title = "学员名称:name,用户名:userName,手机号:mobile,当前试卷分数:exerciseScore,考试时间:startTime";

        ViewFiles excel = new ViewFiles();
        Map<String, Object> map = new HashMap<String, Object>();

        ExcelSheetEntity entity = ExcelSheetEntity.newInstance(title, al);
        map.put("entity", entity);
        map.put("fileName", "考试统计.xls");
        return new ModelAndView(excel, map);
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
}
