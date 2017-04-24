package com.yuxin.wx.controller.homework;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.api.homework.IHomeworkPaperDetailService;
import com.yuxin.wx.api.homework.IHomeworkService;
import com.yuxin.wx.api.homework.IHomeworkStudentCompleteService;
import com.yuxin.wx.api.homework.IHomeworkTeacherReadService;
import com.yuxin.wx.api.resource.IResourceListService;
import com.yuxin.wx.api.resource.IResourceUseRecordService;
import com.yuxin.wx.api.tiku.ITikuPaperService;
import com.yuxin.wx.api.tiku.ITikuPaperTopicService;
import com.yuxin.wx.api.tiku.ITikuTopicService;
import com.yuxin.wx.api.tiku.ITikuUserExerciseAnswerService;
import com.yuxin.wx.api.user.IUsersService;
import com.yuxin.wx.common.JsonMsg;
import com.yuxin.wx.common.TikuConstant;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.model.homework.Homework;
import com.yuxin.wx.model.homework.HomeworkPaperDetail;
import com.yuxin.wx.model.homework.HomeworkStudentComplete;
import com.yuxin.wx.model.homework.HomeworkTeacherRead;
import com.yuxin.wx.model.resource.ResourceList;
import com.yuxin.wx.model.resource.ResourceUseRecord;
import com.yuxin.wx.model.tiku.TikuPaper;
import com.yuxin.wx.model.tiku.TikuPaperTopic;
import com.yuxin.wx.model.tiku.TikuTopic;
import com.yuxin.wx.model.tiku.TikuUserExerciseAnswer;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.utils.PropertiesUtil;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.utils.WorkNotify;
import com.yuxin.wx.vo.tiku.TikuTopicVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Controller of HomeworkTeacherRead
 *
 * @author chopin
 * @date 2016-12-15
 */
@Controller
@RequestMapping("/homeworkTeacherRead")
public class HomeworkTeacherReadController {

