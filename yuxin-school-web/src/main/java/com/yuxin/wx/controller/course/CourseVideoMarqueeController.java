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

import com.yuxin.wx.api.course.ICourseVideoMarqueeService;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.course.CourseVideoMarquee;

/**
 * Controller of CourseVideoMarquee
 * @author wang.zx
 * @date 2015-10-12
 */
@Controller
@RequestMapping("/courseVideoMarquee")
public class CourseVideoMarqueeController {
	
	@Autowired
	private ICourseVideoMarqueeService courseVideoMarqueeServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CourseVideoMarquee search){
		if (search == null) {
			search = new CourseVideoMarquee();
			// search.setPageSize(20);
		}
		model.addAttribute("list", courseVideoMarqueeServiceImpl.findCourseVideoMarqueeByPage(search));
		return "courseVideoMarquee/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(CourseVideoMarquee CourseVideoMarquee) {
		courseVideoMarqueeServiceImpl.insert(CourseVideoMarquee);
		return "redirect:/courseVideoMarquee";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(CourseVideoMarquee CourseVideoMarquee) {
		courseVideoMarqueeServiceImpl.update(CourseVideoMarquee);
		return "redirect:/courseVideoMarquee";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		courseVideoMarqueeServiceImpl.deleteCourseVideoMarqueeById(id);
		return "redirect:/courseVideoMarquee";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public CourseVideoMarquee getJson(Model model, @PathVariable Integer id){
		return courseVideoMarqueeServiceImpl.findCourseVideoMarqueeById(id);
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
