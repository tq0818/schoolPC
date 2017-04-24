package com.yuxin.wx.controller.course;

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

import com.yuxin.wx.api.course.ICoursePotocolBindHistoryService;
import com.yuxin.wx.model.course.CoursePotocolBindHistory;

/**
 * Controller of CoursePotocolBindHistory
 * @author chopin
 * @date 2016-11-1
 */
@Controller
@RequestMapping("/coursePotocolBindHistory")
public class CoursePotocolBindHistoryController {
	
	@Autowired
	private ICoursePotocolBindHistoryService coursePotocolBindHistoryServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CoursePotocolBindHistory search){
		if (search == null) {
			search = new CoursePotocolBindHistory();
			// search.setPageSize(20);
		}
		model.addAttribute("list", coursePotocolBindHistoryServiceImpl.findCoursePotocolBindHistoryByPage(search));
		return "coursePotocolBindHistory/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(CoursePotocolBindHistory CoursePotocolBindHistory) {
		coursePotocolBindHistoryServiceImpl.insert(CoursePotocolBindHistory);
		return "redirect:/coursePotocolBindHistory";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(CoursePotocolBindHistory CoursePotocolBindHistory) {
		coursePotocolBindHistoryServiceImpl.update(CoursePotocolBindHistory);
		return "redirect:/coursePotocolBindHistory";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		coursePotocolBindHistoryServiceImpl.deleteCoursePotocolBindHistoryById(id);
		return "redirect:/coursePotocolBindHistory";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public CoursePotocolBindHistory getJson(Model model, @PathVariable Integer id){
		return coursePotocolBindHistoryServiceImpl.findCoursePotocolBindHistoryById(id);
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
