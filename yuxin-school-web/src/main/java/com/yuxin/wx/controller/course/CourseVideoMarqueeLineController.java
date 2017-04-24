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

import com.yuxin.wx.api.course.ICourseVideoMarqueeLineService;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.course.CourseVideoMarqueeLine;

/**
 * Controller of CourseVideoMarqueeLine
 * @author wang.zx
 * @date 2015-10-12
 */
@Controller
@RequestMapping("/courseVideoMarqueeLine")
public class CourseVideoMarqueeLineController {
	
	@Autowired
	private ICourseVideoMarqueeLineService courseVideoMarqueeLineServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CourseVideoMarqueeLine search){
		if (search == null) {
			search = new CourseVideoMarqueeLine();
			// search.setPageSize(20);
		}
		model.addAttribute("list", courseVideoMarqueeLineServiceImpl.findCourseVideoMarqueeLineByPage(search));
		return "courseVideoMarqueeLine/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(CourseVideoMarqueeLine CourseVideoMarqueeLine) {
		courseVideoMarqueeLineServiceImpl.insert(CourseVideoMarqueeLine);
		return "redirect:/courseVideoMarqueeLine";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(CourseVideoMarqueeLine CourseVideoMarqueeLine) {
		courseVideoMarqueeLineServiceImpl.update(CourseVideoMarqueeLine);
		return "redirect:/courseVideoMarqueeLine";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		courseVideoMarqueeLineServiceImpl.deleteCourseVideoMarqueeLineById(id);
		return "redirect:/courseVideoMarqueeLine";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public CourseVideoMarqueeLine getJson(Model model, @PathVariable Integer id){
		return courseVideoMarqueeLineServiceImpl.findCourseVideoMarqueeLineById(id);
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
