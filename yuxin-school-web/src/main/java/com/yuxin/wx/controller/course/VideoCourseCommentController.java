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

import com.yuxin.wx.api.course.IVideoCourseCommentService;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.course.VideoCourseComment;

/**
 * Controller of VideoCourseComment
 * @author wang.zx
 * @date 2015-9-29
 */
@Controller
@RequestMapping("/videoCourseComment")
public class VideoCourseCommentController {
	
	@Autowired
	private IVideoCourseCommentService videoCourseCommentServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, VideoCourseComment search){
		if (search == null) {
			search = new VideoCourseComment();
			// search.setPageSize(20);
		}
		model.addAttribute("list", videoCourseCommentServiceImpl.findVideoCourseCommentByPage(search));
		return "videoCourseComment/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(VideoCourseComment VideoCourseComment) {
		videoCourseCommentServiceImpl.insert(VideoCourseComment);
		return "redirect:/videoCourseComment";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(VideoCourseComment VideoCourseComment) {
		videoCourseCommentServiceImpl.update(VideoCourseComment);
		return "redirect:/videoCourseComment";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		videoCourseCommentServiceImpl.deleteVideoCourseCommentById(id);
		return "redirect:/videoCourseComment";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public VideoCourseComment getJson(Model model, @PathVariable Integer id){
		return videoCourseCommentServiceImpl.findVideoCourseCommentById(id);
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
