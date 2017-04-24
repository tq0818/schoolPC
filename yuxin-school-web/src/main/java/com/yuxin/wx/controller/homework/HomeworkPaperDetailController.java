package com.yuxin.wx.controller.homework;

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

import com.yuxin.wx.api.homework.IHomeworkPaperDetailService;
import com.yuxin.wx.model.homework.HomeworkPaperDetail;

/**
 * Controller of HomeworkPaperDetail
 * @author chopin
 * @date 2016-12-15
 */
@Controller
@RequestMapping("/homeworkPaperDetail")
public class HomeworkPaperDetailController {
	
	@Autowired
	private IHomeworkPaperDetailService homeworkPaperDetailServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, HomeworkPaperDetail search){
		if (search == null) {
			search = new HomeworkPaperDetail();
			// search.setPageSize(20);
		}
		model.addAttribute("list", homeworkPaperDetailServiceImpl.findHomeworkPaperDetailByPage(search));
		return "homeworkPaperDetail/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(HomeworkPaperDetail HomeworkPaperDetail) {
		homeworkPaperDetailServiceImpl.insert(HomeworkPaperDetail);
		return "redirect:/homeworkPaperDetail";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(HomeworkPaperDetail HomeworkPaperDetail) {
		homeworkPaperDetailServiceImpl.update(HomeworkPaperDetail);
		return "redirect:/homeworkPaperDetail";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		homeworkPaperDetailServiceImpl.deleteHomeworkPaperDetailById(id);
		return "redirect:/homeworkPaperDetail";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public HomeworkPaperDetail getJson(Model model, @PathVariable Integer id){
		return homeworkPaperDetailServiceImpl.findHomeworkPaperDetailById(id);
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
