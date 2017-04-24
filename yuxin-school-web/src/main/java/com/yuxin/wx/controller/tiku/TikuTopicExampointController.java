package com.yuxin.wx.controller.tiku;

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

import com.yuxin.wx.api.tiku.ITikuTopicExampointService;
import com.yuxin.wx.model.tiku.TikuTopicExampoint;

/**
 * Controller of TikuTopicExampoint
 * @author wang.zx
 * @date 2015-7-8
 */
@Controller
@RequestMapping("/tikuTopicExampoint")
public class TikuTopicExampointController {
	
	@Autowired
	private ITikuTopicExampointService tikuTopicExampointServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, TikuTopicExampoint search){
		if (search == null) {
			search = new TikuTopicExampoint();
			// search.setPageSize(20);
		}
		model.addAttribute("list", tikuTopicExampointServiceImpl.findTikuTopicExampointByPage(search));
		return "tikuTopicExampoint/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(TikuTopicExampoint TikuTopicExampoint) {
		tikuTopicExampointServiceImpl.insert(TikuTopicExampoint);
		return "redirect:/tikuTopicExampoint";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(TikuTopicExampoint TikuTopicExampoint) {
		tikuTopicExampointServiceImpl.update(TikuTopicExampoint);
		return "redirect:/tikuTopicExampoint";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		tikuTopicExampointServiceImpl.deleteTikuTopicExampointById(id);
		return "redirect:/tikuTopicExampoint";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public TikuTopicExampoint getJson(Model model, @PathVariable Integer id){
		return tikuTopicExampointServiceImpl.findTikuTopicExampointById(id);
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
