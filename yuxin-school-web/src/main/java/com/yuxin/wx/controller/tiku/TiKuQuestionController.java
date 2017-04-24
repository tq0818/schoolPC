package com.yuxin.wx.controller.tiku;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.tiku.ITikuCategoryService;
import com.yuxin.wx.api.tiku.ITikuChapterService;
import com.yuxin.wx.api.tiku.ITikuExampointService;
import com.yuxin.wx.api.tiku.ITikuPaperTopicService;
import com.yuxin.wx.api.tiku.ITikuSectionService;
import com.yuxin.wx.api.tiku.ITikuSetService;
import com.yuxin.wx.api.tiku.ITikuSubjectService;
import com.yuxin.wx.api.tiku.ITikuTopicExampointService;
import com.yuxin.wx.api.tiku.ITikuTopicOptionService;
import com.yuxin.wx.api.tiku.ITikuTopicService;
import com.yuxin.wx.common.JsonMsg;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.tiku.TikuCategory;
import com.yuxin.wx.model.tiku.TikuChapter;
import com.yuxin.wx.model.tiku.TikuExampoint;
import com.yuxin.wx.model.tiku.TikuPaperTopic;
import com.yuxin.wx.model.tiku.TikuSection;
import com.yuxin.wx.model.tiku.TikuSet;
import com.yuxin.wx.model.tiku.TikuSubject;
import com.yuxin.wx.model.tiku.TikuTopic;
import com.yuxin.wx.model.tiku.TikuTopicExampoint;
import com.yuxin.wx.model.tiku.TikuTopicOption;
import com.yuxin.wx.utils.StringUtil;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.tiku.TikuTopicVo;

@Controller
@RequestMapping("/question")
public class TiKuQuestionController {

    @Autowired
    private ITikuPaperTopicService tikuPaperTopicServiceImpl;

    @Autowired
    private ITikuCategoryService tikuCategoryServiceImpl;

    @Autowired
    private ITikuTopicOptionService tikuTopicOptionServiceImpl;

    @Autowired
    private ITikuTopicExampointService tikuTopicExampointServiceImpl;

    @Autowired
    private ITikuExampointService tikuExampointServiceImpl;

    @Autowired
    private ITikuSectionService tikuSectionServiceImpl;

    @Autowired
    private ITikuChapterService tikuChapterServiceImpl;

    @Autowired
    private ITikuTopicService tikuTopicServiceImpl;

    @Autowired
    private ITikuSetService tikuSetServiceImpl;

    @Autowired
    private ITikuSubjectService tikuSubjectServiceImpl;

    @RequestMapping("/show")
    public String show(Model model, HttpServletRequest request, Integer categoryId, Integer subId, String types, String status, Integer pageNo) {
        // 公司id ，
        Integer companyId = WebUtils.getCurrentCompanyId();
        // 查询设置
        TikuSet tikuSet = new TikuSet();
        tikuSet.setCompanyId(companyId);
        tikuSet = this.tikuSetServiceImpl.findSetByCompanyIdAndCategoryId(tikuSet);
        if (tikuSet == null) {
            return "redirect:/tikuSet/toSet/stuFree";
        }
        // 查询分类下的科目,根据id 排序
        List<TikuSubject> subList = this.tikuSubjectServiceImpl.findSubByCategoryId(categoryId);
        TikuCategory cate = this.tikuCategoryServiceImpl.findTikuCategoryById(categoryId);

        if (subId == null) {
            model.addAttribute("subId", subList.get(0).getId());
        } else {
            model.addAttribute("subId", subId);
        }
        if (status == null) {
            model.addAttribute("status", "");
        } else {
            model.addAttribute("status", status);
        }
        model.addAttribute("types", types);
        model.addAttribute("cate", cate);
        model.addAttribute("tikuSet", tikuSet);
        model.addAttribute("subList", subList);
        model.addAttribute("pageNo", pageNo);
        return "tiku/question/questions";
    }