    Log log = LogFactory.getLog("log");
    @Autowired
    private ITikuPaperService tikuPaperServiceImpl;
    @Autowired
    private ITikuPaperTopicService tikuPaperTopicServiceImpl;
    @Autowired
    private IHomeworkService homeworkServiceImpl;
    @Autowired
    private IHomeworkTeacherReadService homeworkTeacherReadServiceImpl;
    @Autowired
    private IHomeworkStudentCompleteService homeworkStudentCompleteServiceImpl;
    @Autowired
    private IHomeworkPaperDetailService homeworkPaperDetailServiceImpl;
    @Autowired
    private ITikuUserExerciseAnswerService tikuUserExerciseAnswerServiceImpl;
    @Autowired
    private ICompanyServiceStaticService companyServiceStaticImpl;
    @Autowired
    private IUsersService usersImpl;
    @Autowired
    private IResourceListService resourceListImpl;
    @Autowired
    private PropertiesUtil propertiesUtil;
    @Autowired
    private IResourceUseRecordService resourceUseRecordImpl;
    @Autowired
    private ITikuTopicService tikuTopicServiceImpl;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model, HomeworkTeacherRead search) {
        if (search == null) {
            search = new HomeworkTeacherRead();
            // search.setPageSize(20);
        }
        model.addAttribute("list", this.homeworkTeacherReadServiceImpl.findHomeworkTeacherReadByPage(search));
        return "homeworkTeacherRead/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(HomeworkTeacherRead HomeworkTeacherRead) {
        this.homeworkTeacherReadServiceImpl.insert(HomeworkTeacherRead);
        return "redirect:/homeworkTeacherRead";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(HomeworkTeacherRead HomeworkTeacherRead) {
        this.homeworkTeacherReadServiceImpl.update(HomeworkTeacherRead);
        return "redirect:/homeworkTeacherRead";
    }

    @RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
    public String del(Model model, @PathVariable Integer id) {
        this.homeworkTeacherReadServiceImpl.deleteHomeworkTeacherReadById(id);
        return "redirect:/homeworkTeacherRead";
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public HomeworkTeacherRead getJson(Model model, @PathVariable Integer id) {
        return this.homeworkTeacherReadServiceImpl.findHomeworkTeacherReadById(id);
    }

    @RequestMapping(value = "checkHomeWork", method = RequestMethod.GET)
    public String checkHomeWork(Model model, HttpServletRequest request, Integer studentCompleteId) {
        HomeworkStudentComplete hwStudentComplete = this.homeworkStudentCompleteServiceImpl.findHomeworkStudentCompleteById(studentCompleteId);
        if (hwStudentComplete == null) {
            model.addAttribute("msg", "数据不存在");
            return "homework/readHomework";
        }
        model.addAttribute("hwStudentComplete", hwStudentComplete);
        Homework hw = this.homeworkServiceImpl.findHomeworkById(hwStudentComplete.getHomeworkId());
        if (hw == null) {
            model.addAttribute("msg", "该作业已被删除");
            return "homework/readHomework";
        }
        model.addAttribute("hw", hw);
        TikuPaper paper = this.tikuPaperServiceImpl.findTikuPaperById(hwStudentComplete.getPaperId());
        if (paper == null) {
            model.addAttribute("msg", "试卷被删除");
            return "homework/readHomework";
        }
        model.addAttribute("paper", paper);
        Map<String, List<TikuTopic>> topicTypeMap = new TreeMap<String, List<TikuTopic>>(new Comparator<Object>() {
            @Override
            public int compare(Object mapKey1, Object mapKey2) {
                String a = TikuConstant.TopicType.name(mapKey1.toString());
                String b = TikuConstant.TopicType.name(mapKey2.toString());
                return a.compareTo(b);
            }
        });
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("paperId", paper.getId());
        List<TikuTopic> tikuTopics = this.tikuTopicServiceImpl.findTopicByPaperId(param);
        int count = 0;
        for (TikuTopic topic : tikuTopics) {
            String topicType = topic.getTopicType();
            Integer parentId = topic.getParentId();
            if (!topicTypeMap.containsKey(topicType)) {
                topicTypeMap.put(topicType, new ArrayList<TikuTopic>());
            }
            if ("TOPIC_TYPE_CASE".equals(topicType)) {
                List<TikuTopic> clChildrens = this.tikuTopicServiceImpl.findTikuTopicListByParentId(topic.getId());
                for (TikuTopic cc : clChildrens) {
                    topicTypeMap.get(topicType).add(cc);
                    count++;
                }
            } else {
                if (parentId == null || parentId == 0) {
                    // 其他非材料题
                    topicTypeMap.get(topicType).add(topic);
                } else {
                    // 材料子题
                    if (topicTypeMap.containsKey("TOPIC_TYPE_CASE")) {
                        topicTypeMap.put("TOPIC_TYPE_CASE", new ArrayList<TikuTopic>());
                    }
                    topicTypeMap.get("TOPIC_TYPE_CASE").add(topic);
                }
                count++;
            }
        }
        // 题目
        model.addAttribute("topTypeMap", topicTypeMap);
        model.addAttribute("topCount", count);

        Map<String, Object> baseData = new HashMap<String, Object>();
        baseData.put("total", 0);
        baseData.put("right", 0);
        baseData.put("wrong", 0);
        baseData.put("none", 0);
        baseData.put("score", 0);
        baseData.put("tidList", new ArrayList<Integer>());
        Map<String, Map<String, Object>> wrData = new HashMap<String, Map<String, Object>>();
        Map<String, Object> danxuan = new HashMap<String, Object>();
        danxuan.putAll(baseData);
        danxuan.put("tidList", new ArrayList<Integer>());
        wrData.put("TOPIC_TYPE_RADIO", danxuan);
        Map<String, Object> duoxuan = new HashMap<String, Object>();
        duoxuan.putAll(baseData);
        duoxuan.put("tidList", new ArrayList<Integer>());
        wrData.put("TOPIC_TYPE_MULTIPLE", duoxuan);
        Map<String, Object> budingxiangxuan = new HashMap<String, Object>();
        budingxiangxuan.putAll(baseData);
        budingxiangxuan.put("tidList", new ArrayList<Integer>());
        wrData.put("TOPIC_TYPE_UNDEFINED", budingxiangxuan);
        Map<String, Object> panduan = new HashMap<String, Object>();
        panduan.putAll(baseData);
        panduan.put("tidList", new ArrayList<Integer>());
        wrData.put("TOPIC_TYPE_TRUE_FALSE", panduan);
        Map<String, Object> tiankong = new HashMap<String, Object>();
        tiankong.putAll(baseData);
        tiankong.put("tidList", new ArrayList<Integer>());
        wrData.put("TOPIC_TYPE_FILLING", tiankong);
        Map<String, Object> jianda = new HashMap<String, Object>();
        jianda.putAll(baseData);
        jianda.put("tidList", new ArrayList<Integer>());
        wrData.put("TOPIC_TYPE_ANSWER", jianda);

        if (tikuTopics != null && tikuTopics.size() > 0) {
            for (TikuTopic top : tikuTopics) {
                Integer cParentId = top.getParentId();
                String tTopType = top.getTopicType();
                if ("TOPIC_TYPE_CASE".equals(tTopType)) {
                    List<TikuTopic> topicList = this.tikuTopicServiceImpl.findTikuTopicListByParentId(top.getId());
                    for (TikuTopic tp : topicList) {
                        ((List<Integer>) wrData.get(tp.getTopicType()).get("tidList")).add(tp.getId());
                        wrData.get(tp.getTopicType()).put("total", (Integer) wrData.get(tp.getTopicType()).get("total") + 1);
                        wrData.get(tp.getTopicType()).put("none", wrData.get(tp.getTopicType()).get("total"));
                    }
                } else if (!cParentId.equals(0)) {
                    // 材料子题
                    ((List<Integer>) wrData.get(tTopType).get("tidList")).add(top.getId());
                    wrData.get(tTopType).put("total", (Integer) wrData.get(tTopType).get("total") + 1);
                    wrData.get(tTopType).put("none", wrData.get(tTopType).get("total"));
                } else {
                    ((List<Integer>) wrData.get(tTopType).get("tidList")).add(top.getId());
                    wrData.get(tTopType).put("total", (Integer) wrData.get(tTopType).get("total") + 1);
                    wrData.get(tTopType).put("none", wrData.get(tTopType).get("total"));
                }
            }
        }

        // 回答
        List<TikuUserExerciseAnswer> exerciseAnswer = this.tikuUserExerciseAnswerServiceImpl.findByExerId(hwStudentComplete.getExerciseId());
        if (exerciseAnswer != null && exerciseAnswer.size() > 0) {
            for (TikuUserExerciseAnswer ans : exerciseAnswer) {
                String atopType = ans.getTopicType();
                Integer correctFlag = ans.getCorrectFlag();
                Integer parentId = ans.getParentId();
                if (correctFlag == null) {
                    correctFlag = 0;
                }
                if (parentId == null) {
                    parentId = 0;
                }
                if (correctFlag == 1) {
                    wrData.get(atopType).put("right", (Integer) wrData.get(atopType).get("right") + 1);
                    wrData.get(atopType).put("score", (Integer) wrData.get(atopType).get("score") + ans.getScore().intValue());
                } else {
                    wrData.get(atopType).put("wrong", (Integer) wrData.get(atopType).get("wrong") + 1);
                }
                wrData.get(atopType).put("none", (Integer) wrData.get(atopType).get("none") - 1);
            }
        }
        model.addAttribute("exerciseAnswer", exerciseAnswer);
        model.addAttribute("wrData", wrData);

        Users user = WebUtils.getCurrentUser();
        // 查询当前已批阅的题id
        HomeworkPaperDetail hwPaperDetailSearch = new HomeworkPaperDetail();
        hwPaperDetailSearch.setHomeworkId(hwStudentComplete.getHomeworkId());
        hwPaperDetailSearch.setHomeworkSId(hwStudentComplete.getId());
        List<HomeworkPaperDetail> hwPaperDetailList = this.homeworkPaperDetailServiceImpl.findHomeworkPaperDetailByPage(hwPaperDetailSearch);
        List<Integer> hwPaperDetailIdList = new ArrayList<Integer>();
        for (HomeworkPaperDetail hwpaperDetail : hwPaperDetailList) {
            hwPaperDetailIdList.add(hwpaperDetail.getTopicId());
        }
        model.addAttribute("hwPaperDetailIdList", hwPaperDetailIdList);

        // 插入老师阅卷表
        HomeworkTeacherRead homeworkTeacherReadSearch = new HomeworkTeacherRead();
        homeworkTeacherReadSearch.setHomeworkId(hwStudentComplete.getHomeworkId());
        homeworkTeacherReadSearch.setReader(user.getId());
        homeworkTeacherReadSearch.setHomeworkSId(hwStudentComplete.getId());
        List<HomeworkTeacherRead> homeworkTeacherReadList = this.homeworkTeacherReadServiceImpl.findHomeworkTeacherReadByPage(homeworkTeacherReadSearch);
        if (homeworkTeacherReadList == null || homeworkTeacherReadList.size() == 0) {
            // homeworkTeacherReadSearch.setContent("阅卷中");
            homeworkTeacherReadSearch.setReadTime(new Date());
            this.homeworkTeacherReadServiceImpl.insert(homeworkTeacherReadSearch);
        } else {
            homeworkTeacherReadSearch = homeworkTeacherReadList.get(0);
        }
        model.addAttribute("homeworkTeacherRead", homeworkTeacherReadSearch);
        return "homework/readHomework";
    }

    // 试卷作业保存老师判题的分数
    @ResponseBody
    @RequestMapping(value = "/savePaperTopicScore", method = RequestMethod.POST)
    public Map<String, Object> savePaperTopicScore(Model model, HomeworkPaperDetail hwPaperDetail, Integer exerciseId) {
        Map<String, Object> rlt = new HashMap<String, Object>();
        HomeworkStudentComplete hwstudentComplete = this.homeworkStudentCompleteServiceImpl.findHomeworkStudentCompleteById(hwPaperDetail.getHomeworkSId());
        if (hwstudentComplete == null) {
            rlt.put("ec", "-1");
            rlt.put("msg", "提交批阅失败,没有查询到正确的批阅记录");
            return rlt;
        }
        if (hwstudentComplete.getStatus().equals("3")) {
            rlt.put("ec", "-1");
            rlt.put("msg", "试卷已完成批阅， 不允许修改");
            return rlt;
        }
        // 校验分数是否正常
        if (hwPaperDetail.getScore() == null) {
            hwPaperDetail.setScore(0);
        }

        TikuPaperTopic paperTopic = new TikuPaperTopic();
        paperTopic.setPaperId(hwstudentComplete.getPaperId());
        paperTopic.setTopicId(hwPaperDetail.getTopicId());
        List<TikuPaperTopic> paperTopicList = this.tikuPaperTopicServiceImpl.findTikuPaperTopicByPage(paperTopic);
        if (paperTopicList == null || paperTopicList.size() == 0 || paperTopicList.get(0).getTopicScore() == null) {
            paperTopic.setTopicScore(0.0f);
        }
        if (hwPaperDetail.getScore() < 0) {
            rlt.put("ec", "-1");
            rlt.put("msg", "打分不允许为负数，请重新打分");
            return rlt;
        }
        if (hwPaperDetail.getScore() > paperTopicList.get(0).getTopicScore()) {
            rlt.put("ec", "-1");
            rlt.put("msg", "分数不能超过该题总分，请重新打分");
            return rlt;
        }

        HomeworkPaperDetail search = new HomeworkPaperDetail();
        search.setHomeworkId(hwPaperDetail.getHomeworkId());
        search.setHomeworkSId(hwPaperDetail.getHomeworkSId());
        search.setTopicId(hwPaperDetail.getTopicId());
        List<HomeworkPaperDetail> homeworkPaperDetailList = this.homeworkPaperDetailServiceImpl.findHomeworkPaperDetailByPage(search);
        if (homeworkPaperDetailList != null && homeworkPaperDetailList.size() > 0) {
            // 重新判题
            HomeworkPaperDetail oldHwPaperDetail = homeworkPaperDetailList.get(0);
            oldHwPaperDetail.setScore(hwPaperDetail.getScore());
            oldHwPaperDetail.setContent(hwPaperDetail.getContent());
            this.homeworkPaperDetailServiceImpl.update(oldHwPaperDetail);
        } else {
            Homework hw = this.homeworkServiceImpl.findHomeworkById(hwPaperDetail.getHomeworkId());
            // 插入试卷id
            hwPaperDetail.setPaperId(hw.getPaperId());
            this.homeworkPaperDetailServiceImpl.insert(hwPaperDetail);

        }
        rlt.put("ec", "0");
        rlt.put("em", "");
        return rlt;
    }

    @ResponseBody
    @RequestMapping(value = "/findHwPaperTopic", method = RequestMethod.POST)
    public Map<String, Object> findHwPaperTopic(Model model, HttpServletRequest request, Integer hwId, Integer exerciseId, Integer topicId) {
        Map<String, Object> rlt = new HashMap<String, Object>();
        TikuTopicVo topic = this.tikuTopicServiceImpl.findTopicById(topicId);
        rlt.put("topic", topic);
        if (topic.getParentId() != null && topic.getParentId() != 0) {
            TikuTopicVo parent = this.tikuTopicServiceImpl.findTopicById(topic.getParentId());
            rlt.put("parent", parent);
        }

        TikuUserExerciseAnswer userAnswer = new TikuUserExerciseAnswer();
        userAnswer.setUserExerciseId(exerciseId);
        userAnswer.setTopicId(topicId);
        List<TikuUserExerciseAnswer> taList = this.tikuUserExerciseAnswerServiceImpl.findTikuUserExerciseAnswerByPage(userAnswer);
        if (taList != null && taList.size() > 0) {
            userAnswer = taList.get(0);
        }
        rlt.put("userAnswer", userAnswer);

        HomeworkStudentComplete homeworkStudentComplete = new HomeworkStudentComplete();
        homeworkStudentComplete.setExerciseId(exerciseId);
        List<HomeworkStudentComplete> homeworkStudentCompleteList = this.homeworkStudentCompleteServiceImpl
                .findHomeworkStudentCompleteByPage(homeworkStudentComplete);
        if (homeworkStudentCompleteList != null && homeworkStudentCompleteList.size() > 0) {
            homeworkStudentComplete = homeworkStudentCompleteList.get(0);
        }
        HomeworkPaperDetail hwPaperDetail = new HomeworkPaperDetail();
        hwPaperDetail.setHomeworkId(hwId);
        hwPaperDetail.setHomeworkSId(homeworkStudentComplete.getId());
        hwPaperDetail.setTopicId(topicId);
        List<HomeworkPaperDetail> hwPaperDatailList = this.homeworkPaperDetailServiceImpl.findHomeworkPaperDetailByPage(hwPaperDetail);
        if (hwPaperDatailList != null && hwPaperDatailList.size() > 0) {
            hwPaperDetail = hwPaperDatailList.get(0);
        }
        rlt.put("hwPaperDetail", hwPaperDetail);
        return rlt;
    }

    @ResponseBody
    @RequestMapping(value = "/completeRead", method = RequestMethod.POST)
    public Map<String, Object> completeRead(Model model, HttpServletRequest request, HomeworkTeacherRead hwTeacherRead) {
        Map<String, Object> rlt = new HashMap<String, Object>();
        HomeworkTeacherRead homeworkTeacherRead = this.homeworkTeacherReadServiceImpl.findHomeworkTeacherReadById(hwTeacherRead.getId());
        HomeworkStudentComplete hwstudentComplete = this.homeworkStudentCompleteServiceImpl
                .findHomeworkStudentCompleteById(homeworkTeacherRead.getHomeworkSId());
        if (homeworkTeacherRead == null || hwstudentComplete == null) {
            rlt.put("ec", "-1");
            rlt.put("msg", "提交批阅失败,没有查询到正确的批阅记录");
            return rlt;
        }
        if (hwstudentComplete.getStatus().equals("3")) {
            rlt.put("ec", "-1");
            rlt.put("msg", "试卷已完成批阅， 不允许修改");
            return rlt;
        }
        HomeworkStudentComplete homeworkStudentComplete = this.homeworkStudentCompleteServiceImpl
                .findHomeworkStudentCompleteById(homeworkTeacherRead.getHomeworkSId());
        Double score1 = this.tikuUserExerciseAnswerServiceImpl.findExerciseScore(homeworkStudentComplete.getExerciseId());
        Double score2 = this.homeworkPaperDetailServiceImpl.findReadScoreBySid(homeworkStudentComplete.getId());
        if (score1 == null) {
            score1 = 0.0;
        }
        if (score2 == null) {
            score2 = 0.0;
        }
        homeworkTeacherRead.setContent(hwTeacherRead.getContent());
        homeworkTeacherRead.setScore((score1 + score2) + "");
        this.homeworkTeacherReadServiceImpl.update(homeworkTeacherRead);

        HomeworkStudentComplete hwStudentComplete = this.homeworkStudentCompleteServiceImpl
                .findHomeworkStudentCompleteById(homeworkTeacherRead.getHomeworkSId());
        hwStudentComplete.setStatus(3);// 完成批阅
        this.homeworkStudentCompleteServiceImpl.update(hwStudentComplete);
        rlt.put("ec", "0");
        rlt.put("msg", "试卷已完成批阅， 不允许修改");
        return rlt;
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
     *
     * Class Name: HomeworkTeacherReadController.java
     *
     * @Description: 批阅附件作业
     * @author dongshuai
     * @date 2016年12月19日 下午5:49:35
     * @modifier
     * @modify-date 2016年12月19日 下午5:49:35
     * @version 1.0
     * @param json
     * @param htr
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveScore", method = RequestMethod.POST)
    public JSONObject saveScore(HttpServletRequest request, HomeworkTeacherRead htr, HomeworkStudentComplete hsc, Integer htrId) {
        JSONObject json = new JSONObject();
        WebUtils.getCurrentUser().getId();
        Date readTime = new Date();
        /* 添加批阅 */
        htr.setId(htrId);
        htr.setReadTime(readTime);
        this.homeworkTeacherReadServiceImpl.update(htr);
        /* 发送信息 */
        WorkNotify.notify(request, htr.getId());
        /* 更新状态 */
        hsc.setId(htr.getHomeworkSId());
        hsc.setContent(null);
        hsc.setHomeworkId(null);
        hsc.setResourceId(null);
        this.homeworkStudentCompleteServiceImpl.update(hsc);
        json.put("result", "success");
        return json;
    }

    /**
     *
     * Class Name: HomeworkTeacherReadController.java
     *
     * @Description: 上传
     * @author dongshuai
     * @date 2016年12月19日 下午9:52:52
     * @modifier
     * @modify-date 2016年12月19日 下午9:52:52
     * @version 1.0
     * @param request
     * @param rl
     * @return
     */
    @ResponseBody
    @RequestMapping("/homeworkUpload")
    public JSONObject homeworkUpload(HttpServletRequest request, ResourceList rl) {
        JSONObject json = new JSONObject();
        Integer companyId = WebUtils.getCurrentCompanyId();
        CompanyServiceStatic css = this.companyServiceStaticImpl.findByCompanyId(companyId);
        long crs = 0;
        try {
            Users user = this.usersImpl.findUsersById(WebUtils.getCurrentUserId(request));
            rl.setCompanyId(companyId);
            rl.setUploadTime(new Date());
            rl.setUploadUserId(user.getId());
            rl.setUploadType(2);
            rl.setDelFlag(0);
            rl.setUploadUserName((user.getRealName() != null ? user.getRealName() : user.getUsername()));
            rl.setOldData(0);
            this.resourceListImpl.insert(rl);
            json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
            json.put("fileId", rl.getId());
            return json;
        } catch (Exception e) {
            this.log.error("文件上传失败!", e);
            e.printStackTrace();
            json.put(JsonMsg.MSG, "文件上传失败");
            return json;
        }
    }

    /**
     *
     * Class Name: HomeworkTeacherReadController.java
     *
     * @Description: 作业下载
     * @author dongshuai
     * @date 2016年12月19日 下午9:52:55
     * @modifier
     * @modify-date 2016年12月19日 下午9:52:55
     * @version 1.0
     * @param request
     * @param response
     */
    @RequestMapping(value = "/homeworkDdownload", method = RequestMethod.POST)
    public void homeworkDdownload(HttpServletRequest request, HttpServletResponse response) {
        String ids = request.getParameter("ids");
        Integer id = Integer.parseInt(ids);
        Integer userId = WebUtils.getCurrentUserId(request);
        Integer companyId = WebUtils.getCurrentCompanyId();
        CompanyServiceStatic css = this.companyServiceStaticImpl.findByCompanyId(companyId);
        ResourceList rl = this.resourceListImpl.findResourceListById(id);

        String path = "";
//        if (rl.getOldData() != null && !rl.getOldData().equals(1)) {
//            path = FileQNUtils.download(rl.getSourcePath());
//        } else {
//
//        }
        path = "http://" + this.propertiesUtil.getProjectImageUrl() + "/" + rl.getSourcePath();
        response.setContentType("application/octet-stream");
        byte[] b = new byte[1024 * 1024];
        int ch = 0;
        try {
            OutputStream out = response.getOutputStream();
            InputStream in = null;
            response.setHeader("content-disposition", "attachment;fileName=" + new String(rl.getFileName().getBytes("gb2312"), "ISO8859-1").replace(",", ""));
            URL url = new URL(path);
            in = url.openConnection().getInputStream();
            while ((ch = in.read(b)) != -1) {
                out.write(b, 0, ch);
            }
            in.close();
            out.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        long dangq = Long.parseLong(rl.getSourceSize());
        long cssflow = Long.parseLong((css.getResourceFlow() != null ? css.getResourceFlow() : "0"));
        css.setResourceFlow((cssflow + dangq) + "");
        ResourceUseRecord rur = new ResourceUseRecord();
        rur.setFileId(rl.getId());
        rur.setUseFlow((dangq) + "");
        if (userId != null) {
            rur.setUserId("b_" + userId);
        }
        rur.setUseTime(new Date());
        rur.setUseType(1);
        rur.setCompanyId(companyId);
        this.resourceUseRecordImpl.insert(rur);
        this.companyServiceStaticImpl.update(css);
    }

    /**
     *
     * Class Name: HomeworkTeacherReadController.java
     *
     * @Description: 保存附件
     * @author dongshuai
     * @date 2016年12月30日 下午6:33:02
     * @modifier
     * @modify-date 2016年12月30日 下午6:33:02
     * @version 1.0
     * @param htrId
     * @param resourceId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveResource")
    public JSONObject saveResource(Integer htrId, Integer resourceId) {
        JSONObject json = new JSONObject();
        HomeworkTeacherRead htr = this.homeworkTeacherReadServiceImpl.findHomeworkTeacherReadById(htrId);
        if (htr != null) {
            htr.setResourceId(resourceId + "");
            this.homeworkTeacherReadServiceImpl.update(htr);
            json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
        } else {
            json.put(JsonMsg.MSG, "保存附件失败");
        }
        return json;
    }
}
