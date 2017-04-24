package com.yuxin.wx.controller.student;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

import com.yuxin.wx.model.student.StudentPaySlave;
import com.yuxin.wx.vo.student.StudentPaySlaveVo;
import com.yuxin.wx.api.student.IStudentPaySlaveService;

/**
 * Controller of StudentPaySlave
 * @author wang.zx
 * @date 2014-12-5
 */
@Controller
@RequestMapping("/studentPaySlave")
public class StudentPaySlaveController {
	
	@Autowired
	private IStudentPaySlaveService studentPaySlaveServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, StudentPaySlave search){
		if (search == null) {
			search = new StudentPaySlave();
			// search.setPageSize(20);
		}
		model.addAttribute("list", studentPaySlaveServiceImpl.findStudentPaySlaveByPage(search));
		return "studentPaySlave/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(StudentPaySlave studentPaySlave) {
		studentPaySlaveServiceImpl.insert(studentPaySlave);
		return "redirect:/studentPaySlave";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(StudentPaySlave studentPaySlave) {
		studentPaySlaveServiceImpl.update(studentPaySlave);
		return "redirect:/studentPaySlave";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		studentPaySlaveServiceImpl.deleteStudentPaySlaveById(id);
		return "redirect:/studentPaySlave";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public StudentPaySlave getJson(Model model, @PathVariable Integer id){
		return studentPaySlaveServiceImpl.findStudentPaySlaveById(id);
	}
	
	@ResponseBody
	@RequestMapping(value="/findByPayMaster/{mid}", method = RequestMethod.POST)
	public List<StudentPaySlaveVo> findByPayMaster(Model model, @PathVariable Integer mid){
		return studentPaySlaveServiceImpl.findStudentPaySlaveByPayMasterId(mid);
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