    /**
     *
     * Class Name: TiKuQuestionController.java
     *
     * @Description: 分页查询试题信息(查询试题，不能包含材料的子题，也不能包含试卷中的题)
     * @author 周文斌
     * @date 2015-7-9 下午8:04:05
     * @version 1.0
     * @param model
     * @param request
     * @param topic
     * @return
     */
    @RequestMapping("/selTopic")
    public String selTopic(Model model, HttpServletRequest request, TikuTopic topic) {

        // 公司id ，
        Integer companyId = WebUtils.getCurrentCompanyId();
        // 查询设置
        TikuSet tikuSet = new TikuSet();
        tikuSet.setCompanyId(companyId);
        tikuSet = this.tikuSetServiceImpl.findSetByCompanyIdAndCategoryId(tikuSet);
        if (topic.getTopicName() != null && !topic.getTopicName().equals("")) {
            topic.setTopicName(topic.getTopicName().replace("<", "&lt;"));
            topic.setTopicName(topic.getTopicName().replace(">", "&gt;"));
            topic.setTopicName(topic.getTopicName().replace("'", "&quot;"));
        }
        topic.setCompanyId(companyId);
        // 此处为查询试题中的题
        topic.setPaperFlag(0);
        // 试题中不能出现子题
        topic.setChildFlag(0);

        List<TikuTopic> topicList = this.tikuTopicServiceImpl.findTopicByEntity(topic);
        Integer count = this.tikuTopicServiceImpl.fingCountByEntity(topic);
        PageFinder<TikuTopic> topicPage = new PageFinder<TikuTopic>(topic.getPage(), topic.getPageSize(), count, topicList);

        model.addAttribute("topicPage", topicPage);
        model.addAttribute("tikuSet", tikuSet);
        // 如果是材料题返回到单独的页面，其他页面风格统一
        if ("TOPIC_TYPE_CASE".equals(topic.getTopicType())) {
            return "tiku/question/caseTopic";
        } else {
            return "tiku/question/topic";
        }
    }

    @RequestMapping("/editQuestion")
    public String editQuestion(Model model, HttpServletRequest request, String types, Integer id, Integer categoryId, Integer subId, String btn, String status,
            Integer pageNo) {
        // 公司id ，
        Integer companyId = WebUtils.getCurrentCompanyId();
        // 查询设置
        TikuSet tikuSet = new TikuSet();
        tikuSet.setCompanyId(companyId);
        tikuSet = this.tikuSetServiceImpl.findSetByCompanyIdAndCategoryId(tikuSet);
        // 查询分类下的科目,根据id 排序
        List<TikuSubject> subList = this.tikuSubjectServiceImpl.findSubByCategoryId(categoryId);

        model.addAttribute("tikuSet", tikuSet);
        model.addAttribute("subList", subList);
        model.addAttribute("types", types);
        model.addAttribute("id", id);
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("subId", subId);
        model.addAttribute("btn", btn);
        model.addAttribute("status", status);
        model.addAttribute("pageNo", pageNo);
        return "tiku/question/editQuestion";
    }

    /**
     *
     * Class Name: TiKuQuestionController.java
     *
     * @Description: 编辑试题信息
     * @author 周文斌
     * @date 2015-7-9 下午8:11:40
     * @version 1.0
     * @param model
     * @param request
     * @param types
     * @param id
     * @return
     */
    @RequestMapping("/edit")
    public String edit(Model model, HttpServletRequest request, String types, Integer id, Integer categoryId, Integer subId, String btn, Integer paperId,
            String isCase, Integer parentId) {
        //
        // 公司id ，
        Integer companyId = WebUtils.getCurrentCompanyId();
        // 查询设置
        TikuSet tikuSet = new TikuSet();
        tikuSet.setCompanyId(companyId);
        tikuSet = this.tikuSetServiceImpl.findSetByCompanyIdAndCategoryId(tikuSet);

        // 此处做标记，是否编辑材料本身
        String flag = "false";
        if ("case".equals(isCase)) {
            flag = "true";
        }
        model.addAttribute("flag", flag);

        if (!btn.equals("create")) {
            // 查询试题信息 及答案

            // 判断是否为材料题
            if ("TOPIC_TYPE_CASE".equals(types)) {
                TikuTopic topic = this.tikuTopicServiceImpl.findTikuTopicById(id);
                if (topic != null) {
                    model.addAttribute("topic", topic);
                    if (parentId == null || parentId == 0) {
                        parentId = topic.getParentId();
                    }
                }
            } else {
                TikuTopicVo topicVo = this.tikuTopicServiceImpl.findTopicAndOptionById(id);
                if (topicVo.getOption() != null && topicVo.getOption().size() > 0) {
                    String content = "";
                    for (TikuTopicOption o : topicVo.getOption()) {
                        if (o.getOptionName() != null) {
                            content = o.getOptionName();
                            content = content.replaceAll("\"", "&quot;");
                            content = content.replaceAll("<", "&lt;");
                            content = content.replaceAll(">", "&gt;");
                            o.setOptionName(content);
                        }
                    }
                }
                model.addAttribute("topicVo", topicVo);
                if (parentId == null || parentId == 0) {
                    parentId = topicVo.getParentId();
                }
            }
        }
        // 查询分类下的科目,根据id 排序
        List<TikuSubject> subList = this.tikuSubjectServiceImpl.findSubByCategoryId(categoryId);

        model.addAttribute("tikuSet", tikuSet);
        model.addAttribute("subList", subList);
        // 查询科目
        TikuSubject sub = this.tikuSubjectServiceImpl.findTikuSubjectById(subId);
        model.addAttribute("types", types);
        model.addAttribute("tikuSet", tikuSet);
        model.addAttribute("sub", sub);
        model.addAttribute("btn", btn);
        model.addAttribute("paperId", paperId);
        if ("TOPIC_TYPE_CASE".equals(types)) {
            parentId = id;
        }
        model.addAttribute("parentId", parentId);
        return "tiku/question/edit";
    }

