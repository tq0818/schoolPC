package com.yuxin.wx.controller.tiku.exam;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

import com.yuxin.wx.api.tiku.exam.ITikuExamPaperRelationService;
import com.yuxin.wx.api.tiku.exam.ITikuExamService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.tiku.exam.TikuExam;
import com.yuxin.wx.model.tiku.exam.TikuExamPaperRelation;
import com.yuxin.wx.vo.tiku.exam.TikuExamPaperRelationVo;

/**
 * Controller of TikuExamPaperRelation
 * 
 * @author wang.zx
 * @date 2016-2-17
 */
@Controller
@RequestMapping("/tikuExamPaperRelation")
public class TikuExamPaperRelationController {

    @Autowired
    private ITikuExamPaperRelationService tikuExamPaperRelationServiceImpl;

    @Autowired
    private ITikuExamService tikuExamServiceImpl;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model, TikuExamPaperRelation search) {
        if (search == null) {
            search = new TikuExamPaperRelation();
            // search.setPageSize(20);
        }
        model.addAttribute("list", tikuExamPaperRelationServiceImpl.findTikuExamPaperRelationByPage(search));
        return "tikuExamPaperRelation/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(TikuExamPaperRelation TikuExamPaperRelation) {
        tikuExamPaperRelationServiceImpl.insert(TikuExamPaperRelation);
        return "redirect:/tikuExamPaperRelation";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(TikuExamPaperRelation TikuExamPaperRelation) {
        tikuExamPaperRelationServiceImpl.update(TikuExamPaperRelation);
        return "redirect:/tikuExamPaperRelation";
    }

    @RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
    public String del(Model model, @PathVariable Integer id) {
        tikuExamPaperRelationServiceImpl.deleteTikuExamPaperRelationById(id);
        return "redirect:/tikuExamPaperRelation";
    }

    @ResponseBody
    @RequestMapping(value = "/{id:\\d+}", method = RequestMethod.GET)
    public TikuExamPaperRelation getJson(Model model, @PathVariable Integer id) {
        return tikuExamPaperRelationServiceImpl.findTikuExamPaperRelationById(id);
    }

    /**
     * 
     * Class Name: TikuExamPaperRelationController.java
     * 
     * @Description:链接到统计列表
     * @author yuchanglong
     * @date 2016年2月18日 上午10:10:48
     * @version 1.0
     * @param model
     * @param examId
     * @return
     */
    @RequestMapping(value = "/toStatisticsIndex/{examId}")
    public String toStatisticsIndex(Model model, @PathVariable Integer examId) {
        TikuExam exam = tikuExamServiceImpl.findTikuExamById(examId);
        model.addAttribute("exam", exam);
        TikuExamPaperRelationVo rate = tikuExamPaperRelationServiceImpl.findRateByExamId(examId);
        List<TikuExamPaperRelationVo> paperList = tikuExamPaperRelationServiceImpl.findPaperByExamId(examId);
        model.addAttribute("paperList", paperList);
        model.addAttribute("rate", rate);
        model.addAttribute("examId", examId);
        return "tiku/exam/statisticsIndex";
    }

    /**
     * 
     * Class Name: TikuExamPaperRelationController.java
     * 
     * @Description:异步加载统计列表
     * @author yuchanglong
     * @date 2016年2月18日 上午10:06:19
     * @version 1.0
     * @param tepr
     * @param model
     * @return
     */
    @RequestMapping(value = "/loadAjaxInfo")
    public String loadAjaxInfo(TikuExamPaperRelation tepr, Model model) {
        tepr.setPageSize(6);
        tepr.setStatus(1);
        PageFinder<TikuExamPaperRelationVo> pageFinder = tikuExamPaperRelationServiceImpl.findByPage(tepr);
        model.addAttribute("pageFinder", pageFinder);
        return "tiku/exam/statisticsAjax";
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
