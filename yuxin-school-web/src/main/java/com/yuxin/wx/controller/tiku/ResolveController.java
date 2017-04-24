package com.yuxin.wx.controller.tiku;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.tiku.ITikuTopicService;
import com.yuxin.wx.api.tiku.ITikuUserBatchService;
import com.yuxin.wx.api.tiku.ITikuUserCollectService;
import com.yuxin.wx.api.tiku.ITikuUserExerciseAnswerService;
import com.yuxin.wx.api.tiku.ITikuUserExerciseService;
import com.yuxin.wx.common.TikuConstant;
import com.yuxin.wx.model.tiku.TikuPaper;
import com.yuxin.wx.model.tiku.TikuTopic;
import com.yuxin.wx.model.tiku.TikuUserBatch;
import com.yuxin.wx.model.tiku.TikuUserCollect;
import com.yuxin.wx.model.tiku.TikuUserExercise;
import com.yuxin.wx.model.tiku.TikuUserExerciseAnswer;
import com.yuxin.wx.tiku.mapper.TikuPaperMapper;
import com.yuxin.wx.vo.tiku.TikuTopicVo;

@Controller
@RequestMapping("/resolve")
public class ResolveController {

    @Autowired
    private ITikuUserBatchService tikuUserBatchServiceImpl;

    @Autowired
    private ITikuUserCollectService tikuUserCollectServiceImpl;

    @Autowired
    private ITikuUserExerciseAnswerService tikuUserExerciseAnswerServiceImpl;

    @Autowired
    private TikuPaperMapper tikuPaperMapper;

    @Autowired
    private ITikuTopicService tikuTopicServiceImpl;

    @Autowired
    private ITikuUserExerciseService tikuUserExerciseServiceImpl;

    private Log log = LogFactory.getLog("log");

    /**
     *
     * Class Name: ResolveController.java
     *
     * @Description:解析试卷试题
     * @author 周文斌
     * @date 2015-8-24 下午6:38:44
     * @version 1.0
     * @param model
     * @param request
     * @param exerId
     * @return
     */
    @RequestMapping("/showResolve/{exerId}/{userId}")
    public String showResolve(Model model, HttpServletRequest request, @PathVariable Integer exerId, @PathVariable Integer userId) {

        // 根据历史id 查询 题次
        TikuUserExercise exer = this.tikuUserExerciseServiceImpl.findTikuUserExerciseById(exerId);

        Map<String, List<TikuTopic>> param = new TreeMap<String, List<TikuTopic>>(new Comparator<Object>() {
            @Override
            public int compare(Object mapKey1, Object mapKey2) {
                String a = TikuConstant.TopicType.name(mapKey1.toString());
                String b = TikuConstant.TopicType.name(mapKey2.toString());
                return a.compareTo(b);
            }
        });
        // 查看是 试题 还是 其他
        if (TikuConstant.paperExam.contains(exer.getExerciseType())) {
            // 去查试卷下的试题 根据id排序
            List<TikuTopic> topicList = this.tikuTopicServiceImpl.findByPaperId(exer.getExerciseId(), userId, exerId);
            List<TikuTopic> dateList = null;
            if (topicList != null && topicList.size() > 0) {
                for (TikuTopic t : topicList) {
                    if (t.getTopicType().equals("TOPIC_TYPE_CASE") && t.getParentId().equals(0)) {
                        TikuUserExerciseAnswer tuea = new TikuUserExerciseAnswer();
                        tuea.setUserId(userId);
                        tuea.setUserExerciseId(exerId);
                        tuea.setParentId(t.getId());
                        t.setTopicList(this.tikuTopicServiceImpl.findListByParentId(tuea));
                    }
                    if (param.containsKey(t.getTopicType()) && t.getParentId().equals(0)) {
                        dateList = param.get(t.getTopicType());
                        dateList.add(t);
                    } else if (!param.containsKey(t.getTopicType()) && t.getParentId().equals(0)) {
                        dateList = new ArrayList<TikuTopic>();
                        dateList.add(t);
                        param.put(t.getTopicType(), dateList);
                    }
                }
            }
            // 查询试卷名
            TikuPaper paper = this.tikuPaperMapper.findById(exer.getExerciseId());
            model.addAttribute("paper", paper);
        } else {
            // 查询试题根据练习id
            List<TikuTopic> topicList = this.tikuTopicServiceImpl.findTopicByBatchId(exer.getExerciseId(), userId, exerId);

            List<Integer> topoicids = new ArrayList<Integer>();
            List<TikuTopic> delt = new ArrayList<TikuTopic>();
            List<TikuTopic> addt = new ArrayList<TikuTopic>();
            for (TikuTopic t : topicList) {
                if (t.getParentId() != null && !t.getParentId().equals(0)) {
                    if (!topoicids.contains(t.getParentId())) {
                        topoicids.add(t.getParentId());
                        addt.add(this.tikuTopicServiceImpl.findTikuTopicById(t.getParentId()));
                    }
                    delt.add(t);
                }
            }
            topicList.addAll(addt);
            topicList.removeAll(delt);

            List<TikuTopic> dateList = null;
            if (topicList != null && topicList.size() > 0) {
                for (TikuTopic t : topicList) {
                    if (t.getTopicType().equals("TOPIC_TYPE_CASE") && t.getParentId().equals(0)) {
                        TikuUserExerciseAnswer tuea = new TikuUserExerciseAnswer();
                        tuea.setUserId(userId);
                        tuea.setUserExerciseId(exerId);
                        tuea.setParentId(t.getId());
                        t.setTopicList(this.tikuTopicServiceImpl.findListByParentId(tuea));
                    }
                    if (param.containsKey(t.getTopicType()) && t.getParentId().equals(0)) {
                        dateList = param.get(t.getTopicType());
                        dateList.add(t);
                    } else if (!param.containsKey(t.getTopicType()) && t.getParentId().equals(0)) {
                        dateList = new ArrayList<TikuTopic>();
                        dateList.add(t);
                        param.put(t.getTopicType(), dateList);
                    }
                }
            }
            // 查询batch
            TikuUserBatch batch = this.tikuUserBatchServiceImpl.findTikuUserBatchById(exer.getExerciseId());
            model.addAttribute("batch", batch);
        }

        model.addAttribute("exerId", exerId);
        model.addAttribute("userId", userId);
        model.addAttribute("exer", exer);
        model.addAttribute("map", param);

        return "tiku/resolve/resolve";
    }

