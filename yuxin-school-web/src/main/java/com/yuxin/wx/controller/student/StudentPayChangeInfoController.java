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

import com.yuxin.wx.model.student.StudentPayChangeInfo;
import com.yuxin.wx.api.student.IStudentPayChangeInfoService;

/**
 * Controller of StudentPayChangeInfo
 * @author wang.zx
 * @date 2014-12-5
 */
@Controller
@RequestMapping("/studentPayChangeInfo")
public class StudentPayChangeInfoController {
	
	@Autowired
	private IStudentPayChangeInfoService studentPayChangeInfoServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, StudentPayChangeInfo search){
		if (search == null) {
			search = new StudentPayChangeInfo();
			// search.setPageSize(20);
		}
		model.addAttribute("list", studentPayChangeInfoServiceImpl.findStudentPayChangeInfoByPage(search));
		return "studentPayChangeInfo/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(StudentPayChangeInfo studentPayChangeInfo) {
		studentPayChangeInfoServiceImpl.insert(studentPayChangeInfo);
		return "redirect:/studentPayChangeInfo";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(StudentPayChangeInfo studentPayChangeInfo) {
		studentPayChangeInfoServiceImpl.update(studentPayChangeInfo);
		return "redirect:/studentPayChangeInfo";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		studentPayChangeInfoServiceImpl.deleteStudentPayChangeInfoById(id);
		return "redirect:/studentPayChangeInfo";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public StudentPayChangeInfo getJson(Model model, @PathVariable Integer id){
		return studentPayChangeInfoServiceImpl.findStudentPayChangeInfoById(id);
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