    /**
     *
     * Class Name: TiKuQuestionController.java
     *
     * @Description: 删除试题
     * @author 周文斌
     * @date 2015-7-9 下午8:28:40
     * @version 1.0
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/delete")
    public JSONObject delete(Integer id) {
        JSONObject json = new JSONObject();
        try {
            // 查询该试题是否存在于试卷中
            List<TikuPaperTopic> paperTopics = this.tikuPaperTopicServiceImpl.findListByTopicId(id);

            if (paperTopics != null && paperTopics.size() > 0) {
                // 更改试题状态为1
                TikuTopic topic = new TikuTopic();
                topic.setId(id);
                topic.setPaperFlag(1);
                this.tikuTopicServiceImpl.update(topic);
            } else {
                // 彻底删除试题
                // 查询是不是材料题
                TikuTopic topic = this.tikuTopicServiceImpl.findTikuTopicById(id);
                List<Integer> topicIds = new ArrayList<Integer>();
                if (topic.getTopicType().equals("TOPIC_TYPE_CASE")) {
                    // 获得子题id
                    topicIds = this.tikuTopicServiceImpl.findSubjectByParentId(id);
                }
                topicIds.add(id);

                this.tikuTopicServiceImpl.delByIds(topicIds);
                // 删除试题相关
                this.tikuTopicExampointServiceImpl.deleteByTopicId(topicIds);
                this.tikuTopicOptionServiceImpl.deleteByTopicId(topicIds);
            }
            // 删除试题
            json.put(JsonMsg.MSG, "已删除");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            json.put(JsonMsg.MSG, JsonMsg.INFORMATION);
        }
        return json;
    }

    /**
     *
     * Class Name: TiKuQuestionController.java
     *
     * @Description:查询章
     * @author 周文斌
     * @date 2015-7-10 下午2:01:07
     * @version 1.0
     * @param request
     * @param categoryId
     * @param subId
     * @return
     */
    @ResponseBody
    @RequestMapping("/selChapter")
    public JSONObject selChapter(HttpServletRequest request, Integer categoryId, Integer subId) {

        Integer companyId = WebUtils.getCurrentCompanyId();

        JSONObject json = new JSONObject();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("companyId", companyId);
        param.put("categoryId", categoryId);
        param.put("subId", subId);
        List<TikuChapter> chapterList = this.tikuChapterServiceImpl.findChapterByCond(param);
        json.put(JsonMsg.MSG, chapterList);
        return json;
    }

    /**
     *
     * Class Name: TiKuQuestionController.java
     *
     * @Description: 查询节
     * @author 周文斌
     * @date 2015-7-10 下午2:03:51
     * @version 1.0
     * @param chapterId
     * @return
     */
    @ResponseBody
    @RequestMapping("/selSection")
    public JSONObject selSection(Integer chapterId) {
        JSONObject json = new JSONObject();
        List<TikuSection> sectionList = this.tikuSectionServiceImpl.findSectionByChapterId(chapterId);
        json.put(JsonMsg.MSG, sectionList);
        return json;
    }

    @ResponseBody
    @RequestMapping("/delOption")
    public JSONObject delOption(Integer id) {
        JSONObject json = new JSONObject();
        try {
            // 查询选项
            TikuTopicOption option = this.tikuTopicOptionServiceImpl.findTikuTopicOptionById(id);

            if (option.getCorrectFlag() == 1) {
                TikuTopic topic = new TikuTopic();
                topic.setId(option.getTopicId());
                topic.setAnswer("");
                this.tikuTopicServiceImpl.update(topic);
            }

            this.tikuTopicOptionServiceImpl.deleteTikuTopicOptionById(id);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            json.put(JsonMsg.MSG, JsonMsg.INFORMATION);
            return json;
        }
        json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
        return json;
    }

    /**
     *
     * Class Name: TiKuQuestionController.java
     *
     * @Description: 查询 点
     * @author 周文斌
     * @date 2015-7-10 下午2:26:24
     * @version 1.0
     * @param sectionId
     * @return
     */
    @ResponseBody
    @RequestMapping("/selPoint")
    public JSONObject selPoint(Integer sectionId) {
        JSONObject json = new JSONObject();
        List<TikuExampoint> pointList = this.tikuExampointServiceImpl.findPointBySectionId(sectionId);
        json.put(JsonMsg.MSG, pointList);
        return json;
    }