    /**
     *
     * Class Name: ResolveController.java
     *
     * @Description: 查询试题详细
     * @author 周文斌
     * @date 2015-8-27 下午6:16:42
     * @version 1.0
     * @param model
     * @param request
     * @param ue
     * @param pager
     * @return
     */
    @RequestMapping("/selTopic")
    public String selTopic(Model model, HttpServletRequest request, TikuUserExerciseAnswer ue, Integer pager, Integer userId) {
        // 根据试题id 练习表id 查询练习表答案
        TikuUserExerciseAnswer answer = this.tikuUserExerciseAnswerServiceImpl.findAnswerByMoreId(ue);

        // 根据练习表id查询
        TikuUserExercise exer = this.tikuUserExerciseServiceImpl.findTikuUserExerciseById(ue.getUserExerciseId());
        TikuTopicVo topicVo = null;
        if (exer != null) {
            if (exer.getExerciseType().equals("EXERCISE_TYPE_PAPER")) {
                // 试题详细
                topicVo = this.tikuTopicServiceImpl.findTopicById(ue.getTopicId());
                // 此类型共有多少道题
                /*
                 * TikuPaperTopic tpt = new TikuPaperTopic();
                 * tpt.setPaperId(exer.getExerciseId());
                 * tpt.setTopicType(topicVo.getTopicType()); Integer count =
                 * tikuPaperTopicServiceImpl.findCountByMore(tpt); //次题型，每题多少分
                 * TikuPaperTopicType tptt = new TikuPaperTopicType();
                 * tptt.setPaperId(exer.getExerciseId());
                 * tptt.setTopicType(topicVo.getTopicType()); Double score =
                 * tikuPaperTopicTypeServiceImpl.findScoreByMore(tptt);
                 */
                if (topicVo.getTopicName() != null) {
                    topicVo.setTopicName(topicVo.getTopicName().replace("&lt;", "<"));
                    topicVo.setTopicName(topicVo.getTopicName().replace("&gt;", ">"));
                    topicVo.setTopicName(topicVo.getTopicName().replace("&nbsp;", ""));
                    String anas = topicVo.getTopicName();
                    if (anas != null && anas.indexOf("&&") >= 0) {
                        String[] ans = anas.split("&&");
                        String an = "";
                        for (int i = 0; i < ans.length; i++) {
                            an += "<p>" + (i + 1) + "." + ans[i] + "</p><br>";
                        }
                        if (an.indexOf("&&") == 0 && (ans[ans.length - 1].equals("") || ans[ans.length - 1].equals(" "))) {
                            an += "<p>" + (ans.length + 1) + ".</p><br>";
                        }
                        topicVo.setTopicName(an);
                    }
                }

                if (topicVo.getAnalyseWord() != null) {
                    topicVo.setAnalyseWord(topicVo.getAnalyseWord().replace("&lt;", "<"));
                    topicVo.setAnalyseWord(topicVo.getAnalyseWord().replace("&gt;", ">"));
                    topicVo.setAnalyseWord(topicVo.getAnalyseWord().replace("&nbsp;", ""));
                    String anas = topicVo.getAnswer();
                    if (anas != null && anas.indexOf("&&") >= 0) {
                        String[] ans = anas.split("&&");
                        String an = "";
                        for (int i = 0; i < ans.length; i++) {
                            an += "<p>" + (i + 1) + "." + ans[i] + "</p><br>";
                        }
                        if (an.indexOf("&&") == 0 && (ans[ans.length - 1].equals("") || ans[ans.length - 1].equals(" "))) {
                            an += "<p>" + (ans.length + 1) + ".</p><br>";
                        }
                        topicVo.setAnswer(an);
                    }
                } /*
                   * model.addAttribute("count", count);
                   * model.addAttribute("score", score);
                   */
            } else {
                // 试题详细
                topicVo = this.tikuTopicServiceImpl.findTopicById(ue.getTopicId());
                // 此类型共有多少道题
                /*
                 * TikuPaperTopic tpt = new TikuPaperTopic();
                 * tpt.setPaperId(exer.getExerciseId());
                 * tpt.setTopicType(topicVo.getTopicType()); Integer count =
                 * tikuPaperTopicServiceImpl.findCountByMore(tpt);
                 */
                // 次题型，每题多少分
                /*
                 * TikuPaperTopicType tptt = new TikuPaperTopicType();
                 * tptt.setPaperId(exer.getExerciseId());
                 * tptt.setTopicType(topicVo.getTopicType()); Double score =
                 * tikuPaperTopicTypeServiceImpl.findScoreByMore(tptt);
                 */
                if (topicVo.getTopicName() != null) {
                    topicVo.setTopicName(topicVo.getTopicName().replace("&lt;", "<"));
                    topicVo.setTopicName(topicVo.getTopicName().replace("&gt;", ">"));
                    topicVo.setTopicName(topicVo.getTopicName().replace("&nbsp;", ""));
                    String anas = topicVo.getTopicName();
                    if (anas != null && anas.indexOf("&&") >= 0) {
                        String[] ans = anas.split("&&");
                        String an = "";
                        for (int i = 0; i < ans.length; i++) {
                            an += "<p>" + (i + 1) + "." + ans[i] + "</p><br>";
                        }
                        if (an.indexOf("&&") == 0 && (ans[ans.length - 1].equals("") || ans[ans.length - 1].equals(" "))) {
                            an += "<p>" + (ans.length + 1) + ".</p><br>";
                        }
                        topicVo.setTopicName(an);
                    }
                }
                if (topicVo.getAnalyseWord() != null) {
                    topicVo.setAnalyseWord(topicVo.getAnalyseWord().replace("&lt;", "<"));
                    topicVo.setAnalyseWord(topicVo.getAnalyseWord().replace("&gt;", ">"));
                    topicVo.setAnalyseWord(topicVo.getAnalyseWord().replace("&nbsp;", ""));
                    String anas = topicVo.getAnswer();
                    if (anas != null && anas.indexOf("&&") >= 0) {
                        String[] ans = anas.split("&&");
                        String an = "";
                        for (int i = 0; i < ans.length; i++) {
                            an += "<p>" + (i + 1) + "." + ans[i] + "</p><br>";
                        }
                        if (an.indexOf("&&") == 0 && (ans[ans.length - 1].equals("") || ans[ans.length - 1].equals(" "))) {
                            an += "<p>" + (ans.length + 1) + ".</p><br>";
                        }
                        topicVo.setAnswer(an);
                    }
                }
            }
        }
        if (answer != null && answer.getUserAnswer() != null) {
            answer.setUserAnswer(answer.getUserAnswer().replace("&lt;", "<"));
            answer.setUserAnswer(answer.getUserAnswer().replace("&gt;", ">"));
            answer.setUserAnswer(answer.getUserAnswer().replace("&nbsp;", ""));
            String anas = answer.getUserAnswer();
            if (anas != null && anas.indexOf("&&") >= 0) {
                String[] ans = anas.split("&&");
                String an = "";
                for (int i = 0; i < ans.length; i++) {
                    an += "<p>" + (i + 1) + "." + ans[i] + "</p><br>";
                }
                if (anas.indexOf("&&") == 0 && (ans[ans.length - 1].equals("") || ans[ans.length - 1].equals(" "))) {
                    an += "<p>" + (ans.length + 1) + ".</p><br>";
                }
                answer.setUserAnswer(an);
            }
        }

        if (topicVo != null) {
            // 材料题查询其子题
            if (!topicVo.getParentId().equals(0)) {
                TikuTopic t = this.tikuTopicServiceImpl.findTikuTopicById(topicVo.getParentId());
                model.addAttribute("parentTopic", t);
            }
            // 查询该题是否收藏
            TikuUserCollect collect = new TikuUserCollect();
            collect.setTopicId(topicVo.getId());
            collect.setUserId(userId);

            Integer colId = this.tikuUserCollectServiceImpl.findCollectByMore(collect);

            model.addAttribute("colId", colId);
        }

        model.addAttribute("topic", topicVo);
        model.addAttribute("page", pager);
        model.addAttribute("answer", answer);
        return "tiku/resolve/topicDetail";
    }

