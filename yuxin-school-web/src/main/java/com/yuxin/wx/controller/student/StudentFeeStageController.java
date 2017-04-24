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

import com.yuxin.wx.model.student.StudentFeeStage;
import com.yuxin.wx.api.student.IStudentFeeStageService;

/**
 * Controller of StudentFeeStage
 * @author wang.zx
 * @date 2014-12-5
 */
@Controller
@RequestMapping("/studentFeeStage")
public class StudentFeeStageController {
	
	@Autowired
	private IStudentFeeStageService studentFeeStageServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, StudentFeeStage search){
		if (search == null) {
			search = new StudentFeeStage();
			// search.setPageSize(20);
		}
		model.addAttribute("list", studentFeeStageServiceImpl.findStudentFeeStageByPage(search));
		return "studentFeeStage/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(StudentFeeStage studentFeeStage) {
		studentFeeStageServiceImpl.insert(studentFeeStage);
		return "redirect:/studentFeeStage";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(StudentFeeStage studentFeeStage) {
		studentFeeStageServiceImpl.update(studentFeeStage);
		return "redirect:/studentFeeStage";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		studentFeeStageServiceImpl.deleteStudentFeeStageById(id);
		return "redirect:/studentFeeStage";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public StudentFeeStage getJson(Model model, @PathVariable Integer id){
		return studentFeeStageServiceImpl.findStudentFeeStageById(id);
	}
	
	@ResponseBody
	@RequestMapping(value="/findByPayMaster/{payMasterId}", method = RequestMethod.POST)
	public List<StudentFeeStage> findByPayMaster(Model model, @PathVariable Integer payMasterId){
		return studentFeeStageServiceImpl.findHasPayed(payMasterId);
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
