package com.yuxin.wx.controller.queAns;

import java.text.SimpleDateFormat;
import java.util.Date;

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

import com.yuxin.wx.api.queAns.IQuestionClassifyService;
import com.yuxin.wx.model.queAns.QuestionClassify;
import com.yuxin.wx.utils.WebUtils;

/**
 * Controller of QuestionClassify
 * @author wang.zx
 * @date 2015-12-9
 */
@Controller
@RequestMapping("/QuestionClassify")
public class QuestionClassifyController {
	
	@Autowired
	private IQuestionClassifyService questionClassifyServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, QuestionClassify search){
		if (search == null) {
			search = new QuestionClassify();
			// search.setPageSize(20);
		}
		model.addAttribute("list", questionClassifyServiceImpl.findQuestionClassifyByPage(search));
		return "QuestionClassify/list";
	}
	
	@ResponseBody
	@RequestMapping(value="/add")
	public String add(QuestionClassify QuestionClassify) {
		Integer companyId = WebUtils.getCurrentCompanyId();
		Integer schoolId = WebUtils.getCurrentSchoolId();
		QuestionClassify.setCompanyId(companyId);
		QuestionClassify.setSchoolId(schoolId);
		QuestionClassify.setCreateTime(new Date());
		QuestionClassify.setDelFlag(1);
		questionClassifyServiceImpl.insert(QuestionClassify);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value="/update")
	public String update(QuestionClassify QuestionClassify) {
		questionClassifyServiceImpl.update(QuestionClassify);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value="/del")
	public String del(Model model,Integer id) {
		questionClassifyServiceImpl.deleteQuestionClassifyById(id);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public QuestionClassify getJson(Model model, @PathVariable Integer id){
		return questionClassifyServiceImpl.findQuestionClassifyById(id);
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
