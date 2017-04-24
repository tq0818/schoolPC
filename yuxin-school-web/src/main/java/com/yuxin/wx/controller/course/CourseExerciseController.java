package com.yuxin.wx.controller.course;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import com.alibaba.fastjson.JSONArray;
import com.yuxin.wx.api.course.ICourseExerciseService;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.course.CourseExercise;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.course.CourseExerciseVo;

/**
 * Controller of CourseExercise
 * @author wang.zx
 * @date 2015-12-29
 */
@Controller
@RequestMapping("/courseExercise")
public class CourseExerciseController {
	
	@Autowired
	private ICourseExerciseService courseExerciseServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CourseExercise search){
		if (search == null) {
			search = new CourseExercise();
			// search.setPageSize(20);
		}
		model.addAttribute("list", courseExerciseServiceImpl.findCourseExerciseByPage(search));
		return "courseExercise/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(CourseExercise CourseExercise) {
		courseExerciseServiceImpl.insert(CourseExercise);
		return "redirect:/courseExercise";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(CourseExercise CourseExercise) {
		courseExerciseServiceImpl.update(CourseExercise);
		return "redirect:/courseExercise";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		courseExerciseServiceImpl.deleteCourseExerciseById(id);
		return "redirect:/courseExercise";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public CourseExercise getJson(Model model, @PathVariable Integer id){
		return courseExerciseServiceImpl.findCourseExerciseById(id);
	}
	@ResponseBody
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String save(HttpServletRequest request,CourseExercise exercise){

		exercise.setCompanyId(WebUtils.getCurrentCompanyId());
		CourseExercise search=new CourseExercise();
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		search.setResourceId(exercise.getResourceId());
		List<CourseExercise> res=courseExerciseServiceImpl.findCourseExerciseByPage(search);

		if(res!=null && !res.isEmpty()){
			exercise.setId(res.get(0).getId());
			if(null==exercise.getResourceType()){
				courseExerciseServiceImpl.deleteCourseExerciseById(res.get(0).getId());
			}else{
				courseExerciseServiceImpl.update(exercise);
			}
		}else{
			courseExerciseServiceImpl.insert(exercise);
		}
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value="/find", method = RequestMethod.POST)
	public CourseExerciseVo find(HttpServletRequest request,CourseExercise exercise){
		exercise.setCompanyId(WebUtils.getCurrentCompanyId());
		List<CourseExerciseVo> cc=courseExerciseServiceImpl.searchCourseExercise(exercise);
		if(cc!=null && !cc.isEmpty()){
			return cc.get(0);
		}
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value="/delExercise",method=RequestMethod.POST)
	public String delExercise(HttpServletRequest request,Model model){
		
		String eidStr = request.getParameter("eid");
		if(eidStr != null && !"".equals(eidStr)){
			int eid = Integer.parseInt(eidStr);
			courseExerciseServiceImpl.deleteCourseExerciseById(eid);
		}
		
		return "success";
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