    /**
     *
     * Class Name: TiKuQuestionController.java
     *
     * @Description: 单选编辑
     * @author 周文斌
     * @date 2015-7-16 下午5:18:35
     * @version 1.0
     * @param request
     * @param topic
     * @param options
     * @param point
     * @param method
     * @param btn
     * @return
     */
    @ResponseBody
    @RequestMapping("/radioEdit")
    public JSONObject radioEdit(HttpServletRequest request, String topicString, String options, String pointString, String method, String btn, Integer paperId,
            Integer parentId, Float topicScore) {

        TikuTopic topic = JSONObject.parseObject(topicString, TikuTopic.class);
        TikuTopicExampoint point = null;
        List<TikuTopicOption> optionList = null;

        if (pointString != null && pointString.length() > 0) {
            point = JSONObject.parseObject(pointString, TikuTopicExampoint.class);
        }
        if (options != null && options.length() > 0) {
            optionList = JSONObject.parseArray(options, TikuTopicOption.class);
        }
        // 单选 多选 判断 时 答案
        if (topic.getTopicType() != null && (topic.getTopicType().equals("TOPIC_TYPE_RADIO") || topic.getTopicType().equals("TOPIC_TYPE_MULTIPLE")
                || topic.getTopicType().equals("TOPIC_TYPE_TRUE_FALSE") || topic.getTopicType().equals("TOPIC_TYPE_UNDEFINED"))) {
            String rightAnswer = "";
            if (optionList != null && optionList.size() > 0) {
                for (TikuTopicOption o : optionList) {
                    if (o.getCorrectFlag() == 1) {
                        rightAnswer += o.getOptionNo();
                    }
                }
            }
            topic.setAnswer(rightAnswer);
        }

        Integer companyId = WebUtils.getCurrentCompanyId();
        Integer userId = WebUtils.getCurrentUserId(request);
        Date time = new Date();

        if (btn.equals("create")) {
            topic.setCompanyId(companyId);
            topic.setCreator(userId);
            topic.setCreateTime(time);
            topic.setId(null);
            // 判断当前试题是否为试卷中的题
            if (paperId != null) {
                topic.setPaperFlag(1);
            } else {
                topic.setPaperFlag(0);
            }
            // 判断当前题目是否为材料题子题
            Integer pId = topic.getParentId();
            if (pId != null && pId != 0) {
                topic.setChildFlag(1);
            } else {
                topic.setParentId(0);
                topic.setChildFlag(0);
            }
            if (pId != null && pId == 0) {
                topic.setParentId(0);
            }
        } else if (btn.equals("edit")) {
            if (topic.getParentId() != null && topic.getId().equals(topic.getParentId())) {
                topic.setParentId(0);
            }

            /*
             * //判断当前试题是否为试卷中的题 if(paperId != null ){ topic.setPaperFlag(1); }
             */

            topic.setUpdator(userId);
            topic.setUpdateTime(time);
        } else if (btn.equals("audite")) {
            topic.setAuditor(userId);
            topic.setAuditTime(time);
        }
        return this.getCommitResult(method, topic, point, optionList, btn, companyId, paperId, topicScore);
    }

