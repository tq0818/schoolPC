package com.yuxin.wx.controller.tiku;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yuxin.wx.api.system.ISysConfigTeacherService;
import com.yuxin.wx.common.PageFinder2;
import com.yuxin.wx.model.system.SysConfigTeacher;

import org.apache.commons.lang.StringUtils;
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

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.system.ISysConfigServiceService;
import com.yuxin.wx.api.tiku.ITikuCategoryService;
import com.yuxin.wx.api.tiku.ITikuPaperService;
import com.yuxin.wx.api.tiku.ITikuPaperTopicService;
import com.yuxin.wx.api.tiku.ITikuPaperTopicTypeService;
import com.yuxin.wx.api.tiku.ITikuSetService;
import com.yuxin.wx.api.tiku.ITikuSubjectService;
import com.yuxin.wx.api.tiku.ITikuTopicExampointService;
import com.yuxin.wx.api.tiku.ITikuTopicOptionService;
import com.yuxin.wx.api.tiku.ITikuTopicService;
import com.yuxin.wx.api.user.IUsersService;
import com.yuxin.wx.common.JsonMsg;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.system.SysConfigService;
import com.yuxin.wx.model.tiku.TikuCategory;
import com.yuxin.wx.model.tiku.TikuPaper;
import com.yuxin.wx.model.tiku.TikuPaperTopic;
import com.yuxin.wx.model.tiku.TikuPaperTopicType;
import com.yuxin.wx.model.tiku.TikuSet;
import com.yuxin.wx.model.tiku.TikuSubject;
import com.yuxin.wx.model.tiku.TikuTopic;
import com.yuxin.wx.model.tiku.TikuTopicOption;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.tiku.TikuPaperVo;

/**
 * Controller of TikuPaper
 *
 * @author wang.zx
 * @date 2015-7-8
 */

@Controller
@RequestMapping("/tikuPaper")
public class TikuPaperController {

    private Log log = LogFactory.getLog("log");

    @Autowired
    private ISysConfigServiceService sysConfigServiceServiceImpl;

    @Autowired
    private ITikuTopicOptionService tikuTopicOptionServiceImpl;

    @Autowired
    private ITikuTopicExampointService tikuTopicExampointServiceImpl;

    @Autowired
    private ITikuPaperTopicService tikuPaperTopicServiceImpl;

    @Autowired
    private ITikuPaperTopicTypeService tikuPaperTopicTypeServiceImpl;

    @Autowired
    private ITikuTopicService tikuTopicServiceImpl;

    @Autowired
    private IUsersService usersServiceImpl;

    @Autowired
    private ITikuCategoryService tikuCategoryServiceImpl;

    @Autowired
    private ITikuPaperService tikuPaperServiceImpl;

    @Autowired
    private ITikuSetService tikuSetServiceImpl;

    @Autowired
    private ITikuSubjectService subServiceImpl;

    @Autowired
    private ITikuSubjectService tikuSubjectServiceImpl;

