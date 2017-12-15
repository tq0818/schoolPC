package com.yuxin.wx.controller.tiku;

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

import com.alibaba.fastjson.JSONObject;
import com.qiniu.util.Json;
import com.yuxin.wx.api.tiku.ITikuChapterService;
import com.yuxin.wx.api.tiku.ITikuSectionService;
import com.yuxin.wx.api.tiku.ITikuSubjectService;
import com.yuxin.wx.common.JsonMsg;
import com.yuxin.wx.model.tiku.TikuChapter;
import com.yuxin.wx.model.tiku.TikuSection;
import com.yuxin.wx.model.tiku.TikuSubject;

/**
 * Controller of TikuSubject
 *
 * @author wang.zx
 * @date 2015-7-8
 */
@Controller
@RequestMapping("/tikuSubject")
public class TikuSubjectController {

    @Autowired
    private ITikuSubjectService tikuSubjectServiceImpl;

    @Autowired
    private ITikuChapterService tikuChapterServiceImpl;

    @Autowired
    private ITikuSectionService tikuSectionServiceImpl;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model, TikuSubject search) {
        if (search == null) {
            search = new TikuSubject();
            // search.setPageSize(20);
        }
        model.addAttribute("list", this.tikuSubjectServiceImpl.findTikuSubjectByPage(search));
        return "tikuSubject/list";
    }

    @ResponseBody
    @RequestMapping(value = "/add")
    public String add(TikuSubject TikuSubject) {
        this.tikuSubjectServiceImpl.insert(TikuSubject);
        return "success";
    }
    
    @ResponseBody
    @RequestMapping(value = "/add1")
    public JSONObject add1(TikuSubject tikuSubject) {
    	JSONObject json=new JSONObject();
    	try {
    		this.tikuSubjectServiceImpl.insert(tikuSubject);
    		json.put("subId", tikuSubject.getId());
    		json.put(JsonMsg.RESULT, JsonMsg.SUCCESS);
		} catch (Exception e) {
			json.put(JsonMsg.RESULT, JsonMsg.SUCCESS);
		}
        return json;
    }
    

    @ResponseBody
    @RequestMapping(value = "/update")
    public String update(TikuSubject TikuSubject) {
        this.tikuSubjectServiceImpl.update(TikuSubject);
        return "success";
    }

    @RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
    public String del(Model model, @PathVariable Integer id) {
        this.tikuSubjectServiceImpl.deleteTikuSubjectById(id);
        return "redirect:/tikuSubject";
    }

    @ResponseBody
    @RequestMapping(value = "/{id:\\d+}", method = RequestMethod.GET)
    public TikuSubject getJson(Model model, @PathVariable Integer id) {
        return this.tikuSubjectServiceImpl.findTikuSubjectById(id);
    }

    /**
     *
     * Class Name: TikuSubjectController.java
     *
     * @Description: 插入科目并返回科目Id
     * @author yuchanglong
     * @date 2015年7月9日 下午4:58:57
     * @version 1.0
     * @param subject
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getSubjectIdByInsert")
    public Integer getSubjectIdByInsert(TikuSubject subject) {
        subject.setDelFlag(0);
        this.tikuSubjectServiceImpl.insert(subject);
        return subject.getId();
    }

    /**
     *
     * Class Name: TikuSubjectController.java
     *
     * @Description: 题库设置加载科目信息
     * @author yuchanglong
     * @date 2015年7月15日 下午2:45:01
     * @version 1.0
     * @param subject
     * @param model
     * @return
     */
    @RequestMapping(value = "/loadSubject")
    public String loadSubject(TikuSubject subject, Model model) {
        List<TikuSubject> subList = this.tikuSubjectServiceImpl.findTikuSubject(subject);
        model.addAttribute("subList", subList);
        return "tiku/set/subAjax";
    }

    /**
     *
     * Class Name: TikuSubjectController.java
     *
     * @Description: 通过id删除科目
     * @author yuchanglong
     * @date 2015年7月20日 上午11:14:30
     * @version 1.0
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delSubById")
    public String delSubById(Integer id) {
        this.tikuSubjectServiceImpl.deleteTikuSubjectById(id);
        return "success";
    }

    /**
     *
     * Class Name: TikuSubjectController.java
     *
     * @Description: 删除科目前检查是否存在章节信息
     * @author yuchanglong
     * @date 2015年7月28日 上午11:45:50
     * @version 1.0
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/checkSubjectIsHasChapter")
    public Boolean checkSubjectIsHasChapter(Integer subId) {
        // 查询该科目下是否存在章
        TikuChapter tikuChapterSearch = new TikuChapter();
        tikuChapterSearch.setTikuSubjectId(subId);
        if (this.tikuChapterServiceImpl.findTikuChapterByPage(tikuChapterSearch).size() > 0) {
            return true;
        }
        // 查询该科目下是否存在节
        TikuSection tkuSectionSearch = new TikuSection();
        tkuSectionSearch.setTikuSubjectId(subId);
        if (this.tikuSectionServiceImpl.findTikuSectionByPage(tkuSectionSearch).size() > 0) {
            return true;
        }
        // 查询该科目下是否存在题目
        Integer count = this.tikuChapterServiceImpl.findInfoBySubId(subId);
        if (count > 0) {
            return true;
        }
        return false;
    }

    @ResponseBody
    @RequestMapping(value = "/checkSubName")
    public Boolean checkSubName(TikuSubject subject) {
        Integer count = this.tikuSubjectServiceImpl.findCountBySubName(subject);
        if (count > 0) {
            return false;
        }
        return true;
    }

    @ResponseBody
    @RequestMapping(value = "/getList/{id}")
    public List<TikuSubject> getList(@PathVariable Integer id) {
        List<TikuSubject> result = this.tikuSubjectServiceImpl.findSubByCategoryId(id);
        return result;
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