    /**
     * @Description: 添加、编辑填空题
     * @author wzx
     * @date 2015-7-17 下午12:40:01
     * @version 1.0
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "editFilling")
    public JSONObject fillingEdit(HttpServletRequest request, String topicString, String options, String pointString, String method, String btn,
            Integer paperId, Float topicScore) {

        // 试题对象
        TikuTopic topic = JSONObject.parseObject(topicString, TikuTopic.class);
        // 章节知识点对象
        TikuTopicExampoint point = null;
        if (pointString != null && pointString.length() > 0) {
            point = JSONObject.parseObject(pointString, TikuTopicExampoint.class);
        }
        // 试题题目
        String topicName = topic.getTopicName();

        // 解析题干中括号中的内容
        List<Integer> indexList = StringUtil.getIndexOfChar(topicName, "[[", "]]");

        StringBuffer topicNameBlank = new StringBuffer();
        // 填空的答案，存放到List中
        List<String> filling = new ArrayList<String>();
        // 存放截取之后的值
        String subStr = topicName;
        int j = 1;

        // 循环拼接当前判断题的题干
        if (indexList != null) {
            int i = 0;
            for (i = 0; i < indexList.size(); i++) {
                if (i % 2 == 0) {
                    topicNameBlank.append(subStr.substring(0, subStr.indexOf("[[")));
                    subStr = topicName.substring(indexList.get(i) + 2);
                } else {
                    // 将填空的括号添加到题干中
                    topicNameBlank.append("( " + j + " )");
                    // 将填空的内容拿出来
                    filling.add(subStr.substring(0, subStr.indexOf("]]")));
                    subStr = topicName.substring(indexList.get(i) + 2);
                    j++;
                }
            }
            if (i == indexList.size()) {
                topicNameBlank.append(subStr);
            }
        } else {
            topicNameBlank.append(topicName);
        }

        // 赋值上解析之后的内容
        topic.setTopicName(topicNameBlank.toString());
        topic.setTopicNameBlank(topicName);

        // 解析填空题答案
        StringBuffer buffer = new StringBuffer();
        if (filling.size() > 0) {
            for (int i = 0; i < filling.size(); i++) {
                buffer.append(filling.get(i));
                if (i != (filling.size() - 1)) {
                    buffer.append("&&");
                }
            }
        }
        topic.setAnswer(buffer.toString());

        Integer companyId = WebUtils.getCurrentCompanyId();
        Integer userId = WebUtils.getCurrentUserId(request);
        Date time = new Date();

        // 判断当前题目是否为材料题子题
        Integer pId = topic.getParentId();
        if (pId != null && pId != 0) {
            topic.setChildFlag(1);
        } else {
            topic.setParentId(0);
            topic.setChildFlag(0);
        }

        if (paperId == null) {
            topic.setPaperFlag(0);
        } else {
            topic.setPaperFlag(1);
        }

        if (btn.equals("create")) {
        	topic.setId(null);
            topic.setCompanyId(companyId);
            topic.setCreator(userId);
            topic.setCreateTime(time);
        } else if (btn.equals("edit")) {
            topic.setUpdator(userId);
            topic.setUpdateTime(time);
        } else if (btn.equals("audite")) {
            topic.setAuditor(userId);
            topic.setAuditTime(time);
        }

        return this.getCommitResult(method, topic, point, null, btn, companyId, paperId, topicScore);
    }

    // 提交保存 审核
    private JSONObject getCommitResult(String method, TikuTopic topic, TikuTopicExampoint point, List<TikuTopicOption> optionList, String btn,
            Integer companyId, Integer paperId, Float topicScore) {
        JSONObject json = new JSONObject();
        try {
            // 判断当前修改或者保持的题型是否为材料题
            if (topic != null && topic.getTopicType() != null) {
                json.put("topicType", topic.getTopicType());
            }

            // 保存
            if (method.equals("save")) {
                Integer parentId = topic.getParentId();
                if (parentId != null && parentId > 0) {
                    TikuTopic pTopic = this.tikuTopicServiceImpl.findTikuTopicById(parentId);
                    topic.setStatus(pTopic.getStatus());
                } else {
                    if (topic.getId() != null && topic.getId() > 0) {
                        TikuTopic tt = this.tikuTopicServiceImpl.findTikuTopicById(topic.getId());
                        if (tt != null && tt.getStatus() != null && "PAPER_STATUS_WAIT_AUDIT".equals(tt.getStatus())) {
                            topic.setStatus("PAPER_STATUS_WAIT_AUDIT");
                        } else {
                            topic.setStatus("PAPER_STATUS_EDIT");
                        }
                    } else {
                        topic.setStatus("PAPER_STATUS_EDIT");
                    }
                }
                this.editRadio(topic, optionList, point, paperId, json, topicScore);
                if (topic.getParentId() != null && topic.getParentId() > 0 && topic.getPaperFlag() != null && topic.getPaperFlag().equals(1)) {
                    json.put(JsonMsg.MSG, "casePaperSuccess");
                } else if (paperId != null) { // 判断当前是否为试卷
                    json.put(JsonMsg.MSG, "paperSuccess");
                } else if (topic.getParentId() != null && topic.getParentId() > 0) { // 判断当前添加的题是否为材料题
                    json.put(JsonMsg.MSG, "caseSuccess");
                } else {
                    json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
                }
            } else if (method.equals("commit")) { // 审核
                topic.setStatus("PAPER_STATUS_WAIT_AUDIT");
                this.editRadio(topic, optionList, point, paperId, json, topicScore);

                if (topic.getParentId() != null && topic.getParentId() > 0 && topic.getPaperFlag() != null && topic.getPaperFlag().equals(1)) {
                    json.put(JsonMsg.MSG, "casePaperSuccess");
                } else if (paperId != null) { // 判断当前是否为试卷
                    json.put(JsonMsg.MSG, "paperSuccess");
                } else if (topic.getParentId() != null && topic.getParentId() > 0) { // 判断当前添加的题是否为材料题
                    json.put(JsonMsg.MSG, "caseSuccess");
                } else {
                    json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
                }
            } else if (method.equals("commitAndAdd")) {// 审核并继续
                topic.setStatus("PAPER_STATUS_WAIT_AUDIT");
                this.editRadio(topic, optionList, point, paperId, json, topicScore);
                // 查询下一个要审核的 id
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("types", topic.getTopicType());
                param.put("companyId", companyId);
                param.put("cateId", topic.getTikuCategoryId());
                param.put("subId", topic.getTikuSubjectId());

                Integer topicId = this.tikuTopicServiceImpl.findNextId(param);

                json = this.getJsonResult(topicId, topic, btn);
            } else if (method.equals("saveAndPublish")) {// 保存并发布
                topic.setStatus("PAPER_STATUS_PUBLISH");
                this.editRadio(topic, optionList, point, paperId, json, topicScore);

                if (topic.getParentId() != null && topic.getParentId() > 0 && topic.getPaperFlag() != null && topic.getPaperFlag().equals(1)) {
                    json.put(JsonMsg.MSG, "casePaperSuccess");
                } else if (paperId != null) { // 判断当前是否为试卷
                    json.put(JsonMsg.MSG, "paperSuccess");
                } else if (topic.getParentId() != null && topic.getParentId() > 0) { // 判断当前添加的题是否为材料题
                    json.put(JsonMsg.MSG, "caseSuccess");
                } else {
                    json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
                }
            } else if (method.equals("okAndContinue")) {// 通过审核并继续
                topic.setStatus("PAPER_STATUS_PUBLISH");
                this.editRadio(topic, optionList, point, paperId, json, topicScore);
                // 查询下一个要审核的 id
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("types", topic.getTopicType());
                param.put("status", "PAPER_STATUS_WAIT_AUDIT");
                param.put("companyId", companyId);
                param.put("cateId", topic.getTikuCategoryId());
                param.put("subId", topic.getTikuSubjectId());

                Integer topicId = this.tikuTopicServiceImpl.findNextId(param);

                json = this.getJsonResult(topicId, topic, btn);
            } else if (method.equals("noAndContinue")) {// 未通过审核并继续
                topic.setStatus("PAPER_STATUS_AUDIT_FAIL");
                this.editRadio(topic, optionList, point, paperId, json, topicScore);
                // 查询下一个要审核的 id
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("types", topic.getTopicType());
                param.put("status", "PAPER_STATUS_WAIT_AUDIT");
                param.put("companyId", companyId);
                param.put("cateId", topic.getTikuCategoryId());
                param.put("subId", topic.getTikuSubjectId());

                Integer topicId = this.tikuTopicServiceImpl.findNextId(param);

                json = this.getJsonResult(topicId, topic, btn);
            } else if (method.equals("publishAndContinue")) {// 发布并继续
                topic.setStatus("PAPER_STATUS_PUBLISH");
                this.editRadio(topic, optionList, point, paperId, json, topicScore);
                // 查询下一个要审核的 id
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("types", topic.getTopicType());
                /* param.put("status", "PAPER_STATUS_EDIT"); */
                param.put("companyId", companyId);
                param.put("cateId", topic.getTikuCategoryId());
                param.put("subId", topic.getTikuSubjectId());

