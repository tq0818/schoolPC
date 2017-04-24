package com.yuxin.wx.controller.course;

import java.text.SimpleDateFormat;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

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

import com.yuxin.wx.api.course.ICourseAfterTestContentService;
import com.yuxin.wx.api.course.ICourseAfterTestService;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.course.CourseAfterTest;
import com.yuxin.wx.model.course.CourseVideoLecture;
import com.yuxin.wx.utils.WebUtils;

/**
 * Controller of CourseAfterTest
 * @author chopin
 * @date 2016-9-1
 */
@Controller
@RequestMapping("/courseAfterTest")
public class CourseAfterTestController {
	
	@Autowired
	private ICourseAfterTestService courseAfterTestServiceImpl;
	
	@Autowired
	private ICourseAfterTestContentService courseAfterTestContentServiceImpl; 
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CourseAfterTest search){
		if (search == null) {
			search = new CourseAfterTest();
			// search.setPageSize(20);
		}
		model.addAttribute("list", courseAfterTestServiceImpl.findCourseAfterTestByPage(search));
		return "courseAfterTest/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(CourseAfterTest CourseAfterTest) {
		courseAfterTestServiceImpl.insert(CourseAfterTest);
		return "redirect:/courseAfterTest";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(CourseAfterTest CourseAfterTest) {
		courseAfterTestServiceImpl.update(CourseAfterTest);
		return "redirect:/courseAfterTest";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		courseAfterTestServiceImpl.deleteCourseAfterTestById(id);
		return "redirect:/courseAfterTest";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public CourseAfterTest getJson(Model model, @PathVariable Integer id){
		return courseAfterTestServiceImpl.findCourseAfterTestById(id);
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
	
	@ResponseBody
	@RequestMapping(value="/addTest",method=RequestMethod.POST)
	public CourseAfterTest AddTest(CourseAfterTest test,HttpServletRequest request){
		test.setDelFlag(0);
		test.setUpdateTime(new Date());
		test.setUpdater(WebUtils.getCurrentUserId(request));
		courseAfterTestServiceImpl.insert(test);
		return test;
	}
	
	@ResponseBody
	@RequestMapping(value="/delTest/{id}" ,method=RequestMethod.POST)
	public String delTest(Model model, @PathVariable Integer id){
		CourseAfterTest test = courseAfterTestServiceImpl.findCourseAfterTestById(id);
		if(test != null){
			test.setDelFlag(1);
			courseAfterTestServiceImpl.update(test);
		}
		return "success";
	}
	
	/**
	 * @Description:(改变测验所属的章)
	 * @author chopin
	 * @date 2015-1-20 下午11:55:46
	 * @version 1.0
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/mvTest", method = RequestMethod.POST)
	public CourseAfterTest mvTest(Integer target,Integer lecture){
		CourseAfterTest t = courseAfterTestServiceImpl.findCourseAfterTestById(lecture);
		if(t!=null){
			courseAfterTestServiceImpl.mvTest(target, lecture);
		}
		return t;
	}
	
	@ResponseBody
	@RequestMapping(value="/saveTest",method=RequestMethod.POST)
	public CourseAfterTest saveTest(CourseAfterTest test,HttpServletRequest request,Model model){
		if(test == null)
			return null;
		CourseAfterTest test2 = courseAfterTestServiceImpl.findCourseAfterTestById(test.getId());
		if(test2 == null)
			courseAfterTestServiceImpl.insert(test);
		else{
			if(test2.getTestType()!=null && test.getTestType()!=null ){
				if(((int)test2.getTestType())!=((int)test.getTestType())){
					if(((int)test2.getTestType())== 1){
						courseAfterTestContentServiceImpl.deleteByTestId(test2.getId());
					}
					else if(((int)test2.getTestType()) == 0)
						courseAfterTestContentServiceImpl.deleteByTestId2(test2.getId());
				}
			}
			test.setId(test2.getId());
			courseAfterTestServiceImpl.update(test);
		}
		return test;
	}
}
