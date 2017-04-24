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

import com.yuxin.wx.api.course.IVideoCourseNoteService;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.course.VideoCourseNote;

/**
 * Controller of VideoCourseNote
 * @author wang.zx
 * @date 2015-9-29
 */
@Controller
@RequestMapping("/videoCourseNote")
public class VideoCourseNoteController {
	
	@Autowired
	private IVideoCourseNoteService videoCourseNoteServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, VideoCourseNote search){
		if (search == null) {
			search = new VideoCourseNote();
			// search.setPageSize(20);
		}
		model.addAttribute("list", videoCourseNoteServiceImpl.findVideoCourseNoteByPage(search));
		return "videoCourseNote/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(VideoCourseNote VideoCourseNote) {
		videoCourseNoteServiceImpl.insert(VideoCourseNote);
		return "redirect:/videoCourseNote";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(VideoCourseNote VideoCourseNote) {
		videoCourseNoteServiceImpl.update(VideoCourseNote);
		return "redirect:/videoCourseNote";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		videoCourseNoteServiceImpl.deleteVideoCourseNoteById(id);
		return "redirect:/videoCourseNote";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public VideoCourseNote getJson(Model model, @PathVariable Integer id){
		return videoCourseNoteServiceImpl.findVideoCourseNoteById(id);
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
