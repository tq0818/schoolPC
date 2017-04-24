package com.yuxin.wx.controller.course;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.course.ICourseVideoLookAuthService;
import com.yuxin.wx.common.JsonMsg;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.course.CourseVideoLookAuth;
import com.yuxin.wx.utils.WebUtils;

/**
 * Controller of CourseVideoLookAuth
 * @author wang.zx
 * @date 2016-3-4
 */
@Controller
@RequestMapping("/courseVideoLookAuth")
public class CourseVideoLookAuthController {
	
	private Log log = LogFactory.getLog("log");
	
	@Autowired
	private ICourseVideoLookAuthService courseVideoLookAuthServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CourseVideoLookAuth search){
		if (search == null) {
			search = new CourseVideoLookAuth();
			// search.setPageSize(20);
		}
		model.addAttribute("list", courseVideoLookAuthServiceImpl.findCourseVideoLookAuthByPage(search));
		return "courseVideoLookAuth/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(CourseVideoLookAuth CourseVideoLookAuth) {
		courseVideoLookAuthServiceImpl.insert(CourseVideoLookAuth);
		return "redirect:/courseVideoLookAuth";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(CourseVideoLookAuth CourseVideoLookAuth) {
		courseVideoLookAuthServiceImpl.update(CourseVideoLookAuth);
		return "redirect:/courseVideoLookAuth";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		courseVideoLookAuthServiceImpl.deleteCourseVideoLookAuthById(id);
		return "redirect:/courseVideoLookAuth";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public CourseVideoLookAuth getJson(Model model, @PathVariable Integer id){
		return courseVideoLookAuthServiceImpl.findCourseVideoLookAuthById(id);
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
	
	/**
	 * 
	 * Class Name: CourseVideoLookAuthController.java
	 * @Description: 修改课程视频设置
	 * @author 周文斌
	 * @date 2016-3-10 下午6:23:42
	 * @version 1.0
	 * @param request
	 * @param cvla
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateOption")
	public JSONObject addOption(HttpServletRequest request,CourseVideoLookAuth cvla){
		JSONObject json = new JSONObject();
		cvla.setUpdateTime(new Date());
		cvla.setUpdator(WebUtils.getCurrentUserId(request));
		log.info("修改课程视频权限");
		try {
			courseVideoLookAuthServiceImpl.update(cvla);
			json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
			return json;
		} catch (Exception e) {
			log.error("修改课程视频权限出错:" + e.getMessage(),e);
			e.printStackTrace();
			json.put(JsonMsg.MSG, "修改课程视频权限出错");
			return json;
		}
	}
}