    /**
     *
     * Class Name: ResolveController.java
     *
     * @Description: 添加收藏
     * @author 周文斌
     * @date 2015-8-27 上午10:57:37
     * @version 1.0
     * @param request
     * @param topicId
     * @return
     */
    @ResponseBody
    @RequestMapping("/collect")
    public JSONObject collect(HttpServletRequest request, Integer topicId, Integer userId) {
        JSONObject json = new JSONObject();
        try {
            // 根据试题id查询试题信息
            TikuTopic topic = this.tikuTopicServiceImpl.findTikuTopicById(topicId);
            TikuUserCollect collect = new TikuUserCollect();
            collect.setAddTime(new Date());
            collect.setCategoryId(topic.getTikuCategoryId());
            collect.setSubjectId(topic.getTikuSubjectId());
            collect.setCompanyId(topic.getCompanyId().toString());
            collect.setUserId(userId);
            collect.setTopicId(topicId);
            collect.setTopicType(topic.getTopicType());
            this.tikuUserCollectServiceImpl.insert(collect);
            json.put("colId", collect.getId());
            json.put("msg", "success");
            return json;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            json.put("msg", "添加收藏出错");
            return json;
        }
    }

    /**
     *
     * Class Name: ResolveController.java
     *
     * @Description: 取消收藏
     * @author 周文斌
     * @date 2015-8-27 上午11:02:37
     * @version 1.0
     * @param request
     * @param collectId
     * @return
     */
    @ResponseBody
    @RequestMapping("/cancelCollect")
    public JSONObject cancelCollect(HttpServletRequest request, Integer collectId) {
        JSONObject json = new JSONObject();
        try {
            // 获得试题id
            TikuUserCollect collect = this.tikuUserCollectServiceImpl.findTikuUserCollectById(collectId);
            this.tikuUserCollectServiceImpl.deleteTikuUserCollectById(collectId);
            json.put("msg", "success");
            json.put("topicId", collect.getTopicId());
            return json;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            json.put("msg", "取消收藏失败");
            return json;
        }
    }

    public static void main(String[] args) {
        System.out.println(TikuConstant.TopicType.name("TOPIC_TYPE_RADIO"));
    }
}
