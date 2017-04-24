package com.yuxin.wx.controller.student;

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

import com.yuxin.wx.model.student.StudentPayLog;
import com.yuxin.wx.api.student.IStudentPayLogService;

/**
 * Controller of StudentPayLog
 * @author wang.zx
 * @date 2014-12-5
 */
@Controller
@RequestMapping("/studentPayLog")
public class StudentPayLogController {
	
	@Autowired
	private IStudentPayLogService studentPayLogServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, StudentPayLog search){
		if (search == null) {
			search = new StudentPayLog();
			// search.setPageSize(20);
		}
		model.addAttribute("list", studentPayLogServiceImpl.findStudentPayLogByPage(search));
		return "studentPayLog/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(StudentPayLog studentPayLog) {
		studentPayLogServiceImpl.insert(studentPayLog);
		return "redirect:/studentPayLog";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(StudentPayLog studentPayLog) {
		studentPayLogServiceImpl.update(studentPayLog);
		return "redirect:/studentPayLog";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		studentPayLogServiceImpl.deleteStudentPayLogById(id);
		return "redirect:/studentPayLog";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public StudentPayLog getJson(Model model, @PathVariable Integer id){
		return studentPayLogServiceImpl.findStudentPayLogById(id);
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
