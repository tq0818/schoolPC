package com.yuxin.wx.controller.queAns;

import java.text.SimpleDateFormat;
import java.util.Date;

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

import com.yuxin.wx.api.auth.IAuthRoleService;
import com.yuxin.wx.api.queAns.IQuestionAnswerService;
import com.yuxin.wx.api.queAns.IQuestionService;
import com.yuxin.wx.model.queAns.QueQuestion;
import com.yuxin.wx.model.queAns.QuestionAnswer;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.utils.WebUtils;

/**
 * Controller of QuestionAnswer
 * @author wang.zx
 * @date 2015-12-9
 */
@Controller
@RequestMapping("/QuestionAnswer")
public class QuestionAnswerController {
	
	@Autowired
	private IQuestionAnswerService questionAnswerServiceImpl;
	
	@Autowired
	private IQuestionService questionServiceImpl;
	
	@Autowired
	private IAuthRoleService authRoleServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, QuestionAnswer search){
		if (search == null) {
			search = new QuestionAnswer();
			// search.setPageSize(20);
		}
		model.addAttribute("list", questionAnswerServiceImpl.findQuestionAnswerByPage(search));
		return "QuestionAnswer/list";
	}
	
	/**
	 * 
	 * Class Name: QuestionAnswerController.java
	 * @Description: 添加一级评论
	 * @author yuchanglong
	 * @date 2015年12月10日 下午4:08:40
	 * @version 1.0
	 * @param QuestionAnswer
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/add")
	public String add(QuestionAnswer questionAnswer) {
		Users users = WebUtils.getCurrentUser();
		Integer userId = users.getId();
		questionAnswer.setUserId(users.getId());
		questionAnswer.setDelFlag(1);
		questionAnswer.setCreateTime(new Date());
		questionAnswer.setAnswerLevel(1);
		questionAnswer.setCommentCount(0);
		questionAnswer.setReadFlag(0);
		if(authRoleServiceImpl.hasRoleFlag(userId)){
			questionAnswer.setAnswerType("QUESTION_ANSWER_MANAGE");
		}else{  
			questionAnswer.setAnswerType("QUESTION_ANSWER_TEACHER");
		}      
		questionAnswerServiceImpl.insert(questionAnswer);
		QueQuestion entity = new QueQuestion();
		Integer quesId = questionAnswer.getQuestionId();
		entity.setId(quesId);
		QueQuestion question = questionServiceImpl.findQuestionById(quesId);
		Integer ansCount = question.getAnswerCount();
		if(ansCount == null){
			ansCount = 0;
		}
		ansCount = ansCount+1;
		entity.setAnswerCount(ansCount);
		
		entity.setUpdateTime(new Date());
		
		questionServiceImpl.update(entity);
		return "success";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(QuestionAnswer QuestionAnswer) {
		questionAnswerServiceImpl.update(QuestionAnswer);
		return "redirect:/QuestionAnswer";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		questionAnswerServiceImpl.deleteQuestionAnswerById(id);
		return "redirect:/QuestionAnswer";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public QuestionAnswer getJson(Model model, @PathVariable Integer id){
		return questionAnswerServiceImpl.findQuestionAnswerById(id);
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
