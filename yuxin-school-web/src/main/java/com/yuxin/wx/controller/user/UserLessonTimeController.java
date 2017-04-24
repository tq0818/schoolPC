package com.yuxin.wx.controller.user;

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

import com.yuxin.wx.api.user.IUserLessonTimeService;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.user.UserLessonTime;

/**
 * Controller of UserLessonTime
 * @author wang.zx
 * @date 2015-9-25
 */
@Controller
@RequestMapping("/userLessonTime")
public class UserLessonTimeController {
	
	@Autowired
	private IUserLessonTimeService userLessonTimeServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, UserLessonTime search){
		if (search == null) {
			search = new UserLessonTime();
			// search.setPageSize(20);
		}
		model.addAttribute("list", userLessonTimeServiceImpl.findUserLessonTimeByPage(search));
		return "userLessonTime/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(UserLessonTime UserLessonTime) {
		userLessonTimeServiceImpl.insert(UserLessonTime);
		return "redirect:/userLessonTime";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(UserLessonTime UserLessonTime) {
		userLessonTimeServiceImpl.update(UserLessonTime);
		return "redirect:/userLessonTime";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		userLessonTimeServiceImpl.deleteUserLessonTimeById(id);
		return "redirect:/userLessonTime";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public UserLessonTime getJson(Model model, @PathVariable Integer id){
		return userLessonTimeServiceImpl.findUserLessonTimeById(id);
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