    @Autowired
    private ISysConfigTeacherService sysConfigTeacherServiceImpl;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model, TikuPaper search) {
        if (search == null) {
            search = new TikuPaper();
            // search.setPageSize(20);
        }
        model.addAttribute("list", this.tikuPaperServiceImpl.findTikuPaperByPage(search));
        return "tikuPaper/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(TikuPaper TikuPaper) {
        this.tikuPaperServiceImpl.insert(TikuPaper);
        return "redirect:/tikuPaper";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(TikuPaper TikuPaper) {
        this.tikuPaperServiceImpl.update(TikuPaper);
        return "redirect:/tikuPaper";
    }

    @RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
    public String del(Model model, @PathVariable Integer id) {
        this.tikuPaperServiceImpl.deleteTikuPaperById(id);
        return "redirect:/tikuPaper";
    }

    @ResponseBody
    @RequestMapping(value = "/{id:\\d+}", method = RequestMethod.GET)
    public TikuPaper getJson(Model model, @PathVariable Integer id) {
        return this.tikuPaperServiceImpl.findTikuPaperById(id);
    }

    /**
     *
     * Class Name: TikuPaperController.java
     *
     * @Description: 试卷首页查询信息
     * @author yuchanglong
     * @date 2015年7月9日 下午3:26:13
     * @version 1.0
     * @return
     */
    @RequestMapping(value = "/toTikuPaper/{tikuId}")
    public String toTikuPaper(Model model, @PathVariable Integer tikuId) {
        Integer companyId = WebUtils.getCurrentCompanyId();
        // 查询设置
        TikuSet tikuSet = new TikuSet();
        tikuSet.setCompanyId(companyId);
        tikuSet = this.tikuSetServiceImpl.findSetByCompanyIdAndCategoryId(tikuSet);

        TikuSubject search = new TikuSubject();
        search.setDelFlag(0);
        search.setCategoryId(tikuId);
        List<TikuSubject> subList = this.subServiceImpl.findTikuSubject(search);

        model.addAttribute("tikuSet", tikuSet);

        model.addAttribute("subList", subList);
        model.addAttribute("tikuId", tikuId);

        // 查询权限
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("companyId", companyId);
        param.put("groupCode", "SERVICE_TIKU_EXAM");
        SysConfigService scs = this.sysConfigServiceServiceImpl.findByCodeId(param);
        if (scs != null && scs.getDelFlag().equals(1)) {
            model.addAttribute("examOk", "examOk");
        }
        return "tiku/paper/paperIndex";
    }

    /**
     *
     * Class Name: TikuPaperController.java
     *
     * @Description: Ajax查询信息
     * @author yuchanglong
     * @date 2015年7月9日 下午4:02:58
     * @version 1.0
     * @return
     */
    @RequestMapping(value = "/loadAjaxInfo")
    public String loadAjaxInfo(TikuPaper paper, Model model) {
        paper.setPageSize(6);
        Integer companyId = WebUtils.getCurrentCompanyId();
        paper.setCompanyId(companyId);
        PageFinder<TikuPaper> pageFinder = this.tikuPaperServiceImpl.findTikuUserByPage(paper);
        TikuSet tiku = new TikuSet();
        tiku.setCompanyId(companyId);
        TikuSet set = this.tikuSetServiceImpl.findSetByCompanyIdAndCategoryId(tiku);
        if (set != null) {
            if (set.getPaperAuditYes() == 1) {
                model.addAttribute("isAudit", "yes");
            } else {
                model.addAttribute("isAudit", "no");
            }

        }
        Subject subject = SecurityUtils.getSubject();
        System.out.println(subject.hasRole("机构管理员"));
        System.out.println(subject.hasRole("试卷审核员"));
        model.addAttribute("pageFinder", pageFinder);
        return "tiku/paper/paperAjax";
    }

    /**
     *
     * Class Name: TikuPaperController.java
     *
     * @Description: 链接到题库信息
     * @author yuchanglong
     * @date 2015年7月16日 下午12:04:06
     * @version 1.0
     * @param tikuId
     * @param subjectId
     * @param paperId
     * @param model
     * @return
     */
    @RequestMapping(value = "/toPaperInfo/{tikuId}/{subjectId}/{paperId}/{btn}/{exam}")
    public String toPaperInfo(@PathVariable Integer tikuId, @PathVariable Integer subjectId, @PathVariable Integer paperId, @PathVariable String btn,
            @PathVariable String exam, Model model) {
    	pageInfoProcess(tikuId,subjectId,paperId,btn,exam,model);
        return "tiku/paper/paperInfo";
    }

    
    
    
    /**
     *
     * Class Name: TikuPaperController.java
     *
     * @Description: 插入试卷信息并返回id
     * @author yuchanglong
     * @date 2015年7月16日 下午12:04:25
     * @version 1.0
     * @param paper
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/insertAndReturnId")
    public Integer insertAndReturnId(TikuPaper paper, HttpServletRequest request) {
        paper.setCompanyId(WebUtils.getCurrentCompanyId());
        Date date = new Date();
        paper.setCreateTime(date);
        paper.setPaperStatus("PAPER_STATUS_EDIT");
        paper.setCreator(WebUtils.getCurrentUserId(request));
        this.tikuPaperServiceImpl.insert(paper);
        return paper.getId();
    }

    @ResponseBody
    @RequestMapping(value = "/updateAndReturnId")
    public Integer updateAndReturnId(TikuPaper paper) {
        this.tikuPaperServiceImpl.update(paper);

        return paper.getId();
    }

    /**
     *
     * Class Name: TikuPaperController.java
     *
     * @Description: 删除试卷
     * @author yuchanglong
     * @date 2015年7月10日 上午10:59:32
     * @version 1.0
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delPaper")
    public String delPaper(Integer id) {
        try {
            // 获得试卷下所有试题id
            List<TikuPaperTopic> tptList = this.tikuPaperTopicServiceImpl.findTopicByPaperId(id);
            if (tptList != null && tptList.size() > 0) {
                // 遍历每个试题 类型
                for (TikuPaperTopic t : tptList) {
                    Map<String, Object> param = new HashMap<String, Object>();
                    param.put("paperId", t.getPaperId());
                    param.put("topicId", t.getTopicId());
                    // 查询试题是否使用
                    TikuTopic topic = this.tikuTopicServiceImpl.findTikuTopicById(t.getTopicId());
                    // 查询是不是材料题
                    List<Integer> topicIds = new ArrayList<Integer>();
                    if (topic != null) {
                        if (topic.getTopicType().equals("TOPIC_TYPE_CASE")) {
                            // 获得子题id
                            topicIds = this.tikuTopicServiceImpl.findSubjectByParentId(t.getTopicId());
                        }
                        topicIds.add(t.getTopicId());
                        param.put("topicIds", topicIds);
                        Integer topfalg = topic.getPaperFlag();
                        if (topfalg == null || topfalg.equals(0)) {
                            this.tikuPaperTopicServiceImpl.delPaperTopic(param);
                        } else {
                            // 查询其他试卷是否使用该试题
                            List<TikuPaperTopic> tptsList = this.tikuPaperTopicServiceImpl.findOtherByPaperTopic(param);
                            if (tptsList != null && tptsList.size() > 0) {
                                // 其他试卷也有 只删除关系
                                this.tikuPaperTopicServiceImpl.delPaperTopic(param);
                            } else {
                                this.tikuPaperTopicServiceImpl.delPaperTopic(param);
                                // 彻底删除试题
                                this.tikuTopicServiceImpl.delByIds(topicIds);
                                // 删除试题相关
                                this.tikuTopicExampointServiceImpl.deleteByTopicId(topicIds);
                                this.tikuTopicOptionServiceImpl.deleteByTopicId(topicIds);
                            }
                        }
                    }
                }
            }
            this.tikuPaperServiceImpl.deleteTikuPaperById(id);
            return JsonMsg.SUCCESS;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return JsonMsg.ERROR;
        }
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
     * Class Name: TikuPaperController.java
     *
     * @Description: 试卷添加题
     * @author 周文斌
     * @date 2015-7-23 下午6:13:38
     * @version 1.0
     * @param model
     * @param request
     * @param paperId
     * @return
     */
    @RequestMapping("/addTopic")
    public String addTopic(Model model, HttpServletRequest request, Integer paperId, String btn) {
        // 根据试卷id查询试卷信息
        TikuPaper paper = this.tikuPaperServiceImpl.findTikuPaperById(paperId);

        // 试题类型
        String[] types = paper.getContainTopicType().split(",");
        List<String> topicType = new ArrayList<String>();
        for (String s : types) {
            topicType.add(s);
        }

        // 查询题库
        TikuCategory category = this.tikuCategoryServiceImpl.findTikuCategoryById(paper.getTikuCategoryId());

        // 查询科目
        TikuSubject sub = this.subServiceImpl.findTikuSubjectById(paper.getTkuSubjectId());
        // 查询设置
        TikuSet tiku = new TikuSet();
        tiku.setCompanyId(paper.getCompanyId());
        TikuSet tikuSet = this.tikuSetServiceImpl.findSetByCompanyIdAndCategoryId(tiku);
        // 查询编辑人
        Integer userId = null;
        if (paper.getUpdator() == null) {
            userId = paper.getCreator();
        } else {
            userId = paper.getUpdator();
        }

        TikuSubject search = new TikuSubject();
        search.setDelFlag(0);
        search.setCategoryId(category.getCompanyId());
        List<TikuSubject> subList = this.subServiceImpl.findTikuSubject(search);

        model.addAttribute("tikuSet", tikuSet);
        model.addAttribute("subList", subList);
        String name = this.usersServiceImpl.findRealNameByid(userId);

        model.addAttribute("sub", sub);
        model.addAttribute("userName", name);
        model.addAttribute("cate", category);
        model.addAttribute("topicType", topicType);
        model.addAttribute("paper", paper);
        model.addAttribute("set", tikuSet);
        model.addAttribute("btn", btn);
        return "tiku/paper/addTopic";
    }

    /**
     *
     * Class Name: TikuPaperController.java
     *
     * @Description: 查询试卷下的 试题
     * @author 周文斌
     * @date 2015-7-23 下午7:25:19
     * @version 1.0
     * @param model
     * @param request
     * @param paperId
     * @return
     */
    @RequestMapping("/selTopic")
    public String selTopic(Model model, HttpServletRequest request, Integer paperId, String topicType) {
        // 查询试卷下的试题
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("paperId", paperId);
        param.put("topicType", topicType);
        List<TikuTopic> topic = this.tikuTopicServiceImpl.findTopicByPaperId(param);

        // 查询分数
        Double score = this.tikuPaperTopicTypeServiceImpl.findScoreByPaperid(param);
        if (score != null) {
            model.addAttribute("score", score);
        }
        model.addAttribute("topic", topic);
        model.addAttribute("paperId", paperId);
        model.addAttribute("topicType", topicType);
        // 材料题
        if ("TOPIC_TYPE_CASE".equals(topicType)) {
            return "tiku/paper/paperTopicCase";
        } else {
            return "tiku/paper/topic";
        }
    }

    /**
     *
     * Class Name: TikuPaperController.java
     *
     * @Description: 删除试卷试题关系
     * @author 周文斌
     * @date 2015-7-24 下午3:09:26
     * @version 1.0
     * @param paperId
     * @param topicId
     * @return
     */
    @ResponseBody
    @RequestMapping("/delPaperTopic")
    public JSONObject delPaperTopic(Integer paperId, Integer topicId) {
        JSONObject json = new JSONObject();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("paperId", paperId);
        param.put("topicId", topicId);

        try {
            // 查询试题是否使用
            TikuTopic topic = this.tikuTopicServiceImpl.findTikuTopicById(topicId);
            // 查询是不是材料题
            List<Integer> topicIds = new ArrayList<Integer>();
            if (topic.getTopicType().equals("TOPIC_TYPE_CASE")) {
                // 获得子题id
                topicIds = this.tikuTopicServiceImpl.findSubjectByParentId(topicId);
            }
            topicIds.add(topicId);
            param.put("topicIds", topicIds);
            if (topic.getPaperFlag() == 0) {
                this.tikuPaperTopicServiceImpl.delPaperTopic(param);
            } else {
                // 查询其他试卷是否使用该试题
                List<TikuPaperTopic> tptList = this.tikuPaperTopicServiceImpl.findOtherByPaperTopic(param);
                if (tptList != null && tptList.size() > 0) {
                    // 其他试卷也有 只删除关系
                    this.tikuPaperTopicServiceImpl.delPaperTopic(param);
                } else {
                    this.tikuPaperTopicServiceImpl.delPaperTopic(param);
                    // 彻底删除试题
                    this.tikuTopicServiceImpl.delByIds(topicIds);
                    // 删除试题相关
                    this.tikuTopicExampointServiceImpl.deleteByTopicId(topicIds);
                    this.tikuTopicOptionServiceImpl.deleteByTopicId(topicIds);
                }
            }
            json.put(JsonMsg.MSG, "试题已删除");
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            json.put(JsonMsg.MSG, JsonMsg.INFORMATION);
        }
        return json;
    }

    /**
     *
     * Class Name: TikuPaperController.java
     *
     * @Description: 提交试卷审核
     * @author 周文斌
     * @date 2015-7-24 下午5:42:52
     * @version 1.0
     * @param paperId
     * @return
     */
    @ResponseBody
    @RequestMapping("/commitAudite")
    public JSONObject commitAudite(HttpServletRequest request, Integer paperId, String commit) {
        JSONObject json = new JSONObject();
        try {
            // 查询试卷
            TikuPaper paper = this.tikuPaperServiceImpl.findTikuPaperById(paperId);
            String[] types = paper.getContainTopicType().split(",");
            Map<String, Object> param = new HashMap<String, Object>();
            Double sum = 0.0;
            for (String s : types) {
                param.clear();
                param.put("topicType", s);
                param.put("paperId", paperId);
                // 试卷试题总分
                Double topSum = this.tikuPaperTopicServiceImpl.findSumScore(param);
                if (topSum != null) {
                    sum += topSum;
                }
            }

            if (sum > paper.getTotalScore()) {
                json.put(JsonMsg.MSG, "score");
                return json;
            }

            if (sum < paper.getTotalScore()) {
                json.put(JsonMsg.MSG, "less");
                return json;
            }

            if (commit.equals("commit")) {
                paper.setUpdateTime(new Date());
                paper.setUpdator(WebUtils.getCurrentUserId(request));
                paper.setPaperStatus("PAPER_STATUS_WAIT_AUDIT");
            } else if (commit.equals("ok") || commit.equals("publish")) {
                paper.setAuditTime(new Date());
                paper.setAuditor(WebUtils.getCurrentUserId(request));
                paper.setPaperStatus("PAPER_STATUS_PUBLISH");
                // 查询试卷下试题//查询试卷下的试题
                param.clear();
                param.put("paperId", paperId);
                List<TikuTopic> topic = this.tikuTopicServiceImpl.findTopicByPaperId(param);
                for (TikuTopic t : topic) {
                    t.setStatus("PAPER_STATUS_PUBLISH");
                    this.tikuTopicServiceImpl.update(t);
                }
            } else if (commit.equals("no")) {
                paper.setAuditTime(new Date());
                paper.setAuditor(WebUtils.getCurrentUserId(request));
                paper.setPaperStatus("PAPER_STATUS_AUDIT_FAIL");
            }

            // 查询试卷总题数
            Integer count = this.tikuPaperTopicServiceImpl.findPaperCountById(paperId);
            paper.setTotalTopicNum(count);
            this.tikuPaperServiceImpl.update(paper);
            json.put(JsonMsg.MSG, commit);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            json.put(JsonMsg.MSG, JsonMsg.ERROR);
        }
        return json;
    }

    /**
     *
     * Class Name: TikuPaperController.java
     *
     * @Description: 保存分数
     * @author 周文斌
     * @date 2015-7-27 上午11:55:52
     * @version 1.0
     * @param paperId
     * @param score
     * @param topicType
     * @return
     */
    @ResponseBody
    @RequestMapping("/saveScore")
    public JSONObject saveScore(Integer paperId, Double score, String topicType) {
        JSONObject json = new JSONObject();
        try {
            // 插入每题分数
            // 查询分数是否存在
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("paperId", paperId);
            param.put("topicType", topicType);
            TikuPaperTopicType tptt = this.tikuPaperTopicTypeServiceImpl.findScoreById(param);
            if (tptt == null) {
                tptt = new TikuPaperTopicType();
                tptt.setPaperId(paperId);
                tptt.setTopicType(topicType);
                tptt.setScorePerTopic(score);
                this.tikuPaperTopicTypeServiceImpl.insert(tptt);
            } else {
                tptt.setScorePerTopic(score);
                this.tikuPaperTopicTypeServiceImpl.update(tptt);

                List<Integer> ids = this.tikuPaperTopicServiceImpl.findIdByType(param);
                if (ids != null && ids.size() > 0) {
                    param.clear();
                    param.put("list", ids);
                    param.put("topicScore", score);
                    this.tikuPaperTopicServiceImpl.updateByIds(param);
                }
            }
            json.put(JsonMsg.MSG, "当前类型每题分值已保存");
            json.put("msgs", JsonMsg.SUCCESS);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            json.put(JsonMsg.MSG, JsonMsg.INFORMATION);
        }
        return json;
    }

    /**
     *
     * Class Name: TikuPaperController.java
     *
     * @Description: 添加已有试题到试卷
     * @author 周文斌
     * @date 2015-7-30 下午12:52:35
     * @version 1.0
     * @param paperId
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping("/addTopicPaper")
    public JSONObject addTopicPaper(Integer paperId, String ids, String topicType) {
        JSONObject json = new JSONObject();
        // 查询当前类型 分数
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("paperId", paperId);
        param.put("topicType", topicType);
        // 查询分数
        Double score = this.tikuPaperTopicTypeServiceImpl.findScoreByPaperid(param);
        if (score == null) {
            score = 0.0;
        }

        String[] id = ids.split(",");
        List<TikuPaperTopic> tptList = new ArrayList<TikuPaperTopic>();
        for (String s : id) {
            TikuPaperTopic tpt = new TikuPaperTopic();
            tpt.setPaperId(paperId);
            tpt.setTopicId(Integer.parseInt(s));
            // 此处所有的题都为材料题
            tpt.setTopicType(topicType);
            tpt.setTopicScore(score.floatValue());
            tpt.setParentId(0);
            tptList.add(tpt);
        }

        try {
            if ("TOPIC_TYPE_CASE".equals(topicType) && tptList.size() > 0) {
                // 根据材料题查询所有的材料题对应的子题
                List<TikuTopic> topics = this.tikuTopicServiceImpl.findCaseChildTopicByParentIds(id);
                if (topics != null && topics.size() > 0) {
                    for (TikuTopic topic : topics) {
                        TikuPaperTopic tpt = new TikuPaperTopic();
                        tpt.setPaperId(paperId);
                        tpt.setTopicId(topic.getId());
                        tpt.setParentId(topic.getParentId());
                        tpt.setTopicType(topic.getTopicType());
                        tptList.add(tpt);
                    }
                }
            }
            this.tikuPaperTopicServiceImpl.batchInsert(tptList);
            json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
            return json;
        } catch (Exception e) {
            this.log.error("添加已有试题出错：" + e.getMessage(), e);
            e.printStackTrace();
            json.put(JsonMsg.MSG, JsonMsg.INFORMATION);
            return json;
        }
    }

    /**
     *
     * Class Name: TikuPaperController.java
     *
     * @Description: 按查询条件搜索试卷
     * @author chopin
     * @date 2015-7-30 下午12:52:35
     * @version 1.0
     * @param paperId
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping("/search")
    public PageFinder<TikuPaperVo> search(TikuPaper search) {
        search.setPageSize(8);
        search.setCompanyId(WebUtils.getCurrentCompanyId());
        search.setPaperStatus("PAPER_STATUS_PUBLISH");
        return this.tikuPaperServiceImpl.findTikuPaperVoByPage(search);
    }
    
    /**
     * 不包含课后作业的试卷
     * @author licong
     * @date 2017年1月3日 上午11:27:57
     * @param  
     * @param search
     * @return
     */
    @ResponseBody
    @RequestMapping("/search1")
    public PageFinder2<TikuPaperVo> search1(TikuPaper search) {
        search.setPageSize(8);
        search.setCompanyId(WebUtils.getCurrentCompanyId());
        search.setPaperStatus("PAPER_STATUS_PUBLISH");
        search.setPaperType("PAPER_TYPE_HOMEWORK");
        return this.tikuPaperServiceImpl.containhw(search);
    }

    /**
     * @Title: ajaxPaperList @Description: @author male @param model @param
     *         request @param search @return String @date 2016年8月31日
     *         下午2:54:09 @version V1.0 @throws
     */

    @RequestMapping(value = "/ajaxPaperList", method = RequestMethod.POST)
    public String ajaxPaperList(Model model, HttpServletRequest request, TikuPaper search) {
        search.setCompanyId(WebUtils.getCurrentCompanyId());
        search.setPaperStatus("PAPER_STATUS_PUBLISH");
        PageFinder<TikuPaperVo> pageFinder = this.tikuPaperServiceImpl.findTikuPaperVoByPage(search);
        model.addAttribute("pageFinder", pageFinder);
        List<TikuCategory> cateList = this.tikuCategoryServiceImpl.findCateList(WebUtils.getCurrentCompanyId());
        model.addAttribute("cateList", cateList);
        return "";
    }

    @ResponseBody
    @RequestMapping(value = "getTikuSubjectByCategotyId", method = RequestMethod.GET)
    public List<TikuSubject> getTikuSubjectByCategotyId(Model model, Integer categoryId) {
        List<TikuSubject> result = this.tikuSubjectServiceImpl.findSubByCategoryId(categoryId);
        return result;
    }
    
    
    /**
     * 新审核页面
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "toPageInfoAudit")
    public String toPageInfoAudit(HttpServletRequest request,HttpServletResponse response,Model model) {
    	Integer tikuId = Integer.valueOf(request.getParameter("tikuId"));
    	Integer subjectId  = Integer.valueOf(request.getParameter("subjectId"));
    	Integer paperId = Integer.valueOf(request.getParameter("paperId"));
    	String exam = request.getParameter("exam");
    	String btn = request.getParameter("btn");
    	pageInfoProcess(tikuId,subjectId,paperId,btn,exam,model);
    	// 根据试卷id查询试卷信息
        TikuPaper paper = this.tikuPaperServiceImpl.findTikuPaperById(paperId);
        String topicTypes = paper.getContainTopicType();
        Map<String,List<TikuTopic>> topicMap = new LinkedHashMap<String,List<TikuTopic>>();
        if(StringUtils.isNotBlank(topicTypes)){
        	String types[] = topicTypes.split(",");
        	for(String type : types){
        		 Map<String, Object> param = new HashMap<String, Object>();
        	     param.put("paperId", paperId);
        	     param.put("topicType", type);
        	     List<TikuTopic> topics = this.tikuTopicServiceImpl.findTopicByPaperId(param);
        	     if(StringUtils.equals(type, "TOPIC_TYPE_RADIO") || StringUtils.equals(type, "TOPIC_TYPE_MULTIPLE") || StringUtils.equals(type, "TOPIC_TYPE_TRUE_FALSE") || StringUtils.equals(type, "TOPIC_TYPE_UNDEFINED")){
        	    	 List<Integer> idList = new ArrayList<Integer>();
        	    	 for(TikuTopic topic : topics){
        	    		 idList.add(topic.getId());
        	    	 }
        	    	 if(idList.size() > 0){
                         param.put("idList", idList);
        	    		 List<TikuTopicOption> optionList = tikuTopicOptionServiceImpl.findOptionByListTopicId(param);
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
        	     tikuTopicOptionServiceImpl.findOptionByTopicId(1);
        	     topicMap.put(type, topics);
        	}
        }
        model.addAttribute("topicMap", topicMap);
        model.addAttribute("paper", paper);
    	return "tiku/paper/paperInfoAudit";
    }
    
    
    private void pageInfoProcess(Integer tikuId,Integer subjectId,Integer paperId,String btn,String exam, Model model){
          
    	if (paperId > 0) {
            TikuPaper paper = this.tikuPaperServiceImpl.findTikuPaperById(paperId);
            model.addAttribute("paper", paper);
            if (paper.getTeacherId() != null) {
                SysConfigTeacher teacher = this.sysConfigTeacherServiceImpl.findSysConfigTeacherById(paper.getTeacherId());
                model.addAttribute("teacher", teacher);
            }
        }
        Integer companyId = WebUtils.getCurrentCompanyId();
        // 查询设置
        TikuSet tikuSet = new TikuSet();
        tikuSet.setCompanyId(companyId);
        tikuSet = this.tikuSetServiceImpl.findSetByCompanyIdAndCategoryId(tikuSet);

        TikuSubject search = new TikuSubject();
        search.setDelFlag(0);
        search.setCategoryId(tikuId);
        List<TikuSubject> subList = this.subServiceImpl.findTikuSubject(search);

        // 查询权限
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("companyId", companyId);
        param.put("groupCode", "SERVICE_TIKU_EXAM");
        SysConfigService scs = this.sysConfigServiceServiceImpl.findByCodeId(param);
        if (scs != null && scs.getDelFlag().equals(1)) {
            model.addAttribute("examOk", "examOk");
        }

        model.addAttribute("tikuSet", tikuSet);
        model.addAttribute("subList", subList);
        model.addAttribute("btn", btn);
        model.addAttribute("tikuId", tikuId);
        model.addAttribute("subjectId", subjectId);
        model.addAttribute("exam", exam);

        Map<String, String> teacherMap = new HashMap<String, String>();
        teacherMap.put("companyId", WebUtils.getCurrentCompanyId() + "");
        teacherMap.put("schoolId", WebUtils.getCurrentSchoolId() + "");
        model.addAttribute("teachers", this.sysConfigTeacherServiceImpl.findTeachers(teacherMap));
    }
}
