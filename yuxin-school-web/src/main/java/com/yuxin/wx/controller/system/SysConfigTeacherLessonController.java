package com.yuxin.wx.controller.system;

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

import com.yuxin.wx.model.system.SysConfigTeacherLesson;
import com.yuxin.wx.api.system.ISysConfigTeacherLessonService;

/**
 * Controller of SysConfigTeacherLesson
 * @author wang.zx
 * @date 2014-12-5
 */
@Controller
@RequestMapping("/sysConfigTeacherLesson")
public class SysConfigTeacherLessonController {
	
	@Autowired
	private ISysConfigTeacherLessonService sysConfigTeacherLessonServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, SysConfigTeacherLesson search){
		if (search == null) {
			search = new SysConfigTeacherLesson();
			// search.setPageSize(20);
		}
		model.addAttribute("list", sysConfigTeacherLessonServiceImpl.findSysConfigTeacherLessonByPage(search));
		return "sysConfigTeacherLesson/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(SysConfigTeacherLesson sysConfigTeacherLesson) {
		sysConfigTeacherLessonServiceImpl.insert(sysConfigTeacherLesson);
		return "redirect:/sysConfigTeacherLesson";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(SysConfigTeacherLesson sysConfigTeacherLesson) {
		sysConfigTeacherLessonServiceImpl.update(sysConfigTeacherLesson);
		return "redirect:/sysConfigTeacherLesson";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		sysConfigTeacherLessonServiceImpl.deleteSysConfigTeacherLessonById(id);
		return "redirect:/sysConfigTeacherLesson";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public SysConfigTeacherLesson getJson(Model model, @PathVariable Integer id){
		return sysConfigTeacherLessonServiceImpl.findSysConfigTeacherLessonById(id);
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
