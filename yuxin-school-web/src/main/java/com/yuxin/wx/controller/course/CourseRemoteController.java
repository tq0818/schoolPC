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

import com.yuxin.wx.api.course.ICourseRemoteService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.course.CourseRemote;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.course.CourseRemoteVo;

/**
 * Controller of CourseRemote
 * @author wang.zx
 * @date 2014-12-5
 */
@Controller
@RequestMapping("/courseRemote")
public class CourseRemoteController {
	
	@Autowired
	private ICourseRemoteService courseRemoteServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CourseRemote search){
		if (search == null) {
			search = new CourseRemote();
			// search.setPageSize(20);
		}
		model.addAttribute("list", courseRemoteServiceImpl.findCourseRemoteByPage(search));
		return "courseRemote/list";
	}
	
	@ResponseBody
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(HttpServletRequest request,CourseRemote courseRemote) {
		int currentUserId=WebUtils.getCurrentUserId(request);
		courseRemote.setCreateTime(new Date());
		courseRemote.setUpdateTime(new Date());
		courseRemote.setCreator(currentUserId);
		courseRemote.setUpdator(currentUserId);
		courseRemote.setSchoolId(WebUtils.getCurrentSchoolId());
		courseRemote.setCompanyId(WebUtils.getCurrentCompanyId());
		courseRemote.setDelFlag(0);
		courseRemoteServiceImpl.insert(courseRemote);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(HttpServletRequest request,CourseRemote courseRemote) {
		int currentUserId=WebUtils.getCurrentUserId(request);
		courseRemote.setUpdateTime(new Date());
		courseRemote.setUpdator(currentUserId);
		courseRemoteServiceImpl.update(courseRemote);
		return "success";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		courseRemoteServiceImpl.deleteCourseRemoteById(id);
		return "redirect:/courseRemote";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public CourseRemote getJson(Model model, @PathVariable Integer id){
		return courseRemoteServiceImpl.findCourseRemoteById(id);
	}
	
	
	
	@RequestMapping(value="/ramoteIndex",method = RequestMethod.GET)
	public String ramoteIndex(Model model, CourseRemote search){
		search.setPage(1);
		search.setPageSize(10);
		PageFinder<CourseRemoteVo> pageFinder=courseRemoteServiceImpl.queryCourseRemoteVoByKeys(search);
		model.addAttribute("pageFinder",pageFinder);
		return "courseRamote/courseRamoteIndex";
	}
	
	@RequestMapping(value="/ramoteAjaxList/{rPath}",method = RequestMethod.POST)
	public String ramoteAjaxList(HttpServletRequest request,Model model,@PathVariable String rPath, CourseRemote search){
		search.setStatus("REMOTE_ENABLE");
		search.setSchoolId(WebUtils.getCurrentSchoolId());
		PageFinder<CourseRemoteVo> pageFinder=courseRemoteServiceImpl.queryCourseRemoteVoByKeys(search);
		model.addAttribute("pageFinder",pageFinder);
		return "courseRamote/"+rPath;
	}
	
	@RequestMapping(value="/ramoteUpdate",method = RequestMethod.POST)
	public String ramoteUpdate(Model model, CourseRemote search){
		CourseRemoteVo courseRemoteVo=courseRemoteServiceImpl.findByOneId(search.getId());
		model.addAttribute("courseRemoteVo",courseRemoteVo);
		return "courseRamote/ajaxUpdateRamoteClassType";
	}
	
	@ResponseBody
	@RequestMapping(value="/checkStop",method = RequestMethod.POST)
	public boolean checkStop(Model model, CourseRemote search){
		Integer count=courseRemoteServiceImpl.checkStopById(search.getId());
		if(count!=null&&count>0){
			return true;
		}else{
			return false;
		}
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