                Integer topicId = this.tikuTopicServiceImpl.findNextId(param);

                json = this.getJsonResult(topicId, topic, btn);
            } else if (method.equals("auditok")) {
                topic.setStatus("PAPER_STATUS_WAIT_AUDIT");
                this.editRadio(topic, optionList, point, paperId, json, topicScore);

                if (topic.getParentId() != null && topic.getParentId() > 0 && topic.getPaperFlag() != null && topic.getPaperFlag().equals(1)) {
                    json.put(JsonMsg.MSG, "casePaperSuccess");
                } else if (paperId != null) {
                    json.put(JsonMsg.MSG, "paperSuccess");
                } else if (topic.getParentId() != null && topic.getParentId() > 0) { // 判断当前添加的题是否为材料题
                    json.put(JsonMsg.MSG, "caseSuccess");
                } else {
                    json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
                }
            } else if (method.equals("auditno")) {
                topic.setStatus("PAPER_STATUS_AUDIT_FAIL");
                this.editRadio(topic, optionList, point, paperId, json, topicScore);

                if (topic.getParentId() != null && topic.getParentId() > 0 && topic.getPaperFlag() != null && topic.getPaperFlag().equals(1)) {
                    json.put(JsonMsg.MSG, "casePaperSuccess");
                } else if (paperId != null) {
                    json.put(JsonMsg.MSG, "paperSuccess");
                } else if (topic.getParentId() != null && topic.getParentId() > 0) { // 判断当前添加的题是否为材料题
                    json.put(JsonMsg.MSG, "caseSuccess");
                } else {
                    json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            json.put(JsonMsg.MSG, JsonMsg.INFORMATION);
        }
        return json;
    }

    // json结果
    private JSONObject getJsonResult(Integer topicId, TikuTopic topic, String btn) {
        JSONObject json = new JSONObject();
        if (topicId == null) {
            json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
            return json;
        }

        json.put(JsonMsg.MSG, "continue");
        json.put("types", topic.getTopicType());
        json.put("id", topicId);
        json.put("categoryId", topic.getTikuCategoryId());
        json.put("subId", topic.getTikuSubjectId());
        json.put("btn", btn);
        return json;
    }

    // 编辑 条件
    private void editRadio(TikuTopic topic, List<TikuTopicOption> optionList, TikuTopicExampoint point, Integer paperId, JSONObject json, Float topicScore) {
        // 如果是材料题的话，是不可能存在父级Id的
        if ("TOPIC_TYPE_CASE".equals(topic.getTopicType())) {
            topic.setParentId(0);
        }
        Integer topicId = this.tikuTopicServiceImpl.insertTopic(topic, optionList, point, paperId, topicScore);
        json.put("topicId", topicId);
    }

    @ResponseBody
    @RequestMapping("/updateOption")
    public JSONObject updateOption(String options) {
        List<TikuTopicOption> optionList = null;
        if (options != null && options.length() > 0) {
            optionList = JSONObject.parseArray(options, TikuTopicOption.class);
        }
        JSONObject json = new JSONObject();
        try {
            for (TikuTopicOption t : optionList) {
                if (t.getId() != null) {
                    this.tikuTopicOptionServiceImpl.update(t);
                }
            }
            json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            json.put(JsonMsg.MSG, JsonMsg.INFORMATION);
        }
        return json;
    }

    /**
     * @Description: 当前材料题的信息
     * @author zx
     * @date 2015-7-24 下午6:10:12
     * @version 1.0
     * @param request
     * @param topicId
     * @return
     */
    @RequestMapping(value = "editCase")
    public String editTopicCase(HttpServletRequest request, Model model, Integer topicId, Integer cateId, Integer paperId) {
        TikuTopic topic = this.tikuTopicServiceImpl.findTikuTopicById(topicId);

        TikuSet tikuSet = new TikuSet();
        tikuSet.setCompanyId(WebUtils.getCurrentCompanyId());
        tikuSet = this.tikuSetServiceImpl.findSetByCompanyIdAndCategoryId(tikuSet);

        model.addAttribute("tikuSet", tikuSet);
        model.addAttribute("topic", topic);
        model.addAttribute("paperId", paperId);
        return "tiku/question/editCase2";
    }

    /**
     * @Description: 编辑材料题题干本身
     * @author zx
     * @date 2015-7-27 下午5:54:39
     * @version 1.0
     * @return
     */
    @RequestMapping(value = "editCase1")
    public String editTopicCase2(HttpServletRequest request, Model model, Integer topicId
    		,Integer paperId) {
        TikuTopic topic = this.tikuTopicServiceImpl.findTikuTopicById(topicId);
        model.addAttribute("topic", topic);
        model.addAttribute("paperId", paperId);
        return "tiku/question/editCase1";
    }

    /**
     * @Description:为材料添加子题
     * @author zx
     * @date 2015-7-28 上午10:52:59
     * @version 1.0
     * @param request
     * @param model
     * @param topicId
     * @return
     */
    @RequestMapping(value = "addCaseTopic")
    public String addTopicForCase(HttpServletRequest request, Model model, Integer topicId, Integer categoryId, Integer subId, Integer paperId) {
        System.out.println("topicId:" + topicId + ", categoryId:" + categoryId + ", subId:" + subId);
        model.addAttribute("topicId", topicId);
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("subId", subId);
        if (paperId != null && paperId > 0) {
            model.addAttribute("paperId", paperId);
            return "tiku/paper/addPaperCaseTopic";
        }
        return "tiku/question/caseTopic/addCaseTopic";
    }

    /**
     * @Description: 根据材料Id查询该材料下的所有的子题（查看试题中材料的子题）
     * @author 王志兴
     * @date 2015-7-23 下午7:25:19
     * @version 1.0
     * @param model
     * @param request
     * @param paperId
     * @return
     */
    @RequestMapping("/sleChildQuestion")
    public String sleChildQuestion(Model model, HttpServletRequest request, Integer parentId) {
        // 查询试卷下的试题
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("parentId", parentId);
        param.put("paperFlag", "0");
        List<TikuTopic> topics = this.tikuTopicServiceImpl.findChildTopicByParams(param);

        model.addAttribute("topics", topics);
        return "tiku/question/caseTopic/childTopic";
    }

    /**
     * @Description: 根据材料Id查询当前材料下的所有的子题（查看试卷中的材料的子题）
     * @author zx
     * @date 2015-8-6 下午6:22:05
     * @version 1.0
     * @param model
     * @param request
     * @param parentId
     * @return
     */
    @RequestMapping("/slePaperChildQuestion")
    public String slePaperChildQuestion(Model model, HttpServletRequest request, Integer parentId, Integer paperId) {
        List<TikuTopic> topics = this.tikuPaperTopicServiceImpl.findTopicPaperByParentIdAndPaperId(parentId, paperId);
        model.addAttribute("topics", topics);
        return "tiku/question/caseTopic/childTopic";
    }

    /**
     *
     * Class Name: TiKuQuestionController.java
     *
     * @Description: 点击添加已存在试题
     * @author 周文斌
     * @date 2015-7-29 下午4:06:45
     * @version 1.0
     * @param model
     * @param categoryId
     * @param subId
     * @return
     */
    @RequestMapping("/addExistTopic")
    public String addExistTopic(Model model, Integer categoryId, Integer subId, String topicType) {
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("subId", subId);
        model.addAttribute("topicType", topicType);
        return "tiku/paper/topicList";
    }

    /**
     *
     * Class Name: TiKuQuestionController.java
     *
     * @Description: 按条件查看已存在 试题
     * @author 周文斌
     * @date 2015-7-29 下午4:11:35
     * @version 1.0
     * @param model
     * @param request
     * @param categoryId
     * @param subId
     * @param chapterId
     * @param sectionId
     * @param page
     * @param pageSize
     * @param topicType
     * @param topicName
     * @return
     */
    @RequestMapping("/selTopicExist")
    public String selTopicExist(Model model, HttpServletRequest request, Integer paperId, Integer categoryId, Integer subId, Integer chapterId,
            Integer sectionId, Integer page, Integer pageSize, String topicType, String topicName) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("companyId", WebUtils.getCurrentCompanyId());
        param.put("topicType", topicType);
        param.put("topicName", topicName);
        param.put("categoryId", categoryId);
        param.put("subId", subId);
        param.put("page", ((page - 1) * pageSize));
        param.put("pageSize", pageSize);
        param.put("paperId", paperId);

        param.put("chapterId", chapterId);
        param.put("sectionId", sectionId);
        param.put("paperFlag", 0);
        // 判断当前题目是否为材料题
        if ("TOPIC_TYPE_CASE".equals(topicType)) {
            param.put("isCase", null);
        } else {
            param.put("isCase", 1);
        }
        // 根据条件查询
        List<TikuTopic> topicList = this.tikuTopicServiceImpl.findExistTopic(param);
        // 如果topicName的长度大于45的话，截取然后以.....结束
        /*
         * if(topicList != null && topicList.size() > 0){ for(TikuTopic topic :
         * topicList){ String name = topic.getTopicName(); if(name != null &&
         * name.length() > 42){ topic.setAnswer(name.substring(0, 42)+"......");
         * }else{ topic.setAnswer(name); } } }
         */
        // 查询总数
        Integer count = this.tikuTopicServiceImpl.findCountExistTopic(param);
        // 分页
        PageFinder<TikuTopic> topicPage = new PageFinder<TikuTopic>(page, pageSize, count, topicList);

        model.addAttribute("topicPage", topicPage);
        model.addAttribute("topicType", topicType);
        return "tiku/paper/existOption";
    }

    @RequestMapping("/selTopicDetail")
    public String selTopicDetail(Model model, Integer id) {

        TikuTopicVo topicVo = this.tikuTopicServiceImpl.findTopicAndOptionById(id);
        String url = null;

        // 因为材料题比较特殊，故此先判断是否为材料题
        if ("TOPIC_TYPE_CASE".equals(topicVo.getTopicType())) {
            // 查询该材料题下的所有的子题
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("parentId", id);
            param.put("paperFlag", 0);
            List<TikuTopic> childTopics = this.tikuTopicServiceImpl.findChildTopicByParams(param);
            model.addAttribute("childTopics", childTopics);
            url = "tiku/paper/topicDetail/case";
        } else if (topicVo.getTopicType().equals("TOPIC_TYPE_RADIO")) {
            url = "tiku/paper/topicDetail/radio";
        } else if (topicVo.getTopicType().equals("TOPIC_TYPE_MULTIPLE") || topicVo.getTopicType().equals("TOPIC_TYPE_UNDEFINED")) {
            url = "tiku/paper/topicDetail/muitiple";
        } else if (topicVo.getTopicType().equals("TOPIC_TYPE_TRUE_FALSE")) {
            url = "tiku/paper/topicDetail/truefalse";
        } else if (topicVo.getTopicType().equals("TOPIC_TYPE_ANSWER")) {
            url = "tiku/paper/topicDetail/answer";
        } else if (topicVo.getTopicType().equals("TOPIC_TYPE_FILLING")) {
            url = "tiku/paper/topicDetail/filling";
        }
        model.addAttribute("topicVo", topicVo);
        return url;
    }

    @ResponseBody
    @RequestMapping("/selScore")
    public JSONObject selScore(Integer paperId, Integer topicId) {
        JSONObject json = new JSONObject();
        try {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("paperId", paperId);
            param.put("topicId", topicId);
            Float score = this.tikuPaperTopicServiceImpl.findTopicScore(param);
            if (score == null) {
                score = 0f;
            }
            json.put("score", score);
            return json;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            json.put(JsonMsg.MSG, JsonMsg.INFORMATION);
            return json;
        }
    }
}
